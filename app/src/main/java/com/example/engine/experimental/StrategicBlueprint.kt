package com.example.engine.experimental

enum class DifficultyTier {
    EASY, MEDIUM, HARD, DEEP, EXPERT, MASTER
}

data class StrategicBlueprint(
    val tier: DifficultyTier,
    val minDepth: Int,
    val maxDepth: Int,
    val maxTraps: Int,
    val recoveryAllowed: Boolean,
    val minSolutionLength: Int,
    val maxSolutionLength: Int,
    val maxRepetitiveRatio: Float
) {
    companion object {
        val EASY = StrategicBlueprint(
            tier = DifficultyTier.EASY,
            minDepth = 0,
            maxDepth = 1,
            maxTraps = 0,
            recoveryAllowed = true,
            minSolutionLength = 3,
            maxSolutionLength = 15,
            maxRepetitiveRatio = 0.90f
        )
        
        val MEDIUM = StrategicBlueprint(
            tier = DifficultyTier.MEDIUM,
            minDepth = 1,
            maxDepth = 2,
            maxTraps = 2,
            recoveryAllowed = true,
            minSolutionLength = 10,
            maxSolutionLength = 25,
            maxRepetitiveRatio = 0.85f
        )
        
        val HARD = StrategicBlueprint(
            tier = DifficultyTier.HARD,
            minDepth = 2,
            maxDepth = 3,
            maxTraps = 3,
            recoveryAllowed = false,
            minSolutionLength = 15,
            maxSolutionLength = 35,
            maxRepetitiveRatio = 0.80f
        )
        
        val DEEP = StrategicBlueprint(
            tier = DifficultyTier.DEEP,
            minDepth = 3,
            maxDepth = 4,
            maxTraps = 4,
            recoveryAllowed = false,
            minSolutionLength = 20,
            maxSolutionLength = 40,
            maxRepetitiveRatio = 0.75f
        )

        val EXPERT = StrategicBlueprint(
            tier = DifficultyTier.EXPERT,
            minDepth = 4,
            maxDepth = 5,
            maxTraps = 4,
            recoveryAllowed = false,
            minSolutionLength = 20,
            maxSolutionLength = 45,
            maxRepetitiveRatio = 0.70f
        )

        val MASTER = StrategicBlueprint(
            tier = DifficultyTier.MASTER,
            minDepth = 5,
            maxDepth = 6,
            maxTraps = 5,
            recoveryAllowed = false,
            minSolutionLength = 25,
            maxSolutionLength = 50,
            maxRepetitiveRatio = 0.60f
        )
        
        fun forLevel(levelNumber: Int): StrategicBlueprint {
            return when {
                levelNumber <= 10 -> EASY
                levelNumber <= 30 -> MEDIUM
                levelNumber <= 80 -> HARD
                levelNumber <= 140 -> DEEP
                levelNumber <= 180 -> EXPERT
                else -> MASTER
            }
        }
    }
}
