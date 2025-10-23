# IRCamera - Official Topdon TC001 Thermal Camera Implementation

## Overview

The IRCamera project in `external/IRCamera` is the official Topdon
implementation for their TC001 thermal camera device. This is a complete Android
application built with a modular architecture that supports multiple thermal
camera devices from the Topdon product line.

## Project Structure

### Build Configuration

- **Gradle Version**: 7.1.3
- **Kotlin**: 1.7.20
- **Target SDK**: 34 (Android 14)
- **Min SDK**: 24 (Android 7.0)
- **Application ID**: `com.topdon.tc001`
- **Project Name**: TopInfrared
- **NDK Version**: 21.3.6528147
- **ABI Filters**: arm64-v8a, armeabi-v7a

### Module Architecture

The project follows a multi-module architecture with clear separation of
concerns:

#### Core Modules

1. **app** - Main application module with launcher activities and navigation
2. **libir** - Core infrared camera SDK wrapper and JNI integration
3. **libcom** - Common utilities and network communication
4. **libapp** - Application-level shared components
5. **libui** - UI components library (dialogs, pickers, custom views)
6. **libmenu** - Menu system and navigation components
7. **libhik** - Hikvision thermal camera integration

#### Component Modules (Feature-Based)

1. **component:thermal-ir** - Main TC001 thermal camera implementation
2. **component:thermal-lite** - TC001 Lite variant support
3. **component:thermal-hik** - Hikvision device support
4. **component:thermal04** - TS004 device support
5. **component:thermal07** - TC007 device support
6. **component:edit3d** - 3D editing capabilities
7. **component:pseudo** - Pseudo-color palette management
8. **component:house** - Building/house thermal analysis
9. **component:transfer** - Data transfer and upload
10. **component:user** - User management and authentication
11. **component:CommonComponent** - Shared component utilities

#### Local Repository Modules

1. **LocalRepo:libac020** - AC020 device support
2. **LocalRepo:libcommon** - Common library code
3. **LocalRepo:libirutils** - IR utility functions

#### Third-Party Modules

1. **RangeSeekBar** - Custom seek bar widget for temperature range selection
2. **ai-upscale** - AI-based image upscaling

### Product Flavors

The application supports multiple build variants for different markets and
device types:

1. **dev** - Development builds for testing
2. **beta** - Beta testing releases
3. **prod** - Production builds (Google Play)
4. **prodTopdon** - Android 10-specific builds with targetSdk 27
5. **insideChina** - Chinese domestic market (热视界)
6. **prodTopdonInsideChina** - China market with Android 10 compatibility

Each flavor has unique software codes and API keys for backend integration.

## Core SDK Integration

### Native Libraries (libir/libs)

The thermal camera functionality is provided through native libraries:

**AAR Dependencies:**

- `libusbdualsdk_1.3.4_2406271906_standard.aar` - Main USB dual camera SDK
- `opengl_1.3.2_standard.aar` - OpenGL rendering support
- `library_1.0.aar` - Core library functions
- `suplib-release.aar` - Supplementary library
- `ai-upscale-release.aar` - AI upscaling capabilities
- `texturegesture-release.aar` - Texture gesture handling

**Native SO Libraries (arm64-v8a / armeabi-v7a):**

- `libc++_shared.so` - C++ standard library
- `liblog.so` - Logging utilities
- `libopen3d.so` - 3D processing library
- `libopencv_java4.so` - OpenCV computer vision library
- `libSRImage.so` - Super-resolution image processing

### USB Device Integration

#### Supported Devices (ir_device_filter.xml)

The application filters for multiple USB vendor/product ID combinations:

- **Realtek (0x0BDA)**: PID 0x5840, 0x5830 - Primary TC001 devices
- **Infisense (0x3474)**: PID 17185 - Alternative vendor
- **Generic UVC**: Class 14 (video), Class 239 subclass 2 (UVC)
- **Hikvision (0x2BDF)**: PID 258 - Hikvision thermal cameras

#### Core USB Classes

1. **IRUVCTC** (`com.infisense.usbir.camera.IRUVCTC`) - Main IR UVC camera
   controller
    - Handles USB device connection/disconnection
    - Frame data callback management
    - Temperature data processing (256x192 resolution)
    - Auto-gain switching and image enhancement
    - Supports multiple data flow modes

