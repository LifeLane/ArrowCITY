package com.example.engine

import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GridPoint
import com.example.model.LevelData
import java.util.Random

object ProceduralGenerator {

    /**
     * Generates a guaranteed-solvable level based on level number and seed.
     */
    fun generateLevel(levelNumber: Int): LevelData {
        val random = Random(levelNumber.toLong() * 31337L + 7919L)

        // Check if there is a procedural silhouette mask for this milestone
        val silhouetteMask = getSilhouetteMask(levelNumber)
        
        val (width, height, targetArrows) = if (silhouetteMask != null) {
            Triple(silhouetteMask.width, silhouetteMask.height, silhouetteMask.targetArrows)
        } else {
            when {
                levelNumber <= 3 -> Triple(6, 6, 4 + levelNumber)
                levelNumber <= 10 -> Triple(8, 8, 7 + (levelNumber - 3))
                levelNumber <= 25 -> Triple(10, 10, 12 + (levelNumber % 6))
                levelNumber <= 50 -> Triple(12, 12, 16 + (levelNumber % 8))
                levelNumber <= 100 -> Triple(14, 14, 20 + (levelNumber % 10))
                levelNumber <= 200 -> Triple(15, 15, 24 + (levelNumber % 12).coerceAtMost(16))
                else -> Triple(16, 16, 26 + (levelNumber % 14).coerceAtMost(20))
            }
        }

        val arrows = if (silhouetteMask != null) {
            generateMaskedArrowSet(silhouetteMask, random)
        } else {
            generateSolvableArrowSet(width, height, targetArrows, random)
        }

        val bannerWords = listOf(
            "EYE COMFORT",
            "TAP TO CLEAR",
            "CALM MIND",
            "QUIET FLOW",
            "BETTER SLEEP",
            "CHALLENGE ON",
            "FOCUS & UNTANGLE",
            "BREATHE DEEP",
            "MIND AT EASE"
        )
        val banner = if (silhouetteMask != null) {
            silhouetteMask.banner
        } else {
            bannerWords[(levelNumber - 1) % bannerWords.size]
        }

        return LevelData(
            levelNumber = levelNumber,
            title = if (silhouetteMask != null) "Level $levelNumber • ${silhouetteMask.title}" else "Level $levelNumber",
            gridWidth = width,
            gridHeight = height,
            arrows = arrows,
            maxDrops = if (targetArrows > 15) 5 else 3,
            isSilhouette = silhouetteMask != null,
            silhouetteIcon = silhouetteMask?.icon ?: "🧩",
            bannerText = banner
        )
    }

    data class SilhouetteConfig(
        val title: String,
        val icon: String,
        val width: Int,
        val height: Int,
        val targetArrows: Int,
        val banner: String,
        val maskPattern: List<String>
    )

