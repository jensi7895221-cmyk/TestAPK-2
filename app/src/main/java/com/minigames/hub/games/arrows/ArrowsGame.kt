package com.minigames.hub.games.arrows

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.minigames.hub.games.GameInfo
import com.minigames.hub.games.GameModule
import kotlinx.coroutines.delay

class ArrowsGameModule : GameModule {
    override val info = GameInfo(
        id = "arrows",
        title = "Pfeile",
        description = "Loese das Feld in der richtigen Reihenfolge",
        accentColor = Color(0xFFE0663C)
    )

    @Composable
    override fun Content() {
        ArrowsScreen()
    }
}

private val ArrowColor = Color(0xFF37474F)
private val BlockedColor = Color(0xFFD64545)
private const val ExitAnimationMillis = 380

private fun directionVector(direction: Direction): Pair<Float, Float> = when (direction) {
    Direction.UP -> 0f to -1f
    Direction.DOWN -> 0f to 1f
    Direction.LEFT -> -1f to 0f
    Direction.RIGHT -> 1f to 0f
}

@Composable
fun ArrowsScreen() {
    val state = remember { ArrowsGameState() }
    val exitAnimations = remember { mutableStateMapOf<Int, Animatable<Float, AnimationVector1D>>() }

    LaunchedEffect(state.lastBlockedId) {
        if (state.lastBlockedId != null) {
            delay(350)
            state.clearBlockedFlag()
        }
    }

    state.exitingArrows.forEach { arrow ->
        key(arrow.id) {
            ArrowExitEffect(
                arrowId = arrow.id,
                exitAnimations = exitAnimations,
                onFinished = { state.finishExit(arrow.id) }
            )
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        ArrowsHeader(state = state)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val cellSize = (maxWidth / state.level.cols)
                    .coerceAtMost(64.dp)
                val boardWidth = cellSize * state.level.cols
                val boardHeight = cellSize * state.level.rows

                Canvas(
                    modifier = Modifier
                        .size(boardWidth, boardHeight)
                        .pointerInput(state.level, state.remainingArrows) {
                            detectTapGestures { offset ->
                                val cellPx = cellSize.toPx()
                                val col = (offset.x / cellPx).toInt()
                                val row = (offset.y / cellPx).toInt()
                                val tapped = state.remainingArrows.find { arrow ->
                                    arrow.cells.any { it.row == row && it.col == col }
                                }
                                if (tapped != null) state.onArrowTapped(tapped.id)
                            }
                        }
                ) {
                    val cellPx = cellSize.toPx()
                    val exitDistance = size.width + size.height

                    fun centerOf(cell: Cell) = Offset(
                        x = cell.col * cellPx + cellPx / 2f,
                        y = cell.row * cellPx + cellPx / 2f
                    )

                    fun drawArrowPiece(arrow: ArrowPiece, color: Color, alpha: Float, translate: Offset) {
                        val points = arrow.cells.map { centerOf(it) + translate }

                        for (i in 0 until points.size - 1) {
                            drawLine(
                                color = color,
                                start = points[i],
                                end = points[i + 1],
                                strokeWidth = cellPx * 0.16f,
                                cap = StrokeCap.Round,
                                alpha = alpha
                            )
                        }

                        // Pfeilspitze etwas ueber das letzte Feld hinaus zeichnen
                        val headCenter = points.last()
                        val (dx, dy) = directionVector(arrow.direction)
                        val tipLength = cellPx * 0.55f
                        val tip = Offset(headCenter.x + dx * tipLength, headCenter.y + dy * tipLength)
                        drawLine(
                            color = color,
                            start = headCenter,
                            end = tip,
                            strokeWidth = cellPx * 0.16f,
                            cap = StrokeCap.Round,
                            alpha = alpha
                        )

                        val perpX = -dy
                        val perpY = dx
                        val wing = cellPx * 0.22f
                        val backX = tip.x - dx * wing * 1.4f
                        val backY = tip.y - dy * wing * 1.4f
                        val left = Offset(backX + perpX * wing, backY + perpY * wing)
                        val right = Offset(backX - perpX * wing, backY - perpY * wing)
                        drawLine(color, tip, left, strokeWidth = cellPx * 0.14f, cap = StrokeCap.Round, alpha = alpha)
                        drawLine(color, tip, right, strokeWidth = cellPx * 0.14f, cap = StrokeCap.Round, alpha = alpha)
                    }

                    state.remainingArrows.forEach { arrow ->
                        val color = if (arrow.id == state.lastBlockedId) BlockedColor else ArrowColor
                        drawArrowPiece(arrow, color, alpha = 1f, translate = Offset.Zero)
                    }

                    state.exitingArrows.forEach { arrow ->
                        val progress = exitAnimations[arrow.id]?.value ?: 0f
                        val (dx, dy) = directionVector(arrow.direction)
                        val translate = Offset(dx * exitDistance * progress, dy * exitDistance * progress)
                        drawArrowPiece(arrow, ArrowColor, alpha = 1f - progress, translate = translate)
                    }
                }
            }
        }
    }

    if (state.status == ArrowsGameStatus.WON) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Level geschafft!") },
            text = { Text("Punkte in diesem Level: ${state.score}") },
            confirmButton = {
                TextButton(onClick = { state.nextLevel() }) { Text("Naechstes Level") }
            },
            dismissButton = {
                TextButton(onClick = { state.restart() }) { Text("Nochmal") }
            }
        )
    }

    if (state.status == ArrowsGameStatus.LOST) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Keine Leben mehr") },
            text = { Text("Versuche es noch einmal.") },
            confirmButton = {
                TextButton(onClick = { state.restart() }) { Text("Neu starten") }
            }
        )
    }
}

/** Animiert einen Pfeil aus dem Feld heraus und meldet danach das Ende der Animation. */
@Composable
private fun ArrowExitEffect(
    arrowId: Int,
    exitAnimations: MutableMap<Int, Animatable<Float, AnimationVector1D>>,
    onFinished: () -> Unit
) {
    LaunchedEffect(arrowId) {
        val animatable = Animatable(0f)
        exitAnimations[arrowId] = animatable
        animatable.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = ExitAnimationMillis))
        exitAnimations.remove(arrowId)
        onFinished()
    }
}

@Composable
private fun ArrowsHeader(state: ArrowsGameState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            repeat(state.level.lives) { index ->
                val filled = index < state.lives
                Icon(
                    imageVector = if (filled) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = null,
                    tint = if (filled) Color(0xFFD64545) else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
        }

        Text(
            text = "Level ${state.levelIndex + 1} / ${ArrowsLevels.all.size}   Punkte: ${state.score}",
            style = MaterialTheme.typography.bodyLarge
        )

        IconButton(onClick = { state.restart() }) {
            Icon(Icons.Filled.Refresh, contentDescription = "Neu starten")
        }
    }
}
