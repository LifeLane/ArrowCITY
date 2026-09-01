package com.example

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.viewmodel.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Amaze GO!", appName)
    }

    @Test
    fun `test level management system tracking and unlocking`() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = GameViewModel(app)

        // Initial level is 1
        assertEquals(1, viewModel.uiState.value.currentLevelNumber)
        assertTrue(viewModel.isLevelUnlocked(1))

        // Level 2 should initially be unlocked or lockable
        viewModel.unlockLevel(3)
        assertTrue(viewModel.isLevelUnlocked(1))
        assertTrue(viewModel.isLevelUnlocked(2))
        assertTrue(viewModel.isLevelUnlocked(3))
        assertEquals(3, viewModel.uiState.value.highestUnlockedLevel)

        // Loading level 2 resets grid state and tracks level 2
        viewModel.loadLevel(2)
        assertEquals(2, viewModel.uiState.value.currentLevelNumber)
        assertEquals(0, viewModel.uiState.value.movesCount)
        assertFalse(viewModel.uiState.value.isLevelCompleted)
        assertFalse(viewModel.uiState.value.isLevelFailed)
        assertTrue(viewModel.uiState.value.activeArrows.isNotEmpty())
        assertTrue(viewModel.uiState.value.flyingArrows.isEmpty())

        // Next level unlocks and tracks level 3
        viewModel.nextLevel()
        assertEquals(3, viewModel.uiState.value.currentLevelNumber)
        assertTrue(viewModel.isLevelUnlocked(3))

        // Reset grid state cleans up moves and board
        viewModel.resetGridState()
        assertEquals(3, viewModel.uiState.value.currentLevelNumber)
        assertEquals(0, viewModel.uiState.value.movesCount)
        assertEquals(viewModel.uiState.value.levelData.maxDrops, viewModel.uiState.value.remainingDrops)
    }
}

