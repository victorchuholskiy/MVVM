package com.drg.arch.mvvm.screen.dashboard.pages.settings

import com.drg.arch.mvvm.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by aleksey.stepanov
 * 8/15/18.
 */

@Module
abstract class SettingsFragmentModule {

	@Module
	companion object {

		@Provides
		@FragmentScope
		@JvmStatic
		fun provideViewModelFactory() = SettingsFragmentViewModel.Factory(SettingsFragmentViewModel())
	}

}