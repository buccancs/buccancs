# Development Environment

This guide consolidates every active note about provisioning BuccanCS toolchains
across Windows, Linux/WSL, and macOS hosts. Use it alongside the bootstrap
scripts; no other documentation is required.

## 1. Toolchain Layout

All developer installs live under the untracked `toolchains/` directory at the
repository root:

```
toolchains/
  android-sdk/         # Android SDK (platform-tools, build-tools, platforms)
  android-ndk/         # Optional; empty unless native work resumes
  java/                # Temurin JDK 21
  gradle-user-home/    # Gradle caches and wrapper distributions
  downloads/           # Cached archives for re-runs
```

Gradle’s init script (`gradle/init.d/os-setup.gradle.kts`) resolves paths in the
following order on every invocation:

1. Environment variables (`ANDROID_SDK_ROOT`, `JAVA_HOME`, `GRADLE_USER_HOME`,
   optional `ANDROID_NDK_ROOT`)
2. `gradle/os-paths.properties`
3. Default `toolchains/*` directories

The script updates `local.properties` automatically so Android Studio/Gradle
stay in sync across operating systems.

## 2. Bootstrap Scripts (Recommended)

| Host | Command |
|------|---------|
| Windows PowerShell | `pwsh -File scripts/bootstrap-toolchains.ps1` |
| Linux / WSL | `bash ./scripts/bootstrap-toolchains.sh` |

Both scripts:

- Create the directory structure above.
- Download Android command-line tools (`11076708`) and Temurin JDK `21.0.4_7`.
- Install SDK packages: `platform-tools`, `build-tools;36.1.0`, `platforms;android-36`.
- Normalise the `cmdline-tools/latest` layout.
- Configure Gradle (`gradle/os-paths.properties`) and environment variables.
- Accept Android licences automatically.

Pass `-ForceRedownload` (PowerShell) or `FORCE_REDOWNLOAD=true` (Bash) to refresh
existing installs. All parameters/variables remain documented within the script
headers.

## 3. Windows Notes

- Bootstrap sets user-level variables:
  `ANDROID_SDK_ROOT`, `ANDROID_NDK_ROOT`, `JAVA_HOME`,
  `GRADLE_USER_HOME`, and `WSLENV=ANDROID_SDK_ROOT/p:JAVA_HOME/p:GRADLE_USER_HOME/p:ANDROID_NDK_ROOT/p`.
- Restart PowerShell after running the script so the new environment values are
  applied.
- Topdon TC001 requires the vendor USB driver for reliable streaming; install it
  before testing hardware.
- If you keep external installs, run `scripts/setup-windows.ps1` with custom
  paths to update the Gradle properties without touching downloads.

## 4. Linux & WSL Notes

- Ensure `curl`, `tar`, and `unzip` are installed (`sudo apt install curl tar unzip`).
- WSL can reuse the Windows-managed Android SDK if the drive is mounted; the
  init script converts `C:\` paths into `/mnt/c/...`.
- After bootstrap, append the exports printed by `scripts/setup-wsl.sh` to your
  shell profile if you want permanent environment variables.
- When Gradle runs slowly under WSL2, perform heavy builds from native Windows
  shells and reserve WSL for scripting.

## 5. Manual Configuration (Any OS)

1. Populate `toolchains/` with your existing SDK/JDK or symlink the directories.
2. Copy `gradle/os-paths.sample.properties` to `gradle/os-paths.properties` and
   update the platform-specific entries.
3. Optionally set environment variables when launching IDEs or shells:

   ```bash
   export ANDROID_SDK_ROOT=/path/to/toolchains/android-sdk
   export JAVA_HOME=/path/to/toolchains/java
   export GRADLE_USER_HOME=/path/to/toolchains/gradle-user-home
   ```

4. Run any Gradle task; the init script will refresh `local.properties`.

## 6. Verification Checklist

```powershell
# Windows PowerShell
& "$env:JAVA_HOME\\bin\\java.exe" -version
& "$env:ANDROID_SDK_ROOT\\cmdline-tools\\latest\\bin\\sdkmanager.bat" --list
.\gradlew tasks
```

```bash
# Linux / WSL
$JAVA_HOME/bin/java -version
$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --list
./gradlew tasks
```

Confirm that `toolchains/gradle-user-home/wrapper/dists` contains the Gradle
distribution after the first run.

## 7. Troubleshooting

- **Bootstrap download failures** – Check connectivity; manual downloads placed
  in `toolchains/downloads/` are reused automatically.
- **Empty extraction directory** – Scripts remove and re-extract if an archive
  produced an empty folder; rerun with `-ForceRedownload` if corruption persists.
- **SDK licence prompts** – Run the platform-specific `sdkmanager --licenses`
  command; the bootstrap scripts already accept known licences.
- **WSL path issues** – Re-run `scripts/setup-windows.ps1` to refresh `WSLENV`
  and confirm WSL shells inherit the Windows paths.
- **Gradle cache cleanup** – Delete `toolchains/gradle-user-home/caches/` (safe;
  Gradle will repopulate).

## 8. Maintenance

- Re-run the bootstrap script whenever upgrading Android build tools or the JDK.
- Keep `gradle/os-paths.properties` under version control when host-specific
  overrides change.
- Record toolchain upgrades in `docs/planning/active-plan.md` (or the relevant
  sprint note) so other contributors know to refresh their environments.
