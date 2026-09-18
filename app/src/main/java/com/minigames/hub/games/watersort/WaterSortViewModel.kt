package com.minigames.hub.games.watersort

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.minigames.hub.games.Difficulty

/**
 * Haelt den kompletten Spielzustand fuer ein Level einer Schwierigkeitsstufe.
 * Wird ueber remember { WaterSortGameState(difficulty) } in der Composable erzeugt.
 */
class WaterSortGameState(private val difficulty: Difficulty, startLevelIndex: Int = 0) {

    private val levels = WaterSortLevels.forDifficulty(difficulty)
    val totalLevels: Int get() = levels.size

    var levelIndex by mutableIntStateOf(startLevelIndex.coerceIn(levels.indices))
        private set

    var capacity by mutableIntStateOf(levels[levelIndex].tubeCapacity)
        private set

    var tubes by mutableStateOf(levels[levelIndex].tubes)
        private set

    var selectedTube by mutableStateOf<Int?>(null)
        private set

    var moves by mutableIntStateOf(0)
        private set

    var won by mutableStateOf(false)
        private set

    fun loadLevel(index: Int) {
        val safeIndex = index.coerceIn(levels.indices)
        val level = levels[safeIndex]
        levelIndex = safeIndex
        capacity = level.tubeCapacity
        tubes = level.tubes
        selectedTube = null
        moves = 0
        won = false
    }

    fun restart() = loadLevel(levelIndex)

    fun nextLevel() {
        if (levelIndex < levels.lastIndex) {
            loadLevel(levelIndex + 1)
        } else {
            loadLevel(0)
        }
    }

    fun onTubeTapped(index: Int) {
        if (won) return
        val current = selectedTube

        if (current == null) {
            if (tubes[index].isNotEmpty()) selectedTube = index
            return
        }

        if (current == index) {
            selectedTube = null
            return
        }

        val moved = tryPour(from = current, to = index)
        selectedTube = if (moved) null else index.takeIf { tubes[it].isNotEmpty() }
        if (moved) checkWin()
    }

    private fun tryPour(from: Int, to: Int): Boolean {
        val source = tubes[from]
        val target = tubes[to]
        if (source.isEmpty()) return false
        if (target.size >= capacity) return false

        val pourColor = source.last()
        if (target.isNotEmpty() && target.last() != pourColor) return false

        var amount = 0
        while (amount < source.size &&
            source[source.size - 1 - amount] == pourColor &&
            target.size + amount < capacity
        ) {
            amount++
        }
        if (amount == 0) return false

        val newSource = source.dropLast(amount)
        val newTarget = target + List(amount) { pourColor }

        tubes = tubes.mapIndexed { i, tube ->
            when (i) {
                from -> newSource
                to -> newTarget
                else -> tube
            }
        }
        moves += 1
        return true
    }

    private fun checkWin() {
        won = tubes.all { tube -> tube.isEmpty() || (tube.size == capacity && tube.distinct().size == 1) }
    }
}
