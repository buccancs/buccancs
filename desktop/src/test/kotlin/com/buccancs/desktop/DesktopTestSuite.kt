package com.buccancs.desktop

import com.buccancs.desktop.data.discovery.MdnsServiceBrowserTest
import com.buccancs.desktop.ui.ControlPanelTest
import com.buccancs.desktop.ui.OrchestratorAppTest
import com.buccancs.desktop.ui.RetentionAndTransferTest
import com.buccancs.desktop.ui.components.*
import com.buccancs.desktop.ui.navigation.NavigationTest
import com.buccancs.desktop.ui.screens.ScreensRenderTest
import com.buccancs.desktop.ui.theme.ThemeTest
import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite
import org.junit.platform.suite.api.SuiteDisplayName

/**
 * Comprehensive test suite for desktop orchestrator
 * Runs all unit and integration tests
 */
@Suite
@SuiteDisplayName("Desktop Orchestrator Test Suite")
@SelectClasses(
    // Navigation Tests
    NavigationTest::class,
    
    // Component Tests
    CardsTest::class,
    BadgesTest::class,
    ButtonsTest::class,
    ScreenHeaderTest::class,
    
    // Theme Tests
    ThemeTest::class,
    
    // UI Tests
    ControlPanelTest::class,
    RetentionAndTransferTest::class,
    ScreensRenderTest::class,
    OrchestratorAppTest::class,
    
    // Data Layer Tests
    MdnsServiceBrowserTest::class
)
class DesktopTestSuite
