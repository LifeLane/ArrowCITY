package com.example.engine

import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GridPoint
import com.example.model.LevelData

object LevelRepository {

    /**
     * Retrieves LevelData for any level 1 to 10000+.
     */
    fun getLevel(levelNumber: Int): LevelData {
        return curatedLevels[levelNumber] ?: ProceduralGenerator.generateLevel(levelNumber)
    }

    /**
     * Structure for artistic silhouette featured levels.
     */
    data class SilhouetteInfo(
        val levelNumber: Int,
        val title: String,
        val icon: String,
        val subtitle: String,
        val difficulty: String
    )

    /**
     * List of handcrafted silhouette levels for the Art Silhouettes tab.
     */
    val silhouetteLevels = listOf(
        SilhouetteInfo(10, "Trophy of Clarity", "🏆", "Golden Victory Goblet", "Gentle"),
        SilhouetteInfo(15, "Diamond Heart", "💎", "Luminescent Gemstone", "Gentle"),
        SilhouetteInfo(20, "Imperial Crown", "👑", "Regal Golden Tiara", "Moderate"),
        SilhouetteInfo(27, "Anchor of Calm", "⚓", "Harbor of Tranquility", "Moderate"),
        SilhouetteInfo(35, "Playful Kitty", "🐱", "Whimsical Feline Silhouette", "Moderate"),
        SilhouetteInfo(40, "Loyal Puppy", "🐕", "Faithful Companion", "Challenging"),
        SilhouetteInfo(50, "Bonsai Tree", "🌲", "Zen Living Sculpture", "Challenging"),
        SilhouetteInfo(60, "Swimming Koi", "🐟", "Aquatic Harmony", "Challenging"),
        SilhouetteInfo(75, "Soaring Falcon", "🦅", "Wings of Freedom", "Master"),
        SilhouetteInfo(80, "Warm Coffee", "☕", "Steaming Morning Comfort", "Master"),
        SilhouetteInfo(90, "Origami Swan", "🦢", "Geometric Grace", "Master"),
        SilhouetteInfo(99, "Golden Butterfly", "🦋", "Wings of Transformation", "Master"),
        SilhouetteInfo(120, "Sacred Lotus", "🌸", "Pristine Blooming Petals", "Grandmaster"),
        SilhouetteInfo(150, "Compass Star", "🧭", "Guiding Celestial Way", "Grandmaster"),
        SilhouetteInfo(180, "Crescent Moon", "🌙", "Nocturnal Serenity", "Grandmaster"),
        SilhouetteInfo(199, "Grand Labyrinth", "🌀", "Vortex of Infinite Flow", "Legendary"),
        SilhouetteInfo(250, "Phoenix Ascending", "🔥", "Reborn from Pure Light", "Legendary"),
        SilhouetteInfo(300, "Mythic Dragon", "🐉", "Ancient Celestial Serpent", "Legendary"),
        SilhouetteInfo(500, "Celestial Castle", "🏰", "Citadel of Starlight", "Mythic")
    )

    val featuredLevels = silhouetteLevels.map { it.levelNumber to "${it.title} ${it.icon}" }

