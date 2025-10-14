**Last Modified:** 2025-10-14 11:00 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Architecture Visualisation

```mermaid
graph TD
subgraph "Device Discovery & Connection"
A[1. Scan for Devices] --> B{ShimmerSensorConnector}
B --> C[2. Circuit Breaker Check]
C --> D{ShimmerConnectionState}
D --> E[3. Connect via FastBle]
E --> F[4. Validate Connection (Result)]
end

subgraph "State Management"
G[ShimmerConnectionState.Disconnected] --> H[ShimmerConnectionState.Connecting]
H --> I{Connection Result}
I -->|Success| J[ShimmerConnectionState.Connected]
I -->|Failure| K[ShimmerConnectionState.Error]
K --> L[Circuit Breaker Opens]
L --> M[Countdown Timer]
M --> G
J --> N[ShimmerConnectionState.Recording]
end

subgraph "Data Streaming & Processing"
O[5. BLE Notifications] --> P{ThreadSafeByteFifoBuffer}
P --> Q[6. IOThread & ProcessingThread]
Q --> R{ShimmerDevice}
R --> S[7. Parse Data & Create ObjectCluster]
S --> T{Algorithm Modules}
T --> U[8. Add Derived Channels (HR, GSR)]
end

subgraph "Application Layer"
V[9. ShimmerDataWriter] --> W[10. Write CSV with Timestamps]
W --> X[11. Result<Unit, StorageError>]
X --> Y[12. Update ViewModel State]
Y --> Z[13. Compose UI Updates]
end

subgraph "Resource Cleanup"
AA[On Stop/Disconnect] --> AB[Release Handler]
AB --> AC[Clear Context References]
AC --> AD[Close Bluetooth Connection]
AD --> AE[Flush DataWriter]
end

F --> G
N --> O
U --> V
Z --> AA
```

### Shimmer Data Flow Diagram (Updated 2025-10-14)

This diagram shows the complete data flow for Shimmer GSR integration with production error handling, state management, and resource cleanup.

**Device Discovery & Connection:**

1. ShimmerSensorConnector initiates device scan
2. Circuit breaker validates no recent failures (prevents battery drain)
3. ShimmerConnectionState sealed class manages explicit state transitions
4. Connection attempted via FastBle library
5. Result type validates connection success or captures specific error

**State Management:**

- Sealed class hierarchy enforces valid state transitions
- Disconnected -> Connecting -> Connected -> Recording
- Connection failures transition to Error state
- Circuit breaker opens on repeated failures with user countdown
- Half-open state allows retry after timeout

**Data Streaming & Processing:**

5. Connected Shimmer device sends data via BLE notifications
6. ThreadSafeByteFifoBuffer prevents data loss from burst traffic
7. IOThread and ProcessingThread read buffer and pass to ShimmerDevice
8. ShimmerDevice parses raw bytes and creates ObjectCluster instances
9. Algorithm modules compute derived channels (heart rate from PPG, calibrated GSR)

**Application Layer:**

9. ShimmerDataWriter receives ObjectCluster with timestamp
10. Writes CSV format with microsecond-precision timestamps
11. Returns Result<Unit, StorageError> for type-safe error handling
12. ViewModel state updated via StateFlow emission
13. Jetpack Compose UI recomposes with latest sensor data

**Resource Cleanup:**

- On stop or disconnect, explicit cleanup sequence executed
- Handler references released (fixes memory leak)
- Context references cleared (prevents Activity leak)
- Bluetooth connection closed properly
- DataWriter flushes remaining buffers and closes file handles

**Key Improvements:**

- Result pattern replaces exception-based error handling
- Circuit breaker protects battery from connection loops
- Sealed states prevent invalid transitions
- Resource cleanup fixes all memory leaks
- Separated DataWriter enables unit testing
