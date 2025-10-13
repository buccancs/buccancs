```mermaid
graph TD
    subgraph "Hardware"
        A[Shimmer Sensor]
    end

    subgraph "BLE Communication"
        B[FastBle]
        C[BLE Notifications]
    end

    subgraph "Shimmer SDK"
        D[ThreadSafeByteFifoBuffer]
        E[IOThread & ProcessingThread]
        F[ShimmerDevice]
        G[ObjectCluster]
        H[Handler Message]
    end

    subgraph "Application Layer"
        I[ShimmerRepository]
        J[Kotlin Flow]
        K[ViewModel]
        L[Compose UI]
    end

    A -- Raw Sensor Data --> B
    B -- Sends --> C
    C -- Pushes data to --> D
    D --> E
    E -- Feeds data to --> F
    F -- Parses data into --> G
    G -- Wrapped in --> H
    H -- Sent to --> I
    I -- Converts message to --> J
    J -- Emits data to --> K
    K -- Updates --> L
```

### Shimmer Data Pipeline

This diagram illustrates the flow of data from the Shimmer sensor to the application's UI.

1. The **Shimmer Sensor** transmits raw data via BLE.
2. The **FastBle** library receives this data through **BLE Notifications**.
3. The data is pushed into a **ThreadSafeByteFifoBuffer** to ensure no data is lost.
4. The **IOThread** and **ProcessingThread** in the Shimmer SDK read from the buffer and feed the data to the *
   *ShimmerDevice**.
5. The `ShimmerDevice` parses the raw data and organizes it into an **ObjectCluster**.
6. The `ObjectCluster` is then wrapped in a **Handler Message** and sent to the application layer.
7. The **ShimmerRepository** receives the `Handler` message and converts it into a **Kotlin Flow**.
8. The **ViewModel** collects the data from the `Flow` and updates the **Compose UI**.
