cat << 'INNER_EOF' > app/src/main/java/com/example/engine/experimental/StrategicBlueprint.kt
package com.example.engine.experimental

enum class DifficultyTier {
    EASY, MEDIUM, HARD, DEEP, EXPERT, MASTER
}

data class StrategicBlueprint(
    val tier: DifficultyTier,
    val minDepth: Int,
    val maxDepth: Int,
    val maxTraps: Int,
    val recoveryAllowed: Boolean,
    val minSolutionLength: Int,
    val maxSolutionLength: Int,
    val maxRepetitiveRatio: Float
) {
    companion object {
        val EASY = StrategicBlueprint(
            tier = DifficultyTier.EASY,
            minDepth = 0,
            maxDepth = 1,
            maxTraps = 0,
            recoveryAllowed = true,
            minSolutionLength = 3,
            maxSolutionLength = 15,
            maxRepetitiveRatio = 0.90f
        )
        
        val MEDIUM = StrategicBlueprint(
            tier = DifficultyTier.MEDIUM,
            minDepth = 1,
            maxDepth = 2,
            maxTraps = 2,
            recoveryAllowed = true,
            minSolutionLength = 10,
            maxSolutionLength = 25,
            maxRepetitiveRatio = 0.85f
        )
        
        val HARD = StrategicBlueprint(
            tier = DifficultyTier.HARD,
            minDepth = 2,
            maxDepth = 3,
            maxTraps = 3,
            recoveryAllowed = false,
            minSolutionLength = 15,
            maxSolutionLength = 35,
            maxRepetitiveRatio = 0.80f
        )
        
        val DEEP = StrategicBlueprint(
            tier = DifficultyTier.DEEP,
            minDepth = 3,
            maxDepth = 4,
            maxTraps = 4,
            recoveryAllowed = false,
            minSolutionLength = 20,
            maxSolutionLength = 40,
            maxRepetitiveRatio = 0.75f
        )

        val EXPERT = StrategicBlueprint(
            tier = DifficultyTier.EXPERT,
            minDepth = 4,
            maxDepth = 5,
            maxTraps = 4,
            recoveryAllowed = false,
            minSolutionLength = 20,
            maxSolutionLength = 45,
            maxRepetitiveRatio = 0.70f
        )

        val MASTER = StrategicBlueprint(
            tier = DifficultyTier.MASTER,
            minDepth = 5,
            maxDepth = 6,
            maxTraps = 5,
            recoveryAllowed = false,
            minSolutionLength = 25,
            maxSolutionLength = 50,
            maxRepetitiveRatio = 0.60f
        )
        
        fun forLevel(levelNumber: Int): StrategicBlueprint {
            return when {
                levelNumber <= 10 -> EASY
                levelNumber <= 30 -> MEDIUM
                levelNumber <= 80 -> HARD
                levelNumber <= 140 -> DEEP
                levelNumber <= 180 -> EXPERT
                else -> MASTER
            }
        }
    }
}
INNER_EOF

cat << 'INNER_EOF' > app/src/main/java/com/example/engine/experimental/BlueprintGenerator.kt
package com.example.engine.experimental

import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GridPoint
import java.util.Random

class BlueprintGenerator(val width: Int, val height: Int) {
    
    var attempts = 0
    var generationTimeMs = 0L
    
    fun generate(seed: Long, blueprint: StrategicBlueprint): StepBoardState {
        val random = Random(seed)
        attempts = 0
        val startTime = System.currentTimeMillis()
        val maxTime = 5000L // 5 seconds max
        
        while (System.currentTimeMillis() - startTime < maxTime) {
            attempts++
            val candidate = buildSkeleton(random, blueprint) ?: continue
            
            // Limit total arrows to prevent state explosion
            val maxArrows = when (blueprint.tier) {
                DifficultyTier.EASY -> 6
                DifficultyTier.MEDIUM -> 7
                DifficultyTier.HARD -> 9
                DifficultyTier.DEEP -> 10
                DifficultyTier.EXPERT -> 11
                DifficultyTier.MASTER -> 12
            }
            if (candidate.arrows.size > maxArrows) continue
            
            // Analyze the candidate. Use a much lower state limit to keep it fast!
            val maxStates = when(blueprint.tier) {
                DifficultyTier.MASTER, DifficultyTier.EXPERT -> 5000
                DifficultyTier.DEEP -> 3000
                else -> 1500
            }
            val analyzer = StepPuzzleAnalyzer(StepSlideEngine(width, height), candidate, maxStates)
            
            val analysis = try {
                analyzer.analyze()
            } catch (e: Exception) {
                // State explosion or other analysis error
                continue
            }
            
            if (!analysis.solvable) continue
            
            if (analysis.strategicDepth < blueprint.minDepth || analysis.strategicDepth > blueprint.maxDepth) continue
            
            if (blueprint.minDepth > 0 && analysis.criticalChoices == 0) continue
            
            if (blueprint.tier != DifficultyTier.EASY && analysis.deadEndCount == 0) continue
            
            if (analysis.minimumSolutionLength < blueprint.minSolutionLength || analysis.minimumSolutionLength > blueprint.maxSolutionLength) continue
            
            if (analysis.repetitiveMoveRatio > blueprint.maxRepetitiveRatio) continue
            
            generationTimeMs = System.currentTimeMillis() - startTime
            return candidate
        }
        
        generationTimeMs = System.currentTimeMillis() - startTime
        throw IllegalStateException("Failed to constructively generate puzzle for tier ${blueprint.tier} after $attempts attempts and ${generationTimeMs}ms")
    }
    
