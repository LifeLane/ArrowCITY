package com.example.engine

import com.example.model.ArrowItem
import com.example.model.BrainTeaserDifficulty
import com.example.model.CognitiveProfile
import com.example.model.GridPoint
import com.example.model.LevelData
import com.example.model.RaycastTrajectory
import com.example.model.StrategicHint
import kotlin.math.roundToInt

/**
 * Open-source inspired Brain Teaser & Logic Puzzle Game Engine.
 * 
 * Implements Directed Acyclic Graph (DAG) dependency modeling, topological solution
 * pathfinding, cognitive complexity metrics (Puzzle IQ, Branching Entropy, Critical Path),
 * real-time raycast laser trajectory computation, and an intelligent Strategic Advisor.
 */
object BrainTeaserEngine {

    /**
     * Computes the complete optimal solution sequence of arrow IDs from the current active arrows.
     * Uses a heuristic greedy topological search prioritizing depth and critical path clearing.
     */
    fun computeOptimalSolution(
        activeArrows: List<ArrowItem>,
        gridWidth: Int,
        gridHeight: Int
    ): List<Int> {
        val remaining = activeArrows.toMutableList()
        val solutionSequence = mutableListOf<Int>()

        while (remaining.isNotEmpty()) {
            val unblocked = PuzzleSolver.findUnblockedArrows(remaining, gridWidth, gridHeight)
            if (unblocked.isEmpty()) {
                // If blocked by deadlock (e.g. during manual board edits), break
                break
            }

            // Pick the arrow that unlocks the most downstream arrows (chokepoint heuristic)
            val bestNext = unblocked.maxByOrNull { candidate ->
                val simulated = remaining.filter { it.id != candidate.id }
                PuzzleSolver.findUnblockedArrows(simulated, gridWidth, gridHeight).size
            } ?: unblocked.first()

            solutionSequence.add(bestNext.id)
            remaining.removeAll { it.id == bestNext.id }
        }

        return solutionSequence
    }

    /**
     * Analyzes the board state and computes an authentic Cognitive IQ and complexity profile.
     */
    fun analyzeCognitiveProfile(
        levelData: LevelData,
        activeArrows: List<ArrowItem> = levelData.arrows
    ): CognitiveProfile {
        if (activeArrows.isEmpty()) {
            return CognitiveProfile(
                puzzleIQ = 100,
                complexityTier = "Completed",
                criticalPathLength = 0,
                branchingFactor = 0f,
                decisionStatesCount = 0,
                chokepoints = emptyList(),
                totalSolutionMoves = 0,
                analyticalSummary = "Puzzle completely cleared!"
            )
        }

        val graph = PuzzleSolver.buildDependencyGraph(activeArrows, levelData.gridWidth, levelData.gridHeight)
        val criticalPath = PuzzleSolver.getLongestPathLength(graph).coerceAtLeast(1)
        val arrowCount = activeArrows.size

        // Identify chokepoints: arrows that are direct blockers for >= 2 other arrows
        val blockerCounts = mutableMapOf<Int, Int>()
        for ((_, blockers) in graph) {
            for (b in blockers) {
                blockerCounts[b] = (blockerCounts[b] ?: 0) + 1
            }
        }
        val chokepoints = blockerCounts.filter { it.value >= 2 }.keys.toList()

        // Calculate branching factor & decision forks
        val unblocked = PuzzleSolver.findUnblockedArrows(activeArrows, levelData.gridWidth, levelData.gridHeight)
        val initialLegalMoves = unblocked.size
        val branchingFactor = if (initialLegalMoves > 0) initialLegalMoves.toFloat() else 1f

        // Puzzle IQ formula based on arrow count, critical path depth, branching entropy, and chokepoints
        val rawIQ = 100 + (criticalPath * 4.5f) + (arrowCount * 1.5f) + (chokepoints.size * 3.0f)
        val puzzleIQ = rawIQ.roundToInt().coerceIn(105, 175)

        val complexityTier = when {
            puzzleIQ < 118 -> "Tactical Novice"
            puzzleIQ < 130 -> "Spatial Strategist"
            puzzleIQ < 144 -> "Logic Grandmaster"
            puzzleIQ < 158 -> "Cognitive Virtuoso"
            else -> "Cosmic Mensa"
        }

        val summary = when {
            chokepoints.isNotEmpty() -> "Contains ${chokepoints.size} key chokepoint(s) controlling ${criticalPath}-step critical cascades."
            criticalPath >= 6 -> "Deep linear chain requiring $criticalPath consecutive precision clearance steps."
            else -> "High-speed tactical board with $initialLegalMoves immediate exit vectors."
        }

        return CognitiveProfile(
            puzzleIQ = puzzleIQ,
            complexityTier = complexityTier,
            criticalPathLength = criticalPath,
            branchingFactor = branchingFactor,
            decisionStatesCount = maxOf(1, initialLegalMoves),
            chokepoints = chokepoints,
            totalSolutionMoves = arrowCount,
            analyticalSummary = summary
        )
    }

