package com.buccancs.desktop.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.buccancs.desktop.ui.state.RetentionState
import com.buccancs.desktop.ui.state.TransferStatusItem
import org.junit.Rule
import org.junit.Test

class RetentionAndTransferTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun transferSectionDisplaysTransferDetails() {
        val transfer = TransferStatusItem(
            sessionId = "session-42",
            deviceId = "topdon-001",
            fileName = "thermal.bin",
            streamType = "THERMAL_VIDEO",
            state = "IN_PROGRESS",
            attempt = 2,
            bytesTransferred = 512,
            bytesTotal = 1_024,
            errorMessage = "Retry scheduled"
        )

        composeRule.setContent {
            MaterialTheme {
                TransferSection(transfers = listOf(transfer))
            }
        }

        composeRule.onNodeWithText("Session session-42 - Device topdon-001").assertIsDisplayed()
        composeRule.onNodeWithText("File: thermal.bin (THERMAL_VIDEO)").assertIsDisplayed()
        composeRule.onNodeWithText("State: IN_PROGRESS (attempt 2) - 512.00 B / 1.00 KB").assertIsDisplayed()
        composeRule.onNodeWithText("Error: Retry scheduled").assertIsDisplayed()
    }

    @Test
    fun transferSectionShowsEmptyMessage() {
        composeRule.setContent {
            MaterialTheme {
                TransferSection(transfers = emptyList())
            }
        }

        composeRule.onNodeWithText("No transfers recorded").assertIsDisplayed()
    }

    @Test
    fun retentionSectionDisplaysBreakdownsAndAlerts() {
        val retention = RetentionState(
            perSessionBytes = mapOf("session-1" to 10_000L),
            perDeviceBytes = mapOf("device-a" to 7_500L),
            perSessionDeviceBytes = mapOf("session-1" to mapOf("device-a" to 5_000L)),
            totalBytes = 12_000L,
            breaches = listOf("Low free space")
        )

        composeRule.setContent {
            MaterialTheme {
                RetentionSection(retention = retention)
            }
        }

        composeRule.onNodeWithText("Retention").assertIsDisplayed()
        composeRule.onNode(hasText("Total stored: 11.72 KB", substring = true)).assertIsDisplayed()
        composeRule.onNode(hasText("session-1 -> 9.77 KB", substring = true)).assertIsDisplayed()
        composeRule.onNode(hasText("device-a -> 7.32 KB", substring = true)).assertIsDisplayed()
        composeRule.onNode(hasText("Session session-1", substring = true)).assertIsDisplayed()
        composeRule.onNode(hasText("device-a -> 4.88 KB", substring = true)).assertIsDisplayed()
        composeRule.onNodeWithText("Quota alerts").assertIsDisplayed()
        composeRule.onNodeWithText("Low free space").assertIsDisplayed()
    }
}
