package com.buccancs.domain.model

data class ShimmerSettings(
    val targetMacAddress: String? = null,
    val gsrRangeIndex: Int = DEFAULT_GSR_RANGE,
    val sampleRateHz: Double = DEFAULT_SAMPLE_RATE_HZ
) {
    companion object {
        const val DEFAULT_GSR_RANGE =
            4
        const val DEFAULT_SAMPLE_RATE_HZ =
            128.0
    }
}
