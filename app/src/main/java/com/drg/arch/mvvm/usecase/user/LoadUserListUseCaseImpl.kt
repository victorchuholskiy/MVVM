package com.drg.arch.mvvm.usecase.user

import com.drg.arch.mvvm.repository.nodes.user.UserRemoteRepository
import javax.inject.Inject

/**
 * Created by aleksey.stepanov
 * 8/13/18.
 */
class LoadUserListUseCaseImpl @Inject constructor(private val remoteRepository: UserRemoteRepository) : LoadUserListUseCase {

	override fun loadUserList() = remoteRepository.loadUserList()
}