import re

with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "r") as f:
    content = f.read()

# Add isBetaCompletedOpen
if "isBetaCompletedOpen: Boolean = false" not in content:
    content = content.replace(
        "val isVipRewardsOpen: Boolean = false,",
        "val isVipRewardsOpen: Boolean = false,\n    val isBetaCompletedOpen: Boolean = false,"
    )

# Add completionHandled
if "private var completionHandled = false" not in content:
    content = content.replace(
        "private var lastClearTimestamp: Long = 0L",
        "private var lastClearTimestamp: Long = 0L\n    private var completionHandled = false"
    )

# loadLevel replacement
load_level_start = content.find("fun loadLevel(levelNumber: Int) {")
if load_level_start != -1:
    load_level_end = content.find("fun nextLevel() {", load_level_start)
    if load_level_end != -1:
        new_load_level = """fun loadLevel(levelNumber: Int) {
        val safeLevel = maxOf(1, levelNumber)
        
        // Prevent loading locked levels normally
        if (safeLevel > _uiState.value.highestUnlockedLevel) {
            return
        }

        val level = LevelRepository.getLevel(safeLevel)
        levelStartTime = System.currentTimeMillis()
        completionHandled = false

        // Track and persist the current level
        prefs.edit().putInt("current_level", safeLevel).apply()

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
                powerUpsRemaining = guaranteedPowers,
                impactSparks = emptyList(),
                softDustParticles = emptyList(),
                shockwaves = emptyList(),
                moveHistory = emptyList()
            )
        }
    }

    """
        content = content[:load_level_start] + new_load_level + content[load_level_end:]

# nextLevel replacement
next_level_start = content.find("fun nextLevel() {")
if next_level_start != -1:
    next_level_end = content.find("fun onArrowTapped", next_level_start)
    
    # We need to preserve the Javadoc for onArrowTapped if it exists, let's just find the closing brace of nextLevel
    end_brace = content.find("}", next_level_start)
    if end_brace != -1:
        new_next_level = """fun nextLevel() {
        if (_uiState.value.currentLevelNumber >= 200) {
            _uiState.update { it.copy(isLevelCompleted = false, isBetaCompletedOpen = true) }
            return
        }
        val next = _uiState.value.currentLevelNumber + 1
        unlockLevel(next)
        loadLevel(next)
    }
    
    fun closeBetaCompleted() {
        _uiState.update { it.copy(isBetaCompletedOpen = false, isMainMenuActive = true) }
    }"""
        # Let's replace just the old nextLevel logic
        old_next = """fun nextLevel() {
        val next = _uiState.value.currentLevelNumber + 1
        unlockLevel(next)
        loadLevel(next)
    }"""
        content = content.replace(old_next, new_next_level)

# onArrowTapped replacement
on_arrow_tapped_start = content.find("fun onArrowTapped(arrow: ArrowItem) {")
if on_arrow_tapped_start != -1:
    old_on_arrow_tapped = """fun onArrowTapped(arrow: ArrowItem) {
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
    }"""
    
    new_on_arrow_tapped = """fun onArrowTapped(arrow: ArrowItem) {
        val state = _uiState.value
        if (state.isLevelCompleted || state.isLevelFailed) return
        
        val currentArrow = state.activeArrows.firstOrNull { it.id == arrow.id } ?: return
        if (state.flyingArrows.any { it.arrow.id == currentArrow.id }) return

        // Check if an active targeting power-up is waiting for an arrow
        when (state.activePowerUp) {
            PowerUpType.SNIP -> {
                applySnipPowerUp(currentArrow)
                return
            }
            PowerUpType.GHOST -> {
                applyGhostPowerUp(currentArrow)
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
            arrow = currentArrow,
            activeArrows = state.activeArrows,
            gridWidth = state.levelData.gridWidth,
            gridHeight = state.levelData.gridHeight
        )

        if (collision != null) {
            handleArrowBlocked(currentArrow, collision)
        } else {
            handleArrowClear(currentArrow)
        }
    }"""
    content = content.replace(old_on_arrow_tapped, new_on_arrow_tapped)

# handleArrowClear replacement
# We want to replace the animation and completion part
handle_clear_find = """        // Animate flying arrow off screen along its serpentine track
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
        }"""
        
handle_clear_replace = """        // Animate flying arrow off screen along its serpentine track
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
                val newFlying = current.flyingArrows.filter { it.arrow.id != arrow.id }
                
                // Authoritative check after flight completes
                if (current.activeArrows.isEmpty() && newFlying.isEmpty() && !current.isLevelCompleted && !current.isLevelFailed) {
                    if (!completionHandled) {
                        completionHandled = true
                        handleLevelCompleted()
                    }
                }
                
                current.copy(flyingArrows = newFlying)
            }
        }"""

content = content.replace(handle_clear_find, handle_clear_replace)

with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "w") as f:
    f.write(content)
