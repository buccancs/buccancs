package com.buccancs.data.sensor.connector.topdon

import com.buccancs.data.storage.RecordingStorage
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Adapter that delegates artifact persistence to [RecordingStorage] while satisfying the
 * Topdon connector's lightweight storage contract.
 */
@Singleton
internal class StorageBackedRecordingArtifactStorage @Inject constructor(
    private val storage: RecordingStorage
) : RecordingArtifactStorage {
    override fun createArtifactFile(
        sessionId: String,
        deviceId: String,
        streamType: String,
        timestampEpochMs: Long,
        segmentIndex: Int,
        extension: String
    ): File =
        storage.createArtifactFile(
            sessionId = sessionId,
            deviceId = deviceId,
            streamType = streamType,
            timestampEpochMs = timestampEpochMs,
            segmentIndex = segmentIndex,
            extension = extension
        )
}
