# IRCamera Gradle Integration

## Overview

IRCamera (TopInfrared) has been added to the Gradle build system as an external
build task.

> Source control reminder: `external/IRCamera` is tracked as a Git submodule.
> Run `git submodule update --init --recursive` after cloning or pulling to keep
> it in sync. Windows developers should also ensure `git config core.longpaths
> true` is enabled so the repository can materialise nested module paths.

## Changes Made

### build.gradle.kts Added IRCamera to the `externalBuilds` list with the following configuration:

```kotlin
ExternalProjectBuild(
    "buildIRCamera",
    "external/IRCamera",
    tasksToRun = listOf("assembleDebug"),
    maxSupportedJavaMajor = 21
)
```

### Configuration Details

Task Name:\*\* `buildIRCamera`

Location:\*\* `external/IRCamera`

Build Task:\*\* `assembleDebug` - Builds the debug variant of the IRCamera
Android application

Java Compatibility:\*\* Maximum Java 21 (compatible with the IRCamera project's
toolchain requirements)

Integration Type:\*\* External build task (uses IRCamera's own Gradle wrapper)

## Usage

### Build IRCamera Only

```bash
./gradlew buildIRCamera
```

### Build All External Projects

```bash
./gradlew externalBuild
```

### Build Everything (Internal + External)

```bash
./gradlew build
```

## How It Works

5. The system automatically finds a compatible Java runtime ## IRCamera Project
   Structure

IRCamera (TopInfrared) contains multiple modules: - **app** - Main application
module - **BleModule** - Bluetooth Low Energy module - **commonlibrary** -
Common utilities - **component/** - Various thermal imaging components -
CommonComponent - edit3d, house, pseudo - thermal-hik, thermal-ir,
thermal-lite - thermal04, thermal07 - transfer, user - **libapp, libcom, libhik,
libir** - Core libraries - **libmenu, libui** - UI libraries - **LocalRepo/** -
Local dependencies (libac020, libcommon, libirutils) - **RangeSeekBar** - Custom
UI component - **ai-upscale** - AI upscaling functionality

## Build Outputs

When the build succeeds, the APK will be located at:

```
external/IRCamera/app/build/outputs/apk/debug/app-debug.apk
```

## Integration Benefits

5. **Isolated Build** - Uses IRCamera's own dependencies and configuration ##
   Troubleshooting

### Java Version Issues If you see Java version errors, ensure you have Java 21 or earlier installed: - The build system will automatically search for compatible Java installations - Set `externalJavaHome` in `gradle.properties` to override - Or set `EXTERNAL_JAVA_HOME` environment variable

### Build Failures - IRCamera uses older Gradle and Android Gradle Plugin versions - It has its own dependency repositories (including some Chinese mirrors) - Build failures in IRCamera don't affect the main project build

### Wrapper Not Found If the task is skipped with "wrapper was not found": - Verify `external/IRCamera/gradlew` (or `gradlew.bat` on Windows) exists - Ensure the file has execute permissions on Unix-like systems

## Future Enhancements

Potential improvements to the integration: 1. Add release build tasks
(`assembleRelease`) 2. Create tasks for specific product flavors (Google vs
Topdon) 3. Add dependency relationships between main app and IRCamera modules 4.
Extract IRCamera libraries as composite builds for direct integration 5.
Automate APK signing configuration

## Related Documentation - `IRCAMERA_ARCHITECTURE_ANALYSIS.md` - Detailed IRCamera architecture analysis - `external/IRCamera/README.md` - IRCamera project documentation (if available) - Main project build documentation

## Notes - IRCamera maintains its own version of dependencies - It uses older Android Gradle Plugin (7.1.3) and Kotlin (1.7.20) - The project uses Chinese Maven mirrors (Aliyun) for faster builds in China - Build is isolated - failures don't affect the main Buccancs project
