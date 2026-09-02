package com.example

import com.example.content.cities.SpaceCityDefinition
import com.example.content.cities.SpaceCityLevelRegistry
import com.example.content.cities.SpaceCityLevels
import com.example.engine.LevelRepository
import com.example.engine.PuzzleSolver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Rigorous test suite validating the entire CITY 01 — SPACE CITY 🚀 architecture.
 */
class SpaceCityProgressionTest {

    @Test
    fun testAll20LevelsExistAndMatchRegistry() {
        assertEquals("Space City must have exactly 20 levels", 20, SpaceCityLevelRegistry.levels.size)
        assertEquals("SpaceCityLevels map must have 20 entries", 20, SpaceCityLevels.levels.size)
        assertEquals("Map coordinates must have 20 entries", 20, SpaceCityDefinition.mapCoordinates.size)

        for (lvl in 1..20) {
            val def = SpaceCityLevelRegistry.getDefinition(lvl)
            val levelData = SpaceCityLevels.getLevel(lvl)
            val coord = SpaceCityDefinition.mapCoordinates.firstOrNull { it.levelNumber == lvl }

            assertNotNull("Definition for Level $lvl must exist", def)
            assertNotNull("LevelData for Level $lvl must exist", levelData)
            assertNotNull("Map coordinate for Level $lvl must exist", coord)

            assertEquals("Level numbers must match", lvl, levelData?.levelNumber)
            assertTrue("Grid width must be positive", (levelData?.gridWidth ?: 0) >= 8)
            assertTrue("Grid height must be positive", (levelData?.gridHeight ?: 0) >= 8)
            assertTrue("Level must have arrows", (levelData?.arrows?.size ?: 0) >= 3)
            assertTrue("Max drops must be valid", (levelData?.maxDrops ?: 0) >= 3)

            assertTrue("Map X coordinate must be normalized (0..1)", (coord?.xNorm ?: -1f) in 0f..1f)
            assertTrue("Map Y coordinate must be normalized (0..1)", (coord?.yNorm ?: -1f) in 0f..1f)
        }
    }

    @Test
    fun testAll20SpaceCityLevelsAreSolvable() {
        val failedLevels = mutableListOf<String>()
        for (lvl in 1..20) {
            val levelData = SpaceCityLevels.getLevel(lvl)
            assertNotNull("Level $lvl must exist", levelData)

            val isSolvable = PuzzleSolver.isSolvable(
                levelData!!.arrows,
                levelData.gridWidth,
                levelData.gridHeight
            )

            if (!isSolvable) {
                val unblocked = PuzzleSolver.findUnblockedArrows(
                    levelData.arrows,
                    levelData.gridWidth,
                    levelData.gridHeight
                )
                failedLevels.add("Level $lvl: unblocked=${unblocked.map { it.id }}, total=${levelData.arrows.size}")
            }
        }

        assertTrue("Failed levels: $failedLevels", failedLevels.isEmpty())
    }

    @Test
    fun testLevelRepositoryLoadsSpaceCityLevels() {
        for (lvl in 1..20) {
            val levelData = LevelRepository.getLevel(lvl)
            assertNotNull("LevelRepository must load Space City level $lvl", levelData)
            assertEquals("Level number must be $lvl", lvl, levelData.levelNumber)
            assertTrue("Title should indicate Space City", levelData.title.contains("Space City"))
        }
    }

    @Test
    fun testProgressionConstraintsAndDropScaling() {
        // Levels 1-4: Tutorial / Learn tier
        for (lvl in 1..4) {
            val def = SpaceCityLevelRegistry.getDefinition(lvl)
            assertEquals("Levels 1-4 should be LEARN tier", "Learn", def.fuelDropTier.label)
            assertEquals(3, def.fuelDropTier.maxDrops)
        }

        // Levels 5-8: Reinforce tier
        for (lvl in 5..8) {
            val def = SpaceCityLevelRegistry.getDefinition(lvl)
            assertEquals("Levels 5-8 should be REINFORCE tier", "Reinforce", def.fuelDropTier.label)
        }

        // Levels 9-16: Challenge tier
        for (lvl in 9..16) {
            val def = SpaceCityLevelRegistry.getDefinition(lvl)
            assertEquals("Levels 9-16 should be CHALLENGE tier", "Challenge", def.fuelDropTier.label)
        }

        // Levels 17-20: Master tier
        for (lvl in 17..20) {
            val def = SpaceCityLevelRegistry.getDefinition(lvl)
            assertEquals("Levels 17-20 should be MASTER tier", "Master", def.fuelDropTier.label)
            assertEquals(4, def.fuelDropTier.maxDrops)
        }

        // Milestone levels 4, 8, 12, 16, 20
        assertTrue(SpaceCityLevelRegistry.getDefinition(4).isMilestone)
        assertTrue(SpaceCityLevelRegistry.getDefinition(8).isMilestone)
        assertTrue(SpaceCityLevelRegistry.getDefinition(12).isMilestone)
        assertTrue(SpaceCityLevelRegistry.getDefinition(16).isMilestone)
        assertTrue(SpaceCityLevelRegistry.getDefinition(20).isMilestone)
    }
}
