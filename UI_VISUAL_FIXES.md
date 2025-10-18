# Visual Fix Reference - Before & After

## 1. Main Navigation Buttons

### ❌ BEFORE (MainScreen.kt:196-224)
```kotlin
Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(Spacing.SmallMedium)
) {
    AnimatedButton(
        onClick = onOpenLiveSession,
        modifier = Modifier
            .weight(1f)
            .testTag("nav-live-session")
        ,  // ← Wrong: trailing comma, no minHeight
        text = "Live Session"
    )
    AnimatedTonalButton(
        onClick = onOpenLibrary,
        modifier = Modifier
            .weight(1f)
            .testTag("nav-library")
        ,  // ← Wrong: trailing comma, no minHeight
        text = "Sessions"
    )
    AnimatedOutlinedButton(
        onClick = onOpenSettings,
        modifier = Modifier
            .weight(1f)
            .testTag("nav-settings")
        ,  // ← Wrong: trailing comma, no minHeight
        text = "Settings"
    )
}
```

**Issues:**
- No minimum height → buttons can be < 48dp
- Trailing commas in wrong places
- Hard to tap on some devices

### ✅ AFTER
```kotlin
Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(Spacing.SmallMedium)
) {
    AnimatedButton(
        onClick = onOpenLiveSession,
        modifier = Modifier
            .weight(1f)
            .defaultMinSize(minHeight = Dimensions.TouchTargetMinimum)
            .testTag("nav-live-session"),
        text = "Live Session"
    )
    AnimatedTonalButton(
        onClick = onOpenLibrary,
        modifier = Modifier
            .weight(1f)
            .defaultMinSize(minHeight = Dimensions.TouchTargetMinimum)
            .testTag("nav-library"),
        text = "Sessions"
    )
    AnimatedOutlinedButton(
        onClick = onOpenSettings,
        modifier = Modifier
            .weight(1f)
            .defaultMinSize(minHeight = Dimensions.TouchTargetMinimum)
            .testTag("nav-settings"),
        text = "Settings"
    )
}
```

**Improvements:**
- All buttons ≥ 48dp height
- Proper comma placement
- Consistent touch targets

---

## 2. Device Card Buttons

### ❌ BEFORE (DevicesScreen.kt:267-337)
```kotlin
Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
) {
    if (!device.isConnected) {
        FilledTonalButton(
            onClick = onConnect,
            modifier = Modifier.weight(1f)  // ← Wrong: no minHeight
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.size(Dimensions.IconSizeSmall)
            )
            Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
            Text("Connect")
        }
    } else {
        OutlinedButton(
            onClick = onDisconnect,
            modifier = Modifier.weight(1f)  // ← Wrong: no minHeight
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.size(Dimensions.IconSizeSmall)
            )
            Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
            Text("Disconnect")
        }
    }
}
```

**Issues:**
- Buttons too small
- Inconsistent heights between Connect/Disconnect
- Missing vertical alignment

### ✅ AFTER
```kotlin
Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(Spacing.Small),
    verticalAlignment = Alignment.CenterVertically  // ← Added
) {
    if (!device.isConnected) {
        FilledTonalButton(
            onClick = onConnect,
            modifier = Modifier
                .weight(1f)
                .defaultMinSize(minHeight = Dimensions.TouchTargetMinimum)  // ← Fixed
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.size(Dimensions.IconSizeSmall)
            )
            Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
            Text("Connect")
        }
    } else {
        OutlinedButton(
            onClick = onDisconnect,
            modifier = Modifier
                .weight(1f)
                .defaultMinSize(minHeight = Dimensions.TouchTargetMinimum)  // ← Fixed
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.size(Dimensions.IconSizeSmall)
            )
            Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
            Text("Disconnect")
        }
    }
}
```

**Improvements:**
- All buttons ≥ 48dp
- Consistent heights
- Proper vertical alignment

---

## 3. Hardcoded Spacing

