package com.buccancs.ui.topdon

/**
 * Numeric codes that represent the current thermal preview lifecycle state.
 * These codes remain stable across configuration changes and can be logged or
 * persisted for diagnostics.
 */
enum class ThermalUiStateCode(
    val code: Int,
    val label: String
) {
    DEVICE_DISCONNECTED(440, "Device disconnected"),
    DEVICE_CONNECTING(441, "Connecting"),
    DEVICE_READY(442, "Ready"),
    PREVIEW_BUFFERING(449, "Preview buffering"),
    PREVIEW_STREAMING(450, "Preview streaming"),
    PREVIEW_RECORDING(451, "Recording"),
    PREVIEW_ERROR(499, "Preview error")
}
