package com.example

import com.example.engine.LevelRepository
import com.example.engine.PuzzleSolver
import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GridPoint
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testUnblockedArrowExit() {
        val arrow1 = ArrowItem(1, listOf(GridPoint(2, 2), GridPoint(6, 2)), Direction.RIGHT)
        val arrow2 = ArrowItem(2, listOf(GridPoint(4, 5), GridPoint(4, 1)), Direction.UP)
        val active = listOf(arrow1, arrow2)

        val unblocked = PuzzleSolver.findUnblockedArrows(active, 8, 8)
        assertTrue(unblocked.isNotEmpty())
    }

    @Test
    fun testLevelSolvability() {
        val level1 = LevelRepository.getLevel(1)
        val solvable = PuzzleSolver.isSolvable(level1.arrows, level1.gridWidth, level1.gridHeight)
        assertTrue(solvable)
    }
}

