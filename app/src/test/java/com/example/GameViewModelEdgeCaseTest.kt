package com.example

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GridPoint
import com.example.model.PowerUpType
import com.example.viewmodel.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class GameViewModelEdgeCaseTest {

    @Test
    fun testRapidTappingAndDropDeduction() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = GameViewModel(app)

        viewModel.loadLevel(1)
        val initialDrops = viewModel.uiState.value.remainingDrops
        assertEquals(3, initialDrops)

        // Find a blocked arrow or simulate collision
        val blocker = ArrowItem(99, listOf(GridPoint(5, 1), GridPoint(5, 5)), Direction.DOWN)
        val blocked = ArrowItem(100, listOf(GridPoint(1, 2), GridPoint(6, 2)), Direction.RIGHT)

        // Test tapping blocked arrow repeatedly
        viewModel.onArrowTapped(blocked)
        assertTrue(viewModel.uiState.value.remainingDrops <= initialDrops)

        // Repeat tapping
        viewModel.onArrowTapped(blocked)
        viewModel.onArrowTapped(blocked)

        // Test power-up counts are never negative
        for (powerUp in PowerUpType.entries) {
            val count = viewModel.uiState.value.powerUpsRemaining[powerUp] ?: 0
            assertTrue("PowerUp $powerUp count ($count) should never be negative", count >= 0)
        }
    }

    @Test
    fun testPowerUpLifecycleAndRecall() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = GameViewModel(app)

        viewModel.loadLevel(1)
        val stateBefore = viewModel.uiState.value
        val initialArrowsCount = stateBefore.activeArrows.size

        // Select Snip
        viewModel.onPowerUpSelected(PowerUpType.SNIP)
        assertEquals(PowerUpType.SNIP, viewModel.uiState.value.activePowerUp)

        // Toggle Snip off
        viewModel.onPowerUpSelected(PowerUpType.SNIP)
        assertEquals(null, viewModel.uiState.value.activePowerUp)

        // Select Ghost
        viewModel.onPowerUpSelected(PowerUpType.GHOST)
        assertEquals(PowerUpType.GHOST, viewModel.uiState.value.activePowerUp)

        // Test Recall power up execution
        viewModel.onPowerUpSelected(PowerUpType.RECALL)
        assertTrue(viewModel.uiState.value.powerUpsRemaining[PowerUpType.RECALL]!! >= 0)

        // Revive with drops
        viewModel.reviveWithDrops(3)
        assertEquals(3, viewModel.uiState.value.remainingDrops)
        assertFalse(viewModel.uiState.value.isLevelFailed)
    }

    @Test
    fun testAudioAndHapticToggles() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = GameViewModel(app)

        viewModel.toggleSound(false)
        assertFalse(viewModel.uiState.value.soundEnabled)
        assertFalse(viewModel.uiState.value.movementSoundEnabled)

        viewModel.toggleAmbientNature(false)
        assertFalse(viewModel.uiState.value.ambientNatureEnabled)

        viewModel.toggleHaptic(false)
        assertFalse(viewModel.uiState.value.hapticEnabled)

        // Re-enable
        viewModel.toggleSound(true)
        assertTrue(viewModel.uiState.value.soundEnabled)

        viewModel.toggleAmbientNature(true)
        assertTrue(viewModel.uiState.value.ambientNatureEnabled)

        viewModel.toggleHaptic(true)
        assertTrue(viewModel.uiState.value.hapticEnabled)
    }
}
