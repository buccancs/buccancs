**Last Modified:** 2025-01-14 03:38 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Analysis

# MainViewModel Refactoring Analysis

## Executive Summary

The `MainViewModel` (1,248 lines) exhibits classic God Object anti-pattern with 10 constructor dependencies and mixing concerns across session management, device orchestration, time synchronisation, recording coordination, hardware configuration, and UI state transformation. This analysis proposes a phased refactoring strategy to decompose responsibilities whilst maintaining existing functionality.

## Current Architecture Analysis

### Dependencies (10 Total)

```kotlin
@HiltViewModel
class MainViewModel @Inject constructor(
    private val sensorRepository: SensorRepository,                     // Device data access
    private val timeSyncService: TimeSyncService,                       // Time synchronisation
    private val orchestratorConfigRepository: OrchestratorConfigRepository, // Remote config
    private val recordingService: RecordingService,                     // Recording lifecycle
    private val commandService: DeviceCommandService,                   // Remote commands
    private val deviceEventRepository: DeviceEventRepository,           // Event logging
    private val shimmerSettingsRepository: ShimmerSettingsRepository,   // Shimmer config
    private val hardwareConfigRepository: SensorHardwareConfigRepository, // Hardware inventory
    private val topdonDeviceRepository: TopdonDeviceRepository,         // Topdon config
    private val exercise: MultiDeviceRecordingExercise                  // Exercise execution
)
```

### Responsibility Breakdown

| Responsibility                  | Line Count | Methods | State Flows |
|---------------------------------|------------|---------|-------------|
| Session Lifecycle Management    | ~150       | 3       | 3           |
| Device Connection Control       | ~80        | 4       | 0           |
| Shimmer Configuration           | ~180       | 4       | 1           |
| Topdon Device Management        | ~40        | 1       | 1           |
| RGB Camera Configuration        | ~350       | 9       | 1           |
| Recording State Coordination    | ~120       | 3       | 0           |
| Remote Command Handling         | ~150       | 4       | 1           |
| Orchestrator Configuration      | ~120       | 5       | 4           |
| Time Synchronisation UI         | ~80        | 0       | 0           |
| Multi-Device Exercise Control   | ~50        | 1       | 1           |
| UI State Transformation         | ~300       | 15+     | 5           |
| Event Logging UI                | ~40        | 0       | 1           |

**Total State Flows:** 18 (10 MutableStateFlow, 8 derived)

### Code Smells Identified

1. **God Object Anti-Pattern**
   - 10 constructor dependencies
   - 1,248 lines in single class
   - 40+ public methods
   - 18 state flows managed

2. **Mixed Concerns**
   - Business logic (recording coordination)
   - UI transformation (toUiModel methods)
   - Hardware control (device connection, configuration)
   - Input validation (RGB camera, orchestrator config)
   - Remote command handling

3. **Complex State Management**
   - Multiple intermediate state classes (BaseState, SessionState, OrchestratorState, CommandState, ToggleState)
   - Nested combine operators (5 levels deep)
   - State synchronisation between repositories and local flows

4. **Tight Coupling**
   - Direct hardware configuration in ViewModel
   - Device-specific logic (Shimmer, Topdon, RGB Camera)
   - Repository updates mixed with UI state

5. **Testability Issues**
   - Difficult to unit test individual responsibilities
   - Heavy mocking requirements (10 dependencies)
   - Side effects in multiple coroutine scopes

## Proposed Refactoring Strategy

### Phase 1: Extract Use Cases (Clean Architecture Layer)

Create domain use cases to encapsulate business logic and reduce ViewModel dependencies.

#### 1.1 SessionCoordinator Use Case

**Purpose:** Manage recording session lifecycle and coordination.

```kotlin
interface SessionCoordinator {
    val sessionState: StateFlow<SessionState>
    suspend fun startSession(sessionId: String, requestedStart: Instant?): Result<Unit>
    suspend fun stopSession(): Result<Unit>
    suspend fun generateSessionId(): String
}

@Singleton
class SessionCoordinatorImpl @Inject constructor(
    private val recordingService: RecordingService,
    private val timeSyncService: TimeSyncService,
    private val sensorRepository: SensorRepository
) : SessionCoordinator {
    // Coordinates recording start with time sync and device readiness
}
```

**Extracts from MainViewModel:**
- `startRecording()` - lines 482-495
- `stopRecording()` - lines 497-509
- `generateSessionId()` - lines 960-963
- Session state management - lines 77-78, 117-127

