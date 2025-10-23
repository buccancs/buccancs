param(
    [string]$AndroidSdk,
    [string]$JavaHome,
    [string]$GradleUserHome,
    [string]$AndroidNdk
)

$ErrorActionPreference = "Stop"

function Resolve-RepoRoot {
    $scriptDir = if ($PSScriptRoot) {
        $PSScriptRoot
    } elseif ($MyInvocation.MyCommand.Path) {
        Split-Path -Parent $MyInvocation.MyCommand.Path
    } else {
        Get-Location | Select-Object -ExpandProperty Path
    }
    
    return Resolve-Path (Join-Path $scriptDir "..")
}

function Ensure-File {
    param(
        [string]$Source,
        [string]$Destination
    )

    if (-not (Test-Path $Destination)) {
        New-Item -ItemType Directory -Path (Split-Path -Parent $Destination) -Force | Out-Null
        Copy-Item -Path $Source -Destination $Destination -Force
    }
}

function Update-Property {
    param(
        [string[]]$Lines,
        [string]$Key,
        [string]$Value
    )

    $pattern = "^\s*$Key\s*="
    $replacement = if ($Value) { "$Key = $Value" } else { "$Key =" }

    $updated = $false
    for ($i = 0; $i -lt $Lines.Length; $i++) {
        if ($Lines[$i] -match $pattern) {
            $Lines[$i] = $replacement
            $updated = $true
        }
    }

    if (-not $updated) {
        return $Lines + $replacement
    }

    return $Lines
}

$repoRoot = (Resolve-RepoRoot).Path
$toolchainRoot = Join-Path $repoRoot "toolchains"
$osToolchainRoot = Join-Path $toolchainRoot "windows"

if (-not $AndroidSdk) {
    $AndroidSdk = Join-Path $osToolchainRoot "android-sdk"
}

if (-not $AndroidNdk) {
    $AndroidNdk = Join-Path $osToolchainRoot "android-ndk"
}

if (-not $JavaHome) {
    $JavaHome = Join-Path $osToolchainRoot "java"
}

if (-not $GradleUserHome) {
    $GradleUserHome = Join-Path $osToolchainRoot "gradle-user-home"
}

New-Item -ItemType Directory -Path $AndroidSdk -Force | Out-Null
New-Item -ItemType Directory -Path $GradleUserHome -Force | Out-Null
if ($AndroidNdk) {
    New-Item -ItemType Directory -Path $AndroidNdk -Force | Out-Null
}
$sampleConfig = Join-Path $repoRoot "gradle/os-paths.sample.properties"
$configFile = Join-Path $repoRoot "gradle/os-paths.properties"

Ensure-File -Source $sampleConfig -Destination $configFile

$lines = [System.IO.File]::ReadAllLines($configFile)
$lines = Update-Property -Lines $lines -Key "windows.android.sdk" -Value $AndroidSdk
$lines = Update-Property -Lines $lines -Key "windows.android.ndk" -Value $AndroidNdk
$lines = Update-Property -Lines $lines -Key "windows.java.home" -Value $JavaHome
$lines = Update-Property -Lines $lines -Key "windows.gradle.user.home" -Value $GradleUserHome
[System.IO.File]::WriteAllLines($configFile, $lines)

Write-Host "Updated $configFile with Windows toolchain locations."

[System.Environment]::SetEnvironmentVariable("ANDROID_SDK_ROOT", $AndroidSdk, "User")
[System.Environment]::SetEnvironmentVariable("JAVA_HOME", $JavaHome, "User")
[System.Environment]::SetEnvironmentVariable("GRADLE_USER_HOME", $GradleUserHome, "User")
if ($AndroidNdk) {
    [System.Environment]::SetEnvironmentVariable("ANDROID_NDK_ROOT", $AndroidNdk, "User")
}

$wslEnv = "ANDROID_SDK_ROOT/p:JAVA_HOME/p:GRADLE_USER_HOME/p"
if ($AndroidNdk) {
    $wslEnv += ":ANDROID_NDK_ROOT/p"
}
[System.Environment]::SetEnvironmentVariable("WSLENV", $wslEnv, "User")

Write-Host ""
Write-Host "User-level environment variables updated:"
Write-Host "  ANDROID_SDK_ROOT =$AndroidSdk"
Write-Host "  JAVA_HOME        =$JavaHome"
Write-Host "  GRADLE_USER_HOME =$GradleUserHome"
if ($AndroidNdk) {
    Write-Host "  ANDROID_NDK_ROOT =$AndroidNdk"
}
Write-Host "  WSLENV           =$wslEnv"
Write-Host ""
Write-Host "Restart PowerShell/WSL sessions for the new values to take effect."
Write-Host ""
Write-Host "Shared toolchains directory (ignored by git):"
Write-Host "  $toolchainRoot"
Write-Host "Populate android-sdk, android-ndk (optional), java, and gradle-user-home inside it or rerun with custom paths."
