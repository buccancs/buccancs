```mermaid
graph TD
    subgraph "Application Layer (Your App)"
        ComposeUI["Compose UI (Screens, ViewModels)"]
        AppService["Foreground Service (Optional)"]
        HiltDI["Hilt (Dependency Injection)"]
    end

    subgraph "Buccancs Repository (Kotlin Wrappers)"
        ShimmerRepo["ShimmerRepository"]
        KotlinFlows["Kotlin Flows (StateFlow, SharedFlow)"]
    end

    subgraph "Shimmer Android SDK"
        subgraph "Android Instrument Driver"
            ShimmerServiceSDK["ShimmerService (SDK Service)"]
            ShimmerBTManagerAndroid["ShimmerBluetoothManagerAndroid"]
            Shimmer3BLEAndroid["Shimmer3BLEAndroid"]
            HandlerMsgs["Handler Messages"]
        end
        subgraph "Core Driver"
            ShimmerDevice["ShimmerDevice"]
            ShimmerBTManager["ShimmerBluetoothManager"]
            ObjectCluster["ObjectCluster (Data)"]
        end
    end

    subgraph "3rd Party BLE Library"
        FastBle["FastBle"]
    end

    subgraph "Hardware"
        ShimmerSensor["Shimmer Sensor"]
    end

    ComposeUI -- Observes --> KotlinFlows
    AppService -- Uses --> ShimmerRepo
    HiltDI -- Injects --> ShimmerRepo
    ShimmerRepo -- Exposes --> KotlinFlows
    ShimmerRepo -- Wraps --> ShimmerServiceSDK
    ShimmerRepo -- Wraps --> ShimmerBTManagerAndroid
    ShimmerServiceSDK -- Uses --> ShimmerBTManagerAndroid
    ShimmerBTManagerAndroid -- Inherits from --> ShimmerBTManager
    ShimmerBTManagerAndroid -- Creates --> Shimmer3BLEAndroid
    Shimmer3BLEAndroid -- Uses --> FastBle
    Shimmer3BLEAndroid -- Creates --> ShimmerDevice
    Shimmer3BLEAndroid -- Sends --> HandlerMsgs
    ShimmerDevice -- Creates --> ObjectCluster
    FastBle -- Communicates with --> ShimmerSensor
```

### Shimmer Integration Architecture

This diagram shows a recommended architecture for integrating the Shimmer SDK into a modern Android application using
Jetpack Compose and Hilt.

- **Application Layer:** This is your application's UI layer, built with Compose. ViewModels observe data from the
  repository, and a foreground service can be used to manage long-running connections.
- **Buccancs Repository:** This is a crucial layer that you will create. It acts as a bridge between the Shimmer SDK and
  your application. It wraps the `Handler`-based SDK and exposes the data as modern Kotlin `Flows`, which are much
  easier to work with in a Compose-based architecture.
- **Shimmer Android SDK:** This is the provided Shimmer SDK, consisting of the Android-specific instrument driver and
  the core driver libraries.
- **3rd Party BLE Library:** The Shimmer SDK uses the `FastBle` library for its Bluetooth Low Energy communication.
- **Hardware:** The physical Shimmer sensor.

The key takeaway here is the use of a repository to abstract away the complexities of the Shimmer SDK and provide a
clean, modern API to the rest of your application.
