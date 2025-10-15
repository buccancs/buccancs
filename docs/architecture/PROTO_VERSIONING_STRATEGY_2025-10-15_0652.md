**Last Modified:** 2025-10-15 06:52 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Architecture Strategy

# Protocol Versioning Strategy

## Current State

### Existing Version Fields

**sync/control.proto:**
- `CommandEnvelope.protocol_version` (int32, field 6)
- Currently unused in implementation

**orchestration.proto:**
- No version fields present
- Messages used for gRPC service communication

### Issues

1. **No version validation** - Services don't check protocol compatibility
2. **No version negotiation** - Clients and servers may have mismatched protocols
3. **JSON payloads** - Command payloads embedded as JSON strings instead of typed messages
4. **Breaking changes undetected** - No mechanism to detect incompatible protocol versions
5. **Downgrade safety** - Older clients may not handle newer protocol features

## Protocol Versioning Strategy

### Version Scheme

Use semantic versioning with single integer version numbers:

- **Version 1**: Initial protocol (current state)
- **Version 2**: Add typed command messages, remove JSON payloads
- **Version 3**: Add extended device capabilities
- **Version 4**: Add streaming improvements

**Version Number Format:** `MAJOR * 1000 + MINOR`
- Example: v1.0 = 1000, v1.5 = 1005, v2.0 = 2000

### Backward Compatibility Rules

1. **Minor version changes (1000 → 1001):**
   - Add optional fields only
   - Services must handle both old and new field presence
   - Clients can use either version

2. **Major version changes (1000 → 2000):**
   - Breaking changes allowed
   - Services must explicitly support both versions
   - Clients negotiate version during connection

3. **Deprecation policy:**
   - Support N-1 major versions
   - Announce deprecation 3 months in advance
   - Remove after 6 months

## Implementation Plan

### Phase 1: Add Version to All Messages

Add version field to all proto messages:

```protobuf
message StartSessionRequest {
  int32 protocol_version = 100;  // Reserve field 100 for version
  SessionIdentifier session = 1;
  // ... existing fields
}
```

**Field Number Convention:**
- Field 100: protocol_version (reserved across all messages)
- Fields 1-99: Message-specific fields
- Fields 101+: Future extensions

### Phase 2: Version Validation

Implement version checking in all gRPC services:

```kotlin
class OrchestrationServiceImpl : OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineImplBase() {
    override suspend fun startSession(request: StartSessionRequest): CommandAck {
        val clientVersion = request.protocolVersion
        if (!isCompatibleVersion(clientVersion)) {
            return commandAck {
                accepted = false
                info = "Incompatible protocol version: client=$clientVersion, server=$CURRENT_VERSION"
            }
        }
        // ... normal processing
    }
    
    private fun isCompatibleVersion(clientVersion: Int): Boolean {
        val clientMajor = clientVersion / 1000
        val serverMajor = CURRENT_VERSION / 1000
        // Support current and previous major version
        return clientMajor >= serverMajor - 1 && clientMajor <= serverMajor
    }
    
    companion object {
        private const val CURRENT_VERSION = 1000  // v1.0
    }
}
```

### Phase 3: Version Negotiation

Add version negotiation to device registration:

```protobuf
message DeviceRegistration {
  string device_id = 1;
  int32 min_protocol_version = 10;  // Minimum version client supports
  int32 max_protocol_version = 11;  // Maximum version client supports
  // ... existing fields
}

message RegistrationAck {
  bool accepted = 1;
  string session_id = 2;
  int32 negotiated_protocol_version = 3;  // Server chooses version
}
```

### Phase 4: Replace JSON Payloads with Typed Messages

Convert command payloads from JSON to protobuf:

**Before:**
```protobuf
message CommandEnvelope {
  string command_id = 1;
  string session_id = 2;
  string payload_json = 3;  // JSON string
}
```

**After:**
```protobuf
message CommandEnvelope {
  string command_id = 1;
  string session_id = 2;
  oneof payload {
    StartRecordingCommand start_recording = 10;
    StopRecordingCommand stop_recording = 11;
    SyncSignalCommand sync_signal = 12;
    EventMarkerCommand event_marker = 13;
  }
}

message StartRecordingCommand {
  string session_id = 1;
  int64 scheduled_epoch_ms = 2;
  bool include_preview = 3;
}

message StopRecordingCommand {
  string session_id = 1;
  int64 scheduled_epoch_ms = 2;
  bool finalize = 3;
}
```

### Phase 5: Protocol Version Constant

Create shared version constant:

```kotlin
// protocol/src/main/kotlin/com/buccancs/control/ProtocolVersion.kt
package com.buccancs.control

object ProtocolVersion {
    const val CURRENT = 1000  // v1.0
    const val MIN_SUPPORTED = 1000  // v1.0
    
    fun isCompatible(clientVersion: Int): Boolean {
        val clientMajor = clientVersion / 1000
        val currentMajor = CURRENT / 1000
        val minMajor = MIN_SUPPORTED / 1000
        return clientMajor in minMajor..currentMajor
    }
    
    fun versionString(version: Int): String {
        val major = version / 1000
        val minor = version % 1000
        return "v$major.$minor"
    }
}
```

## Migration Strategy

### Step 1: Add Version Fields (Non-Breaking)

Add protocol_version field to all messages with default value 1000.

**Desktop Services:**
- Validate incoming version in all RPC methods
- Log warnings for missing versions
- Default to v1.0 if not specified

**Android Client:**
- Send current version in all requests
- Handle version rejection gracefully

### Step 2: Enforce Version Validation (Warning Phase)

Log version mismatches but don't reject:

```kotlin
if (!isCompatibleVersion(clientVersion)) {
    logger.warn("Client using unsupported version: {}", versionString(clientVersion))
    // Continue processing for now
}
```

