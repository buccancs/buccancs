package com.buccancs.desktop.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.buccancs.desktop.ui.theme.BuccancsTheme
import org.junit.Rule
import org.junit.Test

/**
 * Tests that all screens render without crashing
 */
class ScreensRenderTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `SettingsScreen renders without crashing`() {
        composeRule.setContent {
            BuccancsTheme {
                SettingsScreen()
            }
        }

        composeRule.onNodeWithText("Settings").assertExists()
    }

    @Test
    fun `SensorStatusScreen renders without crashing`() {
        composeRule.setContent {
            BuccancsTheme {
                SensorStatusScreen()
            }
        }

        composeRule.onNodeWithText("Sensor Status").assertExists()
    }

    @Test
    fun `SynchronisationScreen renders without crashing`() {
        composeRule.setContent {
            BuccancsTheme {
                SynchronisationScreen()
            }
        }

        composeRule.onNodeWithText("Synchronisation").assertExists()
    }

    @Test
    fun `CalibrationScreen renders without crashing`() {
        composeRule.setContent {
            BuccancsTheme {
                CalibrationScreen()
            }
        }

        composeRule.onNodeWithText("Calibration").assertExists()
    }

    @Test
    fun `PreviewScreen renders without crashing`() {
        composeRule.setContent {
            BuccancsTheme {
                PreviewScreen()
            }
        }

        composeRule.onNodeWithText("Live Preview").assertExists()
    }

    @Test
    fun `VideoPlaybackScreen renders without crashing`() {
        composeRule.setContent {
            BuccancsTheme {
                VideoPlaybackScreen()
            }
        }

        composeRule.onNodeWithText("Recorded Sessions").assertExists()
    }

    @Test
    fun `FileExplorerScreen renders without crashing`() {
        composeRule.setContent {
            BuccancsTheme {
                FileExplorerScreen()
            }
        }

        composeRule.onNodeWithText("File Explorer").assertExists()
    }

    @Test
    fun `UsersScreen renders without crashing`() {
        composeRule.setContent {
            BuccancsTheme {
                UsersScreen()
            }
        }

        composeRule.onNodeWithText("User Management").assertExists()
    }
}
