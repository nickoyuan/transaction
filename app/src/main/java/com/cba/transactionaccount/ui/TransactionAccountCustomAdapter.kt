package com.cba.transactionaccount.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cba.transactionaccount.databinding.TransactionListDateHeaderItemBinding
import com.cba.transactionaccount.databinding.TransactionListViewItemBinding
import com.cba.transactionaccount.model.AdapterData
import com.cba.transactionaccount.model.TransactionHistory
import org.joda.time.LocalDate

class TransactionAccountCustomAdapter(
    private val onClickListener: (transactionHistory : TransactionHistory) -> Unit
) : ListAdapter<AdapterData, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val TYPE_DATE_HEADER = 0
        private val TYPE_ITEM = 1

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AdapterData>() {
            override fun areItemsTheSame(oldItem: AdapterData, newItem: AdapterData): Boolean =
                oldItem == newItem

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
        return getItem(position).type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_DATE_HEADER -> {
                (holder as TransactionAccountDateHeaderViewHolder).setHeaderText(getItem(position).data as LocalDate)
            }
            TYPE_ITEM -> {
                (holder as TransactionAccountItemViewHolder).setTransactionData(getItem(position).data as TransactionHistory)
                holder.setOnClickListener(onClickListener, getItem(position).data as TransactionHistory)
            }
            else -> {
               // Show placeholder view and hide original view
            }
        }
    }

    override fun getItemCount() = currentList.size

    fun setAdapterData(item : List<TransactionHistory>) {
         submitList(groupData(item))
    }

    private fun groupData(item: List<TransactionHistory>): ArrayList<AdapterData> {
        val list = ArrayList<AdapterData>()
        val nonPending = ArrayList<AdapterData>()
        item.forEach {
            if (!list.contains(AdapterData(it.effectiveDate, TYPE_DATE_HEADER))) {
                list.addAll(nonPending)
                list.add(AdapterData(it.effectiveDate, TYPE_DATE_HEADER))
                nonPending.clear()
            }
            if(it.isPending) {
                list.add(AdapterData(it, TYPE_ITEM))
            } else {
                nonPending.add(AdapterData(it, TYPE_ITEM))
            }
        }
        if(nonPending.isNotEmpty()) {
            list.addAll(nonPending)
        }
        return list
    }


}