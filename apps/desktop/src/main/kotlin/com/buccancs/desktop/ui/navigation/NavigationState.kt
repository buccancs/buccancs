package com.buccancs.desktop.ui.navigation

/**
 * Navigation destinations for the desktop orchestrator
 */
sealed class Screen(
    val route: String,
    val title: String
) {
    object Dashboard :
        Screen(
            "dashboard",
            "Dashboard"
        )

    object Settings :
        Screen(
            "settings",
            "Settings"
        )

    object SensorStatus :
        Screen(
            "sensors",
            "Sensor Status"
        )

    object Synchronisation :
        Screen(
            "sync",
            "Synchronisation"
        )

    object Calibration :
        Screen(
            "calibration",
            "Calibration"
        )

    object Preview :
        Screen(
            "preview",
            "Live Preview"
        )

    object VideoPlayback :
        Screen(
            "video",
            "Video Playback"
        )

    object FileExplorer :
        Screen(
            "files",
            "File Explorer"
        )

    object Users :
        Screen(
            "users",
            "User Management"
        )

    companion object {
        fun fromRoute(
            route: String
        ): Screen {
            return when (route) {
                Dashboard.route -> Dashboard
                Settings.route -> Settings
                SensorStatus.route -> SensorStatus
                Synchronisation.route -> Synchronisation
                Calibration.route -> Calibration
                Preview.route -> Preview
                VideoPlayback.route -> VideoPlayback
                FileExplorer.route -> FileExplorer
                Users.route -> Users
                else -> Dashboard
            }
        }
    }
}
