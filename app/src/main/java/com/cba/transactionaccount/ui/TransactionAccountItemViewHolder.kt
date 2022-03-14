package com.cba.transactionaccount.ui

import androidx.recyclerview.widget.RecyclerView
import com.cba.transactionaccount.databinding.TransactionListViewItemBinding
import com.cba.transactionaccount.model.Category
import com.cba.transactionaccount.model.TransactionHistory
import com.cba.transactionaccount.util.toCurrencyString

class TransactionAccountItemViewHolder(private val binding: TransactionListViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setTransactionData(data: TransactionHistory) {
        if(data.isPending) {
            binding.transactionAccountDescription.text =  "PENDING ${data.description}"
        } else {
            binding.transactionAccountDescription.text = data.description
        }
        binding.transactionAccountAmount.text = data.amount.toCurrencyString()

        val category =  Category.values().find { it.name == data.category } ?: Category.uncategorised
        binding.transactionAccountCategory.setImageResource(category.getValue())
    }

    fun setOnClickListener(onClickListener: (transactionHistory : TransactionHistory) -> Unit, transactionHistory: TransactionHistory) {
        binding.root.setOnClickListener {
            onClickListener.invoke(transactionHistory)
        }
    }
}