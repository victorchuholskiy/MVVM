package com.drg.arch.mvvm.di.module

import android.app.Application
import com.drg.arch.mvvm.di.scope.AppScope
import com.drg.arch.mvvm.repository.Prefs
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides

/**
 * Created by aleksey.stepanov
 * 8/13/18.
 */

@Module
object AppModule {

	@JvmStatic
	@Provides
	@AppScope
	fun provideGson() = GsonBuilder().create()!!

	@JvmStatic
	@Provides
	@AppScope
	fun providePrefs(app: Application) = Prefs(app)

}