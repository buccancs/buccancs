# `sources/pl`

- `pl.flex_it.androidplot` – Extensions around the AndroidPlot library. `MultitouchPlot` enables pinch/drag gestures,
  while `XYSeriesShimmer` wraps sensor data with auto-clearing bounds for the live graph.
- `pl.edu.icm.jlargearrays` – Large-array utilities (off-heap buffers, FFT-friendly arithmetic) used by legacy
  signal-processing modules; PURE only references a subset for efficient math.
- `jlargearrays` introduces custom array types (`DoubleLargeArray`, `FloatLargeArray`) that can exceed JVM heap limits
  and provide vectorised math routines. In PURE they show up primarily in older EXG/IMU processing code paths.
- If you do not rely on these heavy utilities, you can often replace them with standard Java arrays or Kotlin
  collections, but carefully check algorithm performance expectations before doing so.
