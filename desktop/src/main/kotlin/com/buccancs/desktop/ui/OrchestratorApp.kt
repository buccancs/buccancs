package com.buccancs.desktop.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.buccancs.desktop.ui.navigation.BuccancsNavigationRail
import com.buccancs.desktop.ui.navigation.Screen
import com.buccancs.desktop.ui.screens.*
import com.buccancs.desktop.ui.theme.BuccancsTheme
import com.buccancs.desktop.viewmodel.AppViewModel

/**
 * Main orchestrator application with navigation
 * Integrates all screens with a navigation rail
 */
@Composable
fun OrchestratorApp(viewModel: AppViewModel) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Dashboard) }
    
    BuccancsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                // Navigation rail
                BuccancsNavigationRail(
                    currentScreen = currentScreen,
                    onNavigate = { currentScreen = it }
                )
                
                // Main content
                Box(modifier = Modifier.fillMaxSize()) {
                    when (currentScreen) {
                        Screen.Dashboard -> DesktopApp(viewModel)
                        Screen.Settings -> SettingsScreen()
                        Screen.SensorStatus -> SensorStatusScreen()
                        Screen.Synchronisation -> SynchronisationScreen()
                        Screen.Calibration -> CalibrationScreen()
                        Screen.Preview -> PreviewScreen()
                        Screen.VideoPlayback -> VideoPlaybackScreen()
                        Screen.FileExplorer -> FileExplorerScreen()
                        Screen.Users -> UsersScreen()
                    }
                }
            }
        }
    }
}
