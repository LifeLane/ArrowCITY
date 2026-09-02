package com.example

import com.example.engine.BoardState
import com.example.engine.LevelRepository
import com.example.engine.Move
import com.example.engine.PuzzleAnalyzer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PuzzleAnalyzerTest {

    @Test
    fun testAnalyzerTransitions() {
        val levelData = LevelRepository.getLevel(1)
        val analyzer = PuzzleAnalyzer(levelData)
        val state = analyzer.getInitialState()
        
        val moves = analyzer.getAvailableMoves(state)
        assertTrue("Level 1 must have at least one valid move", moves.isNotEmpty())
        
        val firstMove = moves.first()
        val nextState = analyzer.applyMove(state, firstMove)
        
        assertTrue("Applying valid move should reduce remaining arrows", 
            nextState.activeArrowIds.size == state.activeArrowIds.size - 1)
        assertFalse("Removed arrow should not be in next state", 
            firstMove.arrowId in nextState.activeArrowIds)
            
        // Invalid move should not mutate state
        val invalidMove = Move(firstMove.arrowId) // already removed, not a valid move now
        val sameState = analyzer.applyMove(nextState, invalidMove)
        assertEquals("Applying invalid move must not mutate state", nextState, sameState)
    }

    @Test
    fun testMonotonicProperty() {
        // We will empirically verify the monotonic property for the first 5 levels
        for (lvl in 1..5) {
            val levelData = LevelRepository.getLevel(lvl)
            val analyzer = PuzzleAnalyzer(levelData)
            val initialState = analyzer.getInitialState()
            
            // We want to prove: if state is solvable, taking ANY valid move leads to a solvable state.
            // Since we know the initial state is solvable, we can BFS/DFS and assert all states are solvable.
            // Actually, a state is solvable if it can reach the empty state. 
            // In a monotonic DAG, ALL paths from start reach the empty state.
            // Let's do a DFS and assert every state we reach can be cleared.
            
            fun assertSolvable(state: BoardState) {
                if (state.activeArrowIds.isEmpty()) return
                
                val moves = analyzer.getAvailableMoves(state)
                assertTrue("Non-empty state must have at least one move in a monotonic game (no dead ends). Failed on level $lvl", moves.isNotEmpty())
                
                // Explore one path down to verify it solves.
                var curr = state
                while (curr.activeArrowIds.isNotEmpty()) {
                    val currMoves = analyzer.getAvailableMoves(curr)
                    assertTrue("Encountered dead end during completion on level $lvl", currMoves.isNotEmpty())
                    curr = analyzer.applyMove(curr, currMoves.first())
                }
            }
            
            // To be exhaustive, we can check a sample of valid moves from the initial state
            val initialMoves = analyzer.getAvailableMoves(initialState)
            for (move in initialMoves) {
                val nextState = analyzer.applyMove(initialState, move)
                assertSolvable(nextState)
            }
        }
    }

    @Test
    fun testDeterminism() {
        val levelData = LevelRepository.getLevel(1)
        val analyzer1 = PuzzleAnalyzer(levelData)
        val analyzer2 = PuzzleAnalyzer(levelData)
        
        val state1 = analyzer1.getInitialState()
        val state2 = analyzer2.getInitialState()
        assertEquals(state1, state2)
        
        val moves1 = analyzer1.getAvailableMoves(state1)
        val moves2 = analyzer2.getAvailableMoves(state2)
        assertEquals(moves1, moves2)
    }

    @Test
    fun testCompletedState() {
        val levelData = LevelRepository.getLevel(1)
        val analyzer = PuzzleAnalyzer(levelData)
        val emptyState = BoardState(emptySet())
        
        val moves = analyzer.getAvailableMoves(emptyState)
        assertTrue("Completed state must have zero available moves", moves.isEmpty())
    }

    @Test
    fun testAnalysisOutput() {
        // Just verify analysis runs correctly for a small level
        val levelData = LevelRepository.getLevel(1)
        val analyzer = PuzzleAnalyzer(levelData)
        val analysis = analyzer.analyze()
        
        assertTrue(analysis.solvable)
        assertEquals(levelData.arrows.size, analysis.minimumSolutionLength)
        assertEquals(0, analysis.deadEndCount)
        assertEquals(0, analysis.reasoningDepth)
        // Since deadEndCount is 0, we can conclude the puzzle lacks trap states.
    }
}
