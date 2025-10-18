package com.buccancs.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.buccancs.ui.theme.Dimensions
import com.buccancs.ui.theme.Spacing

enum class StatusLevel {
    SUCCESS,
    WARNING,
    ERROR,
    INFO
}

@Composable
fun StatusChip(
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector? = null,
    level: StatusLevel = StatusLevel.INFO,
    onClick: (() -> Unit)? = null
) {
    val iconColor =
        when (level) {
            StatusLevel.SUCCESS -> MaterialTheme.colorScheme.primary
            StatusLevel.WARNING -> MaterialTheme.colorScheme.tertiary
            StatusLevel.ERROR -> MaterialTheme.colorScheme.error
            StatusLevel.INFO -> MaterialTheme.colorScheme.onSurfaceVariant
        }

    AssistChip(
        onClick = onClick
            ?: {},
        label = {
            Text(
                label
            )
        },
        leadingIcon = icon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(
                        Dimensions.IconSizeSmall
                    )
                )
            }
        },
        modifier = modifier,
        enabled = onClick != null
    )
}

@Composable
fun StatusIndicator(
    icon: ImageVector,
    label: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            Spacing.ExtraSmall
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(
                Dimensions.IconSizeSmall
            )
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}
