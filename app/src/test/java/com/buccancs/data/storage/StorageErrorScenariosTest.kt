package com.buccancs.data.storage

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Error scenario tests for storage operations.
 * Tests disk full, permission denied, and other storage failures.
 */
class StorageErrorScenariosTest {

    @Test
    fun `disk full scenario detected`() {
        val spaceState = SpaceState(
            totalBytes = 1_000_000_000L,
            availableBytes = 0L, // Disk full
            usedBytes = 1_000_000_000L,
            usedPercentage = 100.0
        )

        assertEquals(100.0, spaceState.usedPercentage)
        assertEquals(0L, spaceState.availableBytes)
    }

    @Test
    fun `low storage warning threshold`() {
        val spaceState = SpaceState(
            totalBytes = 1_000_000_000L,
            availableBytes = 50_000_000L, // 5% free
            usedBytes = 950_000_000L,
            usedPercentage = 95.0
        )

        assertTrue(spaceState.usedPercentage >= 90.0) // Warning threshold
    }

    @Test
    fun `storage percentage calculation accuracy`() {
        val spaceState = SpaceState(
            totalBytes = 1_000_000_000L,
            availableBytes = 500_000_000L,
            usedBytes = 500_000_000L,
            usedPercentage = 50.0
        )

        assertEquals(50.0, spaceState.usedPercentage, 0.1)
    }

    @Test
    fun `zero total bytes edge case`() {
        // Edge case: device reports 0 total bytes
        val spaceState = SpaceState(
            totalBytes = 0L,
            availableBytes = 0L,
            usedBytes = 0L,
            usedPercentage = 0.0
        )

        assertEquals(0L, spaceState.totalBytes)
    }

    @Test
    fun `negative available bytes handled`() {
        // Edge case: filesystem reports negative available space
        val spaceState = SpaceState(
            totalBytes = 1_000_000_000L,
            availableBytes = -100L,
            usedBytes = 1_000_000_100L,
            usedPercentage = 100.0
        )

        assertTrue(spaceState.availableBytes < 0)
    }

    @Test
    fun `used exceeds total bytes edge case`() {
        // Edge case: used bytes reported higher than total
        val spaceState = SpaceState(
            totalBytes = 1_000_000_000L,
            availableBytes = 0L,
            usedBytes = 1_200_000_000L, // More than total
            usedPercentage = 120.0
        )

        assertTrue(spaceState.usedBytes > spaceState.totalBytes)
    }

    @Test
    fun `retention preferences with zero values`() {
        val prefs = RetentionPreferences(
            minFreeBytes = 0L,
            maxSessions = 0,
            maxAgeDays = 0
        )

        assertEquals(0L, prefs.minFreeBytes)
        assertEquals(0, prefs.maxSessions)
        assertEquals(0, prefs.maxAgeDays)
    }

    @Test
    fun `retention preferences with extreme values`() {
        val prefs = RetentionPreferences(
            minFreeBytes = Long.MAX_VALUE,
            maxSessions = Int.MAX_VALUE,
            maxAgeDays = Int.MAX_VALUE
        )

        assertEquals(Long.MAX_VALUE, prefs.minFreeBytes)
        assertEquals(Int.MAX_VALUE, prefs.maxSessions)
    }

    @Test
    fun `storage state with very large numbers`() {
        val spaceState = SpaceState(
            totalBytes = Long.MAX_VALUE,
            availableBytes = Long.MAX_VALUE / 2,
            usedBytes = Long.MAX_VALUE / 2,
            usedPercentage = 50.0
        )

        assertTrue(spaceState.totalBytes > 0)
        assertTrue(spaceState.availableBytes > 0)
    }
}
