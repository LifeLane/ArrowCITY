package com.example

import com.example.engine.CuratedLevels
import com.example.engine.LevelRepository
import com.example.engine.ProceduralGenerator
import com.example.engine.PuzzleSolver
import com.example.model.ArrowItem
import com.example.model.CityRepository
import com.example.model.Direction
import com.example.model.GridPoint
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LevelSolvabilityTest {

    @Test
    fun testCityMetadataAndCoverage() {
        assertEquals("Should have exactly 10 Beta Cities", 10, CityRepository.cities.size)
        for (city in CityRepository.cities) {
            assertEquals("Each city must have exactly 20 routes", 20, city.totalRoutes)
        }

        // Test boundary mappings
        assertEquals(1, CityRepository.getCityForLevel(1).id)
        assertEquals(1, CityRepository.getRouteNumberInCity(1))
        assertEquals(1, CityRepository.getCityForLevel(20).id)
        assertEquals(20, CityRepository.getRouteNumberInCity(20))

        assertEquals(2, CityRepository.getCityForLevel(21).id)
        assertEquals(1, CityRepository.getRouteNumberInCity(21))
        assertEquals(2, CityRepository.getCityForLevel(40).id)
        assertEquals(20, CityRepository.getRouteNumberInCity(40))

        assertEquals(10, CityRepository.getCityForLevel(200).id)
        assertEquals(20, CityRepository.getRouteNumberInCity(200))
    }

    @Test
    fun testAllCuratedAnchorLevelsAreSolvableAndGeometricallyValid() {
        for ((lvl, levelData) in CuratedLevels.curatedMap) {
            assertNotNull("Level $lvl should exist", levelData)
            assertTrue("Level $lvl should have arrows", levelData.arrows.isNotEmpty())
            assertTrue("Level $lvl gridWidth should be > 0", levelData.gridWidth > 0)
            assertTrue("Level $lvl gridHeight should be > 0", levelData.gridHeight > 0)
            assertTrue("Level $lvl maxDrops should be >= 3", levelData.maxDrops >= 3)

            // Validate geometry and no overlapping cells in initial state
            val allOccupied = mutableSetOf<GridPoint>()
            for (arrow in levelData.arrows) {
                assertTrue("Arrow ${arrow.id} in Level $lvl must have >= 2 points", arrow.points.size >= 2)
                for (i in 0 until arrow.points.size - 1) {
                    val p1 = arrow.points[i]
                    val p2 = arrow.points[i + 1]
                    assertTrue("Arrow ${arrow.id} in Level $lvl segment must be orthogonal: $p1 to $p2", p1.x == p2.x || p1.y == p2.y)
                    assertFalse("Arrow ${arrow.id} in Level $lvl points must not be identical", p1 == p2)
                }

                val cells = arrow.allOccupiedCells()
                for (cell in cells) {
                    assertTrue("Arrow cell $cell in Level $lvl within bounds", cell.x in 0 until levelData.gridWidth && cell.y in 0 until levelData.gridHeight)
                    assertFalse("Arrow cell $cell in Level $lvl overlaps another arrow", cell in allOccupied)
                    allOccupied.add(cell)
                }
            }

            val solvable = PuzzleSolver.isSolvable(
                levelData.arrows,
                levelData.gridWidth,
                levelData.gridHeight
            )
            assertTrue("Level $lvl must be solvable", solvable)
        }
    }

    @Test
    fun testAll200BetaLevelsAreSolvableAndDeterministic() {
        for (lvl in 1..200) {
            val levelData1 = LevelRepository.getLevel(lvl)
            val levelData2 = LevelRepository.getLevel(lvl)

            // Deterministic output check
            assertEquals("Level $lvl title must match across loads", levelData1.title, levelData2.title)
            assertEquals("Level $lvl arrow count must match across loads", levelData1.arrows.size, levelData2.arrows.size)

            val solvable = PuzzleSolver.isSolvable(
                levelData1.arrows,
                levelData1.gridWidth,
                levelData1.gridHeight
            )
            assertTrue("Beta Level $lvl must be 100% solvable", solvable)

            // Validate arrow geometry
            // Validate hint returns an unblocked arrow
            val hint = PuzzleSolver.getHintArrow(levelData1.arrows, levelData1.gridWidth, levelData1.gridHeight)
            assertNotNull("Level $lvl must have at least one unblocked hint arrow at start", hint)
        }
    }

    @Test
    fun testPuzzleSolverFeatures() {
        // 1. Unblocked detection & occupancy
        val a1 = ArrowItem(1, listOf(GridPoint(1, 2), GridPoint(5, 2)), Direction.RIGHT)
        val a2 = ArrowItem(2, listOf(GridPoint(3, 4), GridPoint(3, 1)), Direction.UP)
        val unblocked = PuzzleSolver.findUnblockedArrows(listOf(a1, a2), 8, 8)
        assertTrue(unblocked.contains(a1))
        assertTrue(unblocked.contains(a2))

        // 2. Blocking collision
        val blocker = ArrowItem(3, listOf(GridPoint(6, 1), GridPoint(6, 4)), Direction.DOWN)
        val collision = PuzzleSolver.checkCollision(a1, listOf(a1, blocker), 8, 8)
        assertNotNull("a1 should collide with blocker", collision)
        assertEquals(3, collision?.collidingWithArrowId)

        // 3. Ghost phase passes through
        val ghost = a1.copy(isGhost = true)
        val ghostCollision = PuzzleSolver.checkCollision(ghost, listOf(ghost, blocker), 8, 8)
        assertTrue("Ghost arrow must not collide", ghostCollision == null)

        // 4. Split arrow (Snip)
        val longArrow = ArrowItem(4, listOf(GridPoint(1, 1), GridPoint(7, 1)), Direction.RIGHT)
        val splitResult = PuzzleSolver.splitArrow(longArrow, 99)
        assertNotNull("Long arrow should be snappable", splitResult)
        assertEquals(2, splitResult!!.size)
        assertEquals(4, splitResult[0].id)
        assertEquals(99, splitResult[1].id)

        // 5. Hint retrieval
        val hint = PuzzleSolver.getHintArrow(listOf(a1, blocker), 8, 8)
        assertNotNull("Should find an unblocked hint arrow", hint)
        assertEquals(3, hint?.id)
    }
}
