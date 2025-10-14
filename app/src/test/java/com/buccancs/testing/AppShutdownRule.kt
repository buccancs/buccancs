package com.buccancs.testing

import androidx.test.core.app.ApplicationProvider
import com.buccancs.BuccancsApplication
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Ensures background coroutines started by singletons are cancelled after each test.
 */
class AppShutdownRule : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    base.evaluate()
                } finally {
                    ApplicationProvider.getApplicationContext<BuccancsApplication>().shutdownForTests()
                }
            }
        }
    }
}
