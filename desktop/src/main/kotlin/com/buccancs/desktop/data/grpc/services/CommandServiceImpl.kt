package com.buccancs.desktop.data.grpc.services

import com.buccancs.control.CommandAck
import com.buccancs.control.CommandEnvelope
import com.buccancs.control.CommandReceipt
import com.buccancs.control.CommandServiceGrpcKt
import com.buccancs.control.CommandSubscribeRequest
import com.buccancs.control.ProtocolVersion
import com.buccancs.control.commandAck
import com.buccancs.control.commandEnvelope
import com.buccancs.control.commands.StartRecordingCommandPayload
import com.buccancs.control.commands.StopRecordingCommandPayload
import com.buccancs.desktop.data.repository.CommandRepository
import com.buccancs.desktop.data.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.slf4j.LoggerFactory

class CommandServiceImpl(
    private val commandRepository: CommandRepository,
    private val deviceRepository: DeviceRepository
) : CommandServiceGrpcKt.CommandServiceCoroutineImplBase() {
    private val logger =
        LoggerFactory.getLogger(
            CommandServiceImpl::class.java
        )

    override fun subscribeCommands(
        request: CommandSubscribeRequest
    ): Flow<CommandEnvelope> {
        logger.info(
            "Command stream subscription from {} (session={})",
            request.deviceId,
            request.sessionId.ifBlank { "none" }
        )
        return commandRepository
            .observe(
                request.deviceId,
                includeBroadcast = request.includeBroadcast
            )
            .map { dispatch ->
                val targetId =
                    dispatch.targetDeviceId
                        ?: request.deviceId
                commandEnvelope {
                    commandId =
                        dispatch.commandId
                    sessionId =
                        dispatch.sessionId
                    deviceId =
                        targetId
                    issuedEpochMs =
                        dispatch.issuedEpochMs
                    executeEpochMs =
                        dispatch.executeEpochMs
                    commandJson =
                        dispatch.commandJson
                    protocolVersion =
                        ProtocolVersion.CURRENT
                }
            }
    }

    override suspend fun reportCommandReceipt(
        request: CommandReceipt
    ): CommandAck {
        val payload =
            commandRepository.findStartStopCommand(
                request.commandId
            )
        if (payload != null && request.success) {
            when (payload) {
                is StartRecordingCommandPayload -> deviceRepository.updateRecordingState(
                    request.deviceId,
                    recording = true
                )

                is StopRecordingCommandPayload -> deviceRepository.updateRecordingState(
                    request.deviceId,
                    recording = false
                )

                else -> Unit
            }
        }
        val info =
            buildString {
                append(
                    "Command "
                )
                append(
                    request.commandId
                )
                append(
                    " receipt from "
                )
                append(
                    request.deviceId.ifBlank { "unknown-device" })
                append(
                    ": "
                )
                append(
                    if (request.success) "success" else "failure"
                )
                if (request.message.isNotBlank()) {
                    append(
                        " ("
                    )
                    append(
                        request.message
                    )
                    append(
                        ')'
                    )
                }
            }
        logger.info(
            info
        )
        return commandAck {
            accepted =
                true
            this.info =
                info
        }
    }
}
