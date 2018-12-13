package com.drg.arch.mvvm.screen.encrypt

import android.arch.lifecycle.ViewModelProvider
import com.drg.arch.mvvm.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by aleksey.stepanov
 * 8/15/18.
 */

@Module
abstract class EncryptActivityModule {

	@Module
	companion object {

		@Provides
		@ActivityScope
		@JvmStatic
		fun provideViewModelFactory() = EncryptViewModel.Factory(EncryptViewModel()) as ViewModelProvider.Factory

	}
}