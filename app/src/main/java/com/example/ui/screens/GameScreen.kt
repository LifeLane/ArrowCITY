package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.AboutInfoDialog
import com.example.ui.components.AmazeGameBoard
import com.example.ui.components.CosmeticStoreDialog
import com.example.ui.components.DailyChallengeDialog
import com.example.ui.components.GameBottomBar
import com.example.ui.components.GameConfirmationDialog
import com.example.ui.components.GameTopBar
import com.example.ui.components.LevelCompleteDialog
import com.example.ui.components.LevelFailedDialog
import com.example.ui.components.LevelSelectDialog
import com.example.ui.components.BetaCompleteDialog
import com.example.ui.components.SettingsDialog
import com.example.ui.components.SpaceCityCompletionDialog
import com.example.ui.components.StatisticsDialog
import com.example.ui.components.ThemeSelectDialog
import com.example.ui.components.VipRewardsDialog
import com.example.ui.components.ZenBreatheDialog
import com.example.viewmodel.GameViewModel

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val theme = uiState.selectedTheme

    if (uiState.isExperimentalActive) {
        val prototypeViewModel: com.example.viewmodel.StepSlidePrototypeViewModel = viewModel()
        StepSlidePrototypeScreen(
            viewModel = prototypeViewModel,
            theme = theme,
            onBack = { viewModel.openExperimental(false) }
        )
    } else if (uiState.isSpaceCityMapOpen) {
        SpaceCityMapScreen(
            highestUnlockedLevel = uiState.highestUnlockedLevel,
            completedLevelsStars = uiState.completedLevelsStars,
            currentLevelNumber = uiState.currentLevelNumber,
            onLevelSelected = { levelNum ->
                viewModel.loadLevel(levelNum)
                viewModel.openSpaceCityMap(false)
                viewModel.setMainMenuActive(false)
            },
            onBackToHome = {
                viewModel.openSpaceCityMap(false)
            }
        )
    } else if (uiState.isMainMenuActive) {
        MainHomeScreen(
            uiState = uiState,
            onContinueGame = {
                viewModel.setMainMenuActive(false)
            },
            onOpenSpaceCityMap = {
                viewModel.openSpaceCityMap(true)
            },
            onOpenLevelSelect = {
                viewModel.openLevelSelect(true)
            },
            onOpenDailyChallenge = {
                viewModel.openDailyChallenge(true)
            },
            onOpenStats = {
                viewModel.openStats(true)
            },
            onOpenStore = {
                viewModel.openStore(true)
            },
            onOpenZenBreathe = {
                viewModel.openZenBreathe(true)
            },
            onOpenSettings = {
                viewModel.openSettings(true)
            },
            onOpenVipRewards = {
                viewModel.openVipRewards(true)
            },
            onOpenAbout = {
                viewModel.openAbout(true)
            },
            onOpenExperimental = {
                viewModel.openExperimental(true)
            },
            modifier = modifier
        )
    } else {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .testTag("game_screen_scaffold"),
            containerColor = theme.background
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(theme.background)
            ) {
                // Top Navigation & Drop Status Bar
                GameTopBar(
                    levelNumber = uiState.currentLevelNumber,
                    remainingDrops = uiState.remainingDrops,
                    maxDrops = uiState.maxDrops,
                    sessionSeconds = uiState.sessionSeconds,
                    theme = theme,
                    onBackClicked = { viewModel.requestExit() },
                    onResetClicked = { viewModel.requestReset() },
                    onThemeClicked = { viewModel.openThemeSelect(true) },
                    onSettingsClicked = { viewModel.openSettings(true) }
                )

                // Central Game Board Area
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    AmazeGameBoard(
                        uiState = uiState,
                        onArrowTapped = { arrow -> viewModel.onArrowTapped(arrow) },
                        onArrowLongPressed = { arrow -> viewModel.onArrowLongPressed(arrow) }
                    )
                }

                // Bottom Floating Controls & Banner
                GameBottomBar(
                    bannerText = uiState.levelData.bannerText,
                    hintsRemaining = uiState.hintsRemaining,
                    theme = theme,
                    powerUpsRemaining = uiState.powerUpsRemaining,
                    activePowerUp = uiState.activePowerUp,
                    onGridClicked = { viewModel.openLevelSelect(true) },
                    onHintClicked = { viewModel.useHint() },
                    onPowerUpClicked = { powerUp -> viewModel.onPowerUpSelected(powerUp) }
                )
            }
        }
    }

    // Modals & Overlays (Available globally across both Main Screen and Puzzle Board)
    if (uiState.isLevelCompleted) {
        LevelCompleteDialog(
            uiState = uiState,
            onNextLevel = { viewModel.nextLevel() },
            onReplay = { viewModel.restartCurrentLevel() }
        )
    }

    if (uiState.isLevelFailed) {
        LevelFailedDialog(
            uiState = uiState,
            onRevive = { viewModel.reviveWithDrops(3) },
            onRestart = { viewModel.restartCurrentLevel() }
        )
    }

    
    if (uiState.isBetaCompletedOpen) {
        BetaCompleteDialog(
            uiState = uiState,
            onDismiss = { viewModel.closeBetaCompleted() }
        )
    }

    if (uiState.isSpaceCityCompletedOpen) {
        SpaceCityCompletionDialog(
            totalStarsEarned = (1..20).sumOf { uiState.completedLevelsStars[it] ?: 0 },
            onReplayCity = {
                viewModel.openSpaceCityCompleted(false)
                viewModel.loadLevel(1)
                viewModel.setMainMenuActive(false)
            },
            onReturnToMap = {
                viewModel.openSpaceCityCompleted(false)
                viewModel.openSpaceCityMap(true)
            }
        )
    }

    if (uiState.isLevelSelectOpen) {
        LevelSelectDialog(
            uiState = uiState,
            onSelectLevel = { levelNum ->
                viewModel.loadLevel(levelNum)
                viewModel.setMainMenuActive(false)
            },
            onDismiss = { viewModel.openLevelSelect(false) }
        )
    }

    if (uiState.isThemeSelectOpen) {
        ThemeSelectDialog(
            currentTheme = theme,
            onThemeSelected = { newTheme -> viewModel.setTheme(newTheme) },
            onDismiss = { viewModel.openThemeSelect(false) }
        )
    }

    if (uiState.isSettingsOpen) {
        SettingsDialog(
            uiState = uiState,
            onToggleAmbientNature = { viewModel.toggleAmbientNature(it) },
            onToggleMovementSound = { viewModel.toggleMovementSound(it) },
            onToggleHaptic = { viewModel.toggleHaptic(it) },
            onOpenZenBreathe = {
                viewModel.openSettings(false)
                viewModel.openZenBreathe(true)
            },
            onDismiss = { viewModel.openSettings(false) }
        )
    }

    if (uiState.isZenBreatheOpen) {
        ZenBreatheDialog(
            theme = theme,
            onDismiss = { viewModel.openZenBreathe(false) }
        )
    }

    if (uiState.isStatsOpen) {
        StatisticsDialog(
            uiState = uiState,
            onDismiss = { viewModel.openStats(false) }
        )
    }

    if (uiState.isDailyChallengeOpen) {
        DailyChallengeDialog(
            uiState = uiState,
            onPlayDailyChallenge = { viewModel.startDailyChallenge() },
            onDismiss = { viewModel.openDailyChallenge(false) }
        )
    }

    if (uiState.isStoreOpen) {
        CosmeticStoreDialog(
            uiState = uiState,
            onSelectTheme = { th -> viewModel.setTheme(th) },
            onSelectTrail = { tr -> viewModel.setParticleTrail(tr) },
            onEquipArrowSkin = { skin -> viewModel.setEquippedArrowSkin(skin) },
            onEquipBoardCanvas = { canvas -> viewModel.setEquippedBoardCanvas(canvas) },
            onEquipGridStyle = { grid -> viewModel.setEquippedGridStyle(grid) },
            onEquipBgAnim = { anim -> viewModel.setEquippedBackgroundAnim(anim) },
            onDismiss = { viewModel.openStore(false) }
        )
    }

    if (uiState.isVipRewardsOpen) {
        VipRewardsDialog(
            uiState = uiState,
            onDismiss = { viewModel.openVipRewards(false) }
        )
    }

    if (uiState.isAboutOpen) {
        AboutInfoDialog(
            uiState = uiState,
            onDismiss = { viewModel.openAbout(false) }
        )
    }

    if (uiState.isExitConfirmOpen) {
        GameConfirmationDialog(
            title = "Leave Current Puzzle?",
            message = "Your mindful flow progress and move counts for this puzzle will be reset if you return to the main menu.",
            confirmButtonText = "Exit to Menu",
            dismissButtonText = "Stay & Play",
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            theme = theme,
            onConfirm = { viewModel.confirmExit() },
            onDismiss = { viewModel.openExitConfirm(false) }
        )
    }

    if (uiState.isResetConfirmOpen) {
        GameConfirmationDialog(
            title = "Restart Puzzle?",
            message = "Reset all arrows back to their starting city grid positions and restore your move counter.",
            confirmButtonText = "Restart Level",
            dismissButtonText = "Keep Playing",
            icon = Icons.Outlined.Refresh,
            theme = theme,
            onConfirm = { viewModel.confirmReset() },
            onDismiss = { viewModel.openResetConfirm(false) }
        )
    }
}
