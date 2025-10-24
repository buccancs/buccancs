package com.buccancs.data.sensor.connector.simulated

import java.io.File

/**
 * Provides a shared factory instance for simulated connectors when the Hilt-managed
 * test infrastructure is not available (e.g., during release builds).
 */
object SimulatedTestSupport {
    private val rootDir: File by lazy {
        File(System.getProperty("java.io.tmpdir"), "simulated-artifacts").apply { mkdirs() }
    }

    fun artifactFactory(): SimulatedArtifactFactory =
        SimulatedArtifactFactory.fromRoot(rootDir)
}
