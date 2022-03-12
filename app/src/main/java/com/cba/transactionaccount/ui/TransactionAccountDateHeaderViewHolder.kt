package com.cba.transactionaccount.ui

import androidx.recyclerview.widget.RecyclerView
import com.cba.transactionaccount.databinding.TransactionListDateHeaderItemBinding

class TransactionAccountDateHeaderViewHolder(private val binding: TransactionListDateHeaderItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setHeaderText(text: String) {
        binding.transactionDatetimeTxt.text = text
    }
}