package com.buccancs.di

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.CoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.test.assertNotNull

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class CoroutineModuleTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var dispatchers: CoroutineDispatchers

    @Inject
    @ApplicationScope
    lateinit var applicationScope: CoroutineScope

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `coroutine dispatchers are injectable`() {
        assertNotNull(dispatchers)
        assertNotNull(dispatchers.main)
        assertNotNull(dispatchers.io)
        assertNotNull(dispatchers.default)
    }

    @Test
    fun `application scope is injectable`() {
        assertNotNull(applicationScope)
    }
}
