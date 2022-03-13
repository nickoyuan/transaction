package com.cba.transactionaccount.ui

import androidx.recyclerview.widget.RecyclerView
import com.cba.transactionaccount.databinding.TransactionListDateHeaderItemBinding
import com.cba.transactionaccount.util.dateToString
import org.joda.time.Days
import org.joda.time.LocalDate

class TransactionAccountDateHeaderViewHolder(private val binding: TransactionListDateHeaderItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setHeaderText(date: LocalDate) {
        binding.transactionDatetimeTxt.text = date.dateToString()
        binding.transactionDayText.text = dateDifference(date)
    }

    private fun dateDifference(text : LocalDate): String {
        return Days.daysBetween(text, LocalDate.now()).getDays().toString() + " days ago"
    }
}