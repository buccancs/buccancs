# Cross-OS Gradle Toolchain Bootstrap

This project keeps Windows, WSL/Ubuntu, and macOS builds aligned by resolving
Android SDK / NDK, Java, and Gradle homes dynamically from shared configuration
and environment variables.

## Shared Toolchains Directory

Each checkout contains an untracked `toolchains/` directory (see
`toolchains/README.md`). Populate or symlink your platform toolchains inside it:

- `toolchains/android-sdk` – Android SDK, build-tools, platform-tools
- `toolchains/android-ndk` – Optional NDK install
- `toolchains/java` – JDK used by Gradle/Android tooling
- `toolchains/gradle-user-home` – Gradle caches and wrapper downloads

The Gradle init script automatically picks up these locations when they exist;
leave them empty if you prefer to override with environment variables.

## Quick Start

1. On Windows, run:
   ```powershell
   scripts\bootstrap-toolchains.ps1
   ```
   This downloads the Android command-line tools, installs the required SDK
   packages, fetches Temurin JDK 21, and wires everything into `toolchains/`.
   On other hosts, populate `<repo>/toolchains` manually (or symlink to existing
   installs) so the directories above exist before running builds.
2. Copy the sample config if you need host-specific overrides:
   ```bash
   cp gradle/os-paths.sample.properties gradle/os-paths.properties
   ```
   The sample already points at `toolchains/*`; adjust only if you keep
   toolchains elsewhere. Environment variables always take precedence.
3. Run the helper script for your OS:
   - Windows PowerShell:
     ```powershell
     scripts\setup-windows.ps1
     ```
     This seeds `gradle/os-paths.properties`, sets user-level environment variables,
     and wires `WSLENV` so WSL shells inherit them automatically.
   - Ubuntu/WSL:
     ```bash
     ./scripts/setup-wsl.sh
     ```
     This mirrors the Linux entries and prints `export` commands to add to your shell
     profile if you want them persistent.

## Gradle Init Script Behavior

`gradle/init.d/os-setup.gradle.kts` runs on every Gradle invocation:

- Detects the host OS via `OperatingSystem.current()`.
- Resolves path values in priority order: environment variable → `gradle/os-paths.properties`.
- Applies `org.gradle.java.home` and `GRADLE_USER_HOME` so the wrapper uses the correct
  JDK and cache directory.
- Keeps `local.properties` updated with `sdk.dir` (and `ndk.dir` if supplied), preventing
  “missing aapt/adb” issues when switching between Windows and WSL terminals.
- Translates Windows paths to `/mnt/<drive>/...` automatically when invoked from Linux/WSL.

## Hybrid Windows + WSL Notes

- Keep the Windows install of Android Studio pointed at `<repo>\toolchains\android-sdk`
  so both Windows and WSL share the same files (symlinks work too).
- After updating SDK/NDK/JDK components, rerun `scripts/setup-windows.ps1` to refresh
  environment variables and `gradle/os-paths.properties`.
- The script sets `WSLENV=ANDROID_SDK_ROOT/p:JAVA_HOME/p:GRADLE_USER_HOME/p[:ANDROID_NDK_ROOT/p]`
  so WSL shells inherit the same locations automatically.
- From WSL, run `./gradlew <task>`; Gradle will resolve the toolchains inside `toolchains/`.

## Extending Further

- Add CI-specific prefixes (e.g. `ci.windows.*`) if hosted agents need different paths.
- Track additional tools by introducing new property keys and extending the init script.
- Consider adding validation tasks that assert the resolved locations exist to catch drift early.
