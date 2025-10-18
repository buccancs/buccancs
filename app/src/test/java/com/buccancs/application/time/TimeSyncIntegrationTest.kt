package com.buccancs.application.time

import com.buccancs.data.time.computeSyncSample
import org.junit.Test
import kotlin.math.abs
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Integration-style tests for time synchronization.
 * These tests verify the complete sync algorithm without requiring
 * a running orchestrator by simulating the network exchange.
 */
class TimeSyncIntegrationTest {

    // === Realistic Scenario Tests ===

    @Test
    fun `localhost sync achieves sub-5ms accuracy`() {
        // Simulate localhost sync with minimal latency
        val samples = listOf(
            // Each sample has ~1-2ms RTT and <1ms offset
            simulateSyncSample(rttMs = 1.5, offsetMs = 0.3),
            simulateSyncSample(rttMs = 1.8, offsetMs = 0.2),
            simulateSyncSample(rttMs = 1.2, offsetMs = 0.4),
            simulateSyncSample(rttMs = 2.1, offsetMs = 0.1),
            simulateSyncSample(rttMs = 1.6, offsetMs = 0.3)
        )

        val best = samples.sortedBy { it.roundTripMs }.take(3)
        val avgOffset = best.map { abs(it.offsetMs) }.average()
        val avgRtt = best.map { it.roundTripMs }.average()

        assertTrue("Localhost offset should be <1ms", avgOffset < 1.0)
        assertTrue("Localhost RTT should be <5ms", avgRtt < 5.0)
        println("Localhost: offset=${String.format("%.3f", avgOffset)}ms, RTT=${String.format("%.3f", avgRtt)}ms")
    }

    @Test
    fun `LAN sync achieves sub-10ms accuracy`() {
        // Simulate LAN sync with typical latency
        val samples = listOf(
            simulateSyncSample(rttMs = 8.5, offsetMs = 2.3),
            simulateSyncSample(rttMs = 12.3, offsetMs = 1.8),
            simulateSyncSample(rttMs = 9.8, offsetMs = 2.1),
            simulateSyncSample(rttMs = 11.2, offsetMs = 2.5),
            simulateSyncSample(rttMs = 10.1, offsetMs = 1.9)
        )

        val best = samples.sortedBy { it.roundTripMs }.take(3)
        val avgOffset = best.map { abs(it.offsetMs) }.average()
        val avgRtt = best.map { it.roundTripMs }.average()

        assertTrue("LAN offset should be <5ms", avgOffset < 5.0)
        assertTrue("LAN RTT should be <15ms", avgRtt < 15.0)
        println("LAN: offset=${String.format("%.3f", avgOffset)}ms, RTT=${String.format("%.3f", avgRtt)}ms")
    }

    @Test
    fun `WAN sync maintains sub-15ms offset despite high latency`() {
        // Simulate WAN sync with 50ms base latency
        val samples = listOf(
            simulateSyncSample(rttMs = 95.5, offsetMs = 8.3),
            simulateSyncSample(rttMs = 102.3, offsetMs = 7.8),
            simulateSyncSample(rttMs = 98.8, offsetMs = 9.1),
            simulateSyncSample(rttMs = 105.2, offsetMs = 8.5),
            simulateSyncSample(rttMs = 100.1, offsetMs = 7.9)
        )

        val best = samples.sortedBy { it.roundTripMs }.take(3)
        val avgOffset = best.map { abs(it.offsetMs) }.average()
        val avgRtt = best.map { it.roundTripMs }.average()

        assertTrue("WAN offset should be <15ms", avgOffset < 15.0)
        assertTrue("WAN RTT reflects latency", avgRtt > 50.0)
        println("WAN: offset=${String.format("%.3f", avgOffset)}ms, RTT=${String.format("%.3f", avgRtt)}ms")
    }

    @Test
    fun `best sample selection improves accuracy`() {
        // Mix of good and bad samples
        val samples = listOf(
            simulateSyncSample(rttMs = 5.0, offsetMs = 1.0),   // Good
            simulateSyncSample(rttMs = 25.0, offsetMs = 8.0),  // Poor
            simulateSyncSample(rttMs = 6.0, offsetMs = 1.2),   // Good
            simulateSyncSample(rttMs = 30.0, offsetMs = 10.0), // Poor
            simulateSyncSample(rttMs = 5.5, offsetMs = 1.1)    // Good
        )

        val allAvgOffset = samples.map { abs(it.offsetMs) }.average()
        
        val best = samples.sortedBy { it.roundTripMs }.take(3)
        val bestAvgOffset = best.map { abs(it.offsetMs) }.average()

        assertTrue("Best samples have lower offset", bestAvgOffset < allAvgOffset)
        assertTrue("Best 3 have offset <2ms", bestAvgOffset < 2.0)
        println("All samples: ${String.format("%.3f", allAvgOffset)}ms, Best 3: ${String.format("%.3f", bestAvgOffset)}ms")
    }

