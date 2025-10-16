package com.buccancs.domain.repository

import com.buccancs.domain.model.*
import kotlinx.coroutines.flow.StateFlow

interface CalibrationRepository {
    val sessionState: StateFlow<CalibrationSessionState>
    val metrics: StateFlow<CalibrationMetrics?>
    suspend fun configure(pattern: CalibrationPatternConfig, requiredPairs: Int)
    suspend fun beginSession()
    suspend fun capturePair(): CalibrationCapture
    suspend fun removeCapture(id: String)
    suspend fun computeAndPersist(): CalibrationResult
    suspend fun loadLatestResult(): CalibrationResult?
    suspend fun clearSession()
}
