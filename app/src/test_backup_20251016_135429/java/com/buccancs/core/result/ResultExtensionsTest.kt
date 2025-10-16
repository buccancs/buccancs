package com.buccancs.core.result

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class ResultExtensionsTest {

    @Test
    fun `resultOf wraps successful operation`() = runTest {
        val result = resultOf {
            42
        }

        assertTrue(result.isSuccess())
        assertEquals(42, result.getOrNull())
    }

    @Test
    fun `resultOf wraps exception`() = runTest {
        val result = resultOf {
            throw IOException("Test IO error")
        }

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Network)
        assertEquals("Test IO error", error?.message)
    }

    @Test
    fun `resultOf rethrows CancellationException`() = runTest {
        var exceptionThrown = false
        try {
            resultOf {
                throw CancellationException("Test cancellation")
            }
        } catch (e: CancellationException) {
            exceptionThrown = true
        }

        assertTrue(exceptionThrown)
    }

    @Test
    fun `toError converts IOException to Network error`() {
        val exception = IOException("Network failure")
        val error = exception.toError()

        assertTrue(error is Error.Network)
        assertEquals("Network failure", error.message)
    }

    @Test
    fun `toError converts SecurityException to Permission error`() {
        val exception = SecurityException("Permission denied")
        val error = exception.toError()

        assertTrue(error is Error.Permission)
        assertEquals("Permission denied", error.message)
    }

    @Test
    fun `toError converts IllegalArgumentException to Validation error`() {
        val exception = IllegalArgumentException("Invalid argument")
        val error = exception.toError()

        assertTrue(error is Error.Validation)
        assertEquals("Invalid argument", error.message)
    }

    @Test
    fun `toError converts unknown exception to Unknown error`() {
        val exception = RuntimeException("Unknown error")
        val error = exception.toError()

        assertTrue(error is Error.Unknown)
        assertEquals("Unknown error", error.message)
    }

    @Test
    fun `combine returns success for all successful results`() {
        val results = listOf(
            Result.success(1),
            Result.success(2),
            Result.success(3)
        )

        val combined = results.combine()

        assertTrue(combined.isSuccess())
        assertEquals(listOf(1, 2, 3), combined.getOrNull())
    }

    @Test
    fun `combine returns failure if any result fails`() {
        val error = Error.Unknown("Test error")
        val results = listOf(
            Result.success(1),
            Result.failure(error),
            Result.success(3)
        )

        val combined = results.combine()

        assertTrue(combined.isFailure())
        assertEquals(error, combined.errorOrNull())
    }

    @Test
    fun `toResult converts non-null value to success`() {
        val result = 42.toResult()

        assertTrue(result.isSuccess())
        assertEquals(42, result.getOrNull())
    }

    @Test
    fun `toResult converts null to failure`() {
        val value: String? = null
        val result = value.toResult("Value is missing")

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.NotFound)
        assertEquals("Value is missing", error?.message)
    }

    @Test
    fun `recover replaces failure with value`() {
        val result: Result<Int> = Result.failure(Error.Unknown("Test"))
        val recovered = result.recover { 42 }

        assertTrue(recovered.isSuccess())
        assertEquals(42, recovered.getOrNull())
    }

    @Test
    fun `recover preserves success`() {
        val result = Result.success(10)
        val recovered = result.recover { 42 }

        assertTrue(recovered.isSuccess())
        assertEquals(10, recovered.getOrNull())
    }

    @Test
    fun `recoverWith replaces failure with new result`() {
        val result: Result<Int> = Result.failure(Error.Unknown("Test"))
        val recovered = result.recoverWith { Result.success(42) }

        assertTrue(recovered.isSuccess())
        assertEquals(42, recovered.getOrNull())
    }

    @Test
    fun `recoverWith can propagate failure`() {
        val originalError = Error.Unknown("Original")
        val result: Result<Int> = Result.failure(originalError)
        val newError = Error.Unknown("New")
        val recovered = result.recoverWith { Result.failure(newError) }

        assertTrue(recovered.isFailure())
        assertEquals(newError, recovered.errorOrNull())
    }

    @Test
    fun `zip combines two successful results`() {
        val result1 = Result.success(10)
        val result2 = Result.success("test")

        val zipped = result1.zip(result2)

        assertTrue(zipped.isSuccess())
        assertEquals(Pair(10, "test"), zipped.getOrNull())
    }

    @Test
    fun `zip fails if first result fails`() {
        val error = Error.Unknown("Test")
        val result1: Result<Int> = Result.failure(error)
        val result2 = Result.success("test")

        val zipped = result1.zip(result2)

        assertTrue(zipped.isFailure())
        assertEquals(error, zipped.errorOrNull())
    }

    @Test
    fun `zip fails if second result fails`() {
        val error = Error.Unknown("Test")
        val result1 = Result.success(10)
        val result2: Result<String> = Result.failure(error)

        val zipped = result1.zip(result2)

        assertTrue(zipped.isFailure())
        assertEquals(error, zipped.errorOrNull())
    }

    @Test
    fun `zip with transform combines and transforms`() {
        val result1 = Result.success(10)
        val result2 = Result.success(20)

        val zipped = result1.zip(result2) { a, b -> a + b }

        assertTrue(zipped.isSuccess())
        assertEquals(30, zipped.getOrNull())
    }
}
