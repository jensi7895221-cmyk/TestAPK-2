# Mini Games Hub

Eine native Android App (Kotlin, Jetpack Compose) mit einem Hauptmenue,
von dem aus einzelne Mini Spiele gestartet werden. Aktuell enthalten:

1. **Pfeile** (`games/arrows`): Tippe Pfeile an, um sie vom Feld zu schiessen.
   Manche Pfeile blockieren andere. Bei falscher Reihenfolge geht ein Leben verloren.
2. **Wasser sortieren** (`games/watersort`): Giesse Wasser zwischen Roehrchen um,
   bis jedes Roehrchen nur eine Farbe enthaelt.

## Installierbare APK bekommen, ohne selbst etwas zu installieren

Im Ordner `.github/workflows/build-apk.yml` liegt eine fertige Bauanleitung
fuer GitHub. Damit baut GitHub selbst, kostenlos, die APK fuer dich. Du
brauchst dafuer nichts auf deinem Rechner zu installieren.

1. Auf github.com ein kostenloses Konto anlegen, falls noch keins vorhanden ist.
2. Ein neues, leeres Repository anlegen (Name frei waehlbar, zum Beispiel
   MiniGamesHub).
3. Den Inhalt dieses Ordners in das Repository hochladen. Am einfachsten geht
   das ueber den Browser: im neuen Repository auf "uploading an existing
   file" klicken und alle Dateien und Ordner aus diesem ZIP hineinziehen.
4. Im Repository oben auf den Reiter "Actions" wechseln. Der Workflow
   "APK bauen" startet automatisch, sobald die Dateien hochgeladen sind.
5. Nach ein bis zwei Minuten ist der Lauf fertig, erkennbar am gruenen
   Haekchen. Auf den Lauf klicken, ganz unten erscheint unter "Artifacts"
   die Datei "MiniGamesHub-debug-apk" zum Download.
6. Die heruntergeladene Datei ist eine ZIP mit der fertigen app-debug.apk
   darin. Diese Datei aufs Handy kopieren und dort oeffnen, um sie zu
   installieren. Android fragt dabei eventuell nach der Erlaubnis, Apps aus
   unbekannten Quellen zu installieren, das ist normal fuer selbst gebaute
   Apps ausserhalb des Play Stores.

## Alternative: lokal mit Android Studio bauen

Falls Android Studio bereits installiert ist, geht es auch ohne GitHub:

1. [Android Studio](https://developer.android.com/studio) installieren (falls
   noch nicht vorhanden).
2. Diesen Ordner (`MiniGamesHub`) mit **File > Open** in Android Studio oeffnen.
3. Android Studio synchronisiert Gradle automatisch und erzeugt dabei auch den
   Gradle Wrapper, falls er fehlt.
4. Oben rechts auf **Run** (gruener Pfeil) tippen, um die App auf einem
   Emulator oder einem per USB verbundenen Handy zu starten.
5. Fuer eine installierbare Datei: **Build > Build App Bundle(s) / APK(s) >
   Build APK(s)**. Die fertige Datei liegt danach unter
   `app/build/outputs/apk/debug/app-debug.apk`.

Fuer eine Version zum Veroeffentlichen im Play Store braucht es zusaetzlich
einen Signierschluessel (**Build > Generate Signed Bundle / APK**).

## Architektur, gedacht fuer viele weitere Spiele

Das Projekt ist bewusst so aufgebaut, dass neue Mini Spiele mit minimalem
Aufwand ergaenzt werden koennen, ohne bestehenden Code anzufassen:

```
games/
  GameModule.kt      Schnittstelle, die jedes Spiel implementiert
  GameCatalog.kt      einzige Stelle, an der neue Spiele eingetragen werden
  arrows/              Spiel 1, in sich abgeschlossen
  watersort/            Spiel 2, in sich abgeschlossen
```

Ein neues Spiel hinzuzufuegen bedeutet:

1. Neuen Ordner `games/meinspiel/` anlegen.
2. Eine Klasse erstellen, die `GameModule` implementiert (siehe
   `ArrowsGameModule` oder `WaterSortGameModule` als Vorlage). Sie braucht nur
   `info` (Titel, Beschreibung, Farbe) und eine `Content()` Composable-Funktion.
3. Den eigenen Zustand ueblicherweise in einer kleinen State-Klasse mit
   `mutableStateOf` halten (siehe `ArrowsGameState`, `WaterSortGameState`).
4. In `GameCatalog.kt` eine Zeile ergaenzen: `MeinSpielModule(),`.

Danach erscheint das Spiel automatisch als Kachel im Hauptmenue, die
Navigation und die Titelleiste funktionieren ohne weitere Anpassungen.

## Level ergaenzen

Levels liegen jeweils als einfache Datenlisten vor
(`ArrowsLevels.kt`, `WaterSortLevels.kt`). Ein neues Level ist nur ein
weiterer Eintrag in der jeweiligen Liste, keine Aenderung an der Spiellogik
noetig. Das macht die App wie gewuenscht leicht updatebar.

## Zukuenftige Erweiterungen, die sich anbieten

- Speicherstand (Fortschritt, Sterne pro Level) mit DataStore persistieren.
- Level aus einer JSON Datei oder einem Server laden, statt sie im Code zu
  hinterlegen, fuer Updates ohne neue APK.
- Ein echter Levelgenerator fuer Wasser sortieren, der automatisch
  garantiert loesbare Level erzeugt.
- Toene und kleine Animationen beim Loesen eines Levels.
