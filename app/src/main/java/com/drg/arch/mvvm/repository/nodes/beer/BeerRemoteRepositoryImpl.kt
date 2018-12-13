package com.drg.arch.mvvm.repository.nodes.beer

import com.drg.arch.mvvm.repository.api.MainApi
import javax.inject.Inject

class BeerRemoteRepositoryImpl @Inject constructor(private val mainApi: MainApi) : BeerRemoteRepository {

	override fun loadBeerList() = mainApi.loadBeerList()
}