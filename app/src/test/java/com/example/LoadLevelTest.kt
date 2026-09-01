package com.example

import com.example.engine.LevelRepository
import org.junit.Test
import org.junit.Assert.*

class LoadLevelTest {
    @Test
    fun testLoadLevel() {
        println("Loading level 15...")
        val level15 = LevelRepository.getLevel(15)
        println("Level 15 loaded: ${level15.title}")
        
        println("Loading level 20...")
        val level20 = LevelRepository.getLevel(20)
        println("Level 20 loaded: ${level20.title}")
    }
}
