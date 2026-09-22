package com.example.socialfeed.ui.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.socialfeed.data.model.User
import com.example.socialfeed.data.repository.UserRepository
import kotlinx.coroutines.launch

/**
 * Zeigt alle Personas, denen der Nutzer folgt, als mögliche Chat Partner.
 * TODO: Eine echte "letzte Nachricht" Vorschau je Chat wäre der nächste
 * sinnvolle Ausbauschritt, dafür müsste das Backend einen Endpunkt
 * "letzte Nachricht je Konversation" bereitstellen.
 */
@Composable
fun ChatListScreen(
    currentUserId: String,
    onOpenChat: (String) -> Unit,
) {
    var personas by remember { mutableStateOf<List<User>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    val userRepository = remember { UserRepository() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(currentUserId) {
        scope.launch {
            isLoading = true
            personas = userRepository.getFollowing(currentUserId)
            isLoading = false
        }
    }

    Scaffold(topBar = { TopAppBar(title = { Text("Nachrichten") }) }) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (personas.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Folge Personas, um mit ihnen zu chatten.")
                }
            } else {
                LazyColumn {
                    items(personas) { persona ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onOpenChat(persona.id) }
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = persona.avatar_url,
                                contentDescription = persona.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(48.dp).clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(persona.name, style = MaterialTheme.typography.titleMedium)
                                persona.category?.let {
                                    Text(it, style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
