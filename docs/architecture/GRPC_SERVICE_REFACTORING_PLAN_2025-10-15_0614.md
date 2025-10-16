**Last Modified:** 2025-10-15 06:14 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Architecture Plan

# gRPC Service Refactoring Plan

## Current State

GrpcServer.kt contains 878 lines with 6 nested service implementation classes:

1. **CommandServiceImpl** (lines 108-170) - Command distribution and acknowledgement
2. **OrchestrationServiceImpl** (lines 172-388) - Device registration and status monitoring
3. **TimeSyncServiceImpl** (lines 390-464) - Time synchronisation protocol
4. **PreviewServiceImpl** (lines 466-536) - Preview frame streaming
5. **SensorStreamServiceImpl** (lines 538-621) - Sensor data streaming
6. **DataTransferServiceImpl** (lines 623-875) - Session file uploads

## Design Issues

Current nested class structure causes several problems:

1. **Poor testability** - Cannot unit test services in isolation
2. **High coupling** - All services tightly coupled to GrpcServer class
3. **Complex dependencies** - Unclear which repository each service needs
4. **Difficult navigation** - 878 lines with multiple nested classes hard to read
5. **Shared state** - Services may inadvertently share state through outer class

## Refactoring Strategy

### Phase 1: Extract Service Interfaces

Create interface for each service defining its contract:

```kotlin
interface CommandService {
    fun subscribeCommands(request: CommandSubscribeRequest): Flow<CommandEnvelope>
    fun acknowledgeCommand(receipt: CommandReceipt): CommandAck
}

interface OrchestrationService {
    suspend fun registerDevice(registration: DeviceRegistration): RegistrationAck
    fun subscribeStatus(request: StatusSubscribeRequest): Flow<DeviceStatus>
}

interface TimeSyncService {
    suspend fun ping(request: TimeSyncPing): TimeSyncPong
    suspend fun reportSync(report: TimeSyncReport): Empty
}

interface PreviewService {
    suspend fun sendPreview(frames: Flow<PreviewFrame>): PreviewAck
}

interface SensorStreamService {
    suspend fun streamSamples(batches: Flow<SensorSampleBatch>): SensorStreamAck
}

interface DataTransferService {
    fun upload(requests: Flow<DataTransferRequest>): Flow<DataTransferStatus>
}
```

### Phase 2: Extract Implementation Classes

Move each nested class to separate file with minimal dependencies:

**File structure:**

```
desktop/src/main/kotlin/com/buccancs/desktop/data/grpc/
├── GrpcServer.kt (coordinator only)
├── services/
│   ├── CommandServiceImpl.kt
│   ├── OrchestrationServiceImpl.kt
│   ├── TimeSyncServiceImpl.kt
│   ├── PreviewServiceImpl.kt
│   ├── SensorStreamServiceImpl.kt
│   └── DataTransferServiceImpl.kt
```

**Dependency analysis per service:**

1. **CommandServiceImpl**
    - Dependencies: CommandRepository
    - Size: ~60 lines
    - Complexity: Low

2. **OrchestrationServiceImpl**
    - Dependencies: SessionRepository, DeviceRepository, CommandRepository, SensorRecordingManager
    - Size: ~170 lines
    - Complexity: High (device registration, status aggregation)

3. **TimeSyncServiceImpl**
    - Dependencies: None (stateless)
    - Size: ~60 lines
    - Complexity: Low

4. **PreviewServiceImpl**
    - Dependencies: PreviewRepository, DeviceRepository
    - Size: ~70 lines
    - Complexity: Medium

5. **SensorStreamServiceImpl**
    - Dependencies: SensorRecordingManager, DeviceRepository
    - Size: ~80 lines
    - Complexity: Medium

6. **DataTransferServiceImpl**
    - Dependencies: SessionRepository, DeviceRepository
    - Size: ~220 lines
    - Complexity: High (file upload, checksum validation)

### Phase 3: Update GrpcServer to Coordinator

Simplify GrpcServer.kt to just wire services together:

```kotlin
class GrpcServer(
    private val port: Int,
    private val commandService: CommandService,
    private val orchestrationService: OrchestrationService,
    private val timeSyncService: TimeSyncService,
    private val previewService: PreviewService,
    private val sensorStreamService: SensorStreamService,
    private val dataTransferService: DataTransferService
) {
    private val server: Server = NettyServerBuilder
        .forPort(port)
        .executor(Dispatchers.Default.asExecutor())
        .addService(commandService)
        .addService(orchestrationService)
        .addService(timeSyncService)
        .addService(previewService)
        .addService(sensorStreamService)
        .addService(dataTransferService)
        .build()
    
    fun start() { ... }
    fun stop() { ... }
    fun awaitTermination() { ... }
}
```

