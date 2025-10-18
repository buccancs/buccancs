package com.buccancs.core.result

/**
 * Represents the result of an operation that can succeed or fail.
 * Forces explicit error handling at call sites and provides type-safe error categories.
 */
sealed class Result<out T> {
    data class Success<T>(
        val value: T
    ) : Result<T>()

    data class Failure(
        val error: Error
    ) : Result<Nothing>()

    fun isSuccess(): Boolean =
        this is Success

    fun isFailure(): Boolean =
        this is Failure

    fun getOrNull(): T? =
        when (this) {
            is Success -> value
            is Failure -> null
        }

    fun errorOrNull(): Error? =
        when (this) {
            is Success -> null
            is Failure -> error
        }

    inline fun <R> map(
        transform: (T) -> R
    ): Result<R> =
        when (this) {
            is Success -> Success(
                transform(
                    value
                )
            )

            is Failure -> Failure(
                error
            )
        }

    inline fun <R> flatMap(
        transform: (T) -> Result<R>
    ): Result<R> =
        when (this) {
            is Success -> transform(
                value
            )

            is Failure -> Failure(
                error
            )
        }

    inline fun onSuccess(
        action: (T) -> Unit
    ): Result<T> {
        if (this is Success) action(
            value
        )
        return this
    }

    inline fun onFailure(
        action: (Error) -> Unit
    ): Result<T> {
        if (this is Failure) action(
            error
        )
        return this
    }

    fun getOrThrow(): T =
        when (this) {
            is Success -> value
            is Failure -> throw error.toException()
        }

    fun getOrElse(
        defaultValue: @UnsafeVariance T
    ): T =
        when (this) {
            is Success -> value
            is Failure -> defaultValue
        }

    inline fun getOrElse(
        defaultValue: (Error) -> @UnsafeVariance T
    ): T =
        when (this) {
            is Success -> value
            is Failure -> defaultValue(
                error
            )
        }

    companion object {
        fun <T> success(
            value: T
        ): Result<T> =
            Success(
                value
            )

        fun failure(
            error: Error
        ): Result<Nothing> =
            Failure(
                error
            )

        inline fun <T> runCatching(
            block: () -> T
        ): Result<T> =
            try {
                Success(
                    block()
                )
            } catch (ex: Exception) {
                when (ex) {
                    is kotlinx.coroutines.CancellationException -> throw ex
                    else -> Failure(
                        Error.Unknown(
                            "Operation failed",
                            ex
                        )
                    )
                }
            }
    }
}

/**
 * Type-safe error categories for different failure scenarios.
 */
sealed class Error {
    abstract val message: String
    abstract val cause: Throwable?

    data class Network(
        override val message: String,
        override val cause: Throwable? = null
    ) : Error()

    data class Hardware(
        val deviceId: String?,
        override val message: String,
        override val cause: Throwable? = null
    ) : Error()

    data class Bluetooth(
        val deviceId: String?,
        override val message: String,
        override val cause: Throwable? = null
    ) : Error()

    data class Storage(
        override val message: String,
        override val cause: Throwable? = null
    ) : Error()

    data class Validation(
        val field: String,
        override val message: String,
        override val cause: Throwable? = null
    ) : Error()

    data class Codec(
        override val message: String,
        override val cause: Throwable? = null
    ) : Error()

    data class Configuration(
        override val message: String,
        override val cause: Throwable? = null
    ) : Error()

    data class Timeout(
        override val message: String,
        val timeoutMs: Long? = null,
        override val cause: Throwable? = null
    ) : Error()

    data class NotFound(
        override val message: String,
        override val cause: Throwable? = null
    ) : Error()

    data class Permission(
        override val message: String,
        val permission: String? = null,
        override val cause: Throwable? = null
    ) : Error()

    data class Unknown(
        override val message: String,
        override val cause: Throwable? = null
    ) : Error()

    fun toException(): Exception =
        when (this) {
            is Network -> NetworkException(
                message,
                cause
            )

            is Hardware -> HardwareException(
                message,
                cause
            )

            is Bluetooth -> BluetoothException(
                message,
                cause
            )

            is Storage -> StorageException(
                message,
                cause
            )

            is Validation -> ValidationException(
                message,
                cause
            )

            is Codec -> CodecException(
                message,
                cause
            )

            is Configuration -> ConfigurationException(
                message,
                cause
            )

            is Timeout -> TimeoutException(
                message,
                cause
            )

            is NotFound -> NotFoundException(
                message,
                cause
            )

            is Permission -> PermissionException(
                message,
                cause
            )

            is Unknown -> UnknownException(
                message,
                cause
            )
        }
}

class NetworkException(
    message: String,
    cause: Throwable? = null
) : Exception(
    message,
    cause
)

class HardwareException(
    message: String,
    cause: Throwable? = null
) : Exception(
    message,
    cause
)

class BluetoothException(
    message: String,
    cause: Throwable? = null
) : Exception(
    message,
    cause
)

class StorageException(
    message: String,
    cause: Throwable? = null
) : Exception(
    message,
    cause
)

class ValidationException(
    message: String,
    cause: Throwable? = null
) : Exception(
    message,
    cause
)

class CodecException(
    message: String,
    cause: Throwable? = null
) : Exception(
    message,
    cause
)

class ConfigurationException(
    message: String,
    cause: Throwable? = null
) : Exception(
    message,
    cause
)

class TimeoutException(
    message: String,
    cause: Throwable? = null
) : Exception(
    message,
    cause
)

class NotFoundException(
    message: String,
    cause: Throwable? = null
) : Exception(
    message,
    cause
)

class PermissionException(
    message: String,
    cause: Throwable? = null
) : Exception(
    message,
    cause
)

class UnknownException(
    message: String,
    cause: Throwable? = null
) : Exception(
    message,
    cause
)
