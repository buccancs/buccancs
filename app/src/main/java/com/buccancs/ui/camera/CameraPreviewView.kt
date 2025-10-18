package com.buccancs.ui.camera

import android.annotation.SuppressLint
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Size
import android.view.Surface
import android.view.TextureView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Composable
fun CameraPreviewView(
    modifier: Modifier = Modifier,
    onPreviewStarted: () -> Unit = {},
    onPreviewStopped: () -> Unit = {},
    onError: (Exception) -> Unit = {}
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    
    val cameraManager = remember { 
        context.getSystemService(android.content.Context.CAMERA_SERVICE) as CameraManager 
    }
    
    val cameraHelper = remember {
        Camera2PreviewHelper(cameraManager)
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    // Camera will be opened when TextureView is available
                }
                Lifecycle.Event.ON_PAUSE -> {
                    cameraHelper.stopPreview()
                    onPreviewStopped()
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            cameraHelper.release()
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            TextureView(ctx).apply {
                surfaceTextureListener = object : TextureView.SurfaceTextureListener {
                    override fun onSurfaceTextureAvailable(
                        surface: SurfaceTexture,
                        width: Int,
                        height: Int
                    ) {
                        CoroutineScope(Dispatchers.Main).launch {
                            try {
                                cameraHelper.startPreview(Surface(surface), Size(width, height))
                                onPreviewStarted()
                            } catch (e: Exception) {
                                Log.e("CameraPreview", "Failed to start preview", e)
                                onError(e)
                            }
                        }
                    }

                    override fun onSurfaceTextureSizeChanged(
                        surface: SurfaceTexture,
                        width: Int,
                        height: Int
                    ) {
                        // Handle size change if needed
                    }

                    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                        cameraHelper.stopPreview()
                        onPreviewStopped()
                        return true
                    }

                    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
                        // Frame update callback
                    }
                }
            }
        }
    )
}

private class Camera2PreviewHelper(
    private val cameraManager: CameraManager
) {
    private var cameraDevice: CameraDevice? = null
    private var captureSession: CameraCaptureSession? = null
    private var handlerThread: HandlerThread? = null
    private var handler: Handler? = null

    @SuppressLint("MissingPermission")
    suspend fun startPreview(surface: Surface, size: Size) {
        ensureHandler()
        
        val cameraId = findBackCamera() ?: throw IllegalStateException("No back camera found")
        val device = openCamera(cameraId)
        cameraDevice = device

        suspendCancellableCoroutine<Unit> { continuation ->
            device.createCaptureSession(
                listOf(surface),
                object : CameraCaptureSession.StateCallback() {
                    override fun onConfigured(session: CameraCaptureSession) {
                        captureSession = session
                        
                        val request = device.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW).apply {
                            addTarget(surface)
                            set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_AUTO)
                            set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE)
                            set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON)
                        }.build()

                        try {
                            session.setRepeatingRequest(request, null, handler)
                            continuation.resume(Unit)
                        } catch (e: Exception) {
                            continuation.resumeWithException(e)
                        }
                    }

                    override fun onConfigureFailed(session: CameraCaptureSession) {
                        continuation.resumeWithException(
                            IllegalStateException("Failed to configure camera session")
                        )
                    }
                },
                handler
            )
        }
    }

    fun stopPreview() {
        try {
            captureSession?.stopRepeating()
            captureSession?.close()
        } catch (e: Exception) {
            Log.w("Camera2Helper", "Error stopping preview", e)
        }
        captureSession = null
        cameraDevice?.close()
        cameraDevice = null
    }

    fun release() {
        stopPreview()
        handlerThread?.quitSafely()
        handlerThread = null
        handler = null
    }

    private fun findBackCamera(): String? {
        return cameraManager.cameraIdList.firstOrNull { id ->
            val characteristics = cameraManager.getCameraCharacteristics(id)
            val facing = characteristics.get(android.hardware.camera2.CameraCharacteristics.LENS_FACING)
            facing == android.hardware.camera2.CameraCharacteristics.LENS_FACING_BACK
        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun openCamera(cameraId: String): CameraDevice {
        ensureHandler()
        return suspendCancellableCoroutine { continuation ->
            cameraManager.openCamera(
                cameraId,
                object : CameraDevice.StateCallback() {
                    override fun onOpened(device: CameraDevice) {
                        continuation.resume(device)
                    }

                    override fun onDisconnected(device: CameraDevice) {
                        device.close()
                        if (continuation.isActive) {
                            continuation.resumeWithException(
                                IllegalStateException("Camera disconnected")
                            )
                        }
                    }

                    override fun onError(device: CameraDevice, error: Int) {
                        device.close()
                        if (continuation.isActive) {
                            continuation.resumeWithException(
                                IllegalStateException("Camera error: $error")
                            )
                        }
                    }
                },
                handler
            )
        }
    }

    private fun ensureHandler() {
        if (handler == null) {
            val thread = HandlerThread("CameraPreview").apply { start() }
            handlerThread = thread
            handler = Handler(thread.looper)
        }
    }
}
