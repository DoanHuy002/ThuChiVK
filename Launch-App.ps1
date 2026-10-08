$ErrorActionPreference = 'Stop'
try {
    $installDir = 'D:\Codex\Projects\VinhKhangThuChiApp'
    $appExe = Get-ChildItem -LiteralPath $installDir -Filter '*.exe' -ErrorAction SilentlyContinue | Where-Object { $_.Name -notmatch 'Uninstall' } | Select-Object -First 1
    if (-not $appExe) {
        $version = (Get-Content (Join-Path $PSScriptRoot 'package.json') -Raw | ConvertFrom-Json).version
        $buildDir = Join-Path $PSScriptRoot "releases\v$version\win-unpacked"
        $appExe = Get-ChildItem -LiteralPath $buildDir -Filter '*.exe' -ErrorAction SilentlyContinue | Select-Object -First 1
    }
    if (-not $appExe) { throw 'Please install the latest Thu Chi app first.' }
    $running = Get-Process -ErrorAction SilentlyContinue | Where-Object { $_.Path -eq $appExe.FullName -and $_.MainWindowHandle -ne 0 } | Select-Object -First 1
    if ($running) {
        $shell = New-Object -ComObject WScript.Shell
        $null = $shell.AppActivate($running.Id)
    } else {
        Start-Process -FilePath $appExe.FullName -WorkingDirectory $appExe.DirectoryName
    }
} catch {
    Add-Type -AssemblyName System.Windows.Forms
    [System.Windows.Forms.MessageBox]::Show($_.Exception.Message, 'Thu Chi - Launch error') | Out-Null
}
