package com.drg.arch.mvvm.screen.map

import android.arch.lifecycle.MutableLiveData
import com.drg.arch.mvvm.ViewModelProviderFactory
import com.drg.arch.mvvm.data.entity.remote.common.MapRemote
import com.drg.arch.mvvm.extension.applySchedulers
import com.drg.arch.mvvm.screen.base.BaseViewModel
import com.drg.arch.mvvm.usecase.map.LoadMarkersUseCase


/**
 * Created by viktor.chukholskiy
 * 11/23/18.
 */
class MapViewModel(private val loadMarkersUseCase: LoadMarkersUseCase) : BaseViewModel() {

	var markers = MutableLiveData<List<MapRemote>>()

	/* own methods */

	fun loadMarkers() {
		disposables.add(
				loadMarkersUseCase.loadMarkersList()
						.applySchedulers()
						.subscribe(
								{ markers.value = it },
								{ markers.value = ArrayList() }
						))
	}

	/* data types */

	class Factory(viewModel: MapViewModel) : ViewModelProviderFactory<MapViewModel>(viewModel)
}