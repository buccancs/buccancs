# Active Task Plan: Hardware Integration & Testing Phase

**Created**: 2025-10-16  
**Target completion**: 2025-10-23  
**Status**: 🔵 Active

## Objective

Complete end-to-end hardware validation and bring the platform to production-ready state by integrating physical sensors, verifying multi-device coordination, and eliminating outstanding infrastructure blockers.

## Acceptance Criteria

1. ✅ Android agent connects successfully to physical Shimmer3 GSR sensor and captures valid 128 Hz data
2. ✅ Topdon TC001 thermal camera integration captures and stores thermal frames with correct metadata
3. ✅ Desktop orchestrator discovers multiple Android agents via mDNS and maintains stable gRPC connections
4. ✅ Time synchronisation achieves <10 ms offset across all connected devices during 5-minute test session
5. ✅ Complete recording session produces valid manifest, GSR CSV, RGB MP4, thermal frames, and audio on all agents
6. ✅ Upload pipeline successfully transfers all artefacts to desktop with retry verification
7. ✅ Android SDK Platform 36 licence accepted and full build passes without warnings
8. ✅ At least one automated test suite re-enabled and passing in CI

## Priority Tasks

### Phase 1: Hardware Setup & Basic Connectivity (Days 1-2)

- [ ] **T1.1** Connect physical Shimmer3 GSR sensor to Android device via Bluetooth
- [ ] **T1.2** Verify Shimmer3 pairing, configuration, and raw data stream in HardwareDebugger
- [ ] **T1.3** Connect Topdon TC001 thermal camera to Android device via USB
- [ ] **T1.4** Test thermal camera preview, capture, and frame export with real hardware
- [ ] **T1.5** Document hardware connection procedures and troubleshooting steps

### Phase 2: Multi-Device Orchestration (Days 3-4)

- [ ] **T2.1** Launch desktop orchestrator and verify mDNS device discovery broadcasts from Android
- [ ] **T2.2** Establish gRPC connections from desktop to 2+ Android agents simultaneously
- [ ] **T2.3** Test time synchronisation service with multiple devices and measure offsets
- [ ] **T2.4** Execute coordinated start/stop commands across all agents
- [ ] **T2.5** Verify heartbeat monitoring and reconnection logic during network interruptions

### Phase 3: End-to-End Recording Session (Days 5-6)

- [ ] **T3.1** Configure recording session on desktop with all modalities enabled
- [ ] **T3.2** Start coordinated recording across all agents with physical sensors active
- [ ] **T3.3** Capture 5-minute session with GSR, thermal, RGB, and audio data
- [ ] **T3.4** Stop session and verify manifest generation on all devices
- [ ] **T3.5** Validate artefact completeness: GSR CSV integrity, video encoding, thermal frame sequences
- [ ] **T3.6** Monitor upload pipeline and verify all artefacts transfer to desktop successfully

### Phase 4: Infrastructure & Build Fixes (Days 6-7)

- [ ] **T4.1** Accept Android SDK Platform 36 licences on development machine
- [ ] **T4.2** Run full Gradle build and resolve any remaining compilation issues
- [ ] **T4.3** Re-enable suspended test suites one module at a time
- [ ] **T4.4** Fix test infrastructure blockers preventing test execution
- [ ] **T4.5** Verify at least app core tests pass with `-Ptests.enabled=true`

### Phase 5: Polish & Documentation (Day 7)

- [ ] **T5.1** Update `docs/system-overview.md` with validated hardware configurations
- [ ] **T5.2** Document measured time sync performance and session recording metrics
- [ ] **T5.3** Create hardware setup guide with photos/diagrams for lab replication
- [ ] **T5.4** Update requirements.md with current completion status
- [ ] **T5.5** Archive this task plan and create new backlog items for remaining work

## Risks & Mitigations

| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|------------|
| Shimmer3 pairing failures | Medium | High | Document alternative pairing procedures; implement fallback simulation mode |
| Thermal camera driver issues | Medium | High | Maintain existing Topdon library; prepare USB debugging procedures |
| Desktop mDNS discovery flaky | Medium | Medium | Add manual IP entry fallback; improve discovery retry logic |
| Time sync drift exceeds target | Low | Medium | Increase sync sample frequency; validate NTP math implementation |
| Upload pipeline stalls | Low | High | Exercise retry logic thoroughly; add manual upload trigger |
| SDK licence acceptance blocked | High | Medium | Use alternative build machine; document manual acceptance procedure |

## Parallel Work Streams

- **Stream A** (Hardware): T1.1→T1.2→T1.3→T1.4 (serial sensor validation)
- **Stream B** (Desktop): T2.1→T2.2→T2.3 (orchestrator services)
- **Stream C** (Infrastructure): T4.1→T4.2→T4.3 (build & test fixes)

Streams A and B can proceed independently until Phase 3 integration. Stream C can run continuously alongside other phases.

## Verification Notes

After each phase, update this file with:
- Actual completion dates and time spent
- Hardware configuration details (device models, firmware versions)
- Measured performance metrics (sync offsets, frame rates, upload throughput)
- Issues encountered and resolutions applied
- Links to test artefacts, logs, and captured data samples

## Next Actions

1. Verify phone and peripherals are connected (✅ Complete)
2. Check repository status (✅ Complete)
3. Begin Phase 1: Connect Shimmer3 GSR sensor
4. Set up hardware debugging environment
