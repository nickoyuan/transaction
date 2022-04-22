package com.cba.transactionaccount.util

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasFocus
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher

fun closeKeyboardOnFocused(matcher: Matcher<View>) {
    onView(matcher).check(matches(hasFocus())).perform(closeSoftKeyboard())
}

fun writeToAndCloseKeyboard(@IdRes viewId: Int, text: String) {
    onView(withId(viewId)).perform(typeText(text), closeSoftKeyboard())
}