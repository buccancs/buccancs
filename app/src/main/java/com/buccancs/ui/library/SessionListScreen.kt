package com.buccancs.ui.library

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.ui.components.EmptyState
import com.buccancs.ui.components.ErrorState
import com.buccancs.ui.components.LoadingState
import com.buccancs.ui.components.SearchBar
import com.buccancs.ui.theme.Spacing
import com.buccancs.ui.theme.MotionTransitions
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
    var searchQuery by remember { mutableStateOf("") }
    val filteredSessions = remember(state.sessions, searchQuery) {
        if (searchQuery.isBlank()) {
            state.sessions
        } else {
            state.sessions.filter { session ->
                session.sessionId.contains(searchQuery, ignoreCase = true)
            }
        }
    }
    
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
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { padding ->
        when {
            state.isLoading -> {
                LoadingState(message = "Loading sessions")
            }

            state.errorMessage != null -> {
                ErrorState(
                    title = "Unable to load sessions",
                    message = state.errorMessage,
                    onRetry = onRefresh
                )
            }

            state.sessions.isEmpty() -> {
                EmptyState(
                    icon = Icons.Default.Storage,
                    title = "No sessions found",
                    description = "Start a recording to create your first session"
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
                ) {
                    item {
                        SearchBar(
                            query = searchQuery,
                            onQueryChange = { searchQuery = it },
                            modifier = Modifier.padding(horizontal = Spacing.Medium, vertical = Spacing.Small)
                        )
                    }
                    
                    if (filteredSessions.isEmpty()) {
                        item {
                            NoResultsState(query = searchQuery)
                        }
                    } else {
                        items(filteredSessions, key = { it.sessionId }) { summary ->
                            AnimatedVisibility(
                                visible = true,
                                enter = MotionTransitions.fadeEnter(),
                                exit = MotionTransitions.fadeExit()
                            ) {
                                SessionCard(
                                    summary = summary,
                                    onClick = { onSessionSelected(summary.sessionId) },
                                    modifier = Modifier.padding(horizontal = Spacing.Medium)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        modifier = modifier,
        placeholder = "Search sessions..."
    )
}

@Composable
private fun EmptyState() {
    EmptyState(
        icon = Icons.Default.Storage,
        title = "No sessions found",
        description = "Start a recording to create your first session"
    )
}

@Composable
private fun NoResultsState(query: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.Large),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.padding(bottom = Spacing.Medium),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "No results for \"$query\"",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun SessionCard(
    summary: SessionSummary,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("session-row-${summary.sessionId}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = Spacing.ExtraSmall)
    ) {
        Column(modifier = Modifier.padding(Spacing.Medium)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = summary.sessionId,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                if (summary.simulation) {
                    Icon(
                        imageVector = Icons.Default.Science,
                        contentDescription = "Simulation",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing.Medium),
                horizontalArrangement = Arrangement.spacedBy(Spacing.Medium)
            ) {
                SessionInfoChip(
                    icon = Icons.Default.CalendarToday,
                    label = "Duration",
                    value = summary.durationFormatted
                )
                SessionInfoChip(
                    icon = Icons.Default.Storage,
                    label = "Size",
                    value = formatBytes(summary.totalBytes)
                )
            }
            
            Column(
                modifier = Modifier.padding(top = Spacing.Small),
                verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)
            ) {
                Text(
                    text = "Started: ${summary.startedAt}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                summary.endedAt?.let {
                    Text(
                        text = "Ended: $it",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = "${summary.artifactCount} artifacts",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun SessionInfoChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.padding(end = Spacing.ExtraSmall),
            tint = MaterialTheme.colorScheme.primary
        )
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
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
