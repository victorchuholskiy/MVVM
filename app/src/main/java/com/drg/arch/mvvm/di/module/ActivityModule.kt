package com.drg.arch.mvvm.di.module

import com.drg.arch.mvvm.di.scope.ActivityScope
import com.drg.arch.mvvm.screen.beer.list.BeerListActivity
import com.drg.arch.mvvm.screen.beer.list.BeerListActivityModule
import com.drg.arch.mvvm.screen.dashboard.DashboardActivity
import com.drg.arch.mvvm.screen.dashboard.DashboardActivityModule
import com.drg.arch.mvvm.screen.encrypt.EncryptActivity
import com.drg.arch.mvvm.screen.encrypt.EncryptActivityModule
import com.drg.arch.mvvm.screen.login.LoginActivity
import com.drg.arch.mvvm.screen.login.LoginActivityModule
import com.drg.arch.mvvm.screen.map.MapActivity
import com.drg.arch.mvvm.screen.map.MapActivityModule
import com.drg.arch.mvvm.screen.userList.UserListActivity
import com.drg.arch.mvvm.screen.userList.UserListActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by aleksey.stepanov
 * 8/13/18.
 */

@Module
abstract class ActivityModule {

	@ActivityScope
	@ContributesAndroidInjector(modules = [LoginActivityModule::class])
	abstract fun loginActivity(): LoginActivity

	@ActivityScope
	@ContributesAndroidInjector(modules = [DashboardActivityModule::class])
	abstract fun dashboardActivity(): DashboardActivity

	@ActivityScope
	@ContributesAndroidInjector(modules = [UserListActivityModule::class])
	abstract fun userListActivity(): UserListActivity

	@ActivityScope
	@ContributesAndroidInjector(modules = [BeerListActivityModule::class])
	abstract fun beerListActivity(): BeerListActivity

	@ActivityScope
	@ContributesAndroidInjector(modules = [EncryptActivityModule::class])
	abstract fun encryptActivity(): EncryptActivity

	@ActivityScope
	@ContributesAndroidInjector(modules = [MapActivityModule::class])
	abstract fun mapActivity(): MapActivity
}