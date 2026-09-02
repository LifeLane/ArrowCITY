package com.example.engine.experimental

import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GridPoint
import java.util.Random

class StepConstructiveGenerator(val width: Int, val height: Int) {
    
    var attempts = 0
    var generationTimeMs = 0L
    val rejectionCounts = mutableMapOf<String, Int>()
    
    fun generatePrototype(seed: Long, targetDepth: Int): StepBoardState {
        val random = Random(seed)
        attempts = 0
        rejectionCounts.clear()
        
        val startTime = System.currentTimeMillis()
        
        for (attempt in 1..2000) {
            attempts++
            val candidate = buildSkeleton(random, targetDepth)
            if (candidate == null) {
                incrementRejection("SKELETON_FAILED")
                continue
            }
            
            if (candidate.arrows.size > 7) {
                incrementRejection("TOO_MANY_ARROWS")
                continue
            }
            
            val analyzer = StepPuzzleAnalyzer(StepSlideEngine(width, height), candidate, maxStates = 4000)
            val analysis = try {
                analyzer.analyze()
            } catch (e: IllegalStateException) {
                incrementRejection("STATE_EXPLOSION")
                continue
            }
            
            if (!analysis.solvable) {
                incrementRejection("UNSOLVABLE")
                continue
            }
            
            if (analysis.strategicDepth < targetDepth) {
                incrementRejection("DEPTH_TOO_LOW")
                continue
            }
            
            if (analysis.repetitiveMoveRatio > 0.85f) {
                incrementRejection("HIGH_REPETITIVE_RATIO")
                continue
            }
            
            if (analysis.minimumSolutionLength > 40) {
                incrementRejection("SOLUTION_TOO_LONG")
                continue
            }
            
            if (targetDepth > 0 && analysis.criticalChoices == 0) {
                incrementRejection("NO_CRITICAL_CHOICES")
                continue
            }
            
            if (targetDepth > 0 && analysis.deadEndCount == 0) {
                incrementRejection("NO_DEAD_ENDS")
                continue
            }
            
            generationTimeMs = System.currentTimeMillis() - startTime
            return candidate
        }
        generationTimeMs = System.currentTimeMillis() - startTime
        throw IllegalStateException("Failed to constructively generate puzzle for depth $targetDepth after $attempts attempts")
    }
    
    private fun incrementRejection(reason: String) {
        rejectionCounts[reason] = (rejectionCounts[reason] ?: 0) + 1
    }
    
    private fun buildSkeleton(random: Random, targetDepth: Int): StepBoardState? {
        var arrows = mutableMapOf<Int, ArrowItem>()
        var nextId = 1
        
        // Use fewer arrows to avoid state explosion
        val chainLength = when (targetDepth) {
            0 -> 2 + random.nextInt(2)
            1 -> 3 + random.nextInt(2)
            else -> 4 + random.nextInt(2)
        }
        
        var currentBlocker: ArrowItem? = null
        for (i in 0 until chainLength) {
            val arrow = placeArrowBlocking(random, currentBlocker, arrows.values) ?: return null
            arrows[nextId] = arrow
            currentBlocker = arrow
            nextId++
        }
        
        val numTraps = when (targetDepth) {
            0 -> 0
            1 -> 1
            2 -> 1 + random.nextInt(2)
            else -> 2 + random.nextInt(2)
        }
        
        for (i in 0 until numTraps) {
            val trap = placeTrap(random, arrows.values)
            if (trap != null) {
                arrows[nextId] = trap
                nextId++
            }
        }
        
        return StepBoardState(arrows)
    }
    
    private fun placeArrowBlocking(random: Random, blockedArrow: ArrowItem?, existing: Collection<ArrowItem>): ArrowItem? {
        for (attempt in 1..30) {
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
        for (attempt in 1..30) {
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
        for (i in 0..10) {
            current = GridPoint(current.x + arrow.headDirection.dx, current.y + arrow.headDirection.dy)
            if (current.x !in 0 until width || current.y !in 0 until height) break
            path.add(current)
        }
        return path
    }
}
