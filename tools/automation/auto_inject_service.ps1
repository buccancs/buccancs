<#
.SYNOPSIS
Background service that monitors for completion signal file and auto-injects continuation prompt.

.DESCRIPTION
This service watches for a signal file. When created, it injects the continuation prompt
into the most recently active PowerShell window.

.PARAMETER MonitorInterval
Seconds between checks for completion signal (default: 1)

.PARAMETER ContinuationPrompt
The prompt to inject when completion is detected

.PARAMETER SignalFile
Path to signal file (default: C:\dev\buccancs\logs\.inject_signal)

.PARAMETER LogFile
Path to log file for service activity

.EXAMPLE
.\auto_inject_service.ps1

.EXAMPLE
.\auto_inject_service.ps1 -MonitorInterval 2 -LogFile "C:\dev\buccancs\logs\auto_inject.log"
#>

param(
    [int]$MonitorInterval = 2,
    [string]$ContinuationPrompt = "continue with the next steps. also fix any remaining issues or errors. re-check your work and make sure to use best kotlin jetpack compose material 3 hilt clean architecture best practices. implement any missing tests, remove outdated comments, lines or files, and update documentation",
    [string]$SignalFile = "C:\dev\buccancs\logs\.inject_signal",
    [string]$LogFile = "",
    [switch]$OneShot,
    [switch]$UseSmartPrompts = $true
)

$ErrorActionPreference = "Continue"

# Load modules
. "$PSScriptRoot\session_manager.ps1"
. "$PSScriptRoot\smart_continuation.ps1"
. "$PSScriptRoot\rate_limiter.ps1"

function Write-Log
{
    param([string]$Message, [string]$Level = "INFO")
    $timestamp = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
    $logMessage = "[$timestamp] [$Level] $Message"

    Write-Host $logMessage -ForegroundColor $(
    switch ($Level)
    {
        "ERROR" {
            "Red"
        }
        "WARN" {
            "Yellow"
        }
        "SUCCESS" {
            "Green"
        }
        default {
            "White"
        }
    }
    )

    if ($LogFile)
    {
        $logMessage | Out-File -FilePath $LogFile -Append -Encoding utf8
    }
}

# Add window manipulation helpers
Add-Type @"
using System;
using System.Runtime.InteropServices;
using System.Text;

public class WindowHelper {
    public delegate bool EnumWindowsProc(IntPtr hWnd, IntPtr lParam);
    
    [DllImport("user32.dll")]
    public static extern bool EnumWindows(EnumWindowsProc lpEnumFunc, IntPtr lParam);
    
    [DllImport("user32.dll", SetLastError=true, CharSet=CharSet.Unicode)]
    public static extern int GetWindowText(IntPtr hWnd, StringBuilder lpString, int nMaxCount);
    
    [DllImport("user32.dll")]
    public static extern bool IsWindowVisible(IntPtr hWnd);
    
    [DllImport("user32.dll")]
    public static extern bool SetForegroundWindow(IntPtr hWnd);
    
    [DllImport("user32.dll")]
    public static extern bool ShowWindow(IntPtr hWnd, int nCmdShow);
    
    [DllImport("user32.dll")]
    public static extern bool BringWindowToTop(IntPtr hWnd);
    
    [DllImport("user32.dll")]
    public static extern IntPtr GetForegroundWindow();
    
    [DllImport("user32.dll")]
    public static extern uint GetWindowThreadProcessId(IntPtr hWnd, out uint processId);
    
    [DllImport("kernel32.dll")]
    public static extern uint GetCurrentThreadId();
    
    [DllImport("user32.dll")]
    public static extern bool AttachThreadInput(uint idAttach, uint idAttachTo, bool fAttach);
}
"@ -ErrorAction SilentlyContinue

Add-Type -AssemblyName System.Windows.Forms -ErrorAction SilentlyContinue

# Add console input injection
Add-Type @"
using System;
using System.Runtime.InteropServices;
using System.Collections.Generic;

public class ConsoleInjector {
    [StructLayout(LayoutKind.Sequential, CharSet=CharSet.Unicode)]
    public struct KEY_EVENT_RECORD {
        [MarshalAs(UnmanagedType.Bool)] public bool bKeyDown;
        public ushort wRepeatCount;
        public ushort wVirtualKeyCode;
        public ushort wVirtualScanCode;
        public char UnicodeChar;
        public uint dwControlKeyState;
    }
    
