package com.buccancs.data.control

import android.util.Log
import com.buccancs.control.commands.DeviceCommandPayload
import com.buccancs.control.commands.EventMarkerCommandPayload
import com.buccancs.control.commands.StimulusCommandPayload
import com.buccancs.control.commands.SyncSignalCommandPayload
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.DeviceEventType
import com.buccancs.domain.repository.DeviceEventRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Singleton
class CommandCoordinator @Inject constructor(
    private val commandClient: CommandClient,
    @ApplicationScope private val scope: CoroutineScope,
    private val deviceEventRepository: DeviceEventRepository
) {
    private val _lastCommand = MutableStateFlow<DeviceCommandPayload?>(null)
    val lastCommand: StateFlow<DeviceCommandPayload?> = _lastCommand.asStateFlow()
    private val _syncSignals = MutableSharedFlow<SyncSignalCommandPayload>(extraBufferCapacity = 32)
    val syncSignals: SharedFlow<SyncSignalCommandPayload> = _syncSignals.asSharedFlow()
    val commands: SharedFlow<DeviceCommandPayload> = commandClient.commands

    init {
        scope.launch {
            commandClient.commands.collect { payload ->
                _lastCommand.value = payload
                when (payload) {
                    is SyncSignalCommandPayload -> handleSync(payload)
                    is EventMarkerCommandPayload -> handleEventMarker(payload)
                    is StimulusCommandPayload -> handleStimulus(payload)
                    else -> handleGeneric(payload)
                }
            }
        }
    }

    suspend fun acknowledge(commandId: String, success: Boolean, message: String?) {
        commandClient.reportReceipt(commandId, success, message)
    }

    private fun handleSync(payload: SyncSignalCommandPayload) {
        scope.launch {
            delayForExecution(payload.executeEpochMs)
            recordEvent(
                DeviceEvent(
                    id = payload.commandId,
                    type = DeviceEventType.SYNC_SIGNAL,
                    label = payload.signalType,
                    scheduledAt = Instant.fromEpochMilliseconds(payload.executeEpochMs),
                    receivedAt = Clock.System.now()
                )
            )
            _syncSignals.emit(payload)
            Log.i(TAG, "Sync command ${payload.commandId} executed")
        }
    }

    private fun handleEventMarker(payload: EventMarkerCommandPayload) {
        scope.launch {
            delayForExecution(payload.executeEpochMs)
            recordEvent(
                DeviceEvent(
                    id = payload.markerId,
                    type = DeviceEventType.EVENT_MARKER,
                    label = payload.description,
                    scheduledAt = Instant.fromEpochMilliseconds(payload.executeEpochMs),
                    receivedAt = Clock.System.now()
                )
            )
            Log.i(TAG, "Event marker ${payload.markerId} acknowledged")
        }
    }

    private fun handleStimulus(payload: StimulusCommandPayload) {
        scope.launch {
            delayForExecution(payload.executeEpochMs)
            recordEvent(
                DeviceEvent(
                    id = payload.stimulusId,
                    type = DeviceEventType.STIMULUS,
                    label = payload.action,
                    scheduledAt = Instant.fromEpochMilliseconds(payload.executeEpochMs),
                    receivedAt = Clock.System.now()
                )
            )
            Log.i(TAG, "Stimulus ${payload.stimulusId} (${payload.action}) recorded")
        }
    }

    private fun handleGeneric(payload: DeviceCommandPayload) {
        scope.launch {
            delayForExecution(payload.executeEpochMs)
            recordEvent(
                DeviceEvent(
                    id = payload.commandId,
                    type = DeviceEventType.COMMAND,
                    label = payload::class.simpleName ?: "command",
                    scheduledAt = Instant.fromEpochMilliseconds(payload.executeEpochMs),
                    receivedAt = Clock.System.now()
                )
            )
            Log.i(TAG, "Command ${payload.commandId} executed")
        }
    }

    private suspend fun delayForExecution(executeEpochMs: Long) {
        val delayMs = executeEpochMs - System.currentTimeMillis()
        if (delayMs > 0) {
            delay(delayMs)
        }
    }

    private suspend fun recordEvent(event: DeviceEvent) {
        runCatching { deviceEventRepository.record(event) }
            .onFailure { ex -> Log.w(TAG, "Unable to record event ${event.id}: ${ex.message}", ex) }
    }

    private companion object {
        private const val TAG = "CommandCoordinator"
    }
}
