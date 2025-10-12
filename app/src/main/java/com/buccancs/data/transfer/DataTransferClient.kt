package com.buccancs.data.transfer

import com.buccancs.control.DataTransferRequest
import com.buccancs.control.DataTransferServiceGrpcKt
import com.buccancs.control.dataTransferRequest
import com.buccancs.control.sessionIdentifier
import com.buccancs.data.orchestration.GrpcChannelFactory
import com.buccancs.domain.model.SessionArtifact
import com.buccancs.domain.repository.OrchestratorConfigRepository
import com.google.protobuf.ByteString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataTransferClient @Inject constructor(
    private val channelFactory: GrpcChannelFactory,
    private val configRepository: OrchestratorConfigRepository
) {
    suspend fun upload(
        sessionId: String,
        artifact: SessionArtifact,
        onProgress: suspend (Long) -> Unit
    ): UploadResult = withContext(Dispatchers.IO) {
        val config = configRepository.config.value
        val channel = channelFactory.channel(config)
        val stub = DataTransferServiceGrpcKt.DataTransferServiceCoroutineStub(channel)
        var transferred = 0L
        val requestFlow = flow {
            FileInputStream(artifact.file).use { input ->
                val buffer = ByteArray(DEFAULT_CHUNK_SIZE)
                while (true) {
                    val read = input.read(buffer)
                    if (read == -1) {
                        break
                    }
                    transferred += read
                    emit(buildChunk(sessionId, artifact, buffer, read, endOfStream = false))
                    onProgress(transferred)
                }
            }
            emit(buildChunk(sessionId, artifact, ByteArray(0), 0, endOfStream = true))
            onProgress(artifact.sizeBytes)
        }
        return@withContext try {
            var success = false
            var message: String? = null
            stub.upload(requestFlow).collect { status ->
                success = status.success
                message = status.errorMessage.takeIf { it.isNotBlank() }
            }
            UploadResult(success = success, errorMessage = message)
        } catch (ex: Exception) {
            UploadResult(success = false, errorMessage = ex.message ?: "Data transfer failed")
        }
    }

    private fun buildChunk(
        sessionId: String,
        artifact: SessionArtifact,
        chunk: ByteArray,
        length: Int,
        endOfStream: Boolean
    ): DataTransferRequest = dataTransferRequest {
        session = sessionIdentifier { id = sessionId }
        deviceId = artifact.deviceId.value
        fileName = artifact.file.name
        sizeBytes = artifact.sizeBytes
        sha256 = ByteString.copyFrom(artifact.checksumSha256)
        mimeType = artifact.mimeType
        streamType = artifact.streamType.name
        if (length > 0) {
            this.chunk = ByteString.copyFrom(chunk, 0, length)
        }
        this.endOfStream = endOfStream
    }

    data class UploadResult(
        val success: Boolean,
        val errorMessage: String? = null
    )

    private companion object {
        private const val DEFAULT_CHUNK_SIZE = 256 * 1024
    }
}
