package com.buccancs.data.orchestration

import android.content.Context
import android.os.Build
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceIdentityProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun deviceId(): String {
        val androidId =
            runCatching {
                Settings.Secure.getString(
                    context.contentResolver,
                    Settings.Secure.ANDROID_ID
                )
            }.getOrNull()
                ?.takeIf { !it.isNullOrBlank() }
        if (!androidId.isNullOrBlank()) {
            return "android-$androidId"
        }
        val model =
            Build.MODEL.orEmpty()
                .ifBlank { "unknown" }
        return "android-$model"
    }
}
