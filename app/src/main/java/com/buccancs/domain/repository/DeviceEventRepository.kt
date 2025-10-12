package com.buccancs.domain.repository
import com.buccancs.domain.model.DeviceEvent
import kotlinx.coroutines.flow.StateFlow
interface DeviceEventRepository {
    val events: StateFlow<List<DeviceEvent>>
    suspend fun record(event: DeviceEvent)
}
