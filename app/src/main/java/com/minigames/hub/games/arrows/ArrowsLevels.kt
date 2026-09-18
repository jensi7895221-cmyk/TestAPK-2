package com.minigames.hub.games.arrows

import com.minigames.hub.games.Difficulty

/**
 * Level sind nach Schwierigkeit gruppiert: jeweils 10 Level pro Stufe.
 * Die Level werden algorithmisch erzeugt, indem die Pfeile in umgekehrter
 * Reihenfolge ihrer Loesung aufs Feld gesetzt werden, sodass garantiert eine
 * gueltige Zugfolge existiert, auch wenn sich viele Pfeile gegenseitig blockieren.
 */
object ArrowsLevels {

    fun forDifficulty(difficulty: Difficulty): List<ArrowsLevel> = when (difficulty) {
        Difficulty.EASY -> easy
        Difficulty.MEDIUM -> medium
        Difficulty.HARD -> hard
    }

    // EASY
    private fun easy1() = ArrowsLevel(
        rows = 6,
        cols = 6,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(3, 0), Cell(4, 0)), Direction.DOWN),
            ArrowPiece(2, listOf(Cell(0, 3)), Direction.UP),
            ArrowPiece(3, listOf(Cell(1, 4), Cell(1, 5)), Direction.RIGHT),
            ArrowPiece(4, listOf(Cell(4, 1)), Direction.RIGHT),
            ArrowPiece(5, listOf(Cell(0, 4)), Direction.UP),
            ArrowPiece(6, listOf(Cell(5, 4), Cell(5, 3)), Direction.LEFT),
            ArrowPiece(7, listOf(Cell(0, 2), Cell(1, 2)), Direction.DOWN),
            ArrowPiece(8, listOf(Cell(0, 5)), Direction.UP),
            ArrowPiece(9, listOf(Cell(3, 3), Cell(3, 4)), Direction.RIGHT),
            ArrowPiece(10, listOf(Cell(2, 1), Cell(3, 1)), Direction.DOWN)
        )
    )

    private fun easy2() = ArrowsLevel(
        rows = 6,
        cols = 6,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(5, 1), Cell(5, 0)), Direction.LEFT),
            ArrowPiece(2, listOf(Cell(0, 3)), Direction.UP),
            ArrowPiece(3, listOf(Cell(2, 2)), Direction.UP),
            ArrowPiece(4, listOf(Cell(1, 3)), Direction.LEFT),
            ArrowPiece(5, listOf(Cell(3, 2), Cell(3, 1)), Direction.LEFT),
            ArrowPiece(6, listOf(Cell(2, 3)), Direction.DOWN),
            ArrowPiece(7, listOf(Cell(3, 4)), Direction.RIGHT),
            ArrowPiece(8, listOf(Cell(4, 0)), Direction.DOWN),
            ArrowPiece(9, listOf(Cell(2, 4), Cell(1, 4)), Direction.UP),
            ArrowPiece(10, listOf(Cell(4, 1)), Direction.UP)
        )
    )

    private fun easy3() = ArrowsLevel(
        rows = 6,
        cols = 6,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(1, 4)), Direction.RIGHT),
            ArrowPiece(2, listOf(Cell(0, 1), Cell(0, 0)), Direction.LEFT),
            ArrowPiece(3, listOf(Cell(1, 3)), Direction.RIGHT),
            ArrowPiece(4, listOf(Cell(4, 1)), Direction.UP),
            ArrowPiece(5, listOf(Cell(2, 4)), Direction.DOWN),
            ArrowPiece(6, listOf(Cell(4, 3)), Direction.DOWN),
            ArrowPiece(7, listOf(Cell(4, 2), Cell(3, 2)), Direction.UP),
            ArrowPiece(8, listOf(Cell(5, 1)), Direction.UP),
            ArrowPiece(9, listOf(Cell(3, 5), Cell(2, 5)), Direction.UP),
            ArrowPiece(10, listOf(Cell(0, 4), Cell(0, 3)), Direction.LEFT)
        )
    )

    private fun easy4() = ArrowsLevel(
        rows = 6,
        cols = 6,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(0, 3)), Direction.UP),
            ArrowPiece(2, listOf(Cell(3, 2), Cell(4, 2)), Direction.DOWN),
            ArrowPiece(3, listOf(Cell(4, 4), Cell(5, 4)), Direction.DOWN),
            ArrowPiece(4, listOf(Cell(5, 5)), Direction.DOWN),
            ArrowPiece(5, listOf(Cell(4, 1), Cell(3, 1)), Direction.UP),
            ArrowPiece(6, listOf(Cell(1, 0)), Direction.UP),
            ArrowPiece(7, listOf(Cell(2, 4)), Direction.DOWN),
            ArrowPiece(8, listOf(Cell(3, 5)), Direction.RIGHT),
            ArrowPiece(9, listOf(Cell(3, 0), Cell(4, 0)), Direction.DOWN),
            ArrowPiece(10, listOf(Cell(1, 3)), Direction.RIGHT)
        )
    )

    private fun easy5() = ArrowsLevel(
        rows = 6,
        cols = 6,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(5, 3)), Direction.DOWN),
            ArrowPiece(2, listOf(Cell(2, 4), Cell(1, 4)), Direction.UP),
            ArrowPiece(3, listOf(Cell(1, 1)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(3, 1)), Direction.LEFT),
            ArrowPiece(5, listOf(Cell(2, 3)), Direction.DOWN),
            ArrowPiece(6, listOf(Cell(5, 4)), Direction.RIGHT),
            ArrowPiece(7, listOf(Cell(1, 5), Cell(2, 5)), Direction.DOWN),
            ArrowPiece(8, listOf(Cell(2, 0)), Direction.RIGHT),
            ArrowPiece(9, listOf(Cell(5, 2), Cell(5, 1)), Direction.LEFT),
            ArrowPiece(10, listOf(Cell(4, 4)), Direction.LEFT)
        )
    )

    private fun easy6() = ArrowsLevel(
        rows = 6,
        cols = 6,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(0, 0)), Direction.LEFT),
            ArrowPiece(2, listOf(Cell(4, 3)), Direction.LEFT),
            ArrowPiece(3, listOf(Cell(4, 5), Cell(4, 4)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(5, 0)), Direction.RIGHT),
            ArrowPiece(5, listOf(Cell(0, 2)), Direction.UP),
            ArrowPiece(6, listOf(Cell(3, 0), Cell(2, 0)), Direction.UP),
            ArrowPiece(7, listOf(Cell(2, 4), Cell(2, 5)), Direction.RIGHT),
            ArrowPiece(8, listOf(Cell(0, 1)), Direction.LEFT),
            ArrowPiece(9, listOf(Cell(0, 4), Cell(0, 5)), Direction.RIGHT),
            ArrowPiece(10, listOf(Cell(1, 2), Cell(2, 2)), Direction.DOWN)
        )
    )

    private fun easy7() = ArrowsLevel(
        rows = 6,
        cols = 6,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(1, 5)), Direction.RIGHT),
            ArrowPiece(2, listOf(Cell(2, 2)), Direction.LEFT),
            ArrowPiece(3, listOf(Cell(3, 1)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(0, 0)), Direction.LEFT),
            ArrowPiece(5, listOf(Cell(2, 3)), Direction.DOWN),
            ArrowPiece(6, listOf(Cell(1, 1)), Direction.LEFT),
            ArrowPiece(7, listOf(Cell(5, 1)), Direction.RIGHT),
            ArrowPiece(8, listOf(Cell(2, 5)), Direction.DOWN),
            ArrowPiece(9, listOf(Cell(4, 1)), Direction.UP),
            ArrowPiece(10, listOf(Cell(0, 2)), Direction.RIGHT)
        )
    )

    private fun easy8() = ArrowsLevel(
        rows = 6,
        cols = 6,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(1, 0), Cell(0, 0)), Direction.UP),
            ArrowPiece(2, listOf(Cell(2, 4), Cell(1, 4)), Direction.UP),
            ArrowPiece(3, listOf(Cell(1, 1)), Direction.UP),
            ArrowPiece(4, listOf(Cell(4, 2)), Direction.DOWN),
            ArrowPiece(5, listOf(Cell(4, 0), Cell(4, 1)), Direction.RIGHT),
            ArrowPiece(6, listOf(Cell(5, 4)), Direction.RIGHT),
            ArrowPiece(7, listOf(Cell(1, 3), Cell(2, 3)), Direction.DOWN),
            ArrowPiece(8, listOf(Cell(0, 5)), Direction.DOWN),
            ArrowPiece(9, listOf(Cell(3, 0), Cell(3, 1)), Direction.RIGHT),
            ArrowPiece(10, listOf(Cell(0, 2)), Direction.RIGHT)
        )
    )

    private fun easy9() = ArrowsLevel(
        rows = 6,
        cols = 6,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(5, 1), Cell(5, 0)), Direction.LEFT),
            ArrowPiece(2, listOf(Cell(1, 5), Cell(0, 5)), Direction.UP),
            ArrowPiece(3, listOf(Cell(4, 0)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(2, 1)), Direction.DOWN),
            ArrowPiece(5, listOf(Cell(1, 1), Cell(1, 2)), Direction.RIGHT),
            ArrowPiece(6, listOf(Cell(3, 5)), Direction.RIGHT),
            ArrowPiece(7, listOf(Cell(4, 3)), Direction.DOWN),
            ArrowPiece(8, listOf(Cell(4, 2), Cell(5, 2)), Direction.DOWN),
            ArrowPiece(9, listOf(Cell(5, 5), Cell(4, 5)), Direction.UP),
            ArrowPiece(10, listOf(Cell(2, 2), Cell(2, 3)), Direction.RIGHT)
        )
    )

    private fun easy10() = ArrowsLevel(
        rows = 6,
        cols = 6,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(4, 3), Cell(4, 4)), Direction.RIGHT),
            ArrowPiece(2, listOf(Cell(3, 5)), Direction.DOWN),
            ArrowPiece(3, listOf(Cell(4, 1)), Direction.DOWN),
            ArrowPiece(4, listOf(Cell(3, 4)), Direction.LEFT),
            ArrowPiece(5, listOf(Cell(5, 4), Cell(5, 3)), Direction.LEFT),
            ArrowPiece(6, listOf(Cell(1, 3), Cell(1, 4)), Direction.RIGHT),
            ArrowPiece(7, listOf(Cell(2, 1), Cell(2, 0)), Direction.LEFT),
            ArrowPiece(8, listOf(Cell(0, 0), Cell(1, 0)), Direction.DOWN),
            ArrowPiece(9, listOf(Cell(2, 4)), Direction.RIGHT),
            ArrowPiece(10, listOf(Cell(2, 2), Cell(1, 2)), Direction.UP)
        )
    )

    val easy: List<ArrowsLevel> = listOf(easy1(), easy2(), easy3(), easy4(), easy5(), easy6(), easy7(), easy8(), easy9(), easy10())

    // MEDIUM
    private fun medium1() = ArrowsLevel(
        rows = 8,
        cols = 8,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(1, 7), Cell(0, 7)), Direction.UP),
            ArrowPiece(2, listOf(Cell(7, 7)), Direction.RIGHT),
            ArrowPiece(3, listOf(Cell(2, 7)), Direction.RIGHT),
            ArrowPiece(4, listOf(Cell(5, 0), Cell(6, 0)), Direction.DOWN),
            ArrowPiece(5, listOf(Cell(4, 5), Cell(3, 5)), Direction.UP),
            ArrowPiece(6, listOf(Cell(1, 3)), Direction.RIGHT),
            ArrowPiece(7, listOf(Cell(5, 3), Cell(4, 3)), Direction.UP),
            ArrowPiece(8, listOf(Cell(0, 6)), Direction.UP),
            ArrowPiece(9, listOf(Cell(3, 4), Cell(4, 4), Cell(5, 4)), Direction.DOWN),
            ArrowPiece(10, listOf(Cell(1, 0), Cell(2, 0), Cell(3, 0)), Direction.DOWN),
            ArrowPiece(11, listOf(Cell(6, 1), Cell(7, 1)), Direction.DOWN),
            ArrowPiece(12, listOf(Cell(1, 1), Cell(1, 2)), Direction.RIGHT),
            ArrowPiece(13, listOf(Cell(7, 3), Cell(6, 3)), Direction.UP),
            ArrowPiece(14, listOf(Cell(6, 7), Cell(6, 6), Cell(6, 5)), Direction.LEFT),
            ArrowPiece(15, listOf(Cell(3, 1)), Direction.RIGHT),
            ArrowPiece(16, listOf(Cell(5, 7), Cell(4, 7)), Direction.UP),
            ArrowPiece(17, listOf(Cell(7, 2)), Direction.LEFT),
            ArrowPiece(18, listOf(Cell(0, 2)), Direction.UP)
        )
    )

    private fun medium2() = ArrowsLevel(
        rows = 8,
        cols = 8,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(1, 1)), Direction.UP),
            ArrowPiece(2, listOf(Cell(7, 7)), Direction.RIGHT),
            ArrowPiece(3, listOf(Cell(0, 3)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(4, 1), Cell(3, 1)), Direction.UP),
            ArrowPiece(5, listOf(Cell(4, 6), Cell(5, 6), Cell(6, 6)), Direction.DOWN),
            ArrowPiece(6, listOf(Cell(0, 7), Cell(1, 7), Cell(2, 7)), Direction.DOWN),
            ArrowPiece(7, listOf(Cell(6, 4), Cell(6, 5)), Direction.RIGHT),
            ArrowPiece(8, listOf(Cell(6, 0), Cell(7, 0)), Direction.DOWN),
            ArrowPiece(9, listOf(Cell(1, 3)), Direction.UP),
            ArrowPiece(10, listOf(Cell(5, 4)), Direction.RIGHT),
            ArrowPiece(11, listOf(Cell(3, 0), Cell(2, 0), Cell(1, 0)), Direction.UP),
            ArrowPiece(12, listOf(Cell(0, 6)), Direction.UP),
            ArrowPiece(13, listOf(Cell(0, 5), Cell(1, 5)), Direction.DOWN),
            ArrowPiece(14, listOf(Cell(3, 6)), Direction.LEFT),
            ArrowPiece(15, listOf(Cell(7, 1), Cell(7, 2), Cell(7, 3)), Direction.RIGHT),
            ArrowPiece(16, listOf(Cell(2, 2), Cell(2, 3)), Direction.RIGHT),
            ArrowPiece(17, listOf(Cell(4, 0)), Direction.UP),
            ArrowPiece(18, listOf(Cell(5, 3), Cell(5, 2), Cell(5, 1)), Direction.LEFT)
        )
    )

    private fun medium3() = ArrowsLevel(
        rows = 8,
        cols = 8,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(2, 6)), Direction.RIGHT),
            ArrowPiece(2, listOf(Cell(1, 1)), Direction.LEFT),
            ArrowPiece(3, listOf(Cell(5, 1), Cell(5, 0)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(7, 1), Cell(7, 2), Cell(7, 3)), Direction.RIGHT),
            ArrowPiece(5, listOf(Cell(3, 6), Cell(3, 7)), Direction.RIGHT),
            ArrowPiece(6, listOf(Cell(2, 2)), Direction.RIGHT),
            ArrowPiece(7, listOf(Cell(1, 6), Cell(0, 6)), Direction.UP),
            ArrowPiece(8, listOf(Cell(6, 6)), Direction.DOWN),
            ArrowPiece(9, listOf(Cell(1, 7)), Direction.RIGHT),
            ArrowPiece(10, listOf(Cell(6, 2)), Direction.DOWN),
            ArrowPiece(11, listOf(Cell(1, 2)), Direction.UP),
            ArrowPiece(12, listOf(Cell(5, 4), Cell(4, 4)), Direction.UP),
            ArrowPiece(13, listOf(Cell(0, 0)), Direction.RIGHT),
            ArrowPiece(14, listOf(Cell(4, 2), Cell(3, 2)), Direction.UP),
            ArrowPiece(15, listOf(Cell(4, 5), Cell(5, 5)), Direction.DOWN),
            ArrowPiece(16, listOf(Cell(4, 1)), Direction.RIGHT),
            ArrowPiece(17, listOf(Cell(1, 5)), Direction.DOWN),
            ArrowPiece(18, listOf(Cell(6, 7)), Direction.LEFT)
        )
    )

    private fun medium4() = ArrowsLevel(
        rows = 8,
        cols = 8,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(4, 3)), Direction.UP),
            ArrowPiece(2, listOf(Cell(0, 1)), Direction.UP),
            ArrowPiece(3, listOf(Cell(3, 0)), Direction.UP),
            ArrowPiece(4, listOf(Cell(4, 7), Cell(5, 7), Cell(6, 7)), Direction.DOWN),
            ArrowPiece(5, listOf(Cell(2, 2), Cell(1, 2), Cell(0, 2)), Direction.UP),
            ArrowPiece(6, listOf(Cell(1, 5)), Direction.LEFT),
            ArrowPiece(7, listOf(Cell(5, 1), Cell(5, 0)), Direction.LEFT),
            ArrowPiece(8, listOf(Cell(1, 6), Cell(0, 6)), Direction.UP),
            ArrowPiece(9, listOf(Cell(6, 4), Cell(6, 5), Cell(6, 6)), Direction.RIGHT),
            ArrowPiece(10, listOf(Cell(7, 3)), Direction.DOWN),
            ArrowPiece(11, listOf(Cell(3, 5)), Direction.UP),
            ArrowPiece(12, listOf(Cell(4, 2), Cell(4, 1)), Direction.LEFT),
            ArrowPiece(13, listOf(Cell(4, 5), Cell(4, 4)), Direction.LEFT),
            ArrowPiece(14, listOf(Cell(7, 1)), Direction.DOWN),
            ArrowPiece(15, listOf(Cell(5, 5)), Direction.UP),
            ArrowPiece(16, listOf(Cell(3, 7), Cell(2, 7), Cell(1, 7)), Direction.UP),
            ArrowPiece(17, listOf(Cell(3, 2)), Direction.DOWN),
            ArrowPiece(18, listOf(Cell(4, 6), Cell(5, 6)), Direction.DOWN)
        )
    )

    private fun medium5() = ArrowsLevel(
        rows = 8,
        cols = 8,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(6, 1)), Direction.LEFT),
            ArrowPiece(2, listOf(Cell(2, 1)), Direction.UP),
            ArrowPiece(3, listOf(Cell(0, 0)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(0, 6)), Direction.UP),
            ArrowPiece(5, listOf(Cell(5, 7), Cell(6, 7)), Direction.DOWN),
            ArrowPiece(6, listOf(Cell(0, 2), Cell(0, 3)), Direction.RIGHT),
            ArrowPiece(7, listOf(Cell(6, 4)), Direction.DOWN),
            ArrowPiece(8, listOf(Cell(7, 0)), Direction.LEFT),
            ArrowPiece(9, listOf(Cell(5, 1)), Direction.DOWN),
            ArrowPiece(10, listOf(Cell(1, 5)), Direction.DOWN),
            ArrowPiece(11, listOf(Cell(6, 2), Cell(7, 2)), Direction.DOWN),
            ArrowPiece(12, listOf(Cell(1, 7)), Direction.UP),
            ArrowPiece(13, listOf(Cell(2, 7)), Direction.RIGHT),
            ArrowPiece(14, listOf(Cell(3, 3)), Direction.RIGHT),
            ArrowPiece(15, listOf(Cell(2, 3), Cell(2, 2)), Direction.LEFT),
            ArrowPiece(16, listOf(Cell(6, 6)), Direction.DOWN),
            ArrowPiece(17, listOf(Cell(4, 3), Cell(4, 2), Cell(4, 1)), Direction.LEFT),
            ArrowPiece(18, listOf(Cell(5, 3), Cell(5, 4)), Direction.RIGHT)
        )
    )

    private fun medium6() = ArrowsLevel(
        rows = 8,
        cols = 8,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(1, 0)), Direction.LEFT),
            ArrowPiece(2, listOf(Cell(0, 3), Cell(0, 4)), Direction.RIGHT),
            ArrowPiece(3, listOf(Cell(7, 6), Cell(7, 7)), Direction.RIGHT),
            ArrowPiece(4, listOf(Cell(3, 1), Cell(3, 0)), Direction.LEFT),
            ArrowPiece(5, listOf(Cell(7, 5)), Direction.RIGHT),
            ArrowPiece(6, listOf(Cell(7, 2), Cell(7, 1)), Direction.LEFT),
            ArrowPiece(7, listOf(Cell(4, 2), Cell(4, 1), Cell(4, 0)), Direction.LEFT),
            ArrowPiece(8, listOf(Cell(3, 5), Cell(3, 4), Cell(3, 3)), Direction.LEFT),
            ArrowPiece(9, listOf(Cell(2, 1), Cell(1, 1), Cell(0, 1)), Direction.UP),
            ArrowPiece(10, listOf(Cell(4, 7), Cell(5, 7)), Direction.DOWN),
            ArrowPiece(11, listOf(Cell(4, 4)), Direction.LEFT),
            ArrowPiece(12, listOf(Cell(5, 0)), Direction.RIGHT),
            ArrowPiece(13, listOf(Cell(1, 7), Cell(2, 7), Cell(3, 7)), Direction.DOWN),
            ArrowPiece(14, listOf(Cell(1, 2), Cell(1, 3), Cell(1, 4)), Direction.RIGHT),
            ArrowPiece(15, listOf(Cell(6, 4), Cell(6, 5), Cell(6, 6)), Direction.RIGHT),
            ArrowPiece(16, listOf(Cell(6, 2)), Direction.DOWN),
            ArrowPiece(17, listOf(Cell(7, 3)), Direction.LEFT),
            ArrowPiece(18, listOf(Cell(3, 6)), Direction.RIGHT)
        )
    )

    private fun medium7() = ArrowsLevel(
        rows = 8,
        cols = 8,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(1, 5)), Direction.UP),
            ArrowPiece(2, listOf(Cell(4, 6), Cell(4, 7)), Direction.RIGHT),
            ArrowPiece(3, listOf(Cell(3, 6)), Direction.RIGHT),
            ArrowPiece(4, listOf(Cell(2, 7)), Direction.RIGHT),
            ArrowPiece(5, listOf(Cell(3, 2), Cell(3, 1), Cell(3, 0)), Direction.LEFT),
            ArrowPiece(6, listOf(Cell(2, 3)), Direction.UP),
            ArrowPiece(7, listOf(Cell(1, 7), Cell(0, 7)), Direction.UP),
            ArrowPiece(8, listOf(Cell(3, 3)), Direction.UP),
            ArrowPiece(9, listOf(Cell(4, 1)), Direction.LEFT),
            ArrowPiece(10, listOf(Cell(6, 4), Cell(6, 3), Cell(6, 2)), Direction.LEFT),
            ArrowPiece(11, listOf(Cell(0, 2), Cell(1, 2)), Direction.DOWN),
            ArrowPiece(12, listOf(Cell(2, 6), Cell(1, 6), Cell(0, 6)), Direction.UP),
            ArrowPiece(13, listOf(Cell(7, 0)), Direction.DOWN),
            ArrowPiece(14, listOf(Cell(4, 5), Cell(4, 4), Cell(4, 3)), Direction.LEFT),
            ArrowPiece(15, listOf(Cell(7, 4), Cell(7, 3)), Direction.LEFT),
            ArrowPiece(16, listOf(Cell(6, 7), Cell(5, 7)), Direction.UP),
            ArrowPiece(17, listOf(Cell(2, 1)), Direction.DOWN),
            ArrowPiece(18, listOf(Cell(0, 0)), Direction.DOWN)
        )
    )

    private fun medium8() = ArrowsLevel(
        rows = 8,
        cols = 8,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(5, 4), Cell(6, 4)), Direction.DOWN),
            ArrowPiece(2, listOf(Cell(6, 5)), Direction.RIGHT),
            ArrowPiece(3, listOf(Cell(0, 7)), Direction.UP),
            ArrowPiece(4, listOf(Cell(0, 5), Cell(1, 5), Cell(2, 5)), Direction.DOWN),
            ArrowPiece(5, listOf(Cell(6, 3), Cell(6, 2), Cell(6, 1)), Direction.LEFT),
            ArrowPiece(6, listOf(Cell(3, 0), Cell(4, 0), Cell(5, 0)), Direction.DOWN),
            ArrowPiece(7, listOf(Cell(7, 6)), Direction.RIGHT),
            ArrowPiece(8, listOf(Cell(2, 1)), Direction.UP),
            ArrowPiece(9, listOf(Cell(2, 3), Cell(1, 3), Cell(0, 3)), Direction.UP),
            ArrowPiece(10, listOf(Cell(5, 6), Cell(5, 7)), Direction.RIGHT),
            ArrowPiece(11, listOf(Cell(2, 6), Cell(3, 6)), Direction.DOWN),
            ArrowPiece(12, listOf(Cell(3, 7), Cell(2, 7), Cell(1, 7)), Direction.UP),
            ArrowPiece(13, listOf(Cell(4, 7)), Direction.LEFT),
            ArrowPiece(14, listOf(Cell(1, 2), Cell(0, 2)), Direction.UP),
            ArrowPiece(15, listOf(Cell(0, 4), Cell(1, 4)), Direction.DOWN),
            ArrowPiece(16, listOf(Cell(5, 2), Cell(5, 1)), Direction.LEFT),
            ArrowPiece(17, listOf(Cell(2, 0)), Direction.UP),
            ArrowPiece(18, listOf(Cell(7, 3)), Direction.UP)
        )
    )

    private fun medium9() = ArrowsLevel(
        rows = 8,
        cols = 8,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(4, 0)), Direction.LEFT),
            ArrowPiece(2, listOf(Cell(7, 7)), Direction.DOWN),
            ArrowPiece(3, listOf(Cell(7, 4)), Direction.RIGHT),
            ArrowPiece(4, listOf(Cell(7, 2), Cell(7, 1), Cell(7, 0)), Direction.LEFT),
            ArrowPiece(5, listOf(Cell(5, 5), Cell(6, 5)), Direction.DOWN),
            ArrowPiece(6, listOf(Cell(0, 4)), Direction.LEFT),
            ArrowPiece(7, listOf(Cell(3, 3), Cell(3, 2), Cell(3, 1)), Direction.LEFT),
            ArrowPiece(8, listOf(Cell(5, 0), Cell(6, 0)), Direction.DOWN),
            ArrowPiece(9, listOf(Cell(4, 1)), Direction.LEFT),
            ArrowPiece(10, listOf(Cell(6, 1)), Direction.DOWN),
            ArrowPiece(11, listOf(Cell(5, 3), Cell(6, 3), Cell(7, 3)), Direction.DOWN),
            ArrowPiece(12, listOf(Cell(3, 6)), Direction.RIGHT),
            ArrowPiece(13, listOf(Cell(4, 7), Cell(5, 7)), Direction.DOWN),
            ArrowPiece(14, listOf(Cell(1, 4), Cell(1, 5), Cell(1, 6)), Direction.RIGHT),
            ArrowPiece(15, listOf(Cell(3, 4), Cell(4, 4)), Direction.DOWN),
            ArrowPiece(16, listOf(Cell(5, 2), Cell(4, 2)), Direction.UP),
            ArrowPiece(17, listOf(Cell(2, 1), Cell(2, 0)), Direction.LEFT),
            ArrowPiece(18, listOf(Cell(3, 5), Cell(4, 5)), Direction.DOWN)
        )
    )

    private fun medium10() = ArrowsLevel(
        rows = 8,
        cols = 8,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(6, 0)), Direction.DOWN),
            ArrowPiece(2, listOf(Cell(6, 5), Cell(5, 5), Cell(4, 5)), Direction.UP),
            ArrowPiece(3, listOf(Cell(7, 7), Cell(7, 6)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(2, 0)), Direction.LEFT),
            ArrowPiece(5, listOf(Cell(1, 0)), Direction.LEFT),
            ArrowPiece(6, listOf(Cell(5, 2)), Direction.LEFT),
            ArrowPiece(7, listOf(Cell(1, 3)), Direction.UP),
            ArrowPiece(8, listOf(Cell(6, 6)), Direction.DOWN),
            ArrowPiece(9, listOf(Cell(4, 0)), Direction.LEFT),
            ArrowPiece(10, listOf(Cell(5, 3), Cell(4, 3), Cell(3, 3)), Direction.UP),
            ArrowPiece(11, listOf(Cell(2, 2)), Direction.LEFT),
            ArrowPiece(12, listOf(Cell(0, 4)), Direction.UP),
            ArrowPiece(13, listOf(Cell(6, 1)), Direction.LEFT),
            ArrowPiece(14, listOf(Cell(6, 3), Cell(6, 4)), Direction.RIGHT),
            ArrowPiece(15, listOf(Cell(2, 7)), Direction.DOWN),
            ArrowPiece(16, listOf(Cell(0, 6), Cell(1, 6)), Direction.DOWN),
            ArrowPiece(17, listOf(Cell(5, 4)), Direction.UP),
            ArrowPiece(18, listOf(Cell(1, 1)), Direction.RIGHT)
        )
    )

    val medium: List<ArrowsLevel> = listOf(medium1(), medium2(), medium3(), medium4(), medium5(), medium6(), medium7(), medium8(), medium9(), medium10())

    // HARD
    private fun hard1() = ArrowsLevel(
        rows = 10,
        cols = 10,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(8, 2), Cell(9, 2)), Direction.DOWN),
            ArrowPiece(2, listOf(Cell(4, 8), Cell(4, 9)), Direction.RIGHT),
            ArrowPiece(3, listOf(Cell(1, 2), Cell(1, 1)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(5, 1), Cell(5, 0)), Direction.LEFT),
            ArrowPiece(5, listOf(Cell(8, 8), Cell(9, 8)), Direction.DOWN),
            ArrowPiece(6, listOf(Cell(3, 2), Cell(3, 1), Cell(3, 0)), Direction.LEFT),
            ArrowPiece(7, listOf(Cell(1, 6), Cell(0, 6)), Direction.UP),
            ArrowPiece(8, listOf(Cell(4, 1), Cell(4, 0)), Direction.LEFT),
            ArrowPiece(9, listOf(Cell(6, 0), Cell(7, 0), Cell(8, 0), Cell(9, 0)), Direction.DOWN),
            ArrowPiece(10, listOf(Cell(4, 6), Cell(4, 7)), Direction.RIGHT),
            ArrowPiece(11, listOf(Cell(0, 7), Cell(0, 8), Cell(0, 9)), Direction.RIGHT),
            ArrowPiece(12, listOf(Cell(5, 5), Cell(5, 6), Cell(5, 7), Cell(5, 8)), Direction.RIGHT),
            ArrowPiece(13, listOf(Cell(7, 1), Cell(8, 1)), Direction.DOWN),
            ArrowPiece(14, listOf(Cell(0, 2), Cell(0, 3), Cell(0, 4), Cell(0, 5)), Direction.RIGHT),
            ArrowPiece(15, listOf(Cell(8, 3), Cell(9, 3)), Direction.DOWN),
            ArrowPiece(16, listOf(Cell(2, 2), Cell(2, 1), Cell(2, 0)), Direction.LEFT),
            ArrowPiece(17, listOf(Cell(3, 3), Cell(2, 3), Cell(1, 3)), Direction.UP),
            ArrowPiece(18, listOf(Cell(1, 7), Cell(1, 8)), Direction.RIGHT),
            ArrowPiece(19, listOf(Cell(5, 2), Cell(6, 2), Cell(7, 2)), Direction.DOWN),
            ArrowPiece(20, listOf(Cell(9, 7), Cell(9, 6), Cell(9, 5), Cell(9, 4)), Direction.LEFT),
            ArrowPiece(21, listOf(Cell(3, 4), Cell(4, 4)), Direction.DOWN),
            ArrowPiece(22, listOf(Cell(4, 3), Cell(5, 3), Cell(6, 3)), Direction.DOWN),
            ArrowPiece(23, listOf(Cell(2, 7), Cell(2, 8), Cell(2, 9)), Direction.RIGHT),
            ArrowPiece(24, listOf(Cell(6, 9), Cell(7, 9), Cell(8, 9), Cell(9, 9)), Direction.DOWN),
            ArrowPiece(25, listOf(Cell(0, 0), Cell(0, 1)), Direction.RIGHT),
            ArrowPiece(26, listOf(Cell(2, 5), Cell(2, 4)), Direction.LEFT),
            ArrowPiece(27, listOf(Cell(3, 6), Cell(3, 7), Cell(3, 8), Cell(3, 9)), Direction.RIGHT),
            ArrowPiece(28, listOf(Cell(7, 5), Cell(7, 6), Cell(7, 7), Cell(7, 8)), Direction.RIGHT)
        )
    )

    private fun hard2() = ArrowsLevel(
        rows = 10,
        cols = 10,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(1, 9), Cell(0, 9)), Direction.UP),
            ArrowPiece(2, listOf(Cell(0, 1), Cell(0, 0)), Direction.LEFT),
            ArrowPiece(3, listOf(Cell(7, 8), Cell(7, 9)), Direction.RIGHT),
            ArrowPiece(4, listOf(Cell(9, 1), Cell(9, 0)), Direction.LEFT),
            ArrowPiece(5, listOf(Cell(7, 1), Cell(7, 0)), Direction.LEFT),
            ArrowPiece(6, listOf(Cell(5, 1), Cell(5, 0)), Direction.LEFT),
            ArrowPiece(7, listOf(Cell(8, 7), Cell(8, 8), Cell(8, 9)), Direction.RIGHT),
            ArrowPiece(8, listOf(Cell(9, 7), Cell(9, 8), Cell(9, 9)), Direction.RIGHT),
            ArrowPiece(9, listOf(Cell(5, 3), Cell(5, 2)), Direction.LEFT),
            ArrowPiece(10, listOf(Cell(7, 2), Cell(8, 2), Cell(9, 2)), Direction.DOWN),
            ArrowPiece(11, listOf(Cell(4, 0), Cell(3, 0), Cell(2, 0)), Direction.UP),
            ArrowPiece(12, listOf(Cell(0, 5), Cell(0, 4), Cell(0, 3), Cell(0, 2)), Direction.LEFT),
            ArrowPiece(13, listOf(Cell(8, 5), Cell(8, 6)), Direction.RIGHT),
            ArrowPiece(14, listOf(Cell(2, 8), Cell(3, 8), Cell(4, 8), Cell(5, 8)), Direction.DOWN),
            ArrowPiece(15, listOf(Cell(3, 5), Cell(3, 4), Cell(3, 3), Cell(3, 2)), Direction.LEFT),
            ArrowPiece(16, listOf(Cell(9, 6), Cell(9, 5), Cell(9, 4), Cell(9, 3)), Direction.LEFT),
            ArrowPiece(17, listOf(Cell(8, 1), Cell(8, 0)), Direction.LEFT),
            ArrowPiece(18, listOf(Cell(5, 9), Cell(6, 9)), Direction.DOWN),
            ArrowPiece(19, listOf(Cell(4, 6), Cell(4, 5), Cell(4, 4), Cell(4, 3)), Direction.LEFT),
            ArrowPiece(20, listOf(Cell(6, 3), Cell(7, 3), Cell(8, 3)), Direction.DOWN),
            ArrowPiece(21, listOf(Cell(6, 7), Cell(7, 7)), Direction.DOWN),
            ArrowPiece(22, listOf(Cell(2, 9), Cell(3, 9), Cell(4, 9)), Direction.DOWN),
            ArrowPiece(23, listOf(Cell(1, 8), Cell(0, 8)), Direction.UP),
            ArrowPiece(24, listOf(Cell(1, 3), Cell(1, 2), Cell(1, 1)), Direction.LEFT),
            ArrowPiece(25, listOf(Cell(0, 6), Cell(0, 7)), Direction.RIGHT),
            ArrowPiece(26, listOf(Cell(5, 6), Cell(5, 5)), Direction.LEFT),
            ArrowPiece(27, listOf(Cell(2, 7), Cell(2, 6), Cell(2, 5), Cell(2, 4)), Direction.LEFT),
            ArrowPiece(28, listOf(Cell(6, 2), Cell(6, 1), Cell(6, 0)), Direction.LEFT)
        )
    )

    private fun hard3() = ArrowsLevel(
        rows = 10,
        cols = 10,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(8, 5), Cell(9, 5)), Direction.DOWN),
            ArrowPiece(2, listOf(Cell(1, 1), Cell(0, 1)), Direction.UP),
            ArrowPiece(3, listOf(Cell(3, 8), Cell(3, 9)), Direction.RIGHT),
            ArrowPiece(4, listOf(Cell(8, 1), Cell(8, 0)), Direction.LEFT),
            ArrowPiece(5, listOf(Cell(1, 9), Cell(0, 9)), Direction.UP),
            ArrowPiece(6, listOf(Cell(5, 7), Cell(5, 8), Cell(5, 9)), Direction.RIGHT),
            ArrowPiece(7, listOf(Cell(2, 2), Cell(1, 2)), Direction.UP),
            ArrowPiece(8, listOf(Cell(9, 1), Cell(9, 0)), Direction.LEFT),
            ArrowPiece(9, listOf(Cell(9, 4), Cell(9, 3)), Direction.LEFT),
            ArrowPiece(10, listOf(Cell(2, 0), Cell(1, 0), Cell(0, 0)), Direction.UP),
            ArrowPiece(11, listOf(Cell(3, 5), Cell(3, 6), Cell(3, 7)), Direction.RIGHT),
            ArrowPiece(12, listOf(Cell(2, 6), Cell(2, 7), Cell(2, 8), Cell(2, 9)), Direction.RIGHT),
            ArrowPiece(13, listOf(Cell(5, 0), Cell(6, 0), Cell(7, 0)), Direction.DOWN),
            ArrowPiece(14, listOf(Cell(4, 6), Cell(4, 7), Cell(4, 8), Cell(4, 9)), Direction.RIGHT),
            ArrowPiece(15, listOf(Cell(4, 0), Cell(3, 0)), Direction.UP),
            ArrowPiece(16, listOf(Cell(1, 8), Cell(0, 8)), Direction.UP),
            ArrowPiece(17, listOf(Cell(6, 1), Cell(5, 1), Cell(4, 1)), Direction.UP),
            ArrowPiece(18, listOf(Cell(7, 2), Cell(8, 2)), Direction.DOWN),
            ArrowPiece(19, listOf(Cell(4, 3), Cell(3, 3), Cell(2, 3), Cell(1, 3)), Direction.UP),
            ArrowPiece(20, listOf(Cell(9, 9), Cell(9, 8), Cell(9, 7)), Direction.LEFT),
            ArrowPiece(21, listOf(Cell(1, 4), Cell(1, 5)), Direction.RIGHT),
            ArrowPiece(22, listOf(Cell(0, 4), Cell(0, 5), Cell(0, 6), Cell(0, 7)), Direction.RIGHT),
            ArrowPiece(23, listOf(Cell(3, 2), Cell(4, 2), Cell(5, 2), Cell(6, 2)), Direction.DOWN),
            ArrowPiece(24, listOf(Cell(4, 5), Cell(4, 4)), Direction.LEFT),
            ArrowPiece(25, listOf(Cell(6, 8), Cell(7, 8), Cell(8, 8)), Direction.DOWN),
            ArrowPiece(26, listOf(Cell(6, 5), Cell(7, 5)), Direction.DOWN),
            ArrowPiece(27, listOf(Cell(5, 6), Cell(6, 6), Cell(7, 6), Cell(8, 6)), Direction.DOWN),
            ArrowPiece(28, listOf(Cell(7, 4), Cell(7, 3)), Direction.LEFT)
        )
    )

    private fun hard4() = ArrowsLevel(
        rows = 10,
        cols = 10,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(1, 4), Cell(0, 4)), Direction.UP),
            ArrowPiece(2, listOf(Cell(2, 7), Cell(2, 8), Cell(2, 9)), Direction.RIGHT),
            ArrowPiece(3, listOf(Cell(3, 2), Cell(2, 2)), Direction.UP),
            ArrowPiece(4, listOf(Cell(7, 6), Cell(8, 6), Cell(9, 6)), Direction.DOWN),
            ArrowPiece(5, listOf(Cell(2, 3), Cell(1, 3)), Direction.UP),
            ArrowPiece(6, listOf(Cell(1, 1), Cell(0, 1)), Direction.UP),
            ArrowPiece(7, listOf(Cell(6, 5), Cell(7, 5)), Direction.DOWN),
            ArrowPiece(8, listOf(Cell(4, 1), Cell(3, 1)), Direction.UP),
            ArrowPiece(9, listOf(Cell(8, 7), Cell(9, 7)), Direction.DOWN),
            ArrowPiece(10, listOf(Cell(3, 4), Cell(3, 3)), Direction.LEFT),
            ArrowPiece(11, listOf(Cell(8, 4), Cell(8, 3), Cell(8, 2)), Direction.LEFT),
            ArrowPiece(12, listOf(Cell(7, 8), Cell(7, 9)), Direction.RIGHT),
            ArrowPiece(13, listOf(Cell(5, 0), Cell(6, 0)), Direction.DOWN),
            ArrowPiece(14, listOf(Cell(7, 1), Cell(6, 1)), Direction.UP),
            ArrowPiece(15, listOf(Cell(9, 8), Cell(9, 9)), Direction.RIGHT),
            ArrowPiece(16, listOf(Cell(6, 6), Cell(6, 7), Cell(6, 8), Cell(6, 9)), Direction.RIGHT),
            ArrowPiece(17, listOf(Cell(5, 7), Cell(5, 8)), Direction.RIGHT),
            ArrowPiece(18, listOf(Cell(1, 5), Cell(0, 5)), Direction.UP),
            ArrowPiece(19, listOf(Cell(1, 7), Cell(1, 8), Cell(1, 9)), Direction.RIGHT),
            ArrowPiece(20, listOf(Cell(4, 7), Cell(4, 8)), Direction.RIGHT),
            ArrowPiece(21, listOf(Cell(2, 4), Cell(2, 5)), Direction.RIGHT),
            ArrowPiece(22, listOf(Cell(9, 2), Cell(9, 1)), Direction.LEFT),
            ArrowPiece(23, listOf(Cell(4, 2), Cell(5, 2)), Direction.DOWN),
            ArrowPiece(24, listOf(Cell(3, 9), Cell(3, 8), Cell(3, 7)), Direction.LEFT),
            ArrowPiece(25, listOf(Cell(0, 8), Cell(0, 9)), Direction.RIGHT),
            ArrowPiece(26, listOf(Cell(7, 3), Cell(6, 3), Cell(5, 3)), Direction.UP),
            ArrowPiece(27, listOf(Cell(4, 6), Cell(4, 5), Cell(4, 4)), Direction.LEFT),
            ArrowPiece(28, listOf(Cell(0, 0), Cell(1, 0), Cell(2, 0)), Direction.DOWN)
        )
    )

    private fun hard5() = ArrowsLevel(
        rows = 10,
        cols = 10,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(7, 1), Cell(7, 0)), Direction.LEFT),
            ArrowPiece(2, listOf(Cell(2, 3), Cell(2, 2), Cell(2, 1)), Direction.LEFT),
            ArrowPiece(3, listOf(Cell(8, 3), Cell(8, 2), Cell(8, 1)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(0, 8), Cell(0, 9)), Direction.RIGHT),
            ArrowPiece(5, listOf(Cell(5, 2), Cell(5, 1), Cell(5, 0)), Direction.LEFT),
            ArrowPiece(6, listOf(Cell(7, 9), Cell(8, 9)), Direction.DOWN),
            ArrowPiece(7, listOf(Cell(5, 5), Cell(5, 4)), Direction.LEFT),
            ArrowPiece(8, listOf(Cell(4, 8), Cell(4, 9)), Direction.RIGHT),
            ArrowPiece(9, listOf(Cell(1, 0), Cell(0, 0)), Direction.UP),
            ArrowPiece(10, listOf(Cell(3, 1), Cell(3, 0)), Direction.LEFT),
            ArrowPiece(11, listOf(Cell(7, 3), Cell(7, 2)), Direction.LEFT),
            ArrowPiece(12, listOf(Cell(9, 7), Cell(9, 8)), Direction.RIGHT),
            ArrowPiece(13, listOf(Cell(6, 3), Cell(6, 2), Cell(6, 1), Cell(6, 0)), Direction.LEFT),
            ArrowPiece(14, listOf(Cell(3, 4), Cell(3, 3), Cell(3, 2)), Direction.LEFT),
            ArrowPiece(15, listOf(Cell(5, 7), Cell(5, 8), Cell(5, 9)), Direction.RIGHT),
            ArrowPiece(16, listOf(Cell(0, 5), Cell(0, 6), Cell(0, 7)), Direction.RIGHT),
            ArrowPiece(17, listOf(Cell(4, 3), Cell(4, 4), Cell(4, 5)), Direction.RIGHT),
            ArrowPiece(18, listOf(Cell(8, 4), Cell(8, 5), Cell(8, 6), Cell(8, 7)), Direction.RIGHT),
            ArrowPiece(19, listOf(Cell(2, 6), Cell(2, 7), Cell(2, 8)), Direction.RIGHT),
            ArrowPiece(20, listOf(Cell(0, 1), Cell(0, 2), Cell(0, 3), Cell(0, 4)), Direction.RIGHT),
            ArrowPiece(21, listOf(Cell(9, 1), Cell(9, 0)), Direction.LEFT),
            ArrowPiece(22, listOf(Cell(1, 7), Cell(1, 8), Cell(1, 9)), Direction.RIGHT),
            ArrowPiece(23, listOf(Cell(6, 9), Cell(6, 8), Cell(6, 7)), Direction.LEFT),
            ArrowPiece(24, listOf(Cell(3, 8), Cell(3, 7), Cell(3, 6), Cell(3, 5)), Direction.LEFT),
            ArrowPiece(25, listOf(Cell(7, 6), Cell(7, 5)), Direction.LEFT),
            ArrowPiece(26, listOf(Cell(1, 5), Cell(1, 4), Cell(1, 3), Cell(1, 2)), Direction.LEFT),
            ArrowPiece(27, listOf(Cell(9, 2), Cell(9, 3), Cell(9, 4)), Direction.RIGHT),
            ArrowPiece(28, listOf(Cell(4, 0), Cell(4, 1)), Direction.RIGHT)
        )
    )

    private fun hard6() = ArrowsLevel(
        rows = 10,
        cols = 10,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(1, 4), Cell(0, 4)), Direction.UP),
            ArrowPiece(2, listOf(Cell(1, 5), Cell(0, 5)), Direction.UP),
            ArrowPiece(3, listOf(Cell(1, 3), Cell(0, 3)), Direction.UP),
            ArrowPiece(4, listOf(Cell(4, 4), Cell(4, 3), Cell(4, 2), Cell(4, 1)), Direction.LEFT),
            ArrowPiece(5, listOf(Cell(3, 1), Cell(3, 0)), Direction.LEFT),
            ArrowPiece(6, listOf(Cell(0, 7), Cell(0, 8)), Direction.RIGHT),
            ArrowPiece(7, listOf(Cell(2, 8), Cell(2, 9)), Direction.RIGHT),
            ArrowPiece(8, listOf(Cell(5, 6), Cell(6, 6), Cell(7, 6), Cell(8, 6)), Direction.DOWN),
            ArrowPiece(9, listOf(Cell(7, 5), Cell(8, 5)), Direction.DOWN),
            ArrowPiece(10, listOf(Cell(9, 8), Cell(9, 9)), Direction.RIGHT),
            ArrowPiece(11, listOf(Cell(3, 8), Cell(3, 9)), Direction.RIGHT),
            ArrowPiece(12, listOf(Cell(5, 5), Cell(6, 5)), Direction.DOWN),
            ArrowPiece(13, listOf(Cell(8, 3), Cell(8, 2), Cell(8, 1)), Direction.LEFT),
            ArrowPiece(14, listOf(Cell(1, 6), Cell(0, 6)), Direction.UP),
            ArrowPiece(15, listOf(Cell(0, 1), Cell(0, 0)), Direction.LEFT),
            ArrowPiece(16, listOf(Cell(7, 4), Cell(8, 4), Cell(9, 4)), Direction.DOWN),
            ArrowPiece(17, listOf(Cell(1, 1), Cell(1, 0)), Direction.LEFT),
            ArrowPiece(18, listOf(Cell(2, 1), Cell(2, 0)), Direction.LEFT),
            ArrowPiece(19, listOf(Cell(2, 3), Cell(2, 4), Cell(2, 5), Cell(2, 6)), Direction.RIGHT),
            ArrowPiece(20, listOf(Cell(4, 5), Cell(3, 5)), Direction.UP),
            ArrowPiece(21, listOf(Cell(6, 1), Cell(6, 0)), Direction.LEFT),
            ArrowPiece(22, listOf(Cell(3, 2), Cell(2, 2), Cell(1, 2), Cell(0, 2)), Direction.UP),
            ArrowPiece(23, listOf(Cell(9, 0), Cell(9, 1), Cell(9, 2), Cell(9, 3)), Direction.RIGHT),
            ArrowPiece(24, listOf(Cell(5, 4), Cell(5, 3), Cell(5, 2), Cell(5, 1)), Direction.LEFT),
            ArrowPiece(25, listOf(Cell(7, 3), Cell(7, 2), Cell(7, 1)), Direction.LEFT),
            ArrowPiece(26, listOf(Cell(8, 9), Cell(8, 8), Cell(8, 7)), Direction.LEFT),
            ArrowPiece(27, listOf(Cell(1, 7), Cell(1, 8), Cell(1, 9)), Direction.RIGHT),
            ArrowPiece(28, listOf(Cell(7, 8), Cell(6, 8), Cell(5, 8), Cell(4, 8)), Direction.UP)
        )
    )

    private fun hard7() = ArrowsLevel(
        rows = 10,
        cols = 10,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(6, 1), Cell(6, 0)), Direction.LEFT),
            ArrowPiece(2, listOf(Cell(1, 6), Cell(0, 6)), Direction.UP),
            ArrowPiece(3, listOf(Cell(1, 0), Cell(0, 0)), Direction.UP),
            ArrowPiece(4, listOf(Cell(2, 8), Cell(1, 8), Cell(0, 8)), Direction.UP),
            ArrowPiece(5, listOf(Cell(1, 5), Cell(0, 5)), Direction.UP),
            ArrowPiece(6, listOf(Cell(1, 2), Cell(1, 1)), Direction.LEFT),
            ArrowPiece(7, listOf(Cell(1, 7), Cell(0, 7)), Direction.UP),
            ArrowPiece(8, listOf(Cell(7, 1), Cell(7, 0)), Direction.LEFT),
            ArrowPiece(9, listOf(Cell(6, 8), Cell(6, 9)), Direction.RIGHT),
            ArrowPiece(10, listOf(Cell(8, 8), Cell(9, 8)), Direction.DOWN),
            ArrowPiece(11, listOf(Cell(7, 6), Cell(8, 6)), Direction.DOWN),
            ArrowPiece(12, listOf(Cell(3, 6), Cell(3, 7), Cell(3, 8)), Direction.RIGHT),
            ArrowPiece(13, listOf(Cell(8, 0), Cell(9, 0)), Direction.DOWN),
            ArrowPiece(14, listOf(Cell(1, 4), Cell(0, 4)), Direction.UP),
            ArrowPiece(15, listOf(Cell(0, 2), Cell(0, 1)), Direction.LEFT),
            ArrowPiece(16, listOf(Cell(4, 7), Cell(4, 8)), Direction.RIGHT),
            ArrowPiece(17, listOf(Cell(8, 2), Cell(8, 1)), Direction.LEFT),
            ArrowPiece(18, listOf(Cell(5, 8), Cell(5, 9)), Direction.RIGHT),
            ArrowPiece(19, listOf(Cell(2, 1), Cell(3, 1), Cell(4, 1), Cell(5, 1)), Direction.DOWN),
            ArrowPiece(20, listOf(Cell(2, 0), Cell(3, 0), Cell(4, 0)), Direction.DOWN),
            ArrowPiece(21, listOf(Cell(3, 2), Cell(4, 2), Cell(5, 2), Cell(6, 2)), Direction.DOWN),
            ArrowPiece(22, listOf(Cell(9, 5), Cell(9, 4), Cell(9, 3)), Direction.LEFT),
            ArrowPiece(23, listOf(Cell(7, 9), Cell(8, 9)), Direction.DOWN),
            ArrowPiece(24, listOf(Cell(6, 3), Cell(7, 3), Cell(8, 3)), Direction.DOWN),
            ArrowPiece(25, listOf(Cell(0, 9), Cell(1, 9)), Direction.DOWN),
            ArrowPiece(26, listOf(Cell(2, 5), Cell(2, 6), Cell(2, 7)), Direction.RIGHT),
            ArrowPiece(27, listOf(Cell(9, 7), Cell(8, 7)), Direction.UP),
            ArrowPiece(28, listOf(Cell(0, 3), Cell(1, 3), Cell(2, 3), Cell(3, 3)), Direction.DOWN)
        )
    )

    private fun hard8() = ArrowsLevel(
        rows = 10,
        cols = 10,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(8, 5), Cell(9, 5)), Direction.DOWN),
            ArrowPiece(2, listOf(Cell(3, 8), Cell(3, 9)), Direction.RIGHT),
            ArrowPiece(3, listOf(Cell(8, 7), Cell(8, 8)), Direction.RIGHT),
            ArrowPiece(4, listOf(Cell(2, 8), Cell(2, 9)), Direction.RIGHT),
            ArrowPiece(5, listOf(Cell(2, 0), Cell(1, 0)), Direction.UP),
            ArrowPiece(6, listOf(Cell(5, 8), Cell(5, 9)), Direction.RIGHT),
            ArrowPiece(7, listOf(Cell(2, 3), Cell(2, 2), Cell(2, 1)), Direction.LEFT),
            ArrowPiece(8, listOf(Cell(4, 2), Cell(4, 1)), Direction.LEFT),
            ArrowPiece(9, listOf(Cell(1, 8), Cell(1, 9)), Direction.RIGHT),
            ArrowPiece(10, listOf(Cell(9, 3), Cell(9, 2), Cell(9, 1), Cell(9, 0)), Direction.LEFT),
            ArrowPiece(11, listOf(Cell(2, 6), Cell(2, 5), Cell(2, 4)), Direction.LEFT),
            ArrowPiece(12, listOf(Cell(6, 7), Cell(6, 8), Cell(6, 9)), Direction.RIGHT),
            ArrowPiece(13, listOf(Cell(0, 4), Cell(0, 3), Cell(0, 2), Cell(0, 1)), Direction.LEFT),
            ArrowPiece(14, listOf(Cell(9, 7), Cell(9, 8)), Direction.RIGHT),
            ArrowPiece(15, listOf(Cell(7, 0), Cell(8, 0)), Direction.DOWN),
            ArrowPiece(16, listOf(Cell(4, 4), Cell(3, 4)), Direction.UP),
            ArrowPiece(17, listOf(Cell(5, 3), Cell(4, 3)), Direction.UP),
            ArrowPiece(18, listOf(Cell(5, 6), Cell(4, 6), Cell(3, 6)), Direction.UP),
            ArrowPiece(19, listOf(Cell(7, 7), Cell(7, 8), Cell(7, 9)), Direction.RIGHT),
            ArrowPiece(20, listOf(Cell(0, 7), Cell(1, 7), Cell(2, 7), Cell(3, 7)), Direction.DOWN),
            ArrowPiece(21, listOf(Cell(9, 6), Cell(8, 6)), Direction.UP),
            ArrowPiece(22, listOf(Cell(3, 1), Cell(3, 0)), Direction.LEFT),
            ArrowPiece(23, listOf(Cell(7, 2), Cell(7, 3), Cell(7, 4), Cell(7, 5)), Direction.RIGHT),
            ArrowPiece(24, listOf(Cell(8, 2), Cell(8, 1)), Direction.LEFT),
            ArrowPiece(25, listOf(Cell(6, 2), Cell(6, 3), Cell(6, 4)), Direction.RIGHT),
            ArrowPiece(26, listOf(Cell(1, 2), Cell(1, 1)), Direction.LEFT),
            ArrowPiece(27, listOf(Cell(4, 8), Cell(4, 9)), Direction.RIGHT),
            ArrowPiece(28, listOf(Cell(5, 1), Cell(6, 1), Cell(7, 1)), Direction.DOWN)
        )
    )

    private fun hard9() = ArrowsLevel(
        rows = 10,
        cols = 10,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(2, 3), Cell(1, 3)), Direction.UP),
            ArrowPiece(2, listOf(Cell(1, 4), Cell(0, 4)), Direction.UP),
            ArrowPiece(3, listOf(Cell(8, 4), Cell(9, 4)), Direction.DOWN),
            ArrowPiece(4, listOf(Cell(8, 6), Cell(9, 6)), Direction.DOWN),
            ArrowPiece(5, listOf(Cell(2, 5), Cell(1, 5)), Direction.UP),
            ArrowPiece(6, listOf(Cell(6, 9), Cell(7, 9), Cell(8, 9)), Direction.DOWN),
            ArrowPiece(7, listOf(Cell(8, 7), Cell(9, 7)), Direction.DOWN),
            ArrowPiece(8, listOf(Cell(4, 3), Cell(3, 3)), Direction.UP),
            ArrowPiece(9, listOf(Cell(8, 0), Cell(9, 0)), Direction.DOWN),
            ArrowPiece(10, listOf(Cell(0, 2), Cell(0, 1)), Direction.LEFT),
            ArrowPiece(11, listOf(Cell(6, 0), Cell(7, 0)), Direction.DOWN),
            ArrowPiece(12, listOf(Cell(3, 1), Cell(3, 0)), Direction.LEFT),
            ArrowPiece(13, listOf(Cell(9, 3), Cell(8, 3), Cell(7, 3), Cell(6, 3)), Direction.UP),
            ArrowPiece(14, listOf(Cell(6, 5), Cell(7, 5), Cell(8, 5)), Direction.DOWN),
            ArrowPiece(15, listOf(Cell(1, 6), Cell(1, 7), Cell(1, 8), Cell(1, 9)), Direction.RIGHT),
            ArrowPiece(16, listOf(Cell(5, 7), Cell(6, 7), Cell(7, 7)), Direction.DOWN),
            ArrowPiece(17, listOf(Cell(8, 1), Cell(9, 1)), Direction.DOWN),
            ArrowPiece(18, listOf(Cell(1, 1), Cell(1, 2)), Direction.RIGHT),
            ArrowPiece(19, listOf(Cell(4, 7), Cell(4, 8)), Direction.RIGHT),
            ArrowPiece(20, listOf(Cell(0, 9), Cell(0, 8), Cell(0, 7), Cell(0, 6)), Direction.LEFT),
            ArrowPiece(21, listOf(Cell(9, 8), Cell(8, 8), Cell(7, 8), Cell(6, 8)), Direction.UP),
            ArrowPiece(22, listOf(Cell(3, 6), Cell(3, 5), Cell(3, 4)), Direction.LEFT),
            ArrowPiece(23, listOf(Cell(4, 6), Cell(5, 6), Cell(6, 6), Cell(7, 6)), Direction.DOWN),
            ArrowPiece(24, listOf(Cell(6, 2), Cell(7, 2), Cell(8, 2)), Direction.DOWN),
            ArrowPiece(25, listOf(Cell(7, 4), Cell(6, 4), Cell(5, 4), Cell(4, 4)), Direction.UP),
            ArrowPiece(26, listOf(Cell(2, 0), Cell(1, 0)), Direction.UP),
            ArrowPiece(27, listOf(Cell(4, 0), Cell(5, 0)), Direction.DOWN),
            ArrowPiece(28, listOf(Cell(3, 9), Cell(2, 9)), Direction.UP)
        )
    )

    private fun hard10() = ArrowsLevel(
        rows = 10,
        cols = 10,
        lives = 3,
        arrows = listOf(
            ArrowPiece(1, listOf(Cell(1, 8), Cell(1, 9)), Direction.RIGHT),
            ArrowPiece(2, listOf(Cell(7, 8), Cell(7, 9)), Direction.RIGHT),
            ArrowPiece(3, listOf(Cell(5, 1), Cell(5, 0)), Direction.LEFT),
            ArrowPiece(4, listOf(Cell(8, 4), Cell(9, 4)), Direction.DOWN),
            ArrowPiece(5, listOf(Cell(4, 8), Cell(4, 9)), Direction.RIGHT),
            ArrowPiece(6, listOf(Cell(2, 8), Cell(2, 9)), Direction.RIGHT),
            ArrowPiece(7, listOf(Cell(8, 1), Cell(8, 0)), Direction.LEFT),
            ArrowPiece(8, listOf(Cell(2, 0), Cell(1, 0), Cell(0, 0)), Direction.UP),
            ArrowPiece(9, listOf(Cell(7, 4), Cell(7, 5), Cell(7, 6), Cell(7, 7)), Direction.RIGHT),
            ArrowPiece(10, listOf(Cell(2, 2), Cell(2, 1)), Direction.LEFT),
            ArrowPiece(11, listOf(Cell(5, 8), Cell(5, 9)), Direction.RIGHT),
            ArrowPiece(12, listOf(Cell(0, 7), Cell(0, 8), Cell(0, 9)), Direction.RIGHT),
            ArrowPiece(13, listOf(Cell(7, 3), Cell(7, 2), Cell(7, 1), Cell(7, 0)), Direction.LEFT),
            ArrowPiece(14, listOf(Cell(1, 4), Cell(1, 3), Cell(1, 2), Cell(1, 1)), Direction.LEFT),
            ArrowPiece(15, listOf(Cell(4, 4), Cell(4, 5), Cell(4, 6), Cell(4, 7)), Direction.RIGHT),
            ArrowPiece(16, listOf(Cell(8, 8), Cell(8, 7), Cell(8, 6), Cell(8, 5)), Direction.LEFT),
            ArrowPiece(17, listOf(Cell(6, 2), Cell(6, 1)), Direction.LEFT),
            ArrowPiece(18, listOf(Cell(6, 5), Cell(6, 4)), Direction.LEFT),
            ArrowPiece(19, listOf(Cell(9, 9), Cell(8, 9)), Direction.UP),
            ArrowPiece(20, listOf(Cell(5, 4), Cell(5, 3), Cell(5, 2)), Direction.LEFT),
            ArrowPiece(21, listOf(Cell(9, 0), Cell(9, 1), Cell(9, 2)), Direction.RIGHT),
            ArrowPiece(22, listOf(Cell(3, 3), Cell(3, 2), Cell(3, 1), Cell(3, 0)), Direction.LEFT),
            ArrowPiece(23, listOf(Cell(5, 5), Cell(5, 6), Cell(5, 7)), Direction.RIGHT),
            ArrowPiece(24, listOf(Cell(4, 0), Cell(4, 1), Cell(4, 2), Cell(4, 3)), Direction.RIGHT),
            ArrowPiece(25, listOf(Cell(3, 7), Cell(2, 7), Cell(1, 7)), Direction.UP),
            ArrowPiece(26, listOf(Cell(0, 3), Cell(0, 4), Cell(0, 5), Cell(0, 6)), Direction.RIGHT),
            ArrowPiece(27, listOf(Cell(2, 6), Cell(2, 5), Cell(2, 4)), Direction.LEFT),
            ArrowPiece(28, listOf(Cell(6, 8), Cell(6, 7)), Direction.LEFT)
        )
    )

    val hard: List<ArrowsLevel> = listOf(hard1(), hard2(), hard3(), hard4(), hard5(), hard6(), hard7(), hard8(), hard9(), hard10())
}