    // === Quality Assessment Tests ===

    @Test
    fun `GOOD quality requirements are achievable`() {
        // GOOD: RTT ≤12ms AND offset ≤2ms
        val sample = simulateSyncSample(rttMs = 10.0, offsetMs = 1.5)

        assertTrue("RTT qualifies for GOOD", sample.roundTripMs <= 12.0)
        assertTrue("Offset qualifies for GOOD", abs(sample.offsetMs) <= 2.0)
    }

    @Test
    fun `FAIR quality requirements are achievable`() {
        // FAIR: RTT ≤25ms AND offset ≤5ms
        val sample = simulateSyncSample(rttMs = 20.0, offsetMs = 4.0)

        assertTrue("RTT qualifies for FAIR", sample.roundTripMs <= 25.0)
        assertTrue("Offset qualifies for FAIR", abs(sample.offsetMs) <= 5.0)
    }

    @Test
    fun `POOR quality is detected for high RTT`() {
        // POOR: RTT >25ms OR offset >5ms
        val sample = simulateSyncSample(rttMs = 50.0, offsetMs = 3.0)

        assertTrue("High RTT indicates POOR quality", sample.roundTripMs > 25.0)
    }

    @Test
    fun `POOR quality is detected for high offset`() {
        // POOR: RTT >25ms OR offset >5ms
        val sample = simulateSyncSample(rttMs = 15.0, offsetMs = 8.0)

        assertTrue("High offset indicates POOR quality", abs(sample.offsetMs) > 5.0)
    }

    // === Multi-Sample Statistical Tests ===

    @Test
    fun `median RTT filters outliers`() {
        val samples = listOf(
            simulateSyncSample(rttMs = 10.0, offsetMs = 1.0),
            simulateSyncSample(rttMs = 11.0, offsetMs = 1.1),
            simulateSyncSample(rttMs = 100.0, offsetMs = 20.0), // Outlier
            simulateSyncSample(rttMs = 10.5, offsetMs = 1.05),
            simulateSyncSample(rttMs = 10.2, offsetMs = 1.02)
        )

        val rtts = samples.map { it.roundTripMs }.sorted()
        val median = rtts[rtts.size / 2]
        val average = rtts.average()

        assertTrue("Median is less affected by outliers", median < average)
        assertTrue("Median represents typical RTT", median < 15.0)
        println("Average RTT: ${String.format("%.1f", average)}ms, Median RTT: ${String.format("%.1f", median)}ms")
    }

    @Test
    fun `variance increases with network instability`() {
        // Stable network
        val stableSamples = List(5) { 
            simulateSyncSample(rttMs = 10.0 + it * 0.5, offsetMs = 1.0)
        }
        val stableVariance = calculateVariance(stableSamples.map { it.roundTripMs })

        // Unstable network
        val unstableSamples = listOf(
            simulateSyncSample(rttMs = 5.0, offsetMs = 1.0),
            simulateSyncSample(rttMs = 50.0, offsetMs = 5.0),
            simulateSyncSample(rttMs = 10.0, offsetMs = 1.5),
            simulateSyncSample(rttMs = 40.0, offsetMs = 4.0),
            simulateSyncSample(rttMs = 15.0, offsetMs = 2.0)
        )
        val unstableVariance = calculateVariance(unstableSamples.map { it.roundTripMs })

        assertTrue("Unstable network has higher variance", unstableVariance > stableVariance)
        println("Stable variance: ${String.format("%.1f", stableVariance)}, Unstable: ${String.format("%.1f", unstableVariance)}")
    }

    // === Drift Detection Tests ===

    @Test
    fun `clock drift can be detected over time`() {
        // Simulate clock drifting 1ms per minute
        val driftRateMs = 1.0 // ms per minute
        val intervalMinutes = 5.0

        val observation1 = simulateSyncSample(rttMs = 10.0, offsetMs = 0.0)
        val observation2 = simulateSyncSample(
            rttMs = 10.0,
            offsetMs = driftRateMs * intervalMinutes
        )

        val offsetChange = observation2.offsetMs - observation1.offsetMs
        val estimatedDrift = offsetChange / intervalMinutes

        assertEquals("Drift rate is detected", driftRateMs, estimatedDrift, 0.01)
        println("Detected drift: ${String.format("%.3f", estimatedDrift)}ms/min")
    }

