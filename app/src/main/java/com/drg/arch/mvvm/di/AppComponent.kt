package com.drg.arch.mvvm.di

import android.app.Application
import com.drg.arch.mvvm.App
import com.drg.arch.mvvm.di.module.ActivityModule
import com.drg.arch.mvvm.di.module.AppModule
import com.drg.arch.mvvm.di.module.RepositoryModule
import com.drg.arch.mvvm.di.module.RetrofitModule
import com.drg.arch.mvvm.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by aleksey.stepanov
 * 8/13/18.
 */
@AppScope
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityModule::class, RetrofitModule::class, RepositoryModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

	fun inject(app: App)

	override fun inject(instance: DaggerApplication)

	@Component.Builder
	interface Builder {

		@BindsInstance
		fun app(app: Application): Builder

		fun build(): AppComponent
	}
}