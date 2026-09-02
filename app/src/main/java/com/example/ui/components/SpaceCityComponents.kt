package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.content.cities.SpaceCityDefinition
import com.example.content.cities.SpaceCityLevelRegistry
import com.example.ui.theme.SpaceCityThemeColors

/**
 * Header for the Space City World Map.
 */
@Composable
fun SpaceCityHeader(
    totalStars: Int,
    maxStars: Int = 60,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(44.dp)
                    .background(SpaceCityThemeColors.CardSurface, CircleShape)
                    .border(1.dp, SpaceCityThemeColors.CardBorder, CircleShape)
                    .testTag("space_city_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to Main Menu",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "CITY 01 • SPACE CITY",
                        color = SpaceCityThemeColors.NebulaCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "🚀", fontSize = 14.sp)
                }
                Text(
                    text = "WORLD MAP",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
            }
        }

        // Star Counter Badge
        Box(
            modifier = Modifier
                .background(SpaceCityThemeColors.CardSurface, RoundedCornerShape(20.dp))
                .border(1.dp, SpaceCityThemeColors.OrbitGold.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                .padding(horizontal = 14.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Stars earned",
                    tint = SpaceCityThemeColors.OrbitGold,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "$totalStars / $maxStars",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

/**
 * Hero banner at the top of the Space City map.
 */
@Composable
fun SpaceCityHeroBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(SpaceCityThemeColors.HeroIslandGradient, RoundedCornerShape(20.dp))
            .border(1.dp, SpaceCityThemeColors.CardBorder, RoundedCornerShape(20.dp))
            .padding(18.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "SPACE CITY 🚀",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "LAUNCH YOUR LOGIC INTO THE COSMOS",
                        color = SpaceCityThemeColors.OrbitGold,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.8.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(SpaceCityThemeColors.CosmicViolet, Color.Transparent)
                            ),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🪐", fontSize = 28.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Navigate cosmic pathways, unlock orbital routes and avoid space traps. Every arrow you clear brings you closer to the destination.",
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        }
    }
}

/**
 * Individual Map Node View representing a level (01..20).
 */
