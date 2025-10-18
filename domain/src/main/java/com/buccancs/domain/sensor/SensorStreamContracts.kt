package com.buccancs.domain.sensor

import com.buccancs.domain.model.DeviceId

interface SensorStreamEmitter {
    suspend fun emit(
        timestampEpochMs: Long,
        values: Map<String, Double>
    )

    suspend fun close()
}

interface SensorStreamClient {
    suspend fun openStream(
        sessionId: String,
        deviceId: DeviceId,
        streamId: String,
        sampleRateHz: Double
    ): SensorStreamEmitter
}
