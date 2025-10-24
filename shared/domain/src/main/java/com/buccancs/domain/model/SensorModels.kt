@file:OptIn(kotlin.time.ExperimentalTime::class)

package com.buccancs.domain.model

import kotlin.time.Instant

@JvmInline
value class DeviceId(
    val value: String
)

enum class SensorDeviceType {
    SHIMMER_GSR,
    ANDROID_RGB_CAMERA,
    TOPDON_TC001,
    ANDROID_THERMAL_CAMERA,
    AUDIO_MICROPHONE,
    UNKNOWN
}

enum class SensorStreamType {
    GSR,
    RGB_VIDEO,
    RAW_DNG,
    THERMAL_VIDEO,
    AUDIO,
    PREVIEW
}

sealed interface ConnectionStatus {
    data object Disconnected :
        ConnectionStatus

    data object Connecting :
        ConnectionStatus

    data class Connected(
        val since: Instant,
        val batteryPercent: Int? = null,
        val rssiDbm: Int? = null
    ) : ConnectionStatus
}

data class SensorDevice(
    val id: DeviceId,
    val displayName: String,
    val type: SensorDeviceType,
    val capabilities: Set<SensorStreamType>,
    val connectionStatus: ConnectionStatus,
    val isSimulated: Boolean,
    val attributes: Map<String, String> = emptyMap()
)

data class SensorStreamStatus(
    val deviceId: DeviceId,
    val streamType: SensorStreamType,
    val sampleRateHz: Double?,
    val frameRateFps: Double?,
    val lastSampleTimestamp: Instant?,
    val bufferedDurationSeconds: Double?,
    val isStreaming: Boolean,
    val isSimulated: Boolean
)

enum class RecordingLifecycleState {
    Idle,
    Starting,
    Recording,
    Stopping
}

data class RecordingSessionAnchor(
    val sessionId: String,
    val referenceTimestamp: Instant,
    val sharedClockOffsetMillis: Long
)

data class RecordingState(
    val lifecycle: RecordingLifecycleState,
    val anchor: RecordingSessionAnchor?,
    val updatedAt: Instant
)

enum class TimeSyncQuality {
    UNKNOWN,
    GOOD,
    FAIR,
    POOR
}

data class TimeSyncObservation(
    val timestamp: Instant,
    val offsetMillis: Double,
    val roundTripMillis: Double
)

data class TimeSyncStatus(
    val offsetMillis: Long,
    val roundTripMillis: Long,
    val lastSync: Instant,
    val driftEstimateMillisPerMinute: Double,
    val filteredRoundTripMillis: Double,
    val quality: TimeSyncQuality,
    val sampleCount: Int,
    val regressionSlopeMillisPerMinute: Double
)
