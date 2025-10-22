# `resources/META-INF`

- APK signing metadata (`CERT.RSA`, `CERT.SF`, `MANIFEST.MF`).
- Version marker files for bundled AndroidX/Jetpack libraries (e.g., `androidx.appcompat_appcompat.version`).
- Keep for reference only—Android Studio will regenerate when you rebuild from source.
- When unbundling dependencies, check the `.version` files to see which library revisions shipped in the APK; they can
  guide you on selecting compatible Gradle dependencies.
