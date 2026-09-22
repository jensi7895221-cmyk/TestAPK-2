package com.example.socialfeed

import android.app.Application

/**
 * Da es nur einen echten Nutzer gibt, reicht als Authentifizierung ein
 * einfacher lokaler Zustand mit der User ID nach der einmaligen Registrierung.
 * TODO: currentUserId dauerhaft speichern, zum Beispiel über DataStore, statt
 * nur im Arbeitsspeicher zu halten, damit der Nutzer nicht bei jedem App Start
 * neu angelegt werden muss.
 */
class SocialFeedApp : Application() {
    companion object {
        var currentUserId: String? = null
    }
}
