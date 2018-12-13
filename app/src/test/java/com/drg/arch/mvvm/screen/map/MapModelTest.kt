package com.drg.arch.mvvm.screen.map

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.drg.arch.mvvm.RxTestRule
import com.drg.arch.mvvm.data.entity.remote.common.MapRemote
import com.drg.arch.mvvm.usecase.map.LoadMarkersUseCase
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
import java.util.*

/**
 * Created by aleksey.stepanov
 * 8/15/18.
 */
class MapModelTest {

	@get:Rule
	var liveDataRule = InstantTaskExecutorRule()

	@get:Rule
	var rxRule = RxTestRule()

	@Mock
	private lateinit var useCase: LoadMarkersUseCase

	private lateinit var viewModel: MapViewModel

	@Before
	fun setupAddEditTaskViewModel() {
		MockitoAnnotations.initMocks(this)
		viewModel = MapViewModel(useCase)
	}

	@Test
	fun loadMarkers_correctData() {
		val markerFirst = MapRemote("firstId", "first", .0, .0)
		val markerSecond = MapRemote("secondId", "second", .0, .0)
		val markerThird = MapRemote("thirdId", "third", .0, .0)
		val data = listOf(markerFirst, markerSecond, markerThird)

		`when`(useCase.loadMarkersList()).thenReturn(Single.just(data))
		viewModel.loadMarkers()
		verify(useCase).loadMarkersList()

		assertThat(viewModel.markers.value, `is`(data))

	}

	@Test
	fun loadMarkers_error() {
		val testException = Exception("testException")

		`when`(useCase.loadMarkersList()).thenReturn(Single.error(testException))
		viewModel.loadMarkers()
		verify(useCase).loadMarkersList()

		assertThat(viewModel.markers.value, `is`(Arrays.asList()))
	}
}