package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.engine.experimental.StepBoardState
import com.example.engine.experimental.StepMove
import com.example.engine.experimental.StepPuzzleFixtures
import com.example.engine.experimental.StepSlideEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class StepPrototypeUiState(
    val currentPrototype: String = "A", // A, B, C, D
    val boardState: StepBoardState = StepPuzzleFixtures.prototypeA,
    val movesCount: Int = 0,
    val blockedTapCount: Int = 0,
    val isComplete: Boolean = false,
    val showShakeArrowId: Int? = null
)

class StepSlidePrototypeViewModel : ViewModel() {
    private val engine = StepSlideEngine(5, 5)

    private val _uiState = MutableStateFlow(StepPrototypeUiState())
    val uiState: StateFlow<StepPrototypeUiState> = _uiState.asStateFlow()

    fun selectPrototype(name: String) {
        val initial = StepPuzzleFixtures.getPrototype(name)
        _uiState.update { 
            it.copy(
                currentPrototype = name,
                boardState = initial,
                movesCount = 0,
                blockedTapCount = 0,
                isComplete = false,
                showShakeArrowId = null
            )
        }
    }

    fun restart() {
        selectPrototype(_uiState.value.currentPrototype)
    }

    fun onArrowTapped(arrowId: Int) {
        val state = _uiState.value
        if (state.isComplete) return
        
        val moves = engine.getAvailableMoves(state.boardState)
        val move = StepMove(arrowId)
        
        if (move in moves) {
            val nextState = engine.applyMove(state.boardState, move)
            _uiState.update { 
                it.copy(
                    boardState = nextState,
                    movesCount = it.movesCount + 1,
                    isComplete = nextState.arrows.isEmpty(),
                    showShakeArrowId = null
                )
            }
        } else {
            // Blocked tap
            _uiState.update { 
                it.copy(
                    blockedTapCount = it.blockedTapCount + 1,
                    showShakeArrowId = arrowId
                )
            }
        }
    }
    
    fun clearShake() {
        _uiState.update { it.copy(showShakeArrowId = null) }
    }
}
