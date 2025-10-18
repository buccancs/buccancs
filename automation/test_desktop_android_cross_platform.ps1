# Cross-platform desktop-Android connection test (PowerShell)
# Works on: Windows, Linux (with PowerShell Core), macOS
# Requires: Android SDK, Java 21, Gradle

param(
    [string]$AvdName = "Pixel_9a",
    [int]$Timeout = 120
)

$ErrorActionPreference = "Stop"

# Colors
function Write-ColorOutput {
    param([string]$Color, [string]$Message)
    $colors = @{
        "Red" = [ConsoleColor]::Red
        "Green" = [ConsoleColor]::Green
        "Yellow" = [ConsoleColor]::Yellow
        "Blue" = [ConsoleColor]::Blue
        "Cyan" = [ConsoleColor]::Cyan
    }
    Write-Host $Message -ForegroundColor $colors[$Color]
}

function Write-Step {
    param([string]$Message)
    Write-ColorOutput "Yellow" "`n$Message"
}

function Write-Success {
    param([string]$Message)
    Write-ColorOutput "Green" "[OK] $Message"
}

function Write-Error-Custom {
    param([string]$Message)
    Write-ColorOutput "Red" "[ERROR] $Message"
}

# Main script
Write-ColorOutput "Blue" "========================================"
Write-ColorOutput "Blue" "Desktop-Android Connection Test (PowerShell)"
Write-ColorOutput "Blue" "========================================"
Write-Host ""

# Detect OS
$OS = if ($IsWindows -or $env:OS -match "Windows") { "Windows" }
      elseif ($IsLinux) { "Linux" }
      elseif ($IsMacOS) { "macOS" }
      else { "Unknown" }

Write-ColorOutput "Cyan" "Operating System: $OS"

# Configuration
$PackageName = "com.buccancs"
$MainActivity = "$PackageName/.ui.MainActivity"

# Step 1: Find Android SDK
Write-Step "[1/6] Locating Android SDK..."

$AndroidSdk = $null
if ($env:ANDROID_HOME) {
    $AndroidSdk = $env:ANDROID_HOME
    Write-Host "Found via ANDROID_HOME: $AndroidSdk"
}
elseif ($env:ANDROID_SDK_ROOT) {
    $AndroidSdk = $env:ANDROID_SDK_ROOT
    Write-Host "Found via ANDROID_SDK_ROOT: $AndroidSdk"
}
else {
    # Check default locations
    $DefaultPaths = switch ($OS) {
        "Windows" { 
            "$env:LOCALAPPDATA\Android\Sdk",
            "$env:USERPROFILE\AppData\Local\Android\Sdk"
        }
        "Linux" {
            "$env:HOME/Android/Sdk",
            "$env:HOME/.android/sdk"
        }
        "macOS" {
            "$env:HOME/Library/Android/sdk"
        }
    }
    
    foreach ($path in $DefaultPaths) {
        if (Test-Path $path) {
            $AndroidSdk = $path
            Write-Host "Found at default location: $AndroidSdk"
            break
        }
    }
}

if (-not $AndroidSdk) {
    Write-Error-Custom "Android SDK not found"
    Write-ColorOutput "Yellow" "Please set ANDROID_HOME environment variable"
    exit 1
}

# Set up paths
$EmulatorExe = if ($OS -eq "Windows") { "emulator.exe" } else { "emulator" }
$AdbExe = if ($OS -eq "Windows") { "adb.exe" } else { "adb" }

$Emulator = Join-Path $AndroidSdk "emulator" $EmulatorExe
$Adb = Join-Path $AndroidSdk "platform-tools" $AdbExe

if (-not (Test-Path $Emulator)) {
    Write-Error-Custom "Emulator not found at: $Emulator"
    exit 1
}

if (-not (Test-Path $Adb)) {
    Write-Error-Custom "ADB not found at: $Adb"
    exit 1
}

Write-Success "Android SDK ready"
Write-ColorOutput "Cyan" "Emulator: $Emulator"
Write-ColorOutput "Cyan" "ADB: $Adb"
Write-ColorOutput "Cyan" "AVD: $AvdName"

# Step 2: Cleanup
Write-Step "[2/6] Cleaning up previous sessions..."

if ($OS -eq "Windows") {
    Stop-Process -Name "emulator" -Force -ErrorAction SilentlyContinue
    Stop-Process -Name "qemu-system-x86_64" -Force -ErrorAction SilentlyContinue
}
else {
    & pkill -9 -f "emulator.*$AvdName" 2>$null
    & pkill -9 -f qemu 2>$null
}

& $Adb kill-server 2>$null
Start-Sleep -Seconds 2
Write-Success "Cleanup complete"

# Step 3: Start emulator
Write-Step "[3/6] Starting Android emulator..."

$EmulatorProcess = Start-Process -FilePath $Emulator -ArgumentList "-avd", $AvdName, "-no-snapshot-load" -PassThru -WindowStyle Hidden
Write-Host "Emulator PID: $($EmulatorProcess.Id)"

