package com.example.data

import kotlinx.coroutines.flow.Flow

class GameRepository(private val dao: LevelProgressDao) {
    val allProgressFlow: Flow<List<LevelProgress>> = dao.getAllProgressFlow()
    
    suspend fun getAllProgress() = dao.getAllProgress()

    suspend fun getProgress(levelNumber: Int): LevelProgress? {
        return dao.getProgress(levelNumber)
    }

    suspend fun saveProgress(progress: LevelProgress) {
        dao.insertProgress(progress)
    }
}
