```mermaid
graph TD
    subgraph "Hardware"
        A[TC001 Camera]
    end

    subgraph "Vendor SDK (libir)"
        B[USBMonitorManager]
        C[LibIRProcess]
        D[IRImageHelp]
    end

subgraph "Application Logic"
E[IRThermalPlusActivity]
F[TemperatureView (UI)]
end

A -- Raw YUYV Frames --> B
B -- Raw YUYV Frames --> E
E -- Hands frame to --> C
C -- Converts to Pseudo - color ARGB --> E
E -- Applies custom palette with --> D
D -- Returns final ARGB buffer --> E
E -- Renders buffer to --> F
```

### Topdon Thermal Data Pipeline

This diagram illustrates the flow of thermal data from the camera to the user interface.

1. The **TC001 Camera** captures raw thermal data in YUYV format.
2. The **USBMonitorManager** receives this data from the hardware and passes it to the `IRThermalPlusActivity`.
3. The activity then uses the **LibIRProcess** library to convert the raw YUYV frame into a pseudo-color ARGB bitmap.
   This bitmap represents the thermal data in a human-readable format.
4. The **IRImageHelp** library is then used to apply a custom color palette to the ARGB bitmap.
5. Finally, the resulting ARGB buffer is rendered to the **TemperatureView**, which is the UI component that displays
   the thermal image to the user.
