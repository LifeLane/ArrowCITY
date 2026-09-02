package com.example.engine.experimental

import kotlin.math.max
import kotlin.math.min

data class PuzzleQuality(
    val solvable: Boolean,
    val strategicDepth: Int,
    val criticalChoices: Int,
    val deadEndCount: Int,
    val minimumSolutionLength: Int,
    val maximumSolutionLength: Int,
    val reachableStateCount: Int,
    val initialLegalMoves: Int,
    val maximumBranchingFactor: Int,
    val successfulPaths: Int,
    val failedPaths: Int,
    val forcedMoveCount: Int,
    val decisionDensity: Float,
    val recoveryPotential: Float,
    val trapSeverity: Int,
    val repetitiveMoveRatio: Float
)

class StepPuzzleAnalyzer(val engine: StepSlideEngine, val initialState: StepBoardState, val maxStates: Int = 5000) {
    
    val stateSolvable = mutableMapOf<StepBoardState, Boolean>()
    val stateStrategicDepth = mutableMapOf<StepBoardState, Int>()
    
    var deadEndCount = 0
    var choiceStates = 0
    var criticalChoices = 0
    var successfulPaths = 0
    var failedPaths = 0
    
    var maximumBranchingFactor = 0
    var forcedMoveCount = 0
    
    // distance to dead end
    val stateTrapDepth = mutableMapOf<StepBoardState, Int>()
    
    fun analyze(): PuzzleQuality {
        val solvable = dfs(initialState)
        val strategicDepth = if (solvable) calculateStrategicDepth(initialState) else 0
        
        var minMoves = -1
        var maxMoves = -1
        var initialLegal = 0
        var repRatio = 0f
        var decisionDen = 0f
        var recovPotential = 0f
        var maxTrapSeverity = 0
        
        if (solvable) {
            val movesPaths = findPathsMetrics(initialState)
            minMoves = movesPaths.minMoves
            maxMoves = movesPaths.maxMoves
            
            initialLegal = engine.getAvailableMoves(initialState).size
            
            decisionDen = if (minMoves > 0) strategicDepth.toFloat() / minMoves.toFloat() else 0f
            repRatio = if (minMoves > 0) forcedMoveCount.toFloat() / stateSolvable.size.toFloat() else 0f // or something related to path
            
            // Calculate Trap Depth
            maxTrapSeverity = calculateTrapSeverity(initialState)
            
            // Recovery Potential (number of recoverable mistakes vs total mistakes)
            // A mistake is choosing an unsolvable branch. A recoverable mistake is an incorrect move that 
            // does NOT lead to an unsolvable branch? Wait.
            // If it's a mistake, it leads to a worse state. A recoverable mistake leads to a solvable state but maybe longer path?
            // "recoverable mistake" = choosing a branch that is not the optimal path, but is still solvable!
            // Wait, prompt says: "recoverable mistake = choosing a branch that is solvable but maybe not intended", 
            // "recoveryPotential = whether an incorrect decision always causes immediate irreversible failure."
            // Let's define it as: out of all choice states with >1 legal move, how many have >1 solvable branch?
            var choiceWithMultiSolvable = 0
            var totalChoices = 0
            for ((state, isSolvable) in stateSolvable) {
                val moves = engine.getAvailableMoves(state)
                if (moves.size > 1) {
                    totalChoices++
                    val solvableBranches = moves.count { stateSolvable[engine.applyMove(state, it)] == true }
                    if (solvableBranches > 1) {
                        choiceWithMultiSolvable++
                    }
                }
            }
            recovPotential = if (totalChoices > 0) choiceWithMultiSolvable.toFloat() / totalChoices.toFloat() else 0f
            
            // Repetitive move ratio: sequences of 1-legal-move states along the minimum solution path.
            repRatio = if (minMoves > 0) calculateRepetitiveRatioOnShortestPath(initialState, minMoves) else 0f
        }
        
        return PuzzleQuality(
            solvable = solvable,
            strategicDepth = strategicDepth,
            criticalChoices = criticalChoices,
            deadEndCount = deadEndCount,
            minimumSolutionLength = minMoves,
            maximumSolutionLength = maxMoves,
            reachableStateCount = stateSolvable.size,
            initialLegalMoves = initialLegal,
            maximumBranchingFactor = maximumBranchingFactor,
            successfulPaths = successfulPaths,
            failedPaths = failedPaths,
            forcedMoveCount = forcedMoveCount,
            decisionDensity = decisionDen,
            recoveryPotential = recovPotential,
            trapSeverity = maxTrapSeverity,
            repetitiveMoveRatio = repRatio
        )
    }
    
    private fun dfs(state: StepBoardState): Boolean {
        if (stateSolvable.size > maxStates) {
            throw IllegalStateException("State space exploded (> $maxStates)")
        }
        if (state.arrows.isEmpty()) {
            successfulPaths++
            stateSolvable[state] = true
            return true
        }
        
        if (stateSolvable.containsKey(state)) {
            return stateSolvable[state]!!
        }
        
        val moves = engine.getAvailableMoves(state)
        
        if (moves.size > maximumBranchingFactor) {
            maximumBranchingFactor = moves.size
        }
        
        if (moves.isEmpty()) {
            deadEndCount++
            failedPaths++
            stateSolvable[state] = false
            return false
        }
        
        if (moves.size == 1) {
            forcedMoveCount++
        }
        
        if (moves.size > 1) {
            choiceStates++
        }
        
        var anySolvable = false
        var anyUnsolvable = false
        
        for (move in moves) {
            val nextState = engine.applyMove(state, move)
            val isNextSolvable = dfs(nextState)
            if (isNextSolvable) anySolvable = true else anyUnsolvable = true
        }
        
        if (moves.size > 1 && anySolvable && anyUnsolvable) {
            criticalChoices++
        }
        
        stateSolvable[state] = anySolvable
        return anySolvable
    }
    
