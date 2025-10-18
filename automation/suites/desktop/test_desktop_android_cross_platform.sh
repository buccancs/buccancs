#!/usr/bin/env bash
# Cross-platform automated desktop-Android connection test
# Works on: macOS, Linux, Windows (WSL)

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
# shellcheck disable=SC1091
source "$SCRIPT_DIR/../lib/android_env.sh"

SUITE_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/../../.." && pwd)"
LOG_ROOT="$SUITE_ROOT/logs/desktop"
mkdir -p "$LOG_ROOT"
RUN_ID="$(date +%Y%m%d-%H%M%S)"
ARTIFACT_DIR="$LOG_ROOT/run-$RUN_ID"
mkdir -p "$ARTIFACT_DIR"
DESKTOP_LOG="$ARTIFACT_DIR/desktop_test_output.log"
SCREENSHOT_FILE="$ARTIFACT_DIR/test_android_screen.png"
CONNECTED_SCREENSHOT="$ARTIFACT_DIR/test_android_connected.png"

# Colours (works on all platforms with ANSI support)
if [[ -t 1 ]]; then
    RED='\033[0;31m'
    GREEN='\033[0;32m'
    YELLOW='\033[1;33m'
    BLUE='\033[0;34m'
    CYAN='\033[0;36m'
    NC='\033[0m'
else
    RED='' GREEN='' YELLOW='' BLUE='' CYAN='' NC=''
fi

