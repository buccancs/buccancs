package com.buccancs.data.sensor.connector.topdon

import android.net.Uri
import android.util.Log
import com.buccancs.core.result.DeviceCommandResult
import com.buccancs.core.result.Error
import com.buccancs.core.result.Result
import com.buccancs.core.result.recover
import com.buccancs.core.result.resultOf
import com.buccancs.data.sensor.connector.simulated.BaseSimulatedConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
import com.buccancs.data.sensor.topdon.InMemoryTopdonSettingsRepository
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.*
import com.buccancs.hardware.topdon.GainMode
import com.buccancs.hardware.topdon.Palette
import com.buccancs.hardware.topdon.TopdonHardwareSettings
import com.buccancs.hardware.topdon.TopdonNotice
import com.buccancs.hardware.topdon.TopdonStatus
import com.buccancs.hardware.topdon.TopdonStreamEvent
import com.buccancs.hardware.topdon.TopdonThermalClient as HardwareClient
import com.buccancs.hardware.topdon.TopdonPreviewFrame as HardwarePreviewFrame
import com.buccancs.hardware.topdon.TopdonTemperatureMetrics
import com.buccancs.util.nowInstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import java.io.File
import java.io.FileOutputStream
import java.security.MessageDigest
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.absoluteValue
import kotlin.time.Instant
import kotlin.collections.buildMap

private fun defaultTopdonDevice(): SensorDevice = SensorDevice(
    id = DeviceId("topdon-tc001"),
    displayName = "Topdon TC001",
    type = SensorDeviceType.TOPDON_TC001,
    capabilities = setOf(SensorStreamType.THERMAL_VIDEO, SensorStreamType.PREVIEW),
    connectionStatus = ConnectionStatus.Disconnected,
    isSimulated = false,
    attributes = mapOf("usb.vendorId" to "0x3426", "usb.productId" to "0x0001")
)

