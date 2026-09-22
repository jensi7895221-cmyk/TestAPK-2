package com.example.socialfeed.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = PrimaryPurple,
    secondary = SecondaryPink,
    background = BackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
)

@Composable
fun SocialFeedKITheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}
