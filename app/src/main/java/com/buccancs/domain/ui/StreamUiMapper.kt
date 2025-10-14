package com.buccancs.domain.ui

import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.ui.StreamUiModel
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

/**
 * Maps stream status domain models to UI models.
 * Extracted from MainViewModel to separate transformation concerns.
 */
@Singleton
class StreamUiMapper @Inject constructor() {

    fun toUiModel(status: SensorStreamStatus): StreamUiModel {
        val rate = when {
            status.sampleRateHz != null -> "${status.sampleRateHz.roundToInt()} Hz"
            status.frameRateFps != null -> "${status.frameRateFps.roundToInt()} FPS"
            else -> "n/a"
        }
        val bufferText = status.bufferedDurationSeconds?.let { 
            String.format(Locale.US, "%.1f", it) 
        } ?: "-"
        val detail = "Buffered $bufferText s @ $rate"
        val lastSample = status.lastSampleTimestamp?.toString() ?: "-"
        
        return StreamUiModel(
            deviceId = status.deviceId,
            typeLabel = sensorStreamLabel(status.streamType),
            detail = detail,
            lastSampleTimestamp = lastSample,
            isSimulated = status.isSimulated
        )
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
