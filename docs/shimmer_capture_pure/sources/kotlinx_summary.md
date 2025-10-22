# `sources/kotlinx`

- Kotlin coroutines runtime (channels, flows, dispatchers, debugging hooks) bundled with the APK.
- PURE uses coroutines in select utility classes; most app code relies on Java handlers instead.
- Prefer official `kotlinx-coroutines-android` dependency in Buccancs instead of editing these sources.
- Subpackages mirror the upstream library: `kotlinx.coroutines.channels`, `kotlinx.coroutines.flow`,
  `kotlinx.coroutines.sync`, etc. They provide implementations for `Channel`, `Mutex`, `Flow`, and dispatchers like
  `Dispatchers.IO`.
- If you migrate to the official artifact, verify that PURE code does not depend on any relocated package names (the
  decompiled sources sometimes inline `DebugProbes`). JetBrains publishes `kotlinx-coroutines-android` with identical
  APIs, so swapping should be safe.
