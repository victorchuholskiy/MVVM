package com.drg.arch.mvvm.screen.map

import android.arch.lifecycle.ViewModelProvider
import com.drg.arch.mvvm.di.scope.ActivityScope
import com.drg.arch.mvvm.usecase.map.LoadMarkersUseCase
import com.drg.arch.mvvm.usecase.map.LoadMarkersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by viktor.chukholskiy
 * 11/23/18.
 */
@Module
abstract class MapActivityModule {

	@Module
	companion object {

		@Provides
		@ActivityScope
		@JvmStatic
		fun provideViewModelFactory(loadMarkersUseCase: LoadMarkersUseCase) = MapViewModel.Factory(MapViewModel(loadMarkersUseCase)) as ViewModelProvider.Factory

	}

	@Binds
	@ActivityScope
	abstract fun bindLoadMarkersUseCase(loadMarkersUseCase: LoadMarkersUseCaseImpl): LoadMarkersUseCase
}