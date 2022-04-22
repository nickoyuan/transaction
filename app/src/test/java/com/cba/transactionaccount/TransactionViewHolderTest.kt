package com.cba.transactionaccount

class TransactionViewHolderTest {
}

/**
 * class EftposTransactionViewHolderPresenterTest {

companion object {
private val TRANSACTION_DATE_TIME: LocalDateTime = LocalDateTime()
.withDayOfMonth(1)
.withMonthOfYear(1)
.withYear(1970)
.withHourOfDay(10)
.withMinuteOfHour(10)

private val APPROVED_TRANSACTION = EftposTransaction(
mid = 27762,
tid = 1,
caid = "27762",
amount = Amount.fromCents(950),
terminalStan = 12345L,
cardNumber = "XXXXXXXXXXXX7771",
description = "",
cardType = "EPAL",
cashout = Amount.ZERO,
fee = Amount(BigDecimal.valueOf(0.08)),
feeType = "DEBIT_EFTPOS_LOW_VALUE_NO_CASHOUT",
refund = Amount.ZERO,
status = APPROVED,
donation = Amount.ZERO,
surcharge = Amount.ZERO,
tip = Amount.ZERO,
totalAmount = Amount.fromCents(1050),
transactionDateTime = TRANSACTION_DATE_TIME,
transactionType = PURCHASE,
transmissionDateTimeUtc = LocalDateTime.now().minusHours(1).millisOfSecond.toLong())
}

val view: EftposTransactionViewHolderView = mock()
val presenter = EftposTransactionViewHolderPresenter()

@Before
fun setUp() {
presenter.attachView(view)
}

@Test
fun shouldSetAmountEftposTransactionData() {
presenter.setTransactionData(APPROVED_TRANSACTION)

verify(view).setAmount("$10.50")
}

 */