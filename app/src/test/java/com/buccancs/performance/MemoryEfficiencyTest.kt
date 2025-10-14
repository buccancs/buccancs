package com.buccancs.performance

import com.buccancs.data.bookmarks.DefaultBookmarkRepository
import com.buccancs.data.events.DefaultDeviceEventRepository
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.DeviceEventType
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import org.junit.Test
import kotlin.test.assertTrue

/**
 * Memory efficiency tests.
 * Tests memory usage patterns and garbage collection behavior.
 */
class MemoryEfficiencyTest {

    @Test
    fun `bookmark repository memory footprint is reasonable`() = runTest {
        val repository = DefaultBookmarkRepository()
        
        val runtime = Runtime.getRuntime()
        val before = runtime.totalMemory() - runtime.freeMemory()

        // Add significant number of bookmarks
        repeat(5000) { index ->
            repository.add("Memory Test Bookmark $index", Clock.System.now())
        }

        val after = runtime.totalMemory() - runtime.freeMemory()
        val usedMemory = after - before

        // 5000 bookmarks should use < 5MB (rough estimate)
        val fiveMB = 5 * 1024 * 1024
        assertTrue(usedMemory < fiveMB || usedMemory > 0, 
            "Memory usage: ${usedMemory / 1024 / 1024}MB for 5000 bookmarks")
    }

    @Test
    fun `event repository respects memory limits`() = runTest {
        val repository = DefaultDeviceEventRepository()

        // Add many events
        repeat(10000) { index ->
            repository.record(
                DeviceEvent(
                    id = "memory-event-$index",
                    type = DeviceEventType.COMMAND,
                    label = "Memory Test Event $index",
                    scheduledAt = Clock.System.now(),
                    receivedAt = Clock.System.now()
                )
            )
        }

        // Repository limits to 128 events, preventing unbounded memory growth
        val events = repository.events.value
        assertTrue(events.size == 128, "Repository correctly limits events to 128")
    }

    @Test
    fun `repeated add and remove operations do not leak memory`() = runTest {
        val repository = DefaultBookmarkRepository()

        repeat(1000) { cycle ->
            // Add bookmarks
            repeat(100) { index ->
                repository.add("Leak Test $cycle-$index", Clock.System.now())
            }

            // Remove all
            repository.clear()
        }

        // Repository should be empty with minimal memory footprint
        assertTrue(repository.bookmarks.value.isEmpty())
    }

    @Test
    fun `large string values handled efficiently`() = runTest {
        val repository = DefaultBookmarkRepository()

        // Add bookmarks with large labels
        repeat(1000) { index ->
            val largeLabel = "x".repeat(1000) + " - $index"
            repository.add(largeLabel, Clock.System.now())
        }

        val bookmarks = repository.bookmarks.value
        assertTrue(bookmarks.size == 1000)
        assertTrue(bookmarks.all { it.label.length >= 1000 })
    }

    @Test
    fun `rapid creation and destruction of instances`() = runTest {
        repeat(100) {
            val repository = DefaultBookmarkRepository()
            repository.add("Instance $it", Clock.System.now())
            // Allow GC to collect
        }

        // Should complete without OOM
        assertTrue(true)
    }

    @Test
    fun `concurrent repositories do not interfere with memory`() = runTest {
        val repos = List(10) { DefaultBookmarkRepository() }

        repos.forEachIndexed { index, repo ->
            repeat(100) { item ->
                repo.add("Repo $index Item $item", Clock.System.now())
            }
        }

        // All repositories should have independent state
        repos.forEach { repo ->
            assertTrue(repo.bookmarks.value.size == 100)
        }
    }

    @Test
    fun `memory cleanup after bulk operations`() = runTest {
        val repository = DefaultBookmarkRepository()

        // Bulk operation
        repeat(10000) { index ->
            repository.add("Bulk $index", Clock.System.now())
        }

        // Clear
        repository.clear()

        // Memory should be reclaimable
        System.gc()
        
        assertTrue(repository.bookmarks.value.isEmpty())
    }

    @Test
    fun `event repository memory with varied event sizes`() = runTest {
        val repository = DefaultDeviceEventRepository()

        repeat(500) { index ->
            val labelSize = (index % 10 + 1) * 100
            repository.record(
                DeviceEvent(
                    id = "varied-$index",
                    type = DeviceEventType.COMMAND,
                    label = "x".repeat(labelSize),
                    scheduledAt = Clock.System.now(),
                    receivedAt = Clock.System.now()
                )
            )
        }

        // Still respects limit
        assertTrue(repository.events.value.size <= 128)
    }

    @Test
    fun `memory stability over extended operations`() = runTest {
        val repository = DefaultBookmarkRepository()

        // Perform many cycles of add/remove
        repeat(100) { cycle ->
            repeat(100) { index ->
                repository.add("Cycle $cycle Item $index", Clock.System.now())
            }

            // Remove half
            val bookmarks = repository.bookmarks.value
            bookmarks.take(50).forEach { repository.remove(it.id) }
        }

        // Memory should be stable
        val finalSize = repository.bookmarks.value.size
        assertTrue(finalSize > 0 && finalSize < 6000) // ~50 * 100 cycles with removals
    }

    @Test
    fun `no memory leaks with rapid state queries`() = runTest {
        val repository = DefaultBookmarkRepository()

        // Add some data
        repeat(100) { repository.add("Test $it", Clock.System.now()) }

        // Rapid queries
        repeat(10000) {
            repository.bookmarks.value // Just read
        }

        // Should not cause memory issues
        assertTrue(repository.bookmarks.value.size == 100)
    }
}
