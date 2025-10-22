# `resources/grpc`

- Protobuf definitions for gRPC subsystems (binlog, channelz, health checks, reflection, ALTS, load balancing).
- Needed because PURE ships the gRPC runtime and its supporting protos directly within the APK.
- No Shimmer-specific content—matches upstream gRPC distribution.
- If you enable server reflection or health checks in Buccancs, ensure the corresponding `.proto` files remain available
  on the classpath or regenerate them with your chosen gRPC version.
