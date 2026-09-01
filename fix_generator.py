import re

with open("app/src/main/java/com/example/engine/ProceduralGenerator.kt", "r") as f:
    content = f.read()

# Replace generateLevel logic with metrics check
new_generate_level = """    fun generateLevel(levelNumber: Int): LevelData {
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
            subtitle = bannerText,
            gridWidth = width,
            gridHeight = height,
            arrows = arrows,
            maxDrops = city.dropLimitRange.last,
            backgroundId = city.backgroundId,
            gridStyleId = city.gridStyleId,
            ambientSoundId = city.ambientSoundId
        )
    }"""

content = re.sub(r"    fun generateLevel\(levelNumber: Int\): LevelData \{.*?        \)\n    \}", new_generate_level, content, flags=re.DOTALL)

with open("app/src/main/java/com/example/engine/ProceduralGenerator.kt", "w") as f:
    f.write(content)

