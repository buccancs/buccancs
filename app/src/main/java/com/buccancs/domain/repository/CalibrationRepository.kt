package com.buccancs.domain.repository

import com.buccancs.domain.model.CalibrationCapture
import com.buccancs.domain.model.CalibrationPatternConfig
import com.buccancs.domain.model.CalibrationResult
import com.buccancs.domain.model.CalibrationSessionState
import kotlinx.coroutines.flow.StateFlow

interface CalibrationRepository {
    val sessionState: StateFlow<CalibrationSessionState>

    suspend fun configure(pattern: CalibrationPatternConfig, requiredPairs: Int)
    suspend fun beginSession()
    suspend fun capturePair(): CalibrationCapture
    suspend fun removeCapture(id: String)
    suspend fun computeAndPersist(): CalibrationResult
    suspend fun loadLatestResult(): CalibrationResult?
    suspend fun clearSession()
}
