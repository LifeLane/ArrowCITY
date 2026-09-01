import re

with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "r") as f:
    content = f.read()

# Modify handleLevelCompleted
find_handle_complete = """        // Check if combo streak is best
        val currentBest = prefs.getInt("best_combo", 1)
        if (state.comboMultiplier > currentBest) {
            prefs.edit().putInt("best_combo", state.comboMultiplier).apply()
            _uiState.update { it.copy(bestComboStreak = state.comboMultiplier) }
        }

        // Save stars if better
        val previousStars = prefs.getInt("stars_lvl_${state.currentLevelNumber}", 0)
        if (stars > previousStars) {
            prefs.edit().putInt("stars_lvl_${state.currentLevelNumber}", stars).apply()
            _uiState.update { 
                val newStarsMap = it.completedLevelsStars.toMutableMap()
                newStarsMap[state.currentLevelNumber] = stars
                it.copy(completedLevelsStars = newStarsMap)
            }
        }
        
        // Ensure level is unlocked
        if (state.currentLevelNumber >= state.highestUnlockedLevel) {
            unlockNextLevel()
        }"""

replace_handle_complete = """        // Check if combo streak is best
        val currentBest = state.bestComboStreak
        val newBest = maxOf(currentBest, state.comboMultiplier)
        if (newBest > currentBest) {
            _uiState.update { it.copy(bestComboStreak = newBest) }
        }

        val previousStars = state.completedLevelsStars[state.currentLevelNumber] ?: 0
        val finalStars = maxOf(stars, previousStars)
        if (finalStars > previousStars) {
            _uiState.update { 
                val newStarsMap = it.completedLevelsStars.toMutableMap()
                newStarsMap[state.currentLevelNumber] = finalStars
                it.copy(completedLevelsStars = newStarsMap)
            }
        }
        
        // Save to Room
        viewModelScope.launch {
            repository.saveProgress(com.example.data.LevelProgress(
                levelNumber = state.currentLevelNumber,
                isCompleted = true,
                stars = finalStars,
                bestCombo = newBest
            ))
        }
        
        // Ensure level is unlocked
        if (state.currentLevelNumber >= state.highestUnlockedLevel) {
            unlockNextLevel()
        }"""

if find_handle_complete in content:
    content = content.replace(find_handle_complete, replace_handle_complete)
else:
    print("Could not find handleLevelCompleted block")
    
# Modify unlockLevel
find_unlock_level = """    fun unlockLevel(levelNumber: Int) {
        val newHighest = maxOf(_uiState.value.highestUnlockedLevel, levelNumber)
        prefs.edit().putInt("highest_level", newHighest).apply()
        _uiState.update { it.copy(highestUnlockedLevel = newHighest) }
    }"""

replace_unlock_level = """    fun unlockLevel(levelNumber: Int) {
        val newHighest = maxOf(_uiState.value.highestUnlockedLevel, levelNumber)
        _uiState.update { it.copy(highestUnlockedLevel = newHighest) }
        // No need to persist "highest_level" separately in Room, 
        // as the highest completed level determines it on load.
    }"""

if find_unlock_level in content:
    content = content.replace(find_unlock_level, replace_unlock_level)
else:
    print("Could not find unlockLevel block")

with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "w") as f:
    f.write(content)