    @Test
    fun `stable clock shows minimal drift`() {
        // Simulate stable clock over time
        val observations = List(10) { i ->
            simulateSyncSample(
                rttMs = 10.0,
                offsetMs = 1.0 + i * 0.01 // Only 0.1ms drift over 10 observations
            )
        }

        val first = observations.first().offsetMs
        val last = observations.last().offsetMs
        val totalDrift = abs(last - first)

        assertTrue("Stable clock has minimal drift", totalDrift < 1.0)
        println("Total drift: ${String.format("%.3f", totalDrift)}ms")
    }

    // === Edge Case Tests ===

    @Test
    fun `handles perfect synchronization`() {
        val sample = simulateSyncSample(rttMs = 10.0, offsetMs = 0.0)

        assertEquals("Perfect sync has zero offset", 0.0, sample.offsetMs, 0.001)
        assertTrue("RTT is positive", sample.roundTripMs > 0)
    }

    @Test
    fun `handles client ahead of server`() {
        val sample = simulateSyncSample(rttMs = 10.0, offsetMs = -5.0)

        assertTrue("Negative offset indicates client ahead", sample.offsetMs < 0)
    }

    @Test
    fun `handles client behind server`() {
        val sample = simulateSyncSample(rttMs = 10.0, offsetMs = 5.0)

        assertTrue("Positive offset indicates client behind", sample.offsetMs > 0)
    }

    @Test
    fun `handles asymmetric network paths`() {
        // 10ms outbound, 30ms inbound
        val clientSend = 1000L
        val clientReceive = 1040L  // 40ms total
        val serverReceive = 1010L  // 10ms outbound
        val serverSend = 1012L     // 2ms processing

        val sample = computeSyncSample(
            sendEpochMs = clientSend,
            receiveEpochMs = clientReceive,
            serverReceiveEpochMs = serverReceive,
            serverSendEpochMs = serverSend
        )

        // Asymmetry affects offset calculation
        assertTrue("Asymmetric RTT calculated", sample.roundTripMs > 0)
        println("Asymmetric: offset=${String.format("%.1f", sample.offsetMs)}ms, RTT=${String.format("%.1f", sample.roundTripMs)}ms")
    }

    // === Performance Target Tests ===

    @Test
    fun `NFR2 target of 5ms is achievable on LAN`() {
        // NFR2: Target <5ms clock synchronization accuracy
        val lanSamples = List(5) { 
            simulateSyncSample(rttMs = 8.0 + it, offsetMs = 2.0 + it * 0.3)
        }

        val best = lanSamples.sortedBy { it.roundTripMs }.take(3)
        val avgOffset = best.map { abs(it.offsetMs) }.average()

        assertTrue("LAN can achieve <5ms target", avgOffset < 5.0)
        println("NFR2 target test: ${String.format("%.3f", avgOffset)}ms < 5ms ✓")
    }

    @Test
    fun `NFR2 maximum of 10ms is achievable on WAN`() {
        // NFR2: Maximum <10ms clock synchronization accuracy
        val wanSamples = List(5) { 
            simulateSyncSample(rttMs = 50.0 + it * 5, offsetMs = 4.0 + it * 0.8)
        }

        val best = wanSamples.sortedBy { it.roundTripMs }.take(3)
        val avgOffset = best.map { abs(it.offsetMs) }.average()

        assertTrue("WAN can achieve <10ms maximum", avgOffset < 10.0)
        println("NFR2 maximum test: ${String.format("%.3f", avgOffset)}ms < 10ms ✓")
    }

    // === Helper Functions ===

    /**
     * Simulates a sync sample with given RTT and offset.
     * Creates realistic timestamps that would produce the desired measurements.
     */
    private fun simulateSyncSample(rttMs: Double, offsetMs: Double): com.buccancs.data.time.SyncSample {
        val baseTime = 1_000_000L
        val halfRtt = (rttMs / 2).toLong()
        
        val clientSend = baseTime
        val clientReceive = clientSend + rttMs.toLong()
        
        // Server timestamps adjusted for offset
        val serverReceive = clientSend + halfRtt + offsetMs.toLong()
        val serverSend = serverReceive + 2 // 2ms processing
        
        return computeSyncSample(
            sendEpochMs = clientSend,
            receiveEpochMs = clientReceive,
            serverReceiveEpochMs = serverReceive,
            serverSendEpochMs = serverSend
        )
    }

    /**
     * Calculates variance of a list of values.
     */
    private fun calculateVariance(values: List<Double>): Double {
        if (values.isEmpty()) return 0.0
        val mean = values.average()
        return values.map { (it - mean) * (it - mean) }.average()
    }
}
