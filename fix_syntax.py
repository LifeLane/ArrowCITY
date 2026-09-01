import re

with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "r") as f:
    content = f.read()

# Fix the syntax error around generateSoftDustParticles
# We have an extra ) and } there? No, we are missing the closing brace for handleArrowClear.
# In the sed output:
#            }
#        }
#                    )
#                }
#    private fun generateSoftDustParticles

broken_str = """                                current.copy(flyingArrows = newFlying)
            }
        }
                    )
                }"""

fixed_str = """                                current.copy(flyingArrows = newFlying)
            }
        }
    }"""

content = content.replace(broken_str, fixed_str)

# Restore restartCurrentLevel
if "fun restartCurrentLevel" not in content:
    restart_code = """
    fun restartCurrentLevel() {
        val state = _uiState.value
        val level = state.levelData
        
        collisionResetJob?.cancel()
        hintResetJob?.cancel()
        
        _uiState.update {
            it.copy(
                activeArrows = level.arrows,
                flyingArrows = emptyList(),
                remainingDrops = level.maxDrops,
                movesCount = 0,
                hintArrowId = null,
                guidanceArrowId = null,
                collisionInfo = null,
                isLevelCompleted = false,
                isLevelFailed = false,
                isResetConfirmOpen = false,
                impactSparks = emptyList(),
                softDustParticles = emptyList(),
                shockwaves = emptyList(),
                moveHistory = emptyList()
            )
        }
    }
"""
    # Insert it before openLevelSelect
    content = content.replace("fun openLevelSelect", restart_code + "\n    fun openLevelSelect")

with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "w") as f:
    f.write(content)
