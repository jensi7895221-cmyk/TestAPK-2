package com.example.socialfeed.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

/**
 * Zeigt Name, Bio und Avatar eines Nutzers (Persona oder das eigene Profil).
 * Bei einer Persona wird zusätzlich ein Follow Button und ein Button zum
 * Chat Start angeboten. TODO: eigene Beiträge des Profils als Grid anzeigen,
 * aktuell zeigt dieser Screen bewusst nur die Kopfzeile, da die Endpunkt
 * Route "Posts eines Nutzers" im Backend noch ergänzt werden müsste.
 */
@Composable
fun ProfileScreen(
    userId: String,
    currentUserId: String,
    onOpenChat: (String) -> Unit,
    viewModel: ProfileViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val isOwnProfile = userId == currentUserId

    LaunchedEffect(userId) {
        viewModel.load(userId, currentUserId)
    }

    when (val state = uiState) {
        is ProfileUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ProfileUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Profil konnte nicht geladen werden: ${state.message}")
            }
        }
        is ProfileUiState.Success -> {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = state.user.avatar_url,
                    contentDescription = state.user.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(96.dp).clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(state.user.name, style = MaterialTheme.typography.titleLarge)
                Text("@${state.user.username}", style = MaterialTheme.typography.bodyMedium)
                state.user.category?.let {
                    Text(it, style = MaterialTheme.typography.labelMedium)
                }
                Spacer(modifier = Modifier.height(8.dp))
                state.user.bio?.let {
                    Text(it, style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (!isOwnProfile) {
                    Row {
                        Button(
                            onClick = { viewModel.follow(currentUserId, userId) },
                            enabled = !state.isFollowing
                        ) {
                            Text(if (state.isFollowing) "Folgst du bereits" else "Folgen")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedButton(onClick = { onOpenChat(userId) }) {
                            Text("Nachricht senden")
                        }
                    }
                }
            }
        }
    }
}
