package com.minigames.hub.games.watersort

/**
 * Neue Level koennen hier als weiterer Eintrag ergaenzt werden.
 * Faustregel fuer loesbare Level: es gibt mindestens ein bis zwei leere
 * Roehrchen als Platz zum Umschuetten, und mindestens so viele Roehrchen
 * insgesamt wie es unterschiedliche Farben gibt.
 */
object WaterSortLevels {

    private fun level1() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Red, WaterColors.Green, WaterColors.Yellow, WaterColors.Red),
            listOf(WaterColors.Yellow, WaterColors.Red, WaterColors.Green, WaterColors.Yellow),
            listOf(WaterColors.Green, WaterColors.Yellow, WaterColors.Red, WaterColors.Green),
            emptyList(),
            emptyList()
        )
    )

    private fun level2() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Red, WaterColors.Blue, WaterColors.Green, WaterColors.Yellow),
            listOf(WaterColors.Yellow, WaterColors.Green, WaterColors.Blue, WaterColors.Red),
            listOf(WaterColors.Blue, WaterColors.Red, WaterColors.Yellow, WaterColors.Green),
            listOf(WaterColors.Green, WaterColors.Yellow, WaterColors.Red, WaterColors.Blue),
            emptyList(),
            emptyList()
        )
    )

    private fun level3() = WaterSortLevel(
        tubeCapacity = 4,
        tubes = listOf(
            listOf(WaterColors.Red, WaterColors.Purple, WaterColors.Orange, WaterColors.Blue),
            listOf(WaterColors.Blue, WaterColors.Green, WaterColors.Purple, WaterColors.Red),
            listOf(WaterColors.Green, WaterColors.Orange, WaterColors.Blue, WaterColors.Yellow),
            listOf(WaterColors.Yellow, WaterColors.Red, WaterColors.Green, WaterColors.Purple),
            listOf(WaterColors.Purple, WaterColors.Yellow, WaterColors.Orange, WaterColors.Green),
            emptyList(),
            emptyList()
        )
    )

    val all: List<WaterSortLevel> = listOf(level1(), level2(), level3())
}
