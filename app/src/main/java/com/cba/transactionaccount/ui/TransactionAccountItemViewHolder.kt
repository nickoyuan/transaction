package com.cba.transactionaccount.ui

import androidx.recyclerview.widget.RecyclerView
import com.cba.transactionaccount.databinding.TransactionListViewItemBinding
import com.cba.transactionaccount.model.TransactionHistory

class TransactionAccountItemViewHolder(private val binding: TransactionListViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setTransactionData(data: TransactionHistory) {
        if(data.isPending) {
            binding.transactionAccountDescription.text =  "PENDING " + data.description
        } else {
            binding.transactionAccountDescription.text = data.description
        }
        binding.transactionAccountAmount.text = data.amount
    }

    fun setOnClickListener(onClickListener: (transactionHistory : TransactionHistory) -> Unit, transactionHistory: TransactionHistory) {
        binding.root.setOnClickListener {
            onClickListener.invoke(transactionHistory)
        }
    }
}