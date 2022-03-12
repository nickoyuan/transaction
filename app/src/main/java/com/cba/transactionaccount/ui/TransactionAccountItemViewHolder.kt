package com.cba.transactionaccount.ui

import androidx.recyclerview.widget.RecyclerView
import com.cba.transactionaccount.databinding.TransactionListViewItemBinding
import com.cba.transactionaccount.model.TransactionHistory

class TransactionAccountItemViewHolder(private val binding: TransactionListViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setTransactionData(data: TransactionHistory) {
        binding.transactionAccountDescription.text = data.description
        binding.transactionAccountAmount.text = data.amount
    }
}