# Main script
main() {
    echo -e "${BLUE}========================================${NC}"
    echo -e "${BLUE}Cross-Platform Desktop-Android Test${NC}"
    echo -e "${BLUE}========================================${NC}"
    
    # Detect OS
    OS=$(detect_os)
    OS_LABEL="$OS"
    if is_wsl; then
        OS_LABEL="${OS_LABEL} (wsl)"
    fi
    echo -e "${CYAN}Operating System: $OS_LABEL${NC}"
    
    if [[ "$OS" == "unknown" ]]; then
        echo -e "${RED}✗ Unsupported operating system${NC}"
        exit 1
    fi
    
    # Find Android SDK
    echo -e "\n${YELLOW}[1/6] Locating Android SDK...${NC}"
    ANDROID_SDK=$(find_android_sdk "$OS")
    
    if [[ -z "$ANDROID_SDK" ]]; then
        echo -e "${RED}✗ Android SDK not found${NC}"
        echo -e "${YELLOW}Please set ANDROID_HOME or ANDROID_SDK_ROOT environment variable${NC}"
        echo -e "${YELLOW}Or install Android SDK in default location:${NC}"
        case "$OS" in
            macos)   echo "  ~/Library/Android/sdk";;
            linux)
                echo "  ~/Android/Sdk"
                if is_wsl; then
                    echo "  /mnt/c/Users/<windows-user>/AppData/Local/Android/Sdk"
                fi
                ;;
            windows) echo "  %LOCALAPPDATA%\\Android\\Sdk";;
        esac
        exit 1
    fi
    
    echo -e "${GREEN}✓ Android SDK found: $ANDROID_SDK${NC}"
    
    # Set up paths
    EMULATOR=$(find_android_tool "$ANDROID_SDK" "emulator/emulator")
    ADB=$(find_android_tool "$ANDROID_SDK" "platform-tools/adb")
    
    if [[ -z "$EMULATOR" ]]; then
        echo -e "${RED}✗ Emulator not found under: $ANDROID_SDK${NC}"
        exit 1
    fi
    
    if [[ -z "$ADB" ]]; then
        echo -e "${RED}✗ ADB not found under: $ANDROID_SDK${NC}"
        exit 1
    fi
    
    USE_WINDOWS_TOOLS=false
    if [[ "$EMULATOR" == *.exe ]]; then
        USE_WINDOWS_TOOLS=true
    fi
    
    # Configuration
    AVD_NAME="${AVD_NAME:-Pixel_9a}"
    PACKAGE_NAME="com.buccancs"
    MAIN_ACTIVITY="$PACKAGE_NAME/.ui.MainActivity"
    
    echo -e "${CYAN}Emulator: $EMULATOR${NC}"
    echo -e "${CYAN}ADB: $ADB${NC}"
    echo -e "${CYAN}AVD: $AVD_NAME${NC}"
    echo -e "${CYAN}Artefacts: $ARTIFACT_DIR${NC}"
    
    # Step 2: Cleanup
    echo -e "\n${YELLOW}[2/6] Cleaning up previous sessions...${NC}"
    
    # Kill emulator (OS-specific)
    if [[ "$USE_WINDOWS_TOOLS" == true ]]; then
        TASKKILL="/mnt/c/Windows/System32/taskkill.exe"
        if [[ -x "$TASKKILL" ]]; then
            "$TASKKILL" /F /IM "$(basename "$EMULATOR")" 2>/dev/null || true
            "$TASKKILL" /F /IM "qemu-system-x86_64.exe" 2>/dev/null || true
        else
            pkill -9 -f "emulator.*$AVD_NAME" 2>/dev/null || true
            pkill -9 -f qemu 2>/dev/null || true
        fi
    else
        pkill -9 -f "emulator.*$AVD_NAME" 2>/dev/null || true
        pkill -9 -f qemu 2>/dev/null || true
    fi
    
    "$ADB" kill-server 2>/dev/null || true
    sleep 2
    echo -e "${GREEN}✓ Cleanup complete${NC}"
    
    # Step 3: Start emulator
    echo -e "\n${YELLOW}[3/6] Starting Android emulator...${NC}"
    "$EMULATOR" -avd "$AVD_NAME" -no-snapshot-load > /dev/null 2>&1 &
    EMULATOR_PID=$!
    echo "Emulator PID: $EMULATOR_PID"
    
    # Wait for device
    echo "Waiting for device..."
    "$ADB" wait-for-device
    echo "Device detected, waiting for boot completion..."
    
    # Wait for boot (cross-platform)
    MAX_BOOT_WAIT=60
    BOOT_WAIT=0
    while [[ $BOOT_WAIT -lt $MAX_BOOT_WAIT ]]; do
        BOOT_STATUS=$("$ADB" shell getprop sys.boot_completed 2>/dev/null | tr -d '\r\n' || echo "")
        if [[ "$BOOT_STATUS" == "1" ]]; then
            break
        fi
        sleep 1
        ((BOOT_WAIT++))
    done
    
    if [[ "$BOOT_STATUS" != "1" ]]; then
        echo -e "${RED}✗ Boot timeout${NC}"
        exit 1
    fi
    
    sleep 3
    echo -e "${GREEN}✓ Emulator ready${NC}"
    "$ADB" devices
    
    # Step 4: Grant permissions
    echo -e "\n${YELLOW}[4/6] Granting all app permissions...${NC}"
    
    PERMISSIONS=(
        "android.permission.BLUETOOTH_SCAN"
        "android.permission.BLUETOOTH_CONNECT"
        "android.permission.BLUETOOTH_ADVERTISE"
        "android.permission.ACCESS_FINE_LOCATION"
        "android.permission.ACCESS_COARSE_LOCATION"
        "android.permission.ACCESS_BACKGROUND_LOCATION"
        "android.permission.CAMERA"
        "android.permission.RECORD_AUDIO"
        "android.permission.READ_MEDIA_IMAGES"
        "android.permission.READ_MEDIA_VIDEO"
        "android.permission.READ_EXTERNAL_STORAGE"
        "android.permission.WRITE_EXTERNAL_STORAGE"
        "android.permission.POST_NOTIFICATIONS"
        "android.permission.READ_PHONE_STATE"
    )
    
    GRANTED_COUNT=0
    for PERMISSION in "${PERMISSIONS[@]}"; do
        echo -n "  Granting $PERMISSION... "
        if "$ADB" shell pm grant "$PACKAGE_NAME" "$PERMISSION" 2>/dev/null; then
            echo -e "${GREEN}✓${NC}"
            ((GRANTED_COUNT++))
        else
            echo -e "${YELLOW}⚠${NC}"
        fi
    done
    
    echo -e "${GREEN}✓ Granted $GRANTED_COUNT permissions${NC}"
    
    # Step 5: Start Android app
    echo -e "\n${YELLOW}[5/6] Starting Android app...${NC}"
    "$ADB" shell am start -W -n "$MAIN_ACTIVITY"
    sleep 2
    echo -e "${GREEN}✓ Android app started${NC}"
    
    # Take screenshot (cross-platform)
    if "$ADB" exec-out screencap -p > "$SCREENSHOT_FILE" 2>/dev/null; then
        echo "Screenshot saved: $SCREENSHOT_FILE"
    else
        echo -e "${YELLOW}⚠ Could not capture screenshot${NC}"
    fi
    
    # Step 6: Start desktop app
    echo -e "\n${YELLOW}[6/6] Starting desktop app...${NC}"
    
    # Determine gradlew executable
    if [[ "$OS" == "windows" ]]; then
        GRADLEW="./gradlew.bat"
    else
        GRADLEW="./gradlew"
    fi
    
    if [[ ! -f "$GRADLEW" ]]; then
        echo -e "${RED}✗ Gradle wrapper not found${NC}"
        exit 1
    fi
    
    (cd "$PROJECT_ROOT" && "$GRADLEW" :desktop:run > "$DESKTOP_LOG" 2>&1) &
    DESKTOP_PID=$!
    echo "Desktop app PID: $DESKTOP_PID"
    
    # Wait for desktop to start
    echo "Waiting for desktop app..."
    MAX_WAIT=30
    WAIT_COUNT=0
    while [[ $WAIT_COUNT -lt $MAX_WAIT ]]; do
        if grep -q "gRPC server started" "$DESKTOP_LOG" 2>/dev/null; then
            echo -e "${GREEN}✓ Desktop app started successfully${NC}"
            break
        fi
        sleep 1
        ((WAIT_COUNT++))
    done
    
    # Monitor for connection
    echo -e "\n${CYAN}Monitoring for device connection...${NC}"
    MAX_CONN_WAIT=30
    CONN_WAIT=0
    while [[ $CONN_WAIT -lt $MAX_CONN_WAIT ]]; do
        if grep -q "Registered device android-" "$DESKTOP_LOG" 2>/dev/null; then
            echo -e "\n${GREEN}✓✓✓ CONNECTION ESTABLISHED ✓✓✓${NC}\n"
            
            DEVICE_ID=$(grep "Registered device" "$DESKTOP_LOG" | tail -1 | sed 's/.*Registered device //' | cut -d' ' -f1)
            echo -e "${BLUE}Device ID: ${CYAN}$DEVICE_ID${NC}"
            
            grep -E "Registered device|registered with protocol|connected" "$DESKTOP_LOG" | tail -3
            
            # Final screenshot
            "$ADB" exec-out screencap -p > "$CONNECTED_SCREENSHOT" 2>/dev/null || true
            
            echo -e "\n${GREEN}✓ Test completed successfully!${NC}"
            echo -e "${CYAN}OS: $OS${NC}"
            echo -e "${CYAN}Android SDK: $ANDROID_SDK${NC}"
            echo -e "${CYAN}Screenshots: $SCREENSHOT_FILE, $CONNECTED_SCREENSHOT${NC}"
            echo -e "${CYAN}Logs: $DESKTOP_LOG${NC}"
            
            exit 0
        fi
        echo -n "."
        sleep 2
        ((CONN_WAIT++))
    done
    
    echo -e "\n${RED}✗ Connection timeout${NC}"
    exit 1
}

# Run main function
main "$@"
