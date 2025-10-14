**Last Modified:** 2025-10-14 04:00 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Guide

# Dependency Injection Testing Guide

## Overview

This guide demonstrates how to write tests using the project's Hilt DI infrastructure, including module verification tests and integration tests with mocked hardware services.

## Table of Contents

1. [Module Testing](#module-testing)
2. [Hardware Service Mocking](#hardware-service-mocking)
3. [Integration Testing Patterns](#integration-testing-patterns)
4. [Best Practices](#best-practices)
5. [Common Pitfalls](#common-pitfalls)

## Module Testing

### Purpose

Module tests verify that your Hilt dependency graph is correctly configured. They catch configuration errors at test time rather than runtime.

### Basic Pattern

```kotlin
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class MyModuleTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject lateinit var myDependency: MyInterface

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `dependency is injectable`() {
        assertNotNull(myDependency)
    }
}
```

### Testing Repository Bindings

```kotlin
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class RepositoryModuleTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject lateinit var sensorRepository: SensorRepository
    @Inject lateinit var calibrationRepository: CalibrationRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `all repositories are injectable`() {
        assertNotNull(sensorRepository)
        assertNotNull(calibrationRepository)
    }

    @Test
    fun `repository is singleton`() {
        // Inject again to verify same instance
        val repo1 = sensorRepository
        hiltRule.inject()
        val repo2 = sensorRepository
        assertSame(repo1, repo2)
    }
}
```

### Testing Multibindings

For modules using `@IntoSet` or `@IntoMap`:

```kotlin
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class SensorConnectorModuleTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject 
    lateinit var sensorConnectors: Set<@JvmSuppressWildcards SensorConnector>

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `all expected connectors are present`() {
        assertTrue(sensorConnectors.isNotEmpty())
        assertEquals(2, sensorConnectors.size)
        
        // Verify specific types
        val types = sensorConnectors.map { it::class.simpleName }
        assertTrue("RgbCameraConnector" in types)
        assertTrue("MicrophoneConnector" in types)
    }
}
```

### Testing Custom Qualifiers

```kotlin
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class CoroutineModuleTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject 
    @ApplicationScope
    lateinit var applicationScope: CoroutineScope

    @Inject 
    lateinit var dispatchers: CoroutineDispatchers

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `application scope is injectable`() {
        assertNotNull(applicationScope)
    }

    @Test
    fun `dispatchers are properly configured`() {
        assertNotNull(dispatchers.main)
        assertNotNull(dispatchers.io)
        assertNotNull(dispatchers.default)
    }
}
```

## Hardware Service Mocking

### Why Mock Hardware Services?

Hardware services (`CameraManager`, `BluetoothAdapter`, `UsbManager`) are difficult to test because:
- They require actual hardware or complex Robolectric setup
- They're tightly coupled to the Android framework
- Error conditions are hard to simulate

Our wrapper interfaces solve this:

```kotlin
// Production code
interface BluetoothService {
    fun isEnabled(): Boolean
    fun getBondedDevices(): Set<BluetoothDevice>
}

// Test code gets mocks automatically via TestHardwareModule
```

### Using TestHardwareModule

`TestHardwareModule` automatically replaces production hardware services with MockK mocks:

```kotlin
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class MyFeatureTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var bluetoothService: BluetoothService  // Automatically mocked!

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `feature works when bluetooth is enabled`() {
        // Configure mock
        every { bluetoothService.isEnabled() } returns true
        
        // Test your feature
        // ...
        
        // Verify interactions
        verify { bluetoothService.isEnabled() }
    }
}
```

### Simulating Hardware States

```kotlin
@Test
fun `handles bluetooth disabled`() {
    every { bluetoothService.isEnabled() } returns false
    every { bluetoothService.isAvailable() } returns true
    
    // Test logic when BT is disabled but available
}

@Test
fun `handles bluetooth unavailable`() {
    every { bluetoothService.isAvailable() } returns false
    
    // Test logic when BT hardware doesn't exist
}

@Test
fun `handles camera access error`() {
    every { cameraService.getCameraIdList() } throws 
        SecurityException("Camera permission denied")
    
    // Test error handling
}
```

### Testing Multiple Scenarios

```kotlin
@Test
fun `discovery works with various device counts`() {
    // No devices
    every { bluetoothService.getBondedDevices() } returns emptySet()
    assertFalse(myFeature.hasDevices())
    
    // One device
    val device = mockk<BluetoothDevice>()
    every { bluetoothService.getBondedDevices() } returns setOf(device)
    assertTrue(myFeature.hasDevices())
    
    // Multiple devices
    val devices = setOf(mockk(), mockk(), mockk())
    every { bluetoothService.getBondedDevices() } returns devices
    assertEquals(3, myFeature.getDeviceCount())
}
```

## Integration Testing Patterns

### Testing a Repository with Hardware Service

```kotlin
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class SensorRepositoryIntegrationTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject lateinit var sensorRepository: SensorRepository
    @Inject lateinit var bluetoothService: BluetoothService
    @Inject lateinit var cameraService: CameraService

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `discovers sensors when hardware available`() = runTest {
        // Configure mocks
        every { bluetoothService.isEnabled() } returns true
        every { bluetoothService.getBondedDevices() } returns setOf(mockk())
        every { cameraService.getCameraIdList() } returns listOf("0", "1")

        // Test
        val sensors = sensorRepository.discoverSensors()
        assertTrue(sensors.isNotEmpty())
    }

    @Test
    fun `handles hardware unavailable gracefully`() = runTest {
        // Configure error state
        every { bluetoothService.isAvailable() } returns false
        every { cameraService.getCameraIdList() } returns emptyList()

        // Should not crash
        val sensors = sensorRepository.discoverSensors()
        assertNotNull(sensors)
    }
}
```

### Testing ViewModels with Dependencies

```kotlin
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class SensorViewModelTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject lateinit var repository: SensorRepository
    @Inject lateinit var bluetoothService: BluetoothService
    
    private lateinit var viewModel: SensorViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = SensorViewModel(repository)
    }

    @Test
    fun `loads sensors on init`() = runTest {
        // Configure
        every { bluetoothService.isEnabled() } returns true
        coEvery { repository.discoverSensors() } returns listOf(mockSensor())

        // Trigger load
        viewModel.loadSensors()
        advanceUntilIdle()

        // Verify
        val state = viewModel.state.value
        assertEquals(1, state.sensors.size)
    }
}
```

### Testing with Coroutines

```kotlin
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class AsyncFeatureTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject 
    @ApplicationScope
    lateinit var appScope: CoroutineScope
    
    @Inject 
    lateinit var dispatchers: CoroutineDispatchers

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `async operation completes`() = runTest {
        val job = appScope.launch(dispatchers.io) {
            // Some async work
            delay(100)
        }
        
        job.join()
        assertTrue(job.isCompleted)
    }
}
```

## Best Practices

### 1. Use Constructor Injection

**Good:**
```kotlin
class MyRepository @Inject constructor(
    private val bluetoothService: BluetoothService,
    private val dispatchers: CoroutineDispatchers
) {
    // Implementation
}
```

**Avoid:**
```kotlin
class MyRepository {
    @Inject lateinit var bluetoothService: BluetoothService
    // Field injection harder to test
}
```

### 2. Inject Interfaces, Not Implementations

**Good:**
```kotlin
@Inject lateinit var repository: SensorRepository
```

**Avoid:**
```kotlin
@Inject lateinit var repository: DefaultSensorRepository
```

### 3. Use Appropriate Scopes

```kotlin
// Singleton for stateful services
@Singleton
class CacheManager @Inject constructor()

// Unscoped for stateless utilities
class JsonParser @Inject constructor()
```

### 4. Mock Only What You Need

```kotlin
@Test
fun `test specific behavior`() {
    // Configure only the method under test
    every { service.isEnabled() } returns true
    // Don't configure unused methods
}
```

### 5. Verify Critical Interactions

```kotlin
@Test
fun `saves data when button clicked`() {
    viewModel.onSaveClicked()
    
    // Verify the save actually happened
    verify { repository.save(any()) }
}
```

## Common Pitfalls

### 1. Forgetting to Call inject()

```kotlin
@Before
fun setup() {
    hiltRule.inject()  // MUST call this!
}
```

Without calling `inject()`, your `@Inject` fields remain null.

### 2. Wrong Test Runner

```kotlin
// Wrong - regular JUnit runner doesn't support Hilt
@RunWith(JUnit4::class)

// Correct - use Robolectric for Android tests
@RunWith(RobolectricTestRunner::class)
```

### 3. Missing HiltTestApplication

```kotlin
// Must specify Hilt test application
@Config(application = HiltTestApplication::class)
```

### 4. Injecting in Wrong Lifecycle

```kotlin
class MyTest {
    @Inject lateinit var service: MyService
    
    // Wrong - inject() not called yet
    private val helper = ServiceHelper(service)
    
    @Before
    fun setup() {
        hiltRule.inject()
        // Correct - inject() called, safe to use
        val helper = ServiceHelper(service)
    }
}
```

### 5. Not Using @JvmSuppressWildcards for Collections

```kotlin
// Wrong - compilation error
@Inject lateinit var connectors: Set<SensorConnector>

// Correct - suppresses wildcard types
@Inject lateinit var connectors: Set<@JvmSuppressWildcards SensorConnector>
```

### 6. Mocking Concrete Classes Instead of Interfaces

```kotlin
// Difficult to mock
@Inject lateinit var manager: CameraManager

// Easy to mock
@Inject lateinit var service: CameraService
```

## Running Tests

### Run All Module Tests

```bash
./gradlew :app:testDebugUnitTest --tests "*ModuleTest"
```

### Run Specific Test

```bash
./gradlew :app:testDebugUnitTest --tests "RepositoryModuleTest"
```

### Run with Coverage

```bash
./gradlew :app:testDebugUnitTest jacocoTestReport
```

### Debug Test in IDE

1. Right-click test class/method
2. Select "Debug 'TestName'"
3. Set breakpoints as needed

## Additional Resources

- [Hilt Testing Guide](https://developer.android.com/training/dependency-injection/hilt-testing)
- [MockK Documentation](https://mockk.io/)
- [Robolectric Documentation](http://robolectric.org/)
- Project DI Analysis: `docs/analysis/DI_QUALITY_ANALYSIS_2025-10-14.md`
- Implementation Details: `docs/project/DI_FIXES_IMPLEMENTATION_2025-10-14.md`

## Example Test Suite Structure

```
app/src/test/java/com/buccancs/
├── di/                                  # Module tests
│   ├── RepositoryModuleTest.kt
│   ├── ServiceModuleTest.kt
│   ├── HardwareModuleTest.kt
│   └── TestHardwareModule.kt           # Mock module
├── hardware/                            # Service tests
│   └── HardwareServiceIntegrationTest.kt
├── data/
│   └── sensor/
│       └── SensorRepositoryTest.kt     # Integration tests
└── ui/
    └── sensors/
        └── SensorViewModelTest.kt      # ViewModel tests
```

## Summary

The project's DI testing infrastructure provides:

1. **Module verification tests** - Catch configuration errors early
2. **Mockable hardware services** - Test without real hardware
3. **Integration test support** - Test components together
4. **Clear patterns** - Consistent approach across codebase

Follow these patterns to write fast, reliable, maintainable tests.
