package com.example.engine

import com.example.model.ArrowItem
import com.example.model.CityConfig
import com.example.model.CityRepository
import com.example.model.Direction
import com.example.model.GridPoint
import com.example.model.LevelData
import java.util.Random

object ProceduralGenerator {

    private const val GENERATION_VERSION = 2L

    /**
     * Generates a deterministic, guaranteed-solvable level based on level number and city config.
     */
    fun generateLevel(levelNumber: Int): LevelData {
        val city = CityRepository.getCityForLevel(levelNumber)
        val routeInCity = CityRepository.getRouteNumberInCity(levelNumber)
        val seed = (GENERATION_VERSION * 1000003L + city.id.toLong() * 65537L + levelNumber.toLong() * 31337L + 7919L)

        val progress = (routeInCity - 1).toFloat() / 19f
        val width = city.gridWidthRange.first + ((city.gridWidthRange.last - city.gridWidthRange.first) * progress).toInt()
        val height = city.gridHeightRange.first + ((city.gridHeightRange.last - city.gridHeightRange.first) * progress).toInt()
        val targetArrows = city.arrowCountRange.first + ((city.arrowCountRange.last - city.arrowCountRange.first) * progress).toInt()

        var arrows: List<ArrowItem> = emptyList()
        var attempts = 0
        val maxAttempts = 50

        while (attempts < maxAttempts) {
            val iterSeed = seed + attempts * 10007L
            val iterRandom = java.util.Random(iterSeed)

            val candidate = generateCityConstrainedArrowSet(
                city = city,
                gridWidth = width,
                gridHeight = height,
                targetCount = targetArrows,
                random = iterRandom
            )

            if (candidate.isNotEmpty() && validateArrowSet(candidate, width, height)) {
                val metrics = PuzzleSolver.analyzePuzzle(candidate, width, height)
                
                // Difficulty and quality checks
                if (metrics.solvable && metrics.dependencyDepth >= 1 && metrics.initiallyUnblocked > 0) {
                    arrows = candidate
                    break
                }
            }
            attempts++
        }

        if (arrows.isEmpty() || !PuzzleSolver.analyzePuzzle(arrows, width, height).solvable) {
            val fallback = generateDeterministicCityFallback(city, levelNumber, width, height, targetArrows)
            val fallbackMetrics = PuzzleSolver.analyzePuzzle(fallback, width, height)
            if (fallbackMetrics.solvable) {
                arrows = fallback
            } else {
                // If even fallback fails (which it shouldn't, but for safety), use a known safe curated level
                arrows = CuratedLevels.curatedMap[1]?.arrows ?: emptyList()
                android.util.Log.e("ProceduralGenerator", "Generation failure for Level $levelNumber in City ${city.id}. Attempted $attempts times. Falling back to Level 1.")
            }
        }

        val levelTitle = generateRouteTitle(city, routeInCity, levelNumber)
        val bannerText = generateBannerText(city, routeInCity)

        return LevelData(
            levelNumber = levelNumber,
            title = levelTitle,
            bannerText = bannerText,
            gridWidth = width,
            gridHeight = height,
            arrows = arrows,
            maxDrops = 3
        )
    }

    /**
     * Generates a high-density, guaranteed-solvable anchor / silhouette level for city milestones.
     */
    fun generateAnchorLevel(levelNumber: Int): LevelData {
        val city = CityRepository.getCityForLevel(levelNumber)
        val routeInCity = CityRepository.getRouteNumberInCity(levelNumber)
        val silhouette = LevelRepository.silhouetteLevels.find { it.levelNumber == levelNumber }
        val seed = (GENERATION_VERSION * 2000003L + city.id.toLong() * 99991L + levelNumber.toLong() * 65537L + 1234567L)
        val random = Random(seed)

        val width = city.gridWidthRange.last
        val height = city.gridHeightRange.last
        val targetArrows = city.arrowCountRange.last

        var arrows: List<ArrowItem> = emptyList()
        var attempts = 0
        val maxAttempts = 12

        while (attempts < maxAttempts) {
            val iterSeed = seed + attempts * 10007L
            val iterRandom = Random(iterSeed)

            val candidate = generateCityConstrainedArrowSet(
                city = city,
                gridWidth = width,
                gridHeight = height,
                targetCount = targetArrows,
                random = iterRandom
            )

            if (candidate.isNotEmpty() && validateArrowSet(candidate, width, height)) {
                arrows = candidate
                break
            }
            attempts++
        }

        if (arrows.isEmpty() || !validateArrowSet(arrows, width, height)) {
            arrows = generateDeterministicCityFallback(city, levelNumber, width, height, targetArrows)
        }

        val icon = silhouette?.icon ?: city.icon
        val specificTitle = silhouette?.title ?: "Anchor Route $routeInCity"
        val levelTitle = "City ${city.id} • Route $routeInCity • $specificTitle $icon"
        val bannerText = silhouette?.title?.uppercase() ?: generateBannerText(city, routeInCity)

        return LevelData(
            levelNumber = levelNumber,
            title = levelTitle,
            gridWidth = width,
            gridHeight = height,
            arrows = arrows,
            maxDrops = if (targetArrows >= 18) 5 else if (targetArrows >= 12) 4 else 3,
            isSilhouette = true,
            silhouetteIcon = icon,
            bannerText = bannerText
        )
    }

