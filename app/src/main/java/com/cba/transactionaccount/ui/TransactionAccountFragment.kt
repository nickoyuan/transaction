package com.cba.transactionaccount.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cba.transactionaccount.databinding.TransactionListFragmentBinding
import com.cba.transactionaccount.model.TransactionHistory
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


        // Testing
        var taet = listOf(TransactionHistory(
            true,
            "adslfjals",
            "shopping",
            "231"
        ),
            TransactionHistory(
                true,
                "asfe",
                "shopping",
                "231"
            ),
            TransactionHistory(
                true,
                "fasgsab",
                "shopping",
                "231"
            ),
            TransactionHistory(
                true,
                "adslfjals",
                "shopping",
                "1124"
            )
        )

        submitTransactionHistory(taet)
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