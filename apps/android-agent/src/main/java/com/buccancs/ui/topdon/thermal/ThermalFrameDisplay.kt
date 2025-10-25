package com.buccancs.ui.topdon.thermal

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.buccancs.data.sensor.connector.topdon.ThermalNormalizer
import com.buccancs.data.sensor.connector.topdon.capture.AreaMeasurement
import com.buccancs.data.sensor.connector.topdon.capture.LineMeasurement
import com.buccancs.data.sensor.connector.topdon.capture.MaxMinMeasurement
import com.buccancs.data.sensor.connector.topdon.capture.MeasurementProcessor
import com.buccancs.data.sensor.connector.topdon.capture.SpotMeasurement
import com.buccancs.domain.model.TopdonDynamicRange
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonPreviewFrame
import com.buccancs.domain.model.TopdonSettings
import com.buccancs.domain.model.TopdonTemperatureUnit
import com.buccancs.ui.components.topdon.MeasurementMode
import com.buccancs.ui.components.topdon.TopdonTemperatureCrosshair
import com.buccancs.ui.components.topdon.TopdonTemperatureRange
import com.buccancs.ui.topdon.thermal.ThermalMeasurementTarget.Area
import com.buccancs.ui.topdon.thermal.ThermalMeasurementTarget.Line
import com.buccancs.ui.topdon.thermal.ThermalMeasurementTarget.Spot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.math.roundToInt