    private fun generateRouteTitle(city: CityConfig, routeInCity: Int, levelNumber: Int): String {
        val titlesMap = mapOf(
            1 to listOf("First Steps", "Gentle Turn", "Quiet Path", "Inner Calm", "Zen Flow", "Twin Streams", "Breeze", "Clear Vision", "Stone Garden", "Trophy of Clarity", "Harmony", "Bamboo Lane", "Lantern Walk", "Lotus Pond", "Diamond Heart", "Morning Mist", "Silent Gate", "Purity", "Serenity", "Zendai Gate"),
            2 to listOf("First Wind", "Dune Horizon", "Golden Dust", "Sand Drift", "Dune Run", "Oasis Trace", "Sun Flare", "Desert Ridge", "Mirage Walk", "Wind Sweep", "Canyon Way", "Nomad Track", "Sunstone", "Dust Devil", "Playful Kitty", "Heat Haze", "Scorpion Pass", "Red Sands", "Sirocco", "Loyal Companion"),
            3 to listOf("Shallow Tide", "Coral Branch", "Ocean Drift", "Sea Ripple", "First Wave", "Harbor Light", "Azure Stream", "Currents", "Whirlpool", "Swimming Koi", "Deep Trench", "Reef Run", "Lagoon Bend", "Sailor's Path", "Tidal Fork", "Aquatic Maze", "Storm Surge", "Abyssal Wake", "Pearl Drift", "The Tide"),
            4 to listOf("Sprout", "Mossy Path", "Willow Branch", "Green Canopy", "Ancient Roots", "Timber Line", "Fern Grove", "Bark & Bough", "Forest Clearing", "Forest Canopy", "Bramble Maze", "Pine Needle", "Woodland Trail", "Deep Thicket", "Soaring Falcon", "River Crossing", "Elder Tree", "Emerald Grove", "Verdant Spire", "Warm Coffee"),
            5 to listOf("Spark", "Ash Field", "Cinder Trail", "Smoldering Way", "Ember Trail", "Basalt Path", "Lava Stream", "Furnace Run", "Sulfur Vent", "Origami Swan", "Pyroclast", "Igneous Maze", "Obsidian Spire", "Magma Tunnel", "Volcanic Core", "Heat Chamber", "Rift Valley", "Crater Edge", "Blazing Spiral", "The Volcano"),
            6 to listOf("Updraft", "Breeze Crest", "Zephyr Lane", "Stratus Walk", "Aerith Lift", "Cumulus Path", "Vapor Trail", "Floating Isle", "Cirrus Flow", "Sky Bridge", "Nimbus Gate", "Thermal Drift", "Skyline Maze", "Aurora View", "Cloud Garden", "Solar Sail", "High Altitude", "Wing Spire", "Aether Vortex", "Sacred Lotus"),
            7 to listOf("Quartz Shard", "Facet Lane", "Geode Trace", "Lustrous Way", "Crystal Path", "Specular Bend", "Prism Edge", "Reflecting Pool", "Glinting Spire", "Mirror Prism", "Diamond Matrix", "Beryl Corridor", "Sapphire Run", "Emerald Facet", "Prismatic Spire", "Resonance Chamber", "Starlight Crystal", "Chime Maze", "Crystalline Web", "The Crystal Core"),
            8 to listOf("Cog Tooth", "Axle Turn", "Camshaft Run", "Ratchet Lane", "Piston Drive", "Conveyor Line", "Sprocket Walk", "Steam Valve", "Clockwork Ring", "Compass Star", "Flywheel Path", "Hydraulic Gate", "Gimbal Trace", "Turret Maze", "Interlock Gear", "Pressure Tube", "Mainspring Run", "Escapement", "Chronometer", "The Grand Gear"),
            9 to listOf("Dawn Ray", "Gleam", "Beaming Path", "Luminous Lane", "First Light", "Halo Ring", "Prism Beam", "Spectral Trace", "Strobe Flow", "Solar Radiance", "Photon Gate", "Corona Way", "Incandescent Maze", "Glow Arbor", "Light Tree", "Solar Flare", "Laser Corridor", "Lustre Chamber", "Bioluminescence", "Crescent Moon"),
            10 to listOf("Mobius Path", "Ouroboros", "Vortex Walk", "Singularity", "Infinity Loop", "Tesseract", "Continuum", "Recursion", "Fractal Gate", "Time Maze", "Dimension Bend", "Temporal Flow", "Quantum Trace", "Chronos Loop", "Eternal Flow", "Event Horizon", "Cosmic Spiral", "Hypercube", "Omniverse", "The Infinity Maze")
        )

        val cityTitles = titlesMap[city.id]
        val specificTitle = if (cityTitles != null && routeInCity in 1..cityTitles.size) {
            cityTitles[routeInCity - 1]
        } else {
            "Route $routeInCity"
        }

        return "City ${city.id} • Route $routeInCity • $specificTitle"
    }

