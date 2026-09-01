package com.example.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

enum class Direction(val dx: Int, val dy: Int, val angleDegrees: Float) {
    UP(0, -1, 270f),
    DOWN(0, 1, 90f),
    LEFT(-1, 0, 180f),
    RIGHT(1, 0, 0f);

    companion object {
        fun fromDelta(dx: Int, dy: Int): Direction {
            return when {
                dx > 0 -> RIGHT
                dx < 0 -> LEFT
                dy > 0 -> DOWN
                else -> UP
            }
        }
    }
}

data class GridPoint(val x: Int, val y: Int) {
    fun plus(dir: Direction): GridPoint = GridPoint(x + dir.dx, y + dir.dy)
    fun distanceTo(other: GridPoint): Int = kotlin.math.abs(x - other.x) + kotlin.math.abs(y - other.y)
}

/**
 * An Arrow is represented by an ordered list of waypoints on a 2D integer grid.
 * points[0] is the tail of the arrow.
 * points.last() is the head of the arrow where the arrowhead is drawn and where it exits.
 */
data class ArrowItem(
    val id: Int,
    val points: List<GridPoint>,
    val headDirection: Direction,
    val colorIndex: Int = 0,
    val isGhost: Boolean = false
) {
    val head: GridPoint get() = points.last()
    val tail: GridPoint get() = points.first()

    /**
     * Expands the waypoint list into all discrete grid cells occupied by the arrow segments.
     */
    fun allOccupiedCells(): Set<GridPoint> {
        val cells = mutableSetOf<GridPoint>()
        for (i in 0 until points.size - 1) {
            val p1 = points[i]
            val p2 = points[i + 1]
            if (p1.x == p2.x) {
                val minY = minOf(p1.y, p2.y)
                val maxY = maxOf(p1.y, p2.y)
                for (y in minY..maxY) {
                    cells.add(GridPoint(p1.x, y))
                }
            } else if (p1.y == p2.y) {
                val minX = minOf(p1.x, p2.x)
                val maxX = maxOf(p1.x, p2.x)
                for (x in minX..maxX) {
                    cells.add(GridPoint(x, p1.y))
                }
            }
        }
        return cells
    }

    /**
     * Calculates total grid-distance length of this arrow.
     */
    fun totalLength(): Float {
        var len = 0f
        for (i in 0 until points.size - 1) {
            val p1 = points[i]
            val p2 = points[i + 1]
            len += kotlin.math.hypot((p2.x - p1.x).toFloat(), (p2.y - p1.y).toFloat())
        }
        return len
    }
}

data class LevelData(
    val levelNumber: Int,
    val title: String,
    val gridWidth: Int,
    val gridHeight: Int,
    val arrows: List<ArrowItem>,
    val maxDrops: Int = 3,
    val isSilhouette: Boolean = false,
    val silhouetteIcon: String = "🧩",
    val bannerText: String = "TAP TO CLEAR"
)

data class CollisionInfo(
    val blockedArrowId: Int,
    val blockedAtPoint: GridPoint,
    val collidingWithArrowId: Int,
    val timestamp: Long = System.currentTimeMillis()
)

enum class PowerUpType(
    val title: String,
    val subtitle: String,
    val icon: String,
    val description: String
) {
    SNIP("Zen Snip", "Prune long snake", "✂️", "Cuts a multi-segment snake into shorter arrows"),
    GHOST("Ghost Phase", "Phase through obstacle", "👻", "Enables an arrow to pass through one obstacle"),
    MAGNET("Harmonic Pulse", "Cascade clear", "🌀", "Clears all currently unobstructed arrows at once"),
    RECALL("Flow Recall", "Undo move", "⏳", "Reverts your previous move and restores life")
}

data class ImpactSpark(
    val id: Long,
    val origin: Offset,
    val velocity: Offset,
    val color: Color,
    val size: Float,
    val maxAgeMs: Long = 450,
    val createdAt: Long = System.currentTimeMillis()
)

data class ShockwaveRing(
    val id: Long,
    val center: Offset,
    val maxRadius: Float,
    val color: Color,
    val maxAgeMs: Long = 400,
    val createdAt: Long = System.currentTimeMillis()
)

data class SoftDustParticle(
    val id: Long,
    val origin: Offset,
    val velocity: Offset,
    val radius: Float,
    val color: Color,
    val maxAgeMs: Long = 600,
    val createdAt: Long = System.currentTimeMillis()
)

data class ComboRewardEvent(
    val combo: Int,
    val powerUpType: PowerUpType,
    val message: String,
    val id: Long = System.currentTimeMillis()
)

data class MoveHistoryState(
    val activeArrows: List<ArrowItem>,
    val remainingDrops: Int,
    val movesCount: Int
)

data class GameTheme(
    val id: String,
    val displayName: String,
    val background: Color,
    val boardBackground: Color,
    val arrowStroke: Color,
    val arrowHeadColor: Color,
    val hintColor: Color,
    val errorColor: Color,
    val dropActiveColor: Color,
    val dropInactiveColor: Color,
    val headerGold: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val bannerBg: Color,
    val bannerBorder: Color,
    val bannerText: Color,
    val cardBg: Color,
    val buttonBg: Color,
    val isDark: Boolean = false
)
