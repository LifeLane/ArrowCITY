import re

with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "r") as f:
    content = f.read()

find = """        prefs.edit()
            .putInt("highest_level", newHighest)
            .putInt("stars_lvl_${state.currentLevelNumber}", stars)
            .putInt("puzzles_solved", newSolvedCount)
            .putInt("best_combo", newBestCombo)
            .apply()"""
            
replace = """        prefs.edit()
            .putInt("puzzles_solved", newSolvedCount)
            .apply()
            
        viewModelScope.launch {
            repository.saveProgress(com.example.data.LevelProgress(
                levelNumber = state.currentLevelNumber,
                isCompleted = true,
                stars = stars,
                bestCombo = newBestCombo
            ))
        }"""

if find in content:
    content = content.replace(find, replace)
else:
    print("Not found prefs")

with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "w") as f:
    f.write(content)
