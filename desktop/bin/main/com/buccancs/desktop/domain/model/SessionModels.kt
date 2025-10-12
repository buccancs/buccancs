package com.buccancs.desktop.domain.model

import java.nio.file.Path
import java.time.Instant

data class Session(
    val id: String,
    val createdAt: Instant,
    val startedAt: Instant?,
    val directory: Path,
    val status: SessionStatus,
    val metrics: SessionMetrics = SessionMetrics(),
    val subjectIds: List<String> = emptyList(),
    val totalDurationMs: Long? = null
)

data class SessionMetrics(
    val gsrSamples: Long = 0,
    val videoFrames: Long = 0,
    val thermalFrames: Long = 0,
    val audioSamples: Long = 0,
    val deviceCount: Int = 0,
    val activeRecordingCount: Int = 0,
    val updatedAt: Instant? = null
)

enum class SessionStatus {
    IDLE,
    ACTIVE,
    STOPPING,
    COMPLETED
}

data class DeviceInfo(
    val id: String,
    val model: String,
    val platform: String,
    val capabilities: Set<String>,
    val connected: Boolean,
    val recording: Boolean,
    val batteryPercent: Double?,
    val previewLatencyMs: Double?,
    val clockOffsetMs: Double?,
    val lastHeartbeat: Instant?,
    val sessionId: String?
)

data class StoredSession(
    val id: String,
    val status: SessionStatus,
    val createdAt: Instant,
    val startedAt: Instant?,
    val stoppedAt: Instant?,
    val totalBytes: Long,
    val durationMs: Long?,
    val subjects: List<String>,
    val eventCount: Int,
    val deviceCount: Int
)
