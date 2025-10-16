package com.buccancs.testing

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Ensures background coroutines started by singletons are cancelled after each test.
 * Note: Currently a no-op as the application doesn't expose shutdown methods for testing.
 */
class AppShutdownRule : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    base.evaluate()
                } finally {
                    // No-op: Application shutdown not exposed for testing
                }
            }
        }
    }
}