@Singleton
internal class TopdonThermalConnector @Inject constructor(
    @ApplicationScope private val appScope: CoroutineScope,
    private val hardwareClient: HardwareClient,
    private val recordingStorage: RecordingStorage,
    artifactFactory: SimulatedArtifactFactory,
    private val settingsRepository: InMemoryTopdonSettingsRepository,
    initialDevice: SensorDevice = defaultTopdonDevice()
) : BaseSimulatedConnector(
    scope = appScope,
    artifactFactory = artifactFactory,
    initialDevice = initialDevice
) {
    private val logTag = "TopdonConnector"

    private val previewFrameState = MutableStateFlow<TopdonPreviewFrame?>(null)
    val previewFrameFlow: StateFlow<TopdonPreviewFrame?> = previewFrameState.asStateFlow()

    private val previewRunningState = MutableStateFlow(false)
    val previewRunningFlow: StateFlow<Boolean> = previewRunningState.asStateFlow()

    private var streamingAnchor: RecordingSessionAnchor? = null
    private var currentSessionId: String? = null
    private var thermalFile: File? = null
    private var thermalStream: FileOutputStream? = null
    private var thermalDigest: MessageDigest? = null
    private var thermalBytes: Long = 0
    private var thermalFrameCount: Long = 0
    private var lastMetrics: TopdonTemperatureMetrics? = null

    private val pendingArtifacts = mutableListOf<Pair<String, SessionArtifact>>()
    private val writerLock = ReentrantLock()

    private var currentSettings: TopdonSettings = settingsRepository.settings.value

    init {
        appScope.launch {
            settingsRepository.settings.collect { settings ->
                currentSettings = settings
                applyHardwareSettings(settings)
            }
        }
        appScope.launch {
            hardwareClient.status.collect { status ->
                handleHardwareStatus(status)
            }
        }
        appScope.launch {
            hardwareClient.previewFrames.collect { frame ->
                handlePreviewFrame(frame)
            }
        }
        appScope.launch {
            hardwareClient.streamEvents.collect { event ->
                handleStreamEvent(event)
            }
        }
        appScope.launch {
            hardwareClient.notices.collect { notice ->
                logHardwareNotice(notice)
            }
        }
    }

    override suspend fun refreshInventory() {
        if (isSimulationMode) {
            super.refreshInventory()
            return
        }
        hardwareClient.refreshInventory()
    }

    override suspend fun applySimulation(enabled: Boolean) {
        if (enabled) {
            performDisconnect()
        }
        super.applySimulation(enabled)
    }

    override suspend fun connect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.connect()
        }
        return resultOf { hardwareClient.connect() }
            .map { DeviceCommandResult.Accepted }
            .recover { error ->
                Log.e(logTag, "Topdon connection failed: ${error.message}", error.cause)
                DeviceCommandResult.Failed(error.toException())
            }
            .getOrThrow()
    }

    override suspend fun disconnect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.disconnect()
        }
        return performDisconnect()
            .map { DeviceCommandResult.Accepted }
            .recover { error ->
                Log.e(logTag, "Topdon disconnect failed: ${error.message}", error.cause)
                DeviceCommandResult.Failed(error.toException())
            }
            .getOrThrow()
    }

    private suspend fun performDisconnect(): Result<Unit> = resultOf {
        runCatching { hardwareClient.stopStreaming() }
        runCatching { hardwareClient.stopPreview() }
        runCatching { hardwareClient.disconnect() }
        clearRecordingState(finalize = true)
        previewRunningState.value = false
    }

    override suspend fun startStreaming(anchor: RecordingSessionAnchor): DeviceCommandResult {
        if (isSimulationMode) {
            return super.startStreaming(anchor)
        }
        val status = hardwareClient.status.value
        if (status !is TopdonStatus.Connected && status !is TopdonStatus.Streaming) {
            return DeviceCommandResult.Rejected("Topdon camera not connected.")
        }
        return performStartStreaming(anchor)
            .map { DeviceCommandResult.Accepted }
            .recover { error ->
                Log.e(logTag, "Start thermal streaming failed: ${error.message}", error.cause)
                clearRecordingState(finalize = true)
                DeviceCommandResult.Failed(error.toException())
            }
            .getOrThrow()
    }

    private suspend fun performStartStreaming(anchor: RecordingSessionAnchor): Result<Unit> = resultOf {
        streamingAnchor = anchor
        prepareThermalRecording(anchor.sessionId)
        val request = com.buccancs.hardware.topdon.TopdonStreamRequest(
            sessionId = anchor.sessionId,
            destinationPath = thermalFile?.absolutePath ?: "",
            superSampling = currentSettings.superSampling.multiplier
        )
        hardwareClient.startStreaming(request)
    }

    override suspend fun stopStreaming(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.stopStreaming()
        }
        return resultOf {
            hardwareClient.stopStreaming()
            clearRecordingState(finalize = true)
        }.map { DeviceCommandResult.Accepted }
            .recover { error ->
                Log.e(logTag, "Stop thermal streaming failed: ${error.message}", error.cause)
                DeviceCommandResult.Failed(error.toException())
            }
            .getOrThrow()
    }

    suspend fun startPreview(): DeviceCommandResult {
        if (isSimulationMode) {
            previewRunningState.value = true
            return DeviceCommandResult.Accepted
        }
        return hardwareClient.startPreview()
            .map {
                previewRunningState.value = true
                DeviceCommandResult.Accepted
            }
            .recover { error ->
                Log.e(logTag, "Start preview failed: ${error.message}", error.cause)
                DeviceCommandResult.Failed(error.toException())
            }
            .getOrThrow()
    }

    suspend fun stopPreview(): DeviceCommandResult {
        if (isSimulationMode) {
            previewRunningState.value = false
            return DeviceCommandResult.Accepted
        }
        return hardwareClient.stopPreview()
            .map {
                previewRunningState.value = false
                DeviceCommandResult.Accepted
            }
            .recover { error ->
                Log.e(logTag, "Stop preview failed: ${error.message}", error.cause)
                DeviceCommandResult.Failed(error.toException())
            }
            .getOrThrow()
    }

    override suspend fun collectArtifacts(sessionId: String): List<SessionArtifact> {
        if (isSimulationMode) {
            return super.collectArtifacts(sessionId)
        }
        val artifacts = mutableListOf<SessionArtifact>()
        writerLock.withLock {
            val iterator = pendingArtifacts.iterator()
            while (iterator.hasNext()) {
                val (queuedSession, artifact) = iterator.next()
                if (queuedSession == sessionId) {
                    artifacts += artifact
                    iterator.remove()
                }
            }
        }
        return artifacts
    }

    private fun applyHardwareSettings(settings: TopdonSettings) {
        val hardwareSettings = TopdonHardwareSettings(
            palette = when (settings.palette) {
                TopdonPalette.GRAYSCALE -> Palette.Gray
                TopdonPalette.IRONBOW -> Palette.Ironbow
                TopdonPalette.RAINBOW -> Palette.Rainbow
            },
            emissivity = null,
            distanceMeters = null,
            autoShutter = settings.autoConnectOnAttach,
            gainMode = GainMode.Auto,
            previewFpsLimit = settings.previewFpsLimit,
            superSamplingFactor = settings.superSampling.multiplier
        )
        appScope.launch {
            runCatching { hardwareClient.applySettings(hardwareSettings) }
                .onFailure { error ->
                    Log.w(logTag, "Failed to push Topdon settings: ${error.message}")
                }
        }
    }

    private fun handleHardwareStatus(status: TopdonStatus) {
        when (status) {
            TopdonStatus.Idle -> {
                previewRunningState.value = false
                statusState.value = emptyList()
                deviceState.update { device ->
                    device.copy(
                        connectionStatus = ConnectionStatus.Disconnected,
                        isSimulated = device.isSimulated && isSimulationMode
                    )
                }
            }

            is TopdonStatus.Attached -> {
                previewRunningState.value = false
                val attributes = mapOf(
                    "usb.vendorId" to "0x${status.vendorId.toString(16)}",
                    "usb.productId" to "0x${status.productId.toString(16)}",
                    "usb.serialNumber" to (status.serialNumber ?: "")
                )
                val connectionStatus = if (status.hasPermission) {
                    ConnectionStatus.Connected(
                        since = nowInstant(),
                        batteryPercent = null,
                        rssiDbm = null
                    )
                } else {
                    ConnectionStatus.Connecting
                }
                deviceState.update { device ->
                    device.copy(
                        connectionStatus = connectionStatus,
                        attributes = attributes,
                        isSimulated = false
                    )
                }
            }

            is TopdonStatus.Connected -> {
                previewRunningState.value = false
                val attributes = mapOf(
                    "usb.vendorId" to "0x${status.vendorId.toString(16)}",
                    "usb.productId" to "0x${status.productId.toString(16)}",
                    "usb.serialNumber" to (status.serialNumber ?: "")
                )
                val since = Instant.fromEpochMilliseconds(status.sinceEpochMs)
                deviceState.update { device ->
                    device.copy(
                        connectionStatus = ConnectionStatus.Connected(
                            since = since,
                            batteryPercent = null,
                            rssiDbm = null
                        ),
                        attributes = attributes,
                        isSimulated = false
                    )
                }
            }

            is TopdonStatus.Streaming -> {
                val attributes = mapOf(
                    "usb.vendorId" to "0x${status.vendorId.toString(16)}",
                    "usb.productId" to "0x${status.productId.toString(16)}",
                    "usb.serialNumber" to (status.serialNumber ?: "")
                )
                val since = Instant.fromEpochMilliseconds(status.sinceEpochMs)
                deviceState.update { device ->
                    device.copy(
                        connectionStatus = ConnectionStatus.Connected(
                            since = since,
                            batteryPercent = null,
                            rssiDbm = null
                        ),
                        attributes = attributes,
                        isSimulated = false
                    )
                }
            }

            is TopdonStatus.Error -> {
                previewRunningState.value = false
                Log.e(logTag, "Topdon hardware error: ${status.message} (recoverable=${status.recoverable})")
                statusState.value = emptyList()
                deviceState.update { device ->
                    device.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false)
                }
            }
        }
    }

    private fun handlePreviewFrame(frame: HardwarePreviewFrame) {
        val domainFrame = TopdonPreviewFrame(
            timestamp = Instant.fromEpochMilliseconds(frame.timestampEpochMs),
            width = frame.width,
            height = frame.height,
            mimeType = frame.mimeType,
            payload = frame.payload,
            superSamplingFactor = frame.superSamplingFactor,
            minTemp = frame.metrics.minCelsius.toFloat(),
            maxTemp = frame.metrics.maxCelsius.toFloat(),
            avgTemp = frame.metrics.avgCelsius.toFloat()
        )
        previewFrameState.value = domainFrame
        previewRunningState.value = true
        updatePreviewStatus(domainFrame.timestamp)
    }

    private fun handleStreamEvent(event: TopdonStreamEvent) {
        val payload = event.payload
        val timestamp = Instant.fromEpochMilliseconds(event.timestampEpochMs)
        lastMetrics = event.metrics
        thermalFrameCount = event.frameCount
        if (payload != null) {
        writerLock.withLock {
                try {
                    thermalStream?.write(payload)
                    thermalDigest?.update(payload)
                    thermalBytes += payload.size
                } catch (t: Throwable) {
                    Log.w(logTag, "Failed to persist thermal payload: ${t.message}")
                }
            }
        }
        val thermalStatus = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.THERMAL_VIDEO,
            sampleRateHz = null,
            frameRateFps = 25.0,
            lastSampleTimestamp = timestamp,
            bufferedDurationSeconds = 0.0,
            isStreaming = true,
            isSimulated = false
        )
        val previewStatus = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.PREVIEW,
            sampleRateHz = null,
            frameRateFps = currentSettings.previewFpsLimit.toDouble(),
            lastSampleTimestamp = timestamp,
            bufferedDurationSeconds = 0.0,
            isStreaming = previewRunningState.value,
            isSimulated = false
        )
        statusState.value = listOf(thermalStatus, previewStatus)
        if (event.endOfStream) {
            clearRecordingState(finalize = true)
        }
    }

    private fun updatePreviewStatus(timestamp: Instant) {
        val previewStatus = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.PREVIEW,
            sampleRateHz = null,
            frameRateFps = currentSettings.previewFpsLimit.toDouble(),
            lastSampleTimestamp = timestamp,
            bufferedDurationSeconds = 0.0,
            isStreaming = true,
            isSimulated = false
        )
        val existing = statusState.value.filterNot { it.streamType == SensorStreamType.PREVIEW }
        statusState.value = existing + previewStatus
    }

    private fun logHardwareNotice(notice: TopdonNotice) {
        val tag = when (notice.category) {
            TopdonNotice.Category.Info -> "info"
            TopdonNotice.Category.Warning -> "warn"
            TopdonNotice.Category.Error -> "error"
        }
        Log.i(logTag, "[hardware:$tag] ${notice.message}")
    }

    private suspend fun prepareThermalRecording(sessionId: String) {
        clearRecordingState(finalize = true)
        writerLock.withLock {
            val file = recordingStorage.createArtifactFile(
                sessionId = sessionId,
                deviceId = deviceId.value,
                streamType = "thermal_video",
                timestampEpochMs = System.currentTimeMillis(),
                extension = "raw"
            )
            val digest = MessageDigest.getInstance("SHA-256")
            thermalStream = FileOutputStream(file)
            thermalDigest = digest
            thermalFile = file
            currentSessionId = sessionId
            thermalBytes = 0
            thermalFrameCount = 0
        }
    }

    private fun enqueueArtifact(sessionId: String, file: File, digest: MessageDigest) {
        val checksum = digest.digest()
        val metadata = buildMap {
            put("frameCount", thermalFrameCount.toString())
            put("bytesCaptured", thermalBytes.toString())
            lastMetrics?.let { metrics ->
                put("minTempC", metrics.minCelsius.toString())
                put("maxTempC", metrics.maxCelsius.toString())
                put("avgTempC", metrics.avgCelsius.toString())
            }
        }
        val artifact = SessionArtifact(
            deviceId = deviceId,
            streamType = SensorStreamType.THERMAL_VIDEO,
            uri = Uri.fromFile(file),
            file = file,
            mimeType = "application/octet-stream",
            sizeBytes = file.length(),
            checksumSha256 = checksum,
            metadata = metadata
        )
        pendingArtifacts += sessionId to artifact
    }

    private fun clearRecordingState(finalize: Boolean) {
        writerLock.withLock {
            runCatching { thermalStream?.flush() }
            runCatching { thermalStream?.close() }
            thermalStream = null
        }
        val sessionId = currentSessionId
        val file = thermalFile
        val digest = thermalDigest
        thermalDigest = null
        thermalFile = null
        currentSessionId = null
        if (!finalize) {
            file?.delete()
            return
        }
        if (file != null && sessionId != null && digest != null) {
            enqueueArtifact(sessionId, file, digest)
        } else {
            file?.delete()
        }
        thermalBytes = 0
        thermalFrameCount = 0
        lastMetrics = null
    }

    override fun streamIntervalMs(): Long = 200L

    override fun simulatedBatteryPercent(device: SensorDevice): Int? = null

    override fun simulatedRssi(device: SensorDevice): Int? = null

    override fun sampleStatuses(
        timestamp: Instant,
        frameCounter: Long,
        anchor: RecordingSessionAnchor
    ): List<SensorStreamStatus> {
        val jitter = ((frameCounter + deviceId.value.hashCode()).absoluteValue % 1000) / 10_000.0
        val thermal = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.THERMAL_VIDEO,
            sampleRateHz = null,
            frameRateFps = 25.0,
            lastSampleTimestamp = timestamp,
            bufferedDurationSeconds = simulatedBufferedSeconds(
                streamType = SensorStreamType.THERMAL_VIDEO,
                baseVideo = 0.6,
                baseSample = 0.0,
                randomizer = { jitter }
            ),
            isStreaming = true,
            isSimulated = true
        )
        val preview = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.PREVIEW,
            sampleRateHz = null,
            frameRateFps = currentSettings.previewFpsLimit.toDouble(),
            lastSampleTimestamp = timestamp,
            bufferedDurationSeconds = simulatedBufferedSeconds(
                streamType = SensorStreamType.PREVIEW,
                baseVideo = 0.3,
                baseSample = 0.0,
                randomizer = { jitter / 2 }
            ),
            isStreaming = true,
            isSimulated = true
        )
        return listOf(thermal, preview)
    }

    private companion object {
        private const val ATTR_VENDOR_ID = "usb.vendorId"
        private const val ATTR_PRODUCT_ID = "usb.productId"
        private const val ATTR_SERIAL = "usb.serialNumber"
    }
}
