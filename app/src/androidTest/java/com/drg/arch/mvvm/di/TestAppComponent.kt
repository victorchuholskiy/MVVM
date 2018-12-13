package com.drg.arch.mvvm.di

import android.app.Application
import com.drg.arch.mvvm.App
import com.drg.arch.mvvm.di.module.AppModule
import com.drg.arch.mvvm.di.module.MockedActivityModule
import com.drg.arch.mvvm.di.module.RepositoryModule
import com.drg.arch.mvvm.di.module.RetrofitModule
import com.drg.arch.mvvm.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by viktor.chukholskiy
 * 9/20/18.
 */

@AppScope
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, MockedActivityModule::class, RetrofitModule::class, RepositoryModule::class])
interface TestAppComponent : AppComponent {

	override fun inject(app: App)

	override fun inject(instance: DaggerApplication)

	@Component.Builder
	interface Builder {
		@BindsInstance
		fun application(application: Application): Builder

		fun build(): TestAppComponent
	}
}