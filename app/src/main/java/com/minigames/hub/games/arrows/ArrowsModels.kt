package com.minigames.hub.games.arrows

enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Cell(val row: Int, val col: Int)

/**
 * Ein Pfeil besteht aus mehreren Feldern (dem Koerper) und zeigt in eine Richtung.
 * Das letzte Element in cells ist die Pfeilspitze (head).
 */
data class ArrowPiece(
    val id: Int,
    val cells: List<Cell>,
    val direction: Direction
) {
    val head: Cell get() = cells.last()

    /** Alle Felder zwischen der Spitze und dem Rand des Spielfelds in Pfeilrichtung. */
    fun pathToEdge(rows: Int, cols: Int): List<Cell> {
        val path = mutableListOf<Cell>()
        var r = head.row
        var c = head.col
        while (true) {
            when (direction) {
                Direction.UP -> r -= 1
                Direction.DOWN -> r += 1
                Direction.LEFT -> c -= 1
                Direction.RIGHT -> c += 1
            }
            if (r !in 0 until rows || c !in 0 until cols) break
            path.add(Cell(r, c))
        }
        return path
    }
}

data class ArrowsLevel(
    val rows: Int,
    val cols: Int,
    val arrows: List<ArrowPiece>,
    val lives: Int = 3
)
