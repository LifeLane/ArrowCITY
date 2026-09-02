package com.example

import com.example.engine.experimental.StepPuzzleAnalyzer
import com.example.engine.experimental.StepPuzzleGenerator
import com.example.engine.experimental.StepSlideEngine
import org.junit.Test

class PrototypePrinterTest {
    @Test
    fun printPrototypes() {
        val fixtures = listOf(
            "Prototype A - Tutorial" to com.example.engine.experimental.StepPuzzleFixtures.prototypeA,
            "Prototype B - Basic planning" to com.example.engine.experimental.StepPuzzleFixtures.prototypeB,
            "Prototype C - Intermediate" to com.example.engine.experimental.StepPuzzleFixtures.prototypeC,
            "Prototype D - Advanced prototype" to com.example.engine.experimental.StepPuzzleFixtures.prototypeD
        )
        
        for ((name, state) in fixtures) {
            val analyzer = StepPuzzleAnalyzer(StepSlideEngine(5, 5), state)
            val metrics = analyzer.analyze()
            
            val arrowCount = state.arrows.size
            
            println("=== $name ===")
            println("Arrow count: $arrowCount")
            println("Initial legal moves: ${metrics.initialLegalMoves}")
            println("Total reachable states: ${metrics.reachableStateCount}")
            println("Choice states: ${analyzer.choiceStates}")
            println("Critical choices: ${metrics.criticalChoices}")
            println("Dead ends: ${metrics.deadEndCount}")
            println("Minimum solution length: ${metrics.minimumSolutionLength}")
            println("Maximum solution length: ${metrics.maximumSolutionLength}")
            println("Maximum branching factor: ${metrics.maximumBranchingFactor}")
            println("Strategic decision depth: ${metrics.strategicDepth}")
            println("Trap Severity: ${metrics.trapSeverity}")
            println("Decision Density: ${metrics.decisionDensity}")
            println("Recovery Potential: ${metrics.recoveryPotential}")
            println("Repetitive Move Ratio: ${metrics.repetitiveMoveRatio}")
            println("Successful paths: ${metrics.successfulPaths}")
            println("Failed paths: ${metrics.failedPaths}")
            println()
        }
    }
}
