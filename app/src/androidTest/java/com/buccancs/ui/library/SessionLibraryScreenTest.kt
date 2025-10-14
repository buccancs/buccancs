package com.buccancs.ui.library

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import kotlinx.datetime.Instant
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class SessionLibraryScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingStateShowsIndicator() {
        val state = SessionLibraryUiState(
            isLoading = true,
            sessions = emptyList(),
            errorMessage = null
        )

        composeRule.setContent {
            MaterialTheme {
                SessionLibraryScreen(
                    state = state,
                    onRefresh = {},
                    onSessionSelected = {},
                    onNavigateUp = {}
                )
            }
        }

        composeRule.onNodeWithText("Loading sessions…").assertIsDisplayed()
    }

    @Test
    fun errorStateInvokesRefreshWhenRetryClicked() {
        var refreshed = false
        val state = SessionLibraryUiState(
            isLoading = false,
            sessions = emptyList(),
            errorMessage = "Network unavailable"
        )

        composeRule.setContent {
            MaterialTheme {
                SessionLibraryScreen(
                    state = state,
                    onRefresh = { refreshed = true },
                    onSessionSelected = {},
                    onNavigateUp = {}
                )
            }
        }

        composeRule.onNodeWithText("Retry").performClick()

        assertTrue("Retry button should invoke refresh", refreshed)
    }

    @Test
    fun sessionSelectionPropagatesSessionId() {
        var selectedSession: String? = null
        val session = SessionSummary(
            sessionId = "session-abc",
            startedAt = Instant.parse("2025-10-13T09:00:00Z"),
            endedAt = Instant.parse("2025-10-13T09:30:00Z"),
            durationMillis = 30 * 60 * 1000,
            artifactCount = 12,
            totalBytes = 42_000_000,
            simulation = false
        )
        val state = SessionLibraryUiState(
            isLoading = false,
            sessions = listOf(session),
            errorMessage = null
        )

        composeRule.setContent {
            MaterialTheme {
                SessionLibraryScreen(
                    state = state,
                    onRefresh = {},
                    onSessionSelected = { selectedSession = it },
                    onNavigateUp = {}
                )
            }
        }

        composeRule.onNodeWithText("session-abc").performClick()

        assertEquals("session-abc", selectedSession)
    }
}
