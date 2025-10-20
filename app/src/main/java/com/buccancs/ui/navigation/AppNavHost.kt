package com.buccancs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.TOPDON_TC001_DEVICE_ID
import com.buccancs.hardware.DeviceScannerService
import com.buccancs.ui.base.UsbDeviceAttachmentEvent
import com.buccancs.ui.camera.RgbCameraRoute
import com.buccancs.ui.camera.RgbCameraSettingsRoute
import com.buccancs.ui.components.scanner.TopdonDevicePermissionDialog
import com.buccancs.ui.devices.DevicesRoute
import com.buccancs.ui.library.SessionDetailRoute
import com.buccancs.ui.library.SessionLibraryRoute
import com.buccancs.ui.session.LiveSessionRoute
import com.buccancs.ui.settings.SettingsRoute
import com.buccancs.ui.shimmer.ShimmerScreen
import com.buccancs.ui.theme.MotionTransitions
import com.buccancs.ui.topdon.TopdonRoute
import com.buccancs.ui.topdon.detail.ImageDetailRoute
import com.buccancs.ui.topdon.gallery.TopdonGalleryRoute
import com.buccancs.ui.topdon.guide.ConnectionGuideRoute
import com.buccancs.ui.topdon.settings.TopdonSettingsRoute
import com.buccancs.ui.topdon.thermal.ThermalPreviewRoute
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    usbAttachmentEvents: SharedFlow<UsbDeviceAttachmentEvent>? = null
) {
    // State for Topdon device permission dialog
    var showTopdonPermissionDialog by remember { mutableStateOf(false) }
    var pendingTopdonDevice by remember { mutableStateOf<android.hardware.usb.UsbDevice?>(null) }

    // Get device scanner service via Hilt
    val deviceScanner: DeviceScannerService = hiltViewModel<DeviceScannerViewModel>().deviceScanner

    // Listen for USB attachment events
    LaunchedEffect(usbAttachmentEvents) {
        usbAttachmentEvents?.collect { event ->
            pendingTopdonDevice = event.device
            showTopdonPermissionDialog = true
        }
    }

    // Show Topdon permission dialog
    if (showTopdonPermissionDialog && pendingTopdonDevice != null) {
        val device = pendingTopdonDevice!!
        TopdonDevicePermissionDialog(
            deviceName = device.productName ?: "Topdon Thermal Camera",
            vendorId = device.vendorId,
            productId = device.productId,
            onConfirm = {
                deviceScanner.requestUsbPermission(device)
                showTopdonPermissionDialog = false
                pendingTopdonDevice = null
            },
            onDismiss = {
                showTopdonPermissionDialog = false
                pendingTopdonDevice = null
            }
        )
    }
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
                        navController.navigate(
                            Screen.TopdonDevice.createRoute(
                                deviceId
                            )
                        )
                    },
                    onOpenShimmer = { deviceId ->
                        navController.navigate(
                            Screen.Shimmer.route
                        )
                    },
                    onOpenRgbCamera = { deviceId ->
                        navController.navigate(
                            Screen.RgbCamera.createRoute(
                                deviceId
                            )
                        )
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
                        navController.navigate(
                            Screen.SessionDetail.createRoute(
                                sessionId
                            )
                        )
                    }
                )
            }

            composable(
                route = Screen.Settings.route,
                enterTransition = { MotionTransitions.fadeEnter() },
                exitTransition = { MotionTransitions.fadeExit() }
            ) {
                SettingsRoute(
                    onNavigateUp = { navController.navigateUp() })
            }

            composable(
                route = "session_detail/{sessionId}",
                arguments = listOf(
                    navArgument(
                        "sessionId"
                    ) {
                        type =
                            NavType.StringType
                    }),
                enterTransition = { MotionTransitions.slideInFromEnd() },
                exitTransition = { MotionTransitions.fadeExit() },
                popEnterTransition = { MotionTransitions.fadeEnter() },
                popExitTransition = { MotionTransitions.slideOutToStart() }
            ) {
                SessionDetailRoute(
                    onNavigateUp = { navController.navigateUp() })
            }

            composable(
                route = "topdon/{deviceId}",
                arguments = listOf(
                    navArgument(
                        "deviceId"
                    ) {
                        type =
                            NavType.StringType
                    }),
                enterTransition = { MotionTransitions.slideInFromEnd() },
                exitTransition = { MotionTransitions.fadeExit() },
                popEnterTransition = { MotionTransitions.fadeEnter() },
                popExitTransition = { MotionTransitions.slideOutToStart() }
            ) { backStackEntry ->
                val arg =
                    backStackEntry.arguments?.getString(
                        "deviceId"
                    )
                val deviceId =
                    arg?.takeIf { it.isNotBlank() }
                        ?.let(
                            ::DeviceId
                        )
                        ?: TOPDON_TC001_DEVICE_ID
                TopdonRoute(
                    deviceId = deviceId,
                    onNavigateUp = { navController.navigateUp() },
                    onNavigateToThermalFullScreen = {
                        navController.navigate(
                            Screen.TopdonThermalPreview.createRoute(
                                deviceId
                            )
                        )
                    },
                    onNavigateToGuide = {
                        navController.navigate(
                            Screen.TopdonConnectionGuide.route
                        )
                    },
                    onNavigateToSettings = {
                        navController.navigate(
                            Screen.TopdonSettings.route
                        )
                    },
                    onOpenGalleryDetail = { imageId ->
                        navController.navigate(
                            Screen.TopdonImageDetail.createRoute(
                                imageId
                            )
                        )
                    }
                )
            }

            composable(
                route = "topdon/{deviceId}/thermal",
                arguments = listOf(
                    navArgument(
                        "deviceId"
                    ) {
                        type =
                            NavType.StringType
                    }),
                enterTransition = { MotionTransitions.slideInFromEnd() },
                exitTransition = { MotionTransitions.fadeExit() },
                popEnterTransition = { MotionTransitions.fadeEnter() },
                popExitTransition = { MotionTransitions.slideOutToStart() }
            ) { backStackEntry ->
                val arg =
                    backStackEntry.arguments?.getString(
                        "deviceId"
                    )
                val deviceId =
                    arg?.takeIf { it.isNotBlank() }
                        ?.let(
                            ::DeviceId
                        )
                        ?: TOPDON_TC001_DEVICE_ID
                ThermalPreviewRoute(
                    deviceId = deviceId,
                    onNavigateUp = { navController.navigateUp() },
                    onNavigateToSettings = {
                        navController.navigate(
                            Screen.Settings.route
                        )
                    }
                )
            }

            composable(
                route = Screen.TopdonGallery.route,
                enterTransition = { MotionTransitions.slideInFromEnd() },
                exitTransition = { MotionTransitions.fadeExit() },
                popEnterTransition = { MotionTransitions.fadeEnter() },
                popExitTransition = { MotionTransitions.slideOutToStart() }
            ) {
                TopdonGalleryRoute(
                    onNavigateUp = { navController.navigateUp() },
                    onNavigateToDetail = { imageId ->
                        navController.navigate(
                            Screen.TopdonImageDetail.createRoute(
                                imageId
                            )
                        )
                    }
                )
            }

            composable(
                route = "topdon/image/{imageId}",
                arguments = listOf(
                    navArgument(
                        "imageId"
                    ) {
                        type =
                            NavType.StringType
                    }),
                enterTransition = { MotionTransitions.slideInFromEnd() },
                exitTransition = { MotionTransitions.fadeExit() },
                popEnterTransition = { MotionTransitions.fadeEnter() },
                popExitTransition = { MotionTransitions.slideOutToStart() }
            ) { backStackEntry ->
                val imageId =
                    backStackEntry.arguments?.getString(
                        "imageId"
                    )
                        ?: ""
                ImageDetailRoute(
                    imageId = imageId,
                    onNavigateUp = { navController.navigateUp() }
                )
            }

            composable(
                route = Screen.TopdonSettings.route,
                enterTransition = { MotionTransitions.slideInFromEnd() },
                exitTransition = { MotionTransitions.fadeExit() },
                popEnterTransition = { MotionTransitions.fadeEnter() },
                popExitTransition = { MotionTransitions.slideOutToStart() }
            ) {
                TopdonSettingsRoute(
                    onNavigateUp = { navController.navigateUp() }
                )
            }

            composable(
                route = Screen.TopdonConnectionGuide.route,
                enterTransition = { MotionTransitions.slideInFromEnd() },
                exitTransition = { MotionTransitions.fadeExit() },
                popEnterTransition = { MotionTransitions.fadeEnter() },
                popExitTransition = { MotionTransitions.slideOutToStart() }
            ) {
                ConnectionGuideRoute(
                    onNavigateUp = { navController.navigateUp() },
                    onConnectDevice = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = "shimmer",
                enterTransition = { MotionTransitions.slideInFromEnd() },
                exitTransition = { MotionTransitions.fadeExit() },
                popEnterTransition = { MotionTransitions.fadeEnter() },
                popExitTransition = { MotionTransitions.slideOutToStart() }
            ) {
                ShimmerScreen(
                    onNavigateBack = { navController.navigateUp() }
                )
            }

            composable(
                route = "camera/{deviceId}",
                arguments = listOf(
                    navArgument("deviceId") {
                        type = NavType.StringType
                    }
                ),
                enterTransition = { MotionTransitions.slideInFromEnd() },
                exitTransition = { MotionTransitions.fadeExit() },
                popEnterTransition = { MotionTransitions.fadeEnter() },
                popExitTransition = { MotionTransitions.slideOutToStart() }
            ) { backStackEntry ->
                val deviceId = backStackEntry.arguments?.getString("deviceId")
                    ?.let(::DeviceId) ?: DeviceId("android-rgb")
                RgbCameraRoute(
                    deviceId = deviceId,
                    onNavigateUp = { navController.navigateUp() },
                    onNavigateToSettings = {
                        navController.navigate(
                            Screen.RgbCameraSettings.createRoute(deviceId)
                        )
                    }
                )
            }

            composable(
                route = "camera/{deviceId}/settings",
                arguments = listOf(
                    navArgument("deviceId") {
                        type = NavType.StringType
                    }
                ),
                enterTransition = { MotionTransitions.slideInFromEnd() },
                exitTransition = { MotionTransitions.fadeExit() },
                popEnterTransition = { MotionTransitions.fadeEnter() },
                popExitTransition = { MotionTransitions.slideOutToStart() }
            ) { backStackEntry ->
                val deviceId = backStackEntry.arguments?.getString("deviceId")
                    ?.let(::DeviceId) ?: DeviceId("android-rgb")
                RgbCameraSettingsRoute(
                    deviceId = deviceId,
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}
