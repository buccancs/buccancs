package com.buccancs.desktop.data.retention
import com.buccancs.desktop.domain.policy.RetentionPolicy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong
class DataRetentionManager(
    private val policy: RetentionPolicy
) {
    private val logger = LoggerFactory.getLogger(DataRetentionManager::class.java)
    private val sessionUsage = ConcurrentHashMap<String, AtomicLong>()
    private val deviceUsage = ConcurrentHashMap<String, AtomicLong>()
    private val sessionDeviceUsage = ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicLong>>()
    private val quotaState = MutableStateFlow(
        QuotaSnapshot(
            perSessionBytes = emptyMap(),
            perDeviceBytes = emptyMap(),
            perSessionDeviceBytes = emptyMap(),
            totalBytes = 0,
            actions = emptyList()
        )
    )
    fun state(): StateFlow<QuotaSnapshot> = quotaState.asStateFlow()
    fun registerWrite(sessionId: String, deviceId: String, deltaBytes: Long) {
        if (deltaBytes <= 0) {
            return
        }
        val sessionTotal = sessionUsage.computeIfAbsent(sessionId) { AtomicLong(0) }.addAndGet(deltaBytes)
        val deviceTotal = deviceUsage.computeIfAbsent(deviceId) { AtomicLong(0) }.addAndGet(deltaBytes)
        sessionDeviceUsage
            .computeIfAbsent(sessionId) { ConcurrentHashMap() }
            .computeIfAbsent(deviceId) { AtomicLong(0) }
            .addAndGet(deltaBytes)
        val globalTotal = computeTotalUsage()
        val actions = mutableListOf<QuotaAction>()
        if (sessionTotal > policy.perSessionCapBytes) {
            actions += QuotaAction.SessionCapExceeded(sessionId, sessionTotal, policy.perSessionCapBytes)
        }
        if (deviceTotal > policy.perDeviceCapBytes) {
            actions += QuotaAction.DeviceCapExceeded(deviceId, deviceTotal, policy.perDeviceCapBytes)
        }
        if (globalTotal > policy.globalCapBytes) {
            actions += QuotaAction.GlobalCapExceeded(globalTotal, policy.globalCapBytes)
        }
        if (actions.isNotEmpty()) {
            logger.warn("Retention thresholds exceeded: {}", actions)
        }
        publish(actions, globalTotal)
    }
    fun registerDelete(sessionId: String, deviceId: String, bytesRemoved: Long) {
        if (bytesRemoved <= 0) {
            return
        }
        sessionUsage[sessionId]?.addAndGet(-bytesRemoved)
        deviceUsage[deviceId]?.addAndGet(-bytesRemoved)
        sessionDeviceUsage[sessionId]?.get(deviceId)?.addAndGet(-bytesRemoved)
        sessionDeviceUsage[sessionId]?.entries?.removeIf { it.value.get() <= 0 }
        if (sessionDeviceUsage[sessionId]?.isEmpty() == true) {
            sessionDeviceUsage.remove(sessionId)
        }
        cleanup()
        publish(emptyList(), computeTotalUsage())
    }
    fun resetSession(sessionId: String) {
        sessionUsage.remove(sessionId)
        sessionDeviceUsage.remove(sessionId)?.forEach { (deviceId, usage) ->
            deviceUsage[deviceId]?.addAndGet(-usage.get())
        }
        cleanup()
        publish(emptyList(), computeTotalUsage())
    }
    fun resetDevice(deviceId: String) {
        deviceUsage.remove(deviceId)
        sessionDeviceUsage.forEach { (sessionId, perDevice) ->
            val usage = perDevice.remove(deviceId)?.get() ?: 0L
            if (usage > 0) {
                sessionUsage[sessionId]?.addAndGet(-usage)
            }
        }
        cleanup()
        publish(emptyList(), computeTotalUsage())
    }
    private fun computeTotalUsage(): Long = sessionUsage.values.sumOf { maxOf(it.get(), 0L) }
    private fun cleanup() {
        sessionDeviceUsage.entries.removeIf { entry ->
            entry.value.entries.removeIf { it.value.get() <= 0 }
            entry.value.isEmpty()
        }
        sessionUsage.entries.removeIf { it.value.get() <= 0 }
        deviceUsage.entries.removeIf { it.value.get() <= 0 }
    }
    private fun publish(actions: List<QuotaAction>, totalBytes: Long) {
        val perSession = sessionUsage.mapValues { maxOf(it.value.get(), 0L) }
        val perDevice = deviceUsage.mapValues { maxOf(it.value.get(), 0L) }
        val perSessionDevice = sessionDeviceUsage.mapValues { entry ->
            entry.value.mapValues { maxOf(it.value.get(), 0L) }
        }
        quotaState.value = QuotaSnapshot(
            perSessionBytes = perSession,
            perDeviceBytes = perDevice,
            perSessionDeviceBytes = perSessionDevice,
            totalBytes = totalBytes,
            actions = actions
        )
    }
    data class QuotaSnapshot(
        val perSessionBytes: Map<String, Long>,
        val perDeviceBytes: Map<String, Long>,
        val perSessionDeviceBytes: Map<String, Map<String, Long>>,
        val totalBytes: Long,
        val actions: List<QuotaAction>
    )
    sealed interface QuotaAction {
        data class SessionCapExceeded(
            val sessionId: String,
            val usageBytes: Long,
            val limitBytes: Long
        ) : QuotaAction
        data class DeviceCapExceeded(
            val deviceId: String,
            val usageBytes: Long,
            val limitBytes: Long
        ) : QuotaAction
        data class GlobalCapExceeded(
            val usageBytes: Long,
            val limitBytes: Long
        ) : QuotaAction
    }
}
