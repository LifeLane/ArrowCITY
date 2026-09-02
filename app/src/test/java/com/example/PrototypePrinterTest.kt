package com.example

import com.example.engine.experimental.StepPuzzleAnalyzer
import com.example.engine.experimental.StepPuzzleGenerator
import com.example.engine.experimental.StepSlideEngine
import org.junit.Test

class PrototypePrinterTest {
    @Test
    fun printPrototypes() {
        val generator = StepPuzzleGenerator()
        val seedsAndTargets = listOf(
            Triple("Prototype A - Tutorial", 1234L, 0),
            Triple("Prototype B - Basic planning", 5678L, 1),
            Triple("Prototype C - Intermediate", 9999L, 2),
            Triple("Prototype D - Advanced prototype", 7777L, 3)
        )
        
        for ((name, seed, target) in seedsAndTargets) {
            val state = generator.generatePrototype(seed, target)
            val analyzer = StepPuzzleAnalyzer(StepSlideEngine(5, 5), state)
            val metrics = analyzer.analyze()
            
            val initialMoves = StepSlideEngine(5, 5).getAvailableMoves(state).size
            val arrowCount = state.arrows.size
            
            println("=== $name ===")
            println("Arrow count: $arrowCount")
            println("Initial legal moves: $initialMoves")
            println("Choice states: ${metrics.choiceStates}")
            println("Critical choices: ${metrics.criticalChoices}")
            println("Dead ends: ${metrics.deadEndCount}")
            println("Minimum solution length: ${metrics.minimumMoves}")
            println("Reasoning depth: ${metrics.reasoningDepth}")
            println("Successful paths: ${metrics.successfulPaths}")
            println("Failed paths: ${metrics.failedPaths}")
            println()
        }
    }
}
