# Project Backlog

**Last Modified:** 2025-10-14 10:40 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Project Backlog

## CRITICAL PRIORITY

- **Resolve Gradle wrapper issue** **[BLOCKING]** - ClassNotFoundException on GradleWrapperMain prevents all builds
- Complete desktop file upload receiver and session folder aggregation (blocks end-to-end testing)
- Implement production time synchronisation server and measure actual accuracy (NFR2 unvalidated)
- Enable tests and create integration test framework (85% coverage achieved but tests disabled)
- ~~Fix memory leaks: DisplayListener unregistration, ShimmerConnector Handler/Context lifecycle~~ **[DONE 2025-01-14]** - All 3 resource leaks fixed
- Refactor MainViewModel (1248 lines) - extract use cases for session, device, hardware management

## HIGH PRIORITY

- Execute end-to-end soak with two Shimmer units and two Topdon cameras to confirm sync, transfer retries, and retention alarms
- ~~Adopt Result/Either error handling pattern consistently across connectors and services~~ **[DONE 2025-01-14]** - Comprehensive Result pattern implemented
- Add resource cleanup validation tests (camera, bluetooth, file handles)
- Split desktop GrpcServer.kt into separate service implementation files
- Add DI module tests to catch configuration errors at build time
- Address uncommitted changes in DefaultSessionTransferRepository.kt

## MEDIUM PRIORITY

- ~~Refactor ShimmerSensorConnector (706 lines) - extract state machine and data writer~~ **[PARTIAL 2025-01-14]** -
  Created ShimmerConnectionState & ShimmerDataWriter classes, full integration pending
- Persist runtime inventory updates (Shimmer scans, Topdon attachments) into device-inventory.json
- Add proto versioning and convert embedded JSON payloads to typed messages
- Implement atomic file writes with checksums for critical manifests
- Add timeout to all hardware operations (bluetooth, USB, network)
- **Apply state machine pattern to TopdonThermalConnector and RgbCameraConnector** **[TODO]** - Use
  ShimmerConnectionState as template (estimated 2-3 days each)

## DOCUMENTATION

- **Create comprehensive README** **[DONE 2025-10-14]**
    - Added research context and motivation from LaTeX thesis
    - Documented system architecture and hardware integration
    - Listed features, requirements, and getting started guide
    - Included future work phases and research roadmap
    - Added thesis compilation and citation instructions
- **Update .github/copilot-instructions.md with emoji ban** **[DONE 2025-10-14]**
    - Added comprehensive emoji prohibition across all file types
    - Updated prohibitions section with explicit ban list
- **Documentation consolidation** **[DONE 2025-10-14]**
    - Removed 11 redundant phase documents (2,630 lines)
    - Consolidated error handling, SDK improvements, testing docs
    - Updated INDEX.md with complete file listings
    - 15% reduction in documentation volume

## ONGOING

- Add preview throttling and palette controls once hardware field dates are locked
- Surface ControlServer state/token info in the app UI and document how to call the local command endpoint
- Extend mDNS advertiser/browser with retries, TXT attribute schema, and automatic ControlServer port discovery
- Harden UploadWorker resume logic by persisting queued artifacts and exposing progress in the Live Session screen
- Feed HeartbeatMonitor warnings into RecordingService to trigger safe auto-stop on missed beats
- Expose bookmark capture and review controls (label editing, manifest export)
- Extend Live Session telemetry panel with encoder performance charts per segment

## References

See ../analysis/TECHNICAL_DEBT_ANALYSIS_2025-10-13.md and ../analysis/CODE_QUALITY_ANALYSIS_2025-10-13.md for detailed
findings.
