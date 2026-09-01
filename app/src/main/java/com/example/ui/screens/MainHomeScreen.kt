package com.example.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.GameTheme
import com.example.viewmodel.GameUiState

@Composable
fun MainHomeScreen(
    uiState: GameUiState,
    onContinueGame: () -> Unit,
    onOpenLevelSelect: () -> Unit,
    onOpenDailyChallenge: () -> Unit,
    onOpenStats: () -> Unit,
    onOpenStore: () -> Unit,
    onOpenZenBreathe: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenVipRewards: () -> Unit,
    onOpenAbout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val theme = uiState.selectedTheme
    val scrollState = rememberScrollState()

    // Futuristic gentle emblem pulse & rotation
    val infiniteTransition = rememberInfiniteTransition(label = "homeHeroAnim")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.96f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heroPulse"
    )

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.75f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heroGlow"
    )

    val ringRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(24000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "ringRotate"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(theme.background)
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 520.dp)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Stars Pill Badge
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = theme.boardBackground,
                    border = androidx.compose.foundation.BorderStroke(1.dp, theme.bannerBorder),
                    modifier = Modifier
                        .clickable { onOpenVipRewards() }
                        .testTag("stars_badge_button")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Total Stars",
                            tint = Color(0xFFFBBF24),
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "${uiState.totalStars} Stars",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = theme.textPrimary
                        )
                    }
                }

                // Action Buttons (VIP & Settings)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = theme.boardBackground,
                        border = androidx.compose.foundation.BorderStroke(1.dp, theme.bannerBorder)
                    ) {
                        IconButton(
                            onClick = onOpenZenBreathe,
                            modifier = Modifier.size(42.dp).testTag("home_zen_button")
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.SelfImprovement,
                                contentDescription = "Zen Breathe",
                                tint = theme.dropActiveColor,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }

                    Surface(
                        shape = CircleShape,
                        color = theme.boardBackground,
                        border = androidx.compose.foundation.BorderStroke(1.dp, theme.bannerBorder)
                    ) {
                        IconButton(
                            onClick = onOpenSettings,
                            modifier = Modifier.size(42.dp).testTag("home_settings_button")
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = "Settings",
                                tint = theme.textPrimary,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hero Emblem Area
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 12.dp)
            ) {
                // Futuristic Glowing Emblem
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .scale(pulseScale),
                    contentAlignment = Alignment.Center
                ) {
                    // Outer Rotating Futuristic Glow Ring
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                            .rotate(ringRotation)
                    ) {
                        val strokeWidth = 3.dp.toPx()
                        val radius = size.minDimension / 2f - strokeWidth

                        drawCircle(
                            brush = Brush.sweepGradient(
                                listOf(
                                    theme.dropActiveColor.copy(alpha = 0.1f),
                                    theme.dropActiveColor.copy(alpha = glowAlpha),
                                    theme.headerGold.copy(alpha = glowAlpha),
                                    theme.dropActiveColor.copy(alpha = 0.1f)
                                )
                            ),
                            radius = radius,
                            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                        )
                    }

                    // Inner Emblem Disc
                    Box(
                        modifier = Modifier
                            .size(134.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(
                                        theme.cardBg,
                                        theme.boardBackground
                                    )
                                )
                            )
                            .border(2.dp, theme.dropActiveColor.copy(alpha = 0.5f), CircleShape)
                            .shadow(12.dp, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        // Futuristic Arrow Emblem Chevron Graphic
                        Canvas(modifier = Modifier.size(72.dp)) {
                            val w = size.width
                            val h = size.height

                            // Draw bold neon arrow chevron pointing upward/right
                            val arrowPath = Path().apply {
                                moveTo(w * 0.22f, h * 0.62f)
                                lineTo(w * 0.50f, h * 0.32f)
                                lineTo(w * 0.78f, h * 0.62f)
                            }

                            drawPath(
                                path = arrowPath,
                                color = theme.dropActiveColor,
                                style = Stroke(
                                    width = 12.dp.toPx(),
                                    cap = StrokeCap.Round,
                                    join = StrokeJoin.Round
                                )
                            )

                            // Arrow stem
                            drawLine(
                                color = theme.dropActiveColor,
                                start = Offset(w * 0.50f, h * 0.34f),
                                end = Offset(w * 0.50f, h * 0.82f),
                                strokeWidth = 12.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Title & Subtitle
                Text(
                    text = "ARROW CITY",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 4.sp,
                    color = theme.textPrimary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Navigate city maze grids • Beta Early Access",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = theme.textSecondary,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Primary Action: CONTINUE (LEVEL X)
                Button(
                    onClick = onContinueGame,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF2563EB),
                                    Color(0xFF0284C7),
                                    Color(0xFF06B6D4)
                                )
                            ),
                            shape = RoundedCornerShape(28.dp)
                        )
                        .shadow(10.dp, RoundedCornerShape(28.dp))
                        .testTag("home_continue_button")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "CONTINUE (LEVEL ${uiState.currentLevelNumber})",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp
                        )
                    }
                }

                // Level Selection
                MenuButton(
                    title = "LEVEL SELECTION",
                    icon = Icons.Outlined.GridView,
                    theme = theme,
                    onClick = onOpenLevelSelect,
                    testTag = "home_level_select_button"
                )

                // Daily Challenge
                MenuButton(
                    title = "DAILY CHALLENGE",
                    icon = Icons.Outlined.CalendarMonth,
                    theme = theme,
                    badgeText = "Streak ${uiState.dailyStreak}",
                    onClick = onOpenDailyChallenge,
                    testTag = "home_daily_challenge_button"
                )

                // Statistics & Profile
                MenuButton(
                    title = "STATISTICS & PROFILE",
                    icon = Icons.Outlined.BarChart,
                    theme = theme,
                    onClick = onOpenStats,
                    testTag = "home_stats_button"
                )

                // Cosmetic Store
                MenuButton(
                    title = "COSMETIC STORE",
                    icon = Icons.Outlined.AutoAwesome,
                    theme = theme,
                    onClick = onOpenStore,
                    testTag = "home_store_button"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Footer info
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = theme.boardBackground.copy(alpha = 0.7f),
                modifier = Modifier
                    .clickable { onOpenAbout() }
                    .testTag("home_about_button")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "About",
                        tint = theme.textSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Beta v1.0.0 • No Ads • Offline Ready",
                        fontSize = 12.sp,
                        color = theme.textSecondary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun MenuButton(
    title: String,
    icon: ImageVector,
    theme: GameTheme,
    badgeText: String? = null,
    onClick: () -> Unit,
    testTag: String
) {
    Surface(
        shape = RoundedCornerShape(28.dp),
        color = theme.boardBackground,
        border = androidx.compose.foundation.BorderStroke(1.5.dp, theme.bannerBorder),
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .clickable { onClick() }
            .testTag(testTag)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = theme.dropActiveColor,
                    modifier = Modifier.size(22.dp)
                )
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp,
                    color = theme.textPrimary
                )
            }

            if (badgeText != null) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = theme.bannerBg,
                    border = androidx.compose.foundation.BorderStroke(1.dp, theme.bannerBorder)
                ) {
                    Text(
                        text = badgeText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = theme.headerGold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }
        }
    }
}
