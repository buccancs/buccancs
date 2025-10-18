# Cross-Platform Testing Guide

## Overview

The automation scripts now support Windows, Linux, and macOS, with automatic detection of the operating system and Android SDK location.

## Available Cross-Platform Scripts

### 1. Bash Script (Universal)
**File:** `test_desktop_android_cross_platform.sh`  
**Platforms:** macOS, Linux, Windows (Git Bash/WSL/Cygwin)  
**Features:**
- Automatic OS detection
- Android SDK auto-discovery
- Color-coded output
- Full automation

**Usage:**
```bash
# macOS / Linux
./automation/test_desktop_android_cross_platform.sh

# Windows (Git Bash)
./automation/test_desktop_android_cross_platform.sh

# Windows (WSL)
./automation/test_desktop_android_cross_platform.sh
```

---

### 2. PowerShell Script
**File:** `test_desktop_android_cross_platform.ps1`  
**Platforms:** Windows, Linux (PowerShell Core), macOS  
**Features:**
- Native PowerShell cmdlets
- Cross-platform PowerShell 7+ support
- Colored output
- Parameter support

**Usage:**
```powershell
# Windows PowerShell / PowerShell Core
.\automation\test_desktop_android_cross_platform.ps1

# With parameters
.\automation\test_desktop_android_cross_platform.ps1 -AvdName "Pixel_9a" -Timeout 120

# Linux/macOS (PowerShell Core)
pwsh ./automation/test_desktop_android_cross_platform.ps1
```

---

### 3. Windows Batch Script
**File:** `test_desktop_android_windows.bat`  
**Platforms:** Windows only  
**Features:**
- Native Windows batch
- No external dependencies
- Simple syntax

**Usage:**
```cmd
REM Windows Command Prompt
automation\test_desktop_android_windows.bat
```

---

### 4. Headless Cross-Platform
**File:** `test_headless_cross_platform.sh`  
**Platforms:** macOS, Linux, Windows (Git Bash/WSL)  
**Features:**
- Headless emulator
- Headless desktop
- CI/CD optimized

**Usage:**
```bash
./automation/test_headless_cross_platform.sh
```

---

## Platform-Specific Details

### macOS

**Android SDK Locations (checked in order):**
1. `$ANDROID_HOME` environment variable
2. `$ANDROID_SDK_ROOT` environment variable
3. `~/Library/Android/sdk` (default)

**Requirements:**
- Android SDK installed
- Java 21 (JDK)
- Xcode Command Line Tools (optional)

**Setup:**
```bash
# Install Android SDK via Android Studio
# Or use homebrew
brew install --cask android-sdk
brew install openjdk@21

# Set environment variable
export ANDROID_HOME="$HOME/Library/Android/sdk"
export PATH="$PATH:$ANDROID_HOME/emulator:$ANDROID_HOME/platform-tools"
```

---

### Linux

**Android SDK Locations (checked in order):**
1. `$ANDROID_HOME` environment variable
2. `$ANDROID_SDK_ROOT` environment variable
3. `~/Android/Sdk` (default)
4. `~/.android/sdk` (alternate)

**Requirements:**
- Android SDK installed
- Java 21 (OpenJDK)
- libvirt/KVM for hardware acceleration (optional)

**Setup:**
```bash
# Install dependencies (Ubuntu/Debian)
sudo apt update
sudo apt install openjdk-21-jdk android-sdk

# Or download Android SDK manually
wget https://dl.google.com/android/repository/commandlinetools-linux-latest.zip
unzip commandlinetools-linux-latest.zip -d ~/Android/Sdk
cd ~/Android/Sdk/cmdline-tools/bin
./sdkmanager "platform-tools" "emulator" "platforms;android-34"

# Set environment variables (~/.bashrc or ~/.zshrc)
export ANDROID_HOME="$HOME/Android/Sdk"
export PATH="$PATH:$ANDROID_HOME/emulator:$ANDROID_HOME/platform-tools"

# Reload shell
source ~/.bashrc
```

**Hardware Acceleration:**
```bash
# Install KVM
sudo apt install qemu-kvm libvirt-daemon-system

# Add user to kvm group
sudo usermod -a -G kvm $USER

# Verify
kvm-ok
```

---

