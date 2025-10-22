# `resources/assets`

- `dexopt/baseline.prof` and `baseline.profm` contain ART profile data used by Android Runtime to warm up hotspots.
- Additional subdirectories are empty placeholders from the original build; PURE doesn’t ship runtime assets beyond the
  baseline profile.
- Safe to regenerate via `profileinstaller` if you rebuild the app.
- During development you can update these by running the `profileinstaller` instrumentation test or using
  `adb shell cmd package compile -m speed-profile` to collect fresh profile data from device usage.