# Wait for device
Write-Host "Waiting for device..."
& $Adb wait-for-device

Write-Host "Waiting for boot completion..."
$bootWait = 0
do {
    Start-Sleep -Seconds 1
    $bootStatus = & $Adb shell getprop sys.boot_completed 2>$null
    $bootWait++
    if ($bootWait -gt 60) {
        Write-Error-Custom "Boot timeout"
        exit 1
    }
} while ($bootStatus -notmatch "1")

Start-Sleep -Seconds 3
Write-Success "Emulator ready"
& $Adb devices

# Step 4: Grant permissions
Write-Step "[4/6] Granting permissions..."

$Permissions = @(
    "android.permission.BLUETOOTH_SCAN",
    "android.permission.BLUETOOTH_CONNECT",
    "android.permission.BLUETOOTH_ADVERTISE",
    "android.permission.ACCESS_FINE_LOCATION",
    "android.permission.ACCESS_COARSE_LOCATION",
    "android.permission.ACCESS_BACKGROUND_LOCATION",
    "android.permission.CAMERA",
    "android.permission.RECORD_AUDIO",
    "android.permission.READ_MEDIA_IMAGES",
    "android.permission.READ_MEDIA_VIDEO",
    "android.permission.READ_EXTERNAL_STORAGE",
    "android.permission.WRITE_EXTERNAL_STORAGE",
    "android.permission.POST_NOTIFICATIONS",
    "android.permission.READ_PHONE_STATE"
)

$grantedCount = 0
foreach ($perm in $Permissions) {
    Write-Host "  Granting $perm... " -NoNewline
    $result = & $Adb shell pm grant $PackageName $perm 2>$null
    if ($LASTEXITCODE -eq 0) {
        Write-ColorOutput "Green" "✓"
        $grantedCount++
    }
    else {
        Write-ColorOutput "Yellow" "⚠"
    }
}

Write-Success "Granted $grantedCount permissions"

# Step 5: Start Android app
Write-Step "[5/6] Starting Android app..."
& $Adb shell am start -W -n $MainActivity | Out-Null
Start-Sleep -Seconds 2
Write-Success "Android app started"

# Screenshot
$screenshot = "test_android_screen.png"
& $Adb exec-out screencap -p > $screenshot 2>$null
if (Test-Path $screenshot) {
    Write-Host "Screenshot saved: $screenshot"
}

# Step 6: Start desktop app
Write-Step "[6/6] Starting desktop app..."

$Gradlew = if ($OS -eq "Windows") { ".\gradlew.bat" } else { "./gradlew" }

if (-not (Test-Path $Gradlew)) {
    Write-Error-Custom "Gradle wrapper not found"
    exit 1
}

$DesktopLog = "desktop_test_output.log"
$DesktopProcess = Start-Process -FilePath $Gradlew -ArgumentList ":desktop:run" -RedirectStandardOutput $DesktopLog -RedirectStandardError $DesktopLog -PassThru -WindowStyle Hidden
Write-Host "Desktop app PID: $($DesktopProcess.Id)"

# Wait for desktop
Write-Host "Waiting for desktop app..."
$desktopWait = 0
do {
    Start-Sleep -Seconds 1
    $desktopWait++
    if ($desktopWait -gt 30) {
        Write-Error-Custom "Desktop startup timeout"
        exit 1
    }
} while (-not (Select-String -Path $DesktopLog -Pattern "gRPC server started" -Quiet))

Write-Success "Desktop app ready"

# Monitor connection
Write-Host ""
Write-ColorOutput "Cyan" "Monitoring for connection..."
$connWait = 0
do {
    Write-Host "." -NoNewline
    Start-Sleep -Seconds 2
    $connWait++
    if ($connWait -gt 30) {
        Write-Host ""
        Write-Error-Custom "Connection timeout"
        exit 1
    }
} while (-not (Select-String -Path $DesktopLog -Pattern "Registered device android-" -Quiet))

Write-Host ""
Write-Host ""
Write-ColorOutput "Green" "=========================================="
Write-ColorOutput "Green" "CONNECTION ESTABLISHED"
Write-ColorOutput "Green" "=========================================="
Write-Host ""

$deviceLine = Select-String -Path $DesktopLog -Pattern "Registered device" | Select-Object -Last 1
Write-Host $deviceLine.Line

# Final screenshot
& $Adb exec-out screencap -p > "test_android_connected.png" 2>$null

Write-Host ""
Write-Success "Test completed successfully!"
Write-ColorOutput "Cyan" "OS: $OS"
Write-ColorOutput "Cyan" "Android SDK: $AndroidSdk"
Write-ColorOutput "Cyan" "Screenshots: test_android_screen.png, test_android_connected.png"
Write-ColorOutput "Cyan" "Logs: $DesktopLog"
Write-Host ""

exit 0
