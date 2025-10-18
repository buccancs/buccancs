@echo off
REM Cross-platform desktop-Android connection test for Windows
REM Requires: Android SDK, Java 21, Gradle

setlocal enabledelayedexpansion

set "SCRIPT_DIR=%~dp0"
for %%I in ("%SCRIPT_DIR%..") do set "SUITE_DIR=%%~fI"
for %%I in ("%SUITE_DIR%\..") do set "AUTOMATION_DIR=%%~fI"
for %%I in ("%AUTOMATION_DIR%\..") do set "REPO_DIR=%%~fI"
set "LOG_ROOT=%SUITE_DIR%\logs\desktop"
if not exist "%LOG_ROOT%" mkdir "%LOG_ROOT%" >nul 2>&1
for /f %%I in ('powershell -NoProfile -Command "Get-Date -Format yyyyMMdd-HHmmss"') do set "RUN_ID=%%I"
set "ARTIFACT_DIR=%LOG_ROOT%\run-%RUN_ID%"
if not exist "%ARTIFACT_DIR%" mkdir "%ARTIFACT_DIR%" >nul 2>&1
set "DESKTOP_LOG=%ARTIFACT_DIR%\desktop_test_output.log"
set "SCREENSHOT=%ARTIFACT_DIR%\test_android_screen.png"
set "CONNECTED_SCREENSHOT=%ARTIFACT_DIR%\test_android_connected.png"

echo ========================================
echo Desktop-Android Connection Test (Windows)
echo ========================================
echo.

REM Configuration
set AVD_NAME=Pixel_9a
set PACKAGE_NAME=com.buccancs
set MAIN_ACTIVITY=%PACKAGE_NAME%/.ui.MainActivity

REM Step 1: Find Android SDK
echo [1/6] Locating Android SDK...

if defined ANDROID_HOME (
    set ANDROID_SDK=%ANDROID_HOME%
    echo Found via ANDROID_HOME: %ANDROID_SDK%
) else if defined ANDROID_SDK_ROOT (
    set ANDROID_SDK=%ANDROID_SDK_ROOT%
    echo Found via ANDROID_SDK_ROOT: %ANDROID_SDK%
) else if exist "%LOCALAPPDATA%\Android\Sdk" (
    set ANDROID_SDK=%LOCALAPPDATA%\Android\Sdk
    echo Found at default location: %ANDROID_SDK%
) else (
    echo ERROR: Android SDK not found
    echo Please set ANDROID_HOME environment variable
    echo Or install Android SDK at: %%LOCALAPPDATA%%\Android\Sdk
    exit /b 1
)

set EMULATOR=%ANDROID_SDK%\emulator\emulator.exe
set ADB=%ANDROID_SDK%\platform-tools\adb.exe

if not exist "%EMULATOR%" (
    echo ERROR: Emulator not found at %EMULATOR%
    exit /b 1
)

if not exist "%ADB%" (
    echo ERROR: ADB not found at %ADB%
    exit /b 1
)

echo [OK] Android SDK ready
echo Artefacts: %ARTIFACT_DIR%
echo.

REM Step 2: Cleanup
echo [2/6] Cleaning up previous sessions...
taskkill /F /IM emulator.exe 2>nul
taskkill /F /IM qemu-system-x86_64.exe 2>nul
"%ADB%" kill-server 2>nul
timeout /t 2 /nobreak >nul
echo [OK] Cleanup complete
echo.

REM Step 3: Start emulator
echo [3/6] Starting Android emulator...
start /B "" "%EMULATOR%" -avd %AVD_NAME% -no-snapshot-load
echo Emulator started in background

REM Wait for device
echo Waiting for device...
"%ADB%" wait-for-device

echo Waiting for boot completion...
:wait_boot
"%ADB%" shell getprop sys.boot_completed 2>nul | find "1" >nul
if errorlevel 1 (
    timeout /t 1 /nobreak >nul
    goto wait_boot
)

timeout /t 3 /nobreak >nul
echo [OK] Emulator ready
"%ADB%" devices
echo.

REM Step 4: Grant permissions
echo [4/6] Granting permissions...

set PERMISSIONS=android.permission.BLUETOOTH_SCAN android.permission.BLUETOOTH_CONNECT android.permission.BLUETOOTH_ADVERTISE android.permission.ACCESS_FINE_LOCATION android.permission.ACCESS_COARSE_LOCATION android.permission.ACCESS_BACKGROUND_LOCATION android.permission.CAMERA android.permission.RECORD_AUDIO android.permission.READ_MEDIA_IMAGES android.permission.READ_MEDIA_VIDEO android.permission.READ_EXTERNAL_STORAGE android.permission.WRITE_EXTERNAL_STORAGE android.permission.POST_NOTIFICATIONS android.permission.READ_PHONE_STATE

set GRANTED=0
for %%P in (%PERMISSIONS%) do (
    echo   Granting %%P...
    "%ADB%" shell pm grant %PACKAGE_NAME% %%P 2>nul
    if not errorlevel 1 set /a GRANTED+=1
)

echo [OK] Granted %GRANTED% permissions
echo.

REM Step 5: Start Android app
echo [5/6] Starting Android app...
"%ADB%" shell am start -W -n %MAIN_ACTIVITY%
timeout /t 2 /nobreak >nul
echo [OK] Android app started

REM Screenshot
"%ADB%" exec-out screencap -p > "%SCREENSHOT%" 2>nul
if exist "%SCREENSHOT%" (
    echo Screenshot saved: %SCREENSHOT%
)
echo.

REM Step 6: Start desktop app
echo [6/6] Starting desktop app...

if exist "%REPO_DIR%\gradlew.bat" (
    start "" /B cmd /c "cd /d \"%REPO_DIR%\" && gradlew.bat :desktop:run > \"%DESKTOP_LOG%\" 2>&1"
    echo Desktop app started in background
) else (
    echo ERROR: gradlew.bat not found in %REPO_DIR%
    exit /b 1
)

echo Waiting for desktop to start...
set WAIT=0
:wait_desktop
if %WAIT% GEQ 30 (
    echo ERROR: Desktop startup timeout
    exit /b 1
)
findstr /C:"gRPC server started" "%DESKTOP_LOG%" >nul 2>&1
if errorlevel 1 (
    timeout /t 1 /nobreak >nul
    set /a WAIT+=1
    goto wait_desktop
)
echo [OK] Desktop ready
echo.

REM Monitor connection
echo Monitoring for connection...
set CONN_WAIT=0
:wait_connection
if %CONN_WAIT% GEQ 30 (
    echo ERROR: Connection timeout
    exit /b 1
)
findstr /C:"Registered device android-" "%DESKTOP_LOG%" >nul 2>&1
if errorlevel 1 (
    echo|set /p="."
    timeout /t 2 /nobreak >nul
    set /a CONN_WAIT+=1
    goto wait_connection
)

echo.
echo.
echo ========================================
echo CONNECTION ESTABLISHED
echo ========================================
echo.

for /f "tokens=*" %%A in ('findstr /C:"Registered device" "%DESKTOP_LOG%"') do set LAST_REG=%%A
echo %LAST_REG%

"%ADB%" exec-out screencap -p > "%CONNECTED_SCREENSHOT%" 2>nul

echo.
echo [OK] Test completed successfully!
echo.
echo Screenshots: %SCREENSHOT%, %CONNECTED_SCREENSHOT%
echo Logs: %DESKTOP_LOG%
echo.

endlocal
exit /b 0
