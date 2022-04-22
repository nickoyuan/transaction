package com.cba.transactionaccount

class TransactionNavigationTest {
}


/*
    @Test
    fun should_show_account_statements() {
        val statementSummary = StatementSummary(
            startDate = LocalDate(2016, 6, 1),
            endDate = LocalDate(2016, 6, 30),
            statementNumber = "123456789")

        startTest()

        // Banking menu
        waitUntilViewDisplayed(R.id.fragment_banking_scroll_view)
        dispatcher.setupAccountStatementsResponse(statementSummary)
        clickOn(R.id.fragment_banking_account_statements)

        // Account statements
        waitUntilViewDisplayed(R.id.activity_account_statements_recycler_view)
        pressBack()

        // Banking menu
        waitUntilViewDisplayed(R.id.fragment_banking_scroll_view)
    }

    private fun startTest() {
        val intent = InstrumentationRegistry.getInstrumentation().targetContext.createIntent<MainActivity>("SCREEN_TYPE_KEY" to BankingAccountType.TRANSACTION_ACCOUNT_ITEM)
        scenario = ActivityScenario.launch(intent)

        // Select banking from bottom navbar
        waitUntilViewDisplayed(R.id.activity_main_bottom_navbar)
        dispatcher.setupBankingAccountList(bankAccountList)
        clickOn(R.id.navigation_banking)

        // Select bank account
        waitUntilViewDisplayed(R.id.fragment_banking_tab_recycler_view)
        clickListItem(R.id.fragment_banking_tab_recycler_view, 1)
    }
 */

/*

Navigation
abstract class NavigationTestBase : ActivityTestBase() {

    protected lateinit var scenario: ActivityScenario<MainActivity>

    protected val now: DateTime = DateTime.now()

    @Before
    fun setupBaseTest() {
        DateTimeUtils.setCurrentMillisFixed(now.toDate().time)
        dispatcher.setupNotificationToken()
        dispatcher.setupGenericCardsResponse()
        dispatcher.setupSettlementTimes(settlementTimes)
        dispatcher.setupAccountResponse(account)
        dispatcher.setupBillsSummaryResponse(billsSummary)
        dispatcher.setupEftposSummary(eftposSummary)
        dispatcher.setupNoLoanSummaryDetails()
        dispatcher.setupUserActionsResponse(allUserActions)
        dispatcher.setupBusinessSummaryResponses(businessSummary)
    }

    @After
    fun teardownBaseTest() {
        DateTimeUtils.setCurrentMillisSystem()
        if (this::scenario.isInitialized) {
            scenario.close()
        }
    }
 */