package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engine.PuzzleSolver
import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GameTheme
import com.example.model.PowerUpType
import com.example.model.SoftDustParticle
import com.example.viewmodel.FlyingArrow
import com.example.viewmodel.GameUiState
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.hypot
import kotlin.math.sin

@Composable
fun AmazeGameBoard(
    uiState: GameUiState,
    onArrowTapped: (ArrowItem) -> Unit,
    onArrowLongPressed: (ArrowItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val theme = uiState.selectedTheme
    val levelData = uiState.levelData
    val activeArrows = uiState.activeArrows
    val flyingArrows = uiState.flyingArrows
    val hintArrowId = uiState.hintArrowId
    val guidanceArrowId = uiState.guidanceArrowId
    val collisionInfo = uiState.collisionInfo
    val activePowerUp = uiState.activePowerUp
    val comboMultiplier = uiState.comboMultiplier
    val showCombo = uiState.showComboBanner

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.45f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(650, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )
    val dashPhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 48f,
        animationSpec = infiniteRepeatable(
            animation = tween(1100, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "dashPhase"
    )
    val ghostShimmer by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ghostShimmer"
    )

    // Damped harmonic oscillation along direction of collision
    val bounceProgress = remember { Animatable(0f) }
    LaunchedEffect(collisionInfo) {
        if (collisionInfo != null) {
            bounceProgress.snapTo(0f)
            bounceProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(450, easing = LinearEasing)
            )
            bounceProgress.snapTo(0f)
        }
    }

    // Subtle tactile screen shake on invalid move / obstacle collision
    val shakeProgress = remember { Animatable(0f) }
    LaunchedEffect(uiState.shakeTrigger) {
        if (uiState.shakeTrigger > 0L) {
            shakeProgress.snapTo(1f)
            shakeProgress.animateTo(
                targetValue = 0f,
                animationSpec = tween(300, easing = LinearEasing)
            )
        }
    }

    val shakeIntensity = shakeProgress.value
    val shakeOffsetX = if (shakeIntensity > 0f) {
        (sin(shakeIntensity.toDouble() * PI * 8.0) * 8.0 * shakeIntensity.toDouble()).toFloat()
    } else 0f
    val shakeOffsetY = if (shakeIntensity > 0f) {
        (cos(shakeIntensity.toDouble() * PI * 6.0) * 5.0 * shakeIntensity.toDouble()).toFloat()
    } else 0f

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .offset(x = shakeOffsetX.dp, y = shakeOffsetY.dp)
            .testTag("game_board_canvas")
    ) {
        // Main Board Rendering
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(activeArrows, levelData, activePowerUp) {
                    detectTapGestures(
                        onTap = { tapOffset ->
                            val gridW = levelData.gridWidth
                            val gridH = levelData.gridHeight
                            val margin = 28f
                            val cellWidth = (size.width - margin * 2) / (gridW + 1)
                            val cellHeight = (size.height - margin * 2) / (gridH + 1)
                            val cellSize = minOf(cellWidth, cellHeight)
                            val originX = (size.width - cellSize * gridW) / 2f
                            val originY = (size.height - cellSize * gridH) / 2f

                            val tappedArrow = findTappedArrow(
                                tapOffset = tapOffset,
                                arrows = activeArrows,
                                originX = originX,
                                originY = originY,
                                cellSize = cellSize
                            )
                            if (tappedArrow != null) {
                                onArrowTapped(tappedArrow)
                            }
                        },
                        onLongPress = { tapOffset ->
                            val gridW = levelData.gridWidth
                            val gridH = levelData.gridHeight
                            val margin = 28f
                            val cellWidth = (size.width - margin * 2) / (gridW + 1)
                            val cellHeight = (size.height - margin * 2) / (gridH + 1)
                            val cellSize = minOf(cellWidth, cellHeight)
                            val originX = (size.width - cellSize * gridW) / 2f
                            val originY = (size.height - cellSize * gridH) / 2f

                            val tappedArrow = findTappedArrow(
                                tapOffset = tapOffset,
                                arrows = activeArrows,
                                originX = originX,
                                originY = originY,
                                cellSize = cellSize
                            )
                            if (tappedArrow != null) {
                                onArrowLongPressed(tappedArrow)
                            }
                        }
                    )
                }
        ) {
            val gridW = levelData.gridWidth
            val gridH = levelData.gridHeight
            val margin = 28f
            val cellWidth = (size.width - margin * 2) / (gridW + 1)
            val cellHeight = (size.height - margin * 2) / (gridH + 1)
            val cellSize = minOf(cellWidth, cellHeight)
            val originX = (size.width - cellSize * gridW) / 2f
            val originY = (size.height - cellSize * gridH) / 2f

            val strokeWidth = (cellSize * 0.18f).coerceIn(4f, 11f)

            // Draw rounded cell background tiles and center dots
            drawTactileGridTiles(
                originX = originX,
                originY = originY,
                gridW = gridW,
                gridH = gridH,
                cellSize = cellSize,
                theme = theme
            )

            // Draw Guidance exit trajectory if active
            if (guidanceArrowId != null) {
                val guidedArrow = activeArrows.find { it.id == guidanceArrowId }
                if (guidedArrow != null) {
                    val potentialCollision = PuzzleSolver.checkCollision(
                        arrow = guidedArrow,
                        activeArrows = activeArrows,
                        gridWidth = levelData.gridWidth,
                        gridHeight = levelData.gridHeight
                    )
                    drawEnhancedGuidance(
                        arrow = guidedArrow,
                        collision = potentialCollision,
                        originX = originX,
                        originY = originY,
                        cellSize = cellSize,
                        dashPhase = dashPhase,
                        theme = theme
                    )
                }
            }

            // Draw all active arrows with soft drop shadow & state styling
            for (arrow in activeArrows) {
                val isHinted = arrow.id == hintArrowId
                val isGuidance = arrow.id == guidanceArrowId
                val isColliding = collisionInfo?.blockedArrowId == arrow.id
                val isSnipTarget = activePowerUp == PowerUpType.SNIP && (arrow.points.size >= 3 || arrow.totalLength() >= 3f)
                val isGhostTarget = activePowerUp == PowerUpType.GHOST || arrow.isGhost

                var offsetX = 0f
                var offsetY = 0f
                if (isColliding && bounceProgress.value > 0f) {
                    val t = bounceProgress.value
                    val dampFactor = (exp(-t * 5.0) * sin(t * PI * 8.0) * 14.0).toFloat()
                    offsetX = arrow.headDirection.dx * dampFactor
                    offsetY = arrow.headDirection.dy * dampFactor
                }

                val strokeColor = when {
                    isColliding -> theme.errorColor
                    isGhostTarget -> Color(0xFFA855F7)
                    isSnipTarget -> Color(0xFFF59E0B)
                    isHinted -> theme.hintColor
                    isGuidance -> theme.hintColor
                    else -> theme.arrowStroke
                }

                drawArrowItem(
                    arrow = arrow,
                    originX = originX + offsetX,
                    originY = originY + offsetY,
                    cellSize = cellSize,
                    strokeWidth = strokeWidth,
                    strokeColor = strokeColor,
                    isHinted = isHinted || isSnipTarget || isGhostTarget,
                    pulseAlpha = if (isGhostTarget) ghostShimmer else pulseAlpha,
                    theme = theme
                )

                // Collision indicator at the blocking point
                if (isColliding && collisionInfo != null) {
                    val blockPt = collisionInfo.blockedAtPoint
                    val cx = originX + blockPt.x * cellSize + cellSize / 2f
                    val cy = originY + blockPt.y * cellSize + cellSize / 2f

                    // Red Shockwave Ring
                    drawCircle(
                        color = theme.errorColor.copy(alpha = (1f - bounceProgress.value) * 0.7f),
                        radius = cellSize * (0.3f + bounceProgress.value * 0.9f),
                        style = Stroke(width = 3f),
                        center = Offset(cx, cy)
                    )

                    drawCircle(
                        color = theme.errorColor.copy(alpha = 0.9f),
                        radius = cellSize * 0.38f,
                        center = Offset(cx, cy)
                    )
                    drawCircle(
                        color = Color.White,
                        radius = cellSize * 0.16f,
                        center = Offset(cx, cy)
                    )
                }
            }

            // Draw flying/exiting arrows translating cleanly in head direction
            for (flying in flyingArrows) {
                drawFlyingArrow(
                    flying = flying,
                    originX = originX,
                    originY = originY,
                    cellSize = cellSize,
                    strokeWidth = strokeWidth,
                    theme = theme
                )
            }

            // Draw soft dust & stardust ASMR particles on arrow clear
            drawSoftDustParticles(
                particles = uiState.softDustParticles,
                originX = originX,
                originY = originY,
                cellSize = cellSize
            )
        }

        // Floating Combo Multiplier Badge
        AnimatedVisibility(
            visible = showCombo && comboMultiplier >= 2,
            enter = fadeIn(spring()) + scaleIn(initialScale = 0.8f),
            exit = fadeOut(tween(200)) + scaleOut(targetScale = 0.9f),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
                .testTag("combo_badge")
        ) {
            Box(
                modifier = Modifier
                    .shadow(12.dp, RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                theme.headerGold,
                                theme.dropActiveColor
                            )
                        )
                    )
                    .border(1.5.dp, Color.White.copy(alpha = 0.8f), RoundedCornerShape(20.dp))
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "⚡",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "COMBO x$comboMultiplier",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.2.sp,
                        color = Color.White
                    )
                }
            }
        }

        // Combo Power-Up Reward Toast
        val comboReward = uiState.comboReward
        AnimatedVisibility(
            visible = comboReward != null,
            enter = fadeIn(spring()) + scaleIn(initialScale = 0.85f),
            exit = fadeOut(tween(250)) + scaleOut(targetScale = 0.9f),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 60.dp)
                .testTag("combo_reward_toast")
        ) {
            if (comboReward != null) {
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = theme.cardBg,
                    shadowElevation = 8.dp,
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, theme.headerGold),
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = comboReward.powerUpType.icon,
                            fontSize = 18.sp
                        )
                        Text(
                            text = comboReward.message,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = theme.headerGold
                        )
                    }
                }
            }
        }

        // Active Power-Up Mode Notice
        AnimatedVisibility(
            visible = activePowerUp != null,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
                .testTag("powerup_mode_banner")
        ) {
            val pName = activePowerUp?.title ?: ""
            val pIcon = activePowerUp?.icon ?: ""
            Box(
                modifier = Modifier
                    .shadow(8.dp, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(theme.cardBg)
                    .border(1.dp, theme.bannerBorder, RoundedCornerShape(16.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "$pIcon Tap a snake to apply $pName",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.textPrimary
                )
            }
        }
    }
}

