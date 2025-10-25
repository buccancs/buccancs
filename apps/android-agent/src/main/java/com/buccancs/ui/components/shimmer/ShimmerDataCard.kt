package com.buccancs.ui.components.shimmer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buccancs.ui.components.SectionCard
import com.buccancs.ui.shimmer.SampleEntry
import com.buccancs.ui.theme.Spacing
import java.util.Locale

@Composable
fun ShimmerDataCard(
    timestamp: Double?,
    accelX: Double?,
    accelY: Double?,
    accelZ: Double?,
    gsrData: Double?,
    gsrConductance: Double?,
    history: List<SampleEntry>,
    modifier: Modifier = Modifier
) {
    SectionCard(
        modifier = modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Live Data",
            style = MaterialTheme.typography.titleMedium
        )

        if (timestamp != null) {
            DataRow(
                label = "Timestamp",
                value = String.format(
                    Locale.US,
                    "%.2f ms",
                    timestamp
                )
            )
        }

        if (accelX != null || accelY != null || accelZ != null) {
            Text(
                text = "Accelerometer (m/s^2)",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (accelX != null) {
                DataRow(
                    label = "X-Axis",
                    value = String.format(
                        Locale.US,
                        "%.3f",
                        accelX
                    )
                )
            }
            if (accelY != null) {
                DataRow(
                    label = "Y-Axis",
                    value = String.format(
                        Locale.US,
                        "%.3f",
                        accelY
                    )
                )
            }
            if (accelZ != null) {
                DataRow(
                    label = "Z-Axis",
                    value = String.format(
                        Locale.US,
                        "%.3f",
                        accelZ
                    )
                )
            }
        }

        if (gsrData != null) {
            Text(
                text = "Galvanic Skin Response",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            DataRow(
                label = "GSR",
                value = String.format(
                    Locale.US,
                    "%.2f kOhm",
                    gsrData
                )
            )
        }

        if (gsrConductance != null) {
            Text(
                text = "Conductance",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            DataRow(
                label = "Conductance",
                value = String.format(
                    Locale.US,
                    "%.2f µS",
                    gsrConductance
                )
            )
        }

        val hasConductanceHistory =
            history.any { it.conductanceMicrosiemens != null }
        val hasResistanceHistory =
            history.any { it.resistanceKOhm != null }
        val historySelector: ((SampleEntry) -> Double?)? =
            when {
                hasConductanceHistory -> { entry: SampleEntry ->
                    entry.conductanceMicrosiemens
                }

                hasResistanceHistory -> { entry: SampleEntry ->
                    entry.resistanceKOhm
                }

                else -> null
            }
        if (historySelector != null) {
            Spacer(
                modifier = Modifier.height(
                    Spacing.Small
                )
            )
            val historyLabel =
                if (hasConductanceHistory) {
                    "Conductance History (µS)"
                } else {
                    "Resistance History (kΩ)"
                }
            Text(
                text = historyLabel,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            val historyPoints =
                history.count { entry ->
                    historySelector(entry) != null
                }
            if (historyPoints >= 2) {
                ShimmerHistoryChart(
                    history = history,
                    valueSelector = historySelector,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            140.dp
                        )
                )
            } else {
                Text(
                    text = "Collecting history…",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (history.isEmpty() && timestamp == null && accelX == null && accelY == null && accelZ == null && gsrData == null) {
            Text(
                text = "No data available. Start streaming to see live data.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ShimmerHistoryChart(
    history: List<SampleEntry>,
    valueSelector: (SampleEntry) -> Double?,
    modifier: Modifier = Modifier
) {
    val primaryColor =
        MaterialTheme.colorScheme.primary
    val gridColor =
        MaterialTheme.colorScheme.onSurfaceVariant.copy(
            alpha = 0.15f
        )
    val markerColor =
        MaterialTheme.colorScheme.onPrimary
    val points =
        history.mapNotNull { entry ->
            valueSelector(entry)?.let { value ->
                entry.timestampMillis.toFloat() to value.toFloat()
            }
        }
            .sortedBy { it.first }
    if (points.size < 2) {
        return
    }
    val minTimestamp =
        points.first().first
    val maxTimestamp =
        points.last().first
    val timeRange =
        (maxTimestamp - minTimestamp).takeIf { it > 0f }
            ?: 1f
    val minValue =
        points.minOf { it.second }
    val maxValue =
        points.maxOf { it.second }
    val valueRange =
        (maxValue - minValue).takeIf { it > 0f }
            ?: 0.001f

    Canvas(
        modifier = modifier
    ) {
        val width =
            size.width
        val height =
            size.height
        val horizontalGuides =
            4
        val step =
            height / horizontalGuides
        for (index in 1 until horizontalGuides) {
            val y =
                index * step
            drawLine(
                color = gridColor,
                start = Offset(
                    0f,
                    y
                ),
                end = Offset(
                    width,
                    y
                ),
                strokeWidth = 1f
            )
        }

        val path =
            Path()
        points.forEachIndexed { index, point ->
            val normalizedX =
                (point.first - minTimestamp) / timeRange
            val normalizedY =
                (point.second - minValue) / valueRange
            val x =
                normalizedX * width
            val y =
                height - (normalizedY * height)
            val offset =
                Offset(
                    x,
                    y
                )
            if (index == 0) {
                path.moveTo(
                    offset.x,
                    offset.y
                )
            } else {
                path.lineTo(
                    offset.x,
                    offset.y
                )
            }
        }

        drawPath(
            path = path,
            color = primaryColor,
            style = Stroke(
                width = 4f,
                cap = StrokeCap.Round
            )
        )

        val last =
            points.last()
        val lastX =
            ((last.first - minTimestamp) / timeRange) * width
        val lastY =
            height - ((last.second - minValue) / valueRange) * height
        drawCircle(
            color = markerColor,
            radius = 6f,
            center = Offset(
                lastX,
                lastY
            )
        )
        drawCircle(
            color = primaryColor,
            radius = 4f,
            center = Offset(
                lastX,
                lastY
            )
        )
    }
}

@Composable
private fun DataRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ShimmerDataCardPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(
                Spacing.Small
            )
        ) {
            ShimmerDataCard(
                timestamp = null,
                accelX = null,
                accelY = null,
                accelZ = null,
                gsrData = null,
                gsrConductance = null,
                history = emptyList()
            )

            ShimmerDataCard(
                timestamp = 12345.67,
                accelX = 0.123,
                accelY = -0.456,
                accelZ = 9.789,
                gsrData = 125.45,
                gsrConductance = 3.21,
                history = List(
                    8
                ) { index ->
                    SampleEntry(
                        timestampMillis = index * 4_000L,
                        conductanceMicrosiemens = 2.8 + index * 0.15,
                        resistanceKOhm = 112.0 - index
                    )
                }
            )
        }
    }
}
