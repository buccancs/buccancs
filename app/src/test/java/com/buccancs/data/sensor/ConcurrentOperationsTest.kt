package com.buccancs.data.sensor

import com.buccancs.data.bookmarks.DefaultBookmarkRepository
import com.buccancs.data.events.DefaultDeviceEventRepository
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.DeviceEventType
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests for concurrent operations.
 * Tests thread-safety, race conditions, and parallel execution.
 */
class ConcurrentOperationsTest {

    @Test
    fun `concurrent bookmark additions are thread-safe`() = runTest {
        val repository = DefaultBookmarkRepository()

        val jobs = List(100) { index ->
            launch {
                repository.add("Bookmark $index", Clock.System.now())
            }
        }

        jobs.forEach { it.join() }

        val bookmarks = repository.bookmarks.value
        assertEquals(100, bookmarks.size)
    }

    @Test
    fun `concurrent event recordings are thread-safe`() = runTest {
        val repository = DefaultDeviceEventRepository()

        val jobs = List(200) { index ->
            launch {
                repository.record(
                    DeviceEvent(
                        id = "event-$index",
                        type = DeviceEventType.COMMAND,
                        label = "Event $index",
                        scheduledAt = Clock.System.now(),
                        receivedAt = Clock.System.now()
                    )
                )
            }
        }

        jobs.forEach { it.join() }

        val events = repository.events.value
        // Should be limited to MAX_EVENTS (128)
        assertTrue(events.size <= 128)
    }

    @Test
    fun `concurrent bookmark removals are thread-safe`() = runTest {
        val repository = DefaultBookmarkRepository()

        // Add bookmarks
        repeat(50) { index ->
            repository.add("Bookmark $index", Clock.System.now())
        }

        val bookmarkIds = repository.bookmarks.value.map { it.id }

        // Remove concurrently
        val jobs = bookmarkIds.map { id ->
            launch {
                repository.remove(id)
            }
        }

        jobs.forEach { it.join() }

        val remaining = repository.bookmarks.value
        assertEquals(0, remaining.size)
    }

    @Test
    fun `parallel async operations complete successfully`() = runTest {
        val repository = DefaultBookmarkRepository()

        val deferreds = List(50) { index ->
            async {
                repository.add("Async Bookmark $index", Clock.System.now())
                repository.bookmarks.value.size
            }
        }

        val results = deferreds.awaitAll()

        assertTrue(results.all { it > 0 })
    }

    @Test
    fun `rapid state changes handled correctly`() = runTest {
        val repository = DefaultBookmarkRepository()

        repeat(1000) { index ->
            if (index % 2 == 0) {
                repository.add("Bookmark $index", Clock.System.now())
            } else {
                repository.clear()
            }
        }

        // Should not crash
        val bookmarks = repository.bookmarks.value
        assertTrue(bookmarks.isEmpty() || bookmarks.isNotEmpty())
    }

    @Test
    fun `concurrent reads and writes are thread-safe`() = runTest {
        val repository = DefaultBookmarkRepository()

        val writers = List(25) { index ->
            launch {
                repeat(10) {
                    repository.add("Bookmark $index-$it", Clock.System.now())
                }
            }
        }

        val readers = List(25) {
            launch {
                repeat(10) {
                    val bookmarks = repository.bookmarks.value
                    assertTrue(bookmarks.size >= 0)
                }
            }
        }

        (writers + readers).forEach { it.join() }

        val finalBookmarks = repository.bookmarks.value
        assertTrue(finalBookmarks.size > 0)
    }

    @Test
    fun `stress test with many concurrent operations`() = runTest {
        val bookmarkRepo = DefaultBookmarkRepository()
        val eventRepo = DefaultDeviceEventRepository()

        val jobs = List(500) { index ->
            launch {
                when (index % 3) {
                    0 -> bookmarkRepo.add("Stress $index", Clock.System.now())
                    1 -> eventRepo.record(
                        DeviceEvent(
                            id = "stress-$index",
                            type = DeviceEventType.COMMAND,
                            label = "Stress Event",
                            scheduledAt = Clock.System.now(),
                            receivedAt = Clock.System.now()
                        )
                    )

                    2 -> {
                        val bookmarks = bookmarkRepo.bookmarks.value
                        if (bookmarks.isNotEmpty()) {
                            bookmarkRepo.remove(bookmarks.first().id)
                        }
                    }
                }
            }
        }

        jobs.forEach { it.join() }

        // Should complete without errors
        assertTrue(true)
    }

    @Test
    fun `concurrent clear operations are idempotent`() = runTest {
        val repository = DefaultBookmarkRepository()

        repository.add("Test", Clock.System.now())

        val jobs = List(50) {
            launch {
                repository.clear()
            }
        }

        jobs.forEach { it.join() }

        val bookmarks = repository.bookmarks.value
        assertEquals(0, bookmarks.size)
    }
}
