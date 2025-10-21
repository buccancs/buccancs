package com.buccancs.ui.base

/**
 * Metadata emitted when a Shimmer Bluetooth device connection should prompt or trigger auto-launch
 * behaviour within the app.
 */
data class ShimmerAutoLaunchEvent(
    val deviceAddress: String,
    val deviceName: String?,
    val mode: ShimmerAutoLaunchMode
)

/**
 * Indicates how the UI should respond to an auto-launch event.
 */
enum class ShimmerAutoLaunchMode {
    /**
     * User preference unknown; surface a dialog asking whether to open and optionally remember.
     */
    ASK,

    /**
     * User previously opted-in; navigate directly without prompting.
     */
    ALWAYS
}

/**
 * Captures the user's response to the prompt so preferences can be updated.
 */
enum class ShimmerAutoLaunchDecision {
    ALLOW_ONCE,
    ALWAYS_ALLOW,
    DENY
}
