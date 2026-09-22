"""
Pydantic Schemas für Request und Response Bodies.
Getrennt in Create (Eingabe) und Out (Ausgabe), damit interne Felder wie
personality_prompt nicht ungewollt über die API nach außen gehen.
"""
from datetime import datetime
from typing import Optional, List
from pydantic import BaseModel, ConfigDict


class UserOut(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: str
    name: str
    username: str
    avatar_url: Optional[str] = None
    bio: Optional[str] = None
    is_human: bool
    category: Optional[str] = None


class UserCreate(BaseModel):
    name: str
    username: str
    avatar_url: Optional[str] = None
    bio: Optional[str] = None


class PostCreate(BaseModel):
    author_id: str
    image_url: str
    caption: Optional[str] = None
    topic: Optional[str] = None


class PostOut(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: str
    author_id: str
    image_url: str
    caption: Optional[str] = None
    topic: Optional[str] = None
    created_at: datetime
    like_count: int = 0
    comment_count: int = 0


class CommentCreate(BaseModel):
    post_id: str
    author_id: str
    text: str
    parent_comment_id: Optional[str] = None


class CommentOut(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: str
    post_id: str
    author_id: str
    text: str
    parent_comment_id: Optional[str] = None
    created_at: datetime


class LikeCreate(BaseModel):
    post_id: str
    author_id: str


class LikeOut(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: str
    post_id: str
    author_id: str
    created_at: datetime


class MessageCreate(BaseModel):
    sender_id: str
    receiver_id: str
    text: str


class MessageOut(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: str
    sender_id: str
    receiver_id: str
    text: str
    created_at: datetime


class FollowCreate(BaseModel):
    follower_id: str
    followed_id: str


class StoryOut(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: str
    author_id: str
    media_url: str
    text: Optional[str] = None
    created_at: datetime
    expires_at: datetime
