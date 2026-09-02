package com.example

import com.example.engine.PublishedBetaLevels
import com.example.engine.CuratedLevels
import com.example.engine.experimental.StepSlideEngine
import com.example.engine.experimental.StepPuzzleAnalyzer
import com.example.engine.experimental.StepBoardState
import org.junit.Test
import java.io.File

class LevelProgressionAuditTest {
    @Test
    fun auditLevels() {
        val sb = StringBuilder()
        sb.append("=== Level Progression Audit ===\n")
        
        var solvableCount = 0
        var totalCount = 0
        
        for (i in 1..200) {
            val level = PublishedBetaLevels.levels[i] ?: CuratedLevels.curatedMap[i]
            if (level != null) {
                totalCount++
                val state = StepBoardState(level.arrows.associateBy { it.id })
                val analyzer = StepPuzzleAnalyzer(StepSlideEngine(level.gridWidth, level.gridHeight), state, maxStates = 5000)
                try {
                    val metrics = analyzer.analyze()
                    sb.append("Level $i: Solvable=${metrics.solvable} Depth=${metrics.strategicDepth} Length=${metrics.minimumSolutionLength} Choices=${metrics.criticalChoices} DeadEnds=${metrics.deadEndCount} States=${metrics.reachableStateCount}\n")
                    if (metrics.solvable) solvableCount++
                } catch (e: Exception) {
                    sb.append("Level $i: FAILED TO ANALYZE - ${e.message}\n")
                }
            } else {
                sb.append("Level $i: MISSING\n")
            }
        }
        sb.append("\nSummary: $solvableCount / $totalCount solvable\n")
        File("level_audit.txt").writeText(sb.toString())
    }
}
