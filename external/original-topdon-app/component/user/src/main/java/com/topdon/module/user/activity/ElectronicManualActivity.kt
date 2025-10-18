package com.topdon.module.user.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.topdon.lib.core.config.RouterConfig
import com.topdon.lib.core.utils.Constants
import com.topdon.module.user.R

@Route(
    path = RouterConfig.ELECTRONIC_MANUAL
)
class ElectronicManualActivity :
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

        val productType =
            intent.getIntExtra(
                Constants.SETTING_TYPE,
                0
            )

        setContent {
            MaterialTheme {
                ElectronicManualScreen(
                    productType = productType,
                    onItemClick = { isTS001 ->
                        if (isTS001) {
                            if (productType == Constants.SETTING_BOOK) {
                            } else {
                                ARouter.getInstance()
                                    .build(
                                        RouterConfig.QUESTION
                                    )
                                    .withBoolean(
                                        "isTS001",
                                        true
                                    )
                                    .navigation(
                                        this
                                    )
                            }
                        } else {
                            if (productType == Constants.SETTING_BOOK) {
                                ARouter.getInstance()
                                    .build(
                                        RouterConfig.PDF
                                    )
                                    .withBoolean(
                                        "isTS001",
                                        false
                                    )
                                    .navigation(
                                        this
                                    )
                            } else {
                                ARouter.getInstance()
                                    .build(
                                        RouterConfig.QUESTION
                                    )
                                    .withBoolean(
                                        "isTS001",
                                        false
                                    )
                                    .navigation(
                                        this
                                    )
                            }
                        }
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
private fun ElectronicManualScreen(
    productType: Int,
    onItemClick: (isTS001: Boolean) -> Unit,
    onNavigateUp: () -> Unit
) {
    val items =
        remember(
            productType
        ) {
            buildList {
                if (productType == 1) {
                    add("TS001")
                }
                add("TS004")
            }
        }

    val title =
        if (productType == Constants.SETTING_BOOK) {
            stringResource(
                R.string.electronic_manual
            )
        } else {
            stringResource(
                R.string.app_question
            )
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        title
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    padding
                )
                .padding(
                    16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(
                8.dp
            )
        ) {
            itemsIndexed(
                items
            ) { index, item ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val isTS001 =
                                productType == 1 && index == 0
                            onItemClick(
                                isTS001
                            )
                        }
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
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}