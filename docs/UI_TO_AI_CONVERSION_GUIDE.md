# UI to AI Conversion Guide
## Converting Rendered UI to AI-Understandable Formats

This guide explains how to convert rendered UI into structured data that AI agents can analyze to detect layout issues, broken buttons, text wrapping problems, and other visual defects.

---

## Overview

There are **four main approaches** to make rendered UI understandable to AI:

1. **UI Hierarchy Dumps** (XML/JSON) - Structural representation
2. **Accessibility Tree** - Semantic representation
3. **Screenshot + Vision AI** - Visual analysis
4. **Hybrid Approach** - Combined structural + visual

---

## 1. UI Hierarchy Dumps (Currently Used)

### What It Is
Android UI Automator provides XML dumps of the view hierarchy with all properties.

### How to Generate

```bash
# Android Device/Emulator
adb shell uiautomator dump
adb pull /sdcard/window_dump.xml

# With better formatting
adb shell uiautomator dump --compressed=false
```

### Example Output Structure

```xml
<node index="0" 
      text="Connect" 
      resource-id="" 
      class="android.widget.Button" 
      package="com.buccancs" 
      content-desc="Connect to device" 
      bounds="[96,600][984,744]"
      clickable="true" 
      enabled="true" 
      focused="false">
  <node index="0" 
        text="Connect" 
        class="android.widget.TextView" 
        bounds="[507,648][633,696]"/>
</node>
```

### AI-Readable Conversion

Transform XML to structured JSON for AI processing:

```kotlin
// File: app/src/androidTest/java/com/buccancs/testing/UiHierarchyAnalyzer.kt

data class UiElement(
    val id: String,
    val type: String,
    val text: String,
    val contentDescription: String,
    val bounds: Rectangle,
    val isClickable: Boolean,
    val isEnabled: Boolean,
    val isFocused: Boolean,
    val children: List<UiElement>,
    // AI-friendly computed properties
    val width: Int,
    val height: Int,
    val centerX: Int,
    val centerY: Int,
    val isLikelyTruncated: Boolean,  // Text width vs available space
    val hasOverlappingElements: Boolean,
    val visualDensity: Float  // Children count / area
)

data class Rectangle(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int
) {
    val width get() = right - left
    val height get() = bottom - top
    val area get() = width * height
    val centerX get() = left + width / 2
    val centerY get() = top + height / 2
}

object UiHierarchyAnalyzer {
    
    fun parseXmlDump(xmlContent: String): UiElement {
        // Parse XML and convert to UiElement tree
        val doc = Jsoup.parse(xmlContent)
        return parseNode(doc.select("hierarchy > node").first())
    }
    
    private fun parseNode(element: org.jsoup.nodes.Element): UiElement {
        val boundsStr = element.attr("bounds")
        val bounds = parseBounds(boundsStr)
        
        return UiElement(
            id = element.attr("resource-id"),
            type = element.attr("class"),
            text = element.attr("text"),
            contentDescription = element.attr("content-desc"),
            bounds = bounds,
            isClickable = element.attr("clickable") == "true",
            isEnabled = element.attr("enabled") == "true",
            isFocused = element.attr("focused") == "true",
            children = element.children().map { parseNode(it) },
            width = bounds.width,
            height = bounds.height,
            centerX = bounds.centerX,
            centerY = bounds.centerY,
            isLikelyTruncated = checkTextTruncation(element),
            hasOverlappingElements = false, // Computed after full tree
            visualDensity = 0f // Computed after full tree
        )
    }
    
    private fun parseBounds(boundsStr: String): Rectangle {
        // "[96,600][984,744]" -> Rectangle(96, 600, 984, 744)
        val coords = boundsStr
            .removeSurrounding("[", "]")
            .split("][")
            .flatMap { it.split(",") }
            .map { it.toInt() }
        return Rectangle(coords[0], coords[1], coords[2], coords[3])
    }
    
    private fun checkTextTruncation(element: org.jsoup.nodes.Element): Boolean {
        val text = element.attr("text")
        if (text.isEmpty()) return false
        
        val bounds = parseBounds(element.attr("bounds"))
        val estimatedTextWidth = text.length * 20 // Rough estimate: 20px per char
        
        // Text is likely truncated if estimated width > 90% of available width
        return estimatedTextWidth > bounds.width * 0.9
    }
}
```

