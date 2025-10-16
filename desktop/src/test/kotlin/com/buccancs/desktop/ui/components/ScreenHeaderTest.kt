package com.buccancs.desktop.ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.buccancs.desktop.ui.theme.BuccancsTheme
import org.junit.Rule
import org.junit.Test

/**
 * Tests for ScreenHeader component
 */
class ScreenHeaderTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `ScreenHeader displays title`() {
        composeRule.setContent {
            BuccancsTheme {
                ScreenHeader(title = "Test Screen")
            }
        }

        composeRule.onNodeWithText("Test Screen").assertIsDisplayed()
    }

    @Test
    fun `ScreenHeader displays title and subtitle`() {
        composeRule.setContent {
            BuccancsTheme {
                ScreenHeader(
                    title = "Test Screen",
                    subtitle = "Test Description"
                )
            }
        }

        composeRule.onNodeWithText("Test Screen").assertIsDisplayed()
        composeRule.onNodeWithText("Test Description").assertIsDisplayed()
    }

    @Test
    fun `ScreenHeader without subtitle displays only title`() {
        composeRule.setContent {
            BuccancsTheme {
                ScreenHeader(title = "Test Screen", subtitle = null)
            }
        }

        composeRule.onNodeWithText("Test Screen").assertExists()
    }
}
