package com.buccancs.ui.navigation

import com.buccancs.domain.model.DeviceId

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Clause : Screen("clause")
    data object Policy : Screen("policy/{themeType}") {
        fun createRoute(themeType: Int) = "policy/$themeType"
    }

    data object Version : Screen("version")
    data object Help : Screen("help")
    data object WebView : Screen("webview?url={url}&title={title}") {
        fun createRoute(url: String, title: String = "") = "webview?url=$url&title=$title"
    }

    data object LiveSession : Screen("live_session")
    data object Devices : Screen("devices")
    data object Library : Screen("library")
    data object Settings : Screen("settings")

    data object SessionDetail : Screen("session_detail/{sessionId}") {
        fun createRoute(sessionId: String) = "session_detail/$sessionId"
    }

    data object TopdonDevice : Screen("topdon/{deviceId}") {
        fun createRoute(deviceId: DeviceId) = "topdon/${deviceId.value}"
    }

    data object TopdonThermalPreview : Screen("topdon/{deviceId}/thermal") {
        fun createRoute(deviceId: DeviceId) = "topdon/${deviceId.value}/thermal"
    }
}

sealed class BottomNavItem(
    val screen: Screen,
    val title: String,
    val iconRes: String
) {
    data object LiveSession : BottomNavItem(
        screen = Screen.LiveSession,
        title = "Live",
        iconRes = "play_circle"
    )

    data object Devices : BottomNavItem(
        screen = Screen.Devices,
        title = "Devices",
        iconRes = "devices"
    )

    data object Library : BottomNavItem(
        screen = Screen.Library,
        title = "Sessions",
        iconRes = "folder"
    )

    data object Settings : BottomNavItem(
        screen = Screen.Settings,
        title = "Settings",
        iconRes = "settings"
    )

    companion object {
        val items = listOf(LiveSession, Devices, Library, Settings)
    }
}
