package com.buccancs.domain.time

import com.buccancs.domain.model.TimeSyncObservation
import com.buccancs.domain.model.TimeSyncStatus
import kotlinx.coroutines.flow.StateFlow

interface TimeSyncService {
    val status: StateFlow<TimeSyncStatus>
    val history: StateFlow<List<TimeSyncObservation>>
    suspend fun start()
    suspend fun forceSync(): TimeSyncStatus
}
