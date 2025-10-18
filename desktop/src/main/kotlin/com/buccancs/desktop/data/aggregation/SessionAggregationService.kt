package com.buccancs.desktop.data.aggregation

import com.buccancs.desktop.data.repository.SessionRepository
import com.buccancs.desktop.util.AtomicFileWriter
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path
import java.security.MessageDigest
import kotlin.io.path.exists
import kotlin.io.path.readBytes

/**
 * Service responsible for aggregating uploaded files from multiple devices
 * into a consolidated session structure with unified manifest.
 */
class SessionAggregationService(
    private val sessionDirectoryResolver: suspend (String) -> Path,
    private val json: Json = defaultJson()
) {
    constructor(
        sessionRepository: SessionRepository,
        json: Json = defaultJson()
    ) : this(
        sessionDirectoryResolver = {
            sessionRepository.sessionDirectory(
                it
            )
        },
        json = json
    )

    private val logger =
        LoggerFactory.getLogger(
            SessionAggregationService::class.java
        )
    private val mutex =
        Mutex()

    /**
     * Aggregates a received file into the session structure.
     */
    suspend fun aggregateFile(
        sessionId: String,
        deviceId: String,
        fileName: String,
        content: ByteArray,
        checksum: ByteArray?,
        mimeType: String,
        streamType: String?
    ): AggregationResult =
        mutex.withLock {
            try {
                val sessionDir =
                    sessionDirectoryResolver(
                        sessionId
                    )
                if (!sessionDir.exists()) {
                    return AggregationResult.failure(
                        "Session directory does not exist: $sessionId"
                    )
                }

                // Verify checksum if provided
                if (checksum != null && checksum.isNotEmpty()) {
                    val computed =
                        MessageDigest.getInstance(
                            "SHA-256"
                        )
                            .digest(
                                content
                            )
                    if (!computed.contentEquals(
                            checksum
                        )
                    ) {
                        logger.error(
                            "Checksum mismatch for {} from {}",
                            fileName,
                            deviceId
                        )
                        return AggregationResult.failure(
                            "Checksum mismatch for $fileName"
                        )
                    }
                }

                // Create device directory
                val deviceDir =
                    sessionDir.resolve(
                        "devices"
                    )
                        .resolve(
                            deviceId
                        )
                Files.createDirectories(
                    deviceDir
                )

                // Create stream type subdirectory if specified
                val targetDir =
                    if (streamType != null) {
                        deviceDir.resolve(
                            streamType
                        )
                            .also {
                                Files.createDirectories(
                                    it
                                )
                            }
                    } else {
                        deviceDir
                    }

                // Check for duplicates
                val targetPath =
                    targetDir.resolve(
                        fileName
                    )
                if (targetPath.exists()) {
                    logger.warn(
                        "File already exists: {}",
                        targetPath
                    )
                    val existing =
                        targetPath.readBytes()
                    if (existing.contentEquals(
                            content
                        )
                    ) {
                        logger.info(
                            "Duplicate file detected, skipping: {}",
                            fileName
                        )
                        return AggregationResult.duplicate(
                            fileName
                        )
                    } else {
                        val renamed =
                            generateUniqueFileName(
                                targetDir,
                                fileName
                            )
                        Files.write(
                            renamed,
                            content
                        )
                        updateManifest(
                            sessionDir,
                            deviceId,
                            renamed,
                            content.size.toLong(),
                            checksum,
                            mimeType,
                            streamType
                        )
                        return AggregationResult.success(
                            renamed.fileName.toString()
                        )
                    }
                }

                // Write file
                Files.write(
                    targetPath,
                    content
                )
                logger.info(
                    "Aggregated file {} from device {} ({} bytes)",
                    fileName,
                    deviceId,
                    content.size
                )

                // Update manifest
                updateManifest(
                    sessionDir,
                    deviceId,
                    targetPath,
                    content.size.toLong(),
                    checksum,
                    mimeType,
                    streamType
                )

                AggregationResult.success(
                    fileName
                )
            } catch (e: Exception) {
                logger.error(
                    "Failed to aggregate file {} from {}",
                    fileName,
                    deviceId,
                    e
                )
                AggregationResult.failure(
                    e.message
                        ?: "Unknown error"
                )
            }
        }

    /**
     * Consolidates all device manifests into a unified session manifest.
     */
    suspend fun consolidateManifests(
        sessionId: String
    ): AggregationResult =
        mutex.withLock {
            try {
                val sessionDir =
                    sessionDirectoryResolver(
                        sessionId
                    )
                val devicesDir =
                    sessionDir.resolve(
                        "devices"
                    )

                if (!devicesDir.exists()) {
                    return AggregationResult.failure(
                        "No devices directory found"
                    )
                }

                val consolidatedManifest =
                    SessionManifest(
                        sessionId = sessionId,
                        createdAt = System.currentTimeMillis(),
                        devices = mutableMapOf()
                    )

                // Read each device manifest
                Files.list(
                    devicesDir
                )
                    .use { deviceDirs ->
                        deviceDirs.forEach { deviceDir ->
                            if (Files.isDirectory(
                                    deviceDir
                                )
                            ) {
                                val deviceId =
                                    deviceDir.fileName.toString()
                                val manifestPath =
                                    deviceDir.resolve(
                                        "manifest.json"
                                    )

                                if (manifestPath.exists()) {
                                    try {
                                        val manifestContent =
                                            Files.readString(
                                                manifestPath
                                            )
                                        val deviceManifest =
                                            json.decodeFromString<DeviceManifest>(
                                                manifestContent
                                            )
                                        consolidatedManifest.devices[deviceId] =
                                            deviceManifest
                                        logger.info(
                                            "Consolidated manifest from device: {}",
                                            deviceId
                                        )
                                    } catch (e: Exception) {
                                        logger.error(
                                            "Failed to read manifest for device {}",
                                            deviceId,
                                            e
                                        )
                                    }
                                }
                            }
                        }
                    }

                // Write consolidated manifest atomically with backup
                val consolidatedPath =
                    sessionDir.resolve(
                        "session-manifest.json"
                    )
                val manifestJson =
                    json.encodeToString(
                        consolidatedManifest
                    )
                AtomicFileWriter.writeStringWithBackup(
                    consolidatedPath,
                    manifestJson
                )

                logger.info(
                    "Consolidated manifest created for session {} with {} devices",
                    sessionId,
                    consolidatedManifest.devices.size
                )

                AggregationResult.success(
                    "Consolidated ${consolidatedManifest.devices.size} device manifests"
                )
            } catch (e: Exception) {
                logger.error(
                    "Failed to consolidate manifests for session {}",
                    sessionId,
                    e
                )
                AggregationResult.failure(
                    e.message
                        ?: "Consolidation failed"
                )
            }
        }

    /**
     * Validates session completeness.
     */
    suspend fun validateSessionCompleteness(
        sessionId: String
    ): SessionCompletenessReport {
        return mutex.withLock {
            try {
                val sessionDir =
                    sessionDirectoryResolver(
                        sessionId
                    )
                val manifestPath =
                    sessionDir.resolve(
                        "session-manifest.json"
                    )

                if (!manifestPath.exists()) {
                    return SessionCompletenessReport(
                        sessionId = sessionId,
                        isComplete = false,
                        missingFiles = emptyList(),
                        error = "Session manifest not found"
                    )
                }

                val manifestContent =
                    Files.readString(
                        manifestPath
                    )
                val manifest =
                    json.decodeFromString<SessionManifest>(
                        manifestContent
                    )

                val missingFiles =
                    mutableListOf<String>()
                var totalExpected =
                    0
                var totalReceived =
                    0

                manifest.devices.forEach { (deviceId, deviceManifest) ->
                    deviceManifest.artifacts.forEach { artifact ->
                        totalExpected++
                        val filePath =
                            sessionDir.resolve(
                                "devices"
                            )
                                .resolve(
                                    deviceId
                                )
                                .resolve(
                                    artifact.streamType
                                        ?: ""
                                )
                                .resolve(
                                    artifact.fileName
                                )

                        if (!filePath.exists()) {
                            missingFiles.add(
                                "$deviceId/${artifact.fileName}"
                            )
                        } else {
                            totalReceived++
                        }
                    }
                }

                SessionCompletenessReport(
                    sessionId = sessionId,
                    isComplete = missingFiles.isEmpty(),
                    totalExpectedFiles = totalExpected,
                    totalReceivedFiles = totalReceived,
                    missingFiles = missingFiles,
                    deviceCount = manifest.devices.size
                )
            } catch (e: Exception) {
                logger.error(
                    "Failed to validate session completeness",
                    e
                )
                SessionCompletenessReport(
                    sessionId = sessionId,
                    isComplete = false,
                    missingFiles = emptyList(),
                    error = e.message
                )
            }
        }
    }

    private fun updateManifest(
        sessionDir: Path,
        deviceId: String,
        filePath: Path,
        size: Long,
        checksum: ByteArray?,
        mimeType: String,
        streamType: String?
    ) {
        try {
            val deviceDir =
                sessionDir.resolve(
                    "devices"
                )
                    .resolve(
                        deviceId
                    )
            val manifestPath =
                deviceDir.resolve(
                    "manifest.json"
                )

            val manifest =
                if (manifestPath.exists()) {
                    val content =
                        Files.readString(
                            manifestPath
                        )
                    json.decodeFromString<DeviceManifest>(
                        content
                    )
                } else {
                    DeviceManifest(
                        deviceId = deviceId,
                        artifacts = mutableListOf()
                    )
                }

            val artifact =
                FileArtifact(
                    fileName = filePath.fileName.toString(),
                    size = size,
                    checksum = checksum?.let {
                        bytesToHex(
                            it
                        )
                    },
                    mimeType = mimeType,
                    streamType = streamType,
                    receivedAt = System.currentTimeMillis()
                )

            manifest.artifacts.add(
                artifact
            )

            val manifestJson =
                json.encodeToString(
                    manifest
                )
            AtomicFileWriter.writeStringWithBackup(
                manifestPath,
                manifestJson
            )
        } catch (e: Exception) {
            logger.error(
                "Failed to update manifest",
                e
            )
        }
    }

    private fun generateUniqueFileName(
        directory: Path,
        originalName: String
    ): Path {
        val baseName =
            originalName.substringBeforeLast(
                '.'
            )
        val extension =
            if (originalName.contains(
                    '.'
                )
            ) {
                ".${
                    originalName.substringAfterLast(
                        '.'
                    )
                }"
            } else {
                ""
            }

        var counter =
            1
        var candidatePath: Path
        do {
            candidatePath =
                directory.resolve(
                    "${baseName}_$counter$extension"
                )
            counter++
        } while (candidatePath.exists())

        return candidatePath
    }

    private fun bytesToHex(
        bytes: ByteArray
    ): String {
        return bytes.joinToString(
            ""
        ) {
            "%02x".format(
                it
            )
        }
    }

    companion object {
        private fun defaultJson(): Json =
            Json {
                prettyPrint =
                    true
                ignoreUnknownKeys =
                    true
            }
    }
}

@Serializable
data class FileArtifact(
    val fileName: String,
    val size: Long,
    val checksum: String?,
    val mimeType: String,
    val streamType: String?,
    val receivedAt: Long
)

@Serializable
data class DeviceManifest(
    val deviceId: String,
    val artifacts: MutableList<FileArtifact>
)

@Serializable
data class SessionManifest(
    val sessionId: String,
    val createdAt: Long,
    val devices: MutableMap<String, DeviceManifest>
)

data class SessionCompletenessReport(
    val sessionId: String,
    val isComplete: Boolean,
    val totalExpectedFiles: Int = 0,
    val totalReceivedFiles: Int = 0,
    val missingFiles: List<String>,
    val deviceCount: Int = 0,
    val error: String? = null
)

sealed class AggregationResult {
    data class Success(
        val message: String
    ) : AggregationResult()

    data class Duplicate(
        val fileName: String
    ) : AggregationResult()

    data class Failure(
        val error: String
    ) : AggregationResult()

    companion object {
        fun success(
            message: String
        ) =
            Success(
                message
            )

        fun duplicate(
            fileName: String
        ) =
            Duplicate(
                fileName
            )

        fun failure(
            error: String
        ) =
            Failure(
                error
            )
    }
}
