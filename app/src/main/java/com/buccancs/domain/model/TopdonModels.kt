package com.buccancs.domain.model

import java.time.LocalDateTime
import kotlin.time.Instant

val TOPDON_TC001_DEVICE_ID: DeviceId =
    DeviceId(
        "topdon-tc001"
    )
val SHIMMER_PRIMARY_DEVICE_ID: DeviceId =
    DeviceId(
        "shimmer-primary"
    )

enum class TopdonPalette {
    GRAYSCALE,
    IRONBOW,
    RAINBOW
}

enum class TopdonSuperSamplingFactor(
    val multiplier: Int
) {
    X1(1),
    X2(2),
    X4(4);

    companion object {
        fun fromMultiplier(
            value: Int
        ): TopdonSuperSamplingFactor =
            values().firstOrNull { it.multiplier == value }
                ?: X1
    }
}

data class TopdonSettings(
    val autoConnectOnAttach: Boolean = true,
    val palette: TopdonPalette = TopdonPalette.GRAYSCALE,
    val superSampling: TopdonSuperSamplingFactor = TopdonSuperSamplingFactor.X2,
    val previewFpsLimit: Int = DEFAULT_PREVIEW_FPS_LIMIT
) {
    companion object {
        const val DEFAULT_PREVIEW_FPS_LIMIT =
            12
    }
}

data class TopdonPreviewFrame(
    val timestamp: Instant,
    val width: Int,
    val height: Int,
    val mimeType: String,
    val payload: ByteArray,
    val superSamplingFactor: Int,
    val minTemp: Float? = null,
    val maxTemp: Float? = null,
    val avgTemp: Float? = null
)

data class TopdonDeviceState(
    val device: SensorDevice?,
    val statuses: List<SensorStreamStatus>,
    val previewActive: Boolean,
    val lastPreviewTimestamp: Instant?,
    val scanning: Boolean,
    val lastError: String?
)

data class ThermalImage(
    val id: String,
    val filePath: String,
    val timestamp: LocalDateTime,
    val width: Int,
    val height: Int,
    val palette: TopdonPalette,
    val superSampling: TopdonSuperSamplingFactor,
    val minTemperature: Float,
    val maxTemperature: Float,
    val avgTemperature: Float,
    val thumbnailPath: String? = null,
    val fileSize: Long = 0L
)

data class ThermalVideo(
    val id: String,
    val filePath: String,
    val timestamp: LocalDateTime,
    val duration: Long,
    val frameCount: Int,
    val thumbnailPath: String? = null,
    val fileSize: Long = 0L
)

sealed class ThermalMediaItem {
    abstract val id: String
    abstract val timestamp: LocalDateTime
    abstract val thumbnailPath: String?

    data class Image(
        val image: ThermalImage
    ) : ThermalMediaItem() {
        override val id: String =
            image.id
        override val timestamp: LocalDateTime =
            image.timestamp
        override val thumbnailPath: String? =
            image.thumbnailPath
    }

    data class Video(
        val video: ThermalVideo
    ) : ThermalMediaItem() {
        override val id: String =
            video.id
        override val timestamp: LocalDateTime =
            video.timestamp
        override val thumbnailPath: String? =
            video.thumbnailPath
    }
}
