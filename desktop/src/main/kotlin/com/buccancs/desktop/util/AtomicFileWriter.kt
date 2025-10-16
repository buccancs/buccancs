package com.buccancs.desktop.util

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.nio.file.StandardOpenOption
import java.security.MessageDigest
import kotlin.io.path.exists
import kotlin.io.path.readBytes

/**
 * Utility for atomic file writes with checksums and corruption detection.
 * Ensures files are written completely or not at all.
 */
object AtomicFileWriter {

    /**
     * Writes content atomically by writing to a temporary file first,
     * then moving it to the target location.
     */
    fun writeAtomic(targetPath: Path, content: ByteArray) {
        val tempPath = targetPath.resolveSibling("${targetPath.fileName}.tmp")
        try {
            Files.write(
                tempPath,
                content,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.SYNC
            )

            Files.move(
                tempPath,
                targetPath,
                StandardCopyOption.ATOMIC_MOVE,
                StandardCopyOption.REPLACE_EXISTING
            )
        } catch (e: Exception) {
            tempPath.toFile().delete()
            throw e
        }
    }

    /**
     * Writes string content atomically.
     */
    fun writeAtomicString(targetPath: Path, content: String) {
        writeAtomic(targetPath, content.toByteArray(StandardCharsets.UTF_8))
    }

    /**
     * Writes content atomically with checksum verification.
     * Stores checksum in a companion .sha256 file.
     */
    fun writeAtomicWithChecksum(targetPath: Path, content: ByteArray) {
        val checksum = calculateSha256(content)
        val checksumPath = targetPath.resolveSibling("${targetPath.fileName}.sha256")

        writeAtomic(targetPath, content)

        try {
            Files.writeString(
                checksumPath,
                checksum,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            )
        } catch (e: Exception) {
            targetPath.toFile().delete()
            throw e
        }
    }

    /**
     * Writes string content atomically with checksum.
     */
    fun writeAtomicStringWithChecksum(targetPath: Path, content: String) {
        writeAtomicWithChecksum(targetPath, content.toByteArray(StandardCharsets.UTF_8))
    }

    /**
     * Verifies file integrity by checking stored checksum.
     */
    fun verifyChecksum(filePath: Path): Boolean {
        if (!filePath.exists()) {
            return false
        }

        val checksumPath = filePath.resolveSibling("${filePath.fileName}.sha256")
        if (!checksumPath.exists()) {
            return false
        }

        return try {
            val content = filePath.readBytes()
            val actualChecksum = calculateSha256(content)
            val storedChecksum = Files.readString(checksumPath).trim()
            actualChecksum.equals(storedChecksum, ignoreCase = true)
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Reads file content with checksum verification.
     */
    fun readVerified(filePath: Path): ByteArray {
        if (!verifyChecksum(filePath)) {
            throw CorruptedFileException("File checksum verification failed: $filePath")
        }
        return filePath.readBytes()
    }

    /**
     * Reads string content with checksum verification.
     */
    fun readVerifiedString(filePath: Path): String {
        return String(readVerified(filePath), StandardCharsets.UTF_8)
    }

    /**
     * Write-ahead logging: creates a backup before modifying critical files.
     */
    fun writeWithBackup(targetPath: Path, content: ByteArray) {
        val backupPath = targetPath.resolveSibling("${targetPath.fileName}.backup")

        if (targetPath.exists()) {
            Files.copy(
                targetPath,
                backupPath,
                StandardCopyOption.REPLACE_EXISTING
            )
        }

        try {
            writeAtomicWithChecksum(targetPath, content)
            backupPath.toFile().delete()
        } catch (e: Exception) {
            if (backupPath.exists()) {
                Files.move(
                    backupPath,
                    targetPath,
                    StandardCopyOption.REPLACE_EXISTING
                )
            }
            throw e
        }
    }

    /**
     * Write-ahead logging for string content.
     */
    fun writeStringWithBackup(targetPath: Path, content: String) {
        writeWithBackup(targetPath, content.toByteArray(StandardCharsets.UTF_8))
    }

    /**
     * Recovers from backup if primary file is corrupted.
     */
    fun recoverFromBackup(targetPath: Path): Boolean {
        val backupPath = targetPath.resolveSibling("${targetPath.fileName}.backup")

        if (!backupPath.exists()) {
            return false
        }

        return try {
            Files.move(
                backupPath,
                targetPath,
                StandardCopyOption.REPLACE_EXISTING
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun calculateSha256(content: ByteArray): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(content)
        return hash.joinToString("") { "%02x".format(it) }
    }
}

class CorruptedFileException(message: String) : Exception(message)
