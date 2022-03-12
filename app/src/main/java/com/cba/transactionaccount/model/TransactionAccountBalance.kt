package com.cba.transactionaccount.model

data class TransactionAccountBalance(
    val balance : String,  // Change to AMOUNT CLASS
    val accountName : String,
    val available : String,
    val bsb: String,
    val accountNumber : String
)

/*
		"bsb" : "062005",
		"accountNumber" : "17095888",
		"balance" : "246.7",
		"available" : "226.76",
		"accountName" : "Complete Access"
 */