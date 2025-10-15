package com.buccancs.ui.settings

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.buccancs.data.storage.SpaceState
import com.buccancs.data.storage.SpaceStatus
import com.buccancs.data.storage.SessionUsage
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class SettingsScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun applyOrchestratorInvokesCallback() {
        var invoked = false
        val state = SettingsUiState.empty().copy(
            hostInput = "10.0.0.1",
            portInput = "50051",
            message = null
        )

        composeRule.setContent {
            MaterialTheme {
                SettingsScreen(
                    state = state,
                    onHostChanged = {},
                    onPortChanged = {},
                    onUseTlsChanged = {},
                    onRetentionMinFreeChanged = {},
                    onRetentionMaxSessionsChanged = {},
                    onRetentionMaxAgeDaysChanged = {},
                    onApplyOrchestrator = { invoked = true },
                    onApplyRetention = {},
                    onClearMessage = {},
                    onNavigateUp = {}
                )
            }
        }

        composeRule.onNodeWithTag("settings-apply-orchestrator", useUnmergedTree = true).performClick()

        assertTrue("Apply orchestrator should invoke callback", invoked)
    }

    @Test
    fun retentionApplyInvokesCallback() {
        var invoked = false
        val state = SettingsUiState.empty().copy(
            retentionMinFreeInput = "12",
            retentionMaxSessionsInput = "24",
            retentionMaxAgeDaysInput = "7"
        )

        composeRule.setContent {
            MaterialTheme {
                SettingsScreen(
                    state = state,
                    onHostChanged = {},
                    onPortChanged = {},
                    onUseTlsChanged = {},
                    onRetentionMinFreeChanged = {},
                    onRetentionMaxSessionsChanged = {},
                    onRetentionMaxAgeDaysChanged = {},
                    onApplyOrchestrator = {},
                    onApplyRetention = { invoked = true },
                    onClearMessage = {},
                    onNavigateUp = {}
                )
            }
        }

        composeRule.onNodeWithTag("settings-apply-retention", useUnmergedTree = true).performClick()

        assertTrue("Apply retention should invoke callback", invoked)
    }

    @Test
    fun messageCardDisplaysAndDismisses() {
        var dismissed = false
        val state = SettingsUiState.empty().copy(
            message = "Settings saved.",
            storageState = sampleStorage()
        )

        composeRule.setContent {
            MaterialTheme {
                SettingsScreen(
                    state = state,
                    onHostChanged = {},
                    onPortChanged = {},
                    onUseTlsChanged = {},
                    onRetentionMinFreeChanged = {},
                    onRetentionMaxSessionsChanged = {},
                    onRetentionMaxAgeDaysChanged = {},
                    onApplyOrchestrator = {},
                    onApplyRetention = {},
                    onClearMessage = { dismissed = true },
                    onNavigateUp = {}
                )
            }
        }

        composeRule.onNodeWithText("Settings saved.", ignoreCase = true, useUnmergedTree = true).assertIsDisplayed()
        composeRule.onNodeWithText("Dismiss", ignoreCase = true, useUnmergedTree = true).performClick()
        assertTrue("Dismiss should invoke callback", dismissed)
    }

    private fun sampleStorage(): SpaceState = SpaceState(
        totalBytes = 1_000_000,
        usedBytes = 640_000,
        availableBytes = 360_000,
        status = SpaceStatus.WARNING,
        sessions = listOf(SessionUsage("session-001", 42_000, 1_690_000_000_000))
    )
}
