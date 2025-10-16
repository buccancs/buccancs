package com.topdon.tc001.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.topdon.tc001.ui.screens.DeviceInfo
import com.topdon.tc001.ui.screens.DeviceType
import com.topdon.tc001.ui.screens.MainScreen
import com.topdon.tc001.ui.theme.TopdonTheme
import org.junit.Rule
import org.junit.Test

class MainScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun noDevices_showsEmptyState() {
        composeTestRule.setContent {
            TopdonTheme {
                MainScreen(
                    devices = emptyList(),
                    onConnectDeviceClick = {},
                    onAddDeviceClick = {},
                    onDeviceClick = {},
                    onDeviceLongClick = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText("Connect Device", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun withDevices_showsDeviceList() {
        val testDevices = listOf(
            DeviceInfo("TC001", DeviceType.TC001_LINE, true, 85)
        )

        composeTestRule.setContent {
            TopdonTheme {
                MainScreen(
                    devices = testDevices,
                    onConnectDeviceClick = {},
                    onAddDeviceClick = {},
                    onDeviceClick = {},
                    onDeviceLongClick = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText("TC001")
            .assertIsDisplayed()
    }
}
