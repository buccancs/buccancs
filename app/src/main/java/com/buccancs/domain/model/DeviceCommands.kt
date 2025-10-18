package com.buccancs.domain.model

sealed interface DeviceCommand {
    data object Connect :
        DeviceCommand

    data object Disconnect :
        DeviceCommand

    data class Configure(
        val options: Map<String, String>
    ) : DeviceCommand

    data class StartStreaming(
        val anchor: RecordingSessionAnchor
    ) : DeviceCommand

    data object StopStreaming :
        DeviceCommand
}

typealias DeviceCommandResult = com.buccancs.core.result.DeviceCommandResult
