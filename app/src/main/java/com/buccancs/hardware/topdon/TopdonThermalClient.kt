package com.buccancs.hardware.topdon

import com.buccancs.core.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * Abstraction over the Topdon TC001 vendor SDK.
 *
 * Implementations own the USB monitor, IRCMD lifecycle, preview streams,
 * and temperature normalization derived from the official SDK.
 */
interface TopdonThermalClient {
    /**
     * Current USB attachment state and metadata for the primary device.
     */
    val status: StateFlow<TopdonStatus>

    /**
     * Thermal preview frames emitted when previewing is active.
     */
    val previewFrames: Flow<TopdonPreviewFrame>

    /**
     * Streaming telemetry emitted while recording thermal video.
     */
    val streamEvents: Flow<TopdonStreamEvent>

    /**
     * Vendor notices (warnings, calibration prompts, etc.).
     */
    val notices: Flow<TopdonNotice>

    /**
     * Refresh the underlying USB device inventory.
     */
    suspend fun refreshInventory()

    /**
     * Attempt to connect to the detected device, requesting permission if required.
     */
    suspend fun connect()

    /**
     * Disconnect and reset the hardware state.
     */
    suspend fun disconnect()

    /**
     * Apply runtime settings such as palette, emissivity, and gain.
     */
    suspend fun applySettings(settings: TopdonHardwareSettings)

    /**
     * Start emitting preview frames without persisting them.
     */
    suspend fun startPreview(): Result<Unit>

    /**
     * Stop preview emission.
     */
    suspend fun stopPreview(): Result<Unit>

    /**
     * Begin streaming thermal frames to persistent storage.
     */
    suspend fun startStreaming(request: TopdonStreamRequest)

    /**
     * Stop streaming and finalize artifacts.
     */
    suspend fun stopStreaming()
}

sealed interface TopdonStatus {
    data object Idle : TopdonStatus
    data class Attached(
        val vendorId: Int,
        val productId: Int,
        val serialNumber: String?,
        val hasPermission: Boolean,
    ) : TopdonStatus

    data class Connected(
        val vendorId: Int,
        val productId: Int,
        val serialNumber: String?,
        val sinceEpochMs: Long,
    ) : TopdonStatus

    data class Streaming(
        val vendorId: Int,
        val productId: Int,
        val serialNumber: String?,
        val sinceEpochMs: Long,
    ) : TopdonStatus

    data class Error(val message: String, val recoverable: Boolean) : TopdonStatus
}

data class TopdonPreviewFrame(
    val timestampEpochMs: Long,
    val width: Int,
    val height: Int,
    val payload: ByteArray,
    val metrics: TopdonTemperatureMetrics,
    val palette: Palette,
    val superSamplingFactor: Int = 1,
    val mimeType: String = "application/octet-stream",
)

data class TopdonStreamEvent(
    val timestampEpochMs: Long,
    val frameCount: Long,
    val bytesCaptured: Long,
    val metrics: TopdonTemperatureMetrics,
    val payload: ByteArray?,
    val endOfStream: Boolean = false,
)

data class TopdonNotice(
    val message: String,
    val category: Category,
) {
    enum class Category { Info, Warning, Error }
}

data class TopdonHardwareSettings(
    val palette: Palette?,
    val emissivity: Double?,
    val distanceMeters: Double?,
    val autoShutter: Boolean?,
    val gainMode: GainMode?,
    val previewFpsLimit: Int? = null,
    val superSamplingFactor: Int? = null,
)

data class TopdonStreamRequest(
    val sessionId: String,
    val destinationPath: String,
    val superSampling: Int,
)

data class TopdonTemperatureMetrics(
    val minCelsius: Double,
    val maxCelsius: Double,
    val avgCelsius: Double,
) {
    companion object {
        val Unknown = TopdonTemperatureMetrics(Double.NaN, Double.NaN, Double.NaN)
    }
}

enum class Palette {
    Ironbow,
    Gray,
    Rainbow,
    Arctic,
    Custom,
}

enum class GainMode {
    Auto,
    High,
    Low,
}
