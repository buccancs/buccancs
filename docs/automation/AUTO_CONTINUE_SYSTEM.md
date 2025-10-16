**Last Modified:** 2025-10-16 00:35 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Guide

# Copilot Auto-Continuation System

Complete automated continuation system for GitHub Copilot CLI with smart prompts, session management, rate limiting, and
progress tracking. Supports both Windows PowerShell and WSL/Linux environments.

## Features

### 1. Session Management

- Unique session IDs for each Copilot instance
- Context preservation across continuations
- Session lifecycle tracking
- Automatic cleanup of orphaned sessions

### 2. Smart Continuation Prompts

- Context-aware prompt generation
- Error detection and focused fixes
- Progress-based adjustments
- Backlog integration

### 3. Rate Limiting & Throttling

- Configurable minimum intervals between requests
- Hourly request limits
- Automatic cooldown enforcement
- Rate limit statistics

### 4. Error Detection & Recovery

- Automatic error pattern detection
- Retry with adjusted prompts
- Error tracking per session
- Completion detection

### 5. Progress Tracking

- Task completion tracking
- Continuation count per session
- Session statistics
- Historical data

### 6. Documentation Integration

- Automatic dev-diary updates
- Backlog synchronization
- Session reports
- Progress summaries

### 7. Monitoring Dashboard

- Real-time session status
- Rate limit monitoring
- Active session display
- Log viewing

### 8. Multi-Session Coordination

- Independent session tracking
- No conflicts between instances
- Session-specific context
- Coordinated launches

## Quick Start

### Windows PowerShell

#### 1. Start the Auto-Injection Service

```powershell
.\start_auto_inject.ps1
```

#### 2. In Your Copilot Session, Signal Completion

At the end of each response, run:

```powershell
.\auto_signal_wrapper.ps1 -LastResponse "your last response text"
```

Or manually:

```powershell
.\send_continue.ps1
```

#### 3. Monitor Progress

```powershell
.\monitor_dashboard.ps1 -Continuous
```

### WSL/Linux

#### 1. Start the Auto-Injection Service

```bash
./start_system.sh
```

#### 2. In Your Copilot Session, Signal Completion

At the end of each response, run:

```bash
./send_continue.sh --last-response "your last response text"
```

#### 3. Monitor Progress

```bash
./monitor_dashboard.sh --continuous
```

To stop the service:

```bash
./stop_system.sh
```

## Architecture

### Core Components

**session_manager.ps1**

- Session database management
- Context preservation
- Session lifecycle

**smart_continuation.ps1**

- Intelligent prompt generation
- Response analysis
- Backlog integration

**rate_limiter.ps1**

- Request throttling
- Rate limit enforcement
- Statistics tracking

**auto_inject_service.ps1**

- Background monitoring
- Signal detection
- Copilot instance launching

**doc_updater.ps1**

- Dev-diary updates
- Backlog synchronization
- Session reporting

**monitor_dashboard.ps1**

- Real-time monitoring
- Statistics display
- Service status

### Data Flow

```
Copilot Response
    |
    v
auto_signal_wrapper.ps1
    |
    v
send_continue.ps1
    |
    +-> Session Management (session_manager.ps1)
    +-> Smart Prompt Generation (smart_continuation.ps1)
    +-> Rate Limiting Check (rate_limiter.ps1)
    +-> Create Signal File
    |
    v
auto_inject_service.ps1 (monitors signal file)
    |
    v
Launch New Copilot Instance
    |
    v
New Session with Context
```

## Configuration

### Rate Limiting

Edit rate limits:

```powershell
. .\rate_limiter.ps1
Set-RateLimitConfig -MinIntervalSeconds 30 -MaxRequestsPerHour 60
```

### Smart Prompts

Enable/disable smart prompts in auto_inject_service.ps1:

```powershell
.\start_auto_inject.ps1  # Smart prompts enabled by default
```

### Session Cleanup

Automatic cleanup runs on service start. Manual cleanup:

```powershell
. .\session_manager.ps1
Remove-OrphanedSessions -OlderThanHours 24
```

## Commands Reference

### Session Management

