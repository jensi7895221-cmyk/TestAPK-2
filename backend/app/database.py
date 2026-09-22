"""
Datenbank Setup. Standardmäßig SQLite für lokale Entwicklung, im Datenmodell aber
so gehalten, dass ein Wechsel auf Postgres nur eine Änderung der DATABASE_URL braucht.
"""
import os
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base

DATABASE_URL = os.getenv("DATABASE_URL", "sqlite:///./social_feed.db")

connect_args = {"check_same_thread": False} if DATABASE_URL.startswith("sqlite") else {}
engine = create_engine(DATABASE_URL, connect_args=connect_args)

SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()


def get_db():
    """Dependency für FastAPI Routen, liefert eine DB Session je Request."""
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()
