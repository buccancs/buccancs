# `resources/opencensus`

- Placeholder for OpenCensus resource descriptors bundled with gRPC. Mostly empty directories retaining package
  structure.
- Keep aligned with the gRPC version you ship; no Shimmer-specific data inside.
- If you remove the OpenCensus dependency (e.g., migrate to OpenTelemetry), you can drop these directories entirely.
