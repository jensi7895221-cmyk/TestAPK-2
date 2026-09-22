package com.example.socialfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.socialfeed.navigation.SocialFeedNavGraph
import com.example.socialfeed.ui.theme.SocialFeedKITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialFeedKITheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SocialFeedRoot()
                }
            }
        }
    }
}

/**
 * TODO: Hier vor dem eigentlichen Start prüfen, ob bereits ein Nutzer
 * registriert ist (siehe SocialFeedApp.currentUserId Hinweis) und andernfalls
 * einen einfachen Registrierungs Screen zeigen. Für das Grundgerüst wird direkt
 * mit einer Platzhalter userId gestartet, damit alle Screens erreichbar sind.
 */
@Composable
private fun SocialFeedRoot() {
    val navController = rememberNavController()
    val currentUserId = SocialFeedApp.currentUserId ?: "PLATZHALTER_USER_ID"
    SocialFeedNavGraph(navController = navController, currentUserId = currentUserId)
}
