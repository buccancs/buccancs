# `resources/kotlin`

- Kotlin builtins metadata (`*.kotlin_builtins`) required by the Kotlin runtime for reflection and type inference.
- Produced automatically when packaging Kotlin libraries.
- Keep them aligned with the Kotlin stdlib version you ship; mismatched builtins can cause
  `Incompatible Class Change Error` during reflection-heavy operations.