**Benefits:**
- Testable recording coordination logic
- Reusable across multiple ViewModels or screens
- Clear separation of session lifecycle from UI concerns

#### 1.2 DeviceManagementUseCase

**Purpose:** Abstract device connection, discovery, and inventory management.

```kotlin
interface DeviceManagementUseCase {
    val devices: StateFlow<List<SensorDevice>>
    val connectionStates: StateFlow<Map<DeviceId, ConnectionStatus>>
    suspend fun connectDevice(deviceId: DeviceId): Result<Unit>
    suspend fun disconnectDevice(deviceId: DeviceId): Result<Unit>
    suspend fun refreshInventory(): Result<Unit>
    suspend fun toggleSimulation(): Result<Unit>
}

@Singleton
class DeviceManagementUseCaseImpl @Inject constructor(
    private val sensorRepository: SensorRepository,
    private val hardwareConfigRepository: SensorHardwareConfigRepository
) : DeviceManagementUseCase {
    // Device lifecycle operations
}
```

**Extracts from MainViewModel:**
- `connectDevice()` - lines 299-303
- `disconnectDevice()` - lines 305-309
- `refreshInventory()` - lines 311-315
- `toggleSimulation()` - lines 292-297

**Benefits:**
- Centralised device management logic
- Consistent error handling
- Easier to add new device types

#### 1.3 HardwareConfigurationUseCase

**Purpose:** Device-specific configuration operations (Shimmer, Topdon, RGB Camera).

```kotlin
interface HardwareConfigurationUseCase {
    // Shimmer operations
    suspend fun configureShimmerDevice(
        deviceId: DeviceId,
        macAddress: String?,
        gsrRange: Int,
        sampleRate: Double
    ): Result<Unit>
    
    // Topdon operations
    suspend fun setActiveTopdon(deviceId: DeviceId): Result<Unit>
    
    // RGB Camera operations
    suspend fun configureRgbCamera(
        deviceId: DeviceId,
        settings: RgbCameraSettings
    ): Result<RgbCameraValidation>
}

data class RgbCameraSettings(
    val videoFps: Int,
    val videoBitRate: Int,
    val rawEnabled: Boolean,
    val rawIntervalMs: Long?,
    val exposureNs: Long?,
    val iso: Int?,
    val focusMeters: Double?,
    val awbMode: String
)

data class RgbCameraValidation(
    val isValid: Boolean,
    val errors: Map<RgbCameraField, String>
)
```

**Extracts from MainViewModel:**
- Shimmer configuration - lines 317-369 (3 methods, ~52 lines)
- Topdon configuration - lines 371-375 (~5 lines)
- RGB Camera configuration - lines 394-432 (~40 lines)
- RGB Camera validation - lines 808-879 (~72 lines)

**Benefits:**
- Device-specific logic isolated
- Validation separated from UI
- Easier to add new device types
- Testable configuration logic

#### 1.4 RemoteCommandCoordinator

**Purpose:** Handle remote orchestrator commands with execution timing.

```kotlin
interface RemoteCommandCoordinator {
    val lastCommand: StateFlow<DeviceCommandPayload?>
    suspend fun handleCommand(payload: DeviceCommandPayload): Result<Unit>
}

@Singleton
class RemoteCommandCoordinatorImpl @Inject constructor(
    private val commandService: DeviceCommandService,
    private val sessionCoordinator: SessionCoordinator,
    private val timeSyncService: TimeSyncService
) : RemoteCommandCoordinator {
    // Command handling with time-synchronised execution
}
```

**Extracts from MainViewModel:**
- `handleRemoteCommand()` - lines 511-517
- `handleStartRecordingCommand()` - lines 519-548
- `handleStopRecordingCommand()` - lines 550-575
- `acknowledgeCommand()` - lines 577-583
- `awaitExecution()` - lines 666-675

**Benefits:**
- Isolated command handling logic
- Testable execution timing
- Reusable across different command sources

### Phase 2: Split ViewModels by Feature Domain

After extracting use cases, split MainViewModel into focused feature ViewModels.

#### 2.1 RecordingViewModel

**Responsibility:** Session lifecycle, recording state, exercise execution.

**Dependencies:**
- `SessionCoordinator` (use case)
- `MultiDeviceRecordingExercise` (existing)
- `SensorRepository` (for recording state)

**State:**
```kotlin
data class RecordingUiState(
    val sessionId: String,
    val lifecycle: RecordingLifecycleState,
    val anchorReference: Instant?,
    val sharedClockOffsetMillis: Long?,
    val isBusy: Boolean,
    val errorMessage: String?,
    val exercise: RecordingExerciseUi?
)
```

