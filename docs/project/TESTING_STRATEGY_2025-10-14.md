**Last Modified:** 2025-10-14 04:28 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Strategy Document

# Testing Strategy and Implementation Plan

## Executive Summary

This document addresses the "House of Cards" problem—the absence of a comprehensive testing strategy despite having a testable architecture. While tests exist in the project, they are disabled and insufficient to ensure codebase stability. This strategy outlines a phased approach to establish robust automated testing that prevents regressions, enables safe refactoring, and verifies critical logic.

## Current State Assessment

### Tests Explicitly Disabled

The root `build.gradle.kts` file contains:
```kotlin
subprojects {
    tasks.withType<Test>().configureEach {
        enabled = false
    }
}
```

**Impact:** All automated tests are disabled across all subprojects, regardless of whether tests exist.

### Existing Test Files

**Android App Module** (app/src/test):
- ✅ `DefaultSensorRepositoryTest.kt` - Repository logic with simulated connectors (3 tests)
- ✅ `TimeSyncMathTest.kt` - Time synchronisation calculations (3 tests)
- ✅ `CalibrationViewModelTest.kt` - Calibration UI logic (3 tests)
- ✅ `UploadBacklogCalculatorTest.kt` - Upload queue management
- ✅ `PerformanceMetricsAnalyzerTest.kt` - Performance tracking
- ✅ 7 DI Module Tests - Dependency injection verification (recently added)
- ❌ `ExampleUnitTest.kt` - Trivial placeholder test

**Desktop Module** (desktop/src/test):
- ✅ `SensorRecordingManagerTest.kt` - Recording manager logic
- ✅ `SessionRepositoryTest.kt` - Session data management

**Total:** ~16 meaningful test files (excluding placeholders and external projects)

### Architecture Testability Analysis

**Highly Testable Components:**
1. **Dependency Injection (Hilt)** - Constructor injection enables easy mocking
2. **Repository Pattern** - Data layer abstracted behind interfaces
3. **Service Layer** - Application logic separated from UI
4. **ViewModels (MVVM)** - Business logic isolated from Android framework
5. **Coroutine-based async** - TestCoroutineScope simplifies async testing

**Components Requiring Testing:**
1. Sensor discovery and connection management
2. Recording session lifecycle
3. File upload and retry logic
4. Time synchronisation algorithms
5. Calibration computation and validation
6. Data stream processing
7. Hardware connector implementations
8. Repository implementations
9. Service layer coordination
10. ViewModel state management

## The "House of Cards" Problem

### High Risk of Regressions

**Without Tests:**
- Changes to `DefaultSensorRepository` could break device discovery
- Modifications to upload logic might cause data loss
- Refactoring time sync code could introduce clock drift
- UI changes might break critical user workflows

**Impact:** Every code change carries unquantified risk.

### Inability to Refactor Safely

**Complex Areas Needing Improvement:**
- Transfer retry logic (complex state machine)
- Session manifest generation (intricate data assembly)
- Sensor stream coordination (timing-sensitive)
- Hardware abstraction (multiple protocols)

**Current State:** Refactoring these areas without tests is dangerous, so technical debt accumulates.

### Unverified Logic

**Critical Algorithms Without Tests:**
- Upload backlog calculation
- Performance metrics aggregation
- Bookmark timeline correlation
- Event replay synchronisation
- Calibration error metrics

**Risk:** Bugs in these areas may remain undetected until production failure.

## Testing Strategy

### Phase 1: Foundation (Weeks 1-2)

**Goal:** Establish test infrastructure and verify existing tests

#### 1.1 Enable Tests Conditionally
```kotlin
// build.gradle.kts
val testsEnabled = project.findProperty("tests.enabled")?.toString()?.toBoolean() ?: false

subprojects {
    tasks.withType<Test>().configureEach {
        enabled = testsEnabled
    }
}
```

**Benefit:** Tests can be enabled via `./gradlew test -Ptests.enabled=true` without modifying build files.

#### 1.2 Fix and Run Existing Tests
- Verify all 16 existing tests pass
- Fix any broken tests
- Ensure test dependencies are correct
- Document test execution process

#### 1.3 Establish CI/CD Integration
- Add test task to GitHub Actions workflow
- Configure test reports and coverage
- Set up failure notifications
- Create test result dashboard

**Deliverables:**
- [ ] Tests runnable with flag
- [ ] All existing tests passing
- [ ] CI/CD running tests automatically
- [ ] Test execution documented

### Phase 2: Critical Path Coverage (Weeks 3-5)

**Goal:** Test highest-risk components first

