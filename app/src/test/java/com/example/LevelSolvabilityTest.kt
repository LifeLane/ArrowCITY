package com.example

import com.example.engine.LevelRepository
import com.example.engine.ProceduralGenerator
import com.example.engine.PuzzleSolver
import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GridPoint
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LevelSolvabilityTest {

    @Test
    fun testAllCuratedLevelsAreSolvableAndGeometricallyValid() {
        val curatedLevelNumbers = listOf(1, 2, 3, 4, 5, 10, 27, 40, 99, 199)

        for (lvl in curatedLevelNumbers) {
            val levelData = LevelRepository.getLevel(lvl)
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
                    assertTrue(
                        "Arrow ${arrow.id} in Level $lvl segment must be orthogonal: $p1 to $p2",
                        p1.x == p2.x || p1.y == p2.y
                    )
                    assertFalse(
                        "Arrow ${arrow.id} in Level $lvl points must not be identical",
                        p1 == p2
                    )
                }

                val cells = arrow.allOccupiedCells()
                for (cell in cells) {
                    assertTrue(
                        "Arrow cell $cell in Level $lvl must be within bounds (0..${levelData.gridWidth - 1}, 0..${levelData.gridHeight - 1})",
                        cell.x in 0 until levelData.gridWidth && cell.y in 0 until levelData.gridHeight
                    )
                    if (cell in allOccupied) {
                        System.err.println("OVERLAP in curated Level $lvl at $cell by arrow ${arrow.id}")
                    }
                    assertFalse(
                        "Arrow cell $cell in Level $lvl overlaps another arrow in initial state (arrow ${arrow.id})",
                        cell in allOccupied
                    )
                    allOccupied.add(cell)
                }
            }

            val solvable = PuzzleSolver.isSolvable(
                levelData.arrows,
                levelData.gridWidth,
                levelData.gridHeight
            )
            assertTrue("Curated Level $lvl must be 100% solvable", solvable)
        }
    }

    @Test
    fun testProceduralLevelsFrom1To250AreSolvableAndDeterministic() {
        for (lvl in 1..250) {
            val levelData1 = LevelRepository.getLevel(lvl)
            val levelData2 = LevelRepository.getLevel(lvl)

            // Deterministic output check
            assertTrue("Level $lvl title must match across loads", levelData1.title == levelData2.title)
            assertTrue("Level $lvl arrow count must match across loads", levelData1.arrows.size == levelData2.arrows.size)

            val solvable = PuzzleSolver.isSolvable(
                levelData1.arrows,
                levelData1.gridWidth,
                levelData1.gridHeight
            )
            assertTrue("Procedural/Art Level $lvl must be 100% solvable", solvable)

            // Validate arrow geometry
            val valid = ProceduralGenerator.validateArrowSet(
                levelData1.arrows,
                levelData1.gridWidth,
                levelData1.gridHeight
            )
            assertTrue("Level $lvl must pass strict geometry & bounds validation", valid)
        }
    }

    @Test
    fun testPuzzleSolverFeatures() {
        // 1. Unblocked detection & occupancy
        val a1 = ArrowItem(1, listOf(GridPoint(1, 2), GridPoint(5, 2)), Direction.RIGHT)
        val a2 = ArrowItem(2, listOf(GridPoint(3, 4), GridPoint(3, 1)), Direction.UP)
        // a2 crosses path of a1 moving right from (5,2) -> wait, a1 is at y=2, head at (5,2), moving RIGHT -> path (6,2), (7,2). a2 head is at (3,1), moving UP -> path (3,0).
        val unblocked = PuzzleSolver.findUnblockedArrows(listOf(a1, a2), 8, 8)
        assertTrue(unblocked.contains(a1))
        assertTrue(unblocked.contains(a2))

        // 2. Blocking collision
        val blocker = ArrowItem(3, listOf(GridPoint(6, 1), GridPoint(6, 4)), Direction.DOWN)
        val collision = PuzzleSolver.checkCollision(a1, listOf(a1, blocker), 8, 8)
        assertNotNull("a1 should collide with blocker", collision)
        assertTrue(collision?.collidingWithArrowId == 3)

        // 3. Ghost phase passes through
        val ghost = a1.copy(isGhost = true)
        val ghostCollision = PuzzleSolver.checkCollision(ghost, listOf(ghost, blocker), 8, 8)
        assertTrue("Ghost arrow must not collide", ghostCollision == null)

        // 4. Split arrow (Snip)
        val longArrow = ArrowItem(4, listOf(GridPoint(1, 1), GridPoint(7, 1)), Direction.RIGHT)
        val splitResult = PuzzleSolver.splitArrow(longArrow, 99)
        assertNotNull("Long arrow should be snappable", splitResult)
        assertTrue(splitResult!!.size == 2)
        assertTrue(splitResult[0].id == 4)
        assertTrue(splitResult[1].id == 99)

        // 5. Hint retrieval
        val hint = PuzzleSolver.getHintArrow(listOf(a1, blocker), 8, 8)
        assertNotNull("Should find an unblocked hint arrow", hint)
        assertTrue(hint?.id == 3)
    }
}
