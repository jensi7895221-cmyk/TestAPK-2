package com.minigames.hub.games.watersort

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.minigames.hub.games.GameInfo
import com.minigames.hub.games.GameModule

class WaterSortGameModule : GameModule {
    override val info = GameInfo(
        id = "watersort",
        title = "Wasser sortieren",
        description = "Sortiere jede Farbe in ihr eigenes Glas",
        accentColor = Color(0xFF6A4C93)
    )

    @Composable
    override fun Content() {
        WaterSortScreen()
    }
}

private val TubeBorder = Color(0x66FFFFFF)
private val TubeBorderSelected = Color(0xFFFFC107)
private val TubeBackground = Color(0x22FFFFFF)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WaterSortScreen() {
    val state = remember { WaterSortGameState() }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Level ${state.levelIndex + 1} / ${WaterSortLevels.all.size}   Zuege: ${state.moves}",
                style = MaterialTheme.typography.bodyLarge
            )
            IconButton(onClick = { state.restart() }) {
                Icon(Icons.Filled.Refresh, contentDescription = "Neu starten")
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(14.dp, Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                state.tubes.forEachIndexed { index, tube ->
                    Tube(
                        colors = tube,
                        capacity = state.capacity,
                        selected = state.selectedTube == index,
                        onClick = { state.onTubeTapped(index) }
                    )
                }
            }
        }
    }

    if (state.won) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Geschafft!") },
            text = { Text("Geloest in ${state.moves} Zuegen.") },
            confirmButton = {
                TextButton(onClick = { state.nextLevel() }) { Text("Naechstes Level") }
            },
            dismissButton = {
                TextButton(onClick = { state.restart() }) { Text("Nochmal") }
            }
        )
    }
}

@Composable
private fun Tube(
    colors: List<Color>,
    capacity: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    val segmentHeight = 26.dp
    val tubeWidth = 46.dp
    val tubeShape = RoundedCornerShape(
        bottomStart = 18.dp,
        bottomEnd = 18.dp,
        topStart = 6.dp,
        topEnd = 6.dp
    )

    Column(
        modifier = Modifier
            .width(tubeWidth)
            .height(segmentHeight * capacity)
            .clip(tubeShape)
            .background(TubeBackground)
            .border(
                width = if (selected) 3.dp else 1.5.dp,
                color = if (selected) TubeBorderSelected else TubeBorder,
                shape = tubeShape
            )
            .clickable(onClick = onClick)
            .padding(3.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        repeat(capacity - colors.size) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(segmentHeight)
            )
        }
        colors.asReversed().forEach { color ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(segmentHeight)
                    .background(color)
            )
        }
    }
}
