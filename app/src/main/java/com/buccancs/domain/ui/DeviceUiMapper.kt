package com.buccancs.domain.ui

import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.ShimmerSettings
import com.buccancs.ui.DeviceUiModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Maps domain models to UI models for device display.
 * Extracted from MainViewModel to separate transformation concerns.
 */
@Singleton
class DeviceUiMapper @Inject constructor(
    private val streamUiMapper: StreamUiMapper
) {
    fun toUiModel(
        device: SensorDevice,
        streams: List<SensorStreamStatus>,
        shimmerSettings: ShimmerSettings
    ): DeviceUiModel {
        val connected = device.connectionStatus is ConnectionStatus.Connected
        val connectionLabel = device.connectionStatus.uiLabel()
        val batteryPercent =
            (device.connectionStatus as? ConnectionStatus.Connected)?.batteryPercent
        val activeStreams = streams.filter { it.deviceId == device.id }
            .map { streamUiMapper.toUiModel(it) }
        val capabilityLabels = device.capabilities
            .map { stream -> sensorStreamLabel(stream) }
            .sorted()

        return DeviceUiModel(
            id = device.id,
            title = device.displayName,
            typeLabel = device.type.uiLabel(),
            connectionStatus = connectionLabel,
            batteryPercent = batteryPercent,
            isConnected = connected,
            isSimulated = device.isSimulated,
            supportsTopdon = device.type == SensorDeviceType.TOPDON_TC001,
            capabilityLabels = capabilityLabels,
            streams = activeStreams,
            rgbCamera = null, // Handled separately
            shimmer = null // Handled separately
        )
    }

    fun SensorDeviceType.uiLabel(): String = when (this) {
        SensorDeviceType.SHIMMER_GSR -> "Shimmer3 GSR"
        SensorDeviceType.ANDROID_RGB_CAMERA -> "Android RGB Camera"
        SensorDeviceType.TOPDON_TC001 -> "Topdon TC001"
        SensorDeviceType.ANDROID_THERMAL_CAMERA -> "Android Thermal Camera"
        SensorDeviceType.AUDIO_MICROPHONE -> "Microphone"
        SensorDeviceType.UNKNOWN -> "Unknown Device"
    }

    fun ConnectionStatus.uiLabel(): String = when (this) {
        ConnectionStatus.Disconnected -> "Disconnected"
        ConnectionStatus.Connecting -> "Connecting"
        is ConnectionStatus.Connected -> "Connected"
    }

    private fun sensorStreamLabel(type: SensorStreamType): String = when (type) {
        SensorStreamType.GSR -> "GSR"
        SensorStreamType.RGB_VIDEO -> "RGB Video"
        SensorStreamType.RAW_DNG -> "RAW (DNG)"
        SensorStreamType.THERMAL_VIDEO -> "Thermal Video"
        SensorStreamType.AUDIO -> "Audio"
        SensorStreamType.PREVIEW -> "Preview"
    }
}
