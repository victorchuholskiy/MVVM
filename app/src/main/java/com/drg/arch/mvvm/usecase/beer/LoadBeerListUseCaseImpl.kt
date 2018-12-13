package com.drg.arch.mvvm.usecase.beer

import com.drg.arch.mvvm.repository.nodes.beer.BeerRemoteRepository
import javax.inject.Inject

class LoadBeerListUseCaseImpl @Inject constructor(private val remoteRepository: BeerRemoteRepository) : LoadBeerListUseCase {

	override fun loadBeerList() = remoteRepository.loadBeerList()
}