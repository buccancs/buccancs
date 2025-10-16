**Last Modified:** 2025-10-15 20:52 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Index

# Guides Directory

Practical guides for development, testing, and integration.

## Core Guides

### ERROR_HANDLING_GUIDE_2025-10-15.md

Comprehensive error handling guide covering:

- Migration from exception-based to Result pattern
- Real refactoring examples from codebase
- Testing strategies for Result-based code
- Quick reference for common patterns
- Helper functions and error categories

**Use this for:** All error handling related tasks

### TESTING_GUIDE_2025-10-15.md

Complete testing guide covering:

- Test execution commands and configuration
- Dependency injection testing with Hilt
- Hardware service mocking patterns
- Integration testing strategies
- Best practices and troubleshooting

**Use this for:** All testing related tasks

### MATERIAL3_PATTERNS_GUIDE.md

Material Design 3 UI patterns:

- Reusable component patterns
- Consistent styling approaches
- Visual hierarchy guidelines
- State indication patterns

**Use this for:** Implementing UI components

### WINDOWS_BUILD_GUIDE_2025-10-15_0706.md

Windows-specific build instructions:

- Setup requirements
- Build commands
- Troubleshooting common issues

**Use this for:** Building on Windows

## Integration Reference

### shimmer-integration-notes.txt

Detailed Shimmer SDK integration notes:

- Source layout and architecture
- Android Instrument Driver overview
- API reference and message flow
- Configuration and settings flow
- Data processing details

**Use this for:** Understanding Shimmer SDK internals

### topdon_tc001_integration.txt

TOPDON TC001 thermal camera integration:

- SDK structure and components
- BLE communication patterns
- Device discovery and pairing
- Thermal data processing

**Use this for:** Implementing TOPDON thermal camera support

### topdon_navigation.txt

TOPDON project structure reference:

- Directory layout
- Key source files
- Configuration files
- Build system integration

**Use this for:** Finding TOPDON SDK components

## Related Documentation

### Architecture

- `docs/architecture/ERROR_HANDLING_STRATEGY_2025-10-14.md` - Error handling architecture
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

- Handle errors with Result pattern → ERROR_HANDLING_GUIDE_2025-10-15.md
- Write or run tests → TESTING_GUIDE_2025-10-15.md
- Implement Material 3 UI → MATERIAL3_PATTERNS_GUIDE.md
- Build on Windows → WINDOWS_BUILD_GUIDE_2025-10-15_0706.md
- Integrate Shimmer sensors → shimmer-integration-notes.txt
- Integrate TOPDON camera → topdon_tc001_integration.txt or topdon_navigation.txt

## Recent Changes

**2025-10-15 20:52 UTC:**

- Consolidated 3 error handling guides into ERROR_HANDLING_GUIDE_2025-10-15.md
- Consolidated 3 testing guides into TESTING_GUIDE_2025-10-15.md
- Removed redundant dated files
- Maintained integration reference files
- See `docs/project/DOCUMENTATION_CLEANUP_2025-10-15_2052.md` for details
