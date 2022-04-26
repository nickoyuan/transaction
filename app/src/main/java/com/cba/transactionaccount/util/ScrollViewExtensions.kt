package com.cba.transactionaccount.util

import android.widget.ScrollView
import androidx.core.widget.NestedScrollView

fun NestedScrollView?.canScroll() = this?.getChildAt(0)?.let { height < it.height + paddingTop + paddingBottom }
        ?: false

fun ScrollView.scrollToBottom() {
    val lastChild = this.getChildAt(this.childCount - 1)
    val bottom = lastChild.bottom + this.paddingBottom
    val sy = this.scrollY
    val sh = this.height
    val delta = bottom - (sy + sh)

    this.smoothScrollBy(0, delta)
}