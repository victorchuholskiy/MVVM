package com.drg.arch.mvvm.screen.login

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.drg.arch.mvvm.RxTestRule
import com.drg.arch.mvvm.data.entity.remote.response.StatusResponse
import com.drg.arch.mvvm.usecase.login.LoginUseCase
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
class LoginViewModelTest {

	@get:Rule
	var liveDataRule = InstantTaskExecutorRule()

	@get:Rule
	var rxRule = RxTestRule()

	@Mock
	private lateinit var useCase: LoginUseCase

	private lateinit var viewModel: LoginViewModel

	@Before
	fun setupLoginTaskViewModel() {
		MockitoAnnotations.initMocks(this)
		viewModel = LoginViewModel(useCase)
	}

	@Test
	fun onLoginClick_emptyEmail() {
		with(viewModel) {
			login.value = ""
			onLoginClick()
		}
		assertThat(viewModel.loginError.value, `is`("Empty login"))
	}

	@Test
	fun onLoginClick_nullEmail() {
		with(viewModel) {
			login.value = null
			onLoginClick()
		}
		assertThat(viewModel.loginError.value, `is`("Empty login"))
	}

	@Test
	fun onLoginClick_emptyPassword() {
		with(viewModel) {
			login.value = "Some Login"
			password.value = ""
			onLoginClick()
		}
		assertThat(viewModel.passwordError.value, `is`("Empty pass"))
	}

	@Test
	fun onLoginClick_nullPassword() {
		with(viewModel) {
			login.value = "Some Login"
			password.value = null
			onLoginClick()
		}
		assertThat(viewModel.passwordError.value, `is`("Empty pass"))
	}

	@Test
	fun onLoginClick_correctData() {

		val data = StatusResponse("status")

		`when`(useCase.login()).thenReturn(Single.just(data))

		with(viewModel) {
			login.value = "Some Login"
			password.value = "pass"
			onLoginClick()
		}

		verify(useCase).login()

		assertThat(viewModel.isLoading.value, `is`(false))
		assertThat(viewModel.isAuthorized.value, `is`(true))
	}

	@Test
	fun onLoginClick_error() {

		val testException = Exception("testException")
		`when`(useCase.login()).thenReturn(Single.error(testException))

		with(viewModel) {
			login.value = "Some Login"
			password.value = "pass"
			onLoginClick()
		}

		verify(useCase).login()

		assertThat(viewModel.isLoading.value, `is`(false))
		assertThat(viewModel.errorMessage.value, `is`(testException.toString()))
	}
}