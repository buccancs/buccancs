#!/usr/bin/env bash
# Cross-platform headless command infrastructure test
# Works on: macOS, Linux, Windows (WSL)

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
# shellcheck disable=SC1091
source "$SCRIPT_DIR/../lib/android_env.sh"

# Colors
if [[ -t 1 ]]; then
    RED='\033[0;31m'; GREEN='\033[0;32m'; YELLOW='\033[1;33m'
    BLUE='\033[0;34m'; CYAN='\033[0;36m'; NC='\033[0m'
else
    RED=''; GREEN=''; YELLOW=''; BLUE=''; CYAN=''; NC=''
fi

main() {
    echo -e "${BLUE}========================================${NC}"
    echo -e "${BLUE}Cross-Platform Headless Test${NC}"
    echo -e "${BLUE}========================================${NC}"
    
    OS=$(detect_os)
    OS_LABEL="$OS"
    if is_wsl; then
        OS_LABEL="${OS_LABEL} (wsl)"
    fi
    echo -e "${CYAN}Operating System: $OS_LABEL${NC}"
    [[ "$OS" == "unknown" ]] && { echo -e "${RED}✗ Unsupported OS${NC}"; exit 1; }
    
    # Find Android SDK
    echo -e "\n${YELLOW}[1/5] Locating Android SDK...${NC}"
    ANDROID_SDK=$(find_android_sdk "$OS")
    if [[ -z "$ANDROID_SDK" ]]; then
        echo -e "${RED}✗ Android SDK not found${NC}"
        echo -e "${YELLOW}Set ANDROID_HOME or install at a default location${NC}"
        if [[ "$OS" == "linux" ]] && is_wsl; then
            echo -e "${YELLOW}Common WSL path: /mnt/c/Users/<windows-user>/AppData/Local/Android/Sdk${NC}"
        fi
        exit 1
    fi
    echo -e "${GREEN}✓ Android SDK: $ANDROID_SDK${NC}"
    
    # Set paths
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
    
    AVD_NAME="${AVD_NAME:-Pixel_9a}"
    PACKAGE_NAME="com.buccancs"
    
    # Create log directory
    PROJECT_ROOT="$(cd "$SCRIPT_DIR/../../.." && pwd)"
    SUITE_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
    LOG_ROOT="$SUITE_ROOT/logs"
    mkdir -p "$LOG_ROOT"
    LOG_DIR="$LOG_ROOT/headless_test_${OS}"
    mkdir -p "$LOG_DIR"
    
    echo -e "${CYAN}Log directory: $LOG_DIR${NC}"
    
    # Cleanup
    echo -e "\n${YELLOW}[2/5] Cleaning up...${NC}"
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
    sleep 1
    echo -e "${GREEN}✓ Cleanup complete${NC}"
    
    # Start emulator headless
    echo -e "\n${YELLOW}[3/6] Starting emulator (headless)...${NC}"
    "$EMULATOR" -avd "$AVD_NAME" -no-snapshot-load -no-window -no-audio > "$LOG_DIR/emulator.log" 2>&1 &
    EMULATOR_PID=$!
    
    "$ADB" wait-for-device
    MAX_BOOT=60; BOOT_WAIT=0
    while [[ $BOOT_WAIT -lt $MAX_BOOT ]]; do
        BOOT=$("$ADB" shell getprop sys.boot_completed 2>/dev/null | tr -d '\r\n' || echo "")
        [[ "$BOOT" == "1" ]] && break
        sleep 1; ((BOOT_WAIT++))
    done
    [[ "$BOOT" != "1" ]] && { echo -e "${RED}✗ Boot timeout${NC}"; exit 1; }
    
    sleep 2
    echo -e "${GREEN}✓ Emulator ready (PID: $EMULATOR_PID, headless)${NC}"
    
    # Build and install app
    echo -e "\n${YELLOW}[4/6] Installing Android app...${NC}"
    GRADLEW=$([[ "$OS" == "windows" ]] && echo "./gradlew.bat" || echo "./gradlew")
    [[ ! -f "$GRADLEW" ]] && { echo -e "${RED}✗ Gradle wrapper not found${NC}"; exit 1; }

    if [[ ! -f "$PROJECT_ROOT/app/build/outputs/apk/debug/app-debug.apk" ]]; then
        (cd "$PROJECT_ROOT" && "$GRADLEW" :app:assembleDebug > "$LOG_DIR/assemble.log" 2>&1)
    fi

    "$ADB" install -r "$PROJECT_ROOT/app/build/outputs/apk/debug/app-debug.apk" > "$LOG_DIR/adb_install.log" 2>&1 || {
        echo -e "${RED}✗ Failed to install debug APK (see $LOG_DIR/adb_install.log)${NC}"
        exit 1
    }

    # Grant permissions and start app
    echo -e "\n${YELLOW}[5/6] Starting Android app...${NC}"
    for perm in BLUETOOTH_SCAN BLUETOOTH_CONNECT ACCESS_FINE_LOCATION CAMERA RECORD_AUDIO POST_NOTIFICATIONS; do
        "$ADB" shell pm grant "$PACKAGE_NAME" "android.permission.$perm" 2>/dev/null || true
    done
    
    "$ADB" shell am start -W -n "$PACKAGE_NAME/.ui.MainActivity" > /dev/null 2>&1
    sleep 3
    
    "$ADB" logcat -c
    "$ADB" logcat -v time > "$LOG_DIR/android.log" 2>&1 &
    LOGCAT_PID=$!
    
    echo -e "${GREEN}✓ Android app running${NC}"
    
    # Start desktop headless
    echo -e "\n${YELLOW}[6/6] Starting desktop (headless)...${NC}"

    cd "$PROJECT_ROOT"
    
    "$GRADLEW" :desktop:runHeadlessServer > "$LOG_DIR/desktop.log" 2>&1 &
    DESKTOP_PID=$!
    
    # Wait for gRPC
    MAX_WAIT=30; WAIT=0
    while [[ $WAIT -lt $MAX_WAIT ]]; do
        grep -q "running on port 50051" "$LOG_DIR/desktop.log" 2>/dev/null && break
        sleep 1; ((WAIT++))
    done
    [[ $WAIT -eq $MAX_WAIT ]] && { echo -e "${RED}✗ Desktop timeout${NC}"; exit 1; }
    echo -e "${GREEN}✓ Desktop ready (PID: $DESKTOP_PID, headless)${NC}"
    
    # Wait for connection
    echo -e "\n${CYAN}Monitoring connection...${NC}"
    MAX_CONN=60; CONN=0
    while [[ $CONN -lt $MAX_CONN ]]; do
        if grep -q "Registered device android-" "$LOG_DIR/desktop.log" 2>/dev/null; then
            DEVICE_ID=$(grep "Registered device" "$LOG_DIR/desktop.log" | tail -1 | grep -o "android-[a-z0-9]*")
            echo -e "\n${GREEN}✓ Connected: $DEVICE_ID${NC}\n"
            
            echo -e "${BLUE}Connection Details:${NC}"
            grep -E "Registered|protocol version|connected" "$LOG_DIR/desktop.log" | tail -3
            
            echo -e "\n${BLUE}Summary:${NC}"
            echo -e "  OS: ${CYAN}$OS_LABEL${NC}"
            echo -e "  Device: ${CYAN}$DEVICE_ID${NC}"
            echo -e "  Emulator: ${GREEN}Headless (PID: $EMULATOR_PID)${NC}"
            echo -e "  Desktop: ${GREEN}Headless (PID: $DESKTOP_PID)${NC}"
            echo -e "  Logs: ${CYAN}$LOG_DIR${NC}"
            
            echo -e "\n${GREEN}✓ Headless test passed!${NC}"
            
            # Cleanup
            kill $DESKTOP_PID 2>/dev/null || true
            kill $LOGCAT_PID 2>/dev/null || true
            if [[ "$USE_WINDOWS_TOOLS" == true ]]; then
                TASKKILL="/mnt/c/Windows/System32/taskkill.exe"
                if [[ -x "$TASKKILL" ]]; then
                    "$TASKKILL" /F /PID "$EMULATOR_PID" 2>/dev/null || true
                else
                    kill -9 "$EMULATOR_PID" 2>/dev/null || true
                fi
            else
                kill -9 "$EMULATOR_PID" 2>/dev/null || true
            fi
            "$ADB" kill-server 2>/dev/null || true
            
            exit 0
        fi
        echo -n "."; sleep 2; ((CONN++))
    done
    
    echo -e "\n${RED}✗ Connection timeout${NC}"
    exit 1
}

main "$@"
