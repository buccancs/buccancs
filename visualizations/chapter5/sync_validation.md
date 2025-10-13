```mermaid
xychart-beta
    title "Sensor Data Synchronization Validation"
    x-axis "Time (ms)" 0 --> 100
y-axis "Signal Value"

line "GSR Signal" [5, 5, 5, 50, 10, 5, 5]
line "Camera Event (LED Flash)" [0, 0, 0, 50, 0, 0, 0]

annotation "Simultaneous Event" 3
```

### Figure: Sensor Data Synchronization Validation

This figure conceptually demonstrates how the synchronization of the different sensor data streams can be validated. It
shows a scenario where a known event occurs that can be captured by two different sensors simultaneously (e.g., an LED
flash captured by the RGB camera, and a corresponding spike in the GSR signal triggered by the same event).

By plotting the data from both sensors on a common timeline, we can visually inspect the delay between the two signals.
In this ideal example, the peak of the GSR signal and the camera event occur at the same time, indicating that the data
streams are well-synchronized.
