# Contributing Guide

This guide captures expectations for code quality, documentation style, and collaboration on the BuccanCS platform.

## Workflow Expectations

- Work on feature branches; keep commits focused and well-described.
- Run targeted Gradle tasks (`build`, module-specific assemble/test) before opening a pull request.
- Document notable behavioural changes in the pull request description and link to relevant manual drills when
  applicable.
- Keep the root `readme.md` and this documentation set current; avoid recreating sprawling report directories.

## Coding Standards

- Kotlin code follows Jetpack Compose and MVVM patterns with Hilt for dependency injection.
- Apply the Result pattern consistently for error handling and provide explicit state machines for hardware connectors.
- Minimise comments; prefer self-explanatory code. Add concise comments only when behaviour is non-obvious.
- Maintain ASCII-only source files unless interacting with third-party assets that already use other character sets.
- Use idiomatic coroutines and structured concurrency—avoid unmanaged threads.

## Documentation Standards

- Use British English spelling across documentation, code comments, and commit messages (e.g., *synchronise*, *colour*).
- Store new documentation alongside these guides in `docs/`; keep filenames descriptive without redundant timestamps and
  add new entries to `docs/readme.md`.
- Include concise context at the top of longer documents (purpose, audience, last updated).
- Update existing sections rather than scattering similar information across multiple files.
- Keep `docs/requirements.md` aligned with current delivery status and acceptance notes whenever behaviour shifts.

## Testing Expectations

- Execute relevant unit and instrumentation tests for the modules you touch. Note any temporarily skipped suites in the
  pull request.
- Run at least one manual drill (calibration, stress, or recovery) when modifying recording pipelines or networking.
- Capture logs or artefacts that support fixes and attach them to issues where possible.

## Pull Request Checklist

- [ ] Code compiles for the affected modules (`gradlew.bat build` or targeted task).
- [ ] Tests run or are explicitly justified if skipped.
- [ ] Documentation updates accompany user-facing changes.
- [ ] Breaking changes or follow-up work are recorded in an issue / TODO.
- [ ] Reviewers have clear reproduction steps or validation instructions.


