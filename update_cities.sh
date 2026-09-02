cat << 'INNER_EOF' > app/src/main/java/com/example/model/CityModels.kt
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
            name = "SPACE CITY",
            subtitle = "OUTER SPACE",
            startLevel = 1,
            endLevel = 20,
            icon = "🚀",
            themeDescription = "Orbital paths, planets, and long-range dependencies.",
            difficultyLabel = "Easy • Medium",
            gridWidthRange = 8..10,
            gridHeightRange = 8..10,
            arrowCountRange = 4..8,
            maxTurnsRange = 1..2
        ),
        CityConfig(
            id = 2,
            name = "PHYSICS CITY",
            subtitle = "MECHANICS & MOTION",
            startLevel = 21,
            endLevel = 40,
            icon = "⚙️",
            themeDescription = "Momentum, force, and multi-step consequences.",
            difficultyLabel = "Medium • Hard",
            gridWidthRange = 10..12,
            gridHeightRange = 10..12,
            arrowCountRange = 7..11,
            maxTurnsRange = 1..2
        ),
        CityConfig(
            id = 3,
            name = "GEOGRAPHY CITY",
            subtitle = "EARTH NETWORKS",
            startLevel = 41,
            endLevel = 60,
            icon = "🌍",
            themeDescription = "Route networks, hubs, bottlenecks, and alternative routes.",
            difficultyLabel = "Medium • Hard",
            gridWidthRange = 11..13,
            gridHeightRange = 11..13,
            arrowCountRange = 9..14,
            maxTurnsRange = 2..3
        ),
        CityConfig(
            id = 4,
            name = "ANIMAL CITY",
            subtitle = "ANIMAL KINGDOM",
            startLevel = 61,
            endLevel = 80,
            icon = "🦁",
            themeDescription = "Pack, prey, and habitat routing. Managing interconnected groups.",
            difficultyLabel = "Hard",
            gridWidthRange = 12..14,
            gridHeightRange = 12..14,
            arrowCountRange = 11..16,
            maxTurnsRange = 2..3
        ),
        CityConfig(
            id = 5,
            name = "NATURE CITY",
            subtitle = "FORESTS & RIVERS",
            startLevel = 81,
            endLevel = 100,
            icon = "🌲",
            themeDescription = "Natural flow, rivers, waterfalls, and branching paths.",
            difficultyLabel = "Hard • Deep",
            gridWidthRange = 12..14,
            gridHeightRange = 12..14,
            arrowCountRange = 13..18,
            maxTurnsRange = 2..3
        ),
        CityConfig(
            id = 6,
            name = "OCEAN CITY",
            subtitle = "UNDERWATER EXPLORATION",
            startLevel = 101,
            endLevel = 120,
            icon = "🌊",
            themeDescription = "Tide and current. Direction-based network reasoning.",
            difficultyLabel = "Hard • Deep",
            gridWidthRange = 13..15,
            gridHeightRange = 13..15,
            arrowCountRange = 14..20,
            maxTurnsRange = 2..3
        ),
        CityConfig(
            id = 7,
            name = "HISTORY CITY",
            subtitle = "HUMAN CIVILIZATION",
            startLevel = 121,
            endLevel = 140,
            icon = "🏛️",
            themeDescription = "Era progression. Progressively more complex historical structures.",
            difficultyLabel = "Hard • Deep",
            gridWidthRange = 13..15,
            gridHeightRange = 13..15,
            arrowCountRange = 16..22,
            maxTurnsRange = 2..3
        ),
        CityConfig(
            id = 8,
            name = "TECHNOLOGY CITY",
            subtitle = "SYSTEM NETWORKS",
            startLevel = 141,
            endLevel = 160,
            icon = "🤖",
            themeDescription = "System dependency graph. Paths behaving like digital logic.",
            difficultyLabel = "Deep",
            gridWidthRange = 14..16,
            gridHeightRange = 14..16,
            arrowCountRange = 17..24,
            maxTurnsRange = 2..4
        ),
        CityConfig(
            id = 9,
            name = "SCIENCE CITY",
            subtitle = "BIOLOGY & HUMAN BODY",
            startLevel = 161,
            endLevel = 180,
            icon = "🧬",
            themeDescription = "Cascade effects. One local decision produces deep consequences.",
            difficultyLabel = "Deep • Expert",
            gridWidthRange = 14..16,
            gridHeightRange = 14..16,
            arrowCountRange = 18..25,
            maxTurnsRange = 2..4
        ),
        CityConfig(
            id = 10,
            name = "COSMIC CITY",
            subtitle = "MASTER UNIVERSE",
            startLevel = 181,
            endLevel = 200,
            icon = "🌌",
            themeDescription = "Everything matters. Combines the best strategic patterns of all cities.",
            difficultyLabel = "Expert • Master",
            gridWidthRange = 15..16,
            gridHeightRange = 15..16,
            arrowCountRange = 20..27,
            maxTurnsRange = 3..4
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
INNER_EOF
