package com.buccancs.domain.repository

import com.buccancs.domain.model.TopdonReportDraft
import com.buccancs.domain.model.TopdonReportMetadata
import com.buccancs.domain.model.TopdonSettings
import kotlinx.coroutines.flow.StateFlow

interface TopdonReportRepository {
    val draft: StateFlow<TopdonReportDraft>

    suspend fun updateMaterial(material: String?)

    suspend fun updateNotes(notes: String?)

    suspend fun updateEnvironment(
        temperatureCelsius: Double,
        humidityPercent: Double
    )

    suspend fun snapshot(
        sessionId: String,
        settings: TopdonSettings,
        capturedAtMillis: Long = System.currentTimeMillis()
    ): TopdonReportMetadata

    suspend fun clear()
}
