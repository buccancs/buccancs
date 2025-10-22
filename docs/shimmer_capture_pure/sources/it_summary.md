# `sources/it`

- Legacy “EasyBluetooth” utility (`it.gerdavax.easybluetooth.*`) offering thin wrappers for classic Bluetooth sockets
  and discovery callbacks.
- PURE bundles it for compatibility with older Shimmer firmware; modern code paths prefer the in-house
  `com.shimmerresearch.androidradiodriver` layer.
- No app-specific logic—safe to replace with Android’s standard Bluetooth APIs if you control both ends.
- The package includes interfaces like `BtSocket`, `LocalDevice`, and small platform-specific implementations. They
  abstract away `BluetoothSocket` differences between API levels.
- Search in PURE for references to `it.gerdavax` before removing—most new flows rely on `android.bluetooth.*`, but if
  you see `EasyBluetooth` calls during connect/setup, plan a migration path.
