package com.drg.arch.mvvm.extension

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by aleksey.stepanov
 * 5/16/18.
 */

fun <T : Any?> ObservableEmitter<T>.handleError(throwable: Throwable) {
	if (!isDisposed) {
		onError(throwable)
	}
}

fun <T> Observable<T>.applySchedulers(scheduler: Scheduler = Schedulers.io()) = subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())!!
fun <T> Single<T>.applySchedulers(scheduler: Scheduler = Schedulers.io()) = subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())!!
