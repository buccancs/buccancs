TODO:

# Requirements and Analysis

## Functional Requirements

**FR1** *(Platform: PC, Android; Priority: Essential; Verification:
Test) -- Multi-Device Sensor Integration:* The system shall support
connecting and managing multiple sensor devices simultaneously. This
includes discovering and pairing **Shimmer3 GSR** sensors via Bluetooth
(either directly to the PC or via an Android device such as a *Samsung
S22* smartphone). Additionally, the Android app shall accommodate
external peripherals (for example, a *Topdon TC001* thermal camera
attached via USB-C) alongside the phone's built-in sensors (e.g. the
rear RGB camera). If no physical sensor is connected, the system shall
provide a simulation mode that generates dummy sensor data for testing
purposes.

**Acceptance Criteria:** The system successfully discovers and connects
to all available Shimmer GSR devices (whether through the PC or an
Android host) and begins streaming their data. If no GSR sensor is
present, the researcher can enable simulation mode and observe
continuous dummy GSR output. In a test scenario with an Android phone
(Samsung S22) connected to a Topdon TC001 thermal camera, the app
detects the thermal camera and simultaneously captures both IR (thermal)
and RGB video streams without user intervention.

**FR2** *(Platform: PC, Android; Priority: Essential; Verification:
Test) -- Synchronised Multi-Modal Recording:* The system shall start and
stop data recording **synchronously** across all connected devices. Upon
receiving a single "Start Recording" command, the PC (acting as the
central orchestrator) must instruct all Android devices and any
connected sensors to begin capturing GSR, video (RGB), thermal (IR), and
any other enabled modalities in parallel. All data streams shall share a
common session timestamp to enable precise later alignment between
modalities.

**Acceptance Criteria:** A single start/stop action from the researcher
initiates or ends recording on all devices almost simultaneously (within
a sub-second window). All recorded data from each device/session is
timestamped against a shared timeline, such that when files are
compared, their start and end times align within the expected small
offset.

**FR3** *(Platform: PC, Android; Priority: Essential; Verification:
Test) -- Time Synchronisation Service:* The system shall synchronise
clocks across devices to ensure time-aligned data collection. The PC
shall run a time synchronisation service (e.g. an NTP-like server), and
each Android device shall periodically calibrate its local clock to the
PC's reference clock. This service must account for network delays and
drift so that all devices maintain a high-precision shared time base.

