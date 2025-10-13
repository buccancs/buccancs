# Shimmer GSR Integration: Technical Documentation

This document provides a detailed overview of the Shimmer GSR integration, including its API, SDK, key classes, and data
pipelines.

## 1. API and SDK

The Shimmer integration is built upon a set of core Java libraries and an Android-specific instrument driver.

### 1.1. Core SDK

The primary SDK components are:

* **`shimmerdriver-0.11.5_beta.jar`**: This is the main driver library. It contains the `ShimmerDevice` class, which is
  the core of the driver. It manages the device's configuration, parses incoming data, and handles calibration.
* **`shimmerbluetoothmanager-0.11.5_beta.jar`**: This library contains the platform-agnostic `ShimmerBluetoothManager`
  class, which is responsible for managing connections to multiple Shimmer devices.
* **`shimmerandroidinstrumentdriver-3.2.4_beta.aar`**: This is the Android-specific part of the SDK. It provides the
  following key classes:
    * `ShimmerBluetoothManagerAndroid`: A subclass of `ShimmerBluetoothManager` that is specifically designed for
      Android. It uses the `BluetoothLeScanner` for device discovery.
    * `Shimmer3BLEAndroid`: A subclass of `ShimmerBluetooth` that uses the `FastBle` library to handle the BLE
      communication.
* **`FastBle`**: A third-party library that is used for the low-level BLE communication.

### 1.2. Key APIs

* **`ShimmerBluetoothManagerAndroid`**: The main entry point for the SDK on Android. It is used to scan for and connect
  to devices.
* **`Shimmer3BLEAndroid.connect(mac, "default")`**: Initiates a connection to a Shimmer device.
* **`ShimmerDevice`**: Provides methods for controlling the device, such as `startStreaming()`, `stopStreaming()`,
  `writeEnabledSensors()`, and `setSamplingRate()`.
* **`Handler` Messages**: The SDK uses `Handler` messages to communicate with the application. The most important
  messages are:
    * `ShimmerBluetooth.MSG_IDENTIFIER_STATE_CHANGE`: Indicates a change in the connection state.
    * `ShimmerBluetooth.MSG_IDENTIFIER_DATA_PACKET`: Contains a new data packet from the device.

## 2. Key Classes and Responsibilities

* **`ShimmerBluetoothManagerAndroid`**: Manages the connection lifecycle for multiple Shimmer devices on Android.
* **`Shimmer3BLEAndroid`**: Handles the BLE communication for a single Shimmer 3 device. It uses the `FastBle` library
  and manages the I/O and processing threads.
* **`ShimmerService`**: An Android `Service` that hosts the `ShimmerBluetoothManagerAndroid` and can manage logging and
  plotting of data. It allows for long-running background connections.
* **`ShimmerDevice`**: The core of the driver. It holds the device's state, configuration, and calibration information.
  It is also responsible for parsing the incoming byte stream into meaningful data.
* **`ObjectCluster` / `FormatCluster`**: These are data-holding classes that are used to transport sensor data from the
  driver to the application. `ObjectCluster` contains the data for all sensors in a single packet, and `FormatCluster`
  contains the data for a single sensor channel, with different formats (e.g., calibrated, uncalibrated).
* **`ShimmerBluetoothDialog`**: A UI component that provides a simple way for the user to select a Shimmer device to
  connect to.

## 3. Data Pipelines

### 3.1. Connection and Streaming

1. The `ShimmerBluetoothManagerAndroid` scans for nearby BLE devices.
2. The user selects a device to connect to (often using the `ShimmerBluetoothDialog`).
3. The manager creates a `Shimmer3BLEAndroid` instance and calls its `connect()` method.
4. `Shimmer3BLEAndroid` uses the `FastBle` library to establish a connection and enable notifications on the Shimmer
   data characteristic.
5. Once the connection is established, the `IOThread` starts reading data from the `ThreadSafeByteFifoBuffer`, which is
   fed by the BLE notification callback.
6. The `IOThread` passes the data to the `ShimmerDevice` for parsing.
7. The `ShimmerDevice` parses the byte stream and creates `ObjectCluster` instances.
8. These `ObjectCluster` instances are then sent to the application via a `Handler` message (
   `MSG_IDENTIFIER_DATA_PACKET`).

### 3.2. Data Processing

1. The application receives the `ObjectCluster` in its `Handler`.
2. The application can then extract the data for the desired sensor channels from the `ObjectCluster`.
3. The data is available in different formats (e.g., calibrated, uncalibrated) through the `FormatCluster`.
4. The application can then use this data to update the UI, save it to a database, or perform further analysis.
5. The `ShimmerBiophysicalProcessingLibrary` can be used to apply algorithms to the data to derive additional metrics (
   e.g., heart rate from a PPG signal).
