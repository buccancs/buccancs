# Quick Reference: Building IRCamera

## Build Commands

### Build IRCamera only (debug APK)

```bash
./gradlew buildIRCamera
```

### Build all external projects (including IRCamera)

```bash
./gradlew externalBuild
```

### Build everything (main project + all external)

```bash
./gradlew build
```

## Output Location Debug APK: `external/IRCamera/app/build/outputs/apk/debug/app-debug.apk`

## Task Details - **Task Name:** `buildIRCamera` - **Group:** External build - **Runs:** `assembleDebug` in IRCamera's Gradle project - **Java:** Requires Java 21 or earlier

## Verification Check if task is available:

```bash
./gradlew tasks --group="external build"
```

## Status ✅ IRCamera successfully added to Gradle build system ✅ Task registered and ready to use ✅ Integrated with aggregate build tasks ✅ Documentation complete

## See Also - Full documentation: `docs/ircamera-gradle-integration.md` - Architecture analysis: `docs/ircamera-architecture-analysis.md`
