```mermaid
xychart-beta
    title "Example GSR Signal"
    x-axis "Time" 0 --> 10
    y-axis "Conductance" 0 --> 20
    line [5, 5, 5, 15, 8, 6, 5]
annotation "Stimulus" 3
```

```mermaid
graph TD
subgraph "Example Thermal Image"
A1[" "] -- "" --> A2[" "] -- "" --> A3[" "]
B1[" "] -- "" --> B2[" "] -- "" --> B3[" "]
C1[" "] -- "" --> C2[" "] -- "" --> C3[" "]
end
style A1 fill: #ffff00
style A2 fill: #ff0000
style A3 fill: #ffff00
style B1 fill: #0000ff
style B2 fill: #ffff00
style B3 fill: #0000ff
style C1 fill: #0000ff
style C2 fill: #0000ff
style C3 fill: #0000ff
```

### Figure: Basic GSR and Thermal Data Examples

This figure provides a conceptual illustration of the types of data collected by the system.

- **Example GSR Signal:** The top chart shows a typical Galvanic Skin Response (GSR) signal. It consists of a stable
  baseline, followed by a sharp peak in skin conductance in response to a stimulus, and then a gradual return to the
  baseline.
- **Example Thermal Image:** The bottom diagram represents a thermal image. The different colors correspond to different
  temperatures, with red being the hottest, yellow being warm, and blue being the coolest. This is a simplified
  representation of the data captured by the Topdon TC001.
