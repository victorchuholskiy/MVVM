package com.drg.arch.mvvm.repository.nodes.map

import com.drg.arch.mvvm.repository.api.MapApi
import javax.inject.Inject

/**
 * Created by aleksey.stepanov
 * 5/4/18.
 */
class MapRemoteRepositoryImpl @Inject constructor(private val mapApi: MapApi) : MapRemoteRepository {
	override fun loadMarkersList() = mapApi.loadMarkersList()
}