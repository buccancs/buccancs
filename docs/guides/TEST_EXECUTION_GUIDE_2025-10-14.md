**Last Modified:** 2025-10-14 04:28 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Guide

# Test Execution Guide

## Quick Start

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

## Why Are Tests Disabled by Default?

Tests are disabled in `build.gradle.kts` to prevent build failures during the testing infrastructure setup phase. This allows:
- Incremental test implementation without breaking builds
- Gradual migration to comprehensive testing
- Controlled test enablement when ready

**When to Enable Tests:**
- During development when writing/running tests
- In CI/CD pipelines
- Before committing changes
- During code reviews

## Test Commands Reference

### Running All Tests

```bash
# All modules
./gradlew test -Ptests.enabled=true

# Specific module
./gradlew :app:test -Ptests.enabled=true
./gradlew :desktop:test -Ptests.enabled=true
./gradlew :protocol:test -Ptests.enabled=true
```

### Running Specific Tests

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

### Test Output Options

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

### Coverage Reports

```bash
# Run tests with coverage (when Jacoco configured)
./gradlew test -Ptests.enabled=true jacocoTestReport

# View coverage report
open app/build/reports/jacoco/test/html/index.html  # Mac
start app/build/reports/jacoco/test/html/index.html  # Windows
xdg-open app/build/reports/jacoco/test/html/index.html  # Linux
```

## Test Reports

After running tests, reports are available at:

```
app/build/reports/tests/testDebugUnitTest/index.html
desktop/build/reports/tests/test/index.html
```

Open in browser to view:
- Test results summary
- Pass/fail status
- Execution time
- Failure details with stack traces

## IDE Integration

### IntelliJ IDEA / Android Studio

#### Running Tests

1. **Single Test Method:**
   - Click gutter icon next to test method
   - Right-click → "Run 'testMethodName'"

2. **Test Class:**
   - Click gutter icon next to class name
   - Right-click → "Run 'TestClassName'"

3. **All Tests in Package:**
   - Right-click package → "Run Tests in 'packageName'"

#### Debugging Tests

1. Set breakpoints in test code
2. Right-click test → "Debug 'testName'"
3. Use debugger controls to step through

#### Viewing Results

- Test results appear in "Run" tool window
- Green: passed
- Red: failed
- Yellow: ignored

### Important: Enable Tests in IDE

For IDE test execution to work, you need to enable tests. Add to `gradle.properties`:

```properties
# Uncomment to enable tests in IDE
tests.enabled=true
```

Or configure IDE run configurations to pass `-Ptests.enabled=true`.

## Continuous Testing

### Watch Mode

For continuous test execution during development:

```bash
# Run tests on file change
./gradlew test -Ptests.enabled=true --continuous

# Or use specific module
./gradlew :app:test -Ptests.enabled=true --continuous
```

Press `Ctrl+D` to stop watch mode.

### Pre-commit Hook

Create `.git/hooks/pre-commit`:

```bash
#!/bin/bash
echo "Running tests before commit..."
./gradlew test -Ptests.enabled=true --tests "*Test"

if [ $? -ne 0 ]; then
  echo "❌ Tests failed. Commit aborted."
  exit 1
fi

echo "✅ All tests passed."
exit 0
```

Make executable:
```bash
chmod +x .git/hooks/pre-commit
```

## CI/CD Integration

### GitHub Actions

Create `.github/workflows/tests.yml`:

```yaml
name: Run Tests

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main, develop ]

jobs:
  test:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 21
      uses: actions/setup-java@v3
      with:
        java-version: '21'
        distribution: 'temurin'
        cache: gradle
    
    - name: Grant execute permission for gradlew
      run: chmod +x gradlew
    
    - name: Run tests
      run: ./gradlew test -Ptests.enabled=true
    
    - name: Upload test reports
      if: always()
      uses: actions/upload-artifact@v3
      with:
        name: test-reports
        path: |
          app/build/reports/tests/
          desktop/build/reports/tests/
    
    - name: Publish test results
      if: always()
      uses: EnricoMi/publish-unit-test-result-action@v2
      with:
        files: |
          **/build/test-results/**/*.xml
```

### GitLab CI

Create `.gitlab-ci.yml`:

```yaml
test:
  image: openjdk:21-jdk
  stage: test
  script:
    - ./gradlew test -Ptests.enabled=true
  artifacts:
    when: always
    reports:
      junit:
        - app/build/test-results/**/TEST-*.xml
        - desktop/build/test-results/**/TEST-*.xml
    paths:
      - app/build/reports/tests/
      - desktop/build/reports/tests/
```

## Troubleshooting

### Tests Not Running

**Problem:** No tests execute even with flag enabled.

**Solutions:**
1. Verify flag syntax: `-Ptests.enabled=true` (not `-P tests.enabled=true`)
2. Check build output for "✅ Tests are ENABLED" message
3. Ensure tests exist in `src/test` directories
4. Try `--rerun-tasks` to force re-execution

