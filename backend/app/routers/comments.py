from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

from app import models, schemas
from app.database import get_db
from app.services import notification_service

router = APIRouter(prefix="/comments", tags=["comments"])


@router.post("", response_model=schemas.CommentOut)
async def create_comment(payload: schemas.CommentCreate, db: Session = Depends(get_db)):
    post = db.query(models.Post).filter(models.Post.id == payload.post_id).first()
    if not post:
        raise HTTPException(status_code=404, detail="Beitrag nicht gefunden")

    author = db.query(models.User).filter(models.User.id == payload.author_id).first()
    if not author:
        raise HTTPException(status_code=404, detail="Autor nicht gefunden")

    comment = models.Comment(
        post_id=payload.post_id,
        author_id=payload.author_id,
        text=payload.text,
        parent_comment_id=payload.parent_comment_id,
    )
    db.add(comment)
    db.commit()
    db.refresh(comment)

    # Wenn eine Persona unter dem Beitrag des echten Nutzers kommentiert, Push auslösen.
    post_author = db.query(models.User).filter(models.User.id == post.author_id).first()
    if post_author and post_author.is_human and not author.is_human:
        await notification_service.notify_human("comment", author.name, comment.text)

    return comment


@router.get("/post/{post_id}", response_model=list[schemas.CommentOut])
def get_comments_for_post(post_id: str, db: Session = Depends(get_db)):
    return (
        db.query(models.Comment)
        .filter(models.Comment.post_id == post_id)
        .order_by(models.Comment.created_at.asc())
        .all()
    )
