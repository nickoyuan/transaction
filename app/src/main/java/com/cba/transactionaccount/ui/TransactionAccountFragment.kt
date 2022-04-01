package com.cba.transactionaccount.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cba.transactionaccount.R
import com.cba.transactionaccount.databinding.TransactionListFragmentBinding
import com.cba.transactionaccount.model.AccountData
import com.cba.transactionaccount.model.TransactionHistory
import com.cba.transactionaccount.util.toCurrencyString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


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
        transactionAccountViewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is TransactionViewState.Successful -> {
                    hideLoadingScreen()
                    binding.fragmentTransactionListToolbar.title = "test"
                    populateAccount(AccountData(
                        bsb = "123124",
                        accountNumber = "123123",
                        balance = "12",
                        available = "123123",
                        accountName = "12"
                    ))
                    lifecycleScope.launch {
                        transactionAccountAdapter.submitData(it.data)
                    }
                }
                is TransactionViewState.Loading -> {
                    showLoadingScreen()
                }
                else -> {}
            }
        })

        binding.transactionSwipeToRefresh.setOnRefreshListener {
            transactionAccountViewModel.emitEvent(TransactionViewEvent.EventFetch)
        }

        transactionAccountViewModel.emitEvent(TransactionViewEvent.EventFetch)
    }

    private fun hideLoadingScreen() {
        binding.transactionSwipeToRefresh.isRefreshing = false
    }

    private fun showLoadingScreen() {
        binding.transactionSwipeToRefresh.isRefreshing = true
    }

    private fun populateAccount(account : AccountData) {
        binding.bsbTxt.text = account.bsb
        binding.accountNoTxt.text = account.accountNumber
        binding.accountAvailableTxt.text = account.available.toCurrencyString()
        binding.balanceText.text =  getString(
            R.string.balance,
            account.balance.toCurrencyString()
        )
    }

    private fun setUpTransactionAccountAdapter(transactionAccountRecyclerView: RecyclerView) {
        transactionAccountAdapter = TransactionAccountCustomAdapter(::transactionHistoryItemClickListener)
        with(transactionAccountRecyclerView) {
            adapter = transactionAccountAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun transactionHistoryItemClickListener(transactionHistory: TransactionHistory) {
        val directions =
            TransactionAccountFragmentDirections.actionTransactionAccountFragmentToTransactionAccountInfo(
                transactionInfoArgs = transactionHistory,
                transactionInfoToolbarTitle = binding.fragmentTransactionListToolbar.title.toString()
            )
        findNavController().navigate(directions)
    }
}