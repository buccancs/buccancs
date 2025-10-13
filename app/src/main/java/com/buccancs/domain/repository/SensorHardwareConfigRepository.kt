package com.buccancs.domain.repository

import com.buccancs.domain.model.SensorHardwareConfig
import com.buccancs.domain.model.ShimmerDeviceConfig
import com.buccancs.domain.model.TopdonDeviceConfig
import kotlinx.coroutines.flow.StateFlow

interface SensorHardwareConfigRepository {
    val config: StateFlow<SensorHardwareConfig>
    suspend fun reload()
    suspend fun upsertShimmerDevice(device: ShimmerDeviceConfig)
    suspend fun upsertTopdonDevice(device: TopdonDeviceConfig)
    suspend fun updateConfig(transform: (SensorHardwareConfig) -> SensorHardwareConfig)
}
