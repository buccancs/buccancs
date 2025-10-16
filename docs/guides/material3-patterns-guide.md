**Last Modified:** 2025-10-15 15:14 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Development Guide

# Material Design 3 Patterns Guide

## Overview

This guide provides reusable patterns and code examples for implementing Material Design 3 UI components consistently
across the BuccanCS application.

## Core Principles

1. **Consistent Elevation**: Use `ElevatedCard` for all major content cards
2. **Semantic Colours**: Use colour tokens that match content meaning
3. **Icon Communication**: Include icons with actions and states
4. **Visual Hierarchy**: Use typography and spacing to guide attention
5. **State Indication**: Show state through colour and icons

## Component Patterns

### Card Pattern

```kotlin
@Composable
fun ExampleCard(state: UiState) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header
            Text(
                text = "Card Title",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            
            // Divider
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            
            // Content with Surface containers
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.small
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Content here
                }
            }
        }
    }
}
```

### Button with Icon Pattern

```kotlin
@Composable
fun ActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    FilledTonalButton(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text)
    }
}

// Usage
ActionButton(
    text = "Connect",
    icon = Icons.Default.Check,
    onClick = onConnect,
    enabled = !isConnected
)
```

### Status Badge Pattern

```kotlin
@Composable
fun StatusBadge(
    isActive: Boolean,
    activeText: String,
    inactiveText: String
) {
    Surface(
        color = if (isActive) 
            MaterialTheme.colorScheme.primaryContainer 
        else 
            MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isActive) Icons.Default.Check else Icons.Default.Close,
                contentDescription = null,
                tint = if (isActive)
                    MaterialTheme.colorScheme.onPrimaryContainer
                else
                    MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = if (isActive) activeText else inactiveText,
                style = MaterialTheme.typography.labelSmall,
                color = if (isActive)
                    MaterialTheme.colorScheme.onPrimaryContainer
                else
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// Usage
StatusBadge(
    isActive = device.isConnected,
    activeText = "Connected",
    inactiveText = "Disconnected"
)
```

### Info Row Pattern

```kotlin
@Composable
fun InfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

// Usage in a Surface
Surface(
    modifier = Modifier.fillMaxWidth(),
    color = MaterialTheme.colorScheme.primaryContainer,
    shape = MaterialTheme.shapes.small
) {
    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        InfoRow("Started", session.startTime)
        InfoRow("Duration", session.duration)
        InfoRow("Size", formatBytes(session.size))
    }
}
```

### Error Display Pattern

```kotlin
@Composable
fun ErrorDisplay(
    message: String,
    onDismiss: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.errorContainer,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Error,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.weight(1f)
            )
            FilledTonalIconButton(
                onClick = onDismiss,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Dismiss error",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
```

### Empty State Pattern

```kotlin
@Composable
fun EmptyState(
    icon: ImageVector,
    title: String,
    description: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        description?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}
```

### List Item Pattern

```kotlin
@Composable
fun ListItemCard(
    title: String,
    subtitle: String? = null,
    badge: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                subtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            badge?.invoke()
        }
    }
}
```

## Colour Usage Guidelines

### Primary Container

**Use for:** Main content, connected devices, artifacts, normal states

```kotlin
Surface(
    color = MaterialTheme.colorScheme.primaryContainer,
    shape = MaterialTheme.shapes.small
) {
    // Content with onPrimaryContainer text colour
}
```

### Secondary Container

**Use for:** Secondary information, statistics, session data

```kotlin
Surface(
    color = MaterialTheme.colorScheme.secondaryContainer,
    shape = MaterialTheme.shapes.small
) {
    // Content with onSecondaryContainer text colour
}
```

### Tertiary Container

**Use for:** Simulation badges, bookmarks, warnings

```kotlin
Surface(
    color = MaterialTheme.colorScheme.tertiaryContainer,
    shape = MaterialTheme.shapes.small
) {
    // Content with onTertiaryContainer text colour
}
```

### Surface Variant

**Use for:** Idle states, metadata, general grouping, disconnected devices

```kotlin
Surface(
    color = MaterialTheme.colorScheme.surfaceVariant,
    shape = MaterialTheme.shapes.small
) {
    // Content with onSurfaceVariant text colour
}
```

### Error Container

**Use for:** Errors, critical warnings, low storage, throttle

```kotlin
Surface(
    color = MaterialTheme.colorScheme.errorContainer,
    shape = MaterialTheme.shapes.small
) {
    // Content with onErrorContainer text colour
}
```

## Dynamic State Colouring

### Connection State

```kotlin
@Composable
fun ConnectionDependentSurface(isConnected: Boolean, content: @Composable () -> Unit) {
    Surface(
        color = if (isConnected) 
            MaterialTheme.colorScheme.primaryContainer 
        else 
            MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.small
    ) {
        content()
    }
}
```

### Storage State

