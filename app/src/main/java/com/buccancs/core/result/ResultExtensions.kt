package com.buccancs.core.result

import kotlinx.coroutines.CancellationException
import java.io.IOException

/**
 * Executes a suspending block and wraps the result in a Result type.
 * Automatically handles CancellationException by rethrowing it.
 */
suspend inline fun <T> resultOf(
    block: () -> T
): Result<T> =
    try {
        Result.Success(
            block()
        )
    } catch (ex: CancellationException) {
        throw ex
    } catch (ex: Exception) {
        Result.Failure(
            ex.toError()
        )
    }

/**
 * Converts common exceptions to appropriate Error types.
 */
fun Throwable.toError(): Error =
    when (this) {
        is IOException -> Error.Network(
            message
                ?: "Network operation failed",
            this
        )

        is SecurityException -> Error.Permission(
            message
                ?: "Permission denied",
            null,
            this
        )

        is IllegalArgumentException -> Error.Validation(
            "argument",
            message
                ?: "Invalid argument",
            this
        )

        is IllegalStateException -> Error.Configuration(
            message
                ?: "Invalid state",
            this
        )

        is java.util.concurrent.TimeoutException -> Error.Timeout(
            message
                ?: "Operation timed out",
            null,
            this
        )

        is NoSuchElementException -> Error.NotFound(
            message
                ?: "Element not found",
            this
        )

        else -> Error.Unknown(
            message
                ?: "Unknown error",
            this
        )
    }

/**
 * Combines multiple Result values into a single Result containing a list.
 * Returns failure if any result is a failure.
 */
fun <T> List<Result<T>>.combine(): Result<List<T>> {
    val values =
        mutableListOf<T>()
    for (result in this) {
        when (result) {
            is Result.Success -> values.add(
                result.value
            )

            is Result.Failure -> return Result.Failure(
                result.error
            )
        }
    }
    return Result.Success(
        values
    )
}

/**
 * Converts a nullable value to a Result.
 */
fun <T : Any> T?.toResult(
    errorMessage: String = "Value is null"
): Result<T> =
    if (this != null) Result.Success(
        this
    ) else Result.Failure(
        Error.NotFound(
            errorMessage
        )
    )

/**
 * Recovers from a failure by providing an alternative value.
 */
inline fun <T> Result<T>.recover(
    transform: (Error) -> T
): Result<T> =
    when (this) {
        is Result.Success -> this
        is Result.Failure -> Result.Success(
            transform(
                error
            )
        )
    }

/**
 * Recovers from a failure by providing an alternative Result.
 */
inline fun <T> Result<T>.recoverWith(
    transform: (Error) -> Result<T>
): Result<T> =
    when (this) {
        is Result.Success -> this
        is Result.Failure -> transform(
            error
        )
    }

/**
 * Chains multiple Result-returning operations together.
 */
inline fun <T, R> Result<T>.andThen(
    transform: (T) -> Result<R>
): Result<R> =
    flatMap(
        transform
    )

/**
 * Combines two Result values into a Pair.
 */
fun <T, U> Result<T>.zip(
    other: Result<U>
): Result<Pair<T, U>> =
    flatMap { t -> other.map { u -> t to u } }

/**
 * Combines two Result values with a transformation function.
 */
inline fun <T, U, R> Result<T>.zip(
    other: Result<U>,
    transform: (T, U) -> R
): Result<R> =
    flatMap { t ->
        other.map { u ->
            transform(
                t,
                u
            )
        }
    }

/**
 * Converts Result to kotlin.Result for interop with standard library.
 */
fun <T> Result<T>.toKotlinResult(): kotlin.Result<T> =
    when (this) {
        is Result.Success -> kotlin.Result.success(
            value
        )

        is Result.Failure -> kotlin.Result.failure(
            error.toException()
        )
    }

/**
 * Converts kotlin.Result to our Result type.
 */
fun <T> kotlin.Result<T>.toResult(): Result<T> =
    fold(
        onSuccess = {
            Result.Success(
                it
            )
        },
        onFailure = {
            Result.Failure(
                it.toError()
            )
        }
    )
