# `sources/okio`

- Square’s Okio I/O primitives (buffers, timeouts, UTF-8 helpers) shipped inline with the APK.
- Used by OkHttp/gRPC paths (buffered sinks/sources, Deflater/Inflater wrappers) and occasionally by Shimmer logging
  utilities.
- Functionality is unchanged from upstream Okio 1.x—replace with the official library when possible instead of modifying
  these decompiled versions.
- Core classes to be aware of: `okio.Buffer` (the segmented byte buffer), `RealBufferedSource/RealBufferedSink` (
  wrappers providing buffered I/O), `AsyncTimeout`/`Timeout` (deadline helpers), and `Okio` factory methods.
- Because these classes are performance critical, avoid editing them directly. If Buccancs already depends on
  OkHttp/Okio, remove this directory and rely on the managed dependency to prevent duplicate symbols.
