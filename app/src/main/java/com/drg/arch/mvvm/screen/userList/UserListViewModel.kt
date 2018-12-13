package com.drg.arch.mvvm.screen.userList

import android.arch.lifecycle.MutableLiveData
import com.drg.arch.mvvm.ViewModelProviderFactory
import com.drg.arch.mvvm.data.entity.remote.common.UserRemote
import com.drg.arch.mvvm.extension.applySchedulers
import com.drg.arch.mvvm.repository.Prefs
import com.drg.arch.mvvm.screen.base.BaseViewModel
import com.drg.arch.mvvm.usecase.user.LoadUserListUseCase

/**
 * Created by aleksey.stepanov
 * 8/9/18.
 */
class UserListViewModel(private val prefs: Prefs,
						private val loadUserListUseCase: LoadUserListUseCase) : BaseViewModel() {

	val userList = MutableLiveData<List<UserRemote>>()

	init {
		loadUserList()
	}

	private fun loadUserList() {
		println("=========>>>>>>> ${prefs.appVersion}")

		isLoading.value = true
		disposables.add(
				loadUserListUseCase.loadUserList()
						.applySchedulers()
						.subscribe(
								{
									isLoading.value = false
									userList.value = it
								},
								{
									isLoading.value = false
									errorMessage.value = it.toString()
								}
						))
	}

	/* data types */

	class Factory(viewModel: UserListViewModel) : ViewModelProviderFactory<UserListViewModel>(viewModel)
}