package com.example.engine

import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GridPoint
import com.example.model.LevelData

/**
 * Handcrafted onboarding and introductory anchor levels.
 * Remaining anchor levels are generated deterministically by ProceduralGenerator.generateAnchorLevel.
 */
object CuratedLevels {

    val curatedMap: Map<Int, LevelData> by lazy {
        buildMap {
            // Level 1: Zendai Route 1 - Introduction
            put(1, LevelData(
                levelNumber = 1,
                title = "City 1 • Route 1 • First Launch",
                gridWidth = 8, gridHeight = 8,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(2, 2), GridPoint(6, 2)), Direction.RIGHT),
                    ArrowItem(2, listOf(GridPoint(4, 5), GridPoint(4, 3)), Direction.UP),
                    ArrowItem(3, listOf(GridPoint(1, 6), GridPoint(6, 6)), Direction.RIGHT)
                ),
                maxDrops = 3, bannerText = "TAP TO CLEAR"
            ))

            // Level 2: Zendai Route 2 - Corner Turns
            put(2, LevelData(
                levelNumber = 2,
                title = "City 1 • Route 2 • Moon Path",
                gridWidth = 8, gridHeight = 8,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(2, 6), GridPoint(2, 2), GridPoint(6, 2)), Direction.RIGHT),
                    ArrowItem(2, listOf(GridPoint(5, 6), GridPoint(5, 3)), Direction.UP),
                    ArrowItem(3, listOf(GridPoint(3, 4), GridPoint(4, 4), GridPoint(4, 3)), Direction.UP),
                    ArrowItem(4, listOf(GridPoint(6, 5), GridPoint(6, 3)), Direction.UP)
                ),
                maxDrops = 3, bannerText = "EYE COMFORT"
            ))

            // Level 3: Zendai Route 3 - Interlocking Square
            put(3, LevelData(
                levelNumber = 3,
                title = "City 1 • Route 3 • Satellite",
                gridWidth = 9, gridHeight = 9,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(2, 2), GridPoint(7, 2), GridPoint(7, 5)), Direction.DOWN),
                    ArrowItem(2, listOf(GridPoint(6, 7), GridPoint(2, 7), GridPoint(2, 3)), Direction.UP),
                    ArrowItem(3, listOf(GridPoint(3, 4), GridPoint(5, 4), GridPoint(5, 3)), Direction.UP),
                    ArrowItem(4, listOf(GridPoint(4, 5), GridPoint(3, 5)), Direction.LEFT),
                    ArrowItem(5, listOf(GridPoint(8, 3), GridPoint(8, 1)), Direction.UP)
                ),
                maxDrops = 3, bannerText = "QUIET FLOW"
            ))

            // Level 4: Zendai Route 4 - Windmill Cross
            put(4, LevelData(
                levelNumber = 4,
                title = "City 1 • Route 4 • Gravity Well",
                gridWidth = 9, gridHeight = 9,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(4, 2), GridPoint(4, 3), GridPoint(7, 3)), Direction.RIGHT),
                    ArrowItem(2, listOf(GridPoint(6, 4), GridPoint(5, 4), GridPoint(5, 7)), Direction.DOWN),
                    ArrowItem(3, listOf(GridPoint(4, 6), GridPoint(4, 5), GridPoint(1, 5)), Direction.LEFT),
                    ArrowItem(4, listOf(GridPoint(2, 4), GridPoint(3, 4), GridPoint(3, 1)), Direction.UP),
                    ArrowItem(5, listOf(GridPoint(1, 1), GridPoint(1, 3)), Direction.DOWN),
                    ArrowItem(6, listOf(GridPoint(7, 7), GridPoint(7, 5)), Direction.UP)
                ),
                maxDrops = 3, bannerText = "CALM MIND"
            ))

            // ANCHOR 1: Level 5 (Zendai Route 5) - Orbit
            put(5, LevelData(
                levelNumber = 5,
                title = "City 1 • Route 5 • Orbit",
                gridWidth = 10, gridHeight = 10,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(2, 2), GridPoint(8, 2), GridPoint(8, 7)), Direction.DOWN),
                    ArrowItem(2, listOf(GridPoint(7, 7), GridPoint(3, 7), GridPoint(3, 3)), Direction.UP),
                    ArrowItem(3, listOf(GridPoint(5, 4), GridPoint(6, 4), GridPoint(6, 6)), Direction.DOWN),
                    ArrowItem(4, listOf(GridPoint(5, 5), GridPoint(4, 5), GridPoint(4, 3)), Direction.UP),
                    ArrowItem(5, listOf(GridPoint(1, 8), GridPoint(9, 8)), Direction.RIGHT),
                    ArrowItem(6, listOf(GridPoint(1, 1), GridPoint(1, 6)), Direction.DOWN)
                ),
                maxDrops = 3, isSilhouette = true, silhouetteIcon = "🌀", bannerText = "ORBIT"
            ))

            // ANCHOR 2: Level 10 (Zendai Route 10) - Mars Transfer 🔴
            put(10, LevelData(
                levelNumber = 10,
                title = "City 1 • Route 10 • Mars Transfer 🔴",
                gridWidth = 14, gridHeight = 15,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(3, 2), GridPoint(0, 2)), Direction.LEFT),
                    ArrowItem(2, listOf(GridPoint(10, 2), GridPoint(13, 2)), Direction.RIGHT),
                    ArrowItem(3, listOf(GridPoint(4, 3), GridPoint(9, 3)), Direction.RIGHT),
                    ArrowItem(4, listOf(GridPoint(2, 5), GridPoint(2, 4), GridPoint(0, 4)), Direction.LEFT),
                    ArrowItem(5, listOf(GridPoint(11, 5), GridPoint(11, 4), GridPoint(13, 4)), Direction.RIGHT),
                    ArrowItem(6, listOf(GridPoint(5, 7), GridPoint(3, 7), GridPoint(3, 4)), Direction.UP),
                    ArrowItem(7, listOf(GridPoint(8, 7), GridPoint(10, 7), GridPoint(10, 4)), Direction.UP),
                    ArrowItem(8, listOf(GridPoint(6, 4), GridPoint(4, 4)), Direction.LEFT),
                    ArrowItem(9, listOf(GridPoint(7, 4), GridPoint(9, 4)), Direction.RIGHT),
                    ArrowItem(10, listOf(GridPoint(5, 6), GridPoint(8, 6)), Direction.RIGHT),
                    ArrowItem(11, listOf(GridPoint(4, 8), GridPoint(9, 8)), Direction.RIGHT),
                    ArrowItem(12, listOf(GridPoint(6, 9), GridPoint(6, 11), GridPoint(4, 11)), Direction.LEFT),
                    ArrowItem(13, listOf(GridPoint(7, 9), GridPoint(7, 11), GridPoint(9, 11)), Direction.RIGHT),
                    ArrowItem(14, listOf(GridPoint(3, 12), GridPoint(10, 12)), Direction.RIGHT),
                    ArrowItem(15, listOf(GridPoint(2, 13), GridPoint(11, 13)), Direction.RIGHT),
                    ArrowItem(16, listOf(GridPoint(1, 14), GridPoint(12, 14)), Direction.RIGHT)
                ),
                maxDrops = 3, isSilhouette = true, silhouetteIcon = "🏆", bannerText = "MARS TRANSFER"
            ))

            // ANCHOR 3: Level 15 (Zendai Route 15) - Saturn Rings 🪐
            put(15, LevelData(
                levelNumber = 15,
                title = "City 1 • Route 15 • Saturn Rings 🪐",
                gridWidth = 14, gridHeight = 14,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(3, 2), GridPoint(5, 2)), Direction.RIGHT),
                    ArrowItem(2, listOf(GridPoint(8, 2), GridPoint(10, 2)), Direction.RIGHT),
                    ArrowItem(3, listOf(GridPoint(2, 3), GridPoint(2, 5), GridPoint(0, 5)), Direction.LEFT),
                    ArrowItem(4, listOf(GridPoint(11, 3), GridPoint(11, 5), GridPoint(13, 5)), Direction.RIGHT),
                    ArrowItem(5, listOf(GridPoint(4, 4), GridPoint(6, 4)), Direction.RIGHT),
                    ArrowItem(6, listOf(GridPoint(7, 4), GridPoint(9, 4)), Direction.RIGHT),
                    ArrowItem(7, listOf(GridPoint(3, 6), GridPoint(1, 6)), Direction.LEFT),
                    ArrowItem(8, listOf(GridPoint(10, 6), GridPoint(12, 6)), Direction.RIGHT),
                    ArrowItem(9, listOf(GridPoint(4, 7), GridPoint(9, 7)), Direction.RIGHT),
                    ArrowItem(10, listOf(GridPoint(5, 8), GridPoint(8, 8)), Direction.RIGHT),
                    ArrowItem(11, listOf(GridPoint(6, 9), GridPoint(7, 9)), Direction.RIGHT),
                    ArrowItem(12, listOf(GridPoint(6, 10), GridPoint(6, 12)), Direction.DOWN),
                    ArrowItem(13, listOf(GridPoint(7, 10), GridPoint(7, 12)), Direction.DOWN),
                    ArrowItem(14, listOf(GridPoint(1, 1), GridPoint(1, 4)), Direction.UP)
                ),
                maxDrops = 4, isSilhouette = true, silhouetteIcon = "💎", bannerText = "SATURN RINGS"
            ))

            // ANCHOR 4: Level 20 (Zendai Route 20) - SPACE MASTER 🚀
            put(20, LevelData(
                levelNumber = 20,
                title = "City 1 • Route 20 • SPACE MASTER 🚀",
                gridWidth = 14, gridHeight = 14,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(1, 1), GridPoint(12, 1)), Direction.RIGHT),
                    ArrowItem(2, listOf(GridPoint(2, 3), GridPoint(11, 3)), Direction.RIGHT),
                    ArrowItem(3, listOf(GridPoint(4, 4), GridPoint(4, 12)), Direction.DOWN),
                    ArrowItem(4, listOf(GridPoint(9, 4), GridPoint(9, 12)), Direction.DOWN),
                    ArrowItem(5, listOf(GridPoint(5, 5), GridPoint(8, 5)), Direction.RIGHT),
                    ArrowItem(6, listOf(GridPoint(5, 8), GridPoint(8, 8)), Direction.RIGHT),
                    ArrowItem(7, listOf(GridPoint(1, 4), GridPoint(3, 4)), Direction.LEFT),
                    ArrowItem(8, listOf(GridPoint(10, 4), GridPoint(12, 4)), Direction.RIGHT),
                    ArrowItem(9, listOf(GridPoint(2, 6), GridPoint(2, 11)), Direction.DOWN),
                    ArrowItem(10, listOf(GridPoint(11, 6), GridPoint(11, 11)), Direction.DOWN),
                    ArrowItem(11, listOf(GridPoint(5, 10), GridPoint(8, 10)), Direction.RIGHT),
                    ArrowItem(12, listOf(GridPoint(6, 6), GridPoint(6, 7)), Direction.DOWN),
                    ArrowItem(13, listOf(GridPoint(7, 6), GridPoint(7, 7)), Direction.DOWN),
                    ArrowItem(14, listOf(GridPoint(3, 13), GridPoint(10, 13)), Direction.RIGHT),
                    ArrowItem(15, listOf(GridPoint(1, 12), GridPoint(1, 8)), Direction.UP)
                ),
                maxDrops = 4, isSilhouette = true, silhouetteIcon = "⛩️", bannerText = "SPACE MASTER"
            ))

            // ANCHOR 5: Level 25 (Sandara Route 5) - Momentum 🏃
            put(25, LevelData(
                levelNumber = 25,
                title = "City 2 • Route 5 • Momentum 🏃",
                gridWidth = 13, gridHeight = 13,
                arrows = listOf(
                    ArrowItem(1, listOf(GridPoint(5, 2), GridPoint(1, 2)), Direction.LEFT),
                    ArrowItem(2, listOf(GridPoint(7, 2), GridPoint(11, 2)), Direction.RIGHT),
                    ArrowItem(3, listOf(GridPoint(1, 4), GridPoint(5, 4)), Direction.RIGHT),
                    ArrowItem(4, listOf(GridPoint(7, 4), GridPoint(11, 4)), Direction.RIGHT),
                    ArrowItem(5, listOf(GridPoint(6, 5), GridPoint(6, 1)), Direction.UP),
                    ArrowItem(6, listOf(GridPoint(1, 6), GridPoint(11, 6)), Direction.RIGHT),
                    ArrowItem(7, listOf(GridPoint(11, 8), GridPoint(1, 8)), Direction.LEFT),
                    ArrowItem(8, listOf(GridPoint(2, 9), GridPoint(2, 11)), Direction.DOWN),
                    ArrowItem(9, listOf(GridPoint(10, 9), GridPoint(10, 11)), Direction.DOWN),
                    ArrowItem(10, listOf(GridPoint(3, 10), GridPoint(9, 10)), Direction.RIGHT)
                ),
                maxDrops = 4, isSilhouette = true, silhouetteIcon = "🏜️", bannerText = "MOMENTUM"
            ))
        }
    }
}