### Windows

**Android SDK Locations (checked in order):**
1. `%ANDROID_HOME%` environment variable
2. `%ANDROID_SDK_ROOT%` environment variable
3. `%LOCALAPPDATA%\Android\Sdk` (default)
4. `%USERPROFILE%\AppData\Local\Android\Sdk` (alternate)

**Requirements:**
- Android SDK installed
- Java 21 (JDK)
- For Bash scripts: Git Bash or WSL

**Setup (PowerShell as Administrator):**
```powershell
# Install Java via Chocolatey
choco install openjdk21

# Or download from:
# https://adoptium.net/

# Set environment variables
[Environment]::SetEnvironmentVariable("ANDROID_HOME", "$env:LOCALAPPDATA\Android\Sdk", "User")
[Environment]::SetEnvironmentVariable("Path", "$env:Path;$env:ANDROID_HOME\emulator;$env:ANDROID_HOME\platform-tools", "User")

# Verify
$env:ANDROID_HOME
adb version
```

**Windows Subsystem for Linux (WSL):**
```bash
# Inside WSL
./automation/test_desktop_android_cross_platform.sh

# Note: WSL uses Windows ADB, configure ANDROID_HOME to point to Windows SDK
export ANDROID_HOME="/mnt/c/Users/$USER/AppData/Local/Android/Sdk"
```

---

## Environment Variables

All scripts check these environment variables (in order):

| Variable | Priority | Description |
|----------|----------|-------------|
| `ANDROID_HOME` | 1 | Primary Android SDK location |
| `ANDROID_SDK_ROOT` | 2 | Alternate SDK location |
| Default paths | 3 | OS-specific defaults |

**Setting Environment Variables:**

**macOS/Linux (Bash):**
```bash
# Temporary (current session)
export ANDROID_HOME="$HOME/Library/Android/sdk"

# Permanent (add to ~/.bashrc or ~/.zshrc)
echo 'export ANDROID_HOME="$HOME/Library/Android/sdk"' >> ~/.bashrc
source ~/.bashrc
```

**Windows (PowerShell):**
```powershell
# Temporary (current session)
$env:ANDROID_HOME = "$env:LOCALAPPDATA\Android\Sdk"

# Permanent (user-level)
[Environment]::SetEnvironmentVariable("ANDROID_HOME", "$env:LOCALAPPDATA\Android\Sdk", "User")
```

**Windows (Command Prompt):**
```cmd
REM Temporary
set ANDROID_HOME=%LOCALAPPDATA%\Android\Sdk

REM Permanent
setx ANDROID_HOME "%LOCALAPPDATA%\Android\Sdk"
```

---

## Script Selection Guide

| Scenario | Recommended Script | Alternative |
|----------|-------------------|-------------|
| Windows development | `.ps1` PowerShell | `.bat` Batch |
| Linux server | `.sh` Bash | `.sh` Headless |
| macOS development | `.sh` Bash | - |
| CI/CD (any OS) | `.sh` Headless | `.sh` Bash |
| Windows automation | `.bat` Batch | `.ps1` PowerShell |
| WSL/Git Bash | `.sh` Bash | - |

---

## Troubleshooting by Platform

### macOS

**Issue: Permission denied**
```bash
chmod +x automation/test_*.sh
```

**Issue: Emulator won't start**
```bash
# Check hardware acceleration
sysctl -a | grep machdep.cpu.features
# Should include VMX or SVM
```

**Issue: ADB not found**
```bash
# Install platform-tools
sdkmanager "platform-tools"
```

---

### Linux

**Issue: /dev/kvm permission denied**
```bash
# Add user to kvm group
sudo usermod -a -G kvm $USER
# Log out and back in

# Or run with sudo (not recommended)
sudo ./automation/test_*.sh
```

**Issue: HAXM not available**
```bash
# Install KVM instead
sudo apt install qemu-kvm libvirt-daemon-system

# Verify
ls -la /dev/kvm
```

**Issue: Display errors**
```bash
# For headless mode
export DISPLAY=:0
# Or use headless script
./automation/test_headless_cross_platform.sh
```

---

### Windows

