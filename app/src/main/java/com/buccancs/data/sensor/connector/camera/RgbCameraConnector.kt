package com.buccancs.data.sensor.connector.camera

import com.buccancs.util.nowInstant
import android.annotation.SuppressLint
import android.graphics.ImageFormat
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.media.ImageReader
import android.media.MediaRecorder
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Size
import android.view.Surface
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.datetime.Instant
import java.io.File
import java.io.FileInputStream
import java.security.DigestInputStream
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.math.absoluteValue

@Singleton
class RgbCameraConnector @Inject constructor(
    @ApplicationScope scope: CoroutineScope,
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
    private var mediaRecorder: MediaRecorder? = null
    private var recorderSurface: Surface? = null
    private var videoFile: File? = null
    private var currentSessionId: String? = null
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
        val id = cameraId ?: findBackCameraId()
        if (id == null) {
            return DeviceCommandResult.Rejected("No back-facing camera found.")
        }
        if (cameraDevice != null) {
            return DeviceCommandResult.Accepted
        }
        return try {
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
            DeviceCommandResult.Accepted
        } catch (t: Throwable) {
            Log.e(logTag, "Failed to open RGB camera", t)
            deviceState.update { it.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false) }
            DeviceCommandResult.Failed(t)
        }
    }

    override suspend fun disconnect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.disconnect()
        }
        return try {
            stopStreamingInternal()
            closeCamera()
            tearDownThread()
            deviceState.update { it.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false) }
            DeviceCommandResult.Accepted
        } catch (t: Throwable) {
            Log.e(logTag, "Failed to disconnect RGB camera", t)
            DeviceCommandResult.Failed(t)
        }
    }

    override suspend fun startStreaming(anchor: RecordingSessionAnchor): DeviceCommandResult {
        if (isSimulationMode) {
            return super.startStreaming(anchor)
        }
        val device = cameraDevice ?: run {
            val connectResult = connect()
            if (connectResult !is DeviceCommandResult.Accepted) {
                return connectResult
            }
            cameraDevice ?: return DeviceCommandResult.Rejected("Unable to access camera device.")
        }
        return try {
            val id = cameraId ?: findBackCameraId()
            if (id == null) {
                return DeviceCommandResult.Rejected("No back-facing camera available.")
            }
            configureAndStartSession(device, id, anchor.sessionId)
            DeviceCommandResult.Accepted
        } catch (t: Throwable) {
            Log.e(logTag, "Failed to start RGB camera streaming", t)
            DeviceCommandResult.Failed(t)
        }
    }

    override suspend fun stopStreaming(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.stopStreaming()
        }
        return try {
            stopStreamingInternal()
            DeviceCommandResult.Accepted
        } catch (t: Throwable) {
            Log.e(logTag, "Failed to stop RGB camera streaming", t)
            DeviceCommandResult.Failed(t)
        }
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

    private suspend fun configureAndStartSession(device: CameraDevice, id: String, sessionId: String) {
        stopStreamingInternal()
        val handler = handler ?: throw IllegalStateException("Camera handler not initialized.")
        val size = chooseVideoSize(id)
        val recorderSurface = prepareRecorder(sessionId, size)
        val reader = ImageReader.newInstance(size.width, size.height, ImageFormat.YUV_420_888, 3)
        reader.setOnImageAvailableListener(imageListener, handler)
        imageReader = reader
        val surfaces = mutableListOf<Surface>()
        surfaces += recorderSurface
        surfaces += reader.surface
        try {
            suspendCancellableCoroutine { continuation ->
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
                                mediaRecorder?.start()
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
            releaseRecorder()
            reader.close()
            imageReader = null
            throw t
        }
    }

    private fun stopStreamingInternal() {
        try {
            captureSession?.stopRepeating()
        } catch (t: Throwable) {
            Log.w(logTag, "Failed to stop repeating request", t)
        }
        captureSession?.close()
        captureSession = null
        finalizeVideoRecording()
        imageReader?.close()
        imageReader = null
        statusState.value = emptyList()
    }

    private fun closeCamera() {
        try {
            captureSession?.stopRepeating()
        } catch (_: Throwable) {
        }
        captureSession?.close()
        captureSession = null
        imageReader?.close()
        imageReader = null
        finalizeVideoRecording()
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

    private fun prepareRecorder(sessionId: String, size: Size): Surface {
        val recorder = MediaRecorder()
        recorder.setVideoSource(MediaRecorder.VideoSource.SURFACE)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264)
        recorder.setVideoEncodingBitRate(VIDEO_BIT_RATE)
        recorder.setVideoFrameRate(VIDEO_FRAME_RATE)
        recorder.setVideoSize(size.width, size.height)
        val directory = recordingStorage.deviceDirectory(sessionId, deviceId.value)
        val file = File(directory, "rgb-${System.currentTimeMillis()}.mp4")
        recorder.setOutputFile(file.absolutePath)
        recorder.prepare()
        mediaRecorder = recorder
        recorderSurface = recorder.surface
        videoFile = file
        currentSessionId = sessionId
        return recorder.surface
    }

    private fun releaseRecorder() {
        val recorder = mediaRecorder ?: return
        try {
            recorder.reset()
        } catch (_: Throwable) {
        }
        try {
            recorder.release()
        } catch (_: Throwable) {
        }
        mediaRecorder = null
        recorderSurface = null
        videoFile?.delete()
        videoFile = null
        currentSessionId = null
    }

    private fun finalizeVideoRecording() {
        val recorder = mediaRecorder ?: return
        val file = videoFile
        val sessionId = currentSessionId
        try {
            recorder.stop()
        } catch (t: Throwable) {
            Log.w(logTag, "Unable to stop MediaRecorder cleanly", t)
            file?.delete()
        } finally {
            try {
                recorder.reset()
            } catch (_: Throwable) {
            }
            try {
                recorder.release()
            } catch (_: Throwable) {
            }
            mediaRecorder = null
        }
        recorderSurface = null
        videoFile = null
        currentSessionId = null
        if (file != null && file.exists() && sessionId != null) {
            val checksum = digestFile(file)
            val artifact = SessionArtifact(
                deviceId = deviceId,
                streamType = SensorStreamType.RGB_VIDEO,
                file = file,
                mimeType = "video/mp4",
                sizeBytes = file.length(),
                checksumSha256 = checksum
            )
            pendingArtifacts += artifact
            completedSessionId = sessionId
        }
    }

    private fun digestFile(file: File): ByteArray {
        val digest = MessageDigest.getInstance("SHA-256")
        FileInputStream(file).use { fis ->
            DigestInputStream(fis, digest).use { stream ->
                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                while (stream.read(buffer) != -1) {
                }
            }
        }
        return digest.digest()
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
        val image = try {
            reader.acquireLatestImage()
        } catch (t: Throwable) {
            Log.w(logTag, "Failed to acquire RGB frame", t)
            null
        }
        if (image != null) {
            image.close()
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
