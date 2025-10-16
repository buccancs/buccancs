# Automation Tools

Simple automation for GitHub Copilot CLI continuation.

## Usage

```bash
# Continue with default prompt
./continue.sh

# Continue with custom prompt
./continue.sh "fix the build errors and run tests"
```

## Default Prompt

The script uses a standard continuation prompt that requests:

- Continue with next steps
- Fix remaining issues or errors
- Follow Kotlin Jetpack Compose Material 3 best practices
- Implement missing tests
- Remove outdated code
- Update documentation

## Logs

Session timestamps are logged to `/mnt/c/dev/buccancs/logs/last_session.txt`
