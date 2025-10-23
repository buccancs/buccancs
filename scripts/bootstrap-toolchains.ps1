param(
    [string]$RepoRoot,
    [string]$AndroidCommandLineToolsVersion = "11076708",
    [string[]]$AndroidPackages = @(
        "platform-tools",
        "build-tools;36.0.0",
        "platforms;android-36"
    ),
    [string]$TemurinJdkVersion = "21.0.4_7",
    [switch]$ForceRedownload
)

$ErrorActionPreference = "Stop"

function Resolve-RepoRoot {
    param(
        [string]$ExplicitRepoRoot
    )

    if ($ExplicitRepoRoot) {
        return (Resolve-Path $ExplicitRepoRoot).Path
    }

    $scriptDir = if ($PSScriptRoot) {
        $PSScriptRoot
    } elseif ($MyInvocation.MyCommand.Path) {
        Split-Path -Parent $MyInvocation.MyCommand.Path
    } else {
        Get-Location | Select-Object -ExpandProperty Path
    }

    return (Resolve-Path (Join-Path $scriptDir "..")).Path
}

function Download-IfMissing {
    param(
        [string]$Url,
        [string]$Destination,
        [switch]$Overwrite
    )

    if (-not $Overwrite -and (Test-Path $Destination)) {
        Write-Host "✓ Reusing existing file $Destination"
        return
    }

    $destDir = Split-Path -Parent $Destination
    if (-not (Test-Path $destDir)) {
        New-Item -ItemType Directory -Force -Path $destDir | Out-Null
    }

    Write-Host "⇣ Downloading $Url"
    Invoke-WebRequest -Uri $Url -OutFile $Destination
}

function Ensure-CmdlineToolsLayout {
    param(
        [string]$Root
    )

    $latestDir = Join-Path $Root "latest"
    $sdkManagerPath = Join-Path $latestDir "bin\sdkmanager.bat"

    if (Test-Path $sdkManagerPath) {
        return $sdkManagerPath
    }

    $candidate = Get-ChildItem -Path $Root -Directory -Recurse -ErrorAction SilentlyContinue |
        Where-Object { Test-Path (Join-Path $_.FullName "bin\sdkmanager.bat") } |
        Sort-Object FullName |
        Select-Object -First 1

    if (-not $candidate) {
        throw "Unable to locate sdkmanager.bat under $Root"
    }

    Write-Host "↺ Normalising command-line tools layout"

    if (Test-Path $latestDir) {
        Remove-Item -Recurse -Force $latestDir
    }

    Move-Item -Force -Path $candidate.FullName -Destination $latestDir

    $parent = Split-Path -Parent $candidate.FullName
    while ($parent -and ($parent -ne $Root)) {
        if ((Get-ChildItem -Force $parent | Measure-Object).Count -gt 0) {
            break
        }
        Remove-Item -Force $parent
        $parent = Split-Path -Parent $parent
    }

    if (-not (Test-Path $sdkManagerPath)) {
        throw "Failed to normalise command-line tools layout at $Root"
    }

    return $sdkManagerPath
}

