package com.buccancs.data.storage

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecordingStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun deviceDirectory(sessionId: String, deviceId: String): File {
        val root = File(context.filesDir, "recordings/$sessionId/$deviceId")
        if (!root.exists()) {
            root.mkdirs()
        }
        return root
    }

    fun sessionDirectory(sessionId: String): File {
        val root = File(context.filesDir, "recordings/$sessionId")
        if (!root.exists()) {
            root.mkdirs()
        }
        return root
    }
}
