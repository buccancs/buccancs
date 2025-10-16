```
```mermaid
graph TD
    subgraph "Recorded Data Sample at Time T"
        subgraph "Thermal Image"
            A1[" "] -- "" --> A2[" "] -- "" --> A3[" "]
            B1[" "] -- "" --> B2[" "] -- "" --> B3[" "]
            C1[" "] -- "" --> C2[" "] -- "" --> C3[" "]
        end
        subgraph "GSR Signal"
            GSR_Chart("{{xychart-beta
                title "GSR Signal at Time T"
                x-axis "Time" 0 --> 10
                y-axis "" 0 --> 20
                line [5, 8, 15, 12, 8, 6, 5]
            }}")
        end
    end
    style A1 fill:#ffff00
    style A2 fill:#ff0000
    style A3 fill:#ffff00
    style B1 fill:#0000ff
    style B2 fill:#ffff00
    style B3 fill:#0000ff
    style C1 fill:#0000ff
    style C2 fill:#0000ff
    style C3 fill:#0000ff
```

### Figure: Recorded Data Samples (Thermal & GSR)

This figure provides a conceptual example of the synchronized, multi-modal data captured by the system. It shows a
thermal image and a corresponding GSR signal recorded at the same moment in time.

- The **Thermal Image** on the left shows the temperature distribution of a subject.
- The **GSR Signal** on the right shows the subject's physiological arousal at that same time.

By analyzing these two data streams together, a researcher can gain deeper insights than by looking at either data
stream in isolation.

```
