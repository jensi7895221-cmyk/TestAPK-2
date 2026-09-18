package com.minigames.hub.games.arrows

enum class Direction { UP, DOWN, LEFT, RIGHT }

/** Optische Varianten der Pfeile, damit ein Level unuebersichtlicher/schwerer aussieht. */
enum class ArrowStyle { CLASSIC, ROUND, RIBBED, ZIGZAG, STAR }

data class Cell(val row: Int, val col: Int)

/**
 * Ein Pfeil besteht aus mehreren Feldern (dem Koerper) und zeigt in eine Richtung.
 * Das letzte Element in cells ist die Pfeilspitze (head). Der optische Stil wird,
 * sofern nicht angegeben, deterministisch aus der id abgeleitet, damit bestehende
 * Level-Definitionen ohne Aenderung eine gute Durchmischung der Stile bekommen.
 */
data class ArrowPiece(
    val id: Int,
    val cells: List<Cell>,
    val direction: Direction,
    val style: ArrowStyle = ArrowStyle.entries[((id - 1) % ArrowStyle.entries.size + ArrowStyle.entries.size) % ArrowStyle.entries.size]
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
