# `sources/javax`

- `javax.annotation` – nullability and lifecycle annotations copied from the JSR-305 spec; used by gRPC and other
  libraries.
- `javax.vecmath` – lightweight math primitives (vectors, matrices, quaternions) leveraged by Shimmer algorithms for
  orientation and calibration routines.
- No PURE-specific behavior; rely on upstream javax artifacts where licensing permits.
- `javax.vecmath` is particularly important if you port algorithms like Madgwick/Quaternion-based fusion from
  `com.shimmerresearch.algorithms`. Those classes expect the same method signatures as the original Java 3D vecmath
  package.
- If licensing is a concern, consider replacing vecmath usage with open alternatives (`androidx.math` or custom structs)
  and update the dependent algorithm classes accordingly.
