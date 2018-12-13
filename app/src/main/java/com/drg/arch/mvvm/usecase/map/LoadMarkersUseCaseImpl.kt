package com.drg.arch.mvvm.usecase.map

import com.drg.arch.mvvm.repository.nodes.map.MapRemoteRepository
import javax.inject.Inject

/**
 * Created by aleksey.stepanov
 * 8/13/18.
 */
class LoadMarkersUseCaseImpl @Inject constructor(private val remoteRepository: MapRemoteRepository) : LoadMarkersUseCase {

	override fun loadMarkersList() = remoteRepository.loadMarkersList()
}