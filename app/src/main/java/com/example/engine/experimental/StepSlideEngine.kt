package com.example.engine.experimental

import com.example.model.ArrowItem
import com.example.model.GridPoint

data class StepBoardState(
    val arrows: Map<Int, ArrowItem>
)

data class StepMove(val arrowId: Int)

class StepSlideEngine(val gridWidth: Int, val gridHeight: Int) {

    fun getAvailableMoves(state: StepBoardState): List<StepMove> {
        val validMoves = mutableListOf<StepMove>()
        
        for ((id, arrow) in state.arrows) {
            val nextPoints = arrow.points.map { it.plus(arrow.headDirection) }
            
            // Check collision with OTHER arrows
            var collision = false
            for ((otherId, otherArrow) in state.arrows) {
                if (id == otherId) continue
                // If any point in nextPoints is inside otherArrow.points
                if (nextPoints.any { it in otherArrow.points }) {
                    collision = true
                    break
                }
            }
            
            if (!collision) {
                validMoves.add(StepMove(id))
            }
        }
        
        return validMoves
    }
    
    fun applyMove(state: StepBoardState, move: StepMove): StepBoardState {
        val arrow = state.arrows[move.arrowId] ?: return state
        
        val nextPoints = arrow.points.map { it.plus(arrow.headDirection) }
        
        // Collision check
        for ((otherId, otherArrow) in state.arrows) {
            if (move.arrowId == otherId) continue
            if (nextPoints.any { it in otherArrow.points }) {
                return state // Invalid move
            }
        }
        
        // Check if fully out of bounds
        val isOutOfBounds = nextPoints.all { 
            it.x < 0 || it.x >= gridWidth || it.y < 0 || it.y >= gridHeight 
        }
        
        val newArrows = state.arrows.toMutableMap()
        if (isOutOfBounds) {
            newArrows.remove(move.arrowId)
        } else {
            newArrows[move.arrowId] = arrow.copy(points = nextPoints)
        }
        
        return StepBoardState(newArrows)
    }
}
