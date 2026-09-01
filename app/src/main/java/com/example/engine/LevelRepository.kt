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
        return CuratedLevels.curatedMap[levelNumber]
            ?: if (isAnchorLevel(levelNumber)) {
                ProceduralGenerator.generateAnchorLevel(levelNumber)
            } else {
                ProceduralGenerator.generateLevel(levelNumber)
            }
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
        SilhouetteInfo(5, "Zen Flow", "🌀", "Zendai Spiral Current", "Gentle"),
        SilhouetteInfo(10, "Trophy of Clarity", "🏆", "Golden Victory Goblet", "Gentle"),
        SilhouetteInfo(15, "Diamond Heart", "💎", "Luminescent Gemstone", "Gentle"),
        SilhouetteInfo(20, "Zendai Gate", "⛩️", "Sacred City Portal", "Moderate"),
        SilhouetteInfo(25, "Dune Run", "🏜️", "Sweeping Sand Waves", "Moderate"),
        SilhouetteInfo(30, "Wind Sweep", "💨", "Desert Wind Corridors", "Moderate"),
        SilhouetteInfo(35, "Playful Kitty", "🐱", "Whimsical Feline Silhouette", "Moderate"),
        SilhouetteInfo(40, "Loyal Companion", "🐕", "Faithful Companion", "Challenging"),
        SilhouetteInfo(45, "First Wave", "🌊", "Oceanic Swell", "Challenging"),
        SilhouetteInfo(50, "Bonsai Tree", "🌲", "Zen Living Sculpture", "Challenging"),
        SilhouetteInfo(55, "Tidal Fork", "⚓", "Harbor Anchor Array", "Challenging"),
        SilhouetteInfo(60, "Swimming Koi", "🐟", "Aquatic Harmony", "Challenging"),
        SilhouetteInfo(65, "Ancient Roots", "🌱", "Verdant Sprout Network", "Advanced"),
        SilhouetteInfo(70, "Forest Canopy", "🌳", "Arboreal Branching", "Advanced"),
        SilhouetteInfo(75, "Soaring Falcon", "🦅", "Wings of Freedom", "Advanced"),
        SilhouetteInfo(80, "Warm Coffee", "☕", "Steaming Morning Comfort", "Advanced"),
        SilhouetteInfo(85, "Ember Trail", "🔥", "Volcanic Heat Current", "Advanced"),
        SilhouetteInfo(90, "Origami Swan", "🦢", "Geometric Grace", "Advanced"),
        SilhouetteInfo(95, "Volcanic Core", "🌋", "Magma Chamber Array", "Advanced"),
        SilhouetteInfo(100, "The Volcano", "🌋", "High Pressure Core", "Master"),
        SilhouetteInfo(105, "Aerith Lift", "🎈", "Sky Garden Ascent", "Master"),
        SilhouetteInfo(110, "Sky Bridge", "🌉", "Floating Island Span", "Master"),
        SilhouetteInfo(115, "Cloud Garden", "☁️", "Vaporous Isles", "Master"),
        SilhouetteInfo(120, "Sacred Lotus", "🌸", "Pristine Blooming Petals", "Master"),
        SilhouetteInfo(125, "Crystal Path", "💎", "Refractive Geometric Facets", "Master"),
        SilhouetteInfo(130, "Mirror Prism", "🪞", "Bilateral Mirror Reflection", "Master"),
        SilhouetteInfo(135, "Prismatic Spire", "🔮", "Nested Diamond Spire", "Grandmaster"),
        SilhouetteInfo(140, "The Crystal Core", "💠", "Crystalline Matrix Apex", "Grandmaster"),
        SilhouetteInfo(145, "Piston Drive", "⚙️", "Interlocking Mechanical Drive", "Grandmaster"),
        SilhouetteInfo(150, "Compass Star", "🧭", "Guiding Celestial Way", "Grandmaster"),
        SilhouetteInfo(155, "Interlock Gear", "🔩", "Radial Clockwork Teeth", "Grandmaster"),
        SilhouetteInfo(160, "The Grand Gear", "⚙️", "Clockwork Heart of Mechtropolis", "Grandmaster"),
        SilhouetteInfo(165, "First Light", "✨", "Radiant Dawn Rays", "Grandmaster"),
        SilhouetteInfo(170, "Solar Radiance", "☀️", "Symmetrical Sunburst", "Grandmaster"),
        SilhouetteInfo(175, "Light Tree", "🌟", "Luminous Radiant Arbor", "Legendary"),
        SilhouetteInfo(180, "Crescent Moon", "🌙", "Nocturnal Serenity", "Legendary"),
        SilhouetteInfo(185, "Infinity Loop", "♾️", "Intertwined Cosmic Pathways", "Legendary"),
        SilhouetteInfo(190, "Time Maze", "⏳", "Deep Dependency Labyrinth", "Legendary"),
        SilhouetteInfo(195, "Eternal Flow", "🌌", "Cosmic Endless Matrix", "Legendary"),
        SilhouetteInfo(200, "The Infinity Maze", "🌀", "Pinnacle Master Culmination", "Mythic")
    )

    val featuredLevels = silhouetteLevels.map { it.levelNumber to "${it.title} ${it.icon}" }
}
