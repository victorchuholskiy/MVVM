package com.drg.arch.mvvm.screens.beer

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
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.drg.arch.mvvm.App
import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.data.entity.remote.common.BeerRemote
import com.drg.arch.mvvm.screen.beer.list.BeerListActivity
import com.drg.arch.mvvm.screen.beer.list.BeerListViewModel
import com.drg.arch.mvvm.util.SingleLiveEvent
import com.drg.arch.mvvm.utils.RecyclerViewMatcher
import com.drg.arch.mvvm.utils.RecyclerViewMatcher.Companion.withItemCount
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
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
class BeerListActivityTest {

	@get:Rule
	var activityTestRule = ActivityTestRule<BeerListActivity>(BeerListActivity::class.java, true,false)

	private lateinit var app:App

	private lateinit var viewModel: BeerListViewModel

	private val beerList = MutableLiveData<List<BeerRemote>>()
	private val isLoading = MutableLiveData<Boolean>()
	private val errorMessage = SingleLiveEvent<String?>()

	@Before
	fun init() {
		app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App
		val appInjector = DaggerTestAppComponent.builder()
				.application(app)
				.build()
		appInjector.inject(app)

		viewModel = MockedBeerListActivityModule.viewModel
		`when`(viewModel.beerList).thenReturn(beerList)
		`when`(viewModel.isLoading).thenReturn(isLoading)
		`when`(viewModel.errorMessage).thenReturn(errorMessage)

		val intent = Intent(getTargetContext(), BeerListActivity::class.java)
		activityTestRule.launchActivity(intent)
	}

	@Test
	fun userList_emptyList() {
		val data: List<BeerRemote> = listOf()

		beerList.postValue(data)
		Thread.sleep(100)
		onView(withId(R.id.rv)).check(matches(withItemCount(data.size)))
		onView(withId(R.id.tv_placeholder)).check(matches(isDisplayed()))
	}

	@Test
	fun userList_showElements() {
		val beerFirst = BeerRemote("Test1", "beer_1", 0.15f, 1, 1)
		val beerSecond = BeerRemote("Test2", "beer_2", 0.09f, 2, 2)
		val beerThird = BeerRemote("Test3", "beer_3", 0.35f, 3, 3)
		val data = listOf(beerFirst, beerSecond, beerThird)

		beerList.postValue(data)
		Thread.sleep(100)
		onView(withId(R.id.rv)).check(matches(withItemCount(data.size)))
		for (ind in 0 until data.size) {
			onView(listMatcher().atPosition(ind)).check(matches(hasDescendant(withText(getTargetContext().getString(R.string.name) + data[ind].name))))
			onView(listMatcher().atPosition(ind)).check(matches(hasDescendant(withText(getTargetContext().getString(R.string.alcohol) + data[ind].alc))))
			onView(listMatcher().atPosition(ind)).check(matches(hasDescendant(withText(getTargetContext().getString(R.string.ibu) + data[ind].ibu))))
			onView(listMatcher().atPosition(ind)).check(matches(hasDescendant(withText(getTargetContext().getString(R.string.plate) + data[ind].plate))))
		}
	}

	@Test
	fun actionBarTitle_check() {
		onView(allOf(isAssignableFrom(TextView::class.java), withParent(isAssignableFrom(Toolbar::class.java)))).check(matches(withText("User List ac")))
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
