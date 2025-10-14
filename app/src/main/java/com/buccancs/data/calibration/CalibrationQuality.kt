package com.buccancs.data.calibration

import com.buccancs.domain.model.CalibrationMetrics
import com.buccancs.domain.model.CalibrationResult

/**
 * Quality check result for stereo calibration.
 */
data class CalibrationQualityCheck(
    val passed: Boolean,
    val issues: List<QualityIssue>,
    val warnings: List<String>,
    val metrics: CalibrationMetrics,
    val recommendation: String?
) {
    fun hasErrors(): Boolean = issues.any { 
        it.severity == QualityIssue.Severity.CRITICAL || 
        it.severity == QualityIssue.Severity.ERROR 
    }
    
    fun hasCritical(): Boolean = issues.any { it.severity == QualityIssue.Severity.CRITICAL }
}

/**
 * Quality issue with severity and context.
 */
data class QualityIssue(
    val severity: Severity,
    val message: String,
    val metric: String,
    val actualValue: Double,
    val threshold: Double
) {
    enum class Severity {
        CRITICAL,  // Reject calibration - unusable
        ERROR,     // Reject calibration - poor quality
        WARNING    // Accept but warn - suboptimal
    }
    
    override fun toString(): String {
        return "[$severity] $message (actual: ${"%.3f".format(actualValue)}, threshold: ${"%.3f".format(threshold)})"
    }
}

/**
 * Quality thresholds for calibration validation.
 */
object CalibrationQualityThresholds {
    // Hard thresholds - calibration rejected if exceeded
    const val MAX_MEAN_REPROJECTION_ERROR = 2.0  // pixels
    const val MAX_MAX_REPROJECTION_ERROR = 5.0   // pixels
    const val MIN_IMAGE_COUNT = 5
    
    // Soft thresholds - warnings shown but calibration accepted
    const val TARGET_MEAN_REPROJECTION_ERROR = 1.0  // pixels
    const val TARGET_MAX_REPROJECTION_ERROR = 3.0   // pixels
    const val RECOMMENDED_IMAGE_COUNT = 10
    
    // Additional quality checks
    const val MAX_STD_DEV_REPROJECTION_ERROR = 1.5  // pixels
}

/**
 * Validate calibration quality against thresholds.
 */
fun validateCalibrationQuality(
    result: CalibrationResult
): CalibrationQualityCheck {
    val issues = mutableListOf<QualityIssue>()
    val warnings = mutableListOf<String>()
    
    val maxError = result.perViewErrors.maxOrNull() ?: result.meanReprojectionError
    val stdDev = calculateStdDev(result.perViewErrors, result.meanReprojectionError)
    
    // Check mean reprojection error (CRITICAL)
    if (result.meanReprojectionError > CalibrationQualityThresholds.MAX_MEAN_REPROJECTION_ERROR) {
        issues.add(QualityIssue(
            severity = QualityIssue.Severity.CRITICAL,
            message = "Mean reprojection error exceeds maximum threshold",
            metric = "meanReprojectionError",
            actualValue = result.meanReprojectionError,
            threshold = CalibrationQualityThresholds.MAX_MEAN_REPROJECTION_ERROR
        ))
    } else if (result.meanReprojectionError > CalibrationQualityThresholds.TARGET_MEAN_REPROJECTION_ERROR) {
        warnings.add(
            "Mean reprojection error (${"%.3f".format(result.meanReprojectionError)} px) exceeds target " +
            "(${"%.1f".format(CalibrationQualityThresholds.TARGET_MEAN_REPROJECTION_ERROR)} px). " +
            "Consider recapturing with better lighting and focus."
        )
    }
    
    // Check max reprojection error (ERROR)
    if (maxError > CalibrationQualityThresholds.MAX_MAX_REPROJECTION_ERROR) {
        issues.add(QualityIssue(
            severity = QualityIssue.Severity.ERROR,
            message = "Maximum reprojection error exceeds threshold",
            metric = "maxReprojectionError",
            actualValue = maxError,
            threshold = CalibrationQualityThresholds.MAX_MAX_REPROJECTION_ERROR
        ))
    } else if (maxError > CalibrationQualityThresholds.TARGET_MAX_REPROJECTION_ERROR) {
        warnings.add(
            "Maximum reprojection error (${"%.3f".format(maxError)} px) is elevated. " +
            "Some captures may have poor corner detection."
        )
    }
    
    // Check image count (ERROR)
    if (result.usedPairs < CalibrationQualityThresholds.MIN_IMAGE_COUNT) {
        issues.add(QualityIssue(
            severity = QualityIssue.Severity.ERROR,
            message = "Insufficient calibration images",
            metric = "imageCount",
            actualValue = result.usedPairs.toDouble(),
            threshold = CalibrationQualityThresholds.MIN_IMAGE_COUNT.toDouble()
        ))
    } else if (result.usedPairs < CalibrationQualityThresholds.RECOMMENDED_IMAGE_COUNT) {
        warnings.add(
            "Image count (${result.usedPairs}) is below recommended " +
            "(${CalibrationQualityThresholds.RECOMMENDED_IMAGE_COUNT}). " +
            "More images improve calibration accuracy."
        )
    }
    
    // Check standard deviation (WARNING)
    if (stdDev > CalibrationQualityThresholds.MAX_STD_DEV_REPROJECTION_ERROR) {
        warnings.add(
            "Reprojection error standard deviation (${"%.3f".format(stdDev)} px) is high. " +
            "This indicates inconsistent calibration quality across images."
        )
    }
    
    // Generate recommendation
    val recommendation = when {
        issues.isNotEmpty() -> generateRecommendation(issues, result)
        warnings.isNotEmpty() -> "Calibration acceptable but could be improved. See warnings for details."
        else -> "Excellent calibration quality! Mean error: ${"%.3f".format(result.meanReprojectionError)} px"
    }
    
    val metrics = CalibrationMetrics(
        generatedAt = result.generatedAt,
        meanReprojectionError = result.meanReprojectionError,
        maxReprojectionError = maxError,
        usedPairs = result.usedPairs,
        requiredPairs = result.requiredPairs
    )
    
    return CalibrationQualityCheck(
        passed = !issues.any { 
            it.severity == QualityIssue.Severity.CRITICAL || 
            it.severity == QualityIssue.Severity.ERROR 
        },
        issues = issues,
        warnings = warnings,
        metrics = metrics,
        recommendation = recommendation
    )
}

