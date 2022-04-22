package com.cba.transactionaccount.util

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.interaction.BaristaScrollInteractions.safelyScrollTo
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedAnd

@Suppress("unused")
object TextAction {
    fun typeTo(@IdRes editTextId: Int, text: String) {
        safelyScrollTo(editTextId)
        onView(displayedAnd(withId(editTextId))).perform(typeText(text))
    }

    fun clearText(@IdRes editTextId: Int) {
        safelyScrollTo(editTextId)
        onView(displayedAnd(withId(editTextId))).perform(ViewActions.clearText())
    }
}