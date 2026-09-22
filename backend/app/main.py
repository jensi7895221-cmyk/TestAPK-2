from contextlib import asynccontextmanager

from dotenv import load_dotenv

# Muss vor allen App-Importen laufen, da database.py, llm_service.py und
# image_service.py ihre Umgebungsvariablen bereits beim Import lesen.
load_dotenv()

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.database import Base, engine, SessionLocal
from app import models
from app.personas_loader import load_personas
from app.scheduler import start_scheduler, _get_or_create_persona_user
from app.routers import users, posts, comments, likes, messages, follows, stories


@asynccontextmanager
async def lifespan(app: FastAPI):
    # Tabellen anlegen, falls noch nicht vorhanden.
    Base.metadata.create_all(bind=engine)

    # Alle Personas aus personas.json als User Einträge sicherstellen (Seeding).
    db = SessionLocal()
    try:
        for persona in load_personas():
            _get_or_create_persona_user(db, persona)
    finally:
        db.close()

    start_scheduler()
    yield
    # Kein explizites Herunterfahren des Schedulers nötig für dieses Grundgerüst.


app = FastAPI(title="Social Feed KI Community API", lifespan=lifespan)

# CORS offen für die Entwicklung, in Produktion auf die App Domain einschränken.
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_methods=["*"],
    allow_headers=["*"],
)

app.include_router(users.router)
app.include_router(posts.router)
app.include_router(comments.router)
app.include_router(likes.router)
app.include_router(messages.router)
app.include_router(follows.router)
app.include_router(stories.router)


@app.get("/health")
def health_check():
    return {"status": "ok"}
