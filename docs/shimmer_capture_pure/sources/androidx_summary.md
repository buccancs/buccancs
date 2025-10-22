# `sources/androidx`

- Jetpack support shims (AppCompat, Fragment, ViewPager, Core) that were inlined by the decompiler.
- These mirror real AndroidX APIs but only keep enough code to satisfy references from PURE (e.g., fragment
  transactions, lifecycle dispatch).
- When porting functionality, use proper AndroidX libraries instead of these decompiled artifacts.
- Expect to find minimal implementations of classes like `androidx.fragment.app.Fragment`,
  `androidx.lifecycle.LifecycleOwner`, `androidx.core.content.ContextCompat`, etc. They frequently reference the same
  package names as the upstream Jetpack libraries, so attempting to bundle them alongside the real artifacts will cause
  duplicate-class errors.
- PURE relied on these copies because the original APK was processed through tools that merged dependencies; Buccancs
  should depend on Gradle-managed AndroidX libraries to receive bug fixes, resources, and ProGuard rules.