**Public Methods:**
- `onSessionIdChanged(value: String)`
- `startRecording()`
- `stopRecording()`
- `runExercise()`

**Lines:** ~250 (session + exercise + state)

#### 2.2 DeviceInventoryViewModel

**Responsibility:** Device discovery, connection, inventory management.

**Dependencies:**
- `DeviceManagementUseCase` (use case)
- `HardwareConfigurationUseCase` (use case)
- `SensorRepository` (for device list)

**State:**
```kotlin
data class DeviceInventoryUiState(
    val simulationEnabled: Boolean,
    val devices: List<DeviceUiModel>,
    val inventory: InventoryUiModel,
    val errorMessage: String?
)
```

**Public Methods:**
- `toggleSimulation()`
- `connectDevice(id: DeviceId)`
- `disconnectDevice(id: DeviceId)`
- `refreshInventory()`

**Lines:** ~200

#### 2.3 ShimmerConfigViewModel

**Responsibility:** Shimmer device configuration and scanning.

**Dependencies:**
- `HardwareConfigurationUseCase` (use case)
- `ShimmerSettingsRepository` (existing)
- `SensorRepository` (for device attributes)

**State:**
```kotlin
data class ShimmerConfigUiState(
    val deviceId: DeviceId,
    val candidates: List<ShimmerCandidateUi>,
    val selectedMac: String?,
    val gsrRangeIndex: Int,
    val sampleRateHz: Double,
    val gsrRangeLabels: List<String>,
    val sampleRateOptions: List<Double>,
    val errorMessage: String?
)
```

**Public Methods:**
- `selectShimmerDevice(id: DeviceId, mac: String?)`
- `updateShimmerRange(id: DeviceId, rangeIndex: Int)`
- `updateShimmerSampleRate(id: DeviceId, sampleRate: Double)`

**Lines:** ~200

#### 2.4 RgbCameraConfigViewModel

**Responsibility:** RGB Camera configuration and validation.

**Dependencies:**
- `HardwareConfigurationUseCase` (use case)
- `SensorRepository` (for camera attributes)

**State:**
```kotlin
data class RgbCameraConfigUiState(
    val deviceId: DeviceId,
    val supportsRaw: Boolean,
    val settings: RgbCameraUi,
    val isDirty: Boolean,
    val errors: Map<RgbCameraField, String>
)
```

**Public Methods:**
- `updateRgbCameraField(id: DeviceId, field: RgbCameraField, value: String)`
- `toggleRgbCameraRaw(id: DeviceId, enabled: Boolean)`
- `selectRgbCameraAwb(id: DeviceId, awbValue: String)`
- `resetRgbCameraSettings(id: DeviceId)`
- `applyRgbCameraSettings(id: DeviceId)`

**Lines:** ~350

#### 2.5 TelemetryViewModel

**Responsibility:** Stream status, time sync, events, orchestrator status.

**Dependencies:**
- `SensorRepository` (for stream statuses)
- `TimeSyncService` (existing)
- `DeviceEventRepository` (existing)
- `OrchestratorConfigRepository` (existing)

**State:**
```kotlin
data class TelemetryUiState(
    val streamStatuses: List<StreamUiModel>,
    val timeSyncStatus: TimeSyncStatus,
    val orchestratorConnectionStatus: String,
    val deviceEvents: List<DeviceEventUiModel>,
    val showSyncFlash: Boolean
)
```

**Public Methods:**
- *(None - read-only display)*

**Lines:** ~150

#### 2.6 OrchestratorConfigViewModel

**Responsibility:** Orchestrator connection configuration.

**Dependencies:**
- `OrchestratorConfigRepository` (existing)

**State:**
```kotlin
data class OrchestratorConfigUiState(
    val hostInput: String,
    val portInput: String,
    val useTls: Boolean,
    val currentConfig: OrchestratorConfig?,
    val isDirty: Boolean,
    val message: String?
)
```

**Public Methods:**
- `onOrchestratorHostChanged(value: String)`
- `onOrchestratorPortChanged(value: String)`
- `onOrchestratorUseTlsChanged(enabled: Boolean)`
- `applyOrchestratorConfig()`
- `clearConfigMessage()`

**Lines:** ~120

### Phase 3: Improve State Flow Architecture

Current implementation uses multiple intermediate state classes and nested combines. Simplify using more direct StateFlow mapping.

