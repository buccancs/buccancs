package com.buccancs.desktop.data.repository

import com.buccancs.control.commands.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.merge
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.max

class CommandRepository {
    private val broadcastFlow = MutableSharedFlow<CommandDispatch>(
        replay = HISTORY_SIZE,
        extraBufferCapacity = HISTORY_SIZE,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val deviceFlows = ConcurrentHashMap<String, MutableSharedFlow<CommandDispatch>>()
    private val startStopCommands = ConcurrentHashMap<String, DeviceCommandPayload>()
    private val startStopOrder = ArrayDeque<String>()
    private val latestStartBySession = ConcurrentHashMap<String, StartRecordingCommandPayload>()
    private val latestStopBySession = ConcurrentHashMap<String, StopRecordingCommandPayload>()
    private val startStopLock = Any()
    fun observe(deviceId: String, includeBroadcast: Boolean = true): Flow<CommandDispatch> {
        return if (includeBroadcast) {
            merge(
                broadcastFlow.asSharedFlow(),
                deviceFlow(deviceId).asSharedFlow()
            )
        } else {
            deviceFlow(deviceId).asSharedFlow()
        }
    }

    suspend fun enqueueSyncSignal(
        sessionId: String,
        signalType: String,
        executeEpochMs: Long,
        targets: Set<String>,
        initiator: String?
    ) {
        val payload = SyncSignalCommandPayload(
            commandId = UUID.randomUUID().toString(),
            sessionId = sessionId,
            issuedEpochMs = System.currentTimeMillis(),
            executeEpochMs = executeEpochMs,
            signalType = signalType,
            initiator = initiator
        )
        dispatch(payload, targets)
    }

    suspend fun enqueueEventMarker(
        sessionId: String,
        markerId: String,
        description: String,
        timestampEpochMs: Long,
        targets: Set<String>,
        stimulusId: String? = null
    ) {
        val payload = EventMarkerCommandPayload(
            commandId = UUID.randomUUID().toString(),
            sessionId = sessionId,
            issuedEpochMs = System.currentTimeMillis(),
            executeEpochMs = timestampEpochMs,
            markerId = markerId,
            description = description,
            stimulusId = stimulusId
        )
        dispatch(payload, targets)
    }

    suspend fun enqueueStartRecording(
        sessionId: String,
        anchorEpochMs: Long,
        scheduledEpochMs: Long?,
        targets: Set<String> = emptySet()
    ) {
        val payload = StartRecordingCommandPayload(
            commandId = UUID.randomUUID().toString(),
            sessionId = sessionId,
            issuedEpochMs = System.currentTimeMillis(),
            executeEpochMs = scheduledEpochMs ?: anchorEpochMs,
            anchorEpochMs = anchorEpochMs,
            scheduledEpochMs = scheduledEpochMs
        )
        dispatch(payload, targets)
    }

    suspend fun enqueueStopRecording(
        sessionId: String,
        stopEpochMs: Long,
        targets: Set<String> = emptySet()
    ) {
        val payload = StopRecordingCommandPayload(
            commandId = UUID.randomUUID().toString(),
            sessionId = sessionId,
            issuedEpochMs = System.currentTimeMillis(),
            executeEpochMs = stopEpochMs
        )
        dispatch(payload, targets)
    }

    suspend fun enqueueStimulus(
        sessionId: String,
        stimulusId: String,
        action: String,
        executeEpochMs: Long,
        metadata: Map<String, String>,
        targets: Set<String>
    ) {
        val payload = StimulusCommandPayload(
            commandId = UUID.randomUUID().toString(),
            sessionId = sessionId,
            issuedEpochMs = System.currentTimeMillis(),
            executeEpochMs = executeEpochMs,
            stimulusId = stimulusId,
            action = action,
            metadata = metadata
        )
        dispatch(payload, targets)
    }

    fun findStartStopCommand(commandId: String): DeviceCommandPayload? =
        startStopCommands[commandId]

    suspend fun replayRecordingState(sessionId: String, deviceId: String) {
        val start: StartRecordingCommandPayload?
        val stop: StopRecordingCommandPayload?
        synchronized(startStopLock) {
            start = latestStartBySession[sessionId]
            stop = latestStopBySession[sessionId]
        }
        val candidate = when {
            start == null && stop == null -> return
            start != null && stop == null -> start
            start == null && stop != null -> stop
            start != null && stop != null -> if (start.executeEpochMs >= stop.executeEpochMs) start else stop
            else -> return
        }
        when (candidate) {
            is StartRecordingCommandPayload -> {
                val now = System.currentTimeMillis()
                val replayPayload = candidate.copy(
                    commandId = UUID.randomUUID().toString(),
                    issuedEpochMs = now,
                    executeEpochMs = max(now + REPLAY_DELAY_MS, candidate.executeEpochMs)
                )
                dispatch(replayPayload, setOf(deviceId))
            }

            is StopRecordingCommandPayload -> {
                val now = System.currentTimeMillis()
                val replayPayload = candidate.copy(
                    commandId = UUID.randomUUID().toString(),
                    issuedEpochMs = now,
                    executeEpochMs = now
                )
                dispatch(replayPayload, setOf(deviceId))
            }

            else -> {}
        }
    }

    private suspend fun dispatch(payload: DeviceCommandPayload, targets: Set<String>) {
        val json = CommandSerialization.json.encodeToString(DeviceCommandPayload.serializer(), payload)
        val base = CommandDispatch(
            commandId = payload.commandId,
            sessionId = payload.sessionId,
            issuedEpochMs = payload.issuedEpochMs,
            executeEpochMs = payload.executeEpochMs,
            commandJson = json,
            targetDeviceId = null
        )
        recordStartStop(payload)
        if (targets.isEmpty()) {
            emitCommand(broadcastFlow, base)
        } else {
            targets.forEach { deviceId ->
                val dispatch = base.copy(targetDeviceId = deviceId)
                emitCommand(deviceFlow(deviceId), dispatch)
            }
        }
    }

    private fun recordStartStop(payload: DeviceCommandPayload) {
        if (payload !is StartRecordingCommandPayload && payload !is StopRecordingCommandPayload) {
            return
        }
        synchronized(startStopLock) {
            startStopCommands[payload.commandId] = payload
            startStopOrder.addLast(payload.commandId)
            while (startStopOrder.size > START_STOP_HISTORY) {
                val oldest = startStopOrder.removeFirst()
                startStopCommands.remove(oldest)
            }
            when (payload) {
                is StartRecordingCommandPayload -> latestStartBySession[payload.sessionId] = payload
                is StopRecordingCommandPayload -> latestStopBySession[payload.sessionId] = payload
                else -> Unit
            }
        }
    }

    private suspend fun emitCommand(
        flow: MutableSharedFlow<CommandDispatch>,
        command: CommandDispatch
    ) {
        if (!flow.tryEmit(command)) {
            flow.emit(command)
        }
    }

    private fun deviceFlow(deviceId: String): MutableSharedFlow<CommandDispatch> =
        deviceFlows.computeIfAbsent(deviceId) {
            MutableSharedFlow(
                replay = HISTORY_SIZE,
                extraBufferCapacity = HISTORY_SIZE,
                onBufferOverflow = BufferOverflow.DROP_OLDEST
            )
        }

    data class CommandDispatch(
        val commandId: String,
        val sessionId: String,
        val issuedEpochMs: Long,
        val executeEpochMs: Long,
        val commandJson: String,
        val targetDeviceId: String?
    )

    private companion object {
        private const val HISTORY_SIZE = 32
        private const val START_STOP_HISTORY = 64
        private const val REPLAY_DELAY_MS = 200L
    }
}
