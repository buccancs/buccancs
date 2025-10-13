```mermaid
graph TD
subgraph "Application Layer (Android)"
A[Sample Apps (e.g., shimmer3BLEBasicExample)]
UI[GUI Utilities (ShimmerBluetoothDialog)]
Service[ShimmerService]
end

subgraph "Android Instrument Driver"
AID[shimmerandroidinstrumentdriver-3.2.4_beta.aar]
AID_Manager[ShimmerBluetoothManagerAndroid]
AID_BLE[Shimmer3BLEAndroid]
FastBLE[FastBle Library]
end

subgraph "Core Driver & Bluetooth Manager"
Driver[shimmerdriver-0.11.5_beta.jar]
BTManager[shimmerbluetoothmanager-0.11.5_beta.jar]
Driver_Device[ShimmerDevice]
Driver_BT[ShimmerBluetooth]
Driver_Manager[ShimmerBluetoothManager]
end

subgraph "Data & Algorithms"
Data[ObjectCluster / FormatCluster]
Algos[ShimmerBiophysicalProcessingLibrary]
end

A --> Service
A --> UI
Service --> AID_Manager

UI --> AID_Manager

AID --> AID_Manager
AID --> AID_BLE
AID_Manager --> Driver_Manager
AID_BLE --> Driver_BT
AID_BLE --> FastBLE

BTManager --> Driver_Manager
Driver --> Driver_Device
Driver --> Driver_BT

Driver_Device --> Data
Driver_Device --> Algos

style A fill: #f9f, stroke: #333, stroke-width: 2px
style Service fill: #ccf, stroke: #333, stroke-width: 2px
style AID fill: #f66, stroke: #333, stroke-width: 2px
style Driver fill: #6f6, stroke: #333, stroke-width: 2px
```

**Diagram Description:**

This diagram illustrates the modular architecture of the Shimmer GSR integration.

* **Application Layer (Purple):** This layer contains the example applications and UI components that interact with the
  Shimmer SDK.
* **Android Instrument Driver (Red):** This is the Android-specific part of the SDK, provided as an AAR file. It
  contains classes like `ShimmerBluetoothManagerAndroid` and `Shimmer3BLEAndroid` that bridge the gap between the core
  driver and the Android OS. It uses the `FastBle` library for the underlying BLE communication.
* **Core Driver & Bluetooth Manager (Green):** These are the core Java libraries (`shimmerdriver` and
  `shimmerbluetoothmanager`) that contain the platform-agnostic logic for managing and communicating with Shimmer
  devices.
* **Data & Algorithms (Blue):** This layer represents the data structures (`ObjectCluster`, `FormatCluster`) used to
  transport sensor data, as well as the libraries that provide algorithms for processing that data (e.g.,
  `ShimmerBiophysicalProcessingLibrary`).

The arrows show the dependencies between the modules. The application layer communicates with the Android Instrument
Driver, which in turn relies on the core driver and Bluetooth manager.
