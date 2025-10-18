package com.buccancs.hardware.shimmer

import android.bluetooth.BluetoothAdapter
import android.util.Log
import com.buccancs.di.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

/**
 * Lightweight in-app implementation that mimics the behaviour of the vendor Shimmer SDK.
 *
 * The real Shimmer Java APIs rely on a collection of external jars and Android services that are
 * not available in this codebase. To keep the rest of the pipeline compiling (and to enable
 * integration testing), we provide a minimal stub that exposes the same contract but generates
 * synthetic data.
 *
 * Once the actual SDK is integrated, this implementation can be replaced with one that proxies
 * to the vendor libraries. Until then, the stub keeps all call sites type-safe and responsive.
 */
@Singleton
class DefaultShimmerHardwareClient @Inject constructor(
    @ApplicationContext @Suppress(
        "UnusedPrivateMember"
    ) private val context: android.content.Context,
    @ApplicationScope private val applicationScope: CoroutineScope,
    @Suppress(
        "UnusedPrivateMember"
    ) private val bluetoothAdapter: BluetoothAdapter?,
) : ShimmerHardwareClient {

    private val logTag =
        "ShimmerHardwareStub"

    private val devicesState =
        MutableStateFlow<List<ShimmerHardwareDevice>>(
            emptyList()
        )
    private val statusState =
        MutableStateFlow<ShimmerStatus>(
            ShimmerStatus.Idle
        )
    private val samplesSharedFlow =
        MutableSharedFlow<ShimmerSample>(
            extraBufferCapacity = 64
        )
    private val noticesSharedFlow =
        MutableSharedFlow<ShimmerNotice>(
            extraBufferCapacity = 16
        )

    override val devices: StateFlow<List<ShimmerHardwareDevice>> =
        devicesState.asStateFlow()
    override val status: StateFlow<ShimmerStatus> =
        statusState.asStateFlow()
    override val samples: Flow<ShimmerSample> =
        samplesSharedFlow.asSharedFlow()
    override val notices: Flow<ShimmerNotice> =
        noticesSharedFlow.asSharedFlow()

    private var activeDevice: ShimmerHardwareDevice? =
        null
    private var streamingJob: Job? =
        null

    override suspend fun refreshBondedDevices() {
        // Query actual bonded Bluetooth devices
        try {
            val bondedDevices =
                bluetoothAdapter?.bondedDevices?.filter { device ->
                    device.name?.contains(
                        "Shimmer",
                        ignoreCase = true
                    ) == true ||
                            device.name?.contains(
                                "GSR",
                                ignoreCase = true
                            ) == true
                }
                    ?: emptyList()

            val realDevices =
                bondedDevices.map { btDevice ->
                    ShimmerHardwareDevice(
                        macAddress = btDevice.address,
                        name = btDevice.name
                            ?: "Unknown Shimmer",
                        rssi = 0, // RSSI not available for bonded devices without scanning
                        firmwareVersion = "Unknown",
                        hardwareVersion = 0,
                        bonded = true
                    )
                }

            devicesState.value =
                realDevices
            Log.d(
                logTag,
                "refreshBondedDevices() found ${realDevices.size} real Shimmer devices"
            )

            if (realDevices.isNotEmpty()) {
                noticesSharedFlow.emit(
                    ShimmerNotice(
                        message = "Found ${realDevices.size} paired Shimmer device(s). Note: Using stub implementation for data streaming.",
                        category = ShimmerNotice.Category.Info
                    )
                )
            }
        } catch (e: SecurityException) {
            Log.e(
                logTag,
                "Permission denied when accessing Bluetooth devices",
                e
            )
            devicesState.value =
                emptyList()
        }
    }

    override suspend fun scan(
        duration: kotlin.time.Duration
    ) {
        Log.d(
            logTag,
            "scan() starting Bluetooth discovery (duration=${duration.inWholeSeconds}s)"
        )

        try {
            // Try to discover real devices via Bluetooth
            if (bluetoothAdapter?.isDiscovering == true) {
                bluetoothAdapter.cancelDiscovery()
            }

            bluetoothAdapter?.startDiscovery()
            delay(
                minOf(
                    duration.inWholeMilliseconds,
                    5000
                )
            )
            bluetoothAdapter?.cancelDiscovery()

            // Refresh with any discovered devices
            refreshBondedDevices()

        } catch (e: SecurityException) {
            Log.e(
                logTag,
                "Permission denied during scan",
                e
            )
        }
    }

    override suspend fun connect(
        macAddress: String
    ) {
        val device =
            devicesState.value.firstOrNull {
                it.macAddress.equals(
                    macAddress,
                    ignoreCase = true
                )
            }
                ?: ShimmerHardwareDevice(
                    macAddress = macAddress,
                    name = "Shimmer Device",
                    rssi = null,
                    firmwareVersion = "stub-1.0",
                    hardwareVersion = 1,
                    bonded = true
                )

        activeDevice =
            device
        devicesState.value =
            devicesState.value
                .filterNot {
                    it.macAddress.equals(
                        macAddress,
                        ignoreCase = true
                    )
                } + device

        statusState.value =
            ShimmerStatus.Connected(
                macAddress = device.macAddress,
                sinceEpochMs = System.currentTimeMillis(),
                firmwareVersion = device.firmwareVersion,
                hardwareVersion = device.hardwareVersion
            )
        noticesSharedFlow.tryEmit(
            ShimmerNotice(
                "Connected to ${device.name ?: device.macAddress}",
                ShimmerNotice.Category.Info
            )
        )
    }

    override suspend fun disconnect() {
        streamingJob?.cancel()
        streamingJob =
            null
        val previous =
            activeDevice
        activeDevice =
            null
        statusState.value =
            ShimmerStatus.Idle
        if (previous != null) {
            noticesSharedFlow.tryEmit(
                ShimmerNotice(
                    "Disconnected from ${previous.name ?: previous.macAddress}",
                    ShimmerNotice.Category.Info
                )
            )
        }
    }

    override suspend fun applySettings(
        settings: ShimmerHardwareSettings
    ) {
        // Stub simply logs the request and stores updated metadata in the state flow.
        val current =
            activeDevice
                ?: return
        val updated =
            current.copy(
                firmwareVersion = current.firmwareVersion
            )
        devicesState.value =
            devicesState.value
                .filterNot {
                    it.macAddress.equals(
                        current.macAddress,
                        ignoreCase = true
                    )
                } + updated
        noticesSharedFlow.tryEmit(
            ShimmerNotice(
                message = buildString {
                    append(
                        "Applied settings: "
                    )
                    append(
                        "gsrRange="
                    ); append(
                    settings.gsrRangeIndex
                        ?: "default"
                )
                    append(
                        ", sampleRate="
                    ); append(
                    settings.sampleRateHz
                        ?: "default"
                )
                },
                category = ShimmerNotice.Category.Info
            )
        )
    }

    override suspend fun startStreaming() {
        val device =
            activeDevice
                ?: throw IllegalStateException(
                    "No device connected"
                )
        if (streamingJob?.isActive == true) return

        streamingJob =
            applicationScope.launch(
                Dispatchers.Default
            ) {
                val startTime =
                    System.currentTimeMillis()
                statusState.value =
                    ShimmerStatus.Streaming(
                        macAddress = device.macAddress,
                        sinceEpochMs = startTime,
                        samplesPerSecond = SAMPLE_RATE_HZ
                    )

                var samples =
                    0L
                while (isActive) {
                    val timestamp =
                        System.currentTimeMillis()
                    val conductance =
                        generateConductance(
                            samples
                        )
                    val resistance =
                        conductance?.let { 1.0 / it }
                    samplesSharedFlow.emit(
                        ShimmerSample(
                            timestampEpochMs = timestamp,
                            conductanceSiemens = conductance,
                            resistanceOhms = resistance
                        )
                    )
                    samples++
                    delay(
                        (1000.0 / SAMPLE_RATE_HZ).toLong()
                    )
                }
            }
    }

    override suspend fun stopStreaming() {
        streamingJob?.cancel()
        streamingJob =
            null
        val device =
            activeDevice
        if (device != null) {
            statusState.value =
                ShimmerStatus.Connected(
                    macAddress = device.macAddress,
                    sinceEpochMs = System.currentTimeMillis(),
                    firmwareVersion = device.firmwareVersion,
                    hardwareVersion = device.hardwareVersion
                )
        } else {
            statusState.value =
                ShimmerStatus.Idle
        }
    }

    private fun generateConductance(
        sampleIndex: Long
    ): Double? {
        // Simple synthetic waveform with a bit of noise.
        val base =
            (sampleIndex % SAMPLE_RATE_HZ).toDouble() / SAMPLE_RATE_HZ
        val signal =
            5.0 + kotlin.math.sin(
                base * Math.PI * 2
            ) * 0.5
        val jitter =
            Random.nextDouble(
                -0.2,
                0.2
            )
        return (signal + jitter).coerceAtLeast(
            0.0
        )
    }

    companion object {
        private const val SAMPLE_RATE_HZ =
            128.0
    }
}
