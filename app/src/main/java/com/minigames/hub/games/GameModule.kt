package com.minigames.hub.games

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Beschreibt ein Spiel fuer die Kachel im Hauptmenue.
 *
 * id           eindeutiger Schluessel, wird fuer Navigation und Speicherstand verwendet
 * title        Anzeigename in der Kachel und in der Titelleiste
 * description  kurzer Untertitel in der Kachel
 * accentColor  Farbe der Kachel, sollte sich von anderen Spielen unterscheiden
 */
data class GameInfo(
    val id: String,
    val title: String,
    val description: String,
    val accentColor: Color
)

/**
 * Jedes Mini Spiel implementiert dieses Interface.
 *
 * Ein neues Spiel hinzuzufuegen bedeutet:
 *   1. Einen neuen Ordner unter games/ anlegen
 *   2. Eine Klasse erstellen, die GameModule implementiert
 *   3. Die Klasse in GameCatalog.kt in die Liste eintragen
 *
 * Das Hauptmenue, die Navigation und die Titelleiste passen sich automatisch an,
 * ohne dass an anderer Stelle im Projekt etwas geaendert werden muss.
 */
interface GameModule {
    val info: GameInfo

    @Composable
    fun Content()
}
