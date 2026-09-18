package com.minigames.hub.games

import com.minigames.hub.games.arrows.ArrowsGameModule
import com.minigames.hub.games.watersort.WaterSortGameModule

/**
 * Einziger Ort im Projekt, an dem neue Spiele bekannt gemacht werden muessen.
 *
 * Beispiel fuer ein zukuenftiges drittes Spiel:
 *
 *   val games: List<GameModule> = listOf(
 *       ArrowsGameModule(),
 *       WaterSortGameModule(),
 *       SnakesGameModule()   // <- neue Zeile, mehr ist nicht noetig
 *   )
 */
object GameCatalog {
    val games: List<GameModule> = listOf(
        ArrowsGameModule(),
        WaterSortGameModule()
    )

    fun findById(id: String): GameModule? = games.find { it.info.id == id }
}
