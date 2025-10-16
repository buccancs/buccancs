package com.buccancs.desktop.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.buccancs.desktop.ui.state.ControlPanelState
import com.buccancs.desktop.ui.theme.BuccancsTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ControlPanelTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun startButtonEnabledWhenSessionInactive() {
        var startInvoked = false
        composeRule.setContent {
            BuccancsTheme {
                ControlPanel(
                    control = ControlPanelState(),
                    sessionActive = false,
                    onStartSession = { startInvoked = true },
                    onStopSession = {},
                    onOperatorChange = {},
                    onSubjectChange = {},
                    onSyncTypeChange = {},
                    onSyncDelayChange = {},
                    onSyncTargetsChange = {},
                    onSendSync = {},
                    onEventIdChange = {},
                    onEventDescriptionChange = {},
                    onEventTargetsChange = {},
                    onAddEvent = {},
                    onStimulusIdChange = {},
                    onStimulusActionChange = {},
                    onStimulusTargetsChange = {},
                    onTriggerStimulus = {},
                    onSubjectEraseChange = {},
                    onSubjectErase = {}
                )
            }
        }

        composeRule.onNodeWithText("Start Session").assertIsEnabled().performClick()
        composeRule.onNodeWithText("Stop Session").assertIsNotEnabled()
        assertTrue("Start Session callback should invoke handler", startInvoked)
    }

    @Test
    fun stopButtonEnabledWhenSessionActive() {
        var stopInvoked = false
        composeRule.setContent {
            BuccancsTheme {
                ControlPanel(
                    control = ControlPanelState(),
                    sessionActive = true,
                    onStartSession = {},
                    onStopSession = { stopInvoked = true },
                    onOperatorChange = {},
                    onSubjectChange = {},
                    onSyncTypeChange = {},
                    onSyncDelayChange = {},
                    onSyncTargetsChange = {},
                    onSendSync = {},
                    onEventIdChange = {},
                    onEventDescriptionChange = {},
                    onEventTargetsChange = {},
                    onAddEvent = {},
                    onStimulusIdChange = {},
                    onStimulusActionChange = {},
                    onStimulusTargetsChange = {},
                    onTriggerStimulus = {},
                    onSubjectEraseChange = {},
                    onSubjectErase = {}
                )
            }
        }

        composeRule.onNodeWithText("Stop Session").assertIsEnabled().performClick()
        composeRule.onNodeWithText("Start Session").assertIsNotEnabled()
        assertTrue("Stop Session callback should invoke handler", stopInvoked)
    }

    @Test
    fun syncButtonEnabledOnlyWhenSessionActive() {
        composeRule.setContent {
            BuccancsTheme {
                ControlPanel(
                    control = ControlPanelState(),
                    sessionActive = true,
                    onStartSession = {},
                    onStopSession = {},
                    onOperatorChange = {},
                    onSubjectChange = {},
                    onSyncTypeChange = {},
                    onSyncDelayChange = {},
                    onSyncTargetsChange = {},
                    onSendSync = {},
                    onEventIdChange = {},
                    onEventDescriptionChange = {},
                    onEventTargetsChange = {},
                    onAddEvent = {},
                    onStimulusIdChange = {},
                    onStimulusActionChange = {},
                    onStimulusTargetsChange = {},
                    onTriggerStimulus = {},
                    onSubjectEraseChange = {},
                    onSubjectErase = {}
                )
            }
        }

        composeRule.onNodeWithText("Send Sync").assertIsEnabled()
    }
}
