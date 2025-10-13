package com.buccancs.data.sensor.connector.shimmer

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.net.Uri
import com.buccancs.util.nowInstant
import com.buccancs.data.sensor.SensorStreamClient
import com.buccancs.data.sensor.SensorStreamEmitter
import com.buccancs.data.sensor.connector.simulated.BaseSimulatedConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceCommandResult
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import com.buccancs.domain.model.ShimmerDeviceCandidate
import com.buccancs.domain.model.ShimmerSettings
import com.buccancs.domain.repository.ShimmerSettingsRepository
import com.shimmerresearch.android.Shimmer
import com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid
import com.shimmerresearch.bluetooth.ShimmerBluetooth
import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE
import com.shimmerresearch.driver.CallbackObject
import com.shimmerresearch.driver.Configuration
import com.shimmerresearch.driver.FormatCluster
import com.shimmerresearch.driver.ObjectCluster
import com.shimmerresearch.driver.ShimmerDevice
import com.shimmerresearch.exceptions.ShimmerException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets
import java.security.DigestOutputStream
import java.security.MessageDigest
import java.util.Locale
import java.util.LinkedHashMap
import kotlin.math.absoluteValue

internal class ShimmerSensorConnector(
    private val appScope: CoroutineScope,
    private val context: Context,
    private val bluetoothAdapter: BluetoothAdapter?,
    artifactFactory: SimulatedArtifactFactory,
    private val streamClient: SensorStreamClient,
    private val recordingStorage: RecordingStorage,
    private val shimmerSettingsRepository: ShimmerSettingsRepository,
    initialDevice: SensorDevice
) : BaseSimulatedConnector(
    scope = appScope,
    artifactFactory = artifactFactory,
    initialDevice = initialDevice
) {
    private val logTag = "ShimmerConnector-${deviceId.value}"
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
    private var targetMac: String? = shimmerSettingsRepository.settings.value.targetMacAddress?.normalizeMac()
    private var streamingAnchor: RecordingSessionAnchor? = null
    private var samplesSeen = 0L
    private var lastSampleTimestamp: Instant? = null
    private var streamEmitter: SensorStreamEmitter? = null
    private val writerMutex = Mutex()
    private var localWriter: BufferedWriter? = null
    private var localDigest: MessageDigest? = null
    private var localDigestStream: DigestOutputStream? = null
    private var localFile: File? = null
    private var localSessionId: String? = null
    private var completedArtifact: SessionArtifact? = null
    private var completedArtifactSessionId: String? = null
    private val json = Json { ignoreUnknownKeys = true }
    private var currentSettings: ShimmerSettings = shimmerSettingsRepository.settings.value
    private var discoveredCandidates: List<ShimmerDeviceCandidate> = emptyList()

    init {
        appScope.launch {
            shimmerSettingsRepository.settings.collect { settings ->
                currentSettings = settings
                updateDeviceMetadata()
                applySettingsToConnectedDevice()
            }
        }
    }
    override suspend fun refreshInventory() {
        if (isSimulationMode) {
            super.refreshInventory()
            return
        }
        val adapter = bluetoothAdapter ?: return
        val bonded = bondedShimmerDevices(adapter)
        updateDisplayName(bonded.firstOrNull())
        val scanned = scanForBleDevices(adapter)
        val bondedCandidates = bonded.map {
            ShimmerDeviceCandidate(
                mac = it.address.normalizeMac(),
                name = it.name,
                rssi = null
            )
        }
        val merged = mergeCandidates(bondedCandidates, scanned)
        discoveredCandidates = merged
        if (currentSettings.targetMacAddress.isNullOrBlank() && merged.isNotEmpty()) {
            shimmerSettingsRepository.setTargetMac(merged.first().mac)
        }
        updateDeviceMetadata(merged)
    }

    private fun updateDisplayName(device: BluetoothDevice?) {
        deviceState.update { current ->
            val status = if (current.connectionStatus is ConnectionStatus.Connected) {
                current.connectionStatus
            } else {
                ConnectionStatus.Disconnected
            }
            val attributes = current.attributes.toMutableMap()
            if (device != null) {
                attributes[ATTR_LAST_BONDED_MAC] = device.address.normalizeMac()
                attributes[ATTR_LAST_BONDED_NAME] = device.name ?: "Shimmer"
            }
            current.copy(
                displayName = device?.name ?: current.displayName,
                connectionStatus = status,
                isSimulated = false,
                attributes = attributes.toMap()
            )
        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun scanForBleDevices(adapter: BluetoothAdapter): List<ShimmerDeviceCandidate> =
        withContext(Dispatchers.Default) {
            val scanner = adapter.bluetoothLeScanner ?: return@withContext emptyList()
            val discovered = mutableMapOf<String, ShimmerDeviceCandidate>()
            val callback = object : ScanCallback() {
                override fun onScanResult(callbackType: Int, result: ScanResult) {
                    super.onScanResult(callbackType, result)
                    val device = result.device ?: return
                    val mac = device.address?.normalizeMac() ?: return
                    val name = device.name ?: result.scanRecord?.deviceName
                    val candidate = ShimmerDeviceCandidate(mac = mac, name = name, rssi = result.rssi)
                    discovered[mac] = candidate
                }
            }
            try {
                scanner.startScan(callback)
            } catch (security: SecurityException) {
                Log.w(logTag, "Missing permission to scan for Shimmer devices: ${security.message}")
                return@withContext emptyList()
            } catch (state: IllegalStateException) {
                Log.w(logTag, "Unable to start Shimmer scan: ${state.message}")
                return@withContext emptyList()
            }
            try {
                delay(SCAN_DURATION_MS)
            } finally {
                runCatching { scanner.stopScan(callback) }
            }
            discovered.values.toList()
        }

    private fun mergeCandidates(
        primary: List<ShimmerDeviceCandidate>,
        secondary: List<ShimmerDeviceCandidate>
    ): List<ShimmerDeviceCandidate> {
        val combined = LinkedHashMap<String, ShimmerDeviceCandidate>()
        (primary + secondary).forEach { candidate ->
            val mac = candidate.mac.normalizeMac()
            val existing = combined[mac]
            if (existing == null) {
                combined[mac] = candidate.copy(mac = mac)
            } else {
                combined[mac] = ShimmerDeviceCandidate(
                    mac = mac,
                    name = existing.name ?: candidate.name,
                    rssi = candidate.rssi ?: existing.rssi
                )
            }
        }
        return combined.values.sortedWith(
            compareBy(
                { it.name?.lowercase(Locale.US) ?: it.mac.lowercase(Locale.US) },
                { it.mac }
            )
        )
    }

    private fun updateDeviceMetadata(candidates: List<ShimmerDeviceCandidate>? = null) {
        val candidateList = candidates ?: discoveredCandidates
        val encodedCandidates = runCatching { json.encodeToString(candidateList) }.getOrDefault("[]")
        deviceState.update { current ->
            val attributes = current.attributes.toMutableMap()
            attributes[ATTR_AVAILABLE_DEVICES] = encodedCandidates
            attributes[ATTR_SELECTED_MAC] = currentSettings.targetMacAddress?.normalizeMac().orEmpty()
            attributes[ATTR_GSR_RANGE] = currentSettings.gsrRangeIndex.toString()
            attributes[ATTR_SAMPLE_RATE] = currentSettings.sampleRateHz.toString()
            current.copy(attributes = attributes.toMap())
        }
    }

    private fun applySettingsToConnectedDevice() {
        val shimmer = shimmerDevice as? ShimmerBluetooth ?: return
        appScope.launch(Dispatchers.IO) {
            runCatching {
                val rangeIndex = currentSettings.gsrRangeIndex.coerceIn(0, MAX_GSR_RANGE_INDEX)
                shimmer.setGSRRange(rangeIndex)
                shimmer.setSamplingRateShimmer(currentSettings.sampleRateHz)
                shimmer.writeConfigBytes()
            }.onFailure { ex ->
                Log.w(logTag, "Failed to apply Shimmer settings: ${ex.message}")
            }
        }
    }

    private fun String.normalizeMac(): String = uppercase(Locale.US)

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
        val desiredMac = currentSettings.targetMacAddress?.normalizeMac()
        val mac = desiredMac
            ?: discoveredCandidates.firstOrNull()?.mac
            ?: deviceState.value.attributes[ATTR_LAST_BONDED_MAC]?.normalizeMac()
            ?: bondedShimmerDevices(adapter).firstOrNull()?.address?.normalizeMac()
            ?: return DeviceCommandResult.Rejected("No Shimmer device available. Use Scan to discover devices.")
        shimmerSettingsRepository.setTargetMac(mac)
        targetMac = mac
        updateDeviceMetadata()
        val name = discoveredCandidates.firstOrNull { it.mac == mac }?.name
            ?: deviceState.value.attributes[ATTR_LAST_BONDED_NAME]
            ?: deviceState.value.displayName
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
                prepareLocalRecording(anchor.sessionId)
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
                abortLocalRecording(true)
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
                val sessionId = streamingAnchor?.sessionId
                stopHardwareStreamingInternal()
                streamingAnchor = null
                samplesSeen = 0
                lastSampleTimestamp = null
                statusState.value = emptyList()
                runCatching { streamEmitter?.close() }
                streamEmitter = null
                finalizeLocalRecording()?.let { artifact ->
                    if (sessionId != null) {
                        completedArtifact = artifact
                        completedArtifactSessionId = sessionId
                    }
                }
                DeviceCommandResult.Accepted
            } catch (t: Throwable) {
                Log.e(logTag, "Failed to stop Shimmer streaming", t)
                abortLocalRecording(true)
                DeviceCommandResult.Failed(t)
            }
        }
    }

    override suspend fun collectArtifacts(sessionId: String): List<SessionArtifact> {
        if (isSimulationMode) {
            return super.collectArtifacts(sessionId)
        }
        val artifact = completedArtifact
        return if (artifact != null && sessionId == completedArtifactSessionId) {
            completedArtifact = null
            completedArtifactSessionId = null
            listOf(artifact)
        } else {
            emptyList()
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
        val normalizedMac = mac.normalizeMac()
        shimmerDevice = manager.getShimmerDeviceBtConnectedFromMac(normalizedMac)
        targetMac = normalizedMac
        if (currentSettings.targetMacAddress.isNullOrBlank()) {
            appScope.launch { shimmerSettingsRepository.setTargetMac(normalizedMac) }
        }
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
        updateDeviceMetadata()
        applySettingsToConnectedDevice()
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
        val sessionId = streamingAnchor?.sessionId
        streamingAnchor = null
        if (localWriter != null || localFile != null) {
            appScope.launch {
                finalizeLocalRecording()?.let { artifact ->
                    if (sessionId != null) {
                        completedArtifact = artifact
                        completedArtifactSessionId = sessionId
                    }
                }
            }
        }
        shimmerDevice = null
        samplesSeen = 0
        lastSampleTimestamp = null
        statusState.value = emptyList()
        deviceState.update { it.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false) }
        updateDeviceMetadata()
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
        persistLocalSample(
            timestampEpochMs = sampleInstant.toEpochMilliseconds(),
            conductance = conductance,
            resistance = resistance
        )
        streamEmitter?.let { emitter ->
            appScope.launch {
                emitter.emit(
                    timestampEpochMs = sampleInstant.toEpochMilliseconds(),
                    values = buildMap {
                        if (conductance != null) put("conductance_microsiemens", conductance)
                        if (resistance != null) put("resistance_ohms", resistance)
                    }
                )
            }
        }
    }

    private suspend fun prepareLocalRecording(sessionId: String) {
        abortLocalRecording(deleteFile = true)
        withContext(Dispatchers.IO) {
            writerMutex.withLock {
                val file = recordingStorage.createArtifactFile(
                    sessionId = sessionId,
                    deviceId = deviceId.value,
                    streamType = "gsr",
                    timestampEpochMs = System.currentTimeMillis(),
                    extension = "csv"
                )
                val digest = MessageDigest.getInstance("SHA-256")
                val stream = DigestOutputStream(FileOutputStream(file), digest)
                val writer = BufferedWriter(
                    OutputStreamWriter(stream, StandardCharsets.UTF_8)
                )
                writer.write("timestamp_ms,conductance_microsiemens,resistance_ohms")
                writer.newLine()
                writer.flush()
                localWriter = writer
                localDigest = digest
                localDigestStream = stream
                localFile = file
                localSessionId = sessionId
                completedArtifact = null
                completedArtifactSessionId = null
            }
        }
    }

    private suspend fun finalizeLocalRecording(): SessionArtifact? = withContext(Dispatchers.IO) {
        val file = localFile
        val sessionId = localSessionId
        val digest = localDigest
        if (file == null || sessionId == null || digest == null) {
            clearLocalRecording(deleteFile = true)
            return@withContext null
        }
        writerMutex.withLock {
            runCatching { localWriter?.flush() }
            runCatching { localWriter?.close() }
            localWriter = null
        }
        runCatching { localDigestStream?.close() }
        localDigestStream = null
        val checksum = digest.digest()
        localDigest = null
        localFile = null
        localSessionId = null
        SessionArtifact(
            deviceId = deviceId,
            streamType = SensorStreamType.GSR,
            uri = Uri.fromFile(file),
            file = file,
            mimeType = "text/csv",
            sizeBytes = file.length(),
            checksumSha256 = checksum
        )
    }

    private suspend fun abortLocalRecording(deleteFile: Boolean) {
        withContext(Dispatchers.IO) {
            clearLocalRecording(deleteFile)
        }
    }

    private suspend fun clearLocalRecording(deleteFile: Boolean) {
        writerMutex.withLock {
            runCatching { localWriter?.flush() }
            runCatching { localWriter?.close() }
            localWriter = null
        }
        runCatching { localDigestStream?.close() }
        localDigestStream = null
        localDigest = null
        val file = localFile
        localFile = null
        localSessionId = null
        if (deleteFile) {
            file?.delete()
        }
    }

    private fun persistLocalSample(
        timestampEpochMs: Long,
        conductance: Double?,
        resistance: Double?
    ) {
        if (localWriter == null) {
            return
        }
        val conductanceStr = conductance?.let { String.format(Locale.US, "%.6f", it) } ?: ""
        val resistanceStr = resistance?.let { String.format(Locale.US, "%.2f", it) } ?: ""
        val line = buildString {
            append(timestampEpochMs)
            append(',')
            append(conductanceStr)
            append(',')
            append(resistanceStr)
        }
        appScope.launch(Dispatchers.IO) {
            writerMutex.withLock {
                val writer = localWriter ?: return@withLock
                runCatching {
                    writer.write(line)
                    writer.newLine()
                    writer.flush()
                }.onFailure { ex ->
                    Log.w(logTag, "Failed to persist local GSR sample: ${ex.message}")
                }
            }
        }
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
        abortLocalRecording(true)
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
        private const val SCAN_DURATION_MS = 6_000L
        private const val MAX_GSR_RANGE_INDEX = 4
        private const val ATTR_AVAILABLE_DEVICES = "shimmer.candidates"
        private const val ATTR_SELECTED_MAC = "shimmer.selected"
        private const val ATTR_GSR_RANGE = "shimmer.gsr_range"
        private const val ATTR_SAMPLE_RATE = "shimmer.sample_rate"
        private const val ATTR_LAST_BONDED_MAC = "shimmer.last_bonded_mac"
        private const val ATTR_LAST_BONDED_NAME = "shimmer.last_bonded_name"
    }
}




