package com.example.content.cities

import androidx.compose.ui.graphics.Color
import com.example.engine.experimental.DifficultyTier

/**
 * Metadata and blueprint constraints for each level in Space City.
 */
data class SpaceCityLevelDefinition(
    val levelNumber: Int,
    val title: String,
    val conceptTitle: String,
    val conceptDescription: String,
    val difficultyTier: DifficultyTier,
    val targetStrategicDepth: Int,
    val targetCriticalChoices: Int,
    val maxTraps: Int,
    val fuelDropTier: FuelDropColorTier,
    val loreSnippet: String,
    val seed: Long
) {
    val isMilestone: Boolean get() = levelNumber in listOf(4, 8, 12, 16, 20)
}

enum class FuelDropColorTier(val label: String, val activeColor: Color, val maxDrops: Int) {
    LEARN("Learn", Color(0xFF00CEC9), 3),      // Cyan / Ice Blue
    REINFORCE("Reinforce", Color(0xFF00B894), 3), // Emerald Green
    CHALLENGE("Challenge", Color(0xFFFDCB6E), 3), // Amber Gold
    MASTER("Master", Color(0xFFFF7675), 4)       // Solar Crimson
}

object SpaceCityLevelRegistry {
    val levelDefinitions = listOf(
        SpaceCityLevelDefinition(
            levelNumber = 1,
            title = "First Launch",
            conceptTitle = "Launch Sequence",
            conceptDescription = "Basic arrow clearing tutorial. Direct unblocked flight paths.",
            difficultyTier = DifficultyTier.EASY,
            targetStrategicDepth = 0,
            targetCriticalChoices = 0,
            maxTraps = 0,
            fuelDropTier = FuelDropColorTier.LEARN,
            loreSnippet = "Engines ignite on Launchpad 01. Clear the primary atmospheric trajectory.",
            seed = 10101L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 2,
            title = "Moon Path",
            conceptTitle = "Orbital Ascent",
            conceptDescription = "Basic branching. Choose the correct departure sequence.",
            difficultyTier = DifficultyTier.EASY,
            targetStrategicDepth = 1,
            targetCriticalChoices = 0,
            maxTraps = 0,
            fuelDropTier = FuelDropColorTier.LEARN,
            loreSnippet = "Entering trans-lunar orbit. Multiple trajectories open across the flight deck.",
            seed = 10102L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 3,
            title = "Satellite",
            conceptTitle = "Temporary Blocking",
            conceptDescription = "Clearing a foreground satellite opens a deeper corridor.",
            difficultyTier = DifficultyTier.EASY,
            targetStrategicDepth = 1,
            targetCriticalChoices = 0,
            maxTraps = 0,
            fuelDropTier = FuelDropColorTier.LEARN,
            loreSnippet = "Communications relay array is drifting. Unblock telemetry line first.",
            seed = 10103L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 4,
            title = "Gravity Well",
            conceptTitle = "First Consequential Choice",
            conceptDescription = "One path escapes the gravity well; the other creates a dead end.",
            difficultyTier = DifficultyTier.EASY,
            targetStrategicDepth = 1,
            targetCriticalChoices = 1,
            maxTraps = 1,
            fuelDropTier = FuelDropColorTier.LEARN,
            loreSnippet = "Lunar gravity anomaly detected. A hasty maneuver will collapse the escape route.",
            seed = 10104L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 5,
            title = "Orbit",
            conceptTitle = "Sequential Dependency",
            conceptDescription = "Circular orbital locks. Unravel the sequence from outer to inner.",
            difficultyTier = DifficultyTier.MEDIUM,
            targetStrategicDepth = 1,
            targetCriticalChoices = 1,
            maxTraps = 1,
            fuelDropTier = FuelDropColorTier.REINFORCE,
            loreSnippet = "Geostationary orbital ring. Step through the interlocking perimeter.",
            seed = 10105L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 6,
            title = "Asteroid Belt",
            conceptTitle = "Narrow Branching & Traps",
            conceptDescription = "Debris field. False openings lead to dense impasses.",
            difficultyTier = DifficultyTier.MEDIUM,
            targetStrategicDepth = 2,
            targetCriticalChoices = 1,
            maxTraps = 2,
            fuelDropTier = FuelDropColorTier.REINFORCE,
            loreSnippet = "Main belt asteroid field. Identify the true spatial clearance.",
            seed = 10106L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 7,
            title = "Lunar Eclipse",
            conceptTitle = "Delayed Dependency",
            conceptDescription = "Initial move unlocks a passage required three steps later.",
            difficultyTier = DifficultyTier.MEDIUM,
            targetStrategicDepth = 2,
            targetCriticalChoices = 1,
            maxTraps = 1,
            fuelDropTier = FuelDropColorTier.REINFORCE,
            loreSnippet = "Umbral shadow occludes navigational sensors. Plan beyond the first maneuver.",
            seed = 10107L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 8,
            title = "Space Station",
            conceptTitle = "Multiple Entry Routes",
            conceptDescription = "Docking hub with symmetrical docking arms. Evaluate optimal clearance.",
            difficultyTier = DifficultyTier.MEDIUM,
            targetStrategicDepth = 2,
            targetCriticalChoices = 2,
            maxTraps = 1,
            fuelDropTier = FuelDropColorTier.REINFORCE,
            loreSnippet = "Citadel Station Alpha. Multi-port docking grid requires synchronized entry.",
            seed = 10108L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 9,
            title = "Comet Trail",
            conceptTitle = "Delayed Trap Discovery",
            conceptDescription = "Wrong branch appears valid until ion tail blocks downstream exits.",
            difficultyTier = DifficultyTier.HARD,
            targetStrategicDepth = 2,
            targetCriticalChoices = 2,
            maxTraps = 2,
            fuelDropTier = FuelDropColorTier.CHALLENGE,
            loreSnippet = "Ionized dust stream. Calculate line-of-sight across three future moves.",
            seed = 10109L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 10,
            title = "Mars Transfer",
            conceptTitle = "Two Critical Choices",
            conceptDescription = "First choice unlocks the transfer window; second navigates descent.",
            difficultyTier = DifficultyTier.HARD,
            targetStrategicDepth = 2,
            targetCriticalChoices = 2,
            maxTraps = 2,
            fuelDropTier = FuelDropColorTier.CHALLENGE,
            loreSnippet = "Hohmann transfer orbit. Precision thruster burn dictates the secondary approach.",
            seed = 10110L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 11,
            title = "Solar Flare",
            conceptTitle = "Multi-Layered Dependency",
            conceptDescription = "Radiation field with temporary blocks and interlocking arrows.",
            difficultyTier = DifficultyTier.HARD,
            targetStrategicDepth = 2,
            targetCriticalChoices = 2,
            maxTraps = 2,
            fuelDropTier = FuelDropColorTier.CHALLENGE,
            loreSnippet = "Coronal mass ejection incoming. Shield routes must be cleared in unison.",
            seed = 10111L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 12,
            title = "Black Hole",
            conceptTitle = "Deep Trap Horizon",
            conceptDescription = "Deep trap severity. Unsolvable branches allow 2-3 false steps before lock.",
            difficultyTier = DifficultyTier.HARD,
            targetStrategicDepth = 3,
            targetCriticalChoices = 2,
            maxTraps = 3,
            fuelDropTier = FuelDropColorTier.CHALLENGE,
            loreSnippet = "Singularity gravitational field. Verify every exit before crossing the threshold.",
            seed = 10112L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 13,
            title = "Wormhole",
            conceptTitle = "Long-Range Dependency",
            conceptDescription = "Actions on the left quadrant dictate clearance on the far right.",
            difficultyTier = DifficultyTier.DEEP,
            targetStrategicDepth = 3,
            targetCriticalChoices = 3,
            maxTraps = 2,
            fuelDropTier = FuelDropColorTier.CHALLENGE,
            loreSnippet = "Einstein-Rosen bridge. Spatial entanglement connects opposing quadrants.",
            seed = 10113L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 14,
            title = "Europa",
            conceptTitle = "Recovery Routing",
            conceptDescription = "Mistakes can be recovered through alternate secondary clearances.",
            difficultyTier = DifficultyTier.DEEP,
            targetStrategicDepth = 3,
            targetCriticalChoices = 2,
            maxTraps = 2,
            fuelDropTier = FuelDropColorTier.CHALLENGE,
            loreSnippet = "Subsurface ocean navigation. Multiple viable escape paths through ice fissures.",
            seed = 10114L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 15,
            title = "Saturn Rings",
            conceptTitle = "Branching Cascade",
            conceptDescription = "Nested ring structure with multiple dependent branches.",
            difficultyTier = DifficultyTier.DEEP,
            targetStrategicDepth = 3,
            targetCriticalChoices = 3,
            maxTraps = 3,
            fuelDropTier = FuelDropColorTier.CHALLENGE,
            loreSnippet = "Cassini division orbital crossing. 3 key choices govern the outer ring clearance.",
            seed = 10115L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 16,
            title = "Nebula",
            conceptTitle = "Sequential Choice Chain",
            conceptDescription = "Long multi-stage decision chain. Each move directly unlocks the next.",
            difficultyTier = DifficultyTier.DEEP,
            targetStrategicDepth = 3,
            targetCriticalChoices = 3,
            maxTraps = 3,
            fuelDropTier = FuelDropColorTier.CHALLENGE,
            loreSnippet = "Orion star nursery. Nebular filaments require strict directional sequence.",
            seed = 10116L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 17,
            title = "Pulsar",
            conceptTitle = "Low Recovery Precision",
            conceptDescription = "Unforgiving precision routing. Minimal tolerance for sub-optimal moves.",
            difficultyTier = DifficultyTier.EXPERT,
            targetStrategicDepth = 4,
            targetCriticalChoices = 3,
            maxTraps = 3,
            fuelDropTier = FuelDropColorTier.MASTER,
            loreSnippet = "Neutron star radiation beam sweeps every cycle. Pure analytical deduction required.",
            seed = 10117L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 18,
            title = "Supernova",
            conceptTitle = "Delayed Multi-Step Traps",
            conceptDescription = "Complex delayed traps. False moves reveal lockup only near final arrows.",
            difficultyTier = DifficultyTier.EXPERT,
            targetStrategicDepth = 4,
            targetCriticalChoices = 3,
            maxTraps = 3,
            fuelDropTier = FuelDropColorTier.MASTER,
            loreSnippet = "Stellar detonation shockwave. Look 4 moves deep into the cosmic web.",
            seed = 10118L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 19,
            title = "Event Horizon",
            conceptTitle = "Advanced Multi-Choice Planning",
            conceptDescription = "High decision density. 4+ critical branch points across all quadrants.",
            difficultyTier = DifficultyTier.EXPERT,
            targetStrategicDepth = 4,
            targetCriticalChoices = 4,
            maxTraps = 3,
            fuelDropTier = FuelDropColorTier.MASTER,
            loreSnippet = "Outer edge of spacetime distortion. Master-grade strategic calculation.",
            seed = 10119L
        ),
        SpaceCityLevelDefinition(
            levelNumber = 20,
            title = "SPACE MASTER",
            conceptTitle = "Master Flagship Summit",
            conceptDescription = "The ultimate Space City puzzle. Orbital dependencies, traps, and deep planning.",
            difficultyTier = DifficultyTier.MASTER,
            targetStrategicDepth = 4,
            targetCriticalChoices = 4,
            maxTraps = 4,
            fuelDropTier = FuelDropColorTier.MASTER,
            loreSnippet = "Cosmic Core Citadel. Ascend to the title of Space City Master.",
            seed = 10120L
        )
    )

    val levels: List<SpaceCityLevelDefinition> get() = levelDefinitions

    fun getDefinition(levelNumber: Int): SpaceCityLevelDefinition {
        return levelDefinitions.find { it.levelNumber == levelNumber }
            ?: levelDefinitions.first()
    }
}
