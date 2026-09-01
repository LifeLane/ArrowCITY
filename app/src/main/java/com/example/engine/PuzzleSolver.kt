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
}

