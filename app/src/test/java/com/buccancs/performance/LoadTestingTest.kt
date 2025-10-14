package com.buccancs.performance

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
import kotlin.system.measureTimeMillis
import kotlin.test.assertTrue

/**
 * Load testing tests.
 * Tests system behavior under heavy load with many operations.
 */
class LoadTestingTest {

    @Test
    fun `bookmark repository handles 1000 concurrent additions`() = runTest {
        val repository = DefaultBookmarkRepository()

        val executionTime = measureTimeMillis {
            val jobs = List(1000) { index ->
                launch {
                    repository.add("Load Test Bookmark $index", Clock.System.now())
                }
            }
            jobs.forEach { it.join() }
        }

        val bookmarks = repository.bookmarks.value
        assertTrue(bookmarks.size == 1000, "Expected 1000 bookmarks, got ${bookmarks.size}")
        assertTrue(executionTime < 5000, "Operation took ${executionTime}ms, expected < 5000ms")
    }

    @Test
    fun `event repository handles 5000 rapid events`() = runTest {
        val repository = DefaultDeviceEventRepository()

        val executionTime = measureTimeMillis {
            repeat(5000) { index ->
                repository.record(
                    DeviceEvent(
                        id = "load-event-$index",
                        type = DeviceEventType.COMMAND,
                        label = "Load Test Event",
                        scheduledAt = Clock.System.now(),
                        receivedAt = Clock.System.now()
                    )
                )
            }
        }

        val events = repository.events.value
        // Repository limits to 128 events
        assertTrue(events.size == 128, "Expected 128 events (limit), got ${events.size}")
        assertTrue(executionTime < 3000, "Operation took ${executionTime}ms, expected < 3000ms")
    }

    @Test
    fun `bookmark repository handles mixed operations under load`() = runTest {
        val repository = DefaultBookmarkRepository()

        // Pre-populate
        repeat(100) { repository.add("Initial $it", Clock.System.now()) }

        val executionTime = measureTimeMillis {
            val jobs = List(500) { index ->
                launch {
                    when (index % 4) {
                        0 -> repository.add("Add $index", Clock.System.now())
                        1 -> {
                            val bookmarks = repository.bookmarks.value
                            if (bookmarks.isNotEmpty()) {
                                repository.remove(bookmarks.first().id)
                            }
                        }
                        2 -> repository.bookmarks.value // Read operation
                        3 -> repository.clear()
                    }
                }
            }
            jobs.forEach { it.join() }
        }

        // Should complete without errors
        assertTrue(executionTime < 10000, "Operation took ${executionTime}ms, expected < 10000ms")
    }

    @Test
    fun `repository survives sustained load`() = runTest {
        val bookmarkRepo = DefaultBookmarkRepository()
        val eventRepo = DefaultDeviceEventRepository()

        val executionTime = measureTimeMillis {
            repeat(10) { round ->
                val jobs = List(100) { index ->
                    launch {
                        bookmarkRepo.add("Round $round Item $index", Clock.System.now())
                        eventRepo.record(
                            DeviceEvent(
                                id = "round-$round-event-$index",
                                type = DeviceEventType.COMMAND,
                                label = "Sustained Load",
                                scheduledAt = Clock.System.now(),
                                receivedAt = Clock.System.now()
                            )
                        )
                    }
                }
                jobs.forEach { it.join() }
            }
        }

        assertTrue(executionTime < 15000, "Sustained load took ${executionTime}ms, expected < 15000ms")
    }

    @Test
    fun `parallel async operations scale linearly`() = runTest {
        val repository = DefaultBookmarkRepository()

        // Warm up
        repeat(10) { repository.add("Warmup $it", Clock.System.now()) }
        repository.clear()

        val time10 = measureTimeMillis {
            val jobs = List(10) { async { repository.add("Test $it", Clock.System.now()) } }
            jobs.awaitAll()
        }

        val time100 = measureTimeMillis {
            val jobs = List(100) { async { repository.add("Test $it", Clock.System.now()) } }
            jobs.awaitAll()
        }

        // 10x more operations should take < 20x time (allowing overhead)
        assertTrue(time100 < time10 * 20, "Scaling not linear: 10=$time10ms, 100=$time100ms")
    }

