package com.example.socialfeed.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialfeed.data.model.Post
import com.example.socialfeed.data.model.User
import com.example.socialfeed.data.repository.FeedRepository
import com.example.socialfeed.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ProfileUiState {
    object Loading : ProfileUiState()
    data class Success(val user: User, val isFollowing: Boolean) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
}

class ProfileViewModel(
    private val userRepository: UserRepository = UserRepository(),
    private val feedRepository: FeedRepository = FeedRepository(),
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun load(userId: String, currentUserId: String) {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading
            try {
                val user = userRepository.getUser(userId)
                val following = userRepository.getFollowing(currentUserId)
                val isFollowing = following.any { it.id == userId }
                _uiState.value = ProfileUiState.Success(user, isFollowing)
            } catch (e: Exception) {
                _uiState.value = ProfileUiState.Error(e.message ?: "Profil konnte nicht geladen werden")
            }
        }
    }

    fun follow(currentUserId: String, targetUserId: String) {
        viewModelScope.launch {
            try {
                userRepository.follow(currentUserId, targetUserId)
                val current = _uiState.value
                if (current is ProfileUiState.Success) {
                    _uiState.value = current.copy(isFollowing = true)
                }
            } catch (e: Exception) {
                // TODO: Fehleranzeige, zum Beispiel per Snackbar.
            }
        }
    }
}