    /**
     * Strategic Advisor Hint system providing actionable deduction reasoning.
     */
    fun getStrategicAdvisorHint(
        activeArrows: List<ArrowItem>,
        gridWidth: Int,
        gridHeight: Int
    ): StrategicHint? {
        val unblocked = PuzzleSolver.findUnblockedArrows(activeArrows, gridWidth, gridHeight)
        if (unblocked.isEmpty()) return null

        val graph = PuzzleSolver.buildDependencyGraph(activeArrows, gridWidth, gridHeight)

        // Find candidate that unlocks the most downstream arrows
        var bestArrow = unblocked.first()
        var maxUnlocked = emptyList<Int>()
        var isChokepoint = false

        for (candidate in unblocked) {
            val simulated = activeArrows.filter { it.id != candidate.id }
            val nextUnblocked = PuzzleSolver.findUnblockedArrows(simulated, gridWidth, gridHeight)
                .map { it.id }
                .filter { it !in unblocked.map { u -> u.id } }

            if (nextUnblocked.size > maxUnlocked.size) {
                maxUnlocked = nextUnblocked
                bestArrow = candidate
                isChokepoint = nextUnblocked.size >= 2
            }
        }

        val title = if (isChokepoint) "Key Chokepoint Unlock" else "Tactical Escape Step"
        val explanation = if (maxUnlocked.isNotEmpty()) {
            "Releasing Arrow #${bestArrow.id} unlocks ${maxUnlocked.size} blocked arrow(s): ${maxUnlocked.joinToString { "#$it" }}!"
        } else {
            "Arrow #${bestArrow.id} has a completely clear escape trajectory off the grid."
        }

        return StrategicHint(
            recommendedArrowId = bestArrow.id,
            title = title,
            explanation = explanation,
            unlockedAfterIds = maxUnlocked,
            isChokepoint = isChokepoint
        )
    }

    /**
     * Computes the real-time raycast laser trajectory from an arrow's head.
     */
    fun computeRaycastTrajectory(
        arrow: ArrowItem,
        activeArrows: List<ArrowItem>,
        gridWidth: Int,
        gridHeight: Int
    ): RaycastTrajectory {
        val occupancy = PuzzleSolver.buildOccupancyMap(activeArrows)
        val dir = arrow.headDirection
        var curr = arrow.head.plus(dir)
        val maxSteps = maxOf(gridWidth, gridHeight) * 2 + 5
        var steps = 0

        while (steps < maxSteps) {
            val blocker = occupancy[curr]
            if (blocker != null && blocker.id != arrow.id) {
                return RaycastTrajectory(
                    arrowId = arrow.id,
                    start = arrow.head,
                    endPoint = curr,
                    isBlocked = true,
                    blockingArrowId = blocker.id
                )
            }

            if (curr.x < -1 || curr.x > gridWidth || curr.y < -1 || curr.y > gridHeight) {
                return RaycastTrajectory(
                    arrowId = arrow.id,
                    start = arrow.head,
                    endPoint = curr,
                    isBlocked = false,
                    blockingArrowId = null
                )
            }

            curr = curr.plus(dir)
            steps++
        }

        return RaycastTrajectory(
            arrowId = arrow.id,
            start = arrow.head,
            endPoint = curr,
            isBlocked = false,
            blockingArrowId = null
        )
    }

    /**
     * Generates a guaranteed-solvable custom brain teaser puzzle.
     */
    fun generateProceduralBrainTeaser(
        difficulty: BrainTeaserDifficulty,
        seed: Long
    ): LevelData {
        val generated = ReversePuzzleGenerator.generate(
            gridWidth = difficulty.gridWidth,
            gridHeight = difficulty.gridHeight,
            targetArrowCount = difficulty.targetArrows,
            seed = seed,
            maxInitialUnblocked = when (difficulty) {
                BrainTeaserDifficulty.CASUAL -> 3
                BrainTeaserDifficulty.TACTICAL -> 3
                BrainTeaserDifficulty.STRATEGIST -> 2
                BrainTeaserDifficulty.GRANDMASTER -> 2
                BrainTeaserDifficulty.COSMIC_MENSA -> 2
            }
        )

        return LevelData(
            levelNumber = 9000 + (seed.toInt().let { if (it < 0) -it else it } % 999),
            title = "${difficulty.icon} ${difficulty.title}",
            gridWidth = generated.gridWidth,
            gridHeight = generated.gridHeight,
            arrows = generated.arrows,
            maxDrops = if (difficulty.targetArrows >= 16) 4 else 3,
            isSilhouette = true,
            silhouetteIcon = difficulty.icon,
            bannerText = "BRAIN TEASER • ${difficulty.title.uppercase()}"
        )
    }
}
