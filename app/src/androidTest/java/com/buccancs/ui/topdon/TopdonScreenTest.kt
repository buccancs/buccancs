package com.buccancs.ui.topdon

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performClick
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonPreviewFrame
import com.buccancs.domain.model.TopdonSettings
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import kotlin.time.Instant

class TopdonScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun connectButtonInvokesCallback() {
        var invoked = false
        val state = TopdonUiState.initial()

        renderScreen(
            state = state,
            onConnect = { invoked = true }
        )

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

        renderScreen(
            state = state,
            onDisconnect = { invoked = true }
        )

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

        renderScreen(
            state = state,
            onStartPreview = { invoked = true }
        )

        composeRule.onNodeWithText("Start", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Start Preview should invoke callback", invoked)
    }

    @Test
    fun stopPreviewButtonInvokesCallbackWhenActive() {
        var invoked = false
        val state = TopdonUiState.initial().copy(
            isConnected = true,
            previewActive = true
        )

        renderScreen(
            state = state,
            onStopPreview = { invoked = true }
        )

        composeRule.onNodeWithText("Stop", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Stop Preview should invoke callback", invoked)
    }

    @Test
    fun refreshButtonInvokesCallback() {
        var invoked = false
        val state = TopdonUiState.initial()

        renderScreen(
            state = state,
            onRefresh = { invoked = true }
        )

        composeRule.onNodeWithContentDescription("Refresh", useUnmergedTree = true).performClick()

        assertTrue("Refresh should invoke callback", invoked)
    }

    @Test
    fun errorMessageDisplaysAndClears() {
        var cleared = false
        val state = TopdonUiState.initial().copy(
            errorMessage = "USB permission denied"
        )

        renderScreen(
            state = state,
            onClearError = { cleared = true }
        )

        composeRule.onNodeWithText("USB permission denied", ignoreCase = true, useUnmergedTree = true)
            .assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Dismiss error", useUnmergedTree = true).performClick()

        assertTrue("Error chip should invoke clear callback", cleared)
    }

    @Test
    fun previewFrameDataRendersWhenPresent() {
        val state = TopdonUiState.initial().copy(
            isConnected = true,
            previewActive = true,
            previewFrame = previewFrame(
                superSampling = TopdonSuperSamplingFactor.X2
            )
        )

        renderScreen(state = state)

        composeRule.onNodeWithText("Resolution: 256 x 192 (x2)", ignoreCase = true, useUnmergedTree = true)
            .assertIsDisplayed()
        composeRule.onNodeWithText("Streaming", ignoreCase = true, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun idlePreviewMessageDisplaysWhenNoFrame() {
        val state = TopdonUiState.initial().copy(
            isConnected = true,
            previewActive = false,
            previewFrame = null
        )

        renderScreen(state = state)

        composeRule.onNodeWithText("Preview idle", ignoreCase = true, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun scanningMessageDisplaysWhenScanning() {
        val state = TopdonUiState.initial().copy(scanning = true)

        renderScreen(state = state)

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

        renderScreen(state = state)

        composeRule.onNodeWithText("Auto-connect on USB", ignoreCase = true)
            .performScrollTo()
            .assertIsDisplayed()
        composeRule.onNodeWithText("Starts preview automatically", ignoreCase = true)
            .performScrollTo()
            .assertIsDisplayed()
    }

    private fun renderScreen(
        state: TopdonUiState,
        onNavigateUp: () -> Unit = {},
        onNavigateToThermalPreview: () -> Unit = {},
        onNavigateToGallery: () -> Unit = {},
        onNavigateToSettings: () -> Unit = {},
        onNavigateToGuide: () -> Unit = {},
        onRefresh: () -> Unit = {},
        onConnect: () -> Unit = {},
        onDisconnect: () -> Unit = {},
        onStartPreview: () -> Unit = {},
        onStopPreview: () -> Unit = {},
        onTogglePreview: () -> Unit = {},
        onSetAutoConnect: (Boolean) -> Unit = {},
        onSelectPalette: (TopdonPalette) -> Unit = {},
        onSelectSuperSampling: (TopdonSuperSamplingFactor) -> Unit = {},
        onUpdatePreviewFps: (Int) -> Unit = {},
        onClearError: () -> Unit = {}
    ) {
        composeRule.setContent {
            MaterialTheme {
                TopdonScreen(
                    state = state,
                    onNavigateUp = onNavigateUp,
                    onNavigateToThermalPreview = onNavigateToThermalPreview,
                    onNavigateToGallery = onNavigateToGallery,
                    onNavigateToSettings = onNavigateToSettings,
                    onNavigateToGuide = onNavigateToGuide,
                    onRefresh = onRefresh,
                    onConnect = onConnect,
                    onDisconnect = onDisconnect,
                    onStartPreview = onStartPreview,
                    onStopPreview = onStopPreview,
                    onTogglePreview = onTogglePreview,
                    onSetAutoConnect = onSetAutoConnect,
                    onSelectPalette = onSelectPalette,
                    onSelectSuperSampling = onSelectSuperSampling,
                    onUpdatePreviewFps = onUpdatePreviewFps,
                    onClearError = onClearError
                )
            }
        }
    }

    private fun previewFrame(
        timestamp: Instant = Instant.parse("2025-10-15T04:00:00Z"),
        width: Int = 256,
        height: Int = 192,
        mimeType: String = "image/jpeg",
        payload: ByteArray = ByteArray(16),
        superSampling: TopdonSuperSamplingFactor = TopdonSuperSamplingFactor.X2
    ): TopdonPreviewFrame = TopdonPreviewFrame(
        timestamp = timestamp,
        width = width,
        height = height,
        mimeType = mimeType,
        payload = payload,
        superSamplingFactor = superSampling.multiplier
    )
}
