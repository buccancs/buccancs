# `resources/androidsupportmultidexversion.txt`

- A single-line marker storing the git hash of the embedded multidex support library.
- Informational only; new builds with Gradle’s multidex plugin will emit their own version file.
- If you disable multidex, this asset can be removed. Otherwise, Gradle automatically packages an updated hash when you
  depend on `androidx.multidex:multidex`.