function Run-SdkManager {
    param(
        [string]$SdkManagerPath,
        [string]$SdkRoot,
        [string]$JavaHome,
        [string[]]$Arguments,
        [switch]$AutoAccept
    )

    if (-not (Test-Path $SdkManagerPath)) {
        throw "sdkmanager.bat not found at $SdkManagerPath"
    }

    $environment = [System.Collections.Hashtable]::Synchronized(@{})
    $environment["ANDROID_SDK_ROOT"] = $SdkRoot
    $environment["JAVA_HOME"] = $JavaHome
    $environment["PATH"] = "$JavaHome\bin;" + [System.Environment]::GetEnvironmentVariable("PATH")

    $argumentString = "--sdk_root=`"$SdkRoot`""
    if ($Arguments -and $Arguments.Length -gt 0) {
        $quoted = $Arguments | ForEach-Object { "`"$_`"" }
        $argumentString += " " + ($quoted -join " ")
    }

    $processInfo = New-Object System.Diagnostics.ProcessStartInfo
    $processInfo.FileName = $SdkManagerPath
    $processInfo.Arguments = $argumentString
    $processInfo.WorkingDirectory = Split-Path -Parent $SdkManagerPath
    $processInfo.UseShellExecute = $false
    $processInfo.RedirectStandardInput = $true
    $processInfo.RedirectStandardOutput = $true
    $processInfo.RedirectStandardError = $true
    $processInfo.CreateNoWindow = $true
    foreach ($key in $environment.Keys) {
        $processInfo.Environment[$key] = $environment[$key]
    }

    $process = New-Object System.Diagnostics.Process
    $process.StartInfo = $processInfo
    $process.Start() | Out-Null

    if ($AutoAccept) {
        for ($i = 0; $i -lt 60; $i++) {
            if ($process.HasExited) { break }
            $process.StandardInput.WriteLine("y")
            Start-Sleep -Milliseconds 100
        }
        if (-not $process.HasExited) {
            $process.StandardInput.Close()
        }
    } else {
        $process.StandardInput.Close()
    }

    $stdout = $process.StandardOutput.ReadToEnd()
    $stderr = $process.StandardError.ReadToEnd()
    $process.WaitForExit()

    if ($stdout.Trim()) {
        Write-Host $stdout
    }
    if ($stderr.Trim()) {
        Write-Host $stderr
    }

    if ($process.ExitCode -ne 0) {
        throw "sdkmanager exited with code $($process.ExitCode)"
    }
}

function Expand-Zip {
    param(
        [string]$ArchivePath,
        [string]$Destination,
        [switch]$Force
    )

    if (-not (Test-Path $ArchivePath)) {
        throw "Archive not found: $ArchivePath"
    }

    if (Test-Path $Destination) {
        if (-not $Force) {
            $existingFiles = Get-ChildItem -Path $Destination -Recurse -File -ErrorAction SilentlyContinue
            if ($existingFiles -and ($existingFiles | Measure-Object).Count -gt 0) {
                Write-Host "✓ Reusing existing directory $Destination"
                return
            }
            Write-Host "⚠ Existing directory is empty or invalid, removing: $Destination"
        }
        Remove-Item -Recurse -Force $Destination
    }

    Write-Host "⇢ Extracting $(Split-Path -Leaf $ArchivePath) -> $Destination"
    Expand-Archive -LiteralPath $ArchivePath -DestinationPath $Destination
}

function Install-AndroidPackages {
    param(
        [string]$SdkManagerPath,
        [string]$SdkRoot,
        [string]$JavaHome,
        [string[]]$Packages
    )

    Write-Host ("⚒ Installing Android packages: {0}" -f ($Packages -join ", "))
    Run-SdkManager -SdkManagerPath $SdkManagerPath -SdkRoot $SdkRoot -JavaHome $JavaHome -Arguments $Packages -AutoAccept

    Write-Host "⚖ Accepting Android SDK licenses"
    Run-SdkManager -SdkManagerPath $SdkManagerPath -SdkRoot $SdkRoot -JavaHome $JavaHome -Arguments @("--licenses") -AutoAccept
}

$resolvedRepoRoot = Resolve-RepoRoot -ExplicitRepoRoot $RepoRoot
Write-Host "Repository root: $resolvedRepoRoot"

$toolchainsRoot = Join-Path $resolvedRepoRoot "toolchains"
$androidSdkDir = Join-Path $toolchainsRoot "android-sdk"
$androidNdkDir = Join-Path $toolchainsRoot "android-ndk"
$javaDir = Join-Path $toolchainsRoot "java"
$gradleHomeDir = Join-Path $toolchainsRoot "gradle-user-home"

