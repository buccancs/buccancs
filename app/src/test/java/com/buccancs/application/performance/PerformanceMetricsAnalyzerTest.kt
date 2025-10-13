package com.buccancs.application.performance

import com.buccancs.domain.model.PerformanceSummary
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PerformanceMetricsAnalyzerTest {

    private val json = Json { encodeDefaults = true }

    @Test
    fun `summarise writes summary file`() = runTest {
        val tempDir = createTempDirectory("metrics").toFile()
        val metricsFile = File(tempDir, "performance_metrics.jsonl")
        val summaryFile = File(tempDir, "performance_summary.json")
        val samples = listOf(
            PerformanceSample(
                sessionId = "session",
                timestampEpochMs = 0,
                cpuPercent = 20.0,
                processCpuMillis = 100,
                memoryPssMb = 512.0,
                javaHeapMb = 128.0,
                availableStorageMb = 10_000.0,
                batteryTempC = 35.0
            ),
            PerformanceSample(
                sessionId = "session",
                timestampEpochMs = 2_000,
                cpuPercent = 60.0,
                processCpuMillis = 200,
                memoryPssMb = 768.0,
                javaHeapMb = 196.0,
                availableStorageMb = 9_500.0,
                batteryTempC = 38.0
            )
        )
        metricsFile.writeText(samples.joinToString(separator = "\n") { json.encodeToString(it) })

        val analyzer = PerformanceMetricsAnalyzer(mockk(relaxed = true))
        analyzer.summarise("session", metricsFile, summaryFile)

        assertTrue(summaryFile.exists())
        val summary = json.decodeFromString<PerformanceSummary>(summaryFile.readText())
        assertEquals("session", summary.sessionId)
        assertEquals(2, summary.sampleCount)
        assertEquals(2.0, summary.durationSeconds, 0.001)
        assertEquals(40.0, summary.cpuAveragePercent, 0.001)
        assertEquals(60.0, summary.cpuMaxPercent, 0.001)
        assertEquals(9_500.0, summary.minAvailableStorageMb, 0.001)
        assertEquals(38.0, summary.maxBatteryTempC)
    }
}
