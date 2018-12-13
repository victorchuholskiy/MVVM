package com.drg.arch.mvvm.screen.user

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.drg.arch.mvvm.RxTestRule
import com.drg.arch.mvvm.data.entity.remote.common.UserRemote
import com.drg.arch.mvvm.repository.Prefs
import com.drg.arch.mvvm.screen.userList.UserListViewModel
import com.drg.arch.mvvm.usecase.user.LoadUserListUseCase
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
class UserListModelTest {

	@get:Rule
	var liveDataRule = InstantTaskExecutorRule()

	@get:Rule
	var rxRule = RxTestRule()

	@Mock
	private lateinit var useCase: LoadUserListUseCase

	@Mock
	private lateinit var prefs: Prefs

	private lateinit var viewModel: UserListViewModel

	@Before
	fun setupAddEditTaskViewModel() {
		MockitoAnnotations.initMocks(this)
	}

	@Test
	fun loadUsers_correctData() {
		val userFirst = UserRemote(1, "name", 23)
		val userSecond = UserRemote(2, "name", 23)
		val data = listOf(userFirst, userSecond)

		`when`(prefs.appVersion).thenReturn("123")
		`when`(useCase.loadUserList()).thenReturn(Single.just(data))

		viewModel = UserListViewModel(prefs, useCase)
		verify(useCase).loadUserList()

		assertThat(viewModel.isLoading.value, `is`(false))
		assertThat(viewModel.userList.value, `is`(data))

	}

	@Test
	fun loadUsers_error() {
		val testException = Exception("testException")

		`when`(prefs.appVersion).thenReturn("123")
		`when`(useCase.loadUserList()).thenReturn(Single.error(testException))

		viewModel = UserListViewModel(prefs, useCase)
		verify(useCase).loadUserList()

		assertThat(viewModel.isLoading.value, `is`(false))
		assertThat(viewModel.errorMessage.value, `is`(testException.toString()))
	}
}