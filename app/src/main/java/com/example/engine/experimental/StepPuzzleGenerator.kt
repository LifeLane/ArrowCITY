package com.example.engine.experimental

class StepPuzzleGenerator {
    
    val rejectionCounts = mutableMapOf<String, Int>()
    var totalAttempts = 0
    var generationTimeMs = 0L

    fun generatePrototype(seed: Long, targetStrategicDepth: Int): StepBoardState {
        val startTime = System.currentTimeMillis()
        val constructor = StepConstructiveGenerator(5, 5)
        
        try {
            // Under constructive generation, the number of attempts needed is vastly reduced
            // because every single generated candidate is mathematically 100% solvable.
            val state = constructor.generatePrototype(seed, targetStrategicDepth)
            generationTimeMs = System.currentTimeMillis() - startTime
            return state
        } catch (e: Exception) {
            generationTimeMs = System.currentTimeMillis() - startTime
            throw e
        }
    }
}
