package com.buccancs.desktop.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

/**
 * Tests for theme system
 */
class ThemeTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `BuccancsTheme applies MaterialTheme`() {
        var themeApplied = false
        
        composeRule.setContent {
            BuccancsTheme {
                themeApplied = true
                MaterialTheme.colorScheme.primary
            }
        }

        composeRule.waitForIdle()
        assertTrue(themeApplied, "BuccancsTheme should apply MaterialTheme")
    }

    @Test
    fun `BuccancsTheme supports dark theme`() {
        composeRule.setContent {
            BuccancsTheme(darkTheme = true) {
                MaterialTheme.colorScheme.primary
            }
        }

        composeRule.waitForIdle()
    }

    @Test
    fun `BuccancsTheme supports light theme`() {
        composeRule.setContent {
            BuccancsTheme(darkTheme = false) {
                MaterialTheme.colorScheme.primary
            }
        }

        composeRule.waitForIdle()
    }

    @Test
    fun `Spacing object has all expected values`() {
        assertEquals(4, Spacing.ExtraSmall.value.toInt())
        assertEquals(8, Spacing.Small.value.toInt())
        assertEquals(16, Spacing.Medium.value.toInt())
        assertEquals(24, Spacing.Large.value.toInt())
        assertEquals(32, Spacing.ExtraLarge.value.toInt())
    }

    @Test
    fun `CornerRadius has all expected values`() {
        assertEquals(4, CornerRadius.ExtraSmall.value.toInt())
        assertEquals(8, CornerRadius.Small.value.toInt())
        assertEquals(12, CornerRadius.Medium.value.toInt())
        assertEquals(16, CornerRadius.Large.value.toInt())
        assertEquals(28, CornerRadius.ExtraLarge.value.toInt())
    }

    @Test
    fun `semantic colors are accessible`() {
        composeRule.setContent {
            BuccancsTheme {
                val colors = BuccancsTheme.semanticColors
                assertNotNull(colors)
            }
        }

        composeRule.waitForIdle()
    }
}
