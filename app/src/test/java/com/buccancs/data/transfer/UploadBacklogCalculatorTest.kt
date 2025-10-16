package com.buccancs.data.transfer

import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.UploadBacklogLevel
import com.buccancs.domain.model.UploadStatus
import org.junit.Assert.*
import org.junit.Test

class UploadBacklogCalculatorTest {

    @Test
    fun `shouldDrop returns false when under thresholds`() {
        val pendingBytes = UploadBacklogCalculator.WARNING_BYTES - 1024L
        val pendingCount = UploadBacklogCalculator.WARNING_COUNT - 1
        val artifactSize = 32L * 1024

        val shouldDrop = UploadBacklogCalculator.shouldDrop(pendingBytes, pendingCount, artifactSize)

        assertFalse(shouldDrop)
    }

    @Test
    fun `shouldDrop returns true when bytes exceed critical`() {
        val pendingBytes = UploadBacklogCalculator.CRITICAL_BYTES - 512L
        val pendingCount = UploadBacklogCalculator.WARNING_COUNT
        val artifactSize = 2048L

        val shouldDrop = UploadBacklogCalculator.shouldDrop(pendingBytes, pendingCount, artifactSize)

        assertTrue(shouldDrop)
    }

    @Test
    fun `snapshot reports warning with message`() {
        val statuses = buildStatuses(count = UploadBacklogCalculator.WARNING_COUNT + 1, bytesTotal = 512L * 1024)

        val snapshot = UploadBacklogCalculator.snapshot(statuses)

        assertEquals(UploadBacklogLevel.WARNING, snapshot.level)
        assertEquals(UploadBacklogCalculator.WARNING_COUNT + 1, snapshot.queuedCount)
        assertTrue(snapshot.message?.contains("warning") == true)
        assertEquals(mapOf("session" to UploadBacklogCalculator.WARNING_COUNT + 1), snapshot.perSessionQueued)
        assertEquals(
            ((UploadBacklogCalculator.WARNING_COUNT + 1) * 512L * 1024L),
            snapshot.perSessionBytes["session"]
        )
    }

    @Test
    fun `snapshot reports critical and ignores dropped failures`() {
        val statuses = buildStatuses(count = UploadBacklogCalculator.CRITICAL_COUNT, bytesTotal = 128L * 1024)
            .toMutableList()
        statuses += UploadStatus(
            sessionId = "session",
            deviceId = DeviceId("device"),
            streamType = SensorStreamType.RGB_VIDEO,
            fileName = "dropped.mp4",
            bytesTotal = 100,
            bytesTransferred = 0,
            attempt = 0,
            state = com.buccancs.domain.model.UploadState.FAILED,
            errorMessage = UploadBacklogCalculator.OVERFLOW_MESSAGE
        )

        val snapshot = UploadBacklogCalculator.snapshot(statuses)

        assertEquals(UploadBacklogLevel.CRITICAL, snapshot.level)
        assertEquals(UploadBacklogCalculator.CRITICAL_COUNT, snapshot.queuedCount)
        assertTrue(snapshot.message?.contains("critical") == true)
    }

    private fun buildStatuses(count: Int, bytesTotal: Long): List<UploadStatus> =
        (0 until count).map { index ->
            UploadStatus(
                sessionId = "session",
                deviceId = DeviceId("device-$index"),
                streamType = SensorStreamType.RGB_VIDEO,
                fileName = "file-$index",
                bytesTotal = bytesTotal,
                bytesTransferred = 0,
                attempt = 0,
                state = com.buccancs.domain.model.UploadState.QUEUED,
                errorMessage = null
            )
        }
}
