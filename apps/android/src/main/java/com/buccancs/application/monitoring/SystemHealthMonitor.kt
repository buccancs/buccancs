package com.buccancs.application.monitoring

import com.buccancs.application.time.TimeSyncService
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.domain.model.UploadBacklogLevel
import com.buccancs.domain.model.UploadBacklogState
import com.buccancs.domain.model.UploadRecoveryRecord
import com.buccancs.domain.model.UploadState
import com.buccancs.domain.repository.SessionTransferRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs

@Singleton
class SystemHealthMonitor @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    private val timeSyncService: TimeSyncService,
    private val transferRepository: SessionTransferRepository
) {
    private val _snapshot =
        MutableStateFlow(
            HealthSnapshot(
                emptyList()
            )
        )
    val snapshot: StateFlow<HealthSnapshot> =
        _snapshot.asStateFlow()
    private var monitorJob: Job? =
        null

    fun start() {
        if (monitorJob != null) return
        monitorJob =
            scope.launch {
                combine(
                    timeSyncService.status,
                    transferRepository.backlog,
                    transferRepository.recovery
                ) { status, backlog, recovery ->
                    evaluateHealth(
                        status,
                        backlog,
                        recovery
                    )
                }.collect { snapshot ->
                    _snapshot.value =
                        snapshot
                }
            }
    }

    internal fun evaluateHealth(
        status: TimeSyncStatus,
        backlog: UploadBacklogState,
        recovery: List<UploadRecoveryRecord>
    ): HealthSnapshot {
        val alerts =
            mutableListOf<HealthAlert>()

        val offsetMagnitude =
            abs(status.offsetMillis)
        if (status.quality == TimeSyncQuality.POOR ||
            offsetMagnitude > OFFSET_THRESHOLD_MS ||
            status.roundTripMillis > ROUND_TRIP_THRESHOLD_MS
        ) {
            alerts += HealthAlert(
                type = AlertType.TimeSync,
                severity = AlertSeverity.Warning,
                message = buildString {
                    append(
                        "Time synchronisation degraded: "
                    )
                    append(
                        "offset=${status.offsetMillis} ms, "
                    )
                    append(
                        "roundTrip=${status.roundTripMillis} ms, "
                    )
                    append(
                        "quality=${status.quality}"
                    )
                }
            )
        }

        if (backlog.level != UploadBacklogLevel.NORMAL) {
            alerts += HealthAlert(
                type = AlertType.UploadBacklog,
                severity = when (backlog.level) {
                    UploadBacklogLevel.WARNING -> AlertSeverity.Warning
                    UploadBacklogLevel.CRITICAL -> AlertSeverity.Severe
                    UploadBacklogLevel.NORMAL -> AlertSeverity.Info
                },
                message = backlog.message
                    ?: "Upload backlog level is ${backlog.level}"
            )
        }

        val failedRecoveries =
            recovery.count { it.state == UploadState.FAILED }
        if (failedRecoveries > 0) {
            alerts += HealthAlert(
                type = AlertType.UploadRecovery,
                severity = AlertSeverity.Warning,
                message = "$failedRecoveries upload recovery attempts failed"
            )
        }

        return HealthSnapshot(
            alerts = alerts
        )
    }

    companion object {
        private const val OFFSET_THRESHOLD_MS =
            5L
        private const val ROUND_TRIP_THRESHOLD_MS =
            50L
    }
}

data class HealthSnapshot(
    val alerts: List<HealthAlert>
) {
    val hasIssues: Boolean
        get() = alerts.any { it.severity != AlertSeverity.Info }
}

data class HealthAlert(
    val type: AlertType,
    val severity: AlertSeverity,
    val message: String
)

enum class AlertType {
    TimeSync,
    UploadBacklog,
    UploadRecovery
}

enum class AlertSeverity {
    Info,
    Warning,
    Severe
}

