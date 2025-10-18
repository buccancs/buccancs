# UI-To-AI Conversion

Convert
captured
Android
UI
states
into
AI-ready
artefacts
so
layout
and
accessibility
regressions
surface
quickly.

## Workflow At A Glance

1.

*

*
Capture
the
UI
**

```bash
adb devices
adb shell uiautomator dump
adb pull /sdcard/window_dump.xml
adb shell screencap /sdcard/screen.png
adb pull /sdcard/screen.png
```

2.

*

*
Run
the
analyser
**

```bash
python3 automation/ui/ui_analyzer.py window_dump.xml \
    --screenshot screen.png \
    --output automation/ui/samples/latest
```

    -
    `ui-analysis.json` –
    machine-readable
    issue
    list
    -
    `ui-analysis.html` –
    human-friendly
    report
    -
    `ai-prompt.txt` –
    ready-made
    assistant
    prompt

3.

*

*
Review &
Iterate
**
-
Open
`automation/ui/samples/latest/ui-analysis.html`
in
your
browser
to
inspect
severity,
descriptions,
and
suggested
fixes.
-
Share
`automation/ui/samples/latest/ai-prompt.txt`
with
Codex,
Copilot,
or
Gemini
for
targeted
remediation
guidance.

## Example Run

Sample
output
from
the
Devices
screen
lives
in
`automation/ui/samples/devices/`
and
highlights:

-

*

*
23
high
**:
Text
truncation
across
buttons
and
labels

-

*

*
12
medium
**:
Undersized
touch
targets
and
missing
accessibility
labels

Use
these
artefacts
as
a
reference
when
integrating
the
analyser
into
CI.

## Further Reading

-

`docs/UI_TO_AI_CONVERSION_GUIDE.md` –
Comprehensive
guide (
archived
but
still
accurate
for
the
workflow).

-

`automation/ui/ui_analyzer.py` –
Implementation
details
and
extension
points.
