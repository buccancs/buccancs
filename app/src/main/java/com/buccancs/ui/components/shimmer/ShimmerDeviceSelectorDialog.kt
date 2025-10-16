package com.buccancs.ui.components.shimmer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.buccancs.ui.theme.Spacing

data class ShimmerDevice(
    val name: String,
    val address: String,
    val isPaired: Boolean
)

@Composable
fun ShimmerDeviceSelectorDialog(
    pairedDevices: List<ShimmerDevice>,
    availableDevices: List<ShimmerDevice>,
    isScanning: Boolean,
    onDeviceSelected: (ShimmerDevice) -> Unit,
    onScanForDevices: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = modifier,
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.Medium)
            ) {
                Text(
                    text = "Select Shimmer Device",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = Spacing.Medium)
                )

                if (pairedDevices.isNotEmpty()) {
                    Text(
                        text = "Paired Devices",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = Spacing.Small)
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, fill = false)
                    ) {
                        items(pairedDevices) { device ->
                            DeviceListItem(
                                device = device,
                                onClick = { onDeviceSelected(device) }
                            )
                            HorizontalDivider()
                        }
                    }

                    Spacer(modifier = Modifier.height(Spacing.Medium))
                }

                if (availableDevices.isNotEmpty()) {
                    Text(
                        text = "Available Devices",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = Spacing.Small)
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, fill = false)
                    ) {
                        items(availableDevices) { device ->
                            DeviceListItem(
                                device = device,
                                onClick = { onDeviceSelected(device) }
                            )
                            HorizontalDivider()
                        }
                    }

                    Spacer(modifier = Modifier.height(Spacing.Medium))
                }

                if (pairedDevices.isEmpty() && availableDevices.isEmpty() && !isScanning) {
                    Text(
                        text = "No devices found. Tap 'Scan' to search for devices.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(vertical = Spacing.Large)
                    )
                }

                if (isScanning) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Spacing.Medium),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator()
                        Text(
                            text = "Scanning...",
                            modifier = Modifier.padding(start = Spacing.Medium)
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
                ) {
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = onScanForDevices,
                        enabled = !isScanning,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(if (isScanning) "Scanning..." else "Scan")
                    }
                }
            }
        }
    }
}

@Composable
private fun DeviceListItem(
    device: ShimmerDevice,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(Spacing.Medium),
        horizontalArrangement = Arrangement.spacedBy(Spacing.Medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Bluetooth,
            contentDescription = null,
            tint = if (device.isPaired) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondary
            }
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = device.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = device.address,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