    [StructLayout(LayoutKind.Sequential, CharSet=CharSet.Unicode)]
    public struct INPUT_RECORD {
        public ushort EventType;
        public KEY_EVENT_RECORD KeyEvent;
    }
    
    [DllImport("kernel32.dll", SetLastError=true)]
    public static extern IntPtr OpenProcess(uint dwDesiredAccess, bool bInheritHandle, uint dwProcessId);
    
    [DllImport("kernel32.dll", SetLastError=true)]
    public static extern bool CloseHandle(IntPtr hObject);
    
    [DllImport("kernel32.dll", SetLastError=true)]
    public static extern bool AttachConsole(uint dwProcessId);
    
    [DllImport("kernel32.dll", SetLastError=true)]
    public static extern bool FreeConsole();
    
    [DllImport("kernel32.dll", SetLastError=true)]
    public static extern IntPtr GetStdHandle(int nStdHandle);
    
    [DllImport("kernel32.dll", SetLastError=true, CharSet=CharSet.Unicode)]
    public static extern bool WriteConsoleInput(IntPtr hConsoleInput, INPUT_RECORD[] lpBuffer, uint nLength, out uint lpNumberOfEventsWritten);
    
    const int STD_INPUT_HANDLE = -10;
    const ushort KEY_EVENT = 0x0001;
    const uint PROCESS_ALL_ACCESS = 0x1F0FFF;
    
    public static bool InjectToProcess(uint processId, string text) {
        IntPtr hProcess = OpenProcess(PROCESS_ALL_ACCESS, false, processId);
        if (hProcess == IntPtr.Zero) return false;
        
        try {
            if (!AttachConsole(processId)) return false;
            
            try {
                IntPtr hStdIn = GetStdHandle(STD_INPUT_HANDLE);
                if (hStdIn == IntPtr.Zero || hStdIn == new IntPtr(-1)) return false;
                
                var events = new List<INPUT_RECORD>();
                
                foreach (char c in text) {
                    INPUT_RECORD down = new INPUT_RECORD();
                    down.EventType = KEY_EVENT;
                    down.KeyEvent.bKeyDown = true;
                    down.KeyEvent.wRepeatCount = 1;
                    down.KeyEvent.UnicodeChar = c;
                    events.Add(down);
                    
                    INPUT_RECORD up = new INPUT_RECORD();
                    up.EventType = KEY_EVENT;
                    up.KeyEvent.bKeyDown = false;
                    up.KeyEvent.wRepeatCount = 1;
                    up.KeyEvent.UnicodeChar = c;
                    events.Add(up);
                }
                
                // Add Enter
                INPUT_RECORD enterDown = new INPUT_RECORD();
                enterDown.EventType = KEY_EVENT;
                enterDown.KeyEvent.bKeyDown = true;
                enterDown.KeyEvent.wRepeatCount = 1;
                enterDown.KeyEvent.UnicodeChar = '\r';
                events.Add(enterDown);
                
                INPUT_RECORD enterUp = new INPUT_RECORD();
                enterUp.EventType = KEY_EVENT;
                enterUp.KeyEvent.bKeyDown = false;
                enterUp.KeyEvent.wRepeatCount = 1;
                enterUp.KeyEvent.UnicodeChar = '\r';
                events.Add(enterUp);
                
                uint written;
                bool success = WriteConsoleInput(hStdIn, events.ToArray(), (uint)events.Count, out written);
                return success && written == events.Count;
                
            } finally {
                FreeConsole();
            }
        } finally {
            CloseHandle(hProcess);
        }
    }
}
"@ -ErrorAction SilentlyContinue

