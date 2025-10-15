package com.buccancs.ui.topdon

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonPreviewFrame
import com.buccancs.domain.model.TopdonSettings
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import kotlin.time.Instant
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class TopdonScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun connectButtonInvokesCallback() {
        var invoked = false
        val state = TopdonUiState.initial()

        composeRule.setContent {
            MaterialTheme {
                TopdonScreen(
                    state = state,
                    onNavigateUp = {},
                    onRefresh = {},
                    onConnect = { invoked = true },
                    onDisconnect = {},
                    onStartPreview = {},
                    onStopPreview = {},
                    onTogglePreview = {},
                    onSetAutoConnect = {},
                    onSelectPalette = {},
                    onSelectSuperSampling = {},
                    onUpdatePreviewFps = {},
                    onClearError = {}
                )
            }
        }

        composeRule.onNodeWithText("Connect", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Connect should invoke callback", invoked)
    }

    @Test
    fun disconnectButtonInvokesCallbackWhenConnected() {
        var invoked = false
        val state = TopdonUiState.initial().copy(
            isConnected = true,
            connectionStatusLabel = "Connected"
        )

        composeRule.setContent {
            MaterialTheme {
                TopdonScreen(
                    state = state,
                    onNavigateUp = {},
                    onRefresh = {},
                    onConnect = {},
                    onDisconnect = { invoked = true },
                    onStartPreview = {},
                    onStopPreview = {},
                    onTogglePreview = {},
                    onSetAutoConnect = {},
                    onSelectPalette = {},
                    onSelectSuperSampling = {},
                    onUpdatePreviewFps = {},
                    onClearError = {}
                )
            }
        }

        composeRule.onNodeWithText("Disconnect", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Disconnect should invoke callback", invoked)
    }

    @Test
    fun startPreviewButtonInvokesCallbackWhenConnected() {
        var invoked = false
        val state = TopdonUiState.initial().copy(
            isConnected = true,
            previewActive = false
        )

        composeRule.setContent {
            MaterialTheme {
                TopdonScreen(
                    state = state,
                    onNavigateUp = {},
                    onRefresh = {},
                    onConnect = {},
                    onDisconnect = {},
                    onStartPreview = { invoked = true },
                    onStopPreview = {},
                    onTogglePreview = {},
                    onSetAutoConnect = {},
                    onSelectPalette = {},
                    onSelectSuperSampling = {},
                    onUpdatePreviewFps = {},
                    onClearError = {}
                )
            }
        }

        composeRule.onNodeWithText("Start Preview", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Start Preview should invoke callback", invoked)
    }

    @Test
    fun stopPreviewButtonInvokesCallbackWhenActive() {
        var invoked = false
        val state = TopdonUiState.initial().copy(
            isConnected = true,
            previewActive = true
        )

        composeRule.setContent {
            MaterialTheme {
                TopdonScreen(
                    state = state,
                    onNavigateUp = {},
                    onRefresh = {},
                    onConnect = {},
                    onDisconnect = {},
                    onStartPreview = {},
                    onStopPreview = { invoked = true },
                    onTogglePreview = {},
                    onSetAutoConnect = {},
                    onSelectPalette = {},
                    onSelectSuperSampling = {},
                    onUpdatePreviewFps = {},
                    onClearError = {}
                )
            }
        }

        composeRule.onNodeWithText("Stop Preview", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Stop Preview should invoke callback", invoked)
    }

    @Test
    fun refreshButtonInvokesCallback() {
        var invoked = false
        val state = TopdonUiState.initial()

        composeRule.setContent {
            MaterialTheme {
                TopdonScreen(
                    state = state,
                    onNavigateUp = {},
                    onRefresh = { invoked = true },
                    onConnect = {},
                    onDisconnect = {},
                    onStartPreview = {},
                    onStopPreview = {},
                    onTogglePreview = {},
                    onSetAutoConnect = {},
                    onSelectPalette = {},
                    onSelectSuperSampling = {},
                    onUpdatePreviewFps = {},
                    onClearError = {}
                )
            }
        }

        composeRule.onNodeWithText("Refresh", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Refresh should invoke callback", invoked)
    }

    @Test
    fun errorMessageDisplaysAndClears() {
        var cleared = false
        val state = TopdonUiState.initial().copy(
            errorMessage = "USB permission denied"
        )

        composeRule.setContent {
            MaterialTheme {
                TopdonScreen(
                    state = state,
                    onNavigateUp = {},
                    onRefresh = {},
                    onConnect = {},
                    onDisconnect = {},
                    onStartPreview = {},
                    onStopPreview = {},
                    onTogglePreview = {},
                    onSetAutoConnect = {},
                    onSelectPalette = {},
                    onSelectSuperSampling = {},
                    onUpdatePreviewFps = {},
                    onClearError = { cleared = true }
                )
            }
        }

        composeRule.onNodeWithText("USB permission denied", ignoreCase = true, useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        assertTrue("Error chip should invoke clear callback", cleared)
    }

    @Test
    fun previewFrameDataRendersWhenPresent() {
        val instant = Instant.parse("2025-10-15T04:00:00Z")
        val frame = TopdonPreviewFrame(
            timestamp = instant,
            width = 256,
            height = 192,
            palette = TopdonPalette.IRONBOW,
            superSamplingFactor = TopdonSuperSamplingFactor.X2,
            payload = ByteArray(256 * 192 * 3)
        )
        val state = TopdonUiState.initial().copy(
            isConnected = true,
            previewActive = true,
            previewFrame = frame
        )

        composeRule.setContent {
            MaterialTheme {
                TopdonScreen(
                    state = state,
                    onNavigateUp = {},
                    onRefresh = {},
                    onConnect = {},
                    onDisconnect = {},
                    onStartPreview = {},
                    onStopPreview = {},
                    onTogglePreview = {},
                    onSetAutoConnect = {},
                    onSelectPalette = {},
                    onSelectSuperSampling = {},
                    onUpdatePreviewFps = {},
                    onClearError = {}
                )
            }
        }

        composeRule.onNodeWithText("Resolution: 256 x 192 (x2)", ignoreCase = true, useUnmergedTree = true)
            .assertIsDisplayed()
        composeRule.onNodeWithText("Streaming", ignoreCase = true, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun idlePreviewMessageDisplaysWhenNoFrame() {
        val state = TopdonUiState.initial().copy(
            isConnected = true,
            previewActive = false,
            previewFrame = null
        )

        composeRule.setContent {
            MaterialTheme {
                TopdonScreen(
                    state = state,
                    onNavigateUp = {},
                    onRefresh = {},
                    onConnect = {},
                    onDisconnect = {},
                    onStartPreview = {},
                    onStopPreview = {},
                    onTogglePreview = {},
                    onSetAutoConnect = {},
                    onSelectPalette = {},
                    onSelectSuperSampling = {},
                    onUpdatePreviewFps = {},
                    onClearError = {}
                )
            }
        }

        composeRule.onNodeWithText("Preview idle", ignoreCase = true, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun scanningMessageDisplaysWhenScanning() {
        val state = TopdonUiState.initial().copy(scanning = true)

        composeRule.setContent {
            MaterialTheme {
                TopdonScreen(
                    state = state,
                    onNavigateUp = {},
                    onRefresh = {},
                    onConnect = {},
                    onDisconnect = {},
                    onStartPreview = {},
                    onStopPreview = {},
                    onTogglePreview = {},
                    onSetAutoConnect = {},
                    onSelectPalette = {},
                    onSelectSuperSampling = {},
                    onUpdatePreviewFps = {},
                    onClearError = {}
                )
            }
        }

        composeRule.onNodeWithText("Scanning USB...", ignoreCase = true, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun settingsSectionDisplaysOptions() {
        val state = TopdonUiState.initial().copy(
            settings = TopdonSettings(
                autoConnectOnAttach = true,
                palette = TopdonPalette.IRONBOW,
                superSampling = TopdonSuperSamplingFactor.X2,
                previewFpsLimit = 30
            )
        )

        composeRule.setContent {
            MaterialTheme {
                TopdonScreen(
                    state = state,
                    onNavigateUp = {},
                    onRefresh = {},
                    onConnect = {},
                    onDisconnect = {},
                    onStartPreview = {},
                    onStopPreview = {},
                    onTogglePreview = {},
                    onSetAutoConnect = {},
                    onSelectPalette = {},
                    onSelectSuperSampling = {},
                    onUpdatePreviewFps = {},
                    onClearError = {}
                )
            }
        }

        composeRule.onNodeWithText("Settings", ignoreCase = true, useUnmergedTree = true).assertIsDisplayed()
        composeRule.onNodeWithText("Auto-connect on USB", ignoreCase = true, useUnmergedTree = true)
            .assertIsDisplayed()
        composeRule.onNodeWithText("Device starts preview automatically", ignoreCase = true, useUnmergedTree = true)
            .assertIsDisplayed()
    }
}
