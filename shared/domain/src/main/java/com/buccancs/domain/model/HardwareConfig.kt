package com.buccancs.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SensorHardwareConfig(
    val shimmer: List<ShimmerDeviceConfig> = emptyList(),
    val topdon: List<TopdonDeviceConfig> = emptyList(),
    val rgb: List<RgbCameraConfig> = emptyList()
)

@Serializable
data class ShimmerDeviceConfig(
    val id: String,
    val displayName: String = "Shimmer GSR",
    val macAddress: String? = null,
    val gsrRangeIndex: Int? = null,
    val sampleRateHz: Double? = null
)

@Serializable
data class TopdonDeviceConfig(
    val id: String,
    val displayName: String = "Topdon TC001",
    val vendorId: Int? = null,
    val productId: Int? = null,
    val serialNumber: String? = null,
    val autoConnectOnAttach: Boolean? = null,
    val palette: TopdonPalette? = null,
    val superSampling: TopdonSuperSamplingFactor? = null,
    val previewFpsLimit: Int? = null,
    val emissivity: Double? = null,
    val gainMode: TopdonGainMode? = null,
    val autoShutterEnabled: Boolean? = null,
    val dynamicRange: TopdonDynamicRange? = null,
    val distanceMeters: Double? = null,
    val temperatureUnit: TopdonTemperatureUnit? = null,
    val ambientTemperatureCelsius: Double? = null,
    val ambientHumidityPercent: Double? = null
)

@Serializable
data class RgbCameraConfig(
    val id: String,
    val displayName: String = "Phone RGB Camera",
    val videoFps: Int? = null,
    val videoBitRate: Int? = null,
    val rawEnabled: Boolean? = null,
    val rawIntervalMs: Long? = null,
    val exposureTimeNs: Long? = null,
    val iso: Int? = null,
    val focusDistanceMeters: Double? = null,
    val awbMode: String? = null
)
