package com.buccancs.data.sensor.connector.camera

import android.content.Context
import android.graphics.ImageFormat
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.hardware.camera2.DngCreator
import android.hardware.camera2.TotalCaptureResult
import android.media.Image
import android.media.ImageReader
import android.net.Uri
import android.os.Handler
import android.util.Log
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.security.MessageDigest

internal class RawCaptureHandler(
    private val context: Context,
    private val storage: RecordingStorage,
    private val cameraManager: CameraManager,
    private val scope: CoroutineScope
) {
    private val logTag = "RawCaptureHandler"
    private var captureJob: Job? = null
    private var imageReader: ImageReader? = null
    private val artifacts = mutableListOf<SessionArtifact>()
    private var captureCount = 0

    fun start(
        cameraDevice: CameraDevice,
        cameraId: String,
        sessionId: String,
        deviceId: DeviceId,
        handler: Handler,
        intervalMs: Long
    ): ImageReader {
        stop()

        val characteristics = cameraManager.getCameraCharacteristics(cameraId)
        val configs = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
        val rawSize = configs?.getOutputSizes(ImageFormat.RAW_SENSOR)?.maxByOrNull { it.width * it.height }
            ?: throw IllegalStateException("No RAW output sizes available")

        val reader = ImageReader.newInstance(
            rawSize.width,
            rawSize.height,
            ImageFormat.RAW_SENSOR,
            2
        )
        imageReader = reader

        captureCount = 0

        reader.setOnImageAvailableListener({ imgReader ->
            try {
                val image = imgReader.acquireLatestImage()
                if (image != null) {
                    captureRawImage(image, sessionId, deviceId, characteristics, cameraId)
                    image.close()
                }
            } catch (e: Exception) {
                Log.e(logTag, "Error capturing RAW image", e)
            }
        }, handler)

        // Start periodic capture trigger
        captureJob = scope.launch {
            delay(intervalMs) // Wait for first capture
            while (isActive) {
                try {
                    triggerCapture(cameraDevice, reader.surface, handler)
                } catch (e: Exception) {
                    Log.w(logTag, "Capture trigger failed", e)
                }
                delay(intervalMs)
            }
        }

        return reader
    }

    fun stop() {
        captureJob?.cancel()
        captureJob = null
        imageReader?.close()
        imageReader = null
    }

    fun getArtifacts(): List<SessionArtifact> {
        val result = artifacts.toList()
        artifacts.clear()
        return result
    }

    private fun captureRawImage(
        image: Image,
        sessionId: String,
        deviceId: DeviceId,
        characteristics: CameraCharacteristics,
        cameraId: String
    ) {
        try {
            val file = storage.createArtifactFile(
                sessionId = sessionId,
                deviceId = deviceId.value,
                streamType = "raw_image",
                timestampEpochMs = System.currentTimeMillis(),
                extension = "dng"
            )

            FileOutputStream(file).use { output ->
                val dngCreator = DngCreator(characteristics, image.planes[0].buffer as android.hardware.camera2.CaptureResult)
                dngCreator.writeImage(output, image)
                dngCreator.close()
            }

            captureCount++

            // Calculate checksum
            val digest = MessageDigest.getInstance("SHA-256")
            file.inputStream().use { input ->
                val buffer = ByteArray(8192)
                var bytesRead: Int
                while (input.read(buffer).also { bytesRead = it } != -1) {
                    digest.update(buffer, 0, bytesRead)
                }
            }

            val artifact = SessionArtifact(
                deviceId = deviceId,
                streamType = SensorStreamType.RGB_VIDEO, // Using RGB_VIDEO as closest match
                uri = Uri.fromFile(file),
                file = file,
                mimeType = "image/x-adobe-dng",
                sizeBytes = file.length(),
                checksumSha256 = digest.digest(),
                metadata = mapOf(
                    "format" to "DNG",
                    "captureNumber" to captureCount.toString(),
                    "width" to image.width.toString(),
                    "height" to image.height.toString()
                )
            )

            artifacts.add(artifact)
            Log.d(logTag, "Captured RAW DNG #$captureCount: ${file.name}")

        } catch (e: Exception) {
            Log.e(logTag, "Failed to save RAW image", e)
        }
    }

    private fun triggerCapture(
        cameraDevice: CameraDevice,
        surface: android.view.Surface,
        handler: Handler
    ) {
        val captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
        captureBuilder.addTarget(surface)
        
        // Set capture request parameters
        captureBuilder.set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_AUTO)
        captureBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE)
        captureBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON)
        
        // Trigger single capture
        val captureSession = null // Need to get from camera session
        // This would need to be refactored to integrate with the main capture session
    }
}