private fun calculateStdDev(values: List<Double>, mean: Double): Double {
    if (values.isEmpty()) return 0.0
    val variance = values.map { (it - mean) * (it - mean) }.average()
    return kotlin.math.sqrt(variance)
}

private fun generateRecommendation(
    issues: List<QualityIssue>,
    result: CalibrationResult
): String {
    val recommendations = mutableListOf<String>()
    val maxError = result.perViewErrors.maxOrNull() ?: result.meanReprojectionError
    
    recommendations.add("Calibration quality insufficient. Please address the following:")
    recommendations.add("")
    
    if (issues.any { it.metric == "meanReprojectionError" || it.metric == "maxReprojectionError" }) {
        recommendations.add("To improve corner detection accuracy:")
        recommendations.add("  • Ensure bright, even lighting")
        recommendations.add("  • Keep chessboard in sharp focus")
        recommendations.add("  • Avoid motion blur (hold steady)")
        recommendations.add("  • Ensure entire chessboard pattern is visible")
        recommendations.add("  • Clean camera lenses")
        recommendations.add("")
    }
    
    if (issues.any { it.metric == "imageCount" }) {
        recommendations.add("To improve calibration robustness:")
        recommendations.add("  • Capture at least ${CalibrationQualityThresholds.MIN_IMAGE_COUNT} image pairs")
        recommendations.add("  • Recommended: ${CalibrationQualityThresholds.RECOMMENDED_IMAGE_COUNT}+ pairs")
        recommendations.add("  • Vary chessboard angles and distances")
        recommendations.add("  • Include images from all areas of camera view")
        recommendations.add("")
    }
    
    recommendations.add("Current metrics:")
    recommendations.add("  • Mean error: ${"%.3f".format(result.meanReprojectionError)} px (max: ${CalibrationQualityThresholds.MAX_MEAN_REPROJECTION_ERROR})")
    recommendations.add("  • Max error: ${"%.3f".format(maxError)} px (max: ${CalibrationQualityThresholds.MAX_MAX_REPROJECTION_ERROR})")
    recommendations.add("  • Images: ${result.usedPairs} (min: ${CalibrationQualityThresholds.MIN_IMAGE_COUNT})")
    
    return recommendations.joinToString("\n")
}

/**
 * Exception thrown when calibration quality is insufficient.
 */
class CalibrationQualityException(
    message: String,
    val issues: List<QualityIssue>,
    val recommendation: String?
) : Exception(message) {
    override val message: String
        get() = buildString {
            append(super.message)
            if (issues.isNotEmpty()) {
                append("\n\nIssues found:")
                issues.forEach { issue ->
                    append("\n  • $issue")
                }
            }
            if (recommendation != null) {
                append("\n\n$recommendation")
            }
        }
}
