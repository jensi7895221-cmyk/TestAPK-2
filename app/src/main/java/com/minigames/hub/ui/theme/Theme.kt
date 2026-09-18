package com.minigames.hub.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val HubColorScheme = darkColorScheme(
    primary = HubAccent,
    secondary = HubAccentLight,
    background = HubBackground,
    surface = HubSurface,
    surfaceVariant = HubSurfaceVariant,
    onBackground = HubOnBackground,
    onSurface = HubOnBackground
)

@Composable
fun MiniGamesHubTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = HubColorScheme,
        typography = HubTypography,
        content = content
    )
}
