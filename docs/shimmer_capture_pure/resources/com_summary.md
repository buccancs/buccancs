# `resources/com`

- Contains only package-level `MANIFEST.MF` files for Shimmer’s biophysical processing jar.
- No runtime assets; included by the build to preserve OSGi-style metadata when the code was packaged as a library.
- You can remove these manifests if you rebuild the algorithms as standard Android/Kotlin modules—they are remnants from
  historic Java packaging.
