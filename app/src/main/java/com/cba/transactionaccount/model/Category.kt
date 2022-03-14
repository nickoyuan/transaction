package com.cba.transactionaccount.model

import com.cba.transactionaccount.R

enum class Category(private val value: Int) {
    business(R.drawable.ic_icon_category_business),
    cards(R.drawable.ic_icon_category_cards),
    cash(R.drawable.ic_icon_category_cash),
    categories(R.drawable.ic_icon_category_categories),
    eatingOut(R.drawable.ic_icon_category_eating_out),
    education(R.drawable.ic_icon_category_education),
    entertainment(R.drawable.ic_icon_category_entertainment),
    shopping(R.drawable.ic_icon_category_shopping),
    transport(R.drawable.ic_icon_category_transportation),
    travel(R.drawable.ic_icon_category_travel),
    uncategorised(R.drawable.ic_icon_category_uncategorised);

    fun getValue(): Int = value
}