### Step 3: Create Typed Command Messages (Non-Breaking)

Add new command message types alongside JSON payloads:

```protobuf
message CommandEnvelope {
  string command_id = 1;
  string session_id = 2;
  string payload_json = 3;  // Deprecated, use typed_payload
  oneof typed_payload {
    StartRecordingCommand start_recording = 10;
    StopRecordingCommand stop_recording = 11;
  }
  int32 protocol_version = 6;
}
```

Clients send both for compatibility:
- New servers use typed_payload
- Old servers use payload_json

### Step 4: Enforce Version Requirements (Breaking)

Reject requests with incompatible versions:

```kotlin
if (!isCompatibleVersion(clientVersion)) {
    throw Status.FAILED_PRECONDITION
        .withDescription("Unsupported protocol version")
        .asRuntimeException()
}
```

### Step 5: Remove JSON Payloads (Major Version Bump)

Bump to v2.0 (version 2000) and remove payload_json field.

## Testing Strategy

### Unit Tests

```kotlin
class ProtocolVersionTest {
    @Test
    fun `current version is compatible with itself`() {
        assertTrue(ProtocolVersion.isCompatible(ProtocolVersion.CURRENT))
    }
    
    @Test
    fun `previous major version is supported`() {
        val previousMajor = ProtocolVersion.CURRENT - 1000
        assertTrue(ProtocolVersion.isCompatible(previousMajor))
    }
    
    @Test
    fun `future major version is not supported`() {
        val futureMajor = ProtocolVersion.CURRENT + 1000
        assertFalse(ProtocolVersion.isCompatible(futureMajor))
    }
    
    @Test
    fun `version string formats correctly`() {
        assertEquals("v1.0", ProtocolVersion.versionString(1000))
        assertEquals("v1.5", ProtocolVersion.versionString(1005))
        assertEquals("v2.0", ProtocolVersion.versionString(2000))
    }
}
```

### Integration Tests

```kotlin
class VersionNegotiationTest {
    @Test
    fun `old client connects to new server successfully`() {
        val client = createClient(protocolVersion = 1000)
        val response = client.registerDevice(deviceRegistration {
            deviceId = "test-device"
            minProtocolVersion = 1000
            maxProtocolVersion = 1000
        })
        assertTrue(response.accepted)
        assertEquals(1000, response.negotiatedProtocolVersion)
    }
    
    @Test
    fun `incompatible client is rejected`() {
        val client = createClient(protocolVersion = 500)  // Too old
        assertThrows<StatusException> {
            client.startSession(startSessionRequest {
                session = sessionIdentifier { id = "test" }
                protocolVersion = 500
            })
        }
    }
}
```

## Documentation

### Client Implementation Guide

```kotlin
// Android client example
class GrpcOrchestrationClient(channel: ManagedChannel) {
    private val stub = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)
    
    suspend fun startSession(sessionId: String): Result<Unit> {
        return try {
            val response = stub.startSession(startSessionRequest {
                session = sessionIdentifier { id = sessionId }
                protocolVersion = ProtocolVersion.CURRENT
            })
            if (response.accepted) Result.success(Unit)
            else Result.failure(Exception(response.info))
        } catch (e: StatusException) {
            if (e.status.code == Status.Code.FAILED_PRECONDITION) {
                Result.failure(ProtocolVersionException("Protocol version mismatch"))
            } else {
                Result.failure(e)
            }
        }
    }
}
```

### Server Implementation Guide

```kotlin
// Desktop server example
class OrchestrationServiceImpl : OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineImplBase() {
    override suspend fun startSession(request: StartSessionRequest): CommandAck {
        validateProtocolVersion(request.protocolVersion)
        // ... normal processing
    }
    
    private fun validateProtocolVersion(version: Int) {
        if (!ProtocolVersion.isCompatible(version)) {
            throw Status.FAILED_PRECONDITION
                .withDescription(
                    "Unsupported protocol version: ${ProtocolVersion.versionString(version)}. " +
                    "Server supports ${ProtocolVersion.versionString(ProtocolVersion.MIN_SUPPORTED)} " +
                    "to ${ProtocolVersion.versionString(ProtocolVersion.CURRENT)}"
                )
                .asRuntimeException()
        }
    }
}
```

## Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0 (1000) | 2025-10-15 | Initial protocol definition |
| 1.1 (1001) | TBD | Add protocol_version to all messages |
| 1.2 (1002) | TBD | Add version negotiation to registration |
| 2.0 (2000) | TBD | Replace JSON payloads with typed messages (breaking) |

## Benefits

1. **Compatibility detection** - Clients and servers detect version mismatches immediately
2. **Graceful degradation** - Older clients continue working with newer servers
3. **Safe upgrades** - Version negotiation ensures compatible communication
4. **Type safety** - Typed messages replace JSON payloads
5. **Better debugging** - Version information in logs helps diagnose issues
6. **Future-proof** - Framework supports incremental protocol evolution

## Risks and Mitigation

| Risk | Mitigation |
|------|------------|
| Breaking existing clients | Phased rollout with backward compatibility |
| Version check overhead | Minimal - single integer comparison |
| Complexity increase | Clear documentation and examples |
| Migration effort | Automated tooling for version field addition |

## Next Steps

1. Implement ProtocolVersion object in protocol module
2. Add version validation to all gRPC services
3. Update Android client to send protocol version
4. Add integration tests for version negotiation
5. Document version policy in README
6. Plan v2.0 migration to typed messages

## Timeline

- Week 1: Implement ProtocolVersion constant and validation
- Week 2: Update all services with version checks
- Week 3: Update Android client with version support
- Week 4: Testing and documentation
- Month 2: Plan v2.0 migration for typed messages
