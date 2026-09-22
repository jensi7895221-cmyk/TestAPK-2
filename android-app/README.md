# Social Feed KI, Android App

Grundgerüst der Android App in Kotlin mit Jetpack Compose. Deckt alle im
Konzept genannten Kernfunktionen als lauffähige Struktur ab: Feed, Profile,
Kommentare mit einfacher Thread Ansicht, Likes mit Animation, Direktnachrichten,
Stories und das Posten eigener Fotos.

## Setup

1. Projekt in Android Studio öffnen (Ordner `android-app`).
2. Backend lokal starten (siehe `backend/README.md`).
3. `RetrofitClient.kt` prüfen: Die Standard URL `http://10.0.2.2:8000/` zeigt
   im Android Emulator automatisch auf den localhost der Entwicklungsmaschine.
   Für ein echtes Gerät im selben Netzwerk die IP Adresse der Maschine eintragen.
4. App bauen und starten.

## Struktur

```
app/src/main/java/com/example/socialfeed/
  MainActivity.kt              Einstiegspunkt
  SocialFeedApp.kt              Application Klasse, hält currentUserId
  navigation/NavGraph.kt        Alle Routen: Feed, Upload, Profil, Chats, Chat Detail, Stories
  data/model/                   Datenmodelle, passend zum Backend Schema
  data/remote/                  Retrofit Service und Client
  data/repository/               Feed, Chat und User Repository
  ui/feed/                       Feed Screen und ViewModel
  ui/profile/                    Profil Screen und ViewModel
  ui/chat/                       Chat Liste, Chat Detail und ViewModel
  ui/story/                      Story Viewer
  ui/upload/                     Upload Screen für eigene Fotos
  ui/components/                 PostCard, CommentSheet, LikeButton
  ui/theme/                      Compose Theme
```

## Aktueller Stand (Grundgerüst)

Fertig:
- Vollständige Navigation zwischen allen Kernbildschirmen
- Feed mit Likes (animiert), Kommentaren als Bottom Sheet mit einfacher Thread
  Darstellung, Autor Kopfzeile
- Profilseite mit Follow Button und Einstieg in den Chat
- Chat Liste (alle gefolgten Personas) und Chat Detail mit Nachrichtenverlauf
- Story Viewer mit automatischem Ablauf und Fortschrittsbalken
- Upload Screen zum Posten eigener Fotos

Noch als Platzhalter markiert (siehe `TODO` Kommentare im Code):
- Registrierung des echten Nutzers beim ersten App Start (aktuell feste
  Platzhalter userId in `MainActivity.kt`)
- Echte Bildauswahl aus Galerie oder Kamera im Upload Screen, aktuell
  Texteingabe einer Bild URL
- Offline Zwischenspeicherung des Feeds über Room, laut Konzept gefordert
- Push Benachrichtigungen über Firebase Cloud Messaging
- Unlike mit korrekter like_id, aktuell nur lokaler UI Zustand

## Nächste sinnvolle Schritte

1. Einfachen Registrierungs Screen ergänzen, der einmalig `POST /users`
   aufruft und die userId dauerhaft speichert (zum Beispiel über DataStore).
2. Bildauswahl und Upload zu einem Objektspeicher einbauen.
3. Room Datenbank für Offline Zwischenspeicherung des Feeds ergänzen.
4. Firebase Cloud Messaging für Push Benachrichtigungen anbinden, passend zum
   Platzhalter in `backend/app/services/notification_service.py`.
