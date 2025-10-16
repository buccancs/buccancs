package com.buccancs.desktop.data.aggregation

import com.buccancs.desktop.data.repository.SessionRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path
import java.security.MessageDigest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests for SessionAggregationService.
 * Validates file aggregation, manifest consolidation, and session completeness checking.
 */
class SessionAggregationServiceTest {

    @TempDir
    lateinit var tempDir: Path

    private fun createService(sessionDir: Path): SessionAggregationService {
        val sessionRepository = mockk<SessionRepository>(relaxed = true)
        coEvery { sessionRepository.sessionDirectory(any()) } returns sessionDir
        return SessionAggregationService(sessionRepository)
    }

    @Test
    fun `aggregates file successfully`() = runTest {
        val sessionDir = tempDir.resolve("session-123")
        Files.createDirectories(sessionDir)

        val service = createService(sessionDir)
        val content = "test content".toByteArray()
        val checksum = MessageDigest.getInstance("SHA-256").digest(content)

        val result = service.aggregateFile(
            sessionId = "session-123",
            deviceId = "device-1",
            fileName = "test.txt",
            content = content,
            checksum = checksum,
            mimeType = "text/plain",
            streamType = "data"
        )

        assertTrue(result is AggregationResult.Success)

        val filePath = sessionDir.resolve("devices/device-1/data/test.txt")
        assertTrue(filePath.toFile().exists())
        assertEquals("test content", filePath.toFile().readText())
    }

    @Test
    fun `detects checksum mismatch`() = runTest {
        val sessionDir = tempDir.resolve("session-456")
        Files.createDirectories(sessionDir)

        val service = createService(sessionDir)
        val content = "test content".toByteArray()
        val wrongChecksum = ByteArray(32) { 0 }

        val result = service.aggregateFile(
            sessionId = "session-456",
            deviceId = "device-1",
            fileName = "test.txt",
            content = content,
            checksum = wrongChecksum,
            mimeType = "text/plain",
            streamType = null
        )

        assertTrue(result is AggregationResult.Failure)
        assertTrue((result as AggregationResult.Failure).error.contains("Checksum mismatch"))
    }

    @Test
    fun `handles duplicate files`() = runTest {
        val sessionDir = tempDir.resolve("session-789")
        Files.createDirectories(sessionDir)

        val service = createService(sessionDir)
        val content = "test content".toByteArray()
        val checksum = MessageDigest.getInstance("SHA-256").digest(content)

        // First upload
        service.aggregateFile(
            sessionId = "session-789",
            deviceId = "device-1",
            fileName = "test.txt",
            content = content,
            checksum = checksum,
            mimeType = "text/plain",
            streamType = "data"
        )

        // Duplicate upload
        val result = service.aggregateFile(
            sessionId = "session-789",
            deviceId = "device-1",
            fileName = "test.txt",
            content = content,
            checksum = checksum,
            mimeType = "text/plain",
            streamType = "data"
        )

        assertTrue(result is AggregationResult.Duplicate)
    }

    @Test
    fun `renames conflicting files with different content`() = runTest {
        val sessionDir = tempDir.resolve("session-abc")
        Files.createDirectories(sessionDir)

        val service = createService(sessionDir)
        val content1 = "original content".toByteArray()
        val content2 = "different content".toByteArray()
        val checksum1 = MessageDigest.getInstance("SHA-256").digest(content1)
        val checksum2 = MessageDigest.getInstance("SHA-256").digest(content2)

        // First file
        service.aggregateFile(
            sessionId = "session-abc",
            deviceId = "device-1",
            fileName = "test.txt",
            content = content1,
            checksum = checksum1,
            mimeType = "text/plain",
            streamType = "data"
        )

        // Conflicting file with different content
        val result = service.aggregateFile(
            sessionId = "session-abc",
            deviceId = "device-1",
            fileName = "test.txt",
            content = content2,
            checksum = checksum2,
            mimeType = "text/plain",
            streamType = "data"
        )

        assertTrue(result is AggregationResult.Success)

        // Check both files exist
        val file1 = sessionDir.resolve("devices/device-1/data/test.txt")
        val file2 = sessionDir.resolve("devices/device-1/data/test_1.txt")
        assertTrue(file1.toFile().exists())
        assertTrue(file2.toFile().exists())
        assertEquals("original content", file1.toFile().readText())
        assertEquals("different content", file2.toFile().readText())
    }

    @Test
    fun `creates device manifest`() = runTest {
        val sessionDir = tempDir.resolve("session-def")
        Files.createDirectories(sessionDir)

        val service = createService(sessionDir)
        val content = "test".toByteArray()
        val checksum = MessageDigest.getInstance("SHA-256").digest(content)

        service.aggregateFile(
            sessionId = "session-def",
            deviceId = "device-1",
            fileName = "file1.txt",
            content = content,
            checksum = checksum,
            mimeType = "text/plain",
            streamType = "data"
        )

        val manifestPath = sessionDir.resolve("devices/device-1/manifest.json")
        assertTrue(manifestPath.toFile().exists())

        val manifestContent = manifestPath.toFile().readText()
        val json = Json { ignoreUnknownKeys = true }
        val manifest = json.decodeFromString<DeviceManifest>(manifestContent)

        assertEquals("device-1", manifest.deviceId)
        assertEquals(1, manifest.artifacts.size)
        assertEquals("file1.txt", manifest.artifacts[0].fileName)
    }