### ❌ BEFORE (LiveSessionScreen.kt:186)
```kotlin
HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))  // ← Wrong
```

### ✅ AFTER
```kotlin
HorizontalDivider(modifier = Modifier.padding(vertical = Spacing.Small))  // ← Correct
```

---

### ❌ BEFORE (LiveSessionScreen.kt:227-228)
```kotlin
Spacer(modifier = Modifier.height(8.dp))  // ← Wrong
```

### ✅ AFTER
```kotlin
Spacer(modifier = Modifier.height(Spacing.Small))  // ← Correct
```

---

### ❌ BEFORE (Multiple files)
```kotlin
Column(
    modifier = Modifier.padding(12.dp),  // ← Wrong
    verticalArrangement = Arrangement.spacedBy(4.dp)  // ← Wrong
) {
    // content
}
```

### ✅ AFTER
```kotlin
Column(
    modifier = Modifier.padding(Spacing.SmallMedium),  // ← Correct
    verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)  // ← Correct
) {
    // content
}
```

---

## 4. Icon Sizes

### ❌ BEFORE (LiveSessionScreen.kt:245)
```kotlin
Icon(
    imageVector = Icons.Default.Warning,
    contentDescription = null,
    tint = MaterialTheme.colorScheme.onErrorContainer,
    modifier = Modifier.size(20.dp)  // ← Wrong: hardcoded
)
```

### ✅ AFTER
```kotlin
Icon(
    imageVector = Icons.Default.Warning,
    contentDescription = null,
    tint = MaterialTheme.colorScheme.onErrorContainer,
    modifier = Modifier.size(Dimensions.IconSizeSmall)  // ← Correct: theme token
)
```

---

### ❌ BEFORE (SettingsScreen.kt:219)
```kotlin
Icon(
    imageVector = Icons.Default.Check,
    contentDescription = null,
    modifier = Modifier.size(Dimensions.IconSizeSmall)  // ← Actually correct!
)
```

**Note:** Some files already use the correct pattern. Don't change these!

---

## 5. Text Overflow

### ❌ BEFORE (DevicesScreen.kt:217)
```kotlin
Text(
    text = device.title,  // ← Can overflow if title is long
    style = MaterialTheme.typography.titleMedium,
    fontWeight = FontWeight.SemiBold
)
```

### ✅ AFTER
```kotlin
Text(
    text = device.title,
    style = MaterialTheme.typography.titleMedium,
    fontWeight = FontWeight.SemiBold,
    maxLines = 1,  // ← Added
    overflow = TextOverflow.Ellipsis  // ← Added
)
```

---

### ❌ BEFORE (MainScreen.kt:109)
```kotlin
Text(
    text = "Multi-Modal Capture",  // ← Can overflow on small screens
    maxLines = 1,
    overflow = TextOverflow.Ellipsis
)
```

**Note:** This one is already correct! ✅

---

## 6. Card Spacing Patterns

### Standard Card
```kotlin
// ✅ Correct for most content cards
SectionCard(
    modifier = Modifier.fillMaxWidth(),
    spacing = Spacing.SmallMedium
) {
    Text("Title", style = MaterialTheme.typography.titleMedium)
    Text("Content", style = MaterialTheme.typography.bodyMedium)
}
```

### Compact Card (Status/Lists)
```kotlin
// ✅ Correct for compact information
SectionCard(
    modifier = Modifier.fillMaxWidth(),
    spacing = Spacing.Small
) {
    InfoRow("Label", "Value")
    InfoRow("Label", "Value")
}
```

### Prominent Card
```kotlin
// ✅ Correct for headers/important content
SectionCard(
    modifier = Modifier.fillMaxWidth(),
    tonal = true,
    spacing = Spacing.Medium
) {
    Text("Important", style = MaterialTheme.typography.titleLarge)
    // More content
}
```

---

## 7. Settings Screen Apply Button

