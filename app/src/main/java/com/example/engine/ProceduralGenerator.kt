package com.example.engine

import com.example.engine.experimental.BlueprintGenerator
import com.example.engine.experimental.StrategicBlueprint
import com.example.model.ArrowItem
import com.example.model.CityConfig
import com.example.model.CityRepository
import com.example.model.LevelData

object ProceduralGenerator {
    private const val GENERATION_VERSION = 4L

    fun generateLevel(levelNumber: Int): LevelData {
        val city = CityRepository.getCityForLevel(levelNumber)
        val routeInCity = CityRepository.getRouteNumberInCity(levelNumber)
        val seed = (GENERATION_VERSION * 1000003L + city.id.toLong() * 65537L + levelNumber.toLong() * 31337L + 7919L)

        val progress = (routeInCity - 1).toFloat() / 19f
        val width = city.gridWidthRange.first + ((city.gridWidthRange.last - city.gridWidthRange.first) * progress).toInt()
        val height = city.gridHeightRange.first + ((city.gridHeightRange.last - city.gridHeightRange.first) * progress).toInt()

        val targetArrowCount = (7 + city.id + (routeInCity * 0.75f).toInt()).coerceIn(6, 26)
        val generated = ReversePuzzleGenerator.generate(
            gridWidth = width,
            gridHeight = height,
            targetArrowCount = targetArrowCount,
            seed = seed,
            maxInitialUnblocked = if (routeInCity > 12) 3 else 2
        )
        val arrows = generated.arrows

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
            1 to listOf("First Launch", "Moon Path", "Satellite", "Gravity Well", "Orbit", "Asteroid Belt", "Lunar Eclipse", "Space Station", "Comet Trail", "Mars Transfer", "Solar Flare", "Black Hole", "Wormhole", "Europa", "Saturn Rings", "Nebula", "Pulsar", "Supernova", "Event Horizon", "SPACE MASTER"),
            2 to listOf("Newton's Start", "Pendulum", "Gravity", "Friction", "Momentum", "Force Vector", "Collision", "Lever", "Magnet", "Inertia", "Energy Transfer", "Wave Function", "Pressure", "Elasticity", "Resonance", "Relativity", "Quantum Choice", "Entropy", "Singularity", "PHYSICS MASTER"),
            3 to listOf("World Map", "Continents", "Mountain Pass", "River Crossing", "Desert Route", "Island Chain", "Capital City", "Border Crossing", "Trade Route", "Silk Road", "Great River", "Mountain Network", "Archipelago", "Polar Route", "Desert Storm", "Global Network", "Transcontinental", "Seven Routes", "World Grid", "GEOGRAPHY MASTER"),
            4 to listOf("Safari Start", "Elephant Trail", "Zebra Crossing", "Monkey Canopy", "Penguin Passage", "Wolf Pack", "Lion's Den", "Bird Migration", "Snake Trail", "Bear Territory", "Dolphin Route", "Ant Colony", "Spider Web", "Herd Movement", "Jungle Crossing", "Predator Path", "Migration Season", "Food Chain", "Survival Route", "ANIMAL MASTER"),
            5 to listOf("Forest Trail", "River Bend", "Waterfall", "Mountain Stream", "Hidden Spring", "Forest Junction", "Canyon", "Cave Entrance", "Water Cycle", "Storm", "Glacier", "Volcano", "Rainforest", "Mountain Pass", "Deep Forest", "Waterfall Network", "Natural Labyrinth", "Wild Terrain", "Ancient Forest", "NATURE MASTER"),
            6 to listOf("Shoreline", "Lighthouse", "Coral Reef", "Tide Pool", "Current", "Shipwreck", "Deep Sea", "Submarine", "Whirlpool", "Reef Network", "Ocean Current", "Trench", "Kraken", "Abyss", "Coral Maze", "Tidal Network", "Deep Current", "Mariana", "Ocean Storm", "OCEAN MASTER"),
            7 to listOf("Ancient Dawn", "Pyramid", "Roman Road", "Medieval Gate", "Castle", "Silk Empire", "Renaissance", "Industrial Age", "Steamworks", "Great Expedition", "Ancient Labyrinth", "Royal Roads", "Fortress", "Empire", "Revolution", "Railway", "World War", "Reconstruction", "Civilization", "HISTORY MASTER"),
            8 to listOf("Boot Sequence", "Circuit", "Processor", "Memory", "Network", "Firewall", "Database", "Algorithm", "Robot Factory", "Server Room", "Cloud Network", "Encryption", "AI Core", "Neural Network", "Quantum Computer", "Machine Learning", "Autonomous System", "Singularity", "Digital Maze", "TECHNOLOGY MASTER"),
            9 to listOf("Cell", "DNA", "Neuron", "Synapse", "Blood Flow", "Heart", "Lung", "Nervous System", "Immune Response", "Muscle", "Brain", "Neural Path", "Genetic Code", "Biological Network", "Ecosystem", "Evolution", "Organ System", "Brain Network", "Life System", "BIOLOGY MASTER"),
            10 to listOf("Portal", "Gravity Grid", "Planetary Network", "Living Planet", "Quantum Route", "Neural Galaxy", "Cosmic Current", "Dimensional Gate", "Event Network", "Infinite Loop", "Gravity Maze", "Cosmic Junction", "Parallel Worlds", "Collapse", "Singularity", "Multiverse", "Final Network", "Beyond Space", "The Last City", "ARROWCITY MASTER")
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
