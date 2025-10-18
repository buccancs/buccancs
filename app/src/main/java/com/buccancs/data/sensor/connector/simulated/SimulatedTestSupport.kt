package com.buccancs.data.sensor.connector.simulated

import java.io.File

internal object SimulatedTestSupport {
    fun artifactFactory(): SimulatedArtifactFactory {
        val root =
            File(
                System.getProperty(
                    "java.io.tmpdir"
                ),
                "buccancs-sim-artifacts"
            )
        root.mkdirs()
        return SimulatedArtifactFactory.fromRoot(
            root
        )
    }
}
