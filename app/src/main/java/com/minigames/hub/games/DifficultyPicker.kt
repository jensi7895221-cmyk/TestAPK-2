package com.minigames.hub.games

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

/**
 * Auswahlbildschirm fuer die Schwierigkeit eines Spiels: Beschreibung, ein animiertes
 * Emoji das sich mit der Stufe veraendert, ein dreistufiger Schieberegler und ein
 * Start-Button, der das zuletzt erreichte Level der gewaehlten Stufe anzeigt.
 */
@Composable
fun DifficultyPicker(
    description: String,
    resumeLevel: (Difficulty) -> Int,
    onStart: (Difficulty) -> Unit
) {
    var selected by remember { mutableStateOf(Difficulty.EASY) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, bottom = 40.dp)
        )

        AnimatedContent(
            targetState = selected,
            transitionSpec = {
                (scaleIn(animationSpec = tween(250), initialScale = 0.5f) + fadeIn(tween(250))) togetherWith
                    (scaleOut(animationSpec = tween(200), targetScale = 0.5f) + fadeOut(tween(200)))
            },
            label = "difficulty-emoji"
        ) { difficulty ->
            Box(
                modifier = Modifier
                    .size(128.dp)
                    .background(difficulty.color.copy(alpha = 0.18f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = difficulty.emoji, fontSize = 68.sp)
            }
        }

        Text(
            text = selected.label,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color = selected.color,
            modifier = Modifier.padding(top = 20.dp, bottom = 24.dp)
        )

        Slider(
            value = selected.ordinal.toFloat(),
            onValueChange = { value ->
                val index = value.roundToInt().coerceIn(0, Difficulty.entries.lastIndex)
                selected = Difficulty.entries[index]
            },
            valueRange = 0f..(Difficulty.entries.size - 1).toFloat(),
            steps = Difficulty.entries.size - 2,
            colors = SliderDefaults.colors(
                thumbColor = selected.color,
                activeTrackColor = selected.color
            ),
            modifier = Modifier.fillMaxWidth(0.85f)
        )

        Button(
            onClick = { onStart(selected) },
            colors = ButtonDefaults.buttonColors(containerColor = selected.color, contentColor = Color.White),
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(0.65f)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("SPIELEN", fontWeight = FontWeight.ExtraBold)
                Text(
                    text = "Level ${resumeLevel(selected)}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
