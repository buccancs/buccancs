package com.buccancs.data.sensor.config

import android.content.Context
import com.buccancs.domain.model.SensorHardwareConfig
import com.buccancs.domain.model.ShimmerDeviceConfig
import com.buccancs.domain.model.TopdonDeviceConfig
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class DefaultSensorHardwareConfigRepositoryTest {
    private lateinit var context: Context
    private lateinit var scope: TestScope
    private lateinit var json: Json
    private lateinit var repository: DefaultSensorHardwareConfigRepository

    @Before
    fun setup() {
        scope = TestScope()
        context = mockk(relaxed = true)
        json = Json { ignoreUnknownKeys = true; prettyPrint = true }

        // Mock filesDir
        val tempDir = createTempDir()
        every { context.filesDir } returns tempDir

        repository = DefaultSensorHardwareConfigRepository(context, scope, json)
    }

    @Test
    fun `initial config is loaded`() = runTest {
        val config = repository.config.first()
        
        assertNotNull(config)
    }

    @Test
    fun `upsert shimmer device adds new device`() = runTest {
        val device = ShimmerDeviceConfig(
            id = "shimmer-1",
            name = "Test Shimmer",
            mac = "00:11:22:33:44:55"
        )

        repository.upsertShimmerDevice(device)

        val config = repository.config.first()
        val shimmerDevices = config.shimmer
        assertTrue(shimmerDevices.any { it.id == "shimmer-1" })
    }

    @Test
    fun `upsert shimmer device updates existing device`() = runTest {
        val device1 = ShimmerDeviceConfig(
            id = "shimmer-1",
            name = "Original Name",
            mac = "00:11:22:33:44:55"
        )
        val device2 = ShimmerDeviceConfig(
            id = "shimmer-1",
            name = "Updated Name",
            mac = "00:11:22:33:44:55"
        )

        repository.upsertShimmerDevice(device1)
        repository.upsertShimmerDevice(device2)

        val config = repository.config.first()
        val shimmerDevice = config.shimmer.find { it.id == "shimmer-1" }
        assertEquals("Updated Name", shimmerDevice?.name)
    }

    @Test
    fun `upsert topdon device adds new device`() = runTest {
        val device = TopdonDeviceConfig(
            id = "topdon-1",
            name = "Test Topdon",
            usbProductId = 12345,
            usbVendorId = 67890
        )

        repository.upsertTopdonDevice(device)

        val config = repository.config.first()
        val topdonDevices = config.topdon
        assertTrue(topdonDevices.any { it.id == "topdon-1" })
    }

    @Test
    fun `update config applies transformation`() = runTest {
        repository.updateConfig { current ->
            current.copy(
                shimmer = listOf(
                    ShimmerDeviceConfig("shimmer-test", "Test", "AA:BB:CC:DD:EE:FF")
                )
            )
        }

        val config = repository.config.first()
        assertEquals(1, config.shimmer.size)
        assertEquals("shimmer-test", config.shimmer[0].id)
    }

    @Test
    fun `reload refreshes config from storage`() = runTest {
        repository.reload()

        val config = repository.config.first()
        assertNotNull(config)
    }

    @Test
    fun `devices are sorted by id after upsert`() = runTest {
        repository.upsertShimmerDevice(ShimmerDeviceConfig("shimmer-3", "Third", "AA:AA:AA:AA:AA:AA"))
        repository.upsertShimmerDevice(ShimmerDeviceConfig("shimmer-1", "First", "BB:BB:BB:BB:BB:BB"))
        repository.upsertShimmerDevice(ShimmerDeviceConfig("shimmer-2", "Second", "CC:CC:CC:CC:CC:CC"))

        val config = repository.config.first()
        val ids = config.shimmer.map { it.id }
        assertEquals(listOf("shimmer-1", "shimmer-2", "shimmer-3"), ids)
    }
}