    private fun getSilhouetteMask(levelNumber: Int): SilhouetteConfig? {
        return when (levelNumber) {
            15 -> SilhouetteConfig(
                title = "Diamond Heart 💎",
                icon = "💎",
                width = 13,
                height = 13,
                targetArrows = 14,
                banner = "DIAMOND HEART",
                maskPattern = listOf(
                    "..XXXX.XXXX..",
                    ".XXXXXXXXXXX.",
                    "XXXXXXXXXXXXX",
                    "XXXXXXXXXXXXX",
                    ".XXXXXXXXXXX.",
                    "..XXXXXXXXX..",
                    "...XXXXXXX...",
                    "....XXXXX....",
                    ".....XXX.....",
                    "......X......"
                )
            )
            20 -> SilhouetteConfig(
                title = "Imperial Crown 👑",
                icon = "👑",
                width = 13,
                height = 12,
                targetArrows = 15,
                banner = "ROYAL CROWN",
                maskPattern = listOf(
                    "X.....X.....X",
                    "XX...XXX...XX",
                    "XXX.XXXXX.XXX",
                    "XXXXXXXXXXXXX",
                    "XXXXXXXXXXXXX",
                    ".XXXXXXXXXXX.",
                    "..XXXXXXXXX..",
                    "XXXXXXXXXXXXX"
                )
            )
            35 -> SilhouetteConfig(
                title = "Playful Kitty 🐱",
                icon = "🐱",
                width = 14,
                height = 14,
                targetArrows = 16,
                banner = "PLAYFUL KITTY",
                maskPattern = listOf(
                    "XX........XX.",
                    "XXX......XXX.",
                    "XXXXXXXXXXXX.",
                    "XXXXXXXXXXXX.",
                    ".XXXXXXXXXX..",
                    "..XXXXXXXX...",
                    "....XXXXXX...",
                    "...XXXXXXXX..",
                    "..XXXXXXXXXX.",
                    "..XXXXXXXXXX.",
                    "..XX.XXXX.XX.",
                    "..XX......XX."
                )
            )
            50 -> SilhouetteConfig(
                title = "Bonsai Tree 🌲",
                icon = "🌲",
                width = 14,
                height = 14,
                targetArrows = 18,
                banner = "BONSAI CALM",
                maskPattern = listOf(
                    "....XXXX....",
                    "...XXXXXX...",
                    "..XXXXXXXX..",
                    ".XXXXXXXXXX.",
                    "..XXXXXXXX..",
                    "...XXXXXX...",
                    ".....XX.....",
                    ".....XX.....",
                    "....XXXX....",
                    "...XXXXXX..."
                )
            )
            60 -> SilhouetteConfig(
                title = "Koi Fish 🐟",
                icon = "🐟",
                width = 14,
                height = 14,
                targetArrows = 18,
                banner = "SWIMMING KOI",
                maskPattern = listOf(
                    ".....XXXX....",
                    "...XXXXXXXX..",
                    "..XXXXXXXXXX.",
                    ".XXXXXXXXXXX.",
                    "XXXXXXXXXXXXX",
                    ".XXXXXXXXXXX.",
                    "..XXXXXXXXXX.",
                    "...XXXXXXXX..",
                    ".....XXXX....",
                    "....XX..XX...",
                    "...XXX..XXX.."
                )
            )
            75 -> SilhouetteConfig(
                title = "Soaring Falcon 🦅",
                icon = "🦅",
                width = 15,
                height = 14,
                targetArrows = 20,
                banner = "SOARING FALCON",
                maskPattern = listOf(
                    "X.............X",
                    "XX...........XX",
                    "XXX...XXX...XXX",
                    "XXXX.XXXXX.XXXX",
                    "XXXXXXXXXXXXXXX",
                    ".XXXXXXXXXXXXX.",
                    "..XXXXXXXXXXX..",
                    "....XXXXXXX....",
                    "......XXX......",
                    ".......X......."
                )
            )
            80 -> SilhouetteConfig(
                title = "Warm Coffee ☕",
                icon = "☕",
                width = 14,
                height = 14,
                targetArrows = 16,
                banner = "WARM COFFEE",
                maskPattern = listOf(
                    "...X..X..X...",
                    "...X..X..X...",
                    "XXXXXXXXXXXX.",
                    "XXXXXXXXXXXXX",
                    "XXXXXXXXXXXXX",
                    "XXXXXXXXXXXXX",
                    ".XXXXXXXXXXX.",
                    "..XXXXXXXXX..",
                    "....XXXXX....",
                    "...XXXXXXX..."
                )
            )
            120 -> SilhouetteConfig(
                title = "Sacred Lotus 🌸",
                icon = "🌸",
                width = 15,
                height = 14,
                targetArrows = 22,
                banner = "SACRED LOTUS",
                maskPattern = listOf(
                    ".......X.......",
                    "......XXX......",
                    ".....XXXXX.....",
                    "...XXXXXXXXX...",
                    "..XXXXXXXXXXX..",
                    ".XXXXXXXXXXXXX.",
                    "XXXXXXXXXXXXXXX",
                    ".XXXXXXXXXXXXX.",
                    "...XXXXXXXXX..."
                )
            )
            150 -> SilhouetteConfig(
                title = "Compass Star 🧭",
                icon = "🧭",
                width = 15,
                height = 15,
                targetArrows = 22,
                banner = "COMPASS STAR",
                maskPattern = listOf(
                    ".......X.......",
                    "......XXX......",
                    ".....XXXXX.....",
                    "....XXXXXXX....",
                    "XXXXXXXXXXXXXXX",
                    ".XXXXXXXXXXXXX.",
                    "..XXXXXXXXXXX..",
                    "...XXXXXXXXX...",
                    "....XXXXXXX....",
                    ".....XXXXX.....",
                    "......XXX......",
                    ".......X......."
                )
            )
            else -> null
        }
    }

