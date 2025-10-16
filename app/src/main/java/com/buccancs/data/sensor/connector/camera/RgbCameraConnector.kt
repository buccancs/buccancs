package com.buccancs.data.sensor.connector.camera

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.ImageFormat
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.media.ImageReader
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Size
import android.view.Surface
import com.buccancs.core.result.Error
import com.buccancs.core.result.Result
import com.buccancs.core.result.recover
import com.buccancs.core.result.resultOf
import com.buccancs.data.sensor.connector.simulated.BaseSimulatedConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
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
import com.buccancs.util.nowInstant
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.math.absoluteValue
import kotlin.time.Instant

@Singleton
internal class RgbCameraConnector @Inject constructor(
    @ApplicationScope scope: CoroutineScope,
    @ApplicationContext private val context: Context,
    private val cameraManager: CameraManager,
    private val recordingStorage: RecordingStorage,
    artifactFactory: SimulatedArtifactFactory
) : BaseSimulatedConnector(
    scope = scope,
    artifactFactory = artifactFactory,
    initialDevice = SensorDevice(
        id = DeviceId("android-rgb"),
        displayName = "Phone RGB Camera",
        type = SensorDeviceType.ANDROID_RGB_CAMERA,
        capabilities = setOf(SensorStreamType.RGB_VIDEO, SensorStreamType.PREVIEW),
        connectionStatus = ConnectionStatus.Disconnected,
        isSimulated = false,
        attributes = emptyMap()
    )
) {
    private val logTag = "RgbCameraConnector"
    private var cameraId: String? = null
    private var cameraDevice: CameraDevice? = null
    private var captureSession: CameraCaptureSession? = null
    private var imageReader: ImageReader? = null
    private var handlerThread: HandlerThread? = null
    private var handler: Handler? = null
    private var recorder: SegmentedMediaCodecRecorder? = null
    private var recorderSurface: Surface? = null
    private var currentSessionId: String? = null
    private var currentAnchor: RecordingSessionAnchor? = null
    private var completedSessionId: String? = null
    private val pendingArtifacts = mutableListOf<SessionArtifact>()
    override suspend fun refreshInventory() {
        if (isSimulationMode) {
            super.refreshInventory()
            return
        }
        cameraId = findBackCameraId()
        val attributes = cameraId?.let { mapOf("cameraId" to it) } ?: emptyMap()
        deviceState.update { current ->
            val connection = if (cameraDevice != null) {
                val existing = current.connectionStatus as? ConnectionStatus.Connected
                ConnectionStatus.Connected(
                    since = existing?.since ?: nowInstant(),
                    batteryPercent = null,
                    rssiDbm = null
                )
            } else {
                ConnectionStatus.Disconnected
            }
            current.copy(
                attributes = attributes,
                connectionStatus = connection,
                isSimulated = false
            )
        }
    }

    override suspend fun applySimulation(enabled: Boolean) {
        if (enabled) {
            closeCamera()
            tearDownThread()
        }
        super.applySimulation(enabled)
    }

    override suspend fun connect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.connect()
        }

        return performConnect()
            .map { DeviceCommandResult.Accepted }
            .recover { error ->
                when (error) {
                    is Error.NotFound -> DeviceCommandResult.Rejected(error.message)
                    is Error.Hardware -> DeviceCommandResult.Rejected(error.message)
                    else -> {
                        Log.e(logTag, "Connect failed: ${error.message}", error.cause)
                        deviceState.update {
                            it.copy(
                                connectionStatus = ConnectionStatus.Disconnected,
                                isSimulated = false
                            )
                        }
                        DeviceCommandResult.Failed(error.toException())
                    }
                }
            }
            .getOrThrow()
    }

    private suspend fun performConnect(): Result<Unit> = resultOf {
        val id = cameraId ?: findBackCameraId() ?: throw IllegalStateException("No back-facing camera found.")

        if (cameraDevice != null) {
            return@resultOf  // Already connected
        }

        deviceState.update { it.copy(connectionStatus = ConnectionStatus.Connecting, isSimulated = false) }
        val device = openCamera(id)
        cameraDevice = device
        deviceState.update {
            it.copy(
                connectionStatus = ConnectionStatus.Connected(
                    since = nowInstant(),
                    batteryPercent = null,
                    rssiDbm = null
                ),
                isSimulated = false,
                attributes = mapOf("cameraId" to id)
            )
        }
    }

    override suspend fun disconnect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.disconnect()
        }

        return performDisconnect()
            .map { DeviceCommandResult.Accepted }
            .recover { error ->
                Log.e(logTag, "Disconnect failed: ${error.message}", error.cause)
                DeviceCommandResult.Failed(error.toException())
            }
            .getOrThrow()
    }

    private suspend fun performDisconnect(): Result<Unit> = resultOf {
        stopStreamingInternal()
        closeCamera()
        tearDownThread()
        deviceState.update { it.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false) }
    }

    override suspend fun startStreaming(anchor: RecordingSessionAnchor): DeviceCommandResult {
        if (isSimulationMode) {
            return super.startStreaming(anchor)
        }

        return performStartStreaming(anchor)
            .map { DeviceCommandResult.Accepted }
            .recover { error ->
                Log.e(logTag, "Start streaming failed: ${error.message}", error.cause)
                DeviceCommandResult.Failed(error.toException())
            }
            .getOrThrow()
    }

    private suspend fun performStartStreaming(anchor: RecordingSessionAnchor): Result<Unit> = resultOf {
        val device = cameraDevice ?: run {
            val connectResult = connect()
            if (connectResult !is DeviceCommandResult.Accepted) {
                throw IllegalStateException("Unable to connect to camera device.")
            }
            cameraDevice ?: throw IllegalStateException("Unable to access camera device.")
        }

        val id = cameraId ?: findBackCameraId() ?: throw IllegalStateException("No back-facing camera available.")
        configureAndStartSession(device, id, anchor)
    }

    override suspend fun stopStreaming(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.stopStreaming()
        }

        return performStopStreaming()
            .map { DeviceCommandResult.Accepted }
            .recover { error ->
                Log.e(logTag, "Stop streaming failed: ${error.message}", error.cause)
                DeviceCommandResult.Failed(error.toException())
            }
            .getOrThrow()
    }

    private suspend fun performStopStreaming(): Result<Unit> = resultOf {
        stopStreamingInternal()
    }

    override fun streamIntervalMs(): Long = 160L
    override fun simulatedBatteryPercent(device: SensorDevice): Int? = null
    override fun simulatedRssi(device: SensorDevice): Int? = null
    override fun sampleStatuses(
        timestamp: Instant,
        frameCounter: Long,
        anchor: RecordingSessionAnchor
    ): List<SensorStreamStatus> {
        val random = (deviceId.value.hashCode() + frameCounter).absoluteValue % 1000
        val bufferedVideo = simulatedBufferedSeconds(
            streamType = SensorStreamType.RGB_VIDEO,
            baseVideo = 0.5,
            baseSample = 0.0,
            randomizer = { random / 5000.0 }
        )
        val bufferedPreview = simulatedBufferedSeconds(
            streamType = SensorStreamType.PREVIEW,
            baseVideo = 0.4,
            baseSample = 0.0,
            randomizer = { random / 10_000.0 }
        )
        val rgb = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.RGB_VIDEO,
            sampleRateHz = null,
            frameRateFps = 30.0,
            lastSampleTimestamp = timestamp,
            bufferedDurationSeconds = bufferedVideo,
            isStreaming = true,
            isSimulated = true
        )
        val preview = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.PREVIEW,
            sampleRateHz = null,
            frameRateFps = 15.0,
            lastSampleTimestamp = timestamp,
            bufferedDurationSeconds = bufferedPreview,
            isStreaming = true,
            isSimulated = true
        )
        return listOf(rgb, preview)
    }

    @SuppressLint("MissingPermission")
    private suspend fun openCamera(id: String): CameraDevice {
        ensureHandlerThread()
        val looperHandler = handler ?: throw IllegalStateException("Camera handler not initialized.")
        return suspendCancellableCoroutine { continuation ->
            try {
                cameraManager.openCamera(
                    id,
                    object : CameraDevice.StateCallback() {
                        override fun onOpened(device: CameraDevice) {
                            continuation.resume(device)
                        }

                        override fun onDisconnected(device: CameraDevice) {
                            Log.w(logTag, "Camera disconnected.")
                            device.close()
                            if (continuation.isActive) {
                                continuation.resumeWithException(CameraAccessException(CameraAccessException.CAMERA_DISCONNECTED))
                            } else {
                                deviceState.update {
                                    it.copy(
                                        connectionStatus = ConnectionStatus.Disconnected,
                                        isSimulated = false
                                    )
                                }
                            }
                        }

                        override fun onError(device: CameraDevice, error: Int) {
                            Log.e(logTag, "Camera error $error.")
                            device.close()
                            val exception =
                                CameraAccessException(CameraAccessException.CAMERA_ERROR, "Camera error $error")
                            if (continuation.isActive) {
                                continuation.resumeWithException(exception)
                            } else {
                                deviceState.update {
                                    it.copy(
                                        connectionStatus = ConnectionStatus.Disconnected,
                                        isSimulated = false
                                    )
                                }
                            }
                        }
                    },
                    looperHandler
                )
            } catch (t: SecurityException) {
                continuation.resumeWithException(t)
            } catch (t: CameraAccessException) {
                continuation.resumeWithException(t)
            }
        }
    }

    private suspend fun configureAndStartSession(
        device: CameraDevice,
        id: String,
        anchor: RecordingSessionAnchor
    ) {
        stopStreamingInternal()
        val handler = handler ?: throw IllegalStateException("Camera handler not initialized.")
        val size = chooseVideoSize(id)
        val recorder = SegmentedMediaCodecRecorder(
            context = context,
            storage = recordingStorage,
            sessionId = anchor.sessionId,
            deviceId = deviceId,
            anchorEpochMs = anchor.referenceTimestamp.toEpochMilliseconds(),
            size = size,
            bitRate = VIDEO_BIT_RATE,
            frameRate = VIDEO_FRAME_RATE
        )
        val recorderSurface = recorder.start()
        this.recorder = recorder
        this.recorderSurface = recorderSurface
        currentSessionId = anchor.sessionId
        currentAnchor = anchor
        val reader = ImageReader.newInstance(size.width, size.height, ImageFormat.YUV_420_888, 3)
        reader.setOnImageAvailableListener(imageListener, handler)
        imageReader = reader
        val surfaces = mutableListOf<Surface>()
        surfaces += recorderSurface
        surfaces += reader.surface
        try {
            suspendCancellableCoroutine<Unit> { continuation ->
                device.createCaptureSession(
                    surfaces,
                    object : CameraCaptureSession.StateCallback() {
                        override fun onConfigured(session: CameraCaptureSession) {
                            captureSession = session
                            val request = device.createCaptureRequest(CameraDevice.TEMPLATE_RECORD).apply {
                                set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_AUTO)
                                set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_VIDEO)
                                addTarget(recorderSurface)
                                addTarget(reader.surface)
                            }.build()
                            try {
                                session.setRepeatingRequest(request, null, handler)
                                continuation.resume(Unit)
                            } catch (t: Throwable) {
                                Log.e(logTag, "Failed to start RGB capture session", t)
                                continuation.resumeWithException(t)
                            }
                        }

                        override fun onConfigureFailed(session: CameraCaptureSession) {
                            continuation.resumeWithException(
                                CameraAccessException(
                                    CameraAccessException.CAMERA_ERROR,
                                    "Failed to configure capture session"
                                )
                            )
                        }
                    },
                    handler
                )
            }
        } catch (t: Throwable) {
            runCatching { recorderSurface.release() }
            runCatching { recorder.abort() }
            this.recorder = null
            this.recorderSurface = null
            currentSessionId = null
            reader.close()
            imageReader = null
            throw t
        }
    }

    private suspend fun stopStreamingInternal() {
        try {
            captureSession?.stopRepeating()
        } catch (t: Throwable) {
            Log.w(logTag, "Failed to stop repeating request", t)
        }
        captureSession?.close()
        captureSession = null
        val recorder = this.recorder
        if (recorder != null) {
            val artifacts = try {
                recorder.stop()
            } catch (error: Throwable) {
                Log.w(logTag, "Recorder stop failed, attempting abort: ${error.message}", error)
                try {
                    recorder.abort()
                } catch (abortError: Throwable) {
                    Log.w(logTag, "Recorder abort also failed: ${abortError.message}", abortError)
                }
                emptyList()
            }
            if (artifacts.isNotEmpty()) {
                pendingArtifacts += artifacts
                currentSessionId?.let { completedSessionId = it }
            }
        }
        recorderSurface?.release()
        recorderSurface = null
        this.recorder = null
        currentSessionId = null
        currentAnchor = null
        imageReader?.close()
        imageReader = null
        statusState.value = emptyList()
    }

    private suspend fun closeCamera() {
        stopStreamingInternal()
        cameraDevice?.close()
        cameraDevice = null
    }

    private fun ensureHandlerThread() {
        if (handlerThread != null && handler != null) {
            return
        }
        val thread = HandlerThread("RgbCameraThread").apply { start() }
        handlerThread = thread
        handler = Handler(thread.looper)
    }

    private fun tearDownThread() {
        handlerThread?.quitSafely()
        handlerThread = null
        handler = null
    }

    private fun findBackCameraId(): String? {
        return try {
            cameraManager.cameraIdList.firstOrNull { id ->
                val characteristics = cameraManager.getCameraCharacteristics(id)
                val facing = characteristics.get(CameraCharacteristics.LENS_FACING)
                facing == CameraCharacteristics.LENS_FACING_BACK
            }
        } catch (t: CameraAccessException) {
            Log.e(logTag, "Unable to enumerate cameras", t)
            null
        }
    }

    private fun chooseVideoSize(id: String): Size {
        return try {
            val characteristics = cameraManager.getCameraCharacteristics(id)
            val map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
            val choices = map?.getOutputSizes(ImageFormat.YUV_420_888)
            val target = choices?.firstOrNull { it.width >= 1920 && it.height >= 1080 }
            target ?: choices?.maxByOrNull { it.width * it.height } ?: Size(1280, 720)
        } catch (t: CameraAccessException) {
            Log.w(logTag, "Falling back to default camera size", t)
            Size(1280, 720)
        }
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

    private val imageListener = ImageReader.OnImageAvailableListener { reader ->
        try {
            reader.acquireLatestImage()?.use { image ->
                // Image automatically closed by use block
                // Currently just used for frame timing, actual recording via MediaCodec
            }
        } catch (t: Throwable) {
            Log.w(logTag, "Failed to acquire RGB frame", t)
        }
        val now = nowInstant()
        val rgbStatus = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.RGB_VIDEO,
            sampleRateHz = null,
            frameRateFps = 30.0,
            lastSampleTimestamp = now,
            bufferedDurationSeconds = 0.0,
            isStreaming = true,
            isSimulated = false
        )
        val previewStatus = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.PREVIEW,
            sampleRateHz = null,
            frameRateFps = 15.0,
            lastSampleTimestamp = now,
            bufferedDurationSeconds = 0.0,
            isStreaming = true,
            isSimulated = false
        )
        statusState.value = listOf(rgbStatus, previewStatus)
    }

    companion object {
        private const val VIDEO_BIT_RATE = 8_000_000
        private const val VIDEO_FRAME_RATE = 30
    }
}
