package com.cba.transactionaccount

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.cba.transactionaccount", appContext.packageName)
    }
}


/***

launchFragmentInContainer<TermDepositAboutFragment>(null, R.style.TyroTheme)

protected fun waitUntilViewDisplayed(@IdRes viewId: Int) {
waitForCondition(ViewWithIdVisibleInstruction(viewId))
}

androidx.fragment.app.testing


@RunWith(AndroidJUnit4::class)

 FragmentTest : ActivityTestBase()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

        companion object {
        val GSON: Gson by lazy { GsonFactory.gson }
        const val SPLASH_SCREEN_DELAY_MS = 700
        private const val MOCK_SERVER_PORT = 11227
        private const val HTTP_LOCALHOST = "http://localhost"
        internal const val MOCK_SERVER_BASE_URL = "$HTTP_LOCALHOST:$MOCK_SERVER_PORT/"
    }

    private val mockWebServer = MockWebServer()

    val dispatcher: TestFixtureDispatcher = TestFixtureDispatcher().also {
        mockWebServer.dispatcher = it
        mockWebServer.start(MOCK_SERVER_PORT)
    }


fun TestFixtureDispatcher.setupBillsSummary404Response() {
    putResponse(MockResponseMatcher(
            requestMethod = GET,
            pathPattern = "/accounts/.* /scheduledBatches/summary",
   mockResponse = mockResponse(HTTP_NOT_FOUND)

protected fun injectionSetup() {
    RxJavaIdlingResource.registerIdlingSchedulers()
    val merchantApplication =
        getInstrumentation().targetContext.applicationContext as MerchantApplication
    val dataModule = TestDataModule(MOCK_SERVER_BASE_URL)
    val appModule = TestAppModule(merchantApplication)
    val auth0Module = Auth0Module()
    testAppComponent = DaggerTestAppComponent.builder()
        .appModule(appModule)
        .dataModule(dataModule)
        .auth0Module(auth0Module)
        .build()
    merchantApplication.component = testAppComponent
    injectTest(testAppComponent)
    configurationStore.clearAll()
    eventBus.clearAllMessages()
    javaClass.getAnnotation(SetTime::class.java)
        ?.let { (timeProvider as TestTimeProvider).setTimeMillis(DateTime.parse(it.dateTimeString).millis) }

    setupRegisteredDevice()
}

private fun <T : AppCompatActivity> createScenario(): ActivityScenario<T> {
val intent = InstrumentationRegistry.getInstrumentation().targetContext.createIntent<AccountActivity>("SCREEN_TYPE_KEY" to BankingAccountType.TRANSACTION_ACCOUNT_ITEM)
return ActivityScenario.launch(intent)
}


@Test
fun should_display_about_term_deposit_screen() {
dispatcher.setupTermDepositProducts(getProducts())
dispatcher.setupAccountResponse(getAccount())

launchFragmentInContainer<TermDepositAboutFragment>(null, R.style.TyroTheme)
sleep(500)

waitUntilViewDisplayed(R.id.fragment_term_deposit_about_button_bar)

assertDisplayed(R.id.fragment_term_deposit_about_subtitle, "What makes a Tyro Business Term Deposit different?")

assertDisplayed(R.id.fragment_term_deposit_about_proposal_icon_1)
assertDisplayed(R.id.fragment_term_deposit_about_proposal_title_1, "Competitive interest rates")
assertDisplayed(
R.id.fragment_term_deposit_about_proposal_text_1,
"Lock away a competitive interest rate on deposit amounts as low as $1,000."
)

safelyScrollTo(R.id.fragment_term_deposit_about_proposal_text_4)
assertDisplayed(R.id.fragment_term_deposit_about_proposal_icon_4)
assertDisplayed(R.id.fragment_term_deposit_about_proposal_title_4, "Ease and simplicity")
assertDisplayed(
R.id.fragment_term_deposit_about_proposal_text_4,
"Open in minutes via the Tyro App with your funds automatically transferred from your fee-free Tyro Bank Account."
)

assertDisplayed(R.id.fragment_term_deposit_about_button_bar)
}

*/

/*

import androidx.appcompat.widget.Toolbar
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.interaction.BaristaListInteractions.scrollListToPosition
import com.tyro.merchantbanking.R
import com.tyro.merchantbanking.data.model.external.eftpos.EftposTransaction
import com.tyro.merchantbanking.data.model.external.eftpos.EftposTransactionStatus
import com.tyro.merchantbanking.data.model.external.eftpos.EftposTransactionType
import com.tyro.merchantbanking.data.model.internal.amount.Amount
import com.tyro.merchantbanking.espresso.annotation.SetTime
import com.tyro.merchantbanking.espresso.config.TestAppComponent
import com.tyro.merchantbanking.espresso.fixture.MockResponseMatcher
import com.tyro.merchantbanking.espresso.util.WaitAction.waitUntilDisplayed
import com.tyro.merchantbanking.rest.gson.GsonFactory
import com.tyro.merchantbanking.ui.ActivityTestBase
import com.tyro.merchantbanking.ui.eftpos.transactions.EftposTransactionListActivity
import com.tyro.merchantbanking.util.createIntent
import okhttp3.mockwebserver.MockResponse
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.joda.time.LocalDateTime
import org.junit.After
import org.junit.Test



 Activity
 lateinit var scenario: ActivityScenario<EftposTransactionListActivity>
  scenario = ActivityScenario.launch(intent)


    protected inline fun <reified T : AppCompatActivity> startTestScenario(): ActivityScenario<T> = ActivityScenario.launch(T::class.java)


    fun waitUntilDisplayed(vararg matchers: Matcher<View>) = try {
        doUntilCondition(onView(allOf(matchers.toList())), matches(isCompletelyDisplayed()))
    } catch (_: AmbiguousViewMatcherException) {
        doUntilCondition(onView(allOf(matchers.toList() + isDisplayed())), matches(isDisplayed()))
    }

        protected inline fun <reified ToolbarClass : View> assertToolbarTitle(title: CharSequence) {
        object : BoundedMatcher<View, ToolbarClass>(ToolbarClass::class.java) {
            public override fun matchesSafely(toolbar: ToolbarClass) =
                when (toolbar) {
                    is Toolbar -> title == (toolbar as Toolbar).title
                    else -> false
                }

            override fun describeTo(description: Description) {
                description.appendText("with collapsing toolbar title: ")
                description.appendText(title.toString())
            }
        }.apply {
            onView(this).check(ViewAssertions.matches(isDisplayed()))
        }
    }


 */