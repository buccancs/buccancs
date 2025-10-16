**Last Modified:** 2025-10-15 20:52 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Guide

# Testing Guide

Comprehensive testing guide for BuccanCS covering dependency injection testing, test execution, and best practices.

## Contents

- [Test Execution](#test-execution)
- [Dependency Injection Testing](#dependency-injection-testing)
- [Best Practices](#best-practices)
- [Quick Reference](#quick-reference)

## Test Execution

### Quick Start

Tests are disabled by default but can be enabled via command-line flag:

```bash
# Enable and run all tests
./gradlew test -Ptests.enabled=true

# Enable and run app module tests only
./gradlew :app:test -Ptests.enabled=true

# Enable and run specific test class
./gradlew :app:test -Ptests.enabled=true --tests "DefaultSensorRepositoryTest"

# Enable and run tests matching pattern
./gradlew :app:test -Ptests.enabled=true --tests "*RepositoryTest"
```

### Why Are Tests Disabled by Default?

Tests are disabled in `build.gradle.kts` to prevent build failures during testing infrastructure setup. This allows
incremental test implementation without breaking builds.

**When to Enable Tests:**

- During development when writing/running tests
- In CI/CD pipelines
- Before committing changes
- During code reviews

### Test Commands Reference

#### Running All Tests

```bash
# All modules
./gradlew test -Ptests.enabled=true

# Specific module
./gradlew :app:test -Ptests.enabled=true
./gradlew :desktop:test -Ptests.enabled=true
./gradlew :protocol:test -Ptests.enabled=true
```

#### Running Specific Tests

```bash
# Single test class
./gradlew :app:test -Ptests.enabled=true --tests "TimeSyncMathTest"

# Single test method
./gradlew :app:test -Ptests.enabled=true --tests "TimeSyncMathTest.computeSyncSample returns zero offset for symmetric exchange"

# All tests in package
./gradlew :app:test -Ptests.enabled=true --tests "com.buccancs.data.sensor.*"

# Pattern matching
./gradlew :app:test -Ptests.enabled=true --tests "*ModuleTest"
./gradlew :app:test -Ptests.enabled=true --tests "*ViewModelTest"
```

#### Test Output Options

```bash
# Detailed output
./gradlew test -Ptests.enabled=true --info

# Debug output
./gradlew test -Ptests.enabled=true --debug

# Show standard out/err
./gradlew test -Ptests.enabled=true --rerun-tasks

# Continue on failure
./gradlew test -Ptests.enabled=true --continue
```

#### Coverage Reports

```bash
# Run tests with coverage
./gradlew test -Ptests.enabled=true jacocoTestReport

# View coverage report (platform-specific)
open app/build/reports/jacoco/test/html/index.html  # Mac
start app/build/reports/jacoco/test/html/index.html  # Windows
xdg-open app/build/reports/jacoco/test/html/index.html  # Linux
```

### Test Reports

After test execution, reports are available:

```
app/build/reports/tests/testDebugUnitTest/index.html
app/build/test-results/testDebugUnitTest/
```

## Dependency Injection Testing

### Overview

BuccanCS uses Hilt for dependency injection. Tests verify the DI graph is correctly configured and components work
together.

### Module Testing

Module tests verify that Hilt dependency graph is correctly configured, catching configuration errors at test time
rather than runtime.

#### Basic Pattern

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

#### Testing Repository Bindings

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
        val repo1 = sensorRepository
        hiltRule.inject()
        val repo2 = sensorRepository
        assertSame(repo1, repo2)
    }
}
```

#### Testing Multibindings

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
        
        val types = sensorConnectors.map { it::class.simpleName }
        assertTrue("RgbCameraConnector" in types)
        assertTrue("MicrophoneConnector" in types)
    }
}
```

#### Testing Custom Qualifiers

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

### Hardware Service Mocking

Hardware services (CameraManager, BluetoothAdapter, UsbManager) are difficult to test. BuccanCS uses wrapper interfaces
with TestHardwareModule to provide mocks automatically.

#### Why Mock Hardware Services?

- Require actual hardware or complex Robolectric setup
- Tightly coupled to Android framework
- Error conditions hard to simulate

#### Using TestHardwareModule

`TestHardwareModule` automatically replaces production hardware services with MockK mocks:

```kotlin
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class MyFeatureTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var bluetoothService: BluetoothService  // Automatically mocked

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `feature works when bluetooth is enabled`() {
        every { bluetoothService.isEnabled() } returns true
        
        // Test your feature
        
        verify { bluetoothService.isEnabled() }
    }
}
```

#### Simulating Hardware States

```kotlin
@Test
fun `handles bluetooth disabled`() {
    every { bluetoothService.isEnabled() } returns false
    every { bluetoothService.isAvailable() } returns true
    // Test logic
}

@Test
fun `handles bluetooth unavailable`() {
    every { bluetoothService.isAvailable() } returns false
    // Test logic
}

@Test
fun `handles camera access error`() {
    every { cameraService.getCameraIdList() } throws 
        SecurityException("Camera permission denied")
    // Test error handling
}
```

#### Testing Multiple Scenarios

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

### Integration Testing Patterns

#### Testing Repository with Hardware Service

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
        every { bluetoothService.isEnabled() } returns true
        every { bluetoothService.getBondedDevices() } returns setOf(mockk())
        every { cameraService.getCameraIdList() } returns listOf("0", "1")

        val sensors = sensorRepository.discoverSensors()
        assertTrue(sensors.isNotEmpty())
    }

    @Test
    fun `handles hardware unavailable gracefully`() = runTest {
        every { bluetoothService.isAvailable() } returns false
        every { cameraService.getCameraIdList() } returns emptyList()

        val sensors = sensorRepository.discoverSensors()
        assertNotNull(sensors)
    }
}
```

#### Testing ViewModels

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
        every { bluetoothService.isEnabled() } returns true
        coEvery { repository.discoverSensors() } returns listOf(mockSensor())

        viewModel.loadSensors()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(1, state.sensors.size)
    }
}
```

