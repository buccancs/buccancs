# Desktop ↔ Android Connection Suite

Cross-platform
entry
point
for
validating
that
the
desktop
orchestrator
and
the
Android
agent
can
discover
each
other,
grant
runtime
permissions,
and
establish
a
gRPC
session.

## Run The Suite

```bash
./automation/suites/connection/test_desktop_android_connection.sh
```

The
wrapper
hands
off
to
the
actively
maintained
script
in
`automation/suites/desktop/`
so
existing
automation
keeps
working.
Supply
`AVD_NAME` (
defaults
to
`Pixel_9a`)
when
targeting
a
different
emulator:

```bash
AVD_NAME="Pixel_8" ./automation/suites/connection/test_desktop_android_connection.sh
```

## What It Does

1.

Cleans
up
stray
emulator
and
Gradle
processes.

2.

Boots
the
requested
Android
Virtual
Device.

3.

Grants
the
Buccancs
app
all
declared
runtime
permissions (
Bluetooth,
camera,
microphone,
background
location,
media
access,
notifications,
and
phone
state).

4.

Launches
the
Android
agent
and
the
desktop
orchestrator.

5.

Waits
for
the
desktop
logs
to
report
a
connected
Android
device.

## Artefacts

Each
run
writes
into
`automation/suites/logs/desktop/run-<timestamp>/`:

-

`desktop_test_output.log` –
Desktop
orchestrator
output.

-

`test_android_screen.png` –
Screenshot
captured
immediately
after
launching
the
Android
agent.

-

`test_android_connected.png` –
Screenshot
captured
once
the
connection
is
confirmed.

Remove
old
run
directories
as
needed;
nothing
in
this
path
is
committed
to
git.

## Prerequisites

-

JDK
21
and
the
Android
SDK
Platform
36 (
with
licences
accepted).

-

Emulator
image
named
`Pixel_9a` (
or
use
`AVD_NAME`
to
override).

-

No
other
emulator
instance
or
`:desktop:run`
process
holding
port
`50051`.

-

Repository
checked
out
at
the
workspace
root
so
`./gradlew`
resolves.

## Troubleshooting

-

*

*
SDK
not
found
** –
Set
`ANDROID_HOME` /
`ANDROID_SDK_ROOT`
or
install
the
SDK
under
the
default
paths
documented
in
`automation/suites/lib/android_env.sh`.

-

*

*
Permission
grant
warnings
** –
Harmless
on
Android
13+
when
a
permission
no
longer
applies;
Bluetooth,
camera,
location,
and
notifications
must
still
succeed.

-

*

*
Connection
timeout
** –
Inspect
`desktop_test_output.log`
for
registration
lines,
and
confirm
the
emulator
can
resolve
the
desktop
host (
mDNS
or
manual
address).

-

*

*
Port
50051
busy
** –
Run
`lsof -i :50051` (
macOS/Linux)
or
`netstat -aon | find "50051"` (
Windows)
and
terminate
the
conflicting
process.
