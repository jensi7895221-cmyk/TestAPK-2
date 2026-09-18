package com.minigames.hub.games

import androidx.compose.ui.graphics.Color

/**
 * Schwierigkeitsgrade, die von allen Mini Spielen gemeinsam genutzt werden.
 * Jede Stufe hat 10 Level; siehe die jeweiligen Levels-Objekte der Spiele.
 */
enum class Difficulty(val label: String, val emoji: String, val color: Color) {
    EASY(label = "EINFACH", emoji = "🙂", color = Color(0xFF4CAF50)),
    MEDIUM(label = "MITTEL", emoji = "😏", color = Color(0xFFFF9800)),
    HARD(label = "SCHWER", emoji = "😈", color = Color(0xFFE53935))
}
