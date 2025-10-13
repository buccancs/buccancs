package com.buccancs.data.calibration

import android.graphics.BitmapFactory
import androidx.annotation.VisibleForTesting
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.CalibrationCapture
import com.buccancs.domain.model.CalibrationDefaults
import com.buccancs.domain.model.CalibrationImageDescriptor
import com.buccancs.domain.model.CalibrationMetrics
import com.buccancs.domain.model.CalibrationPatternConfig
import com.buccancs.domain.model.CalibrationResult
import com.buccancs.domain.model.CalibrationSessionState
import com.buccancs.domain.model.CameraIntrinsicParameters
import com.buccancs.domain.model.ExtrinsicTransform
import com.buccancs.domain.repository.CalibrationRepository
import com.buccancs.util.nowInstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.calib3d.Calib3d
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.MatOfDouble
import org.opencv.core.MatOfPoint2f
import org.opencv.core.MatOfPoint3f
import org.opencv.core.Point3
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc
import java.io.File
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.pow

@Singleton
class DefaultCalibrationRepository @Inject constructor(
    private val controller: DualCameraController,
    private val storage: CalibrationStorage,
    private val metricsStore: CalibrationMetricsStore,
    @ApplicationScope private val scope: CoroutineScope
) : CalibrationRepository {
    private val stateMutex = Mutex()
    private val calculationMutex = Mutex()
    private val _state = MutableStateFlow(initialState())
    private val metricsState = MutableStateFlow<CalibrationMetrics?>(null)
    private var sessionId: String? = null
    private var captureCounter = 0
    override val sessionState: StateFlow<CalibrationSessionState>
        get() = _state.asStateFlow()
    override val metrics: StateFlow<CalibrationMetrics?>
        get() = metricsState.asStateFlow()

    init {
        scope.launch {
            metricsStore.metrics.collect { metricsState.value = it }
        }
    }

    override suspend fun configure(pattern: CalibrationPatternConfig, requiredPairs: Int) {
        require(requiredPairs >= 3) { "requiredPairs must be at least 3" }
        stateMutex.withLock {
            _state.value = _state.value.copy(
                pattern = pattern,
                requiredPairs = requiredPairs.coerceAtLeast(3),
                infoMessage = "Pattern updated to ${pattern.rows}x${pattern.cols}",
                errorMessage = null
            )
        }
    }

    override suspend fun beginSession() {
        stateMutex.withLock {
            if (_state.value.active) return
            ensureOpenCvLoaded()
            controller.ensureReady()
            captureCounter = 0
            val newSessionId = createSessionId()
            sessionId = newSessionId
            storage.createSessionDirectory(newSessionId)
            _state.value = _state.value.copy(
                active = true,
                captures = emptyList(),
                isProcessing = false,
                lastResult = null,
                infoMessage = "Calibration session $newSessionId started",
                errorMessage = null
            )
        }
    }

    override suspend fun capturePair(): CalibrationCapture {
        val currentSession = sessionId ?: throw IllegalStateException("Calibration session not started")
        val pattern = _state.value.pattern
        val captureId = "capture-${captureCounter++}"
        val sessionDir = storage.sessionDirectory(currentSession)
        val rgbFile = File(sessionDir, "${captureId}_rgb.png")
        val thermalFile = File(sessionDir, "${captureId}_thermal.png")
        val framePair = controller.capturePair(pattern)
        storage.persistBitmap(framePair.rgb.bitmap, rgbFile)
        storage.persistBitmap(framePair.thermal.bitmap, thermalFile)
        val capture = CalibrationCapture(
            id = captureId,
            rgb = CalibrationImageDescriptor(
                path = rgbFile.absolutePath,
                width = framePair.rgb.bitmap.width,
                height = framePair.rgb.bitmap.height,
                capturedAt = framePair.rgb.capturedAt
            ),
            thermal = CalibrationImageDescriptor(
                path = thermalFile.absolutePath,
                width = framePair.thermal.bitmap.width,
                height = framePair.thermal.bitmap.height,
                capturedAt = framePair.thermal.capturedAt
            )
        )
        stateMutex.withLock {
            _state.value = _state.value.copy(
                captures = _state.value.captures + capture,
                infoMessage = "Captured pair ${_state.value.captures.size + 1}",
                errorMessage = null
            )
        }
        return capture
    }

    override suspend fun removeCapture(id: String) {
        val currentSession = sessionId ?: return
        stateMutex.withLock {
            val capture = _state.value.captures.find { it.id == id } ?: return
            runCatching {
                File(capture.rgb.path).delete()
                File(capture.thermal.path).delete()
            }
            _state.value = _state.value.copy(
                captures = _state.value.captures.filterNot { it.id == id },
                infoMessage = "Removed capture $id"
            )
        }
        storage.createSessionDirectory(currentSession)
    }

    override suspend fun computeAndPersist(): CalibrationResult = calculationMutex.withLock {
        val currentSession = sessionId ?: throw IllegalStateException("Calibration session not started")
        val captures = _state.value.captures
        val required = _state.value.requiredPairs
        if (captures.size < required) {
            throw IllegalStateException("Need at least $required capture pairs before computing calibration")
        }
        stateMutex.withLock {
            _state.value =
                _state.value.copy(isProcessing = true, infoMessage = "Computing calibration...", errorMessage = null)
        }
        val result = runCatching {
            withContext(Dispatchers.Default) {
                performCalibration(_state.value.pattern, captures)
            }
        }.onFailure { error ->
            stateMutex.withLock {
                _state.value = _state.value.copy(
                    isProcessing = false,
                    errorMessage = error.message ?: "Calibration failed",
                    infoMessage = null
                )
            }
        }.getOrThrow()
        storage.writeResult(currentSession, result)
        val metricsSnapshot = CalibrationMetrics(
            generatedAt = result.generatedAt,
            meanReprojectionError = result.meanReprojectionError,
            maxReprojectionError = result.perViewErrors.maxOrNull() ?: result.meanReprojectionError,
            usedPairs = result.usedPairs,
            requiredPairs = result.requiredPairs
        )
        metricsState.value = metricsSnapshot
        metricsStore.update(result)
        stateMutex.withLock {
            val warning = if (metricsSnapshot.maxReprojectionError > 1.0) {
                "High reprojection error detected (max ${"%.3f".format(metricsSnapshot.maxReprojectionError)} px). Consider recapturing images."
            } else {
                null
            }
            _state.value = _state.value.copy(
                isProcessing = false,
                lastResult = result,
                infoMessage = "Calibration complete (RMS ${"%.4f".format(result.meanReprojectionError)})",
                errorMessage = warning
            )
        }
        return result
    }

    override suspend fun loadLatestResult(): CalibrationResult? = storage.loadLatestResult().also { result ->
        if (result != null) {
            stateMutex.withLock {
                _state.value = _state.value.copy(lastResult = result, infoMessage = "Loaded cached calibration")
            }
        }
    }

    override suspend fun clearSession() {
        val previousSession = sessionId
        sessionId = null
        stateMutex.withLock {
            _state.value = initialState()
        }
        scope.launch {
            controller.shutdown()
            previousSession?.let { storage.deleteSessionDirectory(it) }
        }
    }

    private suspend fun performCalibration(
        pattern: CalibrationPatternConfig,
        captures: List<CalibrationCapture>
    ): CalibrationResult {
        val patternSize = Size(pattern.cols.toDouble(), pattern.rows.toDouble())
        val objectPoints = ArrayList<MatOfPoint3f>()
        val rgbPoints = ArrayList<MatOfPoint2f>()
        val thermalPoints = ArrayList<MatOfPoint2f>()
        val rgbSize = Size(
            captures.first().rgb.width.toDouble(),
            captures.first().rgb.height.toDouble()
        )
        val thermalSize = Size(
            captures.first().thermal.width.toDouble(),
            captures.first().thermal.height.toDouble()
        )
        captures.forEach { capture ->
            val rgbGray = loadGrayMat(capture.rgb.path)
            val thermalGray = loadGrayMat(capture.thermal.path)
            val rgbCorners = MatOfPoint2f()
            val thermalCorners = MatOfPoint2f()
            val chessboardFlags =
                Calib3d.CALIB_CB_ADAPTIVE_THRESH or Calib3d.CALIB_CB_NORMALIZE_IMAGE or Calib3d.CALIB_CB_FAST_CHECK
            val foundRgb = Calib3d.findChessboardCorners(rgbGray, patternSize, rgbCorners, chessboardFlags)
            val foundThermal = Calib3d.findChessboardCorners(thermalGray, patternSize, thermalCorners, chessboardFlags)
            if (!foundRgb || !foundThermal) {
                rgbGray.release()
                thermalGray.release()
                rgbCorners.release()
                thermalCorners.release()
                throw IllegalStateException("Failed to detect calibration pattern in capture ${capture.id}")
            }
            refineCorners(rgbGray, rgbCorners)
            refineCorners(thermalGray, thermalCorners)
            val objectPointMat = createObjectPoints(pattern)
            objectPoints.add(objectPointMat)
            rgbPoints.add(rgbCorners)
            thermalPoints.add(thermalCorners)
            rgbGray.release()
            thermalGray.release()
        }
        val rgbResult = calibrateSingleCamera(objectPoints, rgbPoints, rgbSize)
        val thermalResult = calibrateSingleCamera(objectPoints, thermalPoints, thermalSize)
        val stereo = performStereoCalibration(
            objectPoints = objectPoints,
            rgbPoints = rgbPoints,
            thermalPoints = thermalPoints,
            rgbSize = rgbSize,
            rgbIntrinsic = rgbResult,
            thermalIntrinsic = thermalResult
        )
        val perViewErrors = computeAveragePerViewErrors(
            objectPoints,
            rgbPoints,
            thermalPoints,
            rgbResult,
            thermalResult
        )
        val meanRms = (rgbResult.rms + thermalResult.rms + stereo.rms) / 3.0
        return CalibrationResult(
            generatedAt = nowInstant(),
            pattern = pattern,
            requiredPairs = _state.value.requiredPairs,
            usedPairs = captures.size,
            meanReprojectionError = meanRms,
            perViewErrors = perViewErrors,
            rgbIntrinsics = rgbResult.toParameters(),
            thermalIntrinsics = thermalResult.toParameters(),
            extrinsic = ExtrinsicTransform(
                rotationMatrix = stereo.rotationMatrixValues,
                translation = stereo.translationValues
            )
        )
    }

    private fun loadGrayMat(path: String): Mat {
        val bitmap = BitmapFactory.decodeFile(path) ?: throw IllegalStateException("Unable to load image $path")
        val mat = Mat()
        Utils.bitmapToMat(bitmap, mat)
        val gray = Mat()
        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_RGBA2GRAY)
        mat.release()
        bitmap.recycle()
        return gray
    }

    private fun refineCorners(image: Mat, corners: MatOfPoint2f) {
        Imgproc.cornerSubPix(
            image,
            corners,
            Size(11.0, 11.0),
            Size(-1.0, -1.0),
            org.opencv.core.TermCriteria(
                org.opencv.core.TermCriteria.EPS or org.opencv.core.TermCriteria.COUNT,
                30,
                0.001
            )
        )
    }

    private data class SingleCalibration(
        val rms: Double,
        val cameraMatrix: Mat,
        val distortion: MatOfDouble,
        val rvecs: List<Mat>,
        val tvecs: List<Mat>,
        val imageSize: Size
    ) {
        fun toParameters(): CameraIntrinsicParameters {
            val fx = cameraMatrix.get(0, 0)[0]
            val fy = cameraMatrix.get(1, 1)[0]
            val skew = cameraMatrix.get(0, 1)[0]
            val cx = cameraMatrix.get(0, 2)[0]
            val cy = cameraMatrix.get(1, 2)[0]
            val radial = mutableListOf<Double>()
            val tangential = mutableListOf<Double>()
            val indices = listOf(0, 1, 4, 5, 6, 7)
            indices.forEach { idx ->
                if (idx < distortion.total()) {
                    radial += distortion.get(0, idx)[0]
                }
            }
            if (distortion.total() >= 3) {
                tangential += distortion.get(0, 2)[0]
            }
            if (distortion.total() >= 4) {
                tangential += distortion.get(0, 3)[0]
            }
            return CameraIntrinsicParameters(
                fx = fx,
                fy = fy,
                skew = skew,
                cx = cx,
                cy = cy,
                radial = radial,
                tangential = tangential,
                width = imageSize.width.toInt(),
                height = imageSize.height.toInt()
            )
        }
    }

    private data class StereoCalibration(
        val rms: Double,
        val rotationMatrixValues: List<Double>,
        val translationValues: List<Double>
    )

    private fun calibrateSingleCamera(
        objectPoints: List<MatOfPoint3f>,
        imagePoints: List<MatOfPoint2f>,
        imageSize: Size
    ): SingleCalibration {
        val cameraMatrix = Mat.eye(3, 3, CvType.CV_64F)
        val distortion = MatOfDouble()
        val rvecs = mutableListOf<Mat>()
        val tvecs = mutableListOf<Mat>()
        val flags = Calib3d.CALIB_RATIONAL_MODEL or Calib3d.CALIB_FIX_K4 or Calib3d.CALIB_FIX_K5
        val rms = Calib3d.calibrateCamera(
            objectPoints.map { it as Mat },
            imagePoints.map { it as Mat },
            imageSize,
            cameraMatrix,
            distortion,
            rvecs,
            tvecs,
            flags,
            org.opencv.core.TermCriteria(org.opencv.core.TermCriteria.EPS, 100, 1e-6)
        )
        return SingleCalibration(
            rms = rms,
            cameraMatrix = cameraMatrix,
            distortion = distortion,
            rvecs = rvecs,
            tvecs = tvecs,
            imageSize = imageSize
        )
    }

    private fun performStereoCalibration(
        objectPoints: List<MatOfPoint3f>,
        rgbPoints: List<MatOfPoint2f>,
        thermalPoints: List<MatOfPoint2f>,
        rgbSize: Size,
        rgbIntrinsic: SingleCalibration,
        thermalIntrinsic: SingleCalibration
    ): StereoCalibration {
        val rotation = Mat()
        val translation = Mat()
        val essential = Mat()
        val fundamental = Mat()
        val flags = Calib3d.CALIB_FIX_INTRINSIC
        val rms = Calib3d.stereoCalibrate(
            objectPoints.map { it as Mat },
            rgbPoints.map { it as Mat },
            thermalPoints.map { it as Mat },
            rgbIntrinsic.cameraMatrix,
            rgbIntrinsic.distortion,
            thermalIntrinsic.cameraMatrix,
            thermalIntrinsic.distortion,
            rgbSize,
            rotation,
            translation,
            essential,
            fundamental,
            flags,
            org.opencv.core.TermCriteria(org.opencv.core.TermCriteria.EPS, 100, 1e-6)
        )
        val rotationValues = DoubleArray(9)
        for (row in 0 until 3) {
            for (col in 0 until 3) {
                rotationValues[row * 3 + col] = rotation.get(row, col)[0]
            }
        }
        val translationValues = listOf(
            translation.get(0, 0)[0],
            translation.get(1, 0)[0],
            translation.get(2, 0)[0]
        )
        rotation.release()
        translation.release()
        essential.release()
        fundamental.release()
        return StereoCalibration(
            rms = rms,
            rotationMatrixValues = rotationValues.toList(),
            translationValues = translationValues
        )
    }

    private fun computeAveragePerViewErrors(
        objectPoints: List<MatOfPoint3f>,
        rgbPoints: List<MatOfPoint2f>,
        thermalPoints: List<MatOfPoint2f>,
        rgbCalibration: SingleCalibration,
        thermalCalibration: SingleCalibration
    ): List<Double> {
        val errors = mutableListOf<Double>()
        for (index in objectPoints.indices) {
            val objectMat = objectPoints[index]
            val rgbError = computeReprojectionError(
                objectMat,
                rgbPoints[index],
                rgbCalibration.cameraMatrix,
                rgbCalibration.distortion,
                rgbCalibration.rvecs[index],
                rgbCalibration.tvecs[index]
            )
            val thermalError = computeReprojectionError(
                objectMat,
                thermalPoints[index],
                thermalCalibration.cameraMatrix,
                thermalCalibration.distortion,
                thermalCalibration.rvecs[index],
                thermalCalibration.tvecs[index]
            )
            errors += (rgbError + thermalError) / 2.0
        }
        return errors
    }

    private fun computeReprojectionError(
        objectPoints: MatOfPoint3f,
        imagePoints: MatOfPoint2f,
        cameraMatrix: Mat,
        distortion: MatOfDouble,
        rvec: Mat,
        tvec: Mat
    ): Double {
        val projected = MatOfPoint2f()
        Calib3d.projectPoints(objectPoints, rvec, tvec, cameraMatrix, distortion, projected)
        val originalPoints = imagePoints
        val projectedArray = projected.toArray()
        val originalArray = originalPoints.toArray()
        var errorSum = 0.0
        for (i in projectedArray.indices) {
            val dx = projectedArray[i].x - originalArray[i].x
            val dy = projectedArray[i].y - originalArray[i].y
            errorSum += dx.pow(2) + dy.pow(2)
        }
        projected.release()
        return kotlin.math.sqrt(errorSum / projectedArray.size)
    }

    private fun createObjectPoints(pattern: CalibrationPatternConfig): MatOfPoint3f {
        val points = ArrayList<Point3>(pattern.rows * pattern.cols)
        for (row in 0 until pattern.rows) {
            for (col in 0 until pattern.cols) {
                points += Point3(
                    col * pattern.squareSizeMeters,
                    row * pattern.squareSizeMeters,
                    0.0
                )
            }
        }
        return MatOfPoint3f(*points.toTypedArray())
    }

    private fun ensureOpenCvLoaded() {
        if (OPEN_CV_READY.get()) return
        synchronized(OPEN_CV_READY) {
            if (OPEN_CV_READY.get()) return
            val initialized = OpenCVLoader.initDebug()
            if (!initialized) {
                throw IllegalStateException("Unable to load OpenCV native libraries")
            }
            OPEN_CV_READY.set(true)
        }
    }

    private fun createSessionId(): String {
        val timestamp = nowInstant().toEpochMilliseconds()
        return "cal-${timestamp}-${UUID.randomUUID().toString().take(8)}"
    }

    private fun initialState(): CalibrationSessionState = CalibrationSessionState(
        active = false,
        pattern = CalibrationDefaults.Pattern,
        requiredPairs = CalibrationDefaults.RequiredPairs,
        captures = emptyList(),
        isProcessing = false,
        lastResult = null,
        infoMessage = null,
        errorMessage = null
    )

    companion object {
        private val OPEN_CV_READY = AtomicBoolean(false)
    }

    @VisibleForTesting
    internal fun resetForTesting() {
        sessionId = null
        captureCounter = 0
        OPEN_CV_READY.set(false)
        runCatching { runBlocking { controller.shutdown() } }
        _state.value = initialState()
    }
}




