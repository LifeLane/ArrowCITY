package com.example.engine.experimental

import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GridPoint

object StepPuzzleFixtures {
    val prototypeA = StepBoardState(mapOf(
        1 to ArrowItem(1, listOf(GridPoint(3, 0), GridPoint(3, 1)), Direction.DOWN),
        2 to ArrowItem(2, listOf(GridPoint(3, 4), GridPoint(4, 4)), Direction.RIGHT),
        3 to ArrowItem(3, listOf(GridPoint(2, 2), GridPoint(1, 2)), Direction.LEFT),
    ))
    
    val prototypeB = StepBoardState(mapOf(
        1 to ArrowItem(1, listOf(GridPoint(2, 0), GridPoint(2, 1)), Direction.DOWN),
        2 to ArrowItem(2, listOf(GridPoint(0, 0), GridPoint(1, 0)), Direction.RIGHT),
        3 to ArrowItem(3, listOf(GridPoint(2, 2), GridPoint(1, 2)), Direction.LEFT),
        4 to ArrowItem(4, listOf(GridPoint(0, 4), GridPoint(0, 3)), Direction.UP),
    ))
    
    val prototypeC = StepBoardState(mapOf(
        1 to ArrowItem(1, listOf(GridPoint(0, 0), GridPoint(1, 0)), Direction.RIGHT),
        2 to ArrowItem(2, listOf(GridPoint(2, 4), GridPoint(2, 3)), Direction.UP),
        3 to ArrowItem(3, listOf(GridPoint(4, 2), GridPoint(3, 2)), Direction.LEFT),
        4 to ArrowItem(4, listOf(GridPoint(4, 0), GridPoint(4, 1)), Direction.DOWN),
    ))
    
    val prototypeD = StepBoardState(mapOf(
        1 to ArrowItem(1, listOf(GridPoint(2, 0), GridPoint(2, 1)), Direction.DOWN),
        2 to ArrowItem(2, listOf(GridPoint(0, 1), GridPoint(1, 1)), Direction.RIGHT),
        3 to ArrowItem(3, listOf(GridPoint(0, 0), GridPoint(1, 0)), Direction.RIGHT),
        4 to ArrowItem(4, listOf(GridPoint(0, 4), GridPoint(0, 3)), Direction.UP),
        5 to ArrowItem(5, listOf(GridPoint(4, 3), GridPoint(3, 3)), Direction.LEFT),
    ))
    
    fun getPrototype(name: String): StepBoardState {
        return when (name) {
            "A" -> prototypeA
            "B" -> prototypeB
            "C" -> prototypeC
            "D" -> prototypeD
            else -> prototypeA
        }
    }
}
