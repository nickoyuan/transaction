package com.cba.transactionaccount.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cba.transactionaccount.databinding.TransactionAccountInfoFragmentBinding
import com.cba.transactionaccount.model.Category
import com.cba.transactionaccount.util.dateToString
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController


@AndroidEntryPoint
class TransactionAccountInfo : Fragment() {

    private lateinit var binding: TransactionAccountInfoFragmentBinding
    val args: TransactionAccountInfoArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TransactionAccountInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val category = Category.values().find { it.name == args.transactionInfoArgs.category }
            ?: Category.uncategorised
        binding.accountCategoryIcon.setImageResource(category.getValue())
        binding.accountCategoryDesc.text = args.transactionInfoArgs.category
        binding.accountAmount.text = args.transactionInfoArgs.amount
        binding.accountDescription.text = args.transactionInfoArgs.description
        binding.accountTime.text = args.transactionInfoArgs.effectiveDate.dateToString()

        binding.fragmentTransactionInfoToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}