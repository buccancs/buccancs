# Bootstrap Scripts

Full guidance for provisioning toolchains has moved to
`docs/development/environment.md`. This file remains as a breadcrumb so you know
where to look.

Quick commands:

- Windows PowerShell – `pwsh -File scripts/bootstrap-toolchains.ps1`
- Linux / WSL – `bash ./scripts/bootstrap-toolchains.sh`

Both scripts share the same options and behaviour described in the consolidated
guide.

### sdkmanager Not Found
- The scripts automatically normalize the directory layout
- If extraction was incomplete, run with `ForceRedownload`:
  - Windows: `-ForceRedownload`
  - Linux: `FORCE_REDOWNLOAD=true`

### License Acceptance Fails
- The scripts automatically accept licenses
- If manual acceptance is needed, run:
  ```bash
  # Windows
  & "$env:USERPROFILE\dev\buccancs\toolchains\android-sdk\cmdline-tools\latest\bin\sdkmanager.bat" --licenses
  
  # Linux
  $ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --licenses
  ```

---

## Version Information

### Current Defaults
- **Android Command-line Tools**: 11076708
- **Temurin JDK**: 21.0.4_7 (JDK 21)
- **Android Build Tools**: 36.0.0
- **Android Platform**: android-36 (API 36)

### Updating Versions
To use different versions, pass custom parameters (Windows) or set environment variables (Linux) as shown in the usage examples above.

---

## File Structure After Bootstrap

```
buccancs/
├── toolchains/              # Git-ignored
│   ├── android-sdk/
│   │   ├── cmdline-tools/
│   │   │   └── latest/
│   │   │       └── bin/
│   │   │           ├── sdkmanager      # Linux
│   │   │           └── sdkmanager.bat  # Windows
│   │   ├── platform-tools/
│   │   ├── build-tools/
│   │   │   └── 36.0.0/
│   │   └── platforms/
│   │       └── android-36/
│   ├── java/
│   │   └── bin/
│   │       ├── java        # Linux
│   │       └── java.exe    # Windows
│   ├── gradle-user-home/
│   │   └── caches/
│   └── downloads/          # Cached archives
│       ├── commandlinetools-*.zip
│       └── OpenJDK21U-jdk_*.{zip,tar.gz}
└── gradle/
    └── os-paths.properties  # Auto-generated toolchain configuration
```