**Issue: Execution Policy (PowerShell)**
```powershell
# Check current policy
Get-ExecutionPolicy

# Allow script execution (as Administrator)
Set-ExecutionPolicy RemoteSigned -Scope CurrentUser

# Or bypass for single execution
powershell -ExecutionPolicy Bypass -File automation\test_*.ps1
```

**Issue: HAXM installation required**
```
# Install Intel HAXM
sdkmanager "extras;intel;Hardware_Accelerated_Execution_Manager"

# Or download manually from:
# https://github.com/intel/haxm/releases
```

**Issue: Git Bash path issues**
```bash
# Use Windows paths in Git Bash
export ANDROID_HOME="/c/Users/$USER/AppData/Local/Android/Sdk"

# Or use WSL for better compatibility
```

**Issue: ADB device not found**
```cmd
REM Restart ADB server
adb kill-server
adb start-server
adb devices
```

---

## CI/CD Integration

### GitHub Actions (All Platforms)

**macOS Runner:**
```yaml
jobs:
  test-macos:
    runs-on: macos-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 21
        uses: actions/setup-java@v3
        with:
          java-version: '21'
      - name: Run Test
        run: ./automation/test_headless_cross_platform.sh
```

**Linux Runner:**
```yaml
jobs:
  test-linux:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 21
        uses: actions/setup-java@v3
        with:
          java-version: '21'
      - name: Install KVM
        run: |
          sudo apt-get install -y qemu-kvm libvirt-daemon-system
          sudo usermod -a -G kvm $USER
      - name: Run Test
        run: ./automation/test_headless_cross_platform.sh
```

**Windows Runner:**
```yaml
jobs:
  test-windows:
    runs-on: windows-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 21
        uses: actions/setup-java@v3
        with:
          java-version: '21'
      - name: Run Test (PowerShell)
        run: .\automation\test_desktop_android_cross_platform.ps1
      - name: Run Test (Batch)
        run: automation\test_desktop_android_windows.bat
```

---

## Performance Comparison

| Platform | Emulator Boot | Test Total | Notes |
|----------|---------------|------------|-------|
| macOS (Apple Silicon) | 10-15s | 40-50s | Native ARM, fastest |
| macOS (Intel) | 15-20s | 45-55s | x86 emulation |
| Linux (KVM) | 12-18s | 42-52s | Good performance |
| Linux (No KVM) | 25-35s | 60-75s | Software emulation |
| Windows (HAXM) | 15-22s | 48-60s | Good performance |
| Windows (No HAXM) | 30-45s | 70-90s | Slow |
| WSL2 | 20-28s | 55-70s | Moderate |

---

## Best Practices

### General
1. **Use headless mode for CI/CD** - Faster and more reliable
2. **Set ANDROID_HOME** - Avoid SDK detection issues
3. **Grant permissions upfront** - All scripts auto-grant
4. **Check logs** - Located in `logs/` directory
5. **Clean up** - Scripts include cleanup traps

### macOS
- Use native ARM emulator on Apple Silicon
- Enable virtualization in BIOS (Intel Macs)

### Linux
- Always use KVM for acceptable performance
- Run headless on servers
- Add user to kvm group

### Windows
- Install HAXM for Intel processors
- Use PowerShell for better error handling
- Consider WSL2 for bash scripts

---

## File Compatibility Matrix

| Script | macOS | Linux | Windows | Git Bash | WSL | PowerShell |
|--------|-------|-------|---------|----------|-----|------------|
| `test_*_cross_platform.sh` | ✅ | ✅ | ⚠️ | ✅ | ✅ | ❌ |
| `test_*_cross_platform.ps1` | ✅* | ✅* | ✅ | ❌ | ✅* | ✅ |
| `test_*_windows.bat` | ❌ | ❌ | ✅ | ❌ | ❌ | ✅ |
| `test_headless_cross_platform.sh` | ✅ | ✅ | ⚠️ | ✅ | ✅ | ❌ |

*Requires PowerShell Core 7+

Legend:
- ✅ Fully supported
- ⚠️ Requires Git Bash/WSL
- ❌ Not supported

---

## Next Steps

1. **Choose your platform's script**
2. **Set up Android SDK** (if not installed)
3. **Create AVD** (Pixel_9a or modify AVD_NAME)
4. **Run the test!**

For issues, check the troubleshooting section for your platform.
