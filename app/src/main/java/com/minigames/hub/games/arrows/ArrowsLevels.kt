package com.minigames.hub.games.arrows

/**
 * Neue Level koennen hier einfach als weiterer Eintrag in der Liste ergaenzt werden.
 * Jedes Level ist unabhaengig von den anderen und wird beim Laden frisch kopiert.
 */
object ArrowsLevels {

    private fun level1() = ArrowsLevel(
        rows = 5,
        cols = 5,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(0, 0), Cell(0, 1)), Direction.RIGHT),
            ArrowPiece(2, listOf(Cell(0, 3), Cell(1, 3)), Direction.DOWN),
            ArrowPiece(3, listOf(Cell(4, 4)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(3, 2), Cell(2, 2)), Direction.UP)
        )
    )

    private fun level2() = ArrowsLevel(
        rows = 6,
        cols = 6,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(0, 0), Cell(0, 1), Cell(0, 2)), Direction.RIGHT),
            ArrowPiece(2, listOf(Cell(0, 4), Cell(1, 4)), Direction.DOWN),
            ArrowPiece(3, listOf(Cell(3, 5), Cell(3, 4)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(5, 0)), Direction.RIGHT)
        )
    )

    private fun level3() = ArrowsLevel(
        rows = 6,
        cols = 6,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(0, 0), Cell(0, 1), Cell(0, 2)), Direction.RIGHT),
            ArrowPiece(2, listOf(Cell(0, 4), Cell(1, 4)), Direction.DOWN),
            ArrowPiece(3, listOf(Cell(3, 5), Cell(3, 4)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(5, 5)), Direction.LEFT),
            ArrowPiece(5, listOf(Cell(5, 2)), Direction.UP),
            ArrowPiece(6, listOf(Cell(2, 0), Cell(2, 1)), Direction.RIGHT)
        )
    )

    val all: List<ArrowsLevel> = listOf(level1(), level2(), level3())
}
