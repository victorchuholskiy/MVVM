package com.drg.arch.mvvm.screen.login

import android.arch.lifecycle.ViewModelProvider
import com.drg.arch.mvvm.di.scope.ActivityScope
import com.drg.arch.mvvm.usecase.login.LoginUseCase
import com.drg.arch.mvvm.usecase.login.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by aleksey.stepanov
 * 8/15/18.
 */

@Module
abstract class LoginActivityModule {

	@Module
	companion object {

		@Provides
		@ActivityScope
		@JvmStatic
		fun provideViewModelFactory(loginUseCase: LoginUseCase) = LoginViewModel.Factory(LoginViewModel(loginUseCase)) as ViewModelProvider.Factory

	}

	@Binds
	@ActivityScope
	abstract fun bindLoginUseCase(loginUseCase: LoginUseCaseImpl): LoginUseCase
}