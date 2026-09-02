package com.example.engine.experimental

import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GridPoint
import java.util.Random

class StepPuzzleGenerator {

    /**
     * Generates a puzzle matching the target critical choices by random placement and evaluation.
     */
    fun generatePrototype(seed: Long, targetCriticalChoices: Int): StepBoardState {
        val random = Random(seed)
        
        // We evaluate candidates until one satisfies the difficulty criteria.
        for (attempt in 1..20000) {
            val state = generateCandidate(random)
            val engine = StepSlideEngine(5, 5)
            val analyzer = StepPuzzleAnalyzer(engine, state)
            val analysis = analyzer.analyze()
            
            if (analysis.solvable && analysis.criticalChoices >= targetCriticalChoices) {
                return state
            }
        }
        throw IllegalStateException("Failed to generate a puzzle meeting the criteria")
    }

    private fun generateCandidate(random: Random): StepBoardState {
        val arrows = mutableMapOf<Int, ArrowItem>()
        val occupied = mutableSetOf<GridPoint>()
        
        var idCounter = 1
        // Generate 4 to 5 arrows
        val arrowCount = 4 + random.nextInt(2)
        
        for (i in 1..arrowCount) {
            val dir = Direction.values()[random.nextInt(4)]
            val tailX = random.nextInt(5)
            val tailY = random.nextInt(5)
            val headX = tailX + dir.dx
            val headY = tailY + dir.dy
            
            val tail = GridPoint(tailX, tailY)
            val head = GridPoint(headX, headY)
            
            if (headX in 0..4 && headY in 0..4) {
                if (!occupied.contains(tail) && !occupied.contains(head)) {
                    occupied.add(tail)
                    occupied.add(head)
                    arrows[idCounter] = ArrowItem(
                        id = idCounter,
                        points = listOf(tail, head),
                        headDirection = dir
                    )
                    idCounter++
                }
            }
        }
        return StepBoardState(arrows)
    }
}
