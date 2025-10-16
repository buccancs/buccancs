package com.topdon.module.user.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.topdon.lib.core.common.SharedManager
import com.topdon.lib.core.tools.AppLanguageUtils
import com.topdon.lib.core.tools.ConstantLanguages
import com.topdon.module.user.R

class LanguageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val initialLanguage = SharedManager.getLanguage(this)
        val initialIndex = getLanguageIndex(initialLanguage)

        setContent {
            MaterialTheme {
                LanguageScreen(
                    initialSelectedIndex = initialIndex,
                    onLanguageSelected = { index ->
                        val localeStr = getLanguageCode(index)
                        setResult(RESULT_OK, Intent().apply {
                            putExtra("localeStr", localeStr)
                        })
                        finish()
                    },
                    onNavigateUp = { finish() }
                )
            }
        }
    }

    private fun getLanguageIndex(languageCode: String): Int {
        return when (languageCode) {
            ConstantLanguages.ENGLISH -> 0
            ConstantLanguages.RU -> 1
            ConstantLanguages.JA -> 2
            ConstantLanguages.GERMAN -> 3
            ConstantLanguages.FR -> 4
            ConstantLanguages.PT -> 5
            ConstantLanguages.ES -> 6
            ConstantLanguages.IT -> 7
            ConstantLanguages.PL -> 8
            ConstantLanguages.CS -> 9
            ConstantLanguages.UK -> 10
            ConstantLanguages.NL -> 11
            ConstantLanguages.ZH_CN -> 12
            ConstantLanguages.ZH_TW -> 13
            else -> 0
        }
    }

    private fun getLanguageCode(index: Int): String {
        return when (index) {
            0 -> ConstantLanguages.ENGLISH
            1 -> ConstantLanguages.RU
            2 -> ConstantLanguages.JA
            3 -> ConstantLanguages.GERMAN
            4 -> ConstantLanguages.FR
            5 -> ConstantLanguages.PT
            6 -> ConstantLanguages.ES
            7 -> ConstantLanguages.IT
            8 -> ConstantLanguages.PL
            9 -> ConstantLanguages.CS
            10 -> ConstantLanguages.UK
            11 -> ConstantLanguages.NL
            12 -> ConstantLanguages.ZH_CN
            13 -> ConstantLanguages.ZH_TW
            else -> AppLanguageUtils.getSystemLanguage()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LanguageScreen(
    initialSelectedIndex: Int,
    onLanguageSelected: (Int) -> Unit,
    onNavigateUp: () -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(initialSelectedIndex) }

    val languages = listOf(
        "English" to "English",
        "Русский" to "Russian",
        "日本語" to "Japanese",
        "Deutsch" to "German",
        "Français" to "French",
        "Português" to "Portuguese",
        "Español" to "Spanish",
        "Italiano" to "Italian",
        "Polski" to "Polish",
        "Čeština" to "Czech",
        "Українська" to "Ukrainian",
        "Nederlands" to "Dutch",
        "简体中文" to "Simplified Chinese",
        "繁體中文" to "Traditional Chinese"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Language") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = { onLanguageSelected(selectedIndex) }
                    ) {
                        Text("Done")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            itemsIndexed(languages) { index, (nativeName, englishName) ->
                LanguageItem(
                    nativeName = nativeName,
                    englishName = englishName,
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index }
                )
            }
        }
    }
}

@Composable
private fun LanguageItem(
    nativeName: String,
    englishName: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(
                text = nativeName,
                style = MaterialTheme.typography.titleMedium
            )
        },
        supportingContent = {
            Text(
                text = englishName,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        trailingContent = {
            if (selected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        modifier = Modifier.clickable(onClick = onClick),
        colors = ListItemDefaults.colors(
            containerColor = if (selected) {
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        )
    )
}

