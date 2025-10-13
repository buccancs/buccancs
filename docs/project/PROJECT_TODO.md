# Exhaustive Project TODO List

This list covers all major tasks from project inception to final submission, based on the departmental guide and the
provided thesis plan.

## Phase 1: Project Initiation & Planning (Now - Mid-November)

### 1.1. Project Management & Repository Setup

- [ ] Create a `thesis` directory for all report-related documents.
- [ ] Create templates for the Project Plan and Interim Report in the `thesis` directory.
- [ ] Create an `ETHICS.md` file to document ethical considerations.
- [ ] Add a `LICENSE` file (e.g., MIT) to the repository.
- [ ] Enhance the `README.md` with a project overview, aims, objectives, and setup instructions.
- [ ] Review and update the `.gitignore` file.
- [ ] Set up weekly tutorials with your supervisor.

### 1.2. Research & Literature Review

- [ ] Conduct a thorough literature review on multi-sensor data acquisition and synchronization.
- [ ] Research existing solutions for GSR, thermal, and RGB data recording.
- [ ] Investigate the Shimmer, Topdon, and CameraX SDKs/APIs in detail.
- [ ] Write the literature review section (for Chapter 2 of the thesis).

### 1.3. Project Plan (Due ~Nov 10th)

- [ ] Finalize the project title.
- [ ] Define clear and measurable aims and objectives.
- [ ] List the expected deliverables (software, report, etc.).
- [ ] Create a detailed work plan with timelines and milestones.
- [ ] Complete the ethics review section of the plan.
- [ ] Write the full Project Plan document.
- [ ] Submit the Project Plan to your supervisor for approval.

## Phase 2: Core Development & Implementation (Mid-November - Early February)

### 2.1. Android Application (Core)

- [ ] Set up the basic Android project structure with MVVM architecture.
- [ ] Implement the core data logging service.
- [ ] Implement the TCP server for receiving commands from the PC controller.
- [ ] Design and implement the main application UI (sensor status, recording control).

### 2.2. Sensor Integration

- [ ] Implement the Shimmer3 GSR integration via Bluetooth, including connection and data reading.
- [ ] Implement the Topdon TC001 integration via USB, including frame capture.
- [ ] Implement the RGB camera integration using CameraX.
- [ ] Ensure all three sensors can be controlled (start/stop) by the app.

### 2.3. PC Controller

- [ ] Develop a simple PC application (e.g., in Python) that can send START, STOP, and SYNC commands over TCP/IP.

### 2.4. Synchronization

- [ ] Implement the timestamp synchronization logic based on the "SYNC" command from the PC.
- [ ] Design and implement the data storage format to save synchronized data from all sensors.

### 2.5. Interim Report (Due ~Jan 19th)

- [ ] Document the progress made to date.
- [ ] Create a detailed plan for the remaining work.
- [ ] Write the full Interim Report document.
- [ ] Submit the Interim Report to your supervisor for approval.

## Phase 3: Testing, Evaluation & Refinement (February - March)

### 3.1. Testing

- [ ] Write unit tests for critical components (e.g., synchronization logic, command parsing).
- [ ] Conduct integration tests to ensure all sensors work together correctly.
- [ ] Perform end-to-end testing with the PC controller and the Android app.
- [ ] Develop a formal testing strategy document (for Chapter 5 of the thesis).

### 3.2. Evaluation

- [ ] Design and conduct experiments to evaluate the system's performance.
- [ ] Measure key metrics: synchronization accuracy, command latency, data throughput, CPU usage, and battery
  consumption.
- [ ] Analyze the collected data and generate results.

### 3.3. Visualization & Video

- [ ] Create all the figures and tables for the thesis, as outlined in your plan.
- [ ] (Optional) Create the 2-3 minute video preview of the project (due ~March 23rd).

## Phase 4: Final Report Writing & Submission (March - April)

### 4.1. Thesis Writing

- [ ] Write Chapter 1: Introduction.
- [ ] Write Chapter 2: Background and Literature Review.
- [ ] Write Chapter 3: System Design and Architecture.
- [ ] Write Chapter 4: Implementation and Development (including code snippets).
- [ ] Write Chapter 5: Experimental Evaluation and Results.
- [ ] Write Chapter 6: Conclusion and Future Work.
- [ ] Write the Abstract.
- [ ] Compile the Bibliography/References.

### 4.2. Appendices

- [ ] Write the System Manual (how to compile and run the code).
- [ ] Write the User Manual (how to use the app).
- [ ] Include copies of the Project Plan and Interim Report.
- [ ] Prepare the 25-page code listing.

### 4.3. Final Submission (Due ~April 27th)

- [ ] Format the entire report according to the departmental guidelines (1.5 line spacing, 12pt font, etc.).
- [ ] Add the required title page disclaimer.
- [ ] Proofread and edit the entire report for style, grammar, and clarity.
- [ ] Generate the final PDF of the report.
- [ ] Prepare the zip file of the source code.
- [ ] Submit all required files (report PDF, abstract, source code) via Moodle.
