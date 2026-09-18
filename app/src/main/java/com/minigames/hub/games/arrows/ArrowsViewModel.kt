package com.minigames.hub.games.arrows

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class ArrowsGameStatus { PLAYING, WON, LOST }

/**
 * Haelt den kompletten Spielzustand fuer ein Level.
 * Wird ueber remember { ArrowsGameState() } in der Composable erzeugt.
 */
class ArrowsGameState {

    var levelIndex by mutableIntStateOf(0)
        private set

    var level by mutableStateOf(ArrowsLevels.all[0])
        private set

    var remainingArrows by mutableStateOf(level.arrows)
        private set

    var lives by mutableIntStateOf(level.lives)
        private set

    var score by mutableIntStateOf(0)
        private set

    var status by mutableStateOf(ArrowsGameStatus.PLAYING)
        private set

    /** Kurzzeitig gesetzte Id eines Pfeils, der gerade blockiert wurde, fuer die rote Aufblitz-Animation. */
    var lastBlockedId by mutableStateOf<Int?>(null)
        private set

    fun loadLevel(index: Int) {
        val safeIndex = index.coerceIn(ArrowsLevels.all.indices)
        levelIndex = safeIndex
        level = ArrowsLevels.all[safeIndex]
        remainingArrows = level.arrows
        lives = level.lives
        score = 0
        status = ArrowsGameStatus.PLAYING
        lastBlockedId = null
    }

    fun restart() = loadLevel(levelIndex)

    fun nextLevel() {
        if (levelIndex < ArrowsLevels.all.lastIndex) {
            loadLevel(levelIndex + 1)
        } else {
            loadLevel(0)
        }
    }

    fun onArrowTapped(id: Int) {
        if (status != ArrowsGameStatus.PLAYING) return
        val arrow = remainingArrows.find { it.id == id } ?: return

        val occupied = remainingArrows.filter { it.id != id }.flatMap { it.cells }.toSet()
        val path = arrow.pathToEdge(level.rows, level.cols)
        val isBlocked = path.any { it in occupied }

        if (isBlocked) {
            lastBlockedId = id
            lives -= 1
            if (lives <= 0) {
                status = ArrowsGameStatus.LOST
            }
        } else {
            lastBlockedId = null
            remainingArrows = remainingArrows.filter { it.id != id }
            score += 10
            if (remainingArrows.isEmpty()) {
                status = ArrowsGameStatus.WON
            }
        }
    }

    fun clearBlockedFlag() {
        lastBlockedId = null
    }
}