@Composable
fun SpaceCityNodeView(
    levelNumber: Int,
    isUnlocked: Boolean,
    isCurrent: Boolean,
    stars: Int,
    landmarkLabel: String,
    landmarkIcon: String,
    isMajorMilestone: Boolean,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "node_pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

    val nodeSize = if (isMajorMilestone) 56.dp else 48.dp
    val formattedNum = if (levelNumber < 10) "0$levelNumber" else "$levelNumber"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .testTag("space_city_node_$levelNumber")
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(nodeSize + 16.dp)
        ) {
            // Pulse ring for current level
            if (isCurrent) {
                Box(
                    modifier = Modifier
                        .size((nodeSize + 12.dp) * pulseScale)
                        .border(2.dp, SpaceCityThemeColors.NebulaCyan.copy(alpha = 0.6f), CircleShape)
                )
            }

            // Master level halo
            if (levelNumber == 20) {
                Box(
                    modifier = Modifier
                        .size(nodeSize + 8.dp)
                        .border(
                            2.dp,
                            Brush.sweepGradient(
                                listOf(
                                    SpaceCityThemeColors.SolarCrimson,
                                    SpaceCityThemeColors.OrbitGold,
                                    SpaceCityThemeColors.NebulaCyan,
                                    SpaceCityThemeColors.SolarCrimson
                                )
                            ),
                            CircleShape
                        )
                )
            }

            // Main Circle Node
            val bgColor = when {
                !isUnlocked -> SpaceCityThemeColors.NodeLocked
                stars > 0 -> SpaceCityThemeColors.CosmicViolet
                isCurrent -> SpaceCityThemeColors.DarkNebulaBg
                else -> SpaceCityThemeColors.CardSurface
            }

            val borderColor = when {
                !isUnlocked -> SpaceCityThemeColors.NodeLockedBorder
                isCurrent -> SpaceCityThemeColors.NebulaCyan
                stars > 0 -> SpaceCityThemeColors.NebulaCyan
                else -> SpaceCityThemeColors.CosmicViolet
            }

            Box(
                modifier = Modifier
                    .size(nodeSize)
                    .shadow(if (isUnlocked) 8.dp else 0.dp, CircleShape)
                    .background(bgColor, CircleShape)
                    .border(2.dp, borderColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (!isUnlocked) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Locked Level",
                        tint = Color.White.copy(alpha = 0.35f),
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = formattedNum,
                            color = Color.White,
                            fontSize = if (isMajorMilestone) 16.sp else 14.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace
                        )
                        if (stars > 0) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                repeat(stars) {
                                    Icon(
                                        imageVector = Icons.Filled.Star,
                                        contentDescription = null,
                                        tint = SpaceCityThemeColors.OrbitGold,
                                        modifier = Modifier.size(8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Landmark name badge
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(SpaceCityThemeColors.CardSurface.copy(alpha = 0.85f), RoundedCornerShape(8.dp))
                .border(0.5.dp, SpaceCityThemeColors.CardBorder, RoundedCornerShape(8.dp))
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text(text = landmarkIcon, fontSize = 10.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = landmarkLabel,
                color = if (isUnlocked) Color.White else Color.White.copy(alpha = 0.4f),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * Detail modal card shown when tapping a level node.
 */
@Composable
fun SpaceCityLevelDetailDialog(
    levelNumber: Int,
    isUnlocked: Boolean,
    stars: Int,
    onDismiss: () -> Unit,
    onStartLevel: (Int) -> Unit
) {
    val def = SpaceCityLevelRegistry.getDefinition(levelNumber)

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .border(1.5.dp, SpaceCityThemeColors.CosmicViolet, RoundedCornerShape(24.dp)),
            color = SpaceCityThemeColors.DeepCosmosBg
        ) {
            Column(
                modifier = Modifier
                    .background(SpaceCityThemeColors.SpaceGradient)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Tier Badge
                Box(
                    modifier = Modifier
                        .background(def.fuelDropTier.activeColor.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                        .border(1.dp, def.fuelDropTier.activeColor, RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "TIER: ${def.fuelDropTier.label.uppercase()}",
                        color = def.fuelDropTier.activeColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Level Number and Title
                Text(
                    text = "LEVEL ${if (levelNumber < 10) "0$levelNumber" else "$levelNumber"}",
                    color = SpaceCityThemeColors.OrbitGold,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = def.title.uppercase(),
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Concept Description Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SpaceCityThemeColors.CardSurface, RoundedCornerShape(16.dp))
                        .border(1.dp, SpaceCityThemeColors.CardBorder, RoundedCornerShape(16.dp))
                        .padding(14.dp)
                ) {
                    Column {
                        Text(
                            text = "STRATEGIC CONCEPT",
                            color = SpaceCityThemeColors.NebulaCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.8.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = def.conceptDescription,
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "“${def.loreSnippet}”",
                            color = Color.White.copy(alpha = 0.5f),
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Serif
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Metrics Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MetricColumn("STRATEGIC DEPTH", "${def.targetStrategicDepth}")
                    MetricColumn("MAX DROPS", "${def.fuelDropTier.maxDrops}")
                    MetricColumn("RECORD", if (stars > 0) "★ $stars" else "—")
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Action Button
                if (isUnlocked) {
                    Button(
                        onClick = {
                            onDismiss()
                            onStartLevel(levelNumber)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("start_mission_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SpaceCityThemeColors.CosmicViolet
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.PlayArrow,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "START MISSION",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .background(SpaceCityThemeColors.NodeLocked, RoundedCornerShape(16.dp))
                            .border(1.dp, SpaceCityThemeColors.NodeLockedBorder, RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.4f),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "LOCKED • COMPLETE LEVEL ${levelNumber - 1}",
                                color = Color.White.copy(alpha = 0.5f),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MetricColumn(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.4f),
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black
        )
    }
}
