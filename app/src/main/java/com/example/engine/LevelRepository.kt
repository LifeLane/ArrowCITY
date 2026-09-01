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
     * List of featured handcrafted levels for quick level selection.
     */
    val featuredLevels = listOf(
        1 to "First Flow",
        2 to "Turn Around",
        3 to "Crossroads",
        5 to "Zen Spiral",
        10 to "Trophy of Clarity 🏆",
        15 to "Diamond Heart 💎",
        20 to "Imperial Crown 👑",
        27 to "Anchor of Calm ⚓",
        35 to "Playful Kitty 🐱",
        40 to "Loyal Puppy 🐕",
        50 to "Bonsai Tree 🌲",
        75 to "Soaring Falcon 🦅",
        99 to "Golden Butterfly 🦋",
        120 to "Sacred Lotus 🌸",
        199 to "Grand Labyrinth 🌀"
    )

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
                    ArrowItem(2, listOf(GridPoint(4, 5), GridPoint(4, 1)), Direction.UP),
                    ArrowItem(3, listOf(GridPoint(1, 5), GridPoint(6, 5)), Direction.RIGHT)
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
                    ArrowItem(3, listOf(GridPoint(1, 4), GridPoint(4, 4), GridPoint(4, 1)), Direction.UP),
                    ArrowItem(4, listOf(GridPoint(6, 5), GridPoint(3, 5)), Direction.LEFT)
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
                    ArrowItem(1, listOf(GridPoint(2, 2), GridPoint(7, 2), GridPoint(7, 6)), Direction.DOWN),
                    ArrowItem(2, listOf(GridPoint(6, 7), GridPoint(2, 7), GridPoint(2, 3)), Direction.UP),
                    ArrowItem(3, listOf(GridPoint(3, 4), GridPoint(5, 4), GridPoint(5, 1)), Direction.UP),
                    ArrowItem(4, listOf(GridPoint(4, 5), GridPoint(1, 5)), Direction.LEFT),
                    ArrowItem(5, listOf(GridPoint(6, 3), GridPoint(8, 3)), Direction.RIGHT)
                ),
                maxDrops = 3,
                bannerText = "QUIET FLOW"
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
                    ArrowItem(1, listOf(GridPoint(2, 2), GridPoint(8, 2), GridPoint(8, 8)), Direction.DOWN),
                    ArrowItem(2, listOf(GridPoint(7, 7), GridPoint(3, 7), GridPoint(3, 3)), Direction.UP),
                    ArrowItem(3, listOf(GridPoint(4, 4), GridPoint(6, 4), GridPoint(6, 6)), Direction.DOWN),
                    ArrowItem(4, listOf(GridPoint(5, 5), GridPoint(4, 5), GridPoint(4, 1)), Direction.UP),
                    ArrowItem(5, listOf(GridPoint(1, 8), GridPoint(9, 8)), Direction.RIGHT),
                    ArrowItem(6, listOf(GridPoint(1, 1), GridPoint(1, 6)), Direction.DOWN)
                ),
                maxDrops = 3,
                bannerText = "CALM MIND"
            )
        )

        // Level 10: Trophy Cup 🏆 (Inspired by Screenshot 1)
        put(
            10,
            LevelData(
                levelNumber = 10,
                title = "Level 10",
                gridWidth = 14,
                gridHeight = 15,
                arrows = listOf(
                    // Cup rim & handles
                    ArrowItem(1, listOf(GridPoint(1, 4), GridPoint(1, 2), GridPoint(3, 2)), Direction.RIGHT),
                    ArrowItem(2, listOf(GridPoint(12, 4), GridPoint(12, 2), GridPoint(10, 2)), Direction.LEFT),
                    ArrowItem(3, listOf(GridPoint(3, 3), GridPoint(10, 3)), Direction.RIGHT),
                    ArrowItem(4, listOf(GridPoint(2, 5), GridPoint(2, 3), GridPoint(0, 3)), Direction.LEFT),
                    ArrowItem(5, listOf(GridPoint(11, 5), GridPoint(11, 3), GridPoint(13, 3)), Direction.RIGHT),
                    // Cup Body
                    ArrowItem(6, listOf(GridPoint(3, 4), GridPoint(3, 7), GridPoint(6, 7)), Direction.RIGHT),
                    ArrowItem(7, listOf(GridPoint(10, 4), GridPoint(10, 7), GridPoint(7, 7)), Direction.LEFT),
                    ArrowItem(8, listOf(GridPoint(4, 5), GridPoint(4, 4), GridPoint(6, 4)), Direction.RIGHT),
                    ArrowItem(9, listOf(GridPoint(9, 5), GridPoint(9, 4), GridPoint(7, 4)), Direction.LEFT),
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

        // Level 27: Anchor ⚓ (Inspired by Screenshot 2)
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
                    ArrowItem(3, listOf(GridPoint(4, 3), GridPoint(6, 3), GridPoint(6, 1)), Direction.UP),
                    ArrowItem(4, listOf(GridPoint(9, 3), GridPoint(7, 3), GridPoint(7, 1)), Direction.UP),
                    // Crossbar
                    ArrowItem(5, listOf(GridPoint(3, 5), GridPoint(1, 5), GridPoint(1, 4)), Direction.UP),
                    ArrowItem(6, listOf(GridPoint(10, 5), GridPoint(12, 5), GridPoint(12, 4)), Direction.UP),
                    ArrowItem(7, listOf(GridPoint(2, 6), GridPoint(11, 6)), Direction.RIGHT),
                    // Central shaft
                    ArrowItem(8, listOf(GridPoint(6, 4), GridPoint(6, 9)), Direction.DOWN),
                    ArrowItem(9, listOf(GridPoint(7, 4), GridPoint(7, 9)), Direction.DOWN),
                    // Left flukes
                    ArrowItem(10, listOf(GridPoint(6, 10), GridPoint(2, 10), GridPoint(2, 7)), Direction.UP),
                    ArrowItem(11, listOf(GridPoint(1, 11), GridPoint(1, 6)), Direction.UP),
                    ArrowItem(12, listOf(GridPoint(3, 11), GridPoint(3, 8)), Direction.UP),
                    // Right flukes
                    ArrowItem(13, listOf(GridPoint(7, 10), GridPoint(11, 10), GridPoint(11, 7)), Direction.UP),
                    ArrowItem(14, listOf(GridPoint(12, 11), GridPoint(12, 6)), Direction.UP),
                    ArrowItem(15, listOf(GridPoint(10, 11), GridPoint(10, 8)), Direction.UP),
                    // Bottom curve
                    ArrowItem(16, listOf(GridPoint(4, 12), GridPoint(9, 12)), Direction.RIGHT),
                    ArrowItem(17, listOf(GridPoint(5, 13), GridPoint(8, 13)), Direction.RIGHT)
                ),
                maxDrops = 4,
                isSilhouette = true,
                silhouetteIcon = "⚓",
                bannerText = "10000+ LEVELS"
            )
        )

        // Level 40: Loyal Dog / Puppy 🐕 (Inspired by Screenshot 3)
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
                    ArrowItem(2, listOf(GridPoint(0, 4), GridPoint(2, 4), GridPoint(2, 2)), Direction.UP),
                    // Back & Body
                    ArrowItem(3, listOf(GridPoint(2, 5), GridPoint(7, 5)), Direction.RIGHT),
                    ArrowItem(4, listOf(GridPoint(3, 6), GridPoint(7, 6)), Direction.RIGHT),
                    ArrowItem(5, listOf(GridPoint(2, 7), GridPoint(8, 7)), Direction.RIGHT),
                    ArrowItem(6, listOf(GridPoint(2, 8), GridPoint(7, 8)), Direction.RIGHT),
                    // Head & Snout
                    ArrowItem(7, listOf(GridPoint(8, 4), GridPoint(8, 1), GridPoint(10, 1)), Direction.RIGHT),
                    ArrowItem(8, listOf(GridPoint(10, 2), GridPoint(13, 2), GridPoint(13, 3)), Direction.DOWN),
                    ArrowItem(9, listOf(GridPoint(10, 3), GridPoint(12, 3)), Direction.RIGHT),
                    ArrowItem(10, listOf(GridPoint(10, 4), GridPoint(14, 4)), Direction.RIGHT),
                    ArrowItem(11, listOf(GridPoint(9, 5), GridPoint(12, 5), GridPoint(12, 6)), Direction.DOWN),
                    // Front Legs
                    ArrowItem(12, listOf(GridPoint(9, 9), GridPoint(9, 13), GridPoint(11, 13)), Direction.RIGHT),
                    ArrowItem(13, listOf(GridPoint(11, 10), GridPoint(11, 14)), Direction.DOWN),
                    ArrowItem(14, listOf(GridPoint(10, 11), GridPoint(10, 14)), Direction.DOWN),
                    // Hind Legs
                    ArrowItem(15, listOf(GridPoint(2, 9), GridPoint(2, 13), GridPoint(4, 13)), Direction.RIGHT),
                    ArrowItem(16, listOf(GridPoint(4, 10), GridPoint(4, 14)), Direction.DOWN),
                    ArrowItem(17, listOf(GridPoint(3, 11), GridPoint(3, 14)), Direction.DOWN),
                    // Belly
                    ArrowItem(18, listOf(GridPoint(5, 9), GridPoint(8, 9)), Direction.RIGHT)
                ),
                maxDrops = 5,
                isSilhouette = true,
                silhouetteIcon = "🐕",
                bannerText = "TAP TO CLEAR"
            )
        )

        // Level 99: Butterfly 🦋 (Inspired by Screenshot 4)
        put(
            99,
            LevelData(
                levelNumber = 99,
                title = "Level 99",
                gridWidth = 15,
                gridHeight = 15,
                arrows = listOf(
                    // Left Wing top
                    ArrowItem(1, listOf(GridPoint(1, 2), GridPoint(5, 2)), Direction.RIGHT),
                    ArrowItem(2, listOf(GridPoint(2, 1), GridPoint(2, 6)), Direction.DOWN),
                    ArrowItem(3, listOf(GridPoint(3, 1), GridPoint(3, 7)), Direction.DOWN),
                    ArrowItem(4, listOf(GridPoint(4, 1), GridPoint(4, 8)), Direction.DOWN),
                    ArrowItem(5, listOf(GridPoint(1, 4), GridPoint(1, 8), GridPoint(3, 8)), Direction.RIGHT),
                    // Right Wing top
                    ArrowItem(6, listOf(GridPoint(13, 2), GridPoint(9, 2)), Direction.LEFT),
                    ArrowItem(7, listOf(GridPoint(12, 1), GridPoint(12, 6)), Direction.DOWN),
                    ArrowItem(8, listOf(GridPoint(11, 1), GridPoint(11, 7)), Direction.DOWN),
                    ArrowItem(9, listOf(GridPoint(10, 1), GridPoint(10, 8)), Direction.DOWN),
                    ArrowItem(10, listOf(GridPoint(13, 4), GridPoint(13, 8), GridPoint(11, 8)), Direction.LEFT),
                    // Center Antenna & Body
                    ArrowItem(11, listOf(GridPoint(6, 3), GridPoint(6, 1)), Direction.UP),
                    ArrowItem(12, listOf(GridPoint(8, 3), GridPoint(8, 1)), Direction.UP),
                    ArrowItem(13, listOf(GridPoint(7, 3), GridPoint(7, 12)), Direction.DOWN),
                    // Left Wing bottom
                    ArrowItem(14, listOf(GridPoint(2, 9), GridPoint(6, 9)), Direction.RIGHT),
                    ArrowItem(15, listOf(GridPoint(2, 11), GridPoint(5, 11)), Direction.RIGHT),
                    ArrowItem(16, listOf(GridPoint(3, 12), GridPoint(6, 12)), Direction.RIGHT),
                    ArrowItem(17, listOf(GridPoint(4, 13), GridPoint(6, 13)), Direction.RIGHT),
                    // Right Wing bottom
                    ArrowItem(18, listOf(GridPoint(12, 9), GridPoint(8, 9)), Direction.LEFT),
                    ArrowItem(19, listOf(GridPoint(12, 11), GridPoint(9, 11)), Direction.LEFT),
                    ArrowItem(20, listOf(GridPoint(11, 12), GridPoint(8, 12)), Direction.LEFT),
                    ArrowItem(21, listOf(GridPoint(10, 13), GridPoint(8, 13)), Direction.LEFT)
                ),
                maxDrops = 3,
                isSilhouette = true,
                silhouetteIcon = "🦋",
                bannerText = "BETTER SLEEP"
            )
        )

        // Level 199: Grand Maze Matrix 🌀 (Inspired by Screenshot 5)
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
                    ArrowItem(3, listOf(GridPoint(1, 2), GridPoint(1, 6), GridPoint(4, 6)), Direction.RIGHT),
                    ArrowItem(4, listOf(GridPoint(2, 3), GridPoint(5, 3)), Direction.RIGHT),
                    ArrowItem(5, listOf(GridPoint(3, 4), GridPoint(5, 4), GridPoint(5, 2)), Direction.UP),
                    ArrowItem(6, listOf(GridPoint(7, 2), GridPoint(10, 2)), Direction.RIGHT),
                    ArrowItem(7, listOf(GridPoint(11, 2), GridPoint(11, 5)), Direction.DOWN),
                    ArrowItem(8, listOf(GridPoint(12, 2), GridPoint(12, 6)), Direction.DOWN),
                    ArrowItem(9, listOf(GridPoint(8, 3), GridPoint(8, 6), GridPoint(6, 6)), Direction.LEFT),
                    ArrowItem(10, listOf(GridPoint(9, 4), GridPoint(9, 6)), Direction.DOWN),
                    ArrowItem(11, listOf(GridPoint(1, 8), GridPoint(5, 8)), Direction.RIGHT),
                    ArrowItem(12, listOf(GridPoint(6, 7), GridPoint(12, 7)), Direction.RIGHT),
                    ArrowItem(13, listOf(GridPoint(2, 9), GridPoint(2, 12), GridPoint(5, 12)), Direction.RIGHT),
                    ArrowItem(14, listOf(GridPoint(3, 10), GridPoint(5, 10)), Direction.RIGHT),
                    ArrowItem(15, listOf(GridPoint(4, 11), GridPoint(4, 9)), Direction.UP),
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
