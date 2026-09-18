package com.minigames.hub.games.arrows

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.minigames.hub.games.Difficulty

enum class ArrowsGameStatus { PLAYING, WON, LOST }

/**
 * Haelt den kompletten Spielzustand fuer ein Level einer Schwierigkeitsstufe.
 * Wird ueber remember { ArrowsGameState(difficulty) } in der Composable erzeugt.
 */
class ArrowsGameState(private val difficulty: Difficulty, startLevelIndex: Int = 0) {

    private val levels = ArrowsLevels.forDifficulty(difficulty)
    val totalLevels: Int get() = levels.size

    var levelIndex by mutableIntStateOf(startLevelIndex.coerceIn(levels.indices))
        private set

    var level by mutableStateOf(levels[levelIndex])
        private set

    var remainingArrows by mutableStateOf(level.arrows)
        private set

    /** Pfeile, die gerade aus dem Feld hinausfliegen (fuer die Wegflug-Animation). */
    var exitingArrows by mutableStateOf<List<ArrowPiece>>(emptyList())
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
        val safeIndex = index.coerceIn(levels.indices)
        levelIndex = safeIndex
        level = levels[safeIndex]
        remainingArrows = level.arrows
        exitingArrows = emptyList()
        lives = level.lives
        score = 0
        status = ArrowsGameStatus.PLAYING
        lastBlockedId = null
    }

    fun restart() = loadLevel(levelIndex)

    fun nextLevel() {
        if (levelIndex < levels.lastIndex) {
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
            exitingArrows = exitingArrows + arrow
            score += 10
        }
    }

    /** Wird aufgerufen, sobald die Wegflug-Animation eines Pfeils fertig ist. */
    fun finishExit(id: Int) {
        exitingArrows = exitingArrows.filter { it.id != id }
        if (status == ArrowsGameStatus.PLAYING && remainingArrows.isEmpty() && exitingArrows.isEmpty()) {
            status = ArrowsGameStatus.WON
        }
    }

    fun clearBlockedFlag() {
        lastBlockedId = null
    }
}