#### 2.1 Repository Layer Tests (Week 3)

**Priority 1 - Data Integrity:**
```kotlin
class SensorRepositoryTest {
    @Test fun `discovers all configured sensors`()
    @Test fun `handles connection failures gracefully`()
    @Test fun `maintains device state across reconnections`()
    @Test fun `prevents duplicate device registration`()
}

class SessionTransferRepositoryTest {
    @Test fun `queues artifacts for upload`()
    @Test fun `retries failed uploads with exponential backoff`()
    @Test fun `persists queue state across app restarts`()
    @Test fun `respects backlog thresholds`()
}

class CalibrationRepositoryTest {
    @Test fun `captures stereo pairs correctly`()
    @Test fun `validates pattern detection`()
    @Test fun `persists calibration results`()
    @Test fun `loads previous calibration`()
}
```

**Deliverables:**
- [ ] 12+ repository tests
- [ ] 80%+ coverage of critical repositories
- [ ] Integration tests with TestHardwareModule

#### 2.2 Service Layer Tests (Week 4)

**Priority 2 - Business Logic:**
```kotlin
class RecordingServiceTest {
    @Test fun `starts recording all connected sensors`()
    @Test fun `coordinates sensor synchronisation`()
    @Test fun `handles mid-recording sensor failures`()
    @Test fun `finalises session metadata on stop`()
}

class DeviceCommandServiceTest {
    @Test fun `delivers commands to all active devices`()
    @Test fun `replays commands to reconnecting devices`()
    @Test fun `handles command conflicts`()
}

class TimeSyncServiceTest {
    @Test fun `establishes clock synchronisation`()
    @Test fun `maintains sync within tolerance`()
    @Test fun `detects clock drift`()
}
```

**Deliverables:**
- [ ] 15+ service tests
- [ ] End-to-end service integration tests
- [ ] Mock orchestrator for testing

#### 2.3 ViewModel Tests (Week 5)

**Priority 3 - UI Logic:**
```kotlin
class LiveSessionViewModelTest {
    @Test fun `displays active recording state`()
    @Test fun `updates telemetry in real-time`()
    @Test fun `shows upload progress`()
    @Test fun `handles recording errors`()
}

class SensorConfigViewModelTest {
    @Test fun `loads device inventory`()
    @Test fun `saves configuration changes`()
    @Test fun `validates sensor settings`()
}
```

**Deliverables:**
- [ ] 20+ ViewModel tests
- [ ] Full ViewModel coverage for critical screens
- [ ] Fake repositories for isolation

### Phase 3: Edge Cases and Integration (Weeks 6-8)

**Goal:** Test error scenarios and component interactions

#### 3.1 Error Handling Tests

```kotlin
class UploadRecoveryTest {
    @Test fun `recovers from network interruption`()
    @Test fun `handles disk full during upload`()
    @Test fun `preserves partial uploads`()
    @Test fun `logs recovery events`()
}

class SensorFailureTest {
    @Test fun `continues recording when one sensor fails`()
    @Test fun `notifies user of sensor disconnection`()
    @Test fun `attempts automatic reconnection`()
}
```

#### 3.2 Integration Tests

```kotlin
@HiltAndroidTest
class RecordingIntegrationTest {
    @Test fun `complete recording workflow succeeds`()
    @Test fun `session manifest includes all metadata`()
    @Test fun `artifacts queued after session ends`()
}

@HiltAndroidTest
class CalibrationIntegrationTest {
    @Test fun `calibration wizard completes successfully`()
    @Test fun `calibration persists and loads`()
    @Test fun `low confidence triggers remediation`()
}
```

**Deliverables:**
- [ ] 25+ error scenario tests
- [ ] 10+ integration tests
- [ ] Chaos testing framework

### Phase 4: Comprehensive Coverage (Weeks 9-12)

**Goal:** Achieve 70%+ code coverage across critical modules

#### 4.1 Connector Tests

Test hardware connector implementations with mocked hardware services:
```kotlin
class ShimmerConnectorTest {
    @Test fun `discovers paired Shimmer devices`()
    @Test fun `connects to device by MAC address`()
    @Test fun `configures GSR sensor`()
    @Test fun `streams sensor data`()
}

class TopdonConnectorTest {
    @Test fun `detects USB thermal camera`()
    @Test fun `captures thermal frames`()
    @Test fun `handles camera disconnection`()
}
```

#### 4.2 Data Processing Tests

