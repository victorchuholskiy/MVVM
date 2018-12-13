package com.drg.arch.mvvm.screen.login

import android.arch.lifecycle.MutableLiveData
import com.drg.arch.mvvm.ViewModelProviderFactory
import com.drg.arch.mvvm.extension.applySchedulers
import com.drg.arch.mvvm.screen.base.BaseViewModel
import com.drg.arch.mvvm.usecase.login.LoginUseCase
import com.drg.arch.mvvm.util.SingleLiveEvent

/**
 * Created by aleksey.stepanov
 * 8/3/18.
 */
class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

	val isAuthorized = SingleLiveEvent<Boolean>()
	val login = MutableLiveData<String>()
	val loginError = MutableLiveData<String>()
	val password = MutableLiveData<String>()
	val passwordError = MutableLiveData<String>()

	fun onLoginClick() {

		var pass = true

		if (login.value.isNullOrBlank()) {
			loginError.value = "Empty login"
			pass = false
		} else {
			loginError.value = null
		}

		if (password.value.isNullOrBlank()) {
			passwordError.value = "Empty pass"
			pass = false
		} else {
			passwordError.value = null
		}

		if (!pass) {
			return
		}

		isLoading.value = true

		disposables.add(
				loginUseCase.login()
						.applySchedulers()
						.subscribe(
								{
									isLoading.value = false
									isAuthorized.value = true
								},
								{
									isLoading.value = false
									errorMessage.value = it.toString()
								})
		)
	}

	/* data types */

	class Factory(viewModel: LoginViewModel) : ViewModelProviderFactory<LoginViewModel>(viewModel)
}