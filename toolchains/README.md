# Local Toolchains

This directory is intentionally git-ignored so each developer (and CI agent)
can stage platform toolchains alongside the repository checkout without
polluting commits.

Expected layout:

```
toolchains/
  android-sdk/         # Android SDK + build-tools + platform-tools
  android-ndk/         # Optional (only if NDK is required)
  java/                # JDK/JRE used by Gradle/Android tooling
  gradle-user-home/    # Gradle caches and wrapper downloads
```

Populate the subdirectories with your local installs or update
`gradle/os-paths.properties` to point elsewhere. The Gradle init script
(`gradle/init.d/os-setup.gradle.kts`) automatically resolves these locations
and seeds `local.properties` during builds.

Because this folder is ignored, you can safely drop extracted SDK/NDK/JDK
archives here, and the repository will remain clean.
