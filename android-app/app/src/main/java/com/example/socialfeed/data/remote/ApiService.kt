package com.example.socialfeed.data.remote

import com.example.socialfeed.data.model.*
import retrofit2.http.*

/**
 * Schnittstelle zum Backend, spiegelt die Routen aus backend/app/routers/ wider.
 */
interface ApiService {

    @POST("users")
    suspend fun createHumanUser(@Body body: CreateUserRequest): User

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): User

    @GET("users")
    suspend fun listUsers(@Query("is_human") isHuman: Boolean? = null): List<User>

    @POST("posts")
    suspend fun createPost(@Body body: CreatePostRequest): Post

    @GET("posts/feed")
    suspend fun getFeed(@Query("user_id") userId: String, @Query("limit") limit: Int = 30): List<Post>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: String): Post

    @POST("comments")
    suspend fun createComment(@Body body: CreateCommentRequest): Comment

    @GET("comments/post/{postId}")
    suspend fun getComments(@Path("postId") postId: String): List<Comment>

    @POST("likes")
    suspend fun createLike(@Body body: CreateLikeRequest): Like

    @DELETE("likes/{id}")
    suspend fun deleteLike(@Path("id") id: String)

    @POST("messages")
    suspend fun sendMessage(@Body body: CreateMessageRequest): Message

    @GET("messages/chat/{userAId}/{userBId}")
    suspend fun getChatHistory(@Path("userAId") userAId: String, @Path("userBId") userBId: String): List<Message>

    @POST("follows")
    suspend fun follow(@Body body: CreateFollowRequest)

    @GET("follows/following/{userId}")
    suspend fun getFollowing(@Path("userId") userId: String): List<User>

    @GET("stories")
    suspend fun getActiveStories(): List<Story>
}
