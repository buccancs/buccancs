package com.buccancs.data.sensor.connector.simulated

import android.net.Uri
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.security.MessageDigest
import java.util.Locale
import javax.inject.Inject
import kotlin.random.Random

@javax.inject.Singleton
internal class SimulatedArtifactFactory private constructor(
    private val rootDirectory: File
) {
    @Inject
    constructor(@ApplicationContext context: android.content.Context) : this(context.filesDir)

    internal companion object {
        fun fromRoot(root: File): SimulatedArtifactFactory = SimulatedArtifactFactory(root)

        val random: Random = Random(System.currentTimeMillis())
    }

    fun createArtifact(
        sessionId: String,
        deviceId: DeviceId,
        streamType: SensorStreamType,
        extension: String,
        mimeType: String,
        payloadProducer: () -> ByteArray
    ): SessionArtifact {
        val payload = payloadProducer()
        val directory = sessionDirectory(sessionId, deviceId)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        val fileName = buildFileName(streamType, extension)
        val target = File(directory, fileName)
        target.writeBytes(payload)
        val checksum = MessageDigest.getInstance("SHA-256").digest(payload)
        return SessionArtifact(
            deviceId = deviceId,
            streamType = streamType,
            uri = Uri.fromFile(target),
            file = target,
            mimeType = mimeType,
            sizeBytes = payload.size.toLong(),
            checksumSha256 = checksum
        )
    }

    fun createRandomArtifact(
        sessionId: String,
        deviceId: DeviceId,
        streamType: SensorStreamType,
        extension: String,
        mimeType: String,
        sizeBytes: Int
    ): SessionArtifact = createArtifact(
        sessionId = sessionId,
        deviceId = deviceId,
        streamType = streamType,
        extension = extension,
        mimeType = mimeType
    ) {
        ByteArray(sizeBytes).also { random.nextBytes(it) }
    }

    private fun sessionDirectory(sessionId: String, deviceId: DeviceId): File {
        val sanitizedSession = sanitize(sessionId)
        val sanitizedDevice = sanitize(deviceId.value)
        return File(rootDirectory, "sessions/$sanitizedSession/$sanitizedDevice")
    }

    private fun sanitize(input: String): String =
        input.replace(Regex("[^A-Za-z0-9._-]"), "_")

    private fun buildFileName(streamType: SensorStreamType, extension: String): String {
        val suffix = System.currentTimeMillis()
        val prefix = streamType.name.lowercase(Locale.US)
        return "$prefix-$suffix.$extension"
    }
}
