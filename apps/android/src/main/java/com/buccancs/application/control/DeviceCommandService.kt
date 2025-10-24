package com.buccancs.application.control

import com.buccancs.control.commands.DeviceCommandPayload
import com.buccancs.control.commands.SyncSignalCommandPayload
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.time.Instant

interface DeviceCommandService {
    val lastCommand: StateFlow<DeviceCommandPayload?>
    val commands: SharedFlow<DeviceCommandPayload>
    val syncSignals: SharedFlow<SyncSignalCommandPayload>

    suspend fun acknowledge(
        commandId: String,
        success: Boolean,
        message: String?
    )

    fun issueLocalToken(
        sessionId: String,
        ttlMillis: Long = DEFAULT_TOKEN_TTL_MS
    ): ControlToken

    data class ControlToken(
        val value: String,
        val expiresAt: Instant
    )

    companion object {
        const val DEFAULT_TOKEN_TTL_MS: Long =
            60_000L
    }
}
