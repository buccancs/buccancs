package com.buccancs.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

/**
 * Spacing tokens built on a 4dp grid.
 * The expanded scale helps remove ad-hoc paddings in the feature UIs.
 */
object Spacing {
    val None = 0.dp
    val Hairline = 1.dp
    val Micro = 2.dp
    val ExtraSmall = 4.dp
    val Small = 8.dp
    val SmallMedium = 12.dp
    val Medium = 16.dp
    val MediumLarge = 20.dp
    val Large = 24.dp
    val ExtraLarge = 32.dp
    val ExtraExtraLarge = 40.dp
    val Section = 48.dp
    val SectionLarge = 56.dp
    val Hero = 80.dp
}

/**
 * Canonical padding configurations used by surface level components.
 */
object LayoutPadding {
    val Screen = PaddingValues(
        horizontal = Spacing.Large,
        vertical = Spacing.Medium
    )
    val ScreenCompact = PaddingValues(
        horizontal = Spacing.Medium,
        vertical = Spacing.Small
    )
    val Section = PaddingValues(all = Spacing.Medium)
    val Card = PaddingValues(
        horizontal = Spacing.Medium,
        vertical = Spacing.Small
    )
}

object CornerRadius {
    val Small = 8.dp
    val Medium = 12.dp
    val Large = 16.dp
    val ExtraLarge = 28.dp
}
