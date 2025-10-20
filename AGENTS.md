# Workspace guardrails

- Ignore generated or transient assets under `build/`, `dist/`, `out/`, `bin/`, or `tmp/` directories when summarizing or editing.
- Skip caches and tooling state such as `.cache/`, `.gradle/`, `.gradle-tmp/`, `.idea/`, `.vscode/`, and `.terraform/`.
- Treat dependency payload directories (`node_modules/`, `vendor/`, `.venv/`, `__pycache__/`) as read-only; never attempt to edit files inside them.
- Exclude VCS and artifact directories like `.git/`, `.svn/`, `.hg/`, or coverage reports under `coverage/`.
- Prefer working with source files inside `app/`, `core/`, `domain/`, and other first-party code paths unless a prompt explicitly calls for assets elsewhere.
