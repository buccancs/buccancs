package com.buccancs.data.storage

import android.content.Context
import android.os.Environment
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecordingStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val root: File
        get() = File(context.filesDir, "recordings")

    fun recordingsRoot(): File {
        val directory = root
        if (!directory.exists()) {
            directory.mkdirs()
        }
        return directory
    }

    fun sessionIds(): List<String> = recordingsRoot()
        .listFiles()
        ?.asSequence()
        ?.filter { it.isDirectory }
        ?.map { it.name }
        ?.sorted()
        ?.toList()
        ?: emptyList()

    fun sessionDirectoryOrNull(sessionId: String): File? {
        val directory = File(root, sessionId)
        return if (directory.exists() && directory.isDirectory) directory else null
    }

    fun deviceDirectory(sessionId: String, deviceId: String): File {
        val directory = File(sessionDirectory(sessionId), deviceId)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        return directory
    }

    fun sessionDirectory(sessionId: String): File {
        val sessionRoot = File(root, sessionId)
        if (!sessionRoot.exists()) {
            sessionRoot.mkdirs()
        }
        return sessionRoot
    }

    fun manifestFile(sessionId: String): File {
        val directory = sessionDirectory(sessionId)
        return File(directory, "manifest.json")
    }

    fun uploadRecoveryLogFile(sessionId: String): File =
        File(sessionDirectory(sessionId), "upload_recovery.jsonl")

    fun performanceMetricsFile(sessionId: String): File =
        File(sessionDirectory(sessionId), "performance_metrics.jsonl")

    fun backlogTelemetryFile(sessionId: String): File =
        File(sessionDirectory(sessionId), "backlog_telemetry.jsonl")

    fun performanceSummaryFile(sessionId: String): File =
        File(sessionDirectory(sessionId), "performance_summary.json")

    fun relativePath(sessionId: String, artifact: File): String {
        val sessionRoot = sessionDirectory(sessionId).absoluteFile.normalize()
        val target = artifact.absoluteFile.normalize()
        val rootPath = sessionRoot.path
        val filePath = target.path
        return if (filePath.startsWith(rootPath)) {
            val trimmed = filePath.substring(rootPath.length)
            trimmed.trimStart(File.separatorChar)
        } else {
            target.name
        }
    }

    fun createArtifactFile(
        sessionId: String,
        deviceId: String,
        streamType: String,
        timestampEpochMs: Long,
        segmentIndex: Int = 0,
        extension: String
    ): File {
        val deviceDir = deviceDirectory(sessionId, deviceId)
        val fileName = artifactFileName(
            streamType = streamType,
            timestampEpochMs = timestampEpochMs,
            segmentIndex = segmentIndex,
            extension = extension
        )
        return File(deviceDir, fileName)
    }

    fun directorySize(target: File): Long {
        if (!target.exists()) {
            return 0L
        }
        var total = 0L
        target.walkTopDown().forEach { file ->
            if (file.isFile) {
                total += file.length()
            }
        }
        return total
    }

    fun artifactFileName(
        streamType: String,
        timestampEpochMs: Long,
        segmentIndex: Int = 0,
        extension: String
    ): String {
        val timestampLabel = formatter.format(java.time.Instant.ofEpochMilli(timestampEpochMs))
        val normalizedStream = streamType.lowercase().replace(" ", "_")
        val sequenceLabel = if (segmentIndex > 0) "_${segmentIndex.toString().padStart(3, '0')}" else ""
        return "${timestampLabel}_${normalizedStream}$sequenceLabel.$extension"
    }

    fun mediaStoreRelativePath(sessionId: String, deviceId: String): String {
        val sanitizedSession = sanitize(sessionId)
        val sanitizedDevice = sanitize(deviceId)
        return "${Environment.DIRECTORY_MOVIES}/$MEDIA_STORE_ROOT/$sanitizedSession/$sanitizedDevice/"
    }

    private fun sanitize(input: String): String =
        input.replace(Regex("[^A-Za-z0-9._-]"), "_")

    companion object {
        private val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS")
            .withZone(java.time.ZoneOffset.UTC)
        private const val MEDIA_STORE_ROOT = "Buccancs"
    }
}
