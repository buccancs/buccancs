package com.buccancs.data.calibration

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import com.buccancs.domain.model.CalibrationPatternConfig
import com.buccancs.util.nowInstant
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.min
import kotlin.random.Random

@Singleton
class SimulatedDualCameraController @Inject constructor() :
    DualCameraController {
    override val rgbInfo: CameraStreamInfo =
        CameraStreamInfo(
            cameraId = "sim-rgb",
            width = 1920,
            height = 1080,
            description = "Simulated RGB Camera"
        )
    override val thermalInfo: CameraStreamInfo =
        CameraStreamInfo(
            cameraId = "sim-thermal",
            width = 256,
            height = 192,
            description = "Simulated Thermal Camera"
        )
    override val isSimulation: Boolean =
        true
    private val random =
        Random(
            0xBACC4D3
        )

    override suspend fun ensureReady() =
        Unit

    override suspend fun shutdown() =
        Unit

    override suspend fun capturePair(
        pattern: CalibrationPatternConfig
    ): CalibrationFramePair {
        val timestamp =
            nowInstant()
        val rgbBitmap =
            renderPattern(
                width = rgbInfo.width,
                height = rgbInfo.height,
                pattern = pattern,
                rotationRad = randomRotation(),
                translationXPct = randomOffset(),
                translationYPct = randomOffset(),
                scaleMultiplier = randomScale(
                    base = 0.9f,
                    jitter = 0.08f
                ),
                foreground = Color.BLACK,
                background = Color.WHITE
            )
        val thermalBitmap =
            renderPattern(
                width = thermalInfo.width,
                height = thermalInfo.height,
                pattern = pattern,
                rotationRad = randomRotation(
                    extraJitter = 0.08f
                ),
                translationXPct = randomOffset(
                    scale = 0.25f
                ),
                translationYPct = randomOffset(
                    scale = 0.25f
                ),
                scaleMultiplier = randomScale(
                    base = 0.92f,
                    jitter = 0.12f
                ),
                foreground = Color.rgb(
                    52,
                    52,
                    52
                ),
                background = Color.rgb(
                    220,
                    220,
                    220
                )
            )
        return CalibrationFramePair(
            rgb = CalibrationFrame(
                bitmap = rgbBitmap,
                capturedAt = timestamp
            ),
            thermal = CalibrationFrame(
                bitmap = thermalBitmap,
                capturedAt = timestamp
            )
        )
    }

    private fun randomRotation(
        extraJitter: Float = 0.05f
    ): Float {
        val base =
            (random.nextFloat() - 0.5f) * 0.35f
        val jitter =
            (random.nextFloat() - 0.5f) * extraJitter
        return (base + jitter) * PI.toFloat()
    }

    private fun randomOffset(
        scale: Float = 0.18f
    ): Float =
        (random.nextFloat() - 0.5f) * 2f * scale

    private fun randomScale(
        base: Float,
        jitter: Float
    ): Float =
        base + (random.nextFloat() - 0.5f) * jitter

    private fun renderPattern(
        width: Int,
        height: Int,
        pattern: CalibrationPatternConfig,
        rotationRad: Float,
        translationXPct: Float,
        translationYPct: Float,
        scaleMultiplier: Float,
        foreground: Int,
        background: Int
    ): Bitmap {
        val bitmap =
            Bitmap.createBitmap(
                width,
                height,
                Bitmap.Config.ARGB_8888
            )
        val canvas =
            Canvas(
                bitmap
            )
        canvas.drawColor(
            background
        )
        val paint =
            Paint(
                Paint.ANTI_ALIAS_FLAG
            )
        paint.style =
            Paint.Style.FILL
        val minDimension =
            min(
                width,
                height
            ).toFloat()
        val squareSize =
            minDimension * 0.65f / maxOf(
                pattern.cols,
                pattern.rows
            )
        val patternWidth =
            pattern.cols * squareSize
        val patternHeight =
            pattern.rows * squareSize
        val centerX =
            width / 2f
        val centerY =
            height / 2f
        val translationX =
            translationXPct * centerX * 0.6f
        val translationY =
            translationYPct * centerY * 0.6f
        val transform =
            Matrix().apply {
                preTranslate(
                    -patternWidth / 2f,
                    -patternHeight / 2f
                )
                postScale(
                    scaleMultiplier,
                    scaleMultiplier
                )
                postRotate(
                    rotationRad * 180f / PI.toFloat()
                )
                postTranslate(
                    centerX + translationX,
                    centerY + translationY
                )
            }
        val rect =
            RectF()
        for (row in 0 until pattern.rows) {
            for (col in 0 until pattern.cols) {
                val left =
                    col * squareSize
                val top =
                    row * squareSize
                rect.set(
                    left,
                    top,
                    left + squareSize,
                    top + squareSize
                )
                transform.mapRect(
                    rect
                )
                val isDark =
                    ((row + col) and 1) == 0
                paint.color =
                    if (isDark) foreground else background
                canvas.drawRect(
                    rect,
                    paint
                )
            }
        }
        addVignetting(
            canvas,
            width,
            height,
            foreground,
            background
        )
        addStructuredNoise(
            canvas,
            width,
            height,
            foreground
        )
        return bitmap
    }

    private fun addVignetting(
        canvas: Canvas,
        width: Int,
        height: Int,
        foreground: Int,
        background: Int
    ) {
        val paint =
            Paint(
                Paint.ANTI_ALIAS_FLAG
            )
        val centerX =
            width / 2f
        val centerY =
            height / 2f
        val maxRadius =
            min(
                width,
                height
            ) * 0.55f
        val steps =
            32
        for (i in 0 until steps) {
            val t =
                i / (steps - 1f)
            val alpha =
                (1f - abs(
                    t - 0.7f
                )) * 0.2f
            paint.color =
                blendColors(
                    background,
                    foreground,
                    alpha.coerceIn(
                        0f,
                        0.2f
                    )
                )
            paint.style =
                Paint.Style.STROKE
            paint.strokeWidth =
                6f
            val radius =
                (t * maxRadius) + random.nextFloat() * 6f
            canvas.drawCircle(
                centerX,
                centerY,
                radius,
                paint
            )
        }
    }

    private fun addStructuredNoise(
        canvas: Canvas,
        width: Int,
        height: Int,
        foreground: Int
    ) {
        val paint =
            Paint(
                Paint.ANTI_ALIAS_FLAG
            )
        paint.style =
            Paint.Style.FILL
        val count =
            (width * height * 0.0008f).toInt()
                .coerceAtLeast(
                    40
                )
        for (i in 0 until count) {
            val cx =
                random.nextFloat() * width
            val cy =
                random.nextFloat() * height
            val radius =
                1f + random.nextFloat() * 3f
            val intensity =
                0.1f + random.nextFloat() * 0.4f
            paint.color =
                blendColors(
                    Color.TRANSPARENT,
                    foreground,
                    intensity
                )
            canvas.drawCircle(
                cx,
                cy,
                radius,
                paint
            )
        }
    }

    private fun blendColors(
        base: Int,
        overlay: Int,
        alpha: Float
    ): Int {
        val inv =
            1f - alpha
        val r =
            (Color.red(
                base
            ) * inv + Color.red(
                overlay
            ) * alpha).toInt()
                .coerceIn(
                    0,
                    255
                )
        val g =
            (Color.green(
                base
            ) * inv + Color.green(
                overlay
            ) * alpha).toInt()
                .coerceIn(
                    0,
                    255
                )
        val b =
            (Color.blue(
                base
            ) * inv + Color.blue(
                overlay
            ) * alpha).toInt()
                .coerceIn(
                    0,
                    255
                )
        return Color.rgb(
            r,
            g,
            b
        )
    }
}
