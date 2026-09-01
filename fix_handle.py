with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "r") as f:
    content = f.read()

bad = """                                current.copy(flyingArrows = newFlying)
            }
        }
    private fun generateSoftDustParticles"""

good = """                                current.copy(flyingArrows = newFlying)
            }
        }
    }
    private fun generateSoftDustParticles"""

content = content.replace(bad, good)

with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "w") as f:
    f.write(content)
