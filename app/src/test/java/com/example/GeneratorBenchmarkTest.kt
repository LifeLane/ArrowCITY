package com.example

import com.example.engine.experimental.BlueprintGenerator
import com.example.engine.experimental.StrategicBlueprint
import com.example.engine.experimental.DifficultyTier
import com.example.engine.ProceduralGenerator
import com.example.engine.PuzzleSolver
import com.example.model.CityRepository
import org.junit.Test
import java.io.File

class GeneratorBenchmarkTest {
    @Test
    fun benchmarkGenerators() {
        val sb = StringBuilder()
        sb.append("=== GENERATOR BENCHMARK ===\n\n")
        
        sb.append("--- NEW CONSTRUCTIVE GENERATOR ---\n")
        val tiers = listOf(StrategicBlueprint.EASY, StrategicBlueprint.MEDIUM, StrategicBlueprint.HARD, StrategicBlueprint.DEEP)
        for (tier in tiers) {
            val gen = BlueprintGenerator(5, 5)
            var totalTime = 0L
            var totalAttempts = 0
            var successCount = 0
            
            for (i in 1..5) {
                try {
                    val state = gen.generate(i.toLong(), tier)
                    totalTime += gen.generationTimeMs
                    totalAttempts += gen.attempts
                    successCount++
                } catch (e: Exception) {
                    sb.append("Tier ${tier.tier} Seed $i failed: ${e.message}\n")
                }
            }
            if (successCount > 0) {
                sb.append("Tier ${tier.tier}: Avg Time=${totalTime/successCount}ms, Avg Attempts=${totalAttempts/successCount}, Success Rate=${successCount}/5\n")
            }
        }
        
        sb.append("\n--- OLD PROCEDURAL GENERATOR ---\n")
        var oldTotalTime = 0L
        for (i in 1..20) {
            val startTime = System.currentTimeMillis()
            try {
                val level = ProceduralGenerator.generateLevel(i)
                oldTotalTime += (System.currentTimeMillis() - startTime)
            } catch (e: Exception) {
                sb.append("Old Gen Level $i failed\n")
            }
        }
        sb.append("Old Gen: Avg Time=${oldTotalTime/20}ms for 20 levels\n")
        
        File("benchmark_report.txt").writeText(sb.toString())
    }
}
