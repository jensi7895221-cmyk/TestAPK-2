"""
Datenmodell laut Konzept:
User (Persona oder echter Nutzer), Post, Comment, Like, Message, Follow.
Zusätzliche Felder gegenüber dem ursprünglichen Vorschlag sind für den
Persona Scheduler nötig (personalityPrompt, visualStyle, engagementRate etc.)
und als Platzhalter bereits eingebaut.
"""
import uuid
from datetime import datetime

from sqlalchemy import (
    Column, String, Boolean, Integer, Float, DateTime, ForeignKey, Text, JSON
)
from sqlalchemy.orm import relationship

from app.database import Base


def gen_id() -> str:
    return str(uuid.uuid4())


class User(Base):
    __tablename__ = "users"

    id = Column(String, primary_key=True, default=gen_id)
    name = Column(String, nullable=False)
    username = Column(String, unique=True, nullable=False, index=True)
    avatar_url = Column(String, nullable=True)
    bio = Column(Text, nullable=True)
    is_human = Column(Boolean, default=False, nullable=False)

    # Persona spezifische Felder, bei is_human=True größtenteils ungenutzt.
    personality_prompt = Column(Text, nullable=True)
    category = Column(String, nullable=True)
    interests = Column(JSON, nullable=True)          # Liste von Strings
    visual_style = Column(Text, nullable=True)
    tone_hints = Column(String, nullable=True)
    emoji_usage = Column(String, nullable=True)
    posting_frequency_hours_avg = Column(Float, nullable=True)
    posting_frequency_hours_spread = Column(Float, nullable=True)
    reaction_delay_min_minutes = Column(Integer, nullable=True)
    reaction_delay_max_minutes = Column(Integer, nullable=True)
    engagement_rate = Column(Float, nullable=True)
    active_hours = Column(JSON, nullable=True)        # Liste von "HH:MM-HH:MM"

    created_at = Column(DateTime, default=datetime.utcnow)

    posts = relationship("Post", back_populates="author", cascade="all, delete-orphan")
    comments = relationship("Comment", back_populates="author", cascade="all, delete-orphan")
    likes = relationship("Like", back_populates="author", cascade="all, delete-orphan")


class Post(Base):
    __tablename__ = "posts"

    id = Column(String, primary_key=True, default=gen_id)
    author_id = Column(String, ForeignKey("users.id"), nullable=False)
    image_url = Column(String, nullable=False)
    caption = Column(Text, nullable=True)
    topic = Column(String, nullable=True)
    created_at = Column(DateTime, default=datetime.utcnow)

    author = relationship("User", back_populates="posts")
    comments = relationship("Comment", back_populates="post", cascade="all, delete-orphan")
    likes = relationship("Like", back_populates="post", cascade="all, delete-orphan")


class Comment(Base):
    __tablename__ = "comments"

    id = Column(String, primary_key=True, default=gen_id)
    post_id = Column(String, ForeignKey("posts.id"), nullable=False)
    author_id = Column(String, ForeignKey("users.id"), nullable=False)
    text = Column(Text, nullable=False)
    parent_comment_id = Column(String, ForeignKey("comments.id"), nullable=True)  # für Threads
    created_at = Column(DateTime, default=datetime.utcnow)

    post = relationship("Post", back_populates="comments")
    author = relationship("User", back_populates="comments")


class Like(Base):
    __tablename__ = "likes"

    id = Column(String, primary_key=True, default=gen_id)
    post_id = Column(String, ForeignKey("posts.id"), nullable=False)
    author_id = Column(String, ForeignKey("users.id"), nullable=False)
    created_at = Column(DateTime, default=datetime.utcnow)

    post = relationship("Post", back_populates="likes")
    author = relationship("User", back_populates="likes")


class Message(Base):
    __tablename__ = "messages"

    id = Column(String, primary_key=True, default=gen_id)
    sender_id = Column(String, ForeignKey("users.id"), nullable=False)
    receiver_id = Column(String, ForeignKey("users.id"), nullable=False)
    text = Column(Text, nullable=False)
    created_at = Column(DateTime, default=datetime.utcnow)


class Follow(Base):
    __tablename__ = "follows"

    id = Column(String, primary_key=True, default=gen_id)
    follower_id = Column(String, ForeignKey("users.id"), nullable=False)
    followed_id = Column(String, ForeignKey("users.id"), nullable=False)
    created_at = Column(DateTime, default=datetime.utcnow)


class Story(Base):
    """Platzhalter Tabelle für die optionale Story Funktion."""
    __tablename__ = "stories"

    id = Column(String, primary_key=True, default=gen_id)
    author_id = Column(String, ForeignKey("users.id"), nullable=False)
    media_url = Column(String, nullable=False)
    text = Column(String, nullable=True)
    created_at = Column(DateTime, default=datetime.utcnow)
    expires_at = Column(DateTime, nullable=False)
