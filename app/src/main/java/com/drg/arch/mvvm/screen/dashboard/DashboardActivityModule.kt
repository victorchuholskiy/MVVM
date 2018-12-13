package com.drg.arch.mvvm.screen.dashboard

import com.drg.arch.mvvm.di.scope.ActivityScope
import com.drg.arch.mvvm.di.scope.FragmentScope
import com.drg.arch.mvvm.screen.dashboard.pages.home.HomeFragment
import com.drg.arch.mvvm.screen.dashboard.pages.home.HomeFragmentModule
import com.drg.arch.mvvm.screen.dashboard.pages.settings.SettingsFragment
import com.drg.arch.mvvm.screen.dashboard.pages.settings.SettingsFragmentModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by aleksey.stepanov
 * 8/15/18.
 */

@Module
abstract class DashboardActivityModule {

	@Module
	companion object {

		@Provides
		@ActivityScope
		@JvmStatic
		fun provideViewModelFactory() = DashboardActivityViewModel.Factory(DashboardActivityViewModel())

	}

	@FragmentScope
	@ContributesAndroidInjector(modules = [HomeFragmentModule::class])
	abstract fun homeFragment(): HomeFragment

	@FragmentScope
	@ContributesAndroidInjector(modules = [SettingsFragmentModule::class])
	abstract fun settingsFragment(): SettingsFragment
}