**Acceptance Criteria:** All devices continuously adjust to the PC's
clock; measured timestamp offsets between any two devices remain very
low (on the order of a few milliseconds, e.g. within \~5 ms and never
exceeding 10 ms) during
recording[\[1\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,recording%2C%20ensuring%20valid%20sensor%20fusion)[\[2\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=clock%20synchronisation%20accuracy%20on%20the,recording%2C%20ensuring%20valid%20sensor%20fusion).
In practice, logs of timestamps from different devices show no
discernible drift over the course of a session.

**FR4** *(Platform: PC; Priority: Essential; Verification: Test) --
Session Management:* The system shall organise recordings into discrete
sessions, each identified by a unique ID or name. The researcher can
create (start) a new session---which the system timestamps---and later
terminate that session when finished. On session start, the PC creates a
dedicated directory and a session metadata file; on session end, it
finalises the metadata (including the session's start time, end time,
and total duration, as well as any noteworthy events). Only one session
may be active at any given time.

**Acceptance Criteria:** Triggering a "new session" in the UI generates
a session folder on the PC and an initial metadata JSON file. Ending the
session updates the metadata with the session's end timestamp and
calculated duration. Attempts to start a second session while one is
already active are disallowed and properly prevented by the system. All
session metadata (start, end, duration, and any marked events during the
session) is correctly recorded.

**FR5** *(Platform: PC, Android; Priority: Essential; Verification:
Test) -- Data Recording and Storage:* For each session, the system shall
record all enabled sensor streams and video/thermal feeds in parallel.
Specifically: **(a)** GSR (galvanic skin response) and any other
physiological channels from the Shimmer sensor(s) at 128 Hz, and **(b)**
video (at least 1920×1080 resolution, 30 FPS) and thermal infrared data
from each Android device. *(For example, if using a Topdon TC001 thermal
camera, it provides thermal images at \~25 Hz with 256×192 resolution.)*
Sensor readings (e.g. GSR samples) shall stream to the PC in real time
and be written to local CSV files immediately as they arrive. Each
Android device shall store its raw video and thermal files locally in
full quality during the recording, and later **transfer** them to the PC
after the session ends. Audio data (e.g. microphone recording at
44.1 kHz) shall also be recorded and synchronised if enabled, following
the same timing and storage requirements.

**Acceptance Criteria:** In a test session, GSR data is logged to the PC
at 128 Hz with no gaps or interruptions, and each Android device records
full-HD RGB video **and** thermal IR video simultaneously; all resulting
files (GSR CSV, video files, thermal images, audio, etc.) exist on disk
after the session completes. No data loss, frame dropping, or excessive
buffering occurs under expected load conditions (e.g. with 3 or more
devices recording concurrently). Furthermore, even when an Android
smartphone is capturing two video streams (RGB and IR) at once, the
system exhibits no frame drops in either stream and maintains
synchronisation between them.

**FR6** *(Platform: PC; Priority: Essential; Verification: Test) -- User
Interface for Monitoring & Control:* The system shall provide a
graphical user interface on the PC for the researcher to control
sessions and monitor the status of all connected devices. The UI shall
list all connected devices and display status indicators for each (e.g.
battery level, connection status, recording state). It must allow the
user to start and stop sessions with a single click and show real-time
session metrics such as elapsed recording time and live sample counts.
If any device disconnects or encounters an error, the UI should clearly
highlight that device and alert the user. Additionally, the UI shall
offer a live preview of each Android device's camera feeds (both the RGB
and IR streams) at a reduced resolution and frame rate, enabling the
researcher to visually monitor the video and thermal streams in real
time without overwhelming the network.

**Acceptance Criteria:** The PC GUI cleanly displays all connected
devices along with their key status information (streaming/recording
state, battery level, etc.), and provides clearly labeled controls (e.g.
"Start Session" / "Stop Session" buttons). UI elements update in real
time based on incoming device data and messages. If a device
disconnects, its entry in the interface is immediately highlighted or
flagged (e.g. shown in red) to alert the researcher. In addition, the
interface successfully shows a live preview video from each device's
camera feeds with minimal lag (e.g. a low-resolution thumbnail updating
in near real-time), allowing the researcher to confirm camera angles and
sensor views during the session. Observing the preview does not
noticeably degrade the system's performance or the quality of the
recorded data.

**FR7** *(Platform: PC, Android; Priority: Important; Verification:
Test) -- Device Synchronisation and Signals:* The system shall
coordinate multiple devices by sending synchronisation commands and
event cues from the PC to all connected endpoints. For example, the PC
should be able to broadcast a synchronization signal (such as a screen
*flash* on all Android displays or an audible *buzzer*) to mark a common
reference event across recordings. Furthermore, the PC shall be capable
of orchestrating external stimuli during a session (such as playing an
emotion-eliciting video on the PC's screen for the participant) and
simultaneously instructing all Android devices to log a timestamped
**event marker** for the stimulus onset (and offset). This ensures that
the timing of any presented stimulus is recorded in all data streams,
allowing correlation between the stimulus and the physiological
responses. The system shall use a JSON-based command protocol so that
all devices can interpret commands (start/stop recording, sync signals,
stimulus events, etc.) and act in unison with minimal delay.

**Acceptance Criteria:** When the researcher activates a synchronisation
control (e.g. presses a "Flash Sync" button), all Android devices
immediately perform the specified action in unison (observable as nearly
simultaneous flashes in the recorded videos) and the event is logged in
the data timeline for later alignment. Similarly, if the researcher
triggers a stimulus presentation (for instance, starting an
emotion-eliciting video on the PC), a corresponding event marker with
the precise timestamp is automatically recorded on each device's data
stream. Verification is achieved by reviewing the recorded data: all
devices' logs show the stimulus marker at the same moment (within the
known synchronisation tolerance), and any video recordings capture the
synchronization signal or stimulus onset as expected.

**FR8** *(Platform: PC, Android; Priority: Important; Verification:
Test) -- Fault Tolerance and Recovery:* The system shall be robust to
device disconnections or failures during a session, continuing to
operate with remaining components if one part fails. If any device
(Android smartphone or sensor) disconnects or stops responding in the
middle of a recording, the PC shall log a warning and mark that device
as offline, but the overall session will continue for all other devices.
Each Android device is expected to continue recording locally even if
its connection to the PC is lost. When a disconnected device reconnects
during the same session, it shall automatically rejoin the session,
re-synchronise its clock, and resume any ongoing tasks---applying any
missed control commands (e.g. a stop command or sync signal) so that it
catches up with the current session state without manual intervention.

**Acceptance Criteria:** In a simulated session test, an Android device
is intentionally taken offline (for example, by disabling its Wi-Fi)
during recording. The system immediately flags this device as
disconnected in the PC UI, but all other data streams (e.g. GSR and
other phones' video) continue recording without interruption. The
offline Android continues to save its own data locally while
disconnected. When network connectivity is restored, the Android
device's app automatically reconnects to the session, synchronises its
clock with the PC, and **continues recording** seamlessly. Any control
commands that were issued during the disconnection (for example, if the
researcher stopped the session while the device was offline) are
received and honored by the Android upon reconnection (e.g. the device
stops recording and prepares to send its data). The PC log and metadata
show the timeline of the disconnect and reconnect events, and no data is
corrupted or lost due to the disruption.

**FR9** *(Platform: PC, Android; Priority: Important; Verification:
Test) -- Calibration Utilities:* The system shall include tools for
calibrating sensors and aligning the smartphone's cameras. In
particular, it shall support aligning an Android device's thermal camera
view with its RGB camera view using a calibration pattern (such as a
checkerboard). The researcher shall be able to initiate a calibration
session where the system captures paired images from the RGB and IR
cameras simultaneously at various positions/angles. The software shall
then compute the calibration parameters (e.g. intrinsic parameters for
each camera and the extrinsic transformation mapping the thermal
camera's coordinate system to the RGB camera's coordinate system). These
calibration settings (pattern type, pattern size, computed camera
matrices, etc.) shall be configurable and saved for use in later data
analysis, ensuring the thermal and visual data can be accurately fused
post-recording.

**Acceptance Criteria:** The user is able to successfully capture
multiple pairs of RGB and thermal images (e.g. at least 10 image pairs)
using the provided calibration utility. The system computes calibration
results (camera intrinsics and extrinsics aligning the thermal image to
the RGB image) and stores these parameters for future use. If the
computed reprojection error is above an acceptable threshold, the system
clearly warns the researcher and allows the calibration process to be
repeated or refined. Verification is achieved by applying the
calibration data to overlay thermal images on RGB images---if alignment
looks correct (or the numerical error is low), the requirement is met;
if not, the system guides the user to recalibrate.

**FR10** *(Platform: PC, Android; Priority: Essential; Verification:
Test) -- Data Transfer and Aggregation:* When a recording session ends,
the system shall automatically transfer **all recorded data** from each
Android device to the PC for central storage and aggregation. Each
Android app shall package its recorded files (video, thermal, audio, and
any locally logged sensor data) and send them to the PC over the network
(e.g. via Wi-Fi). The PC shall receive each incoming file and save it
into the appropriate session folder, updating the session's metadata
(including file names, sizes, and perhaps checksums) to reflect the
newly received data. The system shall gracefully handle transfer
failures: if any file transfer fails or is incomplete, the system will
retry the transfer and log an error if the file remains missing after
retries. Throughout this process, only low-volume preview or metadata
streams are transmitted in real time (during the session), whereas the
large full-quality recordings are transmitted post-session -- this
ensures that full-resolution data is preserved on the smartphone during
recording and then consolidated on the PC for offline analysis.

**Acceptance Criteria:** After stopping a session, all Android-recorded
files (full-resolution video, thermal images, audio, etc.) successfully
appear in the PC's session directory, each with a corresponding entry in
the session metadata JSON. The researcher is notified when all transfers
complete, and the final metadata confirms that every expected file from
each device has been received. In a test, if a network drop is
introduced after session stop, the system retries sending any files that
didn't initially transfer. Any file that still cannot be transferred is
reported to the researcher (e.g. via an error message or flag in the
UI). Upon manual inspection, the researcher can confirm that the files
present on the PC are identical to the originals on the phone (i.e. no
loss of quality or data), meaning the complete dataset is now available
on the PC for offline analysis.

## Non-Functional Requirements

**NFR1** *(Performance -- Real-Time Handling):* The system shall process
incoming data in real time with minimal end-to-end latency, even while
handling multiple modalities and live preview streams. It must support
at least 128 Hz sensor sampling alongside 30 FPS video recording
concurrently, without any data loss or buffering
delays[\[3\]\[4\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf%7BNFR1%7D%5D%20%5Ctextbf%7B%28Performance%20%E2%80%93%20Real,drops%20even%20with%20multiple%20devices).
This performance level applies under scenarios with several devices
streaming data at once. Multi-threaded and asynchronous I/O techniques
shall be used to parallelize tasks (sensor reading, file writing,
network streaming, UI updates) and ensure no frame drops or UI freezes
occur, even with multiple devices and the additional preview overhead.
For example, the system may downsample or compress live preview video
frames so that displaying them does not interfere with high-rate sensor
logging or video recording.

**NFR2** *(Temporal Accuracy):* The system shall maintain clock
synchronization accuracy on the order of milliseconds or
better[\[1\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,recording%2C%20ensuring%20valid%20sensor%20fusion).
The built-in time server and synchronization protocol must keep
timestamp differences across all devices within \~5 ms (and at most
10 ms) during recording, ensuring valid sensor fusion and alignment of
multimodal
data[\[5\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,recording%2C%20ensuring%20valid%20sensor%20fusion).
This precision level means that events observed in different data
streams (GSR peaks, video frames, thermal changes) can be reliably
compared on a shared timeline with only negligible timing error.

**NFR3** *(Reliability and Fault Tolerance):* The system shall be robust
to interruptions, failures, or data
dropouts[\[6\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,features%20shall%20support%20seamless%20recovery).
If a sensor or network link fails during a session, other recordings
must continue unaffected, and any data already recorded shall remain
intact and uncorrupted. All data files should be written incrementally
and closed safely so that an unexpected crash or power loss does not
corrupt the entire dataset. The system should provide mechanisms such as
queued commands and automatic reconnection attempts so that when
connectivity is restored, the components recover and continue with
minimal user intervention (as described in **FR8**). Overall, the design
should ensure that a single-point failure (e.g. one device going
offline) does not invalidate the whole experiment's data.

**NFR4** *(Data Integrity and Validation):* The system shall ensure that
all recorded data is accurate, authentic, and
uncorrupted[\[7\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,All%20session%20data%20shall%20be).
Incoming sensor values shall be validated against expected ranges or
formats (e.g. GSR values within physiological limits, frame dimensions
matching camera specs). Each file transfer to the PC shall include
completeness checks, such as verifying file sizes or using
checksums/hashes, to detect any corruption or missing data. The session
metadata shall serve as a manifest listing all expected files and their
properties; upon session completion, the system can use this manifest to
detect if any file is missing or incomplete. All session data shall be
stored in uniquely timestamped folders (per session run) to prevent
overwriting or mixing data from different sessions, thereby preserving
data integrity across multiple experiments.

**NFR5** *(Security):* The system shall secure all communications and
data storage by following best practices for confidentiality and access
control[\[8\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,checks%20ensure%20no%20insecure%20defaults)[\[9\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=communications%20and%20data%20,checks%20ensure%20no%20insecure%20defaults).
All network links between the PC and Android device(s) shall use
encryption (e.g. TLS) and device authentication (such as pairing tokens
or keys) to prevent unauthorized access or data interception. The system
must warn the user if any security feature is misconfigured or disabled
(for instance, if encryption is turned off, the UI should clearly alert
the researcher). By default, all recorded data files remain on the
researcher's PC (local storage) unless the researcher explicitly chooses
to export or share them, ensuring sensitive participant data is not
automatically sent to external servers. Additionally, file permissions
and runtime security checks should be in place to avoid common
vulnerabilities (for example, preventing a malicious app from
impersonating a sensor device or intercepting data streams).

**NFR6** *(Usability):* The system shall be easy to use for researchers,
including those without extensive software or technical
expertise[\[10\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,provided%20for%20tasks%20like%20calibration)[\[11\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=by%20researchers%20without%20software%20expertise,app%20help).
The PC GUI shall present clear, intuitive controls (e.g. clearly labeled
buttons for scanning devices, starting/stopping recording, triggering
sync events) and real-time indicators (recording status, time elapsed,
device battery levels) so that the overall status is understood at a
glance. Sensible default settings (such as a default save location,
reasonable initial camera focus and exposure settings, etc.) and
on-screen guidance or tooltips shall facilitate quick setup and usage
with minimal training. The Android app shall require minimal interaction
after initial installation and setup --- typically just launching the
app and tapping "Connect" to join the PC's session. Features like sensor
calibration (from **FR9**) or stimulus presentation controls should be
well-documented and straightforward to execute. *For example, if the
experiment involves playing back a stimulus video during the session,
the interface to load and play that video (and automatically mark the
event) should be simple and obvious (ideally a single button click),
allowing the researcher to focus on the participant and experiment
rather than fiddling with software settings.* In general, the system's
workflow should match the natural flow of an experiment session, with
clear prompts and confirmations for each step.

**NFR7** *(Scalability):* The system shall scale to handle multiple
devices and long-duration recording sessions without performance
degradation[\[12\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,not%20overwhelm%20storage%20or%20processing)[\[13\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=multiple%20devices%20and%20long%20sessions,GB%20segments%29%20so%20that%20long).
The architecture must support at least eight simultaneous Android
devices streaming data to the PC (current tested limit is 10 connected
devices) and recording sessions of at least 120 minutes in length. To
accommodate the large volume of data generated (especially
high-resolution video and continuous sensor data), the system may
automatically chunk or segment recordings -- for example, creating
\~1 GB video file segments -- so that very long sessions do not produce
unwieldy single files and to reduce the risk of data loss if a failure
occurs mid-session. Similarly, the network communication and data
processing pipeline should handle the bandwidth of multiple video
streams plus sensor streams. If live preview feeds are active (see
**FR6**), they should be efficiently compressed so even many parallel
previews do not overwhelm network or CPU resources. Overall, adding more
devices or extending session duration should have a linear or manageable
impact on resource usage, and the system should provide feedback (or
gracefully degrade quality, if necessary) rather than abruptly fail when
limits are approached.

**NFR8** *(Maintainability and Modularity):* The system shall be built
in a modular, configurable way to simplify maintenance and future
upgrades[\[14\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,description)[\[15\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=shall%20be%20modular%20and%20configurable,support%20debugging%20and%20future%20development).
Major components of the software (e.g. *Session Manager*, *Shimmer
Manager*, *Network Server*, *Stimulus Controller*) shall have
well-defined interfaces, allowing parts of the system to be updated or
replaced independently. *(For example, swapping in a new thermal camera
SDK or updating the GSR sensor API should require changes only in the
corresponding module, without affecting unrelated parts of the system.)*
Configuration parameters (such as sensor types, sampling rates, camera
resolution, or stimulus video file paths) shall be externalised in
configuration files (e.g. a `config.json`), so that adapting the system
to new hardware or experimental protocols can be done without modifying
code. This design will accommodate changing requirements or additional
sensor modalities (for instance, integrating a new type of sensor or
adding a different stimulus presentation mechanism) with minimal
development effort. Extensive logging, unit tests, and deployment
scripts shall be provided to support debugging and to ensure that as new
features or devices are introduced, the system's core functionality
remains stable and verifiable.

------------------------------------------------------------------------

[\[1\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,recording%2C%20ensuring%20valid%20sensor%20fusion)
[\[2\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=clock%20synchronisation%20accuracy%20on%20the,recording%2C%20ensuring%20valid%20sensor%20fusion)
[\[3\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf%7BNFR1%7D%5D%20%5Ctextbf%7B%28Performance%20%E2%80%93%20Real,drops%20even%20with%20multiple%20devices)
[\[4\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf%7BNFR1%7D%5D%20%5Ctextbf%7B%28Performance%20%E2%80%93%20Real,drops%20even%20with%20multiple%20devices)
[\[5\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,recording%2C%20ensuring%20valid%20sensor%20fusion)
[\[6\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,features%20shall%20support%20seamless%20recovery)
[\[7\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,All%20session%20data%20shall%20be)
[\[8\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,checks%20ensure%20no%20insecure%20defaults)
[\[9\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=communications%20and%20data%20,checks%20ensure%20no%20insecure%20defaults)
[\[10\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,provided%20for%20tasks%20like%20calibration)
[\[11\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=by%20researchers%20without%20software%20expertise,app%20help)
[\[12\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,not%20overwhelm%20storage%20or%20processing)
[\[13\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=multiple%20devices%20and%20long%20sessions,GB%20segments%29%20so%20that%20long)
[\[14\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=%5Citem%5B%5Ctextbf,description)
[\[15\]](file://file-59dbMmLLFHR2oahNVJTqd5#:~:text=shall%20be%20modular%20and%20configurable,support%20debugging%20and%20future%20development)
3.tex

<file://file-59dbMmLLFHR2oahNVJTqd5>