### Generate AI-Friendly Report

```kotlin
data class UiIssue(
    val severity: Severity,
    val type: IssueType,
    val element: UiElement,
    val description: String,
    val suggestion: String
)

enum class Severity { CRITICAL, HIGH, MEDIUM, LOW }

enum class IssueType {
    TEXT_TRUNCATED,
    TEXT_WRAPPED,
    BUTTON_TOO_SMALL,
    OVERLAPPING_ELEMENTS,
    INVISIBLE_TEXT,
    CLICKABLE_AREA_TOO_SMALL,
    CONTRAST_ISSUE,
    MISSING_CONTENT_DESCRIPTION
}

object UiIssueDetector {
    
    fun analyzeHierarchy(root: UiElement): List<UiIssue> {
        val issues = mutableListOf<UiIssue>()
        
        // Check all elements recursively
        traverseAndCheck(root, issues)
        
        return issues
    }
    
    private fun traverseAndCheck(element: UiElement, issues: MutableList<UiIssue>) {
        // 1. Check for text truncation/wrapping
        if (element.isLikelyTruncated) {
            issues.add(UiIssue(
                severity = Severity.HIGH,
                type = IssueType.TEXT_TRUNCATED,
                element = element,
                description = "Text '${element.text}' appears truncated. " +
                    "Available width: ${element.width}px, estimated need: ${element.text.length * 20}px",
                suggestion = "Increase button/container width or reduce text length"
            ))
        }
        
        // 2. Check button minimum touch target (48dp = ~144px on 3x density)
        if (element.isClickable && (element.width < 144 || element.height < 144)) {
            issues.add(UiIssue(
                severity = Severity.MEDIUM,
                type = IssueType.CLICKABLE_AREA_TOO_SMALL,
                element = element,
                description = "Clickable element too small: ${element.width}x${element.height}px. " +
                    "Android recommends minimum 48dp (144px at 3x)",
                suggestion = "Increase padding or minimum size to 48dp"
            ))
        }
        
        // 3. Check for multi-line text in buttons
        if (element.type.contains("Button") && element.children.isNotEmpty()) {
            val textNodes = element.children.filter { it.type.contains("TextView") }
            if (textNodes.size > 1 || 
                textNodes.any { it.bounds.height > element.bounds.height * 0.6 }) {
                issues.add(UiIssue(
                    severity = Severity.MEDIUM,
                    type = IssueType.TEXT_WRAPPED,
                    element = element,
                    description = "Button text appears to wrap to multiple lines",
                    suggestion = "Use single-line text or increase button width"
                ))
            }
        }
        
        // 4. Check for missing accessibility labels
        if (element.isClickable && 
            element.contentDescription.isEmpty() && 
            element.text.isEmpty()) {
            issues.add(UiIssue(
                severity = Severity.MEDIUM,
                type = IssueType.MISSING_CONTENT_DESCRIPTION,
                element = element,
                description = "Clickable element has no text or content description",
                suggestion = "Add contentDescription for accessibility"
            ))
        }
        
        // 5. Check for overlapping clickable elements
        checkOverlaps(element, issues)
        
        // Recurse into children
        element.children.forEach { traverseAndCheck(it, issues) }
    }
    
    private fun checkOverlaps(element: UiElement, issues: MutableList<UiIssue>) {
        if (!element.isClickable) return
        
        // Check if any siblings overlap
        element.children.filter { it.isClickable }.forEach { sibling ->
            if (boundsOverlap(element.bounds, sibling.bounds)) {
                issues.add(UiIssue(
                    severity = Severity.HIGH,
                    type = IssueType.OVERLAPPING_ELEMENTS,
                    element = element,
                    description = "Clickable element overlaps with another: ${sibling.text}",
                    suggestion = "Adjust layout to prevent overlapping touch targets"
                ))
            }
        }
    }
    
    private fun boundsOverlap(a: Rectangle, b: Rectangle): Boolean {
        return !(a.right < b.left || a.left > b.right || 
                 a.bottom < b.top || a.top > b.bottom)
    }
}
```