    private fun generateMaskedArrowSet(
        config: SilhouetteConfig,
        random: Random
    ): List<ArrowItem> {
        val validCells = mutableSetOf<GridPoint>()
        for (y in config.maskPattern.indices) {
            val line = config.maskPattern[y]
            for (x in line.indices) {
                if (line[x] == 'X') {
                    validCells.add(GridPoint(x + 1, y + 1))
                }
            }
        }

        val occupiedCells = mutableSetOf<GridPoint>()
        val arrows = mutableListOf<ArrowItem>()
        val directions = listOf(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)
        var attempts = 0
        var arrowId = 1

        while (arrows.size < config.targetArrows && attempts < 400) {
            attempts++
            val exitDir = directions[random.nextInt(directions.size)]

            // Pick head in valid silhouette area that has clear exit
            val headCandidate = pickClearMaskedHead(
                config.width,
                config.height,
                exitDir,
                validCells,
                occupiedCells,
                random
            ) ?: continue

            val waypoints = buildBackwardPathWithinMask(
                headCandidate,
                exitDir,
                config.width,
                config.height,
                validCells,
                occupiedCells,
                random
            )

            if (waypoints.size < 2) continue

            val arrowPoints = waypoints.reversed()
            val newArrow = ArrowItem(
                id = arrowId++,
                points = arrowPoints,
                headDirection = exitDir,
                colorIndex = arrows.size % 4
            )

            occupiedCells.addAll(newArrow.allOccupiedCells())
            arrows.add(newArrow)
        }

        if (arrows.isEmpty()) {
            return generateSolvableArrowSet(config.width, config.height, config.targetArrows, random)
        }

        return arrows.mapIndexed { index, arrow -> arrow.copy(id = index + 1) }
    }

    private fun pickClearMaskedHead(
        gridWidth: Int,
        gridHeight: Int,
        exitDir: Direction,
        validCells: Set<GridPoint>,
        occupiedCells: Set<GridPoint>,
        random: Random
    ): GridPoint? {
        val candidates = mutableListOf<GridPoint>()
        for (pt in validCells) {
            if (pt in occupiedCells) continue

            var ray = pt.plus(exitDir)
            var clear = true
            while (ray.x in 0 until gridWidth && ray.y in 0 until gridHeight) {
                if (ray in occupiedCells) {
                    clear = false
                    break
                }
                ray = ray.plus(exitDir)
            }
            if (clear) candidates.add(pt)
        }
        return if (candidates.isNotEmpty()) candidates[random.nextInt(candidates.size)] else null
    }

    private fun buildBackwardPathWithinMask(
        head: GridPoint,
        exitDir: Direction,
        gridWidth: Int,
        gridHeight: Int,
        validCells: Set<GridPoint>,
        occupiedCells: Set<GridPoint>,
        random: Random
    ): List<GridPoint> {
        val pathWaypoints = mutableListOf<GridPoint>()
        pathWaypoints.add(head)

        val localOccupied = occupiedCells.toMutableSet()
        localOccupied.add(head)

        val backwardDir = when (exitDir) {
            Direction.UP -> Direction.DOWN
            Direction.DOWN -> Direction.UP
            Direction.LEFT -> Direction.RIGHT
            Direction.RIGHT -> Direction.LEFT
        }

        val firstLen = 1 + random.nextInt(3)
        var current = head
        for (i in 0 until firstLen) {
            val next = current.plus(backwardDir)
            if (next !in validCells || next in localOccupied) break
            current = next
            localOccupied.add(current)
        }

        if (current == head) return emptyList()
        pathWaypoints.add(current)

        val numTurns = 1 + random.nextInt(2)
        var lastDir = backwardDir

        for (t in 0 until numTurns) {
            val turnDirs = if (lastDir == Direction.UP || lastDir == Direction.DOWN) {
                listOf(Direction.LEFT, Direction.RIGHT)
            } else {
                listOf(Direction.UP, Direction.DOWN)
            }
            val turnDir = turnDirs[random.nextInt(turnDirs.size)]
            val segLen = 1 + random.nextInt(3)

            var segCurrent = current
            for (step in 0 until segLen) {
                val next = segCurrent.plus(turnDir)
                if (next !in validCells || next in localOccupied) break
                segCurrent = next
                localOccupied.add(segCurrent)
            }

            if (segCurrent != current) {
                current = segCurrent
                pathWaypoints.add(current)
                lastDir = turnDir
            } else {
                break
            }
        }

        return pathWaypoints
    }

