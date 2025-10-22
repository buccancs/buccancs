# `sources/junit`

- Bundled JUnit 3/4 APIs required by embedded test utilities (some Google/gRPC code references Assert/Test).
- PURE does not execute these on device; they are present so the decompiled classes compile.
- Buccancs should depend on the official `junit` artifact instead of these copies.
- Classes include `junit.framework.Assert`, `junit.framework.TestCase`, and adapters so legacy code can run under
  JUnit4. They are unchanged from upstream JUnit 4.13.
- Keep in mind that shipping these sources inside your APK provides no runtime value—Gradle’s test configuration should
  pull the proper JUnit jars for unit tests, while the final Android app typically strips them out.
