package com.drg.arch.mvvm.repository.nodes.user

import com.drg.arch.mvvm.repository.api.MainApi
import javax.inject.Inject

/**
 * Created by aleksey.stepanov
 * 5/4/18.
 */
class UserRemoteRepositoryImpl @Inject constructor(private val mainApi: MainApi) : UserRemoteRepository {

	override fun loadUserList() = mainApi.loadUserList()

}