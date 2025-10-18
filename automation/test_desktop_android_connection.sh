#!/bin/bash
# Automated test script for desktop app and Android virtual device connection
# Grants all permissions via ADB before starting the app

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Configuration
ANDROID_SDK_HOME="$HOME/Library/Android/sdk"
EMULATOR="$ANDROID_SDK_HOME/emulator/emulator"
ADB="$ANDROID_SDK_HOME/platform-tools/adb"
AVD_NAME="Pixel_9a"
PACKAGE_NAME="com.buccancs"
MAIN_ACTIVITY="$PACKAGE_NAME/.ui.MainActivity"

echo -e "${BLUE}========================================${NC}"
echo -e "${BLUE}Desktop-Android Connection Test${NC}"
echo -e "${BLUE}========================================${NC}"

# Step 1: Cleanup
echo -e "\n${YELLOW}[1/6] Cleaning up previous sessions...${NC}"
pkill -9 -f "emulator.*$AVD_NAME" 2>/dev/null || true
pkill -9 -f "qemu" 2>/dev/null || true
"$ADB" kill-server 2>/dev/null || true
sleep 2
echo -e "${GREEN}✓ Cleanup complete${NC}"

# Step 2: Start emulator
echo -e "\n${YELLOW}[2/6] Starting Android emulator...${NC}"
"$EMULATOR" -avd "$AVD_NAME" -no-snapshot-load > /dev/null 2>&1 &
EMULATOR_PID=$!
echo "Emulator PID: $EMULATOR_PID"

# Wait for device
echo "Waiting for device..."
"$ADB" wait-for-device
echo "Device detected, waiting for boot completion..."
"$ADB" shell 'while [[ -z $(getprop sys.boot_completed) ]]; do sleep 1; done'
sleep 3
echo -e "${GREEN}✓ Emulator ready${NC}"
"$ADB" devices

# Step 3: Grant all permissions
echo -e "\n${YELLOW}[3/6] Granting all app permissions...${NC}"

# Runtime permissions that need to be granted
PERMISSIONS=(
    # Bluetooth permissions
    "android.permission.BLUETOOTH_SCAN"
    "android.permission.BLUETOOTH_CONNECT"
    "android.permission.BLUETOOTH_ADVERTISE"
    
    # Location permissions
    "android.permission.ACCESS_FINE_LOCATION"
    "android.permission.ACCESS_COARSE_LOCATION"
    "android.permission.ACCESS_BACKGROUND_LOCATION"
    
    # Camera and audio
    "android.permission.CAMERA"
    "android.permission.RECORD_AUDIO"
    
    # Storage/Media permissions
    "android.permission.READ_MEDIA_IMAGES"
    "android.permission.READ_MEDIA_VIDEO"
    "android.permission.READ_EXTERNAL_STORAGE"
    "android.permission.WRITE_EXTERNAL_STORAGE"
    
    # Notifications
    "android.permission.POST_NOTIFICATIONS"
    
    # Phone state
    "android.permission.READ_PHONE_STATE"
)

GRANTED_COUNT=0
FAILED_COUNT=0

for PERMISSION in "${PERMISSIONS[@]}"; do
    echo -n "  Granting $PERMISSION... "
    if "$ADB" shell pm grant "$PACKAGE_NAME" "$PERMISSION" 2>/dev/null; then
        echo -e "${GREEN}✓${NC}"
        ((GRANTED_COUNT++))
    else
        echo -e "${YELLOW}⚠ (may not be applicable)${NC}"
        ((FAILED_COUNT++))
    fi
done

echo -e "\n${GREEN}✓ Granted $GRANTED_COUNT permissions${NC}"
if [ $FAILED_COUNT -gt 0 ]; then
    echo -e "${YELLOW}  Note: $FAILED_COUNT permissions not applicable for this Android version${NC}"
fi

# Verify granted permissions
echo -e "\n${BLUE}Verifying granted permissions:${NC}"
"$ADB" shell dumpsys package "$PACKAGE_NAME" | grep -A 1 "granted=true" | head -20

# Step 4: Start Android app
echo -e "\n${YELLOW}[4/6] Starting Android app...${NC}"
"$ADB" shell am start -W -n "$MAIN_ACTIVITY"
sleep 2
echo -e "${GREEN}✓ Android app started${NC}"

# Take screenshot
"$ADB" exec-out screencap -p > test_android_screen.png 2>/dev/null || true
echo "Screenshot saved: test_android_screen.png"

# Step 5: Start desktop app
echo -e "\n${YELLOW}[5/6] Starting desktop app...${NC}"
cd "$(dirname "$0")/.."
./gradlew :desktop:run > desktop_test_output.log 2>&1 &
DESKTOP_PID=$!
echo "Desktop app PID: $DESKTOP_PID"

# Wait for desktop app to initialize
echo "Waiting for desktop app to start..."
sleep 20

# Check if gRPC server started
if grep -q "gRPC server started" desktop_test_output.log; then
    echo -e "${GREEN}✓ Desktop app started successfully${NC}"
    grep "gRPC server" desktop_test_output.log
else
    echo -e "${RED}✗ Desktop app may have failed to start${NC}"
fi

# Step 6: Monitor connection
echo -e "\n${YELLOW}[6/6] Monitoring connection...${NC}"
echo "Waiting for device registration..."

# Monitor desktop logs for connection
for i in {1..30}; do
    if grep -q "Registered device android-" desktop_test_output.log; then
        echo -e "\n${GREEN}✓✓✓ CONNECTION ESTABLISHED ✓✓✓${NC}\n"
        
        # Extract connection details
        echo -e "${BLUE}Connection Details:${NC}"
        grep -E "Registered device|registered with protocol|Device.*connected" desktop_test_output.log | tail -5
        
        echo -e "\n${BLUE}Time Synchronization:${NC}"
        grep "drift" desktop_test_output.log | tail -5
        
        # Save final screenshot
        "$ADB" exec-out screencap -p > test_android_connected.png 2>/dev/null || true
        echo -e "\n${GREEN}✓ Test completed successfully!${NC}"
        echo "Screenshots saved: test_android_screen.png, test_android_connected.png"
        echo "Desktop logs: desktop_test_output.log"
        
        exit 0
    fi
    echo -n "."
    sleep 2
done

echo -e "\n${RED}✗ Connection timeout after 60 seconds${NC}"
echo "Check desktop_test_output.log for details"
exit 1
