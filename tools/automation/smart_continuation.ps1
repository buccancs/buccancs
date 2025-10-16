<#
.SYNOPSIS
Smart continuation prompt generator based on context and progress.

.DESCRIPTION
Analyzes recent responses and progress to generate contextual continuation prompts.
#>

. "$PSScriptRoot\session_manager.ps1"

function Get-SmartContinuationPrompt
{
    param(
        [string]$SessionId,
        [string]$LastResponse = "",
        [hashtable]$Options = @{ }
    )

    # Default prompt
    $basePrompt = "continue with the next steps. also fix any remaining issues or errors. re-check your work and make sure to use best kotlin jetpack compose material 3 hilt clean architecture best practices. implement any missing tests, remove outdated comments, lines or files, and update documentation"

    # Get session context
    $context = Get-SessionContext -SessionId $SessionId
    if (-not $context)
    {
        return $basePrompt
    }

    # Analyze recent conversation
    $recentHistory = $context.ConversationHistory | Select-Object -Last 3

    # Detect patterns
    $hasErrors = $LastResponse -match "error|exception|failed|cannot"
    $hasWarnings = $LastResponse -match "warning|deprecated|outdated"
    $hasTests = $LastResponse -match "test|spec|junit"
    $hasDocumentation = $LastResponse -match "documentation|readme|comment"
    $hasBuildIssues = $LastResponse -match "build|compile|gradle"

    # Build contextual prompt
    $promptParts = @("continue with the next steps")

    if ($hasErrors)
    {
        $promptParts += "focus on fixing the errors mentioned in the previous response"
    }

    if ($hasWarnings)
    {
        $promptParts += "address any warnings"
    }

    if ($hasBuildIssues)
    {
        $promptParts += "ensure the project builds successfully"
    }

    if (-not $hasTests)
    {
        $promptParts += "implement missing tests"
    }

    if (-not $hasDocumentation)
    {
        $promptParts += "update documentation"
    }

    # Add standard requirements
    $promptParts += "follow kotlin jetpack compose material 3 hilt clean architecture best practices"
    $promptParts += "remove outdated code and comments"

    # Check continuation count
    $session = Get-CopilotSession -SessionId $SessionId
    if ($session -and $session.ContinuationCount -gt 5)
    {
        $promptParts += "summarize progress made so far"
    }

    return ($promptParts -join ". ") + "."
}

function Analyze-ResponseForCompletion
{
    param([string]$Response)

    # Detect if response indicates completion
    $completionIndicators = @(
        "complete",
        "finished",
        "done",
        "all set",
        "successfully",
        "ready"
    )

    $errorIndicators = @(
        "error",
        "failed",
        "exception",
        "cannot",
        "unable"
    )

    $score = @{
        CompletionScore = 0
        ErrorScore = 0
        ShouldContinue = $true
        Reason = ""
    }

    foreach ($indicator in $completionIndicators)
    {
        if ($Response -match $indicator)
        {
            $score.CompletionScore++
        }
    }

    foreach ($indicator in $errorIndicators)
    {
        if ($Response -match $indicator)
        {
            $score.ErrorScore++
        }
    }

    # Decide if should continue
    if ($score.ErrorScore -gt 2)
    {
        $score.ShouldContinue = $true
        $score.Reason = "Multiple errors detected, needs fixing"
    }
    elseif ($score.CompletionScore -gt 3 -and $score.ErrorScore -eq 0)
    {
        $score.ShouldContinue = $false
        $score.Reason = "Task appears complete with no errors"
    }

    return $score
}

function Get-NextTaskFromBacklog
{
    param([string]$BacklogPath = "C:\dev\buccancs\docs\project\BACKLOG.md")

    if (-not (Test-Path $BacklogPath))
    {
        return $null
    }

    $backlog = Get-Content $BacklogPath -Raw

    # Find first TODO or IN PROGRESS item
    if ($backlog -match '\[TODO\](.*?)(?=\[|$)')
    {
        return $matches[1].Trim()
    }

    if ($backlog -match '\[IN PROGRESS\](.*?)(?=\[|$)')
    {
        return $matches[1].Trim()
    }

    return $null
}

Export-ModuleMember -Function @(
    'Get-SmartContinuationPrompt',
    'Analyze-ResponseForCompletion',
    'Get-NextTaskFromBacklog'
)
