package com.cba.transactionaccount.util

import android.app.Activity
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import org.hamcrest.Matcher
import org.hamcrest.Matchers


object EspressoHelper {
    fun getCurrentActivity(): Activity? {
        var currentActivity: Activity? = null
        InstrumentationRegistry.getInstrumentation().runOnMainSync { run { currentActivity = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED).elementAtOrNull(0) } }
        return currentActivity
    }


    fun clickClickableSpan(textToClick: CharSequence): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return Matchers.instanceOf(TextView::class.java)
            }

            override fun getDescription(): String {
                return "clicking on a ClickableSpan"
            }

            override fun perform(uiController: UiController?, view: View) {
                val textView = view as TextView
                val spannableString = textView.text as SpannableString
                if (spannableString.isEmpty()) {
                    // TextView is empty, nothing to do
                    throw NoMatchingViewException.Builder()
                            .includeViewHierarchy(true)
                            .withRootView(textView)
                            .build()
                }

                // Get the links inside the TextView and check if we find textToClick
                val spans = spannableString.getSpans(0, spannableString.length, ClickableSpan::class.java)
                if (spans.isNotEmpty()) {
                    for (span in spans) {
                        val start = spannableString.getSpanStart(span)
                        val end = spannableString.getSpanEnd(span)
                        val sequence = spannableString.subSequence(start, end)
                        if (textToClick.toString() == sequence.toString()) {
                            span.onClick(textView)
                            return
                        }
                    }
                }
                throw NoMatchingViewException.Builder()
                        .includeViewHierarchy(true)
                        .withRootView(textView)
                        .build()
            }
        }
    }
}