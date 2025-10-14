package com.buccancs.ui

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.buccancs.domain.model.DeviceId
import com.buccancs.ui.DeviceUiModel
import com.buccancs.ui.StreamUiModel
import com.buccancs.ui.calibration.CalibrationActions
import com.buccancs.ui.calibration.CalibrationUiState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class MainScreenNavigationTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun settingsButton_invokesCallback() {
        var settingsInvoked = false
        composeRule.setContent {
            MaterialTheme {
                MainScreen(
                    state = MainUiState.initial(),
                    onSessionIdChanged = {},
                    onToggleSimulation = {},
                    onConnectDevice = {},
                    onDisconnectDevice = {},
                    onStartRecording = {},
                    onStopRecording = {},
                    onOrchestratorHostChanged = {},
                    onOrchestratorPortChanged = {},
                    onOrchestratorUseTlsChanged = {},
                    onApplyConfig = {},
                    onClearConfigMessage = {},
                    onOpenLiveSession = {},
                    onOpenLibrary = {},
                    onOpenSettings = { settingsInvoked = true },
                    onOpenTopdon = { _: DeviceId -> },
                    calibrationState = CalibrationUiState.initial(),
                    calibrationActions = noopCalibrationActions()
                )
            }
        }

        composeRule.onNodeWithText("Settings").performClick()

        assertTrue("Settings callback should be invoked", settingsInvoked)
    }

    @Test
    fun liveSessionButton_invokesCallback() {
        var liveSessionInvoked = false
        composeRule.setContent {
            MaterialTheme {
                MainScreen(
                    state = MainUiState.initial(),
                    onSessionIdChanged = {},
                    onToggleSimulation = {},
                    onConnectDevice = {},
                    onDisconnectDevice = {},
                    onStartRecording = {},
                    onStopRecording = {},
                    onOrchestratorHostChanged = {},
                    onOrchestratorPortChanged = {},
                    onOrchestratorUseTlsChanged = {},
                    onApplyConfig = {},
                    onClearConfigMessage = {},
                    onOpenLiveSession = { liveSessionInvoked = true },
                    onOpenLibrary = {},
                    onOpenSettings = {},
                    onOpenTopdon = { _: DeviceId -> },
                    calibrationState = CalibrationUiState.initial(),
                    calibrationActions = noopCalibrationActions()
                )
            }
        }

        composeRule.onNodeWithText("Live Session").performClick()

        assertTrue("Live Session callback should be invoked", liveSessionInvoked)
    }

    @Test
    fun topdonButton_invokesCallbackWithExpectedDevice() {
        val deviceId = DeviceId("topdon-001")
        val device = DeviceUiModel(
            id = deviceId,
            title = "Topdon TC001",
            typeLabel = "Thermal Camera",
            connectionStatus = "Connected",
            batteryPercent = 95,
            isConnected = true,
            isSimulated = false,
            supportsTopdon = true,
            capabilityLabels = listOf("Thermal"),
            streams = listOf(
                StreamUiModel(
                    deviceId = deviceId,
                    typeLabel = "Thermal",
                    detail = "Preview active",
                    lastSampleTimestamp = "Just now",
                    isSimulated = false
                )
            )
        )
        var receivedDeviceId: DeviceId? = null
        composeRule.setContent {
            MaterialTheme {
                MainScreen(
                    state = MainUiState.initial().copy(devices = listOf(device)),
                    onSessionIdChanged = {},
                    onToggleSimulation = {},
                    onConnectDevice = {},
                    onDisconnectDevice = {},
                    onStartRecording = {},
                    onStopRecording = {},
                    onOrchestratorHostChanged = {},
                    onOrchestratorPortChanged = {},
                    onOrchestratorUseTlsChanged = {},
                    onApplyConfig = {},
                    onClearConfigMessage = {},
                    onOpenLiveSession = {},
                    onOpenLibrary = {},
                    onOpenSettings = {},
                    onOpenTopdon = { receivedDeviceId = it },
                    calibrationState = CalibrationUiState.initial(),
                    calibrationActions = noopCalibrationActions()
                )
            }
        }

        composeRule.onNodeWithText("Open Console").performClick()

        assertEquals("Topdon callback should receive device ID", deviceId, receivedDeviceId)
    }

    private fun noopCalibrationActions(): CalibrationActions = CalibrationActions(
        onRowsChanged = {},
        onColsChanged = {},
        onSquareSizeChanged = {},
        onRequiredPairsChanged = {},
        onApplySettings = {},
        onStartSession = {},
        onCapturePair = {},
        onComputeCalibration = {},
        onLoadCachedResult = {},
        onClearSession = {},
        onRemoveCapture = {}
    )
}
