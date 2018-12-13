package com.drg.arch.mvvm.repository.api

import com.drg.arch.mvvm.data.entity.remote.common.MapRemote
import io.reactivex.Single
import retrofit2.http.GET

interface MapApi {

	@GET("/map")
	fun loadMarkersList(): Single<List<MapRemote>>

}