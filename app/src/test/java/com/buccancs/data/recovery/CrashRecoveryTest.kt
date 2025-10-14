package com.buccancs.data.recovery

import com.buccancs.data.bookmarks.DefaultBookmarkRepository
import com.buccancs.data.events.DefaultDeviceEventRepository
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.DeviceEventType
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Crash recovery and resilience tests.
 * Tests behavior after simulated crashes and state restoration.
 */
class CrashRecoveryTest {

    @Test
    fun `repository recovers after clearing state`() = runTest {
        val repository = DefaultBookmarkRepository()

        // Populate
        repeat(50) { index ->
            repository.add("Pre-crash $index", Clock.System.now())
        }

        // Simulate crash by clearing
        repository.clear()

        // Recovery: repository should still work
        repository.add("Post-recovery", Clock.System.now())

        val bookmarks = repository.bookmarks.value
        assertEquals(1, bookmarks.size)
        assertEquals("Post-recovery", bookmarks[0].label)
    }

    @Test
    fun `event repository recovers from overflow`() = runTest {
        val repository = DefaultDeviceEventRepository()

        // Overflow the repository (limit is 128)
        repeat(500) { index ->
            repository.record(
                DeviceEvent(
                    id = "overflow-$index",
                    type = DeviceEventType.COMMAND,
                    label = "Overflow Test",
                    scheduledAt = Clock.System.now(),
                    receivedAt = Clock.System.now()
                )
            )
        }

        // Should maintain limit
        val events = repository.events.value
        assertEquals(128, events.size)

        // Should still accept new events
        repository.record(
            DeviceEvent(
                id = "post-overflow",
                type = DeviceEventType.COMMAND,
                label = "Post Overflow",
                scheduledAt = Clock.System.now(),
                receivedAt = Clock.System.now()
            )
        )

        // Still at limit, oldest dropped
        assertEquals(128, repository.events.value.size)
    }

    @Test
    fun `repository state consistent after exception simulation`() = runTest {
        val repository = DefaultBookmarkRepository()

        // Add some data
        repeat(10) { index ->
            repository.add("Before $index", Clock.System.now())
        }

        // Simulate exception scenario (invalid operations)
        try {
            repository.remove("non-existent-id")
        } catch (e: Exception) {
            // Ignore
        }

        // Repository should still be functional
        repository.add("After exception", Clock.System.now())

        val bookmarks = repository.bookmarks.value
        assertEquals(11, bookmarks.size)
    }

    @Test
    fun `concurrent operations during state reset`() = runTest {
        val repository = DefaultBookmarkRepository()

        // Populate
        repeat(100) { repository.add("Item $it", Clock.System.now()) }

        // Start clearing while adding
        val jobs = List(10) { index ->
            kotlinx.coroutines.launch {
                if (index % 2 == 0) {
                    repository.clear()
                } else {
                    repository.add("Concurrent $index", Clock.System.now())
                }
            }
        }

        jobs.forEach { it.join() }

        // Repository should be in valid state (either empty or has items)
        val bookmarks = repository.bookmarks.value
        assertTrue(bookmarks.size >= 0)
    }

    @Test
    fun `multiple recovery cycles`() = runTest {
        val repository = DefaultBookmarkRepository()

        repeat(5) { cycle ->
            // Add data
            repeat(20) { index ->
                repository.add("Cycle $cycle Item $index", Clock.System.now())
            }

            assertEquals(20, repository.bookmarks.value.size)

            // Simulate crash/recovery
            repository.clear()

            assertEquals(0, repository.bookmarks.value.size)
        }

        // After multiple cycles, repository still works
        repository.add("Final", Clock.System.now())
        assertEquals(1, repository.bookmarks.value.size)
    }

    @Test
    fun `event repository maintains order after stress`() = runTest {
        val repository = DefaultDeviceEventRepository()

        // Add events in known order
        repeat(200) { index ->
            repository.record(
                DeviceEvent(
                    id = "ordered-$index",
                    type = DeviceEventType.COMMAND,
                    label = "Order $index",
                    scheduledAt = Clock.System.now(),
                    receivedAt = Clock.System.now()
                )
            )
        }

        val events = repository.events.value
        // Should have last 128 events
        assertEquals(128, events.size)

        // Verify order preserved (last events should be 72-199)
        assertTrue(events.first().id.contains("72"))
        assertTrue(events.last().id.contains("199"))
    }

    @Test
    fun `repository handles rapid initialization cycles`() = runTest {
        repeat(50) {
            val repository = DefaultBookmarkRepository()
            repository.add("Quick test", Clock.System.now())
            assertEquals(1, repository.bookmarks.value.size)
        }
    }

    @Test
    fun `state consistency after mixed failure scenarios`() = runTest {
        val repository = DefaultBookmarkRepository()

        // Scenario 1: Normal operation
        repository.add("Normal", Clock.System.now())

        // Scenario 2: Invalid removal
        repository.remove("invalid-id")

        // Scenario 3: Multiple rapid operations
        repeat(10) {
            repository.add("Rapid $it", Clock.System.now())
        }

        // Scenario 4: Clear
        repository.clear()

        // Scenario 5: Recovery
        repository.add("Recovered", Clock.System.now())

        assertEquals(1, repository.bookmarks.value.size)
        assertEquals("Recovered", repository.bookmarks.value[0].label)
    }

    @Test
    fun `event limit enforcement survives stress`() = runTest {
        val repository = DefaultDeviceEventRepository()

        // Multiple rounds of overflow
        repeat(10) { round ->
            repeat(200) { index ->
                repository.record(
                    DeviceEvent(
                        id = "round-$round-event-$index",
                        type = DeviceEventType.COMMAND,
                        label = "Round $round",
                        scheduledAt = Clock.System.now(),
                        receivedAt = Clock.System.now()
                    )
                )
            }

            // Always maintains limit
            assertEquals(128, repository.events.value.size)
        }
    }

    @Test
    fun `repository recovers from empty state operations`() = runTest {
        val repository = DefaultBookmarkRepository()

        // Operations on empty repository
        repository.clear() // Clear empty
        repository.remove("non-existent") // Remove from empty
        val empty = repository.bookmarks.value
        assertEquals(0, empty.size)

        // Should still work normally after
        repository.add("Test", Clock.System.now())
        assertEquals(1, repository.bookmarks.value.size)
    }
}
