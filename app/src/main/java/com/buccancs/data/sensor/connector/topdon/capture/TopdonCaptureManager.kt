package com.buccancs.data.sensor.connector.topdon.capture

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.TopdonPreviewFrame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * Manages photo capture and video recording for Topdon thermal camera
 */
internal class TopdonCaptureManager(
    private val context: Context,
    private val deviceId: DeviceId
) {
    private val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
    private var recordingState: RecordingState? = null

    /**
     * Capture current thermal frame as photo
     */
    suspend fun capturePhoto(frame: TopdonPreviewFrame): Result<CaptureResult> = withContext(Dispatchers.IO) {
        try {
            val timestamp = System.currentTimeMillis()
            val filename = "THERMAL_${dateFormat.format(Date(timestamp))}.jpg"
            val outputDir = getOutputDirectory("photos")
            val outputFile = File(outputDir, filename)

            // Convert frame payload to bitmap
            val bitmap = BitmapFactory.decodeByteArray(frame.payload, 0, frame.payload.size)
                ?: return@withContext Result.failure(Exception("Failed to decode thermal frame"))

            // Save as JPEG with metadata
            FileOutputStream(outputFile).use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 95, out)
            }

            // Create metadata file
            val metadataFile = File(outputDir, "$filename.json")
            val metadata = buildCaptureMetadata(frame, timestamp)
            metadataFile.writeText(metadata)

            Result.success(
                CaptureResult(
                    uri = Uri.fromFile(outputFile),
                    file = outputFile,
                    sizeBytes = outputFile.length(),
                    timestamp = timestamp,
                    width = frame.width,
                    height = frame.height,
                    minTemp = frame.minTemp,
                    maxTemp = frame.maxTemp
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Start thermal video recording
     */
    suspend fun startRecording(sessionId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            if (recordingState != null) {
                return@withContext Result.failure(Exception("Recording already in progress"))
            }

            val timestamp = System.currentTimeMillis()
            val filename = "THERMAL_VIDEO_${dateFormat.format(Date(timestamp))}.raw"
            val outputDir = getOutputDirectory("videos")
            val outputFile = File(outputDir, filename)

            recordingState = RecordingState(
                sessionId = sessionId,
                file = outputFile,
                startTime = timestamp,
                frameCount = 0,
                outputStream = FileOutputStream(outputFile)
            )

            Result.success(Unit)
        } catch (e: Exception) {
            recordingState = null
            Result.failure(e)
        }
    }

    /**
     * Record thermal frame during video recording
     */
    suspend fun recordFrame(frame: TopdonPreviewFrame): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val state = recordingState
                ?: return@withContext Result.failure(Exception("No recording in progress"))

            // Write raw frame data
            state.outputStream.write(frame.payload)
            state.frameCount++

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Stop thermal video recording
     */
    suspend fun stopRecording(): Result<RecordingResult> = withContext(Dispatchers.IO) {
        try {
            val state = recordingState
                ?: return@withContext Result.failure(Exception("No recording in progress"))

            state.outputStream.flush()
            state.outputStream.close()

            val duration = System.currentTimeMillis() - state.startTime
            val fps = if (duration > 0) (state.frameCount * 1000.0 / duration).toFloat() else 0f

            // Create metadata file
            val metadataFile = File(state.file.parentFile, "${state.file.name}.json")
            val metadata = buildRecordingMetadata(state, duration, fps)
            metadataFile.writeText(metadata)

            val result = RecordingResult(
                uri = Uri.fromFile(state.file),
                file = state.file,
                sizeBytes = state.file.length(),
                durationMs = duration,
                frameCount = state.frameCount,
                fps = fps
            )

            recordingState = null
            Result.success(result)
        } catch (e: Exception) {
            recordingState?.outputStream?.close()
            recordingState = null
            Result.failure(e)
        }
    }

    /**
     * Check if currently recording
     */
    fun isRecording(): Boolean = recordingState != null

    private fun getOutputDirectory(subdir: String): File {
        val baseDir = context.getExternalFilesDir("Topdon")
            ?: context.filesDir.resolve("Topdon")
        val dir = File(baseDir, subdir)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    private fun buildCaptureMetadata(frame: TopdonPreviewFrame, timestamp: Long): String {
        return """
            {
                "device": "${deviceId.value}",
                "timestamp": $timestamp,
                "width": ${frame.width},
                "height": ${frame.height},
                "superSampling": ${frame.superSamplingFactor},
                "minTemp": ${frame.minTemp},
                "maxTemp": ${frame.maxTemp},
                "avgTemp": ${frame.avgTemp}
            }
        """.trimIndent()
    }

    private fun buildRecordingMetadata(state: RecordingState, duration: Long, fps: Float): String {
        return """
            {
                "device": "${deviceId.value}",
                "sessionId": "${state.sessionId}",
                "startTime": ${state.startTime},
                "duration": $duration,
                "frameCount": ${state.frameCount},
                "fps": $fps
            }
        """.trimIndent()
    }

    private data class RecordingState(
        val sessionId: String,
        val file: File,
        val startTime: Long,
        var frameCount: Int,
        val outputStream: FileOutputStream
    )
}

data class CaptureResult(
    val uri: Uri,
    val file: File,
    val sizeBytes: Long,
    val timestamp: Long,
    val width: Int,
    val height: Int,
    val minTemp: Float?,
    val maxTemp: Float?
)

data class RecordingResult(
    val uri: Uri,
    val file: File,
    val sizeBytes: Long,
    val durationMs: Long,
    val frameCount: Int,
    val fps: Float
)
