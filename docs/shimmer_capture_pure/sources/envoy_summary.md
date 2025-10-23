# `sources/envoy`

- Proto annotations embedded with the gRPC tooling (e.g., `@Resource`, `@Deprecation`). They originate from Envoy’s API
  definitions.
- Only support generated stubs under `com.google`/`io.grpc`; PURE does not add any logic here.
- The files expose static descriptors and extension fields that gRPC uses when parsing Envoy/XDS protos. They are
  auto-generated from `.proto` files such as `envoy/annotations/resource.proto`.
- No PURE code references these classes directly; they are simply required on the classpath whenever the gRPC runtime
  initialises descriptors that depend on Envoy annotations.
