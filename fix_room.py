import re

with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "r") as f:
    content = f.read()

# 1. Add repository and database variables
if "private val database =" not in content:
    init_vars = """    private val prefs = application.getSharedPreferences("amaze_go_prefs", Context.MODE_PRIVATE)
    private val database = com.example.data.AppDatabase.getDatabase(application)
    private val repository = com.example.data.GameRepository(database.levelProgressDao())"""
    content = content.replace('    private val prefs = application.getSharedPreferences("amaze_go_prefs", Context.MODE_PRIVATE)', init_vars)

# 2. Modify loadPersistedState to migrate and use Room
old_load = """    private fun loadPersistedState() {
        val currentLvl = prefs.getInt("current_level", 1)
        val highest = prefs.getInt("highest_level", 1)
        val movementSound = prefs.getBoolean("movement_sound_enabled", prefs.getBoolean("sound_enabled", true))
        val ambientSound = prefs.getBoolean("ambient_nature_enabled", true)
        val haptic = prefs.getBoolean("haptic_enabled", true)
        val hints = prefs.getInt("hints_count", 3)
        val themeId = prefs.getString("selected_theme", GameThemes.EyeComfort.id)
        val arrowSkinId = prefs.getString("equipped_arrow_skin", ArrowSkinType.DEFAULT.id)
        val boardCanvasId = prefs.getString("equipped_board_canvas", BoardCanvasType.DEFAULT.id)
        val gridStyleId = prefs.getString("equipped_grid_style", MazeGridStyle.DEFAULT.id)
        val bgAnimId = prefs.getString("equipped_bg_anim", BackgroundAnimType.DEFAULT.id)
        val theme = GameThemes.allThemes.find { it.id == themeId } ?: GameThemes.EyeComfort
        val arrowSkin = ArrowSkinType.entries.find { it.id == arrowSkinId } ?: ArrowSkinType.DEFAULT
        val boardCanvas = BoardCanvasType.entries.find { it.id == boardCanvasId } ?: BoardCanvasType.DEFAULT
        val gridStyle = MazeGridStyle.entries.find { it.id == gridStyleId } ?: MazeGridStyle.DEFAULT
        val bgAnim = BackgroundAnimType.entries.find { it.id == bgAnimId } ?: BackgroundAnimType.DEFAULT
        val solvedCount = prefs.getInt("puzzles_solved", 0)
        val bestCombo = prefs.getInt("best_combo", 1)
        val powerupsUsed = prefs.getInt("powerups_used", 0)
        val streak = prefs.getInt("daily_streak", 1)
        val trail = prefs.getString("particle_trail", "stardust") ?: "stardust"

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
                totalPuzzlesSolved = if (solvedCount > 0) solvedCount else starsMap.size,
                bestComboStreak = bestCombo,
                totalPowerUpsUsed = powerupsUsed,
                dailyStreak = streak,
                selectedParticleTrail = trail,
                soundEnabled = movementSound,
                movementSoundEnabled = movementSound,
                ambientNatureEnabled = ambientSound,
                hapticEnabled = haptic,
                hintsRemaining = hints,
                selectedTheme = theme,
                equippedArrowSkin = arrowSkin,
                equippedBoardCanvas = boardCanvas,                
                equippedGridStyle = gridStyle,
                equippedBackgroundAnim = bgAnim
            )
        }
    }"""

