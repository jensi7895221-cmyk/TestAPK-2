package com.example.socialfeed.ui.story

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.socialfeed.data.model.Story
import com.example.socialfeed.data.repository.FeedRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Vollflächiger Story Viewer. Wechselt automatisch nach STORY_DURATION_MS
 * zur nächsten Story und schließt sich am Ende der Liste selbst.
 * TODO: Tap Bereiche links und rechts zum manuellen Vor und Zurückspringen
 * sind als Erweiterung vorgesehen, aktuell nur automatischer Ablauf.
 */
private const val STORY_DURATION_MS = 5000L

@Composable
fun StoryScreen(onClose: () -> Unit) {
    var stories by remember { mutableStateOf<List<Story>>(emptyList()) }
    var currentIndex by remember { mutableStateOf(0) }
    var isLoading by remember { mutableStateOf(true) }
    val feedRepository = remember { FeedRepository() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            stories = feedRepository.getActiveStories()
            isLoading = false
            if (stories.isEmpty()) onClose()
        }
    }

    LaunchedEffect(currentIndex, stories) {
        if (stories.isNotEmpty()) {
            delay(STORY_DURATION_MS)
            if (currentIndex < stories.size - 1) {
                currentIndex += 1
            } else {
                onClose()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (stories.isNotEmpty()) {
            val story = stories[currentIndex]

            AsyncImage(
                model = story.media_url,
                contentDescription = story.text,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                stories.forEachIndexed { index, _ ->
                    LinearProgressIndicator(
                        progress = { if (index < currentIndex) 1f else if (index == currentIndex) 0.5f else 0f },
                        modifier = Modifier.weight(1f).padding(horizontal = 2.dp)
                    )
                }
            }

            story.text?.let {
                Text(
                    text = it,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)
                )
            }

            IconButton(
                onClick = onClose,
                modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
            ) {
                Icon(Icons.Filled.Close, contentDescription = "Schließen")
            }
        }
    }
}
