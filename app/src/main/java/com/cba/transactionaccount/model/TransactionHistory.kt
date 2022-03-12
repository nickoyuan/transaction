package com.cba.transactionaccount.model

data class TransactionHistory(
    val isPending: Boolean,
    val description: String,
    val category: String,
    val effectiveDate: String
)

/*
			"amount" : "-20.15",
			"id" : "90F0A3BA-30C9-40E5-8BF8-532FE0D9BD3D",
			"isPending" : true,
			"description" : "Woolworths 1100 Redfern",
			"category" : "groceries",
			"effectiveDate" : "2021-02-27"
 */