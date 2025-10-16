package com.buccancs.core.result

import android.media.MediaCodec
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.*
import java.io.IOException

class CodecResultHelpersTest {

    @Test
    fun `codecOperation wraps successful operation`() {
        val result = codecOperation {
            "success"
        }

        assertTrue(result.isSuccess())
        assertEquals("success", result.getOrNull())
    }

    @Test
    fun `codecOperation converts MediaCodec CodecException to Codec error`() {
        val codecException = MediaCodec.CodecException(
            0, // errorCode
            true, // actionable
            "Codec error"
        )

        val result = codecOperation {
            throw codecException
        }

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Codec)
        assertTrue(error.message.contains("Codec error"))
    }

    @Test
    fun `codecOperation converts IllegalStateException to Codec error`() {
        val result = codecOperation {
            throw IllegalStateException("Invalid codec state")
        }

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Codec)
        assertTrue(error.message.contains("Invalid codec state"))
    }

    @Test
    fun `codecOperation converts IOException to Codec error`() {
        val result = codecOperation {
            throw IOException("Codec I/O error")
        }

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Codec)
        assertTrue(error.message.contains("I/O error"))
    }

    @Test
    fun `codecOperation converts generic exception to Codec error`() {
        val result = codecOperation {
            throw RuntimeException("Unknown codec error")
        }

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Codec)
        assertTrue(error.message.contains("Unexpected codec error"))
    }

    @Test
    fun `safeRelease succeeds for normal codec`() {
        val mockCodec = mock(MediaCodec::class.java)
        val result = mockCodec.safeRelease()

        assertTrue(result.isSuccess())
        verify(mockCodec).stop()
        verify(mockCodec).release()
    }

    @Test
    fun `safeRelease handles stop failure gracefully`() {
        val mockCodec = mock(MediaCodec::class.java)
        doThrow(IllegalStateException("Already stopped")).`when`(mockCodec).stop()

        val result = mockCodec.safeRelease()

        assertTrue(result.isSuccess())
        verify(mockCodec).stop()
        verify(mockCodec).release()
    }

    @Test
    fun `safeRelease fails when release throws`() {
        val mockCodec = mock(MediaCodec::class.java)
        doThrow(IllegalStateException("Already stopped")).`when`(mockCodec).stop()
        doThrow(RuntimeException("Release failed")).`when`(mockCodec).release()

        val result = mockCodec.safeRelease()

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Codec)
        assertTrue(error.message.contains("release"))
    }

    @Test
    fun `safeRelease handles both stop and release failure`() {
        val mockCodec = mock(MediaCodec::class.java)
        doThrow(RuntimeException("Stop failed")).`when`(mockCodec).stop()

        val result = mockCodec.safeRelease()

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Codec)
        assertTrue(error.message.contains("stop/release"))
    }
}
