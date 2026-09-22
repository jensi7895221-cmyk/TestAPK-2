package com.example.socialfeed.data.repository

import com.example.socialfeed.data.model.CreateFollowRequest
import com.example.socialfeed.data.model.CreateUserRequest
import com.example.socialfeed.data.model.User
import com.example.socialfeed.data.remote.RetrofitClient

class UserRepository {

    private val api = RetrofitClient.apiService

    suspend fun createHumanUser(name: String, username: String, avatarUrl: String?, bio: String?): User {
        return api.createHumanUser(CreateUserRequest(name, username, avatarUrl, bio))
    }

    suspend fun getUser(id: String): User {
        return api.getUser(id)
    }

    suspend fun listPersonas(): List<User> {
        return api.listUsers(isHuman = false)
    }

    suspend fun getFollowing(userId: String): List<User> {
        return api.getFollowing(userId)
    }

    suspend fun follow(followerId: String, followedId: String) {
        api.follow(CreateFollowRequest(followerId, followedId))
    }
}
