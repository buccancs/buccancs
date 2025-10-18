package com.buccancs.ui.components.topdon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.buccancs.ui.theme.Dimensions
import com.buccancs.ui.theme.topdon.TopdonColors
import java.util.Locale
import com.buccancs.ui.theme.topdon.TopdonSpacing
import com.buccancs.ui.theme.topdon.TopdonTheme

/**
 * Measurement mode selector for thermal imaging
 * Matches original app's target mode picker
 */
@Composable
fun TopdonMeasurementModeSelector(
    selectedMode: MeasurementMode,
    onModeSelected: (MeasurementMode) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
            .padding(TopdonSpacing.Small),
        horizontalArrangement = Arrangement.spacedBy(TopdonSpacing.Small)
    ) {
        MeasurementMode.entries.forEach { mode ->
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onModeSelected(mode) },
                shape = RoundedCornerShape(6.dp),
                color = if (selectedMode == mode) MaterialTheme.colorScheme.primary else Color.Transparent
            ) {
                Text(
                    text = mode.label,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (selectedMode == mode) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 8.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
}

enum class MeasurementMode(val label: String) {
    SPOT("Spot"),
    AREA("Area"),
    LINE("Line"),
    MAX_MIN("Max/Min")
}

/**
 * Palette colour selector
 * Matches original app's pseudocolour mode picker
 */
@Composable
fun TopdonPaletteSelector(
    palettes: List<ThermalPalette>,
    selectedPalette: ThermalPalette,
    onPaletteSelected: (ThermalPalette) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(TopdonSpacing.Small)
    ) {
        Text(
            text = "Colour Palette",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(TopdonSpacing.Small)
        ) {
            palettes.forEach { palette ->
                PaletteOption(
                    palette = palette,
                    isSelected = palette == selectedPalette,
                    onClick = { onPaletteSelected(palette) }
                )
            }
        }
    }
}

@Composable
private fun PaletteOption(
    palette: ThermalPalette,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
                    colors = palette.gradientColors
                )
            )
            .border(
                width = if (isSelected) Dimensions.BorderDefault else Dimensions.ElevationNone,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
    )
}

data class ThermalPalette(
    val name: String,
    val gradientColors: List<Color>
)

/**
 * Temperature crosshair overlay for thermal preview
 * Shows temperature reading at touch point
 */
@Composable
fun TopdonTemperatureCrosshair(
    temperature: Float,
    position: IntOffset,
    modifier: Modifier = Modifier,
    useFahrenheit: Boolean = false
) {
    Box(
        modifier = modifier.offset { position }
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(Dimensions.BorderDefault, MaterialTheme.colorScheme.primary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(2.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        Surface(
            modifier = Modifier
                .offset(x = 45.dp, y = (-8).dp)
                .align(Alignment.CenterEnd),
            shape = RoundedCornerShape(4.dp),
            color = MaterialTheme.colorScheme.background.copy(alpha = 0.9f)
        ) {
            Text(
                text = formatTemperature(temperature, useFahrenheit),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

private fun formatTemperature(celsius: Float, useFahrenheit: Boolean): String {
    val temp = if (useFahrenheit) (celsius * 9f / 5f) + 32f else celsius
    val unit = if (useFahrenheit) "°F" else "°C"
    return String.format(Locale.US, "%.1f%s", temp, unit)
}

/**
 * Temperature range display
 * Shows min/max temperature readings from thermal frame
 */
@Composable
fun TopdonTemperatureRange(
    minTemp: Float,
    maxTemp: Float,
    modifier: Modifier = Modifier,
    useFahrenheit: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f), RoundedCornerShape(8.dp))
            .padding(TopdonSpacing.Medium),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TemperatureIndicator(
            label = "MIN",
            temperature = minTemp,
            color = TopdonColors.TempCool,
            useFahrenheit = useFahrenheit
        )
        TemperatureIndicator(
            label = "MAX",
            temperature = maxTemp,
            color = TopdonColors.TempHot,
            useFahrenheit = useFahrenheit
        )
    }
}

@Composable
private fun TemperatureIndicator(
    label: String,
    temperature: Float,
    color: Color,
    useFahrenheit: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(TopdonSpacing.ExtraSmall)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = formatTemperature(temperature, useFahrenheit),
            style = MaterialTheme.typography.titleMedium,
            color = color
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF16131E)
@Composable
private fun TopdonCameraControlsPreview() {
    TopdonTheme {
        var selectedMode by remember { mutableStateOf(MeasurementMode.SPOT) }
        var selectedPalette by remember { mutableStateOf(samplePalettes[0]) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopdonMeasurementModeSelector(
                selectedMode = selectedMode,
                onModeSelected = { selectedMode = it }
            )

            TopdonPaletteSelector(
                palettes = samplePalettes,
                selectedPalette = selectedPalette,
                onPaletteSelected = { selectedPalette = it }
            )

            TopdonTemperatureRange(
                minTemp = 18.5f,
                maxTemp = 35.2f
            )
        }
    }
}

private val samplePalettes = listOf(
    ThermalPalette("Iron", listOf(Color(0xFF000033), Color(0xFFFF0000), Color(0xFFFFFF00), Color(0xFFFFFFFF))),
    ThermalPalette("Rainbow", listOf(Color(0xFF0000FF), Color(0xFF00FF00), Color(0xFFFFFF00), Color(0xFFFF0000))),
    ThermalPalette("Gray", listOf(Color(0xFF000000), Color(0xFF808080), Color(0xFFFFFFFF)))
)
