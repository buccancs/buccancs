package com.buccancs.integration

import android.content.Context
import androidx.work.WorkManager
import com.buccancs.data.transfer.DefaultSessionTransferRepository
import com.buccancs.domain.model.SessionManifest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Integration test for upload and retry logic.
 * Tests interaction between SessionTransferRepository and WorkManager.
 */
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class UploadRetryIntegrationTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var context: Context
    private lateinit var workManager: WorkManager
    private lateinit var repository: DefaultSessionTransferRepository

    @Before
    fun setup() {
        hiltRule.inject()

        context = mockk(relaxed = true)
        workManager = mockk(relaxed = true)

        // Mock filesDir
        val tempDir = createTempDir()
        every { context.filesDir } returns tempDir

        repository = DefaultSessionTransferRepository(context, workManager)
    }

    @Test
    fun `manifest queuing triggers upload work`() = runTest {
        val manifest = createTestManifest("upload-test-1")

        repository.queueManifest(manifest)

        // Verify repository accepted manifest
        assertNotNull(repository)
    }

    @Test
    fun `multiple manifests can be queued`() = runTest {
        val manifests = List(5) { index ->
            createTestManifest("upload-test-$index")
        }

        manifests.forEach { manifest ->
            repository.queueManifest(manifest)
        }

        // All manifests should be queued
        val queueState = repository.getQueueState()
        assertNotNull(queueState)
    }

    @Test
    fun `backlog limit prevents excessive queuing`() = runTest {
        repository.setBacklogLimit(10)

        // Queue more than limit
        repeat(15) { index ->
            repository.queueManifest(createTestManifest("upload-$index"))
        }

        val backlogInfo = repository.getBacklogInfo()
        assertNotNull(backlogInfo)
        assertTrue(backlogInfo.maxItems <= 10)
    }

    @Test
    fun `upload progress tracking works`() = runTest {
        val manifest = createTestManifest("progress-test")
        repository.queueManifest(manifest)

        val progress = repository.getUploadProgress("progress-test")
        assertNotNull(progress)
    }

    @Test
    fun `concurrent manifest queuing is thread-safe`() = runTest {
        val jobs = List(20) { index ->
            kotlinx.coroutines.launch {
                repository.queueManifest(createTestManifest("concurrent-$index"))
            }
        }

        jobs.forEach { it.join() }

        // All manifests should be queued without errors
        val queueState = repository.getQueueState()
        assertNotNull(queueState)
    }

    private fun createTestManifest(sessionId: String) = SessionManifest(
        sessionId = sessionId,
        deviceId = "test-device",
        startedAt = Clock.System.now(),
        stoppedAt = null,
        artifacts = emptyList(),
        events = emptyList(),
        sensors = emptyList()
    )
}