$cmdlineToolsZipName = "commandlinetools-win-${AndroidCommandLineToolsVersion}_latest.zip"
$cmdlineToolsUrl = "https://dl.google.com/android/repository/$cmdlineToolsZipName"
$cmdlineToolsZip = Join-Path $toolchainsRoot "downloads\$cmdlineToolsZipName"
$cmdlineToolsExtractRoot = Join-Path $androidSdkDir "cmdline-tools"
$cmdlineToolsLatest = Join-Path $cmdlineToolsExtractRoot "latest"

$temurinZipName = "OpenJDK21U-jdk_x64_windows_hotspot_${TemurinJdkVersion}.zip"
$temurinUrlVersion = $TemurinJdkVersion -replace "_", "+"
$temurinUrl = "https://github.com/adoptium/temurin21-binaries/releases/download/jdk-${temurinUrlVersion}/$temurinZipName"
$temurinZip = Join-Path $toolchainsRoot "downloads\$temurinZipName"

Write-Host "`n==> Preparing directories"
New-Item -ItemType Directory -Force -Path $toolchainsRoot, $androidSdkDir, $androidNdkDir, $javaDir, $gradleHomeDir, (Split-Path $cmdlineToolsZip -Parent) | Out-Null

Write-Host "`n==> Android command-line tools"
Download-IfMissing -Url $cmdlineToolsUrl -Destination $cmdlineToolsZip -Overwrite:$ForceRedownload
Expand-Zip -ArchivePath $cmdlineToolsZip -Destination $cmdlineToolsExtractRoot -Force:$ForceRedownload
$sdkManagerBat = Ensure-CmdlineToolsLayout -Root $cmdlineToolsExtractRoot

Write-Host "`n==> Temurin JDK"
Download-IfMissing -Url $temurinUrl -Destination $temurinZip -Overwrite:$ForceRedownload

$javaReady = Test-Path (Join-Path $javaDir "bin\java.exe")
if (-not $javaReady -or $ForceRedownload) {
    $jdkTempDir = Join-Path $toolchainsRoot "temp-jdk-extract"
    
    if (Test-Path $jdkTempDir) {
        Remove-Item -Recurse -Force $jdkTempDir
    }
    
    Write-Host "⇢ Extracting JDK archive..."
    Expand-Archive -LiteralPath $temurinZip -DestinationPath $jdkTempDir
    
    $jdkFolder = Get-ChildItem $jdkTempDir -Directory |
        Where-Object { Test-Path (Join-Path $_.FullName "bin\java.exe") } |
        Select-Object -First 1
    
    if (-not $jdkFolder) {
        throw "Unable to locate extracted JDK under $jdkTempDir"
    }
    
    Write-Host "↺ Moving JDK to $javaDir"
    
    if (Test-Path $javaDir) {
        Get-ChildItem $javaDir -Force | Remove-Item -Recurse -Force
    }
    
    Get-ChildItem $jdkFolder.FullName | Move-Item -Destination $javaDir -Force
    
    Remove-Item -Recurse -Force $jdkTempDir
    
    if (-not (Test-Path (Join-Path $javaDir "bin\java.exe"))) {
        throw "Failed to install JDK to $javaDir"
    }
}

$javaHomePath = $javaDir

Write-Host "`n==> Installing Android SDK packages"
Install-AndroidPackages -SdkManagerPath $sdkManagerBat -SdkRoot $androidSdkDir -JavaHome $javaHomePath -Packages $AndroidPackages

Write-Host "`n==> Updating Gradle OS paths"
$setupScript = Join-Path $resolvedRepoRoot "scripts\setup-windows.ps1"
& $setupScript -AndroidSdk $androidSdkDir -JavaHome $javaHomePath -GradleUserHome $gradleHomeDir -AndroidNdk $androidNdkDir

Write-Host "`nAll toolchains downloaded and configured."
Write-Host "Android SDK: $androidSdkDir"
Write-Host "JDK:         $javaDir"
Write-Host "Gradle home: $gradleHomeDir"
