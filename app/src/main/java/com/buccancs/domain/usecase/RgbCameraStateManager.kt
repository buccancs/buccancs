package com.buccancs.domain.usecase

import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorDevice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RgbCameraStateManager @Inject constructor(
    private val hardwareConfiguration: HardwareConfigurationUseCase
) {
    private val _cameraInputs =
        MutableStateFlow<Map<DeviceId, RgbCameraInputState>>(
            emptyMap()
        )
    val cameraInputs: StateFlow<Map<DeviceId, RgbCameraInputState>> =
        _cameraInputs

    fun updateField(
        id: DeviceId,
        field: RgbCameraField,
        value: String
    ) {
        updateState(
            id
        ) { state ->
            state.updateField(
                field,
                value
            )
        }
    }

    fun toggleRawEnabled(
        id: DeviceId,
        enabled: Boolean
    ) {
        updateState(
            id
        ) { state ->
            state.updateRawEnabled(
                enabled
            )
        }
    }

    fun selectAwb(
        id: DeviceId,
        awbValue: String
    ) {
        updateState(
            id
        ) { state ->
            state.updateAwb(
                awbValue
            )
        }
    }

    fun resetSettings(
        id: DeviceId
    ) {
        updateState(
            id
        ) { state -> state.reset() }
    }

    suspend fun applySettings(
        id: DeviceId
    ): Result<Unit> {
        val current =
            _cameraInputs.value[id]
                ?: return Result.failure(
                    IllegalStateException(
                        "No camera state for $id"
                    )
                )
        if (!current.dirty) {
            return Result.success(
                Unit
            )
        }
        val validation =
            validateCamera(
                current
            )
        if (validation.errors.isNotEmpty()) {
            updateState(
                id
            ) { state ->
                state.withErrors(
                    validation.errors
                )
            }
            return Result.failure(
                IllegalArgumentException(
                    "Validation errors: ${validation.errors}"
                )
            )
        }
        val options =
            validation.options
        return hardwareConfiguration.configureRgbCamera(
            id,
            options
        )
            .onSuccess {
                updateState(
                    id
                ) { state -> state.markApplied() }
            }
    }

    fun ensureState(
        device: SensorDevice
    ): RgbCameraInputState {
        val supportsRaw =
            parseBooleanValue(
                device.attributes[ATTR_RGB_RAW_SUPPORTED]
            )
                ?: false
        val baseline =
            buildBaseline(
                device.attributes,
                supportsRaw
            )
        var resolved: RgbCameraInputState? =
            null
        _cameraInputs.value =
            _cameraInputs.value.toMutableMap()
                .apply {
                    val existing =
                        this[device.id]
                    val next =
                        when {
                            existing == null -> RgbCameraInputState(
                                supportsRaw = supportsRaw,
                                baseline = baseline,
                                inputs = baseline,
                                dirty = false,
                                errors = emptyMap()
                            )

                            !existing.dirty -> existing.copy(
                                supportsRaw = supportsRaw,
                                baseline = baseline,
                                inputs = baseline,
                                errors = emptyMap()
                            )

                            else -> existing.copy(
                                supportsRaw = supportsRaw,
                                baseline = baseline
                            )
                        }
                    resolved =
                        next
                    this[device.id] =
                        next
                }
        return resolved!!
    }

    private fun updateState(
        id: DeviceId,
        transform: (RgbCameraInputState) -> RgbCameraInputState
    ) {
        _cameraInputs.value =
            _cameraInputs.value.toMutableMap()
                .apply {
                    val state =
                        this[id]
                            ?: return@apply
                    this[id] =
                        transform(
                            state
                        )
                }
    }

    private fun buildBaseline(
        attributes: Map<String, String>,
        supportsRaw: Boolean
    ): RgbCameraValues {
        val videoFps =
            attributes[ATTR_RGB_VIDEO_FPS]?.takeIf { it.isNotBlank() }
                ?: DEFAULT_RGB_VIDEO_FPS
        val videoBitRate =
            attributes[ATTR_RGB_VIDEO_BIT_RATE]?.takeIf { it.isNotBlank() }
                ?: DEFAULT_RGB_VIDEO_BITRATE
        val rawInterval =
            attributes[ATTR_RGB_RAW_INTERVAL]?.takeIf { it.isNotBlank() }
                ?: DEFAULT_RGB_RAW_INTERVAL
        val exposure =
            attributes[ATTR_RGB_EXPOSURE]?.takeIf { it.isNotBlank() }
                ?: ""
        val iso =
            attributes[ATTR_RGB_ISO]?.takeIf { it.isNotBlank() }
                ?: ""
        val focusMeters =
            attributes[ATTR_RGB_FOCUS_METERS]?.takeIf { it.isNotBlank() }
                ?: ""
        val awb =
            attributes[ATTR_RGB_AWB]?.takeIf { it.isNotBlank() }
                ?: DEFAULT_RGB_AWB
        val rawEnabled =
            if (!supportsRaw) {
                false
            } else {
                parseBooleanValue(
                    attributes[ATTR_RGB_RAW_ENABLED]
                )
                    ?: true
            }
        return RgbCameraValues(
            videoFps = videoFps,
            videoBitRate = videoBitRate,
            rawIntervalMs = rawInterval,
            exposureNs = exposure,
            iso = iso,
            focusMeters = focusMeters,
            awbMode = awb,
            rawEnabled = rawEnabled
        )
    }

    private fun validateCamera(
        state: RgbCameraInputState
    ): RgbCameraValidationResult {
        val options =
            mutableMapOf<String, String>()
        val errors =
            mutableMapOf<RgbCameraField, String>()
        val fpsText =
            state.inputs.videoFps.trim()
        if (fpsText.isEmpty()) {
            errors[RgbCameraField.VIDEO_FPS] =
                "Required"
        } else {
            val fps =
                fpsText.toIntOrNull()
            if (fps == null || fps <= 0) {
                errors[RgbCameraField.VIDEO_FPS] =
                    "Invalid FPS"
            } else {
                options["video_fps"] =
                    fps.toString()
            }
        }
        val bitrateText =
            state.inputs.videoBitRate.trim()
        if (bitrateText.isEmpty()) {
            errors[RgbCameraField.VIDEO_BIT_RATE] =
                "Required"
        } else {
            val bitrate =
                bitrateText.toIntOrNull()
            if (bitrate == null || bitrate <= 0) {
                errors[RgbCameraField.VIDEO_BIT_RATE] =
                    "Invalid bitrate"
            } else {
                options["video_bitrate"] =
                    bitrate.toString()
            }
        }
        if (state.supportsRaw) {
            options["raw_enabled"] =
                state.inputs.rawEnabled.toString()
            if (state.inputs.rawEnabled) {
                val intervalText =
                    state.inputs.rawIntervalMs.trim()
                if (intervalText.isEmpty()) {
                    errors[RgbCameraField.RAW_INTERVAL_MS] =
                        "Required"
                } else {
                    val interval =
                        intervalText.toLongOrNull()
                    if (interval == null || interval <= 0L) {
                        errors[RgbCameraField.RAW_INTERVAL_MS] =
                            "Invalid interval"
                    } else {
                        options["raw_interval_ms"] =
                            interval.toString()
                    }
                }
            }
        }
        val exposureText =
            state.inputs.exposureNs.trim()
        if (exposureText.isNotEmpty()) {
            val exposure =
                exposureText.toLongOrNull()
            if (exposure == null || exposure <= 0L) {
                errors[RgbCameraField.EXPOSURE_NS] =
                    "Invalid exposure"
            } else {
                options["exposure_time_ns"] =
                    exposure.toString()
            }
        }
        val isoText =
            state.inputs.iso.trim()
        if (isoText.isNotEmpty()) {
            val iso =
                isoText.toIntOrNull()
            if (iso == null || iso <= 0) {
                errors[RgbCameraField.ISO] =
                    "Invalid ISO"
            } else {
                options["iso"] =
                    iso.toString()
            }
        }
        val focusText =
            state.inputs.focusMeters.trim()
        if (focusText.isNotEmpty()) {
            val focusMeters =
                focusText.toDoubleOrNull()
            if (focusMeters == null || focusMeters <= 0.0) {
                errors[RgbCameraField.FOCUS_METERS] =
                    "Invalid distance"
            } else {
                options["focus_distance_m"] =
                    String.format(
                        Locale.US,
                        "%.4f",
                        focusMeters
                    )
            }
        }
        val awb =
            state.inputs.awbMode.ifBlank { DEFAULT_RGB_AWB }
        options["awb_mode"] =
            awb
        return RgbCameraValidationResult(
            options,
            errors
        )
    }

    private fun parseBooleanValue(
        value: String?
    ): Boolean? =
        when (value?.lowercase(
            Locale.US
        )) {
            "true" -> true
            "false" -> false
            else -> null
        }

    companion object {
        private const val ATTR_RGB_VIDEO_FPS =
            "rgb.video_fps"
        private const val ATTR_RGB_VIDEO_BIT_RATE =
            "rgb.video_bitrate"
        private const val ATTR_RGB_RAW_ENABLED =
            "rgb.raw_enabled"
        private const val ATTR_RGB_RAW_INTERVAL =
            "rgb.raw_interval_ms"
        private const val ATTR_RGB_EXPOSURE =
            "rgb.exposure_time_ns"
        private const val ATTR_RGB_ISO =
            "rgb.iso"
        private const val ATTR_RGB_AWB =
            "rgb.awb_mode"
        private const val ATTR_RGB_FOCUS_METERS =
            "rgb.focus_distance_m"
        private const val ATTR_RGB_RAW_SUPPORTED =
            "rgb.raw_supported"
        private const val DEFAULT_RGB_VIDEO_FPS =
            "60"
        private const val DEFAULT_RGB_VIDEO_BITRATE =
            "75000000"
        private const val DEFAULT_RGB_RAW_INTERVAL =
            "1000"
        private const val DEFAULT_RGB_AWB =
            "auto"
    }
}

