package com.drg.arch.mvvm.di.module

import com.drg.arch.mvvm.di.scope.ActivityScope
import com.drg.arch.mvvm.screen.beer.list.BeerListActivity
import com.drg.arch.mvvm.screen.dashboard.DashboardActivity
import com.drg.arch.mvvm.screen.dashboard.DashboardActivityModule
import com.drg.arch.mvvm.screen.login.LoginActivity
import com.drg.arch.mvvm.screen.userList.UserListActivity
import com.drg.arch.mvvm.screens.beer.MockedBeerListActivityModule
import com.drg.arch.mvvm.screens.login.MockedLoginActivityModule
import com.drg.arch.mvvm.screens.userList.MockedUserListActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by viktor.chukholskiy
 * 9/20/18.
 */
@Module
abstract class MockedActivityModule {

	@ActivityScope
	@ContributesAndroidInjector(modules = [MockedLoginActivityModule::class])
	abstract fun loginActivity(): LoginActivity

	@ActivityScope
	@ContributesAndroidInjector(modules = [DashboardActivityModule::class])
	abstract fun dashboardActivity(): DashboardActivity

	@ActivityScope
	@ContributesAndroidInjector(modules = [MockedUserListActivityModule::class])
	abstract fun userListActivity(): UserListActivity

	@ActivityScope
	@ContributesAndroidInjector(modules = [MockedBeerListActivityModule::class])
	abstract fun beerListActivity(): BeerListActivity
}