package com.drg.arch.mvvm.screens.beer

import android.arch.lifecycle.ViewModelProvider
import com.drg.arch.mvvm.di.scope.ActivityScope
import com.drg.arch.mvvm.screen.beer.list.BeerListViewModel
import com.drg.arch.mvvm.usecase.beer.LoadBeerListUseCase
import com.drg.arch.mvvm.usecase.beer.LoadBeerListUseCaseImpl
import com.drg.arch.mvvm.utils.ViewModelUtil
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

/**
 * Created by aleksey.stepanov
 * 9/4/18
 */

@Module
abstract class MockedBeerListActivityModule {

	@Module
	companion object {

		val viewModel: BeerListViewModel = Mockito.mock(BeerListViewModel::class.java)

		@Provides
		@ActivityScope
		@JvmStatic
		fun provideViewModelFactory(loadBeerListUseCase: LoadBeerListUseCase) : ViewModelProvider.Factory {
			return ViewModelUtil.createFor(viewModel)
		}
	}

	@Binds
	@ActivityScope
	abstract fun provideLoadBeerListUseCase(useCase: LoadBeerListUseCaseImpl): LoadBeerListUseCase
}