# `sources/org`

- Aggregates third-party Java libraries: Apache Commons (codec, io, lang), BouncyCastle crypto, Hamcrest matchers,
  Conscrypt TLS helpers, IntelliJ annotations, etc.
- Also includes scientific/math utilities (`jtransforms`, `joda`) and Android support metadata.
- PURE does not modify these packages; treat them as vendored dependencies when mapping features into Buccancs.
- Useful to know which app subsystems touch these libraries:
    - `org.apache.commons.lang3/StringUtils` appears in many UI/logging utilities.
    - `org.bouncycastle` underpins TLS examples used by the gRPC cert tooling.
    - `org.jtransforms` and `org.apache.commons.math3` support DSP routines in `com.shimmerresearch.algorithms`.
- When rebuilding from source, pull the official Maven artifacts instead of copying these classes; it keeps licensing
  clear and allows security updates.
