package com.buccancs.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.buccancs.application.monitoring.AlertSeverity
import com.buccancs.application.monitoring.AlertType
import com.buccancs.ui.HealthAlertUi
import com.buccancs.ui.common.HorizontalDivider
import com.buccancs.ui.theme.Spacing
import java.util.Locale

@Composable
fun HealthAlertsCard(
    alerts: List<HealthAlertUi>,
    modifier: Modifier = Modifier
) {
    if (alerts.isEmpty()) return
    SectionCard(
        modifier = modifier,
        spacing = Spacing.Small
    ) {
        Text(
            text = "System Alerts",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        HorizontalDivider(
            modifier = Modifier.padding(
                vertical = Spacing.Small
            )
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(
                Spacing.Small
            )
        ) {
            alerts.forEach { alert ->
                HealthAlertRow(
                    alert
                )
            }
        }
    }
}

@Composable
private fun HealthAlertRow(
    alert: HealthAlertUi
) {
    val (containerColor, contentColor) = when (alert.severity) {
        AlertSeverity.Info -> MaterialTheme.colorScheme.surfaceVariant to MaterialTheme.colorScheme.onSurfaceVariant
        AlertSeverity.Warning -> MaterialTheme.colorScheme.tertiaryContainer to MaterialTheme.colorScheme.onTertiaryContainer
        AlertSeverity.Severe -> MaterialTheme.colorScheme.errorContainer to MaterialTheme.colorScheme.onErrorContainer
    }
    Surface(
        color = containerColor,
        contentColor = contentColor,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    Spacing.Medium
                ),
            verticalArrangement = Arrangement.spacedBy(
                Spacing.ExtraSmall
            )
        ) {
            androidx.compose.material3.Text(
                text = buildAlertTitle(
                    alert.type,
                    alert.severity
                ),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold
            )
            androidx.compose.material3.Text(
                text = alert.message,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun buildAlertTitle(
    type: AlertType,
    severity: AlertSeverity
): String {
    val typeLabel =
        formatAlertType(
            type
        )
    val severityLabel =
        formatSeverity(
            severity
        )
    return "$typeLabel • $severityLabel"
}

private fun formatAlertType(
    type: AlertType
): String =
    type.name
        .lowercase(
            Locale.US
        )
        .replace(
            '_',
            ' '
        )
        .replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.US
            ) else it.toString()
        }

private fun formatSeverity(
    severity: AlertSeverity
): String =
    when (severity) {
        AlertSeverity.Info -> "Info"
        AlertSeverity.Warning -> "Warning"
        AlertSeverity.Severe -> "Severe"
    }
