package com.drg.arch.mvvm.repository.nodes.auth

import com.drg.arch.mvvm.data.entity.remote.response.StatusResponse
import com.drg.arch.mvvm.repository.api.AuthApi
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by aleksey.stepanov
 * 5/8/18.
 */
class AuthRemoteRepositoryImpl @Inject constructor(private val authApi: AuthApi) : AuthRemoteRepository {

	override fun login(): Single<StatusResponse> = authApi.login()
}