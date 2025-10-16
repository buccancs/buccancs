<#
.SYNOPSIS
Rate limiting and throttling for continuation requests.

.DESCRIPTION
Prevents API rate limits by enforcing cooldown periods and tracking request rates.
#>

$script:RateLimitDbPath = "C:\dev\buccancs\logs\rate_limits.json"

function Initialize-RateLimitDatabase
{
    if (-not (Test-Path $script:RateLimitDbPath))
    {
        @{
            Requests = @()
            LastRequest = $null
            MinIntervalSeconds = 30
            MaxRequestsPerHour = 60
        } | ConvertTo-Json | Out-File -FilePath $script:RateLimitDbPath -Encoding utf8
    }
}

function Get-RateLimitDatabase
{
    Initialize-RateLimitDatabase
    return Get-Content $script:RateLimitDbPath -Raw | ConvertFrom-Json
}

function Save-RateLimitDatabase
{
    param([object]$Database)
    $Database | ConvertTo-Json -Depth 10 | Out-File -FilePath $script:RateLimitDbPath -Encoding utf8 -Force
}

function Test-RateLimitAllowed
{
    param([string]$SessionId)

    $db = Get-RateLimitDatabase
    $now = Get-Date

    # Check minimum interval
    if ($db.LastRequest)
    {
        $lastRequest = [DateTime]::Parse($db.LastRequest)
        $elapsed = ($now - $lastRequest).TotalSeconds

        if ($elapsed -lt $db.MinIntervalSeconds)
        {
            return @{
                Allowed = $false
                Reason = "Minimum interval not met"
                WaitSeconds = [math]::Ceiling($db.MinIntervalSeconds - $elapsed)
            }
        }
    }

    # Check hourly limit
    $oneHourAgo = $now.AddHours(-1)
    $recentRequests = $db.Requests | Where-Object {
        $reqTime = [DateTime]::Parse($_.Timestamp)
        $reqTime -gt $oneHourAgo
    }

    if ($recentRequests.Count -ge $db.MaxRequestsPerHour)
    {
        return @{
            Allowed = $false
            Reason = "Hourly rate limit exceeded"
            WaitSeconds = 3600
        }
    }

    return @{
        Allowed = $true
        Reason = "OK"
        WaitSeconds = 0
    }
}

function Register-ContinuationRequest
{
    param([string]$SessionId)

    $db = Get-RateLimitDatabase

    $request = @{
        SessionId = $SessionId
        Timestamp = (Get-Date).ToString("o")
    }

    $db.Requests += $request
    $db.LastRequest = $request.Timestamp

    # Clean old requests (older than 1 hour)
    $oneHourAgo = (Get-Date).AddHours(-1)
    $db.Requests = @($db.Requests | Where-Object {
        $reqTime = [DateTime]::Parse($_.Timestamp)
        $reqTime -gt $oneHourAgo
    })

    Save-RateLimitDatabase $db
}

function Get-RateLimitStatistics
{
    $db = Get-RateLimitDatabase

    $now = Get-Date
    $oneHourAgo = $now.AddHours(-1)

    $recentRequests = @($db.Requests | Where-Object {
        $reqTime = [DateTime]::Parse($_.Timestamp)
        $reqTime -gt $oneHourAgo
    })

    return @{
        RequestsLastHour = $recentRequests.Count
        MaxRequestsPerHour = $db.MaxRequestsPerHour
        MinIntervalSeconds = $db.MinIntervalSeconds
        LastRequest = $db.LastRequest
    }
}

function Set-RateLimitConfig
{
    param(
        [int]$MinIntervalSeconds,
        [int]$MaxRequestsPerHour
    )

    $db = Get-RateLimitDatabase

    if ($MinIntervalSeconds)
    {
        $db.MinIntervalSeconds = $MinIntervalSeconds
    }

    if ($MaxRequestsPerHour)
    {
        $db.MaxRequestsPerHour = $MaxRequestsPerHour
    }

    Save-RateLimitDatabase $db
}

Export-ModuleMember -Function @(
    'Test-RateLimitAllowed',
    'Register-ContinuationRequest',
    'Get-RateLimitStatistics',
    'Set-RateLimitConfig'
)
