package com.shekh.test.telstra

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.shekh.test.telstra.ui.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AppUiTest {

    /**
     * Use [ActivityScenario] to create and launch the activity under test. This is a
     * replacement for [androidx.test.rule.ActivityTestRule].
     */
    @Before
    fun launchActivity() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    /**
     * Check if recyclerView is visible. this will be example of successful UI test
     */
    @Test
    fun checkRecyclerViewSuccess() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    /**
     * Check if loadingTextView matches the text. this will be example of unsuccessful UI test
     */
    @Test
    fun checkTextFailed() {
        onView(withId(R.id.loadingTextView)).check(matches(withText("Loading")))
    }
}