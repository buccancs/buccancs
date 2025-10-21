package com.buccancs.hardware.shimmer

import android.content.Context
import androidx.core.content.edit

/**
 * Persists per-device auto-launch preferences for Shimmer Bluetooth hardware. Mapped by the device
 * MAC address so users can independently choose behaviour per sensor.
 */
object ShimmerAutoLaunchPreferences {

    enum class Mode {
        /**
         * Default behaviour. Ask the user whenever the device connects.
         */
        ASK,

        /**
         * Automatically open the app when the device connects.
         */
        ALWAYS,

        /**
         * Never open the app automatically for this device.
         */
        NEVER
    }

    private const val PREFS_NAME =
        "shimmer_auto_launch"
    private const val KEY_PREFIX_MODE =
        "mode_"

    fun getMode(
        context: Context,
        deviceAddress: String
    ): Mode {
        val prefs =
            context.getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
        val raw =
            prefs.getString(
                modeKey(deviceAddress),
                null
            )
        return raw?.let(Mode::valueOf) ?: Mode.ASK
    }

    fun setMode(
        context: Context,
        deviceAddress: String,
        mode: Mode
    ) {
        val prefs =
            context.getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
        prefs.edit {
            when (mode) {
                Mode.ASK ->
                    remove(modeKey(deviceAddress))

                Mode.ALWAYS,
                Mode.NEVER ->
                    putString(
                        modeKey(deviceAddress),
                        mode.name
                    )
            }
        }
    }

    private fun modeKey(deviceAddress: String): String =
        KEY_PREFIX_MODE + deviceAddress.uppercase()
}
