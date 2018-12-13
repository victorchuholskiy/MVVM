package com.drg.arch.mvvm.screen.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.drg.arch.mvvm.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by aleksey.stepanov
 * 8/9/18.
 */
abstract class BaseViewModel : ViewModel() {

	protected val disposables = CompositeDisposable()

	val isLoading = MutableLiveData<Boolean>()
	val errorMessage = SingleLiveEvent<String?>()

	override fun onCleared() {
		disposables.clear()
		super.onCleared()
	}
}