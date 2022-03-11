package com.cba.transactionaccount.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cba.transactionaccount.R

class TransactionAccountCustomAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_DATE_HEADER = 1
    private val TYPE_ITEM = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val headerView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.transaction_list_header_item, parent, false)
                TransactionAccountHeaderViewHolder(headerView)
            }
            TYPE_DATE_HEADER -> {
                val headerView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.transaction_list_date_header_item, parent, false)
                TransactionAccountDateHeaderViewHolder(headerView)
            }
            TYPE_ITEM -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.transaction_list_view_item, parent, false)
                TransactionAccountItemViewHolder(itemView)
            }
            else -> {
                throw IllegalArgumentException("View Holder Type Unknown")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TransactionAccountHeaderViewHolder -> {
                holder.bind(getItemId(position))
            }
            is TransactionAccountDateHeaderViewHolder -> {
                holder.bind(getItemId(position))
            }
            is TransactionAccountItemViewHolder -> {
                holder.bind(getItemId(position))
            }
            else -> {
                throw IllegalArgumentException("View Holder Type Unknown")
            }
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}