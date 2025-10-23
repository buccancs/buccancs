# Bootstrap Toolchains Scripts

This directory contains automated scripts to download and configure all required toolchains (Android SDK, JDK, Gradle) for building the project.

## Available Scripts

### Windows: `bootstrap-toolchains.ps1`

Automated toolchain setup for Windows PowerShell.

#### Usage

**Option 1: Use default settings (recommended)**
```powershell
.\scripts\bootstrap-toolchains.ps1
```

**Option 2: Customize versions**
```powershell
.\scripts\bootstrap-toolchains.ps1 `
    -AndroidCommandLineToolsVersion "11076708" `
    -TemurinJdkVersion "21.0.4_7" `
    -AndroidPackages @("platform-tools", "build-tools;36.0.0", "platforms;android-36") `
    -ForceRedownload
```

#### Parameters
- `-RepoRoot` - Repository root path (auto-detected if not specified)
- `-AndroidCommandLineToolsVersion` - Android command-line tools version (default: `11076708`)
- `-AndroidPackages` - Array of Android SDK packages to install
- `-TemurinJdkVersion` - Temurin JDK version (default: `21.0.4_7`)
- `-ForceRedownload` - Force re-download and re-extract all archives

---

### Linux/WSL: `bootstrap-toolchains.sh`

Automated toolchain setup for Linux and Windows Subsystem for Linux (WSL).

#### Usage

**Option 1: Use default settings (recommended)**
```bash
bash ./scripts/bootstrap-toolchains.sh
```

**Option 2: Customize via environment variables**
```bash
ANDROID_CMDLINE_TOOLS_VERSION=11076708 \
TEMURIN_JDK_VERSION=21.0.4_7 \
ANDROID_PACKAGES="platform-tools build-tools;36.0.0 platforms;android-36" \
FORCE_REDOWNLOAD=true \
bash ./scripts/bootstrap-toolchains.sh
```

#### Environment Variables
- `REPO_ROOT` - Repository root path (auto-detected if not set)
- `ANDROID_CMDLINE_TOOLS_VERSION` - Android command-line tools version (default: `11076708`)
- `ANDROID_PACKAGES` - Space-separated list of Android SDK packages (default: `platform-tools build-tools;36.0.0 platforms;android-36`)
- `TEMURIN_JDK_VERSION` - Temurin JDK version (default: `21.0.4_7`)
- `FORCE_REDOWNLOAD` - Set to `true` to force re-download and re-extract (default: `false`)

---

## What These Scripts Do

Both scripts perform the following operations:

1. **Create toolchain directories** in `toolchains/` (git-ignored):
   ```
   toolchains/
   ├── android-sdk/         # Android SDK + command-line tools
   ├── android-ndk/         # Android NDK (optional, directory created but empty)
   ├── java/                # Temurin JDK 21
   ├── gradle-user-home/    # Gradle cache and wrapper downloads
   └── downloads/           # Downloaded archives (cached for re-runs)
   ```

2. **Download Android command-line tools**:
   - Windows: `commandlinetools-win-{version}_latest.zip`
   - Linux: `commandlinetools-linux-{version}_latest.zip`

3. **Download Temurin JDK 21**:
   - Windows: `OpenJDK21U-jdk_x64_windows_hotspot_{version}.zip`
   - Linux: `OpenJDK21U-jdk_x64_linux_hotspot_{version}.tar.gz`

4. **Extract and normalize directory structure**:
   - Ensures `cmdline-tools/latest/bin/sdkmanager` is in the correct location
   - Extracts JDK to `toolchains/java/`

5. **Install Android SDK packages**:
   - Uses `sdkmanager` to install platform-tools, build-tools, and platform versions
   - Automatically accepts all licenses

6. **Configure Gradle and environment**:
   - Updates `gradle/os-paths.properties` with toolchain locations
   - (Windows) Sets user-level environment variables
   - (Linux) Displays export commands for manual configuration

---

## Setup Scripts (Configuration Only)

### Windows: `setup-windows.ps1`

Configures Gradle properties and environment variables without downloading toolchains.

```powershell
.\scripts\setup-windows.ps1 `
    -AndroidSdk "C:\path\to\android-sdk" `
    -JavaHome "C:\path\to\jdk" `
    -GradleUserHome "C:\path\to\.gradle" `
    -AndroidNdk "C:\path\to\android-ndk"
```

### Linux/WSL: `setup-wsl.sh`

Configures Gradle properties for Linux/WSL environments.

```bash
ANDROID_SDK_ROOT=/path/to/android-sdk \
JAVA_HOME=/path/to/jdk \
GRADLE_USER_HOME=/path/to/.gradle \
ANDROID_NDK_ROOT=/path/to/android-ndk \
bash ./scripts/setup-wsl.sh
```

---

## After Running Bootstrap

### Windows

1. **Restart PowerShell** to pick up new environment variables
2. **Verify installation**:
   ```powershell
   & "$env:USERPROFILE\dev\buccancs\toolchains\java\bin\java.exe" -version
   & "$env:USERPROFILE\dev\buccancs\toolchains\android-sdk\cmdline-tools\latest\bin\sdkmanager.bat" --list
   ```

3. **Build the project**:
   ```powershell
   .\gradlew build
   ```

### Linux/WSL

1. **Add exports to your shell profile** (copy from script output):
   ```bash
   # Add to ~/.bashrc or ~/.zshrc
   export ANDROID_SDK_ROOT=/path/to/buccancs/toolchains/android-sdk
   export JAVA_HOME=/path/to/buccancs/toolchains/java
   export GRADLE_USER_HOME=/path/to/buccancs/toolchains/gradle-user-home
   ```

2. **Reload your shell**:
   ```bash
   source ~/.bashrc  # or source ~/.zshrc
   ```

3. **Verify installation**:
   ```bash
   $JAVA_HOME/bin/java -version
   $ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --list
   ```

4. **Build the project**:
   ```bash
   ./gradlew build
   ```

---

## Troubleshooting

### Downloads Fail
- Ensure you have internet connectivity
- Check if GitHub and Google servers are accessible
- Try manually downloading and placing archives in `toolchains/downloads/`

### Missing Prerequisites (Linux/WSL)
- **The Linux script automatically checks** for required tools before starting
- If tools are missing, you'll see a helpful error message with installation commands
- Required tools: `curl` or `wget`, `unzip`, `tar`
- Install missing tools:
  ```bash
  # Ubuntu/Debian
  sudo apt-get install curl unzip tar
  
  # RHEL/CentOS
  sudo yum install curl unzip tar
  
  # macOS
  brew install curl unzip
  ```

### Extraction Fails
- Windows: Ensure PowerShell 5.1+ or PowerShell Core (usually pre-installed on Windows 10/11)
- Linux: If extraction fails despite having tools installed, check file permissions and disk space

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
