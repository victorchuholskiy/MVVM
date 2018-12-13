package com.drg.arch.mvvm.repository.nodes.map

import com.drg.arch.mvvm.data.entity.remote.common.MapRemote
import io.reactivex.Single

/**
 * Created by aleksey.stepanov
 * 5/4/18.
 */

interface MapRemoteRepository {
	fun loadMarkersList(): Single<List<MapRemote>>
}