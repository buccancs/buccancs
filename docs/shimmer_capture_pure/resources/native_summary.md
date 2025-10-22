# `resources/native`

- Prebuilt HIDAPI JNI binaries for macOS and Windows (32/64-bit). Used by Shimmer’s desktop tooling and gRPC BLE bridge
  when running off-device.
- Android runtime ignores these; keep only if you maintain cross-platform tooling that reuses the APK assets.
- If you create new desktop utilities, consider distributing these DLLs/JNILIBs separately instead of embedding them in
  the Android APK to reduce package size.
