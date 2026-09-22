package com.example.socialfeed.ui.feed

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialfeed.data.model.FeedPost
import com.example.socialfeed.data.repository.FeedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class FeedUiState {
    object Loading : FeedUiState()
    data class Success(val posts: List<FeedPost>) : FeedUiState()
    data class Error(val message: String) : FeedUiState()
}

class FeedViewModel(private val repository: FeedRepository = FeedRepository()) : ViewModel() {

    private val _uiState = MutableStateFlow<FeedUiState>(FeedUiState.Loading)
    val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()

    // TODO: echte Like Zustände sollten aus dem Backend kommen (zum Beispiel ein
    // "liked_by_current_user" Feld auf PostOut). Für das Grundgerüst wird der
    // Zustand lokal im ViewModel gehalten.
    val likedPostIds = mutableStateListOf<String>()

    fun loadFeed(userId: String) {
        viewModelScope.launch {
            _uiState.value = FeedUiState.Loading
            try {
                val posts = repository.getFeed(userId)
                _uiState.value = FeedUiState.Success(posts)
            } catch (e: Exception) {
                _uiState.value = FeedUiState.Error(e.message ?: "Unbekannter Fehler beim Laden des Feeds")
            }
        }
    }

    fun toggleLike(postId: String, currentUserId: String) {
        viewModelScope.launch {
            try {
                if (likedPostIds.contains(postId)) {
                    likedPostIds.remove(postId)
                    // TODO: die konkrete like_id müsste für ein echtes Unlike vom
                    // Backend zurückgegeben oder separat abgefragt werden.
                } else {
                    repository.likePost(postId, currentUserId)
                    likedPostIds.add(postId)
                }
            } catch (e: Exception) {
                // TODO: Fehleranzeige, zum Beispiel per Snackbar.
            }
        }
    }
}
