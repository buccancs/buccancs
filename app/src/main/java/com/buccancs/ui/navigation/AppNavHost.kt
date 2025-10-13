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
                onOpenTopdon = { deviceId -> navController.navigate(destinations.topdon(deviceId)) }
            )
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
    private const val topdonRoot = "topdon"
    const val TOPDON_ARG = "deviceId"
    val topdonPattern: String = "$topdonRoot/{$TOPDON_ARG}"
    fun topdon(deviceId: DeviceId): String = "$topdonRoot/${deviceId.value}"
}
