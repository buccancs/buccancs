#!/usr/bin/env bash
# Cross-platform automated desktop-Android connection test
# Works on: macOS, Linux, Windows (Git Bash/WSL)

set -e

# Colors (works on all platforms with ANSI support)
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

# Detect OS
detect_os() {
    case "$(uname -s)" in
        Darwin*)    OS="macos";;
        Linux*)     OS="linux";;
        CYGWIN*|MINGW*|MSYS*)   OS="windows";;
        *)          OS="unknown";;
    esac
    echo "$OS"
}

# Get Android SDK path based on OS
get_android_sdk() {
    local os="$1"
    
    # Check common environment variables
    if [[ -n "$ANDROID_HOME" ]]; then
        echo "$ANDROID_HOME"
        return 0
    elif [[ -n "$ANDROID_SDK_ROOT" ]]; then
        echo "$ANDROID_SDK_ROOT"
        return 0
    fi
    
    # Check default locations
    case "$os" in
        macos)
            if [[ -d "$HOME/Library/Android/sdk" ]]; then
                echo "$HOME/Library/Android/sdk"
                return 0
            fi
            ;;
        linux)
            if [[ -d "$HOME/Android/Sdk" ]]; then
                echo "$HOME/Android/Sdk"
                return 0
            elif [[ -d "$HOME/.android/sdk" ]]; then
                echo "$HOME/.android/sdk"
                return 0
            fi
            ;;
        windows)
            # Windows paths (Git Bash)
            if [[ -d "$LOCALAPPDATA/Android/Sdk" ]]; then
                echo "$LOCALAPPDATA/Android/Sdk"
                return 0
            elif [[ -d "$HOME/AppData/Local/Android/Sdk" ]]; then
                echo "$HOME/AppData/Local/Android/Sdk"
                return 0
            elif [[ -d "/c/Users/$USER/AppData/Local/Android/Sdk" ]]; then
                echo "/c/Users/$USER/AppData/Local/Android/Sdk"
                return 0
            fi
            ;;
    esac
    
    return 1
}

# Get emulator executable name
get_emulator_exe() {
    local os="$1"
    case "$os" in
        windows) echo "emulator.exe";;
        *)       echo "emulator";;
    esac
}

# Get ADB executable name
get_adb_exe() {
    local os="$1"
    case "$os" in
        windows) echo "adb.exe";;
        *)       echo "adb";;
    esac
}

# Main script
main() {
    echo -e "${BLUE}========================================${NC}"
    echo -e "${BLUE}Cross-Platform Desktop-Android Test${NC}"
    echo -e "${BLUE}========================================${NC}"
    
    # Detect OS
    OS=$(detect_os)
    echo -e "${CYAN}Operating System: $OS${NC}"
    
    if [[ "$OS" == "unknown" ]]; then
        echo -e "${RED}✗ Unsupported operating system${NC}"
        exit 1
    fi
    
    # Find Android SDK
    echo -e "\n${YELLOW}[1/6] Locating Android SDK...${NC}"
    ANDROID_SDK=$(get_android_sdk "$OS")
    
    if [[ -z "$ANDROID_SDK" ]]; then
        echo -e "${RED}✗ Android SDK not found${NC}"
        echo -e "${YELLOW}Please set ANDROID_HOME or ANDROID_SDK_ROOT environment variable${NC}"
        echo -e "${YELLOW}Or install Android SDK in default location:${NC}"
        case "$OS" in
            macos)   echo "  ~/Library/Android/sdk";;
            linux)   echo "  ~/Android/Sdk";;
            windows) echo "  %LOCALAPPDATA%\\Android\\Sdk";;
        esac
        exit 1
    fi
    
    echo -e "${GREEN}✓ Android SDK found: $ANDROID_SDK${NC}"
    
    # Set up paths
    EMULATOR_EXE=$(get_emulator_exe "$OS")
    ADB_EXE=$(get_adb_exe "$OS")
    EMULATOR="$ANDROID_SDK/emulator/$EMULATOR_EXE"
    ADB="$ANDROID_SDK/platform-tools/$ADB_EXE"
    
    # Verify executables exist
    if [[ ! -f "$EMULATOR" ]]; then
        echo -e "${RED}✗ Emulator not found at: $EMULATOR${NC}"
        exit 1
    fi
    
    if [[ ! -f "$ADB" ]]; then
        echo -e "${RED}✗ ADB not found at: $ADB${NC}"
        exit 1
    fi
    
    # Configuration
    AVD_NAME="${AVD_NAME:-Pixel_9a}"
    PACKAGE_NAME="com.buccancs"
    MAIN_ACTIVITY="$PACKAGE_NAME/.ui.MainActivity"
    
    echo -e "${CYAN}Emulator: $EMULATOR${NC}"
    echo -e "${CYAN}ADB: $ADB${NC}"
    echo -e "${CYAN}AVD: $AVD_NAME${NC}"
    
    # Step 2: Cleanup
    echo -e "\n${YELLOW}[2/6] Cleaning up previous sessions...${NC}"
    
    # Kill emulator (OS-specific)
    case "$OS" in
        windows)
            taskkill //F //IM "$EMULATOR_EXE" 2>/dev/null || true
            taskkill //F //IM "qemu-system-x86_64.exe" 2>/dev/null || true
            ;;
        *)
            pkill -9 -f "emulator.*$AVD_NAME" 2>/dev/null || true
            pkill -9 -f qemu 2>/dev/null || true
            ;;
    esac
    
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
    SCREENSHOT_FILE="test_android_screen.png"
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
    
    "$GRADLEW" :desktop:run > desktop_test_output.log 2>&1 &
    DESKTOP_PID=$!
    echo "Desktop app PID: $DESKTOP_PID"
    
    # Wait for desktop to start
    echo "Waiting for desktop app..."
    MAX_WAIT=30
    WAIT_COUNT=0
    while [[ $WAIT_COUNT -lt $MAX_WAIT ]]; do
        if grep -q "gRPC server started" desktop_test_output.log 2>/dev/null; then
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
        if grep -q "Registered device android-" desktop_test_output.log 2>/dev/null; then
            echo -e "\n${GREEN}✓✓✓ CONNECTION ESTABLISHED ✓✓✓${NC}\n"
            
            DEVICE_ID=$(grep "Registered device" desktop_test_output.log | tail -1 | sed 's/.*Registered device //' | cut -d' ' -f1)
            echo -e "${BLUE}Device ID: ${CYAN}$DEVICE_ID${NC}"
            
            grep -E "Registered device|registered with protocol|connected" desktop_test_output.log | tail -3
            
            # Final screenshot
            "$ADB" exec-out screencap -p > test_android_connected.png 2>/dev/null || true
            
            echo -e "\n${GREEN}✓ Test completed successfully!${NC}"
            echo -e "${CYAN}OS: $OS${NC}"
            echo -e "${CYAN}Android SDK: $ANDROID_SDK${NC}"
            echo -e "${CYAN}Screenshots: test_android_screen.png, test_android_connected.png${NC}"
            echo -e "${CYAN}Logs: desktop_test_output.log${NC}"
            
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
