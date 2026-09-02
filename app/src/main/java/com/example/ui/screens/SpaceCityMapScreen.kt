package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.content.cities.SpaceCityDefinition
import com.example.ui.components.SpaceCityHeader
import com.example.ui.components.SpaceCityHeroBanner
import com.example.ui.components.SpaceCityLevelDetailDialog
import com.example.ui.components.SpaceCityNodeView
import com.example.ui.theme.SpaceCityThemeColors

/**
 * World Map screen for CITY 01 — SPACE CITY 🚀.
 * Faithfully implements the visual reference and 20-level spatial progression.
 */
@Composable
fun SpaceCityMapScreen(
    highestUnlockedLevel: Int,
    completedLevelsStars: Map<Int, Int>,
    currentLevelNumber: Int,
    onLevelSelected: (Int) -> Unit,
    onBackToHome: () -> Unit
) {
    var selectedLevelForDetail by remember { mutableStateOf<Int?>(null) }

    val totalStars = (1..20).sumOf { completedLevelsStars[it] ?: 0 }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("space_city_map_screen"),
        containerColor = SpaceCityThemeColors.DeepCosmosBg
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(SpaceCityThemeColors.SpaceGradient)
        ) {
            // Header
            SpaceCityHeader(
                totalStars = totalStars,
                maxStars = 60,
                onBackClick = onBackToHome
            )

            // Scrollable Map Area
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Hero Banner
                SpaceCityHeroBanner()

                Spacer(modifier = Modifier.height(16.dp))

                // Interactive 2D Spatial Map Canvas
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(1250.dp)
                        .background(SpaceCityThemeColors.DarkNebulaBg, RoundedCornerShape(24.dp))
                        .border(1.dp, SpaceCityThemeColors.CardBorder, RoundedCornerShape(24.dp))
                ) {
                    val mapWidth = maxWidth
                    val mapHeight = maxHeight

                    // Canvas to draw cosmic spline paths and background stars
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val widthPx = size.width
                        val heightPx = size.height

                        // Draw background ambient celestial stars
                        val starCoords = listOf(
                            Offset(widthPx * 0.15f, heightPx * 0.05f),
                            Offset(widthPx * 0.85f, heightPx * 0.12f),
                            Offset(widthPx * 0.10f, heightPx * 0.22f),
                            Offset(widthPx * 0.90f, heightPx * 0.35f),
                            Offset(widthPx * 0.25f, heightPx * 0.48f),
                            Offset(widthPx * 0.78f, heightPx * 0.62f),
                            Offset(widthPx * 0.12f, heightPx * 0.75f),
                            Offset(widthPx * 0.88f, heightPx * 0.88f),
                            Offset(widthPx * 0.50f, heightPx * 0.45f)
                        )
                        for (star in starCoords) {
                            drawCircle(
                                color = SpaceCityThemeColors.NebulaCyan.copy(alpha = 0.4f),
                                radius = 2.5f,
                                center = star
                            )
                        }

                        // Build connected path between all 20 nodes
                        val nodeOffsets = SpaceCityDefinition.mapCoordinates.map { coord ->
                            Offset(coord.xNorm * widthPx, coord.yNorm * heightPx)
                        }

                        val path = Path()
                        if (nodeOffsets.isNotEmpty()) {
                            path.moveTo(nodeOffsets.first().x, nodeOffsets.first().y)
                            for (i in 1 until nodeOffsets.size) {
                                val prev = nodeOffsets[i - 1]
                                val curr = nodeOffsets[i]
                                val midY = (prev.y + curr.y) / 2f
                                path.cubicTo(
                                    prev.x, midY,
                                    curr.x, midY,
                                    curr.x, curr.y
                                )
                            }
                        }

                        // Draw background glow path
                        drawPath(
                            path = path,
                            color = SpaceCityThemeColors.CosmicViolet.copy(alpha = 0.35f),
                            style = Stroke(
                                width = 8f,
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(16f, 12f), 0f)
                            )
                        )

                        // Draw main connecting dashed trajectory
                        drawPath(
                            path = path,
                            color = SpaceCityThemeColors.PathDashedColor,
                            style = Stroke(
                                width = 3f,
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 10f), 0f)
                            )
                        )
                    }

                    // Render interactive nodes at precise coordinates
                    SpaceCityDefinition.mapCoordinates.forEach { nodeCoord ->
                        val isUnlocked = nodeCoord.levelNumber <= highestUnlockedLevel
                        val isCurrent = nodeCoord.levelNumber == currentLevelNumber
                        val stars = completedLevelsStars[nodeCoord.levelNumber] ?: 0

                        val xOffset = (mapWidth * nodeCoord.xNorm) - 36.dp
                        val yOffset = (mapHeight * nodeCoord.yNorm) - 36.dp

                        Box(
                            modifier = Modifier
                                .offset(x = xOffset, y = yOffset)
                        ) {
                            SpaceCityNodeView(
                                levelNumber = nodeCoord.levelNumber,
                                isUnlocked = isUnlocked,
                                isCurrent = isCurrent,
                                stars = stars,
                                landmarkLabel = nodeCoord.landmarkLabel,
                                landmarkIcon = nodeCoord.landmarkIcon,
                                isMajorMilestone = nodeCoord.isMajorMilestone,
                                onClick = {
                                    selectedLevelForDetail = nodeCoord.levelNumber
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Bottom Callout: City Master Level 20 Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(SpaceCityThemeColors.CardSurface, RoundedCornerShape(20.dp))
                        .border(1.5.dp, SpaceCityThemeColors.OrbitGold.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                        .clickable { selectedLevelForDetail = 20 }
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(SpaceCityThemeColors.MasterBadgeGradient, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.EmojiEvents,
                                    contentDescription = null,
                                    tint = SpaceCityThemeColors.DeepCosmosBg,
                                    modifier = Modifier.size(26.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "CITY MASTER LEVEL 20",
                                    color = SpaceCityThemeColors.OrbitGold,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Black,
                                    letterSpacing = 0.5.sp
                                )
                                Text(
                                    text = "SPACE MASTER • THE COSMIC SUMMIT",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        val isMasterUnlocked = 20 <= highestUnlockedLevel
                        if (isMasterUnlocked) {
                            val masterStars = completedLevelsStars[20] ?: 0
                            if (masterStars > 0) {
                                Row {
                                    repeat(masterStars) {
                                        Icon(
                                            imageVector = Icons.Filled.Star,
                                            contentDescription = null,
                                            tint = SpaceCityThemeColors.OrbitGold,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            } else {
                                Text(
                                    text = "READY",
                                    color = SpaceCityThemeColors.NebulaCyan,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        } else {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = "Locked",
                                tint = Color.White.copy(alpha = 0.4f),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Level Detail Dialog
        selectedLevelForDetail?.let { lvl ->
            SpaceCityLevelDetailDialog(
                levelNumber = lvl,
                isUnlocked = lvl <= highestUnlockedLevel,
                stars = completedLevelsStars[lvl] ?: 0,
                onDismiss = { selectedLevelForDetail = null },
                onStartLevel = { chosenLevel ->
                    selectedLevelForDetail = null
                    onLevelSelected(chosenLevel)
                }
            )
        }
    }
}
