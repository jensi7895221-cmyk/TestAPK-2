from datetime import datetime, timedelta

from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session

from app import models, schemas
from app.database import get_db

router = APIRouter(prefix="/stories", tags=["stories"])

STORY_LIFETIME = timedelta(hours=24)


@router.get("", response_model=list[schemas.StoryOut])
def get_active_stories(db: Session = Depends(get_db)):
    """Liefert alle Stories, die noch nicht abgelaufen sind."""
    now = datetime.utcnow()
    return (
        db.query(models.Story)
        .filter(models.Story.expires_at > now)
        .order_by(models.Story.created_at.desc())
        .all()
    )


@router.post("", response_model=schemas.StoryOut)
def create_story(author_id: str, media_url: str, text: str | None = None, db: Session = Depends(get_db)):
    now = datetime.utcnow()
    story = models.Story(
        author_id=author_id,
        media_url=media_url,
        text=text,
        created_at=now,
        expires_at=now + STORY_LIFETIME,
    )
    db.add(story)
    db.commit()
    db.refresh(story)
    return story
