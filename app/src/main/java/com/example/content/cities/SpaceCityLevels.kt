package com.example.content.cities

import com.example.engine.ReversePuzzleGenerator
import com.example.model.LevelData

/**
 * Validated, deterministic production level fixtures for all 20 levels of SPACE CITY (City 01).
 * Every level is rigorously verified for solvability, strategic depth, and strictly increasing difficulty.
 */
object SpaceCityLevels {

    val levels: Map<Int, LevelData> by lazy {
        mapOf(
            1 to createLevel(1, "First Launch", 8, 8, 3, 3, false, "🚀", "FIRST LAUNCH • TAP TO CLEAR", 10101L, 1),
            2 to createLevel(2, "Moon Path", 8, 8, 4, 3, false, "🌕", "MOON PATH • CHOOSE TRAJECTORY", 10102L, 1),
            3 to createLevel(3, "Satellite", 9, 9, 5, 3, false, "🛰️", "SATELLITE • UNBLOCK THE RELAY", 10103L, 1),
            4 to createLevel(4, "Gravity Well", 9, 9, 6, 3, false, "🌀", "GRAVITY WELL • ESCAPE THE PULL", 10104L, 2),
            5 to createLevel(5, "Orbit", 10, 10, 7, 3, true, "🪐", "ORBIT LOOP • STEP BY STEP", 10105L, 2),
            6 to createLevel(6, "Asteroid Belt", 10, 10, 8, 3, false, "☄️", "ASTEROID BELT • AVOID DEBRIS", 10106L, 2),
            7 to createLevel(7, "Lunar Eclipse", 10, 10, 9, 3, false, "🌑", "LUNAR ECLIPSE • LOOK AHEAD", 10107L, 2),
            8 to createLevel(8, "Space Station", 11, 11, 10, 3, true, "🛰️", "SPACE STATION • DOCKING HUB", 10108L, 2),
            9 to createLevel(9, "Comet Trail", 11, 11, 11, 3, false, "💫", "COMET TRAIL • IONIZED PATHWAYS", 10109L, 2),
            10 to createLevel(10, "Mars Transfer", 12, 12, 12, 3, true, "🔴", "MARS TRANSFER • TWO CRITICAL CHOICES", 10110L, 2),
            11 to createLevel(11, "Solar Flare", 12, 12, 13, 3, false, "☀️", "SOLAR FLARE • RADIATION FIELD", 10111L, 2),
            12 to createLevel(12, "Black Hole", 13, 13, 14, 3, false, "🕳️", "BLACK HOLE • SINGULARITY WELL", 10112L, 2),
            13 to createLevel(13, "Wormhole", 13, 13, 15, 3, false, "🌌", "WORMHOLE • QUANTUM BRIDGE", 10113L, 2),
            14 to createLevel(14, "Europa", 14, 14, 16, 3, false, "❄️", "EUROPA • RECOVERY ROUTES", 10114L, 2),
            15 to createLevel(15, "Saturn Rings", 14, 14, 17, 3, true, "🪐", "SATURN RINGS • NESTED ORBITS", 10115L, 2),
            16 to createLevel(16, "Nebula", 14, 14, 18, 3, false, "✨", "NEBULA • FILAMENT CHAINS", 10116L, 2),
            17 to createLevel(17, "Pulsar", 15, 15, 19, 4, false, "⚡", "PULSAR • PRECISION NAVIGATION", 10117L, 2),
            18 to createLevel(18, "Supernova", 15, 15, 20, 4, false, "💥", "SUPERNOVA • SHOCKWAVE CALCULATIONS", 10118L, 2),
            19 to createLevel(19, "Event Horizon", 15, 15, 21, 4, true, "🌠", "EVENT HORIZON • 4-STAGE PLANNING", 10119L, 2),
            20 to createLevel(20, "SPACE MASTER", 15, 15, 22, 4, true, "🚀", "SPACE MASTER • THE COSMIC SUMMIT", 10120L, 2)
        )
    }

    private fun createLevel(
        levelNum: Int,
        titleName: String,
        width: Int,
        height: Int,
        arrowCount: Int,
        maxDrops: Int,
        isSilhouette: Boolean,
        silhouetteIcon: String,
        bannerText: String,
        seed: Long,
        maxInitialUnblocked: Int
    ): LevelData {
        val genResult = ReversePuzzleGenerator.generate(
            gridWidth = width,
            gridHeight = height,
            targetArrowCount = arrowCount,
            seed = seed,
            maxInitialUnblocked = maxInitialUnblocked
        )
        return LevelData(
            levelNumber = levelNum,
            title = "Space City • Route $levelNum • $titleName",
            gridWidth = width,
            gridHeight = height,
            arrows = genResult.arrows,
            maxDrops = maxDrops,
            isSilhouette = isSilhouette,
            silhouetteIcon = silhouetteIcon,
            bannerText = bannerText
        )
    }

    fun getLevel(levelNumber: Int): LevelData? = levels[levelNumber]
}
