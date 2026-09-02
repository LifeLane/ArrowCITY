package com.example.content.cities

import androidx.compose.ui.graphics.Color
import com.example.model.GameTheme

/**
 * High-level architecture for a City/World in ArrowCITY.
 * Reusable for City 01 (Space), City 02 (Physics), ..., City 10 (Cosmic).
 */
data class CityDefinition(
    val id: Int,
    val name: String,
    val subtitle: String,
    val tagline: String,
    val description: String,
    val icon: String,
    val levelRange: IntRange,
    val totalLevels: Int = levelRange.last - levelRange.first + 1,
    val primaryColor: Color,
    val accentColor: Color,
    val backgroundColor: Color,
    val masterLevelNumber: Int = levelRange.last,
    val strategicConcept: String,
    val mechanicsSummary: List<String>
) {
    fun containsLevel(levelNumber: Int): Boolean = levelNumber in levelRange
    fun getLocalRoute(levelNumber: Int): Int = (levelNumber - levelRange.first + 1).coerceIn(1, totalLevels)
}
