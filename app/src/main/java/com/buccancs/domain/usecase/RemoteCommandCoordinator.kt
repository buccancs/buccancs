package com.buccancs.domain.usecase

import android.util.Log
import com.buccancs.application.control.DeviceCommandService
import com.buccancs.application.time.TimeSyncService
import com.buccancs.control.commands.DeviceCommandPayload
import com.buccancs.control.commands.StartRecordingCommandPayload
import com.buccancs.control.commands.StopRecordingCommandPayload
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Instant

/**
 * Handle remote orchestrator commands with execution timing.
 * Extracted from MainViewModel to separate remote command coordination concerns.
 */
interface RemoteCommandCoordinator {
    val lastCommand: StateFlow<DeviceCommandPayload?>
    suspend fun handleCommand(payload: DeviceCommandPayload): Result<CommandHandlingResult>
}

data class CommandHandlingResult(
    val success: Boolean,
    val message: String?
)

@Singleton
class RemoteCommandCoordinatorImpl @Inject constructor(
    private val commandService: DeviceCommandService,
    private val sessionCoordinator: SessionCoordinator,
    private val timeSyncService: TimeSyncService
) : RemoteCommandCoordinator {

    override val lastCommand: StateFlow<DeviceCommandPayload?> = commandService.lastCommand

    override suspend fun handleCommand(payload: DeviceCommandPayload): Result<CommandHandlingResult> {
        return when (payload) {
            is StartRecordingCommandPayload -> handleStartRecordingCommand(payload)
            is StopRecordingCommandPayload -> handleStopRecordingCommand(payload)
            else -> Result.success(CommandHandlingResult(success = true, message = null))
        }
    }

    private suspend fun handleStartRecordingCommand(
        payload: StartRecordingCommandPayload
    ): Result<CommandHandlingResult> {
        Log.i(TAG, "Start recording command ${payload.commandId} for session ${payload.sessionId}")

        if (sessionCoordinator.sessionState.value.isBusy) {
            val result = CommandHandlingResult(success = false, message = "Device busy")
            acknowledgeCommand(payload.commandId, result)
            return Result.success(result)
        }

        var message: String? = null
        val success = try {
            awaitExecution(payload.executeEpochMs)
            
            // Update session coordinator with new session ID
            (sessionCoordinator as? SessionCoordinatorImpl)?.updateSessionId(payload.sessionId)
            
            val anchorInstant = payload.anchorEpochMs.takeIf { it > 0 }
                ?.let { Instant.fromEpochMilliseconds(it) }
            
            val result = sessionCoordinator.startSession(payload.sessionId, anchorInstant)
            result.isSuccess
        } catch (cancellation: CancellationException) {
            throw cancellation
        } catch (t: Throwable) {
            message = t.message ?: "Failed to start recording"
            false
        }

        val result = CommandHandlingResult(success, message)
        acknowledgeCommand(payload.commandId, result)
        return Result.success(result)
    }

    private suspend fun handleStopRecordingCommand(
        payload: StopRecordingCommandPayload
    ): Result<CommandHandlingResult> {
        Log.i(TAG, "Stop recording command ${payload.commandId} for session ${payload.sessionId}")

        if (sessionCoordinator.sessionState.value.isBusy) {
            val result = CommandHandlingResult(success = false, message = "Device busy")
            acknowledgeCommand(payload.commandId, result)
            return Result.success(result)
        }

        var message: String? = null
        val success = try {
            awaitExecution(payload.executeEpochMs)
            
            // Update session coordinator with session ID
            (sessionCoordinator as? SessionCoordinatorImpl)?.updateSessionId(payload.sessionId)
            
            val result = sessionCoordinator.stopSession()
            result.isSuccess
        } catch (cancellation: CancellationException) {
            throw cancellation
        } catch (t: Throwable) {
            message = t.message ?: "Failed to stop recording"
            false
        }

        val result = CommandHandlingResult(success, message)
        acknowledgeCommand(payload.commandId, result)
        return Result.success(result)
    }

    private suspend fun acknowledgeCommand(commandId: String, result: CommandHandlingResult) {
        runCatching {
            commandService.acknowledge(commandId, result.success, result.message)
        }.onFailure { ex ->
            Log.w(TAG, "Unable to acknowledge command $commandId: ${ex.message}", ex)
        }
    }

    private suspend fun awaitExecution(executeEpochMs: Long) {
        if (executeEpochMs <= 0L) return
        
        val offset = timeSyncService.status.value.offsetMillis
        val clampedOffset = offset.coerceIn(-MAX_OFFSET_ADJUSTMENT_MS, MAX_OFFSET_ADJUSTMENT_MS)
        val adjustedNow = System.currentTimeMillis() + clampedOffset
        val delayMs = executeEpochMs - adjustedNow
        
        if (delayMs > 0) {
            delay(delayMs)
        }
    }

    private companion object {
        private const val TAG = "RemoteCommandCoordinator"
        private const val MAX_OFFSET_ADJUSTMENT_MS = 60_000L
    }
}
