package com.example.engine

import com.example.model.LevelData

/**
 * Immutable representation of the board state for analytical search.
 */
data class BoardState(
    val activeArrowIds: Set<Int>
)

/**
 * A deterministic representation of a possible move.
 */
data class Move(
    val arrowId: Int
)

/**
 * Compact immutable analysis result containing the requested metrics.
 */
data class PuzzleAnalysis(
    val solvable: Boolean,
    val minimumSolutionLength: Int,
    val maximumSearchDepth: Int,
    val maximumBranchingFactor: Int,
    val forcedMoveCount: Int,
    val choiceStateCount: Int,
    val deadEndCount: Int,
    val reasoningDepth: Int
)

class PuzzleAnalyzer(
    private val levelData: LevelData
) {
    // For the current rules, dependencies are fully static.
    // If arrow A points towards arrow B, A is blocked by B until B is removed.
    // Removing any arrow can NEVER create a new block.
    // This allows us to precompute the blocking graph once.
    private val dependencyGraph: Map<Int, Set<Int>> = PuzzleSolver.buildDependencyGraph(
        levelData.arrows, 
        levelData.gridWidth, 
        levelData.gridHeight
    )

    fun getInitialState(): BoardState {
        return BoardState(levelData.arrows.map { it.id }.toSet())
    }

    /**
     * Enumerates all available moves (arrows that can currently be cleared without collision).
     * This uses the dependency graph, derived from trusted collision mathematics.
     */
    fun getAvailableMoves(state: BoardState): List<Move> {
        val available = mutableListOf<Move>()
        for (id in state.activeArrowIds) {
            val blockers = dependencyGraph[id] ?: emptySet()
            if (blockers.none { it in state.activeArrowIds }) {
                available.add(Move(id))
            }
        }
        return available
    }

    /**
     * Deterministic state transition.
     * Returns the resulting state if the move is valid, or the original state if invalid.
     */
    fun applyMove(state: BoardState, move: Move): BoardState {
        if (!getAvailableMoves(state).contains(move)) {
            return state // invalid move does not mutate state
        }
        return BoardState(state.activeArrowIds - move.arrowId)
    }

    /**
     * Explores the state-space via BFS to determine puzzle metrics.
     */
    fun analyze(): PuzzleAnalysis {
        val initialState = getInitialState()
        val queue = ArrayDeque<BoardState>()
        val visited = mutableSetOf<BoardState>()
        
        queue.add(initialState)
        visited.add(initialState)
        
        var solvable = false
        var maxDepthExplored = 0
        var maxBranching = 0
        var forcedMoveCount = 0
        var choiceStateCount = 0
        var deadEndCount = 0
        
        // Safety limit to prevent unbounded state-space exploration
        val maxStatesLimit = 15000 
        var truncated = false
        
        while (queue.isNotEmpty()) {
            if (visited.size > maxStatesLimit) {
                truncated = true
                break
            }
            
            val state = queue.removeFirst()
            
            if (state.activeArrowIds.isEmpty()) {
                solvable = true
                continue
            }
            
            val moves = getAvailableMoves(state)
            
            if (moves.isEmpty()) {
                deadEndCount++
            } else if (moves.size == 1) {
                forcedMoveCount++
            } else {
                choiceStateCount++
            }
            
            if (moves.size > maxBranching) {
                maxBranching = moves.size
            }
            
            val depth = initialState.activeArrowIds.size - state.activeArrowIds.size
            if (depth > maxDepthExplored) {
                maxDepthExplored = depth
            }
            
            for (move in moves) {
                val nextState = applyMove(state, move)
                if (visited.add(nextState)) {
                    queue.add(nextState)
                }
            }
        }
        
        // For current monotonic DAG rules, reasoning depth is 0
        // because ANY choice is safe. There are no dead ends or traps (except if unsolvable from start).
        return PuzzleAnalysis(
            solvable = solvable,
            minimumSolutionLength = if (solvable) initialState.activeArrowIds.size else -1,
            maximumSearchDepth = maxDepthExplored,
            maximumBranchingFactor = maxBranching,
            forcedMoveCount = forcedMoveCount,
            choiceStateCount = choiceStateCount,
            deadEndCount = deadEndCount,
            reasoningDepth = 0
        )
    }
}
