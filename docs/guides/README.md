**Last Modified:** 2025-10-15 03:25 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Index

# Guides Directory

Practical guides for development, testing, and integration.

## Testing Guides

### DI_TESTING_GUIDE_2025-10-14.md
Comprehensive dependency injection testing guide covering:
- Module verification tests
- Hardware service mocking with TestHardwareModule
- Integration testing patterns
- Quick reference cheat sheet for common patterns
- Troubleshooting and best practices

**Use this for:** Writing tests with Hilt, MockK, and Robolectric

### TEST_EXECUTION_GUIDE_2025-10-14.md
Test execution commands and configuration:
- Running tests with gradle (tests disabled by default)
- Command-line options and filters
- Coverage report generation
- Test output configuration

**Use this for:** Running and debugging tests

## Error Handling Guides

### ERROR_HANDLING_MIGRATION_GUIDE_2025-10-14.md
Generic patterns for migrating to Result-based error handling:
- Before/after examples for common scenarios
- Chaining operations with flatMap
- Error recovery patterns
- Testing Result-based code
- Migration checklist

**Use this for:** Converting existing code to Result pattern

### ERROR_HANDLING_REFACTORING_EXAMPLES_2025-10-14.md
Real BuccanCS codebase refactoring examples:
- ShimmerSensorConnector.connect() refactoring
- SegmentedMediaCodecRecorder error handling
- RecordingStorage operations
- Actual implementation patterns from project

**Use this for:** See concrete examples from this codebase

### ERROR_HANDLING_TESTING_GUIDE_2025-10-14.md
Testing strategies for Result-based error handling:
- Unit test patterns for success and failure
- Error categorisation testing
- Cancellation safety verification
- Coverage and test file locations

**Use this for:** Writing tests for Result-returning functions

## Integration Reference

### shimmer-integration-notes.txt
Detailed notes on Shimmer SDK integration:
- Source layout and architecture
- Android Instrument Driver overview
- Sample app analysis
- API reference and message flow
- Class responsibilities and pipeline
- Configuration and settings flow
- Data processing details
- Applicability to BuccanCS

**Use this for:** Understanding Shimmer SDK internals

### topdon_tc001_integration.txt
TOPDON TC001 thermal camera integration reference:
- SDK structure and components
- BLE communication patterns
- Device discovery and pairing
- Thermal data processing
- Example implementations

**Use this for:** Implementing TOPDON thermal camera support

### topdon_navigation.txt
Navigation and project structure for TOPDON integration:
- Directory layout
- Key source files
- Configuration files
- Build system integration

**Use this for:** Finding TOPDON SDK components

## Related Documentation

### Architecture
- `docs/architecture/ERROR_HANDLING_STRATEGY_2025-10-14.md` - Overall error handling architecture
- `docs/architecture/DI_ARCHITECTURE_2025-10-14.md` - Dependency injection design

### Analysis
- `docs/analysis/DI_QUALITY_ANALYSIS_2025-10-14.md` - DI implementation audit
- `docs/analysis/ERROR_HANDLING_AUDIT_2025-10-14.md` - Error handling audit

### Project
- `docs/project/DI_FIXES_IMPLEMENTATION_2025-10-14.md` - DI implementation work log
- `docs/project/ERROR_HANDLING_IMPLEMENTATION_2025-10-14.md` - Error handling work log
- `docs/project/BACKLOG.md` - Current tasks and priorities

## Guide Selection

**I want to...**
- Write DI tests → DI_TESTING_GUIDE_2025-10-14.md
- Run tests → TEST_EXECUTION_GUIDE_2025-10-14.md
- Convert code to Result pattern → ERROR_HANDLING_MIGRATION_GUIDE_2025-10-14.md
- See real refactoring examples → ERROR_HANDLING_REFACTORING_EXAMPLES_2025-10-14.md
- Test Result-based code → ERROR_HANDLING_TESTING_GUIDE_2025-10-14.md
- Integrate Shimmer sensors → shimmer-integration-notes.txt
- Integrate TOPDON camera → topdon_tc001_integration.txt

## Recent Changes

See `docs/project/DOCUMENTATION_CLEANUP_2025-10-15_0325.md` for recent consolidation changes.
