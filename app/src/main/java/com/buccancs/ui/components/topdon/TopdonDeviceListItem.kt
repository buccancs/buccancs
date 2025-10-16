package com.buccancs.ui.components.topdon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cable
import androidx.compose.material.icons.filled.Usb
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopdonDeviceListItem(
    deviceName: String,
    deviceState: String,
    isConnected: Boolean,
    connectionType: DeviceConnectionType = DeviceConnectionType.USB,
    batteryLevel: Int? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (isConnected) {
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = deviceName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(
                                if (isConnected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                            )
                    )

                    Text(
                        text = deviceState,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isConnected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        },
                        fontSize = 12.sp
                    )

                    batteryLevel?.let { level ->
                        BatteryIndicator(
                            level = level,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }

            Icon(
                imageVector = connectionType.icon,
                contentDescription = connectionType.label,
                modifier = Modifier.size(48.dp),
                tint = if (isConnected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }
}

@Composable
private fun BatteryIndicator(
    level: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 20.dp, height = 12.dp)
                .background(
                    color = MaterialTheme.colorScheme.outline,
                    shape = MaterialTheme.shapes.extraSmall
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = (level / 100f).coerceIn(0f, 1f))
                    .matchParentSize()
                    .padding(1.dp)
                    .background(
                        color = when {
                            level > 60 -> MaterialTheme.colorScheme.primary
                            level > 20 -> MaterialTheme.colorScheme.tertiary
                            else -> MaterialTheme.colorScheme.error
                        },
                        shape = MaterialTheme.shapes.extraSmall
                    )
            )
        }

        Text(
            text = "$level%",
            style = MaterialTheme.typography.bodySmall,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

enum class DeviceConnectionType(val label: String, val icon: ImageVector) {
    USB("USB", Icons.Default.Usb),
    WIRED("Wired", Icons.Default.Cable)
}
