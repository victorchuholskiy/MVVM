package com.drg.arch.mvvm.screen.beer.list

import android.arch.lifecycle.ViewModelProvider
import com.drg.arch.mvvm.di.scope.ActivityScope
import com.drg.arch.mvvm.usecase.beer.LoadBeerListUseCase
import com.drg.arch.mvvm.usecase.beer.LoadBeerListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by aleksey.stepanov
 * 9/4/18
 */

@Module
abstract class BeerListActivityModule {

	@Module
	companion object {

		@Provides
		@ActivityScope
		@JvmStatic
		fun provideViewModelFactory(loadBeerListUseCase: LoadBeerListUseCase) = BeerListViewModel.Factory(BeerListViewModel(loadBeerListUseCase)) as ViewModelProvider.Factory
	}

	@Binds
	@ActivityScope
	abstract fun provideLoadBeerListUseCase(useCase: LoadBeerListUseCaseImpl): LoadBeerListUseCase
}