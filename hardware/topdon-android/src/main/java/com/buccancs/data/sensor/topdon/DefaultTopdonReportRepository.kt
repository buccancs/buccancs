package com.buccancs.data.sensor.topdon

import com.buccancs.domain.model.TopdonReportDraft
import com.buccancs.domain.model.TopdonReportMetadata
import com.buccancs.domain.model.TopdonSettings
import com.buccancs.domain.repository.TopdonReportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DefaultTopdonReportRepository @Inject constructor() : TopdonReportRepository {
    private val mutex =
        Mutex()
    private val draftState =
        MutableStateFlow(TopdonReportDraft())

    override val draft: StateFlow<TopdonReportDraft> =
        draftState

    override suspend fun updateMaterial(material: String?) {
        updateDraft {
            it.copy(material = material?.takeIf { value -> value.isNotBlank() })
        }
    }

    override suspend fun updateNotes(notes: String?) {
        updateDraft {
            it.copy(notes = notes?.takeIf { value -> value.isNotBlank() })
        }
    }

    override suspend fun updateEnvironment(
        temperatureCelsius: Double,
        humidityPercent: Double
    ) {
        updateDraft {
            it.copy(
                ambientTemperatureCelsius = temperatureCelsius,
                ambientHumidityPercent = humidityPercent
            )
        }
    }

    override suspend fun snapshot(
        sessionId: String,
        settings: TopdonSettings,
        capturedAtMillis: Long
    ): TopdonReportMetadata =
        mutex.withLock {
            val current =
                draftState.value
            TopdonReportMetadata(
                sessionId = sessionId,
                capturedAtMillis = capturedAtMillis,
                temperatureUnit = settings.temperatureUnit,
                ambientTemperatureCelsius = current.ambientTemperatureCelsius,
                ambientHumidityPercent = current.ambientHumidityPercent,
                material = current.material,
                notes = current.notes
            )
        }

    override suspend fun clear() {
        updateDraft { TopdonReportDraft() }
    }

    private suspend fun updateDraft(
        transform: (TopdonReportDraft) -> TopdonReportDraft
    ) {
        mutex.withLock {
            draftState.value =
                transform(draftState.value)
        }
    }
}
