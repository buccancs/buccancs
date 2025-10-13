```mermaid
graph TD
subgraph "Device Discovery & Connection"
A[1. Scan for Devices] --> B{ShimmerBluetoothManagerAndroid}
B --> C[2. Select Device (ShimmerBluetoothDialog)]
C --> D{Shimmer3BLEAndroid}
D --> E[3. Connect via FastBle]
end

subgraph "Data Streaming & Processing"
F[4. BLE Notifications] --> G{ThreadSafeByteFifoBuffer}
G --> H[5. IOThread & ProcessingThread]
H --> I{ShimmerDevice}
I --> J[6. Parse Data & Create ObjectCluster]
J --> K{Algorithm Modules}
K --> L[7. Add Derived Channels (e.g., HR)]
end

subgraph "Application Layer"
M[8. Send Handler Message (MSG_IDENTIFIER_DATA_PACKET)] --> N{ShimmerService / Activity}
N --> O[9. Receive Message & Extract ObjectCluster]
O --> P[10. Update UI / Save to DB]
end

E --> F
L --> M
```

**Diagram Description:**

This diagram shows the data flow for the Shimmer GSR integration, from device discovery to the application layer.

1. **Device Discovery & Connection:** The process starts with the `ShimmerBluetoothManagerAndroid` scanning for
   available devices. The user then selects a device (typically using the `ShimmerBluetoothDialog`), and the
   `Shimmer3BLEAndroid` class initiates a connection using the `FastBle` library.
2. **Data Streaming & Processing:** Once connected, the Shimmer device starts sending data via BLE notifications. This
   data is placed in a `ThreadSafeByteFifoBuffer` to prevent data loss. The `IOThread` and `ProcessingThread` read from
   this buffer and pass the data to the `ShimmerDevice` for parsing. The `ShimmerDevice` creates `ObjectCluster`
   instances from the raw data. These can then be processed by algorithm modules to generate derived data (like heart
   rate).
3. **Application Layer:** The processed data is then sent to the application layer (either a `ShimmerService` or an
   Android `Activity`) as a `Handler` message. The application receives this message, extracts the `ObjectCluster`, and
   can then use the data to update the UI or save it to a database.
