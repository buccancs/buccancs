# `sources/bolts`

- Contains the Bolts Task library (`Task`, `TaskCompletionSource`) embedded with the APK.
- PURE uses Bolts for lightweight async coordination (e.g., waiting on gRPC or Verisense protocol operations).
- Buccancs already ships modern Kotlin coroutines; you can replace Bolts usage with suspending APIs if refactoring.
- Key entry points: `bolts.Task`, `bolts.Continuation`, `bolts.CancellationToken`. They provide promise-like constructs
  for chaining background work without needing a full executor framework.
- When translating to coroutines, look for patterns where a `TaskCompletionSource` is set inside callbacks (e.g., gRPC
  connect logic). These map cleanly to `suspendCancellableCoroutine { }` or to `CompletableDeferred`.
