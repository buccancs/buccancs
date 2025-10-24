package com.buccancs.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SensorHardwareConfig(
    val shimmer: List<ShimmerDeviceConfig> = emptyList(),
    val topdon: List<TopdonDeviceConfig> = emptyList()
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
    val previewFpsLimit: Int? = null
)
