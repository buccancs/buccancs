package com.buccancs.ui.theme.topdon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import com.buccancs.ui.theme.BuccancsTypography

/**
 * Topdon-specific Material 3 dark theme
 * Based on original Topdon TC001 application design
 */
private val TopdonDarkColorScheme =
    darkColorScheme(
        primary = TopdonColors.Primary,
        onPrimary = TopdonColors.TextPrimary,
        primaryContainer = TopdonColors.PrimaryContainer,
        onPrimaryContainer = TopdonColors.TextPrimary,

        secondary = TopdonColors.Secondary,
        onSecondary = TopdonColors.TextPrimary,
        secondaryContainer = TopdonColors.SecondaryContainer,
        onSecondaryContainer = TopdonColors.TextPrimary,

        tertiary = TopdonColors.TempHot,
        onTertiary = TopdonColors.TextPrimary,
        tertiaryContainer = TopdonColors.TempCool,
        onTertiaryContainer = TopdonColors.TextPrimary,

        background = TopdonColors.DarkBackground,
        onBackground = TopdonColors.TextPrimary,

        surface = TopdonColors.DarkSurface,
        onSurface = TopdonColors.TextPrimary,
        surfaceVariant = TopdonColors.DarkSurfaceVariant,
        onSurfaceVariant = TopdonColors.TextSecondary,

        error = TopdonColors.SelectRed,
        onError = TopdonColors.TextPrimary,

        outline = TopdonColors.LineSeparator,
        outlineVariant = TopdonColors.CustomControl
    )

@Composable
fun TopdonTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = TopdonDarkColorScheme,
        typography = BuccancsTypography,
        content = content
    )
}
