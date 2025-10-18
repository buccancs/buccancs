package com.topdon.tc001.ui.navigation

sealed class MainDestination(
    val route: String
) {
    object Gallery :
        MainDestination(
            "gallery"
        )

    object Main :
        MainDestination(
            "main"
        )

    object Settings :
        MainDestination(
            "settings"
        )
}

sealed class AppDestination(
    val route: String
) {
    object Splash :
        AppDestination(
            "splash"
        )

    object Clause :
        AppDestination(
            "clause"
        )

    object Main :
        AppDestination(
            "main_container"
        )

    object DeviceType :
        AppDestination(
            "device_type"
        )

    object IRThermal :
        AppDestination(
            "ir_thermal/{isTC007}"
        ) {
        fun createRoute(
            isTC007: Boolean
        ) =
            "ir_thermal/$isTC007"
    }

    object IRMonocular :
        AppDestination(
            "ir_monocular"
        )

    object Policy :
        AppDestination(
            "policy/{type}"
        ) {
        fun createRoute(
            type: String
        ) =
            "policy/$type"
    }

    object Version :
        AppDestination(
            "version"
        )

    object MoreHelp :
        AppDestination(
            "more_help"
        )
}
