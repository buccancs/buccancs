package com.infisense.iruvc.bridge

import com.infisense.iruvc.utils.CommonParams

/**
 * Simple reference to vendor symbols so the Gradle module fails fast if the SDK JAR
 * is missing from the classpath.
 */
internal fun verifyTopdonRuntimeLoaded(): CommonParams.PseudoColorType =
    CommonParams.PseudoColorType.PSEUDO_1
