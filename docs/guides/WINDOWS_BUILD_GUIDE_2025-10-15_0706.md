**Last Modified:** 2025-10-15 07:06 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Build Guide

# Windows Native Build Guide

## Problem Statement

WSL (Windows Subsystem for Linux) has persistent filesystem I/O errors when accessing the Windows Android SDK, preventing Gradle from compiling Android projects. This guide provides the solution: building from native Windows instead.

## Solution Overview

Run Gradle builds from Windows Command Prompt or PowerShell while continuing to use WSL for Git operations and documentation work.

---

## Prerequisites

### 1. Java Development Kit (JDK)

**Required:** JDK 21 (already installed based on local.properties)

Verify installation:
```cmd
java -version
```

Expected output:
```
openjdk version "21.0.8"
```

Location: `C:\Program Files\Java\jdk-24` (from local.properties)

### 2. Android SDK

**Required:** Android SDK with build tools 36.1.0

Verify installation:
```cmd
dir "C:\Users\duyan\AppData\Local\Android\Sdk"
```

Location: `C:\Users\duyan\AppData\Local\Android\Sdk` (from local.properties)

### 3. Gradle Wrapper

**Status:** Fixed (regenerated with proper manifest)

Verify:
```cmd
cd C:\dev\buccancs
gradlew.bat --version
```

---

## Building from Windows

### Quick Start

Open Command Prompt or PowerShell and navigate to project:

```cmd
cd C:\dev\buccancs
```

### Common Build Commands

#### 1. Build All Modules
```cmd
gradlew.bat build
```

#### 2. Clean Build
```cmd
gradlew.bat clean build
```

#### 3. Build Android APK
```cmd
gradlew.bat :app:assembleDebug
```

Output: `app\build\outputs\apk\debug\app-debug.apk`

#### 4. Build Desktop Application
```cmd
gradlew.bat :desktop:run
```

#### 5. Compile Tests
```cmd
gradlew.bat :app:compileDebugAndroidTestKotlin
gradlew.bat :app:compileDebugUnitTestKotlin
```

#### 6. Run Tests (when tests enabled)
```cmd
gradlew.bat :app:testDebugUnitTest
gradlew.bat :desktop:test
```

#### 7. Check Code Style
```cmd
gradlew.bat :app:ktlintCheck
gradlew.bat :desktop:ktlintCheck
```

---

## Project Structure from Windows

```
C:\dev\buccancs\
├── app\                    (Android app module)
├── desktop\                (Desktop orchestrator)
├── protocol\               (Shared protocol definitions)
├── shimmer\                (Shimmer SDK wrapper)
├── gradle\
│   └── wrapper\
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew.bat            (Windows Gradle wrapper)
├── build.gradle.kts       (Root build configuration)
└── settings.gradle.kts    (Project settings)
```

---

## Environment Configuration

### local.properties

**Current (WSL paths - problematic):**
```properties
sdk.dir=/mnt/c/Users/duyan/AppData/Local/Android/Sdk
ANDROID_HOME=/mnt/c/Users/duyan/AppData/Local/Android/Sdk
```

**Required (Windows paths - for native builds):**
```properties
sdk.dir=C\:\\Users\\duyan\\AppData\\Local\\Android\\Sdk
ANDROID_HOME=C\:\\Users\\duyan\\AppData\\Local\\Android\\Sdk
jdk.dir=C\:\\Program Files\\Java\\jdk-24
JAVA_HOME=C\:\\Program Files\\Java\\jdk-24
```

**Solution:** Maintain two versions or use environment variables instead.

### Environment Variables (Recommended)

Set system environment variables in Windows:

1. Open "Edit the system environment variables"
2. Click "Environment Variables"
3. Add/verify:
   - `ANDROID_HOME` = `C:\Users\duyan\AppData\Local\Android\Sdk`
   - `JAVA_HOME` = `C:\Program Files\Java\jdk-24`
4. Remove `sdk.dir` from `local.properties` to use environment variables

---

## IDE Setup

### Android Studio (Recommended)

