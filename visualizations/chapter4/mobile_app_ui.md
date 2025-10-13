```mermaid
graph TD
    subgraph "Mobile App UI"
        subgraph "Sensor Status"
            GSR_Status["GSR: Connected"]
            Thermal_Status["Thermal: Connected"]
            RGB_Status["RGB: Ready"]
        end
        subgraph "Recording Control"
            Rec_Status["Status: Recording"]
        end
    end

    subgraph "Background Services"
        TCP_Server[TCP Server Thread]
        GSR_Service[GSR Data Service]
        Thermal_Service[Thermal Data Service]
        RGB_Service[RGB Data Service]
        Data_Logger[Data Logger]
    end

    subgraph "Incoming Data/Commands"
        PC_Commands["PC Commands (START, STOP)"]
        GSR_Data["GSR Data Stream"]
        Thermal_Data["Thermal Frames"]
        RGB_Data["RGB Frames"]
    end

    PC_Commands --> TCP_Server
    TCP_Server -- Updates --> Rec_Status
    GSR_Data --> GSR_Service
    Thermal_Data --> Thermal_Service
    RGB_Data --> RGB_Service
    GSR_Service --> Data_Logger
    Thermal_Service --> Data_Logger
    RGB_Service --> Data_Logger
    style GSR_Status fill: #9f9, stroke: #333, stroke-width: 2px
    style Thermal_Status fill: #9f9, stroke: #333, stroke-width: 2px
    style RGB_Status fill: #9f9, stroke: #333, stroke-width: 2px
    style Rec_Status fill: #f99, stroke: #333, stroke-width: 2px
```

### Figure: Mobile App UI and Data Flow

This diagram provides a conceptual representation of the mobile application's user interface and its internal data flow.

- **Mobile App UI:** The UI displays the connection status of each sensor and the current recording status of the
  application.
- **Background Services:** Several background services run to handle different tasks:
    - A **TCP Server** listens for commands from the PC.
    - Separate data services for **GSR, Thermal, and RGB** handle the incoming data from each sensor.
    - A **Data Logger** is responsible for writing the data from all sensors to storage.
- **Data Flow:**
    - Commands from the PC are received by the TCP server, which then updates the recording status on the UI.
    - Data from each sensor is processed by its respective service and then passed to the data logger to be saved.
