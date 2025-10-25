package com.buccancs.data.calibration

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.buccancs.core.serialization.PrettyJson
import com.buccancs.core.storage.WriteResult
import com.buccancs.data.storage.AtomicFileWriter
import com.buccancs.domain.model.CalibrationResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CalibrationStorage @Inject constructor(
    @ApplicationContext context: Context,
    @PrettyJson private val json: Json
) {
    private val rootDir: File =
        File(
            context.filesDir,
            "calibration"
        ).apply {
            if (!exists()) {
                mkdirs()
            }
        }

    companion object {
        private const val TAG =
            "CalibrationStorage"
    }

    fun createSessionDirectory(
        sessionId: String
    ): File {
        val dir =
            File(
                rootDir,
                sessionId
            )
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    fun sessionDirectory(
        sessionId: String
    ): File =
        File(
            rootDir,
            sessionId
        )

    fun deleteSessionDirectory(
        sessionId: String
    ) {
        sessionDirectory(
            sessionId
        ).deleteRecursively()
    }

    fun persistBitmap(
        bitmap: Bitmap,
        file: File
    ) {
        file.parentFile?.mkdirs()
        FileOutputStream(
            file
        ).use { stream ->
            if (!bitmap.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    stream
                )
            ) {
                throw IOException(
                    "Unable to encode bitmap to ${file.absolutePath}"
                )
            }
        }
    }

    suspend fun writeResult(
        sessionId: String,
        result: CalibrationResult
    ) {
        val sessionDir =
            createSessionDirectory(
                sessionId
            )
        val output =
            File(
                sessionDir,
                "result.json"
            )
        val payload =
            json.encodeToString(
                result
            )

        when (val writeResult =
            AtomicFileWriter.writeAtomicSafe(
                output,
                payload,
                checkSpace = true
            )) {
            is WriteResult.Success -> {
                val latest =
                    File(
                        rootDir,
                        "latest_result.json"
                    )
                when (AtomicFileWriter.writeAtomicSafe(
                    latest,
                    payload,
                    checkSpace = true
                )) {
                    is WriteResult.Success -> Log.d(
                        TAG,
                        "Calibration result written: $sessionId"
                    )

                    is WriteResult.Failure -> Log.w(
                        TAG,
                        "Failed to update latest result link"
                    )
                }
            }

            is WriteResult.Failure.InsufficientSpace -> {
                Log.e(
                    TAG,
                    "Insufficient space to write calibration result"
                )
                throw IOException(
                    "Insufficient storage space"
                )
            }

            is WriteResult.Failure.WriteError -> {
                Log.e(
                    TAG,
                    "Failed to write calibration result",
                    writeResult.cause
                )
                throw IOException(
                    "Failed to write calibration result: ${writeResult.message}",
                    writeResult.cause
                )
            }
        }
    }

    fun loadResult(
        sessionId: String
    ): CalibrationResult? {
        val file =
            File(
                sessionDirectory(
                    sessionId
                ),
                "result.json"
            )
        if (!file.exists()) return null
        return runCatching {
            json.decodeFromString<CalibrationResult>(
                file.readText()
            )
        }.getOrNull()
    }

    fun loadLatestResult(): CalibrationResult? {
        val file =
            File(
                rootDir,
                "latest_result.json"
            )
        if (!file.exists()) return null
        return runCatching {
            json.decodeFromString<CalibrationResult>(
                file.readText()
            )
        }.getOrNull()
    }
}
