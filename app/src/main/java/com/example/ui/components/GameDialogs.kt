package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.engine.LevelRepository
import com.example.model.GameTheme
import com.example.ui.theme.GameThemes
import com.example.viewmodel.GameUiState

@Composable
fun LevelCompleteDialog(
    uiState: GameUiState,
    onNextLevel: () -> Unit,
    onReplay: () -> Unit
) {
    val theme = uiState.selectedTheme

    Dialog(onDismissRequest = {}) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(16.dp, RoundedCornerShape(24.dp))
                .testTag("level_complete_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Board Cleared!",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.headerGold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "A calm mind brings pure clarity.",
                    fontSize = 14.sp,
                    color = theme.textSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Star Rating
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 1..3) {
                        val isFilled = i <= uiState.completionStars
                        Icon(
                            imageVector = if (isFilled) Icons.Filled.Star else Icons.Outlined.Star,
                            contentDescription = null,
                            tint = if (isFilled) Color(0xFFFBBF24) else theme.dropInactiveColor,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Stats Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(theme.boardBackground)
                        .padding(vertical = 12.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Moves", fontSize = 12.sp, color = theme.textSecondary)
                        Text(
                            "${uiState.movesCount}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = theme.textPrimary
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Time", fontSize = 12.sp, color = theme.textSecondary)
                        Text(
                            "${uiState.completionTimeSeconds}s",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = theme.textPrimary
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Drops Left", fontSize = 12.sp, color = theme.textSecondary)
                        Text(
                            "${uiState.remainingDrops}/${uiState.maxDrops}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = theme.dropActiveColor
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Action Buttons
                Button(
                    onClick = onNextLevel,
                    colors = ButtonDefaults.buttonColors(containerColor = theme.headerGold),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("next_level_button")
                ) {
                    Text(
                        text = "Next Level",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedButton(
                    onClick = onReplay,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("replay_level_button")
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Refresh,
                        contentDescription = null,
                        tint = theme.textPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Replay",
                        color = theme.textPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun LevelFailedDialog(
    uiState: GameUiState,
    onRevive: () -> Unit,
    onRestart: () -> Unit
) {
    val theme = uiState.selectedTheme

    Dialog(onDismissRequest = {}) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(16.dp, RoundedCornerShape(24.dp))
                .testTag("level_failed_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Out of Drops",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.errorColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Obstacles teach us the path. Take a breath and try again or revive!",
                    fontSize = 14.sp,
                    color = theme.textSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onRevive,
                    colors = ButtonDefaults.buttonColors(containerColor = theme.dropActiveColor),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("revive_button")
                ) {
                    Text(
                        text = "Breathe & Revive (+3 Drops)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedButton(
                    onClick = onRestart,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("restart_button")
                ) {
                    Text(
                        text = "Restart Level",
                        color = theme.textPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun LevelSelectDialog(
    uiState: GameUiState,
    onSelectLevel: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    val theme = uiState.selectedTheme
    var selectedTab by remember { mutableIntStateOf(0) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            modifier = Modifier
                .fillMaxWidth()
                .height(540.dp)
                .padding(8.dp)
                .shadow(16.dp, RoundedCornerShape(24.dp))
                .testTag("level_select_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Select Level",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = theme.textPrimary
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Close",
                            tint = theme.textSecondary
                        )
                    }
                }

                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = theme.boardBackground,
                    contentColor = theme.headerGold,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            color = theme.headerGold
                        )
                    }
                ) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = { Text("Silhouettes", fontWeight = FontWeight.Bold) }
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        text = { Text("All (10000+)", fontWeight = FontWeight.Bold) }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (selectedTab == 0) {
                    // Featured handcrafted silhouette levels
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(LevelRepository.featuredLevels) { (lvlNum, title) ->
                            val isCurrent = lvlNum == uiState.currentLevelNumber
                            val isUnlocked = lvlNum <= uiState.highestUnlockedLevel || lvlNum == 10 || lvlNum == 27 || lvlNum == 40 || lvlNum == 99 || lvlNum == 199
                            val stars = uiState.completedLevelsStars[lvlNum] ?: 0

                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(84.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .border(
                                        width = if (isCurrent) 2.dp else 1.dp,
                                        color = if (isCurrent) theme.headerGold else theme.bannerBorder,
                                        shape = RoundedCornerShape(14.dp)
                                    )
                                    .clickable(enabled = isUnlocked) {
                                        onSelectLevel(lvlNum)
                                    },
                                color = if (isCurrent) theme.boardBackground else theme.cardBg
                            ) {
                                Column(
                                    modifier = Modifier.padding(8.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Level $lvlNum",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = theme.textPrimary
                                    )
                                    Text(
                                        text = title,
                                        fontSize = 11.sp,
                                        color = theme.textSecondary,
                                        maxLines = 1
                                    )
                                    if (stars > 0) {
                                        Row {
                                            for (s in 1..stars) {
                                                Icon(
                                                    Icons.Filled.Star,
                                                    contentDescription = null,
                                                    tint = Color(0xFFFBBF24),
                                                    modifier = Modifier.size(12.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    // Quick Level Jump (1 to 100)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(5),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items((1..100).toList()) { lvlNum ->
                            val isCurrent = lvlNum == uiState.currentLevelNumber
                            val isUnlocked = lvlNum <= uiState.highestUnlockedLevel

                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        if (isCurrent) theme.headerGold
                                        else if (isUnlocked) theme.boardBackground
                                        else theme.bannerBg.copy(alpha = 0.5f)
                                    )
                                    .clickable(enabled = isUnlocked) {
                                        onSelectLevel(lvlNum)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (isUnlocked) {
                                    Text(
                                        text = lvlNum.toString(),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = if (isCurrent) Color.White else theme.textPrimary
                                    )
                                } else {
                                    Icon(
                                        Icons.Filled.Lock,
                                        contentDescription = "Locked",
                                        tint = theme.textSecondary.copy(alpha = 0.6f),
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeSelectDialog(
    currentTheme: GameTheme,
    onThemeSelected: (GameTheme) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = currentTheme.cardBg),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .shadow(16.dp, RoundedCornerShape(24.dp))
                .testTag("theme_select_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Calming Themes",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = currentTheme.textPrimary
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Outlined.Close, contentDescription = "Close", tint = currentTheme.textSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    GameThemes.allThemes.forEach { theme ->
                        val isSelected = theme.id == currentTheme.id
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .border(
                                    width = if (isSelected) 2.dp else 1.dp,
                                    color = if (isSelected) currentTheme.headerGold else currentTheme.bannerBorder,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable { onThemeSelected(theme) },
                            color = theme.background
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    // Color swatch preview dots
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clip(CircleShape)
                                            .background(theme.arrowStroke)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clip(CircleShape)
                                            .background(theme.dropActiveColor)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = theme.displayName,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = theme.textPrimary
                                    )
                                }

                                if (isSelected) {
                                    Icon(
                                        Icons.Outlined.CheckCircle,
                                        contentDescription = "Selected",
                                        tint = theme.headerGold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsDialog(
    uiState: GameUiState,
    onToggleAmbientNature: (Boolean) -> Unit,
    onToggleMovementSound: (Boolean) -> Unit,
    onToggleHaptic: (Boolean) -> Unit,
    onOpenZenBreathe: () -> Unit,
    onDismiss: () -> Unit
) {
    val theme = uiState.selectedTheme
    val sessionMins = uiState.sessionSeconds / 60
    val sessionSecs = uiState.sessionSeconds % 60

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .shadow(16.dp, RoundedCornerShape(24.dp))
                .testTag("settings_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Zen Sound & Settings",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = theme.textPrimary
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Outlined.Close, contentDescription = "Close", tint = theme.textSecondary)
                    }
                }

                // Session length tracker badge
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = theme.boardBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            Icons.Outlined.SelfImprovement,
                            contentDescription = null,
                            tint = theme.headerGold,
                            modifier = Modifier.size(20.dp)
                        )
                        Column {
                            Text(
                                text = "Current Session Flow",
                                fontSize = 11.sp,
                                color = theme.textSecondary
                            )
                            Text(
                                text = "${sessionMins}m ${sessionSecs}s of Mindful Focus",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = theme.textPrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Ambient Nature Sounds Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Ambient Nature Sound", fontWeight = FontWeight.SemiBold, color = theme.textPrimary)
                        Text("Forest breeze & singing bowl drone", fontSize = 12.sp, color = theme.textSecondary)
                    }
                    Switch(
                        checked = uiState.ambientNatureEnabled,
                        onCheckedChange = onToggleAmbientNature,
                        colors = SwitchDefaults.colors(checkedThumbColor = theme.headerGold)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Movement Sound Effects Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Movement & ASMR SFX", fontWeight = FontWeight.SemiBold, color = theme.textPrimary)
                        Text("Satisfying slithers, whooshes & clicks", fontSize = 12.sp, color = theme.textSecondary)
                    }
                    Switch(
                        checked = uiState.movementSoundEnabled,
                        onCheckedChange = onToggleMovementSound,
                        colors = SwitchDefaults.colors(checkedThumbColor = theme.headerGold)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Haptic Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Tactile Haptic Feedback", fontWeight = FontWeight.SemiBold, color = theme.textPrimary)
                        Text("Subtle tactile micro-vibrations", fontSize = 12.sp, color = theme.textSecondary)
                    }
                    Switch(
                        checked = uiState.hapticEnabled,
                        onCheckedChange = onToggleHaptic,
                        colors = SwitchDefaults.colors(checkedThumbColor = theme.headerGold)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Zen Breathing Exercise Button
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .clickable { onOpenZenBreathe() },
                    color = theme.boardBackground
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Outlined.SelfImprovement,
                            contentDescription = null,
                            tint = theme.headerGold,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Zen Breathing Exercise",
                            fontWeight = FontWeight.Bold,
                            color = theme.textPrimary,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // How to Play guide
                Text(
                    text = "• Tap an arrow with a clear lane to the edge to escape.\n• Free power-ups are provided every level & on combos.\n• Combos reward extra power-ups for continuous flow!",
                    fontSize = 11.sp,
                    color = theme.textSecondary,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
fun ZenBreatheDialog(
    theme: GameTheme,
    onDismiss: () -> Unit
) {
    var phase by remember { androidx.compose.runtime.mutableStateOf("Inhale") }
    var secondsLeft by remember { androidx.compose.runtime.mutableIntStateOf(4) }
    var cycleCount by remember { androidx.compose.runtime.mutableIntStateOf(1) }

    androidx.compose.runtime.LaunchedEffect(Unit) {
        val phases = listOf("Inhale", "Hold", "Exhale", "Hold")
        var currentPhaseIndex = 0
        while (true) {
            for (sec in 4 downTo 1) {
                secondsLeft = sec
                kotlinx.coroutines.delay(1000)
            }
            currentPhaseIndex = (currentPhaseIndex + 1) % phases.size
            phase = phases[currentPhaseIndex]
            if (currentPhaseIndex == 0) {
                cycleCount++
            }
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "breathe")
    val breatheScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.25f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breatheScale"
    )

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(16.dp, RoundedCornerShape(24.dp))
                .testTag("zen_breathe_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Mindful Box Breathing",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.headerGold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Cycle $cycleCount • 4-4-4-4 Rhythm",
                    fontSize = 13.sp,
                    color = theme.textSecondary
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Pulsing Breathing Circle with real-time phase and countdown
                Box(
                    modifier = Modifier
                        .size(170.dp)
                        .scale(if (phase == "Inhale" || phase == "Hold") breatheScale else (2.05f - breatheScale).coerceIn(0.8f, 1.25f))
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                listOf(
                                    theme.dropActiveColor.copy(alpha = 0.75f),
                                    theme.dropActiveColor.copy(alpha = 0.12f)
                                )
                            )
                        )
                        .border(3.dp, theme.dropActiveColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = phase,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${secondsLeft}s",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(34.dp))

                Text(
                    text = when (phase) {
                        "Inhale" -> "Breathe in deeply through the nose..."
                        "Exhale" -> "Release tension out through the mouth..."
                        else -> "Hold gently and let your mind settle..."
                    },
                    fontSize = 13.sp,
                    color = theme.textSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = theme.headerGold),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Return to Puzzle", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

