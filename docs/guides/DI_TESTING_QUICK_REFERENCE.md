**Last Modified:** 2025-10-14 04:00 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Quick Reference

# DI Testing Quick Reference

## Test Template

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

## Common Injection Patterns

### Repository

```kotlin
@Inject lateinit var sensorRepository: SensorRepository
```

### Hardware Service (auto-mocked)

```kotlin
@Inject lateinit var bluetoothService: BluetoothService
@Inject lateinit var cameraService: CameraService
@Inject lateinit var usbService: UsbService
```

### Qualified Dependency

```kotlin
@Inject @ApplicationScope
lateinit var appScope: CoroutineScope
```

### Collection (multibinding)

```kotlin
@Inject 
lateinit var connectors: Set<@JvmSuppressWildcards SensorConnector>
```

## MockK Cheat Sheet

### Basic Mocking

```kotlin
every { service.getData() } returns testData
every { service.isEnabled() } returns true
```

### Suspend Functions

```kotlin
coEvery { repository.fetch() } returns Result.success(data)
```

### Throwing Exceptions

```kotlin
every { service.getData() } throws IOException("Network error")
```

### Argument Matching

```kotlin
every { service.save(any()) } returns true
every { service.find(eq("test")) } returns item
every { service.process(match { it.size > 5 }) } returns result
```

### Verification

```kotlin
verify { service.getData() }
verify(exactly = 2) { service.getData() }
verify { service.save(any()) }
coVerify { repository.fetch() }
```

### Capture Arguments

```kotlin
val slot = slot<String>()
verify { service.save(capture(slot)) }
assertEquals("expected", slot.captured)
```

## Hardware Service Scenarios

### Bluetooth Enabled

```kotlin
every { bluetoothService.isEnabled() } returns true
every { bluetoothService.isAvailable() } returns true
```

### Bluetooth Disabled

```kotlin
every { bluetoothService.isEnabled() } returns false
every { bluetoothService.isAvailable() } returns true
```

### Bluetooth Unavailable

```kotlin
every { bluetoothService.isAvailable() } returns false
```

### Multiple Cameras

```kotlin
every { cameraService.getCameraIdList() } returns listOf("0", "1")
```

### No Cameras

```kotlin
every { cameraService.getCameraIdList() } returns emptyList()
```

### Camera Error

```kotlin
every { cameraService.getCameraIdList() } throws 
    SecurityException("Permission denied")
```

### USB Devices

```kotlin
val device = mockk<UsbDevice>()
every { usbService.getDeviceList() } returns mapOf("device1" to device)
every { usbService.hasPermission(device) } returns true
```

## Coroutine Testing

### Basic Test

```kotlin
@Test
fun myTest() = runTest {
    val result = repository.fetchData()
    assertNotNull(result)
}
```

### Advance Time

```kotlin
@Test
fun delayedOperation() = runTest {
    launch { 
        delay(1000)
        doSomething()
    }
    advanceTimeBy(1000)
    // Verify doSomething() was called
}
```

### Wait for Completion

```kotlin
@Test
fun asyncTest() = runTest {
    val job = launch { asyncWork() }
    job.join()
    assertTrue(job.isCompleted)
}
```

## Assertion Library

### Kotlin Test

```kotlin
assertNotNull(value)
assertEquals(expected, actual)
assertTrue(condition)
assertFalse(condition)
assertSame(expected, actual)
assertContains(collection, element)
```

### JUnit

```kotlin
Assert.assertNotNull(value)
Assert.assertEquals(expected, actual)
Assert.assertTrue(condition)
```

## Common Test Commands

```bash
# All tests
./gradlew :app:testDebugUnitTest

# Module tests only
./gradlew :app:testDebugUnitTest --tests "*ModuleTest"

# Specific test
./gradlew :app:testDebugUnitTest --tests "MyTest"

# With coverage
./gradlew :app:testDebugUnitTest jacocoTestReport

# Continuous
./gradlew :app:testDebugUnitTest --continuous
```

## Troubleshooting

### Unresolved Reference

- Ensure `hiltRule.inject()` is called in `@Before`
- Check imports: `import javax.inject.Inject`

### NullPointerException

- Did you call `hiltRule.inject()`?
- Is `@Inject` annotation present?

### Wrong Mock Returned

- Using `TestHardwareModule` automatically in tests
- Production code uses real services

### Verification Failed

- Mock not configured: add `every { ... } returns ...`
- Check method signature matches exactly

### Test Hangs

- Missing `advanceUntilIdle()` in coroutine test
- Infinite loop in code under test

## File Locations

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

## Dependencies

```kotlin
// build.gradle.kts
testImplementation(libs.junit.junit)
testImplementation(libs.hilt.android.testing)
testImplementation(libs.robolectric)
testImplementation("io.mockk:mockk:1.13.9")
testImplementation("org.jetbrains.kotlin:kotlin-test")
kaptTest(libs.hilt.compiler)
```

## Key Annotations

- `@HiltAndroidTest` - Marks test class for Hilt
- `@RunWith(RobolectricTestRunner::class)` - Android test runner
- `@Config(application = HiltTestApplication::class)` - Test app
- `@get:Rule` - JUnit rule for Hilt
- `@Before` - Setup method
- `@Test` - Test method
- `@Inject` - Dependency injection
- `@JvmSuppressWildcards` - For generic collections

## See Also

- Full Guide: `docs/guides/DI_TESTING_GUIDE_2025-10-14.md`
- Analysis: `docs/analysis/DI_QUALITY_ANALYSIS_2025-10-14.md`
- Implementation: `docs/project/DI_FIXES_IMPLEMENTATION_2025-10-14.md`
