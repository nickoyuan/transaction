package com.cba.transactionaccount.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cba.transactionaccount.databinding.TransactionListFragmentBinding
import com.cba.transactionaccount.model.TransactionHistory
import com.cba.transactionaccount.model.TransactionViewState.State.SUCCESS
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TransactionAccountFragment : Fragment() {

    private lateinit var binding: TransactionListFragmentBinding
    private val transactionAccountViewModel: TransactionAccountViewModel by viewModels()
    private lateinit var transactionAccountAdapter: TransactionAccountCustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TransactionListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTransactionAccountAdapter(binding.transactionAccountRecyclerView)

        transactionAccountViewModel.screenState.observe(viewLifecycleOwner, Observer {
           when(it.state) {
               SUCCESS -> {
                   submitTransactionHistory(it.data!!)
               }
               else -> {}
           }
        })

        transactionAccountViewModel.loadTransactions()
    }

    private fun submitTransactionHistory(accountHistory : List<TransactionHistory>) {
        transactionAccountAdapter.setAdapterData(accountHistory)
    }

    private fun setUpTransactionAccountAdapter(transactionAccountRecyclerView: RecyclerView) {
        transactionAccountAdapter = TransactionAccountCustomAdapter()
        with(transactionAccountRecyclerView) {
            adapter = transactionAccountAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
    }
}