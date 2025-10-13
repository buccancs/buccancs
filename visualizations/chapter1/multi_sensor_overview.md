```mermaid
graph TD
    subgraph "Remote Controller"
        PC[PC Controller]
    end

    subgraph "Smartphone-Based System"
        subgraph "Android Device"
            Phone[Smartphone]
            subgraph "Internal Modules"
                CameraX[CameraX Module]
                USB_Host[USB Host API]
                Bluetooth_API[Bluetooth API]
            end
        end

        subgraph "Sensors"
            GSR[Shimmer3 GSR]
            Thermal[Topdon TC001]
            RGB[RGB Camera]
        end
    end

    PC -- Wi - Fi (TCP/IP ) --> Phone
GSR -- Bluetooth --> Bluetooth_API
Thermal -- USB --> USB_Host
RGB -- Internal Bus --> CameraX

Bluetooth_API --> Phone
USB_Host --> Phone
CameraX --> Phone

style PC fill: #f9f, stroke: #333, stroke-width:2px
style Phone fill: #ccf, stroke:#333, stroke-width: 2px
```

### Figure: Multi-Sensor System Overview

This diagram provides a high-level overview of the entire system. It shows the central smartphone-based system, which
includes the Android device and the three sensors: the Shimmer3 GSR (connected via Bluetooth), the Topdon TC001 thermal
camera (connected via USB), and the phone's internal RGB camera (accessed via CameraX). The system is controlled
remotely by a PC over a Wi-Fi connection.
