package com.example.socialfeed.data.repository

import com.example.socialfeed.data.model.*
import com.example.socialfeed.data.remote.RetrofitClient

/**
 * Bündelt alle Zugriffe rund um den Feed. Reichert Posts mit Autor Informationen
 * an, da das Backend Posts und User getrennt liefert.
 *
 * TODO Offline Fähigkeit: aktuell rein netzwerkbasiert. Ein Room DAO für
 * FeedPost sollte hier ergänzt werden, um zuletzt geladene Inhalte
 * zwischenzuspeichern, wie im ursprünglichen Konzept gefordert.
 */
class FeedRepository {

    private val api = RetrofitClient.apiService
    private val userCache = mutableMapOf<String, User>()

    suspend fun getFeed(userId: String): List<FeedPost> {
        val posts = api.getFeed(userId)
        return posts.map { post ->
            val author = userCache.getOrPut(post.author_id) { api.getUser(post.author_id) }
            FeedPost(post = post, author = author)
        }
    }

    suspend fun createPost(authorId: String, imageUrl: String, caption: String?, topic: String?): Post {
        return api.createPost(CreatePostRequest(authorId, imageUrl, caption, topic))
    }

    suspend fun likePost(postId: String, authorId: String): Like {
        return api.createLike(CreateLikeRequest(postId, authorId))
    }

    suspend fun unlike(likeId: String) {
        api.deleteLike(likeId)
    }

    suspend fun getComments(postId: String): List<Comment> {
        return api.getComments(postId)
    }

    suspend fun addComment(postId: String, authorId: String, text: String, parentCommentId: String? = null): Comment {
        return api.createComment(CreateCommentRequest(postId, authorId, text, parentCommentId))
    }

    suspend fun getActiveStories(): List<Story> {
        return api.getActiveStories()
    }
}
