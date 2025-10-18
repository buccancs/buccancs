package com.topdon.tc001

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.topdon.lib.core.BaseApplication
import com.topdon.lib.core.common.SharedManager
import com.topdon.lib.core.config.RouterConfig
import com.topdon.lib.core.utils.CommUtils
import com.topdon.lms.sdk.utils.NetworkUtil
import com.topdon.tc001.app.App
import com.topdon.tc001.ui.theme.TopdonTheme
import com.topdon.tc001.utils.VersionUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@Route(
    path = RouterConfig.CLAUSE
)
class ClauseActivity :
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

        val appName =
            CommUtils.getAppName()
        val year =
            Calendar.getInstance()
                .get(
                    Calendar.YEAR
                )
        val version =
            VersionUtils.getCodeStr(
                this
            )
        val keyUseType =
            if (BaseApplication.instance.isDomestic()) 1 else 0

        setContent {
            TopdonTheme {
                ClauseScreen(
                    appName = appName,
                    yearRange = "2023-$year",
                    version = version,
                    onAgree = { confirmInitApp() },
                    onDisagree = { showDisagreeDialog() },
                    onNavigateToPolicy = { themeType ->
                        if (NetworkUtil.isConnected(
                                this
                            )
                        ) {
                            ARouter.getInstance()
                                .build(
                                    RouterConfig.POLICY
                                )
                                .withInt(
                                    PolicyActivity.KEY_THEME_TYPE,
                                    themeType
                                )
                                .withInt(
                                    PolicyActivity.KEY_USE_TYPE,
                                    keyUseType
                                )
                                .navigation(
                                    this
                                )
                        }
                    }
                )
            }
        }
    }

    private fun showDisagreeDialog() {
        androidx.appcompat.app.AlertDialog.Builder(
            this
        )
            .setMessage(
                getString(
                    R.string.privacy_tips
                )
            )
            .setPositiveButton(
                R.string.privacy_confirm
            ) { _, _ -> confirmInitApp() }
            .setNegativeButton(
                R.string.privacy_cancel
            ) { _, _ -> finish() }
            .setCancelable(
                true
            )
            .show()
    }

    private fun confirmInitApp() {
        lifecycleScope.launch {
            App.delayInit()
            async(
                Dispatchers.IO
            ) {
                delay(
                    1000
                )
            }.await()
            ARouter.getInstance()
                .build(
                    RouterConfig.MAIN
                )
                .navigation(
                    this@ClauseActivity
                )
            SharedManager.setHasShowClause(
                true
            )
            finish()
        }
    }
}

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
private fun ClauseScreen(
    appName: String,
    version: String,
    yearRange: String,
    onAgree: () -> Unit,
    onDisagree: () -> Unit,
    onNavigateToPolicy: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Terms & Conditions"
                    )
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
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(
                16.dp
            )
        ) {
            Text(
                text = "Welcome to $appName",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(
                        16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        8.dp
                    )
                ) {
                    Text(
                        text = "Before using this application, please read and agree to:",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    TextButton(
                        onClick = {
                            onNavigateToPolicy(
                                1
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "User Agreement"
                        )
                    }

                    TextButton(
                        onClick = {
                            onNavigateToPolicy(
                                2
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Privacy Policy"
                        )
                    }

                    TextButton(
                        onClick = {
                            onNavigateToPolicy(
                                3
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Terms of Service"
                        )
                    }
                }
            }

            Text(
                text = "By agreeing, you confirm that you have read and understood the above policies.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.weight(
                    1f
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    12.dp
                )
            ) {
                OutlinedButton(
                    onClick = onDisagree,
                    modifier = Modifier.weight(
                        1f
                    )
                ) {
                    Text(
                        "Disagree"
                    )
                }

                Button(
                    onClick = onAgree,
                    modifier = Modifier.weight(
                        1f
                    )
                ) {
                    Text(
                        "Agree & Continue"
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(
                    8.dp
                )
            )

            Text(
                text = "$yearRange\nVersion $version",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
}