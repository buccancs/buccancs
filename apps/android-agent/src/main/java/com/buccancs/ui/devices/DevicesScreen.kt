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
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Divider
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
import androidx.compose.material3.Switch
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
import com.buccancs.ui.InventoryDeviceUi
import com.buccancs.ui.InventoryUiModel
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
import java.util.Locale
import kotlin.math.roundToInt

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
        onToggleSimulation = viewModel::toggleSimulation,
        onRefreshInventory = viewModel::refreshInventory,
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
    onToggleSimulation: () -> Unit,
    onRefreshInventory: () -> Unit,
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
                        android.util.Log.d(
                            "DevicesScreen",
                            "Bluetooth device selected: ${device.name}"
                        )
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
                item {
                    SimulationControlsCard(
                        simulationEnabled = state.simulationEnabled,
                        onToggleSimulation = onToggleSimulation,
                        onRefreshInventory = onRefreshInventory
                    )
                }
                if (state.inventory.shimmer.isNotEmpty() ||
                    state.inventory.topdon.isNotEmpty() ||
                    state.inventory.rgb.isNotEmpty()
                ) {
                    item {
                        InventorySummaryCard(
                            inventory = state.inventory
                        )
                    }
                }
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
                                onOpenRgbCamera = if (device.rgbCamera != null) {
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
private fun SimulationControlsCard(
    simulationEnabled: Boolean,
    onToggleSimulation: () -> Unit,
    onRefreshInventory: () -> Unit
) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.SmallMedium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Simulation mode",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = if (simulationEnabled) "Virtual devices are enabled" else "Hardware devices only",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Switch(
                checked = simulationEnabled,
                onCheckedChange = {
                    if (it != simulationEnabled) {
                        onToggleSimulation()
                    }
                }
            )
        }
        OutlinedButton(
            onClick = onRefreshInventory,
            modifier = Modifier.defaultMinSize(
                minHeight = Dimensions.TouchTargetMinimum
            )
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
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
                text = "Refresh Inventory"
            )
        }
    }
}

@Composable
private fun InventorySummaryCard(
    inventory: InventoryUiModel
) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.SmallMedium
    ) {
        Text(
            text = "Configured Hardware",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        if (inventory.shimmer.isEmpty() &&
            inventory.topdon.isEmpty() &&
            inventory.rgb.isEmpty()
        ) {
            Text(
                text = "No configured devices found. Refresh the inventory or connect hardware.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            if (inventory.shimmer.isNotEmpty()) {
                InventorySection(
                    title = "Shimmer devices",
                    entries = inventory.shimmer
                )
            }
            if (inventory.topdon.isNotEmpty()) {
                InventorySection(
                    title = "Topdon cameras",
                    entries = inventory.topdon
                )
            }
            if (inventory.rgb.isNotEmpty()) {
                InventorySection(
                    title = "RGB cameras",
                    entries = inventory.rgb
                )
            }
        }
    }
}

@Composable
private fun InventorySection(
    title: String,
    entries: List<InventoryDeviceUi>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.Small)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold
        )
        entries.forEachIndexed { index, entry ->
            InventoryEntry(
                device = entry
            )
            if (index < entries.lastIndex) {
                Divider(
                    modifier = Modifier.padding(
                        vertical = Spacing.ExtraSmall
                    )
                )
            }
        }
    }
}

@Composable
private fun InventoryEntry(
    device: InventoryDeviceUi
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = device.label,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = device.detail,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        InventoryStatusBadges(
            connected = device.connected,
            isActive = device.isActive
        )
    }
}

