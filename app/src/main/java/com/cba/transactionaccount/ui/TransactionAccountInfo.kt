package com.cba.transactionaccount.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cba.transactionaccount.databinding.TransactionAccountInfoFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionAccountInfo : Fragment() {

    private lateinit var binding: TransactionAccountInfoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TransactionAccountInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}