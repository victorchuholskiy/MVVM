package com.drg.arch.mvvm

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Created by aleksey.stepanov
 * 8/15/18.
 */
class RxTestRule : TestRule {

	override fun apply(base: Statement,
					   description: Description?) = object : Statement() {
		override fun evaluate() {
			RxJavaPlugins.reset()
			RxAndroidPlugins.reset()
			RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
			RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
			RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
			RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

			try {
				base.evaluate()
			} finally {
				RxJavaPlugins.reset()
				RxAndroidPlugins.reset()
			}
		}
	}
}