1. **Install:** Download from https://developer.android.com/studio
2. **Open Project:** File → Open → `C:\dev\buccancs`
3. **Sync Gradle:** File → Sync Project with Gradle Files
4. **Build:** Build → Make Project
5. **Run:** Run → Run 'app'

**Advantages:**
- Native Windows environment
- Full Android tooling
- Integrated debugger
- UI designer

### IntelliJ IDEA

1. **Open Project:** File → Open → `C:\dev\buccancs`
2. **Trust Project:** Accept Gradle wrapper
3. **Sync:** Wait for Gradle sync to complete
4. **Build:** Build → Build Project

**Advantages:**
- Better Kotlin support
- Desktop module support
- Multi-module project handling

---

## Common Issues and Solutions

### Issue 1: Gradle Wrapper Not Found

**Error:**
```
'gradlew.bat' is not recognized as an internal or external command
```

**Solution:**
```cmd
cd C:\dev\buccancs
dir gradlew.bat
```

If missing, regenerate from WSL:
```bash
cd /mnt/c/dev/buccancs
gradle wrapper --gradle-version 8.14
```

### Issue 2: JAVA_HOME Not Set

**Error:**
```
ERROR: JAVA_HOME is not set and no 'java' command could be found
```

**Solution:**
Set JAVA_HOME environment variable or add to path:
```cmd
set JAVA_HOME=C:\Program Files\Java\jdk-24
set PATH=%JAVA_HOME%\bin;%PATH%
```

### Issue 3: Android SDK Not Found

**Error:**
```
SDK location not found
```

**Solution:**
Create/update `local.properties`:
```properties
sdk.dir=C\:\\Users\\duyan\\AppData\\Local\\Android\\Sdk
```

Or set ANDROID_HOME environment variable.

### Issue 4: Build Tools Version Mismatch

**Error:**
```
Installed Build Tools revision X is corrupted
```

**Solution:**
Update `app/build.gradle.kts`:
```kotlin
android {
    buildToolsVersion = "36.1.0"  // Match installed version
}
```

### Issue 5: Tests Disabled

**Note:**
```
⏸️  Tests are DISABLED (use -Ptests.enabled=true to enable)
```

**Solution:**
Enable tests:
```cmd
gradlew.bat test -Ptests.enabled=true
```

Or update `gradle.properties`:
```properties
tests.enabled=true
```

---

## Hybrid Workflow (WSL + Windows)

### Recommended Approach

**Use WSL for:**
- Git operations (`git commit`, `git push`, `git pull`)
- Documentation editing (Markdown files)
- Text file modifications
- Directory navigation and file management

**Use Windows for:**
- Gradle builds (`gradlew.bat build`)
- Android compilation
- Test execution
- APK generation
- IDE usage (Android Studio, IntelliJ)

### Workflow Example

1. **Edit code in WSL:**
```bash
cd /mnt/c/dev/buccancs
vim app/src/main/kotlin/com/buccancs/ui/MainScreen.kt
```

2. **Build in Windows Command Prompt:**
```cmd
cd C:\dev\buccancs
gradlew.bat :app:assembleDebug
```

3. **Run tests in Windows:**
```cmd
gradlew.bat :app:testDebugUnitTest -Ptests.enabled=true
```

4. **Commit in WSL:**
```bash
cd /mnt/c/dev/buccancs
git add .
git commit -m "Updated MainScreen"
git push
```

---

## PowerShell Script

Create `build.ps1` in project root:

```powershell
# BuccanCS Build Script
# Usage: .\build.ps1 [command]

param(
    [string]$Command = "build"
)

$ProjectRoot = "C:\dev\buccancs"
Set-Location $ProjectRoot

switch ($Command) {
    "build" {
        Write-Host "Building all modules..." -ForegroundColor Green
        .\gradlew.bat build
    }
    "clean" {
        Write-Host "Cleaning build..." -ForegroundColor Yellow
        .\gradlew.bat clean build
    }
    "apk" {
        Write-Host "Building Android APK..." -ForegroundColor Green
        .\gradlew.bat :app:assembleDebug
    }
    "desktop" {
        Write-Host "Running desktop application..." -ForegroundColor Green
        .\gradlew.bat :desktop:run
    }
    "test" {
        Write-Host "Running tests..." -ForegroundColor Green
        .\gradlew.bat test -Ptests.enabled=true
    }
    "check" {
        Write-Host "Running code checks..." -ForegroundColor Green
        .\gradlew.bat check
    }
    default {
        Write-Host "Unknown command: $Command" -ForegroundColor Red
        Write-Host "Available commands: build, clean, apk, desktop, test, check"
    }
}
```

