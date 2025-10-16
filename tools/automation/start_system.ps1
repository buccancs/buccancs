<#
.SYNOPSIS
Start the complete Copilot auto-continuation system.

.DESCRIPTION
Initializes all components and starts the monitoring service.
#>

Write-Host "=" * 80 -ForegroundColor Cyan
Write-Host "  COPILOT AUTO-CONTINUATION SYSTEM - STARTUP" -ForegroundColor Green
Write-Host "=" * 80 -ForegroundColor Cyan
Write-Host ""

# Initialize databases
Write-Host "Initializing session database..." -ForegroundColor Cyan
. "$PSScriptRoot\session_manager.ps1"
Initialize-SessionDatabase
Write-Host "Session database ready" -ForegroundColor Green

# Cleanup old sessions
Write-Host "Cleaning up old sessions..." -ForegroundColor Cyan
$removed = Remove-OrphanedSessions -OlderThanHours 24
Write-Host "Removed $removed orphaned session(s)" -ForegroundColor Green

# Start auto-inject service
Write-Host "Starting auto-injection service..." -ForegroundColor Cyan
& "$PSScriptRoot\start_auto_inject.ps1"

Write-Host ""
Write-Host "=" * 80 -ForegroundColor Cyan
Write-Host "  SYSTEM READY" -ForegroundColor Green
Write-Host "=" * 80 -ForegroundColor Cyan
Write-Host ""

Write-Host "Available commands:" -ForegroundColor Yellow
Write-Host "  Monitor:   .\monitor_dashboard.ps1 -Continuous" -ForegroundColor White
Write-Host "  Signal:    .\auto_signal_wrapper.ps1 -LastResponse `"text`"" -ForegroundColor White
Write-Host "  Stop:      .\stop_auto_inject.ps1" -ForegroundColor White
Write-Host ""

Write-Host "Documentation: AUTO_CONTINUE_SYSTEM.md" -ForegroundColor Gray

