package com.buccancs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.TOPDON_TC001_DEVICE_ID
import com.buccancs.ui.MainRoute
import com.buccancs.ui.library.SessionDetailRoute
import com.buccancs.ui.library.SessionLibraryRoute
import com.buccancs.ui.session.LiveSessionRoute
import com.buccancs.ui.settings.SettingsRoute
import com.buccancs.ui.topdon.TopdonRoute

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    val destinations = remember { AppDestinations }
    NavHost(
        navController = navController,
        startDestination = destinations.dashboard
    ) {
        composable(destinations.dashboard) {
            MainRoute(
                onOpenLiveSession = { navController.navigate(destinations.liveSession) },
                onOpenLibrary = { navController.navigate(destinations.library) },
                onOpenSettings = { navController.navigate(destinations.settings) },
                onOpenTopdon = { deviceId -> navController.navigate(destinations.topdon(deviceId)) }
            )
        }
        composable(destinations.liveSession) {
            LiveSessionRoute(onNavigateUp = { navController.navigateUp() })
        }
        composable(destinations.library) {
            SessionLibraryRoute(
                onNavigateUp = { navController.navigateUp() },
                onSessionSelected = { sessionId ->
                    navController.navigate(destinations.sessionDetail(sessionId))
                }
            )
        }
        composable(
            route = destinations.sessionDetailPattern,
            arguments = listOf(navArgument(AppDestinations.SESSION_ID_ARG) { type = NavType.StringType })
        ) {
            SessionDetailRoute(onNavigateUp = { navController.navigateUp() })
        }
        composable(destinations.settings) {
            SettingsRoute(onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = destinations.topdonPattern,
            arguments = listOf(navArgument(AppDestinations.TOPDON_ARG) { type = NavType.StringType })
        ) { backStackEntry ->
            val arg = backStackEntry.arguments?.getString(AppDestinations.TOPDON_ARG)
            val deviceId = arg?.takeIf { it.isNotBlank() }?.let(::DeviceId) ?: TOPDON_TC001_DEVICE_ID
            TopdonRoute(
                deviceId = deviceId,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}

private object AppDestinations {
    const val dashboard = "dashboard"
    const val liveSession = "liveSession"
    const val library = "sessionLibrary"
    const val settings = "settings"
    private const val topdonRoot = "topdon"
    private const val sessionDetailRoot = "sessionDetail"
    const val TOPDON_ARG = "deviceId"
    const val SESSION_ID_ARG = "sessionId"
    val topdonPattern: String = "$topdonRoot/{$TOPDON_ARG}"
    val sessionDetailPattern: String = "$sessionDetailRoot/{$SESSION_ID_ARG}"
    fun topdon(deviceId: DeviceId): String = "$topdonRoot/${deviceId.value}"
    fun sessionDetail(sessionId: String): String = "$sessionDetailRoot/$sessionId"
}
