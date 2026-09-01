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
    val isDark: Boolean = false,
    val gridLineColor: Color = Color.Unspecified,
    val pathwayGlowColor: Color = Color.Unspecified,
    val themeModeType: String = "standard"
)

enum class ArrowSkinType(
    val id: String,
    val displayName: String,
    val icon: String,
    val description: String
) {
    DEFAULT("default", "Classic Arrow", "🏹", "Standard aerodynamic vector arrowhead"),
    HYPERDRIVE_PRISM("hyperdrive_prism", "Hyperdrive Prism", "💎", "Prismatic diamond cut crystal arrow with rainbow refraction"),
    CYBER_DRAGON("cyber_dragon", "Cyber Dragon", "🐉", "Articulated cybernetic dragon chevron with glowing eyes"),
    STEAMPUNK_BRASS("steampunk_brass", "Steampunk Brass", "⚙️", "Polished clockwork brass arrow with mechanical gear accent"),
    NANOTECH_KATANA("nanotech_katana", "Nanotech Katana", "🗡️", "Sleek razor-sharp obsidian katana blade pointer"),
    APOLLO_ROCKET("apollo_rocket", "Apollo Rocket", "🚀", "Retro silver space cruiser with burning propulsion thrust")
}

enum class BoardCanvasType(
    val id: String,
    val displayName: String,
    val icon: String,
    val description: String
) {
    DEFAULT("default", "Theme Canvas", "🎨", "Default theme board background and styling"),
    CARBON_HEXAGON("carbon_hexagon", "Carbon Matrix", "⬡", "High-tech woven carbon fiber with geometric micro-relief"),
    HOLOGRAPHIC_GLASS("holographic_glass", "Holo Glass", "🪟", "Translucent luminous holographic plate with neon edge bevels"),
    JAPANESE_TATAMI("japanese_tatami", "Kyoto Tatami", "🎋", "Natural woven rush straw mat with dark embroidered silk border"),
    RETRO_CRT("retro_crt", "Retro CRT 8-Bit", "📺", "Vintage arcade phosphor monitor with scanlines"),
    MINIMAL_OLED("minimal_oled", "Pure OLED Void", "⬛", "Absolute deep black obsidian with titanium laser border")
}

enum class MazeGridStyle(
    val id: String,
    val displayName: String,
    val icon: String,
    val description: String
) {
    DEFAULT("default", "Theme Grid", "📐", "Default theme cell tiles and dots"),
    CIRCUIT_PCB("circuit_pcb", "Circuit Traces", "🔌", "Printed circuit board copper traces with glowing soldering pads"),
    CONSTELLATION_NET("constellation_net", "Starry Web", "✨", "Celestial stardust constellation grid with glowing star nodes"),
    NEON_TUBE("neon_tube", "Neon Gas Tube", "💡", "Glowing cylindrical neon light pipes embedded in grid slots"),
    SUBWAY_MAP("subway_map", "Transit Metro", "🚇", "Clean urban transit railway route lines with circular junction stations")
}

enum class BackgroundAnimType(
    val id: String,
    val displayName: String,
    val icon: String,
    val description: String
) {
    DEFAULT("default", "Theme Atmosphere", "🌌", "Default theme animated ambient atmosphere"),
    MATRIX_STREAM("matrix_stream", "Matrix Stream", "💻", "Cascading digital neon binary code streams"),
    COSMIC_NEBULA("cosmic_nebula", "Cosmic Nebulae", "🪐", "Deep parallax rotating stellar gas clouds & starfield"),
    ZEN_SAKURA_DRIFT("zen_sakura_drift", "Sakura Flurry", "🌸", "Floating cherry blossom petals in a spring wind draft"),
    DEEP_OCEAN_RAYS("deep_ocean_rays", "Abyssal Caustics", "🌊", "Rising underwater air bubbles & sun rays"),
    MOLTEN_CINDERS("molten_cinders", "Molten Cinders", "🌋", "Rising glowing lava ember sparks and geothermal heat")
}