@Composable
fun ThermalFrameDisplay(
    frame: TopdonPreviewFrame,
    settings: TopdonSettings,
    measurementMode: MeasurementMode,
    measurementTarget: ThermalMeasurementTarget,
    onSelectionChange: (ThermalMeasurementTarget) -> Unit,
    modifier: Modifier = Modifier,
    paletteOverride: TopdonPalette? = null,
    dynamicRangeOverride: TopdonDynamicRange? = null
) {
    val normalizer =
        remember { ThermalNormalizer() }
    val useFahrenheit =
        settings.temperatureUnit == TopdonTemperatureUnit.FAHRENHEIT
    var imageBitmap by remember(frame.timestamp) {
        mutableStateOf<ImageBitmap?>(null)
    }
    var measurements by remember(frame.timestamp) {
        mutableStateOf<MeasurementBundle?>(null)
    }
    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    val density = LocalDensity.current
    val crosshairRadiusPx = with(density) { 20.dp.roundToPx() }

    LaunchedEffect(frame, settings.palette, settings.dynamicRange, paletteOverride, dynamicRangeOverride) {
        val bitmap =
            withContext(Dispatchers.Default) {
                normalizer.createBitmapFromFrame(
                    frame = frame,
                    palette = paletteOverride ?: settings.palette,
                    dynamicRange = dynamicRangeOverride ?: settings.dynamicRange
                )
            }
        imageBitmap = bitmap.asImageBitmap()
    }

    LaunchedEffect(frame, measurementMode, measurementTarget) {
        measurements = withContext(Dispatchers.Default) {
            MeasurementBundle.fromFrame(
                payload = frame.payload,
                width = frame.width,
                height = frame.height,
                measurementMode = measurementMode,
                selection = measurementTarget
            )
        }
    }

    val pointerModifier = modifier
        .fillMaxSize()
        .onSizeChanged { containerSize = it }
        .pointerInput(measurementMode, measurementTarget, containerSize, frame.width, frame.height) {
            if (containerSize.width == 0 || containerSize.height == 0) return@pointerInput
            when (measurementMode) {
                MeasurementMode.SPOT ->
                    detectTapGestures { offset ->
                        onSelectionChange(
                            offset.toSpot(
                                containerSize,
                                frame.width,
                                frame.height
                            )
                        )
                    }

                MeasurementMode.AREA -> {
                    var dragOrigin: Spot? = null
                    detectDragGestures(
                        onDragStart = { offset ->
                            val start = offset.toSpot(containerSize, frame.width, frame.height)
                            dragOrigin = start
                            onSelectionChange(
                                Area(
                                    startX = start.x,
                                    startY = start.y,
                                    endX = start.x,
                                    endY = start.y
                                )
                            )
                        },
                        onDrag = { change, _ ->
                            change.consume()
                            val current = change.position.toSpot(containerSize, frame.width, frame.height)
                            val origin = dragOrigin ?: current
                            onSelectionChange(
                                Area(
                                    startX = origin.x,
                                    startY = origin.y,
                                    endX = current.x,
                                    endY = current.y
                                )
                            )
                        },
                        onDragEnd = {
                            dragOrigin = null
                        },
                        onDragCancel = {
                            dragOrigin = null
                        }
                    )
                }

                MeasurementMode.LINE -> {
                    var dragOrigin: Spot? = null
                    detectDragGestures(
                        onDragStart = { offset ->
                            val start = offset.toSpot(containerSize, frame.width, frame.height)
                            dragOrigin = start
                            onSelectionChange(
                                Line(
                                    startX = start.x,
                                    startY = start.y,
                                    endX = start.x,
                                    endY = start.y
                                )
                            )
                        },
                        onDrag = { change, _ ->
                            change.consume()
                            val current = change.position.toSpot(containerSize, frame.width, frame.height)
                            val origin = dragOrigin ?: current
                            onSelectionChange(
                                Line(
                                    startX = origin.x,
                                    startY = origin.y,
                                    endX = current.x,
                                    endY = current.y
                                )
                            )
                        },
                        onDragEnd = {
                            dragOrigin = null
                        },
                        onDragCancel = {
                            dragOrigin = null
                        }
                    )
                }

                MeasurementMode.MAX_MIN -> {
                    // Selection is analytics-driven; ignore user gestures.
                }
            }
        }

    Box(
        modifier = pointerModifier
    ) {
        val bitmap = imageBitmap
        if (bitmap != null) {
            Image(
                bitmap = bitmap,
                contentDescription = "Thermal frame",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        } else {
            Surface(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                tonalElevation = 2.dp,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "Rendering thermal frame…",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        val bundle = measurements
        val appliedTarget = bundle?.appliedTarget ?: measurementTarget

        bundle?.let { result ->
            when (measurementMode) {
                MeasurementMode.SPOT -> {
                    result.spot?.let { spot ->
                        val spotTarget = (appliedTarget as? Spot) ?: Spot(spot.x, spot.y)
                        val position = spotTarget.toOffset(containerSize, frame.width, frame.height)
                        val crosshairOffset =
                            IntOffset(
                                (position.x - crosshairRadiusPx).roundToInt(),
                                (position.y - crosshairRadiusPx).roundToInt()
                            )
                        TopdonTemperatureCrosshair(
                            temperature = spot.temperature,
                            position = crosshairOffset,
                            modifier = Modifier.align(Alignment.TopStart),
                            useFahrenheit = useFahrenheit
                        )
                        Surface(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(16.dp),
                            tonalElevation = 3.dp,
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = "Spot: ${formatTemp(spot.temperature, useFahrenheit)}",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }

                MeasurementMode.AREA -> {
                    result.area?.let { area ->
                        MeasurementInfoPanel(
                            title = "Area",
                            lines = listOf(
                                "Min ${formatTemp(area.minTemp, useFahrenheit)}",
                                "Avg ${formatTemp(area.avgTemp, useFahrenheit)}",
                                "Max ${formatTemp(area.maxTemp, useFahrenheit)}"
                            ),
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(16.dp)
                        )
                    }
                }

                MeasurementMode.LINE -> {
                    result.line?.let { line ->
                        MeasurementInfoPanel(
                            title = "Line",
                            lines = listOf(
                                "Min ${formatTemp(line.minTemp, useFahrenheit)}",
                                "Avg ${formatTemp(line.avgTemp, useFahrenheit)}",
                                "Max ${formatTemp(line.maxTemp, useFahrenheit)}"
                            ),
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(16.dp)
                        )
                    }
                }

                MeasurementMode.MAX_MIN -> {
                    result.maxMin?.let { extremes ->
                        MeasurementInfoPanel(
                            title = "Extremes",
                            lines = listOf(
                                "Max ${formatTemp(extremes.maxTemp, useFahrenheit)} @ (${extremes.maxX}, ${extremes.maxY})",
                                "Min ${formatTemp(extremes.minTemp, useFahrenheit)} @ (${extremes.minX}, ${extremes.minY})"
                            ),
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(16.dp)
                        )
                    }
                }
            }

            val (rangeMin, rangeMax) =
                when (measurementMode) {
                    MeasurementMode.MAX_MIN -> result.maxMin?.let { it.minTemp to it.maxTemp }
                    MeasurementMode.AREA -> result.area?.let { it.minTemp to it.maxTemp }
                    MeasurementMode.LINE -> result.line?.let { it.minTemp to it.maxTemp }
                    MeasurementMode.SPOT -> result.spot?.let { it.temperature to it.temperature }
                } ?: (Float.NaN to Float.NaN)

            if (!rangeMin.isNaN() && !rangeMax.isNaN()) {
                TopdonTemperatureRange(
                    minTemp = rangeMin,
                    maxTemp = rangeMax,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    useFahrenheit = useFahrenheit
                )
            }
        }

        val primaryColor = MaterialTheme.colorScheme.primary
        val primaryFillColor = primaryColor.copy(alpha = 0.25f)

        Canvas(modifier = Modifier.matchParentSize()) {
            if (containerSize.width == 0 || containerSize.height == 0) return@Canvas
            when (val target = appliedTarget) {
                is Area -> {
                    val area = target.normalized().ensureSpan(frame.width, frame.height)
                    val topLeft = area.toOffset(containerSize, frame.width, frame.height)
                    val bottomRight = area.toBottomRight(containerSize, frame.width, frame.height)
                    val rectWidth = bottomRight.x - topLeft.x
                    val rectHeight = bottomRight.y - topLeft.y
                    if (rectWidth > 0f && rectHeight > 0f) {
                        drawRect(
                            color = primaryFillColor,
                            topLeft = Offset(topLeft.x, topLeft.y),
                            size = androidx.compose.ui.geometry.Size(rectWidth, rectHeight)
                        )
                        drawRect(
                            color = primaryColor,
                            topLeft = Offset(topLeft.x, topLeft.y),
                            size = androidx.compose.ui.geometry.Size(rectWidth, rectHeight),
                            style = Stroke(width = 2.dp.toPx())
                        )
                    }
                }

                is Line -> {
                    val start = target.startToOffset(containerSize, frame.width, frame.height)
                    val end = target.endToOffset(containerSize, frame.width, frame.height)
                    drawLine(
                        color = primaryColor,
                        start = start,
                        end = end,
                        strokeWidth = 3.dp.toPx()
                    )
                }

                is Spot -> {
                    // Crosshair already rendered via composable.
                }
            }

            if (measurementMode == MeasurementMode.MAX_MIN) {
                bundle?.maxMin?.let { extremes ->
                    val maxOffset = extremes.maxToOffset(containerSize, frame.width, frame.height)
                    val minOffset = extremes.minToOffset(containerSize, frame.width, frame.height)
                    drawCircle(
                        color = Color.Red,
                        radius = 6.dp.toPx(),
                        center = maxOffset,
                        style = Stroke(width = 2.dp.toPx())
                    )
                    drawCircle(
                        color = Color.Cyan,
                        radius = 6.dp.toPx(),
                        center = minOffset,
                        style = Stroke(width = 2.dp.toPx())
                    )
                }
            }
        }
    }
}

@Composable
private fun MeasurementInfoPanel(
    title: String,
    lines: List<String>,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        tonalElevation = 3.dp
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            lines.forEach { line ->
                Text(
                    text = line,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

private data class MeasurementBundle(
    val spot: SpotMeasurement?,
    val area: AreaMeasurement?,
    val line: LineMeasurement?,
    val maxMin: MaxMinMeasurement?,
    val appliedTarget: ThermalMeasurementTarget
) {
    companion object {
        fun fromFrame(
            payload: ByteArray,
            width: Int,
            height: Int,
            measurementMode: MeasurementMode,
            selection: ThermalMeasurementTarget
        ): MeasurementBundle {
            val clampedSelection = selection.clampToFrame(width, height)

            val defaultArea = Area(
                startX = (width / 2) - 24,
                startY = (height / 2) - 24,
                endX = (width / 2) + 24,
                endY = (height / 2) + 24
            ).clampToFrame(width, height).let { (it as Area).normalized().ensureSpan(width, height) }

            val spotTarget = when (clampedSelection) {
                is Spot -> clampedSelection
                is Area -> clampedSelection.normalized().centerSpot()
                is Line -> clampedSelection.centerSpot()
            }

            val areaTarget = when (clampedSelection) {
                is Area -> clampedSelection.normalized().ensureSpan(width, height)
                else -> defaultArea
            }

            val lineTarget = when (clampedSelection) {
                is Line -> clampedSelection.ensureSpan(width, height)
                else -> Line(0, height / 2, width - 1, height / 2)
            }

            val spotMeasurement = MeasurementProcessor.measureSpot(
                payload,
                width,
                height,
                spotTarget.x,
                spotTarget.y
            )
            val areaMeasurement = MeasurementProcessor.measureArea(
                payload,
                width,
                height,
                areaTarget.startX,
                areaTarget.startY,
                areaTarget.endX,
                areaTarget.endY
            )
            val lineMeasurement = MeasurementProcessor.measureLine(
                payload,
                width,
                height,
                lineTarget.startX,
                lineTarget.startY,
                lineTarget.endX,
                lineTarget.endY
            )
            val maxMin = MeasurementProcessor.measureMaxMin(
                payload,
                width,
                height
            )

            val appliedTarget = when (measurementMode) {
                MeasurementMode.SPOT -> spotTarget
                MeasurementMode.AREA -> areaTarget
                MeasurementMode.LINE -> lineTarget
                MeasurementMode.MAX_MIN -> spotTarget
            }

            return MeasurementBundle(
                spot = spotMeasurement,
                area = areaMeasurement,
                line = lineMeasurement,
                maxMin = maxMin,
                appliedTarget = appliedTarget
            )
        }
    }
}

private fun formatTemp(
    value: Float?,
    useFahrenheit: Boolean
): String {
    if (value == null || value.isNaN()) return "—"
    val displayValue =
        if (useFahrenheit) {
            value * 9f / 5f + 32f
        } else {
            value
        }
    val unit =
        if (useFahrenheit) "°F" else "°C"
    return String.format(Locale.US, "%.1f%s", displayValue, unit)
}

private fun Offset.toSpot(
    containerSize: IntSize,
    frameWidth: Int,
    frameHeight: Int
): Spot {
    val xRatio = (x / containerSize.width).coerceIn(0f, 1f)
    val yRatio = (y / containerSize.height).coerceIn(0f, 1f)
    val x = (xRatio * (frameWidth - 1)).roundToInt()
    val y = (yRatio * (frameHeight - 1)).roundToInt()
    return Spot(x, y)
}

private fun Spot.toOffset(
    containerSize: IntSize,
    frameWidth: Int,
    frameHeight: Int
): Offset {
    val xRatio = x.toFloat() / (frameWidth - 1).coerceAtLeast(1)
    val yRatio = y.toFloat() / (frameHeight - 1).coerceAtLeast(1)
    return Offset(
        xRatio * containerSize.width,
        yRatio * containerSize.height
    )
}

private fun Area.centerSpot(): Spot {
    val normalized = normalized()
    val centerX = (normalized.startX + normalized.endX) / 2
    val centerY = (normalized.startY + normalized.endY) / 2
    return Spot(centerX, centerY)
}

private fun Line.centerSpot(): Spot {
    val centerX = (startX + endX) / 2
    val centerY = (startY + endY) / 2
    return Spot(centerX, centerY)
}

private fun Area.toOffset(
    containerSize: IntSize,
    frameWidth: Int,
    frameHeight: Int
): Offset {
    val normalized = normalized()
    return Spot(normalized.startX, normalized.startY).toOffset(containerSize, frameWidth, frameHeight)
}

private fun Area.toBottomRight(
    containerSize: IntSize,
    frameWidth: Int,
    frameHeight: Int
): Offset {
    val normalized = normalized()
    return Spot(normalized.endX, normalized.endY).toOffset(containerSize, frameWidth, frameHeight)
}

private fun Line.ensureSpan(
    width: Int,
    height: Int
): Line {
    val clampedStartX = startX.coerceIn(0, width - 1)
    val clampedStartY = startY.coerceIn(0, height - 1)
    val clampedEndX = endX.coerceIn(0, width - 1)
    val clampedEndY = endY.coerceIn(0, height - 1)
    if (clampedStartX == clampedEndX && clampedStartY == clampedEndY) {
        val adjustedEndX =
            if (clampedEndX < width - 1) clampedEndX + 1 else (clampedEndX - 1).coerceAtLeast(0)
        val adjustedEndY =
            if (clampedEndY < height - 1) clampedEndY + 1 else (clampedEndY - 1).coerceAtLeast(0)
        return copy(
            startX = clampedStartX,
            startY = clampedStartY,
            endX = adjustedEndX,
            endY = adjustedEndY
        )
    }
    return copy(
        startX = clampedStartX,
        startY = clampedStartY,
        endX = clampedEndX,
        endY = clampedEndY
    )
}

private fun Area.ensureSpan(
    width: Int,
    height: Int
): Area {
    val normalized = normalized()
    var startX = normalized.startX
    var startY = normalized.startY
    var endX = normalized.endX
    var endY = normalized.endY
    if (startX == endX) {
        if (endX < width - 1) {
            endX += 1
        } else {
            startX = (startX - 1).coerceAtLeast(0)
        }
    }
    if (startY == endY) {
        if (endY < height - 1) {
            endY += 1
        } else {
            startY = (startY - 1).coerceAtLeast(0)
        }
    }
    return Area(startX, startY, endX, endY)
}

private fun Line.startToOffset(
    containerSize: IntSize,
    frameWidth: Int,
    frameHeight: Int
): Offset = Spot(startX, startY).toOffset(containerSize, frameWidth, frameHeight)

private fun Line.endToOffset(
    containerSize: IntSize,
    frameWidth: Int,
    frameHeight: Int
): Offset = Spot(endX, endY).toOffset(containerSize, frameWidth, frameHeight)

private fun MaxMinMeasurement.maxToOffset(
    containerSize: IntSize,
    frameWidth: Int,
    frameHeight: Int
): Offset = Spot(maxX, maxY).toOffset(containerSize, frameWidth, frameHeight)

private fun MaxMinMeasurement.minToOffset(
    containerSize: IntSize,
    frameWidth: Int,
    frameHeight: Int
): Offset = Spot(minX, minY).toOffset(containerSize, frameWidth, frameHeight)
