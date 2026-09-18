package com.minigames.hub.games.arrows

import android.media.AudioAttributes
import android.media.SoundPool
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.minigames.hub.R
import com.minigames.hub.games.Difficulty
import com.minigames.hub.games.DifficultyPicker
import com.minigames.hub.games.GameInfo
import com.minigames.hub.games.GameModule
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

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
private val SuccessColor = Color(0xFF43A047)
private const val ExitAnimationMillis = 380

/** Laedt den Wegflug-Sound einmal und gibt eine Funktion zurueck, die ihn abspielt. */
@Composable
private fun rememberArrowSwooshPlayer(): () -> Unit {
    val context = LocalContext.current
    val soundPool = remember {
        SoundPool.Builder()
            .setMaxStreams(4)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )
            .build()
    }
    var soundId by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        soundId = soundPool.load(context, R.raw.arrow_swoosh, 1)
    }

    DisposableEffect(Unit) {
        onDispose { soundPool.release() }
    }

    return {
        if (soundId != 0) {
            soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
        }
    }
}

private fun directionVector(direction: Direction): Pair<Float, Float> = when (direction) {
    Direction.UP -> 0f to -1f
    Direction.DOWN -> 0f to 1f
    Direction.LEFT -> -1f to 0f
    Direction.RIGHT -> 1f to 0f
}

@Composable
fun ArrowsScreen() {
    var selectedDifficulty by remember { mutableStateOf<Difficulty?>(null) }
    val reachedLevel = remember { mutableStateMapOf<Difficulty, Int>() }

    val difficulty = selectedDifficulty
    if (difficulty == null) {
        DifficultyPicker(
            description = "Tippe auf die Pfeile, um sie vom Brett zu schiessen, aber stelle sicher, dass ihr Weg frei ist. Einige Pfeile blockieren andere, waehle die richtige Reihenfolge, um jedes Level zu raeumen.",
            resumeLevel = { d -> (reachedLevel[d] ?: 0) + 1 },
            onStart = { d -> selectedDifficulty = d }
        )
    } else {
        ArrowsGameplay(
            difficulty = difficulty,
            startLevelIndex = reachedLevel[difficulty] ?: 0,
            onLevelReached = { index ->
                reachedLevel[difficulty] = maxOf(reachedLevel[difficulty] ?: 0, index)
            },
            onChangeDifficulty = { selectedDifficulty = null }
        )
    }
}