#### Before (Complex Aggregation):
```kotlin
private val baseInputs = combine(/* 5 flows */) { ... }
private val baseState = baseInputs.combine(shimmerSettingsFlow) { ... }
private val sessionState = combine(/* 3 flows */) { ... }
private val orchestratorState = combine(/* 5 flows */) { ... }
private val commandState = combine(/* 2 flows */) { ... }
private val toggleState = combine(/* 2 flows */) { ... }
private val aggregateState = combine(/* 5 intermediate states */) { ... }
val uiState = combine(aggregateState, inventoryState, exerciseUiState) { ... }
```

#### After (Direct Mapping):
```kotlin
val uiState: StateFlow<RecordingUiState> = combine(
    sessionCoordinator.sessionState,
    exercise.result,
    sensorRepository.recordingState
) { session, exerciseResult, recordingState ->
    RecordingUiState(
        sessionId = session.id,
        lifecycle = recordingState.lifecycle,
        // ... direct mapping
    )
}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), RecordingUiState.initial())
```

**Benefits:**
- Fewer intermediate state classes
- Clearer data flow
- Easier to understand and maintain

### Phase 4: Extract UI Transformation Logic

Move `toUiModel()` extension functions out of ViewModel into dedicated mappers.

```kotlin
// domain/ui/DeviceUiMapper.kt
@Singleton
class DeviceUiMapper @Inject constructor() {
    fun toUiModel(
        device: SensorDevice,
        streams: List<SensorStreamStatus>,
        shimmerSettings: ShimmerSettings
    ): DeviceUiModel {
        // Existing toUiModel logic
    }
}

// domain/ui/StreamUiMapper.kt
@Singleton
class StreamUiMapper @Inject constructor() {
    fun toUiModel(status: SensorStreamStatus): StreamUiModel {
        // Existing toUiModel logic
    }
}
```

**Benefits:**
- Testable transformation logic
- Reusable across ViewModels
- Separation of concerns

## Implementation Roadmap

