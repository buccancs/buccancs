package com.topdon.tc001.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.topdon.tc001.ui.navigation.AppNavHost
import com.topdon.tc001.ui.navigation.AppDestination
import com.topdon.tc001.ui.theme.TopdonTheme
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun versionScreen_displaysAppInfo() {
        composeTestRule.setContent {
            TopdonTheme {
                val navController = rememberNavController()
                AppNavHost(
                    navController = navController,
                    startDestination = AppDestination.Version.route
                )
            }
        }

        composeTestRule
            .onNodeWithText("Version Information")
            .assertIsDisplayed()
    }
}
