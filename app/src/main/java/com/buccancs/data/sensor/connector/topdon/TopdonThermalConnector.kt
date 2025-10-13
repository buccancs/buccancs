package com.buccancs.data.sensor.connector.topdon

import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.net.Uri
import android.os.SystemClock
import android.util.Log
import com.buccancs.core.time.TimeModelAdapter
import com.buccancs.data.preview.PreviewStreamClient
import com.buccancs.data.sensor.MetadataWriters
import com.buccancs.data.sensor.connector.simulated.BaseSimulatedConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
import com.buccancs.data.sensor.topdon.InMemoryTopdonSettingsRepository
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceCommandResult
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import com.buccancs.util.toEpochMillis
import com.infisense.iruvc.usb.USBMonitor
import com.infisense.iruvc.utils.CommonParams
import com.infisense.iruvc.utils.IFrameCallback
import com.infisense.iruvc.uvc.ConcreateUVCBuilder
import com.infisense.iruvc.uvc.UVCCamera
import com.infisense.iruvc.uvc.UVCType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant
import java.io.File
import java.io.FileOutputStream
import java.security.MessageDigest
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.absoluteValue

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
    @ApplicationContext private val context: Context,
    private val usbManager: UsbManager,
    private val recordingStorage: RecordingStorage,
    private val thermalNormalizer: ThermalNormalizer,
    artifactFactory: SimulatedArtifactFactory,
    @Suppress("UNUSED_PARAMETER") previewClient: PreviewStreamClient? = null,
    @Suppress("UNUSED_PARAMETER") settingsRepository: InMemoryTopdonSettingsRepository? = null,
    initialDevice: SensorDevice = defaultTopdonDevice()
) : BaseSimulatedConnector(
    scope = appScope,
    artifactFactory = artifactFactory,
    initialDevice = initialDevice
) {
    private val logTag = "TopdonConnector"
    private var usbMonitor: USBMonitor? = null
    private var usbControlBlock: USBMonitor.UsbControlBlock? = null
    private var uvcCamera: UVCCamera? = null
    private var monitorRegistered = false
    private var currentSessionId: String? = null
    private var completedSessionId: String? = null
    private var thermalFile: File? = null
    private var thermalStream: FileOutputStream? = null
    private var thermalDigest: MessageDigest? = null
    private var thermalBytes: Long = 0

    @Volatile
    private var timeModel: TimeModelAdapter? = null

    @Volatile
    private var lastThermalMetrics: ThermalNormalizer.Metrics? = null

    @Volatile
    private var lastFrameEpochMs: Long? = null
    private var thermalFrameCount: Long = 0
    private val pendingArtifacts = mutableListOf<SessionArtifact>()
    private val listener = object : USBMonitor.OnDeviceConnectListener {
        override fun onAttach(device: UsbDevice) {
            appScope.launch {
                Log.d(logTag, "USB device attached: ${device.deviceName}")
                if (!tryRequestPermission(device)) {
                    Log.w(logTag, "Permission request pending for ${device.deviceName}")
                }
            }
        }

        override fun onGranted(usbDevice: UsbDevice, granted: Boolean) {
            Log.d(logTag, "Permission ${if (granted) "granted" else "denied"} for ${usbDevice.deviceName}")
        }

        override fun onConnect(device: UsbDevice, ctrlBlock: USBMonitor.UsbControlBlock, createNew: Boolean) {
            if (!createNew) return
            Log.i(logTag, "USB device connected: ${device.deviceName}")
            usbControlBlock = ctrlBlock
            appScope.launch {
                deviceState.update {
                    it.copy(
                        connectionStatus = ConnectionStatus.Connected(
                            since = deviceNowInstant(),
                            batteryPercent = null,
                            rssiDbm = null
                        ),
                        isSimulated = false
                    )
                }
                try {
                    openCamera(ctrlBlock)
                } catch (t: Throwable) {
                    Log.e(logTag, "Failed to open Topdon camera", t)
                    deviceState.update { state ->
                        state.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false)
                    }
                }
            }
        }

        override fun onDisconnect(device: UsbDevice, ctrlBlock: USBMonitor.UsbControlBlock) {
            Log.i(logTag, "USB device disconnected: ${device.deviceName}")
            appScope.launch {
                stopStreamingInternal()
                closeCamera()
                statusState.value = emptyList()
                deviceState.update { it.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false) }
            }
        }

        override fun onDettach(device: UsbDevice) {
            Log.i(logTag, "USB device detached: ${device.deviceName}")
            appScope.launch {
                stopStreamingInternal()
                closeCamera()
                statusState.value = emptyList()
                deviceState.update { it.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false) }
            }
        }

        override fun onCancel(device: UsbDevice) {
            Log.w(logTag, "USB permission cancelled: ${device.deviceName}")
        }
    }

    override suspend fun refreshInventory() {
        if (isSimulationMode) {
            super.refreshInventory()
            return
        }
        val device = detectTopdonDevice()
        val status = when {
            uvcCamera != null -> ConnectionStatus.Connected(
                since = deviceNowInstant(),
                batteryPercent = null,
                rssiDbm = null
            )

            device != null -> ConnectionStatus.Disconnected
            else -> ConnectionStatus.Disconnected
        }
        val attributes = device?.let {
            mapOf(
                "usb.vendorId" to "0x${it.vendorId.toString(16)}",
                "usb.productId" to "0x${it.productId.toString(16)}",
                "usb.deviceName" to it.deviceName
            )
        } ?: deviceState.value.attributes
        deviceState.update {
            it.copy(
                connectionStatus = status,
                attributes = attributes,
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
        ensureMonitor()
        val device = detectTopdonDevice()
        if (device == null) {
            return DeviceCommandResult.Rejected("Topdon TC001 camera not detected.")
        }
        deviceState.update { it.copy(connectionStatus = ConnectionStatus.Connecting, isSimulated = false) }
        return if (tryRequestPermission(device)) {
            DeviceCommandResult.Accepted
        } else {
            DeviceCommandResult.Accepted
        }
    }

    override suspend fun disconnect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.disconnect()
        }
        return try {
            disconnectHardware()
            DeviceCommandResult.Accepted
        } catch (t: Throwable) {
            Log.e(logTag, "Failed to disconnect Topdon camera", t)
            DeviceCommandResult.Failed(t)
        }
    }

    override suspend fun startStreaming(anchor: RecordingSessionAnchor): DeviceCommandResult {
        if (isSimulationMode) {
            return super.startStreaming(anchor)
        }
        timeModel = TimeModelAdapter.fromAnchor(anchor)
        val camera = uvcCamera ?: run {
            timeModel = null
            return DeviceCommandResult.Rejected("Camera not connected.")
        }
        if (usbControlBlock == null) {
            timeModel = null
            return DeviceCommandResult.Rejected("Camera control unavailable.")
        }
        return try {
            configureCamera(camera)
            prepareThermalRecording(anchor.sessionId)
            camera.setFrameCallback(frameCallback)
            camera.onStartPreview()
            DeviceCommandResult.Accepted
        } catch (t: Throwable) {
            Log.e(logTag, "Failed to start thermal preview", t)
            timeModel = null
            DeviceCommandResult.Failed(t)
        }
    }

    override suspend fun stopStreaming(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.stopStreaming()
        }
        return try {
            stopStreamingInternal()
            finalizeThermalRecording()
            timeModel = null
            DeviceCommandResult.Accepted
        } catch (t: Throwable) {
            Log.e(logTag, "Failed to stop thermal preview", t)
            timeModel = null
            DeviceCommandResult.Failed(t)
        }
    }

    override fun streamIntervalMs(): Long = 200L
    override fun simulatedBatteryPercent(device: SensorDevice): Int? = null
    override fun simulatedRssi(device: SensorDevice): Int? = null
    override fun sampleStatuses(
        timestamp: kotlinx.datetime.Instant,
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
            frameRateFps = 12.0,
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

    private suspend fun disconnectHardware() {
        withContext(Dispatchers.Main) {
            stopStreamingInternal()
            closeCamera()
            statusState.value = emptyList()
            if (monitorRegistered) {
                usbMonitor?.unregister()
                monitorRegistered = false
            }
            usbMonitor?.destroy()
            usbMonitor = null
        }
        deviceState.update { it.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false) }
    }

    private fun stopStreamingInternal() {
        try {
            uvcCamera?.setFrameCallback(null)
            if (uvcCamera?.openStatus == true) {
                uvcCamera?.onStopPreview()
            }
        } catch (t: Throwable) {
            Log.w(logTag, "Error stopping thermal preview", t)
        }
        statusState.value = emptyList()
        finalizeThermalRecording()
        timeModel = null
    }

    private fun closeCamera() {
        try {
            uvcCamera?.onDestroyPreview()
        } catch (_: Throwable) {
        }
        uvcCamera = null
        usbControlBlock = null
    }

    private fun ensureMonitor() {
        if (usbMonitor == null) {
            usbMonitor = USBMonitor(context, listener).also {
                it.register()
                monitorRegistered = true
            }
        } else if (!monitorRegistered) {
            usbMonitor?.register()
            monitorRegistered = true
        }
    }

    private fun detectTopdonDevice(): UsbDevice? {
        return usbManager.deviceList.values.firstOrNull { device ->
            device.vendorId == TOPDON_VENDOR_ID && TOPDON_PRODUCT_IDS.contains(device.productId)
        }
    }

    private fun tryRequestPermission(device: UsbDevice): Boolean {
        ensureMonitor()
        val monitor = usbMonitor ?: return false
        if (monitor.hasPermission(device)) {
            return true
        }
        return monitor.requestPermission(device)
    }

    private fun openCamera(ctrlBlock: USBMonitor.UsbControlBlock) {
        val camera = ConcreateUVCBuilder()
            .setUVCType(UVCType.USB_UVC)
            .build()
        uvcCamera = camera
        camera.openUVCCamera(ctrlBlock)
        configureCamera(camera)
    }

    private fun configureCamera(camera: UVCCamera) {
        camera.setOpenStatus(true)
        camera.setDefaultPreviewMode(CommonParams.FRAMEFORMATType.FRAME_FORMAT_MJPEG)
        camera.setDefaultBandwidth(0.6f)
        camera.setDefaultPreviewMinFps(1)
        camera.setDefaultPreviewMaxFps(30)
        val result = camera.setUSBPreviewSize(THERMAL_WIDTH, THERMAL_HEIGHT)
        if (result != 0) {
            Log.w(logTag, "Failed to set preview size ($THERMAL_WIDTH x $THERMAL_HEIGHT), result=$result")
        }
    }

    private fun prepareThermalRecording(sessionId: String) {
        val file = recordingStorage.createArtifactFile(
            sessionId = sessionId,
            deviceId = deviceId.value,
            streamType = "thermal_video",
            timestampEpochMs = System.currentTimeMillis(),
            extension = "raw"
        )
        thermalFrameCount = 0
        lastThermalMetrics = null
        lastFrameEpochMs = null
        thermalStream = FileOutputStream(file)
        thermalDigest = MessageDigest.getInstance("SHA-256")
        thermalFile = file
        thermalBytes = 0
        currentSessionId = sessionId
    }

    private fun finalizeThermalRecording() {
        val stream = thermalStream ?: return
        try {
            stream.flush()
        } catch (_: Throwable) {
        }
        try {
            stream.close()
        } catch (_: Throwable) {
        }
        thermalStream = null
        val file = thermalFile
        val sessionId = currentSessionId
        val checksum = thermalDigest?.digest() ?: ByteArray(0)
        val bytesCaptured = thermalBytes
        val clockSnapshot = timeModel
        thermalDigest = null
        thermalFile = null
        thermalBytes = 0
        currentSessionId = null
        if (file != null && file.exists() && sessionId != null) {
            val artifact = SessionArtifact(
                deviceId = deviceId,
                streamType = SensorStreamType.THERMAL_VIDEO,
                uri = Uri.fromFile(file),
                file = file,
                mimeType = "application/octet-stream",
                sizeBytes = file.length(),
                checksumSha256 = checksum
            )
            pendingArtifacts += artifact
            val entries = mutableListOf<Pair<String, String>>().apply {
                add("sessionId" to MetadataWriters.stringValue(sessionId))
                add("deviceId" to MetadataWriters.stringValue(deviceId.value))
                add("streamType" to MetadataWriters.stringValue("thermal_video"))
                add("artifactFile" to MetadataWriters.stringValue(file.name))
                val anchorEpoch =
                    clockSnapshot?.let { it.anchorEpochMs + it.clockOffsetMs } ?: System.currentTimeMillis()
                add("anchorEpochMs" to anchorEpoch.toString())
                clockSnapshot?.let { add("clockOffsetMs" to it.clockOffsetMs.toString()) }
                clockSnapshot?.recordingStartEpochMs?.let { add("deviceStartEpochMs" to it.toString()) }
                clockSnapshot?.startAlignedEpochMillis()?.let { add("alignedStartEpochMs" to it.toString()) }
                clockSnapshot?.durationSinceStartMs(SystemClock.elapsedRealtimeNanos())?.let { duration ->
                    add("durationMs" to duration.toString())
                    clockSnapshot.startAlignedEpochMillis()?.let { startAligned ->
                        add("alignedEndEpochMs" to (startAligned + duration).toString())
                    }
                }
                add("frameWidth" to THERMAL_WIDTH.toString())
                add("frameHeight" to THERMAL_HEIGHT.toString())
                add("bytesCaptured" to bytesCaptured.toString())
                add("frameCount" to thermalFrameCount.toString())
                lastFrameEpochMs?.let { add("lastFrameEpochMs" to it.toString()) }
                lastThermalMetrics?.let { metrics ->
                    add("frameMinCelsius" to String.format(Locale.US, "%.2f", metrics.minCelsius))
                    add("frameMaxCelsius" to String.format(Locale.US, "%.2f", metrics.maxCelsius))
                }
                add("checksumSha256" to MetadataWriters.stringValue(checksum.toHexString()))
            }
            val directory = file.parentFile ?: recordingStorage.deviceDirectory(sessionId, deviceId.value)
            val metadataFile = File(directory, file.nameWithoutExtension + "-metadata.json")
            MetadataWriters.writeMetadata(metadataFile, entries)
            val metadataChecksum = digestFile(metadataFile)
            pendingArtifacts += SessionArtifact(
                deviceId = deviceId,
                streamType = SensorStreamType.THERMAL_VIDEO,
                uri = Uri.fromFile(metadataFile),
                file = metadataFile,
                mimeType = "application/json",
                sizeBytes = metadataFile.length(),
                checksumSha256 = metadataChecksum
            )
            completedSessionId = sessionId
        }
        lastThermalMetrics = null
        lastFrameEpochMs = null
        thermalFrameCount = 0
    }

    override suspend fun collectArtifacts(sessionId: String): List<SessionArtifact> {
        if (isSimulationMode) {
            return super.collectArtifacts(sessionId)
        }
        if (sessionId != completedSessionId) {
            return emptyList()
        }
        completedSessionId = null
        val artifacts = pendingArtifacts.toList()
        pendingArtifacts.clear()
        return artifacts
    }

    private val frameCallback = IFrameCallback { data ->
        if (data != null) {
            try {
                thermalStream?.write(data)
                thermalDigest?.update(data)
                thermalBytes += data.size
            } catch (t: Throwable) {
                Log.w(logTag, "Failed to persist thermal frame", t)
            }
            val metrics = runCatching { thermalNormalizer.normalize(data) }.getOrNull()
            if (metrics != null) {
                lastThermalMetrics = metrics
            }
        }
        timeModel = timeModel?.let { clock ->
            if (clock.hasRecordingStarted()) {
                clock
            } else {
                clock.markRecordingStart(System.currentTimeMillis(), SystemClock.elapsedRealtimeNanos())
            }
        }
        if (data != null) {
            thermalFrameCount += 1
            val alignedEpoch = timeModel?.alignMonotonic(SystemClock.elapsedRealtimeNanos())
                ?: System.currentTimeMillis()
            lastFrameEpochMs = alignedEpoch
        }
        val now = alignedNowInstant()
        val thermal = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.THERMAL_VIDEO,
            sampleRateHz = null,
            frameRateFps = 25.0,
            lastSampleTimestamp = now,
            bufferedDurationSeconds = 0.0,
            isStreaming = true,
            isSimulated = false
        )
        val preview = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.PREVIEW,
            sampleRateHz = null,
            frameRateFps = 12.0,
            lastSampleTimestamp = now,
            bufferedDurationSeconds = 0.0,
            isStreaming = true,
            isSimulated = false
        )
        statusState.value = listOf(thermal, preview)
    }

    private fun deviceNowInstant(): Instant =
        Instant.fromEpochMilliseconds(System.currentTimeMillis())

    private fun alignedInstant(instant: Instant): Instant {
        val clock = timeModel ?: return instant
        val alignedMs = clock.alignDeviceEpoch(instant.toEpochMillis())
        return Instant.fromEpochMilliseconds(alignedMs)
    }

    private fun alignedNowInstant(): Instant = alignedInstant(deviceNowInstant())

    private fun digestFile(file: File): ByteArray {
        val digest = MessageDigest.getInstance("SHA-256")
        file.inputStream().use { stream ->
            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
            while (true) {
                val read = stream.read(buffer)
                if (read <= 0) break
                digest.update(buffer, 0, read)
            }
        }
        return digest.digest()
    }

    private fun ByteArray.toHexString(): String =
        joinToString(separator = "") { byte ->
            ((byte.toInt() and 0xFF) + 0x100).toString(16).substring(1)
        }


    private companion object {
        private const val TOPDON_VENDOR_ID = 0x3426
        private val TOPDON_PRODUCT_IDS = setOf(0x0001, 0x0002, 0x0003, 0x3901)
        private const val THERMAL_WIDTH = 256
        private const val THERMAL_HEIGHT = 192
    }
}