### Tests Failing

**Problem:** Tests fail with compilation errors.

**Solutions:**
1. Rebuild project: `./gradlew clean build`
2. Sync Gradle files in IDE
3. Check test dependencies are present
4. Verify Hilt annotation processing works

### Hilt Tests Failing

**Problem:** `@HiltAndroidTest` tests fail with injection errors.

**Solutions:**
1. Ensure `@get:Rule val hiltRule = HiltAndroidRule(this)`
2. Call `hiltRule.inject()` in `@Before` method
3. Add `@Config(application = HiltTestApplication::class)`
4. Check Robolectric is in test dependencies

### Slow Tests

**Problem:** Tests take too long to execute.

**Solutions:**
1. Run specific tests instead of all: `--tests "SpecificTest"`
2. Use `--parallel` for parallel execution
3. Increase test JVM memory: `-Dorg.gradle.jvmargs=-Xmx2g`
4. Profile slow tests and optimise

### IDE Not Running Tests

**Problem:** IDE shows "No tests found" or doesn't execute tests.

**Solutions:**
1. Add `tests.enabled=true` to `gradle.properties`
2. Invalidate caches and restart IDE
3. Ensure JUnit runner is configured correctly
4. Check test class/method has `@Test` annotation

## Performance Tips

### Faster Test Execution

```bash
# Skip unrelated tasks
./gradlew :app:test -Ptests.enabled=true -x lint -x checkstyleMain

# Use build cache
./gradlew test -Ptests.enabled=true --build-cache

# Parallel execution
./gradlew test -Ptests.enabled=true --parallel --max-workers=4

# Daemon mode (default, but explicit)
./gradlew test -Ptests.enabled=true --daemon
```

### Resource Management

```gradle
// In build.gradle.kts
tasks.withType<Test> {
    maxParallelForks = Runtime.getRuntime().availableProcessors() / 2
    maxHeapSize = "2g"
    jvmArgs = listOf("-XX:+UseG1GC")
}
```

## Best Practices

### Development Workflow

1. **Write test first** (TDD approach)
2. **Run test locally** before committing
3. **Check test reports** after failures
4. **Fix flaky tests** immediately
5. **Keep tests fast** (<5s per test)

### Test Organization

```
src/test/java/com/buccancs/
├── data/         # Repository tests
├── application/  # Service tests
├── ui/           # ViewModel tests
├── di/           # DI module tests
└── integration/  # Integration tests
```

### Naming Conventions

- Test classes: `[ClassName]Test`
- Test methods: `` `descriptive name in backticks` ``
- Test packages: Match production package structure

## Quick Reference

### Common Commands

| Task | Command |
|------|---------|
| Run all tests | `./gradlew test -Ptests.enabled=true` |
| Run app tests | `./gradlew :app:test -Ptests.enabled=true` |
| Run specific test | `./gradlew test -Ptests.enabled=true --tests "TestName"` |
| Run with coverage | `./gradlew test -Ptests.enabled=true jacocoTestReport` |
| Continuous mode | `./gradlew test -Ptests.enabled=true --continuous` |
| Clean and test | `./gradlew clean test -Ptests.enabled=true` |

### IDE Shortcuts

| Action | Windows/Linux | Mac |
|--------|---------------|-----|
| Run test | Ctrl+Shift+F10 | Ctrl+Shift+R |
| Debug test | Ctrl+Shift+F9 | Ctrl+Shift+D |
| Re-run tests | Shift+F10 | Shift+F10 |
| Stop tests | Ctrl+F2 | Cmd+F2 |

## Getting Help

- **Strategy:** See `docs/project/TESTING_STRATEGY_2025-10-14.md`
- **DI Testing:** See `docs/guides/DI_TESTING_GUIDE_2025-10-14.md`
- **Quick Reference:** See `docs/guides/DI_TESTING_QUICK_REFERENCE.md`
- **Build Issues:** See `docs/project/BUILD_FIXES_2025-10-13.md`

## Example Session

```bash
# 1. Enable tests and run all
./gradlew test -Ptests.enabled=true
# Output: ✅ Tests are ENABLED via -Ptests.enabled=true
# BUILD SUCCESSFUL with 42 tests passed

# 2. Run specific module
./gradlew :app:test -Ptests.enabled=true --tests "*RepositoryTest"
# Runs only repository tests

# 3. Check test report
open app/build/reports/tests/testDebugUnitTest/index.html

# 4. Run with coverage
./gradlew test -Ptests.enabled=true jacocoTestReport
open app/build/reports/jacoco/test/html/index.html
```

## Summary

Tests are controlled by the `-Ptests.enabled=true` flag, allowing flexible execution without modifying build files. Use this guide to run tests locally, in CI/CD, and from your IDE. Follow best practices for fast, reliable testing that catches issues early.
