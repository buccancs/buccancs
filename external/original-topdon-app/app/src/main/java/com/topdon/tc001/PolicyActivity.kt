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
import com.topdon.lib.core.BaseApplication
import com.topdon.lib.core.config.RouterConfig
import com.topdon.tc001.ui.theme.TopdonTheme

@Route(
    path = RouterConfig.POLICY
)
class PolicyActivity :
    ComponentActivity() {
    companion object {
        const val KEY_THEME_TYPE =
            "key_theme_type"
        const val KEY_USE_TYPE =
            "key_use_type"
    }

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

        val themeType =
            intent.getIntExtra(
                KEY_THEME_TYPE,
                1
            )
        val keyUseType =
            intent.getIntExtra(
                KEY_USE_TYPE,
                0
            )

        val title =
            when (themeType) {
                1 -> getString(
                    R.string.user_services_agreement
                )

                2 -> getString(
                    R.string.privacy_policy
                )

                3 -> getString(
                    R.string.third_party_components
                )

                else -> getString(
                    R.string.user_services_agreement
                )
            }

        val url =
            getAssetUrl(
                themeType,
                keyUseType
            )

        setContent {
            TopdonTheme {
                PolicyScreen(
                    title = title,
                    url = url,
                    onNavigateUp = { finish() }
                )
            }
        }
    }

    private fun getAssetUrl(
        themeType: Int,
        keyUseType: Int
    ): String {
        if (keyUseType != 0) {
            return when (themeType) {
                1 -> "https://plat.topdon.com/topdon-plat/out-user/baseinfo/template/getHtmlContentById?softCode=${BaseApplication.instance.getSoftWareCode()}&language=1&type=21"
                2 -> "https://plat.topdon.com/topdon-plat/out-user/baseinfo/template/getHtmlContentById?softCode=${BaseApplication.instance.getSoftWareCode()}&language=1&type=22"
                3 -> "file:///android_asset/web/third_statement.html"
                else -> "file:///android_asset/web/services_agreement_default.html"
            }
        }

        return when (themeType) {
            1 -> {
                if (BaseApplication.instance.isDomestic()) {
                    "file:///android_asset/web/services_agreement_default_inside_china.html"
                } else {
                    "file:///android_asset/web/services_agreement_default.html"
                }
            }

            2 -> {
                if (BaseApplication.instance.isDomestic()) {
                    "file:///android_asset/web/privacy_default_inside_china.html"
                } else {
                    "file:///android_asset/web/privacy_default.html"
                }
            }

            3 -> "file:///android_asset/web/third_statement.html"
            else -> "file:///android_asset/web/services_agreement_default.html"
        }
    }
}

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
private fun PolicyScreen(
    title: String,
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    padding
                )
        ) {
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
                            defaultTextEncodingName =
                                "utf-8"
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
                        text = "Failed to load policy",
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
}