package com.buccancs.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ShimmerDeviceCandidate(
    val mac: String,
    val name: String? = null,
    val rssi: Int? = null
)
