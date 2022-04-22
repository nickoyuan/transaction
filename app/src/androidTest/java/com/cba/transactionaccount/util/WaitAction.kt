@file:Suppress("unused")
package com.cba.transactionaccount.util


import android.os.SystemClock.sleep
import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import java.lang.System.currentTimeMillis

object WaitAction {

    private const val TIMEOUT_MILLIS_DEFAULT = 10000L
    private const val POLLING_MILLIS_DEFAULT = 50L

    fun sleepMS(timeMS: Long) = sleep(timeMS)

    fun waitUntilDisplayed(@IdRes viewId: Int) = waitUntilDisplayed(withId(viewId))

    fun waitUntilDisplayed(@IdRes viewId: Int, text: String) = waitUntilDisplayed(withId(viewId), withText(text))

    fun waitUntilDisplayed(text: String) = waitUntilDisplayed(withText(text))

    fun waitUntilEnabled(text: String) = doUntilCondition(onView(withText(text)), matches(isEnabled()))

    fun waitUntilDisabled(text: String) = doUntilCondition(onView(withText(text)), matches(not(isEnabled())))

    fun waitUntilHidden(@IdRes viewId: Int) = doUntilCondition(onView(withId(viewId)), matches(not(isDisplayed())))

    fun waitAndClickOn(text: String) {
        waitUntilDisplayed(text).perform(click())
    }

    fun waitAndClickOn(@IdRes viewId: Int) {
        waitUntilDisplayed(viewId).perform(click())
    }

    fun waitAndClickOn(@IdRes viewId: Int, text: String) {
        waitUntilDisplayed(viewId, text).perform(click())
    }

    fun waitUntilDisplayed(vararg matchers: Matcher<View>) = try {
        doUntilCondition(onView(allOf(matchers.toList())), matches(isCompletelyDisplayed()))
    } catch (_: AmbiguousViewMatcherException) {
        doUntilCondition(onView(allOf(matchers.toList() + isDisplayed())), matches(isDisplayed()))
    }

    fun waitUntilTrue(condition: () -> Boolean) {
        val startingMillis = currentTimeMillis()
        while (!condition()) {
            if (currentTimeMillis() - startingMillis > TIMEOUT_MILLIS_DEFAULT) {
                throw RuntimeException("waitUntilTrue timed out")
            }
            sleep(POLLING_MILLIS_DEFAULT)
        }
    }

    private fun doUntilCondition(viewInteraction: ViewInteraction, viewAssertion: ViewAssertion): ViewInteraction {
        val startingMillis = currentTimeMillis()
        while (true) {
            try {
                viewInteraction.check(viewAssertion)
                break
            } catch (e: Throwable) {
                if (currentTimeMillis() - startingMillis > TIMEOUT_MILLIS_DEFAULT) {
                    throw e
                }
                sleep(POLLING_MILLIS_DEFAULT)
            }

        }
        return viewInteraction
    }
}