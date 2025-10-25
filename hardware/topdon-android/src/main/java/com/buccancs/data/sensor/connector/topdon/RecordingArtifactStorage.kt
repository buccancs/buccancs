package com.buccancs.data.sensor.connector.topdon

import java.io.File

/**
 * Minimal abstraction used by the Topdon connector to persist recording artifacts without
 * depending directly on the application-layer storage implementation.
 */
interface RecordingArtifactStorage {
    fun createArtifactFile(
        sessionId: String,
        deviceId: String,
        streamType: String,
        timestampEpochMs: Long,
        segmentIndex: Int = 0,
        extension: String
    ): File
}
