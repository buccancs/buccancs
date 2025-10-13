package com.buccancs.data.sensor.connector.camera

import android.content.Context
import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.media.MediaMuxer
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Size
import android.view.Surface
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.concurrent.atomic.AtomicBoolean

internal class SegmentedMediaCodecRecorder(
    private val context: Context,
    private val storage: RecordingStorage,
    private val sessionId: String,
    private val deviceId: DeviceId,
    private val anchorEpochMs: Long,
    private val size: Size,
    private val bitRate: Int,
    private val frameRate: Int
) {
    private val logTag = "SegmentedRecorder-${deviceId.value}"
    private val streamType = SensorStreamType.RGB_VIDEO
    private val writer = MediaStoreSegmentWriter(
        context = context,
        storage = storage,
        sessionId = sessionId,
        deviceId = deviceId,
        streamType = streamType
    )
    private val completion = CompletableDeferred<List<SessionArtifact>>()
    private val artifacts = mutableListOf<SessionArtifact>()
    private val closed = AtomicBoolean(false)
    private var handlerThread: HandlerThread? = null
    private var codec: MediaCodec? = null
    private var inputSurface: Surface? = null
    private var currentMuxer: MediaMuxer? = null
    private var currentTrackIndex: Int = -1
    private var currentHandle: MediaStoreSegmentWriter.SegmentHandle? = null
    private var currentCollector: EncoderStatsCollector? = null
    private var currentSegmentStartUs: Long = -1
    private var segmentIndex: Int = 0
    private var latestEpochMs: Long = anchorEpochMs
    private var currentFormat: MediaFormat? = null

    suspend fun start(): Surface = withContext(Dispatchers.Default) {
        val thread = HandlerThread("RgbEncoder-${deviceId.value}")
        handlerThread = thread
        thread.start()
        val handler = Handler(thread.looper)
        val codec = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_VIDEO_AVC)
        this@SegmentedMediaCodecRecorder.codec = codec
        codec.setCallback(callback, handler)
        codec.configure(
            createFormat(),
            null,
            null,
            MediaCodec.CONFIGURE_FLAG_ENCODE
        )
        val surface = codec.createInputSurface()
        inputSurface = surface
        codec.start()
        return@withContext surface
    }

    suspend fun stop(): List<SessionArtifact> {
        if (closed.getAndSet(true)) {
            return completion.await()
        }
        codec?.signalEndOfInputStream()
        return completion.await()
    }

    fun abort() {
        if (!closed.getAndSet(true) && !completion.isCompleted) {
            completion.complete(emptyList())
        }
        releaseCodec()
        currentMuxer?.let { muxer ->
            runCatching { muxer.stop() }
            runCatching { muxer.release() }
        }
        currentMuxer = null
        currentCollector = null
        currentHandle?.let { handle ->
            writer.abort(handle)
        }
        currentHandle = null
    }

    private fun createFormat(): MediaFormat =
        MediaFormat.createVideoFormat(MediaFormat.MIMETYPE_VIDEO_AVC, size.width, size.height).apply {
            setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface)
            setInteger(MediaFormat.KEY_BIT_RATE, bitRate)
            setInteger(MediaFormat.KEY_FRAME_RATE, frameRate)
            setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, I_FRAME_INTERVAL_SECONDS)
            setInteger(
                MediaFormat.KEY_BITRATE_MODE,
                MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CBR
            )
        }

    private val callback = object : MediaCodec.Callback() {
        override fun onInputBufferAvailable(codec: MediaCodec, index: Int) {
            // Not used with Surface input.
        }

        override fun onOutputBufferAvailable(codec: MediaCodec, index: Int, info: MediaCodec.BufferInfo) {
            val buffer = codec.getOutputBuffer(index)
            if (buffer == null) {
                codec.releaseOutputBuffer(index, false)
                return
            }
            val drainStartNs = System.nanoTime()
            try {
                if (info.flags and MediaCodec.BUFFER_FLAG_CODEC_CONFIG != 0) {
                    return
                }
                if (info.size > 0) {
                    ensureSegmentReady(info)
                    buffer.position(info.offset)
                    buffer.limit(info.offset + info.size)
                    currentMuxer?.writeSampleData(currentTrackIndex, buffer, info)
                    val epochMs = anchorEpochMs + info.presentationTimeUs / 1_000
                    val latencyNs = System.nanoTime() - drainStartNs
                    currentCollector?.let { collector ->
                        if (currentSegmentStartUs < 0) {
                            currentSegmentStartUs = info.presentationTimeUs
                            collector.markStart(epochMs)
                        }
                        collector.recordBuffer(info, info.size, latencyNs)
                        latestEpochMs = epochMs
                    }
                    if (shouldRotate(info)) {
                        rotateSegment(info)
                    }
                }
            } catch (error: Throwable) {
                handleError(error)
            } finally {
                codec.releaseOutputBuffer(index, false)
            }
            if (info.flags and MediaCodec.BUFFER_FLAG_END_OF_STREAM != 0) {
                finalizeAndComplete()
            }
        }

        override fun onOutputFormatChanged(codec: MediaCodec, format: MediaFormat) {
            currentFormat = format
            startNewSegment(format)
        }

        override fun onError(codec: MediaCodec, e: MediaCodec.CodecException) {
            handleError(e)
        }
    }

    private fun ensureSegmentReady(info: MediaCodec.BufferInfo) {
        if (currentMuxer != null) {
            return
        }
        val format = currentFormat ?: throw IllegalStateException("Output format unavailable before first buffer.")
        startNewSegment(format)
        currentSegmentStartUs = info.presentationTimeUs
        currentCollector?.markStart(anchorEpochMs + info.presentationTimeUs / 1_000)
    }

    private fun startNewSegment(format: MediaFormat) {
        try {
            currentMuxer?.let { muxer ->
                runCatching { muxer.stop() }
                runCatching { muxer.release() }
            }
            currentHandle?.let { handle -> writer.abort(handle) }
            val handle = writer.open(segmentIndex, System.currentTimeMillis())
            currentHandle = handle
            val muxer = MediaMuxer(handle.descriptor.fileDescriptor, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4)
            currentTrackIndex = muxer.addTrack(format)
            muxer.start()
            currentMuxer = muxer
            currentCollector = EncoderStatsCollector(segmentIndex)
            currentSegmentStartUs = -1
        } catch (error: IOException) {
            handleError(error)
        }
    }

    private fun shouldRotate(info: MediaCodec.BufferInfo): Boolean {
        if (currentSegmentStartUs < 0) {
            return false
        }
        val elapsedUs = info.presentationTimeUs - currentSegmentStartUs
        val keyFrame = info.flags and MediaCodec.BUFFER_FLAG_KEY_FRAME != 0
        return elapsedUs >= MAX_SEGMENT_DURATION_US && keyFrame
    }

    private fun rotateSegment(info: MediaCodec.BufferInfo) {
        finalizeCurrentSegment(info)
        currentFormat?.let { startNewSegment(it) }
    }

    private fun finalizeCurrentSegment(info: MediaCodec.BufferInfo? = null) {
        val handle = currentHandle ?: return
        val collector = currentCollector
        val muxer = currentMuxer
        currentHandle = null
        currentCollector = null
        currentMuxer = null
        currentSegmentStartUs = -1
        runCatching { muxer?.stop() }
        runCatching { muxer?.release() }
        val endEpoch = info?.let { anchorEpochMs + it.presentationTimeUs / 1_000 } ?: latestEpochMs
        if (collector != null) {
            collector.markEnd(endEpoch)
            val stats = collector.buildStats()
            runCatching {
                val artifact = writer.finalize(handle, stats)
                artifacts += artifact
            }.onFailure { error ->
                Log.w(logTag, "Failed to finalize segment ${handle.fileName}: ${error.message}", error)
                writer.abort(handle)
            }
            segmentIndex += 1
        } else {
            writer.abort(handle)
        }
    }

    private fun finalizeAndComplete() {
        if (completion.isCompleted) {
            return
        }
        finalizeCurrentSegment()
        releaseCodec()
        completion.complete(artifacts.toList())
    }

    private fun releaseCodec() {
        runCatching { codec?.stop() }
        runCatching { codec?.release() }
        codec = null
        inputSurface?.release()
        inputSurface = null
        handlerThread?.quitSafely()
        handlerThread = null
    }

    private fun handleError(error: Throwable) {
        if (!completion.isCompleted) {
            completion.completeExceptionally(error)
        }
        abort()
    }

    companion object {
        private const val I_FRAME_INTERVAL_SECONDS = 1
        private const val MAX_SEGMENT_DURATION_US = 5L * 60L * 1_000_000L
    }
}
