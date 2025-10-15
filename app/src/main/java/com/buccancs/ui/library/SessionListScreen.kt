package com.buccancs.ui.library

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.util.Locale

@Composable
fun SessionLibraryRoute(
    onNavigateUp: () -> Unit,
    onSessionSelected: (String) -> Unit,
    viewModel: SessionLibraryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SessionLibraryScreen(
        state = state,
        onRefresh = viewModel::refresh,
        onSessionSelected = onSessionSelected,
        onNavigateUp = onNavigateUp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionLibraryScreen(
    state: SessionLibraryUiState,
    onRefresh: () -> Unit,
    onSessionSelected: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Session Library") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        when {
            state.isLoading -> {
                Column(
                    modifier = Modifier
	                    .fillMaxSize()
	                    .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Loading sessions…")
                }
            }

            state.errorMessage != null -> {
                Column(
                    modifier = Modifier
	                    .fillMaxSize()
	                    .padding(padding)
	                    .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Unable to load sessions",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = state.errorMessage,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Button(onClick = onRefresh) {
                        Text(text = "Retry")
                    }
                }
            }

            state.sessions.isEmpty() -> {
                Column(
                    modifier = Modifier
	                    .fillMaxSize()
	                    .padding(padding)
	                    .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "No sessions found.")
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
	                    .fillMaxSize()
	                    .padding(padding)
	                    .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.sessions) { summary ->
                        SessionRow(summary = summary, onClick = { onSessionSelected(summary.sessionId) })
                    }
                }
            }
        }
    }
}

@Composable
private fun SessionRow(summary: SessionSummary, onClick: () -> Unit) {
    Card(
        modifier = Modifier
	        .fillMaxWidth()
	        .clickable(onClick = onClick)
	        .testTag("session-row-${summary.sessionId}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = summary.sessionId, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Started: ${summary.startedAt}")
            summary.endedAt?.let { Text(text = "Ended: $it") }
            Text(text = "Duration: ${summary.durationFormatted}")
            Text(text = "Artifacts: ${summary.artifactCount}")
            Text(text = "Size: ${formatBytes(summary.totalBytes)}")
            Text(text = "Simulation: ${summary.simulation}")
        }
    }
}

private fun formatBytes(bytes: Long): String {
    if (bytes <= 0) return "0 B"
    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    val digitGroups = (Math.log10(bytes.toDouble()) / Math.log10(1024.0)).toInt()
    val value = bytes / Math.pow(1024.0, digitGroups.toDouble())
    return String.format(Locale.US, "%.1f %s", value, units[digitGroups])
}
