<#
.SYNOPSIS
Stops the auto-injection background service.

.DESCRIPTION
Stops and removes the auto-injection service background job.

.EXAMPLE
.\stop_auto_inject.ps1
#>

$ErrorActionPreference = "Continue"

Write-Host "Stopping auto-injection service..." -ForegroundColor Cyan

$job = Get-Job -Name "AutoInjectService" -ErrorAction SilentlyContinue

if (-not $job)
{
    Write-Host "No auto-injection service found running" -ForegroundColor Yellow
    exit 0
}

Write-Host "Found job (ID: $( $job.Id ), State: $( $job.State ))" -ForegroundColor Gray

if ($job.State -eq "Running")
{
    Stop-Job -Name "AutoInjectService" -ErrorAction SilentlyContinue
    Write-Host "Service stopped" -ForegroundColor Green
}

# Show final output
$output = Receive-Job -Name "AutoInjectService" -ErrorAction SilentlyContinue
if ($output)
{
    Write-Host "" -ForegroundColor Gray
    Write-Host "Final output:" -ForegroundColor Cyan
    $output | ForEach-Object { Write-Host $_ -ForegroundColor Gray }
}

Remove-Job -Name "AutoInjectService" -Force -ErrorAction SilentlyContinue
Write-Host "Service removed" -ForegroundColor Green

exit 0
