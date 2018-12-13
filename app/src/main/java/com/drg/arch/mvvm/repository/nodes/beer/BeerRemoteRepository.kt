package com.drg.arch.mvvm.repository.nodes.beer

import com.drg.arch.mvvm.data.entity.remote.common.BeerRemote
import io.reactivex.Single

interface BeerRemoteRepository {

	fun loadBeerList(): Single<List<BeerRemote>>
}