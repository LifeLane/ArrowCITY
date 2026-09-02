package com.example

import com.example.engine.CurrentGameEngine
import com.example.engine.MoveResult
import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GridPoint
import com.example.model.LevelData
import org.junit.Assert.assertTrue
import org.junit.Test

class GameEngineTest {

    @Test
    fun testValidClearMove() {
        val engine = CurrentGameEngine()
        
        val arrow = ArrowItem(
            id = 1,
            points = listOf(GridPoint(2, 1), GridPoint(2, 2)),
            headDirection = Direction.UP
        )
        
        val activeArrows = listOf(arrow)
        val levelData = LevelData(levelNumber = 1, title = "Test", gridWidth = 5, gridHeight = 5, arrows = activeArrows)
        
        val result = engine.processPlayerAction(arrow, activeArrows, levelData)
        
        assertTrue("Expected Complete but got $result", result is MoveResult.Complete)
    }

    @Test
    fun testInvalidBlockedMove() {
        val engine = CurrentGameEngine()
        
        val arrow1 = ArrowItem(
            id = 1,
            points = listOf(GridPoint(2, 2), GridPoint(2, 2)), // Need 2 points for allOccupiedCells to work
            headDirection = Direction.UP
        )
        
        val arrow2 = ArrowItem(
            id = 2,
            points = listOf(GridPoint(2, 1), GridPoint(2, 1)), // Occupies the exact cell arrow1 points to
            headDirection = Direction.LEFT
        )
        
        val activeArrows = listOf(arrow1, arrow2)
        val levelData = LevelData(levelNumber = 1, title = "Test", gridWidth = 5, gridHeight = 5, arrows = activeArrows)
        
        val result = engine.processPlayerAction(arrow1, activeArrows, levelData)
        
        assertTrue("Expected Invalid but got $result", result is MoveResult.Invalid)
    }
    
    @Test
    fun testDeterminism() {
        val engine = CurrentGameEngine()
        
        val arrow1 = ArrowItem(
            id = 1,
            points = listOf(GridPoint(2, 1), GridPoint(2, 2)),
            headDirection = Direction.UP
        )
        
        val arrow2 = ArrowItem(
            id = 2,
            points = listOf(GridPoint(2, 4), GridPoint(2, 3)),
            headDirection = Direction.DOWN
        )
        
        val activeArrows = listOf(arrow1, arrow2)
        val levelData = LevelData(levelNumber = 1, title = "Test", gridWidth = 5, gridHeight = 5, arrows = activeArrows)
        
        val result1 = engine.processPlayerAction(arrow1, activeArrows, levelData)
        val result2 = engine.processPlayerAction(arrow1, activeArrows, levelData)
        
        assertTrue("Results should be equal", result1 == result2)
    }
}
