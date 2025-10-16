package com.buccancs.desktop.ui.navigation

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Tests for navigation system
 */
class NavigationTest {

    @Test
    fun `all screens have unique routes`() {
        val screens = listOf(
            Screen.Dashboard,
            Screen.Settings,
            Screen.SensorStatus,
            Screen.Synchronisation,
            Screen.Calibration,
            Screen.Preview,
            Screen.VideoPlayback,
            Screen.FileExplorer,
            Screen.Users
        )
        
        val routes = screens.map { it.route }
        assertEquals(screens.size, routes.distinct().size, "All routes should be unique")
    }

    @Test
    fun `all screens have titles`() {
        val screens = listOf(
            Screen.Dashboard,
            Screen.Settings,
            Screen.SensorStatus,
            Screen.Synchronisation,
            Screen.Calibration,
            Screen.Preview,
            Screen.VideoPlayback,
            Screen.FileExplorer,
            Screen.Users
        )
        
        screens.forEach { screen ->
            assertTrue(screen.title.isNotBlank(), "Screen ${screen.route} should have a title")
        }
    }

    @Test
    fun `fromRoute returns correct screen`() {
        assertEquals(Screen.Dashboard, Screen.fromRoute("dashboard"))
        assertEquals(Screen.Settings, Screen.fromRoute("settings"))
        assertEquals(Screen.SensorStatus, Screen.fromRoute("sensors"))
        assertEquals(Screen.Synchronisation, Screen.fromRoute("sync"))
        assertEquals(Screen.Calibration, Screen.fromRoute("calibration"))
        assertEquals(Screen.Preview, Screen.fromRoute("preview"))
        assertEquals(Screen.VideoPlayback, Screen.fromRoute("video"))
        assertEquals(Screen.FileExplorer, Screen.fromRoute("files"))
        assertEquals(Screen.Users, Screen.fromRoute("users"))
    }

    @Test
    fun `fromRoute returns Dashboard for unknown route`() {
        assertEquals(Screen.Dashboard, Screen.fromRoute("unknown"))
        assertEquals(Screen.Dashboard, Screen.fromRoute(""))
        assertEquals(Screen.Dashboard, Screen.fromRoute("invalid"))
    }
}
