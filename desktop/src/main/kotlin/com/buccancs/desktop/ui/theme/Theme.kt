package com.buccancs.desktop.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Material Design 3 Theme for Buccancs Desktop
 * 
 * Follows Material Design 3 guidelines with:
 * - Complete color scheme with semantic roles
 * - Typography scale for hierarchy
 * - Shape system for consistency
 * - Support for light and dark themes
 */

private val LightColorScheme = lightColorScheme(
    primary = LightColorTokens.Primary,
    onPrimary = LightColorTokens.OnPrimary,
    primaryContainer = LightColorTokens.PrimaryContainer,
    onPrimaryContainer = LightColorTokens.OnPrimaryContainer,
    
    secondary = LightColorTokens.Secondary,
    onSecondary = LightColorTokens.OnSecondary,
    secondaryContainer = LightColorTokens.SecondaryContainer,
    onSecondaryContainer = LightColorTokens.OnSecondaryContainer,
    
    tertiary = LightColorTokens.Tertiary,
    onTertiary = LightColorTokens.OnTertiary,
    tertiaryContainer = LightColorTokens.TertiaryContainer,
    onTertiaryContainer = LightColorTokens.OnTertiaryContainer,
    
    error = LightColorTokens.Error,
    onError = LightColorTokens.OnError,
    errorContainer = LightColorTokens.ErrorContainer,
    onErrorContainer = LightColorTokens.OnErrorContainer,
    
    background = LightColorTokens.Background,
    onBackground = LightColorTokens.OnBackground,
    
    surface = LightColorTokens.Surface,
    onSurface = LightColorTokens.OnSurface,
    surfaceVariant = LightColorTokens.SurfaceVariant,
    onSurfaceVariant = LightColorTokens.OnSurfaceVariant,
    
    outline = LightColorTokens.Outline,
    outlineVariant = LightColorTokens.OutlineVariant,
    
    scrim = LightColorTokens.Scrim,
    
    inverseSurface = LightColorTokens.InverseSurface,
    inverseOnSurface = LightColorTokens.InverseOnSurface,
    inversePrimary = LightColorTokens.InversePrimary
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkColorTokens.Primary,
    onPrimary = DarkColorTokens.OnPrimary,
    primaryContainer = DarkColorTokens.PrimaryContainer,
    onPrimaryContainer = DarkColorTokens.OnPrimaryContainer,
    
    secondary = DarkColorTokens.Secondary,
    onSecondary = DarkColorTokens.OnSecondary,
    secondaryContainer = DarkColorTokens.SecondaryContainer,
    onSecondaryContainer = DarkColorTokens.OnSecondaryContainer,
    
    tertiary = DarkColorTokens.Tertiary,
    onTertiary = DarkColorTokens.OnTertiary,
    tertiaryContainer = DarkColorTokens.TertiaryContainer,
    onTertiaryContainer = DarkColorTokens.OnTertiaryContainer,
    
    error = DarkColorTokens.Error,
    onError = DarkColorTokens.OnError,
    errorContainer = DarkColorTokens.ErrorContainer,
    onErrorContainer = DarkColorTokens.OnErrorContainer,
    
    background = DarkColorTokens.Background,
    onBackground = DarkColorTokens.OnBackground,
    
    surface = DarkColorTokens.Surface,
    onSurface = DarkColorTokens.OnSurface,
    surfaceVariant = DarkColorTokens.SurfaceVariant,
    onSurfaceVariant = DarkColorTokens.OnSurfaceVariant,
    
    outline = DarkColorTokens.Outline,
    outlineVariant = DarkColorTokens.OutlineVariant,
    
    scrim = DarkColorTokens.Scrim,
    
    inverseSurface = DarkColorTokens.InverseSurface,
    inverseOnSurface = DarkColorTokens.InverseOnSurface,
    inversePrimary = DarkColorTokens.InversePrimary
)

/**
 * Composition local for semantic colors (success, warning, info)
 * These extend beyond Material 3's base color scheme
 */
val LocalSemanticColors = staticCompositionLocalOf { SemanticColors }

/**
 * Main theme composable for Buccancs Desktop
 * 
 * @param darkTheme Whether to use dark theme. Defaults to true for desktop comfort.
 * @param content The content to display within the theme
 */
@Composable
fun BuccancsTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(LocalSemanticColors provides SemanticColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = BuccancsTypography,
            shapes = BuccancsShapes,
            content = content
        )
    }
}

/**
 * Extension to access semantic colors from anywhere in the composition
 */
object BuccancsTheme {
    val semanticColors: SemanticColors
        @Composable
        get() = LocalSemanticColors.current
}
