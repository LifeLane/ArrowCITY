package com.example

import com.example.content.cities.SpaceCityLevels
import com.example.engine.LevelRepository
import com.example.engine.PuzzleSolver
import com.example.engine.ReversePuzzleGenerator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LevelDifficultyProgressionTest {

    @Test
    fun testReversePuzzleGeneratorProducesSolvableLevels() {
        for (targetArrows in listOf(4, 6, 8, 10, 12, 14, 16)) {
            val width = 10
            val height = 10
            val level = ReversePuzzleGenerator.generate(
                gridWidth = width,
                gridHeight = height,
                targetArrowCount = targetArrows,
                seed = 1000L + targetArrows
            )

            assertTrue(
                "Level with $targetArrows arrows should have arrows.size == $targetArrows, got ${level.arrows.size}",
                level.arrows.size == targetArrows
            )
            val isSolvable = PuzzleSolver.isSolvable(level.arrows, width, height)
            assertTrue("Generated level with $targetArrows arrows must be solvable", isSolvable)

            val unblocked = PuzzleSolver.findUnblockedArrows(level.arrows, width, height)
            assertTrue(
                "Initial unblocked count should be at least 1, got ${unblocked.size}",
                unblocked.isNotEmpty()
            )
        }
    }

    @Test
    fun testAllSpaceCityLevelsStrictMonotonicDifficulty() {
        var previousArrowCount = 0

        for (lvl in 1..20) {
            val levelData = SpaceCityLevels.getLevel(lvl)
            assertNotNull("Level $lvl must exist in SpaceCityLevels", levelData)

            val arrowCount = levelData!!.arrows.size
            assertTrue(
                "Level $lvl arrows ($arrowCount) must be strictly greater than level ${lvl - 1} arrows ($previousArrowCount)",
                arrowCount > previousArrowCount
            )
            assertEquals("Level $lvl must have exactly ${lvl + 2} arrows", lvl + 2, arrowCount)
            previousArrowCount = arrowCount

            // Solvability check
            val solvable = PuzzleSolver.isSolvable(levelData.arrows, levelData.gridWidth, levelData.gridHeight)
            assertTrue("Level $lvl must be solvable", solvable)

            // Initial unblocked check
            val unblocked = PuzzleSolver.findUnblockedArrows(levelData.arrows, levelData.gridWidth, levelData.gridHeight)
            assertTrue("Level $lvl must have at least 1 unblocked arrow at start", unblocked.isNotEmpty())
            assertTrue("Level $lvl initial unblocked should be <= 4, got ${unblocked.size}", unblocked.size <= 4)

            // Fuel drops check
            val expectedDrops = if (lvl >= 17) 4 else 3
            assertEquals("Level $lvl maxDrops must match tier", expectedDrops, levelData.maxDrops)
        }
    }
}
