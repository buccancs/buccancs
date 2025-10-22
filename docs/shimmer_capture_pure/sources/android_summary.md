# `sources/android`

- Decompiled android framework stubs (e.g., `R$style`, `Build$VERSION`, handler/IBinder helpers) used so the
  reconstructed APK compiles without depending on the actual platform jars.
- Provides constants and thin wrappers referenced throughout the app; none of these classes contain product logic—treat
  them as placeholders for the SDK.
- Safe to ignore for integration work; rely on the real Android SDK in Buccancs builds.
- Typical files you will see here: `android/os/Handler.java`, `android/app/AlertDialog.java`, `android/util/Log.java`.
  They only expose method signatures and minimal bodies (often throwing `RuntimeException` if invoked). The PURE sources
  never ship these in release builds.
- If you encounter compilation errors while porting, remove these stubs from the classpath and depend on the official
  Android SDK/Jetpack artifacts instead. Mixing the stubbed classes with real SDK jars can lead to confusing
  duplicate-class errors.