```kotlin
class StreamProcessorTest {
    @Test fun `aligns timestamps to orchestrator clock`()
    @Test fun `segments video at keyframes`()
    @Test fun `generates telemetry per segment`()
}

class ManifestBuilderTest {
    @Test fun `includes all session metadata`()
    @Test fun `lists artifacts with checksums`()
    @Test fun `captures event timeline`()
}
```

**Deliverables:**
- [ ] 40+ connector and processor tests
- [ ] 70%+ code coverage on critical paths
- [ ] Performance benchmarks

### Phase 5: Maintenance and Expansion (Ongoing)

**Goal:** Maintain test quality and expand coverage

#### 5.1 Test Maintenance
- Update tests when requirements change
- Refactor tests alongside production code
- Remove obsolete tests
- Improve test performance

#### 5.2 Coverage Expansion
- Add tests for new features
- Increase coverage in untested areas
- Add regression tests for bugs
- Document testing patterns

## Testing Patterns and Standards

### Unit Test Structure

```kotlin
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class MyComponentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    
    @Inject lateinit var dependency: Dependency
    
    private lateinit var component: MyComponent
    
    @Before
    fun setup() {
        hiltRule.inject()
        component = MyComponent(dependency)
    }
    
    @Test
    fun `descriptive test name in backticks`() = runTest {
        // Given - setup test preconditions
        val input = createTestInput()
        
        // When - execute the code under test
        val result = component.doSomething(input)
        
        // Then - verify the outcome
        assertEquals(expected, result)
    }
    
    @After
    fun tearDown() {
        // Cleanup if needed
    }
}
```

### Integration Test Structure

```kotlin
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class FeatureIntegrationTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    
    @Inject lateinit var repository: Repository
    @Inject lateinit var service: Service
    @Inject lateinit var viewModel: ViewModelFactory
    
    @Before
    fun setup() {
        hiltRule.inject()
    }
    
    @Test
    fun `end-to-end workflow succeeds`() = runTest {
        // Test multiple components working together
        val data = repository.loadData()
        service.processData(data)
        val vm = viewModel.create(MyViewModel::class.java)
        
        vm.triggerAction()
        advanceUntilIdle()
        
        val state = vm.state.value
        assertEquals(ExpectedState, state)
    }
}
```

### Naming Conventions

**Test Classes:** `[ComponentName]Test`
- `SensorRepositoryTest`
- `RecordingServiceTest`
- `LiveSessionViewModelTest`

**Test Methods:** Descriptive with backticks
- `` `discovers all configured sensors` ``
- `` `handles connection failures gracefully` ``
- `` `persists state across app restarts` ``

### Test Organization

```
app/src/test/java/com/buccancs/
├── data/
│   ├── sensor/
│   │   ├── DefaultSensorRepositoryTest.kt
│   │   ├── connector/
│   │   │   ├── ShimmerConnectorTest.kt
│   │   │   └── TopdonConnectorTest.kt
│   │   └── config/
│   │       └── SensorHardwareConfigRepositoryTest.kt
│   ├── transfer/
│   │   ├── DefaultSessionTransferRepositoryTest.kt
│   │   └── UploadRecoveryTest.kt
│   └── calibration/
│       └── DefaultCalibrationRepositoryTest.kt
├── application/
│   ├── recording/
│   │   └── DefaultRecordingServiceTest.kt
│   └── control/
│       └── DefaultDeviceCommandServiceTest.kt
├── ui/
│   ├── livesession/
│   │   └── LiveSessionViewModelTest.kt
│   └── calibration/
│       └── CalibrationViewModelTest.kt
├── di/
│   └── [Module]Test.kt  (DI verification)
└── integration/
    ├── RecordingIntegrationTest.kt
    └── CalibrationIntegrationTest.kt
```

## Test Infrastructure Requirements

### Dependencies (Already Added)

```kotlin
// Unit testing
testImplementation(libs.junit.junit)
testImplementation("org.jetbrains.kotlin:kotlin-test")

// Hilt testing
testImplementation(libs.hilt.android.testing)
kaptTest(libs.hilt.compiler)

// Android testing
testImplementation(libs.robolectric)

// Coroutines testing
testImplementation(libs.coroutines.test)

// Mocking
testImplementation("io.mockk:mockk:1.13.9")

// Architecture components testing
testImplementation(libs.androidx.arch.core.testing)
```

### Test Utilities

Create shared test utilities:
```kotlin
// TestFixtures.kt
object TestFixtures {
    fun createTestSensor() = SensorDevice(...)
    fun createTestSession() = RecordingSessionAnchor(...)
    fun createTestManifest() = SessionManifest(...)
}

// TestCoroutineRule.kt
class TestCoroutineRule : TestWatcher() {
    val dispatcher = StandardTestDispatcher()
    val scope = TestScope(dispatcher)
    
    override fun starting(description: Description) {
        Dispatchers.setMain(dispatcher)
    }
    
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
```

