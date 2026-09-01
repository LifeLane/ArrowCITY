package com.example.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.SoundManager
import com.example.engine.LevelRepository
import com.example.engine.PuzzleSolver
import com.example.model.ArrowItem
import com.example.model.CollisionInfo
import com.example.model.ComboRewardEvent
import com.example.model.GameTheme
import com.example.model.GridPoint
import com.example.model.ImpactSpark
import com.example.model.LevelData
import com.example.model.MoveHistoryState
import com.example.model.PowerUpType
import com.example.model.ShockwaveRing
import com.example.model.SoftDustParticle
import com.example.ui.theme.GameThemes
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Random

data class FlyingArrow(
    val arrow: ArrowItem,
    val progress: Float = 0f, // 0.0 to 1.0
    val targetOffset: GridPoint
)

data class GameUiState(
    val currentLevelNumber: Int = 1,
    val levelData: LevelData = LevelRepository.getLevel(1),
    val activeArrows: List<ArrowItem> = emptyList(),
    val flyingArrows: List<FlyingArrow> = emptyList(),
    val remainingDrops: Int = 3,
    val maxDrops: Int = 3,
    val movesCount: Int = 0,
    val hintsRemaining: Int = 3,
    val hintArrowId: Int? = null,
    val guidanceArrowId: Int? = null,
    val collisionInfo: CollisionInfo? = null,
    val isLevelCompleted: Boolean = false,
    val isLevelFailed: Boolean = false,
    val completionStars: Int = 3,
    val completionTimeSeconds: Long = 0,
    val sessionSeconds: Long = 0,
    val isLevelSelectOpen: Boolean = false,
    val isThemeSelectOpen: Boolean = false,
    val isSettingsOpen: Boolean = false,
    val isZenBreatheOpen: Boolean = false,
    val selectedTheme: GameTheme = GameThemes.EyeComfort,
    val highestUnlockedLevel: Int = 1,
    val completedLevelsStars: Map<Int, Int> = emptyMap(),
    val soundEnabled: Boolean = true,
    val ambientNatureEnabled: Boolean = true,
    val movementSoundEnabled: Boolean = true,
    val hapticEnabled: Boolean = true,
    val shakeTrigger: Long = 0L,
    val comboMultiplier: Int = 1,
    val showComboBanner: Boolean = false,
    val comboReward: ComboRewardEvent? = null,
    val activePowerUp: PowerUpType? = null,
    val powerUpsRemaining: Map<PowerUpType, Int> = mapOf(
        PowerUpType.SNIP to 3,
        PowerUpType.GHOST to 3,
        PowerUpType.MAGNET to 2,
        PowerUpType.RECALL to 3
    ),
    val impactSparks: List<ImpactSpark> = emptyList(),
    val softDustParticles: List<SoftDustParticle> = emptyList(),
    val shockwaves: List<ShockwaveRing> = emptyList(),
    val moveHistory: List<MoveHistoryState> = emptyList()
)

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = application.getSharedPreferences("amaze_go_prefs", Context.MODE_PRIVATE)
    val soundManager = SoundManager(application)
    private val random = Random()

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var levelStartTime: Long = System.currentTimeMillis()
    private var collisionResetJob: Job? = null
    private var hintResetJob: Job? = null
    private var comboResetJob: Job? = null
    private var comboRewardResetJob: Job? = null
    private var sessionTimerJob: Job? = null
    private var lastClearTimestamp: Long = 0L

    init {
        loadPersistedState()
        loadLevel(_uiState.value.currentLevelNumber)
        startSessionTimer()
    }

    private fun startSessionTimer() {
        sessionTimerJob?.cancel()
        sessionTimerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _uiState.update { it.copy(sessionSeconds = it.sessionSeconds + 1) }
            }
        }
    }

    private fun loadPersistedState() {
        val currentLvl = prefs.getInt("current_level", 1)
        val highest = prefs.getInt("highest_level", 1)
        val movementSound = prefs.getBoolean("movement_sound_enabled", prefs.getBoolean("sound_enabled", true))
        val ambientSound = prefs.getBoolean("ambient_nature_enabled", true)
        val haptic = prefs.getBoolean("haptic_enabled", true)
        val hints = prefs.getInt("hints_count", 3)
        val themeId = prefs.getString("selected_theme", GameThemes.EyeComfort.id)

        val theme = GameThemes.allThemes.find { it.id == themeId } ?: GameThemes.EyeComfort

        // Load all saved star ratings for completed levels
        val starsMap = mutableMapOf<Int, Int>()
        for (lvl in 1..highest) {
            val s = prefs.getInt("stars_lvl_$lvl", 0)
            if (s > 0) starsMap[lvl] = s
        }

        soundManager.isMovementSoundEnabled = movementSound
        soundManager.isAmbientNatureEnabled = ambientSound
        soundManager.isHapticEnabled = haptic

        if (ambientSound) {
            soundManager.startAmbientNature()
        }

        _uiState.update {
            it.copy(
                currentLevelNumber = currentLvl,
                highestUnlockedLevel = maxOf(highest, currentLvl),
                completedLevelsStars = starsMap,
                soundEnabled = movementSound,
                movementSoundEnabled = movementSound,
                ambientNatureEnabled = ambientSound,
                hapticEnabled = haptic,
                hintsRemaining = hints,
                selectedTheme = theme
            )
        }
    }

    /**
     * Checks whether a specific level number is unlocked.
     */
    fun isLevelUnlocked(levelNumber: Int): Boolean {
        return levelNumber <= _uiState.value.highestUnlockedLevel
    }

    /**
     * Unlocks subsequent puzzles up to the target level number and persists the unlocked state.
     */
    fun unlockLevel(levelNumber: Int) {
        val newHighest = maxOf(_uiState.value.highestUnlockedLevel, levelNumber)
        prefs.edit().putInt("highest_level", newHighest).apply()
        _uiState.update { it.copy(highestUnlockedLevel = newHighest) }
    }

    /**
     * Unlocks the immediate next puzzle.
     */
    fun unlockNextLevel() {
        val nextLevel = _uiState.value.currentLevelNumber + 1
        unlockLevel(nextLevel)
    }

    /**
     * Resets all progress back to Level 1.
     */
    fun resetLevelProgress() {
        prefs.edit()
            .putInt("current_level", 1)
            .putInt("highest_level", 1)
            .apply()
        _uiState.update {
            it.copy(
                highestUnlockedLevel = 1,
                completedLevelsStars = emptyMap()
            )
        }
        loadLevel(1)
    }

    /**
     * Resets the entire grid state and interactive mechanics for the current level.
     */
    fun resetGridState() {
        collisionResetJob?.cancel()
        hintResetJob?.cancel()
        comboResetJob?.cancel()
        comboRewardResetJob?.cancel()

        val state = _uiState.value
        val freshLevel = LevelRepository.getLevel(state.currentLevelNumber)

        // Ensure baseline power-up reserves for every puzzle
        val currentPowers = state.powerUpsRemaining
        val guaranteedPowers = mapOf(
            PowerUpType.SNIP to maxOf(currentPowers[PowerUpType.SNIP] ?: 0, 3),
            PowerUpType.GHOST to maxOf(currentPowers[PowerUpType.GHOST] ?: 0, 3),
            PowerUpType.MAGNET to maxOf(currentPowers[PowerUpType.MAGNET] ?: 0, 2),
            PowerUpType.RECALL to maxOf(currentPowers[PowerUpType.RECALL] ?: 0, 3)
        )

        _uiState.update {
            it.copy(
                levelData = freshLevel,
                activeArrows = freshLevel.arrows,
                flyingArrows = emptyList(),
                remainingDrops = freshLevel.maxDrops,
                maxDrops = freshLevel.maxDrops,
                movesCount = 0,
                hintArrowId = null,
                guidanceArrowId = null,
                collisionInfo = null,
                isLevelCompleted = false,
                isLevelFailed = false,
                comboMultiplier = 1,
                showComboBanner = false,
                comboReward = null,
                activePowerUp = null,
                powerUpsRemaining = guaranteedPowers,
                impactSparks = emptyList(),
                softDustParticles = emptyList(),
                shockwaves = emptyList(),
                moveHistory = emptyList()
            )
        }
    }

    /**
     * Loads a specific level, unlocks it if accessed, resets the grid state, and persists the active level.
     */
    fun loadLevel(levelNumber: Int) {
        val safeLevel = maxOf(1, levelNumber)
        val level = LevelRepository.getLevel(safeLevel)
        levelStartTime = System.currentTimeMillis()

        // Track and persist the current level
        prefs.edit().putInt("current_level", safeLevel).apply()

        // Auto-unlock the level if navigated to
        if (safeLevel > _uiState.value.highestUnlockedLevel) {
            unlockLevel(safeLevel)
        }

        collisionResetJob?.cancel()
        hintResetJob?.cancel()
        comboResetJob?.cancel()
        comboRewardResetJob?.cancel()

        val currentPowers = _uiState.value.powerUpsRemaining
        val guaranteedPowers = mapOf(
            PowerUpType.SNIP to maxOf(currentPowers[PowerUpType.SNIP] ?: 0, 3),
            PowerUpType.GHOST to maxOf(currentPowers[PowerUpType.GHOST] ?: 0, 3),
            PowerUpType.MAGNET to maxOf(currentPowers[PowerUpType.MAGNET] ?: 0, 2),
            PowerUpType.RECALL to maxOf(currentPowers[PowerUpType.RECALL] ?: 0, 3)
        )

        _uiState.update {
            it.copy(
                currentLevelNumber = safeLevel,
                levelData = level,
                activeArrows = level.arrows,
                flyingArrows = emptyList(),
                remainingDrops = level.maxDrops,
                maxDrops = level.maxDrops,
                movesCount = 0,
                hintArrowId = null,
                guidanceArrowId = null,
                collisionInfo = null,
                isLevelCompleted = false,
                isLevelFailed = false,
                isLevelSelectOpen = false,
                comboMultiplier = 1,
                showComboBanner = false,
                comboReward = null,
                activePowerUp = null,
                powerUpsRemaining = guaranteedPowers,
                impactSparks = emptyList(),
                softDustParticles = emptyList(),
                shockwaves = emptyList(),
                moveHistory = emptyList()
            )
        }
    }

    /**
     * Restarts the current level with a clean grid reset.
     */
    fun restartCurrentLevel() {
        loadLevel(_uiState.value.currentLevelNumber)
    }

    /**
     * Advances to and unlocks the next subsequent puzzle, resetting the grid state.
     */
    fun nextLevel() {
        val next = _uiState.value.currentLevelNumber + 1
        unlockLevel(next)
        loadLevel(next)
    }

    /**
     * Handles tap on an arrow.
     */
    fun onArrowTapped(arrow: ArrowItem) {
        val state = _uiState.value
        if (state.isLevelCompleted || state.isLevelFailed) return
        if (state.flyingArrows.any { it.arrow.id == arrow.id }) return

        // Check if an active targeting power-up is waiting for an arrow
        when (state.activePowerUp) {
            PowerUpType.SNIP -> {
                applySnipPowerUp(arrow)
                return
            }
            PowerUpType.GHOST -> {
                applyGhostPowerUp(arrow)
                return
            }
            else -> {}
        }

        // Clear any guidance or hint active on tap
        _uiState.update { it.copy(guidanceArrowId = null) }

        // Save history state before executing move for Recall
        pushMoveHistory()

        // Check if collision occurs
        val collision = PuzzleSolver.checkCollision(
            arrow = arrow,
            activeArrows = state.activeArrows,
            gridWidth = state.levelData.gridWidth,
            gridHeight = state.levelData.gridHeight
        )

        if (collision != null) {
            handleArrowBlocked(arrow, collision)
        } else {
            handleArrowClear(arrow)
        }
    }

    private fun pushMoveHistory() {
        val state = _uiState.value
        val historyEntry = MoveHistoryState(
            activeArrows = state.activeArrows,
            remainingDrops = state.remainingDrops,
            movesCount = state.movesCount
        )
        _uiState.update {
            it.copy(moveHistory = (it.moveHistory + historyEntry).takeLast(10))
        }
    }

    private fun handleArrowBlocked(arrow: ArrowItem, collision: CollisionInfo) {
        soundManager.playBlocked()

        val currentDrops = _uiState.value.remainingDrops - 1
        val isFailed = currentDrops <= 0

        // Reset combo streak on collision and trigger screen shake for tactile feedback
        _uiState.update {
            it.copy(
                collisionInfo = collision,
                remainingDrops = maxOf(0, currentDrops),
                isLevelFailed = isFailed,
                comboMultiplier = 1,
                showComboBanner = false,
                shakeTrigger = System.currentTimeMillis()
            )
        }

        if (!isFailed) {
            soundManager.playDropLost()
        }

        collisionResetJob?.cancel()
        collisionResetJob = viewModelScope.launch {
            delay(500)
            _uiState.update { it.copy(collisionInfo = null) }
        }
    }

    private fun handleArrowClear(arrow: ArrowItem) {
        val now = System.currentTimeMillis()
        val timeSinceLast = now - lastClearTimestamp
        lastClearTimestamp = now

        val newCombo = if (timeSinceLast < 2200L && _uiState.value.movesCount > 0) {
            minOf(_uiState.value.comboMultiplier + 1, 8)
        } else {
            1
        }

        soundManager.playWhoosh(newCombo)

        val state = _uiState.value
        val remainingActive = state.activeArrows.filter { it.id != arrow.id }
        val newMoves = state.movesCount + 1

        val exitDist = maxOf(state.levelData.gridWidth, state.levelData.gridHeight) + 6
        val targetOffset = GridPoint(
            arrow.headDirection.dx * exitDist,
            arrow.headDirection.dy * exitDist
        )

        val flying = FlyingArrow(
            arrow = arrow,
            progress = 0f,
            targetOffset = targetOffset
        )

        // Award Free Power-Ups on Combos (2x -> Recall, 3x -> Ghost, 4x -> Snip, 5x+ -> Magnet)
        var updatedPowers = state.powerUpsRemaining.toMutableMap()
        var newComboReward: ComboRewardEvent? = null

        if (newCombo >= 2) {
            val rewardType = when (newCombo) {
                2 -> PowerUpType.RECALL
                3 -> PowerUpType.GHOST
                4 -> PowerUpType.SNIP
                else -> PowerUpType.MAGNET
            }
            val curCount = updatedPowers[rewardType] ?: 0
            updatedPowers[rewardType] = curCount + 1
            newComboReward = ComboRewardEvent(
                combo = newCombo,
                powerUpType = rewardType,
                message = "${newCombo}x Flow! +1 ${rewardType.title}"
            )
        }

        // Generate soft ASMR dust particles along the arrow line and arrowhead
        val newDustParticles = generateSoftDustParticles(arrow, state.selectedTheme)

        _uiState.update {
            it.copy(
                activeArrows = remainingActive,
                flyingArrows = it.flyingArrows + flying,
                movesCount = newMoves,
                comboMultiplier = newCombo,
                showComboBanner = newCombo >= 2,
                comboReward = newComboReward ?: it.comboReward,
                powerUpsRemaining = updatedPowers,
                softDustParticles = (it.softDustParticles.filter { p -> now - p.createdAt < p.maxAgeMs } + newDustParticles).takeLast(45),
                hintArrowId = if (it.hintArrowId == arrow.id) null else it.hintArrowId
            )
        }

        // Reset combo display and combo reward notification
        comboResetJob?.cancel()
        comboResetJob = viewModelScope.launch {
            delay(2400)
            _uiState.update { it.copy(showComboBanner = false, comboMultiplier = 1) }
        }

        if (newComboReward != null) {
            comboRewardResetJob?.cancel()
            comboRewardResetJob = viewModelScope.launch {
                delay(2200)
                _uiState.update { it.copy(comboReward = null) }
            }
        }

        // Animate flying arrow off screen along its serpentine track
        viewModelScope.launch {
            val steps = 18
            for (step in 1..steps) {
                delay(16)
                val progress = step.toFloat() / steps
                _uiState.update { current ->
                    current.copy(
                        flyingArrows = current.flyingArrows.map {
                            if (it.arrow.id == arrow.id) it.copy(progress = progress) else it
                        }
                    )
                }
            }

            _uiState.update { current ->
                current.copy(
                    flyingArrows = current.flyingArrows.filter { it.arrow.id != arrow.id }
                )
            }

            if (remainingActive.isEmpty()) {
                handleLevelCompleted()
            }
        }
    }

    private fun generateSoftDustParticles(arrow: ArrowItem, theme: GameTheme): List<SoftDustParticle> {
        val particles = ArrayList<SoftDustParticle>()
        val basePoint = arrow.points.last()
        val tailPoint = arrow.points.first()
        val palette = listOf(
            theme.dropActiveColor,
            theme.headerGold,
            Color.White,
            theme.arrowStroke.copy(alpha = 0.8f)
        )

        for (i in 0 until 14) {
            val isHead = i % 2 == 0
            val refPoint = if (isHead) basePoint else tailPoint
            val angle = random.nextDouble() * 2.0 * Math.PI
            val speed = 25f + random.nextFloat() * 65f
            val vx = (kotlin.math.cos(angle) * speed).toFloat()
            val vy = (kotlin.math.sin(angle) * speed).toFloat()
            val radius = 3.5f + random.nextFloat() * 5.5f
            val color = palette[random.nextInt(palette.size)]

            particles.add(
                SoftDustParticle(
                    id = System.nanoTime() + i,
                    origin = Offset(refPoint.x.toFloat(), refPoint.y.toFloat()),
                    velocity = Offset(vx, vy),
                    radius = radius,
                    color = color,
                    maxAgeMs = 550L + random.nextInt(250)
                )
            )
        }
        return particles
    }

    private fun handleLevelCompleted() {
        val state = _uiState.value
        val timeSeconds = (System.currentTimeMillis() - levelStartTime) / 1000
        val stars = when {
            state.remainingDrops == state.maxDrops -> 3
            state.remainingDrops >= state.maxDrops - 1 -> 2
            else -> 1
        }

        val newHighest = maxOf(state.highestUnlockedLevel, state.currentLevelNumber + 1)
        val updatedStars = state.completedLevelsStars.toMutableMap()
        updatedStars[state.currentLevelNumber] = maxOf(updatedStars[state.currentLevelNumber] ?: 0, stars)

        prefs.edit()
            .putInt("highest_level", newHighest)
            .putInt("stars_lvl_${state.currentLevelNumber}", stars)
            .apply()

        soundManager.playLevelComplete()

        _uiState.update {
            it.copy(
                isLevelCompleted = true,
                completionStars = stars,
                completionTimeSeconds = timeSeconds,
                highestUnlockedLevel = newHighest,
                completedLevelsStars = updatedStars,
                showComboBanner = false
            )
        }
    }

    fun onArrowLongPressed(arrow: ArrowItem) {
        val state = _uiState.value
        if (state.isLevelCompleted || state.isLevelFailed) return

        soundManager.playTap()
        _uiState.update {
            it.copy(
                guidanceArrowId = if (it.guidanceArrowId == arrow.id) null else arrow.id,
                collisionInfo = null
            )
        }
    }

    fun onPowerUpSelected(powerUp: PowerUpType) {
        val state = _uiState.value
        if (state.isLevelCompleted || state.isLevelFailed) return

        val count = state.powerUpsRemaining[powerUp] ?: 0
        if (count <= 0) {
            // Replenish with bonus charges
            val updated = state.powerUpsRemaining.toMutableMap()
            updated[powerUp] = 2
            _uiState.update { it.copy(powerUpsRemaining = updated) }
        }

        when (powerUp) {
            PowerUpType.MAGNET -> {
                useMagnetPowerUp()
            }
            PowerUpType.RECALL -> {
                useRecallPowerUp()
            }
            PowerUpType.SNIP, PowerUpType.GHOST -> {
                // Toggle mode or activate
                _uiState.update {
                    it.copy(activePowerUp = if (it.activePowerUp == powerUp) null else powerUp)
                }
                soundManager.playTap()
            }
        }
    }

    private fun applySnipPowerUp(targetArrow: ArrowItem) {
        val state = _uiState.value
        val nextId = (state.activeArrows.maxOfOrNull { it.id } ?: 0) + 10
        val split = PuzzleSolver.splitArrow(targetArrow, nextId)

        if (split != null) {
            soundManager.playSnip()
            pushMoveHistory()

            val updatedArrows = state.activeArrows.mapNotNull {
                if (it.id == targetArrow.id) null else it
            } + split

            val count = (state.powerUpsRemaining[PowerUpType.SNIP] ?: 1) - 1
            val updatedPowers = state.powerUpsRemaining.toMutableMap()
            updatedPowers[PowerUpType.SNIP] = maxOf(0, count)

            _uiState.update {
                it.copy(
                    activeArrows = updatedArrows,
                    activePowerUp = null,
                    powerUpsRemaining = updatedPowers
                )
            }
        } else {
            // Cannot split short arrow - cancel powerup
            _uiState.update { it.copy(activePowerUp = null) }
        }
    }

    private fun applyGhostPowerUp(targetArrow: ArrowItem) {
        soundManager.playGhostPhase()
        pushMoveHistory()

        val ghostArrow = targetArrow.copy(isGhost = true)
        val updatedArrows = _uiState.value.activeArrows.map {
            if (it.id == targetArrow.id) ghostArrow else it
        }

        val count = (_uiState.value.powerUpsRemaining[PowerUpType.GHOST] ?: 1) - 1
        val updatedPowers = _uiState.value.powerUpsRemaining.toMutableMap()
        updatedPowers[PowerUpType.GHOST] = maxOf(0, count)

        _uiState.update {
            it.copy(
                activeArrows = updatedArrows,
                activePowerUp = null,
                powerUpsRemaining = updatedPowers
            )
        }

        // Auto-clear the ethereal arrow
        handleArrowClear(ghostArrow)
    }

    private fun useMagnetPowerUp() {
        val state = _uiState.value
        val unblocked = PuzzleSolver.findUnblockedArrows(
            state.activeArrows,
            state.levelData.gridWidth,
            state.levelData.gridHeight
        )

        if (unblocked.isNotEmpty()) {
            soundManager.playMagnetPulse()
            pushMoveHistory()

            val count = (state.powerUpsRemaining[PowerUpType.MAGNET] ?: 1) - 1
            val updatedPowers = state.powerUpsRemaining.toMutableMap()
            updatedPowers[PowerUpType.MAGNET] = maxOf(0, count)

            _uiState.update {
                it.copy(
                    activePowerUp = null,
                    powerUpsRemaining = updatedPowers
                )
            }

            // Staggered cascade clearance
            viewModelScope.launch {
                for (arrow in unblocked) {
                    if (_uiState.value.activeArrows.any { it.id == arrow.id }) {
                        handleArrowClear(arrow)
                        delay(90)
                    }
                }
            }
        }
    }

    private fun useRecallPowerUp() {
        val state = _uiState.value
        if (state.moveHistory.isNotEmpty()) {
            soundManager.playRecall()

            val lastState = state.moveHistory.last()
            val newHistory = state.moveHistory.dropLast(1)

            val count = (state.powerUpsRemaining[PowerUpType.RECALL] ?: 1) - 1
            val updatedPowers = state.powerUpsRemaining.toMutableMap()
            updatedPowers[PowerUpType.RECALL] = maxOf(0, count)

            _uiState.update {
                it.copy(
                    activeArrows = lastState.activeArrows,
                    remainingDrops = maxOf(it.remainingDrops, lastState.remainingDrops),
                    movesCount = lastState.movesCount,
                    moveHistory = newHistory,
                    isLevelFailed = false,
                    collisionInfo = null,
                    activePowerUp = null,
                    powerUpsRemaining = updatedPowers
                )
            }
        }
    }

    fun useHint() {
        val state = _uiState.value
        if (state.isLevelCompleted || state.isLevelFailed) return
        if (state.hintsRemaining <= 0) {
            _uiState.update { it.copy(hintsRemaining = 3) }
            prefs.edit().putInt("hints_count", 3).apply()
        }

        val hintArrow = PuzzleSolver.getHintArrow(
            activeArrows = state.activeArrows,
            gridWidth = state.levelData.gridWidth,
            gridHeight = state.levelData.gridHeight
        )

        if (hintArrow != null) {
            soundManager.playHint()
            val newHints = maxOf(0, _uiState.value.hintsRemaining - 1)
            prefs.edit().putInt("hints_count", newHints).apply()

            _uiState.update {
                it.copy(
                    hintArrowId = hintArrow.id,
                    hintsRemaining = newHints
                )
            }

            hintResetJob?.cancel()
            hintResetJob = viewModelScope.launch {
                delay(6000)
                _uiState.update { it.copy(hintArrowId = null) }
            }
        }
    }

    fun reviveWithDrops(dropsCount: Int = 3) {
        _uiState.update {
            it.copy(
                remainingDrops = dropsCount,
                isLevelFailed = false,
                collisionInfo = null
            )
        }
        soundManager.playHint()
    }

    fun setTheme(theme: GameTheme) {
        prefs.edit().putString("selected_theme", theme.id).apply()
        _uiState.update {
            it.copy(
                selectedTheme = theme,
                isThemeSelectOpen = false
            )
        }
    }

    fun toggleAmbientNature(enabled: Boolean) {
        soundManager.setAmbientNature(enabled)
        prefs.edit().putBoolean("ambient_nature_enabled", enabled).apply()
        _uiState.update { it.copy(ambientNatureEnabled = enabled) }
    }

    fun toggleMovementSound(enabled: Boolean) {
        soundManager.isMovementSoundEnabled = enabled
        prefs.edit().putBoolean("movement_sound_enabled", enabled).putBoolean("sound_enabled", enabled).apply()
        _uiState.update { it.copy(movementSoundEnabled = enabled, soundEnabled = enabled) }
    }

    fun toggleSound(enabled: Boolean) {
        toggleMovementSound(enabled)
    }

    fun toggleHaptic(enabled: Boolean) {
        soundManager.isHapticEnabled = enabled
        prefs.edit().putBoolean("haptic_enabled", enabled).apply()
        _uiState.update { it.copy(hapticEnabled = enabled) }
    }

    fun openLevelSelect(open: Boolean) {
        _uiState.update { it.copy(isLevelSelectOpen = open) }
    }

    fun openThemeSelect(open: Boolean) {
        _uiState.update { it.copy(isThemeSelectOpen = open) }
    }

    fun openSettings(open: Boolean) {
        _uiState.update { it.copy(isSettingsOpen = open) }
    }

    fun openZenBreathe(open: Boolean) {
        _uiState.update { it.copy(isZenBreatheOpen = open) }
    }
}
