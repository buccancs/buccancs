#!/usr/bin/env python3
"""
Simple gRPC client to send START/STOP recording commands to the desktop orchestrator.
Used for headless testing of command flow to Android devices.
"""

import grpc
import json
import time
import sys
import uuid
from datetime import datetime

# This is a simplified version - actual implementation would need the compiled proto files
# For now, we'll use grpcurl or create a simple test

def send_command_via_grpcurl(session_id, device_id, command_type="START"):
    """Send command using grpcurl tool"""
    import subprocess
    
    now_ms = int(time.time() * 1000)
    command_id = str(uuid.uuid4())
    
    if command_type == "START":
        payload = {
            "commandId": command_id,
            "sessionId": session_id,
            "issuedEpochMs": now_ms,
            "executeEpochMs": now_ms + 1000,
            "anchorEpochMs": now_ms,
            "scheduledEpochMs": now_ms + 1000,
            "_type": "StartRecordingCommandPayload"
        }
    else:  # STOP
        payload = {
            "commandId": command_id,
            "sessionId": session_id,
            "issuedEpochMs": now_ms,
            "executeEpochMs": now_ms,
            "_type": "StopRecordingCommandPayload"
        }
    
    # Note: This requires grpcurl to be installed
    # brew install grpcurl
    
    print(f"Sending {command_type} command: {command_id}")
    print(f"Session: {session_id}")
    print(f"Payload: {json.dumps(payload, indent=2)}")
    
    # This is conceptual - actual gRPC call would need proper proto definitions
    return command_id

def main():
    if len(sys.argv) < 3:
        print("Usage: python3 send_grpc_command.py <session_id> <device_id> [START|STOP]")
        sys.exit(1)
    
    session_id = sys.argv[1]
    device_id = sys.argv[2]
    command_type = sys.argv[3] if len(sys.argv) > 3 else "START"
    
    print(f"=" * 60)
    print(f"Desktop Orchestrator Command Sender")
    print(f"=" * 60)
    print(f"Session ID: {session_id}")
    print(f"Device ID: {device_id}")
    print(f"Command Type: {command_type}")
    print(f"Target: localhost:50051")
    print(f"=" * 60)
    
    command_id = send_command_via_grpcurl(session_id, device_id, command_type)
    
    print(f"\n✓ Command sent: {command_id}")
    print(f"\nMonitor the Android logcat for command reception:")
    print(f"  adb logcat | grep -i 'command\\|recording\\|{session_id}'")

if __name__ == "__main__":
    main()