## Metrics and Goals

### Coverage Targets

| Component | Current | Phase 1 | Phase 2 | Phase 3 | Phase 4 |
|-----------|---------|---------|---------|---------|---------|
| Repositories | ~10% | 40% | 80% | 85% | 90% |
| Services | ~5% | 30% | 70% | 80% | 85% |
| ViewModels | ~15% | 40% | 80% | 85% | 90% |
| Connectors | ~0% | 20% | 50% | 70% | 80% |
| Utilities | ~20% | 50% | 70% | 80% | 85% |
| **Overall** | **~4%** | **35%** | **70%** | **80%** | **85%** |

### Quality Metrics

**Test Health:**
- Test pass rate: >98%
- Average test execution time: <5s per test
- Flaky test rate: <1%
- Test maintainability score: >80%

**Code Quality:**
- Cyclomatic complexity: <15 per method
- Method length: <50 lines
- Class length: <500 lines
- Duplication: <5%

## Test Execution Strategy

### Local Development

```bash
# Run all tests
./gradlew test -Ptests.enabled=true

# Run specific module
./gradlew :app:test -Ptests.enabled=true

# Run specific test class
./gradlew :app:test -Ptests.enabled=true --tests "SensorRepositoryTest"

# Run with coverage
./gradlew test -Ptests.enabled=true jacocoTestReport
```

### CI/CD Pipeline

```yaml
# .github/workflows/test.yml
name: Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '21'
      - name: Run tests
        run: ./gradlew test -Ptests.enabled=true
      - name: Upload coverage
        uses: codecov/codecov-action@v3
```

### Pre-commit Hooks

```bash
# .git/hooks/pre-commit
#!/bin/bash
./gradlew test -Ptests.enabled=true --tests "*Test"
if [ $? -ne 0 ]; then
  echo "Tests failed. Commit aborted."
  exit 1
fi
```

## Migration Path

### Immediate Actions (Week 1)

1. **Enable test flag in build.gradle.kts**
2. **Verify existing tests pass**
3. **Document test execution process**
4. **Add to README.md**

### Short-term (Weeks 2-5)

1. **Write repository tests** (highest risk)
2. **Write service tests** (business logic)
3. **Write ViewModel tests** (UI logic)
4. **Establish CI/CD testing**

### Medium-term (Weeks 6-12)

1. **Add integration tests**
2. **Test error scenarios**
3. **Expand connector coverage**
4. **Achieve 70%+ coverage**

### Long-term (Ongoing)

1. **Maintain test quality**
2. **Expand coverage to 85%+**
3. **Performance testing**
4. **Load testing**

## Risk Mitigation

### Risks to Testing Initiative

| Risk | Impact | Mitigation |
|------|--------|------------|
| Tests take too long | Devs skip tests | Optimise slow tests, parallel execution |
| Flaky tests | Lost confidence | Fix immediately, quarantine if needed |
| Hard to write tests | Low adoption | Provide examples, pair programming |
| Tests break frequently | Maintenance burden | Keep tests focused, use good fixtures |
| Low coverage | False confidence | Track metrics, set targets |

## Success Criteria

### Phase Completion Criteria

**Phase 1 Complete When:**
- [ ] Tests can be enabled via flag
- [ ] All existing tests pass
- [ ] CI/CD runs tests automatically
- [ ] Execution documented

**Phase 2 Complete When:**
- [ ] 40+ repository/service/ViewModel tests written
- [ ] 70%+ coverage on critical paths
- [ ] All tests pass reliably
- [ ] Patterns documented

**Phase 3 Complete When:**
- [ ] 35+ error/integration tests written
- [ ] 80%+ coverage on critical paths
- [ ] End-to-end workflows tested
- [ ] Regression suite established

**Phase 4 Complete When:**
- [ ] 85%+ code coverage achieved
- [ ] All components tested
- [ ] Performance benchmarks established
- [ ] Testing culture established

## Conclusion

The current "House of Cards" problem stems from disabled tests, not absent architecture. The foundation is solid—Hilt DI, repository pattern, service layer—making the codebase highly testable. By systematically enabling tests, writing missing tests, and establishing a testing culture, the project can transition from fragile to robust.

This strategy prioritises high-risk components first, establishes clear patterns, and provides a realistic timeline for achieving comprehensive coverage. The phased approach allows incremental progress while delivering value early through critical path coverage.

**Next Step:** Enable test execution flag and verify existing tests pass.
