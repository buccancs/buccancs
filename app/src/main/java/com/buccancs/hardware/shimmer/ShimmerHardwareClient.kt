package com.buccancs.hardware.shimmer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Abstraction over the vendor Shimmer SDK components.
 *
 * Implementations must encapsulate all direct interactions with the Shimmer
 * artifact jars, expose lifecycle events via flows, and remain safe to call
 * from non-main threads unless otherwise documented.
 */
interface ShimmerHardwareClient {
    /**
     * Current inventory of devices detected via bonding or scanning.
     */
    val devices: StateFlow<List<ShimmerHardwareDevice>>

    /**
     * Real-time connection and streaming status for the active device, if any.
     */
    val status: StateFlow<ShimmerStatus>

    /**
     * Stream of unpacked sensor samples emitted while streaming is active.
     */
    val samples: Flow<ShimmerSample>

    /**
     * Stream of human-readable events surfaced by the SDK (toasts, warnings).
     */
    val notices: Flow<ShimmerNotice>

    /**
     * Refresh the bonded devices cache using the system Bluetooth adapter.
     */
    suspend fun refreshBondedDevices()

    /**
     * Perform a BLE scan for additional devices and merge them into [devices].
     */
    suspend fun scan(duration: Duration = DEFAULT_SCAN_DURATION)

    /**
     * Establish a connection to the given MAC address.
     */
    suspend fun connect(macAddress: String)

    /**
     * Disconnect the current device and reset state.
     */
    suspend fun disconnect()

    /**
     * Apply hardware-level settings to the connected device.
     */
    suspend fun applySettings(settings: ShimmerHardwareSettings)

    /**
     * Begin streaming sensor data. Emitted samples flow through [samples].
     */
    suspend fun startStreaming()

    /**
     * Stop streaming and flush any remaining data.
     */
    suspend fun stopStreaming()

    private companion object {
        val DEFAULT_SCAN_DURATION: Duration = 5.seconds
    }
}

data class ShimmerHardwareDevice(
    val macAddress: String,
    val name: String?,
    val rssi: Int?,
    val firmwareVersion: String?,
    val hardwareVersion: Int?,
    val bonded: Boolean,
)

data class ShimmerHardwareSettings(
    val gsrRangeIndex: Int?,
    val sampleRateHz: Double?,
    val firmwarePreset: FirmwarePreset?,
) {
    enum class FirmwarePreset {
        DEFAULT_ECG,
        DEFAULT_EMG,
        TEST_SIGNAL,
    }
}

sealed interface ShimmerStatus {
    data object Idle : ShimmerStatus
    data class Connecting(val macAddress: String) : ShimmerStatus
    data class Connected(
        val macAddress: String,
        val sinceEpochMs: Long,
        val firmwareVersion: String?,
        val hardwareVersion: Int?,
    ) : ShimmerStatus

    data class Streaming(
        val macAddress: String,
        val sinceEpochMs: Long,
        val samplesPerSecond: Double,
    ) : ShimmerStatus

    data class Error(val macAddress: String?, val message: String, val recoverable: Boolean) : ShimmerStatus
}

data class ShimmerSample(
    val timestampEpochMs: Long,
    val conductanceSiemens: Double?,
    val resistanceOhms: Double?,
)

data class ShimmerNotice(
    val message: String,
    val category: Category = Category.Info,
) {
    enum class Category { Info, Warning, Error }
}
