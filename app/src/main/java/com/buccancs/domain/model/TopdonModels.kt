package com.buccancs.domain.model

import kotlin.time.Instant

val TOPDON_TC001_DEVICE_ID: DeviceId = DeviceId("topdon-tc001")

enum class TopdonPalette {
    GRAYSCALE,
    IRONBOW,
    RAINBOW
}

enum class TopdonSuperSamplingFactor(val multiplier: Int) {
    X1(1),
    X2(2),
    X4(4);

    companion object {
        fun fromMultiplier(value: Int): TopdonSuperSamplingFactor =
            values().firstOrNull { it.multiplier == value } ?: X1
    }
}

data class TopdonSettings(
    val autoConnectOnAttach: Boolean = true,
    val palette: TopdonPalette = TopdonPalette.GRAYSCALE,
    val superSampling: TopdonSuperSamplingFactor = TopdonSuperSamplingFactor.X2,
    val previewFpsLimit: Int = DEFAULT_PREVIEW_FPS_LIMIT
) {
    companion object {
        const val DEFAULT_PREVIEW_FPS_LIMIT = 12
    }
}

data class TopdonPreviewFrame(
    val timestamp: Instant,
    val width: Int,
    val height: Int,
    val mimeType: String,
    val payload: ByteArray,
    val superSamplingFactor: Int
)

data class TopdonDeviceState(
    val device: SensorDevice?,
    val statuses: List<SensorStreamStatus>,
    val previewActive: Boolean,
    val lastPreviewTimestamp: Instant?,
    val scanning: Boolean,
    val lastError: String?
)
