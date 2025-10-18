package com.buccancs.core.result

/**
 * Canonical result type for device-level commands (connect, start streaming, etc).
 *
 * - `Accepted` indicates the command was scheduled/started successfully.
 * - `Rejected` represents a logical rejection (e.g. invalid state).
 * - `Failed` wraps an unexpected throwable that prevented execution.
 */
sealed interface DeviceCommandResult {
    data object Accepted :
        DeviceCommandResult

    data class Rejected(
        val reason: String
    ) : DeviceCommandResult

    data class Failed(
        val error: Throwable?
    ) : DeviceCommandResult
}