### Phase 4: Dependency Injection

Update DI module to provide service implementations:

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object GrpcServiceModule {
    
    @Provides
    @Singleton
    fun provideCommandService(
        commandRepository: CommandRepository
    ): CommandService = CommandServiceImpl(commandRepository)
    
    @Provides
    @Singleton
    fun provideOrchestrationService(
        sessionRepository: SessionRepository,
        deviceRepository: DeviceRepository,
        commandRepository: CommandRepository,
        sensorRecordingManager: SensorRecordingManager
    ): OrchestrationService = OrchestrationServiceImpl(...)
    
    // ... other services
    
    @Provides
    @Singleton
    fun provideGrpcServer(
        @Named("grpc.port") port: Int,
        commandService: CommandService,
        orchestrationService: OrchestrationService,
        timeSyncService: TimeSyncService,
        previewService: PreviewService,
        sensorStreamService: SensorStreamService,
        dataTransferService: DataTransferService
    ): GrpcServer = GrpcServer(port, ...)
}
```

## Benefits

1. **Testability** - Each service can be unit tested independently with mock dependencies
2. **Clarity** - Clear dependency requirements for each service
3. **Maintainability** - Services are 60-220 lines instead of single 878-line file
4. **Reusability** - Services could be deployed independently in future microservices architecture
5. **Type safety** - Interface contracts prevent API drift
6. **Parallel development** - Multiple developers can work on different services simultaneously

## Implementation Order

1. Create services/ directory structure
2. Extract TimeSyncServiceImpl (simplest, no dependencies)
3. Extract CommandServiceImpl (simple, single dependency)
4. Extract PreviewServiceImpl (medium complexity)
5. Extract SensorStreamServiceImpl (medium complexity)
6. Extract OrchestrationServiceImpl (complex, multiple dependencies)
7. Extract DataTransferServiceImpl (complex, file handling)
8. Update GrpcServer to coordinator
9. Add DI module configuration
10. Update tests to use extracted services

## Testing Strategy

For each extracted service:

1. Create unit test file (e.g., CommandServiceImplTest.kt)
2. Test with mock dependencies using MockK
3. Test error handling and edge cases
4. Test flow collection and cancellation
5. Integration test with real gRPC client

Example test structure:

```kotlin
class CommandServiceImplTest {
    private lateinit var commandRepository: CommandRepository
    private lateinit var service: CommandServiceImpl
    
    @BeforeEach
    fun setup() {
        commandRepository = mockk()
        service = CommandServiceImpl(commandRepository)
    }
    
    @Test
    fun `subscribeCommands emits commands from repository`() = runTest {
        // Given
        val deviceId = "test-device"
        val commands = flowOf(
            CommandEnvelope.newBuilder()...build()
        )
        every { commandRepository.subscribeDevice(deviceId) } returns commands
        
        // When
        val result = service.subscribeCommands(
            CommandSubscribeRequest.newBuilder()
                .setDeviceId(deviceId)
                .build()
        ).toList()
        
        // Then
        assertThat(result).hasSize(1)
        verify { commandRepository.subscribeDevice(deviceId) }
    }
}
```

## Migration Risks

1. **Breaking changes** - Must maintain exact API contract during extraction
2. **Missing dependencies** - Service might rely on shared state in outer class
3. **Coroutine context** - Flow builders must preserve context correctly
4. **Error handling** - Exception propagation must work identically
5. **Lifecycle** - Service shutdown must be coordinated

## Rollback Plan

If issues discovered:

1. Keep original GrpcServer.kt as GrpcServerLegacy.kt
2. Add feature flag to switch between implementations
3. Run both implementations in parallel for validation
4. Metrics comparison to ensure behaviour unchanged
5. Gradual rollout with canary deployment

## Success Metrics

- GrpcServer.kt reduced from 878 to ~100 lines
- Each service file 60-220 lines (average ~110 lines)
- Unit test coverage >80% for each service
- Integration tests pass with identical behaviour
- No performance regression (measure with JMH)
- Build time unchanged or improved

## Timeline Estimate

- Phase 1 (Interfaces): 2 hours
- Phase 2 (Extract services): 6-8 hours
- Phase 3 (Update coordinator): 2 hours
- Phase 4 (DI setup): 2 hours
- Testing: 4-6 hours
- Documentation: 2 hours

**Total: 18-22 hours (2-3 days)**

## Next Steps

1. Review plan with team
2. Create feature branch `refactor/grpc-service-extraction`
3. Start with TimeSyncServiceImpl extraction
4. Validate approach before proceeding to complex services
5. Document any deviations from plan
