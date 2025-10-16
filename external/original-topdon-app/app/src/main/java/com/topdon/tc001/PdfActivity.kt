package com.topdon.tc001

import android.os.Bundle
import android.view.WindowManager
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
import com.github.barteksc.pdfviewer.PDFView
import com.topdon.lib.core.config.RouterConfig
import com.topdon.tc001.ui.theme.TopdonTheme
import java.io.File
import java.io.FileOutputStream

@Route(path = RouterConfig.PDF)
class PdfActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val isTS001 = intent.getBooleanExtra("isTS001", false)
        val pdfFileName = if (isTS001) "TC001.pdf" else "TS004.pdf"

        // Copy PDF files to external storage if not exists
        val pdfFile = File(getExternalFilesDir("pdf")!!, pdfFileName)
        if (!pdfFile.exists()) {
            copyAssetToFile(pdfFileName, pdfFile)
        }

        setContent {
            TopdonTheme {
                PdfScreen(
                    pdfFile = pdfFile,
                    title = if (isTS001) "TC001 Manual" else "TS004 Manual",
                    onNavigateUp = { finish() }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onPause() {
        super.onPause()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun copyAssetToFile(assetName: String, targetFile: File) {
        try {
            assets.open(assetName).use { input ->
                FileOutputStream(targetFile).use { output ->
                    val buffer = ByteArray(1024)
                    var length: Int
                    while (input.read(buffer).also { length = it } > 0) {
                        output.write(buffer, 0, length)
                    }
                    output.flush()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PdfScreen(
    pdfFile: File,
    title: String,
    onNavigateUp: () -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }
    var pageCount by remember { mutableStateOf(0) }
    var currentPage by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(title)
                        if (pageCount > 0) {
                            Text(
                                text = "Page ${currentPage + 1} of $pageCount",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
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
                .padding(padding)
        ) {
            AndroidView(
                factory = { context ->
                    PDFView(context, null).apply {
                        fromFile(pdfFile)
                            .enableSwipe(true)
                            .swipeHorizontal(false)
                            .enableDoubletap(true)
                            .defaultPage(0)
                            .enableAnnotationRendering(false)
                            .enableAntialiasing(true)
                            .spacing(0)
                            .onLoad { pages ->
                                isLoading = false
                                pageCount = pages
                            }
                            .onPageChange { page, _ ->
                                currentPage = page
                            }
                            .load()
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}