package com.example.engine

import com.example.model.ArrowItem
import com.example.model.CollisionInfo
import com.example.model.Direction
import com.example.model.GridPoint

object PuzzleSolver {

    /**
     * Maps each grid point to the ArrowItem that currently occupies it.
     */
    fun buildOccupancyMap(activeArrows: List<ArrowItem>): Map<GridPoint, ArrowItem> {
        val map = mutableMapOf<GridPoint, ArrowItem>()
        for (arrow in activeArrows) {
            val cells = arrow.allOccupiedCells()
            for (cell in cells) {
                map[cell] = arrow
            }
        }
        return map
    }

    /**
     * Checks if the given arrow can exit freely without colliding with any other arrow.
     * Returns null if free to exit, or CollisionInfo if blocked.
     * If arrow.isGhost is true, it passes through obstacles without collision.
     */
    fun checkCollision(
        arrow: ArrowItem,
        activeArrows: List<ArrowItem>,
        gridWidth: Int,
        gridHeight: Int
    ): CollisionInfo? {
        if (arrow.isGhost) {
            return null // Ethereal phase passes freely!
        }

        val occupancy = buildOccupancyMap(activeArrows)
        val dir = arrow.headDirection
        var current = arrow.head.plus(dir)

        val maxSteps = maxOf(gridWidth, gridHeight) * 2 + 5
        var steps = 0

        while (steps < maxSteps) {
            val blocker = occupancy[current]
            if (blocker != null && blocker.id != arrow.id) {
                // Collided with another arrow!
                return CollisionInfo(
                    blockedArrowId = arrow.id,
                    blockedAtPoint = current,
                    collidingWithArrowId = blocker.id
                )
            }

            // Check if we have fully exited the board bounds
            if (current.x < -1 || current.x > gridWidth || current.y < -1 || current.y > gridHeight) {
                // Safely exited the board!
                break
            }

            current = current.plus(dir)
            steps++
        }

        return null // No collision, clear to exit
    }

    /**
     * Finds all arrows that are currently unblocked and can be tapped to clear.
     */
    fun findUnblockedArrows(
        activeArrows: List<ArrowItem>,
        gridWidth: Int,
        gridHeight: Int
    ): List<ArrowItem> {
        return activeArrows.filter { arrow ->
            checkCollision(arrow, activeArrows, gridWidth, gridHeight) == null
        }
    }

    /**
     * Splits a long arrow (with multiple waypoints or length >= 3) into two shorter arrows for Zen Snip.
     */
    fun splitArrow(arrow: ArrowItem, nextId: Int): List<ArrowItem>? {
        if (arrow.points.size >= 3) {
            val midIdx = arrow.points.size / 2
            val firstSegmentPoints = arrow.points.subList(0, midIdx + 1)
            val secondSegmentPoints = arrow.points.subList(midIdx, arrow.points.size)

            val dir1 = Direction.fromDelta(
                firstSegmentPoints.last().x - firstSegmentPoints[firstSegmentPoints.size - 2].x,
                firstSegmentPoints.last().y - firstSegmentPoints[firstSegmentPoints.size - 2].y
            )

            val arrow1 = ArrowItem(
                id = arrow.id,
                points = firstSegmentPoints,
                headDirection = dir1,
                colorIndex = arrow.colorIndex
            )

            val arrow2 = ArrowItem(
                id = nextId,
                points = secondSegmentPoints,
                headDirection = arrow.headDirection,
                colorIndex = (arrow.colorIndex + 1) % 4
            )

            return listOf(arrow1, arrow2)
        } else if (arrow.points.size == 2) {
            val p1 = arrow.points[0]
            val p2 = arrow.points[1]
            val dx = p2.x - p1.x
            val dy = p2.y - p1.y
            val dist = maxOf(kotlin.math.abs(dx), kotlin.math.abs(dy))
            if (dist >= 3) {
                val midX = p1.x + dx / 2
                val midY = p1.y + dy / 2
                val midPt = GridPoint(midX, midY)

                val arrow1 = ArrowItem(
                    id = arrow.id,
                    points = listOf(p1, midPt),
                    headDirection = arrow.headDirection,
                    colorIndex = arrow.colorIndex
                )
                val arrow2 = ArrowItem(
                    id = nextId,
                    points = listOf(midPt, p2),
                    headDirection = arrow.headDirection,
                    colorIndex = (arrow.colorIndex + 1) % 4
                )
                return listOf(arrow1, arrow2)
            }
        }
        return null
    }

    /**
     * Finds an unblocked arrow for the hint system.
     */
    fun getHintArrow(
        activeArrows: List<ArrowItem>,
        gridWidth: Int,
        gridHeight: Int
    ): ArrowItem? {
        val unblocked = findUnblockedArrows(activeArrows, gridWidth, gridHeight)
        return unblocked.firstOrNull()
    }

    /**
     * Verifies if the puzzle is 100% solvable from its initial state to completion.
     */
    fun isSolvable(
        initialArrows: List<ArrowItem>,
        gridWidth: Int,
        gridHeight: Int
    ): Boolean {
        var remaining = initialArrows.toList()
        while (remaining.isNotEmpty()) {
            val unblocked = findUnblockedArrows(remaining, gridWidth, gridHeight)
            if (unblocked.isEmpty()) {
                return false
            }
            // Clear the first unblocked arrow
            val toRemove = unblocked.first()
            remaining = remaining.filter { it.id != toRemove.id }
        }
        return true
    }

