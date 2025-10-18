package com.buccancs.hardware.topdon

import android.content.BroadcastReceiver
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Build
import android.os.Handler
import android.util.Log
import com.buccancs.core.result.Result
import com.buccancs.core.result.resultOf
import com.buccancs.di.ApplicationScope
import com.infisense.iruvc.ircmd.ConcreteIRCMDBuilder
import com.infisense.iruvc.ircmd.IRCMD
import com.infisense.iruvc.ircmd.IRCMDType
import com.infisense.iruvc.ircmd.ResultCode
import com.infisense.iruvc.usb.USBMonitor
import com.infisense.iruvc.utils.CommonParams
import com.infisense.iruvc.utils.IFrameCallback
import com.infisense.iruvc.utils.OnCreateResultCallback
import com.infisense.iruvc.uvc.ConcreateUVCBuilder
import com.infisense.iruvc.uvc.UVCCamera
import com.infisense.iruvc.uvc.UVCType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultTopdonThermalClient @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val usbManager: UsbManager,
) : TopdonThermalClient {

    private val logTag =
        "TopdonHardware"
    private val usbContext: Context =
        object :
            ContextWrapper(
                context
            ) {
            override fun registerReceiver(
                receiver: BroadcastReceiver?,
                filter: IntentFilter?
            ): Intent? {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    super.registerReceiver(
                        receiver,
                        filter,
                        Context.RECEIVER_NOT_EXPORTED
                    )
                } else {
                    @Suppress(
                        "DEPRECATION"
                    )
                    super.registerReceiver(
                        receiver,
                        filter
                    )
                }
            }

            override fun registerReceiver(
                receiver: BroadcastReceiver?,
                filter: IntentFilter?,
                broadcastPermission: String?,
                scheduler: Handler?
            ): Intent? {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    super.registerReceiver(
                        receiver,
                        filter,
                        broadcastPermission,
                        scheduler,
                        Context.RECEIVER_NOT_EXPORTED
                    )
                } else {
                    @Suppress(
                        "DEPRECATION"
                    )
                    super.registerReceiver(
                        receiver,
                        filter,
                        broadcastPermission,
                        scheduler
                    )
                }
            }
        }

    private val statusState =
        MutableStateFlow<TopdonStatus>(
            TopdonStatus.Idle
        )
    private val previewFlow =
        MutableSharedFlow<TopdonPreviewFrame>(
            extraBufferCapacity = 8
        )
    private val streamFlow =
        MutableSharedFlow<TopdonStreamEvent>(
            extraBufferCapacity = 16
        )
    private val noticeFlow =
        MutableSharedFlow<TopdonNotice>(
            extraBufferCapacity = 8
        )

    override val status: StateFlow<TopdonStatus> =
        statusState.asStateFlow()
    override val previewFrames =
        previewFlow.asSharedFlow()
    override val streamEvents =
        streamFlow.asSharedFlow()
    override val notices =
        noticeFlow.asSharedFlow()

    private var usbMonitor: USBMonitor? =
        null
    private var usbControlBlock: USBMonitor.UsbControlBlock? =
        null
    private var uvcCamera: UVCCamera? =
        null
    private var ircmd: IRCMD? =
        null

    private var connectedDevice: UsbDevice? =
        null
    private var connectedSinceEpochMs: Long =
        -1L

    private val monitorRegistered =
        AtomicBoolean(
            false
        )
    private var previewEnabled =
        false
    private var streamingEnabled =
        false
    private var streamJob: Job? =
        null

    private var previewFpsLimit: Int =
        DEFAULT_PREVIEW_FPS
    private var superSampling: Int =
        1
    private var currentPalette: Palette =
        Palette.Gray

    private var streamFrameCounter: Long =
        0
    private var streamBytes: Long =
        0
    private var lastMetrics: TopdonTemperatureMetrics =
        TopdonTemperatureMetrics.Unknown
    private var lastPreviewEmitMs: Long =
        0

    private val frameCallback =
        IFrameCallback { data ->
            if (data == null || data.isEmpty()) return@IFrameCallback
            if (!previewEnabled && !streamingEnabled) return@IFrameCallback
            val payload =
                data.copyOf()
            val metrics =
                computeMetrics(
                    payload
                )
            val timestamp =
                System.currentTimeMillis()
            if (streamingEnabled) {
                streamFrameCounter += 1
                streamBytes += payload.size
                lastMetrics =
                    metrics
                applicationScope.launch {
                    streamFlow.emit(
                        TopdonStreamEvent(
                            timestampEpochMs = timestamp,
                            frameCount = streamFrameCounter,
                            bytesCaptured = streamBytes,
                            metrics = metrics,
                            payload = payload,
                            endOfStream = false
                        )
                    )
                }
            }
            if (previewEnabled && shouldEmitPreview(
                    timestamp
                )
            ) {
                lastPreviewEmitMs =
                    timestamp
                val frame =
                    TopdonPreviewFrame(
                        timestampEpochMs = timestamp,
                        width = FRAME_WIDTH,
                        height = FRAME_HEIGHT,
                        payload = payload,
                        metrics = metrics,
                        palette = currentPalette,
                        superSamplingFactor = superSampling,
                        mimeType = "application/octet-stream"
                    )
                applicationScope.launch {
                    previewFlow.emit(
                        frame
                    )
                }
            }
            updateStreamingStatus()
        }

    private val usbListener =
        object :
            USBMonitor.OnDeviceConnectListener {
            override fun onAttach(
                device: UsbDevice
            ) {
                applicationScope.launch {
                    emitNotice(
                        "USB device attached: ${device.deviceName}",
                        TopdonNotice.Category.Info
                    )
                    ensureMonitor()
                    usbMonitor?.requestPermission(
                        device
                    )
                }
            }

            override fun onGranted(
                usbDevice: UsbDevice,
                granted: Boolean
            ) {
                if (granted) {
                    emitNotice(
                        "USB permission granted for ${usbDevice.deviceName}",
                        TopdonNotice.Category.Info
                    )
                } else {
                    emitNotice(
                        "USB permission denied for ${usbDevice.deviceName}",
                        TopdonNotice.Category.Warning
                    )
                }
            }

            override fun onConnect(
                device: UsbDevice,
                ctrlBlock: USBMonitor.UsbControlBlock,
                createNew: Boolean
            ) {
                applicationScope.launch {
                    handleDeviceConnected(
                        device,
                        ctrlBlock,
                        createNew
                    )
                }
            }

            override fun onDisconnect(
                device: UsbDevice,
                ctrlBlock: USBMonitor.UsbControlBlock
            ) {
                applicationScope.launch {
                    emitNotice(
                        "USB device disconnected: ${device.deviceName}",
                        TopdonNotice.Category.Info
                    )
                    handleDeviceDisconnected()
                }
            }

            override fun onDettach(
                device: UsbDevice
            ) {
                applicationScope.launch {
                    emitNotice(
                        "USB device detached: ${device.deviceName}",
                        TopdonNotice.Category.Info
                    )
                    handleDeviceDisconnected()
                }
            }

            override fun onCancel(
                device: UsbDevice
            ) {
                emitNotice(
                    "USB permission cancelled: ${device.deviceName}",
                    TopdonNotice.Category.Warning
                )
            }
        }

    override suspend fun refreshInventory() {
        val device =
            detectTopdonDevice()
        statusState.value =
            if (device != null) {
                TopdonStatus.Attached(
                    vendorId = device.vendorId,
                    productId = device.productId,
                    serialNumber = safeSerialNumber(
                        device
                    ),
                    hasPermission = usbManager.hasPermission(
                        device
                    )
                )
            } else {
                TopdonStatus.Idle
            }
    }

    override suspend fun connect() {
        val device =
            detectTopdonDevice()
                ?: throw IllegalStateException(
                    "Topdon TC001 camera not detected."
                )
        connectedDevice =
            device
        ensureMonitor()
        withContext(
            Dispatchers.Main
        ) {
            val monitor =
                usbMonitor
                    ?: throw IllegalStateException(
                        "USB monitor unavailable"
                    )
            if (usbManager.hasPermission(
                    device
                )
            ) {
                Log.d(
                    logTag,
                    "Connecting to ${device.deviceName} (vendor=${device.vendorId}, product=${device.productId}) with existing permission"
                )
                val ctrlBlock =
                    monitor.openDevice(
                        device
                    )
                if (ctrlBlock != null) {
                    handleDeviceConnected(
                        device,
                        ctrlBlock,
                        createNew = true
                    )
                } else {
                    throw IllegalStateException(
                        "Failed to open Topdon device."
                    )
                }
            } else {
                val requested =
                    monitor.requestPermission(
                        device
                    )
                if (!requested) {
                    if (usbManager.hasPermission(
                            device
                        )
                    ) {
                        Log.d(
                            logTag,
                            "Permission already granted for ${device.deviceName}; opening directly"
                        )
                        monitor.openDevice(
                            device
                        )
                            ?.let { ctrlBlock ->
                                handleDeviceConnected(
                                    device,
                                    ctrlBlock,
                                    createNew = true
                                )
                            }
                            ?: emitNotice(
                                "USB permission already granted but device could not be opened.",
                                TopdonNotice.Category.Warning
                            )
                    } else {
                        Log.w(
                            logTag,
                            "USB permission request not launched for ${device.deviceName}; awaiting existing grant state"
                        )
                        emitNotice(
                            "USB permission request not launched; waiting for existing grant state.",
                            TopdonNotice.Category.Warning
                        )
                    }
                }
            }
        }
    }

    override suspend fun disconnect() {
        disconnectInternal()
    }

    override suspend fun applySettings(
        settings: TopdonHardwareSettings
    ) {
        settings.palette?.let {
            currentPalette =
                it
        }
        settings.previewFpsLimit?.let {
            previewFpsLimit =
                it.coerceIn(
                    4,
                    30
                )
        }
        settings.superSamplingFactor?.let {
            superSampling =
                it.coerceIn(
                    1,
                    4
                )
        }
        emitNotice(
            "Applied Topdon settings: palette=$currentPalette fps=$previewFpsLimit superSampling=$superSampling",
            TopdonNotice.Category.Info
        )
    }

    override suspend fun startPreview(): Result<Unit> =
        resultOf {
            previewEnabled =
                true
            ensureCameraReady()
            withContext(
                Dispatchers.Main
            ) {
                uvcCamera?.setFrameCallback(
                    frameCallback
                )
                uvcCamera?.onStartPreview()
            }
        }

    override suspend fun stopPreview(): Result<Unit> =
        resultOf {
            previewEnabled =
                false
            if (!streamingEnabled) {
                withContext(
                    Dispatchers.Main
                ) {
                    uvcCamera?.setFrameCallback(
                        null
                    )
                    uvcCamera?.onStopPreview()
                }
            }
        }

    override suspend fun startStreaming(
        request: TopdonStreamRequest
    ) {
        ensureCameraReady()
        streamingEnabled =
            true
        superSampling =
            request.superSampling.coerceIn(
                1,
                4
            )
        streamFrameCounter =
            0
        streamBytes =
            0
        lastMetrics =
            TopdonTemperatureMetrics.Unknown
        if (connectedDevice != null && connectedSinceEpochMs > 0) {
            statusState.value =
                TopdonStatus.Streaming(
                    vendorId = connectedDevice!!.vendorId,
                    productId = connectedDevice!!.productId,
                    serialNumber = safeSerialNumber(
                        connectedDevice
                    ),
                    sinceEpochMs = connectedSinceEpochMs
                )
        }
        streamJob?.cancel()
        streamJob =
            applicationScope.launch(
                Dispatchers.Main
            ) {
                uvcCamera?.setFrameCallback(
                    frameCallback
                )
                val result =
                    uvcCamera?.onStartPreview()
                Log.d(
                    logTag,
                    "Start preview result=$result"
                )
            }
    }

    override suspend fun stopStreaming() {
        streamingEnabled =
            false
        streamJob?.cancelAndJoin()
        streamJob =
            null
        applicationScope.launch {
            streamFlow.emit(
                TopdonStreamEvent(
                    timestampEpochMs = System.currentTimeMillis(),
                    frameCount = streamFrameCounter,
                    bytesCaptured = streamBytes,
                    metrics = lastMetrics,
                    payload = null,
                    endOfStream = true
                )
            )
        }
        streamFrameCounter =
            0
        streamBytes =
            0
        lastMetrics =
            TopdonTemperatureMetrics.Unknown
        if (!previewEnabled) {
            withContext(
                Dispatchers.Main
            ) {
                uvcCamera?.setFrameCallback(
                    null
                )
                uvcCamera?.onStopPreview()
            }
        }
        updateStreamingStatus()
    }

    private suspend fun ensureMonitor() {
        if (usbMonitor == null) {
            withContext(
                Dispatchers.Main
            ) {
                if (usbMonitor == null) {
                    usbMonitor =
                        USBMonitor(
                            usbContext,
                            usbListener
                        ).also { monitor ->
                            monitor.register()
                            monitorRegistered.set(
                                true
                            )
                        }
                }
            }
        } else if (!monitorRegistered.get()) {
            withContext(
                Dispatchers.Main
            ) {
                usbMonitor?.register()
                monitorRegistered.set(
                    true
                )
            }
        }
    }

    private suspend fun ensureCameraReady() {
        if (uvcCamera != null && usbControlBlock != null) return
        val device =
            connectedDevice
                ?: detectTopdonDevice()
                ?: throw IllegalStateException(
                    "Topdon TC001 camera not detected."
                )
        ensureMonitor()
        withContext(
            Dispatchers.Main
        ) {
            val monitor =
                usbMonitor
                    ?: throw IllegalStateException(
                        "USB monitor unavailable"
                    )
            if (usbManager.hasPermission(
                    device
                )
            ) {
                val ctrlBlock =
                    monitor.openDevice(
                        device
                    )
                        ?: throw IllegalStateException(
                            "Failed to open Topdon device."
                        )
                handleDeviceConnected(
                    device,
                    ctrlBlock,
                    createNew = true
                )
            } else {
                if (!monitor.requestPermission(
                        device
                    )
                ) {
                    throw IllegalStateException(
                        "USB permission request rejected."
                    )
                }
            }
        }
    }

    private suspend fun handleDeviceConnected(
        device: UsbDevice,
        ctrlBlock: USBMonitor.UsbControlBlock,
        createNew: Boolean
    ) {
        connectedDevice =
            device
        connectedSinceEpochMs =
            System.currentTimeMillis()
        usbControlBlock =
            ctrlBlock
        openCamera(
            ctrlBlock
        )
        emitNotice(
            "Topdon camera connected: ${device.deviceName}",
            TopdonNotice.Category.Info
        )
        statusState.value =
            TopdonStatus.Connected(
                vendorId = device.vendorId,
                productId = device.productId,
                serialNumber = safeSerialNumber(
                    device
                ),
                sinceEpochMs = connectedSinceEpochMs
            )
        // Auto-start preview after connection for immediate feedback
        previewEnabled =
            true
        withContext(
            Dispatchers.Main
        ) {
            uvcCamera?.setFrameCallback(
                frameCallback
            )
            uvcCamera?.onStartPreview()
        }
    }

    private suspend fun handleDeviceDisconnected() {
        streamingEnabled =
            false
        previewEnabled =
            false
        streamJob?.cancelAndJoin()
        streamJob =
            null
        closeCamera()
        destroyMonitor()
        connectedDevice =
            null
        connectedSinceEpochMs =
            -1L
        statusState.value =
            TopdonStatus.Idle
    }

    private suspend fun openCamera(
        ctrlBlock: USBMonitor.UsbControlBlock
    ) {
        withContext(
            Dispatchers.Main
        ) {
            val camera =
                ConcreateUVCBuilder()
                    .setUVCType(
                        UVCType.USB_UVC
                    )
                    .build()
            uvcCamera =
                camera
            camera.openUVCCamera(
                ctrlBlock
            )
            configureCamera(
                camera
            )
            try {
                ircmd =
                    ConcreteIRCMDBuilder()
                        .setIrcmdType(
                            IRCMDType.USB_IR_256_384
                        )
                        .setCreateResultCallback(
                            OnCreateResultCallback { result ->
                                if (result != ResultCode.SUCCESS) {
                                    emitNotice(
                                        "IRCMD init failed: $result",
                                        TopdonNotice.Category.Warning
                                    )
                                }
                            })
                        .build()
            } catch (t: Throwable) {
                emitNotice(
                    "Failed to initialize IRCMD: ${t.message}",
                    TopdonNotice.Category.Warning
                )
                ircmd =
                    null
            }
        }
    }

    private suspend fun closeCamera() {
        withContext(
            Dispatchers.Main
        ) {
            try {
                uvcCamera?.setFrameCallback(
                    null
                )
                uvcCamera?.onStopPreview()
                uvcCamera?.onDestroyPreview()
                uvcCamera?.closeUVCCamera()
            } catch (t: Throwable) {
                emitNotice(
                    "Error closing Topdon camera: ${t.message}",
                    TopdonNotice.Category.Warning
                )
            } finally {
                uvcCamera =
                    null
                usbControlBlock =
                    null
                ircmd =
                    null
            }
        }
    }

    private suspend fun destroyMonitor() {
        withContext(
            Dispatchers.Main
        ) {
            usbMonitor?.let { monitor ->
                runCatching { monitor.unregister() }
                runCatching { monitor.destroy() }
            }
            usbMonitor =
                null
            monitorRegistered.set(
                false
            )
        }
    }

    private suspend fun disconnectInternal() {
        streamingEnabled =
            false
        previewEnabled =
            false
        streamJob?.cancelAndJoin()
        streamJob =
            null
        closeCamera()
        destroyMonitor()
        connectedDevice =
            null
        connectedSinceEpochMs =
            -1L
        statusState.value =
            TopdonStatus.Idle
    }

    private fun configureCamera(
        camera: UVCCamera
    ) {
        camera.setOpenStatus(
            true
        )
        camera.setDefaultPreviewMode(
            CommonParams.FRAMEFORMATType.FRAME_FORMAT_MJPEG
        )
        camera.setDefaultBandwidth(
            0.6f
        )
        camera.setDefaultPreviewMinFps(
            1
        )
        camera.setDefaultPreviewMaxFps(
            30
        )
        val fallbackSizes =
            listOf(
                FRAME_WIDTH to FRAME_HEIGHT,
                FRAME_WIDTH to (FRAME_HEIGHT * 2),
                320 to 240,
                256 to 192
            )

        var appliedSize: Pair<Int, Int>? =
            null
        var lastError: Throwable? =
            null

        for ((width, height) in fallbackSizes) {
            val result =
                runCatching {
                    camera.setUSBPreviewSize(
                        width,
                        height
                    )
                }.onFailure { error ->
                    lastError =
                        error
                    Log.w(
                        logTag,
                        "Preview size attempt ${width}x$height failed",
                        error
                    )
                }
                    .getOrNull()

            if (result != null && result >= 0) {
                appliedSize =
                    width to height
                Log.i(
                    logTag,
                    "Configured USB preview size to ${width}x$height (result=$result)"
                )
                break
            }
        }

        if (appliedSize == null) {
            Log.w(
                logTag,
                "Unable to configure USB preview size after ${fallbackSizes.size} attempts"
            )
            emitNotice(
                "Preview configuration failed; streaming may be unavailable (${lastError?.message ?: "unknown error"})",
                TopdonNotice.Category.Warning
            )
        }
    }

    private fun detectTopdonDevice(): UsbDevice? =
        usbManager.deviceList.values.firstOrNull(
            ::isTopdonCandidate
        )

    private fun isTopdonCandidate(
        device: UsbDevice
    ): Boolean {
        if (!TOPDON_VENDOR_IDS.contains(
                device.vendorId
            )
        ) return false
        return TOPDON_PRODUCT_IDS.contains(
            device.productId
        )
    }

    private fun computeMetrics(
        payload: ByteArray
    ): TopdonTemperatureMetrics {
        var min =
            Double.POSITIVE_INFINITY
        var max =
            Double.NEGATIVE_INFINITY
        var sum =
            0.0
        var count =
            0
        var index =
            0
        while (index < payload.size) {
            val raw =
                (payload[index].toInt() and 0xFF) or ((payload[index + 1].toInt() and 0xFF) shl 8)
            val celsius =
                rawToCelsius(
                    raw
                )
            min =
                kotlin.math.min(
                    min,
                    celsius
                )
            max =
                kotlin.math.max(
                    max,
                    celsius
                )
            sum += celsius
            count += 1
            index += 2
        }
        val avg =
            if (count > 0) sum / count else Double.NaN
        return TopdonTemperatureMetrics(
            minCelsius = min,
            maxCelsius = max,
            avgCelsius = avg
        )
    }

    private fun rawToCelsius(
        raw: Int
    ): Double =
        raw / 100.0 - 273.15

    private fun shouldEmitPreview(
        timestamp: Long
    ): Boolean {
        val interval =
            1000L / previewFpsLimit.coerceIn(
                4,
                30
            )
        return timestamp - lastPreviewEmitMs >= interval
    }

    private fun updateStreamingStatus() {
        val device =
            connectedDevice
                ?: return
        if (streamingEnabled) {
            statusState.value =
                TopdonStatus.Streaming(
                    vendorId = device.vendorId,
                    productId = device.productId,
                    serialNumber = safeSerialNumber(
                        device
                    ),
                    sinceEpochMs = connectedSinceEpochMs.takeIf { it > 0 }
                        ?: System.currentTimeMillis()
                )
        } else {
            statusState.value =
                TopdonStatus.Connected(
                    vendorId = device.vendorId,
                    productId = device.productId,
                    serialNumber = safeSerialNumber(
                        device
                    ),
                    sinceEpochMs = connectedSinceEpochMs.takeIf { it > 0 }
                        ?: System.currentTimeMillis()
                )
        }
    }

    private fun safeSerialNumber(
        device: UsbDevice?
    ): String? {
        device
            ?: return null
        return try {
            if (usbManager.hasPermission(
                    device
                )
            ) device.serialNumber else null
        } catch (_: SecurityException) {
            null
        }
    }

    private fun emitNotice(
        message: String,
        category: TopdonNotice.Category
    ) {
        if (!noticeFlow.tryEmit(
                TopdonNotice(
                    message,
                    category
                )
            )
        ) {
            Log.w(
                logTag,
                "Notice dropped: $message"
            )
        }
    }

    companion object {
        // 0x0BDA covers Realtek-based TC001 enumerations observed in the lab hardware.
        private val TOPDON_VENDOR_IDS =
            setOf(
                0x3426,
                0x0BDA
            )
        private val TOPDON_PRODUCT_IDS =
            setOf(
                0x0001,
                0x0002,
                0x0003,
                0x3901,
                0x5830
            )
        private const val FRAME_WIDTH =
            256
        private const val FRAME_HEIGHT =
            192
        private const val BYTES_PER_PIXEL =
            2
        private const val DEFAULT_PREVIEW_FPS =
            12
    }
}
