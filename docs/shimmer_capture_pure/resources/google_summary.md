# `resources/google`

- Full set of protobuf descriptors from Google APIs (API annotations, logging, RPC status, long-running ops, etc.).
- Required by the generated gRPC and telemetry classes packaged in PURE.
- Leave untouched unless you regenerate the .proto sources; they mirror the upstream Google API definitions.
- Examples include `google/api/annotations.proto`, `google/rpc/status.proto`, and `google/type/latlng.proto`. The gRPC
  runtime loads these descriptors to support extended messages used by Google’s client libraries.
- If you regenerate protobufs with a newer Google API release, ensure both the `.proto` files and the generated Java
  sources under `sources/google` stay in sync.
