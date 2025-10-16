package com.buccancs.ui.debug

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccancs.debug.UsbDeviceInfo

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
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Control buttons
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { viewModel.startDebugSession() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = null)
                        Text(" Start Session")
                    }
                    OutlinedButton(
                        onClick = { viewModel.saveDebugLog() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.Save, contentDescription = null)
                        Text(" Save Log")
                    }
                }
            }

            // System info
            item {
                uiState.systemInfo?.let { info ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "System Information",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            InfoRow("Device", "${info.manufacturer} ${info.model}")
                            InfoRow("Android", "${info.androidVersion} (SDK ${info.sdkInt})")
                            InfoRow("Memory", "${info.totalMemoryMb} MB")
                            InfoRow("CPU", info.cpuAbi)
                        }
                    }
                }
            }

            // USB devices
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(Icons.Default.Usb, contentDescription = null)
                            Text(
                                "USB Devices (${uiState.usbDevices.size})",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))

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
            }

            items(uiState.usbDevices) { device ->
                UsbDeviceCard(device)
            }

            // Bluetooth info
            item {
                uiState.bluetoothInfo?.let { info ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(Icons.Default.Bluetooth, contentDescription = null)
                                Text(
                                    "Bluetooth",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            InfoRow("Available", info.isAvailable.toString())
                            InfoRow("Enabled", info.isEnabled.toString())
                            info.adapterName?.let { InfoRow("Adapter", it) }
                            InfoRow("Bonded Devices", info.bondedDevices.size.toString())
                        }
                    }

                    info.bondedDevices.forEach { device ->
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
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
            }

            // Log file info
            item {
                uiState.logFilePath?.let { path ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "Debug Log",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
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
}

@Composable
private fun UsbDeviceCard(
    device: UsbDeviceInfo,
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    )
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                device.productName ?: device.deviceName,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(8.dp))

            InfoRow("Vendor ID", "0x${device.vendorId.toString(16)}")
            InfoRow("Product ID", "0x${device.productId.toString(16)}")
            device.manufacturerName?.let { InfoRow("Manufacturer", it) }
            device.serialNumber?.let { InfoRow("Serial", it) }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
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
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "TOPDON TC001 THERMAL CAMERA",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
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
