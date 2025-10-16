# Tools

Development and testing utilities for the project.

## Structure

- `automation/` - Simple continuation script for GitHub Copilot CLI
- `build/` - Build automation and SDK installation scripts
- `perf/` - Performance analysis and stress testing tools
- `tests/` - System testing scripts

## Quick Reference

### Automation

```bash
cd automation
./continue.sh                              # Continue with default prompt
./continue.sh "custom prompt"              # Continue with custom prompt
```

### Build

```bash
cd build
./creeping_build.sh                        # Incremental build with detailed logs
./install_sdk_packages.sh                  # Install required Android SDK packages
```

### Performance

```bash
cd perf
./analyze_metrics.py --input metrics.jsonl # Analyse performance metrics
./multi_device_stress.sh --duration 300    # Run stress test across devices
```

### Tests

```bash
cd tests
./offline_recovery.sh <device-serial>      # Test offline recovery behaviour
```

## Documentation

Each subdirectory contains its own README with detailed usage instructions.
