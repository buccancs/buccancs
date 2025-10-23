# `sources/io`

- Full gRPC Java runtime (`io.grpc.*`), including channel abstractions, interceptors, stubs, and binary logging. PURE
  embeds these so the app can act as both client and server without bundling external jars.
- `io.opencensus` and `io.perfmark` are telemetry helpers used by gRPC for tracing/metrics; no Shimmer-specific code
  lives here.
- Treat this tree as third-party infrastructure—Buccancs should depend on official gRPC artifacts rather than copying
  these classes.
- Notable packages:
    - `io.grpc.internal` houses transport implementations, flow-control, and executor pools.
    - `io.grpc.stub` defines the base classes for generated client/server stubs that PURE’s `com.shimmerresearch.grpc`
      package extends.
    - `io.grpc.netty.shaded` provides the Netty-based HTTP/2 transport; its presence is why the APK can speak gRPC
      without system libraries.
- Because these classes are large and tightly coupled, avoid editing them. Upgrading gRPC in Buccancs should be done via
  dependency management (Gradle/Maven) rather than by modifying the copied sources.
