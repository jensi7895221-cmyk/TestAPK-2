package com.example.socialfeed.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialfeed.data.model.Message
import com.example.socialfeed.data.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ChatUiState {
    object Loading : ChatUiState()
    data class Success(val messages: List<Message>) : ChatUiState()
    data class Error(val message: String) : ChatUiState()
}

class ChatViewModel(private val repository: ChatRepository = ChatRepository()) : ViewModel() {

    private val _uiState = MutableStateFlow<ChatUiState>(ChatUiState.Loading)
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    fun loadHistory(userId: String, personaId: String) {
        viewModelScope.launch {
            _uiState.value = ChatUiState.Loading
            try {
                val messages = repository.getChatHistory(userId, personaId)
                _uiState.value = ChatUiState.Success(messages)
            } catch (e: Exception) {
                _uiState.value = ChatUiState.Error(e.message ?: "Chatverlauf konnte nicht geladen werden")
            }
        }
    }

    /**
     * Sendet die Nachricht. Das Backend erzeugt die Persona Antwort bereits
     * synchron im selben Request (siehe backend/app/routers/messages.py), daher
     * reicht ein erneutes Laden der Historie direkt danach.
     */
    fun sendMessage(userId: String, personaId: String, text: String) {
        viewModelScope.launch {
            try {
                repository.sendMessage(userId, personaId, text)
                loadHistory(userId, personaId)
            } catch (e: Exception) {
                _uiState.value = ChatUiState.Error(e.message ?: "Nachricht konnte nicht gesendet werden")
            }
        }
    }
}
