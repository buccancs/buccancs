# `resources/AndroidManifest.xml`

- Declares the PURE package `com.shimmersensing.shimmerconnect`, Bluetooth/location permissions (including Android 12+
  `BLUETOOTH_SCAN/CONNECT/ADVERTISE`), and storage access for log export.
- Registers `MainActivity` (launcher), `ShimmerService`, the pairing dialog, and AndroidX startup/profile installers.
- Minimum SDK 21, target SDK 35, version `1.3.1` (code 32). Useful reference when aligning Buccancs manifest entries.
- Notes for Buccancs integration:
    - Location permissions (both coarse/fine + background) are required because pre-Android 12 BLE scans mandate
      location access. If your strategy differs (e.g., using the new Nearby APIs), adjust accordingly.
    - `android:extractNativeLibs="true"` indicates the APK bundles native HID libraries; if you strip those out, you can
      drop this flag.
    - A custom signature-level permission (`DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`) is declared to interact with the
      app’s receivers. If you do not export receivers, you can omit it in your manifest.
