# Project Backlog

**Last Modified:** 2025-10-13 20:10 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Project Backlog

## CRITICAL PRIORITY

- Complete desktop file upload receiver and session folder aggregation (blocks end-to-end testing)
- Implement production time synchronisation server and measure actual accuracy (NFR2 unvalidated)
- Enable tests and create integration test framework (4% coverage insufficient)
- Fix memory leaks: DisplayListener unregistration, ShimmerConnector Handler/Context lifecycle
- Refactor MainViewModel (1248 lines) - extract use cases for session, device, hardware management

## HIGH PRIORITY

- Execute end-to-end soak with two Shimmer units and two Topdon cameras to confirm sync, transfer retries, and retention alarms
- Adopt Result/Either error handling pattern consistently across connectors and services
- Add resource cleanup validation tests (camera, bluetooth, file handles)
- Split desktop GrpcServer.kt into separate service implementation files
- Add DI module tests to catch configuration errors at build time

## MEDIUM PRIORITY

- Refactor ShimmerSensorConnector (706 lines) - extract state machine and data writer
- Persist runtime inventory updates (Shimmer scans, Topdon attachments) into device-inventory.json
- Add proto versioning and convert embedded JSON payloads to typed messages
- Implement atomic file writes with checksums for critical manifests
- Add timeout to all hardware operations (bluetooth, USB, network)

## ONGOING

- Add preview throttling and palette controls once hardware field dates are locked
- Surface ControlServer state/token info in the app UI and document how to call the local command endpoint
- Extend mDNS advertiser/browser with retries, TXT attribute schema, and automatic ControlServer port discovery
- Harden UploadWorker resume logic by persisting queued artifacts and exposing progress in the Live Session screen
- Feed HeartbeatMonitor warnings into RecordingService to trigger safe auto-stop on missed beats
- Expose bookmark capture and review controls (label editing, manifest export)
- Extend Live Session telemetry panel with encoder performance charts per segment

## References

See ../analysis/TECHNICAL_DEBT_ANALYSIS_2025-10-13.md and ../analysis/CODE_QUALITY_ANALYSIS_2025-10-13.md for detailed findings.
