package com.drg.arch.mvvm.usecase.login

import com.drg.arch.mvvm.data.entity.remote.response.StatusResponse
import com.drg.arch.mvvm.repository.nodes.auth.AuthRemoteRepository
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

class LoginUseCaseImplTest {

	@Mock
	private lateinit var remoteRepository: AuthRemoteRepository

	private lateinit var useCase: LoginUseCase

	@Before
	fun setupAddEditTaskViewModel() {
		MockitoAnnotations.initMocks(this)
		useCase = LoginUseCaseImpl(remoteRepository)
	}

	@Test
	fun login() {
		val status = "status"
		val response = StatusResponse(status)

		Mockito.`when`(remoteRepository.login()).thenReturn(Single.just(response))

		val testObserver = TestObserver<StatusResponse>()
		useCase.login().subscribe(testObserver)
		testObserver.assertValue(response)

		verify(remoteRepository).login()

		testObserver
				.assertNoErrors()
				.assertValue { it.status == status }
	}
}