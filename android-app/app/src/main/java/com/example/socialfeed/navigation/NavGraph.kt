package com.example.socialfeed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.socialfeed.ui.chat.ChatListScreen
import com.example.socialfeed.ui.chat.ChatScreen
import com.example.socialfeed.ui.feed.FeedScreen
import com.example.socialfeed.ui.profile.ProfileScreen
import com.example.socialfeed.ui.story.StoryScreen
import com.example.socialfeed.ui.upload.UploadScreen

/**
 * Zentrale Routen Definition. Jede Route entspricht einer Kernfunktion aus dem
 * Konzept: Feed, Profil, Upload, Chats, Chat Detail, Stories.
 */
object Routes {
    const val FEED = "feed"
    const val UPLOAD = "upload"
    const val PROFILE = "profile/{userId}"
    const val CHAT_LIST = "chats"
    const val CHAT_DETAIL = "chat/{personaId}"
    const val STORIES = "stories"

    fun profile(userId: String) = "profile/$userId"
    fun chatDetail(personaId: String) = "chat/$personaId"
}

@Composable
fun SocialFeedNavGraph(navController: NavHostController, currentUserId: String) {
    NavHost(navController = navController, startDestination = Routes.FEED) {

        composable(Routes.FEED) {
            FeedScreen(
                currentUserId = currentUserId,
                onOpenProfile = { userId -> navController.navigate(Routes.profile(userId)) },
                onOpenStories = { navController.navigate(Routes.STORIES) },
                onOpenChats = { navController.navigate(Routes.CHAT_LIST) },
                onOpenUpload = { navController.navigate(Routes.UPLOAD) },
            )
        }

        composable(Routes.UPLOAD) {
            UploadScreen(
                currentUserId = currentUserId,
                onPostCreated = { navController.popBackStack() },
            )
        }

        composable(
            route = Routes.PROFILE,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: return@composable
            ProfileScreen(
                userId = userId,
                currentUserId = currentUserId,
                onOpenChat = { personaId -> navController.navigate(Routes.chatDetail(personaId)) },
            )
        }

        composable(Routes.CHAT_LIST) {
            ChatListScreen(
                currentUserId = currentUserId,
                onOpenChat = { personaId -> navController.navigate(Routes.chatDetail(personaId)) },
            )
        }

        composable(
            route = Routes.CHAT_DETAIL,
            arguments = listOf(navArgument("personaId") { type = NavType.StringType })
        ) { backStackEntry ->
            val personaId = backStackEntry.arguments?.getString("personaId") ?: return@composable
            ChatScreen(currentUserId = currentUserId, personaId = personaId)
        }

        composable(Routes.STORIES) {
            StoryScreen(onClose = { navController.popBackStack() })
        }
    }
}
