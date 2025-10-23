param(
    [string]$SourceRoot = (Join-Path (Get-Location).Path 'external/ShimmerCapturePure'),
    [string]$OutputRoot = (Join-Path (Get-Location).Path 'external/ShimmerCapturePure_summaries')
)

$resolvedSource = (Resolve-Path $SourceRoot).Path
if (-not (Test-Path $OutputRoot))
{
    New-Item -ItemType Directory -Path $OutputRoot -Force | Out-Null
}
$resolvedOutput = (Resolve-Path $OutputRoot).Path

Write-Host "Source root : $resolvedSource"
Write-Host "Output root : $resolvedOutput"
Write-Host ""

Get-ChildItem -LiteralPath $resolvedSource -Recurse -File -Include *.java, *.kt | ForEach-Object {
    $relativePath = $_.FullName.Substring($resolvedSource.Length).TrimStart('\', '/')
    $relativeDir = Split-Path $relativePath -Parent
    $targetDir = if ( [string]::IsNullOrEmpty($relativeDir))
    {
        $resolvedOutput
    }
    else
    {
        Join-Path $resolvedOutput $relativeDir
    }
    $summaryFile = Join-Path $targetDir ("{0}.summary.txt" -f $_.BaseName)

    New-Item -ItemType Directory -Path $targetDir -Force | Out-Null

    @(
        "// Summary for: $( $_.FullName )"
        "// Size: $( [int64]$_.Length ) bytes"
        "// Modified: $( $_.LastWriteTime )"
        ""
    ) | Set-Content -Encoding UTF8 -LiteralPath $summaryFile

    Get-Content -LiteralPath $_.FullName -Raw | Add-Content -Encoding UTF8 -LiteralPath $summaryFile
}

Write-Host ""
Write-Host "Summaries written to $resolvedOutput"
