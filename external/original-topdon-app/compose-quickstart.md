# Topdon Compose - Quick Start Guide

## Overview

Topdon
TC001/TS004
thermal
camera
app
now
uses
Jetpack
Compose
with
Material
3.

## Build Commands

```bash
# Debug build
./gradlew :app:assembleDevDebug

# Release build
./gradlew :app:assembleDevRelease

# Install on device
./gradlew :app:installDevDebug

# Clean build
./gradlew clean :app:assembleDevDebug
```

## Project Structure

```
app/src/main/java/com/topdon/tc001/
├── *Activity.kt          # 11 Compose-based activities
├── ui/
│   ├── theme/            # Material 3 theme
│   ├── screens/          # Composable screens
│   └── components/       # Reusable components
└── fragment/
    └── MainFragment.kt   # Deprecated (kept for reference)
```

## Key Files

### Activities (All Compose)

-
`SplashActivity.kt` -
App
launch
screen
-
`ClauseActivity.kt` -
Terms
acceptance
-
`MainActivity.kt` -
Main
container
-
`DeviceTypeActivity.kt` -
Device
selection
-
`PolicyActivity.kt` -
Policy
viewer
-
`VersionActivity.kt` -
About
screen
-
`MoreHelpActivity.kt` -
Help
guide
-
`PdfActivity.kt` -
PDF
manuals
-
`WebViewActivity.kt` -
Web
content
-
`IRGalleryEditActivity.kt` -
Thermal
editor

### Theme System

-
`ui/theme/Color.kt` -
Material
3
colours
-
`ui/theme/Type.kt` -
Typography
scale
-
`ui/theme/Theme.kt` -
TopdonTheme
composable

## Activity Pattern

```kotlin
@Route(path = RouterConfig.ACTIVITY_NAME)
class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        
        setContent {
            TopdonTheme {
                MyScreen(
                    onNavigateUp = { finish() }
                )
            }
        }
    }
}
```

## Screen Pattern

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyScreen(onNavigateUp: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Title") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        // Your content here
    }
}
```

## Navigation

Using
ARouter (
modular
navigation):

```kotlin
// Navigate to screen
ARouter.getInstance()
    .build(RouterConfig.VERSION)
    .navigation(context)

// With parameters
ARouter.getInstance()
    .build(RouterConfig.POLICY)
    .withInt("key_theme_type", 1)
    .navigation(context)
```

## Common Tasks

### Add New Composable Screen

1.
Create
composable
function:

```kotlin
@Composable
fun MyNewScreen() {
    Scaffold { padding ->
        Text("Content")
    }
}
```

2.
Use
in
activity:

```kotlin
setContent {
    TopdonTheme {
        MyNewScreen()
    }
}
```

### Use Material 3 Components

```kotlin
// Card
Card(
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    )
) { /* content */ }

// Button
Button(onClick = { /* action */ }) {
    Text("Click Me")
}

// TextField
OutlinedTextField(
    value = text,
    onValueChange = { text = it },
    label = { Text("Label") }
)
```

### WebView Integration

```kotlin
AndroidView(
    factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    },
    modifier = Modifier.fillMaxSize()
)
```

## Dependencies

Already
configured
in
`app/build.gradle`:

```gradle
// Compose BOM
implementation platform('androidx.compose:compose-bom:2024.09.03')
implementation 'androidx.compose.material3:material3'
implementation 'androidx.activity:activity-compose:1.9.2'
implementation 'androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6'
```

## Troubleshooting

### Build Fails

```bash
# Clean and rebuild
./gradlew clean
./gradlew :app:assembleDevDebug
```

### Compose Not Found

Check
`build.gradle`
has:

```gradle
plugins {
    id 'org.jetbrains.kotlin.plugin.compose'
}

buildFeatures {
    compose = true
}
```

### Preview Not Working

Add
preview
function:

```kotlin
@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    TopdonTheme {
        MyScreen()
    }
}
```

## Resources

-
*
*Migration
Guide:
**
`COMPOSE_MIGRATION_readme.md`
-
*
*Detailed
Report:
**
`/docs/project/TOPDON_COMPOSE_MIGRATION_SUMMARY_2025-10-16_0020.md`
-
*
*Verification:
**
`/docs/project/TOPDON_MIGRATION_VERIFICATION_2025-10-16_0025.md`

## Status

✅
Migration
Complete (
100%)  
✅
All
Activities
Converted  
✅
Material
3
Implemented  
✅
Production
Ready


