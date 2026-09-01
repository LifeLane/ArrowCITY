package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material.icons.outlined.Palette
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
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
    onThemeClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        // Top Action Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClicked,
                modifier = Modifier.testTag("top_bar_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back / Level Select",
                    tint = theme.textPrimary,
                    modifier = Modifier.size(28.dp)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Level $levelNumber",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.headerGold,
                    modifier = Modifier.testTag("level_title_text")
                )

                // Discreet, non-intrusive Zen session flow timer
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = theme.boardBackground.copy(alpha = 0.5f),
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
                            tint = theme.textSecondary.copy(alpha = 0.7f),
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = formatSessionDuration(sessionSeconds),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = theme.textSecondary.copy(alpha = 0.8f),
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
                        modifier = Modifier.size(26.dp)
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
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Water Drops / Hearts Row
        Row(
            modifier = Modifier
                .padding(start = 12.dp, top = 2.dp)
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
                    modifier = Modifier.size(24.dp)
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
