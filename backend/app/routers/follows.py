from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

from app import models, schemas
from app.database import get_db

router = APIRouter(prefix="/follows", tags=["follows"])


@router.post("")
def follow_user(payload: schemas.FollowCreate, db: Session = Depends(get_db)):
    existing = (
        db.query(models.Follow)
        .filter(
            models.Follow.follower_id == payload.follower_id,
            models.Follow.followed_id == payload.followed_id,
        )
        .first()
    )
    if existing:
        raise HTTPException(status_code=409, detail="Folgt bereits")

    follow = models.Follow(follower_id=payload.follower_id, followed_id=payload.followed_id)
    db.add(follow)
    db.commit()
    return {"status": "ok"}


@router.delete("")
def unfollow_user(follower_id: str, followed_id: str, db: Session = Depends(get_db)):
    follow = (
        db.query(models.Follow)
        .filter(models.Follow.follower_id == follower_id, models.Follow.followed_id == followed_id)
        .first()
    )
    if not follow:
        raise HTTPException(status_code=404, detail="Follow Eintrag nicht gefunden")
    db.delete(follow)
    db.commit()
    return {"status": "ok"}


@router.get("/followers/{user_id}", response_model=list[schemas.UserOut])
def get_followers(user_id: str, db: Session = Depends(get_db)):
    follows = db.query(models.Follow).filter(models.Follow.followed_id == user_id).all()
    follower_ids = [f.follower_id for f in follows]
    return db.query(models.User).filter(models.User.id.in_(follower_ids)).all()


@router.get("/following/{user_id}", response_model=list[schemas.UserOut])
def get_following(user_id: str, db: Session = Depends(get_db)):
    follows = db.query(models.Follow).filter(models.Follow.follower_id == user_id).all()
    followed_ids = [f.followed_id for f in follows]
    return db.query(models.User).filter(models.User.id.in_(followed_ids)).all()
