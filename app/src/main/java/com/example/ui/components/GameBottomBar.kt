package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.GameTheme
import com.example.model.PowerUpType

@Composable
fun GameBottomBar(
    bannerText: String,
    hintsRemaining: Int,
    theme: GameTheme,
    powerUpsRemaining: Map<PowerUpType, Int>,
    activePowerUp: PowerUpType?,
    onGridClicked: () -> Unit,
    onHintClicked: () -> Unit,
    onPowerUpClicked: (PowerUpType) -> Unit,
    modifier: Modifier = Modifier
) {
    var showPowerUpsRow by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Power-Ups Bar
        AnimatedVisibility(
            visible = showPowerUpsRow,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .shadow(6.dp, RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp))
                    .background(theme.cardBg.copy(alpha = 0.95f))
                    .border(1.dp, theme.bannerBorder, RoundedCornerShape(24.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
                    .testTag("powerups_bar"),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PowerUpButton(
                    type = PowerUpType.SNIP,
                    count = powerUpsRemaining[PowerUpType.SNIP] ?: 0,
                    isActive = activePowerUp == PowerUpType.SNIP,
                    theme = theme,
                    onClick = { onPowerUpClicked(PowerUpType.SNIP) }
                )
                PowerUpButton(
                    type = PowerUpType.GHOST,
                    count = powerUpsRemaining[PowerUpType.GHOST] ?: 0,
                    isActive = activePowerUp == PowerUpType.GHOST,
                    theme = theme,
                    onClick = { onPowerUpClicked(PowerUpType.GHOST) }
                )
                PowerUpButton(
                    type = PowerUpType.MAGNET,
                    count = powerUpsRemaining[PowerUpType.MAGNET] ?: 0,
                    isActive = activePowerUp == PowerUpType.MAGNET,
                    theme = theme,
                    onClick = { onPowerUpClicked(PowerUpType.MAGNET) }
                )
                PowerUpButton(
                    type = PowerUpType.RECALL,
                    count = powerUpsRemaining[PowerUpType.RECALL] ?: 0,
                    isActive = activePowerUp == PowerUpType.RECALL,
                    theme = theme,
                    onClick = { onPowerUpClicked(PowerUpType.RECALL) }
                )
            }
        }

        // Floating Action Buttons Row (Level Select & Hint)
        Row(
            modifier = Modifier
                .padding(bottom = 14.dp)
                .testTag("bottom_action_buttons"),
            horizontalArrangement = Arrangement.spacedBy(36.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Grid / Level Select Button
            Surface(
                modifier = Modifier
                    .size(64.dp)
                    .shadow(elevation = 8.dp, shape = CircleShape)
                    .clip(CircleShape)
                    .clickable(onClick = onGridClicked)
                    .testTag("grid_button"),
                color = theme.buttonBg,
                shape = CircleShape
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(64.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.GridView,
                        contentDescription = "Levels List",
                        tint = theme.arrowStroke,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            // Lightbulb Hint Button
            Box(
                modifier = Modifier.size(64.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .size(64.dp)
                        .shadow(elevation = 8.dp, shape = CircleShape)
                        .clip(CircleShape)
                        .clickable(onClick = onHintClicked)
                        .testTag("hint_button"),
                    color = theme.buttonBg,
                    shape = CircleShape
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(64.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Lightbulb,
                            contentDescription = "Get Hint",
                            tint = theme.arrowStroke,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                // Badge with hint count
                if (hintsRemaining > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 2.dp, y = (-2).dp)
                            .size(22.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFEF4444))
                            .border(1.5.dp, Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = hintsRemaining.toString(),
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Bottom Kraft Texture Banner Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(theme.bannerBg)
                .border(width = 1.dp, color = theme.bannerBorder)
                .testTag("bottom_banner"),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = bannerText,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.5.sp,
                color = theme.bannerText,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun PowerUpButton(
    type: PowerUpType,
    count: Int,
    isActive: Boolean,
    theme: GameTheme,
    onClick: () -> Unit
) {
    val bgColor = if (isActive) theme.headerGold.copy(alpha = 0.35f) else Color.Transparent
    val borderColor = if (isActive) theme.headerGold else Color.Transparent

    Box(
        modifier = Modifier
            .size(46.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .border(1.5.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .testTag("powerup_${type.name.lowercase()}"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = type.icon,
                fontSize = 18.sp
            )
            Text(
                text = if (count > 0) "×$count" else "Free",
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = theme.textSecondary
            )
        }
    }
}
