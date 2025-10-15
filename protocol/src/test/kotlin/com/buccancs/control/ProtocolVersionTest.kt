package com.buccancs.control

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ProtocolVersionTest {
    
    @Test
    fun `current version is v1_0`() {
        assertEquals(1000, ProtocolVersion.CURRENT)
        assertEquals("v1.0", ProtocolVersion.versionString(ProtocolVersion.CURRENT))
    }
    
    @Test
    fun `version string formatting`() {
        assertEquals("v1.0", ProtocolVersion.versionString(1000))
        assertEquals("v1.5", ProtocolVersion.versionString(1005))
        assertEquals("v2.0", ProtocolVersion.versionString(2000))
        assertEquals("v2.15", ProtocolVersion.versionString(2015))
    }
    
    @Test
    fun `major and minor version extraction`() {
        assertEquals(1, ProtocolVersion.majorVersion(1000))
        assertEquals(0, ProtocolVersion.minorVersion(1000))
        
        assertEquals(1, ProtocolVersion.majorVersion(1005))
        assertEquals(5, ProtocolVersion.minorVersion(1005))
        
        assertEquals(2, ProtocolVersion.majorVersion(2015))
        assertEquals(15, ProtocolVersion.minorVersion(2015))
    }
    
    @Test
    fun `same version is supported`() {
        assertTrue(ProtocolVersion.isSupported(1000))
        assertTrue(ProtocolVersion.isCompatible(1000))
    }
    
    @Test
    fun `minor version bump is compatible`() {
        assertTrue(ProtocolVersion.isSupported(1001))
        assertTrue(ProtocolVersion.isCompatible(1001))
    }
    
    @Test
    fun `old major version is not compatible`() {
        assertFalse(ProtocolVersion.isSupported(999))
        assertFalse(ProtocolVersion.isCompatible(999))
        assertFalse(ProtocolVersion.isSupported(500))
        assertFalse(ProtocolVersion.isCompatible(500))
    }
    
    @Test
    fun `future major version is not compatible`() {
        assertFalse(ProtocolVersion.isSupported(2000))
        assertFalse(ProtocolVersion.isCompatible(2000))
    }
    
    @Test
    fun `requireSupported throws on incompatible version`() {
        val exception = assertThrows<IllegalArgumentException> {
            ProtocolVersion.requireSupported(2000)
        }
        assertTrue(exception.message!!.contains("Unsupported protocol version"))
        assertTrue(exception.message!!.contains("v2.0"))
    }
    
    @Test
    fun `requireSupported passes on compatible version`() {
        ProtocolVersion.requireSupported(1000)
        ProtocolVersion.requireSupported(1001)
    }
}