    private fun calculateStrategicDepth(state: StepBoardState): Int {
        if (state.arrows.isEmpty()) return 0
        if (stateStrategicDepth.containsKey(state)) return stateStrategicDepth[state]!!
        
        val moves = engine.getAvailableMoves(state)
        var anySolvable = false
        var anyUnsolvable = false
        
        val solvableNextStates = mutableListOf<StepBoardState>()
        
        for (move in moves) {
            val nextState = engine.applyMove(state, move)
            val isSolvable = stateSolvable[nextState] == true
            if (isSolvable) {
                anySolvable = true
                solvableNextStates.add(nextState)
            } else {
                anyUnsolvable = true
            }
        }
        
        val isCriticalChoice = (moves.size > 1 && anySolvable && anyUnsolvable)
        
        var maxDepth = 0
        for (nextState in solvableNextStates) {
            val depth = calculateStrategicDepth(nextState)
            if (depth > maxDepth) {
                maxDepth = depth
            }
        }
        
        val result = if (isCriticalChoice) maxDepth + 1 else maxDepth
        stateStrategicDepth[state] = result
        return result
    }
    
    private class PathMetrics(val minMoves: Int, val maxMoves: Int)
    
    private fun findPathsMetrics(start: StepBoardState): PathMetrics {
        // Find shortest and longest path to empty state
        val minDists = mutableMapOf<StepBoardState, Int>()
        val maxDists = mutableMapOf<StepBoardState, Int>()
        
        // Since the state graph is a DAG (arrows only move forward or exit), we can compute this using a topological order or recursion.
        fun getDists(state: StepBoardState): Pair<Int, Int> { // returns (min, max) from state to empty
            if (state.arrows.isEmpty()) return 0 to 0
            if (minDists.containsKey(state)) return minDists[state]!! to maxDists[state]!!
            
            var minDist = Int.MAX_VALUE
            var maxDist = -1
            
            val moves = engine.getAvailableMoves(state)
            for (move in moves) {
                val nextState = engine.applyMove(state, move)
                if (stateSolvable[nextState] == true) {
                    val (minD, maxD) = getDists(nextState)
                    if (minD != Int.MAX_VALUE) {
                        minDist = min(minDist, minD + 1)
                        maxDist = max(maxDist, maxD + 1)
                    }
                }
            }
            
            minDists[state] = minDist
            maxDists[state] = maxDist
            return minDist to maxDist
        }
        
        val (min, max) = getDists(start)
        return PathMetrics(if (min == Int.MAX_VALUE) -1 else min, max)
    }
    
    // Calculates the longest path from a state to a dead end
    private fun calculateTrapSeverity(state: StepBoardState): Int {
        if (state.arrows.isEmpty()) return 0
        if (stateTrapDepth.containsKey(state)) return stateTrapDepth[state]!!
        
        val moves = engine.getAvailableMoves(state)
        if (moves.isEmpty()) return 0 // It is a dead end
        
        // A state's trap severity is the max of the trap severities of its children
        // PLUS ONE if the child is unsolvable. Wait:
        // For unsolvable children: severity is 1 + severity(child)
        // For solvable children: severity is severity(child) (the trap doesn't start here, it's somewhere down the line)
        var maxSeverity = 0
        for (move in moves) {
            val nextState = engine.applyMove(state, move)
            if (stateSolvable[nextState] == false) {
                val severity = 1 + getUnsolvableDepth(nextState)
                maxSeverity = max(maxSeverity, severity)
            } else {
                val severity = calculateTrapSeverity(nextState)
                maxSeverity = max(maxSeverity, severity)
            }
        }
        
        stateTrapDepth[state] = maxSeverity
        return maxSeverity
    }
    
    private val unsolvableDepth = mutableMapOf<StepBoardState, Int>()
    private fun getUnsolvableDepth(state: StepBoardState): Int {
        val moves = engine.getAvailableMoves(state)
        if (moves.isEmpty()) return 0
        
        if (unsolvableDepth.containsKey(state)) return unsolvableDepth[state]!!
        
        var maxD = 0
        for (move in moves) {
            val nextState = engine.applyMove(state, move)
            maxD = max(maxD, 1 + getUnsolvableDepth(nextState))
        }
        unsolvableDepth[state] = maxD
        return maxD
    }
    
    private fun calculateRepetitiveRatioOnShortestPath(start: StepBoardState, minMoves: Int): Float {
        // Traverse one of the shortest paths, count how many states had only 1 legal move.
        var curr = start
        var forcedCount = 0
        var steps = 0
        while (curr.arrows.isNotEmpty()) {
            val moves = engine.getAvailableMoves(curr)
            if (moves.size == 1) forcedCount++
            
            // Find next state on shortest path
            val nextStates = moves.map { engine.applyMove(curr, it) }.filter { stateSolvable[it] == true }
            curr = nextStates.minByOrNull { findPathsMetrics(it).minMoves } ?: break
            steps++
        }
        return if (steps > 0) forcedCount.toFloat() / steps.toFloat() else 0f
    }
}
