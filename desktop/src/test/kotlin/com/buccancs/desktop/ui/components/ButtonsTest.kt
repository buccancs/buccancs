package com.buccancs.desktop.ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.buccancs.desktop.ui.theme.BuccancsTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

/**
 * Tests for button components
 */
class ButtonsTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `PrimaryButton displays text and handles clicks`() {
        var clicked = false
        composeRule.setContent {
            BuccancsTheme {
                PrimaryButton(
                    text = "Primary Action",
                    onClick = { clicked = true }
                )
            }
        }

        composeRule.onNodeWithText("Primary Action")
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()

        assertTrue("PrimaryButton should handle click", clicked)
    }

    @Test
    fun `SecondaryButton displays text and handles clicks`() {
        var clicked = false
        composeRule.setContent {
            BuccancsTheme {
                SecondaryButton(
                    text = "Secondary Action",
                    onClick = { clicked = true }
                )
            }
        }

        composeRule.onNodeWithText("Secondary Action")
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()

        assertTrue("SecondaryButton should handle click", clicked)
    }

    @Test
    fun `TertiaryButton displays text and handles clicks`() {
        var clicked = false
        composeRule.setContent {
            BuccancsTheme {
                TertiaryButton(
                    text = "Tertiary Action",
                    onClick = { clicked = true }
                )
            }
        }

        composeRule.onNodeWithText("Tertiary Action")
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()

        assertTrue("TertiaryButton should handle click", clicked)
    }

    @Test
    fun `TonalButton displays text and handles clicks`() {
        var clicked = false
        composeRule.setContent {
            BuccancsTheme {
                TonalButton(
                    text = "Tonal Action",
                    onClick = { clicked = true }
                )
            }
        }

        composeRule.onNodeWithText("Tonal Action")
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()

        assertTrue("TonalButton should handle click", clicked)
    }

    @Test
    fun `buttons respect enabled state`() {
        composeRule.setContent {
            BuccancsTheme {
                PrimaryButton(
                    text = "Disabled Button",
                    onClick = {},
                    enabled = false
                )
            }
        }

        composeRule.onNodeWithText("Disabled Button")
            .assertIsDisplayed()
            .assertIsNotEnabled()
    }

    @Test
    fun `all button types render without crashing`() {
        composeRule.setContent {
            BuccancsTheme {
                PrimaryButton(text = "Primary", onClick = {})
                SecondaryButton(text = "Secondary", onClick = {})
                TertiaryButton(text = "Tertiary", onClick = {})
                TonalButton(text = "Tonal", onClick = {})
            }
        }

        composeRule.onNodeWithText("Primary").assertExists()
        composeRule.onNodeWithText("Secondary").assertExists()
        composeRule.onNodeWithText("Tertiary").assertExists()
        composeRule.onNodeWithText("Tonal").assertExists()
    }
}
