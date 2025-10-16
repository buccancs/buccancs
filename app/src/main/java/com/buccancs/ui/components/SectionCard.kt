package com.buccancs.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.buccancs.ui.theme.LayoutPadding
import com.buccancs.ui.theme.Spacing

/**
 * Canonical card container used across the feature screens. Helps harmonize paddings,
 * shapes, and tonal variations.
 */
@Composable
fun SectionCard(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = LayoutPadding.Card,
    spacing: Dp = Spacing.Small,
    tonal: Boolean = false,
    colors: CardColors? = null,
    elevation: CardElevation? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val resolvedColors = colors ?: if (tonal) {
        CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    } else {
        CardDefaults.elevatedCardColors()
    }

    val resolvedElevation = elevation ?: CardDefaults.elevatedCardElevation(
        defaultElevation = if (tonal) 2.dp else 1.dp,
        pressedElevation = if (tonal) 4.dp else 2.dp
    )

    ElevatedCard(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = resolvedColors,
        elevation = resolvedElevation
    ) {
        Column(
            modifier = Modifier.padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(spacing),
            content = content
        )
    }
}