### AI-Consumable JSON Output

```json
{
  "screen": "DevicesScreen",
  "timestamp": "2025-10-17T13:00:00Z",
  "device": {
    "model": "Pixel 6",
    "resolution": "1080x2400",
    "density": 3.0
  },
  "issues": [
    {
      "severity": "HIGH",
      "type": "TEXT_TRUNCATED",
      "element": {
        "type": "android.widget.Button",
        "text": "Configure Device Settings",
        "bounds": {"left": 96, "top": 600, "right": 300, "bottom": 744},
        "path": "/hierarchy/node[0]/node[2]/node[3]"
      },
      "description": "Text 'Configure Device Settings' appears truncated. Available width: 204px, estimated need: 500px",
      "suggestion": "Increase button width or reduce text to 'Configure' or 'Settings'",
      "confidence": 0.89
    },
    {
      "severity": "MEDIUM",
      "type": "TEXT_WRAPPED",
      "element": {
        "type": "android.widget.Button",
        "text": "Start Recording",
        "bounds": {"left": 120, "top": 624, "right": 278, "bottom": 768},
        "textBounds": {"left": 168, "top": 677, "right": 230, "bottom": 716}
      },
      "description": "Button text appears to wrap to 2 lines",
      "metrics": {
        "buttonWidth": 158,
        "textWidth": 62,
        "textHeight": 39,
        "expectedSingleLineHeight": 24
      },
      "suggestion": "Use 'Record' instead of 'Start Recording' or increase button width",
      "confidence": 0.95
    }
  ],
  "summary": {
    "totalIssues": 2,
    "critical": 0,
    "high": 1,
    "medium": 1,
    "low": 0
  }
}
```

---

## 2. Accessibility Tree (Compose Semantics)

### What It Is
Compose provides a semantics tree optimized for accessibility and testing.

### How to Enable

```kotlin
// In your Composable
@Composable
fun MyButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .semantics {
                contentDescription = "Connect to device"
                testTag = "connect-button"
                // Custom semantics for AI analysis
                val customProperties = mapOf(
                    "expectedTextLines" to 1,
                    "minWidth" to 200,
                    "purpose" to "primary-action"
                )
            }
    ) {
        Text("Connect")
    }
}
```

### Extract Semantics Tree

```kotlin
// In Android Test
@Test
fun extractSemanticsForAI() {
    composeTestRule.setContent {
        DevicesScreen()
    }
    
    // Get semantics tree
    val semantics = composeTestRule.onRoot().printToLog("SEMANTICS")
    
    // Or programmatically
    val tree = composeTestRule.onRoot().fetchSemanticsNode()
    val aiFormat = convertSemanticsToAiFormat(tree)
    
    File("/sdcard/semantics.json").writeText(
        Json.encodeToString(aiFormat)
    )
}

fun convertSemanticsToAiFormat(node: SemanticsNode): JsonElement {
    return buildJsonObject {
        put("id", node.id)
        put("bounds", buildJsonObject {
            put("left", node.boundsInRoot.left)
            put("top", node.boundsInRoot.top)
            put("right", node.boundsInRoot.right)
            put("bottom", node.boundsInRoot.bottom)
        })
        
        node.config.forEach { (key, value) ->
            put(key.name, value.toString())
        }
        
        putJsonArray("children") {
            node.children.forEach { child ->
                add(convertSemanticsToAiFormat(child))
            }
        }
    }
}
```

---

## 3. Screenshot + Vision AI Analysis

### Capture Screenshot

```bash
# Android
adb shell screencap /sdcard/screen.png
adb pull /sdcard/screen.png

# Or programmatically
@Test
fun captureScreenshot() {
    composeTestRule.setContent { DevicesScreen() }
    composeTestRule.onRoot().captureToImage().asAndroidBitmap()
        .compress(Bitmap.CompressFormat.PNG, 100, FileOutputStream("screen.png"))
}
```

### Vision AI Analysis with GPT-4V / Claude

