@file:OptIn(kotlin.time.ExperimentalTime::class)

package com.buccancs.domain.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class CalibrationPatternConfig(
    val rows: Int,
    val cols: Int,
    val squareSizeMeters: Double
) {
    init {
        require(
            rows > 1
        ) { "rows must be greater than 1" }
        require(
            cols > 1
        ) { "cols must be greater than 1" }
        require(
            squareSizeMeters > 0.0
        ) { "squareSizeMeters must be positive" }
    }
}

@Serializable
data class CameraIntrinsicParameters(
    val fx: Double,
    val fy: Double,
    val skew: Double,
    val cx: Double,
    val cy: Double,
    val radial: List<Double>,
    val tangential: List<Double>,
    val width: Int,
    val height: Int
)

@Serializable
data class ExtrinsicTransform(
    val rotationMatrix: List<Double>,
    val translation: List<Double>
)

@Serializable
data class CalibrationResult(
    @Contextual val generatedAt: Instant,
    val pattern: CalibrationPatternConfig,
    val requiredPairs: Int,
    val usedPairs: Int,
    val meanReprojectionError: Double,
    val perViewErrors: List<Double>,
    val rgbIntrinsics: CameraIntrinsicParameters,
    val thermalIntrinsics: CameraIntrinsicParameters,
    val extrinsic: ExtrinsicTransform
)

@Serializable
data class CalibrationMetrics(
    @Contextual val generatedAt: Instant,
    val meanReprojectionError: Double,
    val maxReprojectionError: Double,
    val usedPairs: Int,
    val requiredPairs: Int
)

data class CalibrationImageDescriptor(
    val path: String,
    val width: Int,
    val height: Int,
    @Contextual val capturedAt: Instant
)

data class CalibrationCapture(
    val id: String,
    val rgb: CalibrationImageDescriptor,
    val thermal: CalibrationImageDescriptor
)

data class CalibrationSessionState(
    val active: Boolean,
    val pattern: CalibrationPatternConfig,
    val requiredPairs: Int,
    val captures: List<CalibrationCapture>,
    val isProcessing: Boolean,
    val lastResult: CalibrationResult?,
    val infoMessage: String?,
    val errorMessage: String?
)

object CalibrationDefaults {
    val Pattern =
        CalibrationPatternConfig(
            rows = 7,
            cols = 9,
            squareSizeMeters = 0.024
        )
    const val RequiredPairs =
        12
}
