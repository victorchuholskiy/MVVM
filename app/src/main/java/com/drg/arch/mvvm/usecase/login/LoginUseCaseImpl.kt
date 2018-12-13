package com.drg.arch.mvvm.usecase.login

import com.drg.arch.mvvm.repository.nodes.auth.AuthRemoteRepository
import javax.inject.Inject

/**
 * Created by aleksey.stepanov
 * 8/15/18.
 */
class LoginUseCaseImpl @Inject constructor(private val remoteRepository: AuthRemoteRepository) : LoginUseCase {

	override fun login() = remoteRepository.login()
}