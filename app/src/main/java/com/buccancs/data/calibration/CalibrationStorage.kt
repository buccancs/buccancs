package com.buccancs.data.calibration

import android.content.Context
import android.graphics.Bitmap
import com.buccancs.domain.model.CalibrationResult
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Singleton
class CalibrationStorage @Inject constructor(
    @ApplicationContext context: Context
) {
    private val rootDir: File = File(context.filesDir, "calibration").apply {
        if (!exists()) {
            mkdirs()
        }
    }
    private val json = Json {
        prettyPrint = true
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    fun createSessionDirectory(sessionId: String): File {
        val dir = File(rootDir, sessionId)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    fun sessionDirectory(sessionId: String): File = File(rootDir, sessionId)

    fun deleteSessionDirectory(sessionId: String) {
        sessionDirectory(sessionId).deleteRecursively()
    }

    fun persistBitmap(bitmap: Bitmap, file: File) {
        file.parentFile?.mkdirs()
        FileOutputStream(file).use { stream ->
            if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)) {
                throw IOException("Unable to encode bitmap to ${file.absolutePath}")
            }
        }
    }

    fun writeResult(sessionId: String, result: CalibrationResult) {
        val sessionDir = createSessionDirectory(sessionId)
        val output = File(sessionDir, "result.json")
        output.writeText(json.encodeToString(result))
        File(rootDir, "latest_result.json").writeText(output.readText())
    }

    fun loadResult(sessionId: String): CalibrationResult? {
        val file = File(sessionDirectory(sessionId), "result.json")
        if (!file.exists()) return null
        return runCatching { json.decodeFromString<CalibrationResult>(file.readText()) }.getOrNull()
    }

    fun loadLatestResult(): CalibrationResult? {
        val file = File(rootDir, "latest_result.json")
        if (!file.exists()) return null
        return runCatching { json.decodeFromString<CalibrationResult>(file.readText()) }.getOrNull()
    }
}
