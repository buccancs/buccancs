package com.buccancs

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented tests that run on an Android device.
 * These tests verify that the app can be installed and basic Android context is available.
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.buccancs", appContext.packageName)
    }
    
    @Test
    fun verifyInstrumentationAvailable() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        assertNotNull("Instrumentation should be available", instrumentation)
    }
    
    @Test
    fun verifyTargetContextHasResources() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val resources = appContext.resources
        assertNotNull("Resources should be available", resources)
    }
    
    @Test
    fun verifyAppPackageName() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertTrue("Package name should start with com.buccancs",
            appContext.packageName.startsWith("com.buccancs"))
    }
    
    @Test
    fun verifyApplicationObjectExists() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val application = appContext.applicationContext
        assertNotNull("Application context should exist", application)
    }
}
