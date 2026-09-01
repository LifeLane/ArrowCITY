import re

with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "r") as f:
    content = f.read()

# Add isBetaCompletedOpen
content = re.sub(
    r"val isVipRewardsOpen: Boolean = false,",
    r"val isVipRewardsOpen: Boolean = false,\n    val isBetaCompletedOpen: Boolean = false,",
    content
)

# Add completionHandled
content = re.sub(
    r"private var lastClearTimestamp: Long = 0L",
    r"private var lastClearTimestamp: Long = 0L\n    private var completionHandled = false",
    content
)

# Fix loadLevel
load_level_replacement = """    fun loadLevel(levelNumber: Int) {
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
    }"""

content = re.sub(
    r"    fun loadLevel\(levelNumber: Int\) \{.*?isLevelSelectOpen = false,\n\s*powerUpsRemaining.*?\n\s*\)\n\s*\}\n\s*\}",
    load_level_replacement,
    content,
    flags=re.DOTALL
)

# Fix nextLevel
next_level_replacement = """    fun nextLevel() {
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

content = re.sub(
    r"    fun nextLevel\(\) \{\n        val next = _uiState\.value\.currentLevelNumber \+ 1\n        unlockLevel\(next\)\n        loadLevel\(next\)\n    \}",
    next_level_replacement,
    content
)

# Fix onArrowTapped
on_arrow_tapped_replacement = """    fun onArrowTapped(arrow: ArrowItem) {
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

content = re.sub(
    r"    fun onArrowTapped\(arrow: ArrowItem\) \{.*?    \}",
    on_arrow_tapped_replacement,
    content,
    flags=re.DOTALL,
    count=1
)

# Fix handleArrowClear empty check and add completion Handled
handle_arrow_clear_end_replacement = """        // Animate flying arrow off screen along its serpentine track
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

content = re.sub(
    r"        // Animate flying arrow off screen along its serpentine track.*?        \}",
    handle_arrow_clear_end_replacement,
    content,
    flags=re.DOTALL,
    count=1
)

with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "w") as f:
    f.write(content)
