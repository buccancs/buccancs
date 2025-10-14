package com.buccancs.data.sensor

import com.buccancs.domain.model.SensorStreamType
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Tests for sensor stream processing logic.
 * These tests validate stream type handling and processing patterns.
 */
class SensorStreamProcessingTest {

    @Test
    fun `sensor stream types are properly defined`() = runTest {
        val types = SensorStreamType.values()
        
        assertNotNull(types)
        assert(types.contains(SensorStreamType.RGB))
        assert(types.contains(SensorStreamType.THERMAL))
        assert(types.contains(SensorStreamType.AUDIO))
        assert(types.contains(SensorStreamType.GSR))
    }

    @Test
    fun `stream type identifiers are unique`() = runTest {
        val types = SensorStreamType.values()
        val names = types.map { it.name }
        
        assertEquals(types.size, names.distinct().size)
    }

    @Test
    fun `all expected stream types are present`() = runTest {
        val types = SensorStreamType.values()
        
        // Verify core stream types exist
        val coreTypes = setOf(
            SensorStreamType.RGB,
            SensorStreamType.THERMAL,
            SensorStreamType.AUDIO,
            SensorStreamType.GSR
        )
        
        assert(types.toSet().containsAll(coreTypes))
    }
}
