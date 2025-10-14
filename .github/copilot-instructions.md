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
* **Language:** Use British English spelling in all documentation, comments, commit messages, and LaTeX files (e.g., "synchronise" not "synchronize", "optimise" not "optimize", "centre" not "center"). Exception: Code identifiers and API names remain as specified.
* **Documentation Organization:** Place all documentation in `docs/` subdirectories: analysis/, project/, architecture/, guides/, latex/. Exception: README.md remains in root.

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

* **Documentation Files:** All analysis, report, and documentation MD files MUST include the date and time in the filename using format `FILENAME_YYYY-MM-DD_HHMM.md`
  - Example: `TECHNICAL_DEBT_ANALYSIS_2025-10-13_2031.md`, `SECURITY_AUDIT_2025-10-15_1445.md`
  - Time uses 24-hour format (HHMM) in UTC
  - Exception: Core project files like `README.md`, `BACKLOG.md` do not require dates
* **File Headers:** All generated/modified documentation files MUST include a header:
  ```markdown
  **Last Modified:** YYYY-MM-DD HH:MM UTC
  **Modified By:** [Agent Name] (GitHub Copilot CLI, Gemini, Claude, etc.)
  **Document Type:** [Analysis/Report/Guide/Documentation]
  ```
* **Version Control:** When updating dated documents, create new file with current date and time instead of modifying old ones
* **Cleanup Policy:** After creating a new version, remove redundant or outdated versions:
  - Delete superseded files that are now covered by more comprehensive documents
  - Remove partial implementation files once complete versions exist
  - Keep only the most recent version of duplicate topics
  - Document cleanup actions in `docs/project/DOCUMENTATION_CLEANUP_YYYY-MM-DD_HHMM.md`
  - Exception: Keep historical versions if they document distinct phases or provide unique value
* **Historical Tracking:** Maintain previous versions only when they provide historical context or document project evolution
* **Task Tracking:** 
  - When work is planned but not completed, update `docs/project/BACKLOG.md` with the incomplete status
  - Document reasons for incompletion and estimated effort remaining
  - Mark tasks as [DONE], [IN PROGRESS], [BLOCKED], or [TODO] in backlog

### **Development Workflow & Verification**

* **Workflow:** Disable all tests for now
* **Documentation Updates:** After completing any work session, ALWAYS update the following documents:
  - **`docs/project/dev-diary.md`**: Log what was done, decisions made, and any issues encountered
  - **`docs/project/BACKLOG.md`**: Update task status, mark completed items, add new tasks discovered during work
  - **`README.md`**: Update to reflect the current state of the repository
  - **Architecture Documents**: When making architectural decisions, document them in relevant files in `docs/architecture/`
  - **Cross-References**: Ensure all documentation cross-references are updated when creating new versions

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
* **Documentation Generation:** Agents CAN generate analysis documents, technical reports, and guides following the file naming and tracking standards above
* **LaTeX Files:** Agents ARE ALLOWED to read, modify, and create LaTeX (`.tex`, `.latex`) files for thesis chapters, academic papers, and technical documentation. All LaTeX content must use British English spelling.
* **Source Code:** Full access to read, modify, and create Kotlin, Java, and configuration files
* **Analysis and Reports:** Actively encouraged to create documentation when analysing code, identifying issues, or documenting architecture

### **Post-Work Documentation Requirements**

At the end of EVERY work session, agents MUST update the following documents:

#### **1. Dev Diary (`docs/project/dev-diary.md`)**
Record daily progress with:
- Date and time of work session
- Summary of what was accomplished
- Technical decisions made and rationale
- Problems encountered and how they were resolved
- Code changes made (high-level summary)
- Build/test status
- Next steps or blockers

#### **2. Backlog (`docs/project/BACKLOG.md`)**
Update task status:
- Mark completed tasks as [DONE] with completion date
- Update [IN PROGRESS] items with current status
- Add [BLOCKED] status if issues prevent completion
- Document new tasks discovered during work as [TODO]
- Include effort estimates and dependencies
- Remove redundant or obsolete items

#### **3. README.md**
Keep repository overview current:
- Update project status and current state
- Reflect major architectural changes
- Update build/setup instructions if changed
- Update feature list and capabilities
- Ensure quick-start remains accurate

#### **4. Architecture Documents (`docs/architecture/`)**
When making architectural decisions:
- Document design decisions in dated files
- Explain trade-offs and alternatives considered
- Update architecture diagrams if applicable
- Cross-reference related documents
- Include migration guides for breaking changes

#### **5. Cross-References**
Ensure consistency:
- Update links when moving or renaming files
- Fix broken references in documentation
- Update table of contents in major documents
- Ensure BACKLOG.md references correct documentation files

#### **6. Documentation Cleanup**
Maintain documentation quality by removing redundancy:
- **After creating new versions:** Delete superseded files that are now covered by comprehensive documents
- **Consolidate duplicates:** Keep only the most recent/complete version of each topic
- **Remove partial work:** Delete partial implementation files once complete versions exist
- **Document cleanup:** Record all removals in `docs/project/DOCUMENTATION_CLEANUP_YYYY-MM-DD_HHMM.md`
- **Criteria for removal:**
  - File content is fully covered by a newer, more comprehensive document
  - Multiple files on same topic exist (keep most complete)
  - Partial phase implementations superseded by complete summaries
  - Outdated analysis or reports with newer versions available
  - Legacy files no longer relevant to current project state
- **Criteria for keeping:**
  - File documents unique historical phase or decision
  - Provides distinct value not captured elsewhere
  - Referenced by active code or other documentation
  - Core project files (README, BACKLOG, dev-diary)
- **Cleanup frequency:** Review and clean documentation after major work sessions or monthly

**Example Cleanup Decisions:**
- ✅ Remove: `ERROR_HANDLING_PHASE1_2025-10-14.md` when `ERROR_HANDLING_COMPLETE_2025-10-14.md` exists
- ✅ Remove: `RESOURCE_FIXES_2025-01-14.md` when `RESOURCE_MANAGEMENT_COMPLETE_2025-01-14.md` covers all fixes
- ❌ Keep: `MAINVIEWMODEL_REFACTORING_ANALYSIS_2025-01-14.md` even with implementation file (analysis provides rationale)
- ❌ Keep: `CONCURRENCY_THREADING_AUDIT_2025-10-14.md` even with complete summary (audit is baseline reference)

### **File and Directory Exclusions**

To maintain focus on relevant source code and documentation, agents MUST ignore the following build output, IDE configuration, and dependency-related directories:

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
