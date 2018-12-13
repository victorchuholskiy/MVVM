package com.drg.arch.mvvm.screen.dashboard.pages.home

import com.drg.arch.mvvm.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by aleksey.stepanov
 * 8/15/18.
 */

@Module
abstract class HomeFragmentModule {

	@Module
	companion object {

		@Provides
		@FragmentScope
		@JvmStatic
		fun provideViewModelFactory() = HomeFragmentViewModel.Factory(HomeFragmentViewModel())
	}
}