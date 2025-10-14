package com.buccancs.core.result

import kotlinx.coroutines.CancellationException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ResultTest {

    @Test
    fun `success result contains value`() {
        val result = Result.success(42)

        assertTrue(result.isSuccess())
        assertFalse(result.isFailure())
        assertEquals(42, result.getOrNull())
        assertNull(result.errorOrNull())
    }

    @Test
    fun `failure result contains error`() {
        val error = Error.Unknown("Test error")
        val result = Result.failure(error)

        assertFalse(result.isSuccess())
        assertTrue(result.isFailure())
        assertNull(result.getOrNull())
        assertEquals(error, result.errorOrNull())
    }

    @Test
    fun `map transforms success value`() {
        val result = Result.success(10)
        val mapped = result.map { it * 2 }

        assertTrue(mapped.isSuccess())
        assertEquals(20, mapped.getOrNull())
    }

    @Test
    fun `map preserves failure`() {
        val error = Error.Unknown("Test error")
        val result: Result<Int> = Result.failure(error)
        val mapped = result.map { it * 2 }

        assertTrue(mapped.isFailure())
        assertEquals(error, mapped.errorOrNull())
    }

    @Test
    fun `flatMap chains operations`() {
        val result = Result.success(10)
        val chained = result.flatMap { value ->
            Result.success(value * 2)
        }

        assertTrue(chained.isSuccess())
        assertEquals(20, chained.getOrNull())
    }

    @Test
    fun `flatMap short-circuits on failure`() {
        val error = Error.Unknown("Test error")
        val result: Result<Int> = Result.failure(error)
        val chained = result.flatMap { value ->
            Result.success(value * 2)
        }

        assertTrue(chained.isFailure())
        assertEquals(error, chained.errorOrNull())
    }

    @Test
    fun `onSuccess called for success`() {
        var called = false
        var receivedValue: Int? = null

        Result.success(42)
            .onSuccess {
                called = true
                receivedValue = it
            }

        assertTrue(called)
        assertEquals(42, receivedValue)
    }

    @Test
    fun `onSuccess not called for failure`() {
        var called = false

        Result.failure(Error.Unknown("Test"))
            .onSuccess { called = true }

        assertFalse(called)
    }

    @Test
    fun `onFailure called for failure`() {
        var called = false
        var receivedError: Error? = null

        val error = Error.Unknown("Test error")
        Result.failure(error)
            .onFailure {
                called = true
                receivedError = it
            }

        assertTrue(called)
        assertEquals(error, receivedError)
    }

    @Test
    fun `onFailure not called for success`() {
        var called = false

        Result.success(42)
            .onFailure { called = true }

        assertFalse(called)
    }

    @Test
    fun `getOrThrow returns value for success`() {
        val result = Result.success(42)
        assertEquals(42, result.getOrThrow())
    }

    @Test(expected = UnknownException::class)
    fun `getOrThrow throws for failure`() {
        Result.failure(Error.Unknown("Test error"))
            .getOrThrow()
    }

    @Test
    fun `getOrElse returns value for success`() {
        val result = Result.success(42)
        assertEquals(42, result.getOrElse(0))
    }

    @Test
    fun `getOrElse returns default for failure`() {
        val result: Result<Int> = Result.failure(Error.Unknown("Test"))
        assertEquals(0, result.getOrElse(0))
    }

    @Test
    fun `getOrElse with lambda returns value for success`() {
        val result = Result.success(42)
        assertEquals(42, result.getOrElse { 0 })
    }

    @Test
    fun `getOrElse with lambda returns computed default for failure`() {
        val error = Error.Unknown("Test")
        val result: Result<Int> = Result.failure(error)
        assertEquals(100, result.getOrElse { 100 })
    }

    @Test
    fun `runCatching wraps successful operation`() {
        val result = Result.runCatching {
            42
        }

        assertTrue(result.isSuccess())
        assertEquals(42, result.getOrNull())
    }

    @Test
    fun `runCatching wraps exception`() {
        val result = Result.runCatching {
            throw IllegalArgumentException("Test exception")
        }

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Validation)
    }

    @Test(expected = CancellationException::class)
    fun `runCatching rethrows CancellationException`() {
        Result.runCatching {
            throw CancellationException("Test cancellation")
        }
    }
}
