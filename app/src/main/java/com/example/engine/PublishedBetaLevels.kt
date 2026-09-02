package com.example.engine

import com.example.model.LevelData
import com.example.model.ArrowItem
import com.example.model.GridPoint
import com.example.model.Direction

object PublishedBetaLevels {

    private fun arrow(id: Int, dir: Direction, color: Int, vararg pts: Int): ArrowItem {
        val list = mutableListOf<GridPoint>()
        for (i in pts.indices step 2) {
            list.add(GridPoint(pts[i], pts[i+1]))
        }
        return ArrowItem(id, list, dir, color)
    }

    val levels: Map<Int, LevelData> by lazy {
        buildMap {
            loadChunk1(this)
            loadChunk2(this)
            loadChunk3(this)
            loadChunk4(this)
        }
    }

    private fun loadChunk1(map: MutableMap<Int, LevelData>) {
        map.put(1, LevelData(
            levelNumber = 1,
            title = "City 1 • Route 1 • First Steps",
            gridWidth = 8,
            gridHeight = 8,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 2, 2, 6, 2),
            arrow(2, Direction.UP, 1, 4, 5, 4, 3),
            arrow(3, Direction.RIGHT, 2, 1, 6, 6, 6)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(2, LevelData(
            levelNumber = 2,
            title = "City 1 • Route 2 • Gentle Turn",
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
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(3, LevelData(
            levelNumber = 3,
            title = "City 1 • Route 3 • Quiet Path",
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
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(4, LevelData(
            levelNumber = 4,
            title = "City 1 • Route 4 • Inner Calm",
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
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(5, LevelData(
            levelNumber = 5,
            title = "City 1 • Route 5 • Zen Flow",
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
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(6, LevelData(
            levelNumber = 6,
            title = "City 1 • Route 6 • Twin Streams",
            gridWidth = 10,
            gridHeight = 8,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 2, 6, 3, 6, 4, 6, 5, 6),
            arrow(2, Direction.RIGHT, 1, 1, 3, 1, 2, 2, 2, 3, 2),
            arrow(3, Direction.UP, 2, 8, 5, 8, 4),
            arrow(4, Direction.UP, 3, 6, 3, 6, 2)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(7, LevelData(
            levelNumber = 7,
            title = "City 1 • Route 7 • Breeze",
            gridWidth = 9,
            gridHeight = 9,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 1, 5, 1, 4, 1, 3, 1, 2, 1, 1),
            arrow(2, Direction.UP, 1, 1, 7, 1, 6),
            arrow(3, Direction.DOWN, 2, 6, 3, 6, 4, 6, 5, 6, 6),
            arrow(4, Direction.LEFT, 3, 7, 1, 6, 1)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(8, LevelData(
            levelNumber = 8,
            title = "City 1 • Route 8 • Clear Vision",
            gridWidth = 8,
            gridHeight = 9,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 2, 2, 2, 3, 2, 4, 2, 5, 2, 6),
            arrow(2, Direction.DOWN, 1, 6, 5, 5, 5, 5, 6, 5, 7),
            arrow(3, Direction.DOWN, 2, 3, 2, 3, 3, 3, 4, 3, 5),
            arrow(4, Direction.UP, 3, 6, 1, 6, 0),
            arrow(5, Direction.LEFT, 0, 5, 3, 4, 3),
            arrow(6, Direction.RIGHT, 1, 5, 4, 6, 4, 7, 4)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(9, LevelData(
            levelNumber = 9,
            title = "City 1 • Route 9 • Stone Garden",
            gridWidth = 10,
            gridHeight = 8,
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
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(10, LevelData(
            levelNumber = 10,
            title = "City 1 • Route 10 • Trophy of Clarity",
            gridWidth = 14,
            gridHeight = 15,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 3, 2, 0, 2),
            arrow(2, Direction.RIGHT, 1, 10, 2, 13, 2),
            arrow(3, Direction.RIGHT, 2, 4, 3, 9, 3),
            arrow(4, Direction.LEFT, 3, 2, 5, 2, 4, 0, 4),
            arrow(5, Direction.RIGHT, 0, 11, 5, 11, 4, 13, 4),
            arrow(6, Direction.UP, 1, 5, 7, 3, 7, 3, 4),
            arrow(7, Direction.UP, 2, 8, 7, 10, 7, 10, 4),
            arrow(8, Direction.LEFT, 3, 6, 4, 4, 4),
            arrow(9, Direction.RIGHT, 0, 7, 4, 9, 4),
            arrow(10, Direction.RIGHT, 1, 5, 6, 8, 6),
            arrow(11, Direction.RIGHT, 2, 4, 8, 9, 8),
            arrow(12, Direction.LEFT, 3, 6, 9, 6, 11, 4, 11),
            arrow(13, Direction.RIGHT, 0, 7, 9, 7, 11, 9, 11),
            arrow(14, Direction.RIGHT, 1, 3, 12, 10, 12),
            arrow(15, Direction.RIGHT, 2, 2, 13, 11, 13),
            arrow(16, Direction.RIGHT, 3, 1, 14, 12, 14)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(11, LevelData(
            levelNumber = 11,
            title = "City 1 • Route 11 • Harmony",
            gridWidth = 10,
            gridHeight = 9,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 3, 6, 3, 7, 4, 7, 5, 7),
            arrow(2, Direction.LEFT, 1, 4, 1, 3, 1, 2, 1, 1, 1),
            arrow(3, Direction.UP, 2, 5, 6, 5, 5, 5, 4, 5, 3),
            arrow(4, Direction.UP, 3, 8, 2, 8, 1),
            arrow(5, Direction.UP, 0, 1, 5, 1, 4, 1, 3, 1, 2)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(12, LevelData(
            levelNumber = 12,
            title = "City 1 • Route 12 • Bamboo Lane",
            gridWidth = 8,
            gridHeight = 8,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 6, 3, 6, 4, 6, 5),
            arrow(2, Direction.RIGHT, 1, 1, 2, 2, 2),
            arrow(3, Direction.DOWN, 2, 3, 1, 3, 2, 3, 3, 3, 4),
            arrow(4, Direction.DOWN, 3, 6, 1, 7, 1, 7, 2, 7, 3),
            arrow(5, Direction.RIGHT, 0, 1, 1, 1, 0, 2, 0, 3, 0)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(13, LevelData(
            levelNumber = 13,
            title = "City 1 • Route 13 • Lantern Walk",
            gridWidth = 9,
            gridHeight = 10,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 4, 1, 5, 1),
            arrow(2, Direction.DOWN, 1, 3, 1, 3, 2, 3, 3),
            arrow(3, Direction.RIGHT, 2, 5, 3, 6, 3),
            arrow(4, Direction.RIGHT, 3, 7, 4, 8, 4),
            arrow(5, Direction.RIGHT, 0, 3, 8, 4, 8, 5, 8, 6, 8),
            arrow(6, Direction.LEFT, 1, 1, 7, 0, 7),
            arrow(7, Direction.RIGHT, 2, 6, 6, 7, 6),
            arrow(8, Direction.DOWN, 3, 7, 2, 7, 3)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(14, LevelData(
            levelNumber = 14,
            title = "City 1 • Route 14 • Lotus Pond",
            gridWidth = 8,
            gridHeight = 8,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 4, 6, 4, 5, 4, 4, 3, 4, 2, 4, 1, 4),
            arrow(2, Direction.UP, 1, 5, 2, 5, 1, 5, 0),
            arrow(3, Direction.DOWN, 2, 1, 1, 1, 2),
            arrow(4, Direction.RIGHT, 3, 2, 2, 3, 2),
            arrow(5, Direction.UP, 0, 6, 6, 6, 5)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(15, LevelData(
            levelNumber = 15,
            title = "City 1 • Route 15 • Diamond Heart",
            gridWidth = 14,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 3, 2, 5, 2),
            arrow(2, Direction.RIGHT, 1, 8, 2, 10, 2),
            arrow(3, Direction.LEFT, 2, 2, 3, 2, 5, 0, 5),
            arrow(4, Direction.RIGHT, 3, 11, 3, 11, 5, 13, 5),
            arrow(5, Direction.RIGHT, 0, 4, 4, 6, 4),
            arrow(6, Direction.RIGHT, 1, 7, 4, 9, 4),
            arrow(7, Direction.LEFT, 2, 3, 6, 1, 6),
            arrow(8, Direction.RIGHT, 3, 10, 6, 12, 6),
            arrow(9, Direction.RIGHT, 0, 4, 7, 9, 7),
            arrow(10, Direction.RIGHT, 1, 5, 8, 8, 8),
            arrow(11, Direction.RIGHT, 2, 6, 9, 7, 9),
            arrow(12, Direction.DOWN, 3, 6, 10, 6, 12),
            arrow(13, Direction.DOWN, 0, 7, 10, 7, 12),
            arrow(14, Direction.UP, 1, 1, 1, 1, 4)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(16, LevelData(
            levelNumber = 16,
            title = "City 1 • Route 16 • Morning Mist",
            gridWidth = 10,
            gridHeight = 10,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 4, 4, 4, 3, 4, 2),
            arrow(2, Direction.UP, 1, 8, 8, 8, 7),
            arrow(3, Direction.LEFT, 2, 7, 2, 7, 1, 6, 1, 5, 1),
            arrow(4, Direction.DOWN, 3, 2, 1, 3, 1, 3, 2, 3, 3),
            arrow(5, Direction.RIGHT, 0, 7, 6, 8, 6, 9, 6),
            arrow(6, Direction.RIGHT, 1, 6, 5, 6, 4, 6, 3, 7, 3, 8, 3, 9, 3)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(17, LevelData(
            levelNumber = 17,
            title = "City 1 • Route 17 • Silent Gate",
            gridWidth = 9,
            gridHeight = 10,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 1, 2, 2, 2, 3, 2, 3, 3, 3, 4),
            arrow(2, Direction.RIGHT, 1, 4, 8, 5, 8, 6, 8, 7, 8, 8, 8),
            arrow(3, Direction.RIGHT, 2, 4, 2, 5, 2, 6, 2, 7, 2, 8, 2),
            arrow(4, Direction.RIGHT, 3, 1, 8, 2, 8, 3, 8),
            arrow(5, Direction.UP, 0, 6, 7, 6, 6, 6, 5, 6, 4)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(18, LevelData(
            levelNumber = 18,
            title = "City 1 • Route 18 • Purity",
            gridWidth = 10,
            gridHeight = 9,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 7, 7, 7, 6, 6, 6, 5, 6),
            arrow(2, Direction.DOWN, 1, 5, 2, 5, 3),
            arrow(3, Direction.DOWN, 2, 3, 7, 3, 8),
            arrow(4, Direction.DOWN, 3, 6, 5, 5, 5, 4, 5, 4, 6, 4, 7, 4, 8),
            arrow(5, Direction.UP, 0, 4, 2, 3, 2, 3, 1, 3, 0),
            arrow(6, Direction.DOWN, 1, 8, 2, 7, 2, 6, 2, 6, 3, 6, 4),
            arrow(7, Direction.UP, 2, 7, 5, 7, 4)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(19, LevelData(
            levelNumber = 19,
            title = "City 1 • Route 19 • Serenity",
            gridWidth = 8,
            gridHeight = 8,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 3, 4, 3, 5, 3, 6, 4, 6, 5, 6),
            arrow(2, Direction.DOWN, 1, 2, 2, 2, 3, 2, 4, 2, 5),
            arrow(3, Direction.UP, 2, 6, 2, 6, 1, 6, 0),
            arrow(4, Direction.RIGHT, 3, 4, 4, 4, 3, 5, 3, 6, 3),
            arrow(5, Direction.UP, 0, 1, 1, 1, 0),
            arrow(6, Direction.UP, 1, 5, 4, 6, 4, 7, 4, 7, 3, 7, 2),
            arrow(7, Direction.RIGHT, 2, 2, 1, 3, 1, 4, 1),
            arrow(8, Direction.DOWN, 3, 1, 5, 1, 6)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(20, LevelData(
            levelNumber = 20,
            title = "City 1 • Route 20 • Zendai Gate",
            gridWidth = 14,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 1, 1, 12, 1),
            arrow(2, Direction.RIGHT, 1, 2, 3, 11, 3),
            arrow(3, Direction.DOWN, 2, 4, 4, 4, 12),
            arrow(4, Direction.DOWN, 3, 9, 4, 9, 12),
            arrow(5, Direction.RIGHT, 0, 5, 5, 8, 5),
            arrow(6, Direction.RIGHT, 1, 5, 8, 8, 8),
            arrow(7, Direction.LEFT, 2, 1, 4, 3, 4),
            arrow(8, Direction.RIGHT, 3, 10, 4, 12, 4),
            arrow(9, Direction.DOWN, 0, 2, 6, 2, 11),
            arrow(10, Direction.DOWN, 1, 11, 6, 11, 11),
            arrow(11, Direction.RIGHT, 2, 5, 10, 8, 10),
            arrow(12, Direction.DOWN, 3, 6, 6, 6, 7),
            arrow(13, Direction.DOWN, 0, 7, 6, 7, 7),
            arrow(14, Direction.RIGHT, 1, 3, 13, 10, 13),
            arrow(15, Direction.UP, 2, 1, 12, 1, 8)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(21, LevelData(
            levelNumber = 21,
            title = "City 2 • Route 1 • First Wind",
            gridWidth = 11,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 8, 4, 7, 4, 7, 3, 7, 2),
            arrow(2, Direction.DOWN, 1, 1, 8, 1, 9),
            arrow(3, Direction.RIGHT, 2, 7, 1, 8, 1, 9, 1),
            arrow(4, Direction.DOWN, 3, 6, 6, 7, 6, 8, 6, 8, 7, 8, 8, 8, 9),
            arrow(5, Direction.RIGHT, 0, 2, 4, 3, 4, 4, 4),
            arrow(6, Direction.UP, 1, 2, 6, 2, 5),
            arrow(7, Direction.DOWN, 2, 4, 5, 4, 6)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(22, LevelData(
            levelNumber = 22,
            title = "City 2 • Route 2 • Dune Horizon",
            gridWidth = 10,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 6, 5, 6, 6, 6, 7, 6, 8, 6, 9),
            arrow(2, Direction.LEFT, 1, 3, 6, 3, 5, 3, 4, 2, 4, 1, 4, 0, 4),
            arrow(3, Direction.DOWN, 2, 2, 7, 2, 8),
            arrow(4, Direction.DOWN, 3, 8, 1, 8, 2, 8, 3, 8, 4),
            arrow(5, Direction.RIGHT, 0, 2, 9, 3, 9),
            arrow(6, Direction.LEFT, 1, 7, 4, 6, 4, 5, 4, 4, 4),
            arrow(7, Direction.DOWN, 2, 5, 5, 5, 6),
            arrow(8, Direction.DOWN, 3, 4, 5, 4, 6),
            arrow(9, Direction.UP, 0, 7, 7, 7, 6, 7, 5)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(23, LevelData(
            levelNumber = 23,
            title = "City 2 • Route 3 • Golden Dust",
            gridWidth = 11,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 4, 3, 4, 4, 4, 5),
            arrow(2, Direction.DOWN, 1, 6, 2, 6, 3, 6, 4, 6, 5, 6, 6),
            arrow(3, Direction.LEFT, 2, 8, 8, 7, 8, 6, 8, 5, 8),
            arrow(4, Direction.RIGHT, 3, 7, 1, 8, 1),
            arrow(5, Direction.RIGHT, 0, 2, 1, 3, 1, 4, 1),
            arrow(6, Direction.DOWN, 1, 2, 4, 3, 4, 3, 5, 3, 6),
            arrow(7, Direction.LEFT, 2, 3, 8, 3, 9, 3, 10, 2, 10, 1, 10, 0, 10)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(24, LevelData(
            levelNumber = 24,
            title = "City 2 • Route 4 • Sand Drift",
            gridWidth = 12,
            gridHeight = 10,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 2, 7, 2, 6, 2, 5, 2, 4),
            arrow(2, Direction.LEFT, 1, 7, 7, 6, 7, 5, 7, 4, 7),
            arrow(3, Direction.UP, 2, 5, 5, 5, 4, 5, 3, 5, 2),
            arrow(4, Direction.DOWN, 3, 3, 1, 3, 2, 3, 3, 3, 4),
            arrow(5, Direction.UP, 0, 1, 5, 1, 4),
            arrow(6, Direction.UP, 1, 7, 5, 7, 4, 7, 3, 7, 2),
            arrow(7, Direction.DOWN, 2, 10, 3, 10, 4),
            arrow(8, Direction.LEFT, 3, 6, 1, 5, 1),
            arrow(9, Direction.RIGHT, 0, 9, 1, 10, 1),
            arrow(10, Direction.DOWN, 1, 8, 5, 8, 6, 8, 7)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(25, LevelData(
            levelNumber = 25,
            title = "City 2 • Route 5 • Dune Run",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 5, 2, 1, 2),
            arrow(2, Direction.RIGHT, 1, 7, 2, 11, 2),
            arrow(3, Direction.RIGHT, 2, 1, 4, 5, 4),
            arrow(4, Direction.RIGHT, 3, 7, 4, 11, 4),
            arrow(5, Direction.UP, 0, 6, 5, 6, 1),
            arrow(6, Direction.RIGHT, 1, 1, 6, 11, 6),
            arrow(7, Direction.LEFT, 2, 11, 8, 1, 8),
            arrow(8, Direction.DOWN, 3, 2, 9, 2, 11),
            arrow(9, Direction.DOWN, 0, 10, 9, 10, 11),
            arrow(10, Direction.RIGHT, 1, 3, 10, 9, 10)
            ),
            maxDrops = 3,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(26, LevelData(
            levelNumber = 26,
            title = "City 2 • Route 6 • Oasis Trace",
            gridWidth = 10,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 4, 1, 5, 1),
            arrow(2, Direction.DOWN, 1, 8, 1, 8, 2, 8, 3, 8, 4, 8, 5),
            arrow(3, Direction.RIGHT, 2, 6, 9, 6, 8, 7, 8, 8, 8),
            arrow(4, Direction.RIGHT, 3, 2, 6, 3, 6, 4, 6, 5, 6),
            arrow(5, Direction.LEFT, 0, 5, 3, 5, 2, 4, 2, 3, 2),
            arrow(6, Direction.UP, 1, 2, 2, 2, 1),
            arrow(7, Direction.UP, 2, 7, 2, 7, 1, 7, 0),
            arrow(8, Direction.DOWN, 3, 6, 5, 6, 6),
            arrow(9, Direction.RIGHT, 0, 4, 7, 5, 7)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(27, LevelData(
            levelNumber = 27,
            title = "City 2 • Route 7 • Sun Flare",
            gridWidth = 12,
            gridHeight = 10,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 4, 5, 5, 5, 6, 5),
            arrow(2, Direction.RIGHT, 1, 4, 2, 4, 3, 4, 4, 5, 4, 6, 4, 7, 4),
            arrow(3, Direction.LEFT, 2, 6, 3, 5, 3),
            arrow(4, Direction.DOWN, 3, 7, 1, 8, 1, 9, 1, 9, 2, 9, 3, 9, 4),
            arrow(5, Direction.DOWN, 0, 8, 6, 9, 6, 9, 7, 9, 8),
            arrow(6, Direction.DOWN, 1, 1, 6, 1, 7, 1, 8, 1, 9),
            arrow(7, Direction.DOWN, 2, 2, 5, 2, 6, 2, 7),
            arrow(8, Direction.DOWN, 3, 3, 4, 3, 5, 3, 6),
            arrow(9, Direction.RIGHT, 0, 7, 8, 8, 8),
            arrow(10, Direction.LEFT, 1, 6, 6, 5, 6)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(28, LevelData(
            levelNumber = 28,
            title = "City 2 • Route 8 • Desert Ridge",
            gridWidth = 11,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 5, 8, 4, 8, 3, 8),
            arrow(2, Direction.DOWN, 1, 8, 5, 8, 6, 8, 7, 8, 8, 8, 9),
            arrow(3, Direction.LEFT, 2, 7, 3, 6, 3, 5, 3, 4, 3, 3, 3),
            arrow(4, Direction.UP, 3, 2, 8, 1, 8, 0, 8, 0, 7, 0, 6),
            arrow(5, Direction.DOWN, 0, 1, 1, 1, 2, 1, 3),
            arrow(6, Direction.LEFT, 1, 3, 5, 3, 4, 2, 4, 1, 4),
            arrow(7, Direction.DOWN, 2, 9, 3, 9, 4),
            arrow(8, Direction.LEFT, 3, 7, 1, 6, 1, 5, 1, 4, 1),
            arrow(9, Direction.RIGHT, 0, 6, 2, 7, 2)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(29, LevelData(
            levelNumber = 29,
            title = "City 2 • Route 9 • Mirage Walk",
            gridWidth = 11,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 6, 10, 6, 9),
            arrow(2, Direction.UP, 1, 1, 6, 0, 6, 0, 5, 0, 4),
            arrow(3, Direction.DOWN, 2, 9, 1, 9, 2, 9, 3),
            arrow(4, Direction.RIGHT, 3, 3, 1, 4, 1, 5, 1, 6, 1),
            arrow(5, Direction.DOWN, 0, 5, 3, 4, 3, 4, 4, 4, 5),
            arrow(6, Direction.DOWN, 1, 5, 7, 5, 8, 5, 9, 5, 10, 5, 11),
            arrow(7, Direction.DOWN, 2, 9, 8, 9, 9, 9, 10, 9, 11),
            arrow(8, Direction.RIGHT, 3, 7, 5, 8, 5, 9, 5),
            arrow(9, Direction.UP, 0, 2, 10, 2, 9, 2, 8, 2, 7, 2, 6)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(30, LevelData(
            levelNumber = 30,
            title = "City 2 • Route 10 • Wind Sweep",
            gridWidth = 11,
            gridHeight = 10,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 2, 7, 1, 7),
            arrow(2, Direction.UP, 1, 5, 8, 5, 7),
            arrow(3, Direction.UP, 2, 3, 2, 3, 1),
            arrow(4, Direction.DOWN, 3, 1, 2, 0, 2, 0, 3, 0, 4),
            arrow(5, Direction.RIGHT, 0, 8, 5, 9, 5),
            arrow(6, Direction.LEFT, 1, 6, 4, 5, 4, 4, 4),
            arrow(7, Direction.RIGHT, 2, 6, 6, 7, 6)
            ),
            maxDrops = 3,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(31, LevelData(
            levelNumber = 31,
            title = "City 2 • Route 11 • Canyon Way",
            gridWidth = 10,
            gridHeight = 10,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 6, 1, 6, 2, 6, 3, 6, 4),
            arrow(2, Direction.RIGHT, 1, 3, 6, 3, 5, 3, 4, 4, 4, 5, 4),
            arrow(3, Direction.LEFT, 2, 1, 3, 0, 3),
            arrow(4, Direction.DOWN, 3, 8, 6, 8, 7),
            arrow(5, Direction.LEFT, 0, 5, 8, 4, 8, 3, 8, 2, 8),
            arrow(6, Direction.DOWN, 1, 1, 6, 1, 7),
            arrow(7, Direction.UP, 2, 7, 8, 7, 7, 7, 6, 7, 5, 7, 4),
            arrow(8, Direction.LEFT, 3, 4, 1, 3, 1, 2, 1),
            arrow(9, Direction.LEFT, 0, 6, 5, 6, 6, 6, 7, 5, 7, 4, 7, 3, 7)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(32, LevelData(
            levelNumber = 32,
            title = "City 2 • Route 12 • Nomad Track",
            gridWidth = 10,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 1, 3, 2, 3),
            arrow(2, Direction.RIGHT, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1),
            arrow(3, Direction.LEFT, 2, 7, 5, 6, 5, 5, 5),
            arrow(4, Direction.UP, 3, 4, 8, 4, 7),
            arrow(5, Direction.RIGHT, 0, 1, 8, 2, 8),
            arrow(6, Direction.LEFT, 1, 6, 9, 5, 9),
            arrow(7, Direction.RIGHT, 2, 2, 10, 3, 10, 4, 10, 5, 10)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(33, LevelData(
            levelNumber = 33,
            title = "City 2 • Route 13 • Sunstone",
            gridWidth = 12,
            gridHeight = 10,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 8, 6, 9, 6, 9, 7, 9, 8),
            arrow(2, Direction.DOWN, 1, 6, 6, 6, 7),
            arrow(3, Direction.UP, 2, 9, 5, 10, 5, 11, 5, 11, 4, 11, 3),
            arrow(4, Direction.DOWN, 3, 3, 6, 3, 7),
            arrow(5, Direction.UP, 0, 7, 4, 7, 3),
            arrow(6, Direction.DOWN, 1, 5, 1, 5, 2, 5, 3),
            arrow(7, Direction.RIGHT, 2, 3, 8, 4, 8, 5, 8, 6, 8)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(34, LevelData(
            levelNumber = 34,
            title = "City 2 • Route 14 • Dust Devil",
            gridWidth = 11,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 3, 9, 3, 8, 3, 7, 3, 6, 3, 5),
            arrow(2, Direction.DOWN, 1, 8, 5, 8, 6),
            arrow(3, Direction.RIGHT, 2, 3, 2, 3, 3, 4, 3, 5, 3),
            arrow(4, Direction.UP, 3, 4, 7, 4, 6),
            arrow(5, Direction.DOWN, 0, 9, 5, 9, 6, 9, 7, 9, 8),
            arrow(6, Direction.UP, 1, 9, 3, 9, 2),
            arrow(7, Direction.UP, 2, 5, 9, 5, 8, 5, 7)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(35, LevelData(
            levelNumber = 35,
            title = "City 2 • Route 15 • Playful Kitty",
            gridWidth = 11,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 7, 9, 7, 8, 8, 8, 9, 8),
            arrow(2, Direction.DOWN, 1, 4, 2, 4, 3),
            arrow(3, Direction.DOWN, 2, 2, 5, 3, 5, 4, 5, 4, 6, 4, 7, 4, 8),
            arrow(4, Direction.LEFT, 3, 4, 1, 3, 1, 2, 1),
            arrow(5, Direction.DOWN, 0, 2, 9, 3, 9, 4, 9, 4, 10, 4, 11),
            arrow(6, Direction.RIGHT, 1, 5, 2, 6, 2),
            arrow(7, Direction.DOWN, 2, 6, 5, 5, 5, 5, 6, 5, 7)
            ),
            maxDrops = 3,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(36, LevelData(
            levelNumber = 36,
            title = "City 2 • Route 16 • Heat Haze",
            gridWidth = 11,
            gridHeight = 10,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 4, 4, 4, 3, 4, 2, 4, 1, 4, 0),
            arrow(2, Direction.RIGHT, 1, 2, 6, 2, 7, 2, 8, 3, 8, 4, 8, 5, 8),
            arrow(3, Direction.LEFT, 2, 6, 6, 5, 6),
            arrow(4, Direction.LEFT, 3, 1, 1, 0, 1),
            arrow(5, Direction.LEFT, 0, 3, 5, 2, 5),
            arrow(6, Direction.LEFT, 1, 9, 7, 8, 7),
            arrow(7, Direction.LEFT, 2, 7, 7, 6, 7, 5, 7, 4, 7),
            arrow(8, Direction.RIGHT, 3, 6, 3, 7, 3, 8, 3),
            arrow(9, Direction.UP, 0, 9, 5, 9, 4),
            arrow(10, Direction.LEFT, 1, 8, 5, 7, 5, 6, 5, 5, 5, 4, 5)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(37, LevelData(
            levelNumber = 37,
            title = "City 2 • Route 17 • Scorpion Pass",
            gridWidth = 11,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 5, 6, 5, 5, 5, 4, 4, 4, 3, 4, 2, 4),
            arrow(2, Direction.DOWN, 1, 2, 5, 3, 5, 3, 6, 3, 7),
            arrow(3, Direction.DOWN, 2, 3, 8, 3, 9, 3, 10),
            arrow(4, Direction.LEFT, 3, 8, 10, 7, 10, 6, 10),
            arrow(5, Direction.UP, 0, 4, 9, 4, 8),
            arrow(6, Direction.UP, 1, 2, 8, 2, 7),
            arrow(7, Direction.LEFT, 2, 8, 4, 7, 4),
            arrow(8, Direction.LEFT, 3, 6, 7, 5, 7),
            arrow(9, Direction.RIGHT, 0, 6, 8, 7, 8)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(38, LevelData(
            levelNumber = 38,
            title = "City 2 • Route 18 • Red Sands",
            gridWidth = 10,
            gridHeight = 10,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 2, 8, 1, 8, 0, 8, 0, 7, 0, 6, 0, 5),
            arrow(2, Direction.RIGHT, 1, 4, 4, 4, 5, 4, 6, 5, 6, 6, 6, 7, 6),
            arrow(3, Direction.RIGHT, 2, 8, 1, 9, 1),
            arrow(4, Direction.RIGHT, 3, 5, 4, 6, 4, 7, 4),
            arrow(5, Direction.UP, 0, 4, 3, 4, 2),
            arrow(6, Direction.UP, 1, 2, 4, 2, 3, 2, 2, 2, 1),
            arrow(7, Direction.UP, 2, 8, 4, 8, 3, 8, 2),
            arrow(8, Direction.LEFT, 3, 8, 8, 7, 8, 6, 8, 5, 8),
            arrow(9, Direction.DOWN, 0, 7, 1, 7, 2)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(39, LevelData(
            levelNumber = 39,
            title = "City 2 • Route 19 • Sirocco",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 1, 10, 1, 11),
            arrow(2, Direction.UP, 1, 7, 5, 7, 4, 7, 3, 7, 2),
            arrow(3, Direction.UP, 2, 1, 7, 1, 6, 1, 5, 1, 4, 1, 3),
            arrow(4, Direction.UP, 3, 10, 4, 10, 3, 10, 2, 10, 1),
            arrow(5, Direction.UP, 0, 5, 10, 5, 9),
            arrow(6, Direction.UP, 1, 5, 1, 5, 0),
            arrow(7, Direction.LEFT, 2, 6, 6, 5, 6, 4, 6, 3, 6),
            arrow(8, Direction.DOWN, 3, 9, 1, 9, 2, 9, 3, 9, 4, 9, 5),
            arrow(9, Direction.UP, 0, 10, 8, 10, 7, 10, 6, 10, 5),
            arrow(10, Direction.DOWN, 1, 1, 8, 1, 9)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(40, LevelData(
            levelNumber = 40,
            title = "City 2 • Route 20 • Loyal Companion",
            gridWidth = 10,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 8, 6, 8, 5, 8, 4, 8, 3),
            arrow(2, Direction.UP, 1, 6, 3, 5, 3, 4, 3, 4, 2, 4, 1),
            arrow(3, Direction.UP, 2, 1, 5, 1, 4),
            arrow(4, Direction.DOWN, 3, 7, 2, 8, 2, 9, 2, 9, 3, 9, 4, 9, 5),
            arrow(5, Direction.DOWN, 0, 3, 1, 3, 2, 3, 3, 3, 4, 3, 5),
            arrow(6, Direction.UP, 1, 1, 1, 1, 0),
            arrow(7, Direction.DOWN, 2, 6, 9, 7, 9, 7, 10, 7, 11),
            arrow(8, Direction.RIGHT, 3, 7, 8, 8, 8),
            arrow(9, Direction.RIGHT, 0, 4, 4, 5, 4),
            arrow(10, Direction.UP, 1, 5, 6, 5, 5)
            ),
            maxDrops = 3,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(41, LevelData(
            levelNumber = 41,
            title = "City 3 • Route 1 • Shallow Tide",
            gridWidth = 13,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 2, 1, 3, 1, 4, 1, 5, 1),
            arrow(2, Direction.DOWN, 1, 5, 3, 5, 4),
            arrow(3, Direction.DOWN, 2, 4, 3, 4, 4, 4, 5),
            arrow(4, Direction.UP, 3, 8, 8, 8, 7),
            arrow(5, Direction.LEFT, 0, 10, 4, 9, 4, 8, 4),
            arrow(6, Direction.LEFT, 1, 5, 9, 4, 9, 3, 9, 2, 9, 1, 9),
            arrow(7, Direction.LEFT, 2, 6, 6, 5, 6),
            arrow(8, Direction.UP, 3, 10, 7, 10, 6)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(42, LevelData(
            levelNumber = 42,
            title = "City 3 • Route 2 • Coral Branch",
            gridWidth = 11,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 5, 6, 4, 6, 3, 6),
            arrow(2, Direction.LEFT, 1, 6, 1, 6, 2, 5, 2, 4, 2),
            arrow(3, Direction.UP, 2, 5, 11, 6, 11, 7, 11, 7, 10, 7, 9),
            arrow(4, Direction.UP, 3, 7, 4, 8, 4, 9, 4, 9, 3, 9, 2, 9, 1),
            arrow(5, Direction.DOWN, 0, 8, 5, 8, 6, 8, 7),
            arrow(6, Direction.UP, 1, 9, 11, 9, 10, 9, 9, 9, 8),
            arrow(7, Direction.UP, 2, 3, 10, 3, 9, 3, 8, 3, 7),
            arrow(8, Direction.UP, 3, 2, 8, 2, 7, 2, 6),
            arrow(9, Direction.DOWN, 0, 2, 9, 1, 9, 0, 9, 0, 10, 0, 11, 0, 12),
            arrow(10, Direction.DOWN, 1, 2, 10, 2, 11),
            arrow(11, Direction.LEFT, 2, 6, 9, 5, 9)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(43, LevelData(
            levelNumber = 43,
            title = "City 3 • Route 3 • Ocean Drift",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 8, 5, 8, 6, 8, 7),
            arrow(2, Direction.DOWN, 1, 7, 8, 7, 9, 7, 10, 7, 11),
            arrow(3, Direction.RIGHT, 2, 3, 10, 4, 10, 5, 10, 6, 10),
            arrow(4, Direction.DOWN, 3, 3, 1, 3, 2),
            arrow(5, Direction.RIGHT, 0, 3, 7, 4, 7, 5, 7, 6, 7, 7, 7),
            arrow(6, Direction.LEFT, 1, 7, 5, 6, 5),
            arrow(7, Direction.DOWN, 2, 3, 5, 3, 6),
            arrow(8, Direction.UP, 3, 5, 6, 5, 5, 5, 4, 5, 3, 5, 2),
            arrow(9, Direction.UP, 0, 1, 3, 1, 2)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(44, LevelData(
            levelNumber = 44,
            title = "City 3 • Route 4 • Sea Ripple",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 10, 9, 11, 9),
            arrow(2, Direction.UP, 1, 7, 8, 7, 7, 7, 6, 7, 5),
            arrow(3, Direction.UP, 2, 4, 5, 4, 4),
            arrow(4, Direction.UP, 3, 2, 9, 3, 9, 4, 9, 4, 8, 4, 7, 4, 6),
            arrow(5, Direction.LEFT, 0, 7, 1, 6, 1),
            arrow(6, Direction.LEFT, 1, 2, 8, 1, 8),
            arrow(7, Direction.DOWN, 2, 2, 1, 2, 2),
            arrow(8, Direction.UP, 3, 4, 3, 4, 2)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(45, LevelData(
            levelNumber = 45,
            title = "City 3 • Route 5 • First Wave",
            gridWidth = 12,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 1, 3, 0, 3),
            arrow(2, Direction.RIGHT, 1, 3, 5, 3, 4, 3, 3, 4, 3, 5, 3, 6, 3),
            arrow(3, Direction.DOWN, 2, 2, 4, 2, 5, 2, 6, 2, 7),
            arrow(4, Direction.DOWN, 3, 5, 8, 5, 9),
            arrow(5, Direction.DOWN, 0, 6, 9, 6, 10),
            arrow(6, Direction.DOWN, 1, 10, 6, 9, 6, 8, 6, 8, 7, 8, 8),
            arrow(7, Direction.DOWN, 2, 8, 3, 9, 3, 9, 4, 9, 5),
            arrow(8, Direction.UP, 3, 5, 2, 5, 1, 5, 0),
            arrow(9, Direction.RIGHT, 0, 6, 2, 7, 2, 8, 2),
            arrow(10, Direction.UP, 1, 3, 9, 3, 8, 3, 7)
            ),
            maxDrops = 3,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(46, LevelData(
            levelNumber = 46,
            title = "City 3 • Route 6 • Harbor Light",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 5, 9, 5, 10, 5, 11, 4, 11, 3, 11),
            arrow(2, Direction.LEFT, 1, 8, 3, 7, 3),
            arrow(3, Direction.UP, 2, 9, 9, 9, 8),
            arrow(4, Direction.LEFT, 3, 9, 4, 8, 4, 7, 4),
            arrow(5, Direction.RIGHT, 0, 3, 8, 3, 7, 3, 6, 4, 6, 5, 6),
            arrow(6, Direction.RIGHT, 1, 1, 5, 2, 5, 3, 5, 4, 5),
            arrow(7, Direction.RIGHT, 2, 10, 6, 11, 6),
            arrow(8, Direction.RIGHT, 3, 1, 4, 1, 3, 1, 2, 2, 2, 3, 2, 4, 2),
            arrow(9, Direction.LEFT, 0, 8, 1, 7, 1),
            arrow(10, Direction.LEFT, 1, 5, 5, 5, 4, 5, 3, 4, 3, 3, 3, 2, 3)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(47, LevelData(
            levelNumber = 47,
            title = "City 3 • Route 7 • Azure Stream",
            gridWidth = 11,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 4, 9, 5, 9, 6, 9, 7, 9, 8, 9),
            arrow(2, Direction.RIGHT, 1, 5, 5, 6, 5, 7, 5),
            arrow(3, Direction.RIGHT, 2, 7, 6, 7, 7, 7, 8, 8, 8, 9, 8, 10, 8),
            arrow(4, Direction.LEFT, 3, 6, 6, 5, 6, 4, 6),
            arrow(5, Direction.UP, 0, 6, 2, 6, 1),
            arrow(6, Direction.UP, 1, 4, 2, 3, 2, 3, 1, 3, 0),
            arrow(7, Direction.RIGHT, 2, 1, 4, 1, 5, 2, 5, 3, 5),
            arrow(8, Direction.LEFT, 3, 7, 4, 6, 4),
            arrow(9, Direction.UP, 0, 8, 5, 8, 4, 8, 3)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(48, LevelData(
            levelNumber = 48,
            title = "City 3 • Route 8 • Currents",
            gridWidth = 11,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 7, 6, 7, 7),
            arrow(2, Direction.RIGHT, 1, 2, 9, 3, 9),
            arrow(3, Direction.DOWN, 2, 1, 2, 1, 3, 1, 4),
            arrow(4, Direction.LEFT, 3, 4, 6, 3, 6, 2, 6, 1, 6),
            arrow(5, Direction.LEFT, 0, 8, 4, 7, 4),
            arrow(6, Direction.DOWN, 1, 9, 6, 9, 7, 9, 8, 9, 9, 9, 10),
            arrow(7, Direction.DOWN, 2, 4, 7, 4, 8, 4, 9, 4, 10),
            arrow(8, Direction.DOWN, 3, 3, 1, 3, 2, 3, 3, 3, 4, 3, 5),
            arrow(9, Direction.RIGHT, 0, 6, 2, 7, 2, 8, 2, 9, 2)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(49, LevelData(
            levelNumber = 49,
            title = "City 3 • Route 9 • Whirlpool",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 1, 8, 1, 9, 1, 10, 1, 11),
            arrow(2, Direction.UP, 1, 7, 8, 7, 7),
            arrow(3, Direction.RIGHT, 2, 1, 1, 1, 2, 2, 2, 3, 2),
            arrow(4, Direction.UP, 3, 9, 9, 8, 9, 8, 8, 8, 7),
            arrow(5, Direction.LEFT, 0, 6, 10, 5, 10),
            arrow(6, Direction.UP, 1, 2, 7, 2, 6, 2, 5, 2, 4),
            arrow(7, Direction.LEFT, 2, 7, 4, 7, 5, 6, 5, 5, 5),
            arrow(8, Direction.RIGHT, 3, 3, 8, 4, 8),
            arrow(9, Direction.UP, 0, 1, 5, 1, 4, 1, 3)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(50, LevelData(
            levelNumber = 50,
            title = "City 3 • Route 10 • Swimming Koi",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 10, 5, 11, 5, 12, 5, 12, 4, 12, 3, 12, 2),
            arrow(2, Direction.DOWN, 1, 5, 1, 4, 1, 4, 2, 4, 3),
            arrow(3, Direction.RIGHT, 2, 3, 10, 4, 10),
            arrow(4, Direction.UP, 3, 10, 7, 10, 6),
            arrow(5, Direction.DOWN, 0, 7, 5, 6, 5, 5, 5, 5, 6, 5, 7, 5, 8),
            arrow(6, Direction.UP, 1, 3, 6, 3, 5, 3, 4, 3, 3, 3, 2),
            arrow(7, Direction.RIGHT, 2, 8, 11, 9, 11, 10, 11, 11, 11, 12, 11),
            arrow(8, Direction.UP, 3, 7, 8, 7, 7, 7, 6),
            arrow(9, Direction.LEFT, 0, 10, 9, 9, 9, 8, 9, 7, 9, 6, 9),
            arrow(10, Direction.RIGHT, 1, 1, 10, 1, 9, 1, 8, 2, 8, 3, 8, 4, 8),
            arrow(11, Direction.UP, 2, 1, 3, 1, 2)
            ),
            maxDrops = 3,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

    }

    private fun loadChunk2(map: MutableMap<Int, LevelData>) {
        map.put(51, LevelData(
            levelNumber = 51,
            title = "City 3 • Route 11 • Deep Trench",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 10, 9, 9, 9, 8, 9, 8, 8, 8, 7, 8, 6),
            arrow(2, Direction.DOWN, 1, 6, 4, 6, 5, 6, 6, 6, 7),
            arrow(3, Direction.RIGHT, 2, 2, 5, 3, 5, 4, 5, 5, 5),
            arrow(4, Direction.RIGHT, 3, 6, 8, 7, 8),
            arrow(5, Direction.UP, 0, 8, 1, 8, 0),
            arrow(6, Direction.DOWN, 1, 1, 6, 1, 7, 1, 8),
            arrow(7, Direction.LEFT, 2, 6, 3, 5, 3, 4, 3, 3, 3),
            arrow(8, Direction.RIGHT, 3, 10, 3, 11, 3),
            arrow(9, Direction.LEFT, 0, 4, 8, 3, 8)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(52, LevelData(
            levelNumber = 52,
            title = "City 3 • Route 12 • Reef Run",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 5, 6, 6, 6),
            arrow(2, Direction.DOWN, 1, 3, 8, 3, 9, 3, 10),
            arrow(3, Direction.LEFT, 2, 5, 1, 4, 1, 3, 1),
            arrow(4, Direction.LEFT, 3, 4, 7, 3, 7, 2, 7, 1, 7, 0, 7),
            arrow(5, Direction.UP, 0, 10, 11, 10, 10, 10, 9),
            arrow(6, Direction.LEFT, 1, 6, 7, 6, 8, 5, 8, 4, 8),
            arrow(7, Direction.RIGHT, 2, 1, 6, 2, 6, 3, 6, 4, 6),
            arrow(8, Direction.UP, 3, 11, 7, 11, 6, 11, 5),
            arrow(9, Direction.RIGHT, 0, 5, 9, 5, 10, 5, 11, 6, 11, 7, 11, 8, 11),
            arrow(10, Direction.DOWN, 1, 8, 3, 8, 4),
            arrow(11, Direction.RIGHT, 2, 9, 4, 10, 4)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(53, LevelData(
            levelNumber = 53,
            title = "City 3 • Route 13 • Lagoon Bend",
            gridWidth = 11,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 7, 11, 6, 11, 5, 11, 5, 10, 5, 9),
            arrow(2, Direction.UP, 1, 8, 5, 8, 4),
            arrow(3, Direction.LEFT, 2, 4, 11, 3, 11, 2, 11, 1, 11),
            arrow(4, Direction.LEFT, 3, 8, 7, 8, 8, 7, 8, 6, 8),
            arrow(5, Direction.LEFT, 0, 6, 3, 5, 3),
            arrow(6, Direction.DOWN, 1, 4, 1, 4, 2, 4, 3),
            arrow(7, Direction.UP, 2, 3, 9, 3, 8),
            arrow(8, Direction.RIGHT, 3, 8, 2, 9, 2, 10, 2),
            arrow(9, Direction.DOWN, 0, 6, 5, 6, 6),
            arrow(10, Direction.LEFT, 1, 7, 4, 6, 4),
            arrow(11, Direction.DOWN, 2, 1, 8, 1, 9, 1, 10)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(54, LevelData(
            levelNumber = 54,
            title = "City 3 • Route 14 • Sailor's Path",
            gridWidth = 13,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 6, 6, 7, 6),
            arrow(2, Direction.DOWN, 1, 5, 3, 5, 4),
            arrow(3, Direction.LEFT, 2, 5, 6, 4, 6),
            arrow(4, Direction.UP, 3, 1, 8, 0, 8, 0, 7, 0, 6),
            arrow(5, Direction.LEFT, 0, 2, 6, 2, 5, 2, 4, 1, 4, 0, 4),
            arrow(6, Direction.LEFT, 1, 6, 4, 6, 3, 6, 2, 5, 2, 4, 2),
            arrow(7, Direction.LEFT, 2, 10, 8, 9, 8),
            arrow(8, Direction.LEFT, 3, 11, 5, 10, 5, 9, 5, 8, 5, 7, 5),
            arrow(9, Direction.RIGHT, 0, 7, 9, 8, 9, 9, 9, 10, 9)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(55, LevelData(
            levelNumber = 55,
            title = "City 3 • Route 15 • Tidal Fork",
            gridWidth = 12,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 4, 5, 4, 4, 5, 4, 6, 4),
            arrow(2, Direction.UP, 1, 8, 9, 8, 8, 8, 7, 8, 6, 8, 5),
            arrow(3, Direction.UP, 2, 7, 5, 7, 4),
            arrow(4, Direction.DOWN, 3, 4, 9, 4, 10),
            arrow(5, Direction.LEFT, 0, 8, 3, 7, 3),
            arrow(6, Direction.LEFT, 1, 2, 5, 1, 5),
            arrow(7, Direction.LEFT, 2, 2, 4, 1, 4),
            arrow(8, Direction.DOWN, 3, 3, 4, 3, 5, 3, 6, 3, 7, 3, 8),
            arrow(9, Direction.UP, 0, 2, 1, 2, 0),
            arrow(10, Direction.LEFT, 1, 8, 1, 7, 1),
            arrow(11, Direction.UP, 2, 10, 6, 10, 5, 10, 4)
            ),
            maxDrops = 3,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(56, LevelData(
            levelNumber = 56,
            title = "City 3 • Route 16 • Aquatic Maze",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 2, 4, 2, 3),
            arrow(2, Direction.RIGHT, 1, 9, 7, 10, 7),
            arrow(3, Direction.DOWN, 2, 7, 4, 8, 4, 8, 5, 8, 6),
            arrow(4, Direction.DOWN, 3, 8, 2, 9, 2, 10, 2, 10, 3, 10, 4),
            arrow(5, Direction.UP, 0, 1, 9, 2, 9, 3, 9, 3, 8, 3, 7),
            arrow(6, Direction.UP, 1, 7, 9, 7, 8),
            arrow(7, Direction.UP, 2, 6, 10, 6, 9),
            arrow(8, Direction.LEFT, 3, 11, 8, 10, 8, 9, 8),
            arrow(9, Direction.DOWN, 0, 4, 8, 4, 9, 4, 10, 4, 11)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(57, LevelData(
            levelNumber = 57,
            title = "City 3 • Route 17 • Storm Surge",
            gridWidth = 13,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 5, 2, 4, 2, 3, 2, 2, 2),
            arrow(2, Direction.UP, 1, 8, 6, 8, 5, 8, 4, 8, 3, 8, 2),
            arrow(3, Direction.RIGHT, 2, 3, 5, 4, 5, 5, 5, 6, 5),
            arrow(4, Direction.RIGHT, 3, 1, 6, 2, 6),
            arrow(5, Direction.LEFT, 0, 11, 9, 10, 9, 9, 9),
            arrow(6, Direction.RIGHT, 1, 9, 1, 10, 1, 11, 1),
            arrow(7, Direction.UP, 2, 2, 5, 2, 4),
            arrow(8, Direction.LEFT, 3, 6, 1, 5, 1, 4, 1, 3, 1),
            arrow(9, Direction.LEFT, 0, 11, 4, 10, 4),
            arrow(10, Direction.LEFT, 1, 7, 4, 6, 4, 5, 4, 4, 4)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(58, LevelData(
            levelNumber = 58,
            title = "City 3 • Route 18 • Abyssal Wake",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 7, 10, 6, 10, 5, 10),
            arrow(2, Direction.DOWN, 1, 6, 2, 6, 3),
            arrow(3, Direction.DOWN, 2, 4, 9, 4, 10, 4, 11),
            arrow(4, Direction.RIGHT, 3, 10, 5, 11, 5),
            arrow(5, Direction.UP, 0, 8, 8, 8, 7, 8, 6, 8, 5),
            arrow(6, Direction.RIGHT, 1, 4, 2, 5, 2),
            arrow(7, Direction.LEFT, 2, 10, 3, 9, 3, 8, 3),
            arrow(8, Direction.LEFT, 3, 2, 5, 1, 5),
            arrow(9, Direction.DOWN, 0, 6, 4, 6, 5, 6, 6),
            arrow(10, Direction.RIGHT, 1, 5, 1, 6, 1, 7, 1)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(59, LevelData(
            levelNumber = 59,
            title = "City 3 • Route 19 • Pearl Drift",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 4, 9, 4, 8),
            arrow(2, Direction.UP, 1, 9, 8, 8, 8, 7, 8, 7, 7, 7, 6, 7, 5),
            arrow(3, Direction.LEFT, 2, 5, 4, 4, 4),
            arrow(4, Direction.RIGHT, 3, 9, 5, 9, 6, 10, 6, 11, 6),
            arrow(5, Direction.UP, 0, 5, 10, 5, 9),
            arrow(6, Direction.DOWN, 1, 8, 1, 8, 2),
            arrow(7, Direction.RIGHT, 2, 4, 7, 4, 6, 4, 5, 5, 5, 6, 5),
            arrow(8, Direction.LEFT, 3, 10, 8, 10, 9, 10, 10, 9, 10, 8, 10, 7, 10)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(60, LevelData(
            levelNumber = 60,
            title = "City 3 • Route 20 • The Tide",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 8, 9, 7, 9, 6, 9, 6, 8, 6, 7, 6, 6),
            arrow(2, Direction.UP, 1, 9, 4, 9, 3, 9, 2),
            arrow(3, Direction.UP, 2, 4, 4, 4, 3),
            arrow(4, Direction.RIGHT, 3, 1, 8, 1, 9, 1, 10, 2, 10, 3, 10, 4, 10),
            arrow(5, Direction.RIGHT, 0, 1, 2, 1, 3, 1, 4, 2, 4, 3, 4),
            arrow(6, Direction.DOWN, 1, 7, 4, 7, 5),
            arrow(7, Direction.UP, 2, 5, 7, 5, 6),
            arrow(8, Direction.LEFT, 3, 10, 3, 10, 2, 10, 1, 9, 1, 8, 1),
            arrow(9, Direction.UP, 0, 11, 3, 11, 2),
            arrow(10, Direction.LEFT, 1, 4, 1, 3, 1)
            ),
            maxDrops = 3,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(61, LevelData(
            levelNumber = 61,
            title = "City 4 • Route 1 • Sprout",
            gridWidth = 14,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 8, 10, 8, 9, 9, 9, 10, 9),
            arrow(2, Direction.UP, 1, 11, 5, 11, 4),
            arrow(3, Direction.UP, 2, 9, 8, 10, 8, 10, 7, 10, 6),
            arrow(4, Direction.UP, 3, 5, 5, 5, 4, 5, 3, 5, 2, 5, 1),
            arrow(5, Direction.LEFT, 0, 12, 10, 11, 10),
            arrow(6, Direction.RIGHT, 1, 1, 4, 2, 4, 3, 4),
            arrow(7, Direction.LEFT, 2, 4, 8, 3, 8),
            arrow(8, Direction.UP, 3, 6, 9, 6, 8),
            arrow(9, Direction.LEFT, 0, 10, 5, 9, 5, 8, 5, 7, 5),
            arrow(10, Direction.UP, 1, 2, 3, 2, 2),
            arrow(11, Direction.DOWN, 2, 4, 4, 4, 5, 4, 6, 4, 7),
            arrow(12, Direction.LEFT, 3, 3, 10, 2, 10, 1, 10),
            arrow(13, Direction.DOWN, 0, 1, 7, 1, 8)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(62, LevelData(
            levelNumber = 62,
            title = "City 4 • Route 2 • Mossy Path",
            gridWidth = 14,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 10, 10, 10, 9, 10, 8, 11, 8, 12, 8),
            arrow(2, Direction.LEFT, 1, 4, 8, 4, 7, 3, 7, 2, 7),
            arrow(3, Direction.LEFT, 2, 9, 7, 8, 7, 7, 7),
            arrow(4, Direction.RIGHT, 3, 7, 4, 8, 4),
            arrow(5, Direction.LEFT, 0, 9, 11, 9, 10, 9, 9, 8, 9, 7, 9),
            arrow(6, Direction.LEFT, 1, 6, 12, 5, 12, 4, 12, 3, 12, 2, 12),
            arrow(7, Direction.LEFT, 2, 7, 8, 6, 8, 5, 8),
            arrow(8, Direction.LEFT, 3, 6, 7, 6, 6, 6, 5, 5, 5, 4, 5),
            arrow(9, Direction.RIGHT, 0, 1, 5, 1, 4, 1, 3, 2, 3, 3, 3),
            arrow(10, Direction.DOWN, 1, 5, 9, 4, 9, 4, 10, 4, 11),
            arrow(11, Direction.DOWN, 2, 10, 3, 10, 4, 10, 5, 10, 6, 10, 7)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(63, LevelData(
            levelNumber = 63,
            title = "City 4 • Route 3 • Willow Branch",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 11, 6, 11, 5, 11, 4, 11, 3),
            arrow(2, Direction.UP, 1, 6, 9, 6, 8, 6, 7),
            arrow(3, Direction.LEFT, 2, 3, 3, 2, 3, 1, 3),
            arrow(4, Direction.RIGHT, 3, 5, 2, 6, 2, 7, 2),
            arrow(5, Direction.UP, 0, 10, 2, 10, 1, 10, 0),
            arrow(6, Direction.UP, 1, 6, 11, 7, 11, 8, 11, 8, 10, 8, 9, 8, 8),
            arrow(7, Direction.LEFT, 2, 6, 4, 5, 4, 4, 4, 3, 4),
            arrow(8, Direction.UP, 3, 2, 11, 2, 10, 2, 9),
            arrow(9, Direction.LEFT, 0, 10, 9, 10, 8, 10, 7, 9, 7, 8, 7, 7, 7),
            arrow(10, Direction.LEFT, 1, 4, 2, 3, 2)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(64, LevelData(
            levelNumber = 64,
            title = "City 4 • Route 4 • Green Canopy",
            gridWidth = 14,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 5, 10, 5, 9, 5, 8, 5, 7, 5, 6),
            arrow(2, Direction.LEFT, 1, 6, 3, 5, 3, 4, 3),
            arrow(3, Direction.RIGHT, 2, 2, 10, 3, 10),
            arrow(4, Direction.LEFT, 3, 10, 8, 9, 8, 8, 8, 7, 8),
            arrow(5, Direction.UP, 0, 6, 11, 6, 10, 6, 9, 6, 8),
            arrow(6, Direction.LEFT, 1, 10, 6, 9, 6, 8, 6, 7, 6, 6, 6),
            arrow(7, Direction.RIGHT, 2, 2, 9, 3, 9, 4, 9),
            arrow(8, Direction.UP, 3, 8, 2, 8, 1),
            arrow(9, Direction.LEFT, 0, 11, 2, 10, 2, 9, 2),
            arrow(10, Direction.LEFT, 1, 11, 1, 10, 1)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(65, LevelData(
            levelNumber = 65,
            title = "City 4 • Route 5 • Ancient Roots",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 4, 1, 5, 1, 6, 1),
            arrow(2, Direction.LEFT, 1, 2, 4, 1, 4),
            arrow(3, Direction.DOWN, 2, 2, 10, 2, 11),
            arrow(4, Direction.UP, 3, 5, 5, 5, 4, 5, 3),
            arrow(5, Direction.UP, 0, 3, 4, 3, 3, 3, 2, 3, 1),
            arrow(6, Direction.RIGHT, 1, 3, 6, 4, 6),
            arrow(7, Direction.LEFT, 2, 10, 9, 9, 9, 8, 9, 7, 9),
            arrow(8, Direction.RIGHT, 3, 7, 2, 7, 1, 7, 0, 8, 0, 9, 0),
            arrow(9, Direction.UP, 0, 5, 10, 5, 9, 5, 8, 5, 7, 5, 6)
            ),
            maxDrops = 3,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(66, LevelData(
            levelNumber = 66,
            title = "City 4 • Route 6 • Timber Line",
            gridWidth = 14,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 10, 2, 10, 3, 10, 4),
            arrow(2, Direction.UP, 1, 8, 3, 7, 3, 6, 3, 6, 2, 6, 1, 6, 0),
            arrow(3, Direction.RIGHT, 2, 5, 8, 5, 7, 5, 6, 6, 6, 7, 6),
            arrow(4, Direction.DOWN, 3, 4, 2, 4, 3, 4, 4),
            arrow(5, Direction.LEFT, 0, 3, 1, 2, 1, 1, 1, 0, 1),
            arrow(6, Direction.LEFT, 1, 12, 2, 11, 2),
            arrow(7, Direction.UP, 2, 8, 9, 8, 8),
            arrow(8, Direction.UP, 3, 4, 6, 3, 6, 2, 6, 2, 5, 2, 4, 2, 3),
            arrow(9, Direction.LEFT, 0, 1, 8, 0, 8),
            arrow(10, Direction.RIGHT, 1, 3, 9, 4, 9, 5, 9, 6, 9),
            arrow(11, Direction.DOWN, 2, 11, 5, 11, 6, 11, 7, 11, 8, 11, 9)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(67, LevelData(
            levelNumber = 67,
            title = "City 4 • Route 7 • Fern Grove",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 6, 7, 5, 7, 4, 7),
            arrow(2, Direction.DOWN, 1, 7, 4, 8, 4, 8, 5, 8, 6),
            arrow(3, Direction.RIGHT, 2, 1, 9, 1, 10, 1, 11, 2, 11, 3, 11),
            arrow(4, Direction.RIGHT, 3, 11, 5, 12, 5),
            arrow(5, Direction.LEFT, 0, 9, 7, 8, 7),
            arrow(6, Direction.DOWN, 1, 1, 5, 0, 5, 0, 6, 0, 7),
            arrow(7, Direction.RIGHT, 2, 3, 10, 4, 10, 5, 10),
            arrow(8, Direction.RIGHT, 3, 3, 5, 4, 5, 5, 5),
            arrow(9, Direction.RIGHT, 0, 5, 1, 5, 2, 5, 3, 6, 3, 7, 3, 8, 3),
            arrow(10, Direction.RIGHT, 1, 8, 2, 9, 2, 10, 2, 11, 2, 12, 2),
            arrow(11, Direction.DOWN, 2, 8, 8, 7, 8, 6, 8, 6, 9, 6, 10, 6, 11)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(68, LevelData(
            levelNumber = 68,
            title = "City 4 • Route 8 • Bark & Bough",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 1, 8, 1, 9, 1, 10),
            arrow(2, Direction.UP, 1, 5, 10, 5, 9),
            arrow(3, Direction.LEFT, 2, 8, 10, 7, 10, 6, 10),
            arrow(4, Direction.RIGHT, 3, 9, 3, 9, 2, 10, 2, 11, 2),
            arrow(5, Direction.UP, 0, 10, 6, 9, 6, 9, 5, 9, 4),
            arrow(6, Direction.UP, 1, 2, 7, 2, 6, 2, 5),
            arrow(7, Direction.DOWN, 2, 6, 2, 6, 3, 6, 4, 6, 5, 6, 6),
            arrow(8, Direction.UP, 3, 3, 1, 3, 0),
            arrow(9, Direction.RIGHT, 0, 1, 4, 2, 4, 3, 4),
            arrow(10, Direction.DOWN, 1, 3, 8, 3, 9)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(69, LevelData(
            levelNumber = 69,
            title = "City 4 • Route 9 • Forest Clearing",
            gridWidth = 12,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1),
            arrow(2, Direction.RIGHT, 1, 6, 9, 7, 9),
            arrow(3, Direction.DOWN, 2, 10, 7, 10, 8, 10, 9, 10, 10, 10, 11),
            arrow(4, Direction.UP, 3, 3, 10, 4, 10, 5, 10, 5, 9, 5, 8, 5, 7),
            arrow(5, Direction.LEFT, 0, 5, 3, 4, 3),
            arrow(6, Direction.RIGHT, 1, 4, 2, 5, 2, 6, 2, 7, 2, 8, 2),
            arrow(7, Direction.DOWN, 2, 7, 4, 7, 5, 7, 6),
            arrow(8, Direction.UP, 3, 10, 6, 10, 5, 10, 4, 10, 3, 10, 2),
            arrow(9, Direction.RIGHT, 0, 1, 5, 2, 5, 3, 5, 4, 5),
            arrow(10, Direction.RIGHT, 1, 2, 4, 3, 4, 4, 4, 5, 4, 6, 4)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(70, LevelData(
            levelNumber = 70,
            title = "City 4 • Route 10 • Forest Canopy",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 9, 9, 8, 9),
            arrow(2, Direction.UP, 1, 2, 2, 2, 1),
            arrow(3, Direction.RIGHT, 2, 2, 6, 3, 6, 4, 6, 5, 6),
            arrow(4, Direction.UP, 3, 4, 11, 4, 10, 4, 9, 4, 8, 4, 7),
            arrow(5, Direction.DOWN, 0, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6),
            arrow(6, Direction.DOWN, 1, 9, 2, 9, 3, 9, 4, 9, 5),
            arrow(7, Direction.LEFT, 2, 5, 3, 4, 3),
            arrow(8, Direction.UP, 3, 11, 2, 11, 1),
            arrow(9, Direction.RIGHT, 0, 8, 8, 9, 8),
            arrow(10, Direction.RIGHT, 1, 3, 1, 4, 1),
            arrow(11, Direction.UP, 2, 6, 3, 7, 3, 8, 3, 8, 2, 8, 1, 8, 0)
            ),
            maxDrops = 3,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(71, LevelData(
            levelNumber = 71,
            title = "City 4 • Route 11 • Bramble Maze",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 3, 6, 4, 6, 5, 6, 5, 7, 5, 8, 5, 9),
            arrow(2, Direction.LEFT, 1, 5, 4, 5, 3, 5, 2, 4, 2, 3, 2, 2, 2),
            arrow(3, Direction.RIGHT, 2, 5, 11, 6, 11, 7, 11, 8, 11),
            arrow(4, Direction.LEFT, 3, 11, 2, 10, 2, 9, 2, 8, 2, 7, 2),
            arrow(5, Direction.RIGHT, 0, 6, 8, 7, 8),
            arrow(6, Direction.LEFT, 1, 5, 10, 4, 10, 3, 10),
            arrow(7, Direction.LEFT, 2, 10, 7, 10, 6, 10, 5, 9, 5, 8, 5, 7, 5),
            arrow(8, Direction.UP, 3, 9, 12, 10, 12, 10, 11, 10, 10),
            arrow(9, Direction.UP, 0, 2, 12, 1, 12, 1, 11, 1, 10),
            arrow(10, Direction.UP, 1, 1, 8, 1, 7, 1, 6)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(72, LevelData(
            levelNumber = 72,
            title = "City 4 • Route 12 • Pine Needle",
            gridWidth = 14,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 12, 6, 11, 6),
            arrow(2, Direction.LEFT, 1, 7, 7, 6, 7, 5, 7),
            arrow(3, Direction.RIGHT, 2, 1, 9, 1, 10, 1, 11, 2, 11, 3, 11),
            arrow(4, Direction.UP, 3, 12, 9, 11, 9, 11, 8, 11, 7),
            arrow(5, Direction.DOWN, 0, 8, 8, 9, 8, 10, 8, 10, 9, 10, 10),
            arrow(6, Direction.DOWN, 1, 3, 1, 3, 2, 3, 3, 3, 4),
            arrow(7, Direction.LEFT, 2, 9, 9, 8, 9),
            arrow(8, Direction.LEFT, 3, 10, 6, 9, 6, 8, 6),
            arrow(9, Direction.LEFT, 0, 12, 3, 11, 3),
            arrow(10, Direction.DOWN, 1, 7, 10, 7, 11, 7, 12, 7, 13),
            arrow(11, Direction.RIGHT, 2, 1, 4, 2, 4)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(73, LevelData(
            levelNumber = 73,
            title = "City 4 • Route 13 • Woodland Trail",
            gridWidth = 14,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 9, 4, 10, 4),
            arrow(2, Direction.LEFT, 1, 11, 10, 11, 11, 10, 11, 9, 11),
            arrow(3, Direction.LEFT, 2, 11, 6, 11, 7, 11, 8, 10, 8, 9, 8),
            arrow(4, Direction.LEFT, 3, 8, 2, 7, 2),
            arrow(5, Direction.DOWN, 0, 12, 3, 11, 3, 11, 4, 11, 5),
            arrow(6, Direction.RIGHT, 1, 2, 5, 2, 4, 2, 3, 3, 3, 4, 3, 5, 3),
            arrow(7, Direction.DOWN, 2, 2, 8, 3, 8, 3, 9, 3, 10),
            arrow(8, Direction.RIGHT, 3, 2, 10, 2, 11, 2, 12, 3, 12, 4, 12),
            arrow(9, Direction.DOWN, 0, 5, 8, 6, 8, 7, 8, 7, 9, 7, 10, 7, 11),
            arrow(10, Direction.UP, 1, 3, 2, 3, 1, 3, 0),
            arrow(11, Direction.LEFT, 2, 12, 1, 11, 1)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(74, LevelData(
            levelNumber = 74,
            title = "City 4 • Route 14 • Deep Thicket",
            gridWidth = 12,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 9, 1, 9, 2, 9, 3, 8, 3, 7, 3),
            arrow(2, Direction.UP, 1, 8, 11, 8, 10, 8, 9, 8, 8),
            arrow(3, Direction.UP, 2, 9, 9, 9, 8, 9, 7),
            arrow(4, Direction.UP, 3, 1, 12, 1, 11, 1, 10, 1, 9, 1, 8),
            arrow(5, Direction.RIGHT, 0, 1, 5, 2, 5, 3, 5, 4, 5, 5, 5),
            arrow(6, Direction.LEFT, 1, 4, 3, 3, 3, 2, 3, 1, 3, 0, 3),
            arrow(7, Direction.RIGHT, 2, 4, 12, 5, 12, 6, 12),
            arrow(8, Direction.DOWN, 3, 4, 7, 4, 8),
            arrow(9, Direction.RIGHT, 0, 3, 11, 4, 11, 5, 11, 6, 11, 7, 11)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(75, LevelData(
            levelNumber = 75,
            title = "City 4 • Route 15 • Soaring Falcon",
            gridWidth = 14,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 4, 1, 4, 2, 4, 3, 3, 3, 2, 3),
            arrow(2, Direction.RIGHT, 1, 2, 5, 3, 5, 4, 5),
            arrow(3, Direction.RIGHT, 2, 2, 7, 3, 7, 4, 7, 5, 7),
            arrow(4, Direction.UP, 3, 5, 2, 5, 1),
            arrow(5, Direction.UP, 0, 11, 10, 10, 10, 9, 10, 9, 9, 9, 8, 9, 7),
            arrow(6, Direction.LEFT, 1, 9, 3, 8, 3),
            arrow(7, Direction.DOWN, 2, 2, 8, 1, 8, 1, 9, 1, 10),
            arrow(8, Direction.UP, 3, 10, 3, 10, 2, 10, 1),
            arrow(9, Direction.LEFT, 0, 5, 10, 5, 11, 5, 12, 4, 12, 3, 12, 2, 12),
            arrow(10, Direction.RIGHT, 1, 8, 6, 9, 6, 10, 6),
            arrow(11, Direction.DOWN, 2, 10, 4, 10, 5),
            arrow(12, Direction.LEFT, 3, 3, 10, 2, 10),
            arrow(13, Direction.LEFT, 0, 7, 9, 6, 9, 5, 9)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(76, LevelData(
            levelNumber = 76,
            title = "City 4 • Route 16 • River Crossing",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 5, 10, 5, 11),
            arrow(2, Direction.RIGHT, 1, 2, 12, 2, 11, 2, 10, 3, 10, 4, 10),
            arrow(3, Direction.DOWN, 2, 11, 8, 11, 9, 11, 10, 11, 11),
            arrow(4, Direction.DOWN, 3, 7, 7, 7, 8, 7, 9, 7, 10, 7, 11),
            arrow(5, Direction.UP, 0, 8, 7, 8, 6, 8, 5, 8, 4),
            arrow(6, Direction.UP, 1, 9, 5, 9, 4, 9, 3),
            arrow(7, Direction.RIGHT, 2, 5, 3, 6, 3),
            arrow(8, Direction.RIGHT, 3, 5, 12, 6, 12),
            arrow(9, Direction.DOWN, 0, 9, 6, 9, 7),
            arrow(10, Direction.UP, 1, 11, 12, 12, 12, 12, 11, 12, 10),
            arrow(11, Direction.RIGHT, 2, 10, 4, 10, 3, 11, 3, 12, 3)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(77, LevelData(
            levelNumber = 77,
            title = "City 4 • Route 17 • Elder Tree",
            gridWidth = 14,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 5, 5, 4, 5),
            arrow(2, Direction.LEFT, 1, 11, 5, 10, 5, 9, 5, 8, 5, 7, 5),
            arrow(3, Direction.LEFT, 2, 5, 3, 5, 2, 5, 1, 4, 1, 3, 1),
            arrow(4, Direction.RIGHT, 3, 1, 9, 2, 9, 3, 9, 4, 9),
            arrow(5, Direction.UP, 0, 2, 6, 2, 5, 2, 4),
            arrow(6, Direction.LEFT, 1, 10, 8, 9, 8, 8, 8, 7, 8, 6, 8),
            arrow(7, Direction.RIGHT, 2, 2, 12, 3, 12),
            arrow(8, Direction.UP, 3, 12, 8, 12, 7, 12, 6, 12, 5, 12, 4),
            arrow(9, Direction.RIGHT, 0, 5, 8, 5, 7, 5, 6, 6, 6, 7, 6, 8, 6),
            arrow(10, Direction.LEFT, 1, 3, 3, 2, 3, 1, 3, 0, 3),
            arrow(11, Direction.UP, 2, 7, 12, 6, 12, 5, 12, 5, 11, 5, 10, 5, 9)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(78, LevelData(
            levelNumber = 78,
            title = "City 4 • Route 18 • Emerald Grove",
            gridWidth = 14,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 1, 11, 1, 10),
            arrow(2, Direction.UP, 1, 12, 11, 11, 11, 11, 10, 11, 9),
            arrow(3, Direction.RIGHT, 2, 12, 4, 13, 4),
            arrow(4, Direction.DOWN, 3, 2, 1, 2, 2, 2, 3),
            arrow(5, Direction.DOWN, 0, 5, 2, 6, 2, 6, 3, 6, 4),
            arrow(6, Direction.RIGHT, 1, 10, 8, 10, 7, 10, 6, 11, 6, 12, 6),
            arrow(7, Direction.RIGHT, 2, 4, 5, 4, 6, 4, 7, 5, 7, 6, 7),
            arrow(8, Direction.DOWN, 3, 8, 5, 8, 6),
            arrow(9, Direction.LEFT, 0, 11, 2, 10, 2)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(79, LevelData(
            levelNumber = 79,
            title = "City 4 • Route 19 • Verdant Spire",
            gridWidth = 14,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 7, 11, 7, 12, 7, 13),
            arrow(2, Direction.LEFT, 1, 7, 7, 7, 8, 6, 8, 5, 8),
            arrow(3, Direction.LEFT, 2, 5, 3, 5, 4, 5, 5, 4, 5, 3, 5, 2, 5),
            arrow(4, Direction.DOWN, 3, 10, 6, 10, 7, 10, 8, 10, 9),
            arrow(5, Direction.UP, 0, 2, 4, 1, 4, 0, 4, 0, 3, 0, 2, 0, 1),
            arrow(6, Direction.LEFT, 1, 11, 4, 11, 3, 11, 2, 10, 2, 9, 2, 8, 2),
            arrow(7, Direction.UP, 2, 5, 12, 5, 11),
            arrow(8, Direction.RIGHT, 3, 2, 6, 3, 6, 4, 6, 5, 6),
            arrow(9, Direction.RIGHT, 0, 1, 11, 2, 11),
            arrow(10, Direction.LEFT, 1, 6, 9, 5, 9),
            arrow(11, Direction.DOWN, 2, 6, 5, 6, 6)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(80, LevelData(
            levelNumber = 80,
            title = "City 4 • Route 20 • Warm Coffee",
            gridWidth = 14,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 9, 2, 10, 2, 11, 2, 12, 2),
            arrow(2, Direction.UP, 1, 12, 7, 12, 6, 12, 5, 12, 4, 12, 3),
            arrow(3, Direction.UP, 2, 10, 4, 10, 3),
            arrow(4, Direction.UP, 3, 11, 10, 11, 9),
            arrow(5, Direction.UP, 0, 2, 10, 1, 10, 1, 9, 1, 8),
            arrow(6, Direction.DOWN, 1, 5, 3, 6, 3, 7, 3, 7, 4, 7, 5, 7, 6),
            arrow(7, Direction.RIGHT, 2, 5, 6, 6, 6),
            arrow(8, Direction.DOWN, 3, 7, 1, 7, 2),
            arrow(9, Direction.RIGHT, 0, 6, 7, 6, 8, 6, 9, 7, 9, 8, 9, 9, 9),
            arrow(10, Direction.DOWN, 1, 8, 5, 8, 6)
            ),
            maxDrops = 3,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(81, LevelData(
            levelNumber = 81,
            title = "City 5 • Route 1 • Spark",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 5, 7, 5, 6, 5, 5, 4, 5, 3, 5, 2, 5),
            arrow(2, Direction.DOWN, 1, 9, 1, 10, 1, 11, 1, 11, 2, 11, 3),
            arrow(3, Direction.UP, 2, 6, 3, 6, 2),
            arrow(4, Direction.LEFT, 3, 8, 12, 8, 11, 8, 10, 7, 10, 6, 10, 5, 10),
            arrow(5, Direction.LEFT, 0, 10, 6, 9, 6),
            arrow(6, Direction.RIGHT, 1, 1, 11, 1, 12, 1, 13, 2, 13, 3, 13),
            arrow(7, Direction.DOWN, 2, 8, 1, 8, 2),
            arrow(8, Direction.LEFT, 3, 7, 7, 7, 8, 7, 9, 6, 9, 5, 9),
            arrow(9, Direction.LEFT, 0, 11, 11, 10, 11),
            arrow(10, Direction.LEFT, 1, 2, 11, 2, 10, 2, 9, 1, 9, 0, 9),
            arrow(11, Direction.RIGHT, 2, 9, 11, 9, 12, 9, 13, 10, 13, 11, 13, 12, 13),
            arrow(12, Direction.RIGHT, 3, 7, 5, 7, 4, 7, 3, 8, 3, 9, 3)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(82, LevelData(
            levelNumber = 82,
            title = "City 5 • Route 2 • Ash Field",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 5, 9, 5, 8, 5, 7),
            arrow(2, Direction.LEFT, 1, 8, 1, 7, 1),
            arrow(3, Direction.LEFT, 2, 7, 10, 6, 10, 5, 10),
            arrow(4, Direction.LEFT, 3, 8, 10, 8, 9, 7, 9, 6, 9),
            arrow(5, Direction.UP, 0, 2, 10, 2, 9, 2, 8, 2, 7, 2, 6),
            arrow(6, Direction.RIGHT, 1, 3, 1, 3, 2, 4, 2, 5, 2),
            arrow(7, Direction.LEFT, 2, 6, 7, 6, 6, 5, 6, 4, 6),
            arrow(8, Direction.UP, 3, 8, 5, 7, 5, 7, 4, 7, 3),
            arrow(9, Direction.RIGHT, 0, 10, 5, 11, 5),
            arrow(10, Direction.RIGHT, 1, 5, 4, 6, 4),
            arrow(11, Direction.DOWN, 2, 1, 12, 1, 13),
            arrow(12, Direction.RIGHT, 3, 3, 11, 4, 11)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(83, LevelData(
            levelNumber = 83,
            title = "City 5 • Route 3 • Cinder Trail",
            gridWidth = 14,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 4, 10, 4, 9, 4, 8),
            arrow(2, Direction.UP, 1, 10, 5, 10, 4, 10, 3, 10, 2, 10, 1),
            arrow(3, Direction.UP, 2, 5, 4, 4, 4, 3, 4, 3, 3, 3, 2),
            arrow(4, Direction.LEFT, 3, 5, 5, 4, 5, 3, 5, 2, 5, 1, 5),
            arrow(5, Direction.DOWN, 0, 9, 3, 9, 4),
            arrow(6, Direction.RIGHT, 1, 5, 3, 6, 3, 7, 3),
            arrow(7, Direction.UP, 2, 6, 8, 6, 7),
            arrow(8, Direction.LEFT, 3, 6, 1, 5, 1, 4, 1, 3, 1, 2, 1),
            arrow(9, Direction.RIGHT, 0, 12, 7, 13, 7),
            arrow(10, Direction.RIGHT, 1, 1, 9, 2, 9, 3, 9),
            arrow(11, Direction.RIGHT, 2, 7, 8, 8, 8, 9, 8, 10, 8, 11, 8),
            arrow(12, Direction.DOWN, 3, 2, 3, 2, 4),
            arrow(13, Direction.LEFT, 0, 3, 6, 2, 6),
            arrow(14, Direction.RIGHT, 1, 5, 9, 6, 9, 7, 9, 8, 9, 9, 9),
            arrow(15, Direction.UP, 2, 1, 2, 1, 1, 1, 0)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(84, LevelData(
            levelNumber = 84,
            title = "City 5 • Route 4 • Smoldering Way",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 10, 11, 10, 10),
            arrow(2, Direction.DOWN, 1, 4, 7, 4, 8, 4, 9),
            arrow(3, Direction.LEFT, 2, 9, 6, 9, 7, 9, 8, 8, 8, 7, 8, 6, 8),
            arrow(4, Direction.RIGHT, 3, 1, 10, 1, 9, 2, 9, 3, 9),
            arrow(5, Direction.DOWN, 0, 3, 12, 3, 13),
            arrow(6, Direction.LEFT, 1, 7, 11, 6, 11, 5, 11, 4, 11, 3, 11),
            arrow(7, Direction.DOWN, 2, 1, 6, 1, 7, 1, 8),
            arrow(8, Direction.RIGHT, 3, 2, 4, 3, 4),
            arrow(9, Direction.UP, 0, 6, 3, 6, 2),
            arrow(10, Direction.DOWN, 1, 2, 10, 2, 11),
            arrow(11, Direction.LEFT, 2, 9, 5, 8, 5, 7, 5)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(85, LevelData(
            levelNumber = 85,
            title = "City 5 • Route 5 • Ember Trail",
            gridWidth = 14,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 9, 10, 9, 11, 8, 11, 7, 11),
            arrow(2, Direction.LEFT, 1, 1, 8, 0, 8),
            arrow(3, Direction.DOWN, 2, 7, 2, 7, 3),
            arrow(4, Direction.DOWN, 3, 6, 4, 5, 4, 4, 4, 4, 5, 4, 6),
            arrow(5, Direction.RIGHT, 0, 2, 7, 3, 7, 4, 7),
            arrow(6, Direction.RIGHT, 1, 10, 10, 11, 10, 12, 10, 13, 10),
            arrow(7, Direction.LEFT, 2, 12, 1, 11, 1),
            arrow(8, Direction.LEFT, 3, 6, 10, 5, 10, 4, 10, 3, 10, 2, 10),
            arrow(9, Direction.DOWN, 0, 2, 9, 1, 9, 0, 9, 0, 10, 0, 11),
            arrow(10, Direction.LEFT, 1, 11, 6, 11, 7, 11, 8, 10, 8, 9, 8),
            arrow(11, Direction.LEFT, 2, 8, 5, 7, 5, 6, 5)
            ),
            maxDrops = 3,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(86, LevelData(
            levelNumber = 86,
            title = "City 5 • Route 6 • Basalt Path",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 11, 3, 11, 2, 10, 2, 9, 2),
            arrow(2, Direction.LEFT, 1, 6, 8, 5, 8, 4, 8, 3, 8),
            arrow(3, Direction.DOWN, 2, 2, 9, 2, 10, 2, 11, 2, 12),
            arrow(4, Direction.RIGHT, 3, 4, 10, 5, 10, 6, 10, 7, 10),
            arrow(5, Direction.LEFT, 0, 6, 4, 5, 4, 4, 4),
            arrow(6, Direction.LEFT, 1, 11, 7, 10, 7),
            arrow(7, Direction.LEFT, 2, 10, 9, 10, 10, 10, 11, 9, 11, 8, 11, 7, 11),
            arrow(8, Direction.LEFT, 3, 6, 2, 5, 2, 4, 2, 3, 2),
            arrow(9, Direction.RIGHT, 0, 8, 6, 9, 6),
            arrow(10, Direction.DOWN, 1, 10, 4, 11, 4, 11, 5, 11, 6),
            arrow(11, Direction.LEFT, 2, 7, 7, 6, 7, 5, 7),
            arrow(12, Direction.DOWN, 3, 1, 8, 1, 9, 1, 10, 1, 11, 1, 12),
            arrow(13, Direction.UP, 0, 9, 10, 9, 9),
            arrow(14, Direction.UP, 1, 7, 5, 8, 5, 8, 4, 8, 3),
            arrow(15, Direction.DOWN, 2, 3, 9, 3, 10)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(87, LevelData(
            levelNumber = 87,
            title = "City 5 • Route 7 • Lava Stream",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 3, 3, 4, 3),
            arrow(2, Direction.LEFT, 1, 5, 7, 4, 7, 3, 7, 2, 7, 1, 7),
            arrow(3, Direction.LEFT, 2, 10, 7, 9, 7, 8, 7),
            arrow(4, Direction.RIGHT, 3, 4, 1, 5, 1, 6, 1, 7, 1),
            arrow(5, Direction.DOWN, 0, 2, 4, 1, 4, 0, 4, 0, 5, 0, 6, 0, 7),
            arrow(6, Direction.UP, 1, 5, 10, 6, 10, 7, 10, 7, 9, 7, 8),
            arrow(7, Direction.RIGHT, 2, 2, 8, 3, 8, 4, 8, 5, 8),
            arrow(8, Direction.UP, 3, 5, 6, 5, 5),
            arrow(9, Direction.UP, 0, 10, 6, 11, 6, 11, 5, 11, 4),
            arrow(10, Direction.LEFT, 1, 8, 5, 7, 5),
            arrow(11, Direction.UP, 2, 9, 1, 9, 0),
            arrow(12, Direction.LEFT, 3, 3, 4, 3, 5, 2, 5, 1, 5)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(88, LevelData(
            levelNumber = 88,
            title = "City 5 • Route 8 • Furnace Run",
            gridWidth = 12,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 7, 9, 6, 9, 6, 8, 6, 7),
            arrow(2, Direction.LEFT, 1, 10, 1, 10, 2, 10, 3, 9, 3, 8, 3, 7, 3),
            arrow(3, Direction.UP, 2, 5, 5, 5, 4, 5, 3, 5, 2),
            arrow(4, Direction.RIGHT, 3, 8, 4, 9, 4, 10, 4),
            arrow(5, Direction.RIGHT, 0, 5, 10, 6, 10),
            arrow(6, Direction.RIGHT, 1, 7, 5, 8, 5, 9, 5),
            arrow(7, Direction.RIGHT, 2, 2, 2, 2, 1, 2, 0, 3, 0, 4, 0),
            arrow(8, Direction.RIGHT, 3, 1, 5, 2, 5, 3, 5),
            arrow(9, Direction.DOWN, 0, 8, 7, 8, 8),
            arrow(10, Direction.UP, 1, 4, 6, 4, 5),
            arrow(11, Direction.UP, 2, 9, 10, 10, 10, 10, 9, 10, 8),
            arrow(12, Direction.DOWN, 3, 1, 1, 1, 2, 1, 3),
            arrow(13, Direction.DOWN, 0, 2, 8, 2, 9, 2, 10, 2, 11, 2, 12),
            arrow(14, Direction.LEFT, 1, 9, 1, 8, 1, 7, 1, 6, 1),
            arrow(15, Direction.DOWN, 2, 3, 6, 3, 7)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(89, LevelData(
            levelNumber = 89,
            title = "City 5 • Route 9 • Sulfur Vent",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 10, 11, 10, 10, 10, 9, 10, 8),
            arrow(2, Direction.DOWN, 1, 7, 8, 7, 9),
            arrow(3, Direction.DOWN, 2, 2, 3, 2, 4),
            arrow(4, Direction.UP, 3, 7, 4, 7, 3, 7, 2, 7, 1),
            arrow(5, Direction.DOWN, 0, 6, 6, 6, 7),
            arrow(6, Direction.RIGHT, 1, 3, 7, 3, 8, 4, 8, 5, 8),
            arrow(7, Direction.UP, 2, 1, 8, 1, 7, 1, 6, 1, 5, 1, 4),
            arrow(8, Direction.LEFT, 3, 8, 11, 7, 11, 6, 11, 5, 11),
            arrow(9, Direction.DOWN, 0, 5, 5, 5, 6),
            arrow(10, Direction.LEFT, 1, 2, 2, 2, 1, 2, 0, 1, 0, 0, 0),
            arrow(11, Direction.LEFT, 2, 10, 4, 9, 4),
            arrow(12, Direction.LEFT, 3, 4, 11, 4, 12, 3, 12, 2, 12)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(90, LevelData(
            levelNumber = 90,
            title = "City 5 • Route 10 • Origami Swan",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 7, 12, 8, 12, 9, 12, 9, 11, 9, 10),
            arrow(2, Direction.LEFT, 1, 6, 3, 6, 4, 6, 5, 5, 5, 4, 5),
            arrow(3, Direction.LEFT, 2, 10, 4, 9, 4, 8, 4, 7, 4),
            arrow(4, Direction.UP, 3, 3, 6, 3, 5, 3, 4),
            arrow(5, Direction.UP, 0, 9, 9, 9, 8),
            arrow(6, Direction.LEFT, 1, 11, 12, 10, 12),
            arrow(7, Direction.LEFT, 2, 2, 6, 1, 6),
            arrow(8, Direction.RIGHT, 3, 5, 7, 5, 8, 6, 8, 7, 8),
            arrow(9, Direction.LEFT, 0, 3, 1, 3, 0, 2, 0, 1, 0),
            arrow(10, Direction.LEFT, 1, 6, 1, 5, 1),
            arrow(11, Direction.LEFT, 2, 5, 3, 4, 3, 3, 3, 2, 3, 1, 3),
            arrow(12, Direction.RIGHT, 3, 6, 10, 7, 10),
            arrow(13, Direction.RIGHT, 0, 8, 9, 8, 8, 8, 7, 9, 7, 10, 7),
            arrow(14, Direction.DOWN, 1, 4, 7, 4, 8, 4, 9, 4, 10)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(91, LevelData(
            levelNumber = 91,
            title = "City 5 • Route 11 • Pyroclast",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 11, 2, 10, 2, 9, 2),
            arrow(2, Direction.DOWN, 1, 3, 9, 3, 10),
            arrow(3, Direction.DOWN, 2, 7, 5, 7, 6, 7, 7),
            arrow(4, Direction.RIGHT, 3, 1, 5, 2, 5, 3, 5, 4, 5),
            arrow(5, Direction.DOWN, 0, 7, 2, 7, 3),
            arrow(6, Direction.UP, 1, 8, 9, 8, 8, 8, 7, 8, 6),
            arrow(7, Direction.UP, 2, 9, 5, 9, 4),
            arrow(8, Direction.UP, 3, 4, 6, 5, 6, 6, 6, 6, 5, 6, 4, 6, 3),
            arrow(9, Direction.RIGHT, 0, 5, 9, 5, 10, 6, 10, 7, 10),
            arrow(10, Direction.RIGHT, 1, 8, 3, 9, 3),
            arrow(11, Direction.LEFT, 2, 4, 1, 4, 2, 4, 3, 3, 3, 2, 3),
            arrow(12, Direction.UP, 3, 11, 1, 11, 0)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(92, LevelData(
            levelNumber = 92,
            title = "City 5 • Route 12 • Igneous Maze",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 2, 5, 1, 5, 0, 5, 0, 6, 0, 7),
            arrow(2, Direction.LEFT, 1, 10, 8, 10, 9, 10, 10, 9, 10, 8, 10, 7, 10),
            arrow(3, Direction.RIGHT, 2, 5, 12, 6, 12, 7, 12),
            arrow(4, Direction.UP, 3, 10, 5, 10, 4, 10, 3, 10, 2, 10, 1),
            arrow(5, Direction.DOWN, 0, 8, 5, 8, 6),
            arrow(6, Direction.DOWN, 1, 2, 9, 2, 10),
            arrow(7, Direction.LEFT, 2, 4, 6, 4, 7, 4, 8, 3, 8, 2, 8, 1, 8),
            arrow(8, Direction.DOWN, 3, 1, 1, 1, 2, 1, 3),
            arrow(9, Direction.RIGHT, 0, 2, 4, 3, 4),
            arrow(10, Direction.UP, 1, 4, 5, 4, 4),
            arrow(11, Direction.UP, 2, 3, 12, 3, 11, 3, 10, 3, 9),
            arrow(12, Direction.RIGHT, 3, 1, 7, 2, 7),
            arrow(13, Direction.RIGHT, 0, 3, 2, 3, 1, 3, 0, 4, 0, 5, 0)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(93, LevelData(
            levelNumber = 93,
            title = "City 5 • Route 13 • Obsidian Spire",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 2, 1, 2, 2, 2, 3, 1, 3, 0, 3),
            arrow(2, Direction.LEFT, 1, 7, 1, 6, 1),
            arrow(3, Direction.UP, 2, 5, 10, 5, 9),
            arrow(4, Direction.DOWN, 3, 7, 3, 7, 4, 7, 5, 7, 6),
            arrow(5, Direction.LEFT, 0, 10, 9, 9, 9, 8, 9, 7, 9),
            arrow(6, Direction.UP, 1, 2, 9, 2, 8, 2, 7, 2, 6),
            arrow(7, Direction.LEFT, 2, 7, 8, 6, 8, 5, 8),
            arrow(8, Direction.DOWN, 3, 1, 4, 1, 5, 1, 6, 1, 7),
            arrow(9, Direction.DOWN, 0, 9, 2, 9, 3, 9, 4, 9, 5, 9, 6),
            arrow(10, Direction.UP, 1, 5, 4, 4, 4, 3, 4, 3, 3, 3, 2),
            arrow(11, Direction.DOWN, 2, 10, 10, 10, 11, 10, 12),
            arrow(12, Direction.LEFT, 3, 5, 2, 5, 1, 5, 0, 4, 0, 3, 0, 2, 0),
            arrow(13, Direction.RIGHT, 0, 1, 11, 2, 11)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(94, LevelData(
            levelNumber = 94,
            title = "City 5 • Route 14 • Magma Tunnel",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 4, 9, 3, 9, 3, 10, 3, 11),
            arrow(2, Direction.DOWN, 1, 6, 2, 6, 3, 6, 4),
            arrow(3, Direction.RIGHT, 2, 2, 4, 3, 4),
            arrow(4, Direction.DOWN, 3, 4, 4, 4, 5, 4, 6, 4, 7, 4, 8),
            arrow(5, Direction.LEFT, 0, 9, 10, 8, 10, 7, 10, 6, 10),
            arrow(6, Direction.DOWN, 1, 1, 2, 1, 3, 1, 4, 1, 5),
            arrow(7, Direction.LEFT, 2, 5, 2, 4, 2, 3, 2),
            arrow(8, Direction.RIGHT, 3, 9, 4, 9, 3, 9, 2, 10, 2, 11, 2, 12, 2),
            arrow(9, Direction.LEFT, 0, 2, 10, 1, 10),
            arrow(10, Direction.DOWN, 1, 6, 7, 5, 7, 5, 8, 5, 9),
            arrow(11, Direction.UP, 2, 2, 9, 2, 8, 2, 7)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(95, LevelData(
            levelNumber = 95,
            title = "City 5 • Route 15 • Volcanic Core",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5),
            arrow(2, Direction.UP, 1, 8, 7, 8, 6),
            arrow(3, Direction.RIGHT, 2, 8, 9, 9, 9, 10, 9, 11, 9),
            arrow(4, Direction.UP, 3, 2, 7, 2, 6),
            arrow(5, Direction.LEFT, 0, 6, 3, 5, 3, 4, 3, 3, 3, 2, 3),
            arrow(6, Direction.LEFT, 1, 5, 5, 4, 5),
            arrow(7, Direction.RIGHT, 2, 4, 8, 4, 7, 4, 6, 5, 6, 6, 6),
            arrow(8, Direction.LEFT, 3, 9, 5, 8, 5),
            arrow(9, Direction.RIGHT, 0, 3, 1, 4, 1, 5, 1),
            arrow(10, Direction.RIGHT, 1, 9, 3, 10, 3, 11, 3),
            arrow(11, Direction.RIGHT, 2, 1, 10, 2, 10, 3, 10, 4, 10, 5, 10),
            arrow(12, Direction.UP, 3, 10, 7, 11, 7, 12, 7, 12, 6, 12, 5, 12, 4),
            arrow(13, Direction.RIGHT, 0, 8, 2, 9, 2)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(96, LevelData(
            levelNumber = 96,
            title = "City 5 • Route 16 • Heat Chamber",
            gridWidth = 11,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 6, 8, 6, 9, 6, 10),
            arrow(2, Direction.LEFT, 1, 6, 6, 5, 6),
            arrow(3, Direction.UP, 2, 3, 2, 2, 2, 2, 1, 2, 0),
            arrow(4, Direction.RIGHT, 3, 6, 1, 7, 1, 8, 1, 9, 1, 10, 1),
            arrow(5, Direction.UP, 0, 8, 7, 9, 7, 9, 6, 9, 5),
            arrow(6, Direction.DOWN, 1, 1, 7, 1, 8, 1, 9, 1, 10),
            arrow(7, Direction.DOWN, 2, 3, 9, 3, 10),
            arrow(8, Direction.DOWN, 3, 2, 4, 2, 5, 2, 6, 2, 7, 2, 8),
            arrow(9, Direction.UP, 0, 9, 3, 9, 2),
            arrow(10, Direction.RIGHT, 1, 3, 5, 4, 5, 5, 5, 6, 5, 7, 5),
            arrow(11, Direction.UP, 2, 3, 8, 3, 7),
            arrow(12, Direction.UP, 3, 4, 8, 4, 7, 4, 6),
            arrow(13, Direction.DOWN, 0, 5, 7, 5, 8)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(97, LevelData(
            levelNumber = 97,
            title = "City 5 • Route 17 • Rift Valley",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 8, 7, 7, 7),
            arrow(2, Direction.UP, 1, 2, 4, 2, 3, 2, 2),
            arrow(3, Direction.DOWN, 2, 10, 6, 10, 7, 10, 8),
            arrow(4, Direction.RIGHT, 3, 6, 8, 7, 8),
            arrow(5, Direction.UP, 0, 8, 9, 8, 8),
            arrow(6, Direction.LEFT, 1, 6, 5, 6, 4, 6, 3, 5, 3, 4, 3, 3, 3),
            arrow(7, Direction.RIGHT, 2, 7, 10, 8, 10),
            arrow(8, Direction.RIGHT, 3, 8, 4, 9, 4),
            arrow(9, Direction.UP, 0, 6, 7, 6, 6),
            arrow(10, Direction.UP, 1, 5, 9, 5, 8, 5, 7, 5, 6, 5, 5),
            arrow(11, Direction.RIGHT, 2, 10, 5, 11, 5)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(98, LevelData(
            levelNumber = 98,
            title = "City 5 • Route 18 • Crater Edge",
            gridWidth = 12,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 6, 2, 5, 2),
            arrow(2, Direction.UP, 1, 7, 8, 7, 7),
            arrow(3, Direction.LEFT, 2, 5, 11, 4, 11, 3, 11),
            arrow(4, Direction.RIGHT, 3, 7, 4, 8, 4),
            arrow(5, Direction.UP, 0, 1, 6, 2, 6, 3, 6, 3, 5, 3, 4),
            arrow(6, Direction.DOWN, 1, 5, 5, 6, 5, 6, 6, 6, 7),
            arrow(7, Direction.LEFT, 2, 6, 4, 5, 4),
            arrow(8, Direction.DOWN, 3, 2, 1, 2, 2),
            arrow(9, Direction.LEFT, 0, 1, 9, 0, 9),
            arrow(10, Direction.RIGHT, 1, 9, 12, 9, 13, 10, 13, 11, 13),
            arrow(11, Direction.RIGHT, 2, 2, 10, 3, 10),
            arrow(12, Direction.UP, 3, 8, 2, 8, 1, 8, 0),
            arrow(13, Direction.LEFT, 0, 8, 9, 7, 9, 6, 9, 5, 9, 4, 9),
            arrow(14, Direction.RIGHT, 1, 1, 7, 2, 7)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(99, LevelData(
            levelNumber = 99,
            title = "City 5 • Route 19 • Blazing Spiral",
            gridWidth = 11,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 1, 5, 2, 5, 3, 5, 4, 5, 5, 5),
            arrow(2, Direction.LEFT, 1, 9, 1, 8, 1),
            arrow(3, Direction.RIGHT, 2, 6, 6, 7, 6, 8, 6),
            arrow(4, Direction.LEFT, 3, 6, 2, 5, 2, 4, 2, 3, 2, 2, 2),
            arrow(5, Direction.DOWN, 0, 3, 1, 2, 1, 1, 1, 1, 2, 1, 3, 1, 4),
            arrow(6, Direction.RIGHT, 1, 2, 7, 3, 7, 4, 7, 5, 7, 6, 7),
            arrow(7, Direction.LEFT, 2, 6, 3, 5, 3, 4, 3, 3, 3),
            arrow(8, Direction.UP, 3, 9, 9, 9, 8),
            arrow(9, Direction.DOWN, 0, 1, 7, 1, 8),
            arrow(10, Direction.RIGHT, 1, 7, 8, 8, 8),
            arrow(11, Direction.RIGHT, 2, 6, 4, 7, 4, 8, 4)
            ),
            maxDrops = 3,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(100, LevelData(
            levelNumber = 100,
            title = "City 5 • Route 20 • The Volcano",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 8, 6, 7, 6, 6, 6, 5, 6),
            arrow(2, Direction.UP, 1, 11, 5, 10, 5, 9, 5, 9, 4, 9, 3),
            arrow(3, Direction.DOWN, 2, 2, 6, 2, 7, 2, 8, 2, 9, 2, 10),
            arrow(4, Direction.LEFT, 3, 7, 9, 6, 9, 5, 9, 4, 9, 3, 9),
            arrow(5, Direction.UP, 0, 5, 3, 5, 2, 5, 1),
            arrow(6, Direction.LEFT, 1, 4, 3, 3, 3, 2, 3, 1, 3, 0, 3),
            arrow(7, Direction.DOWN, 2, 6, 7, 6, 8),
            arrow(8, Direction.RIGHT, 3, 8, 1, 9, 1, 10, 1),
            arrow(9, Direction.DOWN, 0, 9, 8, 10, 8, 11, 8, 11, 9, 11, 10, 11, 11),
            arrow(10, Direction.UP, 1, 11, 3, 11, 2),
            arrow(11, Direction.DOWN, 2, 7, 3, 7, 4),
            arrow(12, Direction.LEFT, 3, 11, 7, 10, 7, 9, 7, 8, 7, 7, 7),
            arrow(13, Direction.UP, 0, 8, 2, 7, 2, 6, 2, 6, 1, 6, 0)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

    }

    private fun loadChunk3(map: MutableMap<Int, LevelData>) {
        map.put(101, LevelData(
            levelNumber = 101,
            title = "City 6 • Route 1 • Updraft",
            gridWidth = 13,
            gridHeight = 15,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 10, 13, 9, 13, 8, 13, 8, 12, 8, 11, 8, 10),
            arrow(2, Direction.LEFT, 1, 9, 3, 8, 3),
            arrow(3, Direction.RIGHT, 2, 4, 1, 5, 1, 6, 1),
            arrow(4, Direction.RIGHT, 3, 8, 6, 9, 6, 10, 6, 11, 6),
            arrow(5, Direction.UP, 0, 6, 8, 6, 7, 6, 6, 6, 5),
            arrow(6, Direction.DOWN, 1, 5, 12, 5, 13),
            arrow(7, Direction.LEFT, 2, 6, 3, 5, 3),
            arrow(8, Direction.UP, 3, 10, 9, 10, 8, 10, 7),
            arrow(9, Direction.UP, 0, 7, 11, 7, 10, 7, 9, 7, 8),
            arrow(10, Direction.DOWN, 1, 4, 9, 3, 9, 3, 10, 3, 11),
            arrow(11, Direction.RIGHT, 2, 8, 8, 9, 8),
            arrow(12, Direction.DOWN, 3, 2, 5, 3, 5, 4, 5, 4, 6, 4, 7),
            arrow(13, Direction.UP, 0, 1, 9, 1, 8, 1, 7, 1, 6),
            arrow(14, Direction.LEFT, 1, 4, 2, 4, 3, 4, 4, 3, 4, 2, 4),
            arrow(15, Direction.RIGHT, 2, 10, 3, 11, 3),
            arrow(16, Direction.LEFT, 3, 10, 5, 9, 5)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(102, LevelData(
            levelNumber = 102,
            title = "City 6 • Route 2 • Breeze Crest",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 6, 9, 7, 9),
            arrow(2, Direction.RIGHT, 1, 4, 10, 5, 10),
            arrow(3, Direction.DOWN, 2, 2, 4, 3, 4, 4, 4, 4, 5, 4, 6),
            arrow(4, Direction.LEFT, 3, 4, 11, 3, 11, 2, 11, 1, 11),
            arrow(5, Direction.RIGHT, 0, 1, 10, 2, 10, 3, 10),
            arrow(6, Direction.LEFT, 1, 2, 1, 2, 2, 1, 2, 0, 2),
            arrow(7, Direction.UP, 2, 4, 3, 5, 3, 6, 3, 6, 2, 6, 1, 6, 0),
            arrow(8, Direction.LEFT, 3, 10, 10, 10, 9, 10, 8, 9, 8, 8, 8),
            arrow(9, Direction.UP, 0, 10, 4, 10, 3, 10, 2, 10, 1),
            arrow(10, Direction.DOWN, 1, 2, 5, 2, 6, 2, 7),
            arrow(11, Direction.UP, 2, 3, 3, 3, 2),
            arrow(12, Direction.DOWN, 3, 1, 4, 1, 5),
            arrow(13, Direction.RIGHT, 0, 5, 8, 5, 7, 6, 7, 7, 7),
            arrow(14, Direction.RIGHT, 1, 3, 1, 4, 1, 5, 1)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(103, LevelData(
            levelNumber = 103,
            title = "City 6 • Route 3 • Zephyr Lane",
            gridWidth = 14,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 10, 1, 9, 1, 8, 1, 8, 2, 8, 3),
            arrow(2, Direction.LEFT, 1, 7, 10, 7, 11, 7, 12, 6, 12, 5, 12),
            arrow(3, Direction.RIGHT, 2, 3, 10, 4, 10),
            arrow(4, Direction.LEFT, 3, 11, 6, 11, 5, 11, 4, 10, 4, 9, 4, 8, 4),
            arrow(5, Direction.LEFT, 0, 4, 7, 3, 7, 2, 7, 1, 7, 0, 7),
            arrow(6, Direction.RIGHT, 1, 8, 10, 8, 11, 8, 12, 9, 12, 10, 12),
            arrow(7, Direction.DOWN, 2, 3, 5, 4, 5, 5, 5, 5, 6, 5, 7),
            arrow(8, Direction.RIGHT, 3, 2, 3, 3, 3, 4, 3, 5, 3),
            arrow(9, Direction.DOWN, 0, 7, 3, 7, 4, 7, 5, 7, 6),
            arrow(10, Direction.LEFT, 1, 12, 2, 11, 2, 10, 2),
            arrow(11, Direction.UP, 2, 10, 10, 10, 9),
            arrow(12, Direction.RIGHT, 3, 11, 1, 11, 0, 12, 0, 13, 0),
            arrow(13, Direction.RIGHT, 0, 1, 11, 1, 10, 1, 9, 2, 9, 3, 9, 4, 9),
            arrow(14, Direction.DOWN, 1, 8, 7, 7, 7, 6, 7, 6, 8, 6, 9, 6, 10)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(104, LevelData(
            levelNumber = 104,
            title = "City 6 • Route 4 • Stratus Walk",
            gridWidth = 14,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 11, 6, 11, 5, 12, 5, 13, 5),
            arrow(2, Direction.LEFT, 1, 11, 1, 10, 1, 9, 1, 8, 1, 7, 1),
            arrow(3, Direction.LEFT, 2, 5, 4, 4, 4, 3, 4, 2, 4, 1, 4),
            arrow(4, Direction.RIGHT, 3, 4, 10, 5, 10),
            arrow(5, Direction.UP, 0, 12, 10, 12, 9, 12, 8, 12, 7, 12, 6),
            arrow(6, Direction.DOWN, 1, 1, 8, 2, 8, 3, 8, 3, 9, 3, 10),
            arrow(7, Direction.UP, 2, 10, 7, 10, 6),
            arrow(8, Direction.DOWN, 3, 1, 1, 1, 2, 1, 3),
            arrow(9, Direction.LEFT, 0, 7, 5, 7, 4, 7, 3, 6, 3, 5, 3),
            arrow(10, Direction.LEFT, 1, 12, 4, 11, 4, 10, 4, 9, 4),
            arrow(11, Direction.RIGHT, 2, 6, 11, 6, 10, 6, 9, 7, 9, 8, 9),
            arrow(12, Direction.RIGHT, 3, 12, 3, 13, 3),
            arrow(13, Direction.DOWN, 0, 9, 6, 9, 7, 9, 8, 9, 9)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(105, LevelData(
            levelNumber = 105,
            title = "City 6 • Route 5 • Aerith Lift",
            gridWidth = 15,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 6, 10, 6, 9, 6, 8, 7, 8, 8, 8),
            arrow(2, Direction.DOWN, 1, 13, 3, 12, 3, 11, 3, 11, 4, 11, 5, 11, 6),
            arrow(3, Direction.LEFT, 2, 4, 8, 3, 8, 2, 8, 1, 8),
            arrow(4, Direction.RIGHT, 3, 9, 3, 9, 2, 9, 1, 10, 1, 11, 1, 12, 1),
            arrow(5, Direction.RIGHT, 0, 9, 11, 10, 11),
            arrow(6, Direction.LEFT, 1, 11, 9, 10, 9),
            arrow(7, Direction.LEFT, 2, 13, 6, 12, 6),
            arrow(8, Direction.RIGHT, 3, 4, 2, 4, 3, 4, 4, 5, 4, 6, 4, 7, 4),
            arrow(9, Direction.LEFT, 0, 6, 2, 5, 2),
            arrow(10, Direction.UP, 1, 10, 8, 10, 7),
            arrow(11, Direction.LEFT, 2, 1, 3, 0, 3),
            arrow(12, Direction.UP, 3, 8, 2, 8, 1),
            arrow(13, Direction.UP, 0, 2, 11, 2, 10),
            arrow(14, Direction.LEFT, 1, 12, 2, 11, 2)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(106, LevelData(
            levelNumber = 106,
            title = "City 6 • Route 6 • Cumulus Path",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 1, 5, 1, 4),
            arrow(2, Direction.LEFT, 1, 8, 10, 8, 9, 7, 9, 6, 9),
            arrow(3, Direction.DOWN, 2, 9, 4, 9, 5, 9, 6, 9, 7, 9, 8),
            arrow(4, Direction.RIGHT, 3, 5, 5, 6, 5),
            arrow(5, Direction.LEFT, 0, 10, 11, 9, 11, 8, 11, 7, 11, 6, 11),
            arrow(6, Direction.UP, 1, 3, 12, 4, 12, 4, 11, 4, 10),
            arrow(7, Direction.DOWN, 2, 11, 2, 11, 3, 11, 4, 11, 5, 11, 6),
            arrow(8, Direction.DOWN, 3, 5, 12, 5, 13),
            arrow(9, Direction.DOWN, 0, 1, 2, 2, 2, 2, 3, 2, 4),
            arrow(10, Direction.LEFT, 1, 10, 12, 9, 12),
            arrow(11, Direction.RIGHT, 2, 8, 2, 9, 2),
            arrow(12, Direction.RIGHT, 3, 11, 9, 12, 9),
            arrow(13, Direction.RIGHT, 0, 4, 2, 5, 2)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(107, LevelData(
            levelNumber = 107,
            title = "City 6 • Route 7 • Vapor Trail",
            gridWidth = 14,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 8, 8, 8, 9),
            arrow(2, Direction.DOWN, 1, 3, 3, 3, 4, 3, 5),
            arrow(3, Direction.UP, 2, 10, 7, 11, 7, 12, 7, 12, 6, 12, 5, 12, 4),
            arrow(4, Direction.RIGHT, 3, 10, 6, 11, 6),
            arrow(5, Direction.RIGHT, 0, 2, 8, 3, 8, 4, 8),
            arrow(6, Direction.LEFT, 1, 7, 12, 7, 11, 7, 10, 6, 10, 5, 10),
            arrow(7, Direction.LEFT, 2, 9, 10, 8, 10),
            arrow(8, Direction.DOWN, 3, 4, 10, 4, 11),
            arrow(9, Direction.LEFT, 0, 12, 9, 12, 10, 12, 11, 11, 11, 10, 11),
            arrow(10, Direction.DOWN, 1, 5, 2, 5, 3, 5, 4, 5, 5),
            arrow(11, Direction.LEFT, 2, 12, 3, 11, 3, 10, 3, 9, 3, 8, 3),
            arrow(12, Direction.RIGHT, 3, 8, 12, 9, 12, 10, 12, 11, 12, 12, 12)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(108, LevelData(
            levelNumber = 108,
            title = "City 6 • Route 8 • Floating Isle",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 3, 8, 3, 7, 3, 6, 2, 6, 1, 6),
            arrow(2, Direction.LEFT, 1, 8, 11, 8, 10, 8, 9, 7, 9, 6, 9, 5, 9),
            arrow(3, Direction.DOWN, 2, 6, 4, 6, 5, 6, 6),
            arrow(4, Direction.DOWN, 3, 2, 1, 2, 2, 2, 3, 2, 4, 2, 5),
            arrow(5, Direction.UP, 0, 8, 6, 8, 5, 8, 4, 8, 3),
            arrow(6, Direction.LEFT, 1, 10, 12, 9, 12),
            arrow(7, Direction.RIGHT, 2, 9, 6, 10, 6, 11, 6, 12, 6),
            arrow(8, Direction.UP, 3, 7, 7, 7, 6),
            arrow(9, Direction.LEFT, 0, 2, 11, 1, 11),
            arrow(10, Direction.UP, 1, 4, 8, 4, 7),
            arrow(11, Direction.DOWN, 2, 11, 8, 11, 9),
            arrow(12, Direction.LEFT, 3, 11, 2, 10, 2, 9, 2, 8, 2),
            arrow(13, Direction.RIGHT, 0, 7, 2, 7, 1, 7, 0, 8, 0, 9, 0, 10, 0),
            arrow(14, Direction.LEFT, 1, 11, 1, 10, 1, 9, 1, 8, 1),
            arrow(15, Direction.LEFT, 2, 10, 4, 9, 4),
            arrow(16, Direction.RIGHT, 3, 1, 7, 2, 7)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(109, LevelData(
            levelNumber = 109,
            title = "City 6 • Route 9 • Cirrus Flow",
            gridWidth = 12,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 7, 10, 7, 9, 7, 8, 7, 7),
            arrow(2, Direction.RIGHT, 1, 2, 3, 2, 4, 2, 5, 3, 5, 4, 5),
            arrow(3, Direction.RIGHT, 2, 2, 10, 3, 10, 4, 10, 5, 10),
            arrow(4, Direction.UP, 3, 1, 7, 1, 6, 1, 5),
            arrow(5, Direction.LEFT, 0, 10, 6, 9, 6, 8, 6, 7, 6, 6, 6),
            arrow(6, Direction.LEFT, 1, 3, 6, 2, 6),
            arrow(7, Direction.RIGHT, 2, 8, 5, 9, 5, 10, 5),
            arrow(8, Direction.DOWN, 3, 3, 3, 4, 3, 5, 3, 5, 4, 5, 5),
            arrow(9, Direction.LEFT, 0, 3, 2, 2, 2),
            arrow(10, Direction.DOWN, 1, 4, 7, 3, 7, 3, 8, 3, 9),
            arrow(11, Direction.LEFT, 2, 8, 1, 7, 1),
            arrow(12, Direction.LEFT, 3, 9, 11, 8, 11, 7, 11, 6, 11),
            arrow(13, Direction.LEFT, 0, 2, 9, 1, 9),
            arrow(14, Direction.DOWN, 1, 4, 11, 4, 12)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(110, LevelData(
            levelNumber = 110,
            title = "City 6 • Route 10 • Sky Bridge",
            gridWidth = 14,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 1, 11, 1, 10, 1, 9, 1, 8),
            arrow(2, Direction.DOWN, 1, 2, 3, 2, 4),
            arrow(3, Direction.LEFT, 2, 5, 9, 5, 10, 5, 11, 4, 11, 3, 11),
            arrow(4, Direction.UP, 3, 8, 10, 8, 9, 8, 8, 8, 7),
            arrow(5, Direction.DOWN, 0, 11, 1, 10, 1, 9, 1, 9, 2, 9, 3, 9, 4),
            arrow(6, Direction.LEFT, 1, 9, 9, 9, 10, 9, 11, 8, 11, 7, 11, 6, 11),
            arrow(7, Direction.LEFT, 2, 5, 3, 4, 3),
            arrow(8, Direction.DOWN, 3, 5, 1, 4, 1, 3, 1, 3, 2, 3, 3, 3, 4),
            arrow(9, Direction.UP, 0, 5, 8, 5, 7, 5, 6),
            arrow(10, Direction.DOWN, 1, 11, 3, 11, 4),
            arrow(11, Direction.LEFT, 2, 10, 7, 9, 7),
            arrow(12, Direction.DOWN, 3, 9, 5, 8, 5, 7, 5, 7, 6, 7, 7, 7, 8),
            arrow(13, Direction.LEFT, 0, 7, 4, 6, 4, 5, 4)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(111, LevelData(
            levelNumber = 111,
            title = "City 6 • Route 11 • Nimbus Gate",
            gridWidth = 14,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 6, 3, 5, 3, 4, 3, 3, 3),
            arrow(2, Direction.LEFT, 1, 6, 9, 5, 9),
            arrow(3, Direction.UP, 2, 7, 7, 7, 6, 7, 5, 7, 4),
            arrow(4, Direction.LEFT, 3, 12, 9, 11, 9),
            arrow(5, Direction.LEFT, 0, 7, 8, 6, 8, 5, 8),
            arrow(6, Direction.DOWN, 1, 9, 4, 10, 4, 11, 4, 11, 5, 11, 6, 11, 7),
            arrow(7, Direction.LEFT, 2, 4, 5, 3, 5),
            arrow(8, Direction.DOWN, 3, 2, 4, 2, 5, 2, 6),
            arrow(9, Direction.DOWN, 0, 5, 4, 5, 5),
            arrow(10, Direction.LEFT, 1, 3, 9, 2, 9, 1, 9, 0, 9),
            arrow(11, Direction.UP, 2, 7, 2, 8, 2, 8, 1, 8, 0),
            arrow(12, Direction.RIGHT, 3, 4, 7, 5, 7, 6, 7),
            arrow(13, Direction.RIGHT, 0, 9, 8, 10, 8, 11, 8, 12, 8, 13, 8),
            arrow(14, Direction.RIGHT, 1, 6, 10, 7, 10, 8, 10)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(112, LevelData(
            levelNumber = 112,
            title = "City 6 • Route 12 • Thermal Drift",
            gridWidth = 11,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 9, 3, 8, 3, 7, 3, 6, 3, 5, 3),
            arrow(2, Direction.DOWN, 1, 3, 7, 2, 7, 1, 7, 1, 8, 1, 9, 1, 10),
            arrow(3, Direction.LEFT, 2, 3, 10, 2, 10),
            arrow(4, Direction.UP, 3, 2, 9, 2, 8),
            arrow(5, Direction.LEFT, 0, 5, 6, 4, 6, 3, 6, 2, 6),
            arrow(6, Direction.RIGHT, 1, 5, 4, 6, 4, 7, 4, 8, 4),
            arrow(7, Direction.RIGHT, 2, 3, 3, 3, 4, 3, 5, 4, 5, 5, 5),
            arrow(8, Direction.UP, 3, 4, 1, 4, 0),
            arrow(9, Direction.LEFT, 0, 8, 9, 7, 9, 6, 9, 5, 9, 4, 9),
            arrow(10, Direction.RIGHT, 1, 5, 1, 6, 1, 7, 1, 8, 1),
            arrow(11, Direction.DOWN, 2, 7, 5, 7, 6, 7, 7),
            arrow(12, Direction.LEFT, 3, 5, 11, 4, 11, 3, 11, 2, 11, 1, 11)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(113, LevelData(
            levelNumber = 113,
            title = "City 6 • Route 13 • Skyline Maze",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 2, 2, 3, 2, 4, 2, 4, 3, 4, 4, 4, 5),
            arrow(2, Direction.RIGHT, 1, 10, 2, 11, 2),
            arrow(3, Direction.LEFT, 2, 2, 3, 1, 3, 0, 3),
            arrow(4, Direction.DOWN, 3, 8, 5, 8, 6, 8, 7),
            arrow(5, Direction.UP, 0, 10, 11, 10, 10, 10, 9, 10, 8, 10, 7),
            arrow(6, Direction.LEFT, 1, 10, 4, 9, 4),
            arrow(7, Direction.DOWN, 2, 9, 5, 9, 6),
            arrow(8, Direction.DOWN, 3, 9, 1, 9, 2),
            arrow(9, Direction.RIGHT, 0, 4, 6, 5, 6, 6, 6),
            arrow(10, Direction.UP, 1, 1, 11, 0, 11, 0, 10, 0, 9),
            arrow(11, Direction.LEFT, 2, 8, 8, 8, 9, 8, 10, 7, 10, 6, 10, 5, 10),
            arrow(12, Direction.DOWN, 3, 3, 6, 3, 7),
            arrow(13, Direction.LEFT, 0, 8, 11, 7, 11, 6, 11),
            arrow(14, Direction.LEFT, 1, 1, 2, 0, 2),
            arrow(15, Direction.LEFT, 2, 4, 9, 3, 9),
            arrow(16, Direction.LEFT, 3, 3, 8, 2, 8, 1, 8)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(114, LevelData(
            levelNumber = 114,
            title = "City 6 • Route 14 • Aurora View",
            gridWidth = 14,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 11, 6, 11, 5, 11, 4, 11, 3, 11, 2),
            arrow(2, Direction.UP, 1, 6, 9, 7, 9, 7, 8, 7, 7),
            arrow(3, Direction.RIGHT, 2, 7, 6, 8, 6),
            arrow(4, Direction.LEFT, 3, 5, 11, 4, 11, 3, 11, 2, 11, 1, 11),
            arrow(5, Direction.DOWN, 0, 9, 5, 9, 6, 9, 7, 9, 8, 9, 9),
            arrow(6, Direction.DOWN, 1, 8, 11, 8, 12),
            arrow(7, Direction.RIGHT, 2, 1, 10, 2, 10),
            arrow(8, Direction.LEFT, 3, 3, 5, 3, 6, 3, 7, 2, 7, 1, 7),
            arrow(9, Direction.LEFT, 0, 10, 3, 9, 3, 8, 3),
            arrow(10, Direction.RIGHT, 1, 10, 12, 11, 12, 12, 12, 13, 12),
            arrow(11, Direction.LEFT, 2, 3, 2, 2, 2, 1, 2, 0, 2),
            arrow(12, Direction.UP, 3, 7, 11, 7, 10),
            arrow(13, Direction.UP, 0, 12, 10, 12, 9, 12, 8, 12, 7),
            arrow(14, Direction.RIGHT, 1, 8, 10, 9, 10, 10, 10, 11, 10),
            arrow(15, Direction.LEFT, 2, 4, 10, 4, 9, 4, 8, 3, 8, 2, 8),
            arrow(16, Direction.LEFT, 3, 3, 4, 2, 4)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(115, LevelData(
            levelNumber = 115,
            title = "City 6 • Route 15 • Cloud Garden",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 4, 8, 4, 9, 4, 10, 5, 10, 6, 10),
            arrow(2, Direction.RIGHT, 1, 6, 6, 6, 7, 6, 8, 7, 8, 8, 8),
            arrow(3, Direction.LEFT, 2, 3, 5, 2, 5, 1, 5, 0, 5),
            arrow(4, Direction.RIGHT, 3, 11, 3, 12, 3),
            arrow(5, Direction.RIGHT, 0, 1, 10, 1, 9, 1, 8, 2, 8, 3, 8),
            arrow(6, Direction.DOWN, 1, 10, 9, 10, 10),
            arrow(7, Direction.RIGHT, 2, 11, 1, 12, 1),
            arrow(8, Direction.DOWN, 3, 9, 4, 10, 4, 11, 4, 11, 5, 11, 6),
            arrow(9, Direction.DOWN, 0, 1, 1, 1, 2),
            arrow(10, Direction.DOWN, 1, 11, 8, 11, 9, 11, 10, 11, 11),
            arrow(11, Direction.UP, 2, 7, 10, 7, 9),
            arrow(12, Direction.DOWN, 3, 6, 3, 7, 3, 8, 3, 8, 4, 8, 5, 8, 6)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(116, LevelData(
            levelNumber = 116,
            title = "City 6 • Route 16 • Solar Sail",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 9, 7, 9, 6, 9, 5, 9, 4),
            arrow(2, Direction.RIGHT, 1, 3, 1, 3, 0, 4, 0, 5, 0),
            arrow(3, Direction.RIGHT, 2, 7, 3, 8, 3),
            arrow(4, Direction.DOWN, 3, 5, 6, 5, 7, 5, 8, 5, 9),
            arrow(5, Direction.DOWN, 0, 9, 1, 10, 1, 11, 1, 11, 2, 11, 3, 11, 4),
            arrow(6, Direction.DOWN, 1, 5, 2, 5, 3, 5, 4, 5, 5),
            arrow(7, Direction.UP, 2, 10, 11, 11, 11, 12, 11, 12, 10, 12, 9),
            arrow(8, Direction.LEFT, 3, 11, 7, 11, 8, 10, 8, 9, 8),
            arrow(9, Direction.UP, 0, 1, 5, 2, 5, 3, 5, 3, 4, 3, 3, 3, 2),
            arrow(10, Direction.LEFT, 1, 3, 9, 2, 9, 1, 9),
            arrow(11, Direction.RIGHT, 2, 4, 10, 5, 10, 6, 10, 7, 10, 8, 10),
            arrow(12, Direction.RIGHT, 3, 2, 11, 3, 11, 4, 11, 5, 11, 6, 11)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(117, LevelData(
            levelNumber = 117,
            title = "City 6 • Route 17 • High Altitude",
            gridWidth = 14,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 3, 10, 3, 9, 3, 8, 3, 7, 3, 6),
            arrow(2, Direction.UP, 1, 10, 5, 10, 4, 10, 3),
            arrow(3, Direction.LEFT, 2, 1, 2, 0, 2),
            arrow(4, Direction.RIGHT, 3, 1, 4, 2, 4, 3, 4, 4, 4, 5, 4),
            arrow(5, Direction.LEFT, 0, 2, 8, 1, 8),
            arrow(6, Direction.LEFT, 1, 9, 10, 9, 9, 9, 8, 8, 8, 7, 8),
            arrow(7, Direction.UP, 2, 9, 1, 9, 0),
            arrow(8, Direction.DOWN, 3, 11, 7, 12, 7, 13, 7, 13, 8, 13, 9, 13, 10),
            arrow(9, Direction.UP, 0, 11, 6, 11, 5, 11, 4),
            arrow(10, Direction.DOWN, 1, 11, 10, 11, 11),
            arrow(11, Direction.DOWN, 2, 4, 5, 5, 5, 6, 5, 6, 6, 6, 7, 6, 8),
            arrow(12, Direction.DOWN, 3, 9, 5, 9, 6, 9, 7),
            arrow(13, Direction.UP, 0, 11, 3, 11, 2, 11, 1, 11, 0),
            arrow(14, Direction.RIGHT, 1, 2, 2, 3, 2, 4, 2),
            arrow(15, Direction.LEFT, 2, 2, 6, 1, 6),
            arrow(16, Direction.UP, 3, 5, 10, 4, 10, 4, 9, 4, 8)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(118, LevelData(
            levelNumber = 118,
            title = "City 6 • Route 18 • Wing Spire",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 9, 1, 9, 2),
            arrow(2, Direction.DOWN, 1, 5, 2, 5, 3, 5, 4),
            arrow(3, Direction.DOWN, 2, 6, 8, 6, 9, 6, 10, 6, 11),
            arrow(4, Direction.RIGHT, 3, 1, 1, 2, 1, 3, 1, 4, 1),
            arrow(5, Direction.LEFT, 0, 1, 3, 0, 3),
            arrow(6, Direction.RIGHT, 1, 2, 10, 3, 10),
            arrow(7, Direction.UP, 2, 8, 9, 8, 8, 8, 7, 8, 6, 8, 5),
            arrow(8, Direction.UP, 3, 10, 4, 10, 3, 10, 2, 10, 1, 10, 0),
            arrow(9, Direction.RIGHT, 0, 5, 1, 5, 0, 6, 0, 7, 0),
            arrow(10, Direction.DOWN, 1, 10, 6, 10, 7, 10, 8),
            arrow(11, Direction.UP, 2, 7, 8, 7, 7, 7, 6, 7, 5, 7, 4),
            arrow(12, Direction.LEFT, 3, 11, 5, 10, 5, 9, 5)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(119, LevelData(
            levelNumber = 119,
            title = "City 6 • Route 19 • Aether Vortex",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 6, 3, 6, 4),
            arrow(2, Direction.LEFT, 1, 6, 9, 5, 9),
            arrow(3, Direction.UP, 2, 7, 6, 7, 5, 7, 4, 7, 3),
            arrow(4, Direction.DOWN, 3, 1, 1, 2, 1, 2, 2, 2, 3),
            arrow(5, Direction.DOWN, 0, 10, 2, 10, 3, 10, 4, 10, 5, 10, 6),
            arrow(6, Direction.LEFT, 1, 3, 10, 2, 10),
            arrow(7, Direction.UP, 2, 5, 7, 5, 6),
            arrow(8, Direction.DOWN, 3, 8, 7, 8, 8, 8, 9),
            arrow(9, Direction.RIGHT, 0, 3, 1, 4, 1, 5, 1),
            arrow(10, Direction.UP, 1, 10, 9, 11, 9, 11, 8, 11, 7),
            arrow(11, Direction.DOWN, 2, 1, 3, 1, 4),
            arrow(12, Direction.UP, 3, 5, 3, 5, 2),
            arrow(13, Direction.RIGHT, 0, 2, 6, 3, 6),
            arrow(14, Direction.UP, 1, 8, 5, 8, 4, 8, 3, 8, 2, 8, 1),
            arrow(15, Direction.UP, 2, 9, 7, 9, 6, 9, 5, 9, 4, 9, 3)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(120, LevelData(
            levelNumber = 120,
            title = "City 6 • Route 20 • Sacred Lotus",
            gridWidth = 15,
            gridHeight = 15,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 6, 13, 7, 13),
            arrow(2, Direction.RIGHT, 1, 6, 12, 7, 12, 8, 12, 9, 12),
            arrow(3, Direction.LEFT, 2, 13, 2, 12, 2, 11, 2, 10, 2, 9, 2),
            arrow(4, Direction.RIGHT, 3, 10, 10, 11, 10, 12, 10, 13, 10),
            arrow(5, Direction.LEFT, 0, 10, 6, 9, 6, 8, 6, 7, 6),
            arrow(6, Direction.DOWN, 1, 9, 7, 9, 8, 9, 9),
            arrow(7, Direction.UP, 2, 2, 12, 2, 11, 2, 10, 2, 9, 2, 8),
            arrow(8, Direction.DOWN, 3, 4, 9, 4, 10, 4, 11),
            arrow(9, Direction.DOWN, 0, 1, 1, 2, 1, 3, 1, 3, 2, 3, 3),
            arrow(10, Direction.DOWN, 1, 7, 1, 7, 2, 7, 3, 7, 4, 7, 5),
            arrow(11, Direction.LEFT, 2, 2, 6, 1, 6, 0, 6),
            arrow(12, Direction.DOWN, 3, 11, 6, 11, 7, 11, 8, 11, 9),
            arrow(13, Direction.RIGHT, 0, 1, 9, 1, 8, 1, 7, 2, 7, 3, 7, 4, 7),
            arrow(14, Direction.LEFT, 1, 8, 8, 7, 8),
            arrow(15, Direction.DOWN, 2, 3, 8, 3, 9, 3, 10)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(121, LevelData(
            levelNumber = 121,
            title = "City 7 • Route 1 • Quartz Shard",
            gridWidth = 15,
            gridHeight = 15,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 1, 11, 1, 12, 1, 13),
            arrow(2, Direction.LEFT, 1, 2, 6, 2, 7, 2, 8, 1, 8, 0, 8),
            arrow(3, Direction.RIGHT, 2, 9, 7, 10, 7, 11, 7, 12, 7),
            arrow(4, Direction.LEFT, 3, 11, 3, 10, 3),
            arrow(5, Direction.RIGHT, 0, 8, 11, 8, 12, 8, 13, 9, 13, 10, 13, 11, 13),
            arrow(6, Direction.RIGHT, 1, 3, 10, 3, 11, 3, 12, 4, 12, 5, 12),
            arrow(7, Direction.LEFT, 2, 9, 9, 8, 9, 7, 9, 6, 9),
            arrow(8, Direction.LEFT, 3, 9, 4, 9, 3, 9, 2, 8, 2, 7, 2, 6, 2),
            arrow(9, Direction.LEFT, 0, 3, 3, 2, 3),
            arrow(10, Direction.LEFT, 1, 13, 11, 12, 11, 11, 11, 10, 11),
            arrow(11, Direction.RIGHT, 2, 6, 4, 7, 4),
            arrow(12, Direction.DOWN, 3, 13, 6, 13, 7),
            arrow(13, Direction.RIGHT, 0, 3, 8, 4, 8, 5, 8, 6, 8),
            arrow(14, Direction.DOWN, 1, 12, 4, 12, 5, 12, 6),
            arrow(15, Direction.LEFT, 2, 13, 10, 12, 10),
            arrow(16, Direction.RIGHT, 3, 7, 6, 7, 7, 7, 8, 8, 8, 9, 8, 10, 8),
            arrow(17, Direction.DOWN, 0, 3, 6, 3, 7),
            arrow(18, Direction.UP, 1, 5, 11, 5, 10, 5, 9)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(122, LevelData(
            levelNumber = 122,
            title = "City 7 • Route 2 • Facet Lane",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 5, 7, 5, 8, 5, 9, 4, 9, 3, 9),
            arrow(2, Direction.LEFT, 1, 6, 2, 5, 2),
            arrow(3, Direction.DOWN, 2, 8, 7, 8, 8),
            arrow(4, Direction.LEFT, 3, 11, 9, 10, 9, 9, 9, 8, 9),
            arrow(5, Direction.UP, 0, 3, 8, 4, 8, 4, 7, 4, 6),
            arrow(6, Direction.RIGHT, 1, 3, 4, 4, 4, 5, 4, 6, 4, 7, 4),
            arrow(7, Direction.RIGHT, 2, 1, 1, 2, 1, 3, 1),
            arrow(8, Direction.RIGHT, 3, 9, 8, 10, 8, 11, 8, 12, 8),
            arrow(9, Direction.DOWN, 0, 5, 10, 5, 11, 5, 12),
            arrow(10, Direction.LEFT, 1, 3, 3, 2, 3),
            arrow(11, Direction.DOWN, 2, 8, 4, 8, 5),
            arrow(12, Direction.UP, 3, 9, 5, 9, 4, 9, 3, 9, 2),
            arrow(13, Direction.RIGHT, 0, 6, 11, 7, 11)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(123, LevelData(
            levelNumber = 123,
            title = "City 7 • Route 3 • Geode Trace",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 3, 8, 4, 8, 5, 8),
            arrow(2, Direction.DOWN, 1, 4, 6, 3, 6, 2, 6, 2, 7, 2, 8),
            arrow(3, Direction.LEFT, 2, 6, 1, 6, 0, 5, 0, 4, 0),
            arrow(4, Direction.UP, 3, 8, 10, 8, 9, 8, 8),
            arrow(5, Direction.RIGHT, 0, 1, 1, 2, 1),
            arrow(6, Direction.DOWN, 1, 2, 10, 3, 10, 4, 10, 4, 11, 4, 12),
            arrow(7, Direction.DOWN, 2, 6, 5, 6, 6, 6, 7, 6, 8, 6, 9),
            arrow(8, Direction.UP, 3, 10, 6, 10, 5),
            arrow(9, Direction.UP, 0, 8, 2, 8, 1),
            arrow(10, Direction.LEFT, 1, 8, 4, 7, 4, 6, 4),
            arrow(11, Direction.UP, 2, 9, 7, 9, 6),
            arrow(12, Direction.DOWN, 3, 7, 6, 7, 7, 7, 8, 7, 9, 7, 10),
            arrow(13, Direction.UP, 0, 5, 3, 5, 2),
            arrow(14, Direction.DOWN, 1, 4, 1, 4, 2, 4, 3),
            arrow(15, Direction.RIGHT, 2, 1, 9, 2, 9),
            arrow(16, Direction.LEFT, 3, 2, 5, 1, 5),
            arrow(17, Direction.DOWN, 0, 9, 10, 9, 11, 9, 12)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(124, LevelData(
            levelNumber = 124,
            title = "City 7 • Route 4 • Lustrous Way",
            gridWidth = 11,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 9, 1, 9, 2, 9, 3),
            arrow(2, Direction.RIGHT, 1, 2, 6, 3, 6, 4, 6),
            arrow(3, Direction.RIGHT, 2, 5, 5, 5, 4, 5, 3, 6, 3, 7, 3),
            arrow(4, Direction.RIGHT, 3, 4, 7, 5, 7, 6, 7, 7, 7, 8, 7),
            arrow(5, Direction.LEFT, 0, 4, 1, 3, 1, 2, 1, 1, 1, 0, 1),
            arrow(6, Direction.RIGHT, 1, 1, 9, 2, 9, 3, 9),
            arrow(7, Direction.RIGHT, 2, 6, 9, 7, 9),
            arrow(8, Direction.LEFT, 3, 9, 8, 8, 8, 7, 8, 6, 8, 5, 8),
            arrow(9, Direction.UP, 0, 3, 5, 3, 4, 3, 3),
            arrow(10, Direction.LEFT, 1, 8, 4, 7, 4),
            arrow(11, Direction.DOWN, 2, 9, 4, 9, 5, 9, 6, 9, 7),
            arrow(12, Direction.RIGHT, 3, 6, 6, 7, 6, 8, 6),
            arrow(13, Direction.LEFT, 0, 4, 9, 4, 10, 3, 10, 2, 10)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(125, LevelData(
            levelNumber = 125,
            title = "City 7 • Route 5 • Crystal Path",
            gridWidth = 15,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 9, 11, 9, 10, 9, 9),
            arrow(2, Direction.DOWN, 1, 7, 4, 7, 5, 7, 6, 7, 7),
            arrow(3, Direction.LEFT, 2, 10, 4, 9, 4),
            arrow(4, Direction.LEFT, 3, 4, 9, 3, 9, 2, 9, 1, 9),
            arrow(5, Direction.UP, 0, 2, 5, 2, 4, 2, 3, 2, 2),
            arrow(6, Direction.LEFT, 1, 12, 7, 11, 7, 10, 7, 9, 7),
            arrow(7, Direction.DOWN, 2, 8, 7, 8, 8),
            arrow(8, Direction.LEFT, 3, 6, 6, 5, 6, 4, 6, 3, 6),
            arrow(9, Direction.RIGHT, 0, 12, 11, 13, 11),
            arrow(10, Direction.UP, 1, 3, 2, 3, 1, 3, 0),
            arrow(11, Direction.UP, 2, 6, 5, 6, 4),
            arrow(12, Direction.DOWN, 3, 11, 1, 11, 2, 11, 3, 11, 4),
            arrow(13, Direction.UP, 0, 4, 3, 4, 2, 4, 1, 4, 0),
            arrow(14, Direction.RIGHT, 1, 5, 3, 6, 3),
            arrow(15, Direction.LEFT, 2, 13, 8, 12, 8),
            arrow(16, Direction.LEFT, 3, 9, 5, 8, 5),
            arrow(17, Direction.LEFT, 0, 7, 8, 6, 8, 5, 8)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(126, LevelData(
            levelNumber = 126,
            title = "City 7 • Route 6 • Specular Bend",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 9, 3, 8, 3, 7, 3, 6, 3, 5, 3),
            arrow(2, Direction.RIGHT, 1, 3, 9, 4, 9, 5, 9, 6, 9, 7, 9),
            arrow(3, Direction.RIGHT, 2, 1, 6, 2, 6, 3, 6),
            arrow(4, Direction.LEFT, 3, 9, 8, 8, 8, 7, 8),
            arrow(5, Direction.RIGHT, 0, 8, 10, 9, 10, 10, 10, 11, 10),
            arrow(6, Direction.UP, 1, 11, 9, 11, 8),
            arrow(7, Direction.RIGHT, 2, 8, 5, 9, 5, 10, 5, 11, 5),
            arrow(8, Direction.DOWN, 3, 4, 4, 5, 4, 5, 5, 5, 6),
            arrow(9, Direction.DOWN, 0, 11, 2, 10, 2, 10, 3, 10, 4),
            arrow(10, Direction.UP, 1, 4, 6, 4, 5),
            arrow(11, Direction.RIGHT, 2, 6, 6, 7, 6, 8, 6, 9, 6, 10, 6),
            arrow(12, Direction.LEFT, 3, 3, 4, 2, 4, 1, 4, 0, 4),
            arrow(13, Direction.RIGHT, 0, 1, 5, 2, 5, 3, 5)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(127, LevelData(
            levelNumber = 127,
            title = "City 7 • Route 7 • Prism Edge",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 11, 10, 10, 10, 9, 10),
            arrow(2, Direction.UP, 1, 10, 3, 10, 2),
            arrow(3, Direction.DOWN, 2, 3, 2, 4, 2, 4, 3, 4, 4),
            arrow(4, Direction.LEFT, 3, 2, 10, 2, 11, 2, 12, 1, 12, 0, 12),
            arrow(5, Direction.UP, 0, 7, 11, 7, 10),
            arrow(6, Direction.RIGHT, 1, 2, 6, 3, 6, 4, 6, 5, 6, 6, 6),
            arrow(7, Direction.UP, 2, 9, 7, 9, 6),
            arrow(8, Direction.UP, 3, 11, 7, 11, 6, 11, 5),
            arrow(9, Direction.LEFT, 0, 7, 3, 6, 3),
            arrow(10, Direction.LEFT, 1, 8, 7, 7, 7, 6, 7),
            arrow(11, Direction.UP, 2, 11, 9, 11, 8),
            arrow(12, Direction.UP, 3, 1, 9, 1, 8, 1, 7),
            arrow(13, Direction.DOWN, 0, 5, 12, 5, 13),
            arrow(14, Direction.UP, 1, 8, 12, 8, 11, 8, 10, 8, 9, 8, 8),
            arrow(15, Direction.LEFT, 2, 9, 1, 8, 1, 7, 1),
            arrow(16, Direction.RIGHT, 3, 2, 5, 3, 5, 4, 5),
            arrow(17, Direction.RIGHT, 0, 4, 11, 4, 10, 4, 9, 5, 9, 6, 9)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(128, LevelData(
            levelNumber = 128,
            title = "City 7 • Route 8 • Reflecting Pool",
            gridWidth = 11,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 1, 3, 2, 3, 2, 4, 2, 5),
            arrow(2, Direction.RIGHT, 1, 1, 2, 2, 2, 3, 2),
            arrow(3, Direction.RIGHT, 2, 5, 4, 5, 5, 5, 6, 6, 6, 7, 6, 8, 6),
            arrow(4, Direction.UP, 3, 1, 9, 1, 8, 1, 7, 1, 6),
            arrow(5, Direction.DOWN, 0, 4, 1, 4, 2),
            arrow(6, Direction.UP, 1, 7, 5, 7, 4, 7, 3, 7, 2, 7, 1),
            arrow(7, Direction.RIGHT, 2, 3, 6, 4, 6),
            arrow(8, Direction.UP, 3, 3, 1, 3, 0),
            arrow(9, Direction.UP, 0, 6, 2, 5, 2, 5, 1, 5, 0),
            arrow(10, Direction.DOWN, 1, 3, 3, 3, 4, 3, 5),
            arrow(11, Direction.DOWN, 2, 8, 8, 8, 9),
            arrow(12, Direction.DOWN, 3, 9, 7, 9, 8, 9, 9),
            arrow(13, Direction.LEFT, 0, 8, 7, 7, 7)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(129, LevelData(
            levelNumber = 129,
            title = "City 7 • Route 9 • Glinting Spire",
            gridWidth = 11,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 8, 4, 8, 5, 7, 5, 6, 5),
            arrow(2, Direction.DOWN, 1, 7, 6, 7, 7, 7, 8, 7, 9),
            arrow(3, Direction.UP, 2, 9, 4, 9, 3, 9, 2, 9, 1, 9, 0),
            arrow(4, Direction.DOWN, 3, 2, 4, 3, 4, 4, 4, 4, 5, 4, 6),
            arrow(5, Direction.DOWN, 0, 7, 2, 7, 3),
            arrow(6, Direction.LEFT, 1, 3, 2, 2, 2),
            arrow(7, Direction.UP, 2, 1, 6, 1, 5, 1, 4, 1, 3),
            arrow(8, Direction.RIGHT, 3, 1, 9, 2, 9, 3, 9, 4, 9),
            arrow(9, Direction.UP, 0, 3, 8, 3, 7, 3, 6, 3, 5),
            arrow(10, Direction.UP, 1, 5, 4, 5, 3, 5, 2, 5, 1),
            arrow(11, Direction.LEFT, 2, 2, 8, 2, 7, 1, 7, 0, 7),
            arrow(12, Direction.DOWN, 3, 6, 7, 6, 8, 6, 9),
            arrow(13, Direction.RIGHT, 0, 9, 7, 10, 7)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(130, LevelData(
            levelNumber = 130,
            title = "City 7 • Route 10 • Mirror Prism",
            gridWidth = 12,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 4, 5, 4, 4),
            arrow(2, Direction.DOWN, 1, 9, 8, 10, 8, 10, 9, 10, 10),
            arrow(3, Direction.RIGHT, 2, 5, 3, 5, 2, 5, 1, 6, 1, 7, 1, 8, 1),
            arrow(4, Direction.UP, 3, 8, 10, 8, 9, 8, 8, 8, 7),
            arrow(5, Direction.RIGHT, 0, 5, 5, 6, 5, 7, 5, 8, 5, 9, 5),
            arrow(6, Direction.RIGHT, 1, 5, 7, 6, 7, 7, 7),
            arrow(7, Direction.RIGHT, 2, 9, 4, 10, 4),
            arrow(8, Direction.DOWN, 3, 3, 9, 4, 9, 5, 9, 5, 10, 5, 11, 5, 12),
            arrow(9, Direction.RIGHT, 0, 1, 11, 2, 11),
            arrow(10, Direction.UP, 1, 9, 3, 9, 2, 9, 1, 9, 0),
            arrow(11, Direction.DOWN, 2, 1, 7, 2, 7, 2, 8, 2, 9),
            arrow(12, Direction.RIGHT, 3, 2, 2, 3, 2, 4, 2),
            arrow(13, Direction.UP, 0, 4, 12, 4, 11),
            arrow(14, Direction.LEFT, 1, 1, 3, 0, 3),
            arrow(15, Direction.UP, 2, 10, 2, 10, 1, 10, 0),
            arrow(16, Direction.RIGHT, 3, 2, 3, 3, 3),
            arrow(17, Direction.RIGHT, 0, 7, 11, 8, 11)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(131, LevelData(
            levelNumber = 131,
            title = "City 7 • Route 11 • Diamond Matrix",
            gridWidth = 12,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 10, 4, 9, 4, 9, 3, 9, 2),
            arrow(2, Direction.DOWN, 1, 2, 3, 2, 4),
            arrow(3, Direction.LEFT, 2, 10, 10, 9, 10, 8, 10),
            arrow(4, Direction.UP, 3, 9, 6, 8, 6, 7, 6, 7, 5, 7, 4),
            arrow(5, Direction.LEFT, 0, 5, 1, 4, 1),
            arrow(6, Direction.DOWN, 1, 6, 10, 7, 10, 7, 11, 7, 12),
            arrow(7, Direction.DOWN, 2, 5, 8, 5, 9),
            arrow(8, Direction.LEFT, 3, 5, 4, 4, 4),
            arrow(9, Direction.RIGHT, 0, 2, 2, 2, 1, 2, 0, 3, 0, 4, 0, 5, 0),
            arrow(10, Direction.RIGHT, 1, 3, 7, 4, 7),
            arrow(11, Direction.LEFT, 2, 7, 3, 6, 3, 5, 3),
            arrow(12, Direction.DOWN, 3, 4, 2, 4, 3),
            arrow(13, Direction.RIGHT, 0, 6, 9, 7, 9, 8, 9, 9, 9, 10, 9),
            arrow(14, Direction.DOWN, 1, 2, 8, 3, 8, 4, 8, 4, 9, 4, 10, 4, 11),
            arrow(15, Direction.LEFT, 2, 10, 8, 9, 8, 8, 8, 7, 8),
            arrow(16, Direction.DOWN, 3, 8, 3, 8, 4),
            arrow(17, Direction.LEFT, 0, 5, 5, 4, 5, 3, 5, 2, 5, 1, 5)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(132, LevelData(
            levelNumber = 132,
            title = "City 7 • Route 12 • Beryl Corridor",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 8, 9, 8, 8, 8, 7, 8, 6, 8, 5),
            arrow(2, Direction.UP, 1, 9, 7, 10, 7, 10, 6, 10, 5),
            arrow(3, Direction.DOWN, 2, 2, 6, 1, 6, 1, 7, 1, 8),
            arrow(4, Direction.UP, 3, 6, 9, 6, 8, 6, 7),
            arrow(5, Direction.RIGHT, 0, 2, 7, 3, 7),
            arrow(6, Direction.UP, 1, 8, 11, 8, 10),
            arrow(7, Direction.LEFT, 2, 9, 2, 8, 2, 7, 2, 6, 2, 5, 2),
            arrow(8, Direction.DOWN, 3, 4, 6, 4, 7, 4, 8),
            arrow(9, Direction.UP, 0, 7, 9, 7, 8, 7, 7, 7, 6),
            arrow(10, Direction.RIGHT, 1, 10, 10, 10, 11, 10, 12, 11, 12, 12, 12),
            arrow(11, Direction.DOWN, 2, 11, 9, 12, 9, 12, 10, 12, 11),
            arrow(12, Direction.RIGHT, 3, 8, 3, 9, 3, 10, 3, 11, 3),
            arrow(13, Direction.RIGHT, 0, 2, 10, 3, 10, 4, 10, 5, 10, 6, 10),
            arrow(14, Direction.RIGHT, 1, 1, 11, 2, 11, 3, 11, 4, 11),
            arrow(15, Direction.UP, 2, 4, 2, 3, 2, 2, 2, 2, 1, 2, 0),
            arrow(16, Direction.LEFT, 3, 1, 4, 0, 4),
            arrow(17, Direction.LEFT, 0, 9, 4, 8, 4, 7, 4, 6, 4, 5, 4),
            arrow(18, Direction.UP, 1, 4, 1, 4, 0)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(133, LevelData(
            levelNumber = 133,
            title = "City 7 • Route 13 • Sapphire Run",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 9, 8, 9, 7, 9, 6, 9, 5, 9, 4),
            arrow(2, Direction.RIGHT, 1, 4, 5, 4, 6, 4, 7, 5, 7, 6, 7),
            arrow(3, Direction.LEFT, 2, 8, 10, 7, 10, 6, 10, 5, 10),
            arrow(4, Direction.DOWN, 3, 1, 1, 1, 2, 1, 3),
            arrow(5, Direction.UP, 0, 11, 8, 11, 7),
            arrow(6, Direction.RIGHT, 1, 1, 8, 2, 8),
            arrow(7, Direction.RIGHT, 2, 10, 2, 11, 2),
            arrow(8, Direction.RIGHT, 3, 5, 11, 6, 11),
            arrow(9, Direction.UP, 0, 7, 4, 7, 3, 7, 2, 7, 1),
            arrow(10, Direction.UP, 1, 9, 2, 9, 1, 9, 0),
            arrow(11, Direction.RIGHT, 2, 11, 10, 12, 10),
            arrow(12, Direction.UP, 3, 11, 11, 10, 11, 9, 11, 9, 10, 9, 9),
            arrow(13, Direction.RIGHT, 0, 6, 8, 6, 9, 7, 9, 8, 9),
            arrow(14, Direction.UP, 1, 3, 5, 3, 4, 3, 3),
            arrow(15, Direction.RIGHT, 2, 1, 10, 1, 11, 1, 12, 2, 12, 3, 12, 4, 12),
            arrow(16, Direction.UP, 3, 10, 1, 10, 0),
            arrow(17, Direction.LEFT, 0, 5, 3, 5, 2, 4, 2, 3, 2)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(134, LevelData(
            levelNumber = 134,
            title = "City 7 • Route 14 • Emerald Facet",
            gridWidth = 12,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 8, 9, 8, 10, 8, 11),
            arrow(2, Direction.RIGHT, 1, 2, 5, 3, 5, 4, 5, 5, 5),
            arrow(3, Direction.UP, 2, 6, 9, 5, 9, 5, 8, 5, 7),
            arrow(4, Direction.UP, 3, 1, 4, 1, 3, 1, 2),
            arrow(5, Direction.DOWN, 0, 10, 2, 10, 3, 10, 4, 10, 5),
            arrow(6, Direction.LEFT, 1, 3, 1, 2, 1, 1, 1),
            arrow(7, Direction.UP, 2, 4, 9, 4, 8, 4, 7, 4, 6),
            arrow(8, Direction.LEFT, 3, 10, 10, 9, 10),
            arrow(9, Direction.DOWN, 0, 3, 7, 3, 8, 3, 9, 3, 10, 3, 11),
            arrow(10, Direction.UP, 1, 7, 6, 7, 5, 7, 4, 7, 3, 7, 2),
            arrow(11, Direction.RIGHT, 2, 7, 10, 7, 11, 7, 12, 8, 12, 9, 12),
            arrow(12, Direction.UP, 3, 6, 12, 6, 11),
            arrow(13, Direction.DOWN, 0, 2, 7, 2, 8),
            arrow(14, Direction.LEFT, 1, 5, 12, 4, 12)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(135, LevelData(
            levelNumber = 135,
            title = "City 7 • Route 15 • Prismatic Spire",
            gridWidth = 13,
            gridHeight = 15,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 5, 3, 4, 3, 3, 3, 3, 4, 3, 5, 3, 6),
            arrow(2, Direction.DOWN, 1, 8, 3, 8, 4, 8, 5, 8, 6),
            arrow(3, Direction.LEFT, 2, 10, 13, 9, 13, 8, 13, 7, 13, 6, 13),
            arrow(4, Direction.LEFT, 3, 11, 10, 10, 10),
            arrow(5, Direction.UP, 0, 9, 10, 9, 9, 9, 8, 9, 7),
            arrow(6, Direction.LEFT, 1, 8, 7, 7, 7),
            arrow(7, Direction.DOWN, 2, 3, 13, 3, 14),
            arrow(8, Direction.UP, 3, 5, 10, 5, 9, 5, 8, 5, 7),
            arrow(9, Direction.UP, 0, 6, 5, 6, 4, 6, 3, 6, 2, 6, 1),
            arrow(10, Direction.DOWN, 1, 10, 5, 10, 6, 10, 7),
            arrow(11, Direction.RIGHT, 2, 6, 12, 7, 12, 8, 12, 9, 12, 10, 12),
            arrow(12, Direction.DOWN, 3, 5, 12, 5, 13),
            arrow(13, Direction.LEFT, 0, 1, 7, 0, 7),
            arrow(14, Direction.UP, 1, 11, 12, 11, 11),
            arrow(15, Direction.UP, 2, 8, 2, 9, 2, 9, 1, 9, 0),
            arrow(16, Direction.DOWN, 3, 3, 7, 3, 8, 3, 9, 3, 10, 3, 11),
            arrow(17, Direction.DOWN, 0, 4, 6, 4, 7)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(136, LevelData(
            levelNumber = 136,
            title = "City 7 • Route 16 • Resonance Chamber",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 4, 1, 3, 1, 2, 1, 1, 1),
            arrow(2, Direction.RIGHT, 1, 4, 8, 5, 8),
            arrow(3, Direction.LEFT, 2, 8, 5, 7, 5, 6, 5, 5, 5, 4, 5),
            arrow(4, Direction.DOWN, 3, 7, 6, 7, 7, 7, 8, 7, 9),
            arrow(5, Direction.RIGHT, 0, 7, 2, 8, 2, 9, 2, 10, 2),
            arrow(6, Direction.RIGHT, 1, 2, 11, 3, 11, 4, 11, 5, 11),
            arrow(7, Direction.RIGHT, 2, 9, 3, 9, 4, 10, 4, 11, 4),
            arrow(8, Direction.RIGHT, 3, 4, 10, 5, 10, 6, 10, 7, 10),
            arrow(9, Direction.UP, 0, 3, 5, 3, 4, 3, 3),
            arrow(10, Direction.DOWN, 1, 10, 8, 10, 9),
            arrow(11, Direction.UP, 2, 8, 11, 8, 10, 8, 9, 8, 8, 8, 7),
            arrow(12, Direction.LEFT, 3, 3, 9, 2, 9, 1, 9, 0, 9),
            arrow(13, Direction.LEFT, 0, 4, 7, 3, 7, 2, 7),
            arrow(14, Direction.LEFT, 1, 10, 6, 9, 6)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(137, LevelData(
            levelNumber = 137,
            title = "City 7 • Route 17 • Starlight Crystal",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 6, 2, 6, 3),
            arrow(2, Direction.DOWN, 1, 2, 8, 2, 9),
            arrow(3, Direction.RIGHT, 2, 3, 7, 4, 7),
            arrow(4, Direction.DOWN, 3, 5, 7, 5, 8),
            arrow(5, Direction.RIGHT, 0, 1, 5, 2, 5),
            arrow(6, Direction.RIGHT, 1, 5, 1, 6, 1),
            arrow(7, Direction.LEFT, 2, 7, 9, 6, 9, 5, 9),
            arrow(8, Direction.RIGHT, 3, 2, 1, 3, 1),
            arrow(9, Direction.DOWN, 0, 11, 2, 11, 3),
            arrow(10, Direction.UP, 1, 10, 6, 10, 5, 10, 4, 10, 3, 10, 2),
            arrow(11, Direction.RIGHT, 2, 11, 1, 12, 1),
            arrow(12, Direction.UP, 3, 1, 3, 1, 2, 1, 1),
            arrow(13, Direction.UP, 0, 8, 3, 8, 2),
            arrow(14, Direction.UP, 1, 1, 8, 1, 7),
            arrow(15, Direction.RIGHT, 2, 2, 2, 3, 2, 4, 2),
            arrow(16, Direction.UP, 3, 4, 6, 3, 6, 3, 5, 3, 4),
            arrow(17, Direction.RIGHT, 0, 8, 7, 8, 8, 9, 8, 10, 8),
            arrow(18, Direction.LEFT, 1, 3, 3, 2, 3)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(138, LevelData(
            levelNumber = 138,
            title = "City 7 • Route 18 • Chime Maze",
            gridWidth = 14,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 2, 4, 3, 4),
            arrow(2, Direction.RIGHT, 1, 9, 11, 10, 11, 11, 11, 12, 11),
            arrow(3, Direction.LEFT, 2, 6, 10, 5, 10, 4, 10),
            arrow(4, Direction.RIGHT, 3, 7, 1, 8, 1, 9, 1),
            arrow(5, Direction.DOWN, 0, 10, 6, 10, 7),
            arrow(6, Direction.RIGHT, 1, 4, 2, 5, 2, 6, 2, 7, 2),
            arrow(7, Direction.UP, 2, 2, 9, 2, 8, 2, 7),
            arrow(8, Direction.UP, 3, 8, 5, 8, 4, 8, 3, 8, 2),
            arrow(9, Direction.DOWN, 0, 1, 6, 1, 7, 1, 8, 1, 9),
            arrow(10, Direction.UP, 1, 2, 2, 2, 1),
            arrow(11, Direction.RIGHT, 2, 3, 5, 4, 5, 5, 5),
            arrow(12, Direction.DOWN, 3, 12, 1, 11, 1, 10, 1, 10, 2, 10, 3),
            arrow(13, Direction.DOWN, 0, 6, 8, 7, 8, 7, 9, 7, 10),
            arrow(14, Direction.LEFT, 1, 9, 6, 8, 6),
            arrow(15, Direction.RIGHT, 2, 11, 7, 12, 7)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(139, LevelData(
            levelNumber = 139,
            title = "City 7 • Route 19 • Crystalline Web",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 7, 10, 6, 10, 5, 10),
            arrow(2, Direction.LEFT, 1, 4, 4, 3, 4, 2, 4, 1, 4, 0, 4),
            arrow(3, Direction.LEFT, 2, 4, 8, 3, 8, 2, 8, 1, 8, 0, 8),
            arrow(4, Direction.RIGHT, 3, 10, 4, 11, 4, 12, 4),
            arrow(5, Direction.RIGHT, 0, 1, 9, 2, 9, 3, 9, 4, 9),
            arrow(6, Direction.RIGHT, 1, 7, 9, 8, 9),
            arrow(7, Direction.RIGHT, 2, 10, 8, 11, 8),
            arrow(8, Direction.UP, 3, 9, 8, 9, 7, 9, 6, 9, 5),
            arrow(9, Direction.DOWN, 0, 3, 10, 3, 11),
            arrow(10, Direction.LEFT, 1, 9, 2, 9, 3, 9, 4, 8, 4, 7, 4, 6, 4),
            arrow(11, Direction.UP, 2, 8, 1, 8, 0),
            arrow(12, Direction.DOWN, 3, 1, 1, 2, 1, 2, 2, 2, 3),
            arrow(13, Direction.UP, 0, 1, 7, 0, 7, 0, 6, 0, 5),
            arrow(14, Direction.UP, 1, 5, 7, 5, 6, 5, 5, 5, 4),
            arrow(15, Direction.DOWN, 2, 8, 6, 8, 7),
            arrow(16, Direction.LEFT, 3, 7, 2, 6, 2, 5, 2)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(140, LevelData(
            levelNumber = 140,
            title = "City 7 • Route 20 • The Crystal Core",
            gridWidth = 15,
            gridHeight = 15,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 9, 7, 9, 8),
            arrow(2, Direction.RIGHT, 1, 7, 6, 8, 6),
            arrow(3, Direction.RIGHT, 2, 3, 12, 4, 12, 5, 12),
            arrow(4, Direction.UP, 3, 2, 4, 2, 3),
            arrow(5, Direction.LEFT, 0, 7, 2, 6, 2, 5, 2),
            arrow(6, Direction.LEFT, 1, 9, 1, 8, 1, 7, 1),
            arrow(7, Direction.LEFT, 2, 5, 7, 5, 8, 4, 8, 3, 8),
            arrow(8, Direction.RIGHT, 3, 11, 2, 12, 2),
            arrow(9, Direction.UP, 0, 8, 12, 8, 11, 8, 10, 8, 9),
            arrow(10, Direction.DOWN, 1, 4, 3, 4, 4, 4, 5),
            arrow(11, Direction.DOWN, 2, 1, 3, 1, 4, 1, 5, 1, 6),
            arrow(12, Direction.LEFT, 3, 11, 3, 10, 3),
            arrow(13, Direction.RIGHT, 0, 4, 7, 4, 6, 5, 6, 6, 6),
            arrow(14, Direction.DOWN, 1, 4, 9, 4, 10, 4, 11),
            arrow(15, Direction.DOWN, 2, 13, 7, 12, 7, 12, 8, 12, 9),
            arrow(16, Direction.UP, 3, 10, 6, 9, 6, 9, 5, 9, 4),
            arrow(17, Direction.RIGHT, 0, 5, 5, 6, 5)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(141, LevelData(
            levelNumber = 141,
            title = "City 8 • Route 1 • Cog Tooth",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 3, 8, 2, 8, 1, 8, 1, 7, 1, 6, 1, 5),
            arrow(2, Direction.RIGHT, 1, 3, 1, 3, 2, 3, 3, 4, 3, 5, 3),
            arrow(3, Direction.UP, 2, 5, 8, 6, 8, 7, 8, 7, 7, 7, 6, 7, 5),
            arrow(4, Direction.UP, 3, 1, 3, 2, 3, 2, 2, 2, 1),
            arrow(5, Direction.DOWN, 0, 2, 5, 3, 5, 4, 5, 4, 6, 4, 7, 4, 8),
            arrow(6, Direction.RIGHT, 1, 10, 7, 11, 7, 12, 7),
            arrow(7, Direction.UP, 2, 9, 7, 9, 6, 9, 5),
            arrow(8, Direction.LEFT, 3, 7, 2, 6, 2),
            arrow(9, Direction.DOWN, 0, 11, 1, 11, 2, 11, 3),
            arrow(10, Direction.LEFT, 1, 3, 9, 3, 10, 3, 11, 2, 11, 1, 11, 0, 11),
            arrow(11, Direction.LEFT, 2, 8, 2, 8, 1, 8, 0, 7, 0, 6, 0, 5, 0),
            arrow(12, Direction.RIGHT, 3, 5, 10, 5, 11, 6, 11, 7, 11),
            arrow(13, Direction.RIGHT, 0, 8, 3, 9, 3, 10, 3),
            arrow(14, Direction.LEFT, 1, 10, 10, 10, 9, 10, 8, 9, 8, 8, 8),
            arrow(15, Direction.LEFT, 2, 9, 4, 8, 4, 7, 4, 6, 4, 5, 4),
            arrow(16, Direction.LEFT, 3, 8, 10, 7, 10),
            arrow(17, Direction.RIGHT, 0, 1, 9, 2, 9)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(142, LevelData(
            levelNumber = 142,
            title = "City 8 • Route 2 • Axle Turn",
            gridWidth = 14,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 7, 1, 7, 2, 8, 2, 9, 2),
            arrow(2, Direction.LEFT, 1, 11, 7, 10, 7),
            arrow(3, Direction.LEFT, 2, 5, 8, 5, 7, 5, 6, 4, 6, 3, 6, 2, 6),
            arrow(4, Direction.LEFT, 3, 11, 1, 10, 1),
            arrow(5, Direction.UP, 0, 4, 9, 3, 9, 3, 8, 3, 7),
            arrow(6, Direction.UP, 1, 1, 4, 1, 3, 1, 2, 1, 1, 1, 0),
            arrow(7, Direction.DOWN, 2, 12, 2, 11, 2, 10, 2, 10, 3, 10, 4, 10, 5),
            arrow(8, Direction.UP, 3, 5, 4, 4, 4, 4, 3, 4, 2),
            arrow(9, Direction.UP, 0, 12, 7, 12, 6, 12, 5, 12, 4),
            arrow(10, Direction.LEFT, 1, 8, 9, 7, 9),
            arrow(11, Direction.DOWN, 2, 6, 4, 6, 5, 6, 6, 6, 7),
            arrow(12, Direction.LEFT, 3, 11, 9, 11, 8, 10, 8, 9, 8),
            arrow(13, Direction.LEFT, 0, 6, 8, 6, 9, 6, 10, 5, 10, 4, 10, 3, 10),
            arrow(14, Direction.DOWN, 1, 6, 1, 6, 2),
            arrow(15, Direction.RIGHT, 2, 8, 10, 9, 10),
            arrow(16, Direction.DOWN, 3, 2, 8, 1, 8, 0, 8, 0, 9, 0, 10)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(143, LevelData(
            levelNumber = 143,
            title = "City 8 • Route 3 • Camshaft Run",
            gridWidth = 15,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 6, 3, 6, 2, 5, 2, 4, 2),
            arrow(2, Direction.DOWN, 1, 7, 8, 7, 9),
            arrow(3, Direction.UP, 2, 1, 11, 1, 10, 1, 9),
            arrow(4, Direction.DOWN, 3, 11, 8, 11, 9),
            arrow(5, Direction.RIGHT, 0, 8, 1, 8, 2, 9, 2, 10, 2),
            arrow(6, Direction.UP, 1, 2, 6, 3, 6, 3, 5, 3, 4),
            arrow(7, Direction.LEFT, 2, 3, 12, 2, 12, 1, 12),
            arrow(8, Direction.RIGHT, 3, 4, 7, 5, 7, 6, 7, 7, 7),
            arrow(9, Direction.DOWN, 0, 5, 3, 5, 4, 5, 5, 5, 6),
            arrow(10, Direction.LEFT, 1, 13, 11, 12, 11),
            arrow(11, Direction.LEFT, 2, 9, 5, 9, 6, 8, 6, 7, 6),
            arrow(12, Direction.DOWN, 3, 11, 2, 11, 3),
            arrow(13, Direction.UP, 0, 8, 9, 8, 8),
            arrow(14, Direction.RIGHT, 1, 10, 4, 11, 4, 12, 4, 13, 4),
            arrow(15, Direction.UP, 2, 5, 9, 4, 9, 3, 9, 3, 8, 3, 7),
            arrow(16, Direction.DOWN, 3, 7, 4, 7, 5),
            arrow(17, Direction.RIGHT, 0, 12, 3, 13, 3, 14, 3),
            arrow(18, Direction.LEFT, 1, 13, 12, 12, 12, 11, 12, 10, 12, 9, 12),
            arrow(19, Direction.LEFT, 2, 12, 5, 11, 5, 10, 5)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(144, LevelData(
            levelNumber = 144,
            title = "City 8 • Route 4 • Ratchet Lane",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 7, 10, 6, 10),
            arrow(2, Direction.DOWN, 1, 7, 8, 7, 9),
            arrow(3, Direction.UP, 2, 3, 5, 3, 4),
            arrow(4, Direction.DOWN, 3, 1, 6, 1, 7, 1, 8, 1, 9, 1, 10),
            arrow(5, Direction.UP, 0, 3, 2, 3, 1, 3, 0),
            arrow(6, Direction.LEFT, 1, 9, 9, 8, 9),
            arrow(7, Direction.LEFT, 2, 5, 8, 5, 9, 5, 10, 4, 10, 3, 10, 2, 10),
            arrow(8, Direction.DOWN, 3, 8, 3, 8, 4),
            arrow(9, Direction.DOWN, 0, 7, 6, 7, 7),
            arrow(10, Direction.DOWN, 1, 9, 6, 10, 6, 11, 6, 11, 7, 11, 8, 11, 9),
            arrow(11, Direction.LEFT, 2, 4, 3, 3, 3, 2, 3, 1, 3, 0, 3),
            arrow(12, Direction.RIGHT, 3, 10, 10, 11, 10),
            arrow(13, Direction.DOWN, 0, 8, 5, 8, 6),
            arrow(14, Direction.DOWN, 1, 2, 6, 2, 7, 2, 8, 2, 9),
            arrow(15, Direction.LEFT, 2, 5, 6, 4, 6)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(145, LevelData(
            levelNumber = 145,
            title = "City 8 • Route 5 • Piston Drive",
            gridWidth = 12,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 3, 12, 2, 12, 1, 12, 1, 11, 1, 10),
            arrow(2, Direction.LEFT, 1, 1, 5, 0, 5),
            arrow(3, Direction.DOWN, 2, 4, 3, 4, 4, 4, 5, 4, 6, 4, 7),
            arrow(4, Direction.DOWN, 3, 2, 4, 2, 5),
            arrow(5, Direction.RIGHT, 0, 6, 1, 7, 1),
            arrow(6, Direction.RIGHT, 1, 6, 11, 7, 11, 8, 11, 9, 11, 10, 11),
            arrow(7, Direction.UP, 2, 8, 9, 8, 8, 8, 7, 8, 6),
            arrow(8, Direction.RIGHT, 3, 9, 7, 9, 8, 9, 9, 10, 9, 11, 9),
            arrow(9, Direction.DOWN, 0, 3, 1, 3, 2, 3, 3, 3, 4, 3, 5),
            arrow(10, Direction.RIGHT, 1, 5, 2, 6, 2, 7, 2, 8, 2, 9, 2),
            arrow(11, Direction.UP, 2, 6, 10, 6, 9, 6, 8),
            arrow(12, Direction.RIGHT, 3, 10, 1, 11, 1),
            arrow(13, Direction.RIGHT, 0, 1, 8, 2, 8, 3, 8, 4, 8),
            arrow(14, Direction.RIGHT, 1, 6, 7, 7, 7),
            arrow(15, Direction.LEFT, 2, 3, 10, 2, 10),
            arrow(16, Direction.RIGHT, 3, 4, 10, 5, 10),
            arrow(17, Direction.UP, 0, 5, 4, 5, 3)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(146, LevelData(
            levelNumber = 146,
            title = "City 8 • Route 6 • Conveyor Line",
            gridWidth = 12,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 6, 4, 7, 4, 8, 4),
            arrow(2, Direction.UP, 1, 9, 2, 9, 1),
            arrow(3, Direction.RIGHT, 2, 1, 3, 2, 3, 3, 3, 4, 3, 5, 3),
            arrow(4, Direction.RIGHT, 3, 9, 5, 10, 5, 11, 5),
            arrow(5, Direction.RIGHT, 0, 8, 9, 8, 10, 8, 11, 9, 11, 10, 11, 11, 11),
            arrow(6, Direction.RIGHT, 1, 1, 5, 2, 5),
            arrow(7, Direction.UP, 2, 2, 2, 2, 1, 2, 0),
            arrow(8, Direction.RIGHT, 3, 2, 7, 3, 7, 4, 7, 5, 7),
            arrow(9, Direction.DOWN, 0, 10, 3, 10, 4),
            arrow(10, Direction.LEFT, 1, 8, 6, 7, 6, 6, 6, 5, 6),
            arrow(11, Direction.DOWN, 2, 6, 9, 6, 10),
            arrow(12, Direction.LEFT, 3, 9, 8, 8, 8, 7, 8),
            arrow(13, Direction.UP, 0, 3, 2, 3, 1, 3, 0),
            arrow(14, Direction.UP, 1, 3, 11, 3, 10),
            arrow(15, Direction.UP, 2, 1, 8, 1, 7),
            arrow(16, Direction.LEFT, 3, 2, 6, 1, 6),
            arrow(17, Direction.RIGHT, 0, 7, 11, 7, 12, 7, 13, 8, 13, 9, 13),
            arrow(18, Direction.LEFT, 1, 7, 1, 7, 2, 6, 2, 5, 2),
            arrow(19, Direction.UP, 2, 3, 6, 3, 5, 3, 4)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(147, LevelData(
            levelNumber = 147,
            title = "City 8 • Route 7 • Sprocket Walk",
            gridWidth = 15,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 6, 9, 6, 10, 6, 11, 6, 12),
            arrow(2, Direction.RIGHT, 1, 5, 3, 6, 3, 7, 3, 8, 3),
            arrow(3, Direction.DOWN, 2, 12, 10, 11, 10, 11, 11, 11, 12),
            arrow(4, Direction.LEFT, 3, 4, 7, 3, 7, 2, 7, 1, 7),
            arrow(5, Direction.DOWN, 0, 11, 3, 11, 4),
            arrow(6, Direction.UP, 1, 5, 1, 5, 0),
            arrow(7, Direction.RIGHT, 2, 2, 11, 3, 11, 4, 11, 5, 11),
            arrow(8, Direction.UP, 3, 13, 5, 13, 4, 13, 3, 13, 2),
            arrow(9, Direction.UP, 0, 9, 11, 9, 10, 9, 9),
            arrow(10, Direction.LEFT, 1, 3, 5, 2, 5),
            arrow(11, Direction.LEFT, 2, 12, 9, 11, 9),
            arrow(12, Direction.RIGHT, 3, 13, 1, 14, 1),
            arrow(13, Direction.DOWN, 0, 7, 7, 8, 7, 8, 8, 8, 9),
            arrow(14, Direction.DOWN, 1, 2, 8, 2, 9),
            arrow(15, Direction.UP, 2, 12, 6, 12, 5),
            arrow(16, Direction.DOWN, 3, 11, 6, 11, 7),
            arrow(17, Direction.RIGHT, 0, 5, 7, 5, 6, 5, 5, 6, 5, 7, 5, 8, 5),
            arrow(18, Direction.DOWN, 1, 10, 8, 10, 9),
            arrow(19, Direction.DOWN, 2, 8, 2, 9, 2, 10, 2, 10, 3, 10, 4),
            arrow(20, Direction.LEFT, 3, 1, 8, 0, 8)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(148, LevelData(
            levelNumber = 148,
            title = "City 8 • Route 8 • Steam Valve",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 9, 2, 9, 3, 8, 3, 7, 3),
            arrow(2, Direction.UP, 1, 11, 4, 11, 3),
            arrow(3, Direction.UP, 2, 9, 11, 9, 10),
            arrow(4, Direction.UP, 3, 9, 7, 8, 7, 7, 7, 7, 6, 7, 5),
            arrow(5, Direction.UP, 0, 9, 1, 9, 0),
            arrow(6, Direction.LEFT, 1, 10, 5, 9, 5, 8, 5),
            arrow(7, Direction.UP, 2, 2, 7, 3, 7, 4, 7, 4, 6, 4, 5, 4, 4),
            arrow(8, Direction.LEFT, 3, 11, 11, 10, 11),
            arrow(9, Direction.UP, 0, 3, 6, 2, 6, 1, 6, 1, 5, 1, 4),
            arrow(10, Direction.LEFT, 1, 11, 6, 10, 6),
            arrow(11, Direction.LEFT, 2, 6, 8, 5, 8, 4, 8, 3, 8),
            arrow(12, Direction.RIGHT, 3, 9, 4, 10, 4),
            arrow(13, Direction.DOWN, 0, 7, 8, 7, 9, 7, 10, 7, 11, 7, 12),
            arrow(14, Direction.RIGHT, 1, 10, 9, 11, 9, 12, 9),
            arrow(15, Direction.RIGHT, 2, 4, 1, 4, 0, 5, 0, 6, 0)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(149, LevelData(
            levelNumber = 149,
            title = "City 8 • Route 9 • Clockwork Ring",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 6, 3, 6, 4),
            arrow(2, Direction.RIGHT, 1, 3, 6, 4, 6, 5, 6),
            arrow(3, Direction.UP, 2, 7, 5, 7, 4),
            arrow(4, Direction.DOWN, 3, 8, 4, 8, 5, 8, 6, 8, 7),
            arrow(5, Direction.LEFT, 0, 10, 9, 9, 9, 8, 9, 7, 9),
            arrow(6, Direction.LEFT, 1, 3, 9, 3, 8, 3, 7, 2, 7, 1, 7),
            arrow(7, Direction.DOWN, 2, 9, 1, 9, 2),
            arrow(8, Direction.UP, 3, 1, 5, 1, 4, 1, 3),
            arrow(9, Direction.LEFT, 0, 2, 9, 1, 9),
            arrow(10, Direction.RIGHT, 1, 6, 2, 7, 2, 8, 2),
            arrow(11, Direction.DOWN, 2, 9, 5, 9, 6, 9, 7, 9, 8),
            arrow(12, Direction.UP, 3, 2, 6, 2, 5, 2, 4, 2, 3, 2, 2),
            arrow(13, Direction.DOWN, 0, 7, 10, 7, 11),
            arrow(14, Direction.DOWN, 1, 6, 6, 6, 7, 6, 8, 6, 9),
            arrow(15, Direction.RIGHT, 2, 4, 5, 5, 5),
            arrow(16, Direction.RIGHT, 3, 3, 2, 4, 2),
            arrow(17, Direction.RIGHT, 0, 7, 1, 8, 1)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(150, LevelData(
            levelNumber = 150,
            title = "City 8 • Route 10 • Compass Star",
            gridWidth = 12,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 4, 8, 3, 8, 2, 8, 2, 9, 2, 10, 2, 11),
            arrow(2, Direction.RIGHT, 1, 7, 1, 7, 0, 8, 0, 9, 0),
            arrow(3, Direction.RIGHT, 2, 8, 1, 8, 2, 8, 3, 9, 3, 10, 3),
            arrow(4, Direction.RIGHT, 3, 3, 12, 4, 12, 5, 12),
            arrow(5, Direction.RIGHT, 0, 1, 3, 2, 3, 3, 3, 4, 3, 5, 3),
            arrow(6, Direction.UP, 1, 1, 8, 1, 7),
            arrow(7, Direction.UP, 2, 9, 8, 9, 7),
            arrow(8, Direction.DOWN, 3, 7, 4, 7, 5),
            arrow(9, Direction.LEFT, 0, 2, 4, 1, 4, 0, 4),
            arrow(10, Direction.LEFT, 1, 10, 5, 9, 5, 8, 5),
            arrow(11, Direction.LEFT, 2, 5, 8, 5, 7, 4, 7, 3, 7),
            arrow(12, Direction.DOWN, 3, 6, 5, 6, 6, 6, 7, 6, 8, 6, 9),
            arrow(13, Direction.LEFT, 0, 10, 2, 9, 2),
            arrow(14, Direction.RIGHT, 1, 8, 9, 9, 9),
            arrow(15, Direction.DOWN, 2, 4, 1, 4, 2)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

    }

    private fun loadChunk4(map: MutableMap<Int, LevelData>) {
        map.put(151, LevelData(
            levelNumber = 151,
            title = "City 8 • Route 11 • Flywheel Path",
            gridWidth = 14,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 9, 4, 8, 4, 7, 4, 7, 3, 7, 2),
            arrow(2, Direction.UP, 1, 1, 7, 1, 6, 1, 5, 1, 4),
            arrow(3, Direction.LEFT, 2, 8, 10, 7, 10, 6, 10),
            arrow(4, Direction.RIGHT, 3, 6, 4, 6, 5, 7, 5, 8, 5),
            arrow(5, Direction.DOWN, 0, 6, 6, 6, 7, 6, 8),
            arrow(6, Direction.UP, 1, 11, 10, 10, 10, 9, 10, 9, 9, 9, 8, 9, 7),
            arrow(7, Direction.RIGHT, 2, 6, 12, 7, 12, 8, 12, 9, 12),
            arrow(8, Direction.RIGHT, 3, 10, 12, 11, 12, 12, 12),
            arrow(9, Direction.LEFT, 0, 5, 4, 5, 5, 4, 5, 3, 5),
            arrow(10, Direction.UP, 1, 12, 11, 12, 10),
            arrow(11, Direction.RIGHT, 2, 2, 1, 3, 1, 4, 1, 5, 1),
            arrow(12, Direction.LEFT, 3, 12, 1, 12, 2, 12, 3, 11, 3, 10, 3, 9, 3),
            arrow(13, Direction.LEFT, 0, 12, 8, 11, 8, 10, 8),
            arrow(14, Direction.DOWN, 1, 11, 7, 12, 7, 13, 7, 13, 8, 13, 9),
            arrow(15, Direction.DOWN, 2, 2, 10, 2, 11),
            arrow(16, Direction.DOWN, 3, 5, 8, 5, 9, 5, 10),
            arrow(17, Direction.RIGHT, 0, 7, 11, 8, 11, 9, 11, 10, 11),
            arrow(18, Direction.LEFT, 1, 8, 9, 7, 9),
            arrow(19, Direction.UP, 2, 4, 10, 4, 9, 4, 8),
            arrow(20, Direction.DOWN, 3, 3, 11, 3, 12, 3, 13)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(152, LevelData(
            levelNumber = 152,
            title = "City 8 • Route 12 • Hydraulic Gate",
            gridWidth = 14,
            gridHeight = 15,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 10, 10, 10, 11, 10, 12, 10, 13, 10, 14),
            arrow(2, Direction.UP, 1, 11, 7, 10, 7, 10, 6, 10, 5),
            arrow(3, Direction.RIGHT, 2, 1, 8, 2, 8, 3, 8, 4, 8),
            arrow(4, Direction.DOWN, 3, 12, 11, 12, 12, 12, 13),
            arrow(5, Direction.UP, 0, 2, 12, 2, 11, 2, 10),
            arrow(6, Direction.LEFT, 1, 4, 6, 4, 5, 3, 5, 2, 5),
            arrow(7, Direction.LEFT, 2, 1, 12, 0, 12),
            arrow(8, Direction.RIGHT, 3, 5, 4, 6, 4),
            arrow(9, Direction.UP, 0, 7, 10, 6, 10, 5, 10, 5, 9, 5, 8),
            arrow(10, Direction.DOWN, 1, 8, 4, 8, 5, 8, 6),
            arrow(11, Direction.LEFT, 2, 12, 3, 11, 3),
            arrow(12, Direction.LEFT, 3, 2, 3, 2, 2, 2, 1, 1, 1, 0, 1),
            arrow(13, Direction.UP, 0, 2, 4, 1, 4, 0, 4, 0, 3, 0, 2),
            arrow(14, Direction.LEFT, 1, 12, 2, 11, 2, 10, 2),
            arrow(15, Direction.UP, 2, 9, 1, 9, 0),
            arrow(16, Direction.UP, 3, 9, 7, 9, 6),
            arrow(17, Direction.RIGHT, 0, 4, 7, 5, 7, 6, 7),
            arrow(18, Direction.DOWN, 1, 6, 11, 7, 11, 8, 11, 8, 12, 8, 13),
            arrow(19, Direction.RIGHT, 2, 3, 1, 4, 1, 5, 1),
            arrow(20, Direction.LEFT, 3, 9, 2, 8, 2, 7, 2, 6, 2, 5, 2)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(153, LevelData(
            levelNumber = 153,
            title = "City 8 • Route 13 • Gimbal Trace",
            gridWidth = 11,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 3, 9, 3, 8, 3, 7),
            arrow(2, Direction.UP, 1, 3, 3, 4, 3, 4, 2, 4, 1),
            arrow(3, Direction.UP, 2, 3, 5, 2, 5, 1, 5, 1, 4, 1, 3),
            arrow(4, Direction.DOWN, 3, 4, 7, 5, 7, 5, 8, 5, 9),
            arrow(5, Direction.LEFT, 0, 9, 9, 8, 9, 7, 9, 6, 9),
            arrow(6, Direction.RIGHT, 1, 8, 1, 9, 1),
            arrow(7, Direction.DOWN, 2, 7, 2, 7, 3, 7, 4),
            arrow(8, Direction.LEFT, 3, 8, 5, 7, 5, 6, 5),
            arrow(9, Direction.LEFT, 0, 2, 8, 2, 7, 1, 7, 0, 7),
            arrow(10, Direction.RIGHT, 1, 8, 4, 9, 4),
            arrow(11, Direction.LEFT, 2, 9, 6, 8, 6, 7, 6),
            arrow(12, Direction.LEFT, 3, 6, 4, 5, 4, 4, 4, 3, 4),
            arrow(13, Direction.RIGHT, 0, 2, 2, 2, 1, 2, 0, 3, 0, 4, 0, 5, 0),
            arrow(14, Direction.UP, 1, 7, 1, 7, 0),
            arrow(15, Direction.LEFT, 2, 2, 6, 1, 6, 0, 6),
            arrow(16, Direction.RIGHT, 3, 6, 7, 7, 7, 8, 7, 9, 7, 10, 7)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(154, LevelData(
            levelNumber = 154,
            title = "City 8 • Route 14 • Turret Maze",
            gridWidth = 14,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 1, 5, 2, 5, 3, 5, 4, 5, 5, 5),
            arrow(2, Direction.RIGHT, 1, 10, 5, 10, 6, 10, 7, 11, 7, 12, 7),
            arrow(3, Direction.RIGHT, 2, 5, 11, 5, 10, 5, 9, 6, 9, 7, 9),
            arrow(4, Direction.LEFT, 3, 9, 4, 8, 4, 7, 4, 6, 4, 5, 4),
            arrow(5, Direction.RIGHT, 0, 6, 3, 6, 2, 6, 1, 7, 1, 8, 1),
            arrow(6, Direction.LEFT, 1, 5, 1, 5, 0, 4, 0, 3, 0),
            arrow(7, Direction.LEFT, 2, 10, 2, 9, 2, 8, 2),
            arrow(8, Direction.LEFT, 3, 11, 3, 10, 3, 9, 3, 8, 3, 7, 3),
            arrow(9, Direction.UP, 0, 11, 8, 10, 8, 9, 8, 9, 7, 9, 6),
            arrow(10, Direction.LEFT, 1, 11, 11, 10, 11, 9, 11, 8, 11, 7, 11),
            arrow(11, Direction.RIGHT, 2, 10, 1, 11, 1),
            arrow(12, Direction.RIGHT, 3, 7, 10, 8, 10, 9, 10, 10, 10, 11, 10),
            arrow(13, Direction.DOWN, 0, 3, 9, 3, 10, 3, 11, 3, 12),
            arrow(14, Direction.RIGHT, 1, 5, 6, 6, 6, 7, 6, 8, 6),
            arrow(15, Direction.UP, 2, 8, 9, 8, 8, 8, 7),
            arrow(16, Direction.RIGHT, 3, 1, 6, 2, 6, 3, 6),
            arrow(17, Direction.UP, 0, 3, 4, 4, 4, 4, 3, 4, 2),
            arrow(18, Direction.LEFT, 1, 5, 7, 4, 7, 3, 7, 2, 7),
            arrow(19, Direction.DOWN, 2, 6, 7, 6, 8),
            arrow(20, Direction.RIGHT, 3, 10, 4, 11, 4, 12, 4)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(155, LevelData(
            levelNumber = 155,
            title = "City 8 • Route 15 • Interlock Gear",
            gridWidth = 12,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 4, 12, 5, 12, 6, 12, 7, 12, 8, 12),
            arrow(2, Direction.DOWN, 1, 6, 2, 6, 3, 6, 4),
            arrow(3, Direction.LEFT, 2, 10, 7, 9, 7, 8, 7, 7, 7),
            arrow(4, Direction.DOWN, 3, 6, 8, 6, 9),
            arrow(5, Direction.RIGHT, 0, 3, 6, 3, 7, 3, 8, 4, 8, 5, 8),
            arrow(6, Direction.RIGHT, 1, 2, 10, 3, 10, 4, 10, 5, 10, 6, 10),
            arrow(7, Direction.DOWN, 2, 4, 1, 4, 2, 4, 3, 4, 4, 4, 5),
            arrow(8, Direction.LEFT, 3, 3, 3, 3, 2, 2, 2, 1, 2),
            arrow(9, Direction.DOWN, 0, 8, 8, 8, 9, 8, 10),
            arrow(10, Direction.UP, 1, 10, 5, 10, 4, 10, 3),
            arrow(11, Direction.DOWN, 2, 9, 9, 10, 9, 11, 9, 11, 10, 11, 11, 11, 12),
            arrow(12, Direction.RIGHT, 3, 10, 2, 11, 2),
            arrow(13, Direction.UP, 0, 8, 6, 8, 5),
            arrow(14, Direction.UP, 1, 7, 3, 7, 2, 7, 1)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(156, LevelData(
            levelNumber = 156,
            title = "City 8 • Route 16 • Pressure Tube",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 10, 6, 11, 6),
            arrow(2, Direction.UP, 1, 1, 4, 1, 3, 1, 2, 1, 1),
            arrow(3, Direction.UP, 2, 11, 5, 11, 4),
            arrow(4, Direction.LEFT, 3, 10, 8, 10, 9, 10, 10, 9, 10, 8, 10, 7, 10),
            arrow(5, Direction.RIGHT, 0, 10, 1, 11, 1),
            arrow(6, Direction.UP, 1, 9, 6, 9, 5),
            arrow(7, Direction.UP, 2, 5, 10, 5, 9, 5, 8, 5, 7, 5, 6),
            arrow(8, Direction.UP, 3, 2, 4, 2, 3),
            arrow(9, Direction.DOWN, 0, 7, 7, 7, 8),
            arrow(10, Direction.LEFT, 1, 3, 9, 3, 8, 3, 7, 2, 7, 1, 7),
            arrow(11, Direction.RIGHT, 2, 8, 2, 8, 3, 8, 4, 9, 4, 10, 4),
            arrow(12, Direction.DOWN, 3, 6, 7, 6, 8, 6, 9, 6, 10, 6, 11),
            arrow(13, Direction.UP, 0, 1, 9, 1, 8),
            arrow(14, Direction.LEFT, 1, 7, 4, 7, 3, 7, 2, 6, 2, 5, 2),
            arrow(15, Direction.RIGHT, 2, 5, 3, 5, 4, 5, 5, 6, 5, 7, 5)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(157, LevelData(
            levelNumber = 157,
            title = "City 8 • Route 17 • Mainspring Run",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 6, 4, 7, 4, 8, 4, 9, 4),
            arrow(2, Direction.DOWN, 1, 9, 9, 8, 9, 8, 10, 8, 11),
            arrow(3, Direction.LEFT, 2, 4, 8, 3, 8, 2, 8, 1, 8, 0, 8),
            arrow(4, Direction.LEFT, 3, 3, 10, 2, 10, 1, 10),
            arrow(5, Direction.RIGHT, 0, 8, 8, 8, 7, 8, 6, 9, 6, 10, 6),
            arrow(6, Direction.DOWN, 1, 5, 1, 6, 1, 6, 2, 6, 3),
            arrow(7, Direction.RIGHT, 2, 9, 8, 10, 8, 11, 8),
            arrow(8, Direction.RIGHT, 3, 1, 7, 2, 7, 3, 7, 4, 7, 5, 7),
            arrow(9, Direction.DOWN, 0, 4, 4, 4, 5, 4, 6),
            arrow(10, Direction.UP, 1, 5, 6, 5, 5, 5, 4),
            arrow(11, Direction.DOWN, 2, 2, 2, 2, 3, 2, 4, 2, 5, 2, 6),
            arrow(12, Direction.DOWN, 3, 10, 3, 11, 3, 11, 4, 11, 5),
            arrow(13, Direction.UP, 0, 7, 3, 7, 2),
            arrow(14, Direction.DOWN, 1, 6, 11, 6, 12),
            arrow(15, Direction.LEFT, 2, 10, 2, 9, 2, 8, 2),
            arrow(16, Direction.LEFT, 3, 10, 5, 9, 5),
            arrow(17, Direction.UP, 0, 5, 10, 6, 10, 7, 10, 7, 9, 7, 8),
            arrow(18, Direction.UP, 1, 3, 5, 3, 4, 3, 3)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(158, LevelData(
            levelNumber = 158,
            title = "City 8 • Route 18 • Escapement",
            gridWidth = 14,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 6, 7, 7, 7, 8, 7, 9, 7, 10, 7),
            arrow(2, Direction.LEFT, 1, 4, 6, 3, 6),
            arrow(3, Direction.RIGHT, 2, 1, 7, 2, 7, 3, 7, 4, 7, 5, 7),
            arrow(4, Direction.LEFT, 3, 12, 3, 12, 2, 12, 1, 11, 1, 10, 1),
            arrow(5, Direction.RIGHT, 0, 3, 8, 4, 8, 5, 8, 6, 8),
            arrow(6, Direction.RIGHT, 1, 8, 11, 9, 11, 10, 11),
            arrow(7, Direction.RIGHT, 2, 10, 8, 10, 9, 10, 10, 11, 10, 12, 10),
            arrow(8, Direction.UP, 3, 4, 5, 3, 5, 2, 5, 2, 4, 2, 3),
            arrow(9, Direction.LEFT, 0, 5, 11, 4, 11, 3, 11),
            arrow(10, Direction.LEFT, 1, 9, 10, 9, 9, 8, 9, 7, 9),
            arrow(11, Direction.LEFT, 2, 11, 2, 10, 2, 9, 2, 8, 2),
            arrow(12, Direction.DOWN, 3, 7, 3, 6, 3, 5, 3, 5, 4, 5, 5, 5, 6),
            arrow(13, Direction.UP, 0, 4, 4, 4, 3),
            arrow(14, Direction.UP, 1, 9, 6, 9, 5, 9, 4, 9, 3),
            arrow(15, Direction.DOWN, 2, 11, 11, 11, 12),
            arrow(16, Direction.DOWN, 3, 6, 1, 6, 2),
            arrow(17, Direction.UP, 0, 4, 1, 4, 0),
            arrow(18, Direction.LEFT, 1, 12, 4, 11, 4)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(159, LevelData(
            levelNumber = 159,
            title = "City 8 • Route 19 • Chronometer",
            gridWidth = 12,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 4, 6, 5, 6),
            arrow(2, Direction.UP, 1, 7, 3, 7, 2, 7, 1),
            arrow(3, Direction.RIGHT, 2, 1, 9, 2, 9),
            arrow(4, Direction.RIGHT, 3, 8, 5, 8, 4, 8, 3, 9, 3, 10, 3),
            arrow(5, Direction.RIGHT, 0, 1, 8, 1, 7, 1, 6, 2, 6, 3, 6),
            arrow(6, Direction.RIGHT, 1, 10, 7, 11, 7),
            arrow(7, Direction.RIGHT, 2, 8, 6, 9, 6),
            arrow(8, Direction.UP, 3, 3, 1, 3, 0),
            arrow(9, Direction.DOWN, 0, 10, 5, 10, 6),
            arrow(10, Direction.UP, 1, 7, 8, 7, 7),
            arrow(11, Direction.LEFT, 2, 5, 7, 4, 7, 3, 7, 2, 7),
            arrow(12, Direction.DOWN, 3, 6, 2, 6, 3),
            arrow(13, Direction.DOWN, 0, 8, 7, 8, 8),
            arrow(14, Direction.LEFT, 1, 6, 8, 5, 8, 4, 8)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(160, LevelData(
            levelNumber = 160,
            title = "City 8 • Route 20 • The Grand Gear",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 6, 10, 6, 9, 6, 8, 6, 7),
            arrow(2, Direction.DOWN, 1, 3, 3, 2, 3, 1, 3, 1, 4, 1, 5),
            arrow(3, Direction.LEFT, 2, 10, 10, 9, 10, 8, 10),
            arrow(4, Direction.DOWN, 3, 8, 4, 8, 5, 8, 6, 8, 7),
            arrow(5, Direction.RIGHT, 0, 5, 1, 6, 1),
            arrow(6, Direction.DOWN, 1, 10, 5, 10, 6, 10, 7, 10, 8),
            arrow(7, Direction.DOWN, 2, 5, 9, 5, 10, 5, 11),
            arrow(8, Direction.LEFT, 3, 10, 1, 10, 0, 9, 0, 8, 0),
            arrow(9, Direction.LEFT, 0, 6, 3, 6, 2, 5, 2, 4, 2),
            arrow(10, Direction.RIGHT, 1, 2, 10, 2, 11, 2, 12, 3, 12, 4, 12),
            arrow(11, Direction.RIGHT, 2, 7, 1, 8, 1),
            arrow(12, Direction.DOWN, 3, 3, 5, 3, 6),
            arrow(13, Direction.UP, 0, 3, 2, 2, 2, 1, 2, 1, 1, 1, 0),
            arrow(14, Direction.LEFT, 1, 2, 6, 1, 6),
            arrow(15, Direction.LEFT, 2, 10, 4, 10, 3, 10, 2, 9, 2, 8, 2)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(161, LevelData(
            levelNumber = 161,
            title = "City 9 • Route 1 • Dawn Ray",
            gridWidth = 15,
            gridHeight = 15,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 10, 5, 10, 6, 10, 7, 10, 8),
            arrow(2, Direction.LEFT, 1, 13, 10, 13, 11, 13, 12, 12, 12, 11, 12, 10, 12),
            arrow(3, Direction.RIGHT, 2, 3, 8, 3, 7, 3, 6, 4, 6, 5, 6, 6, 6),
            arrow(4, Direction.LEFT, 3, 7, 8, 6, 8),
            arrow(5, Direction.UP, 0, 5, 10, 5, 9),
            arrow(6, Direction.LEFT, 1, 3, 2, 2, 2),
            arrow(7, Direction.RIGHT, 2, 7, 3, 8, 3, 9, 3, 10, 3),
            arrow(8, Direction.LEFT, 3, 4, 12, 3, 12),
            arrow(9, Direction.RIGHT, 0, 3, 4, 3, 5, 4, 5, 5, 5),
            arrow(10, Direction.UP, 1, 1, 9, 1, 8, 1, 7, 1, 6),
            arrow(11, Direction.UP, 2, 1, 2, 1, 1, 1, 0),
            arrow(12, Direction.UP, 3, 2, 13, 2, 12, 2, 11, 2, 10),
            arrow(13, Direction.DOWN, 0, 9, 6, 9, 7, 9, 8),
            arrow(14, Direction.RIGHT, 1, 13, 8, 14, 8),
            arrow(15, Direction.UP, 2, 2, 9, 2, 8, 2, 7),
            arrow(16, Direction.DOWN, 3, 8, 13, 8, 14),
            arrow(17, Direction.LEFT, 0, 8, 7, 7, 7, 6, 7, 5, 7),
            arrow(18, Direction.LEFT, 1, 6, 11, 5, 11, 4, 11)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(162, LevelData(
            levelNumber = 162,
            title = "City 9 • Route 2 • Gleam",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 7, 4, 8, 4, 9, 4),
            arrow(2, Direction.RIGHT, 1, 8, 6, 8, 5, 9, 5, 10, 5),
            arrow(3, Direction.LEFT, 2, 8, 3, 7, 3),
            arrow(4, Direction.DOWN, 3, 6, 2, 6, 3),
            arrow(5, Direction.LEFT, 0, 7, 5, 6, 5, 5, 5, 4, 5),
            arrow(6, Direction.RIGHT, 1, 5, 10, 6, 10, 7, 10),
            arrow(7, Direction.UP, 2, 3, 2, 3, 1),
            arrow(8, Direction.RIGHT, 3, 1, 9, 2, 9),
            arrow(9, Direction.DOWN, 0, 4, 1, 4, 2),
            arrow(10, Direction.UP, 1, 10, 2, 10, 1),
            arrow(11, Direction.RIGHT, 2, 4, 8, 5, 8),
            arrow(12, Direction.DOWN, 3, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7),
            arrow(13, Direction.RIGHT, 0, 3, 4, 4, 4, 5, 4),
            arrow(14, Direction.DOWN, 1, 9, 6, 9, 7, 9, 8, 9, 9),
            arrow(15, Direction.LEFT, 2, 8, 7, 7, 7, 6, 7),
            arrow(16, Direction.DOWN, 3, 7, 1, 7, 2)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(163, LevelData(
            levelNumber = 163,
            title = "City 9 • Route 3 • Beaming Path",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 10, 2, 10, 3, 10, 4),
            arrow(2, Direction.UP, 1, 9, 10, 9, 9, 9, 8, 9, 7, 9, 6),
            arrow(3, Direction.DOWN, 2, 4, 1, 4, 2, 4, 3, 4, 4),
            arrow(4, Direction.RIGHT, 3, 5, 10, 6, 10, 7, 10),
            arrow(5, Direction.RIGHT, 0, 5, 2, 6, 2, 7, 2, 8, 2, 9, 2),
            arrow(6, Direction.LEFT, 1, 10, 5, 9, 5, 8, 5, 7, 5, 6, 5),
            arrow(7, Direction.LEFT, 2, 7, 8, 7, 7, 7, 6, 6, 6, 5, 6, 4, 6),
            arrow(8, Direction.LEFT, 3, 1, 8, 0, 8),
            arrow(9, Direction.DOWN, 0, 3, 7, 3, 8, 3, 9, 3, 10),
            arrow(10, Direction.DOWN, 1, 1, 1, 2, 1, 3, 1, 3, 2, 3, 3),
            arrow(11, Direction.DOWN, 2, 8, 7, 8, 8, 8, 9),
            arrow(12, Direction.UP, 3, 2, 7, 2, 6, 2, 5, 2, 4),
            arrow(13, Direction.LEFT, 0, 6, 3, 5, 3),
            arrow(14, Direction.LEFT, 1, 1, 9, 0, 9),
            arrow(15, Direction.RIGHT, 2, 2, 10, 2, 11, 3, 11, 4, 11),
            arrow(16, Direction.DOWN, 3, 1, 10, 1, 11)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(164, LevelData(
            levelNumber = 164,
            title = "City 9 • Route 4 • Luminous Lane",
            gridWidth = 13,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 5, 3, 6, 3, 7, 3, 8, 3),
            arrow(2, Direction.RIGHT, 1, 7, 1, 8, 1, 9, 1, 10, 1, 11, 1),
            arrow(3, Direction.DOWN, 2, 1, 6, 1, 7),
            arrow(4, Direction.DOWN, 3, 2, 3, 2, 4, 2, 5, 2, 6),
            arrow(5, Direction.UP, 0, 9, 5, 9, 4, 9, 3, 9, 2),
            arrow(6, Direction.LEFT, 1, 4, 6, 3, 6),
            arrow(7, Direction.RIGHT, 2, 6, 5, 7, 5),
            arrow(8, Direction.DOWN, 3, 3, 2, 3, 3, 3, 4, 3, 5),
            arrow(9, Direction.UP, 0, 11, 5, 11, 4),
            arrow(10, Direction.RIGHT, 1, 2, 2, 2, 1, 2, 0, 3, 0, 4, 0),
            arrow(11, Direction.RIGHT, 2, 2, 9, 3, 9),
            arrow(12, Direction.DOWN, 3, 11, 8, 11, 9),
            arrow(13, Direction.LEFT, 0, 7, 2, 6, 2, 5, 2, 4, 2),
            arrow(14, Direction.RIGHT, 1, 5, 9, 6, 9),
            arrow(15, Direction.LEFT, 2, 7, 4, 6, 4, 5, 4, 4, 4),
            arrow(16, Direction.UP, 3, 8, 6, 8, 5),
            arrow(17, Direction.RIGHT, 0, 9, 8, 10, 8),
            arrow(18, Direction.DOWN, 1, 5, 7, 4, 7, 4, 8, 4, 9)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(165, LevelData(
            levelNumber = 165,
            title = "City 9 • Route 5 • First Light",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 4, 2, 4, 3),
            arrow(2, Direction.RIGHT, 1, 5, 11, 6, 11, 7, 11, 8, 11, 9, 11),
            arrow(3, Direction.DOWN, 2, 6, 8, 6, 9, 6, 10),
            arrow(4, Direction.LEFT, 3, 6, 6, 5, 6),
            arrow(5, Direction.RIGHT, 0, 1, 3, 1, 2, 2, 2, 3, 2),
            arrow(6, Direction.UP, 1, 10, 6, 10, 5, 10, 4),
            arrow(7, Direction.UP, 2, 8, 3, 8, 2),
            arrow(8, Direction.LEFT, 3, 4, 10, 4, 9, 4, 8, 3, 8, 2, 8, 1, 8),
            arrow(9, Direction.RIGHT, 0, 4, 6, 4, 5, 4, 4, 5, 4, 6, 4, 7, 4),
            arrow(10, Direction.RIGHT, 1, 3, 1, 4, 1),
            arrow(11, Direction.LEFT, 2, 3, 11, 2, 11),
            arrow(12, Direction.RIGHT, 3, 9, 9, 9, 8, 10, 8, 11, 8),
            arrow(13, Direction.LEFT, 0, 8, 9, 8, 8, 8, 7, 7, 7, 6, 7, 5, 7),
            arrow(14, Direction.RIGHT, 1, 9, 10, 10, 10),
            arrow(15, Direction.LEFT, 2, 10, 7, 9, 7),
            arrow(16, Direction.LEFT, 3, 3, 7, 2, 7, 1, 7, 0, 7),
            arrow(17, Direction.UP, 0, 2, 10, 2, 9),
            arrow(18, Direction.DOWN, 1, 9, 1, 9, 2, 9, 3, 9, 4, 9, 5),
            arrow(19, Direction.RIGHT, 2, 5, 1, 5, 0, 6, 0, 7, 0),
            arrow(20, Direction.DOWN, 3, 3, 5, 3, 6)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(166, LevelData(
            levelNumber = 166,
            title = "City 9 • Route 6 • Halo Ring",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 11, 6, 10, 6),
            arrow(2, Direction.DOWN, 1, 2, 1, 2, 2, 2, 3),
            arrow(3, Direction.RIGHT, 2, 3, 3, 4, 3, 5, 3),
            arrow(4, Direction.UP, 3, 7, 6, 7, 5, 7, 4, 7, 3, 7, 2),
            arrow(5, Direction.LEFT, 0, 5, 4, 4, 4),
            arrow(6, Direction.UP, 1, 9, 5, 9, 4, 9, 3),
            arrow(7, Direction.LEFT, 2, 4, 8, 3, 8, 2, 8, 1, 8),
            arrow(8, Direction.RIGHT, 3, 1, 5, 2, 5),
            arrow(9, Direction.LEFT, 0, 5, 7, 4, 7),
            arrow(10, Direction.UP, 1, 10, 10, 10, 9, 10, 8, 10, 7),
            arrow(11, Direction.RIGHT, 2, 10, 3, 11, 3),
            arrow(12, Direction.UP, 3, 1, 7, 2, 7, 3, 7, 3, 6, 3, 5, 3, 4),
            arrow(13, Direction.RIGHT, 0, 7, 9, 8, 9, 9, 9),
            arrow(14, Direction.UP, 1, 8, 7, 8, 6),
            arrow(15, Direction.RIGHT, 2, 4, 9, 5, 9),
            arrow(16, Direction.LEFT, 3, 11, 1, 11, 0, 10, 0, 9, 0),
            arrow(17, Direction.RIGHT, 0, 3, 10, 3, 11, 4, 11, 5, 11),
            arrow(18, Direction.UP, 1, 5, 2, 5, 1)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(167, LevelData(
            levelNumber = 167,
            title = "City 9 • Route 7 • Prism Beam",
            gridWidth = 14,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 7, 9, 7, 8, 8, 8, 9, 8),
            arrow(2, Direction.DOWN, 1, 11, 6, 11, 7, 11, 8),
            arrow(3, Direction.RIGHT, 2, 4, 6, 5, 6),
            arrow(4, Direction.DOWN, 3, 1, 8, 1, 9),
            arrow(5, Direction.RIGHT, 0, 8, 9, 9, 9),
            arrow(6, Direction.UP, 1, 5, 4, 5, 3, 5, 2),
            arrow(7, Direction.DOWN, 2, 12, 8, 13, 8, 13, 9, 13, 10),
            arrow(8, Direction.UP, 3, 10, 9, 10, 8),
            arrow(9, Direction.UP, 0, 11, 2, 12, 2, 12, 1, 12, 0),
            arrow(10, Direction.LEFT, 1, 8, 7, 7, 7),
            arrow(11, Direction.UP, 2, 3, 3, 3, 2, 3, 1, 3, 0),
            arrow(12, Direction.LEFT, 3, 9, 5, 8, 5, 7, 5, 6, 5, 5, 5),
            arrow(13, Direction.DOWN, 0, 10, 10, 10, 11),
            arrow(14, Direction.DOWN, 1, 3, 8, 3, 9, 3, 10, 3, 11),
            arrow(15, Direction.DOWN, 2, 2, 1, 2, 2),
            arrow(16, Direction.LEFT, 3, 10, 7, 9, 7),
            arrow(17, Direction.UP, 0, 6, 10, 6, 9, 6, 8, 6, 7),
            arrow(18, Direction.DOWN, 1, 1, 3, 1, 4, 1, 5, 1, 6),
            arrow(19, Direction.DOWN, 2, 4, 3, 4, 4, 4, 5)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(168, LevelData(
            levelNumber = 168,
            title = "City 9 • Route 8 • Spectral Trace",
            gridWidth = 11,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 7, 1, 6, 1, 5, 1, 4, 1, 3, 1),
            arrow(2, Direction.UP, 1, 4, 4, 4, 3),
            arrow(3, Direction.DOWN, 2, 1, 4, 2, 4, 2, 5, 2, 6),
            arrow(4, Direction.LEFT, 3, 5, 2, 4, 2, 3, 2, 2, 2),
            arrow(5, Direction.UP, 0, 3, 6, 3, 5, 3, 4, 3, 3),
            arrow(6, Direction.RIGHT, 1, 4, 9, 4, 8, 4, 7, 5, 7, 6, 7, 7, 7),
            arrow(7, Direction.DOWN, 2, 7, 2, 7, 3, 7, 4, 7, 5, 7, 6),
            arrow(8, Direction.RIGHT, 3, 9, 1, 10, 1),
            arrow(9, Direction.DOWN, 0, 9, 3, 9, 4),
            arrow(10, Direction.DOWN, 1, 1, 1, 1, 2),
            arrow(11, Direction.DOWN, 2, 5, 3, 6, 3, 6, 4, 6, 5),
            arrow(12, Direction.DOWN, 3, 1, 7, 1, 8),
            arrow(13, Direction.RIGHT, 0, 8, 9, 9, 9, 10, 9),
            arrow(14, Direction.DOWN, 1, 1, 5, 1, 6),
            arrow(15, Direction.LEFT, 2, 2, 8, 2, 9, 2, 10, 1, 10, 0, 10),
            arrow(16, Direction.LEFT, 3, 8, 8, 7, 8),
            arrow(17, Direction.LEFT, 0, 6, 6, 5, 6)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(169, LevelData(
            levelNumber = 169,
            title = "City 9 • Route 9 • Strobe Flow",
            gridWidth = 11,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 7, 2, 7, 3),
            arrow(2, Direction.LEFT, 1, 4, 9, 3, 9),
            arrow(3, Direction.UP, 2, 2, 3, 2, 2, 2, 1),
            arrow(4, Direction.DOWN, 3, 2, 5, 2, 6, 2, 7, 2, 8, 2, 9),
            arrow(5, Direction.LEFT, 0, 6, 5, 5, 5),
            arrow(6, Direction.DOWN, 1, 1, 3, 1, 4, 1, 5, 1, 6),
            arrow(7, Direction.RIGHT, 2, 5, 6, 6, 6),
            arrow(8, Direction.DOWN, 3, 9, 1, 9, 2),
            arrow(9, Direction.RIGHT, 0, 8, 8, 9, 8),
            arrow(10, Direction.LEFT, 1, 5, 8, 5, 9, 5, 10, 4, 10, 3, 10, 2, 10),
            arrow(11, Direction.LEFT, 2, 7, 6, 7, 5, 7, 4, 6, 4, 5, 4, 4, 4),
            arrow(12, Direction.DOWN, 3, 4, 5, 4, 6, 4, 7),
            arrow(13, Direction.DOWN, 0, 7, 8, 7, 9),
            arrow(14, Direction.LEFT, 1, 6, 1, 5, 1),
            arrow(15, Direction.RIGHT, 2, 7, 1, 8, 1),
            arrow(16, Direction.DOWN, 3, 3, 2, 3, 3, 3, 4, 3, 5),
            arrow(17, Direction.DOWN, 0, 8, 4, 9, 4, 10, 4, 10, 5, 10, 6, 10, 7)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(170, LevelData(
            levelNumber = 170,
            title = "City 9 • Route 10 • Solar Radiance",
            gridWidth = 16,
            gridHeight = 16,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 13, 10, 13, 11, 12, 11, 11, 11),
            arrow(2, Direction.DOWN, 1, 6, 10, 6, 11, 6, 12),
            arrow(3, Direction.RIGHT, 2, 4, 8, 5, 8, 6, 8, 7, 8, 8, 8),
            arrow(4, Direction.DOWN, 3, 7, 1, 6, 1, 5, 1, 5, 2, 5, 3),
            arrow(5, Direction.LEFT, 0, 14, 14, 14, 15, 13, 15, 12, 15),
            arrow(6, Direction.DOWN, 1, 11, 8, 11, 9),
            arrow(7, Direction.UP, 2, 3, 8, 2, 8, 1, 8, 1, 7, 1, 6),
            arrow(8, Direction.LEFT, 3, 8, 2, 7, 2),
            arrow(9, Direction.UP, 0, 8, 10, 8, 9),
            arrow(10, Direction.LEFT, 1, 3, 11, 2, 11),
            arrow(11, Direction.UP, 2, 9, 13, 9, 12),
            arrow(12, Direction.RIGHT, 3, 10, 14, 11, 14, 12, 14),
            arrow(13, Direction.RIGHT, 0, 7, 7, 8, 7, 9, 7, 10, 7),
            arrow(14, Direction.DOWN, 1, 5, 9, 5, 10),
            arrow(15, Direction.DOWN, 2, 11, 3, 11, 4),
            arrow(16, Direction.RIGHT, 3, 6, 13, 7, 13, 8, 13),
            arrow(17, Direction.UP, 0, 8, 5, 8, 4)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(171, LevelData(
            levelNumber = 171,
            title = "City 9 • Route 11 • Photon Gate",
            gridWidth = 15,
            gridHeight = 15,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 8, 6, 8, 5),
            arrow(2, Direction.LEFT, 1, 7, 4, 6, 4, 5, 4, 4, 4, 3, 4),
            arrow(3, Direction.DOWN, 2, 5, 5, 5, 6),
            arrow(4, Direction.LEFT, 3, 4, 5, 3, 5, 2, 5),
            arrow(5, Direction.LEFT, 0, 12, 3, 12, 4, 12, 5, 11, 5, 10, 5, 9, 5),
            arrow(6, Direction.LEFT, 1, 4, 6, 3, 6, 2, 6),
            arrow(7, Direction.LEFT, 2, 5, 7, 4, 7),
            arrow(8, Direction.RIGHT, 3, 11, 11, 12, 11),
            arrow(9, Direction.LEFT, 0, 4, 3, 3, 3, 2, 3),
            arrow(10, Direction.LEFT, 1, 11, 2, 11, 3, 11, 4, 10, 4, 9, 4, 8, 4),
            arrow(11, Direction.DOWN, 2, 3, 12, 3, 13),
            arrow(12, Direction.UP, 3, 2, 7, 1, 7, 1, 6, 1, 5),
            arrow(13, Direction.LEFT, 0, 7, 10, 7, 11, 7, 12, 6, 12, 5, 12, 4, 12),
            arrow(14, Direction.DOWN, 1, 13, 7, 13, 8, 13, 9, 13, 10),
            arrow(15, Direction.RIGHT, 2, 1, 1, 1, 2, 2, 2, 3, 2),
            arrow(16, Direction.RIGHT, 3, 4, 10, 4, 9, 5, 9, 6, 9),
            arrow(17, Direction.RIGHT, 0, 13, 5, 14, 5),
            arrow(18, Direction.UP, 1, 11, 7, 11, 6),
            arrow(19, Direction.LEFT, 2, 2, 4, 1, 4, 0, 4),
            arrow(20, Direction.LEFT, 3, 12, 13, 11, 13, 10, 13, 9, 13),
            arrow(21, Direction.DOWN, 0, 9, 9, 9, 10, 9, 11)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(172, LevelData(
            levelNumber = 172,
            title = "City 9 • Route 12 • Corona Way",
            gridWidth = 14,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 1, 7, 1, 8, 1, 9, 1, 10, 1, 11),
            arrow(2, Direction.LEFT, 1, 5, 6, 4, 6, 3, 6, 2, 6, 1, 6),
            arrow(3, Direction.LEFT, 2, 4, 5, 3, 5),
            arrow(4, Direction.LEFT, 3, 9, 3, 9, 2, 8, 2, 7, 2),
            arrow(5, Direction.DOWN, 0, 7, 3, 6, 3, 5, 3, 5, 4, 5, 5),
            arrow(6, Direction.UP, 1, 11, 5, 11, 4),
            arrow(7, Direction.LEFT, 2, 7, 8, 6, 8, 5, 8),
            arrow(8, Direction.UP, 3, 4, 3, 4, 2),
            arrow(9, Direction.RIGHT, 0, 10, 7, 11, 7),
            arrow(10, Direction.RIGHT, 1, 2, 10, 2, 11, 3, 11, 4, 11),
            arrow(11, Direction.UP, 2, 9, 10, 9, 9),
            arrow(12, Direction.RIGHT, 3, 1, 4, 2, 4, 3, 4),
            arrow(13, Direction.LEFT, 0, 7, 10, 6, 10, 5, 10, 4, 10),
            arrow(14, Direction.DOWN, 1, 11, 1, 10, 1, 10, 2, 10, 3),
            arrow(15, Direction.RIGHT, 2, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1),
            arrow(16, Direction.LEFT, 3, 11, 8, 10, 8, 9, 8, 8, 8)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(173, LevelData(
            levelNumber = 173,
            title = "City 9 • Route 13 • Incandescent Maze",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 1, 2, 1, 3, 2, 3, 3, 3),
            arrow(2, Direction.LEFT, 1, 5, 7, 4, 7),
            arrow(3, Direction.UP, 2, 7, 5, 6, 5, 6, 4, 6, 3),
            arrow(4, Direction.RIGHT, 3, 6, 11, 6, 12, 7, 12, 8, 12),
            arrow(5, Direction.UP, 0, 9, 6, 9, 5),
            arrow(6, Direction.RIGHT, 1, 2, 9, 3, 9, 4, 9, 5, 9, 6, 9),
            arrow(7, Direction.LEFT, 2, 5, 8, 4, 8),
            arrow(8, Direction.LEFT, 3, 9, 8, 8, 8),
            arrow(9, Direction.RIGHT, 0, 8, 11, 9, 11),
            arrow(10, Direction.UP, 1, 7, 3, 7, 2, 7, 1, 7, 0),
            arrow(11, Direction.UP, 2, 10, 9, 10, 8, 10, 7, 10, 6),
            arrow(12, Direction.DOWN, 3, 3, 6, 3, 7, 3, 8),
            arrow(13, Direction.RIGHT, 0, 11, 3, 12, 3),
            arrow(14, Direction.DOWN, 1, 11, 8, 11, 9),
            arrow(15, Direction.LEFT, 2, 11, 2, 11, 1, 10, 1, 9, 1),
            arrow(16, Direction.LEFT, 3, 3, 4, 2, 4, 1, 4)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(174, LevelData(
            levelNumber = 174,
            title = "City 9 • Route 14 • Glow Arbor",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 4, 5, 5, 5, 6, 5, 6, 6, 6, 7, 6, 8),
            arrow(2, Direction.LEFT, 1, 1, 9, 0, 9),
            arrow(3, Direction.UP, 2, 3, 3, 3, 2),
            arrow(4, Direction.UP, 3, 9, 2, 10, 2, 10, 1, 10, 0),
            arrow(5, Direction.DOWN, 0, 8, 8, 9, 8, 10, 8, 10, 9, 10, 10),
            arrow(6, Direction.DOWN, 1, 3, 1, 2, 1, 2, 2, 2, 3),
            arrow(7, Direction.RIGHT, 2, 3, 8, 4, 8),
            arrow(8, Direction.LEFT, 3, 10, 4, 9, 4, 8, 4),
            arrow(9, Direction.UP, 0, 1, 3, 1, 2, 1, 1, 1, 0),
            arrow(10, Direction.DOWN, 1, 7, 1, 6, 1, 5, 1, 5, 2, 5, 3, 5, 4),
            arrow(11, Direction.LEFT, 2, 10, 11, 9, 11, 8, 11),
            arrow(12, Direction.LEFT, 3, 6, 11, 5, 11, 4, 11, 3, 11, 2, 11),
            arrow(13, Direction.DOWN, 0, 2, 4, 2, 5, 2, 6, 2, 7, 2, 8),
            arrow(14, Direction.RIGHT, 1, 2, 9, 3, 9, 4, 9, 5, 9, 6, 9),
            arrow(15, Direction.DOWN, 2, 4, 2, 4, 3),
            arrow(16, Direction.LEFT, 3, 9, 7, 8, 7),
            arrow(17, Direction.DOWN, 0, 8, 2, 7, 2, 7, 3, 7, 4),
            arrow(18, Direction.LEFT, 1, 5, 10, 4, 10),
            arrow(19, Direction.RIGHT, 2, 6, 10, 7, 10, 8, 10)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(175, LevelData(
            levelNumber = 175,
            title = "City 9 • Route 15 • Light Tree",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 9, 8, 9, 7),
            arrow(2, Direction.LEFT, 1, 7, 11, 7, 12, 7, 13, 6, 13, 5, 13),
            arrow(3, Direction.DOWN, 2, 2, 11, 2, 12, 2, 13),
            arrow(4, Direction.DOWN, 3, 10, 4, 10, 5, 10, 6, 10, 7),
            arrow(5, Direction.LEFT, 0, 9, 5, 8, 5, 7, 5),
            arrow(6, Direction.LEFT, 1, 3, 10, 2, 10),
            arrow(7, Direction.DOWN, 2, 9, 2, 8, 2, 8, 3, 8, 4),
            arrow(8, Direction.DOWN, 3, 10, 10, 10, 11, 10, 12, 10, 13),
            arrow(9, Direction.LEFT, 0, 5, 7, 4, 7, 3, 7),
            arrow(10, Direction.LEFT, 1, 1, 6, 0, 6),
            arrow(11, Direction.UP, 2, 5, 4, 5, 3, 5, 2, 5, 1, 5, 0),
            arrow(12, Direction.UP, 3, 8, 7, 7, 7, 6, 7, 6, 6, 6, 5, 6, 4),
            arrow(13, Direction.UP, 0, 5, 11, 5, 10),
            arrow(14, Direction.DOWN, 1, 2, 6, 2, 7, 2, 8),
            arrow(15, Direction.DOWN, 2, 11, 10, 11, 11),
            arrow(16, Direction.DOWN, 3, 4, 4, 4, 5),
            arrow(17, Direction.UP, 0, 10, 9, 11, 9, 12, 9, 12, 8, 12, 7),
            arrow(18, Direction.UP, 1, 1, 11, 1, 10)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(176, LevelData(
            levelNumber = 176,
            title = "City 9 • Route 16 • Solar Flare",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 9, 10, 8, 10),
            arrow(2, Direction.RIGHT, 1, 4, 5, 4, 4, 4, 3, 5, 3, 6, 3),
            arrow(3, Direction.DOWN, 2, 1, 5, 1, 6, 1, 7),
            arrow(4, Direction.LEFT, 3, 9, 6, 8, 6, 7, 6, 6, 6),
            arrow(5, Direction.DOWN, 0, 3, 10, 4, 10, 4, 11, 4, 12),
            arrow(6, Direction.RIGHT, 1, 7, 9, 8, 9, 9, 9, 10, 9),
            arrow(7, Direction.UP, 2, 6, 10, 6, 9),
            arrow(8, Direction.RIGHT, 3, 2, 2, 3, 2),
            arrow(9, Direction.DOWN, 0, 6, 11, 6, 12),
            arrow(10, Direction.LEFT, 1, 5, 7, 5, 8, 5, 9, 4, 9, 3, 9),
            arrow(11, Direction.DOWN, 2, 8, 2, 9, 2, 10, 2, 10, 3, 10, 4),
            arrow(12, Direction.UP, 3, 1, 1, 1, 0),
            arrow(13, Direction.UP, 0, 3, 7, 3, 6, 3, 5),
            arrow(14, Direction.RIGHT, 1, 2, 8, 3, 8, 4, 8),
            arrow(15, Direction.UP, 2, 9, 5, 9, 4, 9, 3),
            arrow(16, Direction.RIGHT, 3, 6, 7, 7, 7, 8, 7),
            arrow(17, Direction.DOWN, 0, 5, 1, 5, 2),
            arrow(18, Direction.RIGHT, 1, 6, 5, 6, 4, 7, 4, 8, 4),
            arrow(19, Direction.UP, 2, 1, 3, 1, 2)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(177, LevelData(
            levelNumber = 177,
            title = "City 9 • Route 17 • Laser Corridor",
            gridWidth = 11,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 7, 7, 7, 6, 7, 5, 6, 5, 5, 5),
            arrow(2, Direction.UP, 1, 4, 9, 4, 8),
            arrow(3, Direction.DOWN, 2, 1, 4, 1, 5),
            arrow(4, Direction.RIGHT, 3, 6, 9, 6, 8, 7, 8, 8, 8),
            arrow(5, Direction.RIGHT, 0, 5, 7, 6, 7),
            arrow(6, Direction.LEFT, 1, 3, 4, 3, 5, 3, 6, 2, 6, 1, 6),
            arrow(7, Direction.UP, 2, 7, 2, 6, 2, 6, 1, 6, 0),
            arrow(8, Direction.UP, 3, 8, 5, 8, 4),
            arrow(9, Direction.LEFT, 0, 3, 3, 2, 3, 1, 3),
            arrow(10, Direction.RIGHT, 1, 1, 7, 1, 8, 2, 8, 3, 8),
            arrow(11, Direction.RIGHT, 2, 2, 9, 3, 9),
            arrow(12, Direction.LEFT, 3, 9, 2, 8, 2),
            arrow(13, Direction.DOWN, 0, 7, 3, 8, 3, 9, 3, 9, 4, 9, 5),
            arrow(14, Direction.LEFT, 1, 3, 2, 3, 1, 3, 0, 2, 0, 1, 0),
            arrow(15, Direction.DOWN, 2, 9, 7, 10, 7, 10, 8, 10, 9),
            arrow(16, Direction.LEFT, 3, 9, 6, 8, 6),
            arrow(17, Direction.LEFT, 0, 7, 4, 6, 4)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(178, LevelData(
            levelNumber = 178,
            title = "City 9 • Route 18 • Lustre Chamber",
            gridWidth = 11,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 1, 8, 1, 9, 2, 9, 3, 9),
            arrow(2, Direction.DOWN, 1, 4, 6, 3, 6, 2, 6, 2, 7, 2, 8),
            arrow(3, Direction.LEFT, 2, 9, 7, 8, 7),
            arrow(4, Direction.LEFT, 3, 5, 9, 5, 10, 4, 10, 3, 10),
            arrow(5, Direction.RIGHT, 0, 8, 3, 9, 3, 10, 3),
            arrow(6, Direction.LEFT, 1, 7, 2, 7, 3, 7, 4, 6, 4, 5, 4, 4, 4),
            arrow(7, Direction.RIGHT, 2, 8, 2, 9, 2),
            arrow(8, Direction.LEFT, 3, 8, 1, 7, 1, 6, 1),
            arrow(9, Direction.DOWN, 0, 5, 7, 5, 8),
            arrow(10, Direction.DOWN, 1, 3, 3, 3, 4),
            arrow(11, Direction.RIGHT, 2, 2, 5, 3, 5),
            arrow(12, Direction.UP, 3, 1, 2, 1, 1),
            arrow(13, Direction.LEFT, 0, 9, 8, 8, 8),
            arrow(14, Direction.UP, 1, 8, 5, 8, 4),
            arrow(15, Direction.RIGHT, 2, 6, 6, 7, 6),
            arrow(16, Direction.UP, 3, 3, 2, 4, 2, 4, 1, 4, 0),
            arrow(17, Direction.LEFT, 0, 2, 3, 1, 3),
            arrow(18, Direction.DOWN, 1, 6, 7, 6, 8, 6, 9),
            arrow(19, Direction.RIGHT, 2, 5, 3, 6, 3),
            arrow(20, Direction.LEFT, 3, 8, 9, 8, 10, 7, 10, 6, 10)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(179, LevelData(
            levelNumber = 179,
            title = "City 9 • Route 19 • Bioluminescence",
            gridWidth = 14,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 5, 6, 6, 6, 7, 6, 8, 6),
            arrow(2, Direction.UP, 1, 2, 3, 2, 2),
            arrow(3, Direction.UP, 2, 4, 9, 4, 8),
            arrow(4, Direction.LEFT, 3, 11, 3, 10, 3, 9, 3, 8, 3),
            arrow(5, Direction.UP, 0, 4, 4, 4, 3),
            arrow(6, Direction.UP, 1, 9, 10, 9, 9),
            arrow(7, Direction.RIGHT, 2, 6, 4, 7, 4),
            arrow(8, Direction.RIGHT, 3, 1, 7, 2, 7, 3, 7),
            arrow(9, Direction.RIGHT, 0, 3, 10, 4, 10),
            arrow(10, Direction.UP, 1, 3, 6, 3, 5, 3, 4),
            arrow(11, Direction.LEFT, 2, 10, 5, 9, 5),
            arrow(12, Direction.UP, 3, 7, 2, 8, 2, 8, 1, 8, 0),
            arrow(13, Direction.LEFT, 0, 10, 6, 10, 7, 10, 8, 9, 8, 8, 8),
            arrow(14, Direction.RIGHT, 1, 10, 2, 11, 2, 12, 2, 13, 2),
            arrow(15, Direction.DOWN, 2, 6, 1, 6, 2, 6, 3),
            arrow(16, Direction.RIGHT, 3, 8, 11, 9, 11)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(180, LevelData(
            levelNumber = 180,
            title = "City 9 • Route 20 • Crescent Moon",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 9, 6, 9, 5),
            arrow(2, Direction.LEFT, 1, 5, 2, 4, 2),
            arrow(3, Direction.DOWN, 2, 10, 8, 10, 9, 10, 10),
            arrow(4, Direction.UP, 3, 5, 6, 5, 5),
            arrow(5, Direction.DOWN, 0, 8, 3, 8, 4, 8, 5, 8, 6),
            arrow(6, Direction.UP, 1, 4, 6, 4, 5),
            arrow(7, Direction.RIGHT, 2, 5, 1, 6, 1, 7, 1),
            arrow(8, Direction.RIGHT, 3, 1, 1, 1, 0, 2, 0, 3, 0),
            arrow(9, Direction.RIGHT, 0, 11, 3, 12, 3),
            arrow(10, Direction.UP, 1, 5, 8, 5, 7),
            arrow(11, Direction.RIGHT, 2, 10, 1, 11, 1),
            arrow(12, Direction.UP, 3, 2, 9, 2, 8),
            arrow(13, Direction.LEFT, 0, 8, 7, 7, 7, 6, 7),
            arrow(14, Direction.RIGHT, 1, 5, 10, 6, 10),
            arrow(15, Direction.RIGHT, 2, 3, 4, 4, 4),
            arrow(16, Direction.DOWN, 3, 2, 6, 1, 6, 0, 6, 0, 7, 0, 8),
            arrow(17, Direction.LEFT, 0, 7, 9, 6, 9)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(181, LevelData(
            levelNumber = 181,
            title = "City 10 • Route 1 • Mobius Path",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 4, 8, 4, 9, 5, 9, 6, 9),
            arrow(2, Direction.UP, 1, 4, 3, 4, 2),
            arrow(3, Direction.RIGHT, 2, 9, 2, 9, 3, 9, 4, 10, 4, 11, 4),
            arrow(4, Direction.LEFT, 3, 9, 6, 8, 6, 7, 6, 6, 6, 5, 6),
            arrow(5, Direction.UP, 0, 2, 7, 3, 7, 4, 7, 4, 6, 4, 5),
            arrow(6, Direction.UP, 1, 7, 10, 8, 10, 8, 9, 8, 8),
            arrow(7, Direction.LEFT, 2, 6, 3, 5, 3),
            arrow(8, Direction.LEFT, 3, 7, 11, 6, 11, 5, 11, 4, 11),
            arrow(9, Direction.DOWN, 0, 1, 3, 1, 4),
            arrow(10, Direction.UP, 1, 2, 3, 3, 3, 3, 2, 3, 1),
            arrow(11, Direction.RIGHT, 2, 9, 11, 9, 10, 9, 9, 10, 9, 11, 9),
            arrow(12, Direction.LEFT, 3, 4, 4, 3, 4),
            arrow(13, Direction.DOWN, 0, 1, 6, 1, 7),
            arrow(14, Direction.LEFT, 1, 8, 1, 7, 1, 6, 1),
            arrow(15, Direction.RIGHT, 2, 5, 4, 5, 5, 6, 5, 7, 5),
            arrow(16, Direction.RIGHT, 3, 5, 7, 6, 7, 7, 7, 8, 7, 9, 7),
            arrow(17, Direction.RIGHT, 0, 9, 8, 10, 8),
            arrow(18, Direction.LEFT, 1, 2, 10, 2, 11, 2, 12, 1, 12, 0, 12)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(182, LevelData(
            levelNumber = 182,
            title = "City 10 • Route 2 • Ouroboros",
            gridWidth = 13,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 10, 1, 10, 2, 10, 3),
            arrow(2, Direction.DOWN, 1, 10, 10, 10, 11),
            arrow(3, Direction.RIGHT, 2, 1, 4, 2, 4, 3, 4, 4, 4, 5, 4),
            arrow(4, Direction.LEFT, 3, 5, 1, 4, 1),
            arrow(5, Direction.LEFT, 0, 9, 7, 8, 7),
            arrow(6, Direction.DOWN, 1, 2, 8, 2, 9, 2, 10, 2, 11),
            arrow(7, Direction.RIGHT, 2, 6, 1, 7, 1),
            arrow(8, Direction.RIGHT, 3, 3, 6, 4, 6, 5, 6, 6, 6),
            arrow(9, Direction.RIGHT, 0, 8, 4, 9, 4, 10, 4, 11, 4, 12, 4),
            arrow(10, Direction.DOWN, 1, 1, 1, 1, 2),
            arrow(11, Direction.UP, 2, 6, 10, 5, 10, 4, 10, 4, 9, 4, 8, 4, 7),
            arrow(12, Direction.LEFT, 3, 1, 3, 0, 3),
            arrow(13, Direction.LEFT, 0, 10, 9, 9, 9),
            arrow(14, Direction.LEFT, 1, 7, 2, 6, 2, 5, 2),
            arrow(15, Direction.RIGHT, 2, 7, 8, 7, 7, 7, 6, 8, 6, 9, 6, 10, 6),
            arrow(16, Direction.UP, 3, 9, 2, 9, 1, 9, 0),
            arrow(17, Direction.UP, 0, 8, 1, 8, 0),
            arrow(18, Direction.LEFT, 1, 2, 6, 1, 6, 0, 6)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(183, LevelData(
            levelNumber = 183,
            title = "City 10 • Route 3 • Vortex Walk",
            gridWidth = 14,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 12, 8, 11, 8, 11, 9, 11, 10),
            arrow(2, Direction.LEFT, 1, 4, 4, 4, 5, 4, 6, 3, 6, 2, 6),
            arrow(3, Direction.DOWN, 2, 12, 2, 11, 2, 10, 2, 10, 3, 10, 4, 10, 5),
            arrow(4, Direction.LEFT, 3, 10, 1, 10, 0, 9, 0, 8, 0),
            arrow(5, Direction.DOWN, 0, 1, 4, 1, 5, 1, 6),
            arrow(6, Direction.RIGHT, 1, 11, 6, 12, 6),
            arrow(7, Direction.LEFT, 2, 8, 10, 8, 9, 8, 8, 7, 8, 6, 8),
            arrow(8, Direction.DOWN, 3, 6, 4, 6, 5, 6, 6, 6, 7),
            arrow(9, Direction.RIGHT, 0, 6, 2, 7, 2),
            arrow(10, Direction.LEFT, 1, 9, 5, 8, 5, 7, 5),
            arrow(11, Direction.UP, 2, 8, 12, 9, 12, 9, 11, 9, 10),
            arrow(12, Direction.LEFT, 3, 10, 7, 9, 7),
            arrow(13, Direction.RIGHT, 0, 10, 11, 11, 11, 12, 11, 13, 11),
            arrow(14, Direction.LEFT, 1, 8, 1, 7, 1, 6, 1, 5, 1, 4, 1),
            arrow(15, Direction.RIGHT, 2, 2, 2, 3, 2, 4, 2, 5, 2),
            arrow(16, Direction.RIGHT, 3, 3, 9, 4, 9),
            arrow(17, Direction.RIGHT, 0, 7, 6, 8, 6),
            arrow(18, Direction.DOWN, 1, 5, 10, 5, 11, 5, 12, 5, 13),
            arrow(19, Direction.DOWN, 2, 1, 7, 1, 8),
            arrow(20, Direction.UP, 3, 9, 3, 9, 2),
            arrow(21, Direction.UP, 0, 2, 12, 2, 11),
            arrow(22, Direction.LEFT, 1, 12, 12, 11, 12),
            arrow(23, Direction.LEFT, 2, 4, 12, 3, 12),
            arrow(24, Direction.LEFT, 3, 5, 7, 4, 7)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(184, LevelData(
            levelNumber = 184,
            title = "City 10 • Route 4 • Singularity",
            gridWidth = 14,
            gridHeight = 15,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 1, 2, 1, 1),
            arrow(2, Direction.LEFT, 1, 7, 13, 6, 13, 5, 13),
            arrow(3, Direction.DOWN, 2, 12, 1, 11, 1, 10, 1, 10, 2, 10, 3),
            arrow(4, Direction.LEFT, 3, 10, 12, 9, 12),
            arrow(5, Direction.RIGHT, 0, 12, 3, 13, 3),
            arrow(6, Direction.DOWN, 1, 2, 3, 3, 3, 4, 3, 4, 4, 4, 5),
            arrow(7, Direction.DOWN, 2, 5, 6, 5, 7),
            arrow(8, Direction.DOWN, 3, 7, 1, 7, 2, 7, 3, 7, 4, 7, 5),
            arrow(9, Direction.RIGHT, 0, 8, 11, 9, 11, 10, 11, 11, 11),
            arrow(10, Direction.LEFT, 1, 12, 8, 11, 8, 10, 8, 9, 8, 8, 8),
            arrow(11, Direction.UP, 2, 11, 6, 11, 5, 11, 4, 11, 3),
            arrow(12, Direction.DOWN, 3, 10, 4, 10, 5, 10, 6, 10, 7),
            arrow(13, Direction.DOWN, 0, 2, 5, 2, 6),
            arrow(14, Direction.LEFT, 1, 6, 9, 5, 9),
            arrow(15, Direction.LEFT, 2, 6, 12, 5, 12),
            arrow(16, Direction.LEFT, 3, 12, 7, 11, 7),
            arrow(17, Direction.LEFT, 0, 2, 12, 1, 12),
            arrow(18, Direction.RIGHT, 1, 3, 11, 4, 11),
            arrow(19, Direction.RIGHT, 2, 5, 10, 6, 10),
            arrow(20, Direction.DOWN, 3, 10, 9, 10, 10),
            arrow(21, Direction.LEFT, 0, 2, 11, 1, 11),
            arrow(22, Direction.DOWN, 1, 6, 4, 6, 5, 6, 6, 6, 7, 6, 8),
            arrow(23, Direction.UP, 2, 5, 3, 5, 2)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(185, LevelData(
            levelNumber = 185,
            title = "City 10 • Route 5 • Infinity Loop",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 9, 10, 10, 10),
            arrow(2, Direction.RIGHT, 1, 8, 9, 9, 9, 10, 9),
            arrow(3, Direction.RIGHT, 2, 8, 8, 9, 8, 10, 8, 11, 8),
            arrow(4, Direction.DOWN, 3, 8, 2, 8, 3),
            arrow(5, Direction.DOWN, 0, 4, 2, 3, 2, 3, 3, 3, 4),
            arrow(6, Direction.UP, 1, 7, 6, 7, 5),
            arrow(7, Direction.LEFT, 2, 9, 7, 8, 7, 7, 7, 6, 7),
            arrow(8, Direction.DOWN, 3, 10, 4, 10, 5),
            arrow(9, Direction.DOWN, 0, 5, 6, 5, 7, 5, 8, 5, 9),
            arrow(10, Direction.LEFT, 1, 10, 6, 9, 6, 8, 6),
            arrow(11, Direction.DOWN, 2, 3, 6, 3, 7, 3, 8, 3, 9, 3, 10),
            arrow(12, Direction.UP, 3, 2, 10, 2, 9),
            arrow(13, Direction.RIGHT, 0, 6, 8, 7, 8),
            arrow(14, Direction.UP, 1, 1, 9, 1, 8, 1, 7, 1, 6, 1, 5),
            arrow(15, Direction.RIGHT, 2, 3, 5, 4, 5, 5, 5),
            arrow(16, Direction.DOWN, 3, 5, 3, 5, 4),
            arrow(17, Direction.LEFT, 0, 6, 10, 5, 10, 4, 10)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(186, LevelData(
            levelNumber = 186,
            title = "City 10 • Route 6 • Tesseract",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 9, 1, 8, 1),
            arrow(2, Direction.UP, 1, 1, 12, 1, 11, 1, 10, 1, 9),
            arrow(3, Direction.LEFT, 2, 10, 2, 9, 2, 8, 2, 7, 2),
            arrow(4, Direction.LEFT, 3, 6, 12, 5, 12, 4, 12, 3, 12),
            arrow(5, Direction.DOWN, 0, 9, 9, 10, 9, 11, 9, 11, 10, 11, 11, 11, 12),
            arrow(6, Direction.RIGHT, 1, 7, 9, 8, 9),
            arrow(7, Direction.RIGHT, 2, 11, 5, 12, 5),
            arrow(8, Direction.DOWN, 3, 5, 7, 4, 7, 3, 7, 3, 8, 3, 9, 3, 10),
            arrow(9, Direction.LEFT, 0, 6, 3, 6, 2, 5, 2, 4, 2),
            arrow(10, Direction.LEFT, 1, 6, 6, 6, 5, 5, 5, 4, 5),
            arrow(11, Direction.RIGHT, 2, 6, 11, 7, 11),
            arrow(12, Direction.UP, 3, 9, 8, 10, 8, 10, 7, 10, 6),
            arrow(13, Direction.UP, 0, 2, 7, 1, 7, 0, 7, 0, 6, 0, 5),
            arrow(14, Direction.LEFT, 1, 5, 1, 4, 1, 3, 1, 2, 1, 1, 1),
            arrow(15, Direction.DOWN, 2, 9, 10, 9, 11, 9, 12, 9, 13),
            arrow(16, Direction.LEFT, 3, 9, 3, 9, 4, 8, 4, 7, 4),
            arrow(17, Direction.LEFT, 0, 1, 2, 0, 2),
            arrow(18, Direction.LEFT, 1, 2, 5, 1, 5),
            arrow(19, Direction.RIGHT, 2, 1, 6, 2, 6, 3, 6),
            arrow(20, Direction.LEFT, 3, 5, 11, 4, 11, 3, 11),
            arrow(21, Direction.RIGHT, 0, 11, 3, 12, 3),
            arrow(22, Direction.LEFT, 1, 6, 10, 5, 10),
            arrow(23, Direction.DOWN, 2, 4, 8, 4, 9, 4, 10),
            arrow(24, Direction.RIGHT, 3, 11, 4, 12, 4)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(187, LevelData(
            levelNumber = 187,
            title = "City 10 • Route 7 • Continuum",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 4, 6, 4, 5),
            arrow(2, Direction.DOWN, 1, 9, 10, 9, 11),
            arrow(3, Direction.LEFT, 2, 10, 8, 10, 9, 9, 9, 8, 9),
            arrow(4, Direction.UP, 3, 9, 5, 10, 5, 11, 5, 11, 4, 11, 3),
            arrow(5, Direction.DOWN, 0, 5, 4, 4, 4, 3, 4, 3, 5, 3, 6, 3, 7),
            arrow(6, Direction.UP, 1, 6, 7, 6, 6),
            arrow(7, Direction.DOWN, 2, 4, 7, 4, 8, 4, 9),
            arrow(8, Direction.RIGHT, 3, 3, 2, 4, 2),
            arrow(9, Direction.RIGHT, 0, 5, 8, 6, 8, 7, 8, 8, 8, 9, 8),
            arrow(10, Direction.UP, 1, 2, 9, 1, 9, 0, 9, 0, 8, 0, 7),
            arrow(11, Direction.LEFT, 2, 1, 3, 0, 3),
            arrow(12, Direction.LEFT, 3, 2, 1, 1, 1, 0, 1),
            arrow(13, Direction.LEFT, 0, 9, 4, 8, 4),
            arrow(14, Direction.DOWN, 1, 9, 2, 9, 3),
            arrow(15, Direction.DOWN, 2, 2, 3, 2, 4, 2, 5, 2, 6),
            arrow(16, Direction.UP, 3, 7, 2, 7, 1),
            arrow(17, Direction.UP, 0, 10, 1, 10, 0),
            arrow(18, Direction.LEFT, 1, 8, 1, 8, 2, 8, 3, 7, 3, 6, 3),
            arrow(19, Direction.RIGHT, 2, 1, 10, 2, 10),
            arrow(20, Direction.DOWN, 3, 6, 10, 6, 11)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(188, LevelData(
            levelNumber = 188,
            title = "City 10 • Route 8 • Recursion",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 8, 7, 8, 6, 8, 5, 8, 4, 8, 3),
            arrow(2, Direction.LEFT, 1, 5, 9, 5, 10, 4, 10, 3, 10),
            arrow(3, Direction.DOWN, 2, 6, 8, 6, 9),
            arrow(4, Direction.UP, 3, 3, 3, 3, 2, 3, 1, 3, 0),
            arrow(5, Direction.RIGHT, 0, 2, 9, 3, 9, 4, 9),
            arrow(6, Direction.LEFT, 1, 2, 10, 1, 10),
            arrow(7, Direction.UP, 2, 8, 9, 8, 8),
            arrow(8, Direction.DOWN, 3, 1, 1, 2, 1, 2, 2, 2, 3),
            arrow(9, Direction.UP, 0, 1, 9, 1, 8, 1, 7),
            arrow(10, Direction.DOWN, 1, 10, 2, 10, 3, 10, 4, 10, 5, 10, 6),
            arrow(11, Direction.DOWN, 2, 2, 5, 2, 6, 2, 7),
            arrow(12, Direction.DOWN, 3, 5, 1, 6, 1, 6, 2, 6, 3),
            arrow(13, Direction.UP, 0, 5, 6, 5, 5, 5, 4, 5, 3),
            arrow(14, Direction.RIGHT, 1, 10, 8, 11, 8),
            arrow(15, Direction.LEFT, 2, 4, 4, 3, 4, 2, 4, 1, 4),
            arrow(16, Direction.UP, 3, 7, 10, 7, 9, 7, 8, 7, 7, 7, 6),
            arrow(17, Direction.RIGHT, 0, 7, 1, 8, 1, 9, 1, 10, 1, 11, 1),
            arrow(18, Direction.DOWN, 1, 8, 10, 8, 11),
            arrow(19, Direction.RIGHT, 2, 2, 8, 3, 8)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(189, LevelData(
            levelNumber = 189,
            title = "City 10 • Route 9 • Fractal Gate",
            gridWidth = 11,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 5, 10, 6, 10),
            arrow(2, Direction.DOWN, 1, 9, 8, 8, 8, 7, 8, 7, 9, 7, 10, 7, 11),
            arrow(3, Direction.DOWN, 2, 5, 3, 5, 4, 5, 5, 5, 6),
            arrow(4, Direction.RIGHT, 3, 6, 1, 6, 2, 6, 3, 7, 3, 8, 3),
            arrow(5, Direction.RIGHT, 0, 2, 9, 3, 9, 4, 9, 5, 9),
            arrow(6, Direction.RIGHT, 1, 2, 4, 3, 4, 4, 4),
            arrow(7, Direction.DOWN, 2, 8, 4, 8, 5, 8, 6),
            arrow(8, Direction.RIGHT, 3, 2, 5, 3, 5, 4, 5),
            arrow(9, Direction.DOWN, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5),
            arrow(10, Direction.RIGHT, 1, 8, 2, 9, 2, 10, 2),
            arrow(11, Direction.RIGHT, 2, 2, 7, 3, 7, 4, 7, 5, 7),
            arrow(12, Direction.DOWN, 3, 2, 1, 2, 2, 2, 3),
            arrow(13, Direction.RIGHT, 0, 9, 5, 10, 5),
            arrow(14, Direction.DOWN, 1, 4, 1, 4, 2),
            arrow(15, Direction.DOWN, 2, 6, 8, 6, 9),
            arrow(16, Direction.RIGHT, 3, 9, 6, 10, 6),
            arrow(17, Direction.UP, 0, 6, 7, 6, 6),
            arrow(18, Direction.DOWN, 1, 2, 10, 2, 11),
            arrow(19, Direction.UP, 2, 3, 2, 3, 1)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(190, LevelData(
            levelNumber = 190,
            title = "City 10 • Route 10 • Time Maze",
            gridWidth = 12,
            gridHeight = 11,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 9, 8, 9, 7, 9, 6, 9, 5),
            arrow(2, Direction.LEFT, 1, 8, 7, 7, 7, 6, 7, 5, 7),
            arrow(3, Direction.LEFT, 2, 3, 9, 3, 8, 3, 7, 2, 7, 1, 7, 0, 7),
            arrow(4, Direction.DOWN, 3, 3, 1, 2, 1, 1, 1, 1, 2, 1, 3, 1, 4),
            arrow(5, Direction.UP, 0, 4, 9, 4, 8),
            arrow(6, Direction.DOWN, 1, 10, 2, 10, 3, 10, 4),
            arrow(7, Direction.DOWN, 2, 8, 1, 8, 2),
            arrow(8, Direction.DOWN, 3, 6, 9, 6, 10),
            arrow(9, Direction.UP, 0, 5, 1, 5, 0),
            arrow(10, Direction.UP, 1, 5, 5, 6, 5, 7, 5, 7, 4, 7, 3, 7, 2),
            arrow(11, Direction.UP, 2, 2, 5, 2, 4, 2, 3),
            arrow(12, Direction.LEFT, 3, 6, 2, 5, 2, 4, 2, 3, 2),
            arrow(13, Direction.RIGHT, 0, 9, 2, 9, 1, 10, 1, 11, 1),
            arrow(14, Direction.LEFT, 1, 10, 9, 9, 9),
            arrow(15, Direction.RIGHT, 2, 6, 8, 7, 8),
            arrow(16, Direction.RIGHT, 3, 4, 6, 5, 6, 6, 6),
            arrow(17, Direction.UP, 0, 4, 5, 3, 5, 3, 4, 3, 3),
            arrow(18, Direction.LEFT, 1, 5, 3, 4, 3)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(191, LevelData(
            levelNumber = 191,
            title = "City 10 • Route 11 • Dimension Bend",
            gridWidth = 13,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.RIGHT, 0, 1, 10, 1, 9, 2, 9, 3, 9),
            arrow(2, Direction.UP, 1, 6, 11, 6, 10),
            arrow(3, Direction.DOWN, 2, 5, 4, 6, 4, 7, 4, 7, 5, 7, 6, 7, 7),
            arrow(4, Direction.LEFT, 3, 8, 8, 7, 8),
            arrow(5, Direction.DOWN, 0, 5, 1, 4, 1, 4, 2, 4, 3),
            arrow(6, Direction.LEFT, 1, 10, 8, 9, 8),
            arrow(7, Direction.UP, 2, 6, 7, 6, 6, 6, 5),
            arrow(8, Direction.RIGHT, 3, 8, 5, 9, 5, 10, 5, 11, 5, 12, 5),
            arrow(9, Direction.DOWN, 0, 5, 9, 5, 10, 5, 11, 5, 12),
            arrow(10, Direction.RIGHT, 1, 7, 10, 7, 11, 8, 11, 9, 11),
            arrow(11, Direction.RIGHT, 2, 8, 6, 9, 6, 10, 6, 11, 6),
            arrow(12, Direction.RIGHT, 3, 7, 1, 8, 1),
            arrow(13, Direction.LEFT, 0, 5, 8, 4, 8, 3, 8, 2, 8),
            arrow(14, Direction.UP, 1, 6, 1, 6, 0),
            arrow(15, Direction.RIGHT, 2, 7, 2, 8, 2),
            arrow(16, Direction.LEFT, 3, 5, 5, 4, 5, 3, 5, 2, 5),
            arrow(17, Direction.UP, 0, 10, 4, 10, 3),
            arrow(18, Direction.UP, 1, 10, 10, 10, 9),
            arrow(19, Direction.RIGHT, 2, 3, 10, 4, 10),
            arrow(20, Direction.RIGHT, 3, 2, 12, 3, 12),
            arrow(21, Direction.DOWN, 0, 2, 10, 2, 11),
            arrow(22, Direction.RIGHT, 1, 7, 9, 8, 9)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(192, LevelData(
            levelNumber = 192,
            title = "City 10 • Route 12 • Temporal Flow",
            gridWidth = 14,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 9, 8, 9, 7, 9, 6, 9, 5),
            arrow(2, Direction.RIGHT, 1, 2, 9, 3, 9),
            arrow(3, Direction.RIGHT, 2, 11, 3, 12, 3),
            arrow(4, Direction.LEFT, 3, 3, 3, 2, 3),
            arrow(5, Direction.RIGHT, 0, 5, 8, 6, 8, 7, 8),
            arrow(6, Direction.LEFT, 1, 12, 7, 11, 7),
            arrow(7, Direction.LEFT, 2, 4, 1, 3, 1),
            arrow(8, Direction.DOWN, 3, 8, 12, 8, 13),
            arrow(9, Direction.RIGHT, 0, 5, 1, 6, 1),
            arrow(10, Direction.LEFT, 1, 12, 2, 12, 1, 12, 0, 11, 0, 10, 0, 9, 0),
            arrow(11, Direction.LEFT, 2, 4, 5, 3, 5, 2, 5, 1, 5),
            arrow(12, Direction.LEFT, 3, 4, 2, 3, 2, 2, 2, 1, 2, 0, 2),
            arrow(13, Direction.UP, 0, 8, 7, 7, 7, 6, 7, 6, 6, 6, 5, 6, 4),
            arrow(14, Direction.LEFT, 1, 3, 12, 3, 13, 2, 13, 1, 13),
            arrow(15, Direction.LEFT, 2, 9, 11, 8, 11, 7, 11, 6, 11),
            arrow(16, Direction.RIGHT, 3, 7, 1, 8, 1, 9, 1, 10, 1, 11, 1),
            arrow(17, Direction.LEFT, 0, 4, 7, 3, 7, 2, 7, 1, 7, 0, 7),
            arrow(18, Direction.UP, 1, 6, 10, 6, 9),
            arrow(19, Direction.LEFT, 2, 9, 4, 8, 4),
            arrow(20, Direction.UP, 3, 2, 12, 2, 11, 2, 10),
            arrow(21, Direction.DOWN, 0, 10, 3, 10, 4),
            arrow(22, Direction.DOWN, 1, 5, 10, 5, 11)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(193, LevelData(
            levelNumber = 193,
            title = "City 10 • Route 13 • Quantum Trace",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 8, 9, 8, 10, 7, 10, 6, 10),
            arrow(2, Direction.RIGHT, 1, 1, 3, 2, 3, 3, 3, 4, 3, 5, 3),
            arrow(3, Direction.LEFT, 2, 2, 9, 1, 9),
            arrow(4, Direction.LEFT, 3, 9, 6, 8, 6, 7, 6),
            arrow(5, Direction.RIGHT, 0, 4, 7, 5, 7, 6, 7, 7, 7, 8, 7),
            arrow(6, Direction.UP, 1, 9, 4, 8, 4, 8, 3, 8, 2),
            arrow(7, Direction.RIGHT, 2, 5, 8, 6, 8),
            arrow(8, Direction.DOWN, 3, 3, 7, 3, 8, 3, 9),
            arrow(9, Direction.LEFT, 0, 1, 2, 0, 2),
            arrow(10, Direction.DOWN, 1, 9, 1, 8, 1, 7, 1, 7, 2, 7, 3, 7, 4),
            arrow(11, Direction.DOWN, 2, 5, 4, 5, 5, 5, 6),
            arrow(12, Direction.LEFT, 3, 3, 6, 2, 6),
            arrow(13, Direction.UP, 0, 4, 5, 4, 4),
            arrow(14, Direction.RIGHT, 1, 3, 1, 4, 1, 5, 1, 6, 1),
            arrow(15, Direction.DOWN, 2, 9, 8, 9, 9, 9, 10, 9, 11),
            arrow(16, Direction.LEFT, 3, 5, 2, 4, 2),
            arrow(17, Direction.DOWN, 0, 6, 5, 6, 6),
            arrow(18, Direction.RIGHT, 1, 1, 7, 2, 7),
            arrow(19, Direction.DOWN, 2, 5, 9, 5, 10, 5, 11),
            arrow(20, Direction.RIGHT, 3, 10, 9, 11, 9),
            arrow(21, Direction.UP, 0, 10, 3, 10, 2, 10, 1)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(194, LevelData(
            levelNumber = 194,
            title = "City 10 • Route 14 • Chronos Loop",
            gridWidth = 13,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.LEFT, 0, 7, 5, 6, 5),
            arrow(2, Direction.LEFT, 1, 8, 10, 8, 9, 7, 9, 6, 9),
            arrow(3, Direction.UP, 2, 7, 3, 7, 2, 7, 1),
            arrow(4, Direction.UP, 3, 2, 6, 2, 5, 2, 4, 2, 3),
            arrow(5, Direction.DOWN, 0, 5, 2, 5, 3),
            arrow(6, Direction.LEFT, 1, 11, 2, 11, 3, 11, 4, 10, 4, 9, 4, 8, 4),
            arrow(7, Direction.DOWN, 2, 2, 11, 2, 12),
            arrow(8, Direction.DOWN, 3, 4, 11, 4, 12),
            arrow(9, Direction.RIGHT, 0, 2, 7, 3, 7),
            arrow(10, Direction.UP, 1, 11, 9, 11, 8),
            arrow(11, Direction.DOWN, 2, 10, 8, 10, 9, 10, 10, 10, 11),
            arrow(12, Direction.RIGHT, 3, 4, 7, 5, 7, 6, 7, 7, 7),
            arrow(13, Direction.LEFT, 0, 5, 1, 4, 1, 3, 1, 2, 1),
            arrow(14, Direction.LEFT, 1, 3, 2, 2, 2),
            arrow(15, Direction.LEFT, 2, 6, 8, 5, 8),
            arrow(16, Direction.UP, 3, 9, 10, 9, 9, 9, 8, 9, 7),
            arrow(17, Direction.UP, 0, 4, 10, 4, 9, 4, 8),
            arrow(18, Direction.RIGHT, 1, 4, 6, 5, 6, 6, 6, 7, 6),
            arrow(19, Direction.UP, 2, 2, 9, 1, 9, 1, 8, 1, 7)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(195, LevelData(
            levelNumber = 195,
            title = "City 10 • Route 15 • Eternal Flow",
            gridWidth = 15,
            gridHeight = 16,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 13, 8, 13, 7, 13, 6),
            arrow(2, Direction.UP, 1, 6, 11, 6, 10),
            arrow(3, Direction.DOWN, 2, 8, 9, 9, 9, 10, 9, 10, 10, 10, 11, 10, 12),
            arrow(4, Direction.DOWN, 3, 10, 5, 10, 6),
            arrow(5, Direction.LEFT, 0, 12, 8, 11, 8, 10, 8, 9, 8, 8, 8),
            arrow(6, Direction.LEFT, 1, 5, 9, 4, 9),
            arrow(7, Direction.RIGHT, 2, 8, 7, 9, 7, 10, 7, 11, 7),
            arrow(8, Direction.RIGHT, 3, 8, 5, 9, 5),
            arrow(9, Direction.DOWN, 0, 1, 8, 1, 9),
            arrow(10, Direction.RIGHT, 1, 8, 4, 9, 4),
            arrow(11, Direction.RIGHT, 2, 4, 11, 4, 12, 4, 13, 5, 13, 6, 13),
            arrow(12, Direction.RIGHT, 3, 9, 2, 10, 2, 11, 2, 12, 2),
            arrow(13, Direction.RIGHT, 0, 9, 12, 9, 13, 9, 14, 10, 14, 11, 14),
            arrow(14, Direction.LEFT, 1, 13, 12, 12, 12, 11, 12),
            arrow(15, Direction.RIGHT, 2, 1, 5, 2, 5, 3, 5, 4, 5, 5, 5),
            arrow(16, Direction.LEFT, 3, 7, 7, 6, 7, 5, 7),
            arrow(17, Direction.DOWN, 0, 7, 3, 7, 4),
            arrow(18, Direction.LEFT, 1, 1, 11, 0, 11),
            arrow(19, Direction.RIGHT, 2, 1, 12, 1, 13, 2, 13, 3, 13),
            arrow(20, Direction.LEFT, 3, 5, 3, 5, 2, 5, 1, 4, 1, 3, 1, 2, 1),
            arrow(21, Direction.RIGHT, 0, 6, 14, 7, 14),
            arrow(22, Direction.RIGHT, 1, 10, 13, 11, 13, 12, 13, 13, 13, 14, 13),
            arrow(23, Direction.DOWN, 2, 2, 9, 2, 10, 2, 11, 2, 12),
            arrow(24, Direction.UP, 3, 3, 7, 3, 6)
            ),
            maxDrops = 4,
            isSilhouette = true,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(196, LevelData(
            levelNumber = 196,
            title = "City 10 • Route 16 • Event Horizon",
            gridWidth = 11,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 4, 1, 4, 2, 4, 3, 4, 4),
            arrow(2, Direction.UP, 1, 3, 10, 3, 9, 3, 8, 3, 7, 3, 6),
            arrow(3, Direction.LEFT, 2, 3, 3, 2, 3, 1, 3, 0, 3),
            arrow(4, Direction.DOWN, 3, 8, 6, 8, 7, 8, 8),
            arrow(5, Direction.RIGHT, 0, 5, 8, 6, 8),
            arrow(6, Direction.LEFT, 1, 9, 4, 8, 4),
            arrow(7, Direction.RIGHT, 2, 9, 7, 10, 7),
            arrow(8, Direction.RIGHT, 3, 9, 8, 10, 8),
            arrow(9, Direction.DOWN, 0, 5, 3, 5, 4),
            arrow(10, Direction.RIGHT, 1, 5, 10, 6, 10, 7, 10, 8, 10),
            arrow(11, Direction.DOWN, 2, 1, 5, 1, 6, 1, 7),
            arrow(12, Direction.LEFT, 3, 7, 5, 7, 6, 7, 7, 6, 7, 5, 7, 4, 7),
            arrow(13, Direction.DOWN, 0, 8, 2, 7, 2, 7, 3, 7, 4),
            arrow(14, Direction.UP, 1, 3, 5, 3, 4),
            arrow(15, Direction.LEFT, 2, 9, 10, 9, 11, 8, 11, 7, 11),
            arrow(16, Direction.DOWN, 3, 2, 6, 2, 7),
            arrow(17, Direction.LEFT, 0, 3, 2, 2, 2),
            arrow(18, Direction.LEFT, 1, 8, 9, 7, 9),
            arrow(19, Direction.DOWN, 2, 2, 8, 1, 8, 0, 8, 0, 9, 0, 10, 0, 11)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(197, LevelData(
            levelNumber = 197,
            title = "City 10 • Route 17 • Cosmic Spiral",
            gridWidth = 15,
            gridHeight = 14,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 5, 5, 5, 6, 5, 7, 5, 8, 5, 9),
            arrow(2, Direction.UP, 1, 9, 9, 9, 8, 9, 7),
            arrow(3, Direction.LEFT, 2, 3, 7, 3, 6, 2, 6, 1, 6),
            arrow(4, Direction.LEFT, 3, 5, 10, 4, 10),
            arrow(5, Direction.UP, 0, 4, 3, 4, 2),
            arrow(6, Direction.UP, 1, 4, 9, 4, 8, 4, 7),
            arrow(7, Direction.LEFT, 2, 9, 6, 8, 6, 7, 6),
            arrow(8, Direction.RIGHT, 3, 7, 11, 7, 12, 7, 13, 8, 13, 9, 13),
            arrow(9, Direction.LEFT, 0, 10, 5, 9, 5, 8, 5, 7, 5, 6, 5),
            arrow(10, Direction.RIGHT, 1, 10, 9, 11, 9, 12, 9),
            arrow(11, Direction.RIGHT, 2, 8, 11, 9, 11, 10, 11, 11, 11),
            arrow(12, Direction.UP, 3, 2, 10, 2, 9),
            arrow(13, Direction.RIGHT, 0, 11, 7, 12, 7, 13, 7, 14, 7),
            arrow(14, Direction.UP, 1, 12, 11, 12, 10),
            arrow(15, Direction.UP, 2, 8, 10, 8, 9, 8, 8),
            arrow(16, Direction.RIGHT, 3, 2, 4, 3, 4),
            arrow(17, Direction.UP, 0, 1, 3, 1, 2, 1, 1),
            arrow(18, Direction.UP, 1, 12, 5, 13, 5, 14, 5, 14, 4, 14, 3),
            arrow(19, Direction.DOWN, 2, 11, 3, 11, 4)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(198, LevelData(
            levelNumber = 198,
            title = "City 10 • Route 18 • Hypercube",
            gridWidth = 12,
            gridHeight = 12,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 6, 4, 6, 5, 6, 6),
            arrow(2, Direction.DOWN, 1, 10, 4, 10, 5, 10, 6, 10, 7, 10, 8),
            arrow(3, Direction.RIGHT, 2, 3, 6, 4, 6),
            arrow(4, Direction.RIGHT, 3, 7, 5, 8, 5),
            arrow(5, Direction.DOWN, 0, 2, 7, 2, 8, 2, 9),
            arrow(6, Direction.LEFT, 1, 10, 9, 10, 10, 10, 11, 9, 11, 8, 11, 7, 11),
            arrow(7, Direction.DOWN, 2, 5, 8, 5, 9),
            arrow(8, Direction.RIGHT, 3, 8, 3, 9, 3, 10, 3, 11, 3),
            arrow(9, Direction.UP, 0, 4, 4, 4, 3),
            arrow(10, Direction.RIGHT, 1, 3, 9, 3, 8, 3, 7, 4, 7, 5, 7),
            arrow(11, Direction.RIGHT, 2, 5, 2, 6, 2, 7, 2),
            arrow(12, Direction.UP, 3, 9, 9, 8, 9, 8, 8, 8, 7),
            arrow(13, Direction.RIGHT, 0, 1, 9, 1, 10, 2, 10, 3, 10),
            arrow(14, Direction.UP, 1, 9, 7, 9, 6),
            arrow(15, Direction.LEFT, 2, 7, 9, 6, 9),
            arrow(16, Direction.DOWN, 3, 2, 2, 2, 3)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(199, LevelData(
            levelNumber = 199,
            title = "City 10 • Route 19 • Omniverse",
            gridWidth = 12,
            gridHeight = 13,
            arrows = listOf(

            arrow(1, Direction.DOWN, 0, 2, 8, 1, 8, 1, 9, 1, 10),
            arrow(2, Direction.DOWN, 1, 6, 5, 6, 6),
            arrow(3, Direction.DOWN, 2, 2, 1, 2, 2, 2, 3),
            arrow(4, Direction.UP, 3, 7, 8, 6, 8, 5, 8, 5, 7, 5, 6, 5, 5),
            arrow(5, Direction.DOWN, 0, 6, 9, 6, 10),
            arrow(6, Direction.DOWN, 1, 9, 9, 8, 9, 8, 10, 8, 11),
            arrow(7, Direction.UP, 2, 5, 1, 5, 0),
            arrow(8, Direction.LEFT, 3, 10, 5, 9, 5, 8, 5, 7, 5),
            arrow(9, Direction.UP, 0, 10, 10, 10, 9, 10, 8),
            arrow(10, Direction.RIGHT, 1, 7, 3, 8, 3, 9, 3),
            arrow(11, Direction.DOWN, 2, 7, 10, 7, 11),
            arrow(12, Direction.LEFT, 3, 5, 2, 5, 3, 5, 4, 4, 4, 3, 4, 2, 4),
            arrow(13, Direction.RIGHT, 0, 6, 2, 6, 1, 6, 0, 7, 0, 8, 0),
            arrow(14, Direction.UP, 1, 3, 8, 3, 7, 3, 6, 3, 5),
            arrow(15, Direction.DOWN, 2, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6),
            arrow(16, Direction.DOWN, 3, 4, 7, 4, 8),
            arrow(17, Direction.LEFT, 0, 8, 7, 7, 7, 6, 7)
            ),
            maxDrops = 4,
            isSilhouette = false,
            silhouetteIcon = "🧩",
            bannerText = "TAP TO CLEAR"
        ))

        map.put(200, LevelData(
            levelNumber = 200,
            title = "City 10 • Route 20 • The Infinity Maze 🌀",
            gridWidth = 15,
            gridHeight = 15,
            arrows = listOf(

            arrow(1, Direction.UP, 0, 3, 8, 3, 6, 5, 6, 5, 4),
            arrow(2, Direction.LEFT, 1, 9, 8, 11, 8, 11, 6, 9, 6),
            arrow(3, Direction.RIGHT, 2, 6, 8, 8, 8),
            arrow(4, Direction.UP, 3, 8, 6, 6, 6, 6, 4),
            arrow(5, Direction.DOWN, 0, 1, 2, 1, 10),
            arrow(6, Direction.LEFT, 1, 13, 11, 3, 11),
            arrow(7, Direction.UP, 2, 0, 12, 0, 2),
            arrow(8, Direction.RIGHT, 3, 0, 1, 13, 1),
            arrow(9, Direction.DOWN, 0, 14, 1, 14, 12),
            arrow(10, Direction.LEFT, 1, 14, 13, 3, 13),
            arrow(11, Direction.UP, 2, 2, 12, 2, 2)
            ),
            maxDrops = 5,
            isSilhouette = true,
            silhouetteIcon = "🌀",
            bannerText = "INFINITY MAZE"
        ))

    }
}
