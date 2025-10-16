package com.buccancs.desktop.data.grpc.services

import com.buccancs.control.*
import com.buccancs.desktop.data.repository.DeviceRepository
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.abs

class TimeSyncServiceImpl(
    private val deviceRepository: DeviceRepository
) : TimeSyncServiceGrpcKt.TimeSyncServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(TimeSyncServiceImpl::class.java)
    private val history = ConcurrentHashMap<String, TimeSyncSample>()

    override suspend fun ping(request: TimeSyncPing): TimeSyncPong {
        val receiveTs = System.currentTimeMillis()
        val sendTs = System.currentTimeMillis()
        return timeSyncPong {
            serverReceiveEpochMs = receiveTs
            serverSendEpochMs = sendTs
        }
    }

    override suspend fun report(request: TimeSyncReport): CommandAck {
        val offset = request.offsetMs.takeIf { it.isFinite() }
        val heartbeat = if (request.sampleEpochMs > 0) {
            Instant.ofEpochMilli(request.sampleEpochMs)
        } else {
            Instant.now()
        }
        deviceRepository.updateClockOffset(request.deviceId, offset, heartbeat)
        if (offset != null) {
            val previous = history.put(
                request.deviceId,
                TimeSyncSample(
                    timestamp = heartbeat,
                    offsetMs = offset,
                    roundTripMs = request.roundTripMs
                )
            )
            if (previous != null) {
                val elapsedMs = (heartbeat.toEpochMilli() - previous.timestamp.toEpochMilli()).coerceAtLeast(1L)
                val driftPerSecond = ((offset - previous.offsetMs) / elapsedMs) * 1000.0
                if (abs(driftPerSecond) > DRIFT_ALERT_THRESHOLD_MS_PER_SEC) {
                    logger.warn(
                        "Device {} drift {} ms/s (offset={}ms rtt={}ms)",
                        request.deviceId,
                        String.format(Locale.US, "%.3f", driftPerSecond),
                        String.format(Locale.US, "%.1f", offset),
                        String.format(Locale.US, "%.1f", request.roundTripMs)
                    )
                }
            }
            if (request.roundTripMs > ROUND_TRIP_ALERT_THRESHOLD_MS) {
                logger.warn(
                    "Time sync round trip elevated for {}: {} ms",
                    request.deviceId,
                    String.format(Locale.US, "%.1f", request.roundTripMs)
                )
            }
        }
        logger.debug(
            "Sync report from {} offset={}ms rtt={}ms",
            request.deviceId,
            offset,
            request.roundTripMs
        )
        return commandAck {
            accepted = true
            info = "Sync report accepted"
        }
    }

    private data class TimeSyncSample(
        val timestamp: Instant,
        val offsetMs: Double,
        val roundTripMs: Double
    )

    private companion object {
        private const val DRIFT_ALERT_THRESHOLD_MS_PER_SEC = 0.05
        private const val ROUND_TRIP_ALERT_THRESHOLD_MS = 50.0
    }
}
