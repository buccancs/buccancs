```mermaid
sequenceDiagram
    participant App
    participant ShimmerRepo
    participant ShimmerBTManagerAndroid
    participant Shimmer3BLEAndroid
    participant FastBle
    participant ShimmerSensor
    App ->> ShimmerRepo: scanForDevices()
    ShimmerRepo ->> ShimmerBTManagerAndroid: Starts scanning
    ShimmerBTManagerAndroid ->> App: Returns list of devices
    App ->> ShimmerRepo: connectToDevice(macAddress)
    ShimmerRepo ->> ShimmerBTManagerAndroid: connectShimmerThroughBTAddress(macAddress)
    ShimmerBTManagerAndroid ->> Shimmer3BLEAndroid: connect(macAddress)
    Shimmer3BLEAndroid ->> FastBle: connect()
    FastBle ->> ShimmerSensor: Establishes BLE connection
    ShimmerSensor -->> FastBle: Connection established
    FastBle -->> Shimmer3BLEAndroid: onConnectSuccess()
    Shimmer3BLEAndroid ->> Shimmer3BLEAndroid: Enables notifications
    Shimmer3BLEAndroid -->> ShimmerBTManagerAndroid: Sends state change message
    ShimmerBTManagerAndroid -->> ShimmerRepo: Relays state change
    ShimmerRepo -->> App: Emits Connected state
```

### Shimmer BLE Connection Sequence Diagram

This diagram shows the sequence of events for connecting to a Shimmer sensor.

1. The application initiates a device scan through the `ShimmerRepository`.
2. The repository uses `ShimmerBluetoothManagerAndroid` to perform the scan and returns a list of discovered devices to
   the app.
3. The user selects a device, and the app calls `connectToDevice()` on the repository.
4. The repository then calls down through the Shimmer SDK to `Shimmer3BLEAndroid`, which in turn uses the `FastBle`
   library to establish a connection with the sensor.
5. Once the connection is successful, `FastBle` calls back to `Shimmer3BLEAndroid`, which then enables notifications on
   the appropriate BLE characteristic.
6. Finally, the connection status is propagated back up to the application, which can then update its UI to reflect the
   new state.