function Find-PowerShellWindow
{
    $targetWindow = $null
    $callback = [WindowHelper+EnumWindowsProc] {
        param($hWnd, $lParam)
        if ( [WindowHelper]::IsWindowVisible($hWnd))
        {
            $title = New-Object System.Text.StringBuilder 512
            [WindowHelper]::GetWindowText($hWnd, $title, 512) | Out-Null
            $titleStr = $title.ToString()

            $procId = 0
            [WindowHelper]::GetWindowThreadProcessId($hWnd, [ref]$procId) | Out-Null

            # Get process name
            $procName = ""
            try
            {
                $proc = Get-Process -Id $procId -ErrorAction SilentlyContinue
                if ($proc)
                {
                    $procName = $proc.ProcessName
                }
            }
            catch
            {
            }

            # Look for actual terminal windows - prioritize WindowsTerminal, then PowerShell windows
            if ($procName -eq "WindowsTerminal" -or
                    $procName -eq "Terminal" -or
                    ($titleStr -match "PowerShell|Terminal|pwsh" -and $procName -match "powershell|pwsh"))
            {
                $script:targetWindow = @{ Handle = $hWnd; Title = $titleStr; ProcessId = $procId; ProcessName = $procName }
                return $false  # Stop enumeration after first match
            }
        }
        return $true
    }

    $script:targetWindow = $null
    [WindowHelper]::EnumWindows($callback, [IntPtr]::Zero) | Out-Null

    return $script:targetWindow
}

function Invoke-ContinuationInjection
{
    param(
        [string]$Prompt,
        [string]$SessionId
    )

    try
    {
        Write-Log "Using Copilot CLI resume method..." "INFO"

        # Launch new PowerShell window with Copilot
        $projectPath = "C:\dev\buccancs"

        Write-Log "Executing: copilot with prompt" "INFO"

        $startArgs = @{
            FilePath = "pwsh.exe"
            ArgumentList = @(
                "-NoExit",
                "-Command",
                "cd '$projectPath'; `$env:COPILOT_INSTANCE_ID='$SessionId'; copilot --allow-all-paths --allow-all-tools -p '$( $Prompt -replace "'", "''" )'"
            )
            WindowStyle = "Normal"
        }

        if (-not $SessionId)
        {
            $startArgs.ArgumentList[2] = "cd '$projectPath'; copilot --allow-all-paths --allow-all-tools -p '$( $Prompt -replace "'", "''" )'"
        }

        Start-Process @startArgs

        Write-Log "Successfully launched Copilot in new window" "SUCCESS"
        return $true

    }
    catch
    {
        Write-Log "Failed to invoke Copilot: $_" "ERROR"
        return $false
    }
}

Write-Log "Auto-injection service started" "SUCCESS"
Write-Log "Monitor interval: $MonitorInterval seconds" "INFO"
Write-Log "Signal file: $SignalFile" "INFO"
Write-Log "Smart prompts: $UseSmartPrompts" "INFO"

# Initialize systems
Initialize-SessionDatabase
Write-Log "Session database initialized" "INFO"

if ($OneShot)
{
    Write-Log "One-shot mode: will inject once and exit" "INFO"
}

try
{
    while ($true)
    {
        Start-Sleep -Seconds $MonitorInterval

        # Check for signal file
        if (Test-Path $SignalFile)
        {
            Write-Log "Signal file detected!" "INFO"

            # Read session ID and smart prompt from signal file
            $sessionId = $null
            $promptToUse = $ContinuationPrompt
            try
            {
                $signalContent = Get-Content $SignalFile -Raw -ErrorAction SilentlyContinue
                if ($signalContent)
                {
                    Write-Log "Signal content received" "INFO"
                    $signalData = $signalContent | ConvertFrom-Json -ErrorAction SilentlyContinue
                    if ($signalData)
                    {
                        $sessionId = $signalData.SessionId

                        # Use smart prompt if available and enabled
                        if ($UseSmartPrompts -and $signalData.SmartPrompt)
                        {
                            $promptToUse = $signalData.SmartPrompt
                            Write-Log "Using smart prompt" "INFO"
                        }

                        Write-Log "Session: $sessionId" "INFO"
                    }
                }
            }
            catch
            {
                Write-Log "Failed to parse signal file: $_" "WARN"
            }

            # Remove signal file
            Remove-Item $SignalFile -Force -ErrorAction SilentlyContinue
            Write-Log "Signal file removed" "INFO"

            # Inject prompt with session ID
            if (Invoke-ContinuationInjection -Prompt $promptToUse -SessionId $sessionId)
            {
                if ($OneShot)
                {
                    Write-Log "One-shot complete, exiting" "SUCCESS"
                    exit 0
                }
            }
        }
    }
}
catch
{
    Write-Log "Service error: $_" "ERROR"
    throw
}
finally
{
    Write-Log "Auto-injection service stopped" "INFO"
}