/**
 * Draws rounded cell tiles with center dots matching the board style.
 */
private fun DrawScope.drawTactileGridTiles(
    originX: Float,
    originY: Float,
    gridW: Int,
    gridH: Int,
    cellSize: Float,
    theme: GameTheme
) {
    val boardPadding = 8f
    val boardLeft = originX - boardPadding
    val boardTop = originY - boardPadding
    val boardWidth = gridW * cellSize + boardPadding * 2
    val boardHeight = gridH * cellSize + boardPadding * 2

    // Main board container rounded card
    drawRoundRect(
        color = theme.boardBackground,
        topLeft = Offset(boardLeft, boardTop),
        size = androidx.compose.ui.geometry.Size(boardWidth, boardHeight),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(24f, 24f)
    )

    // Cell tiles
    val tileInset = cellSize * 0.07f
    val tileSize = cellSize - tileInset * 2
    val cellTileColor = if (theme.isDark) Color(0xFF3B2B50).copy(alpha = 0.85f) else theme.cardBg
    val dotColor = if (theme.isDark) Color(0xFFE879F9).copy(alpha = 0.6f) else theme.textSecondary.copy(alpha = 0.25f)

    for (x in 0 until gridW) {
        for (y in 0 until gridH) {
            val cellLeft = originX + x * cellSize + tileInset
            val cellTop = originY + y * cellSize + tileInset

            drawRoundRect(
                color = cellTileColor,
                topLeft = Offset(cellLeft, cellTop),
                size = androidx.compose.ui.geometry.Size(tileSize, tileSize),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(14f, 14f)
            )

            // Center subtle tactile dot
            drawCircle(
                color = dotColor,
                radius = 2.2f,
                center = Offset(cellLeft + tileSize / 2f, cellTop + tileSize / 2f)
            )
        }
    }
}

