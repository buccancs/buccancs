package com.buccancs.desktop.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.buccancs.desktop.ui.components.BuccancsCard
import com.buccancs.desktop.ui.theme.BuccancsTheme
import com.buccancs.desktop.ui.theme.Spacing

/**
 * Video playback screen for reviewing recorded sessions
 */
@Composable
fun VideoPlaybackScreen() {
    var selectedSession by remember {
        mutableStateOf<String?>(
            null
        )
    }
    var playbackSpeed by remember {
        mutableStateOf(
            1.0f
        )
    }
    var isPlaying by remember {
        mutableStateOf(
            false
        )
    }
    var currentTime by remember {
        mutableStateOf(
            0f
        )
    }
    val duration =
        185f // 3:05 in seconds

    Row(modifier = Modifier.fillMaxSize()) {
        // Session list sidebar
        Surface(
            modifier = Modifier
                .width(
                    300.dp
                )
                .fillMaxHeight(),
            color = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 1.dp
        ) {
            Column(
                modifier = Modifier.padding(
                    Spacing.Medium
                )
            ) {
                Text(
                    "Recorded Sessions",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(
                        bottom = Spacing.Medium
                    )
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(
                        Spacing.Small
                    )
                ) {
                    items(
                        listOf(
                            SessionRecord(
                                "session-2025-10-16-001",
                                "16 Oct 2025 10:30",
                                "3:05",
                                "1.2 GB"
                            ),
                            SessionRecord(
                                "session-2025-10-16-002",
                                "16 Oct 2025 09:15",
                                "5:42",
                                "2.1 GB"
                            ),
                            SessionRecord(
                                "session-2025-10-15-003",
                                "15 Oct 2025 16:20",
                                "4:18",
                                "1.8 GB"
                            ),
                            SessionRecord(
                                "session-2025-10-15-002",
                                "15 Oct 2025 14:05",
                                "2:33",
                                "0.9 GB"
                            ),
                            SessionRecord(
                                "session-2025-10-15-001",
                                "15 Oct 2025 11:40",
                                "6:12",
                                "2.4 GB"
                            )
                        )
                    ) { session ->
                        SessionListItem(
                            session = session,
                            selected = selectedSession == session.id,
                            onClick = {
                                selectedSession =
                                    session.id
                            }
                        )
                    }
                }
            }
        }

        // Video player and controls
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    Spacing.Large
                ),
            verticalArrangement = Arrangement.spacedBy(
                Spacing.Medium
            )
        ) {
            Text(
                "Video Playback",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary
            )

            if (selectedSession != null) {
                // Video display area
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(
                            1f
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(
                                Spacing.Medium
                            )
                        ) {
                            Icon(
                                Icons.Default.PlayCircle,
                                contentDescription = null,
                                modifier = Modifier.size(
                                    120.dp
                                ),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                selectedSession
                                    ?: "",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White
                            )
                            Text(
                                "Multi-angle synchronized playback",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(
                                    alpha = 0.7f
                                )
                            )
                        }
                    }
                }

                // Playback controls
                BuccancsCard(
                    title = "Playback Controls"
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            Spacing.Medium
                        )
                    ) {
                        // Timeline
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    formatTime(
                                        currentTime
                                    ),
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Text(
                                    formatTime(
                                        duration
                                    ),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            Slider(
                                value = currentTime,
                                onValueChange = {
                                    currentTime =
                                        it
                                },
                                valueRange = 0f..duration,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        // Control buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                Spacing.Small
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    currentTime =
                                        0f
                                }) {
                                Icon(
                                    Icons.Default.SkipPrevious,
                                    "Skip to start"
                                )
                            }

                            FilledIconButton(
                                onClick = {
                                    isPlaying =
                                        !isPlaying
                                },
                                modifier = Modifier.size(
                                    56.dp
                                )
                            ) {
                                Icon(
                                    if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    if (isPlaying) "Pause" else "Play",
                                    modifier = Modifier.size(
                                        32.dp
                                    )
                                )
                            }

                            IconButton(
                                onClick = {
                                    currentTime =
                                        duration
                                }) {
                                Icon(
                                    Icons.Default.SkipNext,
                                    "Skip to end"
                                )
                            }

                            Spacer(
                                Modifier.width(
                                    Spacing.Medium
                                )
                            )

                            Text(
                                "Speed:",
                                style = MaterialTheme.typography.labelMedium
                            )

                            listOf(
                                0.5f,
                                1.0f,
                                1.5f,
                                2.0f
                            ).forEach { speed ->
                                FilterChip(
                                    selected = playbackSpeed == speed,
                                    onClick = {
                                        playbackSpeed =
                                            speed
                                    },
                                    label = {
                                        Text(
                                            "${speed}x"
                                        )
                                    }
                                )
                            }
                        }

                        // Stream selection
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                Spacing.Small
                            )
                        ) {
                            var showRgb by remember {
                                mutableStateOf(
                                    true
                                )
                            }
                            var showThermal by remember {
                                mutableStateOf(
                                    true
                                )
                            }
                            var showGsr by remember {
                                mutableStateOf(
                                    true
                                )
                            }

                            FilterChip(
                                selected = showRgb,
                                onClick = {
                                    showRgb =
                                        !showRgb
                                },
                                label = {
                                    Text(
                                        "RGB"
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Default.Videocam,
                                        null,
                                        Modifier.size(
                                            18.dp
                                        )
                                    )
                                }
                            )

                            FilterChip(
                                selected = showThermal,
                                onClick = {
                                    showThermal =
                                        !showThermal
                                },
                                label = {
                                    Text(
                                        "Thermal"
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Default.Thermostat,
                                        null,
                                        Modifier.size(
                                            18.dp
                                        )
                                    )
                                }
                            )

                            FilterChip(
                                selected = showGsr,
                                onClick = {
                                    showGsr =
                                        !showGsr
                                },
                                label = {
                                    Text(
                                        "GSR"
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Default.ShowChart,
                                        null,
                                        Modifier.size(
                                            18.dp
                                        )
                                    )
                                }
                            )
                        }
                    }
                }

                // Session metadata
                BuccancsCard(
                    title = "Session Metadata"
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            Spacing.Small
                        )
                    ) {
                        MetadataRow(
                            "Session ID",
                            selectedSession
                                ?: ""
                        )
                        MetadataRow(
                            "Recorded",
                            "16 Oct 2025 10:30:15"
                        )
                        MetadataRow(
                            "Duration",
                            "3:05"
                        )
                        MetadataRow(
                            "Devices",
                            "2 (Device-001, Device-002)"
                        )
                        MetadataRow(
                            "Subjects",
                            "Subject-A, Subject-B"
                        )
                        MetadataRow(
                            "Operator",
                            "Operator-001"
                        )
                        MetadataRow(
                            "Total Size",
                            "1.2 GB"
                        )
                    }
                }
            } else {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(
                            1f
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(
                                Spacing.Medium
                            )
                        ) {
                            Icon(
                                Icons.Default.VideoLibrary,
                                contentDescription = null,
                                modifier = Modifier.size(
                                    80.dp
                                ),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                "Select a session to play",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SessionListItem(
    session: SessionRecord,
    selected: Boolean,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(
                Spacing.Small
            )
        ) {
            Text(
                session.id,
                style = MaterialTheme.typography.labelMedium,
                color = if (selected)
                    MaterialTheme.colorScheme.onPrimaryContainer
                else
                    MaterialTheme.colorScheme.onSurface
            )
            Text(
                session.timestamp,
                style = MaterialTheme.typography.bodySmall,
                color = if (selected)
                    MaterialTheme.colorScheme.onPrimaryContainer.copy(
                        alpha = 0.7f
                    )
                else
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Small
                )
            ) {
                Text(
                    session.duration,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (selected)
                        MaterialTheme.colorScheme.onPrimaryContainer.copy(
                            alpha = 0.7f
                        )
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "•"
                )
                Text(
                    session.size,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (selected)
                        MaterialTheme.colorScheme.onPrimaryContainer.copy(
                            alpha = 0.7f
                        )
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun MetadataRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

private fun formatTime(
    seconds: Float
): String {
    val mins =
        (seconds / 60).toInt()
    val secs =
        (seconds % 60).toInt()
    return "%d:%02d".format(
        mins,
        secs
    )
}

private data class SessionRecord(
    val id: String,
    val timestamp: String,
    val duration: String,
    val size: String
)


@Preview
@Composable
private fun VideoPlaybackScreenPreview() {
    BuccancsTheme {
        Surface {
            VideoPlaybackScreen()
        }
    }
}
