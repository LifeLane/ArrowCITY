package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.GameTheme

@Composable
fun GameTopBar(
    levelNumber: Int,
    remainingDrops: Int,
    maxDrops: Int,
    sessionSeconds: Long,
    theme: GameTheme,
    onBackClicked: () -> Unit,
    onResetClicked: () -> Unit = {},
    onThemeClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val setIndex = (levelNumber - 1) / 10
    val sectorNumber = setIndex + 1
    val nodeInSet = (levelNumber - 1) % 10 // 0 to 9
    val sectorNames = listOf(
        "Downtown Grid", "Neon District", "Cyber Hub", "Matrix Core",
        "Aero Harbor", "Pulse Nexus", "Solar Boulevard", "Zenith Heights",
        "Prism Alley", "Metropolis Apex"
    )
    val sectorName = sectorNames.getOrElse(setIndex % sectorNames.size) { "Sector $sectorNumber" }

    val infiniteTransition = rememberInfiniteTransition(label = "nodePulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.35f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "activePulse"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 4.dp)
    ) {
        // Top Action Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onBackClicked,
                    modifier = Modifier.testTag("top_bar_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Exit to Menu",
                        tint = theme.textPrimary,
                        modifier = Modifier.size(26.dp)
                    )
                }

                IconButton(
                    onClick = onResetClicked,
                    modifier = Modifier.testTag("top_bar_reset_button")
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Refresh,
                        contentDescription = "Restart Level",
                        tint = theme.textSecondary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Level $levelNumber",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.headerGold,
                    modifier = Modifier.testTag("level_title_text")
                )

                // Discreet Zen session flow timer
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = theme.boardBackground.copy(alpha = 0.55f),
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .testTag("zen_session_timer")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.SelfImprovement,
                            contentDescription = "Mindful Flow Time",
                            tint = theme.textSecondary.copy(alpha = 0.75f),
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = formatSessionDuration(sessionSeconds),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = theme.textSecondary.copy(alpha = 0.85f),
                            letterSpacing = 0.4.sp
                        )
                    }
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onThemeClicked,
                    modifier = Modifier.testTag("theme_selector_button")
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Palette,
                        contentDescription = "Change Theme",
                        tint = theme.textPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }

                IconButton(
                    onClick = onSettingsClicked,
                    modifier = Modifier.testTag("settings_button")
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = "Audio & Game Settings",
                        tint = theme.textPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Visual Progression Tracker: City Set Nodes & Progress Bar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .testTag("city_progression_tracker")
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$sectorName • Sector $sectorNumber",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = theme.textSecondary.copy(alpha = 0.8f)
                )
                Text(
                    text = "${nodeInSet + 1}/10",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.headerGold
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Interconnected City Nodes Track
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(14.dp),
                contentAlignment = Alignment.Center
            ) {
                // Subtle background connecting track
                val animatedProgress by animateFloatAsState(
                    targetValue = (nodeInSet + 1) / 10f,
                    animationSpec = tween(400, easing = FastOutSlowInEasing),
                    label = "trackProgress"
                )

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                ) {
                    val w = size.width
                    val h = size.height
                    // Base line
                    drawLine(
                        color = theme.bannerBorder.copy(alpha = 0.4f),
                        start = Offset(0f, h / 2f),
                        end = Offset(w, h / 2f),
                        strokeWidth = 2.5f,
                        cap = StrokeCap.Round
                    )
                    // Filled active track
                    drawLine(
                        brush = Brush.horizontalGradient(
                            listOf(theme.dropActiveColor, theme.headerGold)
                        ),
                        start = Offset(0f, h / 2f),
                        end = Offset(w * animatedProgress, h / 2f),
                        strokeWidth = 3f,
                        cap = StrokeCap.Round
                    )
                }

                // 10 City Nodes Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 0 until 10) {
                        val isCompleted = i < nodeInSet
                        val isCurrent = i == nodeInSet

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(14.dp)
                        ) {
                            if (isCurrent) {
                                // Pulsing beacon ring
                                Box(
                                    modifier = Modifier
                                        .size(13.dp)
                                        .scale(pulseScale)
                                        .clip(CircleShape)
                                        .background(theme.headerGold.copy(alpha = 0.28f))
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .size(if (isCurrent) 8.dp else 6.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when {
                                            isCurrent -> theme.headerGold
                                            isCompleted -> theme.dropActiveColor
                                            else -> theme.boardBackground
                                        }
                                    )
                                    .border(
                                        width = if (isCurrent) 1.5.dp else 1.dp,
                                        color = when {
                                            isCurrent -> Color.White
                                            isCompleted -> theme.dropActiveColor
                                            else -> theme.bannerBorder.copy(alpha = 0.7f)
                                        },
                                        shape = CircleShape
                                    )
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Water Drops / Hearts Row
        Row(
            modifier = Modifier
                .padding(start = 8.dp)
                .testTag("drops_container"),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 0 until maxDrops) {
                val isActive = i < remainingDrops
                WaterDropIcon(
                    isActive = isActive,
                    activeColor = theme.dropActiveColor,
                    inactiveColor = theme.dropInactiveColor,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}

private fun formatSessionDuration(seconds: Long): String {
    val mins = seconds / 60
    val secs = seconds % 60
    return String.format("%02d:%02d", mins, secs)
}

/**
 * Custom teardrop water drop vector shape
 */
@Composable
fun WaterDropIcon(
    isActive: Boolean,
    activeColor: Color,
    inactiveColor: Color,
    modifier: Modifier = Modifier
) {
    val animatedColor by animateColorAsState(
        targetValue = if (isActive) activeColor else inactiveColor,
        animationSpec = tween(durationMillis = 280),
        label = "dropColor"
    )

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        val path = Path().apply {
            moveTo(w * 0.5f, 0f)
            // Left curve
            cubicTo(
                w * 0.45f, h * 0.25f,
                0f, h * 0.55f,
                0f, h * 0.72f
            )
            // Bottom circular arc
            cubicTo(
                0f, h * 0.95f,
                w * 0.22f, h,
                w * 0.5f, h
            )
            // Right circular arc
            cubicTo(
                w * 0.78f, h,
                w, h * 0.95f,
                w, h * 0.72f
            )
            // Right curve back to top tip
            cubicTo(
                w, h * 0.55f,
                w * 0.55f, h * 0.25f,
                w * 0.5f, 0f
            )
            close()
        }

        drawPath(path = path, color = animatedColor)

        // Subtle soft highlight glint on active drops
        if (isActive) {
            drawCircle(
                color = Color.White.copy(alpha = 0.45f),
                radius = w * 0.12f,
                center = Offset(w * 0.35f, h * 0.6f)
            )
        }
    }
}
