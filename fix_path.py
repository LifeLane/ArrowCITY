import re

with open("app/src/main/java/com/example/engine/ProceduralGenerator.kt", "r") as f:
    content = f.read()

find = """            val turnDirs = if (lastDir == Direction.UP || lastDir == Direction.DOWN) {
                listOf(Direction.LEFT, Direction.RIGHT)
            } else {
                listOf(Direction.UP, Direction.DOWN)
            }
            val turnDir = turnDirs[random.nextInt(turnDirs.size)]"""

replace = """            val turnDirs = if (lastDir == Direction.UP || lastDir == Direction.DOWN) {
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
            }"""

if find in content:
    content = content.replace(find, replace)
else:
    print("Not found turnDirs")

with open("app/src/main/java/com/example/engine/ProceduralGenerator.kt", "w") as f:
    f.write(content)
