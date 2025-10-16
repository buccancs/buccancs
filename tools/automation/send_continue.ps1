<#
.SYNOPSIS
Signal completion by creating a signal file with session info.

.DESCRIPTION
Called by Copilot at end of each response to signal completion.
Creates a signal file with session ID that the auto-inject service monitors.

.PARAMETER SessionId
The unique session ID for this Copilot instance. If not provided, will be read from
environment variable COPILOT_INSTANCE_ID or generated.

.PARAMETER LastResponse
The last response from Copilot for context analysis.

.EXAMPLE
.\send_continue.ps1 -SessionId "abc123"
#>

param(
    [string]$SessionId,
    [string]$LastResponse = ""
)

. "$PSScriptRoot\session_manager.ps1"
. "$PSScriptRoot\smart_continuation.ps1"
. "$PSScriptRoot\rate_limiter.ps1"

$signalFile = "C:\dev\buccancs\logs\.inject_signal"
$sessionFile = "C:\dev\buccancs\logs\.copilot_session"

# Get or create session ID
if (-not $SessionId)
{
    # Check environment variable first
    $SessionId = $env:COPILOT_INSTANCE_ID

    # Check if we have a stored session
    if (-not $SessionId)
    {
        $existingSession = Get-CopilotSession -SessionId $env:COPILOT_INSTANCE_ID
        if ($existingSession)
        {
            $SessionId = $existingSession.SessionId
        }
    }

    # Generate new session ID if still not found
    if (-not $SessionId)
    {
        $session = New-CopilotSession -ProcessId $PID -Metadata @{
            WorkingDirectory = (Get-Location).Path
        }
        $SessionId = $session.SessionId

        # Set environment variable for this session
        $env:COPILOT_INSTANCE_ID = $SessionId
    }
}

# Check rate limiting
$rateLimitCheck = Test-RateLimitAllowed -SessionId $SessionId
if (-not $rateLimitCheck.Allowed)
{
    Write-Host "Rate limit reached: $( $rateLimitCheck.Reason )" -ForegroundColor Yellow
    Write-Host "Please wait $( $rateLimitCheck.WaitSeconds ) seconds" -ForegroundColor Yellow
    exit 1
}

# Analyze response for completion
$analysis = Analyze-ResponseForCompletion -Response $LastResponse

if (-not $analysis.ShouldContinue)
{
    Write-Host "Task appears complete: $( $analysis.Reason )" -ForegroundColor Green
    Update-CopilotSession -SessionId $SessionId -Updates @{ Status = "Completed" }
    exit 0
}

# Get smart continuation prompt
$smartPrompt = Get-SmartContinuationPrompt -SessionId $SessionId -LastResponse $LastResponse

# Save context
if ($LastResponse)
{
    Save-SessionContext -SessionId $SessionId -Prompt "continuation" -Response $LastResponse
}

# Update session
Update-CopilotSession -SessionId $SessionId -Updates @{
    ContinuationCount = ((Get-CopilotSession -SessionId $SessionId).ContinuationCount + 1)
}

# Register request
Register-ContinuationRequest -SessionId $SessionId

# Create signal file with session ID
$signalContent = @{
    SessionId = $SessionId
    Timestamp = (Get-Date).ToString("o")
    ProcessId = $PID
    SmartPrompt = $smartPrompt
    Analysis = $analysis
}

$signalContent | ConvertTo-Json -Depth 5 | Out-File -FilePath $signalFile -Force -Encoding utf8

Write-Host "Completion signal created: $signalFile" -ForegroundColor Green
Write-Host "Session ID: $SessionId" -ForegroundColor Cyan
Write-Host "Process ID: $PID" -ForegroundColor Gray
Write-Host "Continuation #$( (Get-CopilotSession -SessionId $SessionId).ContinuationCount )" -ForegroundColor Gray
Write-Host "Smart Prompt: $($smartPrompt.Substring(0,[Math]::Min(80, $smartPrompt.Length)) )..." -ForegroundColor Gray


