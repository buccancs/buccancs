package com.buccancs.data.sensor.connector.simulated

import android.test.mock.MockContext
import java.io.File

internal object SimulatedTestSupport {
    fun artifactFactory(): SimulatedArtifactFactory {
        val context = object : MockContext() {
            private val root = File(System.getProperty("java.io.tmpdir"), "buccancs-sim-artifacts")
            override fun getFilesDir(): File = root.apply { mkdirs() }
        }
        return SimulatedArtifactFactory(context)
    }
}
