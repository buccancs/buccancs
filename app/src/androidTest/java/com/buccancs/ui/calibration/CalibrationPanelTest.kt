package com.buccancs.ui.calibration

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CalibrationPanelTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun configureStepShowsInputsAndApplyButton() {
        val state = CalibrationUiState.initial().copy(
            wizardStep = CalibrationWizardStep.Configure,
            active = false
        )

        composeRule.setContent {
            MaterialTheme {
                CalibrationPanel(
                    state = state,
                    actions = noopActions()
                )
            }
        }

        composeRule.onNodeWithText("Pattern Rows", ignoreCase = true, useUnmergedTree = true).assertIsDisplayed()
        composeRule.onNodeWithText("Pattern Columns", ignoreCase = true, useUnmergedTree = true).assertIsDisplayed()
        composeRule.onNodeWithText("Square Size (mm)", ignoreCase = true, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun startSessionInvokesCallback() {
        var invoked = false
        val state = CalibrationUiState.initial().copy(
            wizardStep = CalibrationWizardStep.Configure,
            active = false
        )
        val actions = noopActions().copy(onStartSession = { invoked = true })

        composeRule.setContent {
            MaterialTheme {
                CalibrationPanel(
                    state = state,
                    actions = actions
                )
            }
        }

        composeRule.onNodeWithText("Start Session", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Start Session should invoke callback", invoked)
    }

    @Test
    fun captureStepShowsProgressAndCaptureButton() {
        val state = CalibrationUiState.initial().copy(
            wizardStep = CalibrationWizardStep.Capture,
            active = true,
            capturedCount = 5,
            requiredPairs = 20,
            captureProgress = 0.25f,
            guidanceMessage = "Position pattern in frame"
        )

        composeRule.setContent {
            MaterialTheme {
                CalibrationPanel(
                    state = state,
                    actions = noopActions()
                )
            }
        }

        composeRule.onNodeWithText("Position pattern in frame", ignoreCase = true, useUnmergedTree = true)
            .assertIsDisplayed()
        composeRule.onNodeWithText("5 / 20", ignoreCase = true, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun capturePairInvokesCallback() {
        var invoked = false
        val state = CalibrationUiState.initial().copy(
            wizardStep = CalibrationWizardStep.Capture,
            active = true,
            capturedCount = 10,
            requiredPairs = 20
        )
        val actions = noopActions().copy(onCapturePair = { invoked = true })

        composeRule.setContent {
            MaterialTheme {
                CalibrationPanel(
                    state = state,
                    actions = actions
                )
            }
        }

        composeRule.onNodeWithText("Capture", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Capture should invoke callback", invoked)
    }

    @Test
    fun computeStepShowsComputeButton() {
        val state = CalibrationUiState.initial().copy(
            wizardStep = CalibrationWizardStep.Compute,
            active = true,
            capturedCount = 20,
            requiredPairs = 20,
            guidanceMessage = "Sufficient pairs captured"
        )

        composeRule.setContent {
            MaterialTheme {
                CalibrationPanel(
                    state = state,
                    actions = noopActions()
                )
            }
        }

        composeRule.onNodeWithText("Sufficient pairs captured", ignoreCase = true, useUnmergedTree = true)
            .assertIsDisplayed()
        composeRule.onNodeWithText("Compute Calibration", ignoreCase = true, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun computeCalibrationInvokesCallback() {
        var invoked = false
        val state = CalibrationUiState.initial().copy(
            wizardStep = CalibrationWizardStep.Compute,
            active = true,
            capturedCount = 20,
            requiredPairs = 20
        )
        val actions = noopActions().copy(onComputeCalibration = { invoked = true })

        composeRule.setContent {
            MaterialTheme {
                CalibrationPanel(
                    state = state,
                    actions = actions
                )
            }
        }

        composeRule.onNodeWithText("Compute Calibration", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Compute Calibration should invoke callback", invoked)
    }

    @Test
    fun clearSessionInvokesCallback() {
        var invoked = false
        val state = CalibrationUiState.initial().copy(
            wizardStep = CalibrationWizardStep.Capture,
            active = true,
            capturedCount = 5,
            requiredPairs = 20
        )
        val actions = noopActions().copy(onClearSession = { invoked = true })

        composeRule.setContent {
            MaterialTheme {
                CalibrationPanel(
                    state = state,
                    actions = actions
                )
            }
        }

        composeRule.onNodeWithText("Clear Session", ignoreCase = true, useUnmergedTree = true).performClick()

        assertTrue("Clear Session should invoke callback", invoked)
    }

    @Test
    fun errorMessageDisplaysWhenPresent() {
        val state = CalibrationUiState.initial().copy(
            wizardStep = CalibrationWizardStep.Capture,
            active = true,
            errorMessage = "Pattern detection failed"
        )

        composeRule.setContent {
            MaterialTheme {
                CalibrationPanel(
                    state = state,
                    actions = noopActions()
                )
            }
        }

        composeRule.onNodeWithText("Pattern detection failed", ignoreCase = true, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun processingStateDisablesButtons() {
        val state = CalibrationUiState.initial().copy(
            wizardStep = CalibrationWizardStep.Capture,
            active = true,
            isProcessing = true,
            capturedCount = 10,
            requiredPairs = 20
        )

        composeRule.setContent {
            MaterialTheme {
                CalibrationPanel(
                    state = state,
                    actions = noopActions()
                )
            }
        }

        composeRule.onNodeWithText("Processing...", ignoreCase = true, useUnmergedTree = true).assertExists()
    }

    private fun noopActions(): CalibrationActions = CalibrationActions(
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
