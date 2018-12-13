package com.drg.arch.mvvm.screen.beer

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.drg.arch.mvvm.RxTestRule
import com.drg.arch.mvvm.data.entity.remote.common.BeerRemote
import com.drg.arch.mvvm.screen.beer.list.BeerListViewModel
import com.drg.arch.mvvm.usecase.beer.LoadBeerListUseCase
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by aleksey.stepanov
 * 8/15/18.
 */
class BeerListModelTest {

	@get:Rule
	var liveDataRule = InstantTaskExecutorRule()

	@get:Rule
	var rxRule = RxTestRule()

	@Mock
	private lateinit var useCase: LoadBeerListUseCase

	private lateinit var viewModel: BeerListViewModel

	@Before
	fun setupAddEditTaskViewModel() {
		MockitoAnnotations.initMocks(this)
	}

	@Test
	fun loadBeerList_correctData() {
		val beerFirst = BeerRemote("Test1", "beer1", 0.15f, 1, 1)
		val beerSecond = BeerRemote("Test2", "beer2", 0.09f, 2, 2)
		val beerThird = BeerRemote("Test3", "beer3", 0.35f, 3, 3)
		val data = listOf(beerFirst, beerSecond, beerThird)

		`when`(useCase.loadBeerList()).thenReturn(Single.just(data))

		viewModel = BeerListViewModel(useCase)
		verify(useCase).loadBeerList()

		assertThat(viewModel.isLoading.value, `is`(false))
		assertThat(viewModel.beerList.value, `is`(data))

	}

	@Test
	fun loadBeerList_error() {
		val testException = Exception("testException")

		`when`(useCase.loadBeerList()).thenReturn(Single.error(testException))

		viewModel = BeerListViewModel(useCase)
		verify(useCase).loadBeerList()

		assertThat(viewModel.isLoading.value, `is`(false))
		assertThat(viewModel.errorMessage.value, `is`(testException.toString()))
	}

	@Test
	fun viewModel_clear() {
		val testException = Exception("testException")

		`when`(useCase.loadBeerList()).thenReturn(Single.error(testException))

		viewModel = BeerListViewModel(useCase)

		verify(useCase).loadBeerList()

		assertThat(viewModel.isLoading.value, `is`(false))
		assertThat(viewModel.errorMessage.value, `is`(testException.toString()))
	}
}