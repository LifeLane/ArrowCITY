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
        // A genuine deadlock means it cannot ever be solved (solvable = false, deadEndCount > 0).
        // Let's create a cyclic block.
        // A1 facing right. A2 facing down. A3 facing left. A4 facing up.
        // If we move A1 right, it blocks A2. And A2 blocks A3. And A3 blocks A4. And A4 blocks A1.
        // Actually, simpler: 2 arrows that slide into each other's paths and both get blocked forever.
        val engine = StepSlideEngine(4, 4)
        
        val a1 = ArrowItem(1, listOf(GridPoint(0, 1)), Direction.RIGHT)
        val a2 = ArrowItem(2, listOf(GridPoint(1, 0)), Direction.DOWN)
        val a3 = ArrowItem(3, listOf(GridPoint(2, 2)), Direction.UP)
        // If a1 moves right to (1,1), it blocks a2 (which wants to go to (1,1)). But a1 wants to go to (2,1).
        // If a3 is at (2,2) moving UP, it wants to go to (2,1). So a1 is blocked by a3.
        // a2 is at (1,0) moving down. It is blocked by a1 at (1,1).
        // a3 is at (2,2) moving up. It is blocked by ... maybe a4?
        // Let's just build a 2-arrow state that is ALREADY deadlocked.
        val d1 = ArrowItem(1, listOf(GridPoint(1, 1)), Direction.RIGHT)
        val d2 = ArrowItem(2, listOf(GridPoint(2, 1)), Direction.LEFT)
        val deadlockState = StepBoardState(mapOf(1 to d1, 2 to d2))
        val analyzer = StepPuzzleAnalyzer(engine, deadlockState)
        val analysis = analyzer.analyze()
        assertFalse("Genuinely unsolvable state", analysis.solvable)
        assertTrue("Contains dead ends", analysis.deadEndCount > 0)
    }

    @Test
    fun testTemporaryBlocking() {
        val engine = StepSlideEngine(4, 4)
        // A1 wants to move RIGHT but A2 is in the way.
        // A2 wants to move DOWN and can do so freely.
        val a1 = ArrowItem(1, listOf(GridPoint(1, 1)), Direction.RIGHT)
        val a2 = ArrowItem(2, listOf(GridPoint(2, 1)), Direction.DOWN)
        val state = StepBoardState(mapOf(1 to a1, 2 to a2))
        
        val moves = engine.getAvailableMoves(state)
        assertEquals("A1 is temporarily blocked, only A2 can move", 1, moves.size)
        assertEquals(2, moves[0].arrowId)
        
        val analyzer = StepPuzzleAnalyzer(engine, state)
        val analysis = analyzer.analyze()
        assertTrue("State is solvable because A2 will move out of the way", analysis.solvable)
        assertEquals(0, analysis.deadEndCount)
    }

    @Test
    fun testCriticalChoice() {
        val engine = StepSlideEngine(4, 4)
        // A1 at (0,1) moving RIGHT.
        // A2 at (1,2) moving UP.
        // A3 at (2,1) moving UP.
        // If we move A1, it goes to (1,1). It blocks A2. A2 is at (1,2) moving UP, needs (1,1).
        // But A1 now needs (2,1), which is blocked by A3.
        // A3 needs (2,0). Let's say A3 is at (2,1) moving UP, it can go to (2,0) -> (2,-1).
        // Let's refine the critical choice state:
        // A1 at (0,1) right.
        // A2 at (1,0) down.
        // A3 at (1,2) up.
        // Move A2 down -> (1,1). A1 is blocked by A2. A3 is blocked by A2.
        // Then A2 moves down -> (1,2), blocked by A3. So moving A2 down deadlocks A2 and A3!
        // Move A3 up -> (1,1). A1 blocked. A2 blocked. A3 moves to (1,0) blocks A2.
        // Let's make it simpler.
        // A1 at (0,0) right.
        // A2 at (0,2) up.
        val c1 = ArrowItem(1, listOf(GridPoint(1, 1)), Direction.RIGHT) // needs (2,1), (3,1), out
        val c2 = ArrowItem(2, listOf(GridPoint(2, 2)), Direction.UP)    // needs (2,1), (2,0), out
        val state = StepBoardState(mapOf(1 to c1, 2 to c2))
        
        // Initial state: both can move!
        // Move c1 -> it goes to (2,1). Blocks c2 (c2 needs 2,1). c1 is at 2,1. Next move c1 -> 3,1. c2 is free!
        // So moving c1 is solvable.
        // What if we move c2? c2 goes to 2,1. Blocks c1 (c1 needs 2,1). Next move c2 -> 2,0. c1 is free!
        // Both are solvable! This is not a critical choice yet.
        
        // To make it critical: one move MUST trap another permanently.
        // For example, c1 moves right into (2,1). c2 moves UP into (2,0). 
        // Let's add c3 at (3,1) moving LEFT.
        val c3 = ArrowItem(3, listOf(GridPoint(3, 1)), Direction.LEFT) // needs (2,1), (1,1), out
        // Now c1 at (1,1) right, c3 at (3,1) left.
        // Both can move.
        // Move c1 to (2,1). c3 needs (2,1), so c3 is blocked. c1 needs (3,1). But c3 is at (3,1)!
        // DEADLOCK!
        // But what if c2 is at (2,0) moving DOWN?
        
        // Let's just use the prototype generator to find one, and hardcode it here.
        // But the generator is right there, let's just generate a prototype with 1 critical choice and grab the state.
        val generator = StepPuzzleGenerator()
        val protoState = generator.generatePrototype(seed = 5678L, targetStrategicDepth = 1)
        val analyzer = StepPuzzleAnalyzer(StepSlideEngine(5, 5), protoState)
        
        // Ensure the initial state has at least one critical choice or leads to one.
        val metrics = analyzer.analyze()
        assertTrue(metrics.criticalChoices >= 1)
        
        // Let's find the exact node that is the critical choice.
        var criticalState: StepBoardState? = null
        var solvableMove: StepMove? = null
        var unsolvableMove: StepMove? = null
        
        for ((state, isSolvable) in analyzer.stateSolvable) {
            if (isSolvable) {
                val moves = StepSlideEngine(5, 5).getAvailableMoves(state)
                if (moves.size >= 2) {
                    var sMove: StepMove? = null
                    var uMove: StepMove? = null
                    for (m in moves) {
                        val next = StepSlideEngine(5, 5).applyMove(state, m)
                        if (analyzer.stateSolvable[next] == true) {
                            sMove = m
                        } else {
                            uMove = m
                        }
                    }
                    if (sMove != null && uMove != null) {
                        criticalState = state
                        solvableMove = sMove
                        unsolvableMove = uMove
                        break
                    }
                }
            }
        }
        
        assertTrue("Must find a critical state", criticalState != null)
        val nextSolvable = StepSlideEngine(5, 5).applyMove(criticalState!!, solvableMove!!)
        val nextUnsolvable = StepSlideEngine(5, 5).applyMove(criticalState, unsolvableMove!!)
        
        assertTrue("Move A -> solvable", analyzer.stateSolvable[nextSolvable] == true)
        assertTrue("Move B -> unsolvable", analyzer.stateSolvable[nextUnsolvable] == false)
    }

    @Test
    fun testPrototypes() {
        val generator = StepPuzzleGenerator()
        
        // Prototype A: Tutorial (0 strategic depth)
        val protoA = generator.generatePrototype(seed = 1234L, targetStrategicDepth = 0)
        val analysisA = StepPuzzleAnalyzer(StepSlideEngine(5, 5), protoA).analyze()
        assertTrue(analysisA.solvable)
        assertEquals(0, analysisA.strategicDepth)
        
        // Prototype B: Basic planning (1 strategic depth)
        val protoB = generator.generatePrototype(seed = 5678L, targetStrategicDepth = 1)
        val analysisB = StepPuzzleAnalyzer(StepSlideEngine(5, 5), protoB).analyze()
        assertTrue(analysisB.solvable)
        assertTrue("Must have at least 1 strategic depth", analysisB.strategicDepth >= 1)
        assertTrue("Must have dead ends", analysisB.deadEndCount > 0)
        
        // Prototype C: Intermediate (2 strategic depth)
        val protoC = generator.generatePrototype(seed = 9999L, targetStrategicDepth = 2)
        val analysisC = StepPuzzleAnalyzer(StepSlideEngine(5, 5), protoC).analyze()
        assertTrue(analysisC.solvable)
        assertTrue(analysisC.strategicDepth >= 2)
    }

    @Test
    fun testDeterminism() {
        val generator = StepPuzzleGenerator()
        val proto1 = generator.generatePrototype(seed = 1111L, targetStrategicDepth = 1)
        val proto2 = generator.generatePrototype(seed = 1111L, targetStrategicDepth = 1)
        assertEquals("Identical seed must produce identical board", proto1, proto2)
    }
}
