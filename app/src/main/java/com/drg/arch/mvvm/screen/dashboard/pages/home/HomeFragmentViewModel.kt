package com.drg.arch.mvvm.screen.dashboard.pages.home

import android.arch.lifecycle.MutableLiveData
import com.drg.arch.mvvm.ViewModelProviderFactory
import com.drg.arch.mvvm.screen.base.BaseViewModel
import com.drg.arch.mvvm.util.SingleLiveEvent

/**
 * Created by aleksey.stepanov
 * 8/10/18.
 */
class HomeFragmentViewModel : BaseViewModel() {

	val goToUserList = SingleLiveEvent<Boolean>()
	val goToBeerList = SingleLiveEvent<Boolean>()
	val goToEncrypt = SingleLiveEvent<Boolean>()
	val goToMap = SingleLiveEvent<Boolean>()

	val homeText = MutableLiveData<String>().apply {
		value = "Some home init text"
	}

	/* own methods */

	fun onDoBtnClick() {
		homeText.value = "${System.currentTimeMillis()}ms"
	}

	fun onUserListBtnClick() {
		goToUserList.value = true
	}

	fun onBeerListBtnClick() {
		goToBeerList.value = true
	}

	fun onEncryptBtnClick() {
		goToEncrypt.value = true
	}

	fun onMapBtnClick() {
		goToMap.value = true
	}

	/* data types */

	class Factory(viewModel: HomeFragmentViewModel) : ViewModelProviderFactory<HomeFragmentViewModel>(viewModel)
}