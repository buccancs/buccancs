package com.buccancs.data.sensor.connector.shimmer

import android.net.Uri
import android.util.Log
import com.buccancs.core.result.DeviceCommandResult
import com.buccancs.core.result.Result
import com.buccancs.core.result.recover
import com.buccancs.core.result.resultOf
import com.buccancs.core.result.toResult
import com.buccancs.core.serialization.JsonConfig
import com.buccancs.data.sensor.SensorStreamClient
import com.buccancs.data.sensor.SensorStreamEmitter
import com.buccancs.data.sensor.connector.simulated.BaseSimulatedConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import com.buccancs.domain.model.ShimmerDeviceCandidate
import com.buccancs.domain.model.ShimmerSettings
import com.buccancs.domain.repository.ShimmerSettingsRepository
import com.buccancs.hardware.shimmer.ShimmerHardwareClient
import com.buccancs.hardware.shimmer.ShimmerHardwareDevice
import com.buccancs.hardware.shimmer.ShimmerHardwareSettings
import com.buccancs.hardware.shimmer.ShimmerNotice
import com.buccancs.hardware.shimmer.ShimmerSample
import com.buccancs.hardware.shimmer.ShimmerStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets
import java.security.DigestOutputStream
import java.security.MessageDigest
import java.util.Locale
import kotlin.math.absoluteValue
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant

