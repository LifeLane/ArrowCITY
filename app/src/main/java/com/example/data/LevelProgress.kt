package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "level_progress")
data class LevelProgress(
    @PrimaryKey val levelNumber: Int,
    val isCompleted: Boolean,
    val stars: Int,
    val bestCombo: Int = 0
)
