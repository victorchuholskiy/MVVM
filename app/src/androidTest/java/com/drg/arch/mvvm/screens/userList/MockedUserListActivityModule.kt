package com.drg.arch.mvvm.screens.userList

import android.arch.lifecycle.ViewModelProvider
import com.drg.arch.mvvm.di.scope.ActivityScope
import com.drg.arch.mvvm.repository.Prefs
import com.drg.arch.mvvm.screen.userList.UserListViewModel
import com.drg.arch.mvvm.usecase.user.LoadUserListUseCase
import com.drg.arch.mvvm.usecase.user.LoadUserListUseCaseImpl
import com.drg.arch.mvvm.utils.ViewModelUtil
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

/**
 * Created by aleksey.stepanov
 * 8/13/18.
 */

@Module
abstract class MockedUserListActivityModule {

	@Module
	companion object {

		val viewModel: UserListViewModel = Mockito.mock(UserListViewModel::class.java)

		val loadUserUseCase: LoadUserListUseCase = Mockito.mock(LoadUserListUseCase::class.java)

		@Provides
		@ActivityScope
		@JvmStatic
		fun provideViewModelFactory(prefs: Prefs,
									useCase: LoadUserListUseCaseImpl) : ViewModelProvider.Factory {
			return ViewModelUtil.createFor(viewModel)
		}

		@JvmStatic
		@Provides
		@ActivityScope
		fun provideLoadUserListUseCase(): LoadUserListUseCase {
			return loadUserUseCase
		}
	}
}