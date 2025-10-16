<#
.SYNOPSIS
Starts the auto-injection background service.

.DESCRIPTION
Launches the auto-injection service as a background job that monitors for
completion signal and automatically injects continuation prompts.

.EXAMPLE
.\start_auto_inject.ps1

.EXAMPLE
.\start_auto_inject.ps1 -MonitorInterval 1 -Verbose
#>

param(
    [int]$MonitorInterval = 2,
    [string]$LogFile = "C:\dev\buccancs\logs\auto_inject.log"
)

$ErrorActionPreference = "Stop"

$servicePath = Join-Path $PSScriptRoot "auto_inject_service.ps1"

if (-not (Test-Path $servicePath))
{
    Write-Error "Service script not found: $servicePath"
    exit 1
}

# Ensure logs directory exists
$logsDir = Split-Path $LogFile -Parent
if (-not (Test-Path $logsDir))
{
    New-Item -ItemType Directory -Path $logsDir -Force | Out-Null
}

# Check if service is already running
$existingJob = Get-Job -Name "AutoInjectService" -ErrorAction SilentlyContinue
if ($existingJob)
{
    if ($existingJob.State -eq "Running")
    {
        Write-Host "Auto-injection service is already running (Job ID: $( $existingJob.Id ))" -ForegroundColor Yellow
        Write-Host "Use 'Stop-Job -Name AutoInjectService' to stop it" -ForegroundColor Yellow
        exit 0
    }
    else
    {
        Write-Host "Removing previous job..." -ForegroundColor Yellow
        Remove-Job -Name "AutoInjectService" -Force
    }
}

Write-Host "Starting auto-injection background service..." -ForegroundColor Cyan
Write-Host "Monitor interval: $MonitorInterval seconds" -ForegroundColor Gray
Write-Host "Log file: $LogFile" -ForegroundColor Gray

# Start as background job using PowerShell with -STA flag for clipboard support
$job = Start-Job -Name "AutoInjectService" -ScriptBlock {
    param($ServicePath, $Interval, $Log)
    # Run in STA mode for clipboard support
    powershell.exe -STA -NoProfile -ExecutionPolicy Bypass -File $ServicePath -MonitorInterval $Interval -LogFile $Log
} -ArgumentList $servicePath, $MonitorInterval, $LogFile

Write-Host "Service started successfully!" -ForegroundColor Green
Write-Host "Job ID: $( $job.Id )" -ForegroundColor Green
Write-Host "" -ForegroundColor Gray
Write-Host "Management commands:" -ForegroundColor Cyan
Write-Host "  View status:  Get-Job -Name AutoInjectService" -ForegroundColor Gray
Write-Host "  View output:  Receive-Job -Name AutoInjectService -Keep" -ForegroundColor Gray
Write-Host "  Stop service: Stop-Job -Name AutoInjectService" -ForegroundColor Gray
Write-Host "  Remove job:   Remove-Job -Name AutoInjectService -Force" -ForegroundColor Gray
Write-Host "  View logs:    Get-Content '$LogFile' -Tail 20 -Wait" -ForegroundColor Gray

exit 0
