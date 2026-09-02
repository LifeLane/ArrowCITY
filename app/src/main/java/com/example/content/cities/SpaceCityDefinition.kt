package com.example.content.cities

import androidx.compose.ui.graphics.Color

/**
 * Authoritative definition of CITY 01 — SPACE CITY 🚀.
 * Matches the visual design spec and progression rules.
 */
object SpaceCityDefinition {
    val Definition = CityDefinition(
        id = 1,
        name = "SPACE CITY",
        subtitle = "LAUNCH YOUR LOGIC INTO THE COSMOS",
        tagline = "SPACE CITY MAP",
        description = "Navigate cosmic pathways, unlock orbital routes and avoid space traps. Every arrow you clear brings you closer to the destination.",
        icon = "🚀",
        levelRange = 1..20,
        primaryColor = Color(0xFF6C5CE7), // Cosmic Violet
        accentColor = Color(0xFF00CEC9),  // Nebula Cyan
        backgroundColor = Color(0xFF0D0A1C), // Deep Cosmic Dark
        masterLevelNumber = 20,
        strategicConcept = "Orbital dependency, gravity well traps, and delayed consequences in cosmic space.",
        mechanicsSummary = listOf(
            "Orbital Dependency: Some pathways only unlock after clearing adjacent orbits.",
            "Wormhole Portals: Long-distance geometric influences across quadrants.",
            "Gravity Wells: Irreversible trap branches requiring visual lookahead.",
            "Delayed Traps: Consequences that manifest 2 to 4 moves downstream.",
            "Recovery Routes: Strategic branches that allow correction before failure."
        )
    )

    /**
     * Map nodes spatial layout on the 2D visual canvas.
     * Coordinates (xNorm, yNorm) normalized to [0..1] range matching the visual map reference:
     * Curved serpentine track starting from bottom-left Earth up through Orbit, Asteroid belt,
     * Mars, Jupiter, Saturn rings, Nebula, and culminating at Space Master node 20.
     */
    data class MapNodeCoordinate(
        val levelNumber: Int,
        val xNorm: Float,
        val yNorm: Float,
        val landmarkLabel: String,
        val landmarkIcon: String,
        val isMajorMilestone: Boolean = false,
        val difficultyTier: String = "Learn"
    )

    val mapCoordinates = listOf(
        MapNodeCoordinate(1, 0.20f, 0.90f, "Launch Base", "🌍", false, "Learn"),
        MapNodeCoordinate(2, 0.45f, 0.88f, "Low Orbit", "🛰️", false, "Learn"),
        MapNodeCoordinate(3, 0.70f, 0.84f, "Moon Path", "🌕", false, "Learn"),
        MapNodeCoordinate(4, 0.85f, 0.77f, "Gravity Well", "🌀", true, "Learn"),
        MapNodeCoordinate(5, 0.65f, 0.72f, "Orbit Loop", "🪐", false, "Reinforce"),
        MapNodeCoordinate(6, 0.40f, 0.70f, "Asteroids", "☄️", false, "Reinforce"),
        MapNodeCoordinate(7, 0.18f, 0.66f, "Lunar Eclipse", "🌑", false, "Reinforce"),
        MapNodeCoordinate(8, 0.22f, 0.58f, "Space Station", "🛰️", true, "Reinforce"),
        MapNodeCoordinate(9, 0.48f, 0.55f, "Comet Trail", "💫", false, "Challenge"),
        MapNodeCoordinate(10, 0.75f, 0.52f, "Mars Transfer", "🔴", true, "Challenge"),
        MapNodeCoordinate(11, 0.84f, 0.44f, "Solar Flare", "☀️", false, "Challenge"),
        MapNodeCoordinate(12, 0.62f, 0.40f, "Black Hole", "🕳️", true, "Challenge"),
        MapNodeCoordinate(13, 0.35f, 0.37f, "Wormhole", "🌌", false, "Challenge"),
        MapNodeCoordinate(14, 0.16f, 0.32f, "Europa Ice", "❄️", false, "Challenge"),
        MapNodeCoordinate(15, 0.35f, 0.26f, "Saturn Rings", "🪐", true, "Challenge"),
        MapNodeCoordinate(16, 0.60f, 0.23f, "Nebula Core", "✨", false, "Challenge"),
        MapNodeCoordinate(17, 0.82f, 0.19f, "Pulsar Beam", "⚡", false, "Master"),
        MapNodeCoordinate(18, 0.65f, 0.13f, "Supernova", "💥", false, "Master"),
        MapNodeCoordinate(19, 0.42f, 0.09f, "Event Horizon", "🌠", true, "Master"),
        MapNodeCoordinate(20, 0.50f, 0.02f, "SPACE MASTER", "🚀", true, "Master")
    )
}
