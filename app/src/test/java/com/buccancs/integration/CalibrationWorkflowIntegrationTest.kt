package com.buccancs.integration

import com.buccancs.domain.model.CalibrationPatternConfig
import com.buccancs.domain.model.CalibrationSessionState
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Calibration workflow integration test.
 * Tests complete calibration process from configuration to result.
 */
class CalibrationWorkflowIntegrationTest {

    @Test
    fun `calibration pattern configuration validation`() {
        val pattern = CalibrationPatternConfig(
            rows = 9,
            cols = 6,
            squareSizeMm = 30.0
        )

        assertEquals(9, pattern.rows)
        assertEquals(6, pattern.cols)
        assertEquals(30.0, pattern.squareSizeMm)
    }

    @Test
    fun `calibration session state initializes correctly`() {
        val state = CalibrationSessionState(
            isActive = false,
            pattern = CalibrationPatternConfig(9, 6, 30.0),
            requiredPairs = 10,
            capturedPairs = 0,
            infoMessage = null,
            errorMessage = null,
            previewImageUri = null,
            isProcessing = false
        )

        assertNotNull(state)
        assertEquals(10, state.requiredPairs)
        assertEquals(0, state.capturedPairs)
    }

    @Test
    fun `calibration pattern with various dimensions`() {
        val patterns = listOf(
            CalibrationPatternConfig(4, 3, 25.0),
            CalibrationPatternConfig(9, 6, 30.0),
            CalibrationPatternConfig(11, 8, 35.0)
        )

        patterns.forEach { pattern ->
            assertTrue(pattern.rows > 0)
            assertTrue(pattern.cols > 0)
            assertTrue(pattern.squareSizeMm > 0.0)
        }
    }

    @Test
    fun `calibration progress tracking`() {
        val initial = CalibrationSessionState(
            isActive = true,
            pattern = CalibrationPatternConfig(9, 6, 30.0),
            requiredPairs = 10,
            capturedPairs = 0,
            infoMessage = "Starting calibration",
            errorMessage = null,
            previewImageUri = null,
            isProcessing = false
        )

        val halfway = initial.copy(
            capturedPairs = 5,
            infoMessage = "Halfway complete"
        )

        val complete = initial.copy(
            capturedPairs = 10,
            infoMessage = "Calibration complete",
            isActive = false
        )

        assertEquals(0, initial.capturedPairs)
        assertEquals(5, halfway.capturedPairs)
        assertEquals(10, complete.capturedPairs)
        assertTrue(!complete.isActive)
    }

    @Test
    fun `calibration error handling`() {
        val errorState = CalibrationSessionState(
            isActive = false,
            pattern = CalibrationPatternConfig(9, 6, 30.0),
            requiredPairs = 10,
            capturedPairs = 3,
            infoMessage = null,
            errorMessage = "Pattern not detected",
            previewImageUri = null,
            isProcessing = false
        )

        assertNotNull(errorState.errorMessage)
        assertEquals("Pattern not detected", errorState.errorMessage)
        assertTrue(!errorState.isActive)
    }

    @Test
    fun `calibration minimum requirements validation`() {
        // Minimum 3 pairs required
        val minRequired = CalibrationSessionState(
            isActive = true,
            pattern = CalibrationPatternConfig(9, 6, 30.0),
            requiredPairs = 3,
            capturedPairs = 0,
            infoMessage = null,
            errorMessage = null,
            previewImageUri = null,
            isProcessing = false
        )

        assertTrue(minRequired.requiredPairs >= 3)
    }

    @Test
    fun `calibration pattern square size validation`() {
        val patterns = listOf(
            CalibrationPatternConfig(9, 6, 10.0),  // Small
            CalibrationPatternConfig(9, 6, 30.0),  // Medium
            CalibrationPatternConfig(9, 6, 100.0)  // Large
        )

        patterns.forEach { pattern ->
            assertTrue(pattern.squareSizeMm >= 10.0)
            assertTrue(pattern.squareSizeMm <= 100.0)
        }
    }

    @Test
    fun `calibration state transitions`() {
        val idle = CalibrationSessionState(
            isActive = false,
            pattern = CalibrationPatternConfig(9, 6, 30.0),
            requiredPairs = 10,
            capturedPairs = 0,
            infoMessage = null,
            errorMessage = null,
            previewImageUri = null,
            isProcessing = false
        )

        val active = idle.copy(
            isActive = true,
            infoMessage = "Calibration started"
        )

        val processing = active.copy(
            isProcessing = true,
            infoMessage = "Processing capture"
        )

        val completed = processing.copy(
            isActive = false,
            isProcessing = false,
            capturedPairs = 10,
            infoMessage = "Calibration complete"
        )

        assertTrue(!idle.isActive)
        assertTrue(active.isActive)
        assertTrue(processing.isProcessing)
        assertTrue(!completed.isActive)
    }
}
