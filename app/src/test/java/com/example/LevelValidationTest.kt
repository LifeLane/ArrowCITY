package com.example

import com.example.engine.LevelRepository
import com.example.engine.PuzzleSolver
import org.junit.Test
import org.junit.Assert.*

class LevelValidationTest {

    @Test
    fun testAll200Levels() {
        var passed = 0
        var failed = 0
        var anchors = 0
        var procedural = 0
        
        for (level in 1..200) {
            try {
                val isAnchor = LevelRepository.isAnchorLevel(level)
                val levelData = LevelRepository.getLevel(level)
                
                assertNotNull("LevelData should not be null", levelData)
                assertEquals("Level number must match", level, levelData.levelNumber)
                assertTrue("Must have arrows", levelData.arrows.isNotEmpty())
                
                // Duplicate IDs
                val uniqueIds = levelData.arrows.map { it.id }.toSet()
                assertEquals("Arrow IDs must be unique", levelData.arrows.size, uniqueIds.size)
                
                // Geometry / bounds
                for (arrow in levelData.arrows) {
                    assertTrue("Arrow must have points", arrow.points.size >= 2)
                    for (pt in arrow.points) {
                        assertTrue("Point out of bounds X: ${pt.x}", pt.x in 0 until levelData.gridWidth)
                        assertTrue("Point out of bounds Y: ${pt.y}", pt.y in 0 until levelData.gridHeight)
                    }
                }
                
                // Solvability
                val metrics = PuzzleSolver.analyzePuzzle(levelData.arrows, levelData.gridWidth, levelData.gridHeight)
                assertTrue("Level $level must be solvable", metrics.solvable)
                assertTrue("Level $level must have at least 1 initially unblocked", metrics.initiallyUnblocked > 0)
                
                // Hint exists
                val hint = PuzzleSolver.getHintArrow(levelData.arrows, levelData.gridWidth, levelData.gridHeight)
                assertNotNull("Hint must exist for solvable start state", hint)
                
                passed++
                if (isAnchor || LevelRepository.getLevel(level) == com.example.engine.CuratedLevels.curatedMap[level]) {
                    anchors++
                } else {
                    procedural++
                }
                
            } catch (e: Exception) {
                println("Failed at Level $level: ${e.message}")
                failed++
            }
        }
        
        println("ARROW CITY BETA VALIDATION")
        println("Cities: 10")
        println("Routes: 200")
        println("Anchors: $anchors")
        println("Procedural: $procedural")
        println("PASS: $passed")
        println("FAIL: $failed")
        
        assertEquals("All 200 levels must pass", 0, failed)
        assertEquals("Must test exactly 200 levels", 200, passed)
    }
}
