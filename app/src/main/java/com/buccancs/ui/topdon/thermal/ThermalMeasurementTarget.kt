package com.buccancs.ui.topdon.thermal

sealed interface ThermalMeasurementTarget {
    data class Spot(
        val x: Int,
        val y: Int
    ) : ThermalMeasurementTarget

    data class Area(
        val startX: Int,
        val startY: Int,
        val endX: Int,
        val endY: Int
    ) : ThermalMeasurementTarget

    data class Line(
        val startX: Int,
        val startY: Int,
        val endX: Int,
        val endY: Int
    ) : ThermalMeasurementTarget
}

fun ThermalMeasurementTarget.clampToFrame(
    width: Int,
    height: Int
): ThermalMeasurementTarget =
    when (this) {
        is ThermalMeasurementTarget.Spot ->
            copy(
                x = x.coerceIn(0, width - 1),
                y = y.coerceIn(0, height - 1)
            )

        is ThermalMeasurementTarget.Area -> {
            val clampedStartX = startX.coerceIn(0, width - 1)
            val clampedStartY = startY.coerceIn(0, height - 1)
            val clampedEndX = endX.coerceIn(0, width - 1)
            val clampedEndY = endY.coerceIn(0, height - 1)
            copy(
                startX = clampedStartX,
                startY = clampedStartY,
                endX = clampedEndX,
                endY = clampedEndY
            )
        }

        is ThermalMeasurementTarget.Line -> {
            val clampedStartX = startX.coerceIn(0, width - 1)
            val clampedStartY = startY.coerceIn(0, height - 1)
            val clampedEndX = endX.coerceIn(0, width - 1)
            val clampedEndY = endY.coerceIn(0, height - 1)
            copy(
                startX = clampedStartX,
                startY = clampedStartY,
                endX = clampedEndX,
                endY = clampedEndY
            )
        }
    }

fun ThermalMeasurementTarget.Area.normalized(): ThermalMeasurementTarget.Area =
    ThermalMeasurementTarget.Area(
        startX = minOf(startX, endX),
        startY = minOf(startY, endY),
        endX = maxOf(startX, endX),
        endY = maxOf(startY, endY)
    )
