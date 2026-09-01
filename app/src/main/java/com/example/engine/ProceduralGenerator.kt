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

        var arrows: List<ArrowItem> = emptyList()
        var attempts = 0

        while (attempts < 6) {
            val iterRandom = Random(levelNumber.toLong() * 31337L + 7919L + attempts * 10007L)
            val candidateArrows = if (silhouetteMask != null && attempts < 2) {
                generateMaskedArrowSet(silhouetteMask, iterRandom)
            } else {
                generateSolvableArrowSet(width, height, targetArrows, iterRandom)
            }

            if (candidateArrows.isNotEmpty() && validateArrowSet(candidateArrows, width, height)) {
                arrows = candidateArrows
                break
            }
            attempts++
        }

        if (arrows.isEmpty() || !validateArrowSet(arrows, width, height)) {
            arrows = generateDeterministicFallback(levelNumber, width, height, targetArrows)
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
            90 -> SilhouetteConfig(
                title = "Origami Swan 🦢",
                icon = "🦢",
                width = 15,
                height = 14,
                targetArrows = 20,
                banner = "ORIGAMI SWAN",
                maskPattern = listOf(
                    ".......XX......",
                    "......XXXX.....",
                    ".....XX..XX....",
                    "....XX....X....",
                    "...XX..........",
                    "..XXXXXXXXXXX..",
                    ".XXXXXXXXXXXXX.",
                    "XXXXXXXXXXXXXXX",
                    ".XXXXXXXXXXXX..",
                    "..XXXXXXXXXX...",
                    "...XXXXXXXX...."
                )
            )
            180 -> SilhouetteConfig(
                title = "Crescent Moon 🌙",
                icon = "🌙",
                width = 15,
                height = 15,
                targetArrows = 22,
                banner = "CRESCENT MOON",
                maskPattern = listOf(
                    ".....XXXXX.....",
                    "...XXXXXXXX....",
                    "..XXXXX........",
                    ".XXXXX.........",
                    "XXXXX..........",
                    "XXXXX..........",
                    "XXXXX..........",
                    "XXXXX..........",
                    ".XXXXX.........",
                    "..XXXXX........",
                    "...XXXXXXXX....",
                    ".....XXXXX....."
                )
            )
            250 -> SilhouetteConfig(
                title = "Phoenix Ascending 🔥",
                icon = "🔥",
                width = 16,
                height = 16,
                targetArrows = 26,
                banner = "PHOENIX ASCENT",
                maskPattern = listOf(
                    ".......XX.......",
                    "......XXXX......",
                    "X....XXXXXX....X",
                    "XX..XXXXXXXX..XX",
                    "XXX.XXXXXXXX.XXX",
                    "XXXXXXXXXXXXXXXX",
                    ".XXXXXXXXXXXXXX.",
                    "..XXXXXXXXXXXX..",
                    "...XXXXXXXXXX...",
                    "....XXXXXXXX....",
                    ".....XXXXXX.....",
                    "......XXXX......",
                    ".......XX.......",
                    "......XXXX......",
                    ".....XX..XX....."
                )
            )
            300 -> SilhouetteConfig(
                title = "Mythic Dragon 🐉",
                icon = "🐉",
                width = 16,
                height = 16,
                targetArrows = 28,
                banner = "MYTHIC DRAGON",
                maskPattern = listOf(
                    "...XXXXX........",
                    "..XXXXXXX.......",
                    ".XXXXXXXX.......",
                    "...XXXXXXX......",
                    "....XXXXXXXX....",
                    ".....XXXXXXXX...",
                    "......XXXXXXXX..",
                    "..XX...XXXXXXXX.",
                    ".XXXX...XXXXXXXX",
                    "XXXXX....XXXXXXX",
                    ".XXXXX....XXXXX.",
                    "..XXXXX...XXXX..",
                    "...XXXXXXXXXXX..",
                    "....XXXXXXXXX...",
                    ".....XXXXXXX...."
                )
            )
            500 -> SilhouetteConfig(
                title = "Celestial Castle 🏰",
                icon = "🏰",
                width = 16,
                height = 16,
                targetArrows = 30,
                banner = "STAR CITADEL",
                maskPattern = listOf(
                    "X..X..XXXX..X..X",
                    "X..X..XXXX..X..X",
                    "XXXX..XXXX..XXXX",
                    "XXXXXXXXXXXXXXXX",
                    "XXXXXXXXXXXXXXXX",
                    ".XXXXXXXXXXXXXX.",
                    ".XXXXXXXXXXXXXX.",
                    "XXXXXXXXXXXXXXXX",
                    "XXXXXX....XXXXXX",
                    "XXXXXX....XXXXXX",
                    "XXXXXXXXXXXXXXXX",
                    "XXXXXXXXXXXXXXXX"
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

    /**
     * Validates that all arrows have valid orthogonal geometry, lie strictly within board bounds,
     * do not overlap each other in the initial layout, and that the puzzle is 100% solvable.
     */
    fun validateArrowSet(arrows: List<ArrowItem>, gridWidth: Int, gridHeight: Int): Boolean {
        if (arrows.isEmpty()) return false
        val allOccupied = mutableSetOf<GridPoint>()

        for (arrow in arrows) {
            if (arrow.points.size < 2) return false

            // Verify orthogonal connectivity
            for (i in 0 until arrow.points.size - 1) {
                val p1 = arrow.points[i]
                val p2 = arrow.points[i + 1]
                if (p1.x != p2.x && p1.y != p2.y) return false
                if (p1 == p2) return false
            }

            val cells = arrow.allOccupiedCells()
            if (cells.isEmpty()) return false

            // Verify bounds and non-overlapping condition
            for (cell in cells) {
                if (cell.x < 0 || cell.x >= gridWidth || cell.y < 0 || cell.y >= gridHeight) {
                    return false
                }
                if (cell in allOccupied) {
                    return false
                }
                allOccupied.add(cell)
            }
        }

        return PuzzleSolver.isSolvable(arrows, gridWidth, gridHeight)
    }

    /**
     * Produces a guaranteed geometrically valid and solvable deterministic fallback puzzle.
     */
    fun generateDeterministicFallback(
        levelNumber: Int,
        gridWidth: Int,
        gridHeight: Int,
        targetCount: Int
    ): List<ArrowItem> {
        val arrows = mutableListOf<ArrowItem>()
        val count = targetCount.coerceIn(3, (gridHeight / 2).coerceAtLeast(3))
        var id = 1

        for (i in 0 until count) {
            val y = 1 + (i * 2)
            if (y >= gridHeight - 1) break
            val isEven = i % 2 == 0
            val startX = if (isEven) 1 else gridWidth - 2
            val endX = if (isEven) gridWidth - 2 else 1
            val dir = if (isEven) Direction.RIGHT else Direction.LEFT

            arrows.add(
                ArrowItem(
                    id = id++,
                    points = listOf(GridPoint(startX, y), GridPoint(endX, y)),
                    headDirection = dir,
                    colorIndex = (id - 1) % 4
                )
            )
        }

        if (arrows.isEmpty()) {
            arrows.add(
                ArrowItem(
                    id = 1,
                    points = listOf(GridPoint(1, 1), GridPoint(gridWidth - 2, 1)),
                    headDirection = Direction.RIGHT,
                    colorIndex = 0
                )
            )
        }

        return arrows
    }
}

