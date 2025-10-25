package com.buccancs.desktop.data.repository

import com.buccancs.desktop.domain.model.PreviewModality
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant

class PreviewRepository {
    private val previews =
        MutableStateFlow<Map<PreviewKey, PreviewFrameData>>(
            emptyMap()
        )

    fun observe(): StateFlow<Map<PreviewKey, PreviewFrameData>> =
        previews.asStateFlow()

    fun update(
        deviceId: String,
        cameraId: String,
        frameTimestampEpochMs: Long,
        encodedFrame: ByteArray,
        mimeType: String,
        width: Int,
        height: Int,
        latencyMs: Double,
        modality: PreviewModality,
        averageFps: Double,
        dropCount: Long,
        maxGapMs: Long,
        totalFrames: Long
    ) {
        val key =
            PreviewKey(
                deviceId,
                cameraId
            )
        val data =
            PreviewFrameData(
                deviceId = deviceId,
                cameraId = cameraId,
                capturedAt = Instant.ofEpochMilli(
                    frameTimestampEpochMs
                ),
                receivedAt = Instant.now(),
                mimeType = mimeType,
                width = width,
                height = height,
                payload = encodedFrame,
                latencyMs = latencyMs,
                modality = modality,
                averageFps = averageFps,
                dropCount = dropCount,
                maxGapMs = maxGapMs,
                totalFrames = totalFrames
            )
        previews.value =
            previews.value.toMutableMap()
                .apply {
                    put(
                        key,
                        data
                    )
                }
    }

    data class PreviewKey(
        val deviceId: String,
        val cameraId: String
    )

    data class PreviewFrameData(
        val deviceId: String,
        val cameraId: String,
        val capturedAt: Instant,
        val receivedAt: Instant,
        val mimeType: String,
        val width: Int,
        val height: Int,
        val payload: ByteArray,
        val latencyMs: Double,
        val modality: PreviewModality,
        val averageFps: Double,
        val dropCount: Long,
        val maxGapMs: Long,
        val totalFrames: Long
    )
}
