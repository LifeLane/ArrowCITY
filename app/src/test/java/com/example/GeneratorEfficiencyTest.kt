package com.example

import com.example.engine.experimental.StepConstructiveGenerator
import org.junit.Test
import java.io.File

class GeneratorEfficiencyTest {
    @Test
    fun printEfficiency() {
        val generator = StepConstructiveGenerator(5, 5)
        val sb = StringBuilder()
        sb.append("=== Generator Efficiency ===\n")
        val state = generator.generatePrototype(12345L, 2)
        sb.append("Target Depth: 2\n")
        sb.append("Total Attempts: ${generator.attempts}\n")
        sb.append("Time (ms): ${generator.generationTimeMs}\n")
        sb.append("Rejections:\n")
        generator.rejectionCounts.forEach { (reason, count) ->
            sb.append(" - $reason: $count\n")
        }
        sb.append("============================\n")
        File("efficiency_output.txt").writeText(sb.toString())
    }
}
