package com.buccancs.desktop.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buccancs.desktop.ui.theme.Spacing

/**
 * Reusable card component with consistent styling
 * Follows Material 3 card patterns with header and action support
 */
@Composable
fun BuccancsCard(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    colors: CardColors = CardDefaults.cardColors(),
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = colors,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    Spacing.Medium
                ),
            verticalArrangement = Arrangement.spacedBy(
                Spacing.Medium
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        Spacing.ExtraSmall
                    ),
                    modifier = Modifier.weight(
                        1f
                    )
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    subtitle?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        Spacing.Small
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    content = actions
                )
            }
            content()
        }
    }
}

/**
 * Outlined card variant for secondary content
 */
@Composable
fun BuccancsOutlinedCard(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        border = CardDefaults.outlinedCardBorder()
            .copy(
                width = 1.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    Spacing.Medium
                ),
            verticalArrangement = Arrangement.spacedBy(
                Spacing.Small
            )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.ExtraSmall
                )
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                subtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            content()
        }
    }
}

/**
 * Status card with semantic colour support
 */
@Composable
fun StatusCard(
    title: String,
    status: StatusType,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    val colors =
        when (status) {
            StatusType.Success -> CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )

            StatusType.Warning -> CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )

            StatusType.Error -> CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )

            StatusType.Info -> CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = colors,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    Spacing.Medium
                ),
            verticalArrangement = Arrangement.spacedBy(
                Spacing.Small
            )
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall
            )
            content()
        }
    }
}

enum class StatusType {
    Success,
    Warning,
    Error,
    Info
}

/**
 * Legacy alias for backwards compatibility
 */
@Composable
fun SectionCard(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    BuccancsCard(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
        content = content
    )
}
