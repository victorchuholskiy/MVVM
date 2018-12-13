package com.drg.arch.mvvm.screens.login

import android.arch.lifecycle.MutableLiveData
import android.content.ComponentName
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.MediumTest
import android.support.test.runner.AndroidJUnit4
import com.drg.arch.mvvm.App
import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.screen.dashboard.DashboardActivity
import com.drg.arch.mvvm.screen.login.LoginActivity
import com.drg.arch.mvvm.screen.login.LoginViewModel
import com.drg.arch.mvvm.util.SingleLiveEvent
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`


/**
 * Created by viktor.chukholskiy
 * 9/18/18.
 */
@RunWith(AndroidJUnit4::class)
@MediumTest
class LoginActivityTest {

	@get:Rule
	var activityTestRule = IntentsTestRule<LoginActivity>(LoginActivity::class.java, true,false)

	private lateinit var app:App

	private lateinit var viewModel: LoginViewModel

	private val isAuthorized = SingleLiveEvent<Boolean>()
	private val login = MutableLiveData<String>()
	private val loginError = MutableLiveData<String>()
	private val password = MutableLiveData<String>()
	private val passwordError = MutableLiveData<String>()
	private val isLoading = MutableLiveData<Boolean>()
	private val errorMessage = SingleLiveEvent<String?>()

	@Before
	fun init() {
		app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App
		val appInjector = DaggerTestAppComponent.builder()
				.application(app)
				.build()
		appInjector.inject(app)

		viewModel = MockedLoginActivityModule.viewModel
		`when`(viewModel.isAuthorized).thenReturn(isAuthorized)
		`when`(viewModel.login).thenReturn(login)
		`when`(viewModel.loginError).thenReturn(loginError)
		`when`(viewModel.password).thenReturn(password)
		`when`(viewModel.passwordError).thenReturn(passwordError)
		`when`(viewModel.isLoading).thenReturn(isLoading)
		`when`(viewModel.errorMessage).thenReturn(errorMessage)

		val intent = Intent(getTargetContext(), LoginActivity::class.java)
		activityTestRule.launchActivity(intent)
	}

	@Test
	fun loginPassword_editTask_checkDefState() {
		onView(withId(R.id.et_login)).check(matches(withText("")))
		onView(withId(R.id.et_password)).check(matches(withText("")))
	}

	@Test
	fun btnLogin_enabled() {
		onView(withId(R.id.btn_login)).check(matches(isEnabled()))
	}

	@Test
	fun login_enterData() {
		val testLogin = "test_login"
		onView(withId(R.id.et_login)).perform(click(), typeText(testLogin))
		assertThat(login.value, CoreMatchers.`is`(testLogin))
	}

	@Test
	fun password_enterData() {
		val testPass = "test_pass"
		onView(withId(R.id.et_password)).perform(click(), typeText(testPass))
		assertThat(password.value, CoreMatchers.`is`(testPass))
	}

	@Test
	fun loginError_showMsg() {
		val msg = "login_error"
		loginError.postValue(msg)
		Thread.sleep(100)
		onView(withText(msg)).check(matches(isDisplayed()))
	}

	@Test
	fun passwordError_showMsg() {
		val msg = "password_error"
		passwordError.postValue(msg)
		Thread.sleep(100)
		onView(withText(msg)).check(matches(isDisplayed()))
	}

	@Test
	fun progress_hideShow() {
		isLoading.postValue(true)
		Thread.sleep(100)
		onView(withId(R.id.v_progress)).check(matches(isDisplayed()))

		isLoading.postValue(false)
		Thread.sleep(100)
		onView(withId(R.id.v_progress)).check(matches(not(isDisplayed())))
	}

	@Test
	fun error_showToast() {
		val errorMsg = "toast_msg"
		errorMessage.postValue(errorMsg)
		onView(withText(errorMsg)).inRoot(withDecorView(not(activityTestRule.activity.window.decorView))).check(matches(isDisplayed()))
	}

	@Test
	fun isAuthorized_openNewActivity() {
		isAuthorized.postValue(true)
		intended(hasComponent(ComponentName(getTargetContext(), DashboardActivity::class.java)))
	}
}
