package com.buccancs.data.events

import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.DeviceEventType
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class DefaultDeviceEventRepositoryTest {
    private lateinit var repository: DefaultDeviceEventRepository

    @Before
    fun setup() {
        repository = DefaultDeviceEventRepository()
    }

    @Test
    fun `record single event`() = runTest {
        val event = createTestEvent("event-1", "Test Event")
        
        repository.record(event)
        
        val events = repository.events.value
        assertEquals(1, events.size)
        assertEquals("event-1", events[0].id)
        assertEquals("Test Event", events[0].label)
    }

    @Test
    fun `record multiple events`() = runTest {
        repeat(5) { index ->
            repository.record(createTestEvent("event-$index", "Event $index"))
        }
        
        val events = repository.events.value
        assertEquals(5, events.size)
    }

    @Test
    fun `events limited to maximum size`() = runTest {
        repeat(150) { index ->
            repository.record(createTestEvent("event-$index", "Event $index"))
        }
        
        val events = repository.events.value
        assertEquals(128, events.size)
        assertEquals("event-22", events[0].id)
        assertEquals("event-149", events[127].id)
    }

    @Test
    fun `concurrent event recording is thread-safe`() = runTest {
        val jobs = List(10) { index ->
            launch {
                repeat(10) { subIndex ->
                    repository.record(
                        createTestEvent("event-$index-$subIndex", "Event $index-$subIndex")
                    )
                }
            }
        }
        
        jobs.forEach { it.join() }
        
        val events = repository.events.value
        assertEquals(100, events.size)
    }

    @Test
    fun `events maintain insertion order`() = runTest {
        repository.record(createTestEvent("1", "First"))
        repository.record(createTestEvent("2", "Second"))
        repository.record(createTestEvent("3", "Third"))
        
        val events = repository.events.value
        assertEquals("1", events[0].id)
        assertEquals("2", events[1].id)
        assertEquals("3", events[2].id)
    }

    @Test
    fun `different event types are recorded`() = runTest {
        repository.record(createTestEvent("sync", "Sync", DeviceEventType.SYNC_SIGNAL))
        repository.record(createTestEvent("marker", "Marker", DeviceEventType.EVENT_MARKER))
        repository.record(createTestEvent("command", "Command", DeviceEventType.COMMAND))
        
        val events = repository.events.value
        assertEquals(3, events.size)
        assertEquals(DeviceEventType.SYNC_SIGNAL, events[0].type)
        assertEquals(DeviceEventType.EVENT_MARKER, events[1].type)
        assertEquals(DeviceEventType.COMMAND, events[2].type)
    }

    private fun createTestEvent(
        id: String,
        label: String,
        type: DeviceEventType = DeviceEventType.COMMAND
    ) = DeviceEvent(
        id = id,
        type = type,
        label = label,
        scheduledAt = Clock.System.now(),
        receivedAt = Clock.System.now()
    )
}