```kotlin
data class VisionAnalysisRequest(
    val image: String, // Base64
    val hierarchy: UiElement, // From XML dump
    val prompt: String
)

suspend fun analyzeUiWithVision(
    screenshot: File,
    hierarchy: File
): List<UiIssue> {
    val imageBase64 = screenshot.readBytes().encodeBase64()
    val hierarchyData = UiHierarchyAnalyzer.parseXmlDump(hierarchy.readText())
    
    val prompt = """
    Analyze this mobile app UI screenshot and accompanying hierarchy data.
    
    Identify visual issues including:
    1. Text that is truncated or cut off
    2. Buttons with text wrapping to multiple lines
    3. Overlapping UI elements
    4. Elements that extend beyond screen boundaries
    5. Contrast issues (text hard to read)
    6. Misaligned elements
    7. Inconsistent spacing
    
    For each issue, provide:
    - Element location (coordinates from hierarchy)
    - Issue type and severity
    - Description of the problem
    - Suggested fix
    
    Hierarchy data: ${Json.encodeToString(hierarchyData)}
    
    Return as JSON matching the UiIssue schema.
    """.trimIndent()
    
    val response = openAI.chat.completions.create {
        model = "gpt-4-vision-preview"
        messages = listOf(
            ChatMessage(
                role = "user",
                content = listOf(
                    ContentPart.Text(prompt),
                    ContentPart.Image(imageBase64)
                )
            )
        )
        responseFormat = ResponseFormat.Json
    }
    
    return Json.decodeFromString<List<UiIssue>>(response.choices[0].message.content)
}
```

### Example Vision AI Prompt

```
You are a UI/UX expert analyzing mobile app screenshots.

Given:
- Screenshot (attached)
- UI hierarchy with element bounds and properties (JSON)

Detect issues:

1. TEXT TRUNCATION: Text ending with "..." or visibly cut off
   - Compare text bounds vs container bounds
   - Check if text width exceeds 90% of container width

2. TEXT WRAPPING: Multi-line text in buttons
   - Single-line buttons should have text height < 40% of button height
   - If text height > 50%, likely wrapped

3. SMALL TOUCH TARGETS: Buttons < 48dp (144px at 3x density)
   - Critical for usability

4. OVERLAPPING: Elements with intersecting bounds
   - Especially clickable elements

5. OFF-SCREEN: Elements partially or fully outside screen bounds

For each issue, return JSON:
{
  "severity": "HIGH" | "MEDIUM" | "LOW",
  "type": "TEXT_TRUNCATED" | "TEXT_WRAPPED" | ...,
  "element": { "type": "...", "text": "...", "bounds": {...} },
  "description": "Detailed explanation",
  "visualEvidence": "What you see in the screenshot",
  "suggestion": "How to fix",
  "confidence": 0.0-1.0
}
```

---

## 4. Hybrid Approach (Recommended)

Combine structural and visual analysis for best results:

```kotlin
data class HybridAnalysis(
    val screenshot: File,
    val hierarchy: UiElement,
    val semantics: JsonElement? = null
)

suspend fun comprehensiveUiAnalysis(input: HybridAnalysis): AnalysisReport {
    // 1. Structural analysis (fast, rule-based)
    val structuralIssues = UiIssueDetector.analyzeHierarchy(input.hierarchy)
    
    // 2. Vision analysis (slower, more nuanced)
    val visionIssues = analyzeUiWithVision(input.screenshot, input.hierarchy)
    
    // 3. Merge and deduplicate
    val allIssues = mergeIssues(structuralIssues, visionIssues)
    
    // 4. Generate report
    return AnalysisReport(
        timestamp = Clock.System.now(),
        screen = detectScreenName(input.hierarchy),
        device = getDeviceInfo(),
        issues = allIssues.sortedByDescending { it.severity },
        recommendations = generateRecommendations(allIssues),
        metrics = calculateMetrics(input.hierarchy)
    )
}

fun mergeIssues(
    structural: List<UiIssue>,
    vision: List<UiIssue>
): List<UiIssue> {
    val merged = mutableListOf<UiIssue>()
    val seen = mutableSetOf<String>()
    
    // Prefer vision AI for visual issues, structural for layout issues
    (vision + structural).forEach { issue ->
        val key = "${issue.element.bounds}-${issue.type}"
        if (key !in seen) {
            seen.add(key)
            merged.add(issue)
        }
    }
    
    return merged
}
```

