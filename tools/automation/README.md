# Copilot Auto-Continuation System

Complete automated continuation system for GitHub Copilot CLI.

## Quick Start

```powershell
# Start the complete system
.\start_system.ps1

# Monitor in another window
.\monitor_dashboard.ps1 -Continuous

# Signal completion (call at end of each response)
.\auto_signal_wrapper.ps1 -LastResponse "your response"
```

## Scripts

### Core System

- `start_system.ps1` - Initialize and start all components
- `stop_auto_inject.ps1` - Stop the background service
- `monitor_dashboard.ps1` - Real-time monitoring dashboard

### Automation Components

- `auto_inject_service.ps1` - Background monitoring service
- `send_continue.ps1` - Signal handler with smart prompts
- `auto_signal_wrapper.ps1` - Main integration wrapper

### Intelligence

- `session_manager.ps1` - Session lifecycle and context
- `smart_continuation.ps1` - Context-aware prompt generation
- `rate_limiter.ps1` - Request throttling

### Documentation

- `doc_updater.ps1` - Automatic doc updates

## Features

- Session management with context preservation
- Smart continuation prompts
- Rate limiting and throttling
- Error detection and recovery
- Progress tracking
- Documentation integration
- Real-time monitoring
- Multi-session coordination

## Documentation

See `docs/automation/AUTO_CONTINUE_SYSTEM.md` for complete guide.
