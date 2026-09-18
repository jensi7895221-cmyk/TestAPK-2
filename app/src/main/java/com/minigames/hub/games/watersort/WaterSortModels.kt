package com.minigames.hub.games.watersort

import androidx.compose.ui.graphics.Color

/**
 * Ein Level besteht aus einer Kapazitaet pro Roehrchen und dem Startzustand jedes Roehrchens.
 * Jede Liste ist von unten nach oben sortiert (erstes Element = unterste Fluessigkeit).
 */
data class WaterSortLevel(
    val tubeCapacity: Int,
    val tubes: List<List<Color>>
)

object WaterColors {
    val Red = Color(0xFFE53935)
    val Yellow = Color(0xFFFDD835)
    val Green = Color(0xFF43A047)
    val Blue = Color(0xFF1E88E5)
    val Purple = Color(0xFF8E24AA)
    val Orange = Color(0xFFFB8C00)
    val Teal = Color(0xFF00897B)
    val Pink = Color(0xFFD81B60)
}