    @Test
    fun `consolidates manifests from multiple devices`() = runTest {
        val sessionDir = tempDir.resolve("session-ghi")
        Files.createDirectories(sessionDir)

        val service = createService(sessionDir)
        val content = "test".toByteArray()
        val checksum = MessageDigest.getInstance("SHA-256").digest(content)

        // Upload from device 1
        service.aggregateFile(
            sessionId = "session-ghi",
            deviceId = "device-1",
            fileName = "file1.txt",
            content = content,
            checksum = checksum,
            mimeType = "text/plain",
            streamType = "data"
        )

        // Upload from device 2
        service.aggregateFile(
            sessionId = "session-ghi",
            deviceId = "device-2",
            fileName = "file2.txt",
            content = content,
            checksum = checksum,
            mimeType = "text/plain",
            streamType = "data"
        )

        // Consolidate manifests
        val result = service.consolidateManifests("session-ghi")
        assertTrue(result is AggregationResult.Success)

        // Verify consolidated manifest
        val consolidatedPath = sessionDir.resolve("session-manifest.json")
        assertTrue(consolidatedPath.toFile().exists())

        val json = Json { ignoreUnknownKeys = true }
        val manifest = json.decodeFromString<SessionManifest>(consolidatedPath.toFile().readText())

        assertEquals("session-ghi", manifest.sessionId)
        assertEquals(2, manifest.devices.size)
        assertTrue(manifest.devices.containsKey("device-1"))
        assertTrue(manifest.devices.containsKey("device-2"))
    }

    @Test
    fun `validates session completeness`() = runTest {
        val sessionDir = tempDir.resolve("session-jkl")
        Files.createDirectories(sessionDir)

        val service = createService(sessionDir)
        val content = "test".toByteArray()
        val checksum = MessageDigest.getInstance("SHA-256").digest(content)

        // Upload files
        service.aggregateFile(
            sessionId = "session-jkl",
            deviceId = "device-1",
            fileName = "file1.txt",
            content = content,
            checksum = checksum,
            mimeType = "text/plain",
            streamType = "data"
        )

        service.aggregateFile(
            sessionId = "session-jkl",
            deviceId = "device-2",
            fileName = "file2.txt",
            content = content,
            checksum = checksum,
            mimeType = "text/plain",
            streamType = "data"
        )

        // Consolidate and validate
        service.consolidateManifests("session-jkl")
        val report = service.validateSessionCompleteness("session-jkl")

        assertTrue(report.isComplete)
        assertEquals(2, report.totalExpectedFiles)
        assertEquals(2, report.totalReceivedFiles)
        assertEquals(2, report.deviceCount)
        assertTrue(report.missingFiles.isEmpty())
    }

    @Test
    fun `detects missing files in session`() = runTest {
        val sessionDir = tempDir.resolve("session-mno")
        Files.createDirectories(sessionDir)

        val service = createService(sessionDir)
        val content = "test".toByteArray()
        val checksum = MessageDigest.getInstance("SHA-256").digest(content)

        // Upload one file
        service.aggregateFile(
            sessionId = "session-mno",
            deviceId = "device-1",
            fileName = "file1.txt",
            content = content,
            checksum = checksum,
            mimeType = "text/plain",
            streamType = "data"
        )

        // Consolidate
        service.consolidateManifests("session-mno")

        // Manually delete the file to simulate missing
        val filePath = sessionDir.resolve("devices/device-1/data/file1.txt")
        Files.delete(filePath)

        // Validate
        val report = service.validateSessionCompleteness("session-mno")

        assertFalse(report.isComplete)
        assertEquals(1, report.totalExpectedFiles)
        assertEquals(0, report.totalReceivedFiles)
        assertEquals(1, report.missingFiles.size)
        assertTrue(report.missingFiles[0].contains("file1.txt"))
    }

    @Test
    fun `aggregates file without stream type`() = runTest {
        val sessionDir = tempDir.resolve("session-pqr")
        Files.createDirectories(sessionDir)

        val service = createService(sessionDir)
        val content = "test".toByteArray()

        val result = service.aggregateFile(
            sessionId = "session-pqr",
            deviceId = "device-1",
            fileName = "file.txt",
            content = content,
            checksum = null,
            mimeType = "text/plain",
            streamType = null
        )

        assertTrue(result is AggregationResult.Success)

        // File should be directly in device directory without stream type subdirectory
        val filePath = sessionDir.resolve("devices/device-1/file.txt")
        assertTrue(filePath.toFile().exists())
    }

    @Test
    fun `handles empty file aggregation`() = runTest {
        val sessionDir = tempDir.resolve("session-stu")
        Files.createDirectories(sessionDir)

        val service = createService(sessionDir)
        val emptyContent = ByteArray(0)

        val result = service.aggregateFile(
            sessionId = "session-stu",
            deviceId = "device-1",
            fileName = "empty.txt",
            content = emptyContent,
            checksum = null,
            mimeType = "text/plain",
            streamType = "data"
        )

        assertTrue(result is AggregationResult.Success)

        val filePath = sessionDir.resolve("devices/device-1/data/empty.txt")
        assertTrue(filePath.toFile().exists())
        assertEquals(0, filePath.toFile().length())
    }
}
