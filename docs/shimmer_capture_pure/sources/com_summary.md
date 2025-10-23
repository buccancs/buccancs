# `sources/com`

- **`com.shimmerresearch`** – Core of PURE. Key areas:
    - `shimmercapture` holds `MainActivity` and the fragment UI for connected devices, plotting, and Verisense sync.
    - `android.shimmerService` plus `android.manager.ShimmerBluetoothManagerAndroid` implement the runtime service,
      Bluetooth orchestration, configuration pushes, logging, and plotting hooks.
    - `bluetooth`, `driver`, `driverUtilities`, and `sensors` provide the Shimmer firmware abstractions (commands,
      calibration, sensor metadata) used by both the service and UI.
    - `verisense` and `android.protocol` contain the byte-protocol implementation for Verisense devices, including
      logged-data transfer and CRC/state handling.
    - `grpc` exposes generated stubs and helper clients to control devices over gRPC or bridge BLE communication through
      an external host.
- Additional noteworthy subpackages:
    - `algorithms` and `biophysicalprocessing` implement signal-processing helpers (HR from ECG/PPG, filters, FFT).
      These feed optional derived metrics in the service.
    - `tools` and `utilityfunctions` include platform-agnostic helpers (logging, storage paths, math utilities) reused
      across the app.
    - `androidguiUtilities` (and `androidinstrumentdriver`) provide custom views, dialogs, and adapters the UI relies
      on.
- **3rd-party bundles**:
    - `androidplot`, `squareup`, `fasterxml`, `google`, `clj`, `codeminders`, `shimmersensing` deliver embedded
      dependencies (plotting, HTTP/Okio, JSON, BLE helpers).
    - Treat these as vendored libraries; replace with upstream artifacts in Buccancs where possible.
- Before porting code, map each class you depend on to its logical layer here. For example, anything dealing with
  Bluetooth or radio state likely lives under `android.manager` or `bluetooth`, whereas device configuration objects
  come from `driverUtilities`. Keeping that separation helps when rewriting modules in your architecture.