---

## 5. Automated Testing Integration

### Compose Test Rule Extension

```kotlin
fun ComposeTestRule.captureUiState(): UiState {
    val screenshot = onRoot().captureToImage().asAndroidBitmap()
    val semantics = onRoot().fetchSemanticsNode()
    
    // Also get UI Automator dump
    val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    val dumpFile = File(InstrumentationRegistry.getInstrumentation().targetContext.filesDir, "window_dump.xml")
    device.dumpWindowHierarchy(dumpFile)
    
    return UiState(
        screenshot = screenshot,
        hierarchy = UiHierarchyAnalyzer.parseXmlDump(dumpFile.readText()),
        semantics = convertSemanticsToAiFormat(semantics)
    )
}

@Test
fun detectUiIssues() {
    composeTestRule.setContent {
        DevicesScreen()
    }
    
    val uiState = composeTestRule.captureUiState()
    val issues = UiIssueDetector.analyzeHierarchy(uiState.hierarchy)
    
    // Assert no critical issues
    val criticalIssues = issues.filter { it.severity == Severity.CRITICAL }
    assertTrue("Found ${criticalIssues.size} critical UI issues: $criticalIssues",
        criticalIssues.isEmpty())
    
    // Generate report
    generateHtmlReport(uiState, issues)
}
```

---

## 6. Real-World Example: Detecting Button Text Wrapping

```kotlin
@Test
fun detectButtonTextWrapping() {
    composeTestRule.setContent {
        Button(onClick = {}) {
            Text("This is a very long button text that might wrap")
        }
    }
    
    // Get UI state
    val hierarchy = captureHierarchy()
    
    // Find button
    val button = findElement(hierarchy) { 
        it.type.contains("Button") && it.text.contains("very long")
    }
    
    // Analyze
    val textChild = button.children.firstOrNull { it.type.contains("TextView") }
    checkNotNull(textChild) { "Button has no text child" }
    
    // Check if text is wrapped
    val isWrapped = textChild.height > button.height * 0.5
    val textFitsWidth = estimateTextWidth(textChild.text) <= button.width
    
    if (isWrapped || !textFitsWidth) {
        fail("""
            Button text appears to wrap or be truncated:
            - Button size: ${button.width}x${button.height}px
            - Text size: ${textChild.width}x${textChild.height}px
            - Estimated text width: ${estimateTextWidth(textChild.text)}px
            - Text: "${textChild.text}"
            
            Suggestion: Reduce text to "Long Text" or increase button width to 400px
        """.trimIndent())
    }
}

fun estimateTextWidth(text: String, fontSize: Float = 16f): Int {
    // Rough estimation: average character is ~60% of font size
    return (text.length * fontSize * 0.6).toInt()
}
```

---

## 7. AI Prompt Templates

### For Issue Detection

```
I have a mobile app UI with the following structure:

HIERARCHY:
{hierarchy_json}

SCREENSHOT:
[Attached image]

Please analyze for these specific issues:

1. **Buttons with wrapped text**: Any button where text spans >1 line
   - Look for TextView height > 50% of Button height
   - Check if visual text appears on multiple lines

2. **Truncated text**: Text ending with "..." or visibly cut off
   - Compare text content length vs available width
   - Look for ellipsis in screenshot

3. **Small touch targets**: Clickable elements < 48dp (144px)
   - Check bounds: width and height should be >= 144px

Return JSON array of issues with:
- exact element bounds from hierarchy
- visual confirmation from screenshot
- severity (CRITICAL/HIGH/MEDIUM/LOW)
- fix suggestion
```

### For Comparison

```
Compare these two UI states and identify regressions:

BEFORE:
{before_state}

AFTER:
{after_state}

Detect:
1. Elements that changed position unexpectedly
2. New text wrapping issues
3. Elements that became smaller
4. New overlapping issues
5. Missing elements

For each regression, explain the change and its impact.
```

