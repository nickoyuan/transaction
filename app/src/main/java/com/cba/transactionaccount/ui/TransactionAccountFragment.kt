package com.cba.transactionaccount.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cba.transactionaccount.databinding.TransactionListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TransactionAccountFragment : Fragment() {

    private lateinit var binding: TransactionListFragmentBinding
    private val stakePoolViewModel: TransactionAccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TransactionListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}