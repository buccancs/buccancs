package com.buccancs.data.sensor.connector

import com.buccancs.domain.model.PerformanceThrottleLevel

interface ThrottlableConnector {
    suspend fun applyThrottle(level: PerformanceThrottleLevel)
}
