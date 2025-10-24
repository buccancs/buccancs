package com.buccancs.core.storage

sealed class WriteResult<out T> {
    data class Success<T>(
        val value: T
    ) : WriteResult<T>()

    sealed class Failure :
        WriteResult<Nothing>() {
        data class InsufficientSpace(
            val required: Long,
            val available: Long
        ) : Failure()

        data class WriteError(
            val message: String,
            val cause: Throwable?
        ) : Failure()
    }
}
