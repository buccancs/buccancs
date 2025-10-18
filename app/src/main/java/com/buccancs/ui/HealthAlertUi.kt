package com.buccancs.ui

import com.buccancs.application.monitoring.AlertSeverity
import com.buccancs.application.monitoring.AlertType
import com.buccancs.application.monitoring.HealthAlert

data class HealthAlertUi(
    val type: AlertType,
    val severity: AlertSeverity,
    val message: String
)

fun HealthAlert.toUiModel(): HealthAlertUi = HealthAlertUi(
    type = type,
    severity = severity,
    message = message
)

