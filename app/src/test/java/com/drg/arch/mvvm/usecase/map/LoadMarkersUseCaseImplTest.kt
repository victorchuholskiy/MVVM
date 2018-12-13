package com.drg.arch.mvvm.usecase.map

import com.drg.arch.mvvm.data.entity.remote.common.MapRemote
import com.drg.arch.mvvm.repository.nodes.map.MapRemoteRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by viktor.chukholskiy
 * 10/27/18.
 */

class LoadMarkersUseCaseImplTest {

	@Mock
	private lateinit var remoteRepository: MapRemoteRepository

	private lateinit var useCase: LoadMarkersUseCase

	@Before
	fun setupAddEditTaskViewModel() {
		MockitoAnnotations.initMocks(this)
		useCase = LoadMarkersUseCaseImpl(remoteRepository)
	}

	@Test
	fun loadMarkersList() {
		val markerFirst = MapRemote("firstId", "first", .0, .0)
		val markerSecond = MapRemote("secondId", "second", .0, .0)
		val markerThird = MapRemote("thirdId", "third", .0, .0)
		val data = listOf(markerFirst, markerSecond, markerThird)

		Mockito.`when`(remoteRepository.loadMarkersList()).thenReturn(Single.just(data))

		val testObserver = TestObserver<List<MapRemote>>()
		useCase.loadMarkersList().subscribe(testObserver)
		testObserver.assertValue(data)

		verify(remoteRepository).loadMarkersList()

		testObserver
				.assertValue { it.size == data.size }
				.assertValue {
					Observable.fromIterable(it)
							.map(MapRemote::id)
							.toList()
							.blockingGet() == listOf(markerFirst.id, markerSecond.id, markerThird.id)
				}
	}
}