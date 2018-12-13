package com.drg.arch.mvvm.usecase.map

import com.drg.arch.mvvm.data.entity.remote.common.MapRemote
import io.reactivex.Single

/**
 * Created by aleksey.stepanov
 * 8/13/18.
 */
interface LoadMarkersUseCase {

	fun loadMarkersList(): Single<List<MapRemote>>
}