package com.example.model

/**
 * Configuration and metadata for each of the 10 distinct Cities in Arrow City Beta.
 */
data class CityConfig(
    val id: Int,
    val name: String,
    val subtitle: String,
    val startLevel: Int,
    val endLevel: Int,
    val icon: String,
    val themeDescription: String,
    val difficultyLabel: String,
    val gridWidthRange: IntRange,
    val gridHeightRange: IntRange,
    val arrowCountRange: IntRange,
    val maxTurnsRange: IntRange,
    val symmetryPreference: Float = 0f, // 0.0 to 1.0
    val verticalPreference: Float = 0f, // 0.0 to 1.0
    val longSweepPreference: Float = 0f,  // 0.0 to 1.0
    val illusionPreference: Float = 0f, // 0.0 to 1.0 for visual illusions
    val densityPreference: Float = 0f // 0.0 to 1.0
) {
    val totalRoutes: Int get() = endLevel - startLevel + 1
}

object CityRepository {
    val cities = listOf(
        CityConfig(
            id = 1,
            name = "ZENDAI",
            subtitle = "THE BEGINNING",
            startLevel = 1,
            endLevel = 20,
            icon = "⛩️",
            themeDescription = "Calm beginnings & gentle introductory flows",
            difficultyLabel = "Easy • Early Flow",
            gridWidthRange = 8..10,
            gridHeightRange = 8..10,
            arrowCountRange = 4..8,
            maxTurnsRange = 1..2,
            symmetryPreference = 0.2f,
            illusionPreference = 0.5f,
            densityPreference = 0.4f
        ),
        CityConfig(
            id = 2,
            name = "SANDARA",
            subtitle = "DESERT WINDS",
            startLevel = 21,
            endLevel = 40,
            icon = "🏜️",
            themeDescription = "Desert winds, long sweeping paths & separated lanes",
            difficultyLabel = "Intermediate",
            gridWidthRange = 10..12,
            gridHeightRange = 10..12,
            arrowCountRange = 7..11,
            maxTurnsRange = 1..2,
            longSweepPreference = 0.7f,
            illusionPreference = 0.8f,
            densityPreference = 0.6f
        ),
        CityConfig(
            id = 3,
            name = "AZURIA",
            subtitle = "SEA BREEZE",
            startLevel = 41,
            endLevel = 60,
            icon = "🌊",
            themeDescription = "Water currents, flowing bends & tidal forks",
            difficultyLabel = "Intermediate • Advanced",
            gridWidthRange = 11..13,
            gridHeightRange = 11..13,
            arrowCountRange = 9..14,
            maxTurnsRange = 2..3
        ),
        CityConfig(
            id = 4,
            name = "VERDANIA",
            subtitle = "FOREST PATHS",
            startLevel = 61,
            endLevel = 80,
            icon = "🌲",
            themeDescription = "Branching tree structures, roots & organic paths",
            difficultyLabel = "Advanced",
            gridWidthRange = 12..14,
            gridHeightRange = 12..14,
            arrowCountRange = 11..16,
            maxTurnsRange = 2..3
        ),
        CityConfig(
            id = 5,
            name = "IGNIVAR",
            subtitle = "VOLCANO CORE",
            startLevel = 81,
            endLevel = 100,
            icon = "🌋",
            themeDescription = "Dense clusters, crossing routes & magma pressure",
            difficultyLabel = "Advanced • High Pressure",
            gridWidthRange = 12..14,
            gridHeightRange = 12..14,
            arrowCountRange = 13..18,
            maxTurnsRange = 2..3
        ),
        CityConfig(
            id = 6,
            name = "AERITH",
            subtitle = "SKY GARDENS",
            startLevel = 101,
            endLevel = 120,
            icon = "☁️",
            themeDescription = "Vertical towers, floating islands & ascending routes",
            difficultyLabel = "Advanced • Heights",
            gridWidthRange = 13..15,
            gridHeightRange = 13..15,
            arrowCountRange = 14..20,
            maxTurnsRange = 2..3,
            verticalPreference = 0.65f
        ),
        CityConfig(
            id = 7,
            name = "CRYSTALIA",
            subtitle = "CRYSTAL CAVES",
            startLevel = 121,
            endLevel = 140,
            icon = "💎",
            themeDescription = "Mirror symmetry, diamond structures & reflections",
            difficultyLabel = "Advanced • Expert",
            gridWidthRange = 13..15,
            gridHeightRange = 13..15,
            arrowCountRange = 16..22,
            maxTurnsRange = 2..3,
            symmetryPreference = 0.8f
        ),
        CityConfig(
            id = 8,
            name = "MECHTROPOLIS",
            subtitle = "GEAR REALMS",
            startLevel = 141,
            endLevel = 160,
            icon = "⚙️",
            themeDescription = "Clockwork gears, radial formations & interlocking teeth",
            difficultyLabel = "Expert",
            gridWidthRange = 14..16,
            gridHeightRange = 14..16,
            arrowCountRange = 17..24,
            maxTurnsRange = 2..4
        ),
        CityConfig(
            id = 9,
            name = "LUMENIA",
            subtitle = "LIGHT GROVES",
            startLevel = 161,
            endLevel = 180,
            icon = "✨",
            themeDescription = "Radiant light rays, long branching paths & luminous structures",
            difficultyLabel = "Expert • Radiance",
            gridWidthRange = 14..16,
            gridHeightRange = 14..16,
            arrowCountRange = 18..25,
            maxTurnsRange = 2..4
        ),
        CityConfig(
            id = 10,
            name = "ETERNIA",
            subtitle = "INFINITY MAZE",
            startLevel = 181,
            endLevel = 200,
            icon = "♾️",
            themeDescription = "Nested loops, spirals, deep dependencies & master compositions",
            difficultyLabel = "Master • Pinnacle",
            gridWidthRange = 15..16,
            gridHeightRange = 15..16,
            arrowCountRange = 20..27,
            maxTurnsRange = 3..4,
            symmetryPreference = 0.5f
        )
    )

    fun getCityForLevel(levelNumber: Int): CityConfig {
        val clamped = levelNumber.coerceIn(1, 200)
        return cities.find { clamped in it.startLevel..it.endLevel } ?: cities.last()
    }

    fun getRouteNumberInCity(levelNumber: Int): Int {
        val clamped = levelNumber.coerceIn(1, 200)
        val city = getCityForLevel(clamped)
        return clamped - city.startLevel + 1
    }

    fun getCityById(cityId: Int): CityConfig {
        return cities.find { it.id == cityId } ?: cities.first()
    }

    /**
     * Checks whether a level number corresponds to one of the 40 Anchor milestones (Routes 5, 10, 15, 20).
     */
    fun isAnchorLevel(levelNumber: Int): Boolean {
        if (levelNumber !in 1..200) return false
        val route = getRouteNumberInCity(levelNumber)
        return route == 5 || route == 10 || route == 15 || route == 20
    }
}
