package com.example.socialfeed.data.model

/**
 * Datenmodelle, die 1 zu 1 den Pydantic Schemas des Backends entsprechen
 * (siehe backend/app/schemas.py), damit Retrofit die JSON Antworten direkt
 * abbilden kann.
 */

data class User(
    val id: String,
    val name: String,
    val username: String,
    val avatar_url: String?,
    val bio: String?,
    val is_human: Boolean,
    val category: String?
)

data class Post(
    val id: String,
    val author_id: String,
    val image_url: String,
    val caption: String?,
    val topic: String?,
    val created_at: String,
    val like_count: Int,
    val comment_count: Int
)

data class Comment(
    val id: String,
    val post_id: String,
    val author_id: String,
    val text: String,
    val parent_comment_id: String?,
    val created_at: String
)

data class Like(
    val id: String,
    val post_id: String,
    val author_id: String,
    val created_at: String
)

data class Message(
    val id: String,
    val sender_id: String,
    val receiver_id: String,
    val text: String,
    val created_at: String
)

data class Story(
    val id: String,
    val author_id: String,
    val media_url: String,
    val text: String?,
    val created_at: String,
    val expires_at: String
)

// Request Bodies

data class CreateUserRequest(val name: String, val username: String, val avatar_url: String?, val bio: String?)
data class CreatePostRequest(val author_id: String, val image_url: String, val caption: String?, val topic: String?)
data class CreateCommentRequest(val post_id: String, val author_id: String, val text: String, val parent_comment_id: String?)
data class CreateLikeRequest(val post_id: String, val author_id: String)
data class CreateMessageRequest(val sender_id: String, val receiver_id: String, val text: String)
data class CreateFollowRequest(val follower_id: String, val followed_id: String)

/**
 * Angereichertes Post Modell für die UI, wenn Autor Informationen (Name, Avatar)
 * zusätzlich zum reinen Post benötigt werden. Wird vom Repository zusammengesetzt,
 * da das Backend Posts und User aktuell getrennt liefert.
 */
data class FeedPost(
    val post: Post,
    val author: User
)
