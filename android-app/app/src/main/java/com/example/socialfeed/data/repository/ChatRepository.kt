package com.example.socialfeed.data.repository

import com.example.socialfeed.data.model.CreateMessageRequest
import com.example.socialfeed.data.model.Message
import com.example.socialfeed.data.remote.RetrofitClient

class ChatRepository {

    private val api = RetrofitClient.apiService

    suspend fun getChatHistory(userAId: String, userBId: String): List<Message> {
        return api.getChatHistory(userAId, userBId)
    }

    /**
     * Sendet eine Nachricht. Das Backend erzeugt bei einer Persona als Empfänger
     * automatisch im selben Request eine Antwort, daher reicht ein erneutes
     * Laden von getChatHistory direkt danach, um die Antwort mit anzuzeigen.
     */
    suspend fun sendMessage(senderId: String, receiverId: String, text: String): Message {
        return api.sendMessage(CreateMessageRequest(senderId, receiverId, text))
    }
}
