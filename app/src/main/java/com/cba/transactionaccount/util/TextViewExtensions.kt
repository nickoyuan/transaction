package com.cba.transactionaccount.util

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

private val hyperlinkBlue = Color.argb(255, 28, 105, 232)

fun TextView.setColorCompat(@ColorRes color: Int) = this.setTextColor(ContextCompat.getColor(this.context, color))

fun TextView.setColorOnText(wholeText: String, vararg textColorPairs: Pair<String, Int>) {
    val wholeTextSpannable = SpannableString(wholeText)
    textColorPairs.forEach { textColorPair ->
        val (textToColor, color) = textColorPair
        val startIndex = wholeTextSpannable.indexOf(textToColor)
        if (startIndex == -1) {
            return
        }
        val textColor = ForegroundColorSpan(color)
        wholeTextSpannable.setSpan(textColor, startIndex, startIndex + textToColor.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }

    text = wholeTextSpannable
}

fun TextView.setBoldOnText(wholeText: String, vararg textsToBold: String) {
    val wholeTextSpannable = SpannableString(wholeText)
    textsToBold.forEach { textToBold ->

        val startIndex = wholeTextSpannable.indexOf(textToBold)
        if (startIndex == -1) {
            return
        }
        wholeTextSpannable.setSpan(StyleSpan(Typeface.BOLD), startIndex, startIndex + textToBold.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }
    text = wholeTextSpannable
}

fun TextView.setImeActionListener(vararg actionIds: Int, function: () -> Unit) = setOnEditorActionListener { _, actionId, _ ->
    if (actionIds.contains(actionId)) {
        function()
        return@setOnEditorActionListener true
    }

    return@setOnEditorActionListener false
}

fun TextView.setTextWithHyperlinkOnStrings(wholeText: String, vararg textListenerPairs: Pair<String, (() -> Any)?>) {
    movementMethod = LinkMovementMethod.getInstance()
    val wholeTextSpannable = SpannableString(wholeText)

    textListenerPairs.forEach { pair ->
        val (textToSpan, onClickAction) = pair
        wholeTextSpannable.setHyperlinkSpannable(textToSpan, onClickAction)
    }
    text = wholeTextSpannable
}

fun TextView.setTextWithHyperlinkOnStringsNoUnderline(wholeText: String, vararg textListenerPairs: Pair<String, (() -> Any)?>) {
    movementMethod = LinkMovementMethod.getInstance()
    val wholeTextSpannable = SpannableString(wholeText)

    textListenerPairs.forEach { pair ->
        val (textToSpan, onClickAction) = pair
        wholeTextSpannable.setHyperlinkSpannable(textToSpan, onClickAction, false)
    }
    text = wholeTextSpannable
}

private fun SpannableString.setHyperlinkSpannable(textToSpan: String, onClickAction: (() -> Any)?, withUnderline: Boolean = true) {
    val startIndex = indexOf(textToSpan)
    if (startIndex == -1) {
        return
    }
    val textColor = ForegroundColorSpan(hyperlinkBlue)
    setSpan(textColor, startIndex, startIndex + textToSpan.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    setSpan(UnderlineSpan(), startIndex, startIndex + textToSpan.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    onClickAction?.let { function ->
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                function()
            }

            override fun updateDrawState(tp: TextPaint) {
                tp.isUnderlineText = withUnderline
            }
        }
        setSpan(clickableSpan, startIndex, startIndex + textToSpan.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }
}