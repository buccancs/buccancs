package com.topdon.tc001.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.topdon.tc001.R

data class DeviceInfo(
    val name: String,
    val type: DeviceType,
    val isConnected: Boolean,
    val batteryLevel: Int? = null
)

enum class DeviceType {
    TC001_LINE,
    TS004,
    TC007
}

@Composable
fun MainScreen(
    devices: List<DeviceInfo>,
    onConnectDeviceClick: () -> Unit,
    onAddDeviceClick: () -> Unit,
    onDeviceClick: (DeviceType) -> Unit,
    onDeviceLongClick: (DeviceType) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (devices.isEmpty()) {
            NoDeviceContent(
                onConnectDeviceClick = onConnectDeviceClick,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            DeviceListContent(
                devices = devices,
                onAddDeviceClick = onAddDeviceClick,
                onDeviceClick = onDeviceClick,
                onDeviceLongClick = onDeviceLongClick,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun NoDeviceContent(
    onConnectDeviceClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.15f))

        Text(
            text = stringResource(R.string.top_infrared_up_case),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.see_more_than),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(0.2f))

        Image(
            painter = painterResource(R.drawable.ic_main_device_bg_not),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .weight(0.4f),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.weight(0.15f))

        FilledTonalButton(
            onClick = onConnectDeviceClick,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(54.dp),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text(
                text = stringResource(R.string.tc_connect_device),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
private fun DeviceListContent(
    devices: List<DeviceInfo>,
    onAddDeviceClick: () -> Unit,
    onDeviceClick: (DeviceType) -> Unit,
    onDeviceLongClick: (DeviceType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.top_infrared_up_case),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(R.string.see_more_than),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            IconButton(
                onClick = onAddDeviceClick,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add device",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(devices) { device ->
                DeviceCard(
                    device = device,
                    onClick = { onDeviceClick(device.type) },
                    onLongClick = { onDeviceLongClick(device.type) }
                )
            }
        }
    }
}

@Composable
private fun DeviceCard(
    device: DeviceInfo,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showDeleteOption by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = device.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = if (device.isConnected) Icons.Default.CheckCircle else Icons.Default.Error,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (device.isConnected) Color(0xFF4CAF50) else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = if (device.isConnected) "Connected" else "Disconnected",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (device.isConnected) Color(0xFF4CAF50) else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            device.batteryLevel?.let { level ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.BatteryFull,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "${level}%",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Preview(name = "No Devices", showBackground = true)
@Composable
private fun NoDevicePreview() {
    MainScreen(
        devices = emptyList(),
        onConnectDeviceClick = {},
        onAddDeviceClick = {},
        onDeviceClick = {},
        onDeviceLongClick = {}
    )
}

@Preview(name = "With Devices", showBackground = true)
@Composable
private fun WithDevicesPreview() {
    MainScreen(
        devices = listOf(
            DeviceInfo("TC001 (Line)", DeviceType.TC001_LINE, true, 85),
            DeviceInfo("TS004", DeviceType.TS004, false, null),
            DeviceInfo("TC007", DeviceType.TC007, true, 92)
        ),
        onConnectDeviceClick = {},
        onAddDeviceClick = {},
        onDeviceClick = {},
        onDeviceLongClick = {}
    )
}
