package com.buccancs.ui.session

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.buccancs.application.stimulus.StimulusCue
import com.buccancs.application.stimulus.StimulusState
import com.buccancs.data.storage.SessionUsage
import com.buccancs.data.storage.SpaceState
import com.buccancs.data.storage.SpaceStatus
import com.buccancs.domain.model.*
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import kotlin.time.Instant

class LiveSessionScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun addBookmarkButtonInvokesCallback() {
        var invoked = false
        composeRule.setContent {
            MaterialTheme {
                LiveSessionScreen(
                    state = LiveSessionUiState.initial(),
                    onNavigateUp = {},
                    onAddBookmark = { invoked = true },
                    onTriggerStimulus = {}
                )
            }
        }

        composeRule.onNodeWithText("Add Bookmark", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Add Bookmark should invoke callback", invoked)
    }

    @Test
    fun previewStimulusInvokesCallback() {
        var invoked = false
        val state = LiveSessionUiState.initial().copy(
            stimulus = StimulusState(hasExternalDisplay = true)
        )
        composeRule.setContent {
            MaterialTheme {
                LiveSessionScreen(
                    state = state,
                    onNavigateUp = {},
                    onAddBookmark = {},
                    onTriggerStimulus = { invoked = true }
                )
            }
        }

        composeRule.onNodeWithText("Preview Stimulus", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Preview Stimulus should invoke callback", invoked)
    }

    @Test
    fun backlogAndUploadPanelsRenderWhenDataPresent() {
        val testState = richLiveSessionState()
        composeRule.setContent {
            MaterialTheme {
                LiveSessionScreen(
                    state = testState,
                    onNavigateUp = {},
                    onAddBookmark = {},
                    onTriggerStimulus = {}
                )
            }
        }

        composeRule.onNodeWithTag("live-list", useUnmergedTree = true)
            .performScrollToNode(hasTestTag("live-uploads"))
        composeRule.onAllNodesWithTag("live-uploads", useUnmergedTree = true).assertCountEquals(1)

        composeRule.onNodeWithTag("live-list", useUnmergedTree = true)
            .performScrollToNode(hasTestTag("live-backlog"))
        composeRule.onAllNodesWithTag("live-backlog", useUnmergedTree = true).assertCountEquals(1)
        composeRule.onNodeWithText("Level: WARNING", ignoreCase = true, useUnmergedTree = true).assertIsDisplayed()
        composeRule.onNodeWithText("Queue exceeded threshold", ignoreCase = true, useUnmergedTree = true)
            .assertIsDisplayed()
        composeRule.onNodeWithText("Important marker", ignoreCase = true, useUnmergedTree = true).assertIsDisplayed()
        composeRule.onNodeWithText("Topdon TC001 (TOPDON_TC001)", ignoreCase = true, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun deviceListShowsPlaceholderWhenEmpty() {
        composeRule.setContent {
            MaterialTheme {
                LiveSessionScreen(
                    state = LiveSessionUiState.initial(),
                    onNavigateUp = {},
                    onAddBookmark = {},
                    onTriggerStimulus = {}
                )
            }
        }

        composeRule.onNodeWithTag("live-list", useUnmergedTree = true)
            .performScrollToNode(hasTestTag("live-no-devices"))
        composeRule.onAllNodesWithTag("live-no-devices", useUnmergedTree = true).assertCountEquals(1)
    }

    private fun richLiveSessionState(): LiveSessionUiState {
        val instant = Instant.parse("2025-10-14T11:00:00Z")
        val deviceId = DeviceId("topdon-001")
        val device = SensorDevice(
            id = deviceId,
            displayName = "Topdon TC001",
            type = SensorDeviceType.TOPDON_TC001,
            capabilities = setOf(SensorStreamType.THERMAL_VIDEO),
            connectionStatus = ConnectionStatus.Connected(
                since = instant,
                batteryPercent = 94,
                rssiDbm = -45
            ),
            isSimulated = false,
            attributes = mapOf("previewFps" to "30")
        )
        val stream = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.THERMAL_VIDEO,
            sampleRateHz = null,
            frameRateFps = 30.0,
            lastSampleTimestamp = instant,
            bufferedDurationSeconds = 2.5,
            isStreaming = true,
            isSimulated = false
        )
        val recording = RecordingState(
            lifecycle = RecordingLifecycleState.Recording,
            anchor = RecordingSessionAnchor(
                sessionId = "session-123",
                referenceTimestamp = instant,
                sharedClockOffsetMillis = 12
            ),
            updatedAt = instant
        )
        val syncStatus = TimeSyncStatus(
            offsetMillis = 2,
            roundTripMillis = 35,
            lastSync = instant,
            driftEstimateMillisPerMinute = 0.4,
            filteredRoundTripMillis = 32.1,
            quality = TimeSyncQuality.GOOD,
            sampleCount = 10,
            regressionSlopeMillisPerMinute = 0.05
        )
        val uploads = listOf(
            UploadStatus(
                sessionId = "session-123",
                deviceId = deviceId,
                streamType = SensorStreamType.THERMAL_VIDEO,
                fileName = "thermal_frame.bin",
                bytesTotal = 2_048,
                bytesTransferred = 1_024,
                attempt = 1,
                state = UploadState.IN_PROGRESS
            )
        )
        val backlog = UploadBacklogState(
            level = UploadBacklogLevel.WARNING,
            queuedCount = 3,
            queuedBytes = 9_216,
            message = "Queue exceeded threshold",
            perSessionQueued = mapOf("session-123" to 3),
            perSessionBytes = mapOf("session-123" to 9_216L)
        )
        val recovery = listOf(
            UploadRecoveryRecord(
                sessionId = "session-123",
                deviceId = deviceId,
                streamType = SensorStreamType.THERMAL_VIDEO,
                attempt = 2,
                state = UploadState.FAILED,
                timestamp = instant,
                bytesTransferred = 512,
                bytesTotal = 2_048,
                network = NetworkSnapshot(connected = true, transport = "WIFI", metered = false),
                errorMessage = "Timeout"
            )
        )
        val bookmarks = listOf(
            RecordingBookmark(
                id = "b1",
                label = "Important marker",
                timestamp = instant
            )
        )
        val deviceEvents = listOf(
            DeviceEvent(
                id = "evt-1",
                type = DeviceEventType.EVENT_MARKER,
                label = "Stimulus sent",
                scheduledAt = instant,
                receivedAt = instant
            )
        )
        val space = SpaceState(
            totalBytes = 500_000,
            usedBytes = 320_000,
            availableBytes = 180_000,
            status = SpaceStatus.WARNING,
            sessions = listOf(SessionUsage("session-123", 42_000, instant.toEpochMilliseconds()))
        )
        return LiveSessionUiState(
            recording = recording,
            streams = listOf(stream),
            devices = listOf(device),
            syncStatus = syncStatus,
            syncHistory = listOf(
                TimeSyncObservation(
                    timestamp = instant,
                    offsetMillis = 1.2,
                    roundTripMillis = 34.0
                )
            ),
            uploads = uploads,
            recoveries = recovery,
            backlog = backlog,
            events = deviceEvents,
            bookmarks = bookmarks,
            storage = space,
            simulationEnabled = false,
            stimulus = StimulusState(
                hasExternalDisplay = true,
                activeCue = StimulusCue.preview(),
                activeCueEndsAtEpochMs = instant.toEpochMilliseconds() + 1_000,
                lastCue = StimulusCue.preview()
            ),
            throttleLevel = PerformanceThrottleLevel.CONSERVE
        )
    }
}
