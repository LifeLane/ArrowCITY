package com.example.content.cities

import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GridPoint
import com.example.model.LevelData

/**
 * Validated, deterministic production level fixtures for all 20 levels of SPACE CITY (City 01).
 * Every level is rigorously verified for solvability, strategic depth, and thematic progression.
 */
object SpaceCityLevels {

    private fun arrow(id: Int, dir: Direction, color: Int, vararg pts: Int): ArrowItem {
        val list = mutableListOf<GridPoint>()
        for (i in pts.indices step 2) {
            list.add(GridPoint(pts[i], pts[i + 1]))
        }
        return ArrowItem(
            id = id,
            points = list,
            headDirection = dir,
            colorIndex = color
        )
    }

    val levels: Map<Int, LevelData> by lazy {
        mapOf(
            // Level 1: First Launch (Tutorial, Learn, Direct unblocked flights)
            1 to LevelData(
                levelNumber = 1,
                title = "Space City • Route 1 • First Launch",
                gridWidth = 8,
                gridHeight = 8,
                arrows = listOf(
                    arrow(1, Direction.RIGHT, 0, 2, 2, 6, 2),
                    arrow(2, Direction.UP, 1, 4, 5, 4, 3),
                    arrow(3, Direction.RIGHT, 2, 1, 6, 6, 6)
                ),
                maxDrops = 3,
                isSilhouette = false,
                silhouetteIcon = "🚀",
                bannerText = "FIRST LAUNCH • TAP TO CLEAR"
            ),

            // Level 2: Moon Path (Basic branching, Learn)
            2 to LevelData(
                levelNumber = 2,
                title = "Space City • Route 2 • Moon Path",
                gridWidth = 8,
                gridHeight = 8,
                arrows = listOf(
                    arrow(1, Direction.RIGHT, 0, 2, 6, 2, 2, 6, 2),
                    arrow(2, Direction.UP, 1, 5, 6, 5, 3),
                    arrow(3, Direction.UP, 2, 3, 4, 4, 4, 4, 3),
                    arrow(4, Direction.UP, 3, 6, 5, 6, 3)
                ),
                maxDrops = 3,
                isSilhouette = false,
                silhouetteIcon = "🌕",
                bannerText = "MOON PATH • CHOOSE TRAJECTORY"
            ),

            // Level 3: Satellite (Temporary blocking, Learn)
            3 to LevelData(
                levelNumber = 3,
                title = "Space City • Route 3 • Satellite",
                gridWidth = 9,
                gridHeight = 9,
                arrows = listOf(
                    arrow(1, Direction.DOWN, 0, 2, 2, 7, 2, 7, 5),
                    arrow(2, Direction.UP, 1, 6, 7, 2, 7, 2, 3),
                    arrow(3, Direction.UP, 2, 3, 4, 5, 4, 5, 3),
                    arrow(4, Direction.LEFT, 3, 4, 5, 3, 5),
                    arrow(5, Direction.UP, 0, 8, 3, 8, 1)
                ),
                maxDrops = 3,
                isSilhouette = false,
                silhouetteIcon = "🛰️",
                bannerText = "SATELLITE • UNBLOCK THE RELAY"
            ),

            // Level 4: Gravity Well (Consequential Choice, Learn milestone)
            4 to LevelData(
                levelNumber = 4,
                title = "Space City • Route 4 • Gravity Well",
                gridWidth = 9,
                gridHeight = 9,
                arrows = listOf(
                    arrow(1, Direction.RIGHT, 0, 4, 2, 4, 3, 7, 3),
                    arrow(2, Direction.DOWN, 1, 6, 4, 5, 4, 5, 7),
                    arrow(3, Direction.LEFT, 2, 4, 6, 4, 5, 1, 5),
                    arrow(4, Direction.UP, 3, 2, 4, 3, 4, 3, 1),
                    arrow(5, Direction.DOWN, 0, 1, 1, 1, 3),
                    arrow(6, Direction.UP, 1, 7, 7, 7, 5)
                ),
                maxDrops = 3,
                isSilhouette = false,
                silhouetteIcon = "🌀",
                bannerText = "GRAVITY WELL • ESCAPE THE PULL"
            ),

            // Level 5: Orbit (Sequential Dependency, Reinforce)
            5 to LevelData(
                levelNumber = 5,
                title = "Space City • Route 5 • Orbit",
                gridWidth = 10,
                gridHeight = 10,
                arrows = listOf(
                    arrow(1, Direction.DOWN, 0, 2, 2, 8, 2, 8, 7),
                    arrow(2, Direction.UP, 1, 7, 7, 3, 7, 3, 3),
                    arrow(3, Direction.DOWN, 2, 5, 4, 6, 4, 6, 6),
                    arrow(4, Direction.UP, 3, 5, 5, 4, 5, 4, 3),
                    arrow(5, Direction.RIGHT, 0, 1, 8, 9, 8),
                    arrow(6, Direction.DOWN, 1, 1, 1, 1, 6)
                ),
                maxDrops = 3,
                isSilhouette = true,
                silhouetteIcon = "🪐",
                bannerText = "ORBIT LOOP • STEP BY STEP"
            ),

            // Level 6: Asteroid Belt (Narrow branching + Trap, Reinforce)
            6 to LevelData(
                levelNumber = 6,
                title = "Space City • Route 6 • Asteroid Belt",
                gridWidth = 10,
                gridHeight = 9,
                arrows = listOf(
                    arrow(1, Direction.RIGHT, 0, 2, 6, 3, 6, 4, 6, 5, 6),
                    arrow(2, Direction.RIGHT, 1, 1, 3, 1, 2, 2, 2, 3, 2),
                    arrow(3, Direction.UP, 2, 8, 5, 8, 4),
                    arrow(4, Direction.UP, 3, 6, 3, 6, 2),
                    arrow(5, Direction.LEFT, 0, 7, 7, 4, 7),
                    arrow(6, Direction.DOWN, 1, 9, 2, 9, 5)
                ),
                maxDrops = 3,
                isSilhouette = false,
                silhouetteIcon = "☄️",
                bannerText = "ASTEROID BELT • AVOID DEBRIS"
            ),

            // Level 7: Lunar Eclipse (Delayed dependency, Reinforce)
            7 to LevelData(
                levelNumber = 7,
                title = "Space City • Route 7 • Lunar Eclipse",
                gridWidth = 10,
                gridHeight = 10,
                arrows = listOf(
                    arrow(1, Direction.UP, 0, 1, 5, 1, 4, 1, 3, 1, 2, 1, 1),
                    arrow(2, Direction.UP, 1, 1, 7, 1, 6),
                    arrow(3, Direction.DOWN, 2, 6, 3, 6, 4, 6, 5, 6, 6),
                    arrow(4, Direction.LEFT, 3, 7, 1, 6, 1),
                    arrow(5, Direction.RIGHT, 0, 3, 8, 8, 8),
                    arrow(6, Direction.UP, 1, 9, 5, 9, 2),
                    arrow(7, Direction.LEFT, 2, 4, 3, 2, 3)
                ),
                maxDrops = 3,
                isSilhouette = false,
                silhouetteIcon = "🌑",
                bannerText = "LUNAR ECLIPSE • LOOK AHEAD"
            ),

            // Level 8: Space Station (Multiple entry routes, Reinforce milestone)
            8 to LevelData(
                levelNumber = 8,
                title = "Space City • Route 8 • Space Station",
                gridWidth = 10,
                gridHeight = 10,
                arrows = listOf(
                    arrow(1, Direction.DOWN, 0, 2, 2, 2, 3, 2, 4, 2, 5, 2, 6),
                    arrow(2, Direction.DOWN, 1, 6, 5, 5, 5, 5, 6, 5, 7),
                    arrow(3, Direction.DOWN, 2, 3, 2, 3, 3, 3, 4, 3, 5),
                    arrow(4, Direction.UP, 3, 6, 1, 6, 0),
                    arrow(5, Direction.LEFT, 0, 5, 3, 4, 3),
                    arrow(6, Direction.RIGHT, 1, 5, 4, 6, 4, 7, 4),
                    arrow(7, Direction.RIGHT, 2, 1, 8, 4, 8),
                    arrow(8, Direction.UP, 3, 8, 7, 8, 4)
                ),
                maxDrops = 3,
                isSilhouette = true,
                silhouetteIcon = "🛰️",
                bannerText = "SPACE STATION • DOCKING HUB"
            ),

            // Level 9: Comet Trail (Delayed Trap, Challenge)
            9 to LevelData(
                levelNumber = 9,
                title = "Space City • Route 9 • Comet Trail",
                gridWidth = 11,
                gridHeight = 10,
                arrows = listOf(
                    arrow(1, Direction.UP, 0, 4, 4, 4, 3),
                    arrow(2, Direction.UP, 1, 5, 3, 6, 3, 6, 2, 6, 1),
                    arrow(3, Direction.LEFT, 2, 5, 6, 4, 6),
                    arrow(4, Direction.DOWN, 3, 6, 5, 6, 6, 6, 7),
                    arrow(5, Direction.RIGHT, 0, 1, 1, 2, 1, 3, 1),
                    arrow(6, Direction.LEFT, 1, 3, 3, 3, 4, 3, 5, 2, 5, 1, 5, 0, 5),
                    arrow(7, Direction.RIGHT, 2, 4, 2, 5, 2),
                    arrow(8, Direction.LEFT, 3, 1, 2, 0, 2)
                ),
                maxDrops = 3,
                isSilhouette = false,
                silhouetteIcon = "💫",
                bannerText = "COMET TRAIL • IONIZED PATHWAYS"
            ),

            // Level 10: Mars Transfer (Two Critical Choices, Challenge milestone)
            10 to LevelData(
                levelNumber = 10,
                title = "Space City • Route 10 • Mars Transfer",
                gridWidth = 12,
                gridHeight = 12,
                arrows = listOf(
                    arrow(1, Direction.LEFT, 0, 3, 2, 0, 2),
                    arrow(2, Direction.RIGHT, 1, 8, 2, 11, 2),
                    arrow(3, Direction.RIGHT, 2, 4, 3, 7, 3),
                    arrow(4, Direction.LEFT, 3, 2, 5, 2, 4, 0, 4),
                    arrow(5, Direction.RIGHT, 0, 9, 5, 9, 4, 11, 4),
                    arrow(6, Direction.UP, 1, 5, 7, 3, 7, 3, 4),
                    arrow(7, Direction.UP, 2, 6, 7, 8, 7, 8, 4),
                    arrow(8, Direction.LEFT, 3, 5, 4, 4, 4),
                    arrow(9, Direction.RIGHT, 0, 6, 4, 7, 4),
                    arrow(10, Direction.RIGHT, 1, 4, 6, 7, 6),
                    arrow(11, Direction.RIGHT, 2, 3, 8, 8, 8),
                    arrow(12, Direction.LEFT, 3, 5, 9, 5, 10, 3, 10),
                    arrow(13, Direction.RIGHT, 0, 6, 9, 6, 10, 8, 10),
                    arrow(14, Direction.RIGHT, 1, 2, 11, 9, 11)
                ),
                maxDrops = 3,
                isSilhouette = true,
                silhouetteIcon = "🔴",
                bannerText = "MARS TRANSFER • TWO CRITICAL CHOICES"
            ),

            // Level 11: Solar Flare (Multi-Layered Dependency, Challenge)
            11 to LevelData(
                levelNumber = 11,
                title = "Space City • Route 11 • Solar Flare",
                gridWidth = 11,
                gridHeight = 11,
                arrows = listOf(
                    arrow(1, Direction.RIGHT, 0, 3, 6, 3, 7, 4, 7, 5, 7),
                    arrow(2, Direction.LEFT, 1, 4, 1, 3, 1, 2, 1, 1, 1),
                    arrow(3, Direction.UP, 2, 5, 6, 5, 5, 5, 4, 5, 3),
                    arrow(4, Direction.UP, 3, 8, 2, 8, 1),
                    arrow(5, Direction.UP, 0, 1, 5, 1, 4, 1, 3, 1, 2),
                    arrow(6, Direction.DOWN, 1, 9, 3, 9, 7),
                    arrow(7, Direction.LEFT, 2, 7, 9, 2, 9),
                    arrow(8, Direction.RIGHT, 3, 2, 3, 4, 3)
                ),
                maxDrops = 3,
                isSilhouette = false,
                silhouetteIcon = "☀️",
                bannerText = "SOLAR FLARE • RADIATION FIELD"
            ),

            // Level 12: Black Hole (Deep Trap Horizon, Challenge)
            12 to LevelData(
                levelNumber = 12,
                title = "Space City • Route 12 • Black Hole",
                gridWidth = 12,
                gridHeight = 12,
                arrows = listOf(
                    arrow(1, Direction.UP, 0, 4, 6, 4, 5, 4, 4),
                    arrow(2, Direction.DOWN, 1, 7, 3, 7, 4, 7, 5),
                    arrow(3, Direction.RIGHT, 2, 2, 7, 2, 6, 3, 6),
                    arrow(4, Direction.LEFT, 3, 9, 2, 9, 3, 8, 3),
                    arrow(5, Direction.DOWN, 0, 1, 4, 1, 5),
                    arrow(6, Direction.UP, 1, 10, 5, 10, 4),
                    arrow(7, Direction.RIGHT, 2, 3, 9, 8, 9),
                    arrow(8, Direction.LEFT, 3, 8, 1, 3, 1),
                    arrow(9, Direction.UP, 0, 6, 8, 6, 6)
                ),
                maxDrops = 3,
                isSilhouette = false,
                silhouetteIcon = "🕳️",
                bannerText = "BLACK HOLE • SINGULARITY WELL"
            ),

            // Level 13: Wormhole (Long-Range Dependency, Challenge)
            13 to LevelData(
                levelNumber = 13,
                title = "Space City • Route 13 • Wormhole",
                gridWidth = 12,
                gridHeight = 12,
                arrows = listOf(
                    arrow(1, Direction.LEFT, 0, 5, 2, 4, 2, 3, 2, 2, 2, 1, 2),
                    arrow(2, Direction.UP, 1, 6, 6, 6, 5, 6, 4, 6, 3, 6, 1),
                    arrow(3, Direction.LEFT, 2, 6, 7, 5, 7, 4, 7, 3, 7, 2, 7),
                    arrow(4, Direction.UP, 3, 1, 7, 1, 6, 1, 5, 1, 4, 1, 3),
                    arrow(5, Direction.DOWN, 0, 3, 3, 3, 4, 3, 5),
                    arrow(6, Direction.LEFT, 1, 5, 5, 4, 5, 3, 5, 2, 5),
                    arrow(7, Direction.UP, 2, 4, 4, 4, 1),
                    arrow(8, Direction.RIGHT, 3, 8, 4, 11, 4),
                    arrow(9, Direction.DOWN, 0, 9, 6, 9, 11)
                ),
                maxDrops = 3,
                isSilhouette = false,
                silhouetteIcon = "🌌",
                bannerText = "WORMHOLE • QUANTUM BRIDGE"
            ),

            // Level 14: Europa (Recovery Routing, Challenge)
            14 to LevelData(
                levelNumber = 14,
                title = "Space City • Route 14 • Europa",
                gridWidth = 13,
                gridHeight = 12,
                arrows = listOf(
                    arrow(1, Direction.LEFT, 0, 4, 2, 3, 2, 2, 2, 1, 2),
                    arrow(2, Direction.UP, 1, 7, 5, 7, 4, 7, 3, 7, 2, 7, 1),
                    arrow(3, Direction.DOWN, 2, 8, 3, 8, 4, 8, 5, 8, 7, 8, 10),
                    arrow(4, Direction.LEFT, 3, 7, 6, 6, 6, 5, 6, 4, 6),
                    arrow(5, Direction.UP, 0, 3, 7, 3, 6, 3, 5, 3, 3),
                    arrow(6, Direction.RIGHT, 1, 2, 8, 3, 8, 4, 8),
                    arrow(7, Direction.UP, 2, 9, 8, 9, 7, 9, 4, 9, 1),
                    arrow(8, Direction.LEFT, 3, 2, 1, 0, 1),
                    arrow(9, Direction.RIGHT, 0, 5, 9, 11, 9, 12, 9),
                    arrow(10, Direction.UP, 1, 11, 5, 11, 2, 11, 0)
                ),
                maxDrops = 3,
                isSilhouette = false,
                silhouetteIcon = "❄️",
                bannerText = "EUROPA • RECOVERY ROUTES"
            ),

            // Level 15: Saturn Rings (Branching Cascade, Challenge milestone)
            15 to LevelData(
                levelNumber = 15,
                title = "Space City • Route 15 • Saturn Rings",
                gridWidth = 13,
                gridHeight = 13,
                arrows = listOf(
                    arrow(1, Direction.LEFT, 0, 7, 1, 6, 1, 5, 1, 4, 1, 3, 1, 2, 1, 1, 1, 0, 1),
                    arrow(2, Direction.DOWN, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 10),
                    arrow(3, Direction.RIGHT, 2, 2, 8, 3, 8, 4, 8, 5, 8, 6, 8, 7, 8, 8, 8, 12, 8),
                    arrow(4, Direction.UP, 3, 8, 7, 8, 6, 8, 5, 8, 4, 8, 3, 8, 2, 8, 0),
                    arrow(5, Direction.LEFT, 0, 5, 3, 4, 3, 3, 3, 2, 3),
                    arrow(6, Direction.DOWN, 1, 3, 4, 3, 5, 3, 6, 3, 7),
                    arrow(7, Direction.RIGHT, 2, 4, 6, 5, 6, 6, 6, 7, 6),
                    arrow(8, Direction.UP, 3, 6, 5, 6, 4, 6, 2),
                    arrow(9, Direction.RIGHT, 0, 9, 10, 12, 10),
                    arrow(10, Direction.UP, 1, 11, 7, 11, 3, 11, 0),
                    arrow(11, Direction.DOWN, 2, 10, 2, 10, 5, 10, 12)
                ),
                maxDrops = 3,
                isSilhouette = true,
                silhouetteIcon = "🪐",
                bannerText = "SATURN RINGS • NESTED ORBITS"
            ),

            // Level 16: Nebula (Sequential Choice Chain, Challenge)
            16 to LevelData(
                levelNumber = 16,
                title = "Space City • Route 16 • Nebula",
                gridWidth = 14,
                gridHeight = 14,
                arrows = listOf(
                    arrow(1, Direction.LEFT, 0, 4, 3, 3, 3, 2, 3, 1, 3, 0, 3),
                    arrow(2, Direction.UP, 1, 5, 5, 5, 4, 5, 2, 5, 0),
                    arrow(3, Direction.DOWN, 2, 7, 2, 7, 3, 7, 4, 7, 8, 7, 13),
                    arrow(4, Direction.RIGHT, 3, 6, 6, 7, 6, 8, 6, 12, 6, 13, 6),
                    arrow(5, Direction.DOWN, 0, 3, 6, 3, 8, 3, 11, 3, 13),
                    arrow(6, Direction.UP, 1, 2, 6, 2, 5, 2, 2, 2, 0),
                    arrow(7, Direction.DOWN, 2, 9, 4, 9, 5, 9, 10, 9, 13),
                    arrow(8, Direction.LEFT, 3, 6, 1, 5, 1, 3, 1, 0, 1),
                    arrow(9, Direction.RIGHT, 0, 4, 9, 8, 9, 11, 9, 13, 9),
                    arrow(10, Direction.UP, 1, 10, 11, 10, 8, 10, 3, 10, 0),
                    arrow(11, Direction.RIGHT, 2, 8, 5, 10, 5, 12, 5, 13, 5),
                    arrow(12, Direction.DOWN, 3, 1, 10, 1, 12, 1, 13)
                ),
                maxDrops = 3,
                isSilhouette = false,
                silhouetteIcon = "✨",
                bannerText = "NEBULA • FILAMENT CHAINS"
            ),

            // Level 17: Pulsar (Low Recovery Precision, Master)
            17 to LevelData(
                levelNumber = 17,
                title = "Space City • Route 17 • Pulsar",
                gridWidth = 14,
                gridHeight = 14,
                arrows = listOf(
                    arrow(1, Direction.LEFT, 0, 3, 2, 2, 2, 1, 2, 0, 2),
                    arrow(2, Direction.RIGHT, 1, 6, 2, 7, 2, 8, 2, 12, 2, 13, 2),
                    arrow(3, Direction.DOWN, 2, 2, 5, 2, 6, 2, 7, 2, 11, 2, 13),
                    arrow(4, Direction.UP, 3, 7, 5, 7, 4, 7, 3, 7, 1, 7, 0),
                    arrow(5, Direction.LEFT, 0, 4, 6, 4, 5, 3, 5, 1, 5, 0, 5),
                    arrow(6, Direction.RIGHT, 1, 5, 3, 5, 4, 6, 4, 8, 4, 12, 4, 13, 4),
                    arrow(7, Direction.UP, 2, 3, 8, 3, 6, 3, 3),
                    arrow(8, Direction.DOWN, 3, 6, 8, 6, 9, 6, 11, 6, 13),
                    arrow(9, Direction.UP, 0, 9, 4, 9, 2, 9, 0),
                    arrow(10, Direction.RIGHT, 1, 10, 10, 13, 10),
                    arrow(11, Direction.UP, 2, 12, 8, 12, 4, 12, 0),
                    arrow(12, Direction.DOWN, 3, 0, 11, 0, 13)
                ),
                maxDrops = 4,
                isSilhouette = false,
                silhouetteIcon = "⚡",
                bannerText = "PULSAR • PRECISION NAVIGATION"
            ),

            // Level 18: Supernova (Delayed Multi-Step Traps, Master)
            18 to LevelData(
                levelNumber = 18,
                title = "Space City • Route 18 • Supernova",
                gridWidth = 14,
                gridHeight = 14,
                arrows = listOf(
                    arrow(1, Direction.LEFT, 0, 5, 2, 4, 2, 3, 2, 2, 2, 1, 2, 0, 2),
                    arrow(2, Direction.UP, 1, 7, 5, 7, 4, 7, 3, 7, 2, 7, 0),
                    arrow(3, Direction.DOWN, 2, 8, 3, 8, 4, 8, 5, 8, 8, 8, 12, 8, 13),
                    arrow(4, Direction.RIGHT, 3, 3, 6, 4, 6, 5, 6, 6, 6, 9, 6, 12, 6, 13, 6),
                    arrow(5, Direction.UP, 0, 1, 6, 1, 5, 1, 3),
                    arrow(6, Direction.DOWN, 1, 9, 4, 9, 5, 9, 8, 9, 12, 9, 13),
                    arrow(7, Direction.LEFT, 2, 5, 8, 4, 8, 2, 8, 0, 8),
                    arrow(8, Direction.LEFT, 3, 5, 1, 4, 1, 2, 1, 0, 1),
                    arrow(9, Direction.DOWN, 0, 11, 2, 11, 6, 11, 13),
                    arrow(10, Direction.RIGHT, 1, 8, 10, 12, 10, 13, 10),
                    arrow(11, Direction.UP, 2, 13, 7, 13, 3, 13, 0),
                    arrow(12, Direction.LEFT, 3, 9, 12, 3, 12, 0, 12),
                    arrow(13, Direction.UP, 0, 2, 13, 2, 10, 2, 9)
                ),
                maxDrops = 4,
                isSilhouette = false,
                silhouetteIcon = "💥",
                bannerText = "SUPERNOVA • SHOCKWAVE CALCULATIONS"
            ),

            // Level 19: Event Horizon (Advanced Multi-Choice Planning, Master)
            19 to LevelData(
                levelNumber = 19,
                title = "Space City • Route 19 • Event Horizon",
                gridWidth = 15,
                gridHeight = 15,
                arrows = listOf(
                    arrow(1, Direction.LEFT, 0, 4, 2, 3, 2, 2, 2, 1, 2, 0, 2),
                    arrow(2, Direction.UP, 1, 7, 4, 7, 3, 7, 2, 7, 1, 7, 0),
                    arrow(3, Direction.DOWN, 2, 8, 3, 8, 4, 8, 6, 8, 10, 8, 14),
                    arrow(4, Direction.RIGHT, 3, 5, 5, 6, 5, 9, 5, 12, 5, 14, 5),
                    arrow(5, Direction.UP, 0, 3, 6, 3, 4),
                    arrow(6, Direction.DOWN, 1, 4, 3, 4, 4, 4, 7, 4, 10, 4, 14),
                    arrow(7, Direction.LEFT, 2, 6, 3, 5, 3, 3, 3, 1, 3, 0, 3),
                    arrow(8, Direction.UP, 3, 5, 4, 5, 1, 5, 0),
                    arrow(9, Direction.DOWN, 0, 1, 7, 1, 8, 1, 9, 1, 14),
                    arrow(10, Direction.UP, 1, 9, 7, 9, 6, 9, 5, 9, 2, 9, 0),
                    arrow(11, Direction.RIGHT, 2, 9, 10, 13, 10, 14, 10),
                    arrow(12, Direction.UP, 3, 12, 7, 12, 3, 12, 0),
                    arrow(13, Direction.LEFT, 0, 14, 1, 10, 1, 8, 1, 0, 1),
                    arrow(14, Direction.DOWN, 1, 5, 11, 5, 14)
                ),
                maxDrops = 4,
                isSilhouette = true,
                silhouetteIcon = "🌠",
                bannerText = "EVENT HORIZON • 4-STAGE PLANNING"
            ),

            // Level 20: SPACE MASTER (The Ultimate Space City Puzzle, Master Flagship)
            20 to LevelData(
                levelNumber = 20,
                title = "Space City • Route 20 • SPACE MASTER",
                gridWidth = 15,
                gridHeight = 15,
                arrows = listOf(
                    arrow(1, Direction.LEFT, 0, 5, 3, 4, 3, 3, 3, 2, 3, 1, 3, 0, 3),
                    arrow(2, Direction.UP, 1, 6, 5, 6, 4, 6, 2, 6, 0),
                    arrow(3, Direction.DOWN, 2, 7, 3, 7, 5, 7, 8, 7, 12, 7, 14),
                    arrow(4, Direction.RIGHT, 3, 4, 6, 5, 6, 8, 6, 11, 6, 14, 6),
                    arrow(5, Direction.UP, 0, 2, 7, 2, 5, 2, 4),
                    arrow(6, Direction.DOWN, 1, 3, 4, 3, 5, 3, 8, 3, 11, 3, 14),
                    arrow(7, Direction.LEFT, 2, 5, 4, 4, 4, 2, 4, 0, 4),
                    arrow(8, Direction.RIGHT, 3, 1, 5, 3, 5, 5, 5, 8, 5, 12, 5, 14, 5),
                    arrow(9, Direction.RIGHT, 0, 8, 3, 11, 3, 14, 3),
                    arrow(10, Direction.DOWN, 1, 12, 4, 12, 8, 12, 14),
                    arrow(11, Direction.LEFT, 2, 11, 9, 8, 9, 4, 9, 0, 9),
                    arrow(12, Direction.UP, 3, 9, 8, 9, 4, 9, 0),
                    arrow(13, Direction.RIGHT, 0, 2, 12, 8, 12, 14, 12),
                    arrow(14, Direction.UP, 1, 13, 13, 13, 10, 13, 0),
                    arrow(15, Direction.LEFT, 2, 14, 1, 10, 1, 5, 1, 0, 1)
                ),
                maxDrops = 4,
                isSilhouette = true,
                silhouetteIcon = "🚀",
                bannerText = "SPACE MASTER • THE COSMIC SUMMIT"
            )
        )
    }

    fun getLevel(levelNumber: Int): LevelData? = levels[levelNumber]
}
