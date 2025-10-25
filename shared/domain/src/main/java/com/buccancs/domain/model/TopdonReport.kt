package com.buccancs.domain.model

data class TopdonReportDraft(
    val material: String? = null,
    val notes: String? = null,
    val ambientTemperatureCelsius: Double = TopdonSettings.DEFAULT_AMBIENT_TEMPERATURE_C,
    val ambientHumidityPercent: Double = TopdonSettings.DEFAULT_AMBIENT_HUMIDITY_PERCENT
)

data class TopdonReportMetadata(
    val sessionId: String,
    val capturedAtMillis: Long,
    val temperatureUnit: TopdonTemperatureUnit,
    val ambientTemperatureCelsius: Double,
    val ambientHumidityPercent: Double,
    val material: String?,
    val notes: String?
)
