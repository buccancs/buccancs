package com.buccancs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.TOPDON_TC001_DEVICE_ID
import com.buccancs.ui.devices.DevicesRoute
import com.buccancs.ui.library.SessionDetailRoute
import com.buccancs.ui.library.SessionLibraryRoute
import com.buccancs.ui.session.LiveSessionRoute
import com.buccancs.ui.settings.SettingsRoute
import com.buccancs.ui.theme.MotionTransitions
import com.buccancs.ui.topdon.TopdonRoute
import com.buccancs.ui.topdon.thermal.ThermalPreviewRoute

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    AppScaffold(
        navController = navController,
        showBottomBar = true
    ) { paddingModifier ->
        NavHost(
            navController = navController,
            startDestination = Screen.LiveSession.route,
            modifier = paddingModifier,
            enterTransition = { MotionTransitions.fadeEnter() },
            exitTransition = { MotionTransitions.fadeExit() },
            popEnterTransition = { MotionTransitions.fadeEnter() },
            popExitTransition = { MotionTransitions.fadeExit() }
        ) {
            composable(
                route = Screen.LiveSession.route,
                enterTransition = { MotionTransitions.fadeEnter() },
                exitTransition = { MotionTransitions.fadeExit() }
            ) {
                LiveSessionRoute()
            }

            composable(
                route = Screen.Devices.route,
                enterTransition = { MotionTransitions.fadeEnter() },
                exitTransition = { MotionTransitions.fadeExit() }
            ) {
                DevicesRoute(
                    onOpenTopdon = { deviceId ->
                        navController.navigate(Screen.TopdonDevice.createRoute(deviceId))
                    }
                )
            }

            composable(
                route = Screen.Library.route,
                enterTransition = { MotionTransitions.fadeEnter() },
                exitTransition = { MotionTransitions.fadeExit() }
            ) {
                SessionLibraryRoute(
                    onNavigateUp = { navController.navigateUp() },
                    onSessionSelected = { sessionId ->
                        navController.navigate(Screen.SessionDetail.createRoute(sessionId))
                    }
                )
            }

            composable(
                route = Screen.Settings.route,
                enterTransition = { MotionTransitions.fadeEnter() },
                exitTransition = { MotionTransitions.fadeExit() }
            ) {
                SettingsRoute(onNavigateUp = { navController.navigateUp() })
            }

            composable(
                route = "session_detail/{sessionId}",
                arguments = listOf(navArgument("sessionId") { type = NavType.StringType }),
                enterTransition = { MotionTransitions.slideInFromEnd() },
                exitTransition = { MotionTransitions.fadeExit() },
                popEnterTransition = { MotionTransitions.fadeEnter() },
                popExitTransition = { MotionTransitions.slideOutToStart() }
            ) {
                SessionDetailRoute(onNavigateUp = { navController.navigateUp() })
            }

            composable(
                route = "topdon/{deviceId}",
                arguments = listOf(navArgument("deviceId") { type = NavType.StringType }),
                enterTransition = { MotionTransitions.slideInFromEnd() },
                exitTransition = { MotionTransitions.fadeExit() },
                popEnterTransition = { MotionTransitions.fadeEnter() },
                popExitTransition = { MotionTransitions.slideOutToStart() }
            ) { backStackEntry ->
                val arg = backStackEntry.arguments?.getString("deviceId")
                val deviceId = arg?.takeIf { it.isNotBlank() }?.let(::DeviceId) ?: TOPDON_TC001_DEVICE_ID
                TopdonRoute(
                    deviceId = deviceId,
                    onNavigateUp = { navController.navigateUp() },
                    onNavigateToThermalPreview = {
                        navController.navigate(Screen.TopdonThermalPreview.createRoute(deviceId))
                    }
                )
            }

            composable(
                route = "topdon/{deviceId}/thermal",
                arguments = listOf(navArgument("deviceId") { type = NavType.StringType }),
                enterTransition = { MotionTransitions.slideInFromEnd() },
                exitTransition = { MotionTransitions.fadeExit() },
                popEnterTransition = { MotionTransitions.fadeEnter() },
                popExitTransition = { MotionTransitions.slideOutToStart() }
            ) { backStackEntry ->
                val arg = backStackEntry.arguments?.getString("deviceId")
                val deviceId = arg?.takeIf { it.isNotBlank() }?.let(::DeviceId) ?: TOPDON_TC001_DEVICE_ID
                ThermalPreviewRoute(
                    deviceId = deviceId,
                    onNavigateUp = { navController.navigateUp() },
                    onNavigateToSettings = {
                        navController.navigate(Screen.Settings.route)
                    }
                )
            }
        }
    }
}
