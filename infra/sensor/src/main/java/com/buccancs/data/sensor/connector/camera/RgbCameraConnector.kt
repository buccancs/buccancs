package com.buccancs.data.sensor.connector.camera

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.ImageFormat
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CameraMetadata
import android.hardware.camera2.CaptureRequest
import android.hardware.camera2.TotalCaptureResult
import android.media.ImageReader
import android.net.Uri
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Range
import android.util.Size
import android.view.Surface
import com.buccancs.core.result.DeviceCommandResult
import com.buccancs.core.result.Result
import com.buccancs.core.result.resultOf
import com.buccancs.data.sensor.connector.simulated.BaseSimulatedConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import com.buccancs.domain.model.RgbCameraConfig
import com.buccancs.domain.repository.SensorHardwareConfigRepository
import com.buccancs.util.nowInstant
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.FileOutputStream
import java.security.MessageDigest
import java.util.Locale
import java.util.concurrent.ConcurrentLinkedQueue
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.math.absoluteValue
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Instant

@Singleton
class RgbCameraConnector @Inject constructor(
    @ApplicationScope private val appScope: CoroutineScope,
    @ApplicationContext private val context: Context,
    private val cameraManager: CameraManager,
    private val recordingStorage: RecordingStorage,
    private val hardwareConfigRepository: SensorHardwareConfigRepository,
    artifactFactory: SimulatedArtifactFactory
) : BaseSimulatedConnector(
    scope = appScope,
    artifactFactory = artifactFactory,
    initialDevice = SensorDevice(
        id = DeviceId(
            "android-rgb"
        ),
        displayName = "Phone RGB Camera",
        type = SensorDeviceType.ANDROID_RGB_CAMERA,
        capabilities = setOf(
            SensorStreamType.RGB_VIDEO,
            SensorStreamType.RAW_DNG,
            SensorStreamType.PREVIEW
        ),
        connectionStatus = ConnectionStatus.Disconnected,
        isSimulated = false,
        attributes = emptyMap()
    )
) {
    private val logTag =
        "RgbCameraConnector"
    private val settingsLock =
        Any()
    private var cameraSettings =
        CameraSettings()
    private var cameraId: String? =
        null
    private var characteristics: CameraCharacteristics? =
        null
    private var supportsRaw =
        false
    private var cameraDevice: CameraDevice? =
        null
    private var captureSession: CameraCaptureSession? =
        null
    private var previewReader: ImageReader? =
        null
    private var handlerThread: HandlerThread? =
        null
    private var handler: Handler? =
        null
    private var recorder: SegmentedMediaCodecRecorder? =
        null
    private var recorderSurface: Surface? =
        null
    private var currentAnchor: RecordingSessionAnchor? =
        null
    private var currentSessionId: String? =
        null
    private var completedSessionId: String? =
        null
    private val pendingVideoArtifacts =
        mutableListOf<SessionArtifact>()
    private val pendingRawArtifacts =
        mutableListOf<SessionArtifact>()
    private var rawCoordinator: RawCaptureCoordinator? =
        null
    private var latestVideoStatus: SensorStreamStatus? =
        null
    private var latestPreviewStatus: SensorStreamStatus? =
        null
    private var latestRawStatus: SensorStreamStatus? =
        null

    init {
        observeConfig()
    }

    override suspend fun refreshInventory() {
        if (isSimulationMode) {
            super.refreshInventory()
            return
        }
        val detectedId =
            findBackCameraId()
        cameraId =
            detectedId
        characteristics =
            detectedId?.let {
                runCatching {
                    cameraManager.getCameraCharacteristics(
                        it
                    )
                }
                    .onFailure { error ->
                        Log.w(
                            logTag,
                            "Unable to load camera characteristics: ${error.message}"
                        )
                    }
                    .getOrNull()
            }
        supportsRaw =
            characteristics?.let(::detectRawSupport) == true
        synchronized(settingsLock) {
            cameraSettings =
                cameraSettings.coerceForSupport(
                    supportsRaw
                )
        }
        val connection =
            if (cameraDevice != null) {
                val existing =
                    deviceState.value.connectionStatus as? ConnectionStatus.Connected
                ConnectionStatus.Connected(
                    since = existing?.since
                        ?: nowInstant(),
                    batteryPercent = null,
                    rssiDbm = null
                )
            } else {
                ConnectionStatus.Disconnected
            }
        updateDeviceState(
            connection
        )
    }

    override suspend fun applySimulation(
        enabled: Boolean
    ) {
        if (enabled) {
            stopStreamingInternal(
                finalize = false
            )
            closeCamera()
            tearDownThread()
        }
        super.applySimulation(
            enabled
        )
    }

    override suspend fun connect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.connect()
        }
        val result =
            performConnect()
        return when (result) {
            is Result.Success -> DeviceCommandResult.Accepted
            is Result.Failure -> result.toDeviceCommandResult()
        }
    }

    private suspend fun performConnect(): Result<Unit> =
        resultOf {
            val id =
                cameraId
                    ?: findBackCameraId()
                    ?: throw IllegalStateException(
                        "No back-facing camera found."
                    )
            if (cameraDevice != null) {
                return@resultOf
            }
            deviceState.update {
                it.copy(
                    connectionStatus = ConnectionStatus.Connecting,
                    isSimulated = false
                )
            }
            val device =
                openCamera(
                    id
                )
            cameraDevice =
                device
            updateDeviceState(
                ConnectionStatus.Connected(
                    since = nowInstant(),
                    batteryPercent = null,
                    rssiDbm = null
                )
            )
        }

    override suspend fun disconnect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.disconnect()
        }
        val result =
            performDisconnect()
        return when (result) {
            is Result.Success -> DeviceCommandResult.Accepted
            is Result.Failure -> result.toDeviceCommandResult()
        }
    }

    private suspend fun performDisconnect(): Result<Unit> =
        resultOf {
            stopStreamingInternal()
            closeCamera()
            tearDownThread()
            cameraDevice =
                null
            updateDeviceState(
                ConnectionStatus.Disconnected
            )
        }

    override suspend fun configure(
        options: Map<String, String>
    ): DeviceCommandResult {
        if (isSimulationMode) {
            return super.configure(
                options
            )
        }
        val result =
            applyConfiguration(
                options
            )
        return when (result) {
            is Result.Success -> DeviceCommandResult.Accepted
            is Result.Failure -> result.toDeviceCommandResult()
        }
    }

    private suspend fun applyConfiguration(
        options: Map<String, String>
    ): Result<Unit> =
        resultOf {
            val normalized =
                synchronized(settingsLock) {
                    val next =
                        cameraSettings.updateWith(
                            options,
                            supportsRaw
                        )
                    cameraSettings =
                        next
                    next
                }
            hardwareConfigRepository.upsertRgbCamera(
                normalized.toConfig(
                    deviceId.value
                )
            )
            updateDeviceState()
            restartStreamingIfActive()
        }

    override suspend fun startStreaming(
        anchor: RecordingSessionAnchor
    ): DeviceCommandResult {
        if (isSimulationMode) {
            return super.startStreaming(
                anchor
            )
        }
        val result =
            performStartStreaming(
                anchor
            )
        return when (result) {
            is Result.Success -> DeviceCommandResult.Accepted
            is Result.Failure -> result.toDeviceCommandResult()
        }
    }

    private suspend fun performStartStreaming(
        anchor: RecordingSessionAnchor
    ): Result<Unit> =
        resultOf {
            val device =
                cameraDevice
                    ?: run {
                        val connectResult =
                            connect()
                        if (connectResult !is DeviceCommandResult.Accepted) {
                            throw IllegalStateException(
                                "Unable to connect to RGB camera."
                            )
                        }
                        cameraDevice
                            ?: throw IllegalStateException(
                                "Camera device unavailable after connection."
                            )
                    }
            val id =
                cameraId
                    ?: findBackCameraId()
                    ?: throw IllegalStateException(
                        "No back-facing camera found."
                    )
            configureAndStartSession(
                device,
                id,
                anchor
            )
        }

    override suspend fun stopStreaming(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.stopStreaming()
        }
        val result =
            performStopStreaming()
        return when (result) {
            is Result.Success -> DeviceCommandResult.Accepted
            is Result.Failure -> result.toDeviceCommandResult()
        }
    }

    private suspend fun performStopStreaming(): Result<Unit> =
        resultOf {
            stopStreamingInternal()
        }

    override suspend fun collectArtifacts(
        sessionId: String
    ): List<SessionArtifact> {
        if (isSimulationMode) {
            return super.collectArtifacts(
                sessionId
            )
        }
        if (sessionId != completedSessionId) {
            return emptyList()
        }
        completedSessionId =
            null
        val results =
            mutableListOf<SessionArtifact>()
        synchronized(pendingVideoArtifacts) {
            results += pendingVideoArtifacts
            pendingVideoArtifacts.clear()
        }
        synchronized(pendingRawArtifacts) {
            results += pendingRawArtifacts
            pendingRawArtifacts.clear()
        }
        return results
    }

    @SuppressLint(
        "MissingPermission"
    )
    private suspend fun openCamera(
        id: String
    ): CameraDevice {
        ensureHandlerThread()
        val looperHandler =
            handler
                ?: throw IllegalStateException(
                    "Camera handler not initialised."
                )
        return suspendCancellableCoroutine { continuation ->
            try {
                cameraManager.openCamera(
                    id,
                    object :
                        CameraDevice.StateCallback() {
                        override fun onOpened(
                            device: CameraDevice
                        ) {
                            continuation.resume(
                                device
                            )
                        }

                        override fun onDisconnected(
                            device: CameraDevice
                        ) {
                            Log.w(
                                logTag,
                                "Camera disconnected."
                            )
                            device.close()
                            if (continuation.isActive) {
                                continuation.resumeWithException(
                                    CameraAccessException(
                                        CameraAccessException.CAMERA_DISCONNECTED
                                    )
                                )
                            } else {
                                updateDeviceState(
                                    ConnectionStatus.Disconnected
                                )
                            }
                        }

                        override fun onError(
                            device: CameraDevice,
                            error: Int
                        ) {
                            Log.e(
                                logTag,
                                "Camera error $error."
                            )
                            device.close()
                            val exception =
                                CameraAccessException(
                                    CameraAccessException.CAMERA_ERROR,
                                    "Camera error $error"
                                )
                            if (continuation.isActive) {
                                continuation.resumeWithException(
                                    exception
                                )
                            } else {
                                updateDeviceState(
                                    ConnectionStatus.Disconnected
                                )
                            }
                        }
                    },
                    looperHandler
                )
            } catch (t: SecurityException) {
                continuation.resumeWithException(
                    t
                )
            } catch (t: CameraAccessException) {
                continuation.resumeWithException(
                    t
                )
            }
        }
    }

    private suspend fun configureAndStartSession(
        device: CameraDevice,
        id: String,
        anchor: RecordingSessionAnchor
    ) {
        stopStreamingInternal(
            finalize = false
        )
        val handler =
            handler
                ?: throw IllegalStateException(
                    "Camera handler not initialised."
                )
        val cameraCharacteristics =
            characteristics
                ?: runCatching {
                    cameraManager.getCameraCharacteristics(
                        id
                    )
                }
                    .onFailure { error ->
                        Log.w(
                            logTag,
                            "Unable to load characteristics on session start: ${error.message}"
                        )
                    }
                    .getOrNull()
        characteristics =
            cameraCharacteristics
        supportsRaw =
            cameraCharacteristics?.let(::detectRawSupport) == true
        synchronized(settingsLock) {
            cameraSettings =
                cameraSettings.coerceForSupport(
                    supportsRaw
                )
        }
        val settings =
            resolvedSettings()
        val previewSize =
            cameraCharacteristics?.let {
                chooseVideoSize(
                    it
                )
            }
                ?: Size(
                    1920,
                    1080
                )
        val preview =
            ImageReader.newInstance(
                previewSize.width,
                previewSize.height,
                ImageFormat.YUV_420_888,
                3
            )
        previewReader =
            preview
        preview.setOnImageAvailableListener(
            previewListener,
            handler
        )

        val recorder =
            SegmentedMediaCodecRecorder(
                context = context,
                storage = recordingStorage,
                sessionId = anchor.sessionId,
                deviceId = deviceId,
                anchorEpochMs = anchor.referenceTimestamp.toEpochMilliseconds(),
                size = previewSize,
                bitRate = settings.videoBitRate,
                frameRate = settings.videoFps
            )
        val recorderSurface =
            recorder.start()
        this.recorder =
            recorder
        this.recorderSurface =
            recorderSurface
        currentAnchor =
            anchor
        currentSessionId =
            anchor.sessionId

        val rawCoordinatorCandidate =
            if (settings.rawEnabled && supportsRaw && cameraCharacteristics != null) {
                RawCaptureCoordinator(
                    sessionId = anchor.sessionId,
                    device = device,
                    deviceId = deviceId,
                    handler = handler,
                    characteristics = cameraCharacteristics,
                    settings = settings,
                    storage = recordingStorage,
                    onArtifact = ::onRawArtifactCaptured,
                    onStatus = ::onRawStatus
                )
            } else {
                null
            }

        val surfaces =
            mutableListOf(
                recorderSurface,
                preview.surface
            )
        rawCoordinatorCandidate?.let {
            surfaces += it.surface
        }

        suspendCancellableCoroutine<Unit> { continuation ->
            try {
                device.createCaptureSession(
                    surfaces,
                    object :
                        CameraCaptureSession.StateCallback() {
                        override fun onConfigured(
                            session: CameraCaptureSession
                        ) {
                            captureSession =
                                session
                            val request =
                                device.createCaptureRequest(
                                    CameraDevice.TEMPLATE_RECORD
                                )
                                    .apply {
                                        addTarget(
                                            recorderSurface
                                        )
                                        addTarget(
                                            preview.surface
                                        )
                                        applySettings(
                                            this,
                                            settings,
                                            cameraCharacteristics
                                        )
                                    }
                                    .build()
                            try {
                                session.setRepeatingRequest(
                                    request,
                                    null,
                                    handler
                                )
                                rawCoordinatorCandidate?.attach(
                                    session
                                )
                                rawCoordinator =
                                    rawCoordinatorCandidate
                                continuation.resume(
                                    Unit
                                )
                            } catch (t: Throwable) {
                                Log.e(
                                    logTag,
                                    "Unable to start RGB capture session",
                                    t
                                )
                                continuation.resumeWithException(
                                    t
                                )
                            }
                        }

                        override fun onConfigureFailed(
                            session: CameraCaptureSession
                        ) {
                            continuation.resumeWithException(
                                IllegalStateException(
                                    "Failed to configure RGB capture session."
                                )
                            )
                        }
                    },
                    handler
                )
            } catch (t: Throwable) {
                continuation.resumeWithException(
                    t
                )
            }
        }
        latestVideoStatus =
            null
        latestPreviewStatus =
            null
        latestRawStatus =
            if (settings.rawEnabled && supportsRaw) {
                buildRawStatus(
                    nowInstant()
                )
            } else {
                null
            }
        publishStatuses()
    }

    private fun stopStreamingInternal(
        finalize: Boolean = true
    ) {
        rawCoordinator?.shutdown()
        rawCoordinator =
            null
        captureSession?.run {
            runCatching {
                stopRepeating()
            }
            runCatching {
                close()
            }
        }
        captureSession =
            null
        previewReader?.close()
        previewReader =
            null
        val recorder =
            recorder
        val sessionId =
            currentSessionId
        if (recorder != null && sessionId != null) {
            val artifacts =
                if (finalize) {
                    runCatching {
                        runBlocking {
                            withContext(Dispatchers.Default) {
                                recorder.stop()
                            }
                        }
                    }
                        .onFailure { error ->
                            Log.e(
                                logTag,
                                "Recorder stop failed: ${error.message}",
                                error
                            )
                        }
                        .getOrElse {
                            emptyList()
                        }
                } else {
                    recorder.abort()
                    emptyList()
                }
            synchronized(pendingVideoArtifacts) {
                pendingVideoArtifacts += artifacts
            }
            if (artifacts.isNotEmpty() || pendingRawArtifacts.isNotEmpty()) {
                completedSessionId =
                    sessionId
            }
        }
        recorderSurface?.release()
        recorderSurface =
            null
        this.recorder =
            null
        if (!finalize) {
            synchronized(pendingVideoArtifacts) {
                pendingVideoArtifacts.clear()
            }
            synchronized(pendingRawArtifacts) {
                pendingRawArtifacts.clear()
            }
        }
        currentAnchor =
            null
        currentSessionId =
            null
        latestVideoStatus =
            null
        latestPreviewStatus =
            null
        latestRawStatus =
            null
        publishStatuses()
    }

    private fun closeCamera() {
        stopStreamingInternal(
            finalize = false
        )
        cameraDevice?.close()
        cameraDevice =
            null
    }

    private fun ensureHandlerThread() {
        if (handlerThread != null && handler != null) {
            return
        }
        val thread =
            HandlerThread(
                "RgbCameraThread"
            ).apply { start() }
        handlerThread =
            thread
        handler =
            Handler(
                thread.looper
            )
    }

    private fun tearDownThread() {
        handlerThread?.quitSafely()
        handlerThread =
            null
        handler =
            null
    }

    private fun findBackCameraId(): String? =
        runCatching {
            cameraManager.cameraIdList.firstOrNull { id ->
                val info =
                    cameraManager.getCameraCharacteristics(
                        id
                    )
                val facing =
                    info.get(
                        CameraCharacteristics.LENS_FACING
                    )
                facing == CameraCharacteristics.LENS_FACING_BACK
            }
        }
            .onFailure { error ->
                Log.e(
                    logTag,
                    "Unable to enumerate cameras",
                    error
                )
            }
            .getOrNull()

    private fun chooseVideoSize(
        characteristics: CameraCharacteristics
    ): Size {
        val map =
            characteristics.get(
                CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP
            )
                ?: return Size(
                    1920,
                    1080
                )
        val choices =
            map.getOutputSizes(
                ImageFormat.YUV_420_888
            )
                ?: return Size(
                    1920,
                    1080
                )
        val preferred =
            choices.firstOrNull { it.width >= 1920 && it.height >= 1080 }
        return preferred
            ?: choices.maxByOrNull { it.width * it.height }
            ?: Size(
                1280,
                720
            )
    }

    private fun detectRawSupport(
        info: CameraCharacteristics
    ): Boolean {
        val caps =
            info.get(
                CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES
            )
                ?: return false
        return caps.any { it == CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_RAW }
    }

    private fun selectFpsRange(
        info: CameraCharacteristics?,
        targetFps: Int
    ): Range<Int>? {
        if (info == null || targetFps <= 0) return null
        val ranges =
            info.get(
                CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES
            )
                ?: return null
        val exact =
            ranges.firstOrNull { range ->
                range.lower <= targetFps && range.upper >= targetFps
            }
        return exact
            ?: ranges.maxByOrNull { range ->
                val clamped =
                    targetFps.coerceIn(
                        range.lower,
                        range.upper
                    )
                clamped
            }
    }

    private fun applySettings(
        builder: CaptureRequest.Builder,
        settings: CameraSettings,
        info: CameraCharacteristics?
    ) {
        builder.set(
            CaptureRequest.CONTROL_MODE,
            CaptureRequest.CONTROL_MODE_AUTO
        )
        builder.set(
            CaptureRequest.CONTROL_AF_MODE,
            CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_VIDEO
        )
        val awbMode =
            settings.awbMode.requestValue
        if (awbMode != null) {
            builder.set(
                CaptureRequest.CONTROL_AWB_MODE,
                awbMode
            )
        }
        val fpsRange =
            selectFpsRange(
                info,
                settings.videoFps
            )
        fpsRange?.let {
            builder.set(
                CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE,
                it
            )
        }
        val exposure =
            settings.exposureTimeNs
        val iso =
            settings.iso
        if (exposure != null || iso != null) {
            builder.set(
                CaptureRequest.CONTROL_AE_MODE,
                CaptureRequest.CONTROL_AE_MODE_OFF
            )
            exposure?.let {
                builder.set(
                    CaptureRequest.SENSOR_EXPOSURE_TIME,
                    it
                )
            }
            iso?.let {
                builder.set(
                    CaptureRequest.SENSOR_SENSITIVITY,
                    it
                )
            }
        } else {
            builder.set(
                CaptureRequest.CONTROL_AE_MODE,
                CaptureRequest.CONTROL_AE_MODE_ON
            )
        }
        settings.focusDistanceMeters?.let { meters ->
            val distance =
                if (meters > 0.0) {
                    (1.0 / meters).toFloat()
                } else {
                    0f
                }
            builder.set(
                CaptureRequest.LENS_FOCUS_DISTANCE,
                distance
            )
        }
    }

    private val previewListener =
        ImageReader.OnImageAvailableListener { reader ->
            try {
                reader.acquireLatestImage()
                    ?.close()
            } catch (t: Throwable) {
                Log.w(
                    logTag,
                    "Failed to acquire RGB preview frame",
                    t
                )
            }
            val now =
                nowInstant()
            latestVideoStatus =
                SensorStreamStatus(
                    deviceId = deviceId,
                    streamType = SensorStreamType.RGB_VIDEO,
                    sampleRateHz = null,
                    frameRateFps = resolvedSettings().videoFps.toDouble(),
                    lastSampleTimestamp = now,
                    bufferedDurationSeconds = 0.0,
                    isStreaming = true,
                    isSimulated = false
                )
            latestPreviewStatus =
                SensorStreamStatus(
                    deviceId = deviceId,
                    streamType = SensorStreamType.PREVIEW,
                    sampleRateHz = null,
                    frameRateFps = 15.0,
                    lastSampleTimestamp = now,
                    bufferedDurationSeconds = 0.0,
                    isStreaming = true,
                    isSimulated = false
                )
            publishStatuses()
        }

    private fun publishStatuses() {
        statusState.value =
            listOfNotNull(
                latestVideoStatus,
                latestRawStatus,
                latestPreviewStatus
            )
    }

    override fun streamIntervalMs(): Long {
        val fps =
            resolvedSettings().videoFps.coerceAtLeast(
                1
            )
        val intervalMs =
            (1_000.0 / fps)
                .coerceAtLeast(
                    1.0
                )
                .toLong()
        return intervalMs.coerceIn(
            8L,
            1_000L
        )
    }

    override fun simulatedBatteryPercent(
        device: SensorDevice
    ): Int? =
        null

    override fun simulatedRssi(
        device: SensorDevice
    ): Int? =
        null

    override fun sampleStatuses(
        timestamp: Instant,
        frameCounter: Long,
        anchor: RecordingSessionAnchor
    ): List<SensorStreamStatus> {
        val settings =
            resolvedSettings()
        val randomSeed =
            (deviceId.value.hashCode() + frameCounter).absoluteValue % 1_000
        val rgbStatus =
            SensorStreamStatus(
                deviceId = deviceId,
                streamType = SensorStreamType.RGB_VIDEO,
                sampleRateHz = null,
                frameRateFps = settings.videoFps.toDouble(),
                lastSampleTimestamp = timestamp,
                bufferedDurationSeconds = simulatedBufferedSeconds(
                    streamType = SensorStreamType.RGB_VIDEO,
                    baseVideo = 0.5,
                    baseSample = 0.0
                ) {
                    randomSeed / 5_000.0
                },
                isStreaming = true,
                isSimulated = true
            )
        val previewStatus =
            SensorStreamStatus(
                deviceId = deviceId,
                streamType = SensorStreamType.PREVIEW,
                sampleRateHz = null,
                frameRateFps = 15.0,
                lastSampleTimestamp = timestamp,
                bufferedDurationSeconds = simulatedBufferedSeconds(
                    streamType = SensorStreamType.PREVIEW,
                    baseVideo = 0.4,
                    baseSample = 0.0
                ) {
                    randomSeed / 10_000.0
                },
                isStreaming = true,
                isSimulated = true
            )
        val statuses =
            mutableListOf(
                rgbStatus,
                previewStatus
            )
        if (settings.rawEnabled) {
            val intervalMs =
                settings.rawIntervalMs.coerceAtLeast(
                    MIN_RAW_INTERVAL_MS
                )
            statuses +=
                SensorStreamStatus(
                    deviceId = deviceId,
                    streamType = SensorStreamType.RAW_DNG,
                    sampleRateHz = null,
                    frameRateFps = 1_000.0 / intervalMs,
                    lastSampleTimestamp = timestamp,
                    bufferedDurationSeconds = simulatedBufferedSeconds(
                        streamType = SensorStreamType.RAW_DNG,
                        baseVideo = 0.2,
                        baseSample = 0.0
                    ) {
                        randomSeed / 20_000.0
                    },
                    isStreaming = true,
                    isSimulated = true
                )
        }
        return statuses
    }

    private fun buildRawStatus(
        timestamp: Instant
    ): SensorStreamStatus {
        val intervalMs =
            resolvedSettings().rawIntervalMs.coerceAtLeast(
                MIN_RAW_INTERVAL_MS
            )
        val fps =
            1_000.0 / intervalMs
        return SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.RAW_DNG,
            sampleRateHz = null,
            frameRateFps = fps,
            lastSampleTimestamp = timestamp,
            bufferedDurationSeconds = 0.0,
            isStreaming = true,
            isSimulated = false
        )
    }

    private fun onRawStatus(
        timestamp: Instant
    ) {
        latestRawStatus =
            buildRawStatus(
                timestamp
            )
        publishStatuses()
    }

    private fun onRawArtifactCaptured(
        artifact: SessionArtifact
    ) {
        synchronized(pendingRawArtifacts) {
            pendingRawArtifacts += artifact
        }
        completedSessionId =
            currentSessionId
    }

    private fun updateDeviceState(
        connection: ConnectionStatus? = null
    ) {
        val settings =
            resolvedSettings()
        val attributes =
            buildAttributes(
                settings
            )
        deviceState.update { current ->
            current.copy(
                attributes = attributes,
                connectionStatus = connection ?: current.connectionStatus,
                isSimulated = false
            )
        }
    }

    private fun buildAttributes(
        settings: CameraSettings
    ): Map<String, String> =
        buildMap {
            cameraId?.let {
                put(
                    "cameraId",
                    it
                )
            }
            put(
                ATTR_RGB_VIDEO_FPS,
                settings.videoFps.toString()
            )
            put(
                ATTR_RGB_VIDEO_BITRATE,
                settings.videoBitRate.toString()
            )
            put(
                ATTR_RGB_RAW_ENABLED,
                settings.rawEnabled.toString()
            )
            put(
                ATTR_RGB_RAW_INTERVAL,
                settings.rawIntervalMs.toString()
            )
            settings.exposureTimeNs?.let {
                put(
                    ATTR_RGB_EXPOSURE,
                    it.toString()
                )
            }
            settings.iso?.let {
                put(
                    ATTR_RGB_ISO,
                    it.toString()
                )
            }
            settings.focusDistanceMeters?.let {
                put(
                    ATTR_RGB_FOCUS_METERS,
                    it.toString()
                )
            }
            put(
                ATTR_RGB_AWB,
                settings.awbMode.token
            )
            put(
                ATTR_RGB_RAW_SUPPORTED,
                supportsRaw.toString()
            )
        }

    private fun resolvedSettings(): CameraSettings =
        synchronized(settingsLock) {
            cameraSettings.coerceForSupport(
                supportsRaw
            )
        }

    private fun observeConfig() {
        appScope.launch {
            hardwareConfigRepository.config.collectLatest { config ->
                val target =
                    config.rgb.firstOrNull { it.id == deviceId.value }
                synchronized(settingsLock) {
                    cameraSettings =
                        CameraSettings.fromConfig(
                            target
                        ).coerceForSupport(
                            supportsRaw
                        )
                }
                updateDeviceState()
            }
        }
    }

    private fun restartStreamingIfActive() {
        val anchor =
            currentAnchor
        if (anchor == null || captureSession == null) {
            return
        }
        appScope.launch {
            performStopStreaming()
            performStartStreaming(
                anchor
            )
        }
    }

    private fun Result.Failure.toDeviceCommandResult(): DeviceCommandResult =
        DeviceCommandResult.Failed(
            error.toException()
        )

    private inner class RawCaptureCoordinator(
        private val sessionId: String,
        private val device: CameraDevice,
        private val deviceId: DeviceId,
        private val handler: Handler,
        private val characteristics: CameraCharacteristics,
        private val settings: CameraSettings,
        private val storage: RecordingStorage,
        private val onArtifact: (SessionArtifact) -> Unit,
        private val onStatus: (Instant) -> Unit
    ) {
        private val reader =
            ImageReader.newInstance(
                settings.rawResolutionWidth(characteristics),
                settings.rawResolutionHeight(characteristics),
                ImageFormat.RAW_SENSOR,
                2
            )
        private val pendingResults =
            ConcurrentLinkedQueue<TotalCaptureResult>()
        private var captureJob: Job? =
            null
        private val captureCallback =
            object :
                CameraCaptureSession.CaptureCallback() {
                override fun onCaptureCompleted(
                    session: CameraCaptureSession,
                    request: CaptureRequest,
                    result: TotalCaptureResult
                ) {
                    pendingResults.add(
                        result
                    )
                }
            }

        val surface: Surface =
            reader.surface

        init {
            reader.setOnImageAvailableListener(
                this::onImageAvailable,
                handler
            )
        }

        fun attach(
            session: CameraCaptureSession
        ) {
            captureJob =
                appScope.launch {
                    val intervalMs =
                        settings.rawIntervalMs.coerceAtLeast(
                            MIN_RAW_INTERVAL_MS
                        )
                    delay(intervalMs.milliseconds)
                    while (isActive) {
                        try {
                            val builder =
                                device.createCaptureRequest(
                                    CameraDevice.TEMPLATE_STILL_CAPTURE
                                )
                            builder.addTarget(
                                reader.surface
                            )
                            applySettings(
                                builder,
                                settings,
                                characteristics
                            )
                            session.capture(
                                builder.build(),
                                captureCallback,
                                handler
                            )
                        } catch (t: Throwable) {
                            Log.w(
                                logTag,
                                "RAW capture trigger failed: ${t.message}"
                            )
                        }
                        delay(intervalMs.milliseconds)
                    }
                }
        }

        fun shutdown() {
            captureJob?.cancel()
            captureJob =
                null
            reader.close()
        }

        private fun onImageAvailable(
            reader: ImageReader
        ) {
            val image =
                try {
                    reader.acquireLatestImage()
                } catch (t: Throwable) {
                    Log.w(
                        logTag,
                        "Unable to acquire RAW image",
                        t
                    )
                    null
                }
            if (image == null) {
                return
            }
            val captureResult =
                pendingResults.poll()
            if (captureResult == null) {
                image.close()
                return
            }
            try {
                val file =
                    storage.createArtifactFile(
                        sessionId = sessionId,
                        deviceId = deviceId.value,
                        streamType = SensorStreamType.RAW_DNG.name.lowercase(),
                        timestampEpochMs = nowInstant().toEpochMilliseconds(),
                        extension = "dng"
                    )
                FileOutputStream(
                    file
                ).use { output ->
                    val creator =
                        android.hardware.camera2.DngCreator(
                            characteristics,
                            captureResult
                        )
                    creator.writeImage(
                        output,
                        image
                    )
                    creator.close()
                }
                val digest =
                    MessageDigest.getInstance(
                        "SHA-256"
                    )
                file.inputStream()
                    .use { stream ->
                        val buffer =
                            ByteArray(
                                DEFAULT_BUFFER_SIZE
                            )
                        while (true) {
                            val read =
                                stream.read(
                                    buffer
                                )
                            if (read == -1) break
                            if (read > 0) {
                                digest.update(
                                    buffer,
                                    0,
                                    read
                                )
                            }
                        }
                    }
                val checksum =
                    runCatching {
                        digest.digest()
                    }
                        .onFailure { error ->
                            Log.w(
                                logTag,
                                "Checksum generation failed: ${error.message}"
                            )
                        }
                        .getOrDefault(
                            ByteArray(0)
                        )
                val artifact =
                    SessionArtifact(
                        deviceId = deviceId,
                        streamType = SensorStreamType.RAW_DNG,
                        uri = Uri.fromFile(
                            file
                        ),
                        file = file,
                        mimeType = "image/x-adobe-dng",
                        sizeBytes = file.length(),
                        checksumSha256 = checksum,
                        metadata = mapOf(
                            "width" to image.width.toString(),
                            "height" to image.height.toString()
                        )
                    )
                onArtifact(
                    artifact
                )
                onStatus(
                    nowInstant()
                )
            } catch (t: Throwable) {
                Log.e(
                    logTag,
                    "Failed to persist RAW capture",
                    t
                )
            } finally {
                image.close()
            }
        }
    }

    private fun CameraSettings.rawResolutionWidth(
        info: CameraCharacteristics
    ): Int {
        val sizes =
            info.get(
                CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP
            )
                ?.getOutputSizes(
                    ImageFormat.RAW_SENSOR
                )
                ?: return 1920
        return sizes.maxByOrNull { it.width * it.height }?.width
            ?: 1920
    }

    private fun CameraSettings.rawResolutionHeight(
        info: CameraCharacteristics
    ): Int {
        val sizes =
            info.get(
                CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP
            )
                ?.getOutputSizes(
                    ImageFormat.RAW_SENSOR
                )
                ?: return 1080
        return sizes.maxByOrNull { it.width * it.height }?.height
            ?: 1080
    }

    private data class CameraSettings(
        val videoFps: Int = 60,
        val videoBitRate: Int = 75_000_000,
        val rawEnabled: Boolean = false,
        val rawIntervalMs: Long = 1_000,
        val exposureTimeNs: Long? = null,
        val iso: Int? = null,
        val focusDistanceMeters: Double? = null,
        val awbMode: WhiteBalanceMode = WhiteBalanceMode.AUTO
    ) {
        fun toConfig(
            id: String
        ): RgbCameraConfig =
            RgbCameraConfig(
                id = id,
                displayName = "Phone RGB Camera",
                videoFps = videoFps,
                videoBitRate = videoBitRate,
                rawEnabled = rawEnabled,
                rawIntervalMs = rawIntervalMs,
                exposureTimeNs = exposureTimeNs,
                iso = iso,
                focusDistanceMeters = focusDistanceMeters,
                awbMode = awbMode.token
            )

        fun updateWith(
            options: Map<String, String>,
            supportsRaw: Boolean
        ): CameraSettings {
            var next =
                this
            options["video_fps"]?.toIntOrNull()
                ?.takeIf { it > 0 }
                ?.let { next = next.copy(videoFps = it) }
            options["video_bitrate"]?.toIntOrNull()
                ?.takeIf { it > 0 }
                ?.let { next = next.copy(videoBitRate = it) }
            options["raw_enabled"]?.let { token ->
                parseBooleanFlag(
                    token
                )
                    ?.let { enabled ->
                        next =
                            next.copy(
                                rawEnabled = enabled && supportsRaw
                            )
                    }
            }
            options["raw_interval_ms"]?.toLongOrNull()
                ?.takeIf { it > 0 }
                ?.let { next = next.copy(rawIntervalMs = it) }
            options["exposure_time_ns"]?.toLongOrNull()
                ?.let { next = next.copy(exposureTimeNs = it) }
            options["iso"]?.toIntOrNull()
                ?.takeIf { it > 0 }
                ?.let { next = next.copy(iso = it) }
            options["focus_distance_m"]?.toDoubleOrNull()
                ?.takeIf { it > 0.0 }
                ?.let { next = next.copy(focusDistanceMeters = it) }
            options["awb_mode"]?.let { token ->
                next =
                    next.copy(
                        awbMode = WhiteBalanceMode.fromToken(
                            token
                        )
                    )
            }
            return next.coerceForSupport(
                supportsRaw
            )
        }

        fun coerceForSupport(
            supportsRaw: Boolean
        ): CameraSettings =
            if (supportsRaw) {
                copy(
                    rawIntervalMs = rawIntervalMs.coerceAtLeast(
                        MIN_RAW_INTERVAL_MS
                    )
                )
            } else {
                copy(
                    rawEnabled = false
                )
            }

        companion object {
            fun fromConfig(
                config: RgbCameraConfig?
            ): CameraSettings =
                when (config) {
                    null -> CameraSettings()
                    else -> CameraSettings(
                        videoFps = config.videoFps ?: 60,
                        videoBitRate = config.videoBitRate ?: 75_000_000,
                        rawEnabled = config.rawEnabled ?: false,
                        rawIntervalMs = config.rawIntervalMs ?: 1_000,
                        exposureTimeNs = config.exposureTimeNs,
                        iso = config.iso,
                        focusDistanceMeters = config.focusDistanceMeters,
                        awbMode = WhiteBalanceMode.fromToken(
                            config.awbMode
                        )
                    )
                }

            private fun parseBooleanFlag(
                value: String
            ): Boolean? =
                when (value.lowercase(Locale.US)) {
                    "true",
                    "1",
                    "yes",
                    "on" -> true

                    "false",
                    "0",
                    "no",
                    "off" -> false

                    else -> null
                }
        }
    }

    private enum class WhiteBalanceMode(
        val token: String,
        val requestValue: Int?
    ) {
        AUTO("auto", CameraMetadata.CONTROL_AWB_MODE_AUTO),
        DAYLIGHT("daylight", CameraMetadata.CONTROL_AWB_MODE_DAYLIGHT),
        CLOUDY("cloudy", CameraMetadata.CONTROL_AWB_MODE_CLOUDY_DAYLIGHT),
        SHADE("shade", CameraMetadata.CONTROL_AWB_MODE_SHADE),
        INCANDESCENT("incandescent", CameraMetadata.CONTROL_AWB_MODE_INCANDESCENT),
        FLUORESCENT("fluorescent", CameraMetadata.CONTROL_AWB_MODE_FLUORESCENT),
        TWILIGHT("twilight", CameraMetadata.CONTROL_AWB_MODE_TWILIGHT),
        WARM("warm", CameraMetadata.CONTROL_AWB_MODE_WARM_FLUORESCENT),
        OFF("off", CameraMetadata.CONTROL_AWB_MODE_OFF);

        companion object {
            fun fromToken(
                token: String?
            ): WhiteBalanceMode =
                values().firstOrNull {
                    it.token.equals(
                        token,
                        ignoreCase = true
                    )
                }
                    ?: AUTO
        }
    }

    private companion object {
        private const val ATTR_RGB_VIDEO_FPS =
            "rgb.video_fps"
        private const val ATTR_RGB_VIDEO_BITRATE =
            "rgb.video_bitrate"
        private const val ATTR_RGB_RAW_ENABLED =
            "rgb.raw_enabled"
        private const val ATTR_RGB_RAW_INTERVAL =
            "rgb.raw_interval_ms"
        private const val ATTR_RGB_EXPOSURE =
            "rgb.exposure_time_ns"
        private const val ATTR_RGB_ISO =
            "rgb.iso"
        private const val ATTR_RGB_AWB =
            "rgb.awb_mode"
        private const val ATTR_RGB_FOCUS_METERS =
            "rgb.focus_distance_m"
        private const val ATTR_RGB_RAW_SUPPORTED =
            "rgb.raw_supported"
        private const val DEFAULT_BUFFER_SIZE =
            256 * 1024
        private const val MIN_RAW_INTERVAL_MS =
            500L
    }
}
