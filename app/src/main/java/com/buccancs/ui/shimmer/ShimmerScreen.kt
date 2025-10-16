package com.buccancs.ui.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccancs.ui.components.shimmer.*
import com.buccancs.ui.theme.LayoutPadding
import com.buccancs.ui.theme.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShimmerScreen(
    onNavigateBack: () -> Unit,
    viewModel: ShimmerScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var showDeviceSelector by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.clearError()
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Shimmer Device") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(LayoutPadding.ScreenCompact)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
        ) {
            ShimmerConnectionCard(
                isConnected = uiState.isConnected,
                isConnecting = uiState.isConnecting,
                deviceName = uiState.deviceName,
                deviceAddress = uiState.deviceAddress,
                onConnect = viewModel::connectDevice,
                onDisconnect = viewModel::disconnectDevice
            )

            if (uiState.isConnected) {
                ShimmerStreamingCard(
                    isStreaming = uiState.isStreaming,
                    isConnected = uiState.isConnected,
                    onStartStreaming = viewModel::startStreaming,
                    onStopStreaming = viewModel::stopStreaming
                )

                ShimmerConfigCard(
                    gsrRange = uiState.gsrRangeLabel,
                    gsrRangeOptions = uiState.gsrRangeOptions,
                    sampleRate = uiState.sampleRate,
                    sampleRateOptions = uiState.sampleRateOptions,
                    onGsrRangeChange = viewModel::updateGsrRange,
                    onSampleRateChange = viewModel::updateSampleRate,
                    enabled = !uiState.isStreaming
                )

                ShimmerDataCard(
                    timestamp = uiState.timestamp,
                    accelX = uiState.accelX,
                    accelY = uiState.accelY,
                    accelZ = uiState.accelZ,
                    gsrData = uiState.gsrData
                )
            }
        }

        if (showDeviceSelector) {
            ShimmerDeviceSelectorDialog(
                pairedDevices = emptyList(),
                availableDevices = emptyList(),
                isScanning = uiState.isScanning,
                onDeviceSelected = { device ->
                    showDeviceSelector = false
                    // Device selection handled through settings
                },
                onScanForDevices = viewModel::scanForDevices,
                onDismiss = { showDeviceSelector = false }
            )
        }
    }
}
