from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from sqlalchemy import func

from app import models, schemas
from app.database import get_db

router = APIRouter(prefix="/posts", tags=["posts"])


def _to_post_out(post: models.Post, db: Session) -> schemas.PostOut:
    like_count = db.query(func.count(models.Like.id)).filter(models.Like.post_id == post.id).scalar()
    comment_count = db.query(func.count(models.Comment.id)).filter(models.Comment.post_id == post.id).scalar()
    return schemas.PostOut(
        id=post.id,
        author_id=post.author_id,
        image_url=post.image_url,
        caption=post.caption,
        topic=post.topic,
        created_at=post.created_at,
        like_count=like_count or 0,
        comment_count=comment_count or 0,
    )


@router.post("", response_model=schemas.PostOut)
def create_post(payload: schemas.PostCreate, db: Session = Depends(get_db)):
    """
    Erstellt einen Post. Wird sowohl vom echten Nutzer beim Hochladen eines
    eigenen Fotos genutzt, als auch intern vom Scheduler für Persona Posts.
    """
    author = db.query(models.User).filter(models.User.id == payload.author_id).first()
    if not author:
        raise HTTPException(status_code=404, detail="Autor nicht gefunden")

    post = models.Post(
        author_id=payload.author_id,
        image_url=payload.image_url,
        caption=payload.caption,
        topic=payload.topic,
    )
    db.add(post)
    db.commit()
    db.refresh(post)
    return _to_post_out(post, db)


@router.get("/feed", response_model=list[schemas.PostOut])
def get_feed(user_id: str, limit: int = 30, db: Session = Depends(get_db)):
    """
    Liefert den Feed für user_id. Platzhalter Logik: aktuell rein chronologisch,
    gemischt aus Posts aller gefolgten Personas. Ein algorithmisches Ranking
    (zum Beispiel nach Engagement oder Relevanz) ist als nächster Ausbauschritt
    vorgesehen, ohne dass sich die Response Struktur ändern müsste.
    """
    followed_ids = [
        f.followed_id for f in db.query(models.Follow).filter(models.Follow.follower_id == user_id).all()
    ]
    if not followed_ids:
        return []

    posts = (
        db.query(models.Post)
        .filter(models.Post.author_id.in_(followed_ids))
        .order_by(models.Post.created_at.desc())
        .limit(limit)
        .all()
    )
    return [_to_post_out(p, db) for p in posts]


@router.get("/{post_id}", response_model=schemas.PostOut)
def get_post(post_id: str, db: Session = Depends(get_db)):
    post = db.query(models.Post).filter(models.Post.id == post_id).first()
    if not post:
        raise HTTPException(status_code=404, detail="Beitrag nicht gefunden")
    return _to_post_out(post, db)
