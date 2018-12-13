package com.drg.arch.mvvm

import android.content.Context
import android.os.StrictMode
import android.support.multidex.MultiDex
import com.crashlytics.android.Crashlytics
import com.drg.arch.mvvm.di.DaggerAppComponent
import dagger.android.DaggerApplication
import io.fabric.sdk.android.Fabric


/**
 * Created by aleksey.stepanov
 * 8/9/18.
 */
class App : DaggerApplication() {

	companion object {
		var app: App? = null
	}

	override fun onCreate() {
		super.onCreate()

		app = this

		if (BuildConfig.DEBUG) {
			StrictMode.enableDefaults()
		}

		Fabric.with(this, Crashlytics())
	}

	override fun applicationInjector() = DaggerAppComponent.builder().app(this).build()

	override fun attachBaseContext(base: Context) {
		super.attachBaseContext(base)
		MultiDex.install(this)
	}
}