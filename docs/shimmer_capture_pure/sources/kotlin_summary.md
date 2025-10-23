# `sources/kotlin`

- Decompiled Kotlin stdlib and intrinsics (e.g., `ArrayIntrinsicsKt`, coroutine helpers, metadata annotations).
- PURE’s Kotlin code (`kotlinx.*`, app fragments) relies on these runtime classes; they mirror the official
  `kotlin-stdlib`.
- Replace with JetBrains’ published artifacts when rebuilding; do not edit manually.
- Look for files ending in `Kt.java`: these are the result of decompiling Kotlin extension functions and companion
  object helpers. They expose the same API surface as the original Kotlin stdlib.
- Mixing these copies with the official stdlib leads to method duplication; ensure only one source of Kotlin runtime is
  present in your build. In Gradle, add `implementation "org.jetbrains.kotlin:kotlin-stdlib"` and delete this folder.
