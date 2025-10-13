package com.buccancs.ui.calibration

import com.buccancs.domain.model.CalibrationCapture
import com.buccancs.domain.model.CalibrationDefaults
import com.buccancs.domain.model.CalibrationImageDescriptor
import com.buccancs.domain.model.CalibrationMetrics
import com.buccancs.domain.model.CalibrationPatternConfig
import com.buccancs.domain.model.CalibrationResult
import com.buccancs.domain.model.CalibrationSessionState
import com.buccancs.domain.repository.CalibrationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CalibrationViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)
    private lateinit var repository: FakeCalibrationRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repository = FakeCalibrationRepository()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state prompts configuration`() = scope.runTest {
        val viewModel = CalibrationViewModel(repository)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals(CalibrationWizardStep.Configure, uiState.wizardStep)
        assertTrue(uiState.actionHints.any { it.contains("checkerboard", ignoreCase = true) })
    }

    @Test
    fun `capture step reflects remaining pairs`() = scope.runTest {
        val viewModel = CalibrationViewModel(repository)
        advanceUntilIdle()

        val captures = (0 until 4).map { index -> capture("capture-$index") }
        repository.updateSession(
            repository.sessionState.value.copy(
                active = true,
                captures = captures,
                requiredPairs = 6
            )
        )
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals(CalibrationWizardStep.Capture, uiState.wizardStep)
        assertTrue(uiState.guidanceMessage.contains("Capture 2 additional pair"))
        assertTrue(uiState.actionHints.any { it.contains("tilt", ignoreCase = true) })
    }

    @Test
    fun `low confidence metrics trigger remediation hint`() = scope.runTest {
        val viewModel = CalibrationViewModel(repository)
        advanceUntilIdle()

        val result = sampleResult(meanError = 1.2, maxError = 2.0, usedPairs = 10)
        repository.updateSession(
            repository.sessionState.value.copy(
                lastResult = result,
                captures = emptyList(),
                active = false
            )
        )
        repository.updateMetrics(
            CalibrationMetrics(
                generatedAt = result.generatedAt,
                meanReprojectionError = 1.2,
                maxReprojectionError = 2.0,
                usedPairs = 10,
                requiredPairs = 10
            )
        )
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals(CalibrationWizardStep.Review, uiState.wizardStep)
        assertTrue(uiState.isLowConfidence)
        assertTrue(uiState.actionHints.any {
            it.contains("recalibration", ignoreCase = true) || it.contains(
                "recapture",
                ignoreCase = true
            )
        })
    }

    private fun capture(id: String): CalibrationCapture = CalibrationCapture(
        id = id,
        rgb = CalibrationImageDescriptor(
            path = "rgb/$id.png",
            width = 1920,
            height = 1080,
            capturedAt = Clock.System.now()
        ),
        thermal = CalibrationImageDescriptor(
            path = "thermal/$id.png",
            width = 256,
            height = 192,
            capturedAt = Clock.System.now()
        )
    )

    companion object {
        fun sampleResult(meanError: Double, maxError: Double, usedPairs: Int): CalibrationResult =
            CalibrationResult(
                generatedAt = Instant.fromEpochMilliseconds(Clock.System.now().toEpochMilliseconds()),
                pattern = CalibrationDefaults.Pattern,
                requiredPairs = usedPairs,
                usedPairs = usedPairs,
                meanReprojectionError = meanError,
                perViewErrors = listOf(maxError),
                rgbIntrinsics = dummyIntrinsics(),
                thermalIntrinsics = dummyIntrinsics(),
                extrinsic = com.buccancs.domain.model.ExtrinsicTransform(
                    rotationMatrix = listOf(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0),
                    translation = listOf(0.0, 0.0, 0.0)
                )
            )

        private fun dummyIntrinsics() = com.buccancs.domain.model.CameraIntrinsicParameters(
            fx = 1.0,
            fy = 1.0,
            skew = 0.0,
            cx = 0.5,
            cy = 0.5,
            radial = listOf(0.0, 0.0),
            tangential = listOf(0.0, 0.0),
            width = 1920,
            height = 1080
        )
    }

    private class FakeCalibrationRepository : CalibrationRepository {
        private val _sessionState = MutableStateFlow(
            CalibrationSessionState(
                active = false,
                pattern = CalibrationDefaults.Pattern,
                requiredPairs = CalibrationDefaults.RequiredPairs,
                captures = emptyList(),
                isProcessing = false,
                lastResult = null,
                infoMessage = null,
                errorMessage = null
            )
        )
        private val _metrics = MutableStateFlow<CalibrationMetrics?>(null)

        override val sessionState: StateFlow<CalibrationSessionState> = _sessionState
        override val metrics: StateFlow<CalibrationMetrics?> = _metrics

        override suspend fun configure(pattern: CalibrationPatternConfig, requiredPairs: Int) {
            _sessionState.value = _sessionState.value.copy(pattern = pattern, requiredPairs = requiredPairs)
        }

        override suspend fun beginSession() {
            _sessionState.value = _sessionState.value.copy(active = true, captures = emptyList(), isProcessing = false)
        }

        override suspend fun capturePair(): CalibrationCapture = CalibrationCapture(
            id = "synthetic",
            rgb = CalibrationImageDescriptor(
                path = "rgb/synthetic.png",
                width = 1920,
                height = 1080,
                capturedAt = Clock.System.now()
            ),
            thermal = CalibrationImageDescriptor(
                path = "thermal/synthetic.png",
                width = 256,
                height = 192,
                capturedAt = Clock.System.now()
            )
        )

        override suspend fun removeCapture(id: String) {
            _sessionState.value = _sessionState.value.copy(
                captures = _sessionState.value.captures.filterNot { it.id == id }
            )
        }

        override suspend fun computeAndPersist(): CalibrationResult =
            CalibrationViewModelTest.sampleResult(0.2, 0.4, _sessionState.value.captures.size)

        override suspend fun loadLatestResult(): CalibrationResult? = _sessionState.value.lastResult

        override suspend fun clearSession() {
            _sessionState.value = _sessionState.value.copy(active = false, captures = emptyList())
        }

        fun updateSession(state: CalibrationSessionState) {
            _sessionState.value = state
        }

        fun updateMetrics(metrics: CalibrationMetrics?) {
            _metrics.value = metrics
        }
    }
}
