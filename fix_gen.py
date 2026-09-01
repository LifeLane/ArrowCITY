import re

with open("app/src/main/java/com/example/engine/ProceduralGenerator.kt", "r") as f:
    content = f.read()

# Fix pickClearHead to take city as argument and use density/illusion
old_pick = """    private fun pickClearHead(
        gridWidth: Int,
        gridHeight: Int,
        exitDir: Direction,
        occupiedCells: Set<GridPoint>,
        random: Random
    ): GridPoint? {"""

new_pick = """    private fun pickClearHead(
        city: CityConfig,
        gridWidth: Int,
        gridHeight: Int,
        exitDir: Direction,
        occupiedCells: Set<GridPoint>,
        random: Random
    ): GridPoint? {"""
content = content.replace(old_pick, new_pick)

old_call = """val headCandidate = pickClearHead(gridWidth, gridHeight, exitDir, occupiedCells, random)"""
new_call = """val headCandidate = pickClearHead(city, gridWidth, gridHeight, exitDir, occupiedCells, random)"""
content = content.replace(old_call, new_call)

# Now rewrite pickClearHead body
old_body = """        val candidates = mutableListOf<GridPoint>()
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
        return if (candidates.isNotEmpty()) candidates[random.nextInt(candidates.size)] else null"""

new_body = """        val candidates = mutableListOf<GridPoint>()
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
        if (candidates.isEmpty()) return null
        
        // Apply illusion and density scoring
        if (city.illusionPreference > 0f || city.densityPreference > 0f) {
            val scored = candidates.map { pt ->
                var score = 0f
                // Check neighbors
                val neighbors = listOf(pt.plus(Direction.UP), pt.plus(Direction.DOWN), pt.plus(Direction.LEFT), pt.plus(Direction.RIGHT))
                val occupiedNeighbors = neighbors.count { it in occupiedCells }
                score += occupiedNeighbors * city.densityPreference * 10f
                
                // Illusion: Tucked in behind other arrows
                val backwardDir = when (exitDir) {
                    Direction.UP -> Direction.DOWN
                    Direction.DOWN -> Direction.UP
                    Direction.LEFT -> Direction.RIGHT
                    Direction.RIGHT -> Direction.LEFT
                }
                val behind = pt.plus(backwardDir)
                if (behind in occupiedCells) {
                    score += city.illusionPreference * 20f
                }
                
                Pair(pt, score)
            }
            // Sort descending by score, but add slight randomness
            val sorted = scored.sortedByDescending { it.second + random.nextFloat() * 5f }
            // Pick from the top tier
            val topPool = sorted.take(maxOf(1, sorted.size / 3)).map { it.first }
            return topPool[random.nextInt(topPool.size)]
        }
        
        return candidates[random.nextInt(candidates.size)]"""

content = content.replace(old_body, new_body)

with open("app/src/main/java/com/example/engine/ProceduralGenerator.kt", "w") as f:
    f.write(content)