/**
 * Renders an arrow with soft drop shadow, crisp geometric lines, and hint halo.
 */
private fun DrawScope.drawArrowItem(
    arrow: ArrowItem,
    originX: Float,
    originY: Float,
    cellSize: Float,
    strokeWidth: Float,
    strokeColor: Color,
    isHinted: Boolean,
    pulseAlpha: Float,
    theme: GameTheme
) {
    if (arrow.points.isEmpty()) return

    val headPt = arrow.points.last()
    val headCenterX = originX + headPt.x * cellSize + cellSize / 2f
    val headCenterY = originY + headPt.y * cellSize + cellSize / 2f

    // Glowing yellow hint aura centered on arrowhead
    if (isHinted) {
        drawCircle(
            color = theme.headerGold.copy(alpha = pulseAlpha * 0.45f),
            radius = cellSize * 0.48f,
            center = Offset(headCenterX, headCenterY)
        )
        drawCircle(
            color = theme.headerGold.copy(alpha = pulseAlpha * 0.25f),
            radius = cellSize * 0.65f,
            center = Offset(headCenterX, headCenterY)
        )
    }

    if (arrow.points.size >= 2) {
        val path = Path()
        val shadowPath = Path()

        val shadowOffsetY = 3.5f
        val firstPt = arrow.points.first()
        val startX = originX + firstPt.x * cellSize + cellSize / 2f
        val startY = originY + firstPt.y * cellSize + cellSize / 2f

        path.moveTo(startX, startY)
        shadowPath.moveTo(startX, startY + shadowOffsetY)

        for (i in 1 until arrow.points.size) {
            val pt = arrow.points[i]
            val px = originX + pt.x * cellSize + cellSize / 2f
            val py = originY + pt.y * cellSize + cellSize / 2f
            path.lineTo(px, py)
            shadowPath.lineTo(px, py + shadowOffsetY)
        }

        // Soft drop shadow underneath body
        val shadowColor = if (theme.isDark) Color.Black.copy(alpha = 0.4f) else Color(0x33000000)
        drawPath(
            path = shadowPath,
            color = shadowColor,
            style = Stroke(
                width = strokeWidth + 2f,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )

        // Outer glow if hinted or selected
        if (isHinted) {
            drawPath(
                path = path,
                color = strokeColor.copy(alpha = pulseAlpha * 0.55f),
                style = Stroke(
                    width = strokeWidth * 2.6f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
        }

        // Main arrow body
        drawPath(
            path = path,
            color = strokeColor,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }

    // Arrowhead at the last point
    val headColor = if (arrow.isGhost) Color(0xFFA855F7) else strokeColor
    drawArrowHead(
        center = Offset(headCenterX, headCenterY),
        direction = arrow.headDirection,
        size = strokeWidth * 2.3f,
        color = headColor
    )
}

/**
 * Snake Slither Kinematics:
 * The arrow follows its multi-bend polyline track forward, turning around corners
 * naturally as its tail advances through each bend vertex along the path.
 */
private fun DrawScope.drawFlyingArrow(
    flying: FlyingArrow,
    originX: Float,
    originY: Float,
    cellSize: Float,
    strokeWidth: Float,
    theme: GameTheme
) {
    val arrow = flying.arrow
    val progress = flying.progress
    if (arrow.points.isEmpty() || progress >= 1f) return

    val alpha = (1f - progress * progress).coerceIn(0f, 1f)
    if (alpha <= 0.01f) return

    val strokeColor = if (arrow.isGhost) {
        Color(0xFFA855F7).copy(alpha = alpha)
    } else {
        theme.arrowStroke.copy(alpha = alpha)
    }

    // Handle single-point arrowheads (1-cell arrows)
    if (arrow.points.size == 1) {
        val pt = arrow.points.first()
        val dir = arrow.headDirection
        val exitDist = 14f * cellSize
        val currentX = originX + pt.x * cellSize + cellSize / 2f + dir.dx * exitDist * progress
        val currentY = originY + pt.y * cellSize + cellSize / 2f + dir.dy * exitDist * progress

        val headColor = if (arrow.isGhost) Color(0xFFA855F7).copy(alpha = alpha) else strokeColor
        drawArrowHead(
            center = Offset(currentX, currentY),
            direction = dir,
            size = strokeWidth * 2.3f,
            color = headColor
        )

        // Stardust trail
        for (p in 1..3) {
            val trailDist = p * cellSize * 0.35f
            val sparkX = currentX - dir.dx * trailDist
            val sparkY = currentY - dir.dy * trailDist
            val sparkAlpha = (alpha * (0.7f / p)).coerceIn(0f, 1f)
            drawCircle(
                color = theme.dropActiveColor.copy(alpha = sparkAlpha),
                radius = (cellSize * 0.1f * (1f - p * 0.25f)).coerceAtLeast(2f),
                center = Offset(sparkX, sparkY)
            )
        }
        return
    }

    // Multi-segment arrow slither along polyline track
    val exitDistanceCells = 16f
    val lastPt = arrow.points.last()
    val extHead = Offset(
        originX + lastPt.x * cellSize + cellSize / 2f + arrow.headDirection.dx * cellSize * exitDistanceCells,
        originY + lastPt.y * cellSize + cellSize / 2f + arrow.headDirection.dy * cellSize * exitDistanceCells
    )

    // 1. Build polyline track in Canvas space
    val track = ArrayList<Offset>(arrow.points.size + 1)
    for (pt in arrow.points) {
        track.add(
            Offset(
                originX + pt.x * cellSize + cellSize / 2f,
                originY + pt.y * cellSize + cellSize / 2f
            )
        )
    }
    track.add(extHead)

    // 2. Measure cumulative track lengths
    var bodyLength = 0f
    var totalTrackLen = 0f
    val segLengths = ArrayList<Float>(track.size - 1)
    for (i in 0 until track.size - 1) {
        val segLen = (track[i + 1] - track[i]).getDistance()
        segLengths.add(segLen)
        totalTrackLen += segLen
        if (i < arrow.points.size - 1) {
            bodyLength += segLen
        }
    }

    // 3. Compute continuous travel distance along track
    val distanceTraveled = progress * totalTrackLen
    val sTail = distanceTraveled
    val sHead = minOf(totalTrackLen, bodyLength + distanceTraveled)

    if (sTail >= sHead) return

    val visiblePoints = sampleTrackSubPolyline(track, segLengths, sTail, sHead)
    if (visiblePoints.size < 2) return

    val path = Path()
    val shadowPath = Path()
    val shadowOffsetY = 4f

    val pFirst = visiblePoints.first()
    path.moveTo(pFirst.x, pFirst.y)
    shadowPath.moveTo(pFirst.x, pFirst.y + shadowOffsetY)

    for (i in 1 until visiblePoints.size) {
        val pt = visiblePoints[i]
        path.lineTo(pt.x, pt.y)
        shadowPath.lineTo(pt.x, pt.y + shadowOffsetY)
    }

    // Shadow under flying arrow
    val shadowAlpha = (0.35f * alpha).coerceAtLeast(0f)
    drawPath(
        path = shadowPath,
        color = Color.Black.copy(alpha = shadowAlpha),
        style = Stroke(
            width = strokeWidth + 2f,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
    )

    // Glowing speed trail
    drawPath(
        path = path,
        color = theme.dropActiveColor.copy(alpha = alpha * 0.45f),
        style = Stroke(
            width = strokeWidth * 1.8f,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
    )

    // Main arrow body
    drawPath(
        path = path,
        color = strokeColor,
        style = Stroke(
            width = strokeWidth,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
    )

    // Instantaneous arrowhead oriented along tangent of leading front
    val lastVisible = visiblePoints.last()
    val prevVisible = visiblePoints[visiblePoints.size - 2]
    val tangent = lastVisible - prevVisible
    val angleDegrees = Math.toDegrees(atan2(tangent.y.toDouble(), tangent.x.toDouble())).toFloat()

    val headColor = if (arrow.isGhost) Color(0xFFA855F7).copy(alpha = alpha) else strokeColor
    drawOrientedArrowHead(
        center = lastVisible,
        angleDegrees = angleDegrees,
        size = strokeWidth * 2.3f,
        color = headColor
    )

    // Trailing stardust sparks behind the moving tail
    val tailPt = visiblePoints.first()
    val tailTangent = if (visiblePoints.size >= 2) (visiblePoints[1] - visiblePoints[0]) else tangent
    val tailNorm = if (tailTangent.getDistance() > 0.001f) (tailTangent / tailTangent.getDistance()) else Offset(1f, 0f)

    for (p in 1..3) {
        val trailDist = p * cellSize * 0.3f
        val sparkPos = tailPt - tailNorm * trailDist
        val sparkAlpha = (alpha * (0.75f / p)).coerceIn(0f, 1f)

        drawCircle(
            color = theme.dropActiveColor.copy(alpha = sparkAlpha),
            radius = (cellSize * 0.09f * (1f - p * 0.22f)).coerceAtLeast(2f),
            center = sparkPos
        )
    }
}

/**
 * Samples the exact sub-polyline segment between cumulative arc lengths [sTail] and [sHead].
 */
private fun sampleTrackSubPolyline(
    track: List<Offset>,
    segLengths: List<Float>,
    sTail: Float,
    sHead: Float
): List<Offset> {
    if (track.size < 2 || sTail >= sHead) return emptyList()

    val result = ArrayList<Offset>(track.size + 2)
    var accumulated = 0f

    for (i in segLengths.indices) {
        val segLen = segLengths[i]
        if (segLen <= 0.001f) continue

        val segStart = accumulated
        val segEnd = accumulated + segLen
        accumulated = segEnd

        if (segEnd < sTail) continue
        if (segStart > sHead) break

        val p1 = track[i]
        val p2 = track[i + 1]

        // 1. Add interpolated start point if sTail lies on this segment
        if (sTail in segStart..segEnd) {
            val t = (sTail - segStart) / segLen
            val pt = p1 + (p2 - p1) * t
            result.add(pt)
        }

        // 2. Add corner vertex p2 if it lies strictly between sTail and sHead
        if (segEnd > sTail + 0.5f && segEnd < sHead - 0.5f) {
            result.add(p2)
        }

        // 3. Add interpolated end point if sHead lies on this segment
        if (sHead in segStart..segEnd) {
            val t = (sHead - segStart) / segLen
            val pt = p1 + (p2 - p1) * t
            if (result.isEmpty() || (result.last() - pt).getDistance() > 0.5f) {
                result.add(pt)
            }
            break
        }
    }

    return result
}

private fun DrawScope.drawArrowHead(
    center: Offset,
    direction: Direction,
    size: Float,
    color: Color
) {
    drawOrientedArrowHead(center, direction.angleDegrees, size, color)
}

private fun DrawScope.drawOrientedArrowHead(
    center: Offset,
    angleDegrees: Float,
    size: Float,
    color: Color
) {
    val angleRad = Math.toRadians(angleDegrees.toDouble()).toFloat()

    val tip = Offset(
        center.x + cos(angleRad) * (size * 0.9f),
        center.y + sin(angleRad) * (size * 0.9f)
    )

    val leftAngle = angleRad + Math.toRadians(142.0).toFloat()
    val rightAngle = angleRad - Math.toRadians(142.0).toFloat()

    val p1 = Offset(
        tip.x + cos(leftAngle) * size,
        tip.y + sin(leftAngle) * size
    )
    val p2 = Offset(
        tip.x + cos(rightAngle) * size,
        tip.y + sin(rightAngle) * size
    )

    val headPath = Path().apply {
        moveTo(tip.x, tip.y)
        lineTo(p1.x, p1.y)
        lineTo(p2.x, p2.y)
        close()
    }

    drawPath(
        path = headPath,
        color = color
    )
}

private fun DrawScope.drawEnhancedGuidance(
    arrow: ArrowItem,
    collision: com.example.model.CollisionInfo?,
    originX: Float,
    originY: Float,
    cellSize: Float,
    dashPhase: Float,
    theme: GameTheme
) {
    val headPt = arrow.points.last()
    val dir = arrow.headDirection

    val startX = originX + headPt.x * cellSize + cellSize / 2f
    val startY = originY + headPt.y * cellSize + cellSize / 2f

    val isBlocked = collision != null
    val targetDistanceCells = if (isBlocked) {
        val blockPt = collision.blockedAtPoint
        hypot((blockPt.x - headPt.x).toFloat(), (blockPt.y - headPt.y).toFloat())
    } else {
        22f
    }

    val endX = startX + dir.dx * cellSize * targetDistanceCells
    val endY = startY + dir.dy * cellSize * targetDistanceCells

    val color = if (isBlocked) theme.errorColor.copy(alpha = 0.85f) else theme.hintColor.copy(alpha = 0.85f)
    val dashedEffect = PathEffect.dashPathEffect(floatArrayOf(16f, 12f), dashPhase)

    drawLine(
        color = color,
        start = Offset(startX, startY),
        end = Offset(endX, endY),
        strokeWidth = 4.5f,
        pathEffect = dashedEffect,
        cap = StrokeCap.Round
    )

    if (isBlocked) {
        drawCircle(
            color = theme.errorColor,
            radius = cellSize * 0.28f,
            center = Offset(endX, endY)
        )
        drawCircle(
            color = Color.White,
            radius = cellSize * 0.12f,
            center = Offset(endX, endY)
        )
    } else {
        drawCircle(
            color = theme.hintColor.copy(alpha = 0.4f),
            radius = cellSize * 0.35f,
            center = Offset(endX, endY)
        )
    }
}

/**
 * Hit testing: Checks if a tap coordinate touches any segment of an arrow.
 */
private fun findTappedArrow(
    tapOffset: Offset,
    arrows: List<ArrowItem>,
    originX: Float,
    originY: Float,
    cellSize: Float
): ArrowItem? {
    val hitTolerance = cellSize * 0.8f

    for (arrow in arrows) {
        for (i in 0 until arrow.points.size - 1) {
            val p1 = arrow.points[i]
            val p2 = arrow.points[i + 1]

            val ax = originX + p1.x * cellSize + cellSize / 2f
            val ay = originY + p1.y * cellSize + cellSize / 2f
            val bx = originX + p2.x * cellSize + cellSize / 2f
            val by = originY + p2.y * cellSize + cellSize / 2f

            val dist = distanceToSegment(tapOffset.x, tapOffset.y, ax, ay, bx, by)
            if (dist <= hitTolerance) {
                return arrow
            }
        }
    }
    return null
}

private fun distanceToSegment(
    px: Float, py: Float,
    x1: Float, y1: Float,
    x2: Float, y2: Float
): Float {
    val dx = x2 - x1
    val dy = y2 - y1
    val lenSq = dx * dx + dy * dy

    if (lenSq == 0f) {
        return hypot(px - x1, py - y1)
    }

    val t = ((px - x1) * dx + (py - y1) * dy) / lenSq
    val clampedT = t.coerceIn(0f, 1f)

    val projX = x1 + clampedT * dx
    val projY = y1 + clampedT * dy

    return hypot(px - projX, py - projY)
}

/**
 * Draws floating ASMR soft dust and stardust particles on arrow escape.
 */
private fun DrawScope.drawSoftDustParticles(
    particles: List<SoftDustParticle>,
    originX: Float,
    originY: Float,
    cellSize: Float
) {
    val now = System.currentTimeMillis()
    for (particle in particles) {
        val age = now - particle.createdAt
        if (age < particle.maxAgeMs) {
            val progress = age.toFloat() / particle.maxAgeMs
            val alpha = (1f - progress).coerceIn(0f, 1f)
            val currentX = originX + particle.origin.x * cellSize + cellSize / 2f + particle.velocity.x * progress
            val currentY = originY + particle.origin.y * cellSize + cellSize / 2f + particle.velocity.y * progress
            val currentRadius = particle.radius * (1f + progress * 0.4f)

            // Outer soft dispersion glow
            drawCircle(
                color = particle.color.copy(alpha = alpha * 0.3f),
                radius = currentRadius * 1.9f,
                center = Offset(currentX, currentY)
            )
            // Core spark particle
            drawCircle(
                color = particle.color.copy(alpha = alpha * 0.85f),
                radius = currentRadius,
                center = Offset(currentX, currentY)
            )
            // Bright white stardust center
            drawCircle(
                color = Color.White.copy(alpha = alpha * 0.9f),
                radius = currentRadius * 0.35f,
                center = Offset(currentX, currentY)
            )
        }
    }
}
