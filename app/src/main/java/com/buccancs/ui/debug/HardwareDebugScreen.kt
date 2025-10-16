package com.buccancs.ui.debug

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccancs.debug.UsbDeviceInfo
import com.buccancs.ui.components.AnimatedButton
import com.buccancs.ui.components.AnimatedOutlinedButton
import com.buccancs.ui.components.SectionCard
import com.buccancs.ui.theme.LayoutPadding
import com.buccancs.ui.theme.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HardwareDebugScreen(
    viewModel: HardwareDebugViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hardware Debugger") }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(LayoutPadding.Screen),
            verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
        ) {
            // Control buttons
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
                ) {
                    AnimatedButton(
                        onClick = { viewModel.startDebugSession() },
                        modifier = Modifier.weight(1f),
                        text = "Start Session"
                    )
                    AnimatedOutlinedButton(
                        onClick = { viewModel.saveDebugLog() },
                        modifier = Modifier.weight(1f),
                        text = "Save Log"
                    )
                }
            }

            // System info
            item {
                uiState.systemInfo?.let { info ->
                    SectionCard(
                        modifier = Modifier.fillMaxWidth(),
                        spacing = Spacing.Small
                    ) {
                        Text(
                            "System Information",
                            style = MaterialTheme.typography.titleMedium
                        )
                        InfoRow("Device", "${info.manufacturer} ${info.model}")
                        InfoRow("Android", "${info.androidVersion} (SDK ${info.sdkInt})")
                        InfoRow("Memory", "${info.totalMemoryMb} MB")
                        InfoRow("CPU", info.cpuAbi)
                    }
                }
            }

            // USB devices
            item {
                SectionCard(
                    modifier = Modifier.fillMaxWidth(),
                    spacing = Spacing.Small
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)
                    ) {
                        Icon(Icons.Default.Usb, contentDescription = null)
                        Text(
                            "USB Devices (${uiState.usbDevices.size})",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    if (uiState.isScanning) {
                        CircularProgressIndicator()
                    } else if (uiState.usbDevices.isEmpty()) {
                        Text(
                            "No USB devices detected",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            items(uiState.usbDevices) { device ->
                UsbDeviceCard(device)
            }

            // Bluetooth info
            item {
                uiState.bluetoothInfo?.let { info ->
                    SectionCard(
                        modifier = Modifier.fillMaxWidth(),
                        spacing = Spacing.Small
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)
                        ) {
                            Icon(Icons.Default.Bluetooth, contentDescription = null)
                            Text(
                                "Bluetooth",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        InfoRow("Available", info.isAvailable.toString())
                        InfoRow("Enabled", info.isEnabled.toString())
                        info.adapterName?.let { InfoRow("Adapter", it) }
                        InfoRow("Bonded Devices", info.bondedDevices.size.toString())
                    }

                    info.bondedDevices.forEach { device ->
                        SectionCard(
                            modifier = Modifier.fillMaxWidth(),
                            spacing = Spacing.ExtraSmall
                        ) {
                            Text(
                                device.name ?: "Unknown Device",
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                device.address,
                                style = MaterialTheme.typography.bodyMedium,
                                fontFamily = FontFamily.Monospace
                            )
                            InfoRow("Bond State", device.bondState.toString())
                            InfoRow("Type", device.type.toString())

                            if (device.name?.contains("Shimmer", ignoreCase = true) == true) {
                                Text(
                                    "SHIMMER DEVICE",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }

            // Log file info
            item {
                uiState.logFilePath?.let { path ->
                    SectionCard(
                        modifier = Modifier.fillMaxWidth(),
                        spacing = Spacing.Small
                    ) {
                        Text(
                            "Debug Log",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            path,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun UsbDeviceCard(device: UsbDeviceInfo) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Text(
            device.productName ?: device.deviceName,
            style = MaterialTheme.typography.titleSmall
        )

        InfoRow("Vendor ID", "0x${device.vendorId.toString(16)}")
        InfoRow("Product ID", "0x${device.productId.toString(16)}")
        device.manufacturerName?.let { InfoRow("Manufacturer", it) }
        device.serialNumber?.let { InfoRow("Serial", it) }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)
        ) {
            Icon(
                if (device.hasPermission) Icons.Default.Check else Icons.Default.Close,
                contentDescription = null,
                tint = if (device.hasPermission) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.error
                }
            )
            Text(
                if (device.hasPermission) "Permission Granted" else "Permission Denied",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (device.vendorId == 0x3426) {
            Text(
                "TOPDON TC001 THERMAL CAMERA",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.Hairline)
    ) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(0.4f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = FontFamily.Monospace
        )
    }
}
