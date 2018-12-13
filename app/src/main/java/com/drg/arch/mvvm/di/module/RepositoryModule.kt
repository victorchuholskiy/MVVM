package com.drg.arch.mvvm.di.module

import com.drg.arch.mvvm.repository.nodes.auth.AuthRemoteRepository
import com.drg.arch.mvvm.repository.nodes.auth.AuthRemoteRepositoryImpl
import com.drg.arch.mvvm.repository.nodes.beer.BeerRemoteRepository
import com.drg.arch.mvvm.repository.nodes.beer.BeerRemoteRepositoryImpl
import com.drg.arch.mvvm.repository.nodes.map.MapRemoteRepository
import com.drg.arch.mvvm.repository.nodes.map.MapRemoteRepositoryImpl
import com.drg.arch.mvvm.repository.nodes.user.UserRemoteRepository
import com.drg.arch.mvvm.repository.nodes.user.UserRemoteRepositoryImpl
import dagger.Binds
import dagger.Module

/**
 * Created by aleksey.stepanov
 * 8/15/18.
 */

@Module
abstract class RepositoryModule {

	@Binds
	abstract fun bindAuthRemoteRepository(repository: AuthRemoteRepositoryImpl): AuthRemoteRepository

	@Binds
	abstract fun bindUserRemoteRepository(repository: UserRemoteRepositoryImpl): UserRemoteRepository

	@Binds
	abstract fun bindBeerRemoteRepository(repository: BeerRemoteRepositoryImpl): BeerRemoteRepository

	@Binds
	abstract fun bindMapRemoteRepository(repository: MapRemoteRepositoryImpl): MapRemoteRepository
}