    data class PuzzleMetrics(
        val solvable: Boolean,
        val minimumMoves: Int,
        val maximumSearchDepth: Int,
        val forcedMoveCount: Int,
        val decisionCount: Int,
        val initialLegalMoves: Int,
        val criticalPath: Int,
        val branchingFactor: Float,
        val deadEndCount: Int,
        val dependencyDepth: Int,
        val initiallyUnblocked: Int = initialLegalMoves,
        val maxBlockingChain: Int = dependencyDepth,
        val arrowCount: Int = if (solvable) minimumMoves else 0,
        val solutionMoves: Int = arrowCount,
        val averageRouteLength: Float = 0f,
        val longestRoute: Float = 0f
    )

    fun buildDependencyGraph(
        arrows: List<ArrowItem>,
        gridWidth: Int,
        gridHeight: Int
    ): Map<Int, Set<Int>> {
        val graph = mutableMapOf<Int, Set<Int>>()
        val occupancy = buildOccupancyMap(arrows)
        
        for (arrow in arrows) {
            val blockedBy = mutableSetOf<Int>()
            val dir = arrow.headDirection
            var current = arrow.head.plus(dir)
            val maxSteps = maxOf(gridWidth, gridHeight) * 2 + 5
            var steps = 0
            
            while (steps < maxSteps) {
                if (current.x < 0 || current.x >= gridWidth || current.y < 0 || current.y >= gridHeight) {
                    break
                }
                val blocker = occupancy[current]
                if (blocker != null && blocker.id != arrow.id) {
                    blockedBy.add(blocker.id)
                }
                current = current.plus(dir)
                steps++
            }
            graph[arrow.id] = blockedBy
        }
        return graph
    }

    fun getLongestPathLength(graph: Map<Int, Set<Int>>): Int {
        val memo = mutableMapOf<Int, Int>()
        val visiting = mutableSetOf<Int>()
        var cycleDetected = false
        
        fun dfs(node: Int): Int {
            if (cycleDetected) return 0
            if (node in memo) return memo[node]!!
            if (node in visiting) {
                cycleDetected = true
                return 0
            }
            visiting.add(node)
            val neighbors = graph[node] ?: emptySet()
            var maxSub = 0
            for (neighbor in neighbors) {
                maxSub = maxOf(maxSub, 1 + dfs(neighbor))
            }
            visiting.remove(node)
            memo[node] = maxSub
            return maxSub
        }
        
        var maxPath = 0
        for (node in graph.keys) {
            maxPath = maxOf(maxPath, dfs(node))
            if (cycleDetected) return -1
        }
        return maxPath
    }

    fun analyzePuzzle(
        initialArrows: List<ArrowItem>,
        gridWidth: Int,
        gridHeight: Int
    ): PuzzleMetrics {
        val graph = buildDependencyGraph(initialArrows, gridWidth, gridHeight)
        val criticalPath = getLongestPathLength(graph)
        val initialIds = initialArrows.map { it.id }.toSet()
        
        val visited = mutableSetOf<Set<Int>>()
        val queue = ArrayDeque<Set<Int>>()
        
        queue.add(initialIds)
        visited.add(initialIds)
        
        var solvable = criticalPath >= 0
        var maxDepthExplored = 0
        var forcedMoveCount = 0
        var decisionCount = 0
        var deadEndCount = 0
        var totalBranching = 0
        var reachableNonEmptyCount = 0
        
        val maxStatesLimit = 5000
        
        while (queue.isNotEmpty() && visited.size < maxStatesLimit) {
            val state = queue.removeFirst()
            
            if (state.isEmpty()) {
                solvable = true
                continue
            }
            
            val unblocked = state.filter { id ->
                val blockers = graph[id] ?: emptySet()
                blockers.none { it in state }
            }
            
            reachableNonEmptyCount++
            totalBranching += unblocked.size
            
            when {
                unblocked.isEmpty() -> deadEndCount++
                unblocked.size == 1 -> forcedMoveCount++
                unblocked.size >= 2 -> decisionCount++
            }
            
            val depth = initialIds.size - state.size
            if (depth > maxDepthExplored) {
                maxDepthExplored = depth
            }
            
            for (id in unblocked) {
                val nextState = state.filter { it != id }.toSet()
                if (nextState !in visited) {
                    visited.add(nextState)
                    queue.add(nextState)
                }
            }
        }
        
        val initialUnblocked = initialIds.filter { id ->
            val blockers = graph[id] ?: emptySet()
            blockers.none { it in initialIds }
        }.size
        
        val avgBranchingFactor = if (reachableNonEmptyCount > 0) {
            totalBranching.toFloat() / reachableNonEmptyCount
        } else {
            0f
        }
        
        val avgLen = if (initialArrows.isNotEmpty()) initialArrows.map { it.totalLength() }.average().toFloat() else 0f
        val maxLen = if (initialArrows.isNotEmpty()) initialArrows.map { it.totalLength() }.maxOrNull() ?: 0f else 0f
        val dependencyDepth = if (criticalPath >= 0) criticalPath else 0
        
        return PuzzleMetrics(
            solvable = solvable,
            minimumMoves = if (solvable) initialArrows.size else -1,
            maximumSearchDepth = maxDepthExplored,
            forcedMoveCount = forcedMoveCount,
            decisionCount = decisionCount,
            initialLegalMoves = initialUnblocked,
            criticalPath = dependencyDepth,
            branchingFactor = avgBranchingFactor,
            deadEndCount = deadEndCount,
            dependencyDepth = dependencyDepth,
            averageRouteLength = avgLen,
            longestRoute = maxLen
        )
    }
}