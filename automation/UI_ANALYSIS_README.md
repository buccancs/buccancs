# UI to AI Conversion - Quick Start

## Overview

This project now includes automated UI analysis that converts rendered Android UI into AI-understandable formats to detect layout issues like:

- ❌ Text truncation (text cut off or ending with "...")
- ❌ Button text wrapping to multiple lines  
- ❌ Touch targets that are too small (< 48dp)
- ❌ Overlapping clickable elements
- ❌ Missing accessibility labels

## Quick Usage

### 1. Capture UI State

```bash
# Connect your Android device/emulator
adb devices

# Dump UI hierarchy
adb shell uiautomator dump
adb pull /sdcard/window_dump.xml

# Capture screenshot
adb shell screencap /sdcard/screen.png
adb pull /sdcard/screen.png
```

### 2. Analyze UI

```bash
# Run analyzer
python3 automation/ui_analyzer.py window_dump.xml --screenshot screen.png

# Outputs:
# - automation/reports/ui-analysis.json (machine-readable)
# - automation/reports/ui-analysis.html (human-readable)
# - automation/reports/ai-prompt.txt (ready for GPT-4/Claude)
```

### 3. Review Results

Open `automation/reports/ui-analysis.html` to see detected issues with severity, descriptions, and fix suggestions.

### 4. Get AI Insights

```bash
# Copy AI prompt
cat automation/reports/ai-prompt.txt | pbcopy

# Paste into ChatGPT, Claude, or GitHub Copilot
```

## Example Output

The analyzer found **35 issues** in the Devices screen, including:

- **23 HIGH**: Text truncation in labels and buttons
- **12 MEDIUM**: Small touch targets and missing accessibility labels

See `automation/reports/ui-analysis.html` for the full visual report.

## Learn More

- [Full Guide](../docs/UI_TO_AI_CONVERSION_GUIDE.md) - Complete documentation
- [Analyzer Script](ui_analyzer.py) - Python implementation

## Quick Demo

```bash
# Analyze existing UI dumps in this repository
python3 automation/ui_analyzer.py window_dump_devices.xml \
    --screenshot phone_screen.png \
    --output automation/reports
```
