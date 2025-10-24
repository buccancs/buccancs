package com.buccancs.domain.connector

import com.buccancs.domain.model.PerformanceThrottleLevel

interface ThrottlableConnector {
    suspend fun applyThrottle(
        level: PerformanceThrottleLevel
    )
}
