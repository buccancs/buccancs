# `resources/javax`

- Resource copies of the javax annotation sources packaged with certain libraries for reflective use.
- Mirrors the classes in `sources/javax`; primarily used by tools that load annotations via classpath resources.
- You only need these if a library expects to read annotation bytecode via `ClassLoader.getResourceAsStream`. When
  replacing the Javax artifacts with Gradle dependencies, this folder can normally be removed.
