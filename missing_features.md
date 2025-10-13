Missing pieces, by area.

#### Quick diff to your tree

Yes. Keep MVVM+Compose+Hilt, but introduce a thin **application‑service layer**, slice by **feature**, and make **API/impl** boundaries explicit. Your current tree mixes orchestration and IO inside “data”. Move coordination out of ViewModels and repositories.

### Sync + networking

* **On‑device gRPC server** for command intake and events. Add:

    * `data/orchestration/server/ControlServer.kt`
    * `data/orchestration/server/EventPublisher.kt`
    * `data/orchestration/server/AuthInterceptor.kt`
* **.proto + generated stubs** (API only). Add a small module or package:

    * `sync/proto/control.proto` → Kotlin stubs
* **mDNS advertise/browse** to find devices:

    * `data/orchestration/discovery/MdnsAdvertiser.kt`, `MdnsBrowser.kt`
* **TLS/mTLS + tokens** bound to `sessionId`:

    * `data/orchestration/security/KeyStoreProvider.kt`, `TokenIssuer.kt`
* **Offset/drift service** surfaced app‑wide:

    * Promote `time/DefaultTimeSyncRepository.kt` to a singleton `TimeSyncService` with quality flags, RTT filters, regression, and history.
* **Heartbeat + health**:

    * `data/events/HeartbeatMonitor.kt` emitting WARN/SAFE and auto‑stop on loss.

### Recording core

* **Recording controller and segmentation** (MediaCodec→MediaMuxer):

    * `data/recording/RecordingController.kt`
    * `data/recording/mux/SegmentWriter.kt`, `MediaMuxerFactory.kt`
* **Crash‑safe MediaStore flow**:

    * `data/recording/store/MediaStoreGateway.kt` (IS_PENDING, finalise, recovery)
* **Session manifest (JSON) writer**:

    * `data/recording/manifest/ManifestModels.kt`, `ManifestWriter.kt`
    * `assets/schemas/session_manifest.schema.json` (optional validation)
* **Bookmarks API**:

    * `data/recording/BookmarkRepository.kt`
* **Per‑segment telemetry**:

    * `data/telemetry/EncoderStats.kt`, `StatsCollector.kt`

### Storage, quotas, transfers

* **Quota and retention worker**:

    * `data/storage/SpaceMonitor.kt`, `RetentionWorker.kt`
* **Background transfer jobs** (retry, back‑pressure):

    * `transfer/UploadWorker.kt`, `transfer/WorkPolicy.kt`
* **File naming + indexing** (time‑sortable):

    * Extend `storage/RecordingStorage.kt` with session foldering and LRU eviction.

### Camera/thermal pipeline gaps

* **CameraX video node** with PTS mapping:

    * `data/sensor/connector/camera/VideoPipeline.kt`
* **Thermal frame normaliser + PTS mapper**:

    * `data/sensor/connector/topdon/ThermalNormalizer.kt`
* **Single timebase adapter**:

    * Replace `sensor/SessionClock.kt` with `core/time/TimeModelAdapter.kt` used by all streams.

### UI

* **Live session screen** with overlays and controls:

    * `ui/session/LiveSessionScreen.kt`, `LiveSessionViewModel.kt`
* **Library of sessions**:

    * `ui/library/SessionListScreen.kt`, `SessionDetailScreen.kt`
* **Debug panels** (offset, drift, RTT, encoder stats):

    * `ui/debug/ClockPanel.kt`, `EncoderPanel.kt`
* **Settings for capture/sync/retention**:

    * `ui/settings/*` mapped to DataStore.

### Testing and tooling

* **Fakes and harnesses**:

    * `testshared/FakeClock.kt`, `FakePingEndpoint.kt`, `FakeCameraFrames.kt`
* **Soak and benchmark**:

    * `benchmark/EncoderThroughput.kt`, `OffsetStability.kt`
* **PC controller tool** (separate module/repo):

    * `pc-tools/controller-cli` for discovery, sync fit, start/stop, bookmarks.
