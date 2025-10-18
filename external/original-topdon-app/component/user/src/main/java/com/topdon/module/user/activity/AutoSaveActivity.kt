package com.topdon.module.user.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.topdon.lib.core.common.SharedManager
import com.topdon.lib.core.config.RouterConfig
import com.topdon.module.user.R

@Route(
    path = RouterConfig.AUTO_SAVE
)
class AutoSaveActivity :
    ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        setContent {
            MaterialTheme {
                AutoSaveScreen(
                    initialAutoSave = SharedManager.is04AutoSync,
                    onAutoSaveChanged = { enabled ->
                        SharedManager.is04AutoSync =
                            enabled
                    },
                    onNavigateUp = { finish() }
                )
            }
        }
    }
}

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
private fun AutoSaveScreen(
    initialAutoSave: Boolean,
    onAutoSaveChanged: (Boolean) -> Unit,
    onNavigateUp: () -> Unit
) {
    var autoSaveEnabled by remember {
        mutableStateOf(
            initialAutoSave
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(
                            R.string.auto_save_title
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    padding
                )
                .padding(
                    16.dp
                )
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            16.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(
                            1f
                        )
                    ) {
                        Text(
                            text = stringResource(
                                R.string.auto_save_label
                            ),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(
                            modifier = Modifier.height(
                                4.dp
                            )
                        )
                        Text(
                            text = stringResource(
                                R.string.auto_save_description
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = autoSaveEnabled,
                        onCheckedChange = { enabled ->
                            autoSaveEnabled =
                                enabled
                            onAutoSaveChanged(
                                enabled
                            )
                        }
                    )
                }
            }
        }
    }
}