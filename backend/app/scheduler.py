"""
Steuert die eigenständige Aktivität der Personas: neue Posts, Likes und
Kommentare zu bestehenden Beiträgen. Läuft als wiederkehrender Background Job
über APScheduler. Zeitpunkte pro Persona sind bewusst zufällig gestreut
(siehe posting_frequency und reaction_delay je Persona), damit das Verhalten
nicht mechanisch wirkt.

Dies ist das Grundgerüst mit einer einfachen, aber vollständigen Job Logik.
Feintuning (zum Beispiel Vermeidung von Doppel Kommentaren am selben Tag,
Tagesrhythmus pro Persona) ist als Platzhalter markiert.
"""
import asyncio
import random
from datetime import datetime, timedelta

from apscheduler.schedulers.asyncio import AsyncIOScheduler
from sqlalchemy.orm import Session

from app.database import SessionLocal
from app import models
from app.personas_loader import load_personas
from app.services import llm_service, image_service, notification_service

scheduler = AsyncIOScheduler()


def start_scheduler() -> None:
    # Alle 10 Minuten prüfen, ob eine Persona gerade eine Aktion auslösen soll.
    # Die tatsächliche Häufigkeit pro Persona wird über engagement_rate und
    # posting_frequency_hours_avg in der Job Logik selbst gesteuert.
    scheduler.add_job(run_persona_activity_cycle, "interval", minutes=10, id="persona_activity_cycle")
    scheduler.start()


async def run_persona_activity_cycle() -> None:
    db = SessionLocal()
    try:
        personas = load_personas()
        for persona in personas:
            if _is_in_active_hours(persona):
                await _maybe_create_post(db, persona)
                await _maybe_react_to_recent_posts(db, persona)
    finally:
        db.close()


def _is_in_active_hours(persona: dict) -> bool:
    """Prüft grob, ob die aktuelle Uhrzeit in einem der activeHours Fenster liegt."""
    now_hour = datetime.utcnow().hour
    for window in persona.get("activeHours", []):
        start_str, end_str = window.split("-")
        start_hour = int(start_str.split(":")[0])
        end_hour = int(end_str.split(":")[0])
        if start_hour <= now_hour < end_hour or (end_hour < start_hour and (now_hour >= start_hour or now_hour < end_hour)):
            return True
    # TODO: Wenn activeHours fehlt oder leer ist, Fallback auf "immer aktiv".
    return len(persona.get("activeHours", [])) == 0


async def _maybe_create_post(db: Session, persona: dict) -> None:
    persona_user = _get_or_create_persona_user(db, persona)

    last_post = (
        db.query(models.Post)
        .filter(models.Post.author_id == persona_user.id)
        .order_by(models.Post.created_at.desc())
        .first()
    )
    avg_hours = persona.get("postingFrequencyHours", {}).get("avg", 48)
    spread_hours = persona.get("postingFrequencyHours", {}).get("spreadHours", 12)
    next_due = (last_post.created_at if last_post else datetime.min) + timedelta(
        hours=random.uniform(avg_hours - spread_hours, avg_hours + spread_hours)
    )

    if datetime.utcnow() < next_due:
        return

    topic = persona.get("category", "Alltag")
    image_url = await image_service.get_image_for_post(persona.get("category", "default"), topic)
    caption = await llm_service.generate_caption(persona, topic)

    post = models.Post(author_id=persona_user.id, image_url=image_url, caption=caption, topic=topic)
    db.add(post)
    db.commit()


async def _maybe_react_to_recent_posts(db: Session, persona: dict) -> None:
    persona_user = _get_or_create_persona_user(db, persona)
    engagement_rate = persona.get("engagementRate", 0.3)

    recent_posts = (
        db.query(models.Post)
        .filter(models.Post.author_id != persona_user.id)
        .filter(models.Post.created_at > datetime.utcnow() - timedelta(days=2))
        .all()
    )

    for post in recent_posts:
        if random.random() > engagement_rate:
            continue

        already_liked = (
            db.query(models.Like)
            .filter(models.Like.post_id == post.id, models.Like.author_id == persona_user.id)
            .first()
        )
        if not already_liked:
            db.add(models.Like(post_id=post.id, author_id=persona_user.id))
            db.commit()

            post_author = db.query(models.User).filter(models.User.id == post.author_id).first()
            if post_author and post_author.is_human:
                await notification_service.notify_human("like", persona_user.name, "hat deinen Beitrag geliked")

        # Kommentar nur mit reduzierter Wahrscheinlichkeit gegenüber dem Like, wirkt sonst unglaubwürdig.
        if random.random() < engagement_rate * 0.4:
            already_commented = (
                db.query(models.Comment)
                .filter(models.Comment.post_id == post.id, models.Comment.author_id == persona_user.id)
                .first()
            )
            if not already_commented:
                post_author = db.query(models.User).filter(models.User.id == post.author_id).first()
                comment_text = await llm_service.generate_comment(
                    persona, post_author.name if post_author else "unbekannt", post.topic or "", post.caption or ""
                )
                db.add(models.Comment(post_id=post.id, author_id=persona_user.id, text=comment_text))
                db.commit()

                if post_author and post_author.is_human:
                    await notification_service.notify_human("comment", persona_user.name, comment_text)


def _get_or_create_persona_user(db: Session, persona: dict) -> models.User:
    """
    Stellt sicher, dass für jede Persona aus personas.json ein User Eintrag
    in der Datenbank existiert. Wird auch beim App Start für das initiale
    Seeding genutzt (siehe main.py).
    """
    user = db.query(models.User).filter(models.User.id == persona["id"]).first()
    if user:
        return user

    user = models.User(
        id=persona["id"],
        name=persona["name"],
        username=persona["username"],
        bio=persona.get("bio"),
        is_human=False,
        personality_prompt=persona.get("personalityPrompt"),
        category=persona.get("category"),
        interests=persona.get("interests"),
        visual_style=persona.get("visualStyle"),
        tone_hints=persona.get("toneHints"),
        emoji_usage=persona.get("emojiUsage"),
        posting_frequency_hours_avg=persona.get("postingFrequencyHours", {}).get("avg"),
        posting_frequency_hours_spread=persona.get("postingFrequencyHours", {}).get("spreadHours"),
        reaction_delay_min_minutes=persona.get("reactionDelayMinutes", {}).get("min"),
        reaction_delay_max_minutes=persona.get("reactionDelayMinutes", {}).get("max"),
        engagement_rate=persona.get("engagementRate"),
        active_hours=persona.get("activeHours"),
    )
    db.add(user)
    db.commit()
    db.refresh(user)
    return user
