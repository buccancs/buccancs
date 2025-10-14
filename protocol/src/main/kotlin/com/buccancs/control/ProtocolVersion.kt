package com.buccancs.control

object ProtocolVersion {
    const val CURRENT = 1
    const val MIN_SUPPORTED = 1

    fun isSupported(version: Int): Boolean {
        return version >= MIN_SUPPORTED && version <= CURRENT
    }

    fun requireSupported(version: Int) {
        require(isSupported(version)) {
            "Unsupported protocol version: $version (current: $CURRENT, min supported: $MIN_SUPPORTED)"
        }
    }
}