new_load = """    private fun loadPersistedState() {
        val currentLvl = prefs.getInt("current_level", 1)
        val movementSound = prefs.getBoolean("movement_sound_enabled", prefs.getBoolean("sound_enabled", true))
        val ambientSound = prefs.getBoolean("ambient_nature_enabled", true)
        val haptic = prefs.getBoolean("haptic_enabled", true)
        val hints = prefs.getInt("hints_count", 3)
        val themeId = prefs.getString("selected_theme", GameThemes.EyeComfort.id)
        val arrowSkinId = prefs.getString("equipped_arrow_skin", ArrowSkinType.DEFAULT.id)
        val boardCanvasId = prefs.getString("equipped_board_canvas", BoardCanvasType.DEFAULT.id)
        val gridStyleId = prefs.getString("equipped_grid_style", MazeGridStyle.DEFAULT.id)
        val bgAnimId = prefs.getString("equipped_bg_anim", BackgroundAnimType.DEFAULT.id)
        
        val theme = GameThemes.allThemes.find { it.id == themeId } ?: GameThemes.EyeComfort
        val arrowSkin = ArrowSkinType.entries.find { it.id == arrowSkinId } ?: ArrowSkinType.DEFAULT
        val boardCanvas = BoardCanvasType.entries.find { it.id == boardCanvasId } ?: BoardCanvasType.DEFAULT
        val gridStyle = MazeGridStyle.entries.find { it.id == gridStyleId } ?: MazeGridStyle.DEFAULT
        val bgAnim = BackgroundAnimType.entries.find { it.id == bgAnimId } ?: BackgroundAnimType.DEFAULT
        
        val powerupsUsed = prefs.getInt("powerups_used", 0)
        val streak = prefs.getInt("daily_streak", 1)
        val trail = prefs.getString("particle_trail", "stardust") ?: "stardust"

        soundManager.isMovementSoundEnabled = movementSound
        soundManager.isAmbientNatureEnabled = ambientSound
        soundManager.isHapticEnabled = haptic
        if (ambientSound) {
            soundManager.startAmbientNature()
        }

        viewModelScope.launch {
            var progressList = repository.getAllProgress()
            
            // Migration from SharedPreferences if Room is empty
            if (progressList.isEmpty()) {
                val highest = prefs.getInt("highest_level", 1)
                val bestCombo = prefs.getInt("best_combo", 1)
                for (lvl in 1..highest) {
                    val s = prefs.getInt("stars_lvl_$lvl", 0)
                    if (s > 0) {
                        repository.saveProgress(com.example.data.LevelProgress(
                            levelNumber = lvl,
                            isCompleted = true,
                            stars = s,
                            bestCombo = bestCombo
                        ))
                    }
                }
                progressList = repository.getAllProgress()
            }
            
            val starsMap = mutableMapOf<Int, Int>()
            var highestRoom = 1
            var bestComboRoom = 1
            var solvedRoom = 0
            
            for (p in progressList) {
                if (p.isCompleted) {
                    highestRoom = maxOf(highestRoom, p.levelNumber + 1)
                    solvedRoom++
                }
                if (p.stars > 0) starsMap[p.levelNumber] = p.stars
                if (p.bestCombo > bestComboRoom) bestComboRoom = p.bestCombo
            }
            
            // Ensure highest Room is clamped up to the actual level and currentLvl
            highestRoom = maxOf(highestRoom, currentLvl)
            
            _uiState.update {
                it.copy(
                    currentLevelNumber = currentLvl,
                    highestUnlockedLevel = highestRoom,
                    completedLevelsStars = starsMap,
                    totalPuzzlesSolved = solvedRoom,
                    bestComboStreak = bestComboRoom,
                    totalPowerUpsUsed = powerupsUsed,
                    dailyStreak = streak,
                    selectedParticleTrail = trail,
                    soundEnabled = movementSound,
                    movementSoundEnabled = movementSound,
                    ambientNatureEnabled = ambientSound,
                    hapticEnabled = haptic,
                    hintsRemaining = hints,
                    selectedTheme = theme,
                    equippedArrowSkin = arrowSkin,
                    equippedBoardCanvas = boardCanvas,                
                    equippedGridStyle = gridStyle,
                    equippedBackgroundAnim = bgAnim
                )
            }
        }
    }"""

# Since old_load might have slight whitespace differences, let's replace by finding the start and end of loadPersistedState
load_start = content.find("private fun loadPersistedState() {")
load_end = content.find("fun isLevelUnlocked(levelNumber: Int): Boolean {", load_start)

if load_start != -1 and load_end != -1:
    # Just to be safe with trailing/leading whitespaces
    content = content[:load_start] + new_load + "\n\n    /**\n     * Checks whether a specific level number is unlocked.\n     */\n    " + content[load_end:]
else:
    print("Could not find loadPersistedState")


with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "w") as f:
    f.write(content)

