package com.buccancs.data.sensor.connector.camera

import android.hardware.camera2.CaptureRequest
import java.util.Locale
import kotlin.math.max
import kotlin.math.min

internal data class CameraSettings(
    val videoFps: Int = DEFAULT_VIDEO_FPS,
    val videoBitRate: Int = DEFAULT_VIDEO_BIT_RATE,
    val exposureTimeNs: Long? = null,
    val sensitivityIso: Int? = null,
    val awbMode: Int? = null,
    val focusDistanceDiopters: Float? = null,
    val enableRawCapture: Boolean = true,
    val rawCaptureIntervalMs: Long = DEFAULT_RAW_INTERVAL_MS
) {
    fun withOverrides(options: Map<String, String>): CameraSettings {
        var settings = this
        options["video_fps"]?.toIntOrNull()?.let { fps ->
            val clamped = fps.coerceIn(1, MAX_SUPPORTED_FPS)
            settings = settings.copy(videoFps = clamped)
        }
        options["video_bitrate"]?.toIntOrNull()?.let { bitrate ->
            val clamped = bitrate.coerceIn(MIN_VIDEO_BIT_RATE, MAX_VIDEO_BIT_RATE)
            settings = settings.copy(videoBitRate = clamped)
        }
        options["exposure_time_ns"]?.toLongOrNull()?.let { exposure ->
            val constrained = max(MIN_EXPOSURE_TIME_NS, min(exposure, MAX_EXPOSURE_TIME_NS))
            settings = settings.copy(exposureTimeNs = constrained)
        }
        options["iso"]?.toIntOrNull()?.let { iso ->
            val constrained = iso.coerceIn(MIN_ISO_SENSITIVITY, MAX_ISO_SENSITIVITY)
            settings = settings.copy(sensitivityIso = constrained)
        }
        options["awb_mode"]?.let { mode ->
            parseAwbMode(mode)?.let { awb ->
                settings = settings.copy(awbMode = awb)
            }
        }
        options["focus_distance_diopters"]?.toFloatOrNull()?.let { diopters ->
            val constrained = diopters.coerceIn(0.0f, MAX_FOCUS_DIOPTERS)
            settings = settings.copy(focusDistanceDiopters = constrained)
        }
        options["focus_distance_m"]?.toFloatOrNull()?.takeIf { it > 0f }?.let { meters ->
            val diopters = 1.0f / meters
            val constrained = diopters.coerceIn(0.0f, MAX_FOCUS_DIOPTERS)
            settings = settings.copy(focusDistanceDiopters = constrained)
        }
        options["raw_enabled"]?.let { flag ->
            parseBoolean(flag)?.let { enabled ->
                settings = settings.copy(enableRawCapture = enabled)
            }
        }
        options["raw_interval_ms"]?.toLongOrNull()?.let { interval ->
            val constrained = interval.coerceIn(MIN_RAW_INTERVAL_MS, MAX_RAW_INTERVAL_MS)
            settings = settings.copy(rawCaptureIntervalMs = constrained)
        }
        return settings
    }

    companion object {
        const val DEFAULT_VIDEO_FPS = 60
        const val DEFAULT_VIDEO_BIT_RATE = 75_000_000
        const val DEFAULT_RAW_INTERVAL_MS = 1_000L
        private const val MAX_SUPPORTED_FPS = 240
        private const val MIN_VIDEO_BIT_RATE = 4_000_000
        private const val MAX_VIDEO_BIT_RATE = 120_000_000
        private const val MIN_EXPOSURE_TIME_NS = 100_000L
        private const val MAX_EXPOSURE_TIME_NS = 100_000_000L
        private const val MIN_ISO_SENSITIVITY = 50
        private const val MAX_ISO_SENSITIVITY = 6400
        private const val MAX_FOCUS_DIOPTERS = 1000.0f
        private const val MIN_RAW_INTERVAL_MS = 200L
        private const val MAX_RAW_INTERVAL_MS = 5_000L

        fun defaults(): CameraSettings = CameraSettings()

        fun fromOptions(options: Map<String, String>): CameraSettings =
            defaults().withOverrides(options)

        private fun parseBoolean(value: String): Boolean? {
            return when (value.trim().lowercase(Locale.US)) {
                "true", "1", "yes", "on" -> true
                "false", "0", "no", "off" -> false
                else -> null
            }
        }

        private fun parseAwbMode(value: String): Int? {
            val normalized = value.trim().lowercase(Locale.US)
            return when (normalized) {
                "auto" -> CaptureRequest.CONTROL_AWB_MODE_AUTO
                "incandescent" -> CaptureRequest.CONTROL_AWB_MODE_INCANDESCENT
                "fluorescent" -> CaptureRequest.CONTROL_AWB_MODE_FLUORESCENT
                "daylight" -> CaptureRequest.CONTROL_AWB_MODE_DAYLIGHT
                "cloudy" -> CaptureRequest.CONTROL_AWB_MODE_CLOUDY_DAYLIGHT
                "shade" -> CaptureRequest.CONTROL_AWB_MODE_SHADE
                "twilight" -> CaptureRequest.CONTROL_AWB_MODE_TWILIGHT
                "warm" -> CaptureRequest.CONTROL_AWB_MODE_WARM_FLUORESCENT
                "off" -> CaptureRequest.CONTROL_AWB_MODE_OFF
                else -> normalized.toIntOrNull()
            }
        }
    }
}
