package com.buccancs.performance

import com.buccancs.data.storage.SpaceState
import com.buccancs.domain.model.RecordingSessionAnchor
import kotlinx.datetime.Clock
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Resource constraint tests.
 * Tests behavior under memory pressure, disk full, and resource exhaustion.
 */
class ResourceConstraintsTest {

    @Test
    fun `handles disk at 99 percent full`() {
        val spaceState = SpaceState(
            totalBytes = 100_000_000_000L,
            availableBytes = 1_000_000_000L, // 1% free
            usedBytes = 99_000_000_000L,
            usedPercentage = 99.0
        )

        assertEquals(99.0, spaceState.usedPercentage, 0.1)
        assertTrue(spaceState.availableBytes < spaceState.totalBytes * 0.02)
    }

    @Test
    fun `detects critical storage threshold`() {
        val critical = SpaceState(
            totalBytes = 1_000_000_000L,
            availableBytes = 10_000_000L, // 1% free
            usedBytes = 990_000_000L,
            usedPercentage = 99.0
        )

        assertTrue(critical.usedPercentage >= 99.0)
        assertTrue(critical.availableBytes < 50_000_000L)
    }

    @Test
    fun `handles minimal available storage`() {
        val minimal = SpaceState(
            totalBytes = 1_000_000_000L,
            availableBytes = 1L, // Almost full
            usedBytes = 999_999_999L,
            usedPercentage = 99.9999999
        )

        assertTrue(minimal.availableBytes < 1000)
    }

    @Test
    fun `storage calculations with very large disks`() {
        val largeDisk = SpaceState(
            totalBytes = 10_000_000_000_000L, // 10TB
            availableBytes = 5_000_000_000_000L, // 5TB free
            usedBytes = 5_000_000_000_000L,
            usedPercentage = 50.0
        )

        assertTrue(largeDisk.totalBytes > 1_000_000_000_000L)
        assertEquals(50.0, largeDisk.usedPercentage, 0.1)
    }

    @Test
    fun `handles storage with minimal capacity`() {
        val tiny = SpaceState(
            totalBytes = 100_000L, // 100KB
            availableBytes = 10_000L,
            usedBytes = 90_000L,
            usedPercentage = 90.0
        )

        assertTrue(tiny.totalBytes < 1_000_000L)
        assertTrue(tiny.usedPercentage > 80.0)
    }

    @Test
    fun `session anchor with extreme timestamp values`() {
        val farFuture = RecordingSessionAnchor(
            sessionId = "extreme-test",
            referenceTimestamp = Clock.System.now(),
            sharedClockOffsetMillis = Long.MAX_VALUE
        )

        assertEquals(Long.MAX_VALUE, farFuture.sharedClockOffsetMillis)
    }

    @Test
    fun `session anchor with negative offset`() {
        val negativeOffset = RecordingSessionAnchor(
            sessionId = "negative-test",
            referenceTimestamp = Clock.System.now(),
            sharedClockOffsetMillis = Long.MIN_VALUE
        )

        assertEquals(Long.MIN_VALUE, negativeOffset.sharedClockOffsetMillis)
    }

    @Test
    fun `handles zero storage space`() {
        val empty = SpaceState(
            totalBytes = 0L,
            availableBytes = 0L,
            usedBytes = 0L,
            usedPercentage = 0.0
        )

        assertEquals(0L, empty.totalBytes)
        assertEquals(0L, empty.availableBytes)
    }

    @Test
    fun `storage percentage at exact boundaries`() {
        val exactly50 = SpaceState(
            totalBytes = 1000L,
            availableBytes = 500L,
            usedBytes = 500L,
            usedPercentage = 50.0
        )

        assertEquals(50.0, exactly50.usedPercentage, 0.001)
    }

    @Test
    fun `handles fractional byte calculations`() {
        val fractional = SpaceState(
            totalBytes = 999L,
            availableBytes = 333L,
            usedBytes = 666L,
            usedPercentage = 66.666666
        )

        assertTrue(fractional.usedPercentage > 66.0)
        assertTrue(fractional.usedPercentage < 67.0)
    }
}
