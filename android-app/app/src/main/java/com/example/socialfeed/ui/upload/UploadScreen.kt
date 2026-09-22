package com.example.socialfeed.ui.upload

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.socialfeed.data.repository.FeedRepository
import kotlinx.coroutines.launch

/**
 * Einfacher Upload Screen. TODO: Fotoauswahl aus der Galerie oder Kamera
 * Aufnahme über ActivityResultContracts einbauen und das Bild zu einem
 * Objektspeicher (zum Beispiel S3 kompatibel) hochladen, um eine echte
 * image_url zu erhalten. Für das Grundgerüst wird die URL als Texteingabe
 * simuliert, damit der komplette Ablauf (Post erstellen, im Feed erscheinen,
 * von Personas kommentiert werden) bereits durchspielbar ist.
 */
@Composable
fun UploadScreen(
    currentUserId: String,
    onPostCreated: () -> Unit,
) {
    var imageUrl by remember { mutableStateOf("") }
    var caption by remember { mutableStateOf("") }
    var isSubmitting by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf<String?>(null) }

    val feedRepository = remember { FeedRepository() }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Neuer Beitrag", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("Bild URL (Platzhalter für echten Upload)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = caption,
            onValueChange = { caption = it },
            label = { Text("Caption") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        errorText?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                if (imageUrl.isBlank()) {
                    errorText = "Bitte eine Bild URL angeben."
                    return@Button
                }
                isSubmitting = true
                errorText = null
                scope.launch {
                    try {
                        feedRepository.createPost(currentUserId, imageUrl, caption.ifBlank { null }, topic = null)
                        onPostCreated()
                    } catch (e: Exception) {
                        errorText = e.message ?: "Beitrag konnte nicht erstellt werden."
                    } finally {
                        isSubmitting = false
                    }
                }
            },
            enabled = !isSubmitting,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isSubmitting) "Wird gepostet ..." else "Posten")
        }
    }
}
