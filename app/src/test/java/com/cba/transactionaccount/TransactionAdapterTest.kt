package com.cba.transactionaccount

class TransactionAdapterTest {
}


/**
 * abstract class AdapterBaseTest {
protected lateinit var scenario: ActivityScenario<EmptyActivity>
protected val context: Context
get() {
lateinit var tempContext: Context
scenario.onActivity {
tempContext = it
}

return tempContext
}

@Before
fun waitForEmptyActivityToStart() {
RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

scenario = ActivityScenario.launch(EmptyActivity::class.java)
sleep(300)
waitUntilDisplayed(R.id.empty_activity_title)
}

@After
fun reset() {
RxJavaPlugins.reset()
RxAndroidPlugins.reset()
scenario.close()
}
}
 */

/**
 * class GrantDetailsAccountAdapterTest : AdapterBaseTest() {

@Test
fun should_create_view_holder() {
val adapter = GrantDetailsAccountAdapter()
assertTrue(adapter.onCreateViewHolder(FrameLayout(context), 0) is GrantDetailsAccountViewHolder)
}

@Test
fun should_call_on_bind_with_correct_value() {
val viewHolder: GrantDetailsAccountViewHolder = mock()
val adapter = GrantDetailsAccountAdapter()

val bankAccount = createAccount(BankingProductCategory.TRANS_AND_SAVINGS_ACCOUNTS)
val loanAccount = createAccount(BankingProductCategory.BUSINESS_LOANS)
adapter.submitList(mutableListOf(bankAccount, loanAccount))

adapter.onBindViewHolder(viewHolder, 0)

verify(viewHolder).populate(bankAccount)
}

 */
