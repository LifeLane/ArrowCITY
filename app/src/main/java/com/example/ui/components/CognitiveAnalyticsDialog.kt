package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.CrisisAlert
import androidx.compose.material.icons.outlined.FastForward
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.Radar
import androidx.compose.material.icons.outlined.Timeline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.model.CognitiveProfile
import com.example.model.GameTheme

@Composable
fun CognitiveAnalyticsDialog(
    profile: CognitiveProfile,
    theme: GameTheme,
    isInspectorActive: Boolean,
    isAutoSolveActive: Boolean,
    onToggleInspector: () -> Unit,
    onStepSolve: () -> Unit,
    onAutoSolve: () -> Unit,
    onDismiss: () -> Unit
) {
    val iqProgress by animateFloatAsState(
        targetValue = ((profile.puzzleIQ - 100) / 75f).coerceIn(0f, 1f),
        animationSpec = tween(1000, easing = FastOutSlowInEasing),
        label = "iqProgress"
    )

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .testTag("cognitive_analytics_dialog"),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, theme.bannerBorder, RoundedCornerShape(24.dp))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Psychology,
                            contentDescription = null,
                            tint = theme.headerGold,
                            modifier = Modifier.size(28.dp)
                        )
                        Column {
                            Text(
                                text = "Cognitive Intelligence",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = theme.textPrimary
                            )
                            Text(
                                text = "Open-Source DAG Logic Engine",
                                fontSize = 11.sp,
                                color = theme.textSecondary
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = theme.textSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // IQ Ring & Complexity Tier
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(120.dp)
                ) {
                    CircularProgressIndicator(
                        progress = { 1f },
                        modifier = Modifier.size(110.dp),
                        color = theme.boardBackground,
                        strokeWidth = 10.dp,
                        trackColor = Color.Transparent
                    )
                    CircularProgressIndicator(
                        progress = { iqProgress },
                        modifier = Modifier.size(110.dp),
                        color = theme.headerGold,
                        strokeWidth = 10.dp,
                        strokeCap = StrokeCap.Round,
                        trackColor = Color.Transparent
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${profile.puzzleIQ}",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = theme.headerGold
                        )
                        Text(
                            text = "PUZZLE IQ",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = theme.textSecondary,
                            letterSpacing = 1.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = theme.headerGold.copy(alpha = 0.15f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, theme.headerGold.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = profile.complexityTier.uppercase(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = theme.headerGold,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp),
                        letterSpacing = 1.2.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Detailed Metrics Grid
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = theme.boardBackground.copy(alpha = 0.6f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        MetricRow(
                            icon = Icons.Outlined.Timeline,
                            label = "Critical Path Depth",
                            value = "${profile.criticalPathLength} moves",
                            theme = theme
                        )
                        MetricRow(
                            icon = Icons.Outlined.CrisisAlert,
                            label = "Key Chokepoints",
                            value = if (profile.chokepoints.isEmpty()) "None (Linear)" else "${profile.chokepoints.size} arrows",
                            theme = theme
                        )
                        MetricRow(
                            icon = Icons.Outlined.AutoAwesome,
                            label = "Branching Entropy",
                            value = String.format("%.1f choices", profile.branchingFactor),
                            theme = theme
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Summary Note
                Text(
                    text = profile.analyticalSummary,
                    fontSize = 12.sp,
                    color = theme.textSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Interactive Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = {
                            onToggleInspector()
                            onDismiss()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("toggle_inspector_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isInspectorActive) Color(0xFF0284C7) else theme.buttonBg
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Radar,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = if (isInspectorActive) Color.White else theme.textPrimary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isInspectorActive) "Radar Active" else "Tactical Radar",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isInspectorActive) Color.White else theme.textPrimary
                        )
                    }

                    Button(
                        onClick = {
                            onStepSolve()
                            onDismiss()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("step_solve_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = theme.headerGold
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.FastForward,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Step Solve",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MetricRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    theme: GameTheme
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = theme.headerGold,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = label,
                fontSize = 13.sp,
                color = theme.textPrimary
            )
        }
        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = theme.headerGold
        )
    }
}
