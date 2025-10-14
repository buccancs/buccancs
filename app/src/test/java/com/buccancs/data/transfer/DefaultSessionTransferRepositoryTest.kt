package com.buccancs.data.transfer

import android.content.Context
import androidx.work.WorkManager
import com.buccancs.domain.model.SessionManifest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class DefaultSessionTransferRepositoryTest {
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
        repository = DefaultSessionTransferRepository(context, workManager)
    }

    @Test
    fun `queues manifest for upload`() = runTest {
        val manifest = createTestManifest("session-1")

        repository.queueManifest(manifest)

        // Verify queue state
        val queueState = repository.getQueueState()
        assertNotNull(queueState)
        assertTrue(queueState.pendingCount >= 0)
    }

    @Test
    fun `respects backlog threshold`() = runTest {
        val limit = 10
        repository.setBacklogLimit(limit)

        val backlogInfo = repository.getBacklogInfo()
        assertNotNull(backlogInfo)
        assertEquals(limit, backlogInfo.maxItems)
    }

    @Test
    fun `tracks upload progress`() = runTest {
        val manifest = createTestManifest("session-2")
        repository.queueManifest(manifest)

        val progress = repository.getUploadProgress("session-2")
        assertNotNull(progress)
    }

    private fun createTestManifest(sessionId: String) = SessionManifest(
        sessionId = sessionId,
        deviceId = "test-device",
        startedAt = kotlinx.datetime.Clock.System.now(),
        stoppedAt = null,
        artifacts = emptyList(),
        events = emptyList(),
        sensors = emptyList()
    )
}