data class RgbCameraInputState(
    val supportsRaw: Boolean,
    val baseline: RgbCameraValues,
    val inputs: RgbCameraValues,
    val dirty: Boolean,
    val errors: Map<RgbCameraField, String>
) {
    fun updateField(
        field: RgbCameraField,
        value: String
    ): RgbCameraInputState {
        val trimmed =
            value.trim()
        val updatedInputs =
            when (field) {
                RgbCameraField.VIDEO_FPS -> inputs.copy(
                    videoFps = trimmed
                )

                RgbCameraField.VIDEO_BIT_RATE -> inputs.copy(
                    videoBitRate = trimmed
                )

                RgbCameraField.RAW_INTERVAL_MS -> inputs.copy(
                    rawIntervalMs = trimmed
                )

                RgbCameraField.EXPOSURE_NS -> inputs.copy(
                    exposureNs = trimmed
                )

                RgbCameraField.ISO -> inputs.copy(
                    iso = trimmed
                )

                RgbCameraField.FOCUS_METERS -> inputs.copy(
                    focusMeters = trimmed
                )
            }
        return copy(
            inputs = updatedInputs,
            dirty = true,
            errors = errors - field
        )
    }

    fun updateRawEnabled(
        enabled: Boolean
    ): RgbCameraInputState {
        val coerced =
            if (supportsRaw) enabled else false
        val clearedErrors =
            if (!coerced) errors - RgbCameraField.RAW_INTERVAL_MS else errors
        return copy(
            inputs = inputs.copy(
                rawEnabled = coerced
            ),
            dirty = true,
            errors = clearedErrors
        )
    }

    fun updateAwb(
        value: String
    ): RgbCameraInputState =
        copy(
            inputs = inputs.copy(
                awbMode = value
            ),
            dirty = true
        )

    fun reset(): RgbCameraInputState =
        copy(
            inputs = baseline,
            dirty = false,
            errors = emptyMap()
        )

    fun markApplied(): RgbCameraInputState =
        copy(
            baseline = inputs,
            dirty = false,
            errors = emptyMap()
        )

    fun withErrors(
        messages: Map<RgbCameraField, String>
    ): RgbCameraInputState =
        copy(
            errors = messages
        )
}

data class RgbCameraValues(
    val videoFps: String,
    val videoBitRate: String,
    val rawIntervalMs: String,
    val exposureNs: String,
    val iso: String,
    val focusMeters: String,
    val awbMode: String,
    val rawEnabled: Boolean
)

data class RgbCameraValidationResult(
    val options: Map<String, String>,
    val errors: Map<RgbCameraField, String>
)

enum class RgbCameraField {
    VIDEO_FPS,
    VIDEO_BIT_RATE,
    RAW_INTERVAL_MS,
    EXPOSURE_NS,
    ISO,
    FOCUS_METERS
}
