package com.drg.arch.mvvm.usecase.beer

import com.drg.arch.mvvm.data.entity.remote.common.BeerRemote
import com.drg.arch.mvvm.repository.nodes.beer.BeerRemoteRepository
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
 * Created by aleksey.stepanov
 * 8/15/18.
 */

class LoadBeerListUseCaseImplTest {

	@Mock
	private lateinit var remoteRepository: BeerRemoteRepository

	private lateinit var useCase: LoadBeerListUseCase

	@Before
	fun setupAddEditTaskViewModel() {
		MockitoAnnotations.initMocks(this)
		useCase = LoadBeerListUseCaseImpl(remoteRepository)
	}

	@Test
	fun loadUserList() {
		val beerFirst = BeerRemote("Test1", "beer1", 0.15f, 1, 1)
		val beerSecond = BeerRemote("Test2", "beer2", 0.09f, 2, 2)
		val beerThird = BeerRemote("Test3", "beer3", 0.35f, 3, 3)
		val data = listOf(beerFirst, beerSecond, beerThird)

		Mockito.`when`(remoteRepository.loadBeerList()).thenReturn(Single.just(data))

		val testObserver = TestObserver<List<BeerRemote>>()
		useCase.loadBeerList().subscribe(testObserver)
		testObserver.assertValue(data)

		verify(remoteRepository).loadBeerList()

		testObserver
				.assertNoErrors()
				.assertValue { it.size == data.size }
				.assertValue {
					Observable.fromIterable(it)
							.map(BeerRemote::name)
							.toList()
							.blockingGet() == listOf(beerFirst.name, beerSecond.name, beerThird.name)
				}
	}
}