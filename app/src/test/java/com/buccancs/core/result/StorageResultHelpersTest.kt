package com.buccancs.core.result

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.io.IOException

class StorageResultHelpersTest {

    @get:Rule
    val temporaryFolder = TemporaryFolder()

    @Test
    fun `storageOperation wraps successful operation`() {
        val result = storageOperation {
            "success"
        }
        
        assertTrue(result.isSuccess())
        assertEquals("success", result.getOrNull())
    }

    @Test
    fun `storageOperation converts SecurityException to Permission error`() {
        val result = storageOperation {
            throw SecurityException("Storage permission denied")
        }
        
        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Permission)
        assertEquals("WRITE_EXTERNAL_STORAGE", (error as Error.Permission).permission)
    }

    @Test
    fun `storageOperation converts IOException to Storage error`() {
        val result = storageOperation {
            throw IOException("Disk full")
        }
        
        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Storage)
        assertTrue(error.message.contains("Disk full"))
    }

    @Test
    fun `storageOperation converts IllegalArgumentException to Validation error`() {
        val result = storageOperation {
            throw IllegalArgumentException("Invalid path")
        }
        
        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Validation)
        assertEquals("path", (error as Error.Validation).field)
    }

    @Test
    fun `storageOperation converts generic exception to Storage error`() {
        val result = storageOperation {
            throw RuntimeException("Unknown storage error")
        }
        
        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Storage)
    }

    @Test
    fun `ensureDirectory succeeds for existing directory`() {
        val directory = temporaryFolder.newFolder("test")
        val result = directory.ensureDirectory()
        
        assertTrue(result.isSuccess())
        assertEquals(directory, result.getOrNull())
    }

    @Test
    fun `ensureDirectory creates new directory`() {
        val directory = File(temporaryFolder.root, "new_dir")
        assertFalse(directory.exists())
        
        val result = directory.ensureDirectory()
        
        assertTrue(result.isSuccess())
        assertTrue(directory.exists())
        assertTrue(directory.isDirectory)
    }

    @Test
    fun `ensureDirectory fails for existing file`() {
        val file = temporaryFolder.newFile("not_a_directory")
        val result = file.ensureDirectory()
        
        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Storage)
        assertTrue(error.message.contains("not a directory"))
    }

    @Test
    fun `ensureDirectory fails when cannot create`() {
        val directory = File("/invalid/path/that/cannot/be/created")
        val result = directory.ensureDirectory()
        
        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Storage)
    }

    @Test
    fun `ensureReadable succeeds for existing file`() {
        val file = temporaryFolder.newFile("test.txt")
        file.writeText("content")
        
        val result = file.ensureReadable()
        
        assertTrue(result.isSuccess())
        assertEquals(file, result.getOrNull())
    }

    @Test
    fun `ensureReadable fails for non-existent file`() {
        val file = File(temporaryFolder.root, "does_not_exist.txt")
        val result = file.ensureReadable()
        
        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.NotFound)
        assertTrue(error.message.contains("does not exist"))
    }

    @Test
    fun `ensureReadable fails for directory`() {
        val directory = temporaryFolder.newFolder("dir")
        val result = directory.ensureReadable()
        
        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Validation)
        assertTrue(error.message.contains("not a file"))
    }

    @Test
    fun `safeDelete succeeds for existing file`() {
        val file = temporaryFolder.newFile("to_delete.txt")
        assertTrue(file.exists())
        
        val result = file.safeDelete()
        
        assertTrue(result.isSuccess())
        assertFalse(file.exists())
    }

    @Test
    fun `safeDelete succeeds for non-existent file`() {
        val file = File(temporaryFolder.root, "does_not_exist.txt")
        assertFalse(file.exists())
        
        val result = file.safeDelete()
        
        assertTrue(result.isSuccess())
    }

    @Test
    fun `safeDelete deletes directory recursively`() {
        val directory = temporaryFolder.newFolder("to_delete")
        val subFile = File(directory, "file.txt")
        subFile.writeText("content")
        assertTrue(directory.exists())
        assertTrue(subFile.exists())
        
        val result = directory.safeDelete()
        
        assertTrue(result.isSuccess())
        assertFalse(directory.exists())
        assertFalse(subFile.exists())
    }
}