---

## 8. Best Practices

### For AI-Friendly UI Dumps

1. **Add semantic metadata**:
   ```kotlin
   Modifier.semantics {
       contentDescription = "Device connection status"
       testTag = "device-status-shimmer-gsr"
       set(CustomKey, "expected-single-line")
   }
   ```

2. **Include metrics in dumps**:
   ```json
   {
     "element": "Button",
     "bounds": {...},
     "textMetrics": {
       "fontSize": 16,
       "lineCount": 2,
       "estimatedSingleLineWidth": 300,
       "actualWidth": 200
     }
   }
   ```

3. **Annotate expected behavior**:
   ```kotlin
   @Composable
   @UiExpectation(
       name = "ConnectButton",
       maxLines = 1,
       minWidth = 200,
       minHeight = 48
   )
   fun ConnectButton() { ... }
   ```

4. **Generate diff-friendly outputs**:
   - Use consistent ordering
   - Include stable IDs
   - Add timestamps

### For AI Analysis

1. **Provide context**: Include screen name, user flow, device specs
2. **Use structured formats**: JSON > XML > plain text
3. **Include both visual and structural**: Screenshot + hierarchy
4. **Add reference data**: Expected layouts, design specs
5. **Enable iterative analysis**: Support follow-up questions

---

## 9. Implementation in This Project

### Files to Create

```
app/src/androidTest/java/com/buccancs/testing/
├── ui/
│   ├── UiHierarchyAnalyzer.kt       # Parse XML dumps
│   ├── UiIssueDetector.kt           # Rule-based detection
│   ├── VisionAnalyzer.kt            # Vision AI integration
│   └── UiStateCapture.kt            # Capture utilities
├── models/
│   ├── UiElement.kt                 # Data models
│   ├── UiIssue.kt
│   └── AnalysisReport.kt
└── reports/
    ├── HtmlReportGenerator.kt       # Generate visual reports
    └── JsonReportGenerator.kt       # API-friendly reports
```

### Example Test

```kotlin
@RunWith(AndroidJUnit4::class)
class DevicesScreenUiTest {
    
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    
    @Test
    fun detectAllUiIssues() {
        // Navigate to Devices screen
        composeTestRule.setContent {
            BuccancsApp()
        }
        composeTestRule.onNodeWithText("Devices").performClick()
        composeTestRule.waitForIdle()
        
        // Capture UI state
        val uiState = composeTestRule.captureUiState()
        
        // Analyze
        val issues = runBlocking {
            comprehensiveUiAnalysis(
                HybridAnalysis(
                    screenshot = uiState.screenshotFile,
                    hierarchy = uiState.hierarchy,
                    semantics = uiState.semantics
                )
            )
        }
        
        // Generate report
        val reportFile = File("build/reports/ui-analysis-devices.html")
        HtmlReportGenerator.generate(issues, uiState, reportFile)
        
        // Assert
        val criticalIssues = issues.issues.filter { it.severity == Severity.CRITICAL }
        assertTrue(
            "Found ${criticalIssues.size} critical issues. See $reportFile",
            criticalIssues.isEmpty()
        )
    }
}
```

---

## 10. Summary

**Best Approach for Your Use Case:**

1. **Quick automated checks**: UI Hierarchy XML + Rule-based detector
2. **Comprehensive analysis**: Hybrid (Hierarchy + Screenshot + Vision AI)
3. **CI/CD integration**: Automated tests with JSON reports
4. **Manual review**: HTML reports with side-by-side screenshots

**Key Advantages:**
- ✅ Detects layout issues invisible to unit tests
- ✅ Validates against design specs
- ✅ Finds regressions across app updates
- ✅ Provides actionable fix suggestions
- ✅ Integrates with existing test suites

**Next Steps:**
1. Implement `UiHierarchyAnalyzer` for your existing XML dumps
2. Add Vision AI analysis for critical screens
3. Create automated tests for key user flows
4. Generate HTML reports for manual review
5. Integrate into CI pipeline