#### Testing with Coroutines

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
            delay(100)
        }
        
        job.join()
        assertTrue(job.isCompleted)
    }
}
```

## Best Practices

### Use Constructor Injection

```kotlin
// GOOD
class MyRepository @Inject constructor(
    private val bluetoothService: BluetoothService,
    private val dispatchers: CoroutineDispatchers
) {
    // Implementation
}

// AVOID
class MyRepository {
    @Inject lateinit var bluetoothService: BluetoothService
}
```

### Inject Interfaces, Not Implementations

```kotlin
// GOOD
@Inject lateinit var repository: SensorRepository

// AVOID
@Inject lateinit var repository: DefaultSensorRepository
```

### Use Appropriate Scopes

```kotlin
// Singleton for stateful services
@Singleton
class CacheManager @Inject constructor()

// Unscoped for stateless utilities
class JsonParser @Inject constructor()
```

### Mock Only What You Need

```kotlin
@Test
fun `test specific behavior`() {
    every { service.isEnabled() } returns true
    // Don't configure unused methods
}
```

### Verify Critical Interactions

```kotlin
@Test
fun `saves data when button clicked`() {
    viewModel.onSaveClicked()
    verify { repository.save(any()) }
}
```

## Quick Reference

### Test Template

```kotlin
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class MyTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject lateinit var dependency: MyInterface

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun myTest() {
        // Test code
    }
}
```

### Common Injection Patterns

```kotlin
// Repository
@Inject lateinit var sensorRepository: SensorRepository

// Hardware Service (auto-mocked)
@Inject lateinit var bluetoothService: BluetoothService
@Inject lateinit var cameraService: CameraService
@Inject lateinit var usbService: UsbService

// Qualified Dependency
@Inject @ApplicationScope
lateinit var appScope: CoroutineScope

// Collection (multibinding)
@Inject 
lateinit var connectors: Set<@JvmSuppressWildcards SensorConnector>
```

### MockK Cheat Sheet

```kotlin
// Basic Mocking
every { service.getData() } returns testData
every { service.isEnabled() } returns true

// Suspend Functions
coEvery { repository.fetch() } returns Result.success(data)

// Throwing Exceptions
every { service.getData() } throws IOException("Network error")

// Argument Matching
every { service.save(any()) } returns true
every { service.find(eq("test")) } returns item
every { service.process(match { it.size > 5 }) } returns result

// Verification
verify { service.getData() }
verify(exactly = 2) { service.getData() }
verify { service.save(any()) }
coVerify { repository.fetch() }

// Capture Arguments
val slot = slot<String>()
verify { service.save(capture(slot)) }
assertEquals("expected", slot.captured)
```

### Hardware Service Scenarios

```kotlin
// Bluetooth Enabled
every { bluetoothService.isEnabled() } returns true
every { bluetoothService.isAvailable() } returns true

// Bluetooth Disabled
every { bluetoothService.isEnabled() } returns false
every { bluetoothService.isAvailable() } returns true

// Bluetooth Unavailable
every { bluetoothService.isAvailable() } returns false

// Multiple Cameras
every { cameraService.getCameraIdList() } returns listOf("0", "1")

// No Cameras
every { cameraService.getCameraIdList() } returns emptyList()

// Camera Error
every { cameraService.getCameraIdList() } throws 
    SecurityException("Permission denied")

