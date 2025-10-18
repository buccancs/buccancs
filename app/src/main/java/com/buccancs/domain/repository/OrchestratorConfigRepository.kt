package com.buccancs.domain.repository

import com.buccancs.domain.model.OrchestratorConfig
import kotlinx.coroutines.flow.StateFlow

interface OrchestratorConfigRepository {
    val config: StateFlow<OrchestratorConfig>
    suspend fun update(
        config: OrchestratorConfig
    )
}
