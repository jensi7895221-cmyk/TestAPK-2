package com.example.socialfeed.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.socialfeed.data.model.Comment

/**
 * Kommentarbereich mit einfacher Thread Darstellung: Antworten (erkennbar an
 * parent_comment_id) werden eingerückt unter dem übergeordneten Kommentar gezeigt.
 * TODO: Aktuell eine einfache, flache Sortierung nach Erstellzeit mit Einrückung.
 * Für tiefere Threads (Antworten auf Antworten) wäre ein rekursiver Aufbau nötig.
 */
@Composable
fun CommentSheet(
    comments: List<Comment>,
    onSendComment: (text: String, parentCommentId: String?) -> Unit,
) {
    var newCommentText by remember { mutableStateOf("") }
    var replyingTo by remember { mutableStateOf<Comment?>(null) }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text("Kommentare", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f, fill = false).heightIn(max = 400.dp)) {
            items(comments) { comment ->
                val indent = if (comment.parent_comment_id != null) 24.dp else 0.dp
                Row(modifier = Modifier.padding(start = indent, top = 4.dp, bottom = 4.dp)) {
                    Text(comment.text, modifier = Modifier.weight(1f))
                    TextButton(onClick = { replyingTo = comment }) {
                        Text("Antworten")
                    }
                }
            }
        }

        replyingTo?.let {
            Text("Antwort auf: \"${it.text}\"", style = MaterialTheme.typography.labelSmall)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newCommentText,
                onValueChange = { newCommentText = it },
                placeholder = { Text("Kommentar schreiben") },
                modifier = Modifier.weight(1f)
            )
            TextButton(onClick = {
                if (newCommentText.isNotBlank()) {
                    onSendComment(newCommentText, replyingTo?.id)
                    newCommentText = ""
                    replyingTo = null
                }
            }) {
                Text("Senden")
            }
        }
    }
}
