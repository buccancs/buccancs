package com.buccancs

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Basic sanity tests to verify the test infrastructure is working correctly.
 * These tests ensure that JUnit is properly configured and can execute tests.
 */
class ExampleUnitTest {
    @Test
    fun `addition operation computes correctly`() {
        val result = 2 + 2
        assertEquals(4, result)
    }
    
    @Test
    fun `string concatenation works as expected`() {
        val result = "Buccan" + "CS"
        assertEquals("BuccanCS", result)
    }
    
    @Test
    fun `list operations function correctly`() {
        val list = listOf(1, 2, 3, 4, 5)
        assertEquals(5, list.size)
        assertEquals(1, list.first())
        assertEquals(5, list.last())
        assertEquals(15, list.sum())
    }
    
    @Test
    fun `object instantiation succeeds`() {
        val obj = Any()
        assertNotNull(obj)
    }
}