    private val curatedLevels = mutableMapOf<Int, LevelData>().apply {
        // Level 1: Gentle Introduction - 3 straight arrows
        put(
            1,
            LevelData(
                levelNumber = 1,
                title = "Level 1",
                gridWidth = 8,
                gridHeight = 8,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(2, 2), GridPoint(6, 2)), Direction.RIGHT),
                    ArrowItem(2, listOf(GridPoint(4, 5), GridPoint(4, 3)), Direction.UP),
                    ArrowItem(3, listOf(GridPoint(1, 6), GridPoint(6, 6)), Direction.RIGHT)
                ),
                maxDrops = 3,
                bannerText = "TAP TO CLEAR"
            )
        )

        // Level 2: Corner Turns
        put(
            2,
            LevelData(
                levelNumber = 2,
                title = "Level 2",
                gridWidth = 8,
                gridHeight = 8,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(2, 6), GridPoint(2, 2), GridPoint(6, 2)), Direction.RIGHT),
                    ArrowItem(2, listOf(GridPoint(5, 6), GridPoint(5, 3)), Direction.UP),
                    ArrowItem(3, listOf(GridPoint(3, 4), GridPoint(4, 4), GridPoint(4, 3)), Direction.UP),
                    ArrowItem(4, listOf(GridPoint(6, 5), GridPoint(6, 3)), Direction.UP)
                ),
                maxDrops = 3,
                bannerText = "EYE COMFORT"
            )
        )

        // Level 3: Interlocking Square
        put(
            3,
            LevelData(
                levelNumber = 3,
                title = "Level 3",
                gridWidth = 9,
                gridHeight = 9,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(2, 2), GridPoint(7, 2), GridPoint(7, 5)), Direction.DOWN),
                    ArrowItem(2, listOf(GridPoint(6, 7), GridPoint(2, 7), GridPoint(2, 3)), Direction.UP),
                    ArrowItem(3, listOf(GridPoint(3, 4), GridPoint(5, 4), GridPoint(5, 3)), Direction.UP),
                    ArrowItem(4, listOf(GridPoint(4, 5), GridPoint(3, 5)), Direction.LEFT),
                    ArrowItem(5, listOf(GridPoint(8, 3), GridPoint(8, 1)), Direction.UP)
                ),
                maxDrops = 3,
                bannerText = "QUIET FLOW"
            )
        )

        // Level 4: Windmill Cross
        put(
            4,
            LevelData(
                levelNumber = 4,
                title = "Level 4",
                gridWidth = 9,
                gridHeight = 9,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(4, 2), GridPoint(4, 3), GridPoint(7, 3)), Direction.RIGHT),
                    ArrowItem(2, listOf(GridPoint(6, 4), GridPoint(5, 4), GridPoint(5, 7)), Direction.DOWN),
                    ArrowItem(3, listOf(GridPoint(4, 6), GridPoint(4, 5), GridPoint(1, 5)), Direction.LEFT),
                    ArrowItem(4, listOf(GridPoint(2, 4), GridPoint(3, 4), GridPoint(3, 1)), Direction.UP),
                    ArrowItem(5, listOf(GridPoint(1, 1), GridPoint(1, 3)), Direction.DOWN),
                    ArrowItem(6, listOf(GridPoint(7, 7), GridPoint(7, 5)), Direction.UP)
                ),
                maxDrops = 3,
                bannerText = "CALM MIND"
            )
        )

        // Level 5: Spiral
        put(
            5,
            LevelData(
                levelNumber = 5,
                title = "Level 5",
                gridWidth = 10,
                gridHeight = 10,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(2, 2), GridPoint(8, 2), GridPoint(8, 7)), Direction.DOWN),
                    ArrowItem(2, listOf(GridPoint(7, 7), GridPoint(3, 7), GridPoint(3, 3)), Direction.UP),
                    ArrowItem(3, listOf(GridPoint(5, 4), GridPoint(6, 4), GridPoint(6, 6)), Direction.DOWN),
                    ArrowItem(4, listOf(GridPoint(5, 5), GridPoint(4, 5), GridPoint(4, 3)), Direction.UP),
                    ArrowItem(5, listOf(GridPoint(1, 8), GridPoint(9, 8)), Direction.RIGHT),
                    ArrowItem(6, listOf(GridPoint(1, 1), GridPoint(1, 6)), Direction.DOWN)
                ),
                maxDrops = 3,
                bannerText = "CALM MIND"
            )
        )

        // Level 10: Trophy Cup 🏆
        put(
            10,
            LevelData(
                levelNumber = 10,
                title = "Level 10",
                gridWidth = 14,
                gridHeight = 15,
                arrows = listOf(
                    // Cup rim & handles
                    ArrowItem(1, listOf(GridPoint(3, 2), GridPoint(0, 2)), Direction.LEFT),
                    ArrowItem(2, listOf(GridPoint(10, 2), GridPoint(13, 2)), Direction.RIGHT),
                    ArrowItem(3, listOf(GridPoint(4, 3), GridPoint(9, 3)), Direction.RIGHT),
                    ArrowItem(4, listOf(GridPoint(2, 5), GridPoint(2, 4), GridPoint(0, 4)), Direction.LEFT),
                    ArrowItem(5, listOf(GridPoint(11, 5), GridPoint(11, 4), GridPoint(13, 4)), Direction.RIGHT),
                    // Cup Body
                    ArrowItem(6, listOf(GridPoint(5, 7), GridPoint(3, 7), GridPoint(3, 4)), Direction.UP),
                    ArrowItem(7, listOf(GridPoint(8, 7), GridPoint(10, 7), GridPoint(10, 4)), Direction.UP),
                    ArrowItem(8, listOf(GridPoint(6, 4), GridPoint(4, 4)), Direction.LEFT),
                    ArrowItem(9, listOf(GridPoint(7, 4), GridPoint(9, 4)), Direction.RIGHT),
                    ArrowItem(10, listOf(GridPoint(5, 6), GridPoint(8, 6)), Direction.RIGHT),
                    ArrowItem(11, listOf(GridPoint(4, 8), GridPoint(9, 8)), Direction.RIGHT),
                    // Stem & Base
                    ArrowItem(12, listOf(GridPoint(6, 9), GridPoint(6, 11), GridPoint(4, 11)), Direction.LEFT),
                    ArrowItem(13, listOf(GridPoint(7, 9), GridPoint(7, 11), GridPoint(9, 11)), Direction.RIGHT),
                    ArrowItem(14, listOf(GridPoint(3, 12), GridPoint(10, 12)), Direction.RIGHT),
                    ArrowItem(15, listOf(GridPoint(2, 13), GridPoint(11, 13)), Direction.RIGHT),
                    ArrowItem(16, listOf(GridPoint(1, 14), GridPoint(12, 14)), Direction.RIGHT)
                ),
                maxDrops = 3,
                isSilhouette = true,
                silhouetteIcon = "🏆",
                bannerText = "EYE COMFORT"
            )
        )

        // Level 27: Anchor ⚓
        put(
            27,
            LevelData(
                levelNumber = 27,
                title = "Level 27",
                gridWidth = 14,
                gridHeight = 15,
                arrows = listOf(
                    // Top ring / crown
                    ArrowItem(1, listOf(GridPoint(6, 2), GridPoint(5, 2), GridPoint(5, 0)), Direction.UP),
                    ArrowItem(2, listOf(GridPoint(7, 2), GridPoint(8, 2), GridPoint(8, 0)), Direction.UP),
                    ArrowItem(3, listOf(GridPoint(3, 3), GridPoint(4, 3), GridPoint(4, 0)), Direction.UP),
                    ArrowItem(4, listOf(GridPoint(10, 3), GridPoint(9, 3), GridPoint(9, 0)), Direction.UP),
                    // Crossbar
                    ArrowItem(5, listOf(GridPoint(3, 5), GridPoint(1, 5), GridPoint(1, 4)), Direction.UP),
                    ArrowItem(6, listOf(GridPoint(10, 5), GridPoint(12, 5), GridPoint(12, 4)), Direction.UP),
                    ArrowItem(7, listOf(GridPoint(2, 6), GridPoint(5, 6)), Direction.LEFT),
                    ArrowItem(8, listOf(GridPoint(11, 6), GridPoint(8, 6)), Direction.RIGHT),
                    // Central shaft
                    ArrowItem(9, listOf(GridPoint(6, 4), GridPoint(6, 9)), Direction.DOWN),
                    ArrowItem(10, listOf(GridPoint(7, 4), GridPoint(7, 9)), Direction.DOWN),
                    // Left flukes
                    ArrowItem(11, listOf(GridPoint(5, 10), GridPoint(2, 10), GridPoint(2, 7)), Direction.UP),
                    ArrowItem(12, listOf(GridPoint(1, 11), GridPoint(1, 7)), Direction.UP),
                    ArrowItem(13, listOf(GridPoint(3, 9), GridPoint(3, 7)), Direction.UP),
                    // Right flukes
                    ArrowItem(14, listOf(GridPoint(8, 10), GridPoint(11, 10), GridPoint(11, 7)), Direction.UP),
                    ArrowItem(15, listOf(GridPoint(12, 11), GridPoint(12, 7)), Direction.UP),
                    ArrowItem(16, listOf(GridPoint(10, 9), GridPoint(10, 7)), Direction.UP),
                    // Bottom curve
                    ArrowItem(17, listOf(GridPoint(4, 12), GridPoint(9, 12)), Direction.RIGHT),
                    ArrowItem(18, listOf(GridPoint(5, 13), GridPoint(8, 13)), Direction.RIGHT)
                ),
                maxDrops = 4,
                isSilhouette = true,
                silhouetteIcon = "⚓",
                bannerText = "10000+ LEVELS"
            )
        )

        // Level 40: Loyal Dog / Puppy 🐕
        put(
            40,
            LevelData(
                levelNumber = 40,
                title = "Level 40",
                gridWidth = 15,
                gridHeight = 15,
                arrows = listOf(
                    // Tail
                    ArrowItem(1, listOf(GridPoint(1, 5), GridPoint(1, 3)), Direction.UP),
                    ArrowItem(2, listOf(GridPoint(0, 4), GridPoint(0, 2), GridPoint(2, 2)), Direction.RIGHT),
                    // Back & Body
                    ArrowItem(3, listOf(GridPoint(3, 5), GridPoint(7, 5)), Direction.RIGHT),
                    ArrowItem(4, listOf(GridPoint(3, 6), GridPoint(7, 6)), Direction.RIGHT),
                    ArrowItem(5, listOf(GridPoint(2, 7), GridPoint(8, 7)), Direction.RIGHT),
                    ArrowItem(6, listOf(GridPoint(2, 8), GridPoint(7, 8)), Direction.RIGHT),
                    // Head & Snout
                    ArrowItem(7, listOf(GridPoint(8, 4), GridPoint(8, 1), GridPoint(10, 1)), Direction.RIGHT),
                    ArrowItem(8, listOf(GridPoint(11, 2), GridPoint(13, 2), GridPoint(13, 3)), Direction.DOWN),
                    ArrowItem(9, listOf(GridPoint(10, 3), GridPoint(12, 3)), Direction.RIGHT),
                    ArrowItem(10, listOf(GridPoint(10, 4), GridPoint(14, 4)), Direction.RIGHT),
                    ArrowItem(11, listOf(GridPoint(9, 5), GridPoint(12, 5), GridPoint(12, 6)), Direction.DOWN),
                    // Front Legs
                    ArrowItem(12, listOf(GridPoint(9, 9), GridPoint(9, 14)), Direction.DOWN),
                    ArrowItem(13, listOf(GridPoint(12, 10), GridPoint(12, 14)), Direction.DOWN),
                    ArrowItem(14, listOf(GridPoint(10, 11), GridPoint(10, 14)), Direction.DOWN),
                    // Hind Legs
                    ArrowItem(15, listOf(GridPoint(2, 9), GridPoint(2, 14)), Direction.DOWN),
                    ArrowItem(16, listOf(GridPoint(5, 10), GridPoint(5, 14)), Direction.DOWN),
                    ArrowItem(17, listOf(GridPoint(3, 11), GridPoint(3, 14)), Direction.DOWN),
                    // Belly
                    ArrowItem(18, listOf(GridPoint(6, 9), GridPoint(8, 9)), Direction.RIGHT)
                ),
                maxDrops = 5,
                isSilhouette = true,
                silhouetteIcon = "🐕",
                bannerText = "TAP TO CLEAR"
            )
        )

        // Level 99: Butterfly 🦋
        put(
            99,
            LevelData(
                levelNumber = 99,
                title = "Level 99",
                gridWidth = 15,
                gridHeight = 15,
                arrows = listOf(
                    // Left Wing top
                    ArrowItem(1, listOf(GridPoint(5, 2), GridPoint(1, 2)), Direction.LEFT),
                    ArrowItem(2, listOf(GridPoint(2, 3), GridPoint(2, 6)), Direction.DOWN),
                    ArrowItem(3, listOf(GridPoint(3, 3), GridPoint(3, 7)), Direction.DOWN),
                    ArrowItem(4, listOf(GridPoint(4, 3), GridPoint(4, 8)), Direction.DOWN),
                    ArrowItem(5, listOf(GridPoint(3, 8), GridPoint(1, 8), GridPoint(1, 4)), Direction.UP),
                    // Right Wing top
                    ArrowItem(6, listOf(GridPoint(9, 2), GridPoint(13, 2)), Direction.RIGHT),
                    ArrowItem(7, listOf(GridPoint(12, 3), GridPoint(12, 6)), Direction.DOWN),
                    ArrowItem(8, listOf(GridPoint(11, 3), GridPoint(11, 7)), Direction.DOWN),
                    ArrowItem(9, listOf(GridPoint(10, 3), GridPoint(10, 8)), Direction.DOWN),
                    ArrowItem(10, listOf(GridPoint(11, 8), GridPoint(13, 8), GridPoint(13, 4)), Direction.UP),
                    // Center Antenna & Body
                    ArrowItem(11, listOf(GridPoint(6, 3), GridPoint(6, 1)), Direction.UP),
                    ArrowItem(12, listOf(GridPoint(8, 3), GridPoint(8, 1)), Direction.UP),
                    ArrowItem(13, listOf(GridPoint(7, 3), GridPoint(7, 12)), Direction.DOWN),
                    // Left Wing bottom
                    ArrowItem(14, listOf(GridPoint(6, 9), GridPoint(2, 9)), Direction.LEFT),
                    ArrowItem(15, listOf(GridPoint(5, 11), GridPoint(2, 11)), Direction.LEFT),
                    ArrowItem(16, listOf(GridPoint(6, 12), GridPoint(3, 12)), Direction.LEFT),
                    ArrowItem(17, listOf(GridPoint(6, 13), GridPoint(4, 13)), Direction.LEFT),
                    // Right Wing bottom
                    ArrowItem(18, listOf(GridPoint(8, 9), GridPoint(12, 9)), Direction.RIGHT),
                    ArrowItem(19, listOf(GridPoint(9, 11), GridPoint(12, 11)), Direction.RIGHT),
                    ArrowItem(20, listOf(GridPoint(8, 12), GridPoint(11, 12)), Direction.RIGHT),
                    ArrowItem(21, listOf(GridPoint(8, 13), GridPoint(10, 13)), Direction.RIGHT)
                ),
                maxDrops = 3,
                isSilhouette = true,
                silhouetteIcon = "🦋",
                bannerText = "BETTER SLEEP"
            )
        )

        // Level 199: Grand Maze Matrix 🌀
        put(
            199,
            LevelData(
                levelNumber = 199,
                title = "Level 199",
                gridWidth = 14,
                gridHeight = 14,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(1, 1), GridPoint(6, 1)), Direction.RIGHT),
                    ArrowItem(2, listOf(GridPoint(7, 1), GridPoint(12, 1)), Direction.RIGHT),
                    ArrowItem(3, listOf(GridPoint(4, 6), GridPoint(1, 6), GridPoint(1, 2)), Direction.UP),
                    ArrowItem(4, listOf(GridPoint(2, 3), GridPoint(5, 3)), Direction.RIGHT),
                    ArrowItem(5, listOf(GridPoint(3, 4), GridPoint(5, 4), GridPoint(5, 5)), Direction.DOWN),
                    ArrowItem(6, listOf(GridPoint(7, 2), GridPoint(10, 2)), Direction.RIGHT),
                    ArrowItem(7, listOf(GridPoint(11, 2), GridPoint(11, 5)), Direction.DOWN),
                    ArrowItem(8, listOf(GridPoint(12, 2), GridPoint(12, 6)), Direction.DOWN),
                    ArrowItem(9, listOf(GridPoint(8, 3), GridPoint(8, 6), GridPoint(6, 6)), Direction.LEFT),
                    ArrowItem(10, listOf(GridPoint(9, 4), GridPoint(9, 6)), Direction.DOWN),
                    ArrowItem(11, listOf(GridPoint(1, 8), GridPoint(5, 8)), Direction.RIGHT),
                    ArrowItem(12, listOf(GridPoint(6, 7), GridPoint(12, 7)), Direction.RIGHT),
                    ArrowItem(13, listOf(GridPoint(2, 9), GridPoint(2, 12), GridPoint(5, 12)), Direction.RIGHT),
                    ArrowItem(14, listOf(GridPoint(3, 10), GridPoint(5, 10)), Direction.RIGHT),
                    ArrowItem(15, listOf(GridPoint(3, 11), GridPoint(5, 11)), Direction.RIGHT),
                    ArrowItem(16, listOf(GridPoint(6, 8), GridPoint(6, 12)), Direction.DOWN),
                    ArrowItem(17, listOf(GridPoint(7, 9), GridPoint(7, 12)), Direction.DOWN),
                    ArrowItem(18, listOf(GridPoint(8, 8), GridPoint(8, 11), GridPoint(10, 11)), Direction.RIGHT),
                    ArrowItem(19, listOf(GridPoint(11, 8), GridPoint(11, 12)), Direction.DOWN),
                    ArrowItem(20, listOf(GridPoint(12, 8), GridPoint(12, 12)), Direction.DOWN),
                    ArrowItem(21, listOf(GridPoint(1, 13), GridPoint(12, 13)), Direction.RIGHT)
                ),
                maxDrops = 3,
                isSilhouette = false,
                silhouetteIcon = "🌀",
                bannerText = "CHALLENGE ON"
            )
        )
    }
}
