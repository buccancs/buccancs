package com.buccancs.ui.library

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.buccancs.data.recording.manifest.*
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import kotlin.time.Instant

class SessionDetailScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingStateShowsProgressMessage() {
        val state = SessionDetailUiState(
            isLoading = true,
            sessionId = "session-1",
            manifest = null,
            totalBytes = 0,
            errorMessage = null
        )

        composeRule.setContent {
            MaterialTheme {
                SessionDetailScreen(
                    state = state,
                    onRefresh = {},
                    onNavigateUp = {}
                )
            }
        }

        composeRule.onNodeWithText("Loading session...", ignoreCase = true, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun errorStateOffersRetry() {
        var retried = false
        val state = SessionDetailUiState(
            isLoading = false,
            sessionId = "session-2",
            manifest = null,
            totalBytes = 0,
            errorMessage = "Manifest missing"
        )

        composeRule.setContent {
            MaterialTheme {
                SessionDetailScreen(
                    state = state,
                    onRefresh = { retried = true },
                    onNavigateUp = {}
                )
            }
        }

        composeRule.onNodeWithText("Retry", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Retry button should invoke refresh", retried)
    }

    @Test
    fun manifestDisplaysSummaryAndSections() {
        val manifest = sampleManifest()
        val state = SessionDetailUiState(
            isLoading = false,
            sessionId = "session-3",
            manifest = manifest,
            totalBytes = 123_456,
            errorMessage = null
        )

        composeRule.setContent {
            MaterialTheme {
                SessionDetailScreen(
                    state = state,
                    onRefresh = {},
                    onNavigateUp = {}
                )
            }
        }

        composeRule.onNodeWithTag("session-summary", useUnmergedTree = true).assertIsDisplayed()
        composeRule.onNodeWithTag("session-devices", useUnmergedTree = true).assertIsDisplayed()

        composeRule.onNodeWithTag("session-detail-list", useUnmergedTree = true)
            .performScrollToNode(hasTestTag("session-artifacts"))
        composeRule.onNodeWithTag("session-artifacts", useUnmergedTree = true).assertIsDisplayed()

        composeRule.onNodeWithTag("session-detail-list", useUnmergedTree = true)
            .performScrollToNode(hasTestTag("session-events"))
        composeRule.onNodeWithTag("session-events", useUnmergedTree = true).assertIsDisplayed()

        composeRule.onNodeWithTag("session-detail-list", useUnmergedTree = true)
            .performScrollToNode(hasTestTag("session-bookmarks"))
        composeRule.onNodeWithTag("session-bookmarks", useUnmergedTree = true).assertIsDisplayed()
        composeRule.onNodeWithText("session-total.json", ignoreCase = true, useUnmergedTree = true, substring = true)
            .assertExists()
        composeRule.onNodeWithText("Important event", ignoreCase = true, useUnmergedTree = true).assertExists()
        composeRule.onNodeWithText("Calibrated start", ignoreCase = true, useUnmergedTree = true).assertExists()
    }

    private fun sampleManifest(): SessionManifest {
        val instant = Instant.parse("2025-10-14T10:00:00Z")
        val device = DeviceManifest(
            deviceId = "topdon-001",
            displayName = "Topdon TC001",
            type = "THERMAL_CAMERA",
            capabilities = listOf("THERMAL_VIDEO"),
            attributes = mapOf("previewFps" to "30"),
            simulated = false
        )
        val artifact = ArtifactEntry(
            deviceId = "topdon-001",
            streamType = "THERMAL_VIDEO",
            relativePath = "thermal/session-total.json",
            contentUri = "file:///artifact.json",
            mimeType = "application/json",
            sizeBytes = 1024,
            checksumSha256 = "abcd",
            metadata = mapOf("format" to "json"),
            capturedEpochMs = instant.toEpochMillis()
        )
        val event = EventEntry(
            eventId = "evt-1",
            type = "EVENT_MARKER",
            label = "Important event",
            scheduledEpochMs = instant.toEpochMillis(),
            receivedEpochMs = instant.toEpochMillis()
        )
        val bookmark = BookmarkEntry(
            bookmarkId = "bm-1",
            label = "Calibrated start",
            timestampEpochMs = instant.toEpochMillis()
        )
        return SessionManifest(
            sessionId = "session-3",
            startedAt = instant.toString(),
            startedAtEpochMs = instant.toEpochMillis(),
            simulation = false,
            orchestratorOffsetMillis = 12,
            devices = listOf(device),
            events = listOf(event),
            artifacts = listOf(artifact),
            bookmarks = listOf(bookmark),
            endedAt = instant.plus(600_000, DateTimeUnit.MILLISECOND).toString(),
            endedAtEpochMs = instant.plus(600_000, DateTimeUnit.MILLISECOND).toEpochMillis(),
            durationMillis = 600_000,
            notes = emptyList()
        )
    }

    private fun Instant.toEpochMillis(): Long =
        (epochSeconds * 1000) + (nanosecondsOfSecond / 1_000_000)
}
