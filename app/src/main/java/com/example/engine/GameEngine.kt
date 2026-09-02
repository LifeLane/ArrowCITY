package com.example.engine

import com.example.model.ArrowItem
import com.example.model.CollisionInfo
import com.example.model.LevelData

sealed interface MoveResult {
    data class Valid(val arrow: ArrowItem) : MoveResult
    data class Invalid(val arrow: ArrowItem, val collision: CollisionInfo) : MoveResult
    data class Complete(val arrow: ArrowItem) : MoveResult
}

interface GameEngine {
    fun processPlayerAction(
        actionArrow: ArrowItem,
        activeArrows: List<ArrowItem>,
        levelData: LevelData
    ): MoveResult
}

class CurrentGameEngine : GameEngine {
    override fun processPlayerAction(
        actionArrow: ArrowItem,
        activeArrows: List<ArrowItem>,
        levelData: LevelData
    ): MoveResult {
        val currentArrow = activeArrows.firstOrNull { it.id == actionArrow.id } 
            ?: return MoveResult.Invalid(actionArrow, CollisionInfo(actionArrow.id, actionArrow.head, -1))

        val collision = PuzzleSolver.checkCollision(
            arrow = currentArrow,
            activeArrows = activeArrows,
            gridWidth = levelData.gridWidth,
            gridHeight = levelData.gridHeight
        )

        return if (collision != null) {
            MoveResult.Invalid(currentArrow, collision)
        } else {
            if (activeArrows.size == 1) {
                MoveResult.Complete(currentArrow)
            } else {
                MoveResult.Valid(currentArrow)
            }
        }
    }
}
