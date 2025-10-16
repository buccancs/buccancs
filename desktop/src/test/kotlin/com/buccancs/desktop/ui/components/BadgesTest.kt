package com.buccancs.desktop.ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.buccancs.desktop.ui.theme.BuccancsTheme
import org.junit.Rule
import org.junit.Test

/**
 * Tests for badge components
 */
class BadgesTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `StatusBadge displays custom text`() {
        composeRule.setContent {
            BuccancsTheme {
                StatusBadge(text = "Custom Status")
            }
        }

        composeRule.onNodeWithText("Custom Status").assertIsDisplayed()
    }

    @Test
    fun `ConnectedBadge displays Connected text`() {
        composeRule.setContent {
            BuccancsTheme {
                ConnectedBadge()
            }
        }

        composeRule.onNodeWithText("Connected").assertIsDisplayed()
    }

    @Test
    fun `DisconnectedBadge displays Disconnected text`() {
        composeRule.setContent {
            BuccancsTheme {
                DisconnectedBadge()
            }
        }

        composeRule.onNodeWithText("Disconnected").assertIsDisplayed()
    }

    @Test
    fun `RecordingBadge displays Recording text`() {
        composeRule.setContent {
            BuccancsTheme {
                RecordingBadge()
            }
        }

        composeRule.onNodeWithText("Recording").assertIsDisplayed()
    }

    @Test
    fun `IdleBadge displays Idle text`() {
        composeRule.setContent {
            BuccancsTheme {
                IdleBadge()
            }
        }

        composeRule.onNodeWithText("Idle").assertIsDisplayed()
    }

    @Test
    fun `all semantic badges render without crashing`() {
        composeRule.setContent {
            BuccancsTheme {
                ConnectedBadge()
                DisconnectedBadge()
                RecordingBadge()
                IdleBadge()
            }
        }

        composeRule.onNodeWithText("Connected").assertExists()
        composeRule.onNodeWithText("Disconnected").assertExists()
        composeRule.onNodeWithText("Recording").assertExists()
        composeRule.onNodeWithText("Idle").assertExists()
    }
}
