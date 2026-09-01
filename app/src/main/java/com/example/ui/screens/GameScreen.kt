package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.ui.components.AmazeGameBoard
import com.example.ui.components.GameBottomBar
import com.example.ui.components.GameTopBar
import com.example.ui.components.LevelCompleteDialog
import com.example.ui.components.LevelFailedDialog
import com.example.ui.components.LevelSelectDialog
import com.example.ui.components.SettingsDialog
import com.example.ui.components.ThemeSelectDialog
import com.example.ui.components.ZenBreatheDialog
import com.example.viewmodel.GameViewModel

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val theme = uiState.selectedTheme

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
                onBackClicked = { viewModel.openLevelSelect(true) },
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

        // Modals & Overlays
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

        if (uiState.isLevelSelectOpen) {
            LevelSelectDialog(
                uiState = uiState,
                onSelectLevel = { levelNum -> viewModel.loadLevel(levelNum) },
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
    }
}
