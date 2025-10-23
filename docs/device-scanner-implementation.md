# Device Scanner Implementation

Updated: 2025-10-20

---

## Overview

The device scanner feature provides a unified experience for discovering,
granting access to, and monitoring USB (Topdon TC001) and Bluetooth peripherals.
It consists of a long-lived hardware service, a reusable Compose dialog, and
host integrations that react to hardware intents in real time.

---

## Service Responsibilities (`DeviceScannerService`)

Located at `app/src/main/java/com/buccancs/hardware/DeviceScannerService.kt`.

- Manual scans: kicks off Bluetooth discovery (12 s window) and enumerates USB
  devices on demand.
- Background scans: polls USB devices every 30 seconds by default, pausing when
  `MainActivity` stops.
- Permission flow: exposes `requestUsbPermission` and tracks grant/deny events.
- State surface: publishes combined scan state via `StateFlow` and discrete
  hardware events via `SharedFlow`.

Helper APIs

```kotlin
deviceScanner.startManualScan()
deviceScanner.startBackgroundScan(intervalMs = 30_000)
deviceScanner.stopBackgroundScan()
deviceScanner.requestUsbPermission(device)

deviceScanner.scanState.collect { /* USB + Bluetooth lists */ }
deviceScanner.deviceEvents.collect { /* Attachment, permission, etc. */ }
```

Dependencies are injected with Hilt (`BluetoothService`, `UsbService`,
`UsbManager`). Long-running work executes on application-scoped coroutines.

---

## UI Components (`DeviceScannerDialog`)

Located at `app/src/main/java/com/buccancs/ui/components/scanner/DeviceScannerDialog.kt`.

- Material 3 dialog with USB and Bluetooth tabs.
- Displays device name, vendor/product (USB) or address + RSSI (Bluetooth).
- Shows scanning progress, empty states, and inline errors.
- Includes a dedicated `TopdonDevicePermissionDialog` when Topdon hardware is
  detected and permission has not been granted.

Reusable composables:

- `DeviceScannerDialog` – root dialog with tabbed layout.
- `UsbDeviceItem` / `BluetoothDeviceItem` – per-device rows.
- `TopdonDevicePermissionDialog` – modal confirmation for USB permission.

---

## Host Integration

- **`MainActivity`** registers USB attachment intents, starts/stops background
  scans, and pipes events into a `SharedFlow` for downstream consumption.
- **`AppNavHost`** listens for `usbAttachmentEvents` and displays the Topdon
  permission dialog automatically.
- **`DevicesScreen`** exposes the search icon that opens the dialog.
- **`DeviceScannerViewModel`** provides the service to Compose via Hilt.

Topdon vendor/product IDs are listed in `app/src/main/res/xml/device_filter.xml`
and include 11261, 1003, 3034, and 1240.

---

## User Workflows

**Manual scan**

1. Open the Devices screen and tap the search icon.
2. In the dialog, press **Start Scan** to begin Bluetooth discovery (USB devices
   appear immediately).
3. Grant USB permission for devices that show the **Grant** button.

**Automatic Topdon detection**

1. Connect the TC001 via USB-C.
2. The permission dialog appears with device details.
3. Tap **Allow** to enable streaming (or **Deny** to dismiss).
4. DeviceScannerService emits success/failure events for telemetry.

Background USB scanning runs continuously while the app is in the foreground; no
user interaction is required.

---

## Configuration & Permissions

- Bluetooth: `BLUETOOTH`, `BLUETOOTH_ADMIN` (API ≤30) or `BLUETOOTH_SCAN`,
  `BLUETOOTH_CONNECT`, `BLUETOOTH_ADVERTISE` (API 31+), plus coarse/fine
  location.
- USB: device filters declared in `app/src/main/res/xml/device_filter.xml`;
  permission intent wired via `UsbManager`.
- The scanner is registered as a `@Singleton` and leverages `@ApplicationScope`
  coroutines for predictable lifecycle management.

---

## Testing Checklist

- Plug in a Topdon device → permission dialog appears, **Allow** grants access.
- Deny permission → dialog dismisses, event recorded.
- Manual scan → dialog lists USB + Bluetooth devices, Bluetooth run stops after
  the 12 s window.
- USB devices without permission render the **Grant** button and update after
  permission is granted.
- Bluetooth entries show live RSSI.
- Background scanning detects attach/detach while app is active.
- Stopping `MainActivity` halts background scans (no lingering coroutines).

---

## Future Enhancements

1. Persist preferred devices and auto-connect policies.
2. Provide waveform/diagnostics for Bluetooth health during discovery.
3. Surface notifications when known hardware reconnects.
4. Expand filtering to highlight thermal-capable devices only.
5. Add device nicknames to aid lab workflows.
