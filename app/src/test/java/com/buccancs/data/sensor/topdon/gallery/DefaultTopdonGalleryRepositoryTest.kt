package com.buccancs.data.sensor.topdon.gallery

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class DefaultTopdonGalleryRepositoryTest {

    private lateinit var repository: DefaultTopdonGalleryRepository
    private lateinit var context: Context
    private lateinit var testDir: File

    @Before
    fun setup() {
        context = mockk(relaxed = true)
        testDir = createTempDir("test_gallery")

        every { context.getExternalFilesDir(null) } returns testDir

        repository = DefaultTopdonGalleryRepository(context)
    }

    @Test
    fun `getAllMedia returns empty list initially`() = runTest {
        val media = repository.getAllMedia().first()
        assertEquals(0, media.size)
    }

    @Test
    fun `getImages returns empty list initially`() = runTest {
        val images = repository.getImages().first()
        assertEquals(0, images.size)
    }

    @Test
    fun `getImageById returns null for non-existent image`() = runTest {
        val image = repository.getImageById("non_existent")
        assertEquals(null, image)
    }

    @Test
    fun `deleteImage returns failure for non-existent image`() = runTest {
        val result = repository.deleteImage("non_existent")
        assertTrue(result.isFailure)
    }

    @Test
    fun `deleteMultiple handles multiple ids`() = runTest {
        val result = repository.deleteMultiple(listOf("id1", "id2"))
        assertTrue(result.isSuccess)
    }

    @Test
    fun `shareImages returns success`() = runTest {
        val result = repository.shareImages(listOf("id1"))
        assertTrue(result.isSuccess)
    }

    @Test
    fun `exportImages creates destination directory`() = runTest {
        val destDir = File(testDir, "export")
        val result = repository.exportImages(listOf("id1"), destDir.absolutePath)

        assertTrue(result.isSuccess)
        assertTrue(destDir.exists())
    }
}
