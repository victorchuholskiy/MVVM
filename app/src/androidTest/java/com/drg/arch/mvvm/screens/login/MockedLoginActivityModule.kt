package com.drg.arch.mvvm.screens.login

import android.arch.lifecycle.ViewModelProvider
import com.drg.arch.mvvm.di.scope.ActivityScope
import com.drg.arch.mvvm.screen.login.LoginViewModel
import com.drg.arch.mvvm.usecase.login.LoginUseCase
import com.drg.arch.mvvm.usecase.login.LoginUseCaseImpl
import com.drg.arch.mvvm.utils.ViewModelUtil
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

/**
 * Created by viktor.chukholskiy
 * 9/20/18.
 */
@Module
abstract class MockedLoginActivityModule {

	@Module
	companion object {

		val viewModel: LoginViewModel = Mockito.mock(LoginViewModel::class.java)

		@Provides
		@ActivityScope
		@JvmStatic
		fun provideViewModelFactory(loginUseCase: LoginUseCase) : ViewModelProvider.Factory {
			return ViewModelUtil.createFor(viewModel)
		}

	}

	@Binds
	@ActivityScope
	abstract fun bindLoginUseCase(loginUseCase: LoginUseCaseImpl): LoginUseCase
}