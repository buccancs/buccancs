package com.buccancs.data.sensor.connector.shimmer

import android.util.Log
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

/**
 * Circuit breaker for Shimmer connection failures.
 * Prevents repeated connection attempts to failing device, reducing battery drain.
 */
class ShimmerCircuitBreaker(
    private val failureThreshold: Int = 5,
    private val resetTimeoutMs: Long = 60_000L,
    private val halfOpenRetries: Int = 1
) {
    private val failureCount =
        AtomicInteger(
            0
        )
    private val lastFailureTime =
        AtomicLong(
            0
        )
    private val mutex =
        Mutex()

    @Volatile
    private var state =
        State.CLOSED

    enum class State {
        CLOSED,     // Normal operation, requests pass through
        OPEN,       // Too many failures, reject all requests
        HALF_OPEN   // Testing if service recovered
    }

    /**
     * Execute operation with circuit breaker protection.
     * Returns Result.failure if circuit is open.
     */
    suspend fun <T> execute(
        operation: suspend () -> T
    ): Result<T> {
        return mutex.withLock {
            when (state) {
                State.OPEN -> handleOpenState(
                    operation
                )

                State.HALF_OPEN -> handleHalfOpenState(
                    operation
                )

                State.CLOSED -> handleClosedState(
                    operation
                )
            }
        }
    }

    private suspend fun <T> handleOpenState(
        operation: suspend () -> T
    ): Result<T> {
        val timeSinceFailure =
            System.currentTimeMillis() - lastFailureTime.get()

        return if (timeSinceFailure > resetTimeoutMs) {
            // Try transitioning to half-open
            state =
                State.HALF_OPEN
            Log.i(
                TAG,
                "Circuit breaker: OPEN -> HALF_OPEN (testing recovery)"
            )
            handleHalfOpenState(
                operation
            )
        } else {
            // Still too soon, reject immediately
            val remainingMs =
                resetTimeoutMs - timeSinceFailure
            Result.failure(
                CircuitBreakerOpenException(
                    "Circuit breaker open due to ${failureCount.get()} failures. " +
                            "Retry in ${remainingMs / 1000}s"
                )
            )
        }
    }

    private suspend fun <T> handleHalfOpenState(
        operation: suspend () -> T
    ): Result<T> {
        return try {
            val result =
                operation()
            // Success! Close circuit
            reset()
            Log.i(
                TAG,
                "Circuit breaker: HALF_OPEN -> CLOSED (recovery successful)"
            )
            Result.success(
                result
            )
        } catch (e: Exception) {
            // Still failing, reopen circuit
            recordFailure()
            Log.w(
                TAG,
                "Circuit breaker: HALF_OPEN -> OPEN (recovery failed)",
                e
            )
            Result.failure(
                e
            )
        }
    }

    private suspend fun <T> handleClosedState(
        operation: suspend () -> T
    ): Result<T> {
        return try {
            val result =
                operation()
            // Success, reset failure count
            if (failureCount.get() > 0) {
                failureCount.set(
                    0
                )
                Log.d(
                    TAG,
                    "Circuit breaker: Failures reset after successful operation"
                )
            }
            Result.success(
                result
            )
        } catch (e: Exception) {
            recordFailure()
            Result.failure(
                e
            )
        }
    }

    private fun recordFailure() {
        val failures =
            failureCount.incrementAndGet()
        lastFailureTime.set(
            System.currentTimeMillis()
        )

        if (failures >= failureThreshold && state != State.OPEN) {
            state =
                State.OPEN
            Log.w(
                TAG,
                "Circuit breaker: CLOSED -> OPEN after $failures failures"
            )
        } else {
            Log.d(
                TAG,
                "Circuit breaker: Recorded failure $failures/$failureThreshold"
            )
        }
    }

    private fun reset() {
        failureCount.set(
            0
        )
        state =
            State.CLOSED
    }

    /**
     * Get current circuit breaker status.
     */
    fun getStatus(): Status {
        return Status(
            state = state,
            failureCount = failureCount.get(),
            lastFailureTime = lastFailureTime.get(),
            resetTimeoutMs = resetTimeoutMs
        )
    }

    /**
     * Manually reset circuit breaker (for testing or user override).
     */
    suspend fun forceReset() {
        mutex.withLock {
            reset()
            Log.i(
                TAG,
                "Circuit breaker: Force reset"
            )
        }
    }

    data class Status(
        val state: State,
        val failureCount: Int,
        val lastFailureTime: Long,
        val resetTimeoutMs: Long
    ) {
        fun isOpen(): Boolean =
            state == State.OPEN

        fun canRetry(): Boolean =
            state != State.OPEN

        fun timeUntilRetry(): Long {
            if (state != State.OPEN) return 0
            val elapsed =
                System.currentTimeMillis() - lastFailureTime
            return (resetTimeoutMs - elapsed).coerceAtLeast(
                0
            )
        }

        override fun toString(): String {
            return when (state) {
                State.CLOSED -> "CLOSED (failures: $failureCount)"
                State.OPEN -> "OPEN (retry in ${timeUntilRetry() / 1000}s)"
                State.HALF_OPEN -> "HALF_OPEN (testing recovery)"
            }
        }
    }

    companion object {
        private const val TAG =
            "ShimmerCircuitBreaker"
    }
}

class CircuitBreakerOpenException(
    message: String
) : Exception(
    message
)