@Composable
private fun InventoryStatusBadges(
    connected: Boolean,
    isActive: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            Spacing.ExtraSmall
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatusBadge(
            text = if (connected) "Connected" else "Configured",
            containerColor = if (connected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },
            contentColor = if (connected) {
                MaterialTheme.colorScheme.onPrimaryContainer
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )
        if (isActive) {
            StatusBadge(
                text = "Active",
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
private fun StatusBadge(
    text: String,
    containerColor: androidx.compose.ui.graphics.Color,
    contentColor: androidx.compose.ui.graphics.Color
) {
    Surface(
        color = containerColor,
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = contentColor,
            modifier = Modifier
                .padding(horizontal = Spacing.Small, vertical = Spacing.ExtraSmall)
        )
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

        if (device.capabilityLabels.isNotEmpty()) {
            Divider(
                modifier = Modifier.padding(vertical = Spacing.ExtraSmall)
            )
            Text(
                text = "Capabilities",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = device.capabilityLabels.joinToString(", "),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (device.streams.isNotEmpty()) {
            Divider(
                modifier = Modifier.padding(vertical = Spacing.ExtraSmall)
            )
            Text(
                text = "Active streams",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            device.streams.forEach { stream ->
                DeviceDetailRow(
                    label = stream.typeLabel,
                    value = stream.detail
                )
            }
        }

        device.rgbCamera?.let { rgb ->
            Divider(
                modifier = Modifier.padding(vertical = Spacing.ExtraSmall)
            )
            Text(
                text = "RGB camera",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            DeviceDetailRow(
                label = "Video",
                value = "${rgb.videoFps} fps • ${formatBitRate(rgb.videoBitRate)}"
            )
            DeviceDetailRow(
                label = "RAW capture",
                value = formatRawCapture(rgb.rawEnabled, rgb.rawIntervalMs)
            )
            DeviceDetailRow(
                label = "Exposure",
                value = formatExposure(rgb.exposureNs)
            )
            DeviceDetailRow(
                label = "ISO",
                value = formatIso(rgb.iso)
            )
            DeviceDetailRow(
                label = "Focus",
                value = formatFocus(rgb.focusDistanceMeters)
            )
            DeviceDetailRow(
                label = "White balance",
                value = formatAwb(rgb.awbMode)
            )
        }

        device.shimmer?.let { shimmer ->
            Divider(
                modifier = Modifier.padding(vertical = Spacing.ExtraSmall)
            )
            Text(
                text = "Shimmer settings",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            DeviceDetailRow(
                label = "Selected MAC",
                value = shimmer.selectedMac ?: "Not paired"
            )
            DeviceDetailRow(
                label = "GSR range",
                value = shimmer.gsrRangeLabels[shimmer.gsrRangeIndex]
            )
            DeviceDetailRow(
                label = "Sample rate",
                value = formatSampleRate(shimmer.sampleRateHz)
            )
        }
    }
}

@Composable
private fun DeviceDetailRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(end = Spacing.Small)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

private fun formatBitRate(
    bitrate: String
): String {
    val numeric =
        bitrate.toLongOrNull()
            ?: return bitrate
    val mbps =
        numeric / 1_000_000.0
    return String.format(
        Locale.US,
        "%.1f Mbps",
        mbps
    )
}

private fun formatExposure(
    exposureNs: String
): String {
    val ns =
        exposureNs.toLongOrNull()
            ?: return "Auto"
    val ms =
        ns / 1_000_000.0
    if (ms <= 0.0) return "Auto"
    val reciprocal =
        (1000.0 / ms).roundToInt()
            .takeIf { it in 1..8000 }
    val msLabel =
        if (ms >= 1) {
            String.format(
                Locale.US,
                "%.1f ms",
                ms
            )
        } else {
            String.format(
                Locale.US,
                "%.3f ms",
                ms
            )
        }
    return buildString {
        append(msLabel)
        reciprocal?.let {
            append(" (1/")
            append(it)
            append(" s)")
        }
    }
}

private fun formatIso(
    iso: String
): String =
    iso.toIntOrNull()
        ?.let { "ISO $it" }
        ?: "Auto"

private fun formatFocus(
    focusMeters: String
): String =
    focusMeters.toDoubleOrNull()
        ?.let {
            String.format(
                Locale.US,
                "%.2f m",
                it
            )
        }
        ?: "Auto"

private fun formatSampleRate(
    sampleRate: Double
): String =
    String.format(
        Locale.US,
        "%.0f Hz",
        sampleRate
    )

private fun formatRawCapture(
    enabled: Boolean,
    intervalMs: String
): String {
    if (!enabled) return "Disabled"
    val interval =
        intervalMs.toLongOrNull()
            ?: return "Enabled"
    return "Enabled (${interval} ms)"
}

private fun formatAwb(
    awbMode: String
): String {
    val normalized =
        awbMode.lowercase(
            Locale.US
        )
    return when (normalized) {
        "auto" -> "Auto"
        "daylight" -> "Daylight"
        "cloudy" -> "Cloudy"
        "shade" -> "Shade"
        "incandescent" -> "Incandescent"
        "fluorescent" -> "Fluorescent"
        "twilight" -> "Twilight"
        "warm" -> "Warm Fluorescent"
        "off" -> "Manual"
        else -> awbMode.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.US
            ) else it.toString()
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
