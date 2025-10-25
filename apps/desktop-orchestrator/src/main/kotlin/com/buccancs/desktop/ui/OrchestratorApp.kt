package com.buccancs.desktop.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.buccancs.desktop.ui.navigation.BuccancsNavigationRail
import com.buccancs.desktop.ui.navigation.Screen
import com.buccancs.desktop.ui.screens.CalibrationScreen
import com.buccancs.desktop.ui.screens.FileExplorerScreen
import com.buccancs.desktop.ui.screens.PreviewScreen
import com.buccancs.desktop.ui.screens.SensorStatusScreen
import com.buccancs.desktop.ui.screens.SettingsScreen
import com.buccancs.desktop.ui.screens.SynchronisationScreen
import com.buccancs.desktop.ui.screens.UsersScreen
import com.buccancs.desktop.ui.screens.VideoPlaybackScreen
import com.buccancs.desktop.ui.theme.BuccancsTheme
import com.buccancs.desktop.viewmodel.AppViewModel

/**
 * Main orchestrator application with navigation
 * Integrates all screens with a navigation rail
 */
@Composable
fun OrchestratorApp(
    viewModel: AppViewModel
) {
    var currentScreen by remember {
        mutableStateOf<Screen>(
            Screen.Dashboard
        )
    }

    BuccancsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                // Navigation rail
                BuccancsNavigationRail(
                    currentScreen = currentScreen,
                    onNavigate = {
                        currentScreen =
                            it
                    }
                )

                // Main content
                Box(modifier = Modifier.fillMaxSize()) {
                    when (currentScreen) {
                        Screen.Dashboard -> DesktopApp(
                            viewModel
                        )

                        Screen.Settings -> SettingsScreen()
                        Screen.SensorStatus -> SensorStatusScreen()
                        Screen.Synchronisation -> SynchronisationScreen()
                        Screen.Calibration -> CalibrationScreen()
                        Screen.Preview -> PreviewScreen(
                            viewModel = viewModel
                        )
                        Screen.VideoPlayback -> VideoPlaybackScreen()
                        Screen.FileExplorer -> FileExplorerScreen()
                        Screen.Users -> UsersScreen()
                    }
                }
            }
        }
    }
}
