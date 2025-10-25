package com.buccancs.desktop.domain.model

/**
 * Represents the modality of a preview stream delivered to the desktop orchestrator.
 * Used to cluster unified capture controls and surface per-modality diagnostics.
 */
enum class PreviewModality {
    RGB,
    THERMAL
}
