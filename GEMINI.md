### **Core Principles**

* **Communication:** Be modest and succinct.
* **Clarity:** If something is not clear, refer to the requirements, backlogs, todos, build fixes.
* **Character Set:** Use only ASCII-safe characters in all code, comments, and commit messages.

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

### **File Exclusions**

<!-- * Exclude all Markdown (`.md`) files from agent analysis and processing
* Exclude all LaTeX (`.tex`, `.latex`) files from agent analysis and processing
* Exclude `docs/` directory from agent processing -->

### **Prohibitions**

* Do not use emojis in any context (code, comments, documentation, or commit messages).

<!-- * Do not generate Markdown (`.md`) documentation files. -->