package com.example.engine

import com.example.model.LevelData

object LevelRepository {

    private val anchorLevelNumbers = setOf(
        5, 10, 15, 20, 25, 30, 35, 40, 45, 50,
        55, 60, 65, 70, 75, 80, 85, 90, 95, 100,
        105, 110, 115, 120, 125, 130, 135, 140, 145, 150,
        155, 160, 165, 170, 175, 180, 185, 190, 195, 200
    )

    fun isAnchorLevel(levelNumber: Int): Boolean {
        return levelNumber in anchorLevelNumbers
    }

    /**
     * Retrieves LevelData for any level 1 to 200 (or beyond).
     */
    fun getLevel(levelNumber: Int): LevelData {
        if (levelNumber in 1..20) {
            val spaceLevel = com.example.content.cities.SpaceCityLevels.getLevel(levelNumber)
            if (spaceLevel != null) return spaceLevel
        }
        return PublishedBetaLevels.levels[levelNumber]
            ?: CuratedLevels.curatedMap[levelNumber]
            ?: ProceduralGenerator.generateLevel(levelNumber)
    }

    /**
     * Structure for artistic silhouette featured levels.
     */
    data class SilhouetteInfo(
        val levelNumber: Int,
        val title: String,
        val icon: String,
        val subtitle: String,
        val difficulty: String
    )

    /**
     * List of handcrafted silhouette levels for the Art Silhouettes tab.
     */
    val silhouetteLevels = listOf(
        SilhouetteInfo(5, "Orbit", "🌍", "Sequential dependency", "Easy"),
        SilhouetteInfo(10, "Mars Transfer", "🔴", "Two critical choices", "Medium"),
        SilhouetteInfo(15, "Saturn Rings", "🪐", "Multiple dependent branches", "Medium"),
        SilhouetteInfo(20, "SPACE MASTER", "🚀", "Space City Master Level", "Hard"),
        SilhouetteInfo(25, "Momentum", "🏃", "First multi-step consequence", "Medium"),
        SilhouetteInfo(30, "Inertia", "🛑", "Delayed trap", "Medium"),
        SilhouetteInfo(35, "Resonance", "📳", "Repeated dependency pattern", "Hard"),
        SilhouetteInfo(40, "PHYSICS MASTER", "⚙️", "Physics City Master Level", "Hard"),
        SilhouetteInfo(45, "Desert Route", "🏜️", "Long route", "Medium"),
        SilhouetteInfo(50, "Silk Road", "🐪", "Delayed trap", "Medium"),
        SilhouetteInfo(55, "Desert Storm", "🌪️", "Low visibility-style complexity", "Hard"),
        SilhouetteInfo(60, "GEOGRAPHY MASTER", "🌍", "Geography City Master Level", "Hard"),
        SilhouetteInfo(65, "Penguin Passage", "🐧", "Temporary blocking", "Hard"),
        SilhouetteInfo(70, "Bear Territory", "🐻", "Branching trap", "Hard"),
        SilhouetteInfo(75, "Jungle Crossing", "🌴", "Multiple traps", "Hard"),
        SilhouetteInfo(80, "ANIMAL MASTER", "🦁", "Animal City Master Level", "Hard"),
        SilhouetteInfo(85, "Hidden Spring", "⛲", "Delayed consequence", "Hard"),
        SilhouetteInfo(90, "Storm", "⛈️", "Multiple temporary blocks", "Hard"),
        SilhouetteInfo(95, "Deep Forest", "🌲", "Reduced recovery", "Deep"),
        SilhouetteInfo(100, "NATURE MASTER", "🌲", "Nature City Master Level", "Deep"),
        SilhouetteInfo(105, "Current", "🌊", "Directional dependency", "Hard"),
        SilhouetteInfo(110, "Reef Network", "🪸", "Multiple branches", "Deep"),
        SilhouetteInfo(115, "Coral Maze", "🪸", "High density", "Deep"),
        SilhouetteInfo(120, "OCEAN MASTER", "🌊", "Ocean City Master Level", "Deep"),
        SilhouetteInfo(125, "Castle", "🏰", "Defensive branching", "Hard"),
        SilhouetteInfo(130, "Great Expedition", "🧭", "Long route", "Deep"),
        SilhouetteInfo(135, "Revolution", "⚔️", "Branch restructuring", "Deep"),
        SilhouetteInfo(140, "HISTORY MASTER", "🏛️", "History City Master Level", "Deep"),
        SilhouetteInfo(145, "Network", "🌐", "Multiple paths", "Deep"),
        SilhouetteInfo(150, "Server Room", "💻", "Bottleneck", "Deep"),
        SilhouetteInfo(155, "Quantum Computer", "🧮", "Deep dependency", "Deep"),
        SilhouetteInfo(160, "TECHNOLOGY MASTER", "🤖", "Technology City Master Level", "Expert"),
        SilhouetteInfo(165, "Blood Flow", "🩸", "Flow network", "Deep"),
        SilhouetteInfo(170, "Muscle", "💪", "Temporary blocking", "Deep"),
        SilhouetteInfo(175, "Ecosystem", "🦠", "Cross-dependencies", "Expert"),
        SilhouetteInfo(180, "BIOLOGY MASTER", "🧬", "Biology City Master Level", "Expert"),
        SilhouetteInfo(185, "Quantum Route", "🌌", "Delayed consequences", "Expert"),
        SilhouetteInfo(190, "Infinite Loop", "♾️", "Cycle-like visual structure", "Expert"),
        SilhouetteInfo(195, "Singularity", "🌀", "Very deep delayed traps", "Master"),
        SilhouetteInfo(200, "ARROWCITY MASTER", "🌌", "The Ultimate Puzzle", "Master")
    )

    val featuredLevels = silhouetteLevels.map { it.levelNumber to "${it.title} ${it.icon}" }
}
