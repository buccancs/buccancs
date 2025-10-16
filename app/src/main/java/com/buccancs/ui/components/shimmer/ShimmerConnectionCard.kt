package com.buccancs.ui.components.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.BluetoothConnected
import androidx.compose.material.icons.filled.BluetoothDisabled
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.buccancs.ui.components.SectionCard
import com.buccancs.ui.theme.Spacing

@Composable
fun ShimmerConnectionCard(
    isConnected: Boolean,
    isConnecting: Boolean,
    deviceName: String?,
    deviceAddress: String?,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    modifier: Modifier = Modifier
) {
    SectionCard(
        modifier = modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.Small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = when {
                    isConnected -> Icons.Default.BluetoothConnected
                    isConnecting -> Icons.Default.Bluetooth
                    else -> Icons.Default.BluetoothDisabled
                },
                contentDescription = null,
                tint = when {
                    isConnected -> MaterialTheme.colorScheme.primary
                    isConnecting -> MaterialTheme.colorScheme.secondary
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                }
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = when {
                        isConnecting -> "Connecting..."
                        isConnected -> deviceName ?: "Shimmer Device"
                        else -> "Not Connected"
                    },
                    style = MaterialTheme.typography.titleMedium
                )

                if (isConnected && deviceAddress != null) {
                    Text(
                        text = deviceAddress,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            if (isConnected) {
                OutlinedButton(
                    onClick = onDisconnect,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Disconnect")
                }
            } else {
                Button(
                    onClick = onConnect,
                    enabled = !isConnecting,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (isConnecting) "Connecting..." else "Connect")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShimmerConnectionCardPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            ShimmerConnectionCard(
                isConnected = false,
                isConnecting = false,
                deviceName = null,
                deviceAddress = null,
                onConnect = {},
                onDisconnect = {}
            )

            ShimmerConnectionCard(
                isConnected = false,
                isConnecting = true,
                deviceName = null,
                deviceAddress = null,
                onConnect = {},
                onDisconnect = {}
            )

            ShimmerConnectionCard(
                isConnected = true,
                isConnecting = false,
                deviceName = "Shimmer3 GSR",
                deviceAddress = "E8:EB:1B:97:67:FC",
                onConnect = {},
                onDisconnect = {}
            )
        }
    }
}