    @Test
    fun `memory efficiency with large datasets`() = runTest {
        val repository = DefaultBookmarkRepository()

        // Add many bookmarks
        repeat(10000) { index ->
            repository.add("Memory Test $index", Clock.System.now())
        }

        val bookmarks = repository.bookmarks.value
        assertTrue(bookmarks.size == 10000, "Expected 10000 bookmarks")
        
        // Verify all bookmarks are accessible
        val firstId = bookmarks.first().id
        val lastId = bookmarks.last().id
        assertTrue(firstId != lastId, "IDs should be unique")
    }

    @Test
    fun `rapid state transitions performance`() = runTest {
        val repository = DefaultBookmarkRepository()

        val executionTime = measureTimeMillis {
            repeat(10000) { index ->
                if (index % 2 == 0) {
                    repository.add("State $index", Clock.System.now())
                } else {
                    repository.clear()
                }
            }
        }

        assertTrue(executionTime < 5000, "Rapid transitions took ${executionTime}ms, expected < 5000ms")
    }

    @Test
    fun `concurrent read performance under write load`() = runTest {
        val repository = DefaultBookmarkRepository()

        val writers = List(50) {
            launch {
                repeat(100) { index ->
                    repository.add("Writer $it Item $index", Clock.System.now())
                }
            }
        }

        val readers = List(50) {
            launch {
                repeat(1000) {
                    repository.bookmarks.value // Just read
                }
            }
        }

        val executionTime = measureTimeMillis {
            (writers + readers).forEach { it.join() }
        }

        assertTrue(executionTime < 10000, "Concurrent R/W took ${executionTime}ms, expected < 10000ms")
    }

    @Test
    fun `throughput measurement for event recording`() = runTest {
        val repository = DefaultDeviceEventRepository()

        val startTime = System.currentTimeMillis()
        repeat(1000) { index ->
            repository.record(
                DeviceEvent(
                    id = "throughput-$index",
                    type = DeviceEventType.COMMAND,
                    label = "Throughput Test",
                    scheduledAt = Clock.System.now(),
                    receivedAt = Clock.System.now()
                )
            )
        }
        val duration = System.currentTimeMillis() - startTime

        val throughput = 1000.0 / (duration / 1000.0)
        assertTrue(throughput > 100, "Throughput was $throughput events/sec, expected > 100/sec")
    }

    @Test
    fun `stress test with maximum concurrent operations`() = runTest {
        val bookmarkRepo = DefaultBookmarkRepository()
        val eventRepo = DefaultDeviceEventRepository()

        val executionTime = measureTimeMillis {
            val jobs = List(1000) { index ->
                launch {
                    when (index % 5) {
                        0 -> bookmarkRepo.add("Stress $index", Clock.System.now())
                        1 -> bookmarkRepo.bookmarks.value
                        2 -> eventRepo.record(
                            DeviceEvent(
                                id = "stress-$index",
                                type = DeviceEventType.COMMAND,
                                label = "Stress",
                                scheduledAt = Clock.System.now(),
                                receivedAt = Clock.System.now()
                            )
                        )
                        3 -> eventRepo.events.value
                        4 -> {
                            val bookmarks = bookmarkRepo.bookmarks.value
                            if (bookmarks.isNotEmpty()) {
                                bookmarkRepo.remove(bookmarks.first().id)
                            }
                        }
                    }
                }
            }
            jobs.forEach { it.join() }
        }

        assertTrue(executionTime < 15000, "Stress test took ${executionTime}ms, expected < 15000ms")
    }
}
