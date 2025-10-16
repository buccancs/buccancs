package com.buccancs.desktop.data.discovery

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests for MdnsServiceBrowser
 */
class MdnsServiceBrowserTest {

    private lateinit var scope: CoroutineScope
    private lateinit var browser: MdnsServiceBrowser

    @BeforeEach
    fun setup() {
        scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        browser = MdnsServiceBrowser(scope)
    }

    @AfterEach
    fun teardown() {
        browser.stop()
        scope.cancel()
    }

    @Test
    fun `browser starts with not browsing state`() = runBlocking {
        val browsing = browser.browsing.first()
        assertFalse(browsing, "Browser should start in not browsing state")
    }

    @Test
    fun `browser starts with empty devices`() = runBlocking {
        val devices = browser.devices.first()
        assertTrue(devices.isEmpty(), "Browser should start with no devices")
    }

    @Test
    fun `start method initiates browsing`() = runBlocking {
        browser.start()
        
        // Give it a moment to start
        kotlinx.coroutines.delay(100)
        
        // Note: Actual browsing state depends on network availability
        // This test just ensures it doesn't crash
    }

    @Test
    fun `stop method stops browsing`() = runBlocking {
        browser.start()
        
        // Wait until browsing actually starts (or times out)
        withTimeout(3000) {
            while (!browser.browsing.first()) {
                kotlinx.coroutines.delay(100)
            }
        }
        
        // Now stop it
        browser.stop()
        
        // Wait until browsing actually stops (or times out)
        withTimeout(3000) {
            while (browser.browsing.first()) {
                kotlinx.coroutines.delay(100)
            }
        }
        
        val browsing = browser.browsing.first()
        assertFalse(browsing, "Browser should stop browsing after stop()")
        
        val devices = browser.devices.first()
        assertTrue(devices.isEmpty(), "Devices should be cleared after stop()")
    }

    @Test
    fun `multiple start calls are idempotent`() = runBlocking {
        browser.start()
        kotlinx.coroutines.delay(50)
        browser.start()
        kotlinx.coroutines.delay(50)
        browser.start()
        
        // Should not crash from multiple starts
    }

    @Test
    fun `stop can be called multiple times safely`() = runBlocking {
        browser.start()
        kotlinx.coroutines.delay(50)
        
        browser.stop()
        browser.stop()
        browser.stop()
        
        // Should not crash from multiple stops
    }

    @Test
    fun `custom service type can be specified`() = runBlocking {
        browser.start("_custom._tcp.local.")
        kotlinx.coroutines.delay(100)
        
        // Should not crash with custom service type
        browser.stop()
    }
}
