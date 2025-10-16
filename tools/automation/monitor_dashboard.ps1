<#
.SYNOPSIS
Monitoring dashboard for Copilot auto-continuation system.

.DESCRIPTION
Displays real-time status of active sessions, statistics, and logs.
#>

param(
    [int]$RefreshInterval = 5,
    [switch]$Continuous
)

. "$PSScriptRoot\session_manager.ps1"
. "$PSScriptRoot\rate_limiter.ps1"

function Show-Dashboard
{
    Clear-Host

    $timestamp = Get-Date -Format "yyyy-MM-dd HH:mm:ss"

    Write-Host "=" * 80 -ForegroundColor Cyan
    Write-Host "  COPILOT AUTO-CONTINUATION MONITORING DASHBOARD" -ForegroundColor Green
    Write-Host "  Last Update: $timestamp" -ForegroundColor Gray
    Write-Host "=" * 80 -ForegroundColor Cyan
    Write-Host ""

    # Session Statistics
    Write-Host "SESSION STATISTICS" -ForegroundColor Yellow
    Write-Host "-" * 80 -ForegroundColor Gray

    $stats = Get-SessionStatistics
    Write-Host "  Total Sessions:       $( $stats.TotalSessions )" -ForegroundColor White
    Write-Host "  Active Sessions:      $( $stats.ActiveSessions )" -ForegroundColor Green
    Write-Host "  Completed Sessions:   $( $stats.CompletedSessions )" -ForegroundColor Gray
    Write-Host "  Total Continuations:  $( $stats.TotalContinuations )" -ForegroundColor Cyan
    Write-Host "  Tasks Completed:      $( $stats.TotalTasksCompleted )" -ForegroundColor Green
    Write-Host "  Total Errors:         $( $stats.TotalErrors )" -ForegroundColor Red
    Write-Host ""

    # Rate Limit Status
    Write-Host "RATE LIMIT STATUS" -ForegroundColor Yellow
    Write-Host "-" * 80 -ForegroundColor Gray

    $rateLimits = Get-RateLimitStatistics
    $rateLimitColor = if ($rateLimits.RequestsLastHour -lt $rateLimits.MaxRequestsPerHour * 0.8)
    {
        "Green"
    }
    else
    {
        "Yellow"
    }

    Write-Host "  Requests Last Hour:   $( $rateLimits.RequestsLastHour ) / $( $rateLimits.MaxRequestsPerHour )" -ForegroundColor $rateLimitColor
    Write-Host "  Min Interval:         $( $rateLimits.MinIntervalSeconds ) seconds" -ForegroundColor White
    Write-Host "  Last Request:         $( $rateLimits.LastRequest )" -ForegroundColor Gray
    Write-Host ""

    # Active Sessions
    Write-Host "ACTIVE SESSIONS" -ForegroundColor Yellow
    Write-Host "-" * 80 -ForegroundColor Gray

    $activeSessions = Get-ActiveSessions
    if ($activeSessions.Count -eq 0)
    {
        Write-Host "  No active sessions" -ForegroundColor Gray
    }
    else
    {
        foreach ($session in $activeSessions)
        {
            $duration = ((Get-Date) - [DateTime]::Parse($session.StartTime)).ToString("hh\:mm\:ss")
            Write-Host "  [$( $session.SessionId )]" -ForegroundColor Cyan
            Write-Host "    PID:              $( $session.ProcessId )" -ForegroundColor Gray
            Write-Host "    Duration:         $duration" -ForegroundColor Gray
            Write-Host "    Continuations:    $( $session.ContinuationCount )" -ForegroundColor White
            Write-Host "    Last Activity:    $( $session.LastActivity )" -ForegroundColor Gray
            Write-Host ""
        }
    }

    # Service Status
    Write-Host "SERVICE STATUS" -ForegroundColor Yellow
    Write-Host "-" * 80 -ForegroundColor Gray

    $job = Get-Job -Name "AutoInjectService" -ErrorAction SilentlyContinue
    if ($job)
    {
        $statusColor = if ($job.State -eq "Running")
        {
            "Green"
        }
        else
        {
            "Red"
        }
        Write-Host "  Auto-Inject Service:  $( $job.State )" -ForegroundColor $statusColor
        Write-Host "  Job ID:               $( $job.Id )" -ForegroundColor Gray
    }
    else
    {
        Write-Host "  Auto-Inject Service:  Not Running" -ForegroundColor Red
    }
    Write-Host ""

    # Recent Log Entries
    Write-Host "RECENT LOG ENTRIES (Last 5)" -ForegroundColor Yellow
    Write-Host "-" * 80 -ForegroundColor Gray

    $logFile = "C:\dev\buccancs\logs\auto_inject.log"
    if (Test-Path $logFile)
    {
        $recentLogs = Get-Content $logFile -Tail 5
        foreach ($log in $recentLogs)
        {
            $logColor = if ($log -match "ERROR")
            {
                "Red"
            }
            elseif ($log -match "WARN")
            {
                "Yellow"
            }
            elseif ($log -match "SUCCESS")
            {
                "Green"
            }
            else
            {
                "Gray"
            }
            Write-Host "  $log" -ForegroundColor $logColor
        }
    }
    else
    {
        Write-Host "  No log file found" -ForegroundColor Gray
    }

    Write-Host ""
    Write-Host "=" * 80 -ForegroundColor Cyan

    if ($Continuous)
    {
        Write-Host "Press Ctrl+C to exit. Refreshing in $RefreshInterval seconds..." -ForegroundColor Gray
    }
}

# Main loop
if ($Continuous)
{
    while ($true)
    {
        Show-Dashboard
        Start-Sleep -Seconds $RefreshInterval
    }
}
else
{
    Show-Dashboard
}
