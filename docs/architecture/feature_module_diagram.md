```mermaid
graph TD
    subgraph "User Interface"
        A[app]
        UI[libui]
        Menu[libmenu]
        Range[RangeSeekBar]
        Component[component]
    end

    subgraph "Core Logic"
        LibApp[libapp]
        Common[commonlibrary]
    end

    subgraph "Hardware Integration"
        subgraph "Thermal (IR)"
            IR[libir]
            IRDemo[libir-demo]
            ThermalIR[component/thermal-ir]
            ThermalLite[component/thermal-lite]
        end
        subgraph "Bluetooth"
            BLE[BleModule]
        end
        subgraph "Hikvision"
            Hik[libhik]
        end
    end

    subgraph "Data & Communication"
        Com[libcom]
        Matrix[libmatrix]
    end

    A --> LibApp
    A --> UI
    A --> Menu
    A --> BLE
    A --> ThermalIR
    A .-> ThermalLite
    LibApp --> Common
    LibApp --> Com
    ThermalIR --> IR
    ThermalIR --> LibApp
    ThermalIR --> UI
    ThermalIR --> Menu
    ThermalLite --> IR
    ThermalLite --> LibApp
    ThermalLite --> UI
    IR --> Matrix
    style A fill: #f9f, stroke: #333, stroke-width: 2px
    style LibApp fill: #ccf, stroke: #333, stroke-width: 2px
    style IR fill: #f66, stroke: #333, stroke-width: 2px
    style BLE fill: #6f6, stroke: #333, stroke-width: 2px
```

**Diagram Description:**

This diagram shows the high-level module structure of the Topdon application.

* **User Interface (Green):** The `app` module is the main application entry point. It utilizes UI components from
  `libui`, `libmenu`, `RangeSeekBar`, and the `component` module.
* **Core Logic (Blue):** `libapp` and `commonlibrary` provide the core application logic and shared functionalities.
* **Hardware Integration (Red/Yellow):**
    * The application integrates with thermal cameras through the `libir`, `component/thermal-ir`, and
      `component/thermal-lite` modules. `libir-demo` is likely a demonstration of the `libir` capabilities.
    * Bluetooth functionality is handled by `BleModule`.
    * `libhik` suggests a possible integration with Hikvision devices.
* **Data & Communication (Gray):** `libcom` likely handles general communication protocols, and `libmatrix` may be used
  for image processing or data transformation.

The arrows indicate dependencies between the modules. For example, the `app` module depends on `libapp`, `libui`,
`libmenu`, `BleModule`, and the thermal components.
