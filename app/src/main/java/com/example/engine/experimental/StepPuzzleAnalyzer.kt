package com.example.engine.experimental

data class StepPuzzleAnalysis(
    val solvable: Boolean,
    val minimumMoves: Int,
    val deadEndCount: Int,
    val choiceStates: Int,
    val criticalChoices: Int,
    val successfulPaths: Int,
    val failedPaths: Int,
    val reasoningDepth: Int
)

class StepPuzzleAnalyzer(val engine: StepSlideEngine, val initialState: StepBoardState) {
    
    val stateSolvable = mutableMapOf<StepBoardState, Boolean>()
    var deadEndCount = 0
    var choiceStates = 0
    var criticalChoices = 0
    var successfulPaths = 0
    var failedPaths = 0
    
    fun analyze(): StepPuzzleAnalysis {
        val solvable = dfs(initialState)
        
        var minMoves = -1
        if (solvable) {
            minMoves = findMinMoves(initialState)
        }
        
        return StepPuzzleAnalysis(
            solvable = solvable,
            minimumMoves = minMoves,
            deadEndCount = deadEndCount,
            choiceStates = choiceStates,
            criticalChoices = criticalChoices,
            successfulPaths = successfulPaths,
            failedPaths = failedPaths,
            reasoningDepth = criticalChoices 
        )
    }
    
    private fun dfs(state: StepBoardState): Boolean {
        if (state.arrows.isEmpty()) {
            successfulPaths++
            return true
        }
        
        if (stateSolvable.containsKey(state)) {
            return stateSolvable[state]!!
        }
        
        val moves = engine.getAvailableMoves(state)
        
        if (moves.isEmpty()) {
            deadEndCount++
            failedPaths++
            stateSolvable[state] = false
            return false
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
    
    private fun findMinMoves(start: StepBoardState): Int {
        val queue = ArrayDeque<Pair<StepBoardState, Int>>()
        val visited = mutableSetOf<StepBoardState>()
        
        queue.add(start to 0)
        visited.add(start)
        
        while (queue.isNotEmpty()) {
            val (state, dist) = queue.removeFirst()
            
            if (state.arrows.isEmpty()) {
                return dist
            }
            
            for (move in engine.getAvailableMoves(state)) {
                val nextState = engine.applyMove(state, move)
                if (visited.add(nextState)) {
                    queue.add(nextState to (dist + 1))
                }
            }
        }
        return -1
    }
}
