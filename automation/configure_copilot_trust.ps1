[CmdletBinding()]
param(
    [string]$ConfigDirectory = (Join-Path $env:APPDATA "GitHub Copilot CLI"),
    [string]$TrustedRoot = "C:\"
)

$configPath = Join-Path $ConfigDirectory "config.json"

if (-not (Test-Path -Path $ConfigDirectory -PathType Container)) {
    New-Item -ItemType Directory -Path $ConfigDirectory | Out-Null
}

$configObject = @{}

if (Test-Path -Path $configPath -PathType Leaf) {
    try {
        $content = Get-Content -Path $configPath -Raw -ErrorAction Stop
        if ($content.Trim()) {
            $configObject = $content | ConvertFrom-Json -ErrorAction Stop
        }
    } catch {
        Write-Warning "Existing Copilot CLI config is invalid JSON; starting from a clean configuration."
        $configObject = @{}
    }
}

if (-not $configObject.PSObject.Properties.Name.Contains("trustedFolders")) {
    $configObject | Add-Member -MemberType NoteProperty -Name "trustedFolders" -Value @()
}

$trusted = $configObject.trustedFolders

if ($trusted -isnot [System.Collections.IList]) {
    $trusted = @()
}

if (-not ($trusted | ForEach-Object { $_.TrimEnd('\') } | Where-Object { $_ -eq $TrustedRoot.TrimEnd('\') })) {
    $trusted += $TrustedRoot
    Write-Host ("Added '{0}' to Copilot CLI trusted folders." -f $TrustedRoot)
} else {
    Write-Host ("'{0}' already present in Copilot CLI trusted folders." -f $TrustedRoot)
}

$configObject.trustedFolders = $trusted

$configObject | ConvertTo-Json -Depth 10 | Set-Content -Path $configPath -Encoding UTF8

Write-Host ("Copilot CLI configuration updated at {0}" -f $configPath)
