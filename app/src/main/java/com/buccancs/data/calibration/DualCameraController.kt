package com.buccancs.data.calibration

import android.graphics.Bitmap
import com.buccancs.domain.model.CalibrationPatternConfig
import kotlin.time.Instant

data class CalibrationFrame(
    val bitmap: Bitmap,
    val capturedAt: Instant
)

data class CalibrationFramePair(
    val rgb: CalibrationFrame,
    val thermal: CalibrationFrame
)

data class CameraStreamInfo(
    val cameraId: String,
    val width: Int,
    val height: Int,
    val description: String
)

interface DualCameraController {
    val rgbInfo: CameraStreamInfo
    val thermalInfo: CameraStreamInfo
    val isSimulation: Boolean
    suspend fun ensureReady()
    suspend fun capturePair(pattern: CalibrationPatternConfig): CalibrationFramePair
    suspend fun shutdown()
}
