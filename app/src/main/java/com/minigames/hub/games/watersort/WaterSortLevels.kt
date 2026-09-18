package com.minigames.hub.games.watersort

import com.minigames.hub.games.Difficulty

/**
 * Level sind nach Schwierigkeit gruppiert: jeweils 10 Level pro Stufe.
 * Jedes Level wurde per Zufallsverteilung erzeugt und anschliessend mit einer
 * Tiefensuche geprueft, sodass garantiert eine Loesung existiert.
 */
object WaterSortLevels {

    fun forDifficulty(difficulty: Difficulty): List<WaterSortLevel> = when (difficulty) {
        Difficulty.EASY -> easy
        Difficulty.MEDIUM -> medium
        Difficulty.HARD -> hard
    }

    // EASY
    private fun easy1() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Yellow, WaterColors.Red, WaterColors.Green, WaterColors.Yellow),
            listOf(WaterColors.Green, WaterColors.Red, WaterColors.Red, WaterColors.Yellow),
            listOf(WaterColors.Green, WaterColors.Red, WaterColors.Green, WaterColors.Yellow),
            emptyList(),
            emptyList()
        )
    )

    private fun easy2() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Green, WaterColors.Yellow, WaterColors.Yellow, WaterColors.Red),
            listOf(WaterColors.Green, WaterColors.Green, WaterColors.Yellow, WaterColors.Yellow),
            listOf(WaterColors.Green, WaterColors.Red, WaterColors.Red, WaterColors.Red),
            emptyList(),
            emptyList()
        )
    )

    private fun easy3() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Yellow, WaterColors.Red, WaterColors.Yellow, WaterColors.Green),
            listOf(WaterColors.Green, WaterColors.Yellow, WaterColors.Red, WaterColors.Red),
            listOf(WaterColors.Red, WaterColors.Yellow, WaterColors.Green, WaterColors.Green),
            emptyList(),
            emptyList()
        )
    )

    private fun easy4() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Red, WaterColors.Red, WaterColors.Red, WaterColors.Green),
            listOf(WaterColors.Yellow, WaterColors.Yellow, WaterColors.Green, WaterColors.Green),
            listOf(WaterColors.Red, WaterColors.Yellow, WaterColors.Green, WaterColors.Yellow),
            emptyList(),
            emptyList()
        )
    )

    private fun easy5() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Yellow, WaterColors.Yellow, WaterColors.Yellow, WaterColors.Red),
            listOf(WaterColors.Red, WaterColors.Green, WaterColors.Green, WaterColors.Red),
            listOf(WaterColors.Green, WaterColors.Green, WaterColors.Red, WaterColors.Yellow),
            emptyList(),
            emptyList()
        )
    )

    private fun easy6() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Green, WaterColors.Yellow, WaterColors.Red, WaterColors.Red),
            listOf(WaterColors.Red, WaterColors.Green, WaterColors.Red, WaterColors.Yellow),
            listOf(WaterColors.Green, WaterColors.Green, WaterColors.Yellow, WaterColors.Yellow),
            emptyList(),
            emptyList()
        )
    )

    private fun easy7() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Green, WaterColors.Yellow, WaterColors.Green, WaterColors.Green),
            listOf(WaterColors.Red, WaterColors.Yellow, WaterColors.Red, WaterColors.Green),
            listOf(WaterColors.Yellow, WaterColors.Red, WaterColors.Red, WaterColors.Yellow),
            emptyList(),
            emptyList()
        )
    )

    private fun easy8() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Red, WaterColors.Yellow, WaterColors.Yellow, WaterColors.Yellow),
            listOf(WaterColors.Green, WaterColors.Green, WaterColors.Red, WaterColors.Green),
            listOf(WaterColors.Yellow, WaterColors.Red, WaterColors.Green, WaterColors.Red),
            emptyList(),
            emptyList()
        )
    )

    private fun easy9() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Red, WaterColors.Yellow, WaterColors.Red, WaterColors.Yellow),
            listOf(WaterColors.Green, WaterColors.Green, WaterColors.Red, WaterColors.Green),
            listOf(WaterColors.Red, WaterColors.Yellow, WaterColors.Yellow, WaterColors.Green),
            emptyList(),
            emptyList()
        )
    )

    private fun easy10() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Green, WaterColors.Red, WaterColors.Green, WaterColors.Yellow),
            listOf(WaterColors.Yellow, WaterColors.Green, WaterColors.Yellow, WaterColors.Green),
            listOf(WaterColors.Red, WaterColors.Yellow, WaterColors.Red, WaterColors.Red),
            emptyList(),
            emptyList()
        )
    )
    val easy: List<WaterSortLevel> = listOf(easy1(), easy2(), easy3(), easy4(), easy5(), easy6(), easy7(), easy8(), easy9(), easy10())

    // MEDIUM
    private fun medium1() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Purple, WaterColors.Green, WaterColors.Yellow, WaterColors.Red),
            listOf(WaterColors.Blue, WaterColors.Yellow, WaterColors.Purple, WaterColors.Purple),
            listOf(WaterColors.Purple, WaterColors.Blue, WaterColors.Red, WaterColors.Yellow),
            listOf(WaterColors.Red, WaterColors.Green, WaterColors.Green, WaterColors.Yellow),
            listOf(WaterColors.Green, WaterColors.Blue, WaterColors.Red, WaterColors.Blue),
            emptyList(),
            emptyList()
        )
    )

    private fun medium2() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Yellow, WaterColors.Yellow, WaterColors.Yellow, WaterColors.Yellow),
            listOf(WaterColors.Purple, WaterColors.Red, WaterColors.Green, WaterColors.Blue),
            listOf(WaterColors.Blue, WaterColors.Purple, WaterColors.Purple, WaterColors.Blue),
            listOf(WaterColors.Blue, WaterColors.Green, WaterColors.Green, WaterColors.Red),
            listOf(WaterColors.Red, WaterColors.Green, WaterColors.Red, WaterColors.Purple),
            emptyList(),
            emptyList()
        )
    )

    private fun medium3() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Purple, WaterColors.Yellow, WaterColors.Purple, WaterColors.Yellow),
            listOf(WaterColors.Red, WaterColors.Yellow, WaterColors.Green, WaterColors.Blue),
            listOf(WaterColors.Blue, WaterColors.Blue, WaterColors.Red, WaterColors.Purple),
            listOf(WaterColors.Green, WaterColors.Yellow, WaterColors.Blue, WaterColors.Green),
            listOf(WaterColors.Red, WaterColors.Green, WaterColors.Purple, WaterColors.Red),
            emptyList(),
            emptyList()
        )
    )

    private fun medium4() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Blue, WaterColors.Purple, WaterColors.Green, WaterColors.Blue),
            listOf(WaterColors.Purple, WaterColors.Blue, WaterColors.Red, WaterColors.Yellow),
            listOf(WaterColors.Yellow, WaterColors.Red, WaterColors.Red, WaterColors.Green),
            listOf(WaterColors.Blue, WaterColors.Yellow, WaterColors.Green, WaterColors.Purple),
            listOf(WaterColors.Green, WaterColors.Yellow, WaterColors.Purple, WaterColors.Red),
            emptyList(),
            emptyList()
        )
    )

    private fun medium5() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Yellow, WaterColors.Purple, WaterColors.Purple, WaterColors.Blue),
            listOf(WaterColors.Purple, WaterColors.Yellow, WaterColors.Red, WaterColors.Blue),
            listOf(WaterColors.Yellow, WaterColors.Red, WaterColors.Blue, WaterColors.Green),
            listOf(WaterColors.Green, WaterColors.Blue, WaterColors.Green, WaterColors.Red),
            listOf(WaterColors.Green, WaterColors.Yellow, WaterColors.Red, WaterColors.Purple),
            emptyList(),
            emptyList()
        )
    )

    private fun medium6() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Yellow, WaterColors.Green, WaterColors.Blue, WaterColors.Green),
            listOf(WaterColors.Purple, WaterColors.Purple, WaterColors.Green, WaterColors.Blue),
            listOf(WaterColors.Yellow, WaterColors.Purple, WaterColors.Red, WaterColors.Red),
            listOf(WaterColors.Purple, WaterColors.Green, WaterColors.Yellow, WaterColors.Yellow),
            listOf(WaterColors.Blue, WaterColors.Red, WaterColors.Red, WaterColors.Blue),
            emptyList(),
            emptyList()
        )
    )

    private fun medium7() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Yellow, WaterColors.Yellow, WaterColors.Red, WaterColors.Blue),
            listOf(WaterColors.Yellow, WaterColors.Green, WaterColors.Purple, WaterColors.Yellow),
            listOf(WaterColors.Green, WaterColors.Red, WaterColors.Purple, WaterColors.Purple),
            listOf(WaterColors.Blue, WaterColors.Green, WaterColors.Blue, WaterColors.Green),
            listOf(WaterColors.Red, WaterColors.Red, WaterColors.Blue, WaterColors.Purple),
            emptyList(),
            emptyList()
        )
    )

    private fun medium8() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Green, WaterColors.Red, WaterColors.Green, WaterColors.Blue),
            listOf(WaterColors.Yellow, WaterColors.Blue, WaterColors.Blue, WaterColors.Purple),
            listOf(WaterColors.Yellow, WaterColors.Red, WaterColors.Red, WaterColors.Purple),
            listOf(WaterColors.Purple, WaterColors.Purple, WaterColors.Green, WaterColors.Yellow),
            listOf(WaterColors.Green, WaterColors.Blue, WaterColors.Red, WaterColors.Yellow),
            emptyList(),
            emptyList()
        )
    )

    private fun medium9() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Green, WaterColors.Red, WaterColors.Purple, WaterColors.Green),
            listOf(WaterColors.Purple, WaterColors.Blue, WaterColors.Blue, WaterColors.Yellow),
            listOf(WaterColors.Green, WaterColors.Red, WaterColors.Yellow, WaterColors.Blue),
            listOf(WaterColors.Yellow, WaterColors.Green, WaterColors.Purple, WaterColors.Red),
            listOf(WaterColors.Red, WaterColors.Blue, WaterColors.Purple, WaterColors.Yellow),
            emptyList(),
            emptyList()
        )
    )

    private fun medium10() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Yellow, WaterColors.Yellow, WaterColors.Green, WaterColors.Red),
            listOf(WaterColors.Blue, WaterColors.Blue, WaterColors.Purple, WaterColors.Purple),
            listOf(WaterColors.Red, WaterColors.Blue, WaterColors.Purple, WaterColors.Green),
            listOf(WaterColors.Yellow, WaterColors.Purple, WaterColors.Red, WaterColors.Green),
            listOf(WaterColors.Red, WaterColors.Blue, WaterColors.Yellow, WaterColors.Green),
            emptyList(),
            emptyList()
        )
    )
    val medium: List<WaterSortLevel> = listOf(medium1(), medium2(), medium3(), medium4(), medium5(), medium6(), medium7(), medium8(), medium9(), medium10())

    // HARD
    private fun hard1() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Purple, WaterColors.Red, WaterColors.Red, WaterColors.Purple),
            listOf(WaterColors.Purple, WaterColors.Teal, WaterColors.Blue, WaterColors.Yellow),
            listOf(WaterColors.Blue, WaterColors.Purple, WaterColors.Blue, WaterColors.Green),
            listOf(WaterColors.Red, WaterColors.Green, WaterColors.Orange, WaterColors.Yellow),
            listOf(WaterColors.Yellow, WaterColors.Orange, WaterColors.Blue, WaterColors.Teal),
            listOf(WaterColors.Teal, WaterColors.Teal, WaterColors.Orange, WaterColors.Green),
            listOf(WaterColors.Red, WaterColors.Green, WaterColors.Yellow, WaterColors.Orange),
            emptyList(),
            emptyList()
        )
    )

    private fun hard2() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Teal, WaterColors.Yellow, WaterColors.Teal, WaterColors.Red),
            listOf(WaterColors.Purple, WaterColors.Yellow, WaterColors.Teal, WaterColors.Purple),
            listOf(WaterColors.Green, WaterColors.Red, WaterColors.Purple, WaterColors.Purple),
            listOf(WaterColors.Red, WaterColors.Blue, WaterColors.Red, WaterColors.Orange),
            listOf(WaterColors.Blue, WaterColors.Orange, WaterColors.Blue, WaterColors.Green),
            listOf(WaterColors.Orange, WaterColors.Teal, WaterColors.Yellow, WaterColors.Green),
            listOf(WaterColors.Yellow, WaterColors.Blue, WaterColors.Orange, WaterColors.Green),
            emptyList(),
            emptyList()
        )
    )

    private fun hard3() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Red, WaterColors.Green, WaterColors.Yellow, WaterColors.Purple),
            listOf(WaterColors.Blue, WaterColors.Yellow, WaterColors.Red, WaterColors.Blue),
            listOf(WaterColors.Green, WaterColors.Teal, WaterColors.Teal, WaterColors.Purple),
            listOf(WaterColors.Orange, WaterColors.Green, WaterColors.Purple, WaterColors.Green),
            listOf(WaterColors.Yellow, WaterColors.Teal, WaterColors.Orange, WaterColors.Blue),
            listOf(WaterColors.Purple, WaterColors.Teal, WaterColors.Red, WaterColors.Red),
            listOf(WaterColors.Blue, WaterColors.Orange, WaterColors.Orange, WaterColors.Yellow),
            emptyList(),
            emptyList()
        )
    )

    private fun hard4() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Green, WaterColors.Yellow, WaterColors.Yellow, WaterColors.Teal),
            listOf(WaterColors.Blue, WaterColors.Yellow, WaterColors.Teal, WaterColors.Blue),
            listOf(WaterColors.Teal, WaterColors.Blue, WaterColors.Red, WaterColors.Red),
            listOf(WaterColors.Orange, WaterColors.Green, WaterColors.Red, WaterColors.Purple),
            listOf(WaterColors.Purple, WaterColors.Teal, WaterColors.Green, WaterColors.Orange),
            listOf(WaterColors.Red, WaterColors.Purple, WaterColors.Green, WaterColors.Orange),
            listOf(WaterColors.Yellow, WaterColors.Orange, WaterColors.Blue, WaterColors.Purple),
            emptyList(),
            emptyList()
        )
    )

    private fun hard5() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Red, WaterColors.Green, WaterColors.Teal, WaterColors.Blue),
            listOf(WaterColors.Orange, WaterColors.Blue, WaterColors.Green, WaterColors.Teal),
            listOf(WaterColors.Yellow, WaterColors.Purple, WaterColors.Green, WaterColors.Purple),
            listOf(WaterColors.Yellow, WaterColors.Teal, WaterColors.Blue, WaterColors.Yellow),
            listOf(WaterColors.Purple, WaterColors.Green, WaterColors.Purple, WaterColors.Red),
            listOf(WaterColors.Orange, WaterColors.Red, WaterColors.Orange, WaterColors.Red),
            listOf(WaterColors.Orange, WaterColors.Yellow, WaterColors.Teal, WaterColors.Blue),
            emptyList(),
            emptyList()
        )
    )

    private fun hard6() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Orange, WaterColors.Teal, WaterColors.Teal, WaterColors.Yellow),
            listOf(WaterColors.Orange, WaterColors.Purple, WaterColors.Yellow, WaterColors.Purple),
            listOf(WaterColors.Green, WaterColors.Teal, WaterColors.Green, WaterColors.Red),
            listOf(WaterColors.Yellow, WaterColors.Purple, WaterColors.Blue, WaterColors.Teal),
            listOf(WaterColors.Blue, WaterColors.Green, WaterColors.Purple, WaterColors.Red),
            listOf(WaterColors.Orange, WaterColors.Red, WaterColors.Yellow, WaterColors.Blue),
            listOf(WaterColors.Green, WaterColors.Blue, WaterColors.Red, WaterColors.Orange),
            emptyList(),
            emptyList()
        )
    )

    private fun hard7() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Green, WaterColors.Blue, WaterColors.Teal, WaterColors.Purple),
            listOf(WaterColors.Red, WaterColors.Yellow, WaterColors.Orange, WaterColors.Red),
            listOf(WaterColors.Blue, WaterColors.Purple, WaterColors.Orange, WaterColors.Yellow),
            listOf(WaterColors.Yellow, WaterColors.Red, WaterColors.Blue, WaterColors.Blue),
            listOf(WaterColors.Green, WaterColors.Orange, WaterColors.Yellow, WaterColors.Green),
            listOf(WaterColors.Teal, WaterColors.Green, WaterColors.Purple, WaterColors.Red),
            listOf(WaterColors.Purple, WaterColors.Teal, WaterColors.Teal, WaterColors.Orange),
            emptyList(),
            emptyList()
        )
    )

    private fun hard8() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Yellow, WaterColors.Green, WaterColors.Purple, WaterColors.Orange),
            listOf(WaterColors.Teal, WaterColors.Green, WaterColors.Teal, WaterColors.Purple),
            listOf(WaterColors.Orange, WaterColors.Red, WaterColors.Red, WaterColors.Red),
            listOf(WaterColors.Yellow, WaterColors.Yellow, WaterColors.Teal, WaterColors.Orange),
            listOf(WaterColors.Green, WaterColors.Purple, WaterColors.Blue, WaterColors.Teal),
            listOf(WaterColors.Red, WaterColors.Yellow, WaterColors.Blue, WaterColors.Blue),
            listOf(WaterColors.Purple, WaterColors.Blue, WaterColors.Green, WaterColors.Orange),
            emptyList(),
            emptyList()
        )
    )

    private fun hard9() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Red, WaterColors.Blue, WaterColors.Orange, WaterColors.Teal),
            listOf(WaterColors.Teal, WaterColors.Teal, WaterColors.Purple, WaterColors.Blue),
            listOf(WaterColors.Purple, WaterColors.Red, WaterColors.Yellow, WaterColors.Yellow),
            listOf(WaterColors.Teal, WaterColors.Purple, WaterColors.Green, WaterColors.Yellow),
            listOf(WaterColors.Green, WaterColors.Orange, WaterColors.Orange, WaterColors.Green),
            listOf(WaterColors.Purple, WaterColors.Orange, WaterColors.Red, WaterColors.Blue),
            listOf(WaterColors.Yellow, WaterColors.Red, WaterColors.Blue, WaterColors.Green),
            emptyList(),
            emptyList()
        )
    )

    private fun hard10() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Red, WaterColors.Purple, WaterColors.Green, WaterColors.Purple),
            listOf(WaterColors.Teal, WaterColors.Teal, WaterColors.Yellow, WaterColors.Yellow),
            listOf(WaterColors.Blue, WaterColors.Orange, WaterColors.Blue, WaterColors.Yellow),
            listOf(WaterColors.Purple, WaterColors.Teal, WaterColors.Red, WaterColors.Orange),
            listOf(WaterColors.Yellow, WaterColors.Green, WaterColors.Red, WaterColors.Teal),
            listOf(WaterColors.Green, WaterColors.Blue, WaterColors.Red, WaterColors.Orange),
            listOf(WaterColors.Orange, WaterColors.Green, WaterColors.Purple, WaterColors.Blue),
            emptyList(),
            emptyList()
        )
    )
    val hard: List<WaterSortLevel> = listOf(hard1(), hard2(), hard3(), hard4(), hard5(), hard6(), hard7(), hard8(), hard9(), hard10())
}
