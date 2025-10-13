package com.buccancs.data.storage

import android.content.Context
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
        val timestampLabel = formatter.format(java.time.Instant.ofEpochMilli(timestampEpochMs))
        val normalizedStream = streamType.lowercase().replace(" ", "_")
        val sequenceLabel = if (segmentIndex > 0) "_${segmentIndex.toString().padStart(3, '0')}" else ""
        val fileName = "${timestampLabel}_${normalizedStream}$sequenceLabel.$extension"
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

    companion object {
        private val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS")
            .withZone(java.time.ZoneOffset.UTC)
    }
}