    private fun generateBannerText(city: CityConfig, routeInCity: Int): String {
        val banners = listOf(
            "TAP TO CLEAR",
            "EYE COMFORT",
            "CALM MIND",
            "QUIET FLOW",
            "BREATHE DEEP",
            "FOCUS & UNTANGLE",
            "MIND AT EASE",
            "BETTER SLEEP"
        )
        return banners[(city.id * 7 + routeInCity) % banners.size]
    }

    private fun generateCityConstrainedArrowSet(
        city: CityConfig,
        gridWidth: Int,
        gridHeight: Int,
        targetCount: Int,
        random: Random
    ): List<ArrowItem> {
        val occupiedCells = mutableSetOf<GridPoint>()
        val arrows = mutableListOf<ArrowItem>()
        val directions = listOf(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)

        var attempts = 0
        var arrowId = 1

        while (arrows.size < targetCount && attempts < 450) {
            attempts++

            // Apply direction preferences based on city profile
            val exitDir = if (city.verticalPreference > 0f && random.nextFloat() < city.verticalPreference) {
                if (random.nextBoolean()) Direction.UP else Direction.DOWN
            } else {
                directions[random.nextInt(directions.size)]
            }

            val headCandidate = pickClearHead(city, gridWidth, gridHeight, exitDir, occupiedCells, random)
                ?: continue

            val waypoints = buildBackwardPathWithCityStyle(
                city = city,
                head = headCandidate,
                exitDir = exitDir,
                gridWidth = gridWidth,
                gridHeight = gridHeight,
                occupiedCells = occupiedCells,
                random = random
            )

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
        city: CityConfig,
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

    private fun buildBackwardPathWithCityStyle(
        city: CityConfig,
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

        val firstLen = if (city.longSweepPreference > 0f && random.nextFloat() < city.longSweepPreference) {
            2 + random.nextInt(4)
        } else {
            1 + random.nextInt(3)
        }

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

        val maxTurns = city.maxTurnsRange.last.coerceAtLeast(1)
        val numTurns = 1 + random.nextInt(maxTurns)
        var lastDir = backwardDir

        for (t in 0 until numTurns) {
            val turnDirs = if (lastDir == Direction.UP || lastDir == Direction.DOWN) {
                listOf(Direction.LEFT, Direction.RIGHT)
            } else {
                listOf(Direction.UP, Direction.DOWN)
            }
            
            var turnDir = turnDirs[random.nextInt(turnDirs.size)]
            
            // Illusion preference: actively steer towards existing structures to trace them
            if (city.illusionPreference > 0f && random.nextFloat() < city.illusionPreference) {
                var bestDir = turnDir
                var maxAdj = -1
                for (dir in turnDirs) {
                    val testPt = current.plus(dir)
                    if (testPt.x in 1 until gridWidth - 1 && testPt.y in 1 until gridHeight - 1 && testPt !in localOccupied) {
                        val adjCount = listOf(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)
                            .count { testPt.plus(it) in occupiedCells }
                        if (adjCount > maxAdj) {
                            maxAdj = adjCount
                            bestDir = dir
                        }
                    }
                }
                if (maxAdj >= 0) {
                    turnDir = bestDir
                }
            }

            val segLen = if (city.longSweepPreference > 0f) {
                2 + random.nextInt(4)
            } else {
                1 + random.nextInt(3)
            }

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
     * Produces a guaranteed geometrically valid and solvable deterministic fallback puzzle tailored to the city.
     */
    fun generateDeterministicCityFallback(
        city: CityConfig,
        levelNumber: Int,
        gridWidth: Int,
        gridHeight: Int,
        targetCount: Int
    ): List<ArrowItem> {
        val arrows = mutableListOf<ArrowItem>()
        val count = targetCount.coerceIn(3, (gridHeight - 2).coerceAtLeast(3))
        var id = 1

        for (i in 0 until count) {
            val y = 1 + (i % (gridHeight - 2))
            val isEven = (i + levelNumber) % 2 == 0
            val startX = if (isEven) 1 else gridWidth - 2
            val endX = if (isEven) gridWidth - 2 else 1
            val dir = if (isEven) Direction.RIGHT else Direction.LEFT

            // Check if this horizontal line is already occupied
            val lineCells = (minOf(startX, endX)..maxOf(startX, endX)).map { GridPoint(it, y) }
            val existingCells = arrows.flatMap { it.allOccupiedCells() }.toSet()
            if (lineCells.none { it in existingCells }) {
                arrows.add(
                    ArrowItem(
                        id = id++,
                        points = listOf(GridPoint(startX, y), GridPoint(endX, y)),
                        headDirection = dir,
                        colorIndex = (id - 1) % 4
                    )
                )
            }
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
            arrows.add(
                ArrowItem(
                    id = 2,
                    points = listOf(GridPoint(gridWidth - 2, 3), GridPoint(1, 3)),
                    headDirection = Direction.LEFT,
                    colorIndex = 1
                )
            )
        }

        return arrows
    }
}
