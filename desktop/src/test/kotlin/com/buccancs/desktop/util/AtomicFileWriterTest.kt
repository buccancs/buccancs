package com.buccancs.desktop.util

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests for AtomicFileWriter.
 * Validates atomic writes, checksum verification, and backup/recovery.
 */
class AtomicFileWriterTest {

    @TempDir
    lateinit var tempDir: Path

    @Test
    fun `writes file atomically`() {
        val targetPath = tempDir.resolve("test.txt")
        val content = "test content".toByteArray()

        AtomicFileWriter.writeAtomic(targetPath, content)

        assertTrue(targetPath.exists())
        assertEquals("test content", String(Files.readAllBytes(targetPath)))
    }

    @Test
    fun `writes string atomically`() {
        val targetPath = tempDir.resolve("test.txt")

        AtomicFileWriter.writeAtomicString(targetPath, "test content")

        assertTrue(targetPath.exists())
        assertEquals("test content", Files.readString(targetPath))
    }

    @Test
    fun `writes with checksum`() {
        val targetPath = tempDir.resolve("test.txt")
        val content = "test content".toByteArray()

        AtomicFileWriter.writeAtomicWithChecksum(targetPath, content)

        assertTrue(targetPath.exists())
        val checksumPath = tempDir.resolve("test.txt.sha256")
        assertTrue(checksumPath.exists())
    }

    @Test
    fun `verifies checksum correctly`() {
        val targetPath = tempDir.resolve("test.txt")
        val content = "test content".toByteArray()

        AtomicFileWriter.writeAtomicWithChecksum(targetPath, content)

        assertTrue(AtomicFileWriter.verifyChecksum(targetPath))
    }

    @Test
    fun `detects corrupted file`() {
        val targetPath = tempDir.resolve("test.txt")
        val content = "test content".toByteArray()

        AtomicFileWriter.writeAtomicWithChecksum(targetPath, content)

        // Corrupt the file
        Files.write(targetPath, "corrupted content".toByteArray())

        assertFalse(AtomicFileWriter.verifyChecksum(targetPath))
    }

    @Test
    fun `reads verified file successfully`() {
        val targetPath = tempDir.resolve("test.txt")
        val content = "test content".toByteArray()

        AtomicFileWriter.writeAtomicWithChecksum(targetPath, content)

        val read = AtomicFileWriter.readVerified(targetPath)
        assertEquals("test content", String(read))
    }

    @Test
    fun `throws exception when reading corrupted file`() {
        val targetPath = tempDir.resolve("test.txt")
        val content = "test content".toByteArray()

        AtomicFileWriter.writeAtomicWithChecksum(targetPath, content)
        Files.write(targetPath, "corrupted".toByteArray())

        assertFailsWith<CorruptedFileException> {
            AtomicFileWriter.readVerified(targetPath)
        }
    }

    @Test
    fun `reads verified string successfully`() {
        val targetPath = tempDir.resolve("test.txt")

        AtomicFileWriter.writeAtomicStringWithChecksum(targetPath, "test content")

        val read = AtomicFileWriter.readVerifiedString(targetPath)
        assertEquals("test content", read)
    }

    @Test
    fun `writes with backup`() {
        val targetPath = tempDir.resolve("test.txt")
        val content = "original content".toByteArray()

        // Write initial content
        AtomicFileWriter.writeAtomicWithChecksum(targetPath, content)

        // Update with backup
        val newContent = "updated content".toByteArray()
        AtomicFileWriter.writeWithBackup(targetPath, newContent)

        assertTrue(targetPath.exists())
        assertEquals("updated content", String(Files.readAllBytes(targetPath)))

        // Backup should be deleted after successful write
        val backupPath = tempDir.resolve("test.txt.backup")
        assertFalse(backupPath.exists())
    }

    @Test
    fun `recovers from backup on write failure`() {
        val targetPath = tempDir.resolve("test.txt")

        // Simulate existing file
        Files.write(targetPath, "original content".toByteArray())

        val backupPath = tempDir.resolve("test.txt.backup")
        Files.write(backupPath, "backup content".toByteArray())

        AtomicFileWriter.recoverFromBackup(targetPath)

        assertEquals("backup content", Files.readString(targetPath))
        assertFalse(backupPath.exists())
    }

    @Test
    fun `recovery returns false when no backup exists`() {
        val targetPath = tempDir.resolve("test.txt")

        assertFalse(AtomicFileWriter.recoverFromBackup(targetPath))
    }

    @Test
    fun `replaces existing file atomically`() {
        val targetPath = tempDir.resolve("test.txt")

        Files.write(targetPath, "original content".toByteArray())

        AtomicFileWriter.writeAtomicString(targetPath, "new content")

        assertEquals("new content", Files.readString(targetPath))
    }

    @Test
    fun `atomic write cleans up temp file on failure`() {
        val targetPath = tempDir.resolve("subdir/test.txt")
        val content = "test".toByteArray()

        // Subdirectory doesn't exist, should fail
        assertFailsWith<Exception> {
            AtomicFileWriter.writeAtomic(targetPath, content)
        }

        val tempPath = tempDir.resolve("subdir/test.txt.tmp")
        assertFalse(tempPath.exists())
    }

    @Test
    fun `writes empty file atomically`() {
        val targetPath = tempDir.resolve("empty.txt")
        val content = ByteArray(0)

        AtomicFileWriter.writeAtomic(targetPath, content)

        assertTrue(targetPath.exists())
        assertEquals(0, Files.size(targetPath))
    }

    @Test
    fun `writes large file atomically`() {
        val targetPath = tempDir.resolve("large.dat")
        val content = ByteArray(10_000_000) { it.toByte() }

        AtomicFileWriter.writeAtomicWithChecksum(targetPath, content)

        assertTrue(targetPath.exists())
        assertEquals(10_000_000, Files.size(targetPath))
        assertTrue(AtomicFileWriter.verifyChecksum(targetPath))
    }

    @Test
    fun `checksum verification returns false for missing file`() {
        val targetPath = tempDir.resolve("nonexistent.txt")

        assertFalse(AtomicFileWriter.verifyChecksum(targetPath))
    }

    @Test
    fun `checksum verification returns false when checksum file missing`() {
        val targetPath = tempDir.resolve("test.txt")
        Files.write(targetPath, "content".toByteArray())

        assertFalse(AtomicFileWriter.verifyChecksum(targetPath))
    }
}
