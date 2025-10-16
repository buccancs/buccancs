<#
.SYNOPSIS
Centralized session management for Copilot auto-continuation system.

.DESCRIPTION
Manages session lifecycle, context preservation, and coordination between instances.
#>

$script:SessionDbPath = "C:\dev\buccancs\logs\sessions.json"
$script:ContextPath = "C:\dev\buccancs\logs\context"

function Initialize-SessionDatabase
{
    if (-not (Test-Path $script:SessionDbPath))
    {
        @{
            Sessions = @{ }
            LastCleanup = (Get-Date).ToString("o")
        } | ConvertTo-Json | Out-File -FilePath $script:SessionDbPath -Encoding utf8
    }

    $contextDir = Split-Path $script:ContextPath -Parent
    if (-not (Test-Path $contextDir))
    {
        New-Item -ItemType Directory -Path $contextDir -Force | Out-Null
    }

    if (-not (Test-Path $script:ContextPath))
    {
        New-Item -ItemType Directory -Path $script:ContextPath -Force | Out-Null
    }
}

function Get-SessionDatabase
{
    Initialize-SessionDatabase
    return Get-Content $script:SessionDbPath -Raw | ConvertFrom-Json
}

function Save-SessionDatabase
{
    param([object]$Database)
    $Database | ConvertTo-Json -Depth 10 | Out-File -FilePath $script:SessionDbPath -Encoding utf8 -Force
}

function New-CopilotSession
{
    param(
        [string]$ProcessId = $PID,
        [hashtable]$Metadata = @{ }
    )

    $db = Get-SessionDatabase

    $sessionId = "copilot_" + [guid]::NewGuid().ToString().Substring(0, 8)

    $session = @{
        SessionId = $sessionId
        ProcessId = $ProcessId
        StartTime = (Get-Date).ToString("o")
        LastActivity = (Get-Date).ToString("o")
        Status = "Active"
        ContinuationCount = 0
        TasksCompleted = @()
        ErrorCount = 0
        Metadata = $Metadata
        ContextFile = "$script:ContextPath\$sessionId.json"
    }

    $db.Sessions.$sessionId = $session
    Save-SessionDatabase $db

    # Create context file
    @{
        SessionId = $sessionId
        ConversationHistory = @()
        WorkingDirectory = (Get-Location).Path
        Environment = @{
            COPILOT_INSTANCE_ID = $sessionId
        }
    } | ConvertTo-Json -Depth 10 | Out-File -FilePath $session.ContextFile -Encoding utf8

    return $session
}

function Update-CopilotSession
{
    param(
        [string]$SessionId,
        [hashtable]$Updates
    )

    $db = Get-SessionDatabase

    if ($db.Sessions.$SessionId)
    {
        $db.Sessions.$SessionId.LastActivity = (Get-Date).ToString("o")

        foreach ($key in $Updates.Keys)
        {
            $db.Sessions.$SessionId.$key = $Updates[$key]
        }

        Save-SessionDatabase $db
        return $true
    }

    return $false
}

function Get-CopilotSession
{
    param([string]$SessionId)

    $db = Get-SessionDatabase
    return $db.Sessions.$SessionId
}

function Get-ActiveSessions
{
    $db = Get-SessionDatabase
    $activeSessions = @()

    foreach ($sessionId in $db.Sessions.Keys)
    {
        $session = $db.Sessions.$sessionId
        if ($session.Status -eq "Active")
        {
            # Check if process still exists
            $proc = Get-Process -Id $session.ProcessId -ErrorAction SilentlyContinue
            if ($proc)
            {
                $activeSessions += $session
            }
            else
            {
                # Mark as completed if process died
                Update-CopilotSession -SessionId $sessionId -Updates @{ Status = "Completed" }
            }
        }
    }

    return $activeSessions
}

function Save-SessionContext
{
    param(
        [string]$SessionId,
        [string]$Prompt,
        [string]$Response,
        [hashtable]$Metadata = @{ }
    )

    $session = Get-CopilotSession -SessionId $SessionId
    if (-not $session)
    {
        return $false
    }

    $contextFile = $session.ContextFile
    if (-not (Test-Path $contextFile))
    {
        return $false
    }

    $context = Get-Content $contextFile -Raw | ConvertFrom-Json

    $entry = @{
        Timestamp = (Get-Date).ToString("o")
        Prompt = $Prompt
        Response = $Response
        Metadata = $Metadata
    }

    $context.ConversationHistory += $entry

    $context | ConvertTo-Json -Depth 10 | Out-File -FilePath $contextFile -Encoding utf8 -Force

    return $true
}

function Get-SessionContext
{
    param([string]$SessionId)

    $session = Get-CopilotSession -SessionId $SessionId
    if (-not $session)
    {
        return $null
    }

    $contextFile = $session.ContextFile
    if (-not (Test-Path $contextFile))
    {
        return $null
    }

    return Get-Content $contextFile -Raw | ConvertFrom-Json
}

function Remove-OrphanedSessions
{
    param([int]$OlderThanHours = 24)

    $db = Get-SessionDatabase
    $cutoffTime = (Get-Date).AddHours(-$OlderThanHours)
    $removed = 0

    $sessionIds = @($db.Sessions.Keys)
    foreach ($sessionId in $sessionIds)
    {
        $session = $db.Sessions.$sessionId
        $lastActivity = [DateTime]::Parse($session.LastActivity)

        if ($lastActivity -lt $cutoffTime)
        {
            # Check if process exists
            $proc = Get-Process -Id $session.ProcessId -ErrorAction SilentlyContinue
            if (-not $proc)
            {
                # Remove session and context
                if (Test-Path $session.ContextFile)
                {
                    Remove-Item $session.ContextFile -Force
                }
                $db.Sessions.Remove($sessionId)
                $removed++
            }
        }
    }

    if ($removed -gt 0)
    {
        $db.LastCleanup = (Get-Date).ToString("o")
        Save-SessionDatabase $db
    }

    return $removed
}

function Get-SessionStatistics
{
    $db = Get-SessionDatabase

    $stats = @{
        TotalSessions = $db.Sessions.Count
        ActiveSessions = 0
        CompletedSessions = 0
        TotalContinuations = 0
        TotalTasksCompleted = 0
        TotalErrors = 0
    }

    foreach ($sessionId in $db.Sessions.Keys)
    {
        $session = $db.Sessions.$sessionId

        if ($session.Status -eq "Active")
        {
            $stats.ActiveSessions++
        }
        elseif ($session.Status -eq "Completed")
        {
            $stats.CompletedSessions++
        }

        $stats.TotalContinuations += $session.ContinuationCount
        $stats.TotalTasksCompleted += $session.TasksCompleted.Count
        $stats.TotalErrors += $session.ErrorCount
    }

    return $stats
}

Export-ModuleMember -Function @(
    'Initialize-SessionDatabase',
    'New-CopilotSession',
    'Update-CopilotSession',
    'Get-CopilotSession',
    'Get-ActiveSessions',
    'Save-SessionContext',
    'Get-SessionContext',
    'Remove-OrphanedSessions',
    'Get-SessionStatistics'
)
