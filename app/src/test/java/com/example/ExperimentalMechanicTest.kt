package com.example

import com.example.engine.experimental.StepBoardState
import com.example.engine.experimental.StepMove
import com.example.engine.experimental.StepPuzzleAnalyzer
import com.example.engine.experimental.StepPuzzleGenerator
import com.example.engine.experimental.StepSlideEngine
import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GridPoint
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExperimentalMechanicTest {

    @Test
    fun testStepTransition() {
        val engine = StepSlideEngine(3, 3)
        val arrow = ArrowItem(
            id = 1,
            points = listOf(GridPoint(1, 1)),
            headDirection = Direction.RIGHT
        )
        val state = StepBoardState(mapOf(1 to arrow))
        
        val moves = engine.getAvailableMoves(state)
        assertEquals(1, moves.size)
        
        val nextState = engine.applyMove(state, moves[0])
        val movedArrow = nextState.arrows[1]!!
        assertEquals(GridPoint(2, 1), movedArrow.points[0]) // Moved right
    }

    @Test
    fun testStepClear() {
        val engine = StepSlideEngine(3, 3)
        val arrow = ArrowItem(
            id = 1,
            points = listOf(GridPoint(2, 1)),
            headDirection = Direction.RIGHT
        )
        val state = StepBoardState(mapOf(1 to arrow))
        
        // Moving right from x=2 in a 3x3 grid puts it at x=3, which is out of bounds
        val nextState = engine.applyMove(state, StepMove(1))
        assertTrue(nextState.arrows.isEmpty()) // Cleared!
    }

    @Test
    fun testStepCollision() {
        val engine = StepSlideEngine(3, 3)
        val arrow1 = ArrowItem(
            id = 1,
            points = listOf(GridPoint(1, 1)),
            headDirection = Direction.RIGHT
        )
        val arrow2 = ArrowItem(
            id = 2,
            points = listOf(GridPoint(2, 1)),
            headDirection = Direction.UP
        )
        val state = StepBoardState(mapOf(1 to arrow1, 2 to arrow2))
        
        val moves = engine.getAvailableMoves(state)
        assertFalse(StepMove(1) in moves) // Arrow 1 is blocked by Arrow 2
        assertTrue(StepMove(2) in moves)  // Arrow 2 can move UP
    }

    @Test
    fun testDeadlockCreation() {
        val engine = StepSlideEngine(4, 4)
        
        // A1 at (1,1) facing RIGHT
        val a1 = ArrowItem(1, listOf(GridPoint(1, 1)), Direction.RIGHT)
        // A2 at (2,0) facing DOWN
        val a2 = ArrowItem(2, listOf(GridPoint(2, 0)), Direction.DOWN)
        
        val state = StepBoardState(mapOf(1 to a1, 2 to a2))
        
        // A1 moving right blocks A2!
        val nextState = engine.applyMove(state, StepMove(1))
        
        val a1Moved = nextState.arrows[1]!!
        assertEquals(GridPoint(2, 1), a1Moved.points[0])
        
        val movesAfter = engine.getAvailableMoves(nextState)
        assertFalse("A2 should be blocked by A1", StepMove(2) in movesAfter)
    }

    @Test
    fun testPrototypes() {
        val generator = StepPuzzleGenerator()
        
        // Prototype A: Tutorial (0 critical choices)
        val protoA = generator.generatePrototype(seed = 1234L, targetCriticalChoices = 0)
        val analysisA = StepPuzzleAnalyzer(StepSlideEngine(5, 5), protoA).analyze()
        assertTrue(analysisA.solvable)
        
        // Prototype B: Basic planning (1 critical choice)
        val protoB = generator.generatePrototype(seed = 5678L, targetCriticalChoices = 1)
        val analysisB = StepPuzzleAnalyzer(StepSlideEngine(5, 5), protoB).analyze()
        assertTrue(analysisB.solvable)
        assertTrue("Must have at least 1 critical choice", analysisB.criticalChoices >= 1)
        assertTrue("Must have dead ends", analysisB.deadEndCount > 0)
        
        // Prototype C: Intermediate (2 critical choices)
        val protoC = generator.generatePrototype(seed = 9999L, targetCriticalChoices = 2)
        val analysisC = StepPuzzleAnalyzer(StepSlideEngine(5, 5), protoC).analyze()
        assertTrue(analysisC.solvable)
        assertTrue(analysisC.criticalChoices >= 2)
        
        println("Proto B: $analysisB")
        println("Proto C: $analysisC")
    }
}
