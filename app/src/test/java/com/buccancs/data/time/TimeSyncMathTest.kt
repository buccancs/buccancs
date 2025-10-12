package com.buccancs.data.time
import org.junit.Assert.assertEquals
import org.junit.Test
class TimeSyncMathTest {
    @Test
    fun `computeSyncSample returns zero offset for symmetric exchange`() {
        val sample = computeSyncSample(
            sendEpochMs = 1_000L,
            receiveEpochMs = 1_020L,
            serverReceiveEpochMs = 1_010L,
            serverSendEpochMs = 1_015L
        )
        assertEquals(0.0, sample.offsetMs, 0.0001)
        assertEquals(15.0, sample.roundTripMs, 0.0001)
    }
    @Test
    fun `computeSyncSample removes server processing time from round trip`() {
        val sample = computeSyncSample(
            sendEpochMs = 5_000L,
            receiveEpochMs = 5_032L,
            serverReceiveEpochMs = 5_010L,
            serverSendEpochMs = 5_022L
        )
        assertEquals(0.0, sample.offsetMs, 0.0001)
        assertEquals(20.0, sample.roundTripMs, 0.0001)
    }
    @Test
    fun `computeSyncSample clamps negative round trip to zero`() {
        val sample = computeSyncSample(
            sendEpochMs = 10_000L,
            receiveEpochMs = 10_001L,
            serverReceiveEpochMs = 9_999L,
            serverSendEpochMs = 10_500L
        )
        assertEquals(-250.5, sample.offsetMs, 0.0001)
        assertEquals(0.0, sample.roundTripMs, 0.0001)
    }
}
