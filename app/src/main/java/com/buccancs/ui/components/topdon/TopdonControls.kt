package com.buccancs.ui.components.topdon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buccancs.ui.theme.topdon.TopdonColors
import com.buccancs.ui.theme.topdon.TopdonSpacing
import com.buccancs.ui.theme.topdon.TopdonTheme
import java.util.Locale
import kotlin.math.roundToInt

/**
 * Topdon-styled switch control
 */
@Composable
fun TopdonSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.onSurface,
            checkedTrackColor = MaterialTheme.colorScheme.primary,
            uncheckedThumbColor = TopdonColors.TextTertiary,
            uncheckedTrackColor = MaterialTheme.colorScheme.outlineVariant,
            disabledCheckedThumbColor = TopdonColors.TextTertiary,
            disabledCheckedTrackColor = MaterialTheme.colorScheme.outline,
            disabledUncheckedThumbColor = TopdonColors.TextTertiary,
            disabledUncheckedTrackColor = MaterialTheme.colorScheme.outline
        )
    )
}

/**
 * Topdon-styled checkbox
 */
@Composable
fun TopdonCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.primary,
            uncheckedColor = MaterialTheme.colorScheme.outline,
            checkmarkColor = MaterialTheme.colorScheme.onSurface,
            disabledCheckedColor = MaterialTheme.colorScheme.outlineVariant,
            disabledUncheckedColor = MaterialTheme.colorScheme.outlineVariant
        )
    )
}

/**
 * Topdon-styled radio button
 */
@Composable
fun TopdonRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    RadioButton(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = RadioButtonDefaults.colors(
            selectedColor = MaterialTheme.colorScheme.primary,
            unselectedColor = MaterialTheme.colorScheme.outline,
            disabledSelectedColor = MaterialTheme.colorScheme.outlineVariant,
            disabledUnselectedColor = MaterialTheme.colorScheme.outlineVariant
        )
    )
}

/**
 * Topdon-styled slider with value display
 */
@Composable
fun TopdonSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    label: String,
    modifier: Modifier = Modifier,
    steps: Int = 0,
    enabled: Boolean = true,
    valueFormatter: (Float) -> String = {
        it.roundToInt()
            .toString()
    },
    onValueChangeFinished: (() -> Unit)? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            TopdonSpacing.ExtraSmall
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = valueFormatter(
                    value
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps,
            enabled = enabled,
            onValueChangeFinished = onValueChangeFinished,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.outline,
                disabledThumbColor = MaterialTheme.colorScheme.outlineVariant,
                disabledActiveTrackColor = MaterialTheme.colorScheme.outlineVariant,
                disabledInactiveTrackColor = MaterialTheme.colorScheme.outline
            )
        )
    }
}

/**
 * Temperature slider with Celsius/Fahrenheit formatting
 */
@Composable
fun TopdonTemperatureSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    label: String,
    modifier: Modifier = Modifier,
    useFahrenheit: Boolean = false,
    onValueChangeFinished: (() -> Unit)? = null
) {
    TopdonSlider(
        value = value,
        onValueChange = onValueChange,
        valueRange = valueRange,
        label = label,
        modifier = modifier,
        steps = 0,
        valueFormatter = { temp ->
            val displayTemp =
                if (useFahrenheit) (temp * 9f / 5f) + 32f else temp
            String.format(
                Locale.US,
                "%.1f°%s",
                displayTemp,
                if (useFahrenheit) "F" else "C"
            )
        },
        onValueChangeFinished = onValueChangeFinished
    )
}

/**
 * Zoom slider for camera controls
 */
@Composable
fun TopdonZoomSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    maxZoom: Float = 8f,
    onValueChangeFinished: (() -> Unit)? = null
) {
    TopdonSlider(
        value = value,
        onValueChange = onValueChange,
        valueRange = 1f..maxZoom,
        label = "Zoom",
        modifier = modifier,
        steps = 0,
        valueFormatter = {
            String.format(
                Locale.US,
                "%.1fx",
                it
            )
        },
        onValueChangeFinished = onValueChangeFinished
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF16131E
)
@Composable
private fun TopdonControlsPreview() {
    TopdonTheme {
        var switchState by remember {
            mutableStateOf(
                false
            )
        }
        var checkboxState by remember {
            mutableStateOf(
                false
            )
        }
        var radioState by remember {
            mutableStateOf(
                0
            )
        }
        var sliderValue by remember {
            mutableFloatStateOf(
                50f
            )
        }
        var tempValue by remember {
            mutableFloatStateOf(
                25f
            )
        }
        var zoomValue by remember {
            mutableFloatStateOf(
                1f
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(
                24.dp
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Auto-connect",
                    color = MaterialTheme.colorScheme.onSurface
                )
                TopdonSwitch(
                    checked = switchState,
                    onCheckedChange = {
                        switchState =
                            it
                    })
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                TopdonCheckbox(
                    checked = checkboxState,
                    onCheckedChange = {
                        checkboxState =
                            it
                    })
                Text(
                    text = "Enable high resolution",
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(
                        start = 8.dp
                    )
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(
                    8.dp
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TopdonRadioButton(
                        selected = radioState == 0,
                        onClick = {
                            radioState =
                                0
                        })
                    Text(
                        text = "Celsius",
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(
                            start = 8.dp
                        )
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TopdonRadioButton(
                        selected = radioState == 1,
                        onClick = {
                            radioState =
                                1
                        })
                    Text(
                        text = "Fahrenheit",
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(
                            start = 8.dp
                        )
                    )
                }
            }

            TopdonSlider(
                value = sliderValue,
                onValueChange = {
                    sliderValue =
                        it
                },
                valueRange = 0f..100f,
                label = "Frame Rate"
            )

            TopdonTemperatureSlider(
                value = tempValue,
                onValueChange = {
                    tempValue =
                        it
                },
                valueRange = -20f..120f,
                label = "Temperature Threshold"
            )

            TopdonZoomSlider(
                value = zoomValue,
                onValueChange = {
                    zoomValue =
                        it
                }
            )
        }
    }
}
