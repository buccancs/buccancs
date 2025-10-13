```mermaid
graph TD
    subgraph "Current System"
        A[Android Device]
        B[PC Controller]
        C[GSR Sensor]
        D[Thermal Sensor]
        E[RGB Camera]
        A -- Wi - Fi --> B
        A -- BLE --> C
        A -- USB --> D
        A -- Internal --> E
    end

    subgraph "Future Enhancements"
        F[ECG Sensor]
        G[Accelerometer]
        H[Cloud Data Analysis]
        I[Real-time Bio-feedback]
    end

    A -- BLE --> F
    A -- Internal/BLE --> G
    A -- 4G/5G --> H
    H -- Insights --> B
    H -- Alerts --> I
    style F fill: #9cf, stroke: #333, stroke-width: 2px
    style G fill: #9cf, stroke: #333, stroke-width: 2px
    style H fill: #9cf, stroke: #333, stroke-width: 2px
    style I fill: #9cf, stroke: #333, stroke-width: 2px
```

### Figure: Proposed Future System Enhancements

This diagram illustrates potential future enhancements to the system.

- **Additional Sensors:** The system could be extended to include other physiological sensors, such as an **ECG Sensor**
  for heart rate variability analysis, or an **Accelerometer** for activity tracking.
- **Cloud Data Analysis:** The collected data could be streamed to a **Cloud Data Analysis** platform in real-time. This
  would enable more sophisticated analysis and the generation of deeper insights.
- **Real-time Bio-feedback:** The insights from the cloud analysis could be used to provide **Real-time Bio-feedback**
  to the user or the experimenter.
