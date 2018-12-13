package com.drg.arch.mvvm.usecase.user

import com.drg.arch.mvvm.data.entity.remote.common.UserRemote
import com.drg.arch.mvvm.repository.nodes.user.UserRemoteRepository
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
class LoadUserListUseCaseImplTest {

	@Mock
	private lateinit var remoteRepository: UserRemoteRepository

	private lateinit var useCase: LoadUserListUseCase

	@Before
	fun setupAddEditTaskViewModel() {
		MockitoAnnotations.initMocks(this)
		useCase = LoadUserListUseCaseImpl(remoteRepository)
	}

	@Test
	fun loadUserList() {
		val userFirst = UserRemote(1, "name", 23)
		val userSecond = UserRemote(2, "name", 23)
		val data = listOf(userFirst, userSecond)

		Mockito.`when`(remoteRepository.loadUserList()).thenReturn(Single.just(data))

		val testObserver = TestObserver<List<UserRemote>>()
		useCase.loadUserList().subscribe(testObserver)
		testObserver.assertValue(data)

		verify(remoteRepository).loadUserList()

		testObserver
				.assertNoErrors()
				.assertValue { it.size == data.size }
				.assertValue {
					Observable.fromIterable(it)
							.map(UserRemote::id)
							.toList()
							.blockingGet() == listOf(userFirst.id, userSecond.id)
				}
	}
}