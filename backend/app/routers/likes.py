from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

from app import models, schemas
from app.database import get_db
from app.services import notification_service

router = APIRouter(prefix="/likes", tags=["likes"])


@router.post("", response_model=schemas.LikeOut)
async def create_like(payload: schemas.LikeCreate, db: Session = Depends(get_db)):
    post = db.query(models.Post).filter(models.Post.id == payload.post_id).first()
    if not post:
        raise HTTPException(status_code=404, detail="Beitrag nicht gefunden")

    existing = (
        db.query(models.Like)
        .filter(models.Like.post_id == payload.post_id, models.Like.author_id == payload.author_id)
        .first()
    )
    if existing:
        raise HTTPException(status_code=409, detail="Bereits geliked")

    author = db.query(models.User).filter(models.User.id == payload.author_id).first()
    if not author:
        raise HTTPException(status_code=404, detail="Autor nicht gefunden")

    like = models.Like(post_id=payload.post_id, author_id=payload.author_id)
    db.add(like)
    db.commit()
    db.refresh(like)

    post_author = db.query(models.User).filter(models.User.id == post.author_id).first()
    if post_author and post_author.is_human and not author.is_human:
        await notification_service.notify_human("like", author.name, f"hat deinen Beitrag geliked")

    return like


@router.delete("/{like_id}")
def delete_like(like_id: str, db: Session = Depends(get_db)):
    like = db.query(models.Like).filter(models.Like.id == like_id).first()
    if not like:
        raise HTTPException(status_code=404, detail="Like nicht gefunden")
    db.delete(like)
    db.commit()
    return {"status": "ok"}
