# Project Backlog

**Last Modified:** 2025-10-15 13:12 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Project Backlog

## TOPDON COMPOSE MATERIAL MIGRATION - DEV A WORKSTREAM

### Week 1-2: Foundation Components Complete (Days 1-10) ✓ DONE

- ~~**Day 1-2: Theme & Colour System**~~ **[DONE 2025-10-15]** - TopdonColors.kt, TopdonTheme.kt, TopdonSpacing.kt created
- ~~**Day 3: Button Components**~~ **[DONE 2025-10-15]** - TopdonButton.kt with all variants
- ~~**Day 4: Text Field Components**~~ **[DONE 2025-10-15]** - TopdonTextField.kt with validation
- ~~**Day 5: Dialog Components**~~ **[DONE 2025-10-15]** - TopdonDialog.kt with multiple variants
- ~~**Day 6: Progress Components**~~ **[DONE 2025-10-15]** - TopdonProgress.kt with indicators
- ~~**Day 7-8: Navigation Components**~~ **[DONE 2025-10-15]** - TopdonNavigation.kt, TopdonAppBar.kt
- ~~**Day 8-9: Card & List Components**~~ **[DONE 2025-10-15]** - TopdonCard.kt with device/gallery/settings variants
- ~~**Day 9-10: Control Components**~~ **[DONE 2025-10-15]** - TopdonControls.kt with sliders, switches, checkboxes
- ~~**Day 10: Camera Components**~~ **[DONE 2025-10-15]** - TopdonCameraControls.kt with measurement modes, palettes, crosshair, temperature range

**Summary:** 12 files created, ~8500 lines, 30+ composables, all with previews  
**Result:** Complete Topdon UI component library ready for screen migration

### Week 3+: Support Role
- **Screen Migration Support** **[TODO]** - Assist Dev B with navigation integration
- **Thermal UI Polish** **[TODO]** - Assist Dev C with thermal screen components
- **Additional Components** **[TODO]** - Create components as needed during migration
- **Accessibility Audit** **[TODO]** - WCAG compliance review
- **Performance Optimisation** **[TODO]** - Recomposition and rendering optimisation

**Status:** Foundation complete, ready to support other workstreams

## TOPDON COMPOSE MATERIAL MIGRATION - DEV D WORKSTREAM

### Week 1: UI Enhancement (Days 1-6)
- ~~**Day 6: TopdonScreen Material 3 Enhancement**~~ **[DONE 2025-10-15]** - Migrated TopdonScreen.kt to enhanced Material 3 design with icons, ElevatedCards, improved visual hierarchy, FilledTonalButtons, and better error handling
- **Day 7-10: Additional Screen Migrations** **[TODO]** - Apply Material 3 enhancements to remaining device screens (Shimmer, RGB Camera)

**Estimated Time Remaining:** 2-3 days  
**Files Modified:** 1 screen (TopdonScreen.kt)  
**Next Steps:** Migrate other device screens following same Material 3 patterns

## TOPDON COMPOSE MATERIAL MIGRATION - DEV B WORKSTREAM

### Week 1: Foundation Setup (Days 1-6)
- ~~**Day 6: Splash Screen Migration**~~ **[DONE 2025-10-15]** - SplashScreen.kt, SplashViewModel.kt created with fade-in animation
- ~~**Day 6-7: Information Screens**~~ **[DONE 2025-10-15]** - ClauseScreen, PolicyScreen, VersionScreen, HelpScreen, WebViewScreen all created with Material 3 components
- **Day 7-10: Navigation Integration** **[IN PROGRESS]** - Update AppNavHost.kt to wire up new screens, implement navigation flows
- **Repository Setup** **[TODO]** - Create GalleryRepository.kt, SettingsRepository.kt, UserRepository.kt with Hilt bindings

**Estimated Time Remaining:** 2 days  
**Files Created:** 12 screens and ViewModels  
**Next Steps:** Wire navigation, add persistence for clause acceptance, create repositories

## UI MODERNIZATION ✓ COMPLETE

- ~~**Phase 1: Android Foundation**~~ **[DONE 2025-10-15]** - Design system, navigation, devices screen complete
- ~~**Phase 2: Android Screen Refinement**~~ **[DONE 2025-10-15]** - Live session enhancements, settings reorganization, sessions library improvements complete
- ~~**Phase 3: Component Library**~~ **[DONE 2025-10-15]** - Extracted 8 reusable components
- ~~**Phase 4: Desktop UI Foundation**~~ **[DONE 2025-10-15]** - Theme system, spacing tokens
- ~~**Phase 5: Desktop Screens**~~ **[DONE 2025-10-15]** - Dashboard modernization complete
- ~~**Phase 6: Polish & Testing**~~ **[DONE 2025-10-15]** - Code quality verification, documentation complete

**Total Time:** 4 hours 45 minutes  
**Result:** Complete UI modernization across Android and Desktop with Material 3 design, consistent spacing, and reusable components

