package com.drg.arch.mvvm.screen.userList

import android.arch.lifecycle.ViewModelProvider
import com.drg.arch.mvvm.di.scope.ActivityScope
import com.drg.arch.mvvm.repository.Prefs
import com.drg.arch.mvvm.usecase.user.LoadUserListUseCase
import com.drg.arch.mvvm.usecase.user.LoadUserListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by aleksey.stepanov
 * 8/13/18.
 */

@Module
abstract class UserListActivityModule {

	@Module
	companion object {

		@Provides
		@ActivityScope
		@JvmStatic
		fun provideViewModelFactory(prefs: Prefs,
									useCase: LoadUserListUseCase) = UserListViewModel.Factory(UserListViewModel(prefs, useCase)) as ViewModelProvider.Factory

	}

	@Binds
	@ActivityScope
	abstract fun provideLoadUserListUseCase(useCase: LoadUserListUseCaseImpl): LoadUserListUseCase

}