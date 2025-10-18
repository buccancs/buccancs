# Platform Requirements

This
summary
captures
the
functional
and
non-functional
requirements
that
guided
the
original
BuccanCS
implementation.
Each
entry
lists
the
intent,
essential
acceptance
notes,
and
the
present
delivery
state
so
the
team
can
track
what
still
needs
hardening
before
field
studies.

## Functional Requirements

-

*

*FR1 –
Multi-device
sensor
integration
**  
The
platform
must
manage
Shimmer3
GSR
sensors,
the
Topdon
TC001
thermal
camera,
on-device
RGB
camera,
microphone,
and
simulation
fallbacks.  
*Status:*
Complete –
connectors,
simulators,
and
desktop-driven
discovery
are
live
for
every
supported
modality.

-

*

*FR2 –
Synchronised
recording
control
**  
One
start/stop
action
triggers
every
enabled
modality
to
record
against
a
shared
session
clock.  
*Status:*
Complete –
shared
anchors,
orchestration
hooks,
and
recording
exercises
have
been
validated
across
devices.

-

*

*FR3 –
Time
synchronisation
service
**  
Desktop
provides
the
reference
clock;
Android
devices
maintain
offsets
within
≈5
ms.  
*Status:*
Complete –
Android
clients
continually
apply
offsets
and
the
desktop
service
sustains
sub-5
ms
drift.

-

*

*FR4 –
Session
lifecycle
management
**  
Sessions
require
unique
manifests
with
start/end
markers,
duration,
and
bookmark
metadata.  
*Status:*
Complete –
manifest
writer,
storage,
and
concurrent
desktop
workflows
have
been
verified.

-

*

*FR5 –
High-fidelity
data
recording
**  
Simultaneous
GSR (
128
Hz),
RGB
video (
1080p/30
FPS),
thermal
frames,
and
audio
must
be
stored
without
loss.  
*Status:*
Complete –
all
connectors
record
concurrently
with
manifest-linked
metadata
and
sustained
stress
coverage.

-

*

*FR6 –
Monitoring
user
interface
**  
Desktop
UI
must
list
devices,
expose
session
controls,
raise
alerts,
and
render
live
RGB/thermal
previews.  
*Status:*
Complete –
Compose
Desktop
renders
devices,
previews,
alerts,
and
controls
with
the
production
bridge.

-

*

*FR7 –
Stimulus
broadcasting
and
markers
**  
Desktop
issues
sync
flashes,
auditory
cues,
and
stimulus
markers
that
every
agent
logs
against
the
shared
timeline.  
*Status:*
Complete –
command
pipeline,
cue
presentation,
and
logging
are
synchronised
across
all
agents.

-

*

*FR8 –
Fault
tolerance
during
sessions
**  
Individual
device
failures
should
not
abort
the
session;
reconnecting
devices
resynchronise
and
honour
missed
commands.  
*Status:*
Complete –
heartbeats,
reconnection,
and
recovered
command
replay
are
covered
in
automated
and
manual
drills.

-

*

*FR9 –
Calibration
utilities
**  
Provide
guided
RGB/thermal
calibration
with
quality
thresholds
and
persisted
results.  
*Status:*
Complete –
the
calibration
wizard,
quality
gates,
and
persisted
metrics
now
operate
end-to-end.

-

*

*FR10 –
Post-session
data
aggregation
**  
Android
uploads
every
artefact,
desktop
reconciles
them
against
the
manifest
with
retries
for
failures.  
*Status:*
Complete –
upload
pipeline,
reconciliation,
and
retry
telemetry
are
proven
against
recovery
drills.

## Non-Functional Requirements

-

*

*NFR1 –
Real-time
throughput
**  
Must
handle
concurrent
128
Hz
sensors
and
30
FPS
video
plus
previews
without
drops.  
*Status:*
Complete –
buffered
pipelines
and
soak
testing
sustain
target
loads
without
frame
loss.

-

*

*NFR2 –
Temporal
accuracy
**  
Maintain
≤10
ms
drift (
target
5
ms)
across
devices
throughout
a
session.  
*Status:*
Complete –
time-sync
monitoring
keeps
offsets
within
5
ms
with
automated
alerting.

-

*

*NFR3 –
Reliability
**  
Recording
survives
network
interruptions;
data
already
written
stays
valid.  
*Status:*
Complete –
incremental
writers,
retry
paths,
and
recovery
logging
cover
expected
failure
modes.

-

*

*NFR4 –
Data
integrity
**  
Validate
sensor
ranges,
checksum
artefacts,
and
reconcile
manifests
automatically.  
*Status:*
Complete –
checksum
enforcement,
reconciliation,
and
range
validation
run
as
part
of
the
recording
pipeline.

-

*

*NFR5 –
Security
**  
Employ
authenticated,
encrypted
channels
and
warn
when
protections
lapse.  
*Status:*
Complete –
TLS
enforcement,
token
verification,
and
operator
warnings
cover
the
full
transport
chain.

-

*

*NFR6 –
Observability &
usability
**  
Deliver
metrics
dashboards,
operational
warnings,
and
operator
guidance
suitable
for
non-developers.  
*Status:*
Complete –
dashboards,
operator
cues,
and
health
monitoring
meet
non-developer
workflow
needs.

-

*

*NFR7 –
Scalability
**  
Support
multiple
agents
and
long
sessions
with
graceful
degradation
rather
than
failure.  
*Status:*
Complete –
retention
policies,
multi-device
exercises,
and
graceful
degradation
paths
are
validated.

-

*

*NFR8 –
Maintainability
**  
Keep
modules
loosely
coupled
with
configuration-driven
behaviour
and
solid
test
coverage.  
*Status:*
Complete –
modular
architecture,
configuration-driven
features,
and
the
reinstated
tests
cover
maintainability.

## Using This Document

-

Update
the
relevant
requirement
entry
whenever
behaviour,
acceptance
tests,
or
status
changes.

-

Link
new
validation
evidence (
logs,
test
artefacts)
from
`docs/testing.md`
so
future
reviewers
can
trace
compliance.

-

If
new
requirements
emerge,
add
them
here
and
update
`docs/index.md`
to
reflect
the
expansion.


