package com.buccancs.desktop.ui.components

import androidx.compose.material3.Text
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.buccancs.desktop.ui.theme.BuccancsTheme
import org.junit.Rule
import org.junit.Test

/**
 * Tests for card components
 */
class CardsTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `BuccancsCard displays title and subtitle`() {
        composeRule.setContent {
            BuccancsTheme {
                BuccancsCard(
                    title = "Test Title",
                    subtitle = "Test Subtitle"
                ) {
                    Text("Card Content")
                }
            }
        }

        composeRule.onNodeWithText("Test Title").assertIsDisplayed()
        composeRule.onNodeWithText("Test Subtitle").assertIsDisplayed()
        composeRule.onNodeWithText("Card Content").assertIsDisplayed()
    }

    @Test
    fun `BuccancsCard without subtitle displays only title`() {
        composeRule.setContent {
            BuccancsTheme {
                BuccancsCard(title = "Test Title") {
                    Text("Card Content")
                }
            }
        }

        composeRule.onNodeWithText("Test Title").assertIsDisplayed()
        composeRule.onNodeWithText("Card Content").assertIsDisplayed()
    }

    @Test
    fun `BuccancsOutlinedCard displays correctly`() {
        composeRule.setContent {
            BuccancsTheme {
                BuccancsOutlinedCard(
                    title = "Outlined Title",
                    subtitle = "Outlined Subtitle"
                ) {
                    Text("Outlined Content")
                }
            }
        }

        composeRule.onNodeWithText("Outlined Title").assertIsDisplayed()
        composeRule.onNodeWithText("Outlined Subtitle").assertIsDisplayed()
        composeRule.onNodeWithText("Outlined Content").assertIsDisplayed()
    }

    @Test
    fun `StatusCard displays with different statuses`() {
        composeRule.setContent {
            BuccancsTheme {
                StatusCard(
                    title = "Success Status",
                    status = StatusType.Success
                ) {
                    Text("Success Content")
                }
            }
        }

        composeRule.onNodeWithText("Success Status").assertIsDisplayed()
        composeRule.onNodeWithText("Success Content").assertIsDisplayed()
    }

    @Test
    fun `StatusCard supports all status types`() {
        val statuses = listOf(
            StatusType.Success,
            StatusType.Warning,
            StatusType.Error,
            StatusType.Info
        )

        statuses.forEach { status ->
            composeRule.setContent {
                BuccancsTheme {
                    StatusCard(
                        title = "Status: $status",
                        status = status
                    ) {}
                }
            }

            composeRule.onNodeWithText("Status: $status").assertExists()
        }
    }
}