    private fun generateSolvableArrowSet(
        gridWidth: Int,
        gridHeight: Int,
        count: Int,
        random: Random
    ): List<ArrowItem> {
        val occupiedCells = mutableSetOf<GridPoint>()
        val arrows = mutableListOf<ArrowItem>()

        val directions = listOf(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)

        var attempts = 0
        var arrowId = 1

        while (arrows.size < count && attempts < 350) {
            attempts++

            val exitDir = directions[random.nextInt(directions.size)]

            val headCandidate = pickClearHead(gridWidth, gridHeight, exitDir, occupiedCells, random)
                ?: continue

            val waypoints = buildBackwardPath(headCandidate, exitDir, gridWidth, gridHeight, occupiedCells, random)
            if (waypoints.size < 2) continue

            val arrowPoints = waypoints.reversed()
            val newArrow = ArrowItem(
                id = arrowId++,
                points = arrowPoints,
                headDirection = exitDir,
                colorIndex = arrows.size % 4
            )

            val newCells = newArrow.allOccupiedCells()
            occupiedCells.addAll(newCells)
            arrows.add(newArrow)
        }

        return arrows.mapIndexed { index, arrow ->
            arrow.copy(id = index + 1)
        }
    }

    private fun pickClearHead(
        gridWidth: Int,
        gridHeight: Int,
        exitDir: Direction,
        occupiedCells: Set<GridPoint>,
        random: Random
    ): GridPoint? {
        val candidates = mutableListOf<GridPoint>()

        for (x in 1 until gridWidth - 1) {
            for (y in 1 until gridHeight - 1) {
                val pt = GridPoint(x, y)
                if (pt in occupiedCells) continue

                var ray = pt.plus(exitDir)
                var clear = true
                while (ray.x in 0 until gridWidth && ray.y in 0 until gridHeight) {
                    if (ray in occupiedCells) {
                        clear = false
                        break
                    }
                    ray = ray.plus(exitDir)
                }

                if (clear) {
                    candidates.add(pt)
                }
            }
        }

        return if (candidates.isNotEmpty()) candidates[random.nextInt(candidates.size)] else null
    }

    private fun buildBackwardPath(
        head: GridPoint,
        exitDir: Direction,
        gridWidth: Int,
        gridHeight: Int,
        occupiedCells: Set<GridPoint>,
        random: Random
    ): List<GridPoint> {
        val pathWaypoints = mutableListOf<GridPoint>()
        pathWaypoints.add(head)

        val localOccupied = occupiedCells.toMutableSet()
        localOccupied.add(head)

        val backwardDir = when (exitDir) {
            Direction.UP -> Direction.DOWN
            Direction.DOWN -> Direction.UP
            Direction.LEFT -> Direction.RIGHT
            Direction.RIGHT -> Direction.LEFT
        }

        val firstLen = 1 + random.nextInt(3)
        var current = head
        for (i in 0 until firstLen) {
            val next = current.plus(backwardDir)
            if (next.x !in 1 until gridWidth - 1 || next.y !in 1 until gridHeight - 1 || next in localOccupied) {
                break
            }
            current = next
            localOccupied.add(current)
        }

        if (current == head) return emptyList()
        pathWaypoints.add(current)

        val numTurns = 1 + random.nextInt(3)
        var lastDir = backwardDir

        for (t in 0 until numTurns) {
            val turnDirs = if (lastDir == Direction.UP || lastDir == Direction.DOWN) {
                listOf(Direction.LEFT, Direction.RIGHT)
            } else {
                listOf(Direction.UP, Direction.DOWN)
            }
            val turnDir = turnDirs[random.nextInt(turnDirs.size)]
            val segLen = 1 + random.nextInt(4)

            var segCurrent = current
            for (step in 0 until segLen) {
                val next = segCurrent.plus(turnDir)
                if (next.x !in 1 until gridWidth - 1 || next.y !in 1 until gridHeight - 1 || next in localOccupied) {
                    break
                }
                segCurrent = next
                localOccupied.add(segCurrent)
            }

            if (segCurrent != current) {
                current = segCurrent
                pathWaypoints.add(current)
                lastDir = turnDir
            } else {
                break
            }
        }

        return pathWaypoints
    }
}