## CRITICAL PRIORITY

- ~~**Resolve Gradle wrapper issue**~~ **[DONE 2025-10-15]** - Wrapper regenerated with proper manifest, functional for basic operations; WSL/Android SDK interaction issues remain
- **Enable tests and verify UI test compilation** **[BLOCKED]** - 7 UI test files implemented, WSL filesystem I/O errors prevent Android compilation from WSL
- ~~Complete desktop file upload receiver and session folder aggregation~~ **[DONE 2025-10-15]** - DataTransferServiceImpl fully implemented with SessionAggregationService integration; manifest consolidation, duplicate detection, checksum validation, session completeness checking all working; comprehensive test suite (11 tests) created
- ~~Implement integration tests for Android-Desktop communication~~ **[DONE 2025-10-15]** - Comprehensive test suite created with 8 tests covering device registration, session lifecycle, file upload, sensor streaming, time sync, commands, preview, and multi-device scenarios
- ~~Implement time synchronisation accuracy validation and measurement~~ **[DONE 2025-10-15]** - TimeSyncAccuracyTest validates NFR2 requirements (5ms avg, 10ms max), includes stability and load tests
- ~~Create multi-device stress tests~~ **[DONE 2025-10-15]** - MultiDeviceStressTest validates 8 device scalability with concurrent sensor streaming, file uploads, command broadcast, and 120-second duration test
- ~~Fix memory leaks: DisplayListener unregistration, ShimmerConnector Handler/Context lifecycle~~ **[DONE 2025-01-14]** - All 3 resource leaks fixed
- ~~Refactor MainViewModel (1248 lines) - extract use cases for session, device, hardware management~~ **[DONE 2025-10-15]** - Reduced from 1222 to 991 lines (19% reduction), extracted RgbCameraStateManager (231 lines) for camera state management and validation

## HIGH PRIORITY

- Execute end-to-end soak with two Shimmer units and two Topdon cameras to confirm sync, transfer retries, and retention
  alarms
- ~~Adopt Result/Either error handling pattern consistently across connectors and services~~ **[DONE 2025-01-14]** -
  Comprehensive Result pattern implemented
- ~~Add protocol version validation to all gRPC services~~ **[DONE 2025-10-15]** - Version validation implemented in OrchestrationServiceImpl.registerDevice, CommandServiceImpl, ControlServer.pushCommand; Android client sends version in DeviceRegistration; test suite created
- ~~Add resource cleanup validation tests (camera, bluetooth, file handles)~~ **[DONE 2025-10-15]** - Created ResourceCleanupTest with 15 tests covering camera resources, handler threads, surfaces, image readers, file handles, idempotent operations, and scope cancellation
- ~~Split desktop GrpcServer.kt into separate service implementation files~~ **[DONE 2025-10-15]** - Extracted 6 services (909 lines) into separate files, improved testability and maintainability
- ~~Add DI module tests to catch configuration errors at build time~~ **[DONE 2025-10-15]** - Created DependencyInjectionTest with 18 tests validating all repository and use case bindings, singleton scopes, and dependency resolution
- Address uncommitted changes in DefaultSessionTransferRepository.kt

## MEDIUM PRIORITY

- ~~Refactor ShimmerSensorConnector (706 lines) - extract state machine and data writer~~ **[PARTIAL 2025-01-14]** -
  Created ShimmerConnectionState & ShimmerDataWriter classes, full integration pending
- Persist runtime inventory updates (Shimmer scans, Topdon attachments) into device-inventory.json
- ~~Add proto versioning and convert embedded JSON payloads to typed messages~~ **[DONE 2025-10-15]** - Protocol versioning fully implemented with validation in all services, clients send version, server validates compatibility and logs version mismatches
- ~~Implement atomic file writes with checksums for critical manifests~~ **[DONE 2025-10-15]** - Created AtomicFileWriter utility with atomic operations, checksum verification, write-ahead logging, and backup/recovery; integrated into SessionAggregationService for all manifest writes; 18 comprehensive tests
- ~~Add timeout to all hardware operations (bluetooth, USB, network)~~ **[DONE 2025-10-15]** - Created HardwareTimeouts constants and withHardwareTimeout wrapper; integrated into RgbCameraConnector (camera open 10s, configure 5s); standardized timeouts for bluetooth (connect 15s, scan 30s), network (connect 10s, requests 30s), file I/O (read 10s, write 30s)
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

See the following documents for detailed information:
- ../analysis/PROJECT_STATUS_2025-10-15_1158.md - Comprehensive current status report (82% complete)
- ../analysis/CONCURRENCY_COMPLETE_SUMMARY_2025-10-14.md - Concurrency and threading analysis
- ../analysis/PROTOCOL_SERIALIZATION_ANALYSIS_2025-01-14.md - Protocol and message format details
- ../analysis/TOPDON_INTEGRATION_COMPARISON_2025-10-14.md - Hardware integration patterns
