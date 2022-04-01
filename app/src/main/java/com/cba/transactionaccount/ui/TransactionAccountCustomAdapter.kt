package com.cba.transactionaccount.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cba.transactionaccount.databinding.TransactionListDateHeaderItemBinding
import com.cba.transactionaccount.databinding.TransactionListViewItemBinding
import com.cba.transactionaccount.model.AdapterData
import com.cba.transactionaccount.model.TransactionHistory

class TransactionAccountCustomAdapter(
    private val onClickListener: (transactionHistory : TransactionHistory) -> Unit
) : PagingDataAdapter<AdapterData, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val TYPE_DATE_HEADER = 0
        private val TYPE_ITEM = 1

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AdapterData>() {
            override fun areItemsTheSame(oldItem: AdapterData, newItem: AdapterData): Boolean {
                val sameHistoryData = oldItem is AdapterData.data
                        && newItem is AdapterData.data
                         && oldItem.data.id == newItem.data.id

                val sameDateHeader =  oldItem is AdapterData.SeparatorItem  && newItem is AdapterData.SeparatorItem
                        && oldItem == newItem

                return sameHistoryData || sameDateHeader
            }
            override fun areContentsTheSame(oldItem: AdapterData, newItem: AdapterData): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_DATE_HEADER -> {
                val binding = TransactionListDateHeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TransactionAccountDateHeaderViewHolder(binding)
            }
            TYPE_ITEM -> {
                val binding = TransactionListViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TransactionAccountItemViewHolder(binding)
            }
            else -> {
                throw IllegalArgumentException("View Holder Type Unknown")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is AdapterData.data -> TYPE_ITEM
            is AdapterData.SeparatorItem -> TYPE_DATE_HEADER
            else -> throw UnsupportedOperationException("Unknown view")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_DATE_HEADER -> {
                (holder as TransactionAccountDateHeaderViewHolder).setHeaderText(getItem(position) as AdapterData.SeparatorItem)
            }
            TYPE_ITEM -> {
                val transactionHistory = getItem(position) as AdapterData.data
                (holder as TransactionAccountItemViewHolder).setTransactionData(transactionHistory.data)
                holder.setOnClickListener(onClickListener, transactionHistory.data)
            }
            else -> {
                //holder.bindPlaceholder()
            }
        }
    }

}