package com.example.socialfeed.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ModeComment
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.socialfeed.data.model.FeedPost

/**
 * Zeigt einen Post inklusive Autor Kopfzeile, Bild, Like Button mit Animation
 * und Kommentar Zähler. Die eigentliche Like Logik (Netzwerkaufruf) liegt in
 * FeedViewModel, diese Komponente ist bewusst zustandslos gehalten.
 */
@Composable
fun PostCard(
    feedPost: FeedPost,
    isLikedByCurrentUser: Boolean,
    onAuthorClick: () -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .clickable { onAuthorClick() }
        ) {
            AsyncImage(
                model = feedPost.author.avatar_url,
                contentDescription = feedPost.author.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(36.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = feedPost.author.name)
        }

        AsyncImage(
            model = feedPost.post.image_url,
            contentDescription = feedPost.post.caption,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(360.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            LikeButton(isLiked = isLikedByCurrentUser, onClick = onLikeClick)
            Text(text = "${feedPost.post.like_count}")

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = onCommentClick) {
                Icon(Icons.Filled.ModeComment, contentDescription = "Kommentare")
            }
            Text(text = "${feedPost.post.comment_count}")
        }

        feedPost.post.caption?.let { caption ->
            Text(
                text = "${feedPost.author.name} $caption",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
fun LikeButton(isLiked: Boolean, onClick: () -> Unit) {
    val scale by animateFloatAsState(
        targetValue = if (isLiked) 1.2f else 1f,
        animationSpec = spring(),
        label = "like_scale"
    )
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Like",
            modifier = Modifier.scale(scale)
        )
    }
}
