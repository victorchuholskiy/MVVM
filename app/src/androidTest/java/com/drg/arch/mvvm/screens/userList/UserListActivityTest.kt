package com.drg.arch.mvvm.screens.userList

import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.drg.arch.mvvm.App
import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.data.entity.remote.common.UserRemote
import com.drg.arch.mvvm.screen.userList.UserListActivity
import com.drg.arch.mvvm.screen.userList.UserListViewModel
import com.drg.arch.mvvm.util.SingleLiveEvent
import com.drg.arch.mvvm.utils.RecyclerViewMatcher
import com.drg.arch.mvvm.utils.RecyclerViewMatcher.Companion.withItemCount
import org.hamcrest.Matchers
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
class UserListActivityTest {

	@get:Rule
	var activityTestRule = ActivityTestRule<UserListActivity>(UserListActivity::class.java, true,false)

	private lateinit var app:App

	private lateinit var viewModel: UserListViewModel

	private val userList = MutableLiveData<List<UserRemote>>()
	private val isLoading = MutableLiveData<Boolean>()
	private val errorMessage = SingleLiveEvent<String?>()

	@Before
	fun init() {
		app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App
		val appInjector = DaggerTestAppComponent.builder()
				.application(app)
				.build()
		appInjector.inject(app)

		viewModel = MockedUserListActivityModule.viewModel
		`when`(viewModel.userList).thenReturn(userList)
		`when`(viewModel.isLoading).thenReturn(isLoading)
		`when`(viewModel.errorMessage).thenReturn(errorMessage)

		val intent = Intent(getTargetContext(), UserListActivity::class.java)
		activityTestRule.launchActivity(intent)
	}

	@Test
	fun userList_showElements() {
		val userFirst = UserRemote(1, "name_1", 23)
		val userSecond = UserRemote(2, "name_2", 23)
		val data = listOf(userFirst, userSecond)

		userList.postValue(data)
		Thread.sleep(100)
		onView(withId(R.id.rv)).check(matches(withItemCount(data.size)))
		for (ind in 0 until data.size) {
			onView(listMatcher().atPosition(ind)).check(matches(hasDescendant(withText(data[ind].name))))
			onView(listMatcher().atPosition(ind)).check(matches(hasDescendant(withText("${getTargetContext().getString(R.string.age)}: ${data[ind].age}"))))
		}
	}

	@Test
	fun progress_hideShow() {
		isLoading.postValue(true)
		Thread.sleep(100)
		onView(withId(R.id.v_progress)).check(matches(ViewMatchers.isDisplayed()))

		isLoading.postValue(false)
		Thread.sleep(100)
		onView(withId(R.id.v_progress)).check(matches(Matchers.not(ViewMatchers.isDisplayed())))
	}

	@Test
	fun error_showToast() {
		val errorMsg = "toast_msg"
		errorMessage.postValue(errorMsg)
		onView(withText(errorMsg)).inRoot(withDecorView(Matchers.not(activityTestRule.activity.window.decorView))).check(matches(isDisplayed()))
	}

	private fun listMatcher(): RecyclerViewMatcher {
		return RecyclerViewMatcher(R.id.rv)
	}
}
