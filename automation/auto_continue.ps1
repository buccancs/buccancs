[CmdletBinding()]
param(
    [string]$AgentExecutable = "copilot-cli",
[string[]]$AgentArgumentsTemplate = @(
    "chat",
    "--allow-all-paths",
    "--allow-all-tools",
    "--show-usage",
    "--prompt",
    "{Prompt}"
),
    [string]$DefaultPrompt = "continue",
    [string]$WorkingDirectory = (Split-Path -Path $PSScriptRoot -Parent),
    [string]$LogDirectory = (Join-Path -Path $PSScriptRoot -ChildPath "logs"),
    [string]$PromptQueueDirectory = (Join-Path -Path $PSScriptRoot -ChildPath "queue"),
    [string]$PauseFile = (Join-Path -Path $PSScriptRoot -ChildPath "pause.txt"),
    [int]$DelaySeconds = 2,
    [string[]]$StopPatterns = @("\[WAITING\]", "\[NEEDS_APPROVAL\]"),
    [int]$MaxIterations = 0
)

$templatePromptPath = Join-Path -Path $PSScriptRoot -ChildPath "templates\default-prompt.txt"
if (Test-Path -Path $templatePromptPath -PathType Leaf) {
    $templatePrompt = (Get-Content -Path $templatePromptPath -Raw).Trim()
    if ($templatePrompt) {
        $DefaultPrompt = $templatePrompt
    }
}

function New-DirectoryIfMissing {
    param([string]$Path)
    if (-not (Test-Path -Path $Path -PathType Container)) {
        New-Item -ItemType Directory -Path $Path | Out-Null
    }
}

New-DirectoryIfMissing -Path $LogDirectory
New-DirectoryIfMissing -Path $PromptQueueDirectory

$logFile = Join-Path -Path $LogDirectory -ChildPath ("session-{0:yyyyMMdd-HHmmss}.log" -f (Get-Date))
$iteration = 0

function Write-Log {
    param([string]$Message)
    $timestamp = (Get-Date).ToString("u")
    Add-Content -Path $logFile -Value ("{0} :: {1}" -f $timestamp, $Message)
}

function Get-NextPrompt {
    param([ref]$Source)
    $Source.Value = "default"

    if (Test-Path -Path $PromptQueueDirectory -PathType Container) {
        $queuedItem = Get-ChildItem -Path $PromptQueueDirectory -File -ErrorAction SilentlyContinue |
            Sort-Object LastWriteTime |
            Select-Object -First 1

        if ($null -ne $queuedItem) {
            $Source.Value = $queuedItem.Name
            $content = Get-Content -Path $queuedItem.FullName -Raw
            Remove-Item -Path $queuedItem.FullName -Force
            if ($content) {
                return $content.TrimEnd("`r", "`n")
            }
        }
    }

    return $DefaultPrompt
}

function Invoke-AgentPrompt {
    param([string]$PromptText)

    function ConvertTo-CommandLine {
        param([string[]]$Arguments)
        $builder = New-Object System.Text.StringBuilder
        foreach ($arg in $Arguments) {
            if ($builder.Length -gt 0) { [void]$builder.Append(' ') }
            if ($arg -match '[\s"`^]') {
                $escaped = $arg.Replace('"', '\"')
                [void]$builder.Append('"').Append($escaped).Append('"')
            } else {
                [void]$builder.Append($arg)
            }
        }
        return $builder.ToString()
    }

    $argumentList = foreach ($item in $AgentArgumentsTemplate) {
        if ($item -eq "{Prompt}") {
            $PromptText
        } else {
            $item.Replace("{Prompt}", $PromptText)
        }
    }

    $psi = New-Object System.Diagnostics.ProcessStartInfo
    $psi.FileName = $AgentExecutable
    $psi.WorkingDirectory = $WorkingDirectory
    $psi.RedirectStandardOutput = $true
    $psi.RedirectStandardError = $true
    $psi.UseShellExecute = $false
    $psi.CreateNoWindow = $true
    $psi.StandardOutputEncoding = [System.Text.Encoding]::UTF8
    $psi.StandardErrorEncoding = [System.Text.Encoding]::UTF8
    $psi.Arguments = ConvertTo-CommandLine -Arguments $argumentList

    $process = New-Object System.Diagnostics.Process
    $process.StartInfo = $psi

    [void]$process.Start()
    $standardOutput = $process.StandardOutput.ReadToEnd()
    $standardError = $process.StandardError.ReadToEnd()
    $process.WaitForExit()

    Write-Log ("prompt => {0}" -f $PromptText)
    if ($standardOutput) { Write-Log ($standardOutput.TrimEnd()) }
    if ($standardError) { Write-Log ("stderr :: {0}" -f $standardError.TrimEnd()) }

    return [pscustomobject]@{
        ExitCode = $process.ExitCode
        Output   = $standardOutput
        Error    = $standardError
    }
}

Write-Log "automation started"

while ($true) {
    if (Test-Path -Path $PauseFile) {
        Write-Log "pause file detected; stopping loop"
        Write-Host "Pause file found at $PauseFile. Halting automation."
        break
    }

    if ($MaxIterations -gt 0 -and $iteration -ge $MaxIterations) {
        Write-Log "max iterations reached ($MaxIterations); stopping loop"
        break
    }

    $sourceRef = [ref]""
    $prompt = Get-NextPrompt -Source $sourceRef
    $iteration++

    Write-Host ("[{0:HH:mm:ss}] iteration {1} from {2}" -f (Get-Date), $iteration, $sourceRef.Value)
    $result = Invoke-AgentPrompt -PromptText $prompt

    if ($result.ExitCode -ne 0) {
        Write-Log ("agent exited with code {0}; stopping loop" -f $result.ExitCode)
        Write-Host ("Agent exited with code {0}. Halting automation." -f $result.ExitCode)
        break
    }

    $shouldContinue = $true
    foreach ($pattern in $StopPatterns) {
        if ($result.Output -match $pattern -or $result.Error -match $pattern) {
            Write-Log ("stop pattern detected ({0}); halting automation" -f $pattern)
            $shouldContinue = $false
            break
        }
    }

    if (-not $shouldContinue) {
        Write-Host "Stop pattern detected. Halting automation."
        break
    }

    Start-Sleep -Seconds $DelaySeconds
}

Write-Log "automation stopped"
Write-Host "Automation finished. Log saved to $logFile"
