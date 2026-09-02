package com.example.engine

import com.example.engine.experimental.BlueprintGenerator
import com.example.engine.experimental.StrategicBlueprint
import com.example.model.ArrowItem
import com.example.model.CityConfig
import com.example.model.CityRepository
import com.example.model.LevelData

object ProceduralGenerator {
    private const val GENERATION_VERSION = 3L

    fun generateLevel(levelNumber: Int): LevelData {
        val city = CityRepository.getCityForLevel(levelNumber)
        val routeInCity = CityRepository.getRouteNumberInCity(levelNumber)
        val seed = (GENERATION_VERSION * 1000003L + city.id.toLong() * 65537L + levelNumber.toLong() * 31337L + 7919L)

        val progress = (routeInCity - 1).toFloat() / 19f
        val width = city.gridWidthRange.first + ((city.gridWidthRange.last - city.gridWidthRange.first) * progress).toInt()
        val height = city.gridHeightRange.first + ((city.gridHeightRange.last - city.gridHeightRange.first) * progress).toInt()

        val blueprint = StrategicBlueprint.forLevel(levelNumber)
        val generator = BlueprintGenerator(width, height)
        
        var arrows = try {
            val candidateState = generator.generate(seed, blueprint)
            candidateState.arrows.values.toList().mapIndexed { index, arrow ->
                arrow.copy(id = index + 1, colorIndex = index % 4)
            }
        } catch (e: Exception) {
            android.util.Log.e("ProceduralGenerator", "Generation failure for Level $levelNumber: ${e.message}")
            CuratedLevels.curatedMap[1]?.arrows ?: emptyList()
        }
        
        if (arrows.isEmpty()) {
            arrows = CuratedLevels.curatedMap[1]?.arrows ?: emptyList()
        }

        val levelTitle = generateRouteTitle(city, routeInCity, levelNumber)
        val bannerText = generateBannerText(city, routeInCity)

        return LevelData(
            levelNumber = levelNumber,
            title = levelTitle,
            bannerText = bannerText,
            gridWidth = width,
            gridHeight = height,
            arrows = arrows,
            maxDrops = 3
        )
    }

    private fun generateRouteTitle(city: CityConfig, routeInCity: Int, levelNumber: Int): String {
        val titlesMap = mapOf(
            1 to listOf("First Steps", "Gentle Turn", "Quiet Path", "Inner Calm", "Zen Flow", "Twin Streams", "Breeze", "Clear Vision", "Stone Garden", "Trophy of Clarity", "Harmony", "Bamboo Lane", "Lantern Walk", "Lotus Pond", "Diamond Heart", "Morning Mist", "Silent Gate", "Purity", "Serenity", "Zendai Gate"),
            2 to listOf("First Wind", "Dune Horizon", "Golden Dust", "Sand Drift", "Dune Run", "Oasis Trace", "Sun Flare", "Desert Ridge", "Mirage Walk", "Wind Sweep", "Canyon Way", "Nomad Track", "Sunstone", "Dust Devil", "Playful Kitty", "Heat Haze", "Scorpion Pass", "Red Sands", "Sirocco", "Loyal Companion"),
            3 to listOf("Shallow Tide", "Coral Branch", "Ocean Drift", "Sea Ripple", "First Wave", "Harbor Light", "Azure Stream", "Currents", "Whirlpool", "Swimming Koi", "Deep Trench", "Reef Run", "Lagoon Bend", "Sailor's Path", "Tidal Fork", "Aquatic Maze", "Storm Surge", "Abyssal Wake", "Pearl Drift", "The Tide"),
            4 to listOf("Sprout", "Mossy Path", "Willow Branch", "Green Canopy", "Ancient Roots", "Timber Line", "Fern Grove", "Bark & Bough", "Forest Clearing", "Forest Canopy", "Bramble Maze", "Pine Needle", "Woodland Trail", "Deep Thicket", "Soaring Falcon", "River Crossing", "Elder Tree", "Emerald Grove", "Verdant Spire", "Warm Coffee"),
            5 to listOf("Spark", "Ash Field", "Cinder Trail", "Smoldering Way", "Ember Trail", "Basalt Path", "Lava Stream", "Furnace Run", "Sulfur Vent", "Origami Swan", "Pyroclast", "Igneous Maze", "Obsidian Spire", "Magma Tunnel", "Volcanic Core", "Heat Chamber", "Rift Valley", "Crater Edge", "Blazing Spiral", "The Volcano"),
            6 to listOf("Updraft", "Breeze Crest", "Zephyr Lane", "Stratus Walk", "Aerith Lift", "Cumulus Path", "Vapor Trail", "Floating Isle", "Cirrus Flow", "Sky Bridge", "Nimbus Gate", "Thermal Drift", "Skyline Maze", "Aurora View", "Cloud Garden", "Solar Sail", "High Altitude", "Wing Spire", "Aether Vortex", "Sacred Lotus"),
            7 to listOf("Quartz Shard", "Facet Lane", "Geode Trace", "Lustrous Way", "Crystal Path", "Specular Bend", "Prism Edge", "Reflecting Pool", "Glinting Spire", "Mirror Prism", "Diamond Matrix", "Beryl Corridor", "Sapphire Run", "Emerald Facet", "Prismatic Spire", "Resonance Chamber", "Starlight Crystal", "Chime Maze", "Crystalline Web", "The Crystal Core"),
            8 to listOf("Cog Tooth", "Axle Turn", "Camshaft Run", "Ratchet Lane", "Piston Drive", "Conveyor Line", "Sprocket Walk", "Steam Valve", "Clockwork Ring", "Compass Star", "Flywheel Path", "Hydraulic Gate", "Gimbal Trace", "Turret Maze", "Interlock Gear", "Pressure Tube", "Mainspring Run", "Escapement", "Chronometer", "The Grand Gear"),
            9 to listOf("Dawn Ray", "Gleam", "Beaming Path", "Luminous Lane", "First Light", "Halo Ring", "Prism Beam", "Spectral Trace", "Strobe Flow", "Solar Radiance", "Photon Gate", "Corona Way", "Incandescent Maze", "Glow Arbor", "Light Tree", "Solar Flare", "Laser Corridor", "Lustre Chamber", "Bioluminescence", "Crescent Moon"),
            10 to listOf("Mobius Path", "Ouroboros", "Vortex Walk", "Singularity", "Infinity Loop", "Tesseract", "Continuum", "Recursion", "Fractal Gate", "Time Maze", "Dimension Bend", "Temporal Flow", "Quantum Trace", "Chronos Loop", "Eternal Flow", "Event Horizon", "Cosmic Spiral", "Hypercube", "Omniverse", "The Infinity Maze")
        )
        val cityTitles = titlesMap[city.id]
        val specificTitle = if (cityTitles != null && routeInCity in 1..cityTitles.size) {
            cityTitles[routeInCity - 1]
        } else {
            "Route $routeInCity"
        }
        return "${city.name} - $specificTitle"
    }

    private fun generateBannerText(city: CityConfig, routeInCity: Int): String {
        return when (routeInCity) {
            1 -> "Welcome to ${city.name}!"
            20 -> "Final challenge of ${city.name}!"
            else -> "Journey through ${city.name}"
        }
    }
}
