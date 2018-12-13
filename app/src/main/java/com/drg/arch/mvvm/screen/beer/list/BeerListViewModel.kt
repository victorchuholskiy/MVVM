package com.drg.arch.mvvm.screen.beer.list

import android.arch.lifecycle.MutableLiveData
import com.drg.arch.mvvm.ViewModelProviderFactory
import com.drg.arch.mvvm.data.entity.remote.common.BeerRemote
import com.drg.arch.mvvm.extension.applySchedulers
import com.drg.arch.mvvm.screen.base.BaseViewModel
import com.drg.arch.mvvm.usecase.beer.LoadBeerListUseCase

class BeerListViewModel(private val loadBeerListUseCase: LoadBeerListUseCase) : BaseViewModel() {

	val beerList = MutableLiveData<List<BeerRemote>>()

	init {
		loadBeerList()
	}

	private fun loadBeerList() {

		isLoading.value = true
		disposables.add(
				loadBeerListUseCase.loadBeerList()
						.applySchedulers()
						.doFinally { isLoading.value = false }
						.subscribe(
								{
									beerList.value = it
								},
								{
									errorMessage.value = it.toString()
								}
						)
		)
	}

	/* data types */

	class Factory(viewModel: BeerListViewModel) : ViewModelProviderFactory<BeerListViewModel>(viewModel)
}