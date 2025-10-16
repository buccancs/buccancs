<#
.SYNOPSIS
Automatic documentation updater for dev-diary and backlog.

.DESCRIPTION
Updates project documentation based on session activity and completions.
#>

. "$PSScriptRoot\session_manager.ps1"

function Update-DevDiary
{
    param(
        [string]$SessionId,
        [string]$Summary,
        [string[]]$TasksCompleted = @(),
        [string[]]$Issues = @()
    )

    $diaryPath = "C:\dev\buccancs\docs\project\dev-diary.md"

    if (-not (Test-Path $diaryPath))
    {
        Write-Warning "Dev diary not found: $diaryPath"
        return $false
    }

    $timestamp = Get-Date -Format "yyyy-MM-dd HH:mm"

    $entry = @"

### $timestamp - Session $SessionId

    $Summary

"@

    if ($TasksCompleted.Count -gt 0)
    {
        $entry += "`n**Completed:**`n"
        foreach ($task in $TasksCompleted)
        {
            $entry += "- $task`n"
        }
    }

    if ($Issues.Count -gt 0)
    {
        $entry += "`n**Issues:**`n"
        foreach ($issue in $Issues)
        {
            $entry += "- $issue`n"
        }
    }

    Add-Content -Path $diaryPath -Value $entry -Encoding UTF8

    return $true
}

function Update-Backlog
{
    param(
        [string[]]$TasksCompleted = @(),
        [string[]]$NewTasks = @()
    )

    $backlogPath = "C:\dev\buccancs\docs\project\BACKLOG.md"

    if (-not (Test-Path $backlogPath))
    {
        Write-Warning "Backlog not found: $backlogPath"
        return $false
    }

    $backlog = Get-Content $backlogPath -Raw

    # Mark completed tasks as [DONE]
    foreach ($task in $TasksCompleted)
    {
        $backlog = $backlog -replace "\[TODO\]\s+$task", "[DONE] $task - $( Get-Date -Format 'yyyy-MM-dd' )"
        $backlog = $backlog -replace "\[IN PROGRESS\]\s+$task", "[DONE] $task - $( Get-Date -Format 'yyyy-MM-dd' )"
    }

    # Add new tasks
    if ($NewTasks.Count -gt 0)
    {
        $newTasksSection = "`n## New Tasks - $( Get-Date -Format 'yyyy-MM-dd' )`n`n"
        foreach ($task in $NewTasks)
        {
            $newTasksSection += "[TODO] $task`n"
        }
        $backlog += $newTasksSection
    }

    $backlog | Out-File -FilePath $backlogPath -Encoding UTF8 -Force

    return $true
}

function Generate-SessionSummary
{
    param([string]$SessionId)

    $session = Get-CopilotSession -SessionId $SessionId
    if (-not $session)
    {
        return "No session data available"
    }

    $context = Get-SessionContext -SessionId $SessionId

    $summary = "Automated continuation session completed.`n"
    $summary += "- Continuations: $( $session.ContinuationCount )`n"
    $summary += "- Tasks completed: $( $session.TasksCompleted.Count )`n"

    if ($context -and $context.ConversationHistory.Count -gt 0)
    {
        $summary += "- Conversation turns: $( $context.ConversationHistory.Count )`n"
    }

    return $summary
}

function Export-SessionReport
{
    param(
        [string]$SessionId,
        [string]$OutputPath = "C:\dev\buccancs\logs\session_reports"
    )

    if (-not (Test-Path $OutputPath))
    {
        New-Item -ItemType Directory -Path $OutputPath -Force | Out-Null
    }

    $session = Get-CopilotSession -SessionId $SessionId
    if (-not $session)
    {
        return $null
    }

    $context = Get-SessionContext -SessionId $SessionId

    $reportFile = Join-Path $OutputPath "$SessionId-$( Get-Date -Format 'yyyyMMdd-HHmmss' ).md"

    $report = @"
# Session Report: $SessionId

**Generated:** $( Get-Date -Format "yyyy-MM-dd HH:mm:ss" )

## Session Information

- **Start Time:** $( $session.StartTime )
- **Last Activity:** $( $session.LastActivity )
- **Status:** $( $session.Status )
- **Process ID:** $( $session.ProcessId )
- **Continuation Count:** $( $session.ContinuationCount )
- **Tasks Completed:** $( $session.TasksCompleted.Count )
- **Errors:** $( $session.ErrorCount )

## Tasks Completed

"@

    if ($session.TasksCompleted.Count -gt 0)
    {
        foreach ($task in $session.TasksCompleted)
        {
            $report += "- $task`n"
        }
    }
    else
    {
        $report += "None`n"
    }

    $report += "`n## Conversation History`n`n"

    if ($context -and $context.ConversationHistory.Count -gt 0)
    {
        foreach ($turn in $context.ConversationHistory)
        {
            $report += "### $(([DateTime]::Parse($turn.Timestamp)).ToString('yyyy-MM-dd HH:mm:ss') )`n`n"
            $report += "**Prompt:** $( $turn.Prompt )`n`n"
            $report += "**Response:** $($turn.Response.Substring(0,[Math]::Min(500, $turn.Response.Length)) )...`n`n"
        }
    }

    $report | Out-File -FilePath $reportFile -Encoding UTF8 -Force

    return $reportFile
}

Export-ModuleMember -Function @(
    'Update-DevDiary',
    'Update-Backlog',
    'Generate-SessionSummary',
    'Export-SessionReport'
)
