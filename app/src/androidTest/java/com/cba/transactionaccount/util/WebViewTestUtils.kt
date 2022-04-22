package com.cba.transactionaccount.util

import android.os.SystemClock
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.web.assertion.WebViewAssertions
import androidx.test.espresso.web.sugar.Web
import androidx.test.espresso.web.webdriver.DriverAtoms
import androidx.test.espresso.web.webdriver.Locator
import org.hamcrest.CoreMatchers

fun assertWebviewText(id: Int, label: String, text: String) {
    val startingMillis = System.currentTimeMillis()
    while (true) {
        try {
            Web.onWebView(ViewMatchers.withId(id))
                    .forceJavascriptEnabled()
                    .withElement(DriverAtoms.findElement(Locator.ID, label))
                    .check(WebViewAssertions.webMatches(DriverAtoms.getText(), CoreMatchers.equalTo(text)))
            break
        } catch (e: Throwable) {
            if (System.currentTimeMillis() - startingMillis > 10_000) {
                throw e
            }
            SystemClock.sleep(50)
        }
    }
}