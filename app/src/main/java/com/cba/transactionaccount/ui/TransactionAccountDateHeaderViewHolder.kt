package com.cba.transactionaccount.ui

import androidx.recyclerview.widget.RecyclerView
import com.cba.transactionaccount.databinding.TransactionListDateHeaderItemBinding
import com.cba.transactionaccount.model.AdapterData
import com.cba.transactionaccount.util.dateToString
import org.joda.time.Days
import org.joda.time.LocalDate

class TransactionAccountDateHeaderViewHolder(private val binding: TransactionListDateHeaderItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setHeaderText(date: AdapterData.SeparatorItem) {
        binding.transactionDatetimeTxt.text = date.localDate.dateToString()
        binding.transactionDayText.text = dateDifference(date.localDate)
    }

    private fun dateDifference(text : LocalDate): String {
        return Days.daysBetween(text, LocalDate.now()).getDays().toString() + " days ago"
    }
}