// USB Devices
val device = mockk<UsbDevice>()
every { usbService.getDeviceList() } returns mapOf("device1" to device)
every { usbService.hasPermission(device) } returns true
```

### Coroutine Testing

```kotlin
// Basic Test
@Test
fun myTest() = runTest {
    val result = repository.fetchData()
    assertNotNull(result)
}

// Advance Time
@Test
fun delayedOperation() = runTest {
    launch { 
        delay(1000)
        doSomething()
    }
    advanceTimeBy(1000)
    // Verify doSomething() was called
}

// Wait for Completion
@Test
fun asyncTest() = runTest {
    val job = launch { asyncWork() }
    job.join()
    assertTrue(job.isCompleted)
}
```

### Assertion Library

```kotlin
// Kotlin Test
assertNotNull(value)
assertEquals(expected, actual)
assertTrue(condition)
assertFalse(condition)
assertSame(expected, actual)
assertContains(collection, element)

// JUnit
Assert.assertNotNull(value)
Assert.assertEquals(expected, actual)
Assert.assertTrue(condition)
```

### Common Test Commands

```bash
# All tests
./gradlew :app:testDebugUnitTest -Ptests.enabled=true

# Module tests only
./gradlew :app:testDebugUnitTest -Ptests.enabled=true --tests "*ModuleTest"

# Specific test
./gradlew :app:testDebugUnitTest -Ptests.enabled=true --tests "MyTest"

# With coverage
./gradlew :app:testDebugUnitTest -Ptests.enabled=true jacocoTestReport

# Continuous
./gradlew :app:testDebugUnitTest -Ptests.enabled=true --continuous
```

### Common Pitfalls

#### Forgetting to Call inject()

```kotlin
@Before
fun setup() {
    hiltRule.inject()  // MUST call this
}
```

#### Wrong Test Runner

```kotlin
// WRONG
@RunWith(JUnit4::class)

// CORRECT
@RunWith(RobolectricTestRunner::class)
```

#### Missing HiltTestApplication

```kotlin
// Must specify
@Config(application = HiltTestApplication::class)
```

#### Injecting in Wrong Lifecycle

```kotlin
class MyTest {
    @Inject lateinit var service: MyService
    
    // WRONG - inject() not called yet
    private val helper = ServiceHelper(service)
    
    @Before
    fun setup() {
        hiltRule.inject()
        // CORRECT - inject() called
        val helper = ServiceHelper(service)
    }
}
```

#### Not Using @JvmSuppressWildcards

```kotlin
// WRONG
@Inject lateinit var connectors: Set<SensorConnector>

// CORRECT
@Inject lateinit var connectors: Set<@JvmSuppressWildcards SensorConnector>
```

### Troubleshooting

**Unresolved Reference**

- Ensure `hiltRule.inject()` is called in `@Before`
- Check imports: `import javax.inject.Inject`

**NullPointerException**

- Did you call `hiltRule.inject()`?
- Is `@Inject` annotation present?

**Wrong Mock Returned**

- Using `TestHardwareModule` automatically in tests
- Production code uses real services

**Verification Failed**

- Mock not configured: add `every { ... } returns ...`
- Check method signature matches exactly

**Test Hangs**

- Missing `advanceUntilIdle()` in coroutine test
- Infinite loop in code under test

### File Locations

```
Hardware Services:
  app/src/main/java/com/buccancs/hardware/

DI Modules:
  app/src/main/java/com/buccancs/di/

Module Tests:
  app/src/test/java/com/buccancs/di/

Test Module:
  app/src/test/java/com/buccancs/di/TestHardwareModule.kt

Example Tests:
  app/src/test/java/com/buccancs/hardware/HardwareServiceIntegrationTest.kt
```

### Dependencies

```kotlin
// build.gradle.kts
testImplementation(libs.junit.junit)
testImplementation(libs.hilt.android.testing)
testImplementation(libs.robolectric)
testImplementation("io.mockk:mockk:1.13.9")
testImplementation("org.jetbrains.kotlin:kotlin-test")
kaptTest(libs.hilt.compiler)
```

### Key Annotations

- `@HiltAndroidTest` - Marks test class for Hilt
- `@RunWith(RobolectricTestRunner::class)` - Android test runner
- `@Config(application = HiltTestApplication::class)` - Test app
- `@get:Rule` - JUnit rule for Hilt
- `@Before` - Setup method
- `@Test` - Test method
- `@Inject` - Dependency injection
- `@JvmSuppressWildcards` - For generic collections

## Related Documentation

- **DI Architecture**: `docs/architecture/DI_ARCHITECTURE_2025-10-14.md`
- **DI Analysis**: `docs/analysis/DI_QUALITY_ANALYSIS_2025-10-14.md`
- **DI Implementation**: `docs/project/DI_FIXES_IMPLEMENTATION_2025-10-14.md`
- **Error Handling Testing**: See error-handling-guide-2025-10-15.md

