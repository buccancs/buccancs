**Last Modified:** 2025-10-13 20:05 UTC  
**Modified By:** GitHub Copilot CLI

### **Core Principles**

* **Communication:** Be modest and succinct.
* **Clarity:** If something is not clear, refer to the requirements, backlogs, todos, build fixes.
* **Character Set:** Use only ASCII-safe characters in all code, comments, and commit messages.

### **File Naming and Tracking Standards**

* **Documentation Files:** All analysis, report, and documentation MD files MUST include the date in the filename using format `FILENAME_YYYY-MM-DD.md`
  - Example: `TECHNICAL_DEBT_ANALYSIS_2025-10-13.md`
* **File Headers:** All generated/modified documentation files MUST include a header block:
  ```markdown
  **Last Modified:** YYYY-MM-DD HH:MM UTC
  **Modified By:** [Agent Name] (e.g., Gemini, GitHub Copilot CLI, Claude)
  **Document Type:** [Analysis/Report/Guide/Documentation]
  ```
* **Version Control:** When updating dated documents, create new file with current date rather than modifying old ones
* **References:** Always check if newer versions of referenced documents exist

### **Development Workflow & Verification**

* **Workflow:** Disable all tests for now
* **Documentation** Maintain a really minimal backlog, dev-diary. Always update the readme with the current state of the
  repo.

<!-- 2. **Pre-Commit Build:** Always execute a full Gradle build before committing changes and try to fix the issues.
4. **Final Build Check:** Re-verify with a final `gradle build` to ensure project integrity and try to fix the issues. -->

### **Coding and Architectural Standards**

* **Code Conventions:** Strictly adhere to the official Kotlin and Android coding conventions.
* **Architecture:**
    * Implement all features following the Jetpack Compose and Model-View-ViewModel (MVVM) architecture with HILT.
    * Use the Repository pattern for data abstraction and access.
* **Commenting:**
    * Only add comments that are essential for development, such as explaining complex algorithms or non-obvious logic.
    * Refrain from adding non-development-related comments.
* **Code Deletion:** Remove redundant files and code duplications

### **Implementation Ground Truth**

For specific feature integrations where a local implementation is missing, refer to the following repositories as the
definitive source:

* **TOPDON SDK - GROUNDTRUTH:**
    * `sdk/libs/topdon.aar`

* **Example TOPDON TC001 Integration- GROUNDTRUTH:**
    * **Main:** `external/original-topdon-app`
    * **BLE:** `external/original-topdon-app/BleModule`

* **Shimmer SDK - GROUNDTRUTH:**
    * `sdk/libs/shimmerbluetoothmanager-0.11.5_beta.jar`
    * `sdk/libs/shimmerdriver-0.11.5_beta.jar`
    * `sdk/libs/shimmermanager-0.11.5_beta.jar`
    * `sdk/libs/shimmerandroidinstrumentdriver-3.2.4_beta.aar`

* **Example Shimmer3 GSR Integration - GROUNDTRUTH:**
    * `external/Shimmer-Java-Android-API`
    * `external/ShimmerAndroidAPI`

### **File Permissions and Capabilities**

* **Markdown Files:** Agents ARE ALLOWED to read, modify, and create Markdown (`.md`) files
* **Documentation Generation:** Agents CAN generate documentation files following the file naming and tracking standards
* **LaTeX Files:** Agents CAN read and reference LaTeX files but should not modify them without explicit instruction
* **Analysis and Reports:** Agents SHOULD create analysis documents, technical reports, and documentation as needed

### **Prohibitions**

* Do not use emojis in any context (code, comments, documentation, or commit messages).