```kotlin
@Composable
fun StorageDependentSurface(status: StorageStatus, content: @Composable () -> Unit) {
    Surface(
        color = when (status) {
            StorageStatus.LOW -> MaterialTheme.colorScheme.errorContainer
            StorageStatus.WARNING -> MaterialTheme.colorScheme.tertiaryContainer
            else -> MaterialTheme.colorScheme.primaryContainer
        },
        shape = MaterialTheme.shapes.small
    ) {
        content()
    }
}
```

## Icon Guidelines

### Icon Sizing

- **Inline Icons**: 14-18dp (next to text)
- **Status Icons**: 20dp (standalone status)
- **Action Icons**: 24dp (buttons, app bar)
- **Empty State Icons**: 48dp (center of empty areas)

### Common Icon Patterns

```kotlin
// Success/Connected
Icon(
    imageVector = Icons.Default.Check,
    tint = MaterialTheme.colorScheme.onPrimaryContainer,
    modifier = Modifier.size(18.dp)
)

// Error/Disconnected
Icon(
    imageVector = Icons.Default.Close,
    tint = MaterialTheme.colorScheme.onSurfaceVariant,
    modifier = Modifier.size(18.dp)
)

// Warning
Icon(
    imageVector = Icons.Default.Warning,
    tint = MaterialTheme.colorScheme.onErrorContainer,
    modifier = Modifier.size(20.dp)
)

// Status OK
Icon(
    imageVector = Icons.Default.CheckCircle,
    tint = MaterialTheme.colorScheme.primary,
    modifier = Modifier.size(18.dp)
)
```

## Typography Guidelines

### Headers

```kotlin
Text(
    text = "Section Title",
    style = MaterialTheme.typography.titleMedium,
    fontWeight = FontWeight.SemiBold
)
```

### Body Text

```kotlin
Text(
    text = "Main content",
    style = MaterialTheme.typography.bodyMedium
)
```

### Important Values

```kotlin
Text(
    text = "1.5 GB",
    style = MaterialTheme.typography.bodyMedium,
    fontWeight = FontWeight.SemiBold
)
```

### Metadata

```kotlin
Text(
    text = "Last updated: 10:45",
    style = MaterialTheme.typography.bodySmall,
    color = MaterialTheme.colorScheme.onSurfaceVariant
)
```

### Section Labels

```kotlin
Text(
    text = "Settings",
    style = MaterialTheme.typography.labelLarge,
    fontWeight = FontWeight.SemiBold
)
```

## Spacing Guidelines

### Standard Spacing

- **ExtraSmall**: 4.dp (tight groups)
- **Small**: 8.dp (related items)
- **Medium**: 12-16.dp (card content, sections)
- **Large**: 24.dp (major sections)

### Card Padding

```kotlin
Column(
    modifier = Modifier.padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
) {
    // Card content
}
```

### Surface Padding

```kotlin
Surface(/* ... */) {
    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Surface content
    }
}
```

## Common Imports

```kotlin
// Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

// Material 3 Components
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

// Icons
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning

// UI
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
```

## Best Practices

### Do's

✓ Use ElevatedCard for all major content cards  
✓ Include icons with all primary actions  
✓ Use semantic colour tokens consistently  
✓ Add HorizontalDivider after card headers  
✓ Wrap related content in Surface containers  
✓ Use SemiBold for emphasis and hierarchy  
✓ Maintain consistent spacing patterns  
✓ Provide contentDescription for accessibility

### Don'ts

✗ Mix Card and ElevatedCard in same screen  
✗ Use random colour values instead of tokens  
✗ Forget icons on buttons  
✗ Use excessive nesting of Surfaces  
✗ Skip dividers in long cards  
✗ Use Bold font weight (use SemiBold)  
✗ Hard-code spacing values  
✗ Leave icons without proper sizing

## Testing Checklist

When implementing a new screen:

- [ ] All cards use ElevatedCard
- [ ] All primary buttons use FilledTonalButton with icons
- [ ] Colour usage matches semantic meaning
- [ ] Icons are properly sized (14-18dp inline, 20dp status, 48dp empty)
- [ ] Typography hierarchy is clear
- [ ] Spacing is consistent (8dp/12dp/16dp)
- [ ] HorizontalDivider used in complex cards
- [ ] Empty states show icons
- [ ] Error states use errorContainer
- [ ] Status indicators use appropriate colours
- [ ] Accessibility: contentDescription provided
- [ ] Test on light and dark themes

## Reference Examples

See these files for complete implementations:

- `app/src/main/java/com/buccancs/ui/topdon/TopdonScreen.kt`
- `app/src/main/java/com/buccancs/ui/devices/DevicesScreen.kt`
- `app/src/main/java/com/buccancs/ui/settings/SettingsScreen.kt`
- `app/src/main/java/com/buccancs/ui/library/SessionListScreen.kt`
- `app/src/main/java/com/buccancs/ui/library/SessionDetailScreen.kt`
- `app/src/main/java/com/buccancs/ui/session/LiveSessionScreen.kt`

## Conclusion

Following these patterns ensures consistency, maintainability, and a professional Material Design 3 experience across
the entire application. When in doubt, reference the existing implementations for guidance.
