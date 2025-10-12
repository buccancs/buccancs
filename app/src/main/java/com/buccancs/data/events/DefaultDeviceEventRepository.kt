package com.buccancs.data.events

import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.repository.DeviceEventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultDeviceEventRepository @Inject constructor() : DeviceEventRepository {
    private val mutex = Mutex()
    private val _events = MutableStateFlow<List<DeviceEvent>>(emptyList())
    override val events: StateFlow<List<DeviceEvent>> = _events.asStateFlow()
    override suspend fun record(event: DeviceEvent) {
        mutex.withLock {
            val updated = (_events.value + event).takeLast(MAX_EVENTS)
            _events.value = updated
        }
    }

    private companion object {
        private const val MAX_EVENTS = 128
    }
}
