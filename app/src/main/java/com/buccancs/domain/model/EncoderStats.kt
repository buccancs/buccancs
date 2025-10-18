package com.buccancs.domain.model

data class EncoderStats(
    val segmentIndex: Int,
    val startedAtEpochMs: Long,
    val endedAtEpochMs: Long,
    val durationMillis: Long,
    val frameCount: Long,
    val keyFrameCount: Long,
    val droppedFrameCount: Long,
    val bytesWritten: Long,
    val averageBitrateBps: Long,
    val maxEncodeLatencyMillis: Double,
    val averageEncodeLatencyMillis: Double
) {
    fun asMetadata(): Map<String, String> =
        mapOf(
            "segmentIndex" to segmentIndex.toString(),
            "startedAtEpochMs" to startedAtEpochMs.toString(),
            "endedAtEpochMs" to endedAtEpochMs.toString(),
            "durationMillis" to durationMillis.toString(),
            "frameCount" to frameCount.toString(),
            "keyFrameCount" to keyFrameCount.toString(),
            "droppedFrameCount" to droppedFrameCount.toString(),
            "bytesWritten" to bytesWritten.toString(),
            "averageBitrateBps" to averageBitrateBps.toString(),
            "maxEncodeLatencyMillis" to maxEncodeLatencyMillis.toString(),
            "averageEncodeLatencyMillis" to averageEncodeLatencyMillis.toString()
        )
}
