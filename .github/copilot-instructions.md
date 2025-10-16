**Last Modified:** 2025-01-14 04:51 UTC  
**Modified By:** GitHub Copilot CLI

### **Core Principles**

* **Communication Style:**
    - Be modest and succinct
    - Use plain language - avoid jargon, buzzwords, and unnecessary complexity
    - State facts directly without exaggeration or hyperbole
    - Professional and dry tone - no marketing language or superlatives
    - Prefer simple words over complex alternatives
    - No overstatement - "works" not "revolutionary", "fixed" not "transformed"
* **Clarity:** If something is not clear, refer to the requirements, backlogs, todos, build fixes.
* **Character Set:** Use only ASCII-safe characters in all code, comments, and commit messages.
* **Language:** Use British English spelling in all documentation, comments, commit messages, and LaTeX files (e.g., "
  synchronise" not "synchronize", "optimise" not "optimize", "centre" not "center"). Exception: Code identifiers and API
  names remain as specified.
* **Documentation Organization:** Keep documentation inside `docs/` using the curated set (`readme.md`,
  `system-overview.md`, `requirements.md`, `development.md`, `testing.md`, `contributing.md`, and the existing `latex/`
  sources). The primary `readme.md` stays in the repository root.

### **Writing Style Examples**

**Good (Succinct, modest, professional):**

- "Fixed memory leak in DisplayListener"
- "Added cleanup method to release resources"
- "Refactored connector to use sealed classes"
- "Compilation blocked by cache corruption"
- "Removed 13 redundant files"

**Bad (Verbose, overstated, marketing):** 

- "Successfully eliminated critical memory leak issue"
- "Implemented comprehensive resource management solution"
- "Revolutionised connector architecture with modern patterns"
- "Unfortunately experiencing compilation challenges"
- "Dramatically reduced documentation redundancy"

**Good (Technical, direct):**

- "Method returns null if file not found"
- "Uses mutex for thread safety"
- "Cache corruption prevents build"

**Bad (Complex, indirect):**

- "Method gracefully handles edge case scenarios"
- "Leverages synchronisation primitives for concurrent access"
- "Build pipeline experiencing technical difficulties"

### **File Naming and Tracking Standards**

* **Documentation Files:** Update the maintained guides in `docs/`. If a new topic is required, pick a concise,
  descriptive filename (no timestamp suffixes) and list it in `docs/readme.md`.
* **File Headers:** Optional for the curated docs. Include a short context block when it materially helps future readers.
* **Version Control:** Avoid parallel dated copies. Replace or expand the canonical document and delete superseded drafts
  as part of the same change.

### **Development Workflow & Verification**

* **Workflow:** Tests remain disabled by default; run targeted Gradle tasks when your change touches the exercised area.
* **Documentation Updates:** When behaviour or process changes, refresh the root `readme.md` and whichever of
  `docs/system-overview.md`, `docs/requirements.md`, `docs/development.md`, `docs/testing.md`, or `docs/contributing.md`
  apply. Keep `docs/readme.md` aligned with the available guides and fix cross-references as part of the same change.

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
* **Documentation Generation:** Agents CAN generate analysis documents, technical reports, and guides following the file
  naming and tracking standards above
* **LaTeX Files:** Agents ARE ALLOWED to read, modify, and create LaTeX (`.tex`, `.latex`) files for thesis chapters,
  academic papers, and technical documentation. All LaTeX content must use British English spelling.
* **Source Code:** Full access to read, modify, and create Kotlin, Java, and configuration files
* **Analysis and Reports:** Actively encouraged to create documentation when analysing code, identifying issues, or
  documenting architecture

### **Post-Work Documentation Requirements**

After each work session, ensure the curated documentation stays accurate:

- Update the root `readme.md` whenever overall project status, quick start steps, or capability summaries change.
- Update `docs/system-overview.md` for architecture, data flow, or component capability changes.
- Update `docs/requirements.md` when requirement wording, acceptance coverage, or delivery status moves.
- Update `docs/development.md` for environment setup, build commands, or tooling workflow changes.
- Update `docs/testing.md` whenever validation procedures, scripts, or diagnostic drills are added or altered.
- Update `docs/contributing.md` when collaboration norms, coding standards, or review expectations shift.
- Keep `docs/readme.md` in sync with the available guides and add new entries when you introduce additional documents.

### **File and Directory Exclusions**

To maintain focus on relevant source code and documentation, agents MUST ignore the following build output, IDE
configuration, and dependency-related directories:

- `build/`
- `app/build/`
- `desktop/build/`
- `protocol/build/`
- `artifacts/build/`
- `.gradle/`
- `.idea/`
- `desktop/bin/`
- `protocol/bin/`

### **Prohibitions**

* **TOTAL EMOJI BAN:** Emojis are strictly prohibited in ALL files including but not limited to:
    - Markdown files (.md)
    - Text files (.txt)
    - Kotlin source files (.kt)
    - Java source files (.java)
    - XML files (.xml)
    - Configuration files
    - Comments in any language
    - Commit messages
    - Documentation of any kind
    - No exceptions - use plain text only

### **Automation Script**

* **Final Step:** After completing ANY work session, task, or when reaching a natural stopping point, ALWAYS output this exact line at the very end of the response the usage and the session id.

* **Purpose:** Provide the continuation command in a format the user can immediately execute
* **When to Output:**
    - After finishing a phase of work
    - After completing documentation updates
    - After fixing build errors
    - After any significant milestone
    - When work is complete and ready for next steps

* **Format:** 
    - Output as plain text (not in code block)
    - On its own line
    - At the very end of the response
    - User can then copy/paste or press Up arrow + Enter if in history



