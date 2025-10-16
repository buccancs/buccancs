package com.buccancs.ui.components.topdon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buccancs.ui.theme.topdon.TopdonColors
import com.buccancs.ui.theme.topdon.TopdonSpacing
import com.buccancs.ui.theme.topdon.TopdonTheme

/**
 * Topdon-styled filled card
 */
@Composable
fun TopdonCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = if (onClick != null) {
            modifier.clickable(onClick = onClick)
        } else {
            modifier
        },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = TopdonColors.DarkSurface
        ),
        content = { content() }
    )
}

/**
 * Topdon-styled elevated card with shadow
 */
@Composable
fun TopdonElevatedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    ElevatedCard(
        modifier = if (onClick != null) {
            modifier.clickable(onClick = onClick)
        } else {
            modifier
        },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = TopdonColors.DarkSurface
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = TopdonSpacing.CardElevation
        ),
        content = { content() }
    )
}

/**
 * Topdon-styled outlined card
 */
@Composable
fun TopdonOutlinedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    OutlinedCard(
        modifier = if (onClick != null) {
            modifier.clickable(onClick = onClick)
        } else {
            modifier
        },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = TopdonColors.DarkSurface
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(TopdonColors.LineSeparator)
        ),
        content = { content() }
    )
}

/**
 * Device list item card
 * Matches original app's device connection list items
 */
@Composable
fun TopdonDeviceCard(
    deviceName: String,
    deviceType: String,
    isConnected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    TopdonElevatedCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(TopdonSpacing.Large),
            horizontalArrangement = Arrangement.spacedBy(TopdonSpacing.Medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = TopdonColors.DarkSurfaceVariant,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp),
                        tint = if (isConnected) TopdonColors.Primary else TopdonColors.TextSecondary
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(TopdonSpacing.ExtraSmall)
            ) {
                Text(
                    text = deviceName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = TopdonColors.TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = deviceType,
                    style = MaterialTheme.typography.bodySmall,
                    color = TopdonColors.TextSecondary
                )
            }

            Surface(
                shape = RoundedCornerShape(4.dp),
                color = if (isConnected) TopdonColors.GreenPoint else TopdonColors.CustomControl
            ) {
                Text(
                    text = if (isConnected) "Connected" else "Disconnected",
                    style = MaterialTheme.typography.labelSmall,
                    color = TopdonColors.TextPrimary,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

/**
 * Gallery image card
 * Matches original app's gallery grid items
 */
@Composable
fun TopdonGalleryCard(
    thumbnailContent: @Composable () -> Unit,
    title: String,
    timestamp: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopdonCard(
        modifier = modifier,
        onClick = onClick
    ) {
        Column {
            thumbnailContent()
            Column(
                modifier = Modifier.padding(TopdonSpacing.Small),
                verticalArrangement = Arrangement.spacedBy(TopdonSpacing.ExtraSmall)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = TopdonColors.TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = timestamp,
                    style = MaterialTheme.typography.bodySmall,
                    color = TopdonColors.TextSecondary
                )
            }
        }
    }
}

/**
 * Settings list item
 */
@Composable
fun TopdonSettingsItem(
    title: String,
    subtitle: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingContent: @Composable (() -> Unit)? = null
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = TopdonColors.DarkSurface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(TopdonSpacing.Large),
            horizontalArrangement = Arrangement.spacedBy(TopdonSpacing.Medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = TopdonColors.TextSecondary,
                    modifier = Modifier.size(24.dp)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(TopdonSpacing.ExtraSmall)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TopdonColors.TextPrimary
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = TopdonColors.TextSecondary
                    )
                }
            }

            trailingContent?.invoke()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF16131E)
@Composable
private fun TopdonDeviceCardPreview() {
    TopdonTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TopdonDeviceCard(
                deviceName = "TC001 #12345",
                deviceType = "Thermal Camera",
                isConnected = true,
                onClick = {},
                icon = Icons.Default.Thermostat
            )
            TopdonDeviceCard(
                deviceName = "Shimmer3 GSR",
                deviceType = "Biosensor",
                isConnected = false,
                onClick = {},
                icon = Icons.Default.Sensors
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF16131E)
@Composable
private fun TopdonSettingsItemPreview() {
    TopdonTheme {
        Column {
            TopdonSettingsItem(
                title = "Device Settings",
                subtitle = "Configure thermal camera",
                onClick = {},
                leadingIcon = Icons.Default.Settings,
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = TopdonColors.TextTertiary
                    )
                }
            )
        }
    }
}
