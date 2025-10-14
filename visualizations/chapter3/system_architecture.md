**Last Modified:** 2025-10-14 11:00 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Architecture Visualisation

```mermaid
graph TD
    subgraph "PC Orchestrator (Compose Desktop)"
        PC[Desktop Controller]
        GrpcServer[gRPC Server]
        SessionManager[Session Manager]
        TimeSyncServer[Time Sync Server (NTP-like)]
        MdnsServer[mDNS Discovery]
    end

subgraph "Android Device (MVVM + Hilt)"
subgraph "Application Layer"
App[Main Application (Compose UI)]
RecordingService[Recording Service (Foreground)]
UploadService[Upload Service (WorkManager)]
end

subgraph "Control & Synchronization"
TimeSyncClient[Time Sync Client (<10ms)]
CommandClient[Command Client (gRPC)]
ControlServer[Control Server (Local)]
MdnsAdvertiser[mDNS Advertiser]
end

subgraph "Domain Layer"
UseCases[Use Cases (Business Logic)]
ResultTypes[Result<T, E> Pattern]
end

subgraph "Data Acquisition Layer"
ShimmerConnector[Shimmer Connector + State Machine]
TopdonConnector[Topdon Connector]
RgbConnector[RGB Camera Connector]
AudioConnector[Audio Connector]
CircuitBreaker[Circuit Breaker]
end

subgraph "Driver & Hardware Interface"
ShimmerSDK[Shimmer SDK (BLE)]
TopdonSDK[Topdon SDK (USB)]
Camera2[Camera2 API]
MediaCodec[MediaCodec/MediaMuxer]
AudioRecord[AudioRecord]
end
end

subgraph "External Sensors"
Shimmer[Shimmer3 GSR+ (BLE)]
Topdon[Topdon TC001 (USB-C)]
RGBCam[Internal RGB Camera (4K@60fps)]
Mic[Microphone]
end

PC -- gRPC/TLS --> CommandClient
GrpcServer -- Commands --> CommandClient
TimeSyncServer -- NTP Protocol --> TimeSyncClient
MdnsServer -- Discovery --> MdnsAdvertiser
SessionManager -- Session Metadata --> UploadService

CommandClient --> RecordingService
TimeSyncClient --> RecordingService
ControlServer -- Local Commands --> RecordingService
RecordingService --> UseCases
UseCases -- Result Types --> ShimmerConnector
UseCases -- Result Types --> TopdonConnector
UseCases -- Result Types --> RgbConnector
UseCases -- Result Types --> AudioConnector

ShimmerConnector -- Protected by --> CircuitBreaker
ShimmerConnector --> ShimmerSDK
TopdonConnector --> TopdonSDK
RgbConnector --> Camera2
RgbConnector --> MediaCodec
AudioConnector --> AudioRecord

ShimmerSDK -- Bluetooth LE --> Shimmer
TopdonSDK -- USB Host --> Topdon
Camera2 -- Internal --> RGBCam
AudioRecord -- Internal --> Mic

App -- Observes --> UseCases
UploadService -- Transfers --> SessionManager
```

### Figure: System Architecture Diagram (Updated 2025-10-14)

This diagram details the production system architecture with comprehensive error handling, resource management, and distributed coordination.

**PC Orchestrator (Compose Desktop):**

- **gRPC Server:** Distributes recording commands, time sync, stimuli to connected Android devices with command replay for reconnections
- **Session Manager:** Creates session directories, manages metadata manifests, aggregates uploads from devices
- **Time Sync Server:** NTP-like protocol with RTT measurement providing <10ms accuracy across devices
- **mDNS Discovery:** Automatic device detection on local network

**Android Device Architecture (MVVM + Hilt):**

- **Application Layer:** Jetpack Compose UI with MVVM pattern, RecordingService coordinates multi-sensor capture, UploadService handles background session transfers
- **Control & Synchronization:** Time sync client maintains clock accuracy, command client receives orchestrator commands, control server accepts local commands, mDNS advertiser broadcasts device availability
- **Domain Layer:** Use cases implement business logic, Result pattern provides type-safe error handling across all operations
- **Data Acquisition Layer:** Sensor connectors manage hardware lifecycle with explicit state machines, circuit breaker protects against connection failure loops
- **Driver Interface:** Shimmer SDK (BLE), Topdon SDK (USB), Camera2 API (4K@60fps), MediaCodec segmented recording, AudioRecord for contextual annotation

**External Sensors:**

- **Shimmer3 GSR+:** Bluetooth LE connection, 128Hz sampling, CSV output with microsecond timestamps
- **Topdon TC001:** USB-C connection, 256x192 resolution at 25Hz, 16-bit raw thermal data
- **RGB Camera:** Internal camera via Camera2, 4K@60fps video, periodic RAW (DNG) capture for calibration
- **Microphone:** High-quality audio capture synchronised with other modalities

**Key Architectural Features:**

- Result pattern eliminates exception-based error flow
- Circuit breaker prevents battery drain from failed connections
- Segmented recording survives application crashes
- gRPC with TLS for secure orchestrator communication
- mDNS for automatic multi-device discovery
- WorkManager ensures reliable background uploads
- Hilt dependency injection with comprehensive test coverage
