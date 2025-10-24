package com.buccancs.control

object ProtocolVersion {
    const val CURRENT =
        1000  // v1.0
    const val MIN_SUPPORTED =
        1000  // v1.0

    fun isSupported(
        version: Int
    ): Boolean {
        // Support same major version with any minor version
        val versionMajor =
            version / 1000
        val currentMajor =
            CURRENT / 1000
        val minMajor =
            MIN_SUPPORTED / 1000
        return versionMajor in minMajor..currentMajor
    }

    fun isCompatible(
        clientVersion: Int
    ): Boolean {
        val clientMajor =
            clientVersion / 1000
        val currentMajor =
            CURRENT / 1000
        val minMajor =
            MIN_SUPPORTED / 1000
        return clientMajor in minMajor..currentMajor
    }

    fun requireSupported(
        version: Int
    ) {
        require(
            isSupported(
                version
            )
        ) {
            "Unsupported protocol version: ${
                versionString(
                    version
                )
            } " +
                    "(server: ${
                        versionString(
                            CURRENT
                        )
                    }, min supported: ${
                        versionString(
                            MIN_SUPPORTED
                        )
                    })"
        }
    }

    fun versionString(
        version: Int
    ): String {
        val major =
            version / 1000
        val minor =
            version % 1000
        return "v$major.$minor"
    }

    fun majorVersion(
        version: Int
    ): Int =
        version / 1000

    fun minorVersion(
        version: Int
    ): Int =
        version % 1000
}
