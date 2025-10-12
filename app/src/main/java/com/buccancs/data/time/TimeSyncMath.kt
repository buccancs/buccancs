package com.buccancs.data.time

internal data class SyncSample(
    val offsetMs: Double,
    val roundTripMs: Double
)

internal fun computeSyncSample(
    sendEpochMs: Long,
    receiveEpochMs: Long,
    serverReceiveEpochMs: Long,
    serverSendEpochMs: Long
): SyncSample {
    val serverProcessingMs = (serverSendEpochMs - serverReceiveEpochMs).coerceAtLeast(0L)
    val clientFlightMs = (receiveEpochMs - sendEpochMs).coerceAtLeast(0L)
    val roundTrip = (clientFlightMs - serverProcessingMs).coerceAtLeast(0L).toDouble()
    val offset = (
        (serverReceiveEpochMs - sendEpochMs).toDouble() +
            (serverSendEpochMs - receiveEpochMs).toDouble()
        ) / 2.0
    return SyncSample(
        offsetMs = offset,
        roundTripMs = roundTrip
    )
}
