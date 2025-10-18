package com.buccancs.ui.components.topdon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonSuperSamplingFactor

@Composable
fun PaletteSelectionDialog(
    currentPalette: TopdonPalette,
    onDismiss: () -> Unit,
    onConfirm: (TopdonPalette) -> Unit
) {
    var selectedPalette by remember {
        mutableStateOf(
            currentPalette
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Select Colour Palette"
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    8.dp
                )
            ) {
                TopdonPalette.values()
                    .forEach { palette ->
                        PaletteOption(
                            palette = palette,
                            selected = palette == selectedPalette,
                            onClick = {
                                selectedPalette =
                                    palette
                            }
                        )
                    }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(
                        selectedPalette
                    )
                    onDismiss()
                }
            ) {
                Text(
                    "Apply"
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    "Cancel"
                )
            }
        }
    )
}

@Composable
private fun PaletteOption(
    palette: TopdonPalette,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick
            )
            .padding(
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            16.dp
        )
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )

        Column(
            modifier = Modifier.weight(
                1f
            )
        ) {
            Text(
                text = palette.name.lowercase()
                    .replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = when (palette) {
                    TopdonPalette.GRAYSCALE -> "Black and white thermal display"
                    TopdonPalette.IRONBOW -> "Classic thermal imaging colours"
                    TopdonPalette.RAINBOW -> "Full spectrum colour range"
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        PalettePreview(
            palette
        )
    }
}

@Composable
private fun PalettePreview(
    palette: TopdonPalette
) {
    Row(
        modifier = Modifier
            .width(
                80.dp
            )
            .height(
                24.dp
            )
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(
                    4.dp
                )
            )
    ) {
        val colours =
            when (palette) {
                TopdonPalette.GRAYSCALE -> listOf(
                    Color(
                        0xFF000000
                    ),
                    Color(
                        0xFF404040
                    ),
                    Color(
                        0xFF808080
                    ),
                    Color(
                        0xFFC0C0C0
                    ),
                    Color(
                        0xFFFFFFFF
                    )
                )

                TopdonPalette.IRONBOW -> listOf(
                    Color(
                        0xFF000000
                    ),
                    Color(
                        0xFF800080
                    ),
                    Color(
                        0xFFFF0000
                    ),
                    Color(
                        0xFFFFFF00
                    ),
                    Color(
                        0xFFFFFFFF
                    )
                )

                TopdonPalette.RAINBOW -> listOf(
                    Color(
                        0xFF0000FF
                    ),
                    Color(
                        0xFF00FFFF
                    ),
                    Color(
                        0xFF00FF00
                    ),
                    Color(
                        0xFFFFFF00
                    ),
                    Color(
                        0xFFFF0000
                    )
                )
            }

        colours.forEach { colour ->
            Box(
                modifier = Modifier
                    .weight(
                        1f
                    )
                    .height(
                        24.dp
                    )
                    .background(
                        colour
                    )
            )
        }
    }
}

@Composable
fun SuperSamplingDialog(
    currentFactor: TopdonSuperSamplingFactor,
    onDismiss: () -> Unit,
    onConfirm: (TopdonSuperSamplingFactor) -> Unit
) {
    var selectedFactor by remember {
        mutableStateOf(
            currentFactor
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Super Sampling Quality"
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    8.dp
                )
            ) {
                Text(
                    text = "Higher quality increases processing time",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(
                    modifier = Modifier.height(
                        8.dp
                    )
                )

                TopdonSuperSamplingFactor.values()
                    .forEach { factor ->
                        SuperSamplingOption(
                            factor = factor,
                            selected = factor == selectedFactor,
                            onClick = {
                                selectedFactor =
                                    factor
                            }
                        )
                    }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(
                        selectedFactor
                    )
                    onDismiss()
                }
            ) {
                Text(
                    "Apply"
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    "Cancel"
                )
            }
        }
    )
}

@Composable
private fun SuperSamplingOption(
    factor: TopdonSuperSamplingFactor,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick
            )
            .padding(
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            16.dp
        )
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )

        Column(
            modifier = Modifier.weight(
                1f
            )
        ) {
            Text(
                text = "${factor.multiplier}x",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = when (factor) {
                    TopdonSuperSamplingFactor.X1 -> "Standard quality, fastest processing"
                    TopdonSuperSamplingFactor.X2 -> "Better quality, balanced performance"
                    TopdonSuperSamplingFactor.X4 -> "Best quality, slower processing"
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (selected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(
                    24.dp
                )
            )
        }
    }
}

@Composable
fun FpsLimitDialog(
    currentFps: Int,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var selectedFps by remember {
        mutableFloatStateOf(
            currentFps.toFloat()
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Preview Frame Rate"
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    16.dp
                )
            ) {
                Text(
                    text = "Limit preview frame rate to reduce power consumption",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "${selectedFps.toInt()} FPS",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                )

                Slider(
                    value = selectedFps,
                    onValueChange = {
                        selectedFps =
                            it
                    },
                    valueRange = 1f..30f,
                    steps = 28,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "1 FPS",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "30 FPS",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(
                        selectedFps.toInt()
                    )
                    onDismiss()
                }
            ) {
                Text(
                    "Apply"
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    "Cancel"
                )
            }
        }
    )
}
