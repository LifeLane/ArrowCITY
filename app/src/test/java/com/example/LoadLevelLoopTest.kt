package com.example

import com.example.engine.LevelRepository
import org.junit.Test
import org.junit.Assert.*

class LoadLevelLoopTest {
    @Test
    fun testLoadLevels() {
        for (i in 1..50) {
            val level = LevelRepository.getLevel(i)
            assertNotNull(level)
            assertTrue(level.arrows.isNotEmpty())
        }
    }
}
