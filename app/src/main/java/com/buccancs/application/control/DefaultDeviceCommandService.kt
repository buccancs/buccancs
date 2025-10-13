package com.buccancs.application.control

import android.util.Log
import com.buccancs.BuildConfig
import com.buccancs.application.time.TimeSyncService
import com.buccancs.control.commands.CommandSerialization
import com.buccancs.control.commands.DeviceCommandPayload
import com.buccancs.control.commands.EventMarkerCommandPayload
import com.buccancs.control.commands.StimulusCommandPayload
import com.buccancs.control.commands.SyncSignalCommandPayload
import com.buccancs.data.control.CommandClient
import com.buccancs.data.orchestration.DeviceIdentityProvider
import com.buccancs.data.orchestration.server.ControlServer
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.DeviceEventType
import com.buccancs.domain.repository.DeviceEventRepository
import com.buccancs.util.nowInstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultDeviceCommandService @Inject constructor(
    private val commandClient: CommandClient,
    @ApplicationScope private val scope: CoroutineScope,
    private val deviceEventRepository: DeviceEventRepository,
    private val timeSyncService: TimeSyncService,
    private val controlServer: ControlServer,
    private val identityProvider: DeviceIdentityProvider
) : DeviceCommandService {
    private val _lastCommand = MutableStateFlow<DeviceCommandPayload?>(null)
    override val lastCommand: StateFlow<DeviceCommandPayload?> = _lastCommand.asStateFlow()
    private val _commands = MutableSharedFlow<DeviceCommandPayload>(extraBufferCapacity = 64)
    override val commands: SharedFlow<DeviceCommandPayload> = _commands.asSharedFlow()
    private val _syncSignals = MutableSharedFlow<SyncSignalCommandPayload>(extraBufferCapacity = 32)
    override val syncSignals: SharedFlow<SyncSignalCommandPayload> = _syncSignals.asSharedFlow()

    init {
        scope.launch {
            commandClient.commands.collect { payload ->
                onCommandReceived(payload)
            }
        }
        scope.launch {
            startLocalControlServer()
        }
    }

    override suspend fun acknowledge(commandId: String, success: Boolean, message: String?) {
        commandClient.reportReceipt(commandId, success, message)
    }

    override fun issueLocalToken(
        sessionId: String,
        ttlMillis: Long
    ): DeviceCommandService.ControlToken {
        val issued = controlServer.issueToken(sessionId, ttlMillis)
        return DeviceCommandService.ControlToken(
            value = issued.value,
            expiresAt = issued.expiresAt
        )
    }

    private suspend fun onCommandReceived(payload: DeviceCommandPayload) {
        _lastCommand.value = payload
        _commands.emit(payload)
        when (payload) {
            is SyncSignalCommandPayload -> handleSync(payload)
            is EventMarkerCommandPayload -> handleEventMarker(payload)
            is StimulusCommandPayload -> handleStimulus(payload)
            else -> handleGeneric(payload)
        }
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
                    receivedAt = nowInstant()
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
                    receivedAt = nowInstant()
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
                    receivedAt = nowInstant()
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
                    receivedAt = nowInstant()
                )
            )
            Log.i(TAG, "Command ${payload.commandId} executed")
        }
    }

    private suspend fun startLocalControlServer() {
        val config = ControlServer.Config(
            port = BuildConfig.CONTROL_SERVER_PORT,
            sessionId = identityProvider.deviceId()
        )
        runCatching { controlServer.start(config) }
            .onFailure { error ->
                Log.w(
                    TAG,
                    "Unable to start local control server on port ${config.port}: ${error.message}",
                    error
                )
            }
        controlServer.events.collect { event ->
            if (event.type != ControlServer.EVENT_TYPE_COMMAND) return@collect
            val payload = runCatching {
                CommandSerialization.json.decodeFromString(
                    DeviceCommandPayload.serializer(),
                    event.detailJson
                )
            }.getOrElse { error ->
                Log.w(TAG, "Unable to decode local command ${event.eventId}: ${error.message}", error)
                return@collect
            }
            onCommandReceived(payload)
        }
    }

    private suspend fun delayForExecution(executeEpochMs: Long) {
        val offset = timeSyncService.status.value.offsetMillis
        val clampedOffset = offset.coerceIn(-MAX_OFFSET_ADJUSTMENT_MS, MAX_OFFSET_ADJUSTMENT_MS)
        val adjustedNow = System.currentTimeMillis() + clampedOffset
        val delayMs = executeEpochMs - adjustedNow
        if (delayMs > 0) {
            delay(delayMs)
        }
    }

    private suspend fun recordEvent(event: DeviceEvent) {
        runCatching { deviceEventRepository.record(event) }
            .onFailure { ex -> Log.w(TAG, "Unable to record event ${event.id}: ${ex.message}", ex) }
    }

    private companion object {
        private const val TAG = "DeviceCommandService"
        private const val MAX_OFFSET_ADJUSTMENT_MS = 60_000L
    }
}

