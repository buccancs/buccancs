package com.buccancs.domain.repository
import com.buccancs.domain.model.TimeSyncStatus
import kotlinx.coroutines.flow.StateFlow
interface TimeSyncRepository {
    val status: StateFlow<TimeSyncStatus>
    suspend fun start()
    suspend fun forceSync(): TimeSyncStatus
}