2. **USBMonitor** - USB device monitoring and permission handling

3. **UVCCamera** - UVC protocol implementation for camera control

## Architecture Patterns

### MVVM with Kotlin

The thermal-ir component follows modern Android development patterns:

**ViewModels:**

- `IRGalleryViewModel` - Gallery image management
- `IRConfigViewModel` - Camera configuration
- `IRMonitorViewModel` - Real-time monitoring
- `IRGalleryEditViewModel` - Image editing operations
- `IRGalleryTabViewModel` - Gallery tab navigation

**Fragments:**

- `IRThermalFragment` - Main thermal camera interface
- `IRGalleryFragment` - Saved thermal images gallery
- `IRMonitorThermalFragment` - Live thermal monitoring
- `IRGalleryTabFragment` - Gallery organization
- `IRPlushFragment` - TC001 Plus variant support

**Activities:**

- `IRMainActivity` - Main entry point with ViewPager2 navigation
- `IRThermalNightActivity` - Night mode thermal imaging
- `IRThermalPlusActivity` - TC001 Plus enhanced features
- `IRGalleryDetailActivity` - Image detail viewer with editing
- `IRMonitorActivity` - Real-time monitoring dashboard

### Key Features

#### 1. Multi-Device Support

The codebase intelligently detects and handles different device types:

```kotlin
if (DeviceTools.isTC001PlusConnect()) {
    // TC001 Plus handling
} else if (DeviceTools.isTC001LiteConnect()) {
    // TC001 Lite handling
} else if (DeviceTools.isHikConnect()) {
    // Hikvision handling
}
```

#### 2. Connection Management

Device connection states are tracked through lifecycle-aware callbacks:

- `connected()` - Device successfully connected
- `disConnected()` - Device disconnected
- `onSocketConnected()` - WebSocket connection for TC007/TS004
- `onSocketDisConnected()` - WebSocket disconnection

#### 3. Temperature Data Flow

Camera resolution and data format:

- **Image Resolution**: 256x192 pixels
- **Data Length**: 256 × 192 × 2 bytes = 98,304 bytes
- **Dual Mode**: Image + Temperature data at 256x384
- **Temperature Mode**: Raw temperature values at 256x192

#### 4. Frame Processing Pipeline

1. USB device attachment detected via `USBMonitor`
2. Permission requested and granted
3. `UVCCamera` opens device connection
4. `IRCMD` sends configuration commands
5. Frame callbacks deliver:
    - Raw infrared image data
    - Temperature matrix
    - TNR (Temporal Noise Reduction) enhanced frames
6. `LibIRProcess` applies ISP processing
7. Rendered to `SurfaceView` or `GLSurfaceView`

## UI Components

### Custom Views

**Temperature Display:**

- `TemperatureBaseView` - Base temperature overlay
- `Temperature07View` - TC007-specific display
- `TemperatureEditView` - Editable temperature annotations
- `TemperatureHikView` - Hikvision temperature display

**Measurement Tools:**

- `DistanceMeasureView` - Distance measurement overlay
- `TargetBarPickView` - Target selection bar
- `EmissivityView` - Emissivity adjustment control

**Charts and Monitoring:**

- `ChartMonitorView` - Real-time temperature monitoring charts
- `ChartLogView` - Historical temperature logs
- `ChartTrendView` - Temperature trend visualization
- `TrendView` - General trend display

### Dialog System (libui)

Comprehensive dialog library for user interactions:

- `TipDialog` - Information and confirmation dialogs
- `HomeGuideDialog` - First-run guidance
- `DelPopup` - Deletion confirmation
- Custom wheel pickers for date/time/options

## Data Management

### Gallery Repository

Images are organized by type:

```kotlin
enum class DirType {
    IR,      // Thermal images
    PSEUDO,  // Pseudo-color images
    HOUSE,   // Building analysis
    VIDEO    // Video recordings
}
```

### Session Metadata

Each capture includes:

- Timestamp
- Device type and serial number
- Temperature range and palette
- Emissivity settings
- Environmental parameters
- Operator annotations

## Network Integration

### WebSocket Support (TC007/TS004)

WiFi-enabled devices use WebSocket for:

