package com.example.socialfeed.ui.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.socialfeed.data.model.Comment
import com.example.socialfeed.data.repository.FeedRepository
import com.example.socialfeed.ui.components.CommentSheet
import com.example.socialfeed.ui.components.PostCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    currentUserId: String,
    onOpenProfile: (String) -> Unit,
    onOpenStories: () -> Unit,
    onOpenChats: () -> Unit,
    onOpenUpload: () -> Unit,
    viewModel: FeedViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val feedRepository = remember { FeedRepository() }

    var activeCommentsPostId by remember { mutableStateOf<String?>(null) }
    var comments by remember { mutableStateOf<List<Comment>>(emptyList()) }

    LaunchedEffect(currentUserId) {
        viewModel.loadFeed(currentUserId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Social Feed KI") },
                actions = {
                    IconButton(onClick = onOpenChats) {
                        Icon(Icons.Filled.ChatBubble, contentDescription = "Nachrichten")
                    }
                    IconButton(onClick = onOpenUpload) {
                        Icon(Icons.Filled.Add, contentDescription = "Neuer Beitrag")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {

            // Platzhalter Story Leiste, siehe StoryScreen für die volle Ansicht.
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                TextButton(onClick = onOpenStories) {
                    Text("Stories ansehen")
                }
            }

            when (val state = uiState) {
                is FeedUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is FeedUiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Feed konnte nicht geladen werden: ${state.message}")
                    }
                }
                is FeedUiState.Success -> {
                    if (state.posts.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Noch keine Beiträge. Folge ein paar Personas, um deinen Feed zu füllen.")
                        }
                    } else {
                        LazyColumn {
                            items(state.posts) { feedPost ->
                                PostCard(
                                    feedPost = feedPost,
                                    isLikedByCurrentUser = viewModel.likedPostIds.contains(feedPost.post.id),
                                    onAuthorClick = { onOpenProfile(feedPost.author.id) },
                                    onLikeClick = { viewModel.toggleLike(feedPost.post.id, currentUserId) },
                                    onCommentClick = {
                                        activeCommentsPostId = feedPost.post.id
                                        scope.launch {
                                            comments = feedRepository.getComments(feedPost.post.id)
                                        }
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    activeCommentsPostId?.let { postId ->
        ModalBottomSheet(onDismissRequest = { activeCommentsPostId = null }) {
            CommentSheet(
                comments = comments,
                onSendComment = { text, parentId ->
                    scope.launch {
                        feedRepository.addComment(postId, currentUserId, text, parentId)
                        comments = feedRepository.getComments(postId)
                    }
                }
            )
        }
    }
}