### ❌ BEFORE (SettingsScreen.kt:210-226)
```kotlin
FilledTonalButton(
    onClick = onApply,
    enabled = !state.isApplying,
    modifier = Modifier
        .fillMaxWidth()
        .testTag("settings-apply-orchestrator")  // ← Missing minHeight
) {
    Icon(
        imageVector = Icons.Default.Check,
        contentDescription = null,
        modifier = Modifier.size(Dimensions.IconSizeSmall)
    )
    Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
    Text(text = "Apply Connection")
}
```

### ✅ AFTER
```kotlin
FilledTonalButton(
    onClick = onApply,
    enabled = !state.isApplying,
    modifier = Modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = Dimensions.TouchTargetMinimum)  // ← Added
        .testTag("settings-apply-orchestrator")
) {
    Icon(
        imageVector = Icons.Default.Check,
        contentDescription = null,
        modifier = Modifier.size(Dimensions.IconSizeSmall)
    )
    Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
    Text(text = "Apply Connection")
}
```

---

## 8. Stimulus Preview Button

### ❌ BEFORE (LiveSessionScreen.kt:476-482)
```kotlin
AnimatedTonalButton(
    onClick = onTriggerStimulus,
    modifier = Modifier
        .fillMaxWidth()
        .testTag("stimulus-preview-button"),  // ← Missing minHeight
    text = "Preview Stimulus"
)
```

### ✅ AFTER
```kotlin
AnimatedTonalButton(
    onClick = onTriggerStimulus,
    modifier = Modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = Dimensions.TouchTargetMinimum)  // ← Added
        .testTag("stimulus-preview-button"),
    text = "Preview Stimulus"
)
```

---

## Quick Reference: Common Replacements

### Spacing
```kotlin
2.dp  → Spacing.Micro
4.dp  → Spacing.ExtraSmall
8.dp  → Spacing.Small
12.dp → Spacing.SmallMedium
16.dp → Spacing.Medium
20.dp → Spacing.MediumLarge
24.dp → Spacing.Large
32.dp → Spacing.ExtraLarge
```

### Icon Sizes
```kotlin
16.dp → Dimensions.IconSizeSmall
18.dp → Dimensions.IconSizeSmall  (round down)
20.dp → Dimensions.IconSizeSmall  (round down)
24.dp → Dimensions.IconSizeDefault
32.dp → Dimensions.IconSizeLarge
```

### Button Pattern
```kotlin
// Add to every button's modifier chain:
.defaultMinSize(minHeight = Dimensions.TouchTargetMinimum)
```

### Text in Constrained Space
```kotlin
// Add to text that might overflow:
maxLines = 1,
overflow = TextOverflow.Ellipsis
```

---

## Testing Each Fix

After applying a fix, verify:
1. ✅ Button is easy to tap
2. ✅ Visual alignment looks correct
3. ✅ Spacing feels consistent
4. ✅ Text doesn't overflow
5. ✅ Dark mode looks good
6. ✅ No layout shifts

---

## Files Priority Order

1. **MainScreen.kt** - Most visible, primary navigation
2. **DevicesScreen.kt** - Core functionality
3. **SettingsScreen.kt** - User configuration
4. **LiveSessionScreen.kt** - Main usage screen
5. **TopdonScreen.kt** - Device-specific UI
6. Others - Lower priority

---

## Common Mistakes to Avoid

1. ❌ Don't just find-replace without context
2. ❌ Don't remove 0.dp or None spacing (some are intentional)
3. ❌ Don't change spacing in complex layouts without testing
4. ❌ Don't modify auto-generated code
5. ❌ Don't change test files without updating assertions

## When to NOT Apply These Fixes

- Custom components with specific size requirements
- Animations that need precise sizing
- Third-party library components
- Test mock data

---

Ready to implement! Start with MainScreen.kt navigation buttons for immediate impact.