```powershell
# Create new session
. .\session_manager.ps1
$session = New-CopilotSession -ProcessId $PID

# Update session
Update-CopilotSession -SessionId $session.SessionId -Updates @{ Status = "Active" }

# Get session info
Get-CopilotSession -SessionId $session.SessionId

# List active sessions
Get-ActiveSessions

# Get statistics
Get-SessionStatistics
```

### Rate Limiting

```powershell
# Check if allowed
. .\rate_limiter.ps1
$check = Test-RateLimitAllowed -SessionId $sessionId

# Register request
Register-ContinuationRequest -SessionId $sessionId

# Get stats
Get-RateLimitStatistics
```

### Documentation

```powershell
# Update dev diary
. .\doc_updater.ps1
Update-DevDiary -SessionId $sessionId -Summary "Completed tasks" -TasksCompleted @("Task 1", "Task 2")

# Update backlog
Update-Backlog -TasksCompleted @("Task 1") -NewTasks @("New Task")

# Export report
Export-SessionReport -SessionId $sessionId
```

### Monitoring

```powershell
# One-time view
.\monitor_dashboard.ps1

# Continuous monitoring
.\monitor_dashboard.ps1 -Continuous -RefreshInterval 5

# Stop monitoring: Ctrl+C
```

## File Locations

### Configuration & Data

- `logs/sessions.json` - Session database
- `logs/context/` - Session context files
- `logs/rate_limits.json` - Rate limiting data
- `logs/.inject_signal` - Current signal file
- `logs/auto_inject.log` - Service logs

### Reports

- `logs/session_reports/` - Generated session reports

### Documentation

- `docs/project/dev-diary.md` - Development diary
- `docs/project/BACKLOG.md` - Project backlog

## Troubleshooting

### Service Not Starting

Check if already running:

```powershell
Get-Job -Name AutoInjectService
```

Stop existing:

```powershell
.\stop_auto_inject.ps1
```

### Rate Limit Issues

Check current limits:

```powershell
. .\rate_limiter.ps1
Get-RateLimitStatistics
```

Adjust limits if needed.

### Session Not Found

Verify session exists:

```powershell
. .\session_manager.ps1
Get-ActiveSessions
```

Create new session if needed.

### Smart Prompts Not Working

Disable temporarily:

```powershell
.\send_continue.ps1 -LastResponse "" # Uses default prompt
```

## Best Practices

1. **Always Monitor**: Keep dashboard running to track progress
2. **Regular Cleanup**: Run session cleanup weekly
3. **Review Logs**: Check logs for errors and patterns
4. **Adjust Rates**: Tune rate limits based on API quotas
5. **Document Progress**: Let system update docs automatically
6. **Session Reports**: Export reports for important sessions
7. **Backlog Sync**: Keep backlog updated with completions

## Integration with Existing Workflow

### Automatic Signaling

Add to end of Copilot responses:

```powershell
& C:\dev\buccancs\auto_signal_wrapper.ps1 -LastResponse $response
```

### Manual Control

For manual control, use:

```powershell
.\send_continue.ps1 -LastResponse "response text"
```

### Disable Auto-Continuation

Stop the service:

```powershell
.\stop_auto_inject.ps1
```

## Advanced Usage

### Custom Prompts

Override smart prompt:

```powershell
.\send_continue.ps1 -LastResponse "text"
# Edit signal file before service picks it up
```

### Multi-Instance Coordination

Each instance gets unique session ID automatically.
Monitor all with dashboard.

### Context Preservation

Context saved automatically. Access with:

```powershell
. .\session_manager.ps1
$context = Get-SessionContext -SessionId $sessionId
$context.ConversationHistory
```

## Performance Metrics

Track via dashboard or commands:

- Active sessions count
- Continuations per session
- Tasks completed
- Error rates
- Rate limit utilization

## Security Considerations

- Session data stored locally
- No external network calls except Copilot CLI
- Log files may contain sensitive info
- Clean up old sessions regularly

## Future Enhancements

- Web UI dashboard
- Metrics visualization
- Machine learning for prompt optimization
- Team collaboration features
- Cloud sync option

## Support

Check logs first:

```powershell
Get-Content C:\dev\buccancs\logs\auto_inject.log -Tail 50
```

View dashboard for status:

```powershell
.\monitor_dashboard.ps1
```
