package com.topdon.tc001

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.topdon.lib.core.config.ExtraKeyConfig
import com.topdon.lib.core.config.RouterConfig
import com.topdon.tc001.ui.theme.TopdonTheme

@Route(
    path = RouterConfig.WEB_VIEW
)
class WebViewActivity :
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

        val url =
            intent.extras?.getString(
                ExtraKeyConfig.URL
            )
                ?: ""

        setContent {
            TopdonTheme {
                WebViewScreen(
                    url = url,
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
private fun WebViewScreen(
    url: String,
    onNavigateUp: () -> Unit
) {
    var isLoading by remember {
        mutableStateOf(
            true
        )
    }
    var hasError by remember {
        mutableStateOf(
            false
        )
    }
    var webView by remember {
        mutableStateOf<WebView?>(
            null
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Web View"
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    padding
                )
        ) {
            if (url.isNotEmpty()) {
                AndroidView(
                    factory = { context ->
                        WebView(
                            context
                        ).apply {
                            @SuppressLint(
                                "SetJavaScriptEnabled"
                            )
                            settings.apply {
                                javaScriptEnabled =
                                    true
                                domStorageEnabled =
                                    true
                                setSupportZoom(
                                    false
                                )
                                useWideViewPort =
                                    true
                                javaScriptCanOpenWindowsAutomatically =
                                    true
                                allowFileAccess =
                                    true
                            }
                            webViewClient =
                                object :
                                    WebViewClient() {
                                    override fun onPageFinished(
                                        view: WebView?,
                                        url: String?
                                    ) {
                                        super.onPageFinished(
                                            view,
                                            url
                                        )
                                        isLoading =
                                            false
                                    }

                                    override fun onReceivedError(
                                        view: WebView?,
                                        errorCode: Int,
                                        description: String?,
                                        failingUrl: String?
                                    ) {
                                        super.onReceivedError(
                                            view,
                                            errorCode,
                                            description,
                                            failingUrl
                                        )
                                        isLoading =
                                            false
                                        hasError =
                                            true
                                    }
                                }
                            loadUrl(
                                url
                            )
                            webView =
                                this
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }

            if (hasError) {
                Column(
                    modifier = Modifier
                        .align(
                            Alignment.Center
                        )
                        .padding(
                            16.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        16.dp
                    )
                ) {
                    Text(
                        text = "Failed to load page",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Button(
                        onClick = {
                            hasError =
                                false
                            isLoading =
                                true
                            webView?.reload()
                        }
                    ) {
                        Text(
                            "Retry"
                        )
                    }
                }
            }
        }
    }
}