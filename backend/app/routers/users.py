from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

from app import models, schemas
from app.database import get_db

router = APIRouter(prefix="/users", tags=["users"])


@router.post("", response_model=schemas.UserOut)
def create_human_user(payload: schemas.UserCreate, db: Session = Depends(get_db)):
    """Legt den einen echten Nutzer an. is_human wird hier fest auf True gesetzt."""
    existing = db.query(models.User).filter(models.User.username == payload.username).first()
    if existing:
        raise HTTPException(status_code=409, detail="Username bereits vergeben")

    user = models.User(
        name=payload.name,
        username=payload.username,
        avatar_url=payload.avatar_url,
        bio=payload.bio,
        is_human=True,
    )
    db.add(user)
    db.commit()
    db.refresh(user)
    return user


@router.get("/{user_id}", response_model=schemas.UserOut)
def get_user(user_id: str, db: Session = Depends(get_db)):
    user = db.query(models.User).filter(models.User.id == user_id).first()
    if not user:
        raise HTTPException(status_code=404, detail="Nutzer nicht gefunden")
    return user


@router.get("", response_model=list[schemas.UserOut])
def list_users(is_human: bool | None = None, db: Session = Depends(get_db)):
    query = db.query(models.User)
    if is_human is not None:
        query = query.filter(models.User.is_human == is_human)
    return query.all()