@Composable
private fun ArrowsGameplay(
    difficulty: Difficulty,
    startLevelIndex: Int,
    onLevelReached: (Int) -> Unit,
    onChangeDifficulty: () -> Unit
) {
    val state = remember(difficulty) { ArrowsGameState(difficulty, startLevelIndex) }
    val exitAnimations = remember(difficulty) { mutableStateMapOf<Int, Animatable<Float, AnimationVector1D>>() }
    val playSwoosh = rememberArrowSwooshPlayer()

    LaunchedEffect(state.levelIndex) {
        onLevelReached(state.levelIndex)
    }

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
                onStart = playSwoosh,
                onFinished = { state.finishExit(arrow.id) }
            )
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = difficulty.label,
                color = difficulty.color,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.labelLarge
            )
            TextButton(onClick = onChangeDifficulty) {
                Text("Schwierigkeit wechseln")
            }
        }

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

                    fun drawStar(center: Offset, outerRadius: Float, innerRadius: Float, color: Color, alpha: Float) {
                        val spikes = 5
                        val path = Path()
                        for (i in 0 until spikes * 2) {
                            val radius = if (i % 2 == 0) outerRadius else innerRadius
                            val angle = (Math.PI / spikes) * i - Math.PI / 2
                            val x = center.x + (radius * cos(angle)).toFloat()
                            val y = center.y + (radius * sin(angle)).toFloat()
                            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                        }
                        path.close()
                        drawPath(path, color = color, alpha = alpha)
                    }

                    fun drawRibs(points: List<Offset>, color: Color, alpha: Float) {
                        val ribLength = cellPx * 0.26f
                        for (i in 0 until points.size - 1) {
                            val a = points[i]
                            val b = points[i + 1]
                            val dirX = b.x - a.x
                            val dirY = b.y - a.y
                            val len = hypot(dirX, dirY)
                            if (len < 1f) continue
                            val perpX = -dirY / len
                            val perpY = dirX / len
                            val ribCount = 3
                            for (j in 1..ribCount) {
                                val t = j / (ribCount + 1f)
                                val cx = a.x + dirX * t
                                val cy = a.y + dirY * t
                                drawLine(
                                    color = color,
                                    start = Offset(cx - perpX * ribLength, cy - perpY * ribLength),
                                    end = Offset(cx + perpX * ribLength, cy + perpY * ribLength),
                                    strokeWidth = cellPx * 0.06f,
                                    alpha = alpha
                                )
                            }
                        }
                    }

                    fun drawZigzagSegment(a: Offset, b: Offset, color: Color, strokeWidth: Float, alpha: Float) {
                        val dirX = b.x - a.x
                        val dirY = b.y - a.y
                        val len = hypot(dirX, dirY)
                        if (len < 1f) {
                            drawLine(color, a, b, strokeWidth = strokeWidth, cap = StrokeCap.Round, alpha = alpha)
                            return
                        }
                        val perpX = -dirY / len
                        val perpY = dirX / len
                        val amplitude = cellPx * 0.13f
                        val segments = 4
                        var prev = a
                        for (i in 1..segments) {
                            val t = i / segments.toFloat()
                            val baseX = a.x + dirX * t
                            val baseY = a.y + dirY * t
                            val point = if (i == segments) {
                                Offset(baseX, baseY)
                            } else {
                                val side = if (i % 2 == 0) 1f else -1f
                                Offset(baseX + perpX * amplitude * side, baseY + perpY * amplitude * side)
                            }
                            drawLine(color = color, start = prev, end = point, strokeWidth = strokeWidth, cap = StrokeCap.Round, alpha = alpha)
                            prev = point
                        }
                    }

                    fun drawArrowPiece(arrow: ArrowPiece, color: Color, alpha: Float, translate: Offset) {
                        val points = arrow.cells.map { centerOf(it) + translate }
                        val strokeWidth = cellPx * 0.16f

                        if (arrow.style == ArrowStyle.ZIGZAG) {
                            for (i in 0 until points.size - 1) {
                                drawZigzagSegment(points[i], points[i + 1], color, strokeWidth, alpha)
                            }
                        } else {
                            for (i in 0 until points.size - 1) {
                                drawLine(
                                    color = color,
                                    start = points[i],
                                    end = points[i + 1],
                                    strokeWidth = strokeWidth,
                                    cap = StrokeCap.Round,
                                    alpha = alpha
                                )
                            }
                        }

                        if (arrow.style == ArrowStyle.RIBBED) {
                            drawRibs(points, color, alpha)
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
                            strokeWidth = strokeWidth,
                            cap = StrokeCap.Round,
                            alpha = alpha
                        )

                        when (arrow.style) {
                            ArrowStyle.ROUND -> {
                                drawCircle(color = color, radius = cellPx * 0.22f, center = tip, alpha = alpha)
                            }
                            ArrowStyle.STAR -> {
                                drawStar(center = tip, outerRadius = cellPx * 0.30f, innerRadius = cellPx * 0.13f, color = color, alpha = alpha)
                            }
                            else -> {
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
                        }
                    }

                    state.remainingArrows.forEach { arrow ->
                        val color = if (arrow.id == state.lastBlockedId) BlockedColor else ArrowColor
                        drawArrowPiece(arrow, color, alpha = 1f, translate = Offset.Zero)
                    }

                    state.exitingArrows.forEach { arrow ->
                        val progress = exitAnimations[arrow.id]?.value ?: 0f
                        val (dx, dy) = directionVector(arrow.direction)
                        val translate = Offset(dx * exitDistance * progress, dy * exitDistance * progress)
                        drawArrowPiece(arrow, SuccessColor, alpha = 1f - progress, translate = translate)
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

/** Animiert einen Pfeil aus dem Feld heraus, spielt den Zisch-Sound ab und meldet danach das Ende. */
@Composable
private fun ArrowExitEffect(
    arrowId: Int,
    exitAnimations: MutableMap<Int, Animatable<Float, AnimationVector1D>>,
    onStart: () -> Unit,
    onFinished: () -> Unit
) {
    LaunchedEffect(arrowId) {
        onStart()
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
            text = "Level ${state.levelIndex + 1} / ${state.totalLevels}   Punkte: ${state.score}",
            style = MaterialTheme.typography.bodyLarge
        )

        IconButton(onClick = { state.restart() }) {
            Icon(Icons.Filled.Refresh, contentDescription = "Neu starten")
        }
    }
}
