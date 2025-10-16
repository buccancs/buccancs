<#
.SYNOPSIS
Automatic signal wrapper that should be called at the end of every Copilot response.

.DESCRIPTION
This is the main integration point. Call this script at the end of each response
to automatically trigger continuation with all enhanced features.

.EXAMPLE
& C:\dev\buccancs\auto_signal_wrapper.ps1 -LastResponse $response
#>

param(
    [string]$LastResponse = "",
    [switch]$Force,
    [switch]$NoDocUpdate
)

try
{
    # Call send_continue with response analysis
    $result = & "$PSScriptRoot\send_continue.ps1" -LastResponse $LastResponse

    if ($LASTEXITCODE -eq 0)
    {
        Write-Host "Auto-continuation triggered successfully" -ForegroundColor Green

        # Update documentation if enabled
        if (-not $NoDocUpdate)
        {
            . "$PSScriptRoot\doc_updater.ps1"
            $sessionId = $env:COPILOT_INSTANCE_ID

            if ($sessionId)
            {
                $summary = Generate-SessionSummary -SessionId $sessionId
                Update-DevDiary -SessionId $sessionId -Summary $summary
            }
        }
    }
    elseif ($LASTEXITCODE -eq 1)
    {
        Write-Host "Rate limit reached or task complete" -ForegroundColor Yellow
    }
}
catch
{
    Write-Error "Auto-signal failed: $_"
}

