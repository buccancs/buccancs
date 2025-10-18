package com.topdon.tc001.ui.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.topdon.tc001.BuildConfig
import com.topdon.tc001.ui.components.NavigationTransitions
import com.topdon.tc001.ui.screens.*

@Composable
fun AppNavHost(
    startDestination: String = AppDestination.Splash.route,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { NavigationTransitions.enterTransition() },
        exitTransition = { NavigationTransitions.exitTransition() },
        popEnterTransition = { NavigationTransitions.popEnterTransition() },
        popExitTransition = { NavigationTransitions.popExitTransition() }
    ) {
        // Splash Screen
        composable(
            AppDestination.Splash.route
        ) {
            // SplashScreen is in SplashActivity, navigates automatically
        }

        // Clause/Terms Screen
        composable(
            AppDestination.Clause.route
        ) {
            ClauseScreen(
                onAgree = {
                    navController.navigate(
                        AppDestination.Main.route
                    ) {
                        popUpTo(
                            AppDestination.Clause.route
                        ) {
                            inclusive =
                                true
                        }
                    }
                },
                onDisagree = {
                    // Exit app
                },
                onUserAgreementClick = {
                    navController.navigate(
                        AppDestination.Policy.createRoute(
                            "user_agreement"
                        )
                    )
                },
                onPrivacyPolicyClick = {
                    navController.navigate(
                        AppDestination.Policy.createRoute(
                            "privacy"
                        )
                    )
                },
                onTermsOfServiceClick = {
                    navController.navigate(
                        AppDestination.Policy.createRoute(
                            "terms"
                        )
                    )
                }
            )
        }

        // Policy Screen
        composable(
            route = AppDestination.Policy.route,
            arguments = listOf(
                navArgument(
                    "type"
                ) {
                    type =
                        NavType.StringType
                })
        ) { backStackEntry ->
            val policyTypeString =
                backStackEntry.arguments?.getString(
                    "type"
                )
                    ?: "user_agreement"
            val policyType =
                when (policyTypeString) {
                    "privacy" -> PolicyType.PRIVACY_POLICY
                    "terms" -> PolicyType.TERMS_OF_SERVICE
                    else -> PolicyType.USER_AGREEMENT
                }

            PolicyScreen(
                policyType = policyType,
                policyContent = "Policy content would be loaded here...",
                isLoading = false,
                onNavigateUp = { navController.navigateUp() }
            )
        }

        // Main Container (with bottom nav)
        composable(
            AppDestination.Main.route
        ) {
            // This is handled by MainActivityCompose
        }

        // Device Type Selection
        composable(
            AppDestination.DeviceType.route
        ) {
            DeviceTypeScreen(
                onDeviceTypeSelected = { deviceType ->
                    // Handle device type selection and navigate
                    navController.navigateUp()
                },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        // Version Screen
        composable(
            AppDestination.Version.route
        ) {
            VersionScreen(
                versionInfo = VersionInfo(
                    appName = "TopInfrared",
                    versionName = BuildConfig.VERSION_NAME,
                    versionCode = BuildConfig.VERSION_CODE,
                    buildDate = BuildConfig.VERSION_DATE,
                    flavor = BuildConfig.FLAVOR,
                    sdkVersion = Build.VERSION.SDK_INT,
                    deviceModel = "${Build.MANUFACTURER} ${Build.MODEL}",
                    androidVersion = Build.VERSION.RELEASE
                ),
                onNavigateUp = { navController.navigateUp() }
            )
        }

        // More Help Screen
        composable(
            AppDestination.MoreHelp.route
        ) {
            val helpTopics =
                listOf(
                    HelpTopic(
                        id = "1",
                        title = "How to connect device?",
                        content = "Connect your thermal camera via USB or WiFi. Make sure USB debugging is enabled if using USB connection."
                    ),
                    HelpTopic(
                        id = "2",
                        title = "How to capture images?",
                        content = "Once connected, tap the capture button on the main screen. Images are saved to your gallery automatically."
                    ),
                    HelpTopic(
                        id = "3",
                        title = "How to adjust settings?",
                        content = "Go to Settings > More Settings to adjust camera parameters, storage options, and display preferences."
                    ),
                    HelpTopic(
                        id = "4",
                        title = "Battery optimization",
                        content = "Disable battery optimization for this app to ensure stable connection. Go to Settings > Battery > App battery usage."
                    ),
                    HelpTopic(
                        id = "5",
                        title = "WiFi connection issues",
                        content = "Ensure both devices are on the same network. Check that the device IP address is correct in settings."
                    )
                )

            MoreHelpScreen(
                helpTopics = helpTopics,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}

// Navigation extensions for easier usage
fun NavHostController.navigateToPolicy(
    type: String
) {
    navigate(
        AppDestination.Policy.createRoute(
            type
        )
    )
}

fun NavHostController.navigateToVersion() {
    navigate(
        AppDestination.Version.route
    )
}

fun NavHostController.navigateToDeviceType() {
    navigate(
        AppDestination.DeviceType.route
    )
}

fun NavHostController.navigateToMoreHelp() {
    navigate(
        AppDestination.MoreHelp.route
    )
}

fun NavHostController.navigateToMain() {
    navigate(
        AppDestination.Main.route
    ) {
        popUpTo(
            AppDestination.Splash.route
        ) {
            inclusive =
                true
        }
    }
}