internal class ShimmerSensorConnector(
    private val appScope: CoroutineScope,
    private val hardwareClient: ShimmerHardwareClient,
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
    private val logTag =
        "ShimmerConnector-${deviceId.value}"

    private var streamingAnchor: RecordingSessionAnchor? =
        null
    private var samplesSeen: Long =
        0
    private var lastSampleTimestamp: Instant? =
        null
    private var streamEmitter: SensorStreamEmitter? =
        null

    private val writerMutex =
        Mutex()
    private var localWriter: BufferedWriter? =
        null
    private var localDigest: MessageDigest? =
        null
    private var localDigestStream: DigestOutputStream? =
        null
    private var localFile: File? =
        null
    private var localSessionId: String? =
        null
    private var completedArtifact: SessionArtifact? =
        null
    private var completedArtifactSessionId: String? =
        null

    private val json =
        JsonConfig.standard
    private var currentSettings: ShimmerSettings =
        shimmerSettingsRepository.settings.value
    private var discoveredCandidates: List<ShimmerDeviceCandidate> =
        emptyList()
    private var hardwareInventory: List<ShimmerHardwareDevice> =
        emptyList()
    private var hardwareStatus: ShimmerStatus =
        ShimmerStatus.Idle
    private var targetMac: String? =
        currentSettings.targetMacAddress?.normalizeMac()

    init {
        appScope.launch {
            shimmerSettingsRepository.settings.collect { settings ->
                currentSettings =
                    settings
                updateDeviceMetadata()
                applySettingsToConnectedDevice()
            }
        }
        appScope.launch {
            hardwareClient.devices.collect { devices ->
                hardwareInventory =
                    devices
            }
        }
        appScope.launch {
            hardwareClient.status.collect { status ->
                hardwareStatus =
                    status
                handleHardwareStatus(
                    status
                )
            }
        }
        appScope.launch {
            hardwareClient.samples.collect { sample ->
                handleHardwareSample(
                    sample
                )
            }
        }
        appScope.launch {
            hardwareClient.notices.collect { notice ->
                logHardwareNotice(
                    notice
                )
            }
        }
    }

    private fun logHardwareNotice(
        notice: ShimmerNotice
    ) {
        val tag =
            when (notice.category) {
                ShimmerNotice.Category.Info -> "info"
                ShimmerNotice.Category.Warning -> "warn"
                ShimmerNotice.Category.Error -> "error"
            }
        Log.i(
            logTag,
            "[hardware:$tag] ${notice.message}"
        )
    }

    override suspend fun refreshInventory() {
        if (isSimulationMode) {
            super.refreshInventory()
            return
        }

        hardwareClient.refreshBondedDevices()
        if (ENABLE_BLE_SCANNING) {
            hardwareClient.scan(
                DEFAULT_SCAN_DURATION
            )
        }

        val candidates =
            hardwareInventory.map { it.toCandidate() }
        discoveredCandidates =
            candidates
        updateDisplayName(
            candidates.firstOrNull()
        )

        if (currentSettings.targetMacAddress.isNullOrBlank() && candidates.isNotEmpty()) {
            shimmerSettingsRepository.setTargetMac(
                candidates.first().mac
            )
        }
        updateDeviceMetadata(
            candidates
        )
    }

    private fun updateDisplayName(
        candidate: ShimmerDeviceCandidate?
    ) {
        deviceState.update { current ->
            val connection =
                current.connectionStatus
            val normalizedStatus =
                if (connection is ConnectionStatus.Connected) connection else ConnectionStatus.Disconnected
            val attributes =
                current.attributes.toMutableMap()
            if (candidate != null) {
                attributes[ATTR_LAST_BONDED_MAC] =
                    candidate.mac
                attributes[ATTR_LAST_BONDED_NAME] =
                    candidate.name
                        ?: "Shimmer"
            }
            current.copy(
                displayName = candidate?.name
                    ?: current.displayName,
                connectionStatus = normalizedStatus,
                isSimulated = false,
                attributes = attributes.toMap()
            )
        }
    }

    private fun ShimmerHardwareDevice.toCandidate(): ShimmerDeviceCandidate =
        ShimmerDeviceCandidate(
            mac = macAddress.normalizeMac(),
            name = name,
            rssi = rssi
        )

    private fun updateDeviceMetadata(
        candidates: List<ShimmerDeviceCandidate>? = null
    ) {
        val candidateList =
            candidates
                ?: discoveredCandidates
        val encoded =
            runCatching {
                json.encodeToString(
                    candidateList
                )
            }.getOrDefault(
                "[]"
            )
        deviceState.update { current ->
            val attributes =
                current.attributes.toMutableMap()
            attributes[ATTR_AVAILABLE_DEVICES] =
                encoded
            attributes[ATTR_SELECTED_MAC] =
                currentSettings.targetMacAddress?.normalizeMac()
                    .orEmpty()
            attributes[ATTR_GSR_RANGE] =
                currentSettings.gsrRangeIndex.toString()
            attributes[ATTR_SAMPLE_RATE] =
                currentSettings.sampleRateHz.toString()
            current.copy(
                attributes = attributes.toMap()
            )
        }
    }

    private fun handleHardwareStatus(
        status: ShimmerStatus
    ) {
        when (status) {
            ShimmerStatus.Idle -> {
                statusState.value =
                    emptyList()
                deviceState.update {
                    it.copy(
                        connectionStatus = ConnectionStatus.Disconnected,
                        isSimulated = false
                    )
                }
            }

            is ShimmerStatus.Connecting -> {
                status.macAddress?.let {
                    targetMac =
                        it.normalizeMac()
                }
                statusState.value =
                    emptyList()
                deviceState.update {
                    it.copy(
                        connectionStatus = ConnectionStatus.Connecting,
                        isSimulated = false
                    )
                }
            }

            is ShimmerStatus.Connected -> {
                status.macAddress?.let {
                    targetMac =
                        it.normalizeMac()
                }
                val since =
                    Instant.fromEpochMilliseconds(
                        status.sinceEpochMs
                    )
                deviceState.update {
                    it.copy(
                        connectionStatus = ConnectionStatus.Connected(
                            since = since,
                            batteryPercent = null,
                            rssiDbm = null
                        ),
                        isSimulated = false
                    )
                }
            }

            is ShimmerStatus.Streaming -> {
                status.macAddress?.let {
                    targetMac =
                        it.normalizeMac()
                }
                val since =
                    Instant.fromEpochMilliseconds(
                        status.sinceEpochMs
                    )
                deviceState.update {
                    it.copy(
                        connectionStatus = ConnectionStatus.Connected(
                            since = since,
                            batteryPercent = null,
                            rssiDbm = null
                        ),
                        isSimulated = false
                    )
                }
            }

            is ShimmerStatus.Error -> {
                Log.e(
                    logTag,
                    "Hardware error for ${status.macAddress ?: "unknown"}: ${status.message} (recoverable=${status.recoverable})"
                )
                statusState.value =
                    emptyList()
                deviceState.update {
                    it.copy(
                        connectionStatus = ConnectionStatus.Disconnected,
                        isSimulated = false
                    )
                }
            }
        }
    }

    private fun handleHardwareSample(
        sample: ShimmerSample
    ) {
        val conductance =
            sample.conductanceSiemens
        val resistance =
            sample.resistanceOhms
                ?: conductance?.let { if (it > 0.0) 1_000_000.0 / it else null }
        val timestamp =
            sample.timestampEpochMs
        val instant =
            Instant.fromEpochMilliseconds(
                timestamp
            )

        lastSampleTimestamp =
            instant
        samplesSeen += 1

        statusState.value =
            listOf(
                SensorStreamStatus(
                    deviceId = deviceId,
                    streamType = SensorStreamType.GSR,
                    sampleRateHz = currentSettings.sampleRateHz,
                    frameRateFps = null,
                    lastSampleTimestamp = instant,
                    bufferedDurationSeconds = 0.0,
                    isStreaming = true,
                    isSimulated = false
                )
            )

        persistLocalSample(
            timestamp,
            conductance,
            resistance
        )

        streamEmitter?.let { emitter ->
            val payload =
                buildMap<String, Double> {
                    conductance?.let {
                        put(
                            "conductance_microsiemens",
                            it
                        )
                    }
                    resistance?.let {
                        put(
                            "resistance_ohms",
                            it
                        )
                    }
                }
            if (payload.isNotEmpty()) {
                appScope.launch(
                    Dispatchers.IO
                ) {
                    emitter.emit(
                        timestamp,
                        payload
                    )
                }
            }
        }
    }

    private fun applySettingsToConnectedDevice() {
        val settings =
            ShimmerHardwareSettings(
                gsrRangeIndex = currentSettings.gsrRangeIndex,
                sampleRateHz = currentSettings.sampleRateHz,
                firmwarePreset = null
            )
        appScope.launch {
            runCatching {
                hardwareClient.applySettings(
                    settings
                )
            }
                .onFailure { error ->
                    Log.w(
                        logTag,
                        "Failed to apply Shimmer settings: ${error.message}",
                        error
                    )
                }
        }
    }

    override suspend fun applySimulation(
        enabled: Boolean
    ) {
        if (enabled) {
            performDisconnect()
        }
        super.applySimulation(
            enabled
        )
    }

    override suspend fun connect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.connect()
        }

        return connectHardware()
            .map { DeviceCommandResult.Accepted }
            .recover { error ->
                Log.e(
                    logTag,
                    "Hardware connection failed: ${error.message}",
                    error.cause
                )
                DeviceCommandResult.Failed(
                    error.toException()
                )
            }
            .getOrThrow()
    }

    private suspend fun connectHardware(): Result<Unit> {
        val mac =
            selectTargetMac()
                .getOrElse { error ->
                    return Result.Failure(
                        error
                    )
                }

        shimmerSettingsRepository.setTargetMac(
            mac
        )
        targetMac =
            mac
        updateDeviceMetadata()

        return resultOf {
            hardwareClient.connect(
                mac
            )
        }
    }

    private fun selectTargetMac(): Result<String> {
        val desired =
            currentSettings.targetMacAddress?.normalizeMac()
        val mac =
            desired
                ?: discoveredCandidates.firstOrNull()?.mac
                ?: hardwareInventory.firstOrNull()?.macAddress?.normalizeMac()
                ?: deviceState.value.attributes[ATTR_LAST_BONDED_MAC]?.normalizeMac()

        return mac.toResult(
            "No Shimmer device available. Use Scan to discover devices."
        )
    }

    override suspend fun disconnect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.disconnect()
        }

        return performDisconnect()
            .map { DeviceCommandResult.Accepted }
            .recover { error ->
                Log.e(
                    logTag,
                    "Disconnect failed: ${error.message}",
                    error.cause
                )
                DeviceCommandResult.Failed(
                    error.toException()
                )
            }
            .getOrThrow()
    }

    private suspend fun performDisconnect(): Result<Unit> =
        resultOf {
            hardwareClient.stopStreaming()
            hardwareClient.disconnect()
            streamingAnchor =
                null
            samplesSeen =
                0
            lastSampleTimestamp =
                null
            statusState.value =
                emptyList()
            runCatching { streamEmitter?.close() }
            streamEmitter =
                null
            abortLocalRecording(
                deleteFile = true
            )
        }

    override suspend fun startStreaming(
        anchor: RecordingSessionAnchor
    ): DeviceCommandResult {
        if (isSimulationMode) {
            return super.startStreaming(
                anchor
            )
        }

        val status =
            hardwareStatus
        if (status !is ShimmerStatus.Connected && status !is ShimmerStatus.Streaming) {
            return DeviceCommandResult.Rejected(
                "Shimmer device not connected."
            )
        }

        return performStartStreaming(
            anchor
        )
            .map { DeviceCommandResult.Accepted }
            .recover { error ->
                Log.e(
                    logTag,
                    "Start streaming failed: ${error.message}",
                    error.cause
                )
                abortLocalRecording(
                    deleteFile = true
                )
                DeviceCommandResult.Failed(
                    error.toException()
                )
            }
            .getOrThrow()
    }

    private suspend fun performStartStreaming(
        anchor: RecordingSessionAnchor
    ): Result<Unit> =
        resultOf {
            streamingAnchor =
                anchor
            samplesSeen =
                0
            lastSampleTimestamp =
                null
            prepareLocalRecording(
                anchor.sessionId
            )

            streamEmitter?.close()
            streamEmitter =
                streamClient.openStream(
                    sessionId = anchor.sessionId,
                    deviceId = deviceId,
                    streamId = STREAM_ID,
                    sampleRateHz = currentSettings.sampleRateHz
                )

            hardwareClient.startStreaming()
        }

    override suspend fun stopStreaming(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.stopStreaming()
        }

        return performStopStreaming()
            .map { DeviceCommandResult.Accepted }
            .recover { error ->
                Log.e(
                    logTag,
                    "Stop streaming failed: ${error.message}",
                    error.cause
                )
                abortLocalRecording(
                    deleteFile = true
                )
                DeviceCommandResult.Failed(
                    error.toException()
                )
            }
            .getOrThrow()
    }

    private suspend fun performStopStreaming(): Result<Unit> =
        resultOf {
            val sessionId =
                streamingAnchor?.sessionId
            hardwareClient.stopStreaming()
            streamingAnchor =
                null
            samplesSeen =
                0
            lastSampleTimestamp =
                null
            statusState.value =
                emptyList()
            runCatching { streamEmitter?.close() }
            streamEmitter =
                null
            finalizeLocalRecording()?.let { artifact ->
                if (sessionId != null) {
                    completedArtifact =
                        artifact
                    completedArtifactSessionId =
                        sessionId
                }
            }
        }

    override suspend fun collectArtifacts(
        sessionId: String
    ): List<SessionArtifact> {
        if (isSimulationMode) {
            return super.collectArtifacts(
                sessionId
            )
        }
        val artifact =
            completedArtifact
        return if (artifact != null && sessionId == completedArtifactSessionId) {
            completedArtifact =
                null
            completedArtifactSessionId =
                null
            listOf(
                artifact
            )
        } else {
            emptyList()
        }
    }

    override fun streamIntervalMs(): Long =
        250L

    override fun simulatedBatteryPercent(
        device: SensorDevice
    ): Int {
        val baseline =
            90 - (device.id.value.hashCode().absoluteValue % 12)
        return baseline.coerceIn(
            40,
            98
        )
    }

    override fun simulatedRssi(
        device: SensorDevice
    ): Int =
        -45

    override fun sampleStatuses(
        timestamp: Instant,
        frameCounter: Long,
        anchor: RecordingSessionAnchor
    ): List<SensorStreamStatus> {
        val streamType =
            SensorStreamType.GSR
        val buffered =
            simulatedBufferedSeconds(
                streamType = streamType,
                baseVideo = 0.0,
                baseSample = 0.5,
                randomizer = {
                    randomJitter(
                        frameCounter
                    )
                }
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

    private suspend fun prepareLocalRecording(
        sessionId: String
    ) {
        abortLocalRecording(
            deleteFile = true
        )
        writerMutex.withLock {
            val file =
                recordingStorage.createArtifactFile(
                    sessionId = sessionId,
                    deviceId = deviceId.value,
                    streamType = STREAM_ID,
                    timestampEpochMs = System.currentTimeMillis(),
                    extension = "csv"
                )
            val digest =
                MessageDigest.getInstance(
                    "SHA-256"
                )
            val stream =
                DigestOutputStream(
                    FileOutputStream(
                        file
                    ),
                    digest
                )
            val writer =
                BufferedWriter(
                    OutputStreamWriter(
                        stream,
                        StandardCharsets.UTF_8
                    )
                )
            writer.write(
                "timestamp_ms,conductance_microsiemens,resistance_ohms"
            )
            writer.newLine()
            writer.flush()
            localWriter =
                writer
            localDigest =
                digest
            localDigestStream =
                stream
            localFile =
                file
            localSessionId =
                sessionId
            completedArtifact =
                null
            completedArtifactSessionId =
                null
        }
    }

    private suspend fun finalizeLocalRecording(): SessionArtifact? {
        val file =
            localFile
        val sessionId =
            localSessionId
        val digest =
            localDigest
        if (file == null || sessionId == null || digest == null) {
            clearLocalRecording(
                deleteFile = true
            )
            return null
        }
        writerMutex.withLock {
            runCatching { localWriter?.flush() }
            runCatching { localWriter?.close() }
            localWriter =
                null
        }
        runCatching { localDigestStream?.close() }
        localDigestStream =
            null
        val checksum =
            digest.digest()
        localDigest =
            null
        localFile =
            null
        localSessionId =
            null
        return SessionArtifact(
            deviceId = deviceId,
            streamType = SensorStreamType.GSR,
            uri = Uri.fromFile(
                file
            ),
            file = file,
            mimeType = "text/csv",
            sizeBytes = file.length(),
            checksumSha256 = checksum
        )
    }

    private suspend fun abortLocalRecording(
        deleteFile: Boolean
    ) {
        clearLocalRecording(
            deleteFile
        )
    }

    private suspend fun clearLocalRecording(
        deleteFile: Boolean
    ) {
        writerMutex.withLock {
            runCatching { localWriter?.flush() }
            runCatching { localWriter?.close() }
            localWriter =
                null
        }
        runCatching { localDigestStream?.close() }
        localDigestStream =
            null
        localDigest =
            null
        val file =
            localFile
        localFile =
            null
        localSessionId =
            null
        if (deleteFile) {
            file?.delete()
        }
    }

    private fun persistLocalSample(
        timestampEpochMs: Long,
        conductance: Double?,
        resistance: Double?
    ) {
        if (localWriter == null) return
        val conductanceStr =
            conductance?.let {
                String.format(
                    Locale.US,
                    "%.6f",
                    it
                )
            }
                ?: ""
        val resistanceStr =
            resistance?.let {
                String.format(
                    Locale.US,
                    "%.2f",
                    it
                )
            }
                ?: ""
        val line =
            buildString {
                append(
                    timestampEpochMs
                )
                append(
                    ','
                )
                append(
                    conductanceStr
                )
                append(
                    ','
                )
                append(
                    resistanceStr
                )
            }
        appScope.launch(
            Dispatchers.IO
        ) {
            writerMutex.withLock {
                val writer =
                    localWriter
                        ?: return@withLock
                runCatching {
                    writer.write(
                        line
                    )
                    writer.newLine()
                    writer.flush()
                }.onFailure { error ->
                    Log.w(
                        logTag,
                        "Failed to persist GSR sample: ${error.message}"
                    )
                }
            }
        }
    }

    private fun randomJitter(
        seed: Long
    ): Double {
        val hash =
            (seed + deviceId.value.hashCode()).absoluteValue % 1000
        return hash / 10_000.0
    }

    private fun String.normalizeMac(): String =
        uppercase(
            Locale.US
        )

    private companion object {
        private const val STREAM_ID =
            "gsr"
        private const val ATTR_AVAILABLE_DEVICES =
            "shimmer.candidates"
        private const val ATTR_SELECTED_MAC =
            "shimmer.selected"
        private const val ATTR_GSR_RANGE =
            "shimmer.gsr_range"
        private const val ATTR_SAMPLE_RATE =
            "shimmer.sample_rate"
        private const val ATTR_LAST_BONDED_MAC =
            "shimmer.last_bonded_mac"
        private const val ATTR_LAST_BONDED_NAME =
            "shimmer.last_bonded_name"
        private const val ENABLE_BLE_SCANNING =
            true
        private val DEFAULT_SCAN_DURATION =
            5.seconds
    }
}
