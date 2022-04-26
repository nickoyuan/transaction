package  com.cba.transactionaccount.util

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BulletSpan
import androidx.annotation.ColorInt

const val BULLET_POINT_GAP_WIDTH = 10


fun SpannableStringBuilder.appendBulletSpans(
        list: List<String>,
        @ColorInt bulletPointColor: Int
): SpannableStringBuilder {
    list.forEach { text ->
        val length = this.length
        this.append(text)
        this.append("\n\n")
        this.setSpan(
                BulletSpan(BULLET_POINT_GAP_WIDTH, bulletPointColor),
                length,
                length + text.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
    }

    return this
}
