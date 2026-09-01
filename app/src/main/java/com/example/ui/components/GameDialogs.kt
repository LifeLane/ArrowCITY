package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.FlashOn
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.RocketLaunch
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.engine.LevelRepository
import com.example.model.ArrowSkinType
import com.example.model.BackgroundAnimType
import com.example.model.BoardCanvasType
import com.example.model.GameTheme
import com.example.model.MazeGridStyle
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
    val focusManager = LocalFocusManager.current
    var selectedTab by remember { mutableIntStateOf(0) }

    // Page state for 10000+ levels (50 levels per page)
    val pageSize = 50
    val initialPage = ((uiState.currentLevelNumber - 1) / pageSize).coerceIn(0, 199)
    var currentPage by remember { mutableIntStateOf(initialPage) }
    var jumpInputText by remember { mutableStateOf("") }
    var jumpErrorMessage by remember { mutableStateOf<String?>(null) }

    val pageStart = currentPage * pageSize + 1
    val pageEnd = pageStart + pageSize - 1

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .fillMaxHeight(0.88f)
                .padding(vertical = 12.dp)
                .shadow(20.dp, RoundedCornerShape(26.dp))
                .testTag("level_select_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)
            ) {
                // Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Select Level",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = theme.textPrimary
                        )
                        Text(
                            text = "Highest Reached: Level ${uiState.highestUnlockedLevel} • 10,000+ Mazes",
                            fontSize = 12.sp,
                            color = theme.textSecondary
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Close",
                            tint = theme.textSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Mode Tabs
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = theme.boardBackground,
                    contentColor = theme.headerGold,
                    indicator = { tabPositions: List<TabPosition> ->
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            color = theme.headerGold
                        )
                    }
                ) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("🎨 Art Silhouettes", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            }
                        }
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("🗺️ All (10,000+)", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (selectedTab == 0) {
                    // TAB 0: Art Silhouettes (Handcrafted Milestones)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(LevelRepository.silhouetteLevels.size) { idx ->
                            val item = LevelRepository.silhouetteLevels[idx]
                            val isCurrent = item.levelNumber == uiState.currentLevelNumber
                            val isUnlocked = item.levelNumber <= uiState.highestUnlockedLevel
                            val stars = uiState.completedLevelsStars[item.levelNumber] ?: 0

                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(104.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .border(
                                        width = if (isCurrent) 2.dp else 1.dp,
                                        color = if (isCurrent) theme.headerGold else theme.bannerBorder,
                                        shape = RoundedCornerShape(14.dp)
                                    )
                                    .clickable(enabled = isUnlocked) {
                                        onSelectLevel(item.levelNumber)
                                    },
                                color = if (isCurrent) theme.boardBackground else if (isUnlocked) theme.cardBg else theme.bannerBg.copy(alpha = 0.35f)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = item.icon,
                                            fontSize = 20.sp
                                        )
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(if (isUnlocked) theme.dropActiveColor.copy(alpha = 0.15f) else theme.boardBackground)
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        ) {
                                            Text(
                                                text = "Lvl ${item.levelNumber}",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = if (isUnlocked) theme.dropActiveColor else theme.textSecondary
                                            )
                                        }
                                    }

                                    Column {
                                        Text(
                                            text = item.title,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp,
                                            color = if (isUnlocked) theme.textPrimary else theme.textSecondary,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = item.subtitle,
                                            fontSize = 10.sp,
                                            color = theme.textSecondary,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if (isCurrent) {
                                            Text(
                                                text = "PLAYING",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.ExtraBold,
                                                color = theme.headerGold
                                            )
                                        } else if (stars > 0) {
                                            Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                for (s in 1..stars) {
                                                    Icon(
                                                        Icons.Filled.Star,
                                                        contentDescription = null,
                                                        tint = Color(0xFFFBBF24),
                                                        modifier = Modifier.size(13.dp)
                                                    )
                                                }
                                            }
                                        } else if (isUnlocked) {
                                            Text(
                                                text = item.difficulty,
                                                fontSize = 10.sp,
                                                color = theme.textSecondary
                                            )
                                        } else {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Icon(
                                                    Icons.Filled.Lock,
                                                    contentDescription = "Locked",
                                                    tint = theme.textSecondary.copy(alpha = 0.6f),
                                                    modifier = Modifier.size(12.dp)
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text(
                                                    text = "Lvl ${item.levelNumber}",
                                                    fontSize = 10.sp,
                                                    color = theme.textSecondary.copy(alpha = 0.7f)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    // TAB 1: 10,000+ Universe with Sector Pagination & Fast Search
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        // Quick Sector Chips (Horizontal Scroll)
                        val sectorPresets = listOf(
                            "1 - 50" to 0,
                            "51 - 100" to 1,
                            "101 - 150" to 2,
                            "151 - 200" to 3,
                            "201 - 250" to 4,
                            "251 - 500" to 5,
                            "501 - 1000" to 10,
                            "1001 - 5000" to 20,
                            "5001 - 10000" to 100
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            sectorPresets.forEach { (label, targetPage) ->
                                val isSelected = currentPage == targetPage
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isSelected) theme.headerGold else theme.boardBackground)
                                        .clickable { currentPage = targetPage }
                                        .padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = label,
                                        fontSize = 11.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color.White else theme.textPrimary
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Page Stepper & Active Level Button
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { if (currentPage > 0) currentPage-- },
                                enabled = currentPage > 0,
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                    contentDescription = "Previous Page",
                                    tint = if (currentPage > 0) theme.textPrimary else theme.textSecondary.copy(alpha = 0.3f)
                                )
                            }

                            Text(
                                text = "Levels $pageStart – $pageEnd",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = theme.textPrimary
                            )

                            IconButton(
                                onClick = { if (currentPage < 199) currentPage++ },
                                enabled = currentPage < 199,
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                                    contentDescription = "Next Page",
                                    tint = if (currentPage < 199) theme.textPrimary else theme.textSecondary.copy(alpha = 0.3f)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        // Level Grid (50 levels per page)
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(5),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            items(pageSize) { offset ->
                                val lvlNum = pageStart + offset
                                val isCurrent = lvlNum == uiState.currentLevelNumber
                                val isUnlocked = lvlNum <= uiState.highestUnlockedLevel
                                val stars = uiState.completedLevelsStars[lvlNum] ?: 0

                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(
                                            if (isCurrent) theme.headerGold
                                            else if (isUnlocked) theme.boardBackground
                                            else theme.bannerBg.copy(alpha = 0.4f)
                                        )
                                        .border(
                                            width = if (isCurrent) 2.dp else if (isUnlocked && stars > 0) 1.dp else 0.dp,
                                            color = if (isCurrent) Color.White else if (stars > 0) Color(0xFFFBBF24).copy(alpha = 0.6f) else Color.Transparent,
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .clickable(enabled = isUnlocked) {
                                            onSelectLevel(lvlNum)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (isUnlocked) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = lvlNum.toString(),
                                                fontWeight = FontWeight.Bold,
                                                fontSize = if (lvlNum >= 1000) 11.sp else 13.sp,
                                                color = if (isCurrent) Color.White else theme.textPrimary
                                            )
                                            if (stars > 0) {
                                                Row(
                                                    horizontalArrangement = Arrangement.spacedBy(1.dp),
                                                    modifier = Modifier.padding(top = 1.dp)
                                                ) {
                                                    for (s in 1..stars) {
                                                        Icon(
                                                            Icons.Filled.Star,
                                                            contentDescription = null,
                                                            tint = if (isCurrent) Color.White else Color(0xFFFBBF24),
                                                            modifier = Modifier.size(8.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        Icon(
                                            Icons.Filled.Lock,
                                            contentDescription = "Locked",
                                            tint = theme.textSecondary.copy(alpha = 0.5f),
                                            modifier = Modifier.size(15.dp)
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Jump to Level Input Row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(theme.boardBackground)
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            OutlinedTextField(
                                value = jumpInputText,
                                onValueChange = {
                                    jumpInputText = it.filter { ch -> ch.isDigit() }.take(5)
                                    jumpErrorMessage = null
                                },
                                placeholder = { Text("Jump to Level # (1-10000)", fontSize = 11.sp, color = theme.textSecondary) },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Go
                                ),
                                keyboardActions = KeyboardActions(onGo = {
                                    val target = jumpInputText.toIntOrNull()
                                    if (target != null && target in 1..10000) {
                                        if (target <= uiState.highestUnlockedLevel) {
                                            onSelectLevel(target)
                                        } else {
                                            currentPage = ((target - 1) / pageSize).coerceIn(0, 199)
                                            jumpErrorMessage = "Level $target is locked. Clear earlier levels first!"
                                        }
                                        focusManager.clearFocus()
                                    }
                                }),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = theme.headerGold,
                                    unfocusedBorderColor = Color.Transparent,
                                    focusedTextColor = theme.textPrimary,
                                    unfocusedTextColor = theme.textPrimary
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    val target = jumpInputText.toIntOrNull()
                                    if (target != null && target in 1..10000) {
                                        if (target <= uiState.highestUnlockedLevel) {
                                            onSelectLevel(target)
                                        } else {
                                            currentPage = ((target - 1) / pageSize).coerceIn(0, 199)
                                            jumpErrorMessage = "Level $target is locked. Clear earlier levels first!"
                                        }
                                        focusManager.clearFocus()
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = theme.headerGold),
                                shape = RoundedCornerShape(10.dp),
                                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
                            ) {
                                Text("GO", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.White)
                            }
                        }

                        if (jumpErrorMessage != null) {
                            Text(
                                text = jumpErrorMessage ?: "",
                                fontSize = 11.sp,
                                color = theme.dropActiveColor,
                                modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                            )
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
fun GameConfirmationDialog(
    title: String,
    message: String,
    confirmButtonText: String,
    dismissButtonText: String = "Stay in Flow",
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    theme: GameTheme,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(18.dp, RoundedCornerShape(24.dp))
                .testTag("game_confirmation_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(theme.headerGold.copy(alpha = 0.15f))
                        .border(1.5.dp, theme.headerGold.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = theme.headerGold,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.textPrimary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = message,
                    fontSize = 13.sp,
                    color = theme.textSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 19.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .testTag("confirm_dialog_dismiss"),
                        shape = RoundedCornerShape(14.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, theme.bannerBorder)
                    ) {
                        Text(
                            text = dismissButtonText,
                            color = theme.textPrimary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }

                    Button(
                        onClick = onConfirm,
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .testTag("confirm_dialog_confirm"),
                        colors = ButtonDefaults.buttonColors(containerColor = theme.headerGold),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            text = confirmButtonText,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ZenBreatheDialog(
    theme: GameTheme,
    onDismiss: () -> Unit
) {
    var phaseIndex by remember { androidx.compose.runtime.mutableIntStateOf(0) }
    var secondsLeft by remember { androidx.compose.runtime.mutableIntStateOf(4) }
    var cycleCount by remember { androidx.compose.runtime.mutableIntStateOf(1) }

    val phases = listOf("Inhale", "Hold", "Exhale", "Rest")
    val phase = phases[phaseIndex % phases.size]

    androidx.compose.runtime.LaunchedEffect(Unit) {
        while (true) {
            for (sec in 4 downTo 1) {
                secondsLeft = sec
                kotlinx.coroutines.delay(1000)
            }
            phaseIndex = (phaseIndex + 1) % phases.size
            if (phaseIndex == 0) {
                cycleCount++
            }
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "breathe")
    val breatheScale by infiniteTransition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.30f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breatheScale"
    )

    val currentScale = when (phase) {
        "Inhale" -> 0.85f + (4 - secondsLeft) * 0.11f
        "Hold" -> 1.30f
        "Exhale" -> 1.30f - (4 - secondsLeft) * 0.11f
        else -> 0.85f // Rest
    }

    val animatedScale by animateFloatAsState(
        targetValue = currentScale,
        animationSpec = tween(900, easing = FastOutSlowInEasing),
        label = "smoothBreatheScale"
    )

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
                .shadow(20.dp, RoundedCornerShape(26.dp))
                .testTag("zen_breathe_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Zen Pranayama Breathing",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = theme.headerGold
                        )
                        Text(
                            text = "4-4-4-4 Box Breath • Cycle $cycleCount",
                            fontSize = 12.sp,
                            color = theme.textSecondary
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Outlined.Close, contentDescription = "Close", tint = theme.textSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Expanding and Contracting Visual Breathing Circle
                Box(
                    modifier = Modifier
                        .size(190.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Outer aura ring
                    Box(
                        modifier = Modifier
                            .size(175.dp)
                            .scale(animatedScale * 1.15f)
                            .clip(CircleShape)
                            .background(theme.dropActiveColor.copy(alpha = 0.14f))
                    )

                    // Core expanding breathing sphere
                    Box(
                        modifier = Modifier
                            .size(145.dp)
                            .scale(animatedScale)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(
                                        theme.dropActiveColor.copy(alpha = 0.85f),
                                        theme.dropActiveColor.copy(alpha = 0.35f),
                                        theme.headerGold.copy(alpha = 0.20f)
                                    )
                                )
                            )
                            .border(3.dp, theme.dropActiveColor, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = phase,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "${secondsLeft}s",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White.copy(alpha = 0.95f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Phase Affirmation Guidance
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = theme.boardBackground,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = when (phase) {
                            "Inhale" -> "Inhale calm clarity through your nose..."
                            "Hold" -> "Hold gently, resting in peaceful stillness..."
                            "Exhale" -> "Exhale tension and release every worry..."
                            else -> "Rest softly before the next mindful breath..."
                        },
                        fontSize = 13.sp,
                        color = theme.textPrimary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = theme.headerGold),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                ) {
                    Text("Resume Flow", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun StatisticsDialog(
    uiState: GameUiState,
    onDismiss: () -> Unit
) {
    val theme = uiState.selectedTheme
    val totalTimeMinutes = uiState.sessionSeconds / 60
    val totalStars = uiState.totalStars

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(16.dp, RoundedCornerShape(24.dp))
                .testTag("statistics_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Player Statistics",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = theme.headerGold
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Outlined.Close, contentDescription = "Close", tint = theme.textSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Stats Grid
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatCard(
                            title = "Total Stars",
                            value = "$totalStars",
                            icon = Icons.Filled.Star,
                            tint = Color(0xFFFBBF24),
                            theme = theme,
                            modifier = Modifier.weight(1f)
                        )
                        StatCard(
                            title = "Puzzles Solved",
                            value = "${uiState.totalPuzzlesSolved}",
                            icon = Icons.Outlined.EmojiEvents,
                            tint = theme.dropActiveColor,
                            theme = theme,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatCard(
                            title = "Best Combo",
                            value = "${uiState.bestComboStreak}x",
                            icon = Icons.Outlined.FlashOn,
                            tint = Color(0xFFE11D48),
                            theme = theme,
                            modifier = Modifier.weight(1f)
                        )
                        StatCard(
                            title = "Flow Time",
                            value = "${totalTimeMinutes}m",
                            icon = Icons.Outlined.Timer,
                            tint = Color(0xFF10B981),
                            theme = theme,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatCard(
                            title = "Daily Streak",
                            value = "${uiState.dailyStreak} Days",
                            icon = Icons.Outlined.CalendarMonth,
                            tint = Color(0xFFF97316),
                            theme = theme,
                            modifier = Modifier.weight(1f)
                        )
                        StatCard(
                            title = "Power-Ups Used",
                            value = "${uiState.totalPowerUpsUsed}",
                            icon = Icons.Outlined.AutoAwesome,
                            tint = Color(0xFFA855F7),
                            theme = theme,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = theme.headerGold),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Close", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    tint: Color,
    theme: GameTheme,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = theme.boardBackground,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(18.dp))
                Text(title, fontSize = 11.sp, color = theme.textSecondary, fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(value, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = theme.textPrimary)
        }
    }
}

@Composable
fun DailyChallengeDialog(
    uiState: GameUiState,
    onPlayDailyChallenge: () -> Unit,
    onDismiss: () -> Unit
) {
    val theme = uiState.selectedTheme

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(16.dp, RoundedCornerShape(24.dp))
                .testTag("daily_challenge_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Daily Challenge",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = theme.headerGold
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Outlined.Close, contentDescription = "Close", tint = theme.textSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = theme.boardBackground,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarMonth,
                            contentDescription = null,
                            tint = Color(0xFFF97316),
                            modifier = Modifier.size(44.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Streak: ${uiState.dailyStreak} Days in Flow",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = theme.textPrimary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Complete today's curated maze to earn bonus drops and double star rewards!",
                            fontSize = 12.sp,
                            color = theme.textSecondary,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DailyRewardPill(title = "Stars", value = "+3 ⭐", theme = theme)
                    DailyRewardPill(title = "Snip Power", value = "+2 ✂️", theme = theme)
                    DailyRewardPill(title = "Ghost Power", value = "+2 🔮", theme = theme)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onPlayDailyChallenge,
                    colors = ButtonDefaults.buttonColors(containerColor = theme.headerGold),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("play_daily_button")
                ) {
                    Icon(Icons.Outlined.PlayArrow, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Enter Daily Maze", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
private fun DailyRewardPill(title: String, value: String, theme: GameTheme) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = theme.boardBackground,
        modifier = Modifier.padding(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, fontSize = 10.sp, color = theme.textSecondary)
            Text(value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = theme.textPrimary)
        }
    }
}

@Composable
fun CosmeticStoreDialog(
    uiState: GameUiState,
    onSelectTheme: (GameTheme) -> Unit,
    onSelectTrail: (String) -> Unit,
    onEquipArrowSkin: (ArrowSkinType) -> Unit,
    onEquipBoardCanvas: (BoardCanvasType) -> Unit,
    onEquipGridStyle: (MazeGridStyle) -> Unit,
    onEquipBgAnim: (BackgroundAnimType) -> Unit,
    onDismiss: () -> Unit
) {
    val theme = uiState.selectedTheme
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Arrow Skins", "Board Style", "Maze Grids", "Background FX")

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.88f)
                .padding(8.dp)
                .shadow(20.dp, RoundedCornerShape(24.dp))
                .testTag("cosmetic_store_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Cosmetic Boutique",
                            fontSize = 21.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = theme.headerGold
                        )
                        Text(
                            text = "✨ Modular customizations on top of active theme",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = theme.textSecondary
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Outlined.Close, contentDescription = "Close", tint = theme.textSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = Color.Transparent,
                    contentColor = theme.headerGold,
                    indicator = { tabPositions: List<TabPosition> ->
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            color = theme.headerGold
                        )
                    }
                ) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = { Text("Arrows", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        text = { Text("Boards", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
                    )
                    Tab(
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 },
                        text = { Text("Mazes", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
                    )
                    Tab(
                        selected = selectedTab == 3,
                        onClick = { selectedTab = 3 },
                        text = { Text("Atmosphere", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Tab Content List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    when (selectedTab) {
                        0 -> { // Arrow Skins
                            items(ArrowSkinType.entries.size) { idx ->
                                val skin = ArrowSkinType.entries[idx]
                                val isEquipped = skin == uiState.equippedArrowSkin
                                CosmeticItemCard(
                                    title = skin.displayName,
                                    subtitle = skin.description,
                                    iconString = skin.icon,
                                    isEquipped = isEquipped,
                                    theme = theme,
                                    onEquip = { onEquipArrowSkin(skin) }
                                )
                            }
                        }
                        1 -> { // Board Canvases
                            items(BoardCanvasType.entries.size) { idx ->
                                val canvas = BoardCanvasType.entries[idx]
                                val isEquipped = canvas == uiState.equippedBoardCanvas
                                CosmeticItemCard(
                                    title = canvas.displayName,
                                    subtitle = canvas.description,
                                    iconString = canvas.icon,
                                    isEquipped = isEquipped,
                                    theme = theme,
                                    onEquip = { onEquipBoardCanvas(canvas) }
                                )
                            }
                        }
                        2 -> { // Maze Grids
                            items(MazeGridStyle.entries.size) { idx ->
                                val grid = MazeGridStyle.entries[idx]
                                val isEquipped = grid == uiState.equippedGridStyle
                                CosmeticItemCard(
                                    title = grid.displayName,
                                    subtitle = grid.description,
                                    iconString = grid.icon,
                                    isEquipped = isEquipped,
                                    theme = theme,
                                    onEquip = { onEquipGridStyle(grid) }
                                )
                            }
                        }
                        3 -> { // Background FX & Animations
                            items(BackgroundAnimType.entries.size) { idx ->
                                val anim = BackgroundAnimType.entries[idx]
                                val isEquipped = anim == uiState.equippedBackgroundAnim
                                CosmeticItemCard(
                                    title = anim.displayName,
                                    subtitle = anim.description,
                                    iconString = anim.icon,
                                    isEquipped = isEquipped,
                                    theme = theme,
                                    onEquip = { onEquipBgAnim(anim) }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = theme.headerGold),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("cosmetic_done_button")
                ) {
                    Text("Apply & Return", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun CosmeticItemCard(
    title: String,
    subtitle: String,
    iconString: String,
    isEquipped: Boolean,
    theme: GameTheme,
    onEquip: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = if (isEquipped) theme.bannerBg else theme.boardBackground,
        border = BorderStroke(
            width = if (isEquipped) 2.dp else 1.dp,
            color = if (isEquipped) theme.headerGold else theme.bannerBorder.copy(alpha = 0.5f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEquip() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(if (isEquipped) theme.headerGold.copy(alpha = 0.2f) else theme.cardBg),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = iconString, fontSize = 20.sp)
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = theme.textPrimary
                    )
                    Text(
                        text = subtitle,
                        fontSize = 11.sp,
                        color = theme.textSecondary,
                        maxLines = 2
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            if (isEquipped) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = theme.headerGold,
                    modifier = Modifier.padding(2.dp)
                ) {
                    Text(
                        text = "EQUIPPED",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            } else {
                OutlinedButton(
                    onClick = onEquip,
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = theme.headerGold),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text("Equip", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun VipRewardsDialog(
    uiState: GameUiState,
    onDismiss: () -> Unit
) {
    val theme = uiState.selectedTheme
    val totalStars = uiState.totalStars

    val milestones = listOf(
        Pair(3, "Bronze Mind (3 ⭐): Free +3 Hints"),
        Pair(10, "Silver Clarity (10 ⭐): Snip & Ghost Power"),
        Pair(25, "Golden Flow (25 ⭐): Cyber Neon Theme"),
        Pair(50, "Zen Master (50 ⭐): Infinite Zen Mode"),
        Pair(100, "Cosmic Enlightenment (100 ⭐): Master Title")
    )

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(16.dp, RoundedCornerShape(24.dp))
                .testTag("vip_rewards_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Star Milestones & VIP",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = theme.headerGold
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Outlined.Close, contentDescription = "Close", tint = theme.textSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "You have collected $totalStars Stars across all mazes.",
                    fontSize = 13.sp,
                    color = theme.textSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    milestones.forEach { (starsReq, desc) ->
                        val isUnlocked = totalStars >= starsReq
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isUnlocked) theme.bannerBg else theme.boardBackground,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Icon(
                                    imageVector = if (isUnlocked) Icons.Filled.Star else Icons.Outlined.Lock,
                                    contentDescription = null,
                                    tint = if (isUnlocked) Color(0xFFFBBF24) else theme.textSecondary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = desc,
                                    fontSize = 12.sp,
                                    fontWeight = if (isUnlocked) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isUnlocked) theme.textPrimary else theme.textSecondary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = theme.headerGold),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Close", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun AboutInfoDialog(
    uiState: GameUiState,
    onDismiss: () -> Unit
) {
    val theme = uiState.selectedTheme

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(16.dp, RoundedCornerShape(24.dp))
                .testTag("about_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Arrow City • Beta v1.0.0",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.headerGold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "com.mitsara.arrowcity • Beta Early Access",
                    fontSize = 12.sp,
                    color = theme.textSecondary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = theme.boardBackground,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("• Tap clear arrows to navigate the city maze grid.", fontSize = 12.sp, color = theme.textPrimary)
                        Text("• Early Access Perks: All themes & particle trails 100% free.", fontSize = 12.sp, color = theme.textPrimary)
                        Text("• Zero Ads • Zero Sign-In Required • Privacy First.", fontSize = 12.sp, color = theme.textPrimary)
                        Text("• 100% playable offline anytime anywhere.", fontSize = 12.sp, color = theme.textPrimary)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = theme.headerGold),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Got It", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

