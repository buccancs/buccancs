# Topdon Application: Technical Documentation

This document provides a detailed overview of the Topdon application's architecture, including its API, SDK, key
classes, and data pipelines.

## 1. API and SDK

The application relies on a combination of third-party libraries and proprietary SDKs to interact with the Topdon
thermal camera hardware.

### 1.1. Vendor SDKs (Primary)

The core of the thermal imaging functionality is provided by a set of vendor-supplied Android Archive (AAR) files and
native libraries, primarily for the TC001 device. These are located in the `libir` module.

* **`libusbdualsdk_1.3.4_2406271906_standard.aar`**: This is the main SDK for the dual-sensor (IR + visible light)
  camera. It provides the following key components:
    * `com.infisense.usbdual.camera.USBMonitorManager`: Manages the USB connection to the camera.
    * `com.infisense.usbdual.camera.DualViewWithExternalCameraCommonApi`: Synchronizes and renders the IR and visible
      light streams.
    * `com.energy.iruvc.ircmd.IRCMD`: Provides a low-level command interface for controlling the camera (e.g., shutter,
      zoom, emissivity).
* **`com.energy.iruvc.sdkisp.LibIRProcess`**: Used to convert the raw YUYV frames from the camera into a pseudo-color
  ARGB format that can be displayed on the screen.
* **`com.infisense.usbir.utils.IRImageHelp`**: A utility library for applying pseudo-color palettes, detecting contours,
  and enforcing custom color settings.
* **`com.example.suplib.wrapper.SupHelp`**: Toggles the "TISR" super-resolution feature, which is accelerated using
  OpenCL.
* **Lite-specific SDKs**: The TC001 Lite has its own set of SDKs, including `com.energy.iruvccamera.usb` and
  `com.example.thermal_lite.camera`.

### 1.2. Third-Party Libraries

The application also uses several open-source libraries for common tasks:

* **ARouter**: A router library for navigating between activities and fragments.
* **EventBus**: For decoupled communication between different parts of the application.
* **GSYVideoPlayer**: For playing back recorded videos.
* **Lottie**: For displaying complex animations.
* **XLog**: For logging.
* **Blankj utilcode**: A utility library for various Android development tasks.

## 2. Key Classes and Responsibilities

The application is divided into several key classes, each with its own set of responsibilities.

* **`App.kt`**: The main application class. It extends `BaseApplication` and is responsible for initializing global
  services like logging, cloud services, and USB receivers.
* **`MainActivity.kt`**: The main entry point for the UI. It hosts a `ViewPager2` with three tabs and is responsible for
  handling USB device connection events and requesting necessary permissions.
* **`MainFragment.kt`**: The central dashboard of the app, displaying a list of connected devices. It uses `DeviceTools`
  to determine the status of connected hardware and launches the appropriate activity when a device is selected.
* **`IRThermalNightActivity.kt`**: The base activity for thermal camera capture. It handles the toolbar, menus,
  measurement overlays, and gallery integration. It delegates the actual USB and image processing control to its
  subclasses.
* **`IRThermalPlusActivity.kt`**: A subclass of `IRThermalNightActivity` that is specifically designed for the
  dual-sensor TC001 Plus hardware. It manages the `USBMonitorManager` and `DualViewWithExternalCameraCommonApi` to
  render the aligned IR and visible light streams.
* **`IRThermalLiteActivity.kt`**: Another subclass of `IRThermalNightActivity` that targets the TC001 Lite hardware and
  uses its specific SDKs.
* **`DeviceTools.kt` / `DeviceBroadcastReceiver.kt`**: These classes abstract the complexities of USB device detection
  and permission handling. They listen for USB connection events, identify the hardware, and then broadcast events (
  `DeviceConnectEvent`, `DevicePermissionEvent`) to the rest of the app using EventBus.
* **`SharedManager.kt` / `SaveSettingUtil.kt`**: These classes provide a centralized way to manage shared preferences
  and application state. They are used to store everything from device connection flags and user preferences to
  calibration data.

## 3. Data Pipelines

This section describes the end-to-end data flow for key operations within the application.

### 3.1. USB Connection and Permission

1. The `DeviceBroadcastReceiver` listens for Android's USB attach/detach broadcasts.
2. When a device is connected, the receiver uses `DeviceTools` to identify the hardware based on its vendor and product
   IDs.
3. `DeviceTools` then posts either a `DevicePermissionEvent` (if permissions are needed) or a `DeviceConnectEvent` to
   the EventBus.
4. `MainActivity` subscribes to these events and will either request USB permissions or launch the appropriate thermal
   imaging activity.

### 3.2. Thermal Preview (TC001 Plus)

1. When `IRThermalPlusActivity` starts, it initializes the `USBMonitorManager`.
2. The `USBMonitorManager` establishes a connection with the camera and provides an `IRCMD` interface for low-level
   control.
3. The activity then starts the preview, which binds the IR stream to a `TemperatureView` and the visible light stream
   to a `TextureView`.
4. The `LibIRProcess` and `IRImageHelp` libraries are used to convert the raw camera data into a pseudo-color bitmap,
   which is then rendered on the screen.

### 3.3. Image/Video Capture

1. **Still Images**: When the user captures a still image, the `TemperatureView` is drawn to a bitmap, and any
   overlays (e.g., temperature measurements) are composited on top.
2. **Video**: Video is captured using `VideoRecordFFmpeg`, which composites the IR, visible light, and overlay views
   over time.
3. The captured media is then saved to the device's storage, and a `GalleryAddEvent` is broadcast to notify the gallery
   to refresh its contents.