- Real-time command/control
- Live frame streaming
- Time synchronization
- Status updates

### Cloud Services

Integration with Topdon backend:

- User authentication (LMS SDK)
- Firmware updates
- Cloud storage sync
- Analytics and crash reporting (Firebase/Aliyun)

## Key Differences from BuccanCS Implementation

### 1. Architecture

**IRCamera:**

- Monolithic feature modules
- Direct USB integration via native SDK
- Heavy use of Android View system
- WebSocket for WiFi devices

**BuccanCS:**

- Clean architecture with domain/data layers
- Hardware abstraction interfaces
- Jetpack Compose throughout
- gRPC for desktop coordination

### 2. Hardware Abstraction

**IRCamera:**

- Tightly coupled to Topdon SDK AAR files
- Device-specific code branches
- Direct JNI calls

**BuccanCS:**

- `ThermalCameraConnector` interface
- `TopdonThermalHardwareClient` implementation
- Simulation fallback support
- Hardware-agnostic domain layer

### 3. Data Flow

**IRCamera:**

- In-app processing and display
- Local storage with gallery
- Optional cloud upload

**BuccanCS:**

- Agent streams to desktop orchestrator
- Session-based manifest generation
- Coordinated multi-device capture
- Background upload with WorkManager

### 4. UI Philosophy

**IRCamera:**

- Consumer-facing feature-rich UI
- Editing, filters, reporting tools
- Integrated social sharing
- AR/VR preview modes

**BuccanCS:**

- Research-focused minimal UI
- Emphasizes metadata accuracy
- Multi-agent coordination
- Desktop reconciliation

## Integration Opportunities

### What BuccanCS Can Adopt

1. **Native SDK AAR Files** - Direct access to `libusbdualsdk` and ISP libraries
2. **USB Device Filters** - Complete vendor/product ID mappings
3. **Temperature Calibration** - Emissivity and environmental correction
4. **Frame Enhancement** - TNR, auto-gain switching, super-resolution
5. **Palette Management** - Pseudo-color palettes from `component:pseudo`

### What to Avoid

1. **Monolithic Coupling** - Keep clean architecture boundaries
2. **View-Based UI** - Continue with Compose
3. **Consumer Features** - Skip social, AR, editing unless needed for research
4. **Cloud Dependencies** - Maintain local-first design

## Build Instructions

### Prerequisites

- JDK 8 or JDK 11 (some external projects require older Java)
- Android SDK Platform 34
- NDK 21.3.6528147
- Gradle wrapper included

### Building

From the IRCamera directory:

```powershell
# Debug build
.\gradlew.bat assembleDebug

# Production release
.\gradlew.bat assembleProdRelease

# Android 10 variant
.\gradlew.bat assembleProdTopdonRelease
```

APK outputs to `outputs/release/` or `outputs/debug/` with timestamped names.

### Module Dependencies

The `depend.gradle` file centralizes version management and API keys. Each
product flavor uses distinct software codes for backend validation.

## License and Distribution

- Signed with ArtiBox keystore (credentials in build.gradle)
- Multi-APK generation for different markets
- Google Play and internal distribution channels
- ProGuard disabled to preserve debugging symbols

## Technical Debt and Notes

1. **Deprecated jcenter()** - Should migrate to mavenCentral() alternatives
2. **Kotlin Extensions** deprecated - Modern code uses view binding
3. **Java 8 Target** - Could modernize to Java 11+ for newer APIs
4. **Hardcoded Secrets** - API keys and signing credentials in version control
5. **Mixed Languages** - Java and Kotlin coexist, could standardize
6. **Large AAR Files** - Binary SDK dependencies increase build size

## Conclusion

The IRCamera project represents a production-grade, feature-complete thermal
camera application with extensive device support and consumer-focused
capabilities. Its modular architecture, native SDK integration, and
comprehensive UI toolkit demonstrate Topdon's mature development approach.

For BuccanCS, the most valuable aspects are the native SDK integration patterns,
USB device management, and temperature processing algorithms. However, BuccanCS
should maintain its research-oriented architecture with clean abstractions
rather than directly adopting the consumer-focused feature set.

The key integration path forward is extracting the core SDK AAR files and JNI
interfaces while preserving BuccanCS's domain-driven design and multi-agent
coordination capabilities.