Usage:
```powershell
.\build.ps1 apk
.\build.ps1 test
```

---

## Batch Script

Create `build.bat` in project root:

```batch
@echo off
REM BuccanCS Build Script
REM Usage: build.bat [command]

cd /d C:\dev\buccancs

if "%1"=="build" (
    echo Building all modules...
    gradlew.bat build
) else if "%1"=="clean" (
    echo Cleaning build...
    gradlew.bat clean build
) else if "%1"=="apk" (
    echo Building Android APK...
    gradlew.bat :app:assembleDebug
) else if "%1"=="desktop" (
    echo Running desktop application...
    gradlew.bat :desktop:run
) else if "%1"=="test" (
    echo Running tests...
    gradlew.bat test -Ptests.enabled=true
) else (
    echo Usage: build.bat [build^|clean^|apk^|desktop^|test]
)
```

Usage:
```cmd
build.bat apk
build.bat test
```

---

## Verification Steps

After setup, verify everything works:

### 1. Check Java
```cmd
java -version
```
Expected: JDK 21

### 2. Check Gradle
```cmd
gradlew.bat --version
```
Expected: Gradle 8.14

### 3. Check Android SDK
```cmd
dir "%ANDROID_HOME%\build-tools\36.1.0"
```
Expected: Build tools present

### 4. Build Project
```cmd
gradlew.bat build --no-daemon
```
Expected: BUILD SUCCESSFUL

### 5. Compile Tests
```cmd
gradlew.bat :app:compileDebugAndroidTestKotlin --no-daemon
```
Expected: Successful compilation

---

## Performance Tips

### 1. Use Gradle Daemon

Add to `gradle.properties`:
```properties
org.gradle.daemon=true
org.gradle.parallel=true
org.gradle.caching=true
```

### 2. Increase Gradle Memory

Add to `gradle.properties`:
```properties
org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=512m
```

### 3. Enable Build Cache

```cmd
gradlew.bat build --build-cache
```

### 4. Use Configuration Cache (Gradle 8+)

Add to `gradle.properties`:
```properties
org.gradle.configuration-cache=true
```

---

## Troubleshooting

### Gradle Daemon Issues

Stop daemon:
```cmd
gradlew.bat --stop
```

View daemon status:
```cmd
gradlew.bat --status
```

### Clean Gradle Cache

```cmd
rd /s /q %USERPROFILE%\.gradle\caches
```

### Full Clean Build

```cmd
gradlew.bat clean
rd /s /q build
rd /s /q app\build
rd /s /q desktop\build
gradlew.bat build
```

---

## Next Steps

1. **Verify Setup:** Run verification steps above
2. **Build Project:** `gradlew.bat build`
3. **Run Tests:** `gradlew.bat test -Ptests.enabled=true`
4. **Generate APK:** `gradlew.bat :app:assembleDebug`
5. **Test Desktop:** `gradlew.bat :desktop:run`

---

## Summary

**Problem:** WSL/Android SDK filesystem incompatibility  
**Solution:** Build from native Windows environment  
**Approach:** Hybrid workflow (WSL for Git, Windows for builds)  
**Tools:** Command Prompt, PowerShell, or IDE  
**Status:** Ready to implement

**Benefits:**
- Eliminates WSL filesystem issues
- Full Android tooling support
- Faster build times
- IDE integration
- Reliable test execution

**Drawbacks:**
- Requires context switching between WSL and Windows
- Two environment configurations to maintain
- Learning curve for Windows command line

**Recommendation:** Use Android Studio for best developer experience with native Windows builds and WSL for Git operations.
