package com.buccancs.desktop.data.grpc.services

import com.buccancs.control.DataTransferRequest
import com.buccancs.control.DataTransferServiceGrpcKt
import com.buccancs.control.DataTransferStatus
import com.buccancs.desktop.data.aggregation.AggregationResult
import com.buccancs.desktop.data.aggregation.SessionAggregationService
import com.buccancs.desktop.data.repository.SessionRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.security.MessageDigest
import java.util.concurrent.ConcurrentHashMap

class DataTransferServiceImpl(
    private val sessionRepository: SessionRepository,
    private val aggregationService: SessionAggregationService
) : DataTransferServiceGrpcKt.DataTransferServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(DataTransferServiceImpl::class.java)
    private val attempts = ConcurrentHashMap<String, Int>()

    override fun upload(requests: Flow<DataTransferRequest>): Flow<DataTransferStatus> =
        channelFlow {
            val accumulators = mutableMapOf<String, TransferAccumulator>()
            val job = launch {
                requests.collect { request ->
                    val streamType = request.streamType.takeIf { it.isNotBlank() }
                    val key = transferKey(
                        request.session.id,
                        request.deviceId,
                        request.fileName,
                        streamType
                    )
                    val accumulator = accumulators.getOrPut(key) {
                        val attempt =
                            attempts.merge(key, 1) { previous, increment -> previous + increment }
                                ?: 1
                        try {
                            sessionRepository.markTransferStarted(
                                sessionId = request.session.id,
                                deviceId = request.deviceId,
                                fileName = request.fileName,
                                streamType = streamType,
                                expectedBytes = request.sizeBytes,
                                attempt = attempt
                            )
                        } catch (ex: Exception) {
                            logger.warn(
                                "Unable to note transfer start for {} from {}",
                                request.fileName,
                                request.deviceId,
                                ex
                            )
                        }
                        TransferAccumulator(
                            sessionId = request.session.id,
                            deviceId = request.deviceId,
                            fileName = request.fileName,
                            expectedBytes = request.sizeBytes,
                            checksum = request.sha256.toByteArray(),
                            mimeType = request.mimeType.ifBlank { "application/octet-stream" },
                            streamType = streamType,
                            attempt = attempt
                        )
                    }
                    if (!request.chunk.isEmpty()) {
                        val chunkBytes = request.chunk.toByteArray()
                        accumulator.stream.write(chunkBytes)
                        accumulator.bytes += chunkBytes.size
                        try {
                            sessionRepository.markTransferProgress(
                                sessionId = accumulator.sessionId,
                                deviceId = accumulator.deviceId,
                                fileName = accumulator.fileName,
                                streamType = accumulator.streamType,
                                receivedBytes = accumulator.bytes.toLong()
                            )
                        } catch (ex: Exception) {
                            logger.warn(
                                "Unable to update transfer progress for {} from {}",
                                accumulator.fileName,
                                accumulator.deviceId,
                                ex
                            )
                        }
                    }
                    if (request.endOfStream) {
                        val payload = accumulator.stream.toByteArray()
                        val actualBytes =
                            accumulator.bytes.takeIf { it > 0 }?.toLong() ?: payload.size.toLong()
                        try {
                            sessionRepository.markTransferProgress(
                                sessionId = accumulator.sessionId,
                                deviceId = accumulator.deviceId,
                                fileName = accumulator.fileName,
                                streamType = accumulator.streamType,
                                receivedBytes = actualBytes
                            )
                        } catch (ex: Exception) {
                            logger.warn(
                                "Unable to mark final transfer size for {} from {}",
                                accumulator.fileName,
                                accumulator.deviceId,
                                ex
                            )
                        }
                        if (accumulator.expectedBytes > 0 && actualBytes != accumulator.expectedBytes) {
                            val message =
                                "Size mismatch: expected ${accumulator.expectedBytes} bytes, received $actualBytes bytes"
                            logger.warn(
                                "Discarding file {} from {} due to size mismatch",
                                accumulator.fileName,
                                accumulator.deviceId
                            )
                            accumulators.remove(key)
                            try {
                                sessionRepository.markTransferFailed(
                                    sessionId = accumulator.sessionId,
                                    deviceId = accumulator.deviceId,
                                    fileName = accumulator.fileName,
                                    streamType = accumulator.streamType,
                                    attempt = accumulator.attempt,
                                    receivedBytes = actualBytes,
                                    error = message
                                )
                            } catch (ex: Exception) {
                                logger.warn(
                                    "Unable to record failed transfer for {} from {}",
                                    accumulator.fileName,
                                    accumulator.deviceId,
                                    ex
                                )
                            }
                            trySend(
                                buildTransferStatus(
                                    accumulator,
                                    success = false,
                                    errorMessage = message
                                )
                            )
                            return@collect
                        }
                        if (accumulator.checksum.isNotEmpty()) {
                            val calculated = MessageDigest.getInstance("SHA-256").digest(payload)
                            if (!calculated.contentEquals(accumulator.checksum)) {
                                val message = "Checksum mismatch"
                                logger.warn(
                                    "Checksum mismatch for file {} from device {}",
                                    accumulator.fileName,
                                    accumulator.deviceId
                                )
                                accumulators.remove(key)
                                try {
                                    sessionRepository.markTransferFailed(
                                        sessionId = accumulator.sessionId,
                                        deviceId = accumulator.deviceId,
                                        fileName = accumulator.fileName,
                                        streamType = accumulator.streamType,
                                        attempt = accumulator.attempt,
                                        receivedBytes = actualBytes,
                                        error = message
                                    )
                                } catch (ex: Exception) {
                                    logger.warn(
                                        "Unable to record checksum failure for {} from {}",
                                        accumulator.fileName,
                                        accumulator.deviceId,
                                        ex
                                    )
                                }
                                trySend(
                                    buildTransferStatus(
                                        accumulator,
                                        success = false,
                                        errorMessage = message
                                    )
                                )
                                return@collect
                            }
                        }
                        try {
                            sessionRepository.attachFile(
                                sessionId = accumulator.sessionId,
                                deviceId = accumulator.deviceId,
                                fileName = accumulator.fileName,
                                content = payload,
                                mimeType = accumulator.mimeType,
                                checksum = accumulator.checksum,
                                streamType = accumulator.streamType
                            )

                            // Aggregate file into session structure with manifest update
                            try {
                                val aggregationResult = aggregationService.aggregateFile(
                                    sessionId = accumulator.sessionId,
                                    deviceId = accumulator.deviceId,
                                    fileName = accumulator.fileName,
                                    content = payload,
                                    checksum = accumulator.checksum,
                                    mimeType = accumulator.mimeType,
                                    streamType = accumulator.streamType
                                )

                                when (aggregationResult) {
                                    is AggregationResult.Success -> {
                                        logger.debug(
                                            "File aggregated: {}",
                                            aggregationResult.message
                                        )
                                    }

                                    is AggregationResult.Duplicate -> {
                                        logger.info(
                                            "Duplicate file detected: {}",
                                            aggregationResult.fileName
                                        )
                                    }

                                    is AggregationResult.Failure -> {
                                        logger.warn(
                                            "Aggregation failed: {}",
                                            aggregationResult.error
                                        )
                                    }
                                }
                            } catch (ex: Exception) {
                                logger.warn(
                                    "Failed to aggregate file {} from {}",
                                    accumulator.fileName,
                                    accumulator.deviceId,
                                    ex
                                )
                            }

                            try {
                                sessionRepository.markTransferCompleted(
                                    sessionId = accumulator.sessionId,
                                    deviceId = accumulator.deviceId,
                                    fileName = accumulator.fileName,
                                    streamType = accumulator.streamType,
                                    expectedBytes = accumulator.expectedBytes,
                                    receivedBytes = actualBytes
                                )
                            } catch (ex: Exception) {
                                logger.warn(
                                    "Unable to mark transfer completion for {} from {}",
                                    accumulator.fileName,
                                    accumulator.deviceId,
                                    ex
                                )
                            }
                            attempts.remove(key)
                            accumulators.remove(key)
                            logger.info(
                                "Received file {} from device {} ({} bytes)",
                                accumulator.fileName,
                                accumulator.deviceId,
                                actualBytes
                            )
                            trySend(buildTransferStatus(accumulator, success = true))
                        } catch (ex: Exception) {
                            logger.error(
                                "Failed to persist file {} for session {}",
                                accumulator.fileName,
                                accumulator.sessionId,
                                ex
                            )
                            accumulators.remove(key)
                            try {
                                sessionRepository.markTransferFailed(
                                    sessionId = accumulator.sessionId,
                                    deviceId = accumulator.deviceId,
                                    fileName = accumulator.fileName,
                                    streamType = accumulator.streamType,
                                    attempt = accumulator.attempt,
                                    receivedBytes = actualBytes,
                                    error = ex.message.orEmpty()
                                )
                            } catch (markEx: Exception) {
                                logger.warn(
                                    "Unable to record transfer failure for {} from {}",
                                    accumulator.fileName,
                                    accumulator.deviceId,
                                    markEx
                                )
                            }
                            trySend(
                                buildTransferStatus(
                                    accumulator = accumulator,
                                    success = false,
                                    errorMessage = ex.message.orEmpty()
                                )
                            )
                        }
                    }
                }
            }
            awaitClose {
                job.cancel()
                accumulators.clear()
            }
        }

    private fun transferKey(
        sessionId: String,
        deviceId: String,
        fileName: String,
        streamType: String?
    ): String = listOf(sessionId, deviceId, fileName, streamType.orEmpty()).joinToString("|")

    private data class TransferAccumulator(
        val sessionId: String,
        val deviceId: String,
        val fileName: String,
        val expectedBytes: Long,
        val checksum: ByteArray,
        val mimeType: String,
        val streamType: String?,
        val attempt: Int,
        val stream: ByteArrayOutputStream = ByteArrayOutputStream(),
        var bytes: Long = 0
    )

    private fun buildTransferStatus(
        accumulator: TransferAccumulator,
        success: Boolean,
        errorMessage: String? = null
    ): DataTransferStatus {
        val builder = DataTransferStatus.newBuilder()
            .setFileName(accumulator.fileName)
            .setDeviceId(accumulator.deviceId)
            .setSuccess(success)
        builder.streamType = accumulator.streamType ?: ""
        errorMessage?.let { builder.errorMessage = it }
        return builder.build()
    }
}