### Milestone 1: Extract Use Cases (2-3 days)
- [ ] Create `SessionCoordinator` interface and implementation
- [ ] Create `DeviceManagementUseCase` interface and implementation
- [ ] Create `HardwareConfigurationUseCase` interface and implementation
- [ ] Create `RemoteCommandCoordinator` interface and implementation
- [ ] Add Hilt bindings for new use cases
- [ ] Update MainViewModel to use new use cases (refactor, don't replace)
- [ ] Write unit tests for each use case

### Milestone 2: Create UI Mappers (1 day)
- [ ] Create `DeviceUiMapper` class
- [ ] Create `StreamUiMapper` class
- [ ] Create `EventUiMapper` class
- [ ] Create `ExerciseUiMapper` class
- [ ] Update MainViewModel to use mappers
- [ ] Write unit tests for mappers

### Milestone 3: Split ViewModels (3-4 days)
- [ ] Create `RecordingViewModel` from MainViewModel
- [ ] Create `DeviceInventoryViewModel` from MainViewModel
- [ ] Create `ShimmerConfigViewModel` from MainViewModel
- [ ] Create `RgbCameraConfigViewModel` from MainViewModel
- [ ] Create `TelemetryViewModel` from MainViewModel
- [ ] Create `OrchestratorConfigViewModel` from MainViewModel
- [ ] Update UI layer to use multiple ViewModels (Composable refactoring)
- [ ] Update Hilt modules

### Milestone 4: Simplify State Flows (1-2 days)
- [ ] Remove intermediate state classes where not needed
- [ ] Simplify combine operators in new ViewModels
- [ ] Remove unused state flows
- [ ] Optimise StateFlow sharing strategies

### Milestone 5: Testing & Validation (2 days)
- [ ] Write unit tests for all new ViewModels
- [ ] Integration tests for use case coordination
- [ ] Manual testing of all features
- [ ] Performance testing (state flow efficiency)
- [ ] Remove deprecated MainViewModel

**Total Estimated Effort:** 9-12 days

## Risks and Mitigation

### Risk 1: Breaking UI Composition
**Mitigation:** Refactor ViewModels incrementally, keeping MainViewModel alongside new ViewModels initially. Migrate UI screens one by one.

### Risk 2: State Synchronisation Issues
**Mitigation:** Use shared repositories as single source of truth. Ensure use cases don't introduce duplicate state.

### Risk 3: Increased Complexity from Multiple ViewModels
**Mitigation:** Use clear naming conventions. Document ViewModel responsibilities. Provide architecture decision records.

### Risk 4: Testing Coverage Gaps
**Mitigation:** Write tests alongside refactoring. Aim for 80%+ coverage on new use cases and ViewModels.

## Success Criteria

1. **Reduced Dependencies:** Each ViewModel should have ≤5 constructor dependencies
2. **Smaller Classes:** Each ViewModel should be <400 lines
3. **Single Responsibility:** Each ViewModel should manage one feature domain
4. **Testability:** Unit test coverage >80% for new components
5. **Maintainability:** Clear separation of concerns, documented responsibilities
6. **Performance:** No regression in UI responsiveness or state flow efficiency

## Related Documents

- `ARCHITECTURAL_ISSUES.md` - Original God Object identification
- `ARCHITECTURE_SUMMARY.md` - Current system architecture
- Domain layer structure: `app/src/main/java/com/buccancs/domain/`
- Application layer services: `app/src/main/java/com/buccancs/application/`

## Appendix A: Current Method Breakdown

### Public Methods (40+)

**Session Management (4):**
- `onSessionIdChanged(String)`
- `startRecording()`
- `stopRecording()`
- `runExercise()`

**Device Management (4):**
- `toggleSimulation()`
- `connectDevice(DeviceId)`
- `disconnectDevice(DeviceId)`
- `refreshInventory()`

**Shimmer Configuration (3):**
- `selectShimmerDevice(DeviceId, String?)`
- `updateShimmerRange(DeviceId, Int)`
- `updateShimmerSampleRate(DeviceId, Double)`

**Topdon Configuration (1):**
- `setActiveTopdon(DeviceId)`

**RGB Camera Configuration (5):**
- `updateRgbCameraField(DeviceId, RgbCameraField, String)`
- `toggleRgbCameraRaw(DeviceId, Boolean)`
- `selectRgbCameraAwb(DeviceId, String)`
- `resetRgbCameraSettings(DeviceId)`
- `applyRgbCameraSettings(DeviceId)`

**Orchestrator Configuration (5):**
- `onOrchestratorHostChanged(String)`
- `onOrchestratorPortChanged(String)`
- `onOrchestratorUseTlsChanged(Boolean)`
- `applyOrchestratorConfig()`
- `clearConfigMessage()`

### Private Methods (15+)

- `handleRemoteCommand()`
- `handleStartRecordingCommand()`
- `handleStopRecordingCommand()`
- `acknowledgeCommand()`
- `awaitExecution()`
- `ensureRgbCameraState()`
- `buildRgbBaseline()`
- `updateRgbCameraState()`
- `validateRgbCamera()`
- `buildShimmerUi()`
- `deriveConnectionLabel()`
- `describeCommand()`
- `inputsDirty()`
- `generateSessionId()`
- Multiple `toUiModel()` extensions

## Appendix B: State Flow Inventory

**MutableStateFlow (10):**
1. `sessionId`
2. `busy`
3. `lastError`
4. `hostInput`
5. `portInput`
6. `useTlsInput`
7. `currentConfig`
8. `configMessage`
9. `uploads`
10. `showSyncFlash`
11. `exerciseResult`
12. `rgbCameraInputs`

**Derived StateFlow (8):**
1. `shimmerSettingsFlow` (from repository)
2. `baseInputs` (combined)
3. `baseState` (combined)
4. `sessionState` (combined)
5. `orchestratorState` (combined)
6. `commandState` (combined)
7. `toggleState` (combined)
8. `inventoryState` (mapped)
9. `exerciseUiState` (mapped)
10. `aggregateState` (combined)
11. `uiState` (final combined)

## Appendix C: Data Classes (12)

**Private State Classes (7):**
- `BaseInputs`
- `BaseState`
- `SessionState`
- `OrchestratorState`
- `CommandState`
- `ToggleState`
- `UiAggregate`

**Private Configuration Classes (3):**
- `RgbCameraValues`
- `RgbCameraInputState`
- `RgbCameraValidationResult`

**Public UI State Classes (12):**
- `MainUiState`
- `DeviceUiModel`
- `ShimmerDeviceUi`
- `ShimmerCandidateUi`
- `StreamUiModel`
- `DeviceEventUiModel`
- `RgbCameraUi`
- `RgbCameraAwbOption`
- `InventoryUiModel`
- `InventoryDeviceUi`
- `RecordingExerciseUi`
- `DeviceExerciseUi`

**Enum (1):**
- `RgbCameraField`
