package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelProgressDao {
    @Query("SELECT * FROM level_progress ORDER BY levelNumber ASC")
    fun getAllProgressFlow(): Flow<List<LevelProgress>>
    
    @Query("SELECT * FROM level_progress ORDER BY levelNumber ASC")
    suspend fun getAllProgress(): List<LevelProgress>

    @Query("SELECT * FROM level_progress WHERE levelNumber = :levelNumber")
    suspend fun getProgress(levelNumber: Int): LevelProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: LevelProgress)
}
