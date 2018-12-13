package com.drg.arch.mvvm.usecase.beer

import com.drg.arch.mvvm.data.entity.remote.common.BeerRemote
import io.reactivex.Single

interface LoadBeerListUseCase {

	fun loadBeerList() : Single<List<BeerRemote>>
}