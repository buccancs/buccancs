package com.buccancs.data.sensor.connector.shimmer

import com.buccancs.util.nowInstant
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.buccancs.data.sensor.SensorStreamClient
import com.buccancs.data.sensor.SensorStreamEmitter
import com.buccancs.data.sensor.connector.simulated.BaseSimulatedConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceCommandResult
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.shimmerresearch.android.Shimmer
import com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid
import com.shimmerresearch.bluetooth.ShimmerBluetooth
import com.shimmerresearch.driver.CallbackObject
import com.shimmerresearch.driver.Configuration
import com.shimmerresearch.driver.FormatCluster
import com.shimmerresearch.driver.ObjectCluster
import com.shimmerresearch.driver.ShimmerDevice
import com.shimmerresearch.exceptions.ShimmerException
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.absoluteValue

@Singleton
internal class ShimmerSensorConnector @Inject constructor(
    @ApplicationScope scope: CoroutineScope,
    @ApplicationContext private val context: Context,
    private val bluetoothAdapter: BluetoothAdapter?,
    artifactFactory: SimulatedArtifactFactory,
    private val streamClient: SensorStreamClient
) : BaseSimulatedConnector(
    scope = scope,
    artifactFactory = artifactFactory,
    initialDevice = SensorDevice(
        id = DeviceId("shimmer-primary"),
        displayName = "Shimmer3 GSR",
        type = SensorDeviceType.SHIMMER_GSR,
        capabilities = setOf(SensorStreamType.GSR),
        connectionStatus = ConnectionStatus.Disconnected,
        isSimulated = false,
        attributes = emptyMap()
    )
) {
    private val logTag = "ShimmerConnector"
    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                ShimmerBluetooth.MSG_IDENTIFIER_STATE_CHANGE -> handleStateChange(msg.obj)
                ShimmerBluetooth.MSG_IDENTIFIER_DATA_PACKET -> if (msg.obj is ObjectCluster) {
                    handleDataPacket(msg.obj as ObjectCluster)
                }

                Shimmer.MESSAGE_TOAST -> {
                    val text = msg.data?.getString(Shimmer.TOAST)
                    if (!text.isNullOrBlank()) {
                        Log.i(logTag, text)
                    }
                }

                else -> super.handleMessage(msg)
            }
        }
    }
    private var bluetoothManager: ShimmerBluetoothManagerAndroid? = null
    private var shimmerDevice: ShimmerDevice? = null
    private var targetMac: String? = null
    private var streamingAnchor: RecordingSessionAnchor? = null
    private var samplesSeen = 0L
    private var lastSampleTimestamp: Instant? = null
    private var streamEmitter: SensorStreamEmitter? = null
    override suspend fun refreshInventory() {
        if (isSimulationMode) {
            super.refreshInventory()
            return
        }
        val adapter = bluetoothAdapter ?: return
        val bonded = bondedShimmerDevices(adapter)
        val device = bonded.firstOrNull()
        deviceState.update { current ->
            val attributes = if (device != null) {
                mapOf(
                    "mac" to device.address,
                    "name" to (device.name ?: "Shimmer")
                )
            } else {
                emptyMap()
            }
            val status = when (current.connectionStatus) {
                is ConnectionStatus.Connected -> current.connectionStatus
                else -> ConnectionStatus.Disconnected
            }
            current.copy(
                displayName = device?.name ?: "Shimmer3 GSR",
                attributes = attributes,
                connectionStatus = status,
                isSimulated = false
            )
        }
    }

    override suspend fun applySimulation(enabled: Boolean) {
        if (enabled) {
            disconnectHardware()
        }
        super.applySimulation(enabled)
    }

    override suspend fun connect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.connect()
        }
        val adapter = bluetoothAdapter ?: return DeviceCommandResult.Rejected("Bluetooth not available on this device.")
        if (!adapter.isEnabled) {
            return DeviceCommandResult.Rejected("Bluetooth is disabled.")
        }
        val mac = deviceState.value.attributes["mac"]
            ?: bondedShimmerDevices(adapter).firstOrNull()?.address
            ?: return DeviceCommandResult.Rejected("No paired Shimmer device found.")
        targetMac = mac
        val name = deviceState.value.attributes["name"] ?: deviceState.value.displayName
        return withContext(Dispatchers.Main) {
            try {
                val manager = ensureManager()
                deviceState.update { it.copy(connectionStatus = ConnectionStatus.Connecting, isSimulated = false) }
                manager.connectShimmerThroughBTAddress(mac, name, preferredBtType)
                DeviceCommandResult.Accepted
            } catch (t: Throwable) {
                Log.e(logTag, "Failed to initiate Shimmer connection", t)
                deviceState.update { it.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false) }
                DeviceCommandResult.Failed(t)
            }
        }
    }

    override suspend fun disconnect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.disconnect()
        }
        return withContext(Dispatchers.Main) {
            try {
                stopHardwareStreamingInternal()
                disconnectHardware()
                statusState.value = emptyList()
                DeviceCommandResult.Accepted
            } catch (t: Throwable) {
                Log.e(logTag, "Failed to disconnect Shimmer device", t)
                DeviceCommandResult.Failed(t)
            }
        }
    }

    override suspend fun startStreaming(anchor: RecordingSessionAnchor): DeviceCommandResult {
        if (isSimulationMode) {
            return super.startStreaming(anchor)
        }
        val device = shimmerDevice ?: return DeviceCommandResult.Rejected("Shimmer device not connected.")
        return withContext(Dispatchers.Main) {
            try {
                streamingAnchor = anchor
                samplesSeen = 0
                lastSampleTimestamp = null
                device.startStreaming()
                streamEmitter?.close()
                streamEmitter = streamClient.openStream(
                    sessionId = anchor.sessionId,
                    deviceId = deviceId,
                    streamId = STREAM_ID,
                    sampleRateHz = 128.0
                )
                DeviceCommandResult.Accepted
            } catch (t: Throwable) {
                Log.e(logTag, "Failed to start Shimmer streaming", t)
                DeviceCommandResult.Failed(t)
            }
        }
    }

    override suspend fun stopStreaming(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.stopStreaming()
        }
        return withContext(Dispatchers.Main) {
            try {
                stopHardwareStreamingInternal()
                streamingAnchor = null
                samplesSeen = 0
                lastSampleTimestamp = null
                statusState.value = emptyList()
                runCatching { streamEmitter?.close() }
                streamEmitter = null
                DeviceCommandResult.Accepted
            } catch (t: Throwable) {
                Log.e(logTag, "Failed to stop Shimmer streaming", t)
                DeviceCommandResult.Failed(t)
            }
        }
    }

    override fun streamIntervalMs(): Long = 250L
    override fun simulatedBatteryPercent(device: SensorDevice): Int? {
        val baseline = 90 - (device.id.value.hashCode().absoluteValue % 12)
        return baseline.coerceIn(40, 98)
    }

    override fun simulatedRssi(device: SensorDevice): Int? = -45
    override fun sampleStatuses(
        timestamp: Instant,
        frameCounter: Long,
        anchor: RecordingSessionAnchor
    ): List<SensorStreamStatus> {
        val streamType = SensorStreamType.GSR
        val buffered = simulatedBufferedSeconds(
            streamType = streamType,
            baseVideo = 0.0,
            baseSample = 0.5,
            randomizer = { randomJitter(frameCounter) }
        )
        return listOf(
            SensorStreamStatus(
                deviceId = deviceId,
                streamType = streamType,
                sampleRateHz = 128.0,
                frameRateFps = null,
                lastSampleTimestamp = timestamp,
                bufferedDurationSeconds = buffered,
                isStreaming = true,
                isSimulated = true
            )
        )
    }

    @SuppressLint("MissingPermission")
    private fun bondedShimmerDevices(adapter: BluetoothAdapter): List<BluetoothDevice> {
        return adapter.bondedDevices?.filter { device ->
            val name = device.name ?: return@filter false
            name.contains("shimmer", ignoreCase = true)
        } ?: emptyList()
    }

    private fun ensureManager(): ShimmerBluetoothManagerAndroid {
        val existing = bluetoothManager
        if (existing != null) {
            return existing
        }
        return ShimmerBluetoothManagerAndroid(context, handler).also {
            bluetoothManager = it
        }
    }

    private fun handleStateChange(payload: Any?) {
        val state: ShimmerBluetooth.BT_STATE
        val mac: String
        when (payload) {
            is ObjectCluster -> {
                state = payload.mState
                mac = payload.macAddress
            }

            is CallbackObject -> {
                state = payload.mState
                mac = payload.mBluetoothAddress ?: ""
            }

            else -> return
        }
        when (state) {
            ShimmerBluetooth.BT_STATE.CONNECTED -> onConnected(mac)
            ShimmerBluetooth.BT_STATE.CONNECTING -> onConnecting()
            ShimmerBluetooth.BT_STATE.DISCONNECTED -> onDisconnected()
            ShimmerBluetooth.BT_STATE.STREAMING,
            ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING -> onStreaming()

            ShimmerBluetooth.BT_STATE.SDLOGGING -> onLogging()
            else -> Unit
        }
    }

    private fun onConnecting() {
        deviceState.update { it.copy(connectionStatus = ConnectionStatus.Connecting, isSimulated = false) }
    }

    private fun onConnected(mac: String) {
        val manager = bluetoothManager ?: return
        shimmerDevice = manager.getShimmerDeviceBtConnectedFromMac(mac)
        deviceState.update {
            it.copy(
                connectionStatus = ConnectionStatus.Connected(
                    since = nowInstant(),
                    batteryPercent = null,
                    rssiDbm = null
                ),
                isSimulated = false
            )
        }
    }

    private fun onStreaming() {
        deviceState.update { current ->
            val existing = current.connectionStatus as? ConnectionStatus.Connected
            current.copy(
                connectionStatus = ConnectionStatus.Connected(
                    since = existing?.since ?: nowInstant(),
                    batteryPercent = existing?.batteryPercent,
                    rssiDbm = existing?.rssiDbm
                ),
                isSimulated = false
            )
        }
    }

    private fun onLogging() {
        onStreaming()
    }

    private fun onDisconnected() {
        shimmerDevice = null
        samplesSeen = 0
        lastSampleTimestamp = null
        statusState.value = emptyList()
        deviceState.update { it.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false) }
    }

    private fun handleDataPacket(cluster: ObjectCluster) {
        val now = nowInstant()
        lastSampleTimestamp = now
        samplesSeen += 1
        val sampleInstant = extractTimestamp(cluster) ?: now
        val status = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.GSR,
            sampleRateHz = 128.0,
            frameRateFps = null,
            lastSampleTimestamp = sampleInstant,
            bufferedDurationSeconds = 0.0,
            isStreaming = true,
            isSimulated = false
        )
        statusState.value = listOf(status)
        val conductance = extractConductance(cluster)
        val resistance = conductance?.let { if (it > 0) 1_000_000.0 / it else 0.0 }
        streamEmitter?.emit(
            timestampEpochMs = sampleInstant.toEpochMilliseconds(),
            values = buildMap {
                if (conductance != null) put("conductance_microsiemens", conductance)
                if (resistance != null) put("resistance_ohms", resistance)
            }
        )
    }

    private fun extractTimestamp(cluster: ObjectCluster): Instant? {
        val collections =
            cluster.getCollectionOfFormatClusters(Configuration.Shimmer3.ObjectClusterSensorName.TIMESTAMP)
        val calibrated = ObjectCluster.returnFormatCluster(collections, "CAL") as? FormatCluster
        val seconds = calibrated?.mData ?: return null
        val millis = (seconds * 1_000.0).toLong()
        return Instant.fromEpochMilliseconds(millis)
    }

    private suspend fun disconnectHardware() {
        withContext(Dispatchers.Main) {
            try {
                stopHardwareStreamingInternal()
            } catch (t: Throwable) {
                Log.w(logTag, "Error stopping Shimmer streaming during disconnect", t)
            }
            shimmerDevice?.let { device ->
                try {
                    (device as? ShimmerBluetooth)?.disconnect()
                } catch (t: Throwable) {
                    Log.w(logTag, "Error disconnecting Shimmer device", t)
                }
            }
            shimmerDevice = null
            bluetoothManager?.disconnectAllDevices()
        }
        runCatching { streamEmitter?.close() }
        streamEmitter = null
        deviceState.update { it.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false) }
    }

    private suspend fun stopHardwareStreamingInternal() {
        withContext(Dispatchers.Main) {
            shimmerDevice?.let { device ->
                if (device.isStreaming) {
                    try {
                        device.stopStreaming()
                    } catch (t: ShimmerException) {
                        Log.w(logTag, "Failed to stop Shimmer streaming cleanly", t)
                    }
                }
            }
        }
    }

    private fun randomJitter(seed: Long): Double {
        val hash = (seed + deviceId.value.hashCode()).absoluteValue % 1000
        return hash / 10_000.0
    }

    private fun extractConductance(cluster: ObjectCluster): Double? {
        val conductanceClusters = cluster.getCollectionOfFormatClusters(
            Configuration.Shimmer3.ObjectClusterSensorName.GSR_CONDUCTANCE
        )
        val calibrated = ObjectCluster.returnFormatCluster(conductanceClusters, "CAL") as? FormatCluster
        return calibrated?.mData
    }

    private companion object {
        private val preferredBtType = ShimmerBluetoothManagerAndroid.BT_TYPE.BLE
        private const val STREAM_ID = "gsr"
    }
}
