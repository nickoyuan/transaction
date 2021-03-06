package com.cba.transactionaccount.ui

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cba.transactionaccount.databinding.TransactionAccountInfoFragmentBinding
import com.cba.transactionaccount.model.Category
import com.cba.transactionaccount.util.dateToString
import com.cba.transactionaccount.util.toCurrencyString
import com.cba.transactionaccount.util.toHtml
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TransactionAccountInfo : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var binding: TransactionAccountInfoFragmentBinding
    val args: TransactionAccountInfoArgs by navArgs()
    private lateinit var textToSpeech: TextToSpeech

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

        textToSpeech = TextToSpeech(context, this)
        val category = Category.values().find { it.name == args.transactionInfoArgs.category }
            ?: Category.uncategorised
        binding.accountCategoryIcon.setImageResource(category.getValue())
        binding.accountCategoryDesc.text = args.transactionInfoArgs.category
        binding.accountAmount.text = args.transactionInfoArgs.amount.toCurrencyString()
        if (args.transactionInfoArgs.isPending) {
            binding.accountDescription.text =
                "PENDING: ${args.transactionInfoArgs.description.toHtml()}"
        } else {
            binding.accountDescription.text = args.transactionInfoArgs.description.toHtml()
        }
        binding.accountTime.text = args.transactionInfoArgs.effectiveDate.dateToString()

        binding.fragmentTransactionInfoToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.let {
            it.stop()
            it.shutdown()
        }
    }

    override fun onInit(status: Int) {
        binding.hearingAidIcon.setOnClickListener {
            textToSpeech?.let {
                it.speak(
                    "Category of ${args.transactionInfoArgs.category} with Transaction amount " +
                            "of ${args.transactionInfoArgs.amount} with the" +
                            " Description ${args.transactionInfoArgs.description}" +
                            " on ${args.transactionInfoArgs.effectiveDate}",
                    TextToSpeech.QUEUE_FLUSH,
                    null
                )
            }
        }
    }
}