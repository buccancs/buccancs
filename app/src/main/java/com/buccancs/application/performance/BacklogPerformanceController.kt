package com.buccancs.application.performance

import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.PerformanceThrottleLevel
import com.buccancs.domain.model.UploadBacklogLevel
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.SessionTransferRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BacklogPerformanceController @Inject constructor(
    sessionTransferRepository: SessionTransferRepository,
    private val sensorRepository: SensorRepository,
    @ApplicationScope private val scope: CoroutineScope
) {
    init {
        scope.launch {
            sessionTransferRepository.backlog.collectLatest { state ->
                val level = when (state.level) {
                    UploadBacklogLevel.NORMAL -> PerformanceThrottleLevel.NORMAL
                    UploadBacklogLevel.WARNING, UploadBacklogLevel.CRITICAL -> PerformanceThrottleLevel.CONSERVE
                }
                sensorRepository.applyPerformanceThrottle(level)
            }
        }
    }
}