    private fun buildSkeleton(random: Random, blueprint: StrategicBlueprint): StepBoardState? {
        val arrows = mutableMapOf<Int, ArrowItem>()
        var nextId = 1
        
        val chainLength = when (blueprint.tier) {
            DifficultyTier.EASY -> 2 + random.nextInt(2)
            DifficultyTier.MEDIUM -> 3 + random.nextInt(2)
            DifficultyTier.HARD -> 3 + random.nextInt(3)
            DifficultyTier.DEEP -> 4 + random.nextInt(2)
            DifficultyTier.EXPERT -> 4 + random.nextInt(3)
            DifficultyTier.MASTER -> 5 + random.nextInt(2)
        }
        
        var currentBlocker: ArrowItem? = null
        for (i in 0 until chainLength) {
            val arrow = placeArrowBlocking(random, currentBlocker, arrows.values) ?: return null
            arrows[nextId] = arrow
            currentBlocker = arrow
            nextId++
        }
        
        val numTraps = if (blueprint.maxTraps > 0) random.nextInt(blueprint.maxTraps + 1) else 0
        
        for (i in 0 until numTraps) {
            val trap = placeTrap(random, arrows.values)
            if (trap != null) {
                arrows[nextId] = trap
                nextId++
            }
        }
        
        if (blueprint.recoveryAllowed && random.nextBoolean()) {
             val rec = placeArrowBlocking(random, null, arrows.values)
             if (rec != null) {
                 arrows[nextId] = rec
                 nextId++
             }
        }
        
        return StepBoardState(arrows)
    }
    
    private fun placeArrowBlocking(random: Random, blockedArrow: ArrowItem?, existing: Collection<ArrowItem>): ArrowItem? {
        for (attempt in 1..20) {
            val dir = Direction.values()[random.nextInt(4)]
            val tailX = random.nextInt(width)
            val tailY = random.nextInt(height)
            val headX = tailX + dir.dx
            val headY = tailY + dir.dy
            
            if (headX in 0 until width && headY in 0 until height) {
                val candidate = ArrowItem(-1, listOf(GridPoint(tailX, tailY), GridPoint(headX, headY)), dir)
                
                if (existing.any { it.points.any { p -> p in candidate.points } }) continue
                
                if (blockedArrow == null) return candidate
                
                val path = getFuturePath(blockedArrow)
                if (candidate.points.any { it in path }) {
                    return candidate
                }
            }
        }
        return null
    }
    
    private fun placeTrap(random: Random, existing: Collection<ArrowItem>): ArrowItem? {
        for (attempt in 1..20) {
            val dir = Direction.values()[random.nextInt(4)]
            val tailX = random.nextInt(width)
            val tailY = random.nextInt(height)
            val headX = tailX + dir.dx
            val headY = tailY + dir.dy
            
            if (headX in 0 until width && headY in 0 until height) {
                val candidate = ArrowItem(-1, listOf(GridPoint(tailX, tailY), GridPoint(headX, headY)), dir)
                if (existing.any { it.points.any { p -> p in candidate.points } }) continue
                return candidate
            }
        }
        return null
    }
    
    private fun getFuturePath(arrow: ArrowItem): List<GridPoint> {
        val path = mutableListOf<GridPoint>()
        var current = arrow.points.last()
        for (i in 0..8) {
            current = GridPoint(current.x + arrow.headDirection.dx, current.y + arrow.headDirection.dy)
            if (current.x !in 0 until width || current.y !in 0 until height) break
            path.add(current)
        }
        return path
    }
}
INNER_EOF
