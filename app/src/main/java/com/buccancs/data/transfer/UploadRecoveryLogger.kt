package com.buccancs.data.transfer

import com.buccancs.core.serialization.StandardJson
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.UploadRecoveryRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UploadRecoveryLogger @Inject constructor(
    private val storage: RecordingStorage,
    @StandardJson private val json: Json
) {
    private val mutex = Mutex()

    suspend fun append(record: UploadRecoveryRecord) {
        withContext(Dispatchers.IO) {
            val payload = Payload(
                sessionId = record.sessionId,
                deviceId = record.deviceId.value,
                streamType = record.streamType.name,
                attempt = record.attempt,
                state = record.state.name,
                timestampEpochMs = record.timestamp.toEpochMilliseconds(),
                bytesTransferred = record.bytesTransferred,
                bytesTotal = record.bytesTotal,
                networkTransport = record.network.transport,
                networkConnected = record.network.connected,
                networkMetered = record.network.metered,
                errorMessage = record.errorMessage
            )
            mutex.withLock {
                val file = storage.uploadRecoveryLogFile(record.sessionId)
                file.parentFile?.mkdirs()
                file.appendText(json.encodeToString(payload) + "\n")
            }
        }
    }

    @Serializable
    private data class Payload(
        val sessionId: String,
        val deviceId: String,
        val streamType: String,
        val attempt: Int,
        val state: String,
        val timestampEpochMs: Long,
        val bytesTransferred: Long,
        val bytesTotal: Long,
        val networkTransport: String,
        val networkConnected: Boolean,
        val networkMetered: Boolean,
        val errorMessage: String?
    )
}
