import re

with open("app/src/main/java/com/example/engine/PuzzleSolver.kt", "r") as f:
    content = f.read()

# Remove the appended stuff if it exists outside
content = re.sub(r"data class PuzzleMetrics.*", "", content, flags=re.DOTALL)

# Now insert it inside PuzzleSolver object
metrics_code = """
    data class PuzzleMetrics(
        val solvable: Boolean,
        val solutionMoves: Int,
        val dependencyDepth: Int,
        val initiallyUnblocked: Int,
        val maxBlockingChain: Int,
        val arrowCount: Int,
        val averageRouteLength: Float,
        val longestRoute: Float,
        val branchingFactor: Float
    )

    fun analyzePuzzle(
        initialArrows: List<ArrowItem>,
        gridWidth: Int,
        gridHeight: Int
    ): PuzzleMetrics {
        var remaining = initialArrows.toList()
        var dependencyDepth = 0
        var solutionMoves = 0
        var initiallyUnblocked = 0
        var maxBranching = 0
        val totalArrows = initialArrows.size
        
        var solvable = true
        
        while (remaining.isNotEmpty()) {
            val unblocked = findUnblockedArrows(remaining, gridWidth, gridHeight)
            if (unblocked.isEmpty()) {
                solvable = false
                break
            }
            if (dependencyDepth == 0) {
                initiallyUnblocked = unblocked.size
            }
            maxBranching = maxOf(maxBranching, unblocked.size)
            
            // Remove all unblocked (to calculate depth of dependencies)
            val unblockedIds = unblocked.map { it.id }.toSet()
            remaining = remaining.filter { it.id !in unblockedIds }
            dependencyDepth++
            solutionMoves += unblocked.size
        }
        
        val avgLen = if (initialArrows.isNotEmpty()) initialArrows.map { it.totalLength() }.average().toFloat() else 0f
        val maxLen = if (initialArrows.isNotEmpty()) initialArrows.map { it.totalLength() }.maxOrNull() ?: 0f else 0f

        return PuzzleMetrics(
            solvable = solvable,
            solutionMoves = totalArrows,
            dependencyDepth = dependencyDepth,
            initiallyUnblocked = initiallyUnblocked,
            maxBlockingChain = dependencyDepth, 
            arrowCount = totalArrows,
            averageRouteLength = avgLen,
            longestRoute = maxLen,
            branchingFactor = maxBranching.toFloat()
        )
    }
}"""

content = content.rstrip().rstrip('}') + metrics_code

with open("app/src/main/java/com/example/engine/PuzzleSolver.kt", "w") as f:
    f.write(content)
