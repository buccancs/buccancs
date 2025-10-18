package com.buccancs.ui.devices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.domain.model.DeviceId
import com.buccancs.ui.DeviceUiModel
import com.buccancs.ui.MainUiState
import com.buccancs.ui.MainViewModel
import com.buccancs.ui.calibration.CalibrationActions
import com.buccancs.ui.calibration.CalibrationPanel
import com.buccancs.ui.calibration.CalibrationUiState
import com.buccancs.ui.calibration.CalibrationViewModel
import com.buccancs.ui.components.SectionCard
import com.buccancs.ui.theme.Dimensions
import com.buccancs.ui.theme.LayoutPadding
import com.buccancs.ui.theme.Spacing

@Composable
fun DevicesRoute(
    onOpenTopdon: (DeviceId) -> Unit,
    onOpenShimmer: (DeviceId) -> Unit,
    onOpenRgbCamera: (DeviceId) -> Unit = {},
    viewModel: MainViewModel = hiltViewModel(),
    calibrationViewModel: CalibrationViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val calibrationState by calibrationViewModel.uiState.collectAsStateWithLifecycle()

    val calibrationActions =
        CalibrationActions(
            onRowsChanged = calibrationViewModel::updatePatternRows,
            onColsChanged = calibrationViewModel::updatePatternCols,
            onSquareSizeChanged = calibrationViewModel::updateSquareSizeMm,
            onRequiredPairsChanged = calibrationViewModel::updateRequiredPairs,
            onApplySettings = calibrationViewModel::applyPatternSettings,
            onStartSession = calibrationViewModel::startSession,
            onCapturePair = calibrationViewModel::capture,
            onComputeCalibration = calibrationViewModel::computeCalibration,
            onLoadCachedResult = calibrationViewModel::loadCachedResult,
            onClearSession = calibrationViewModel::clearSession,
            onRemoveCapture = calibrationViewModel::removeCapture
        )

    DevicesScreen(
        state = state,
        calibrationState = calibrationState,
        calibrationActions = calibrationActions,
        onConnectDevice = viewModel::connectDevice,
        onDisconnectDevice = viewModel::disconnectDevice,
        onOpenTopdon = onOpenTopdon,
        onOpenShimmer = onOpenShimmer,
        onOpenRgbCamera = onOpenRgbCamera
    )
}

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun DevicesScreen(
    state: MainUiState,
    calibrationState: CalibrationUiState,
    calibrationActions: CalibrationActions,
    onConnectDevice: (DeviceId) -> Unit,
    onDisconnectDevice: (DeviceId) -> Unit,
    onOpenTopdon: (DeviceId) -> Unit,
    onOpenShimmer: (DeviceId) -> Unit,
    onOpenRgbCamera: (DeviceId) -> Unit = {}
) {
    var selectedTab by remember {
        mutableIntStateOf(
            0
        )
    }

    // Device scanner dialog state
    var showScannerDialog by remember { mutableStateOf(false) }
    val deviceScanner: com.buccancs.hardware.DeviceScannerService = 
        androidx.hilt.navigation.compose.hiltViewModel<com.buccancs.ui.navigation.DeviceScannerViewModel>().deviceScanner
    val scanState by deviceScanner.scanState.collectAsStateWithLifecycle()

    // Show device scanner dialog
    if (showScannerDialog) {
        com.buccancs.ui.components.scanner.DeviceScannerDialog(
            scanState = scanState,
            onDismiss = { showScannerDialog = false },
            onStartScan = { deviceScanner.startManualScan() },
            onRequestUsbPermission = { device -> deviceScanner.requestUsbPermission(device.device) },
            onDeviceSelected = { device ->
                showScannerDialog = false
                // Handle device selection based on type
                when (device) {
                    is com.buccancs.hardware.ScannedDevice.Usb -> {
                        // USB device selected, could trigger connection flow
                        android.util.Log.d("DevicesScreen", "USB device selected: ${device.name}")
                    }
                    is com.buccancs.hardware.ScannedDevice.Bluetooth -> {
                        // Bluetooth device selected
                        android.util.Log.d("DevicesScreen", "Bluetooth device selected: ${device.name}")
                    }
                }
            }
        )
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Devices"
                    )
                },
                actions = {
                    // Scan button
                    IconButton(onClick = { showScannerDialog = true }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Scan for devices"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        },
        contentWindowInsets = WindowInsets(
            0,
            0,
            0,
            0
        )
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    paddingValues
                )
        ) {
            PrimaryTabRow(
                selectedTabIndex = selectedTab
            ) {
                deviceTabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = {
                            selectedTab =
                                index
                        },
                        text = {
                            Text(
                                tab.title
                            )
                        },
                        icon = {
                            Icon(
                                tab.icon,
                                contentDescription = tab.title
                            )
                        }
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        LayoutPadding.ScreenCompact
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                when (selectedTab) {
                    0 -> { // All Devices
                        items(
                            state.devices,
                            key = { it.id.value }) { device ->
                            DeviceCard(
                                device = device,
                                onConnect = {
                                    onConnectDevice(
                                        device.id
                                    )
                                },
                                onDisconnect = {
                                    onDisconnectDevice(
                                        device.id
                                    )
                                },
                                onOpenTopdon = if (device.supportsTopdon) {
                                    {
                                        onOpenTopdon(
                                            device.id
                                        )
                                    }
                                } else null,
                                onOpenShimmer = if (device.shimmer != null) {
                                    {
                                        onOpenShimmer(
                                            device.id
                                        )
                                    }
                                } else null,
                                onOpenRgbCamera = if (device.typeLabel.contains("RGB", ignoreCase = true)) {
                                    {
                                        onOpenRgbCamera(
                                            device.id
                                        )
                                    }
                                } else null
                            )
                        }
                    }

                    1 -> { // Shimmer
                        items(
                            state.devices.filter {
                                it.typeLabel.contains(
                                    "Shimmer",
                                    ignoreCase = true
                                )
                            },
                            key = { it.id.value }) { device ->
                            DeviceCard(
                                device = device,
                                onConnect = {
                                    onConnectDevice(
                                        device.id
                                    )
                                },
                                onDisconnect = {
                                    onDisconnectDevice(
                                        device.id
                                    )
                                },
                                onOpenTopdon = null,
                                onOpenShimmer = if (device.shimmer != null) {
                                    {
                                        onOpenShimmer(
                                            device.id
                                        )
                                    }
                                } else null
                            )
                        }
                    }

                    2 -> { // TOPDON
                        items(
                            state.devices.filter { it.supportsTopdon },
                            key = { it.id.value }) { device ->
                            DeviceCard(
                                device = device,
                                onConnect = {
                                    onConnectDevice(
                                        device.id
                                    )
                                },
                                onDisconnect = {
                                    onDisconnectDevice(
                                        device.id
                                    )
                                },
                                onOpenTopdon = {
                                    onOpenTopdon(
                                        device.id
                                    )
                                },
                                onOpenShimmer = null
                            )
                        }
                    }

                    3 -> { // Calibration
                        item {
                            CalibrationPanel(
                                state = calibrationState,
                                actions = calibrationActions
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DeviceCard(
    device: DeviceUiModel,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    onOpenTopdon: (() -> Unit)?,
    onOpenShimmer: (() -> Unit)?,
    onOpenRgbCamera: (() -> Unit)? = null
) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(
                    1f
                )
            ) {
                Text(
                    text = device.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = device.typeLabel,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Surface(
                color = if (device.isConnected) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                },
                shape = MaterialTheme.shapes.small
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = Spacing.SmallMedium,
                        vertical = Spacing.Small
                    ),
                    horizontalArrangement = Arrangement.spacedBy(
                        Spacing.ExtraSmall
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = device.connectionStatus,
                        style = MaterialTheme.typography.labelMedium,
                        color = if (device.isConnected) {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                    Icon(
                        imageVector = if (device.isConnected) Icons.Default.Check else Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.size(
                            Dimensions.IconSizeSmall
                        ),
                        tint = if (device.isConnected) {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                Spacing.Small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!device.isConnected) {
                FilledTonalButton(
                    onClick = onConnect,
                    modifier = Modifier
                        .weight(
                            1f
                        )
                        .defaultMinSize(
                            minHeight = Dimensions.TouchTargetMinimum
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(
                            Dimensions.IconSizeSmall
                        )
                    )
                    Spacer(
                        modifier = Modifier.width(
                            Spacing.ExtraSmall
                        )
                    )
                    Text(
                        "Connect"
                    )
                }
            } else {
                OutlinedButton(
                    onClick = onDisconnect,
                    modifier = Modifier
                        .weight(
                            1f
                        )
                        .defaultMinSize(
                            minHeight = Dimensions.TouchTargetMinimum
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.size(
                            Dimensions.IconSizeSmall
                        )
                    )
                    Spacer(
                        modifier = Modifier.width(
                            Spacing.ExtraSmall
                        )
                    )
                    Text(
                        "Disconnect"
                    )
                }
            }

            onOpenTopdon?.let {
                OutlinedButton(
                    onClick = it,
                    enabled = device.isConnected,
                    modifier = Modifier
                        .weight(
                            1f
                        )
                        .defaultMinSize(
                            minHeight = Dimensions.TouchTargetMinimum
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        modifier = Modifier.size(
                            Dimensions.IconSizeSmall
                        )
                    )
                    Spacer(
                        modifier = Modifier.width(
                            Spacing.ExtraSmall
                        )
                    )
                    Text(
                        "Configure"
                    )
                }
            }

            onOpenShimmer?.let {
                OutlinedButton(
                    onClick = it,
                    enabled = device.isConnected,
                    modifier = Modifier
                        .weight(
                            1f
                        )
                        .defaultMinSize(
                            minHeight = Dimensions.TouchTargetMinimum
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        modifier = Modifier.size(
                            Dimensions.IconSizeSmall
                        )
                    )
                    Spacer(
                        modifier = Modifier.width(
                            Spacing.ExtraSmall
                        )
                    )
                    Text(
                        "Configure"
                    )
                }
            }

            onOpenRgbCamera?.let {
                OutlinedButton(
                    onClick = it,
                    enabled = device.isConnected,
                    modifier = Modifier
                        .weight(
                            1f
                        )
                        .defaultMinSize(
                            minHeight = Dimensions.TouchTargetMinimum
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Camera,
                        contentDescription = null,
                        modifier = Modifier.size(
                            Dimensions.IconSizeSmall
                        )
                    )
                    Spacer(
                        modifier = Modifier.width(
                            Spacing.ExtraSmall
                        )
                    )
                    Text(
                        "View"
                    )
                }
            }
        }
    }
}

private data class DeviceTab(
    val title: String,
    val icon: ImageVector
)

private val deviceTabs =
    listOf(
        DeviceTab(
            "All",
            Icons.Default.Bluetooth
        ),
        DeviceTab(
            "Shimmer",
            Icons.Default.Bluetooth
        ),
        DeviceTab(
            "TOPDON",
            Icons.Default.Thermostat
        ),
        DeviceTab(
            "Calibration",
            Icons.Default.Camera
        )
    )
