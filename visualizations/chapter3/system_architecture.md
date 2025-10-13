```mermaid
graph TD
    subgraph "PC Orchestrator"
        PC[PC Controller]
    end

subgraph "Android Device"
subgraph "Application Layer"
App[Main Application]
UI[User Interface]
end

subgraph "Control & Synchronization"
SyncManager[Sync Manager]
NetworkHandler[Network Handler (TCP Server)]
end

subgraph "Data Acquisition Layer"
GSR_Module[GSR Module]
Thermal_Module[Thermal Module]
RGB_Module[RGB Module]
end

subgraph "Driver & Hardware Interface"
ShimmerSDK[Shimmer SDK]
TopdonSDK[Topdon SDK]
CameraX[CameraX API]
Bluetooth[Bluetooth Stack]
USB[USB Host Stack]
end
end

subgraph "External Sensors"
Shimmer[Shimmer3 GSR]
Topdon[Topdon TC001]
RGBCam[Internal RGB Camera]
end

PC -- TCP/IP --> NetworkHandler
NetworkHandler --> SyncManager
SyncManager --> App
App --> UI

SyncManager -- Controls --> GSR_Module
SyncManager -- Controls --> Thermal_Module
SyncManager -- Controls --> RGB_Module

GSR_Module --> ShimmerSDK
Thermal_Module --> TopdonSDK
RGB_Module --> CameraX

ShimmerSDK --> Bluetooth
TopdonSDK --> USB

Bluetooth -- BLE --> Shimmer
USB -- USB- C --> Topdon
CameraX -- Internal --> RGBCam
```

### Figure: System Architecture Diagram

This diagram details the system's architecture, illustrating the interaction between the PC orchestrator, the Android
device, and the external sensors.

- The **PC Orchestrator** sends control commands over TCP/IP.
- The **Android Device** is structured in layers:
    - **Control & Synchronization:** The `Network Handler` receives commands from the PC and passes them to the
      `Sync Manager`, which orchestrates the recording process.
    - **Data Acquisition Layer:** Separate modules for GSR, Thermal, and RGB data are responsible for acquiring data
      from their respective sensors.
    - **Driver & Hardware Interface:** This layer contains the specific SDKs and APIs (`Shimmer SDK`, `Topdon SDK`,
      `CameraX`) used to communicate with the hardware.
- The **External Sensors** are the Shimmer3 GSR, the Topdon TC001, and the internal RGB camera.
