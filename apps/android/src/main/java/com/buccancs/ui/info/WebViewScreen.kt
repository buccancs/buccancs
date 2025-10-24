package com.buccancs.ui.info

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun WebViewRoute(
    url: String,
    title: String,
    onNavigateUp: () -> Unit,
    viewModel: WebViewViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(
        url
    ) {
        viewModel.loadUrl(
            url
        )
    }

    WebViewScreen(
        title = title.ifEmpty { state.title },
        url = state.url,
        isLoading = state.isLoading,
        onNavigateUp = onNavigateUp
    )
}

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
private fun WebViewScreen(
    title: String,
    url: String,
    isLoading: Boolean,
    onNavigateUp: () -> Unit
) {
    var webViewLoading by remember {
        mutableStateOf(
            true
        )
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
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
        },
        contentWindowInsets = WindowInsets(
            0,
            0,
            0,
            0
        )
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
                                        webViewLoading =
                                            false
                                    }
                                }
                            settings.apply {
                                javaScriptEnabled =
                                    true
                                domStorageEnabled =
                                    true
                            }
                            loadUrl(
                                url
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            if (isLoading || webViewLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
